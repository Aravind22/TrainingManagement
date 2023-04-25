package com.htc.trainingMgt.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.htc.trainingMgt.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
	@Query("SELECT e FROM Employee e JOIN FETCH e.skillList WHERE e.empId = ?1")
	Employee findEmployeeWithSkills(Long empId);
	
	List<Employee> findByEmpIdOrSkillListSkillIdIn(long empId, Set<Long> skillIds);
}
