package com.htc.trainingMgt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	
	@Id
	private long empId;
	private String empName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Skill> skillList;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private Set<Allocation> allocationList;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Training> trainings;

	
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", skillList=" + skillList + ", allocationList="
				+ allocationList + ", trainings=" + trainings + "]";
	}



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



	public Set<Training> getTrainings() {
		return trainings;
	}



	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}
	
	
}
