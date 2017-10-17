package com.mavenMVC.service;

import com.mavenMVC.entity.Patient;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IPatientService {

    public Patient getPatientById(Long id);

    public void saveOrUpdatePatient(Patient patient);

    public List<Patient> getPatientsByIds(List<Long> ids);

    public List<Patient> getPatientsByUserId(Long userId);

    public List<Long> getPatientIdsByUserId(Long userId);

    public List<Patient> getPatientsByIds(List<Long> ids, Integer start, Integer offset, List<Long> receivedIds);

    public List<Patient> getPatientsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Patient> getPatientsByDoctorId(Long doctorId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Long> getPatientIdsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds);

}
