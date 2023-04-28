package com.htc.trainingMgt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
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

import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.dto.TrainingDto;
import com.htc.trainingMgt.dto.searchSkillDto;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;
import com.htc.trainingMgt.service.impl.SkillService;

@Controller
@RequestMapping("/skill")
public class SkillController {

	Logger logger = Logger.getLogger(SkillController.class.getName());
	@Autowired
	SkillService skillService;
	
	@RequestMapping(value = "/createSkill", method = RequestMethod.POST)
	public String createTraining(@ModelAttribute(value = "skill") 
	@Validated SkillDto skillDto,BindingResult rslt, 
	RedirectAttributes redirectAttrs) {
		if(rslt.hasErrors()) {
			logger.info(rslt.getAllErrors());
		}
		Skill data = skillService.findBySkillName(skillDto.getSkillName());
		if(data!= null) {
			return "addSkillFailure";
		}
		Boolean result = skillService.addSkill(skillDto);
		if(result) {
			redirectAttrs.addFlashAttribute("msg", "Skill Added Successfully");
			return "redirect:/skill/";
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
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchSkill(@ModelAttribute(value = "skillSearch")
		@Validated searchSkillDto searchDto,BindingResult rslt, 
		RedirectAttributes redirectAttrs) {
		List<Skill> skills = new ArrayList<Skill>();
		ModelAndView mv = new ModelAndView();
		System.out.println(searchDto);
		if(!searchDto.getSkillCategory().isEmpty() && !searchDto.getSkillName().isEmpty()) {
			skills = skillService.searchSkills(searchDto);
		} else if(searchDto.getSkillCategory().isEmpty() && !searchDto.getSkillName().isEmpty()) {
			System.out.println("INSIDE");
			skills = skillService.searchByName(searchDto);
			System.out.println(skills);
		} else if(searchDto.getSkillName().isEmpty() && !searchDto.getSkillCategory().isEmpty()){
			skills = skillService.searchByCategory(searchDto);
		} else {
			skills = skillService.getAllSkills();
		}
		mv.addObject("skillList", skills);
		mv.setViewName("skillList");
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchSkillGET(@ModelAttribute(value = "skillSearch")
		@Validated searchSkillDto searchDto,BindingResult rslt, 
		RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView();
		List<Skill> skills = skillService.searchSkills(searchDto);
		mv.addObject("skillList", skills);
		mv.setViewName("skillList");
		return mv;
	}
	
	@GetMapping(value = "/")
	public ModelAndView listAllSkills() {
		ModelAndView mv = new ModelAndView();
		Skill skill = new Skill();
		mv.setViewName("skillList");
		System.out.println(skillService.getAllSkills());
		mv.addObject("skillList",skillService.getAllSkills());
		mv.addObject("skill", skill);
		searchSkillDto searchSKDto = new searchSkillDto();
		mv.addObject("skillSearch", searchSKDto);
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
	
	@RequestMapping(value="/employee/{skillId}", method=RequestMethod.GET)
	public String getEmployeeBySkillId(@PathVariable long skillId) {
		SkillDto data = skillService.getSkillById(skillId);
		return "redirect:/skill/";
	}

}
