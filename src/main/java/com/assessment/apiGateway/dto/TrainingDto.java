package com.assessment.apiGateway.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Skill;

@Component
public class TrainingDto {

	private long trainingID;
	
	@NotNull(message = "StartDate can't be null")
	private String startDate;
	
	@NotNull(message = "EndDate can't be null")
	private String endDate;
	
	@NotNull(message = "Status can't be null")
	private boolean status;
	
	private String skill;
	
	
	
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public long getTrainingID() {
		return trainingID;
	}

	public void setTrainingID(long trainingID) {
		this.trainingID = trainingID;
	}

	public String getSkillList() {
		return skill;
	}

	public void setSkillList(String skillList) {
		this.skill = skillList;
	}

	private List<Allocation> allocationList;

	public long gettrainingID() {
		return trainingID;
	}

	public void settrainingID(long trainingID) {
		this.trainingID = trainingID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

//	public String getSkill() {
//		return skill;
//	}
//
//	public void setSkill(String skill) {
//		this.skill = skill;
//	}

	public List<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<Allocation> allocationList) {
		this.allocationList = allocationList;
	}

	public TrainingDto(long trainingID, String startDate, String endDate,
			boolean status, String skill, List<Allocation> allocationList) {
		super();
		this.trainingID = trainingID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
//		this.skill = skill;
		this.allocationList = allocationList;
	}

	public TrainingDto() {
		super();
	}

	@Override
	public String toString() {
		return "TrainingDto [trainingID=" + trainingID + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", skill=" + skill + ", allocationList=" + allocationList + "]";
	}



	
	
}
