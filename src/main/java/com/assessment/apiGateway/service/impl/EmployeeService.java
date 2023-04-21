package com.assessment.apiGateway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.EmployeeConverter;
import com.assessment.apiGateway.dao.EmployeeDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeConverter empConverter;
	
	public EmployeeDto addEmployee(EmployeeDto emDto) {
		Employee emp = empConverter.convertToEntity(emDto);
		if(empDao.save(emp) != null) {
			emDto.setEmpId(emp.getEmpId());
			return emDto;
		}
		return null;
	}
	
	public List<Employee> getAllEmployees() {
		return empDao.findAll();
	}
	
	public EmployeeDto getEmployeeById(long id) {
		Optional<Employee> result = empDao.findById(id);
		Employee emp = result.get();
		return empConverter.convertToDto(emp);
	}
	
	public void deleteByEmployeeId(long id) {
		empDao.deleteById(id);
	}
	
}
