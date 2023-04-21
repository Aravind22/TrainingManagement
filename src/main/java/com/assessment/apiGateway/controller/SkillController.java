package com.assessment.apiGateway.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dto.SkillDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;
import com.assessment.apiGateway.service.impl.SkillService;

@Controller
@RequestMapping("/skill")
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
			return "redirect:/skill/addSkillSuccess";
		} else {
			return "redirect:/skill/addSkillFailure";
		}
	}
	
	@GetMapping(value = "/add")
	public String showCreateSkillForm(@ModelAttribute(name="skill") 
	@Valid Skill skill, BindingResult result, 
	RedirectAttributes redirectAttrs, Model model) {
		return "createSkill";
	}
	
	@GetMapping(value = "/")
	public ModelAndView listAllSkills() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("skillList");
		System.out.println(skillService.getAllSkills());
		mv.addObject("skillList",skillService.getAllSkills());
		return mv;
	}
	
	@GetMapping(value = "/addSkillSuccess")
	public String showSkillSuccessForm() {
		return "addSkillSuccess";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getSkillById(@RequestParam long skillId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createSkill");
		modelAndView.addObject("skill", skillService.getSkillById(skillId));
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete/{skillId}", method = RequestMethod.GET)
	public String deleteSkillById(@PathVariable long skillId) {
		skillService.deleteSkillById(skillId);
		return "redirect:/skill/";
	}
}
