package com.mavenMVC.dao.impl;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IExtractMoneyDao;
import com.mavenMVC.entity.ExtractMoney;

@Repository
@Transactional
public class ExtractMoneyDaoImpl extends GenericDaoHibernate<ExtractMoney, Long> implements IExtractMoneyDao {

	@Override
	public void addExtractMoney(ExtractMoney extractMoney) {
		extractMoney.setCreateTime(Calendar.getInstance().getTimeInMillis());
		extractMoney.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(extractMoney);
	}
	
	public ExtractMoneyDaoImpl() {
		super(ExtractMoney.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
