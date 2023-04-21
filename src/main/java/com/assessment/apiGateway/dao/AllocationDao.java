package com.assessment.apiGateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.apiGateway.entity.Allocation;

@Repository
public interface AllocationDao extends JpaRepository<Allocation, Long> {

}
