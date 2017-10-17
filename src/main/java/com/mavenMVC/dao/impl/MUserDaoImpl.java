package com.mavenMVC.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IMUserDao;
import com.mavenMVC.entity.BuyerUser;
import com.mavenMVC.entity.MUserInfo;

@Repository
@Transactional
public class MUserDaoImpl extends GenericDaoHibernate<MUserInfo, Long> implements IMUserDao {

	public MUserDaoImpl() {
		super(MUserInfo.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public MUserInfo login(String cellPhone, String password) {
		DetachedCriteria query = DetachedCriteria.forClass(MUserInfo.class);
		Criterion criterion = Restrictions.eq("cellPhone", cellPhone);
		Criterion criterion1 = Restrictions.eq("password", password);
		query.add(criterion);
		query.add(criterion1);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (MUserInfo) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public MUserInfo loginByToken(String token) {
		DetachedCriteria query = DetachedCriteria.forClass(MUserInfo.class);
		Criterion criterion = Restrictions.eq("token", token);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (MUserInfo) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

}
