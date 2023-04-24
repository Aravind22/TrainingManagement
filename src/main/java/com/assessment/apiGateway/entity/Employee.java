package com.assessment.apiGateway.entity;

import java.util.Set;

import javax.persistence.Entity;
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
@ToString
@Getter
@Setter
public class Employee {
	
	@Id
	private long empId;
	private String empName;
	
	@ManyToMany
	private Set<Skill> skillList;
	
	@OneToMany
	private Set<Allocation> allocationList;

	public Employee(String empName, Set<Skill> skillList, Set<Allocation> allocationList) {
		super();
		this.empName = empName;
		this.skillList = skillList;
		this.allocationList = allocationList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (empId ^ (empId >>> 32));
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
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		return true;
	}
	
}
