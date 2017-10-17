package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IExtractMoneyDao;
import com.mavenMVC.entity.ExtractMoney;
import com.mavenMVC.service.IExtractMoneyService;

@Service("ExtractMoneyServiceImpl")
@Transactional
public class ExtractMoneyServiceImpl implements IExtractMoneyService {

	@Autowired
	IExtractMoneyDao extractMoneyDao;
	
	@Override
	public void extractMoney(ExtractMoney extractMoney) {
		extractMoneyDao.addExtractMoney(extractMoney);
	}

}
