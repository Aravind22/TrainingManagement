package com.assessment.apiGateway.dto;

import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Training;

@Component
public class AllocationDto {

	private long allocationId;
	private Training training;
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
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
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
	public AllocationDto(Training training, Employee employee, int score, String status, String remarks) {
		super();
		this.training = training;
		this.employee = employee;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	public AllocationDto() {
		super();
	}
	@Override
	public String toString() {
		return "AllocationDto [allocationId=" + allocationId + ", training=" + training + ", employee=" + employee
				+ ", score=" + score + ", status=" + status + ", remarks=" + remarks + "]";
	}	
}
