package com.assessment.apiGateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.EmployeeConverter;
import com.assessment.apiGateway.converter.SkillConverter;
import com.assessment.apiGateway.converter.TrainingConverter;
import com.assessment.apiGateway.dao.AllocationDao;
import com.assessment.apiGateway.dao.SkillsDao;
import com.assessment.apiGateway.dao.TrainingDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;

@Service
public class TrainingService {

	@Autowired
	TrainingDao trainingDao;
	
	@Autowired
	SkillsDao skillDao;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	AllocationDao allocationDao;
	
	@Autowired
	TrainingConverter trainingConverter;
	
	@Autowired
	SkillConverter skillConverter;
	
	@Autowired
	EmployeeConverter employeeConverter;
	
	@Autowired EmployeeService empService;
	
	public TrainingDto createTraining(TrainingDto trainingDto) {
		System.out.println("TrainingDTO"+trainingDto.toString());
		Skill skillObj = skillDao.findBySkillName(trainingDto.getSkill());
		if(skillObj == null) {
			return null;
		}
		Training training = trainingConverter.convertToEntity(trainingDto);
		training.setSkill(skillObj);
//		training.se
		System.out.println("TrainingSKILL"+skillObj.toString());
		if(trainingDao.save(training) != null) {
			trainingDto.setTrainingID(training.getTrainingID());
			EmployeeDto empData = empService.getEmployeeById(trainingDto.getEmpId());
			trainingDto.setTrainerName(empData.getEmpName());
			return trainingDto;
		}
		return null;
	}
	
	public Allocation createAllocation(Allocation allocation) {
		if(allocationDao.save(allocation) != null) {
			return allocation;
		}
		return null;
	}
	
	public List<Training> listAllTraining(){
		return trainingDao.findAll();
	}
	
	public List<String> listAllSkillNames() {
		List<Skill> skillList = skillDao.findAll();
		List<String> skillNames = new ArrayList<String>();
		for(int i=0;i<skillList.size();i++) {
			skillNames.add(skillList.get(i).getSkillName());
		}
		return skillNames;
	}
	
	public TrainingDto getTrainingById(long id) {
		Optional<Training> training = trainingDao.findById(id);
		Training trainingObj = training.get();
		TrainingDto trainDTO = new TrainingDto();
		BeanUtils.copyProperties(trainingObj, trainDTO);
		trainDTO.setSkill(trainingObj.getSkill().getSkillName());
		return trainDTO;
	}
	
	public void deleteTrainingById(long id) {
		trainingDao.deleteById(id);
	}
	
	public Training findBySkill(Skill skill) {
		return trainingDao.findBySkill(skill);
	}
}
