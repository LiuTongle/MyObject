package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IHouseInfoDao;
import com.mavenMVC.entity.HouseInfo;
import com.mavenMVC.service.IHouseInfoService;
@Service
@Transactional
public class HouseInfoServiceImpl implements IHouseInfoService {
	@Autowired
	IHouseInfoDao houseInfoDao;

	@Override
	public void upload(HouseInfo houseInfo) {
		houseInfoDao.upload(houseInfo);
	}
}
