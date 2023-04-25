package com.htc.trainingMgt.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.htc.trainingMgt.dto.AllocationDto;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.entity.Allocation;
import com.htc.trainingMgt.entity.Employee;

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
