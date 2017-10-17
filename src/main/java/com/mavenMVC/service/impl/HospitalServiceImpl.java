package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IHospitalDao;
import com.mavenMVC.entity.Hospital;
import com.mavenMVC.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizai on 16/5/9.
 */

@Service("HospitalServiceImpl")
@Transactional
public class HospitalServiceImpl implements IHospitalService{

    @Autowired
    private IHospitalDao iHospitalDao;

    @Override
    public Hospital getHospitalById(Long id) {
        return iHospitalDao.getHospitalById(id);
    }

    @Override
    public Hospital searchHospitalByName(String name) {
        Hospital hospital = iHospitalDao.getHospitalByName(name);
        if(hospital == null){
            hospital = new Hospital();
            hospital.setHospitalName(name);
            iHospitalDao.saveOrUpdateHospital(hospital);
        }
        return hospital;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return iHospitalDao.getAllHospitals();
    }
}
