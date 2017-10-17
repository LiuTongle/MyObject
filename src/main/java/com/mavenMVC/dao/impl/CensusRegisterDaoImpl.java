package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ICensusRegisterDao;
import com.mavenMVC.entity.CensusRegister;

@Repository
@Transactional
public class CensusRegisterDaoImpl extends GenericDaoHibernate<CensusRegister, Long> implements ICensusRegisterDao {

	public CensusRegisterDaoImpl() {
		super(CensusRegister.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void upload(CensusRegister censusRegister) {
		censusRegister.setCreateTime(Calendar.getInstance().getTimeInMillis());
		censusRegister.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(censusRegister);
	}

	@Override
	public CensusRegister getByIdNumber(String idNumber) {
		DetachedCriteria query = DetachedCriteria.forClass(CensusRegister.class);
		query.add(Restrictions.eq("identification", idNumber));
		List <CensusRegister>findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria!=null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		}
		return null;
	}

}
