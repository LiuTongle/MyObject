package com.mavenMVC.service;

import com.mavenMVC.entity.Hospital;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IHospitalService {

    public Hospital getHospitalById(Long id);

    public Hospital searchHospitalByName(String name);

    public List<Hospital> getAllHospitals();

}
