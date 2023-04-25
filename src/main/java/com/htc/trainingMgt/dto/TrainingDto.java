package com.htc.trainingMgt.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.htc.trainingMgt.entity.Allocation;
import com.htc.trainingMgt.entity.Skill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TrainingDto {

	private long trainingID;
	
	@NotNull(message = "StartDate can't be null")
	private String startDate;
	
	@NotNull(message = "EndDate can't be null")
	private String endDate;
	
	@NotNull(message = "Status can't be null")
	private boolean status;
	
	private String skill;
	
	private long empId;	
	private String trainerName;	
	
}
