package com.assessment.apiGateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.apiGateway.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
