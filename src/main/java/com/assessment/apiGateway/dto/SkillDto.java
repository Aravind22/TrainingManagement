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

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SkillDto(String skillName, String category) {
		super();
		this.skillName = skillName;
		this.category = category;
	}

	public SkillDto() {
		super();
	}

	@Override
	public String toString() {
		return "SkillDto [skillId=" + skillId + ", skillName=" + skillName + ", category=" + category + "]";
	}
	
	
}
