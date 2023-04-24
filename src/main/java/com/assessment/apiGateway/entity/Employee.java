package com.assessment.apiGateway.entity;

import java.util.Set;

import javax.persistence.Entity;
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
@Getter
@Setter
public class Employee {
	
	@Id
	private long empId;
	private String empName;
	
	@ManyToMany
	private Set<Skill> skillList;
	
	@OneToMany(mappedBy = "employee")
	private Set<Allocation> allocationList;
	
	@OneToMany(mappedBy = "employee")
	private Set<Training> trainings;
}
