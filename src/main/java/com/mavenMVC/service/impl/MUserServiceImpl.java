package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IMUserDao;
import com.mavenMVC.entity.MUserInfo;
import com.mavenMVC.service.IMUserService;

@Service("MUserServiceImpl")
@Transactional
public class MUserServiceImpl implements IMUserService {

	@Autowired
	IMUserDao MUserDao;
	
	@Override
	public MUserInfo login(String cellPhone, String password) {
		return MUserDao.login(cellPhone, password);
	}

	@Override
	public MUserInfo loginByToken(String token) {
		return MUserDao.loginByToken(token);
	}

}
