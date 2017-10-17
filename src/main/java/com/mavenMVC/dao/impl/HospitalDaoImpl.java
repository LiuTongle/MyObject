package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IHospitalDao;
import com.mavenMVC.entity.Hospital;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */

@Repository
@Transactional
public class HospitalDaoImpl extends GenericDaoHibernate<Hospital, Long> implements IHospitalDao {

    public HospitalDaoImpl() {
        super(Hospital.class);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return get(id);
    }

    @Override
    public Hospital getHospitalByName(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Hospital.class);
        Criterion criterion = Restrictions.eq("hospitalName", name);
        query.add(criterion);
        if (getHibernateTemplate().findByCriteria(query).size() > 0) {
            return (Hospital) getHibernateTemplate().findByCriteria(query).get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveOrUpdateHospital(Hospital hospital) {
        if (hospital.getCreateTime() == null) {
            hospital.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        hospital.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        DetachedCriteria query = DetachedCriteria.forClass(Hospital.class);
        return getHibernateTemplate().findByCriteria(query);
    }

    @Override
    public List<Long> getHospitalIdsLikeNames(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Hospital.class);
        Criterion criterion = Restrictions.like("hospitalName",name, MatchMode.ANYWHERE);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Property.forName("hospitalId"));
        query.setProjection(projectionList);
        query.add(criterion);
        List<Long> results = getHibernateTemplate().findByCriteria(query);
        if(results!=null && results.size()>0){
            return results;
        }
        return null;
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
