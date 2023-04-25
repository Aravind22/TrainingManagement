package com.htc.trainingMgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Skill;

@Repository
public interface SkillsDao extends JpaRepository<Skill, Long> {

	public Skill findBySkillName(String skill);
}
