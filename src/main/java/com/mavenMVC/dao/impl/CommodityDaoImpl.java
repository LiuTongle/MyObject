package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.entity.Commodity;

@Repository
@Transactional
public class CommodityDaoImpl extends GenericDaoHibernate<Commodity, Long> implements com.mavenMVC.dao.ICommodityDao {

	@Override
	public Commodity getById(Long id) {
		return get(id);
	}

	@Override
	public void saveCommodity(Commodity commodity) {
		if (commodity.getCreateTime()==null) {
			commodity.setCreateTime(Calendar.getInstance().getTimeInMillis());
		}
		commodity.setLastmodTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(commodity);
	}

	@Override
	public void updateCommodity(Commodity commodity) {
		commodity.setLastmodTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(commodity);
	}

	@Override
	public boolean ifCommodityExists(Long id) {
		DetachedCriteria query = DetachedCriteria.forClass(Commodity.class);
		Criterion criterion = Restrictions.eq("commodityId", id);
		query.add(criterion);
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public CommodityDaoImpl() {
		super(Commodity.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Commodity> searchCommodity(String query, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Commodity.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("commodityTitle", query, MatchMode.ANYWHERE));
		detachedCriteria.add(disjunction);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("commodityId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

	@Override
	public List<Commodity> getCommoditys(int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Commodity.class);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("commodityId", receivedIds));
			detachedCriteria.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, start, offset);
	}

	@Override
	public List<Commodity> getCommoditysByBuyerUser(Long id, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria query = DetachedCriteria.forClass(Commodity.class);
		Criterion criterion = Restrictions.eq("buyerUserId", id);
		query.add(criterion);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("commodityId", receivedIds));
			query.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(query, start, offset);
	}

	@Override
	public List<Commodity> getCommoditysByTab(Integer tab, int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria query = DetachedCriteria.forClass(Commodity.class);
		Criterion criterion = Restrictions.eq("commodityTab", tab);
		query.add(criterion);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("commodityId", receivedIds));
			query.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(query, start, offset);
	}

}
