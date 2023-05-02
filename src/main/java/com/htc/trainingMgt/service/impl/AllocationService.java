package com.htc.trainingMgt.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htc.trainingMgt.converter.AllocationConverter;
import com.htc.trainingMgt.converter.EmployeeConverter;
import com.htc.trainingMgt.converter.SkillConverter;
import com.htc.trainingMgt.converter.TrainingConverter;
import com.htc.trainingMgt.dao.AllocationDao;
import com.htc.trainingMgt.dao.EmployeeDao;
import com.htc.trainingMgt.dto.AllocationDto;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.dto.TrainingDto;
import com.htc.trainingMgt.entity.Allocation;
import com.htc.trainingMgt.entity.Employee;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;

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
		Training trainObj = trainingService.getTrainingByTrainingId(allocationDto.getTrainingId());
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
			allocationDto.setSkillName(allocation.get().gettraining().getSkill().getSkillName());
			allocationDto.setTrainingId(allocation.get().gettraining().getTrainingID());
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
