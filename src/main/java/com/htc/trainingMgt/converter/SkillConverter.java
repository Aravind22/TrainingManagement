package com.htc.trainingMgt.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.entity.Skill;

@Component
public class SkillConverter {
	
	public Skill convertToEntity(SkillDto skillDto) {
		Skill skill = new Skill();
		BeanUtils.copyProperties(skillDto, skill);
		return skill;
	}
	
	public SkillDto convertToDto(Skill skill) {
		SkillDto skillDTo = new SkillDto();
		BeanUtils.copyProperties(skill, skillDTo);
		return skillDTo;
	}

}
