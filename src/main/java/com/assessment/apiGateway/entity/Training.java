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
	@JoinColumn(name = "trainingSet")
	private Skill skill;
	
	private long empId;
	
	@OneToMany
	private List<Allocation> allocationList;
	
	private String startDate;
	private String endDate;
	
	private int score;
	private boolean status;
	private String remarks;
	
}
