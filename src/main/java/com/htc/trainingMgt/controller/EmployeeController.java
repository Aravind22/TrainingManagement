package com.htc.trainingMgt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.dto.EmployeeFilterDto;
import com.htc.trainingMgt.dto.SkillOptionDto;
import com.htc.trainingMgt.entity.Employee;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.service.impl.EmployeeService;
import com.htc.trainingMgt.service.impl.SkillService;

@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {

	Logger logger = Logger.getLogger(EmployeeController.class.getName());

	@Autowired
	EmployeeService empService;

	@Autowired
	SkillService skillService;

	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView ShowAddEmployee(Model model) {
		EmployeeDto empDto = new EmployeeDto();
		empDto.setDisabled("notdisabled");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addEmployee");
		mv.addObject("employee", empDto);
		
		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill -> {
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);
		
		return mv;
	}

	@RequestMapping(path = "search", method = RequestMethod.POST)
	public ModelAndView filterEmployee(@ModelAttribute(value = "filterObj") @Validated EmployeeFilterDto filterDto,
			RedirectAttributes redirectAttributes, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList",
				empService.filterEmployeeByIdAndSkillIds(filterDto.getEmpId(), filterDto.getSkillIds()));
		logger.info(filterDto);
//		EmployeeFilterDto filterObj = new EmployeeFilterDto();
		mv.addObject("filterObj", filterDto);

		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill -> {
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);

		return mv;

	}

	@RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute(value = "employee") @Validated EmployeeDto employeeDto,
			BindingResult rslt, RedirectAttributes redirectAttributes) {
		EmployeeDto empDto = new EmployeeDto();
		String sucessMessage = null;
		if(rslt.hasErrors()) {
			return "addEmployee";
		}
		if (employeeDto.getDisabled().equals("disabled")) {
			empDto = empService.addEmployee(employeeDto);
			sucessMessage = empDto.getEmpName() + " updated Successfully!";
			EmployeeDto data = empService.getEmployeeById(employeeDto.getEmpId());
			empDto = empService.addEmployee(employeeDto);
		} else {
			EmployeeDto data = empService.getEmployeeById(employeeDto.getEmpId());
			if (data != null) {
				sucessMessage = "Employee Id " + data.getEmpId() + " already available, Not created or updated";
			} else {
				empDto = empService.addEmployee(employeeDto);
				sucessMessage = empDto.getEmpName() + " created Successfully!";
			}
		}

		redirectAttributes.addFlashAttribute("employeeAdded", sucessMessage);
		return "redirect:list";

	}

//	EmpList
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getAllEmployee(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList", empService.getAllEmployees());
		
		EmployeeFilterDto filterObj = new EmployeeFilterDto();
		mv.addObject("filterObj", filterObj);

		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill -> {
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);

		
		return mv;
	}

//	Edit Employee
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEmployeeById(@RequestParam long empId, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addEmployee");
		EmployeeDto data = empService.getEmployeeById(empId);
		data.setDisabled("disabled");
		modelAndView.addObject("employee", data);
		
		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		Set<Skill> empSkills = data.getSkillSet();
		skillList.forEach(skill -> {
			String selected = empSkills.stream().anyMatch(s -> skill.getSkillId() == s.getSkillId()) ? "selected": "";
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName(), selected));
		});
		model.addAttribute("skillOptions", options);
		
		
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteByEmployeeId(@PathVariable long id) {
		empService.deleteByEmployeeId(id);
		return "redirect:/employee/list";
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public ModelAndView afterSearch(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList", empService.getAllEmployees());
		EmployeeFilterDto filterObj = new EmployeeFilterDto();
		mv.addObject("filterObj", filterObj);

		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill -> {
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);

		return mv;
	}
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getHome(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employeeList");
		mv.addObject("employeeList", empService.getAllEmployees());
		EmployeeFilterDto filterObj = new EmployeeFilterDto();
		mv.addObject("filterObj", filterObj);

		// DropDown Skill
		List<Skill> skillList = skillService.getAllSkills();
		List<SkillOptionDto> options = new ArrayList<SkillOptionDto>();
		skillList.forEach(skill -> {
			options.add(new SkillOptionDto(skill.getSkillId(), skill.getSkillName()));
		});
		model.addAttribute("skillOptions", options);

		return mv;
	}
	
	public boolean hasSkillId(Set<Skill> skills, long skillId) {
	    for (Skill skill : skills) {
	        if (skill.getSkillId() == skillId) {
	            return true;
	        }
	    }
	    return false;
	}

}
