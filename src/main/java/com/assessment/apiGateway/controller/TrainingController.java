package com.assessment.apiGateway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dao.TrainingDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.SkillOptionDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;
import com.assessment.apiGateway.service.impl.EmployeeService;
import com.assessment.apiGateway.service.impl.SkillService;
import com.assessment.apiGateway.service.impl.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String createTraining(@ModelAttribute(value = "training") 
	@Validated TrainingDto trainingDto,BindingResult rslt, 
	RedirectAttributes redirectAttrs) {
		if(rslt.hasErrors()) {
			System.out.println(rslt.getAllErrors());
		}
		TrainingDto trainDto = new TrainingDto();
		long empId = trainingDto.getEmpId();
		EmployeeDto empData = employeeService.getEmployeeById(empId);
		System.out.println(empData);
		System.out.println("EMP DATA ====================== EMP DATA ");
		if(empData!= null) {
			trainDto = trainingService.createTraining(trainingDto);
			redirectAttrs.addFlashAttribute("msg", "Trainer Employee Not available");
		}
		System.out.println(trainDto);
		System.out.println("TRAIN DATA ====================== TRAIN DATA ");
		if(trainDto != null && trainDto.getTrainingID()!=0) {
			redirectAttrs.addFlashAttribute("msg", "Training Added Successfully");
			return "redirect:/training/addTrainingSuccess";
		} else {
			return "redirect:/training/addTrainingFailure";
		}
	}
	
	@GetMapping(value = "/add")
	public String showCreateTraining(@ModelAttribute(name="training") 
	@Valid Training training, BindingResult result, 
	RedirectAttributes redirectAttrs, Model model) {
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill->{
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);
		return "createTraining";
	}
	
	@GetMapping(value = "/addTrainingSuccess")
	public String showTrainingSuccess() {
		return "addTrainingSuccess";
	}
	
	@GetMapping(value = "/addTrainingFailure")
	public String showTrainingFailure() {
		return "addTrainingFailure";
	}
	
	@GetMapping(value = "/")
	public ModelAndView listAllTrainings() {
		System.out.println("=================================");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("trainingList");
		List<Training> trainingList = trainingService.listAllTraining();
		mv.addObject("trainingList",trainingList);
		return mv;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getTrainingById(@RequestParam long trainingId,Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createTraining");
		TrainingDto trainDTO = trainingService.getTrainingById(trainingId);
		modelAndView.addObject("training", trainDTO);
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill->{
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete/{trainingId}", method = RequestMethod.GET)
	public String deleteTrainingById(@PathVariable long trainingId) {
		trainingService.deleteTrainingById(trainingId);
		return "redirect:/training/";
	}

}
