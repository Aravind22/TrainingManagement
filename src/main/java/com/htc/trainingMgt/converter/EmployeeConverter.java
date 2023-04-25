package com.htc.trainingMgt.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.entity.Employee;

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
