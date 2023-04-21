package com.assessment.apiGateway.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class SkillDto {

	private long skillId;
	
	@NotNull(message = "Skill name can't be null")
	private String skillName;
	
	@NotNull(message = "Skill category can't be null")
	private String category;
}
