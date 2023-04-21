package com.assessment.apiGateway.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Skill;

@Component
public class TrainingDto {

	private long trainingId;
	
	@NotNull(message = "StartDate can't be null")
	private String startDate;
	
	@NotNull(message = "EndDate can't be null")
	private String endDate;
	
	@NotNull(message = "Status can't be null")
	private boolean status;
	
	private String skill;
	
	private List<Allocation> allocationList;

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<Allocation> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<Allocation> allocationList) {
		this.allocationList = allocationList;
	}

	public TrainingDto(long trainingId, String startDate, String endDate,
			boolean status, String skill, List<Allocation> allocationList) {
		super();
		this.trainingId = trainingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.skill = skill;
		this.allocationList = allocationList;
	}

	public TrainingDto() {
		super();
	}

	@Override
	public String toString() {
		return "TrainingDto [trainingId=" + trainingId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", skill=" + skill + ", allocationList=" + allocationList + "]";
	}


	
	
}
