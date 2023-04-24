package com.assessment.apiGateway.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.apiGateway.dto.AllocationDto;
import com.assessment.apiGateway.dto.EmployeeDto;
import com.assessment.apiGateway.dto.TrainingDto;
import com.assessment.apiGateway.entity.Allocation;
import com.assessment.apiGateway.entity.Employee;
import com.assessment.apiGateway.entity.Skill;
import com.assessment.apiGateway.entity.Training;
import com.assessment.apiGateway.service.impl.AllocationService;
import com.assessment.apiGateway.service.impl.EmployeeService;
import com.assessment.apiGateway.service.impl.SkillService;
import com.assessment.apiGateway.service.impl.TrainingService;

@Controller
@RequestMapping("/allocation")
public class AllocationController {
	
	@Autowired
	AllocationService allocationService;
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	SkillService skillService;
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView ShowAddAllocation() {
		AllocationDto allocationDto = new AllocationDto();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createAllocation");
		mv.addObject("allocation",allocationDto);
		return mv;
	}
	
	@RequestMapping(path = "/createAllocation", method=RequestMethod.POST )
	public String createAllocation(@ModelAttribute(value = "allocation") 
	@Validated AllocationDto allocationDto,BindingResult rslt, RedirectAttributes redirectAttributes) {
		AllocationDto allocationDtobj = new AllocationDto();
		Employee emp = new Employee();
		Training training = new Training();
		Allocation allocationObj = new Allocation();
		String sucessMessage = null;
		
		allocationDtobj = allocationService.addAllocation(allocationDto);
		
		
		redirectAttributes.addFlashAttribute("employeeAdded", sucessMessage);
		return "redirect:list";
		
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getAllocationHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allocationList");
		mv.addObject("allocationList",allocationService.getAllAllocation());
		return mv;
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ModelAndView getAllocationList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allocationList");
		mv.addObject("allocationList",allocationService.getAllAllocation());
		return mv;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editAllocaitonById(@RequestParam long allocationId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createAllocation");
		AllocationDto allocationDto = allocationService.getAllocationById(allocationId);
		modelAndView.addObject("allocation", allocationDto);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteByAllocationId(@PathVariable long id) {
		allocationService.deleteAllocation(id);
		return "redirect:/allocation/list";
	}
	
	@RequestMapping(value = "/allocate/{allocationId}/{empId}/{skillId}", method = RequestMethod.GET)
	public String allocateSkill(@PathVariable long allocationId,
			@PathVariable long empId,
			@PathVariable long skillId) {
		System.out.println("COMPLETE");
		allocationService.allocateSkill(allocationId, empId, skillId);
		return "redirect:/allocation/list";
	}

}
