package com.htc.trainingMgt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long skillId;
	
	private String skillName;
	private String category;
	
	@ManyToMany()
	Set<Employee> employeeList;
	
	@OneToMany(mappedBy = "skill") // casecade purpose is if we deleted relavent mapped data also will delete
	private Set<Training> trainings;

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Skill(String skillName, String category, Set<Employee> employeeList) {
		super();
		this.skillName = skillName;
		this.category = category;
		this.employeeList = employeeList;
	}

	public Skill() {
		super();
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + ", category=" + category + ", employeeList="
				+ employeeList + "]";
	}
	
	
}
