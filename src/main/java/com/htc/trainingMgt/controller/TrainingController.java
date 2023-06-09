package com.htc.trainingMgt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htc.trainingMgt.dao.TrainingDao;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.dto.EmployeeFilterDto;
import com.htc.trainingMgt.dto.SkillOptionDto;
import com.htc.trainingMgt.dto.TrainingDto;
import com.htc.trainingMgt.dto.TrainingFilterDto;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;
import com.htc.trainingMgt.service.impl.EmployeeService;
import com.htc.trainingMgt.service.impl.SkillService;
import com.htc.trainingMgt.service.impl.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	
	Logger logger = Logger.getLogger(TrainingController.class.getName());
	
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
		System.out.println("OCMMIN"+trainingDto);
		long empId = trainingDto.getEmpId();
		EmployeeDto empData = employeeService.getEmployeeById(empId);
		logger.info(empData);
		logger.info("EMP DATA ====================== EMP DATA ");
		if(empData!= null) {
			trainDto = trainingService.createTraining(trainingDto);
			redirectAttrs.addFlashAttribute("msg", "Trainer Employee Not available");
		}
		logger.info(trainDto);
		logger.info("TRAIN DATA ====================== TRAIN DATA ");
		if(trainDto != null && trainDto.getTrainingID()!=0) {
			redirectAttrs.addFlashAttribute("msg", "Training Added Successfully");
			return "redirect:/training/";
		} else {
			return "redirect:/training/addTrainingFailure";
		}
	}
	
	@GetMapping(value = "/add")
	public String showCreateTraining(@ModelAttribute(name="training") 
	@Valid TrainingDto training, BindingResult result, 
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
		ModelAndView mv = new ModelAndView();
		mv.setViewName("trainingList");
		List<Training> trainingList = trainingService.listAllTraining();
		System.out.println("TRAININGLIST::=>"+trainingList);
		mv.addObject("trainingList",trainingList);
		

		TrainingFilterDto filterObj = new TrainingFilterDto();
		filterObj.setFilterBy("startDate");
		mv.addObject("filterObj", filterObj);
		
		return mv;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getTrainingById(@RequestParam long trainingId,Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createTraining");
		TrainingDto trainDTO = trainingService.getTrainingById(trainingId);
		modelAndView.addObject("training", trainDTO);
		logger.info(trainDTO);
		logger.info("#########################");
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
		logger.info("======================== DELETE TRAINING ============");
		logger.info(trainingId);
		trainingService.deleteTrainingById(trainingId);
		return "redirect:/training/";
	}
	
	@RequestMapping(path = "search", method = RequestMethod.POST)
	public ModelAndView filtertraining(@ModelAttribute(value = "filterObj") @Validated TrainingFilterDto trainingFilterDto,
			RedirectAttributes redirectAttributes, Model model) {
		logger.info(trainingFilterDto);
		logger.info("============= TRANING FILTER DTO =============");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("trainingList");
		List<Training> data = trainingService.getTrainingsAfterFilter(trainingFilterDto);
		mv.addObject("trainingList", data);
		return mv;
	}

}
