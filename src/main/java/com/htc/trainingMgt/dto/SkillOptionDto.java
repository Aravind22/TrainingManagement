package com.htc.trainingMgt.dto;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

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
public class SkillOptionDto {
	private long skillId;
	
	private String skillName;
	
	private String selected;

	public SkillOptionDto(long skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	
	
}
