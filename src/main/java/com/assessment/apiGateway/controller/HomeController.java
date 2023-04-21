package com.assessment.apiGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.apiGateway.service.impl.EmployeeService;

@Controller
public class HomeController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeHome");
		System.out.println(empService.getAllEmployees());
		mv.addObject("employeeList",empService.getAllEmployees());
		return mv;
	}
}
