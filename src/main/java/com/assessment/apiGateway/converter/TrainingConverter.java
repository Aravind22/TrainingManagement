package com.assessment.apiGateway.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;

@Component
public class TrainingConverter {

	public Training convertToEntity(TrainingDto trainingDto) {
		Training training = new Training();
		BeanUtils.copyProperties(trainingDto, training);
		return training;
	}
	
	public TrainingDto convertToDto(Training training) {
		TrainingDto trainingDto = new TrainingDto();
		BeanUtils.copyProperties(training, trainingDto);
		return trainingDto;
	}
}
