package com.htc.trainingMgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Skill;

@Repository
public interface SkillsDao extends JpaRepository<Skill, Long> {
	
	public List<Skill> findBySkillNameAndCategory(String skillName, String category);
	public List<Skill> findBySkillName(String skillName);
	public List<Skill> findByCategory(String category);
}
