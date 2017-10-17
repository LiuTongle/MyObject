package com.mavenMVC.dao;

import com.mavenMVC.entity.FloatingPopulation;

public interface IFloatingPopulationDao {

	void upload(FloatingPopulation floatingPopulation);

	FloatingPopulation getByIdNumber(String idNumber);

}
