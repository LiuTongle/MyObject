package com.mavenMVC.dao;

import com.mavenMVC.entity.Hospital;

import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */
public interface IHospitalDao {

    public Hospital getHospitalById(Long id);

    public Hospital getHospitalByName(String name);

    public void saveOrUpdateHospital(Hospital hospital);

    public List<Hospital> getAllHospitals();

    public List<Long> getHospitalIdsLikeNames(String name);

}
