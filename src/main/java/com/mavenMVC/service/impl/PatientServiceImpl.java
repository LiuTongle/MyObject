package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IOrderDao;
import com.mavenMVC.dao.IPatientDao;
import com.mavenMVC.dao.IUserDao;
import com.mavenMVC.entity.Patient;
import com.mavenMVC.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
@Service("PatientServiceImpl")
@Transactional
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IPatientDao patientDao;

    @Autowired
    private IOrderDao orderDao;

    @Override
    public Patient getPatientById(Long id) {
        return patientDao.getById(id);
    }

    @Override
    public void saveOrUpdatePatient(Patient patient) {
        patientDao.saveOrUpdatePatient(patient);
    }

    @Override
    public List<Patient> getPatientsByIds(List<Long> ids) {
        return patientDao.getPatientsByIds(ids);
    }

    @Override
    public List<Patient> getPatientsByUserId(Long userId) {
        return patientDao.getPatientsByUserId(userId);
    }

    @Override
    public List<Long> getPatientIdsByUserId(Long userId) {
        return patientDao.getPatientIdsByUserId(userId);
    }

    @Override
    public List<Patient> getPatientsByIds(List<Long> ids, Integer start, Integer offset, List<Long> receivedIds) {
        return patientDao.getPatientsByIds(ids, start, offset, receivedIds);
    }

    @Override
    public List<Patient> getPatientsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds) {
        return patientDao.getPatientsByUserId(userId, start, offset, receivedIds);
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId, Integer start, Integer offset, List<Long> receivedIds) {
        List<Long> patientIds = orderDao.getOrderPatientIdsByDoctorId(doctorId, null, null, start, offset, receivedIds);
        List<Patient> patients = patientDao.getPatientsByIds(patientIds);
        return patients;
    }

    @Override
    public List<Long> getPatientIdsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds) {
        return patientDao.getPatientIdsByUserId(userId, start, offset, receivedIds);
    }
}
