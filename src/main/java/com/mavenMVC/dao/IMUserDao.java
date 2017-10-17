package com.mavenMVC.dao;

import com.mavenMVC.entity.MUserInfo;

public interface IMUserDao {
	MUserInfo login(String cellPhone , String password);

	MUserInfo loginByToken(String token);
}
