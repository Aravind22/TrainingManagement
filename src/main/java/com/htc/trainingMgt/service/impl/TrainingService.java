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

	@Autowired
	EmployeeService empService;

	Logger logger = Logger.getLogger(TrainingService.class.getCanonicalName());

	@Transactional
	public TrainingDto createTraining(TrainingDto trainingDto) {
		Skill skillObj = skillDao.findBySkillName(trainingDto.getSkill()).get(0);
		if (skillObj == null) {
			return null;
		}
		Training training = trainingConverter.convertToEntity(trainingDto);
		EmployeeDto empDto = empService.getEmployeeById(trainingDto.getEmpId());
		Employee emp = employeeConverter.convertToEntity(empDto);
		logger.info(emp.toString());
		logger.info("------------------------------EMP------------");
		training.setEmployee(emp);
		logger.info(training.getEmployee().toString());
		training.setSkill(skillObj);
//		training.se
		logger.info(training.toString());
		logger.info(training.getSkill().toString());
		logger.info(trainingDto.getStartDate() + trainingDto.getEndDate());
		LocalDate startDate = LocalDate.parse(trainingDto.getStartDate());
		LocalDate endDate = LocalDate.parse(trainingDto.getEndDate());
		training.setStartDate(startDate);
		training.setEndDate(endDate);
		logger.info("============TRAINING BEFORE SAVE DATA============");
		logger.info(training.toString());
		logger.info(training.getEmployee().toString());
		if (trainingDao.save(training) != null) {
			trainingDto.setTrainingID(training.getTrainingID());
			EmployeeDto empData = empService.getEmployeeById(trainingDto.getEmpId());
			trainingDto.setTrainerName(empData.getEmpName());
//			logger.info("TRAINING ENTITY==>"+ trainingDto.getSkill().toString());
			return trainingDto;
		}
		return null;
	}

	public Allocation createAllocation(Allocation allocation) {
		if (allocationDao.save(allocation) != null) {
			return allocation;
		}
		return null;
	}

	public List<Training> listAllTraining() {
		return trainingDao.findAll();
	}

	public List<String> listAllSkillNames() {
		List<Skill> skillList = skillDao.findAll();
		List<String> skillNames = new ArrayList<String>();
		for (int i = 0; i < skillList.size(); i++) {
			skillNames.add(skillList.get(i).getSkillName());
		}
		return skillNames;
	}

	public TrainingDto getTrainingById(long id) {
		Optional<Training> training = trainingDao.findById(id);
		Training trainingObj = training.get();
		logger.info(trainingObj.toString());
		logger.info("==========TRAINING OBJECT BEFORE CONVERT");
		TrainingDto trainDTO = new TrainingDto();
		BeanUtils.copyProperties(trainingObj, trainDTO);
		trainDTO.setSkill(trainingObj.getSkill().getSkillName());
		logger.info("========TRAINING EDIT========");
		logger.info(trainDTO.toString());
		trainDTO.setEmpId(trainingObj.getEmployee().getEmpId());
		trainDTO.setStartDate(trainingObj.getStartDate().toString());
		trainDTO.setEndDate(trainingObj.getEndDate().toString());
		return trainDTO;
	}

	public void deleteTrainingById(long id) {
//		trainingDao.delete
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

	public List<Training> getTrainingsAfterFilter(TrainingFilterDto trainingFilterDto) {
		logger.info(trainingFilterDto.getFilterBy());
		logger.info("============ FFFFFFFFFFFFFFFFFF ===========");
		if (trainingFilterDto.getFilterBy().equals("startDate")) {
			LocalDate startDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
			LocalDate startDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
			List<Training> data = trainingDao.findByStartDateBetween(startDate1, startDate2);
			return data;
		} else if (trainingFilterDto.getFilterBy().equals("endDate")) {
			LocalDate startDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
			LocalDate startDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
			List<Training> data = trainingDao.findByEndDateBetween(startDate1, startDate2);
			return data;
		} else {
			LocalDate startDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
			LocalDate startDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
			LocalDate endDate1 = LocalDate.parse(trainingFilterDto.getStartDate());
			LocalDate endDate2 = LocalDate.parse(trainingFilterDto.getEndDate());
			List<Training> data = trainingDao.findByStartDateBetweenOrEndDateBetween(startDate1, startDate2, endDate1,
					endDate2);
			return data;
		}

	}
}
