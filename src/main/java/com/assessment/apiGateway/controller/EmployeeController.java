package com.assessment.apiGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.service.impl.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView ShowAddEmployee() {
		EmployeeDto empDto = new EmployeeDto();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addEmployee");
		mv.addObject("employee",empDto);
		return mv;
	}

	@RequestMapping(path = "/addEmployee", method=RequestMethod.POST )
	public ModelAndView addEmployee(@ModelAttribute(value = "employee") 
	@Validated EmployeeDto employeeDto,BindingResult rslt) {
		EmployeeDto empDto = new EmployeeDto();
		empDto = empService.addEmployee(employeeDto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employeeHome");
		
		if(empDto != null) return modelAndView;
		return null;
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeHome");
		System.out.println(empService.getAllEmployees());
		mv.addObject("employeeList",empService.getAllEmployees());
		return mv;
	}
}
