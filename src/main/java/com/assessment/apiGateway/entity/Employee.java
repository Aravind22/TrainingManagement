package com.assessment.apiGateway.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	
	@Id
	private long empId;
	private String empName;
	
	@ManyToMany
	private Set<Skill> skillList;
	
	@OneToMany
	private Set<Allocation> allocationList;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Set<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(Set<Skill> skillList) {
		this.skillList = skillList;
	}

	public Set<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(Set<Allocation> allocationList) {
		this.allocationList = allocationList;
	}

	public Employee(long empId, String empName, Set<Skill> skillList, Set<Allocation> allocationList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.skillList = skillList;
		this.allocationList = allocationList;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", skillList=" + skillList + ", allocationList="
				+ allocationList + "]";
	}
		
}
