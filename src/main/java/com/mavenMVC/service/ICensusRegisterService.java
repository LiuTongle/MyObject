package com.mavenMVC.service;

import com.mavenMVC.entity.CensusRegister;

public interface ICensusRegisterService {
	void upload(CensusRegister censusRegister);

	CensusRegister getByIdNumber(String idNumber);
}
