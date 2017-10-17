package com.mavenMVC.dao;

import com.mavenMVC.entity.Doctor;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IDoctorDao {

    public Doctor getById(Long id);

    public Doctor getDoctorByName(String name);

    public void saveDoctor(Doctor user);

    public void updateDoctor(Doctor user);

    public Doctor getDoctorByToken(String token);

    public boolean ifDoctorExists(String cellphone);

    public Doctor getDoctorByCellphone(String cellphone);

    public Doctor getDoctorByCellphoneAndPassword(String cellphone, String password);

    public List<Doctor> getDoctorsByIds(List<Long> ids);

    public List<Doctor> searchDoctors(String query, List<Long> hospitalIds, List<Long> departmentIds, Integer start, Integer offset, List<Long> receivedIds);

    public List<Doctor> getTopDoctors(Integer start, Integer offset, List<Long> receivedIds);

    public List<Doctor> getDoctors(List<String> orderColumns, Integer start, Integer offset, List<Long> receivedIds);
}
