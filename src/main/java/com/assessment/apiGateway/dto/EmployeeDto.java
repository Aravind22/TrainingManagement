package com.assessment.apiGateway.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDto {

	@NotNull(message = "Employee ID can't be null")
	private long empId;
	
	@NotNull(message = "Employee name can't be null")
	private String empName;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
}
