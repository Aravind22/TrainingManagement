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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dao.TrainingDao;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Training;
import com.assessment.apiGateway.service.impl.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	TrainingService trainingService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String createTraining(@ModelAttribute(value = "training") 
	@Validated TrainingDto trainingDto,BindingResult rslt, 
	RedirectAttributes redirectAttrs) {
		if(rslt.hasErrors()) {
			System.out.println(rslt.getAllErrors());
		}
		TrainingDto trainDto = new TrainingDto();
		trainDto = trainingService.createTraining(trainingDto);
		if(trainDto != null) {
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
		mv.addObject("trainingList",trainingService.listAllTraining());
		return mv;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getTrainingById(@RequestParam long trainingId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createTraining");
		TrainingDto trainDTO = trainingService.getTrainingById(trainingId);
		modelAndView.addObject("training", trainDTO);
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete/{trainingId}", method = RequestMethod.GET)
	public String deleteTrainingById(@PathVariable long trainingId) {
		trainingService.deleteTrainingById(trainingId);
		return "redirect:/training/";
	}

}
