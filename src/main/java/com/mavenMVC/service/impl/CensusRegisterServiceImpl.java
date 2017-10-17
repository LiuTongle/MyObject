package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ICensusRegisterDao;
import com.mavenMVC.entity.CensusRegister;
import com.mavenMVC.service.ICensusRegisterService;

@Service("CensusRegisterServiceImpl")
@Transactional
public class CensusRegisterServiceImpl implements ICensusRegisterService {

	@Autowired
	ICensusRegisterDao CensusRegisterDao;
	
	@Override
	public void upload(CensusRegister censusRegister) {
		CensusRegisterDao.upload(censusRegister);
	}

	@Override
	public CensusRegister getByIdNumber(String idNumber) {
		return CensusRegisterDao.getByIdNumber(idNumber);
	}

}