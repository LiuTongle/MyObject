package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IDoctorDao;
import com.mavenMVC.entity.Doctor;
import com.mavenMVC.util.DoctorCode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */

@Repository
@Transactional
public class DoctorDaoImpl extends GenericDaoHibernate<Doctor, Long> implements IDoctorDao{

    public DoctorDaoImpl() {
        super(Doctor.class);
    }

    @Override
    public Doctor getById(Long id) {
        return get(id);
    }

    @Override
    public Doctor getDoctorByName(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorName", name);
        query.add(criterion);
        if(getHibernateTemplate().findByCriteria(query).size()>0){
            return (Doctor) getHibernateTemplate().findByCriteria(query).get(0);
        }else{
            return null;
        }
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctor.setCreateTime(Calendar.getInstance().getTimeInMillis());
        doctor.setLastModTime(Calendar.getInstance().getTimeInMillis());
        doctor.setDoctorStatus(DoctorCode.DOCTOR_NEW);
        doctor.setDoctorTop(DoctorCode.DOCTOR_UNTOP);
        doctor.setDoctorSwitch(true);
        saveOrUpdate(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctor.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(doctor);
    }

    @Override
    public Doctor getDoctorByToken(String token) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorToken", token);
        query.add(criterion);
        if(getHibernateTemplate().findByCriteria(query).size()>0){
            return (Doctor) getHibernateTemplate().findByCriteria(query).get(0);
        }else{
            return null;
        }
    }

    @Override
    public boolean ifDoctorExists(String cellphone) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorCellphone", cellphone);
        query.add(criterion);
        if(getHibernateTemplate().findByCriteria(query).size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Doctor getDoctorByCellphone(String cellphone) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorCellphone", cellphone);
        query.add(criterion);
        if(getHibernateTemplate().findByCriteria(query).size()>0){
            return (Doctor) getHibernateTemplate().findByCriteria(query).get(0);
        }else{
            return null;
        }
    }

    @Override
    public Doctor getDoctorByCellphoneAndPassword(String cellphone, String password) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorCellphone", cellphone);
        Criterion criterion1 = Restrictions.eq("doctorPassword", password);
        query.add(criterion);
        query.add(criterion1);
        List<Doctor> doctors = getHibernateTemplate().findByCriteria(query);
        if(doctors != null && doctors.size()>0){
            return doctors.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Doctor> getDoctorsByIds(List<Long> ids) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.in("doctorId", ids);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("doctorStatus", DoctorCode.DOCTOR_VERIFIED);
        query.add(criterion1);
        return getHibernateTemplate().findByCriteria(query);
    }

    @Override
    public List<Doctor> searchDoctors(String query, List<Long> hospitalIds, List<Long> departmentIds, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorStatus", DoctorCode.DOCTOR_VERIFIED);
        detachedCriteria.add(criterion);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("doctorName", query, MatchMode.ANYWHERE));
        if(hospitalIds!=null && hospitalIds.size()>0){
            disjunction.add(Restrictions.in("doctorHospital", hospitalIds));
        }
        if(departmentIds!=null && departmentIds.size()>0){
            disjunction.add(Restrictions.in("doctorDepartment", departmentIds));
        }
        detachedCriteria.add(disjunction);
        if(receivedIds!=null && receivedIds.size()>0){
            Criterion criterion3 = Restrictions.not(Restrictions.in("doctorId", receivedIds));
            detachedCriteria.add(criterion3);
        }
        return getHibernateTemplate().findByCriteria(detachedCriteria,start,offset);
    }

    @Override
    public List<Doctor> getTopDoctors(Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorStatus", DoctorCode.DOCTOR_VERIFIED);
        Criterion criterion1 = Restrictions.gt("doctorTop", 0);
        query.add(criterion);
        query.add(criterion1);
        if((receivedIds!=null)&&(receivedIds.size()>0)){
            Criterion criterion2 = Restrictions.not(Restrictions.in("doctorId", receivedIds));
            query.add(criterion2);
        }
        query.addOrder(Order.desc("doctorTop"));
        List<Doctor> results = new ArrayList();
        if((start>=0)&&(offset>0)){
            results = getHibernateTemplate().findByCriteria(query, start, offset);
        }
        return results;
    }

    @Override
    public List<Doctor> getDoctors(List<String> orderColumns, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Doctor.class);
        Criterion criterion = Restrictions.eq("doctorStatus", DoctorCode.DOCTOR_VERIFIED);
        query.add(criterion);
        List<Doctor> results = new ArrayList();
        if(orderColumns!=null && orderColumns.size()>0){
            for(String orderColumn : orderColumns){
                query.addOrder(Order.desc(orderColumn));
            }
        }
        if((start>=0)&&(offset>0)){
            results = getHibernateTemplate().findByCriteria(query, start, offset);
        }
        return results;
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
