package com.htc.trainingMgt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Allocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long allocationId;
	
	@ManyToOne
	private Training training;
	
	@ManyToOne
	@JoinTable(name = "AllocationEmployeeRelation",
	joinColumns = @JoinColumn(referencedColumnName = "allocationId"),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "empId"))
	private Employee employee;
	
	private int score;
	private String status;
	private String remarks;
	
	public long getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(long allocationId) {
		this.allocationId = allocationId;
	}
	public Training gettraining() {
		return training;
	}
	public void settraining(Training training) {
		this.training = training;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Allocation(Training training, Employee employee, int score, String status, String remarks) {
		super();
		this.training = training;
		this.employee = employee;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	public Allocation() {
		super();
	}
	@Override
	public String toString() {
		return "Allocation [allocationId=" + allocationId + ", training=" + training + ", employee=" + employee
				+ ", score=" + score + ", status=" + status + ", remarks=" + remarks + "]";
	}
	
	

}
