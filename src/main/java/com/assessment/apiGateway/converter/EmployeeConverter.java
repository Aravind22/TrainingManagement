package com.assessment.apiGateway.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;

@Component
public class EmployeeConverter {

	public Employee convertToEntity(EmployeeDto empDTO) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(empDTO, emp);
		return emp;
	}
	
	public EmployeeDto convertToDto(Employee emp) {
		EmployeeDto empDto = new EmployeeDto();
		BeanUtils.copyProperties(emp, empDto);
		return empDto;
	}
}
