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

import com.mavenMVC.dao.ICollectDao;
import com.mavenMVC.entity.Collect;

@Repository
@Transactional
public class CollectDaoImpl extends GenericDaoHibernate<Collect, Long> implements ICollectDao {

	@Override
	public void saveCollent(Collect collect) {
		collect.setCreateTime(Calendar.getInstance().getTimeInMillis());
		collect.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(collect);
	}

	@Override
	public void deleteCollent(Collect collect) {
		getHibernateTemplate().delete(collect);
	}

	@Override
	public boolean ifCollectExists(Long userId, Long contentId, Integer collectType) {
		DetachedCriteria query = DetachedCriteria.forClass(Collect.class);
		if (userId != null) {
			Criterion criterion = Restrictions.eq("userId", userId);
			query.add(criterion);
		}
		if (contentId != null) {
			Criterion criterion1 = Restrictions.eq("collectContentId", contentId);
			query.add(criterion1);
		}
		if (collectType != null) {
			Criterion criterion2 = Restrictions.eq("collectType", collectType);
			query.add(criterion2);
		}
		if (getHibernateTemplate().findByCriteria(query).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Collect> getCommodityByCollect(Long userId, Integer collectType,int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria query = DetachedCriteria.forClass(Collect.class);
		if (userId != null) {
			Criterion criterion = Restrictions.eq("userId", userId);
			query.add(criterion);
		}
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("collectId", receivedIds));
			query.add(criterion3);
		}
		if (collectType != null) {
			Criterion criterion2 = Restrictions.eq("collectType", collectType);
			query.add(criterion2);
		}
		return getHibernateTemplate().findByCriteria(query,start,offset);
	}
	
	@Override
	public List<Collect> getCollectBycontent(Long userId, Long contentId, Integer collectType) {
		DetachedCriteria query = DetachedCriteria.forClass(Collect.class);
		if (userId != null) {
			Criterion criterion = Restrictions.eq("userId", userId);
			query.add(criterion);
		}
		if (contentId != null) {
			Criterion criterion1 = Restrictions.eq("collectContentId", contentId);
			query.add(criterion1);
		}
		if (collectType != null) {
			Criterion criterion2 = Restrictions.eq("collectType", collectType);
			query.add(criterion2);
		}
		return getHibernateTemplate().findByCriteria(query);
	}

	public CollectDaoImpl() {
		super(Collect.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
