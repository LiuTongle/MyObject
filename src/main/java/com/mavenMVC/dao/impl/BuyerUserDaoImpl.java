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

import com.mavenMVC.dao.IBuyerUserDao;
import com.mavenMVC.entity.BuyerUser;

@Repository
@Transactional
public class BuyerUserDaoImpl extends GenericDaoHibernate<BuyerUser, Long> implements IBuyerUserDao {

	public BuyerUserDaoImpl() {
		super(BuyerUser.class);
	}

	@Override
	public void saveUser(BuyerUser user) {
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
	public BuyerUser getById(Long id) {
		return get(id);
	}

	@Override
	public BuyerUser getUserByName(String name) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userName", name);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (BuyerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateUser(BuyerUser user) {
		user.setLastModTime(Calendar.getInstance().getTimeInMillis());
		// saveOrUpdate(getHibernateTemplate().merge(user));
		getHibernateTemplate().merge(user);
	}

	@Override
	public BuyerUser getUserByToken(String token) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userToken", token);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (BuyerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean ifUserExists(String cellphone) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public BuyerUser ifUserByWxIdExists(String wxId) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userWXId", wxId);
		query.add(criterion);
		List<BuyerUser> findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		} else {
			return null;
		}
	}

	@Override
	public BuyerUser getUserByCellphone(String cellphone) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (BuyerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public BuyerUser getUserByCellphoneAndPassword(String cellphone, String password) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.eq("userCellphone", cellphone);
		Criterion criterion1 = Restrictions.eq("userPassword", password);
		query.add(criterion);
		query.add(criterion1);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return (BuyerUser) getHibernateTemplate().findByCriteria(query).get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<BuyerUser> getUsersByIds(List<Long> ids) {
		DetachedCriteria query = DetachedCriteria.forClass(BuyerUser.class);
		Criterion criterion = Restrictions.in("userId", ids);
		query.add(criterion);
		return getHibernateTemplate().findByCriteria(query);
	}

}
