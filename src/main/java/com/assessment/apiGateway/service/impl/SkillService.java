package com.assessment.apiGateway.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.SkillConverter;
import com.assessment.apiGateway.dao.SkillsDao;
import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.entity.Skill;

@Service
public class SkillService {

	@Autowired
	SkillsDao skillDao;
	
	@Autowired
	SkillConverter skillConverter;
	
	public boolean addSkill(SkillDto skillDto) {
		Skill skill = skillConverter.convertToEntity(skillDto);
		if(skillDao.save(skill) != null) return true;
		return false;
	}
}
