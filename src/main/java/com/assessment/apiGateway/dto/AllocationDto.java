package com.assessment.apiGateway.dto;

import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Training;

@Component
public class AllocationDto {

	private long allocationId;
	private long trainingId;
	private String skillName;
	private long empId;
	private int score;
	private String status;
	private String remarks;
	
	public AllocationDto(long allocationId, long trainingId, String skillName, long empId, int score, String status,
			String remarks) {
		super();
		this.allocationId = allocationId;
		this.trainingId = trainingId;
		this.skillName = skillName;
		this.empId = empId;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	public long getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}
	public long getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(long allocationId) {
		this.allocationId = allocationId;
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

	public AllocationDto() {
		super();
	}

	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public AllocationDto(String skillName, long empId, int score, String status, String remarks) {
		super();
		this.skillName = skillName;
		this.empId = empId;
		this.score = score;
		this.status = status;
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "AllocationDto [allocationId=" + allocationId + ", skillName=" + skillName + ", empId=" + empId
				+ ", score=" + score + ", status=" + status + ", remarks=" + remarks + "]";
	}

}
