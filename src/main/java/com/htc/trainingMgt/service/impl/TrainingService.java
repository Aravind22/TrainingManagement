package com.htc.trainingMgt.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htc.trainingMgt.converter.EmployeeConverter;
import com.htc.trainingMgt.converter.SkillConverter;
import com.htc.trainingMgt.converter.TrainingConverter;
import com.htc.trainingMgt.dao.AllocationDao;
import com.htc.trainingMgt.dao.SkillsDao;
import com.htc.trainingMgt.dao.TrainingDao;
import com.htc.trainingMgt.dto.EmployeeDto;
import com.htc.trainingMgt.dto.SkillDto;
import com.htc.trainingMgt.dto.TrainingDto;
import com.htc.trainingMgt.dto.TrainingFilterDto;
import com.htc.trainingMgt.entity.Allocation;
import com.htc.trainingMgt.entity.Employee;
import com.htc.trainingMgt.entity.Skill;
import com.htc.trainingMgt.entity.Training;

@Service
public class TrainingService {

	@Autowired
	TrainingDao trainingDao;
	
	@Autowired
	SkillsDao skillDao;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	AllocationDao allocationDao;
	
	@Autowired
	TrainingConverter trainingConverter;
	
	@Autowired
	SkillConverter skillConverter;
	
	@Autowired
	EmployeeConverter employeeConverter;
	 
	@Autowired EmployeeService empService;
	
	Logger logger = Logger.getLogger(TrainingService.class.getCanonicalName());
	
	@Transactional
	public TrainingDto createTraining(TrainingDto trainingDto) {
		Skill skillObj = skillDao.findBySkillName(trainingDto.getSkill());
		if(skillObj == null) {
			return null;
		}
		Training training = trainingConverter.convertToEntity(trainingDto);
		EmployeeDto empDto = empService.getEmployeeById(trainingDto.getEmpId());
		Employee emp = employeeConverter.convertToEntity(empDto);
		training.setEmployee(emp);
		training.setSkill(skillObj);
//		training.se
		logger.info(training.toString());
		logger.info(training.getSkill().toString());
		logger.info(trainingDto.getStartDate()+ trainingDto.getEndDate());
		LocalDate startDate = LocalDate.parse(trainingDto.getStartDate());
		LocalDate endDate = LocalDate.parse(trainingDto.getEndDate());
		training.setStartDate(startDate);
		training.setEndDate(endDate);
		logger.info("============TRAINING BEFORE SAVE DATA============");
		logger.info(training.getSkill().toString());
		if(trainingDao.save(training) != null) {
			trainingDto.setTrainingID(training.getTrainingID());
			EmployeeDto empData = empService.getEmployeeById(trainingDto.getEmpId());
			trainingDto.setTrainerName(empData.getEmpName());
//			logger.info("TRAINING ENTITY==>"+ trainingDto.getSkill().toString());
			return trainingDto;
		}
		return null;
	}
	
	public Allocation createAllocation(Allocation allocation) {
		if(allocationDao.save(allocation) != null) {
			return allocation;
		}
		return null;
	}
	
	public List<Training> listAllTraining(){
		return trainingDao.findAll();
	}
	
	public List<String> listAllSkillNames() {
		List<Skill> skillList = skillDao.findAll();
		List<String> skillNames = new ArrayList<String>();
		for(int i=0;i<skillList.size();i++) {
			skillNames.add(skillList.get(i).getSkillName());
		}
		return skillNames;
	}
	
	public TrainingDto getTrainingById(long id) {
		Optional<Training> training = trainingDao.findById(id);
		Training trainingObj = training.get();
		TrainingDto trainDTO = new TrainingDto();
		BeanUtils.copyProperties(trainingObj, trainDTO);
		trainDTO.setSkill(trainingObj.getSkill().getSkillName());
		return trainDTO;
	}
	
	public void deleteTrainingById(long id) {
		trainingDao.deleteById(id);
	}
	
	public Training findBySkill(Skill skill) {
		return trainingDao.findBySkill(skill);
	}
	
	public Training getTrainingByTrainingId(long trainingId) {
		Optional<Training> training = trainingDao.findById(trainingId);
		Training trainingObj = training.get();
		return trainingObj;
	}
	
	public List<Training> getTrainingsAfterFilter(TrainingFilterDto trainingFilterDto){
		LocalDate startDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
		LocalDate startDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
		LocalDate endDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
		LocalDate endDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
		 List<Training> data = trainingDao.findByStartDateBetweenOrEndDateBetween(startDate1, startDate2, endDate1, endDate2);
		return data;
	}
}
