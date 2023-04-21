package com.assessment.apiGateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.apiGateway.entity.Training;

@Repository
public interface TrainingDao extends JpaRepository<Training, Long> {

}
