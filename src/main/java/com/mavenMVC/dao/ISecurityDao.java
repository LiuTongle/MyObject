package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.Security;

public interface ISecurityDao {
	List<Security> getSecurityListByUser(int userType, Long userId);

	void updateSecurityByUser(Security security);

	void saveSecurity(Security security);
}
