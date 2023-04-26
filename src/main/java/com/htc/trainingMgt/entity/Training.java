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

@Entity
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trainingID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "TrainingSkillRelation", 
	joinColumns = @JoinColumn(referencedColumnName = "trainingID"),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "skillId")
	)
	private Skill skill;
	
	@OneToMany
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
	
	public long getTrainingID() {
		return trainingID;
	}
	public void setTrainingID(long trainingID) {
		this.trainingID = trainingID;
	}
	public Skill getskill() {
		return skill;
	}
	public void setskill(Skill skill) {
		this.skill = skill;
	}
	public List<Allocation> getAllocationList() {
		return allocationList;
	}
	public void setAllocationList(List<Allocation> allocationList) {
		this.allocationList = allocationList;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Training(Skill skill, List<Allocation> allocationList, LocalDate startDate, LocalDate endDate, int score,
			boolean status, String remarks) {
		super();
		this.skill = skill;
		this.allocationList = allocationList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	public Training() {
		super();
	}
	@Override
	public String toString() {
		return "Training [trainingID=" + trainingID + ", skill=" + skill + ", allocationList=" + allocationList
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", score=" + score + ", status=" + status
				+ ", remarks=" + remarks + "]";
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}