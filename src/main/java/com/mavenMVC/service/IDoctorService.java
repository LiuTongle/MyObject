package com.mavenMVC.service;

import com.mavenMVC.entity.Doctor;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IDoctorService {

    public Doctor getDoctorByName(String name);

    public Doctor getDoctorByToken(String token);

    public Doctor getDoctorById(Long id);

    public void collectDoctor(Long userId, Long doctorId);

    public List<Doctor> getDoctorsByIds(List<Long> doctorIds);

    public List<Doctor> getMyCollectionDoctors(Long userId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Doctor> getMyOrderedDoctors(Long userId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Long> getMyCollectionDoctorIds(Long userId);

    public List<Doctor> getTopDoctors(Integer start, Integer offset, List<Long> receivedIds);

    public Doctor registerDoctor(String doctorName, String password, String cellphone);

    public boolean ifDoctorCellphoneRegisted(String cellphone);

    public boolean resetPassword(String cellphone, String password);

    public Doctor loginValid(String cellphone, String password);

    public void updateDoctor(Doctor doctorEntity);

    public List<Doctor> searchDoctors(String query, Integer start, Integer offset, List<Long> receivedIds);

    public List<Doctor> getDoctorsByOrderType(Integer orderType, Integer start, Integer offset, List<Long> receivedIds);

}
