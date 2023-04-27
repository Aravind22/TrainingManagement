package com.htc.trainingMgt.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;

@Repository
public interface TrainingDao extends JpaRepository<Training, Long> {
	public Training findBySkill(Skill skill);

	public List<Training> findByStartDateBetweenOrEndDateBetween(LocalDate startDate1, LocalDate startDate2,
			LocalDate endDate1, LocalDate endDate2);
	
	public List<Training> findByStartDateBetween(LocalDate startDate1, LocalDate startDate2);
	
	public List<Training> findByEndDateBetween(LocalDate endDate1, LocalDate endDate2);

}
