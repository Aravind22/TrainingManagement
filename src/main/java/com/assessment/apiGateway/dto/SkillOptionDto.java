package com.assessment.apiGateway.dto;

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
}
