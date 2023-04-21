package com.assessment.apiGateway.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeDto {

	@NotNull(message = "Employee ID can't be null")
	private long empId;
	
	@NotNull(message = "Employee name can't be null")
	private String empName;
	
	private String disabled;

}
