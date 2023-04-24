package com.assessment.apiGateway.service.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.EmployeeConverter;
import com.assessment.apiGateway.dao.EmployeeDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;

@Service
public class EmployeeService {
	Logger logger = Logger.getLogger(EmployeeService.class.getName());
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeConverter empConverter;
	
	public EmployeeDto addEmployee(EmployeeDto emDto) {
		logger.info(emDto);
		logger.info("####################################");
		Employee emp = empConverter.convertToEntity(emDto);
		emp.setSkillList(emDto.getSkillSet());
		logger.info(emp);
		logger.info("====================================");
		if(empDao.save(emp) != null) {
			emDto.setEmpId(emp.getEmpId());
			return emDto;
		}
		return null;
	}
	
	public List<Employee> getAllEmployees() {
		logger.info(" EMP LIST ==================================== EMP LIST");
//		logger.info(empDao.findAll().get(0).toString()+"sssssss");
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
	
}
