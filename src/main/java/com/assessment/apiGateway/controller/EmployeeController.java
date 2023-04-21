package com.assessment.apiGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.service.impl.EmployeeService;

@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView ShowAddEmployee() {
		EmployeeDto empDto = new EmployeeDto();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addEmployee");
		mv.addObject("employee",empDto);
		return mv;
	}

	@RequestMapping(path = "/addEmployee", method=RequestMethod.POST )
	public String addEmployee(@ModelAttribute(value = "employee") 
	@Validated EmployeeDto employeeDto,BindingResult rslt, RedirectAttributes redirectAttributes) {
		EmployeeDto empDto = new EmployeeDto();
		empDto = empService.addEmployee(employeeDto);
		String sucessMessage = empDto.getEmpName() + " added Successfully!";
//		if(employeeDto.getEmpId())
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("employeeHome");
		redirectAttributes.addAttribute("employeeAdded", sucessMessage);
		return "redirect:list";
		
	}
//	EmpList
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllEmployee() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeHome");
		mv.addObject("employeeList",empService.getAllEmployees());
		return mv;
	}
	
//	Edit Employee
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEmployeeById(@RequestParam long empId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addEmployee");
		modelAndView.addObject("employee", empService.getEmployeeById(empId));
		return modelAndView;
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
