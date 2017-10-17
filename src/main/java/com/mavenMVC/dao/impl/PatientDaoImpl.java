package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IPatientDao;
import com.mavenMVC.entity.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */

@Repository
@Transactional
public class PatientDaoImpl extends GenericDaoHibernate<Patient, Long> implements IPatientDao {

    public PatientDaoImpl() {
        super(Patient.class);
    }

    @Override
    public Patient getById(Long id) {
        return get(id);
    }

    @Override
    public void saveOrUpdatePatient(Patient patient) {
        if(patient.getCreateTime()==null)
            patient.setCreateTime(Calendar.getInstance().getTimeInMillis());
        patient.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(patient);
    }

    @Override
    public List<Patient> getPatientsByIds(List<Long> ids) {
        try{
            Assert.notNull(ids, "patientIds can not be null");
            Assert.notEmpty(ids, "patientIds can not be empty");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.in("patientId", ids);
            query.add(criterion);
            query.addOrder(Order.desc("createTime"));
            List<Patient> results = getHibernateTemplate().findByCriteria(query);
            return results;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Patient> getPatientsByUserId(Long userId) {
        try{
            Assert.notNull(userId, "patientId can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.eq("patientUserId", userId);
            query.add(criterion);
            query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
            List<Patient> results = getHibernateTemplate().findByCriteria(query);
            return results;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Long> getPatientIdsByUserId(Long userId) {
        try{
            Assert.notNull(userId, "userId can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.eq("patientUserId", userId);
            query.add(criterion);
            ProjectionList  projectionList = Projections.projectionList();
            query.setProjection(projectionList);
            projectionList.add(Property.forName("patientId"));
            query.addOrder(Order.desc("createTime"));
            List<Long> results = getHibernateTemplate().findByCriteria(query);
            return results;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Patient> getPatientsByIds(List<Long> ids, Integer start, Integer offset, List<Long> receivedIds) {
        try{
            Assert.notNull(ids, "patientIds can not be null");
            Assert.notEmpty(ids, "patientIds can not be empty");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset,"offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.in("patientId", ids);
            query.add(criterion);
            if((receivedIds!=null)&&(receivedIds.size()>0)){
                Criterion criterion3 = Restrictions.not(Restrictions.in("patientId", receivedIds));
                query.add(criterion3);
            }
            if(offset!=null&&offset>0){
                query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
                List<Patient> results = getHibernateTemplate().findByCriteria(query,start,offset);
                return results;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Patient> getPatientsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds) {
        try{
            Assert.notNull(userId, "userId can not be null");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset,"offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.eq("patientUserId", userId);
            query.add(criterion);
            if((receivedIds!=null)&&(receivedIds.size()>0)){
                Criterion criterion3 = Restrictions.not(Restrictions.in("patientId", receivedIds));
                query.add(criterion3);
            }
            if(offset!=null&&offset>0){
                query.addOrder(Order.desc("createTime"));
                List<Patient> results = getHibernateTemplate().findByCriteria(query,start,offset);
                return results;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Long> getPatientIdsByUserId(Long userId, Integer start, Integer offset, List<Long> receivedIds) {
        try{
            Assert.notNull(userId, "userId can not be null");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset,"offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Patient.class);
            Criterion criterion = Restrictions.eq("patientUserId", userId);
            query.add(criterion);
            if((receivedIds!=null)&&(receivedIds.size()>0)){
                Criterion criterion3 = Restrictions.not(Restrictions.in("patientId", receivedIds));
                query.add(criterion3);
            }
            ProjectionList  projectionList = Projections.projectionList();
            query.setProjection(projectionList);
            projectionList.add(Property.forName("patientId"));
            if(offset!=null&&offset>0){
                query.addOrder(Order.desc("createTime"));
                List<Long> results = getHibernateTemplate().findByCriteria(query,start,offset);
                return results;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
