package com.htc.trainingMgt.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htc.trainingMgt.converter.EmployeeConverter;
import com.htc.trainingMgt.dao.EmployeeDao;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.entity.Employee;
import com.htc.trainingMgt.entity.Skill;

@Service
public class EmployeeService {
	Logger logger = Logger.getLogger(EmployeeService.class.getName());
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeConverter empConverter;
	
	@Autowired
	private SkillService skillService;
	
	public EmployeeDto addEmployee(EmployeeDto emDto) {
		Employee emp = empConverter.convertToEntity(emDto);
		 Optional<Employee> exisingEmployee = empDao.findById(emDto.getEmpId());
		 if(exisingEmployee.isPresent()) {
			 Set<Skill> skillData = exisingEmployee.get().getSkillList();
			 emp.setSkillList(skillData);
		 }
		 else {
			 emp.setSkillList(emDto.getSkillSet());	 
		 }
		 if(emDto.getSkillIds() != null) {
			 Set<Skill> skills = skillService.findBySkillIds(emDto.getSkillIds());
			 emp.setSkillList(skills);
		 }
		if(empDao.save(emp) != null) {
			emDto.setEmpId(emp.getEmpId());
			return emDto;
		}
		return null;
	}
	
	public List<Employee> getAllEmployees() {
		logger.info(empDao.findAll());
		logger.info("==============getAllEmployees=============");
		return empDao.findAll();
	}
	
	public EmployeeDto getEmployeeById(long id) {
		Optional<Employee> result = empDao.findById(id);
		if(result.isPresent()) {
			Employee emp = result.get();
			EmployeeDto emDTO = empConverter.convertToDto(emp);	
			emDTO.setSkillSet(emp.getSkillList());
			return emDTO;
		}
		return null;
	}
	
	public void deleteByEmployeeId(long id) {
		empDao.deleteById(id);
	}
	
	public Set<Employee> filterEmployeeByIdAndSkillIds(long empId, Set<Long> skillIds) {
		List<Employee> data = empDao.findByEmpIdOrSkillListSkillIdIn(empId, skillIds);
		Set<Employee> empSet = new HashSet<Employee>();
		for(Employee e: data) {
			empSet.add(e);
		}
		return empSet;
	}
	
}
