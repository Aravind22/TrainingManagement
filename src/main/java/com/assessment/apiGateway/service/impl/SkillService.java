package com.assessment.apiGateway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.apiGateway.converter.SkillConverter;
import com.assessment.apiGateway.dao.SkillsDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Skill;

@Service
public class SkillService {

	@Autowired
	SkillsDao skillDao;
	
	@Autowired
	SkillConverter skillConverter;
	
	public boolean addSkill(SkillDto skillDto) {
		System.out.println("SKILLDTO"+skillDto.toString());
		Skill skill = skillConverter.convertToEntity(skillDto);
		if(skillDao.save(skill) != null) return true;
		return false;
	}
	
	public List<Skill> getAllSkills(){
		return skillDao.findAll();
	}
	
	public SkillDto getSkillById(long id) {
		Optional<Skill> result = skillDao.findById(id);
		Skill skill = result.get();
		return skillConverter.convertToDto(skill);
	}
	
	public void deleteSkillById(long id) {
		skillDao.deleteById(id);
	}
}
