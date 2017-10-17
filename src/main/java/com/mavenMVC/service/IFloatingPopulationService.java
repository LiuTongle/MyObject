package com.mavenMVC.service;

import com.mavenMVC.entity.FloatingPopulation;

public interface IFloatingPopulationService {

	void upload(FloatingPopulation floatingPopulation);

	FloatingPopulation getByIdNumber(String idNumber);

}
