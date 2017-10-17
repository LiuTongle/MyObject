package com.mavenMVC.service;

import java.util.List;

import com.mavenMVC.entity.Security;

public interface ISecurityService {
	List<Security> getSecurityListByUser(int userType, Long userId);

	void updateSecurityByUser(Security security);

	void saveSecurity(Security security);
}
