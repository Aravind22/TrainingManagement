package com.assessment.apiGateway.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.assessment.apiGateway.dto.AllocationDto;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Employee;

@Component
public class AllocationConverter {
	
	public Allocation convertToEntity(AllocationDto allocationDTO) {
		Allocation allocation = new Allocation();
		BeanUtils.copyProperties(allocationDTO, allocation);
		return allocation;
	}
	
	public AllocationDto convertToDto(Allocation allocation) {
		AllocationDto allocationDto = new AllocationDto();
		BeanUtils.copyProperties(allocation, allocationDto);
		return allocationDto;
	}
}
