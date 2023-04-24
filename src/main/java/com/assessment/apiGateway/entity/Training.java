package com.assessment.apiGateway.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trainingID;
	
	@ManyToOne
	@JoinColumn(name = "skillId")
	private Skill skill;
	
	private long empId;
	
	@OneToMany
	private List<Allocation> allocationList;
	
	private String startDate;
	private String endDate;
	
	private int score;
	private boolean status;
	private String remarks;
	
	public Training(Skill skill, long empId, List<Allocation> allocationList, String startDate, String endDate,
			int score, boolean status, String remarks) {
		super();
		this.skill = skill;
		this.empId = empId;
		this.allocationList = allocationList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (trainingID ^ (trainingID >>> 32));
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
		Training other = (Training) obj;
		if (trainingID != other.trainingID)
			return false;
		return true;
	}

	
	
	
	
}
