package com.mavenMVC.service.impl;

import com.mavenMVC.dao.*;
import com.mavenMVC.entity.Collection;
import com.mavenMVC.entity.Doctor;
import com.mavenMVC.service.IDoctorService;
import com.mavenMVC.util.CollectionCode;
import com.mavenMVC.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
@Service("DoctorServiceImpl")
@Transactional
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private IDoctorDao doctorDao;

    @Autowired
    private IHospitalDao hospitalDao;

    @Autowired
    private IDepartmentDao departmentDao;

    @Autowired
    private ICollectionDao collectionDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IPatientDao patientDao;

    @Override
    public Doctor getDoctorByName(String name) {
        return doctorDao.getDoctorByName(name);
    }

    @Override
    public Doctor getDoctorByToken(String token) {
        return doctorDao.getDoctorByToken(token);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorDao.getById(id);
    }

    @Override
    public void collectDoctor(Long userId, Long doctorId) {
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setDoctorId(doctorId);
        collection.setCollectionType(CollectionCode.COLLECTION_DOCTOR);
        collectionDao.saveOrUpdateCollection(collection);
    }

    @Override
    public List<Doctor> getDoctorsByIds(List<Long> doctorIds) {
        return doctorDao.getDoctorsByIds(doctorIds);
    }

    @Override
    public List<Doctor> getMyCollectionDoctors(Long userId, Integer start, Integer offset, List<Long> receivedIds) {
        List<Collection> collections = collectionDao.getCollectionsByUserIdAndType(userId, CollectionCode.COLLECTION_DOCTOR, start, offset, receivedIds);
        List<Long> dIds = new ArrayList<Long>();
        List<Doctor> doctors = new ArrayList<Doctor>();
        if (collections != null && collections.size() > 0) {
            for (Collection collection : collections) {
                dIds.add(collection.getDoctorId());
            }
        }
        if (dIds != null && dIds.size() > 0) {
            doctors = doctorDao.getDoctorsByIds(dIds);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getMyOrderedDoctors(Long userId, Integer start, Integer offset, List<Long> receivedDoctorIds) {
        List<Long> patientIds = patientDao.getPatientIdsByUserId(userId);
        List<Doctor> doctors = new ArrayList<Doctor>();
        if (patientIds != null && patientIds.size() > 0) {
            List<Long> doctorIds = orderDao.getOrderDoctorIdsByPatientIds(patientIds, start, offset, receivedDoctorIds);
            if(doctorIds!=null && doctorIds.size()>0){
                doctors = doctorDao.getDoctorsByIds(doctorIds);
            }
        }
        return doctors;
    }

    @Override
    public List<Long> getMyCollectionDoctorIds(Long userId) {
        return collectionDao.getCollectDoctorIds(userId);
    }

    @Override
    public List<Doctor> getTopDoctors(Integer start, Integer offset, List<Long> receivedIds) {
        return doctorDao.getTopDoctors(start, offset, receivedIds);
    }

    @Override
    public Doctor registerDoctor(String doctorName, String password, String cellphone) {
        if (cellphone != null) {
            Doctor doctorEntity = new Doctor();
            doctorEntity.setDoctorCellphone(cellphone);
            if (doctorName != null) {
                doctorEntity.setDoctorName(doctorName);
            }
            if (password != null) {
                doctorEntity.setDoctorPassword(password);
            }
            if (cellphone != null) {
                doctorEntity.setDoctorCellphone(cellphone);
            }
            doctorEntity.setDoctorStatus(0);
            doctorEntity.setDoctorToken(MD5.GetMD5Code(cellphone + Calendar.getInstance().getTimeInMillis()));
            doctorDao.saveDoctor(doctorEntity);
            return doctorEntity;
        } else {
            return null;
        }
    }

    @Override
    public boolean ifDoctorCellphoneRegisted(String cellphone) {
        return doctorDao.ifDoctorExists(cellphone);
    }

    @Override
    public boolean resetPassword(String cellphone, String password) {
        if (cellphone != null) {
            Doctor doctorEntity = doctorDao.getDoctorByCellphone(cellphone);
            if (doctorEntity != null) {
                doctorEntity.setDoctorPassword(password);
                doctorDao.updateDoctor(doctorEntity);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Doctor loginValid(String cellphone, String password) {
        Doctor doctor = doctorDao.getDoctorByCellphoneAndPassword(cellphone, password);
        if(doctor!=null){
            doctor.setDoctorLogin(1);
            doctorDao.updateDoctor(doctor);
        }
        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctorEntity) {
        doctorDao.updateDoctor(doctorEntity);
    }

    @Override
    public List<Doctor> searchDoctors(String query, Integer start, Integer offset, List<Long> receivedIds) {
        List<Long> hospitalIds = hospitalDao.getHospitalIdsLikeNames(query);
        List<Long> departmentIds = departmentDao.getDepartmentIdsLikeNames(query);
        List<Doctor> doctors = doctorDao.searchDoctors(query, hospitalIds, departmentIds, start, offset, receivedIds);
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByOrderType(Integer orderType, Integer start, Integer offset, List<Long> receivedIds) {
        List<String> orderColumns = new ArrayList<String>();
        if(orderType == 0){
            orderColumns.add("doctorTop");
        }
        orderColumns.add("doctorScore");
        return doctorDao.getDoctors(orderColumns, start, offset, receivedIds);
    }
}
