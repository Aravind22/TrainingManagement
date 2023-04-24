package com.assessment.apiGateway.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.AllocationConverter;
import com.assessment.apiGateway.converter.EmployeeConverter;
import com.assessment.apiGateway.converter.SkillConverter;
import com.assessment.apiGateway.converter.TrainingConverter;
import com.assessment.apiGateway.dao.AllocationDao;
import com.assessment.apiGateway.dao.EmployeeDao;
import com.assessment.apiGateway.dto.AllocationDto;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;

@Service
public class AllocationService {
	
	@Autowired
	AllocationDao allocatiDao;
	
	@Autowired
	AllocationConverter allocationConverter;
	
	@Autowired
	EmployeeConverter empConverter;
	
	@Autowired
	TrainingConverter trainingConverter;
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	SkillConverter skillConverter;
	
	Logger logger = Logger.getLogger(AllocationService.class.getSimpleName());
	
	public AllocationDto addAllocation(AllocationDto allocationDto) {
		Allocation allocation = new Allocation();
		BeanUtils.copyProperties(allocationDto, allocation);
		EmployeeDto empObj = empService.getEmployeeById(allocationDto.getEmpId());
		Skill skillObj = skillService.findBySkillName(allocationDto.getSkillName());
		Training trainObj = trainingService.findBySkill(skillObj);
		allocation.setEmployee(empConverter.convertToEntity(empObj));
		allocation.settraining(trainObj);
		if(allocatiDao.save(allocation) != null) {
			allocationDto.setAllocationId(allocation.getAllocationId());
			return allocationDto;
		}
		return null;
	}
	
	public List<Allocation> getAllAllocation(){
		return allocatiDao.findAll();
	}
	
	public void deleteAllocation(long id) {
		allocatiDao.deleteById(id);
	}
	
	public AllocationDto getAllocationById(long id) {
		AllocationDto allocationDto = new AllocationDto();
		Optional<Allocation> allocation = allocatiDao.findById(id);
		if(allocation.isPresent()) {
			BeanUtils.copyProperties(allocation, allocationDto);
			allocationDto.setAllocationId(allocation.get().getAllocationId());
			allocationDto.setStatus(allocation.get().getStatus());
			allocationDto.setScore(allocation.get().getScore());
			allocationDto.setRemarks(allocation.get().getRemarks());
			allocationDto.setEmpId(allocation.get().getEmployee().getEmpId());
			allocationDto.setSkillName(allocation.get().gettraining().getskill().getSkillName());
			return allocationDto;
		}
		return null;
	}
	
	public boolean allocateSkill(long allocationId, long empId, long skillId) {
		EmployeeDto empDto = empService.getEmployeeById(empId);
		Employee empObj = new Employee();
		BeanUtils.copyProperties(empDto, empObj);
		
		SkillDto skillDto = skillService.getSkillById(skillId);
		Skill skillObj = skillConverter.convertToEntity(skillDto);
		logger.info("SKILLOBJ::=>"+skillObj);
		
		
		Set<Skill> skillSet = empDto.getSkillSet();
		if (skillSet == null) {
			skillSet = new HashSet<Skill>();
		}
		skillSet.add(skillObj);
		empObj.setSkillList(skillSet);
		
		EmployeeDto employeeDto = empConverter.convertToDto(empObj);
		employeeDto.setSkillSet(empObj.getSkillList());
		
		logger.info("EMPLOYEEDTO::=>"+employeeDto);
		empService.addEmployee(employeeDto);
		
		Optional<Allocation> allocationObj = allocatiDao.findById(allocationId);
		if(allocationObj.isPresent()) {
			allocationObj.get().setStatus("TRAINING COMPLETED");
		}
		if(allocatiDao.save(allocationObj.get()) != null ) {
			return true;
		}
		return false;
	}
	
}
