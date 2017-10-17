package com.mavenMVC.dao.impl;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mavenMVC.dao.IHouseInfoDao;
import com.mavenMVC.entity.HouseInfo;
@Repository
public class HouseInfoDaoImpl extends GenericDaoHibernate<HouseInfo, Long> implements IHouseInfoDao {

	public HouseInfoDaoImpl() {
		super(HouseInfo.class);
	}


	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	@Override
	public void upload(HouseInfo houseInfo) {
		houseInfo.setCreateTime(Calendar.getInstance().getTimeInMillis());
		houseInfo.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(houseInfo);
	}

}
