package com.assessment.apiGateway.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView ShowAddEmployee() {
		EmployeeDto empDto = new EmployeeDto();
		empDto.setDisabled("notdisabled");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addEmployee");
		mv.addObject("employee",empDto);
		return mv;
	}

	@RequestMapping(path = "/addEmployee", method=RequestMethod.POST )
	public String addEmployee(@ModelAttribute(value = "employee") 
	@Validated EmployeeDto employeeDto,BindingResult rslt, RedirectAttributes redirectAttributes) {
		EmployeeDto empDto = new EmployeeDto();
		String sucessMessage = null;
		if(employeeDto.getDisabled().equals("disabled")) {
			empDto = empService.addEmployee(employeeDto);
			sucessMessage = empDto.getEmpName() + " updated Successfully!";		
			EmployeeDto data = empService.getEmployeeById(employeeDto.getEmpId());
			empDto = empService.addEmployee(employeeDto);
		}
		else {
			EmployeeDto data = empService.getEmployeeById(employeeDto.getEmpId());
			if(data!=null) {
				sucessMessage = "Employee Id " + data.getEmpId() + " already available, Not created or updated";
			}else {
				empDto = empService.addEmployee(employeeDto);
				sucessMessage = empDto.getEmpName() + " created Successfully!";
			}
		}
		
		redirectAttributes.addFlashAttribute("employeeAdded", sucessMessage);
		return "redirect:list";
		
	}
	
//	EmpList
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllEmployee() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList",empService.getAllEmployees());
		return mv;
	}
	
//	Edit Employee
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEmployeeById(@RequestParam long empId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addEmployee");
		EmployeeDto data = empService.getEmployeeById(empId);
		data.setDisabled("disabled");
		modelAndView.addObject("employee", data);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteByEmployeeId(@PathVariable long id) {
		empService.deleteByEmployeeId(id);
		return "redirect:/employee/list";
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList",empService.getAllEmployees());
		return mv;
	}
}
