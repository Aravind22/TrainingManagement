package com.assessment.apiGateway.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Allocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long allocationId;
	
	@ManyToOne
	private Training training;
	
	@ManyToOne
	private Employee employee;
	private int score;
	private String status;
	private String remarks;

	
	
	public Allocation(Training training, Employee employee, int score, String status, String remarks) {
		super();
		this.training = training;
		this.employee = employee;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (allocationId ^ (allocationId >>> 32));
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
		Allocation other = (Allocation) obj;
		if (allocationId != other.allocationId)
			return false;
		return true;
	}
	
	
	

}
