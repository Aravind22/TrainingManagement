package com.assessment.apiGateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assessment.apiGateway.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
	@Query("SELECT e FROM Employee e JOIN FETCH e.skillList WHERE e.empId = ?1")
	Employee findEmployeeWithSkills(Long empId);
}
