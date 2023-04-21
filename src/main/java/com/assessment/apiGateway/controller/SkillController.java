package com.assessment.apiGateway.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;
import com.assessment.apiGateway.service.impl.SkillService;

@Controller
public class SkillController {

	@Autowired
	SkillService skillService;
	
	@RequestMapping(value = "/createSkill", method = RequestMethod.POST)
	public String createTraining(@ModelAttribute(value = "skill") 
	@Validated SkillDto skillDto,BindingResult rslt, 
	RedirectAttributes redirectAttrs) {
		if(rslt.hasErrors()) {
			System.out.println(rslt.getAllErrors());
		}
		Boolean result = skillService.addSkill(skillDto);
		if(result) {
			redirectAttrs.addFlashAttribute("msg", "Skill Added Successfully");
			return "redirect:/addSkillSuccess";
		} else {
			return "redirect:/addSkillFailure";
		}
	}
	
	@GetMapping(value = "/createSkil")
	public String showCreateSkillForm(@ModelAttribute(name="skill") 
	@Valid Skill skill, BindingResult result, 
	RedirectAttributes redirectAttrs, Model model) {
		return "createSkill";
	}
	
	@GetMapping(value = "/addSkillSuccess")
	public String showSkillSuccessForm() {
		return "addSkillSuccess";
	}
}
