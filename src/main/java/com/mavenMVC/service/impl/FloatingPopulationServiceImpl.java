package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IFloatingPopulationDao;
import com.mavenMVC.entity.FloatingPopulation;
import com.mavenMVC.service.IFloatingPopulationService;
@Service
@Transactional
public class FloatingPopulationServiceImpl implements IFloatingPopulationService {

	@Autowired
	IFloatingPopulationDao floatingPopulationDao;
	
	@Override
	public void upload(FloatingPopulation floatingPopulation) {
	    FloatingPopulation byIdNumber = this.floatingPopulationDao.getByIdNumber(floatingPopulation.getIdentification());
	      if(byIdNumber != null) {
	         this.floatingPopulationDao.update(floatingPopulation, byIdNumber.getID());
	      } else {
	         this.floatingPopulationDao.upload(floatingPopulation);
	      }
	}

	@Override
	public FloatingPopulation getByIdNumber(String idNumber) {
		return 	floatingPopulationDao.getByIdNumber(idNumber);
	}

}
