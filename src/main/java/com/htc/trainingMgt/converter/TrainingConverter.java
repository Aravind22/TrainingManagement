package com.htc.trainingMgt.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.dto.TrainingDto;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;

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
