package com.assessment.apiGateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.TrainingConverter;
import com.assessment.apiGateway.dao.AllocationDao;
import com.assessment.apiGateway.dao.SkillsDao;
import com.assessment.apiGateway.dao.TrainingDao;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Training;

@Service
public class TrainingService {

	@Autowired
	TrainingDao trainingDao;
	
	@Autowired
	SkillsDao skillDao;
	
	@Autowired
	AllocationDao allocationDao;
	
	@Autowired
	TrainingConverter trainingConverter;
	
	public TrainingDto createTraining(TrainingDto trainingDto) {
		Training training = trainingConverter.convertToEntity(trainingDto);
		
		if(trainingDao.save(training) != null) {
			trainingDto.setTrainingId(training.getTrainingID());
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
}
