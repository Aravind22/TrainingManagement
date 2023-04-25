package com.htc.trainingMgt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htc.trainingMgt.converter.SkillConverter;
import com.htc.trainingMgt.dao.SkillsDao;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.entity.Employee;
import com.htc.trainingMgt.entity.Skill;

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
	
	public Skill findBySkillName(String skillName) {
		return skillDao.findBySkillName(skillName);
	}
}
