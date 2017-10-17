package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.entity.Compete;

@Repository
@Transactional
public class CompeteDaoImpl extends GenericDaoHibernate<Compete, Long> implements com.mavenMVC.dao.ICompeteDao {

	@Override
	public Compete getById(Long id) {
		return get(id);
	}

	@Override
	public void saveCompete(Compete compete) {
		if (compete.getCreateTime()==null) {
			compete.setCreateTime(Calendar.getInstance().getTimeInMillis());
		}
		compete.setLastmodTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(compete);
	}

	@Override
	public void updateCompete(Compete Compete) {
		Compete.setLastmodTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(Compete);
	}

	@Override
	public boolean ifCompeteExists(Long id) {
		DetachedCriteria query = DetachedCriteria.forClass(Compete.class);
		Criterion criterion = Restrictions.eq("CompeteId", id);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Long getCompeteCountByTab(Integer tab) {
		Session session = this.getSession();
		String queryString = "SELECT COUNT(*) FROM Compete WHERE competeTab=:competeTab";
		Query createQuery = session.createQuery(queryString);
		createQuery.setInteger("competeTab", tab);
		List<Long> list = createQuery.list();
		if (list!=null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Compete> getCompetesByCommodity(Long id) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Compete.class);
		Criterion criterion = Restrictions.eq("commodityId", id);
		detachedCriteria.add(criterion);
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public CompeteDaoImpl() {
		super(Compete.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Compete> searchCompete(String query, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Compete.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("competeTitle", query, MatchMode.ANYWHERE));
		detachedCriteria.add(disjunction);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("competeId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

	@Override
	public List<Compete> getCompetes(int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Compete.class);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("competeId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

	@Override
	public List<Compete> getCompetesByTab(Integer tab, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Compete.class);
		Criterion criterion = Restrictions.eq("competeTab", tab);
		detachedCriteria.add(criterion);
		Criterion criterion2 = Restrictions.eq("competeStatus", 1);
		detachedCriteria.add(criterion2);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("competeId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

	@Override
	public List<Compete> getCompetesByCommodity(Long userId, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Compete.class);
		Criterion criterion = Restrictions.eq("sellerUserId", userId);
		detachedCriteria.add(criterion);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("competeId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

}
