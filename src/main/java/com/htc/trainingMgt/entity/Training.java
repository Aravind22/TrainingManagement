package com.htc.trainingMgt.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	private String trainingName;
	
	@ManyToOne()
	@JoinTable(name = "TrainingSkillRelation", 
	joinColumns = @JoinColumn(referencedColumnName = "trainingID"),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "skillId")
	)
	private Skill skill;
	
	@OneToMany()
	private List<Allocation> allocationList;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private int score;
	private boolean status;
	private String remarks;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "TrainingEmployeeRelation",
	joinColumns = @JoinColumn(referencedColumnName = "trainingID"),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "empId"))
	private Employee employee;
	
}
