package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ISecurityDao;
import com.mavenMVC.entity.Security;

@Repository
@Transactional
public class SecurityDaoImpl extends GenericDaoHibernate<Security, Long> implements ISecurityDao {

	@Override
	public List<Security> getSecurityListByUser(int userType, Long userId) {
		DetachedCriteria query = DetachedCriteria.forClass(Security.class);
		Criterion criterion = Restrictions.eq("userType", userType);
		Criterion criterion1 = Restrictions.eq("userId", userId);
		query.add(criterion);
		query.add(criterion1);
		return getHibernateTemplate().findByCriteria(query);
	}

	@Override
	public void updateSecurityByUser(Security security) {
		security.setLastModTime(Calendar.getInstance().getTimeInMillis());
		getHibernateTemplate().merge(security);
	}

	@Override
	public void saveSecurity(Security security) {
		security.setCreateTime(Calendar.getInstance().getTimeInMillis());
		security.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(security);
	}

	public SecurityDaoImpl() {
		super(Security.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
