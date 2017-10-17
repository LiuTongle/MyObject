package com.mavenMVC.dao;

import com.mavenMVC.entity.Patient;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IPatientDao {

    public Patient getById(Long id);

    public void saveOrUpdatePatient(Patient patient);

    public List<Patient> getPatientsByIds(List<Long> ids);

    public List<Patient> getPatientsByUserId(Long userId);

    public List<Long> getPatientIdsByUserId(Long userId);

    public List<Patient> getPatientsByIds(List<Long> ids, Integer start, Integer offset, List<Long> receivedIds);

    public List<Patient> getPatientsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Long> getPatientIdsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds);

}
