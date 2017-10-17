package com.mavenMVC.service;

import com.mavenMVC.entity.MUserInfo;

public interface IMUserService {
	MUserInfo login(String cellPhone , String password);
	MUserInfo loginByToken(String token);
}
