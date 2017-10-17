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

import com.mavenMVC.dao.ISellerUserDao;
import com.mavenMVC.entity.SellerUser;

@Repository
@Transactional
public class SellerUserDaoImpl extends GenericDaoHibernate<SellerUser, Long> implements ISellerUserDao {

	public SellerUserDaoImpl() {
		super(SellerUser.class);
	}

	@Override
	public void saveUser(SellerUser user) {
		user.setCreateTime(Calendar.getInstance().getTimeInMillis());
		user.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(user);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public SellerUser getById(Long id) {
		return get(id);
	}

	@Override
	public SellerUser getUserByName(String name) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userName", name);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (SellerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateUser(SellerUser user) {
		user.setLastModTime(Calendar.getInstance().getTimeInMillis());
		// saveOrUpdate(getHibernateTemplate().merge(user));
		getHibernateTemplate().merge(user);
	}

	@Override
	public SellerUser getUserByToken(String token) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userToken", token);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (SellerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean ifUserExists(String cellphone) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public SellerUser ifUserByWxIdExists(String wxId) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userWXId", wxId);
		query.add(criterion);
		List<SellerUser> findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		} else {
			return null;
		}
	}

	@Override
	public SellerUser getUserByCellphone(String cellphone) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (SellerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public SellerUser getUserByCellphoneAndPassword(String cellphone, String password) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		Criterion criterion1 = Restrictions.eq("userPassword", password);
		query.add(criterion);
		query.add(criterion1);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (SellerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<SellerUser> getUsersByIds(List<Long> ids) {
		DetachedCriteria query = DetachedCriteria.forClass(SellerUser.class);
		Criterion criterion = Restrictions.in("userId", ids);
		query.add(criterion);
		return getHibernateTemplate().findByCriteria(query);
	}

}
