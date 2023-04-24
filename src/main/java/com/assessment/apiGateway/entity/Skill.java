package com.assessment.apiGateway.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long skillId;
	
	private String skillName;
	private String category;
	
	@ManyToMany
	Set<Employee> employeeList;
	
	@OneToMany
	@JoinColumn(name = "trainingID")
	private Set<Training> trainings;

	public Skill(String skillName, String category, Set<Employee> employeeList, Set<Training> trainings) {
		super();
		this.skillName = skillName;
		this.category = category;
		this.employeeList = employeeList;
		this.trainings = trainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (skillId ^ (skillId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (skillId != other.skillId)
			return false;
		return true;
	}
	
	
}
