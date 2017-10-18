package com.mavenMVC.dao;

import com.mavenMVC.entity.CensusRegister;

public interface ICensusRegisterDao {
	void upload(CensusRegister censusRegister);

	CensusRegister getByIdNumber(String idNumber);

	void update(CensusRegister censusRegister, Long id);
}
