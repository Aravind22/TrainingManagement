package com.htc.trainingMgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.trainingMgt.dto.EmployeeFilterDto;
import com.htc.trainingMgt.service.impl.EmployeeService;

@Controller
public class HomeController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/employee/";
	}
}
