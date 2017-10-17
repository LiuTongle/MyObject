package com.mavenMVC.dao.impl;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IFeedBackDao;
import com.mavenMVC.entity.FeedBack;

@Repository
@Transactional
public class FeedBackDaoImpl extends GenericDaoHibernate<FeedBack, Long> implements IFeedBackDao {

	@Override
	public void saveFeedBack(FeedBack feedBack) {
		if (feedBack.getCreateTime()==null) {
			feedBack.setCreateTime(Calendar.getInstance().getTimeInMillis());
		}
		feedBack.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(feedBack);
	}
	
	public FeedBackDaoImpl() {
		super(FeedBack.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
}
