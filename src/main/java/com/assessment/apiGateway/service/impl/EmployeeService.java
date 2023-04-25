package com.assessment.apiGateway.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.EmployeeConverter;
import com.assessment.apiGateway.dao.EmployeeDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Skill;

@Service
public class EmployeeService {
	Logger logger = Logger.getLogger(EmployeeService.class.getName());
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeConverter empConverter;
	
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
