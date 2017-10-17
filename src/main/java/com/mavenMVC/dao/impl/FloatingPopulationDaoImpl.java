package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mavenMVC.dao.IFloatingPopulationDao;
import com.mavenMVC.entity.FloatingPopulation;
@Repository
public class FloatingPopulationDaoImpl extends GenericDaoHibernate<FloatingPopulation, Long> implements IFloatingPopulationDao {

	public FloatingPopulationDaoImpl() {
		super(FloatingPopulation.class);
	}
	
	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void upload(FloatingPopulation floatingPopulation) {
		floatingPopulation.setCreateTime(Calendar.getInstance().getTimeInMillis());
		floatingPopulation.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(floatingPopulation);
	}

	@Override
	public FloatingPopulation getByIdNumber(String idNumber) {
		DetachedCriteria query = DetachedCriteria.forClass(FloatingPopulation.class);
		query.add(Restrictions.eq("identification", idNumber));
		List <FloatingPopulation> findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria!=null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		}
		return null;
	}
}
