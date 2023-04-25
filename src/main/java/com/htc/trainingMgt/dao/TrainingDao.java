package com.htc.trainingMgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;

@Repository
public interface TrainingDao extends JpaRepository<Training, Long> {
	public Training findBySkill(Skill skill);
}
