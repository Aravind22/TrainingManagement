package com.htc.trainingMgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Allocation;

@Repository
public interface AllocationDao extends JpaRepository<Allocation, Long> {

}
