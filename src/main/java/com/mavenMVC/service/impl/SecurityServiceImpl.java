package com.mavenMVC.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ISecurityDao;
import com.mavenMVC.entity.Security;
import com.mavenMVC.service.ISecurityService;

@Service("SecurityServiceImpl")
@Transactional
public class SecurityServiceImpl implements ISecurityService {

	@Autowired
	private ISecurityDao securityDao;

	@Override
	public List<Security> getSecurityListByUser(int userType, Long userId) {
		return securityDao.getSecurityListByUser(userType, userId);
	}

	@Override
	public void updateSecurityByUser(Security security) {
		securityDao.updateSecurityByUser(security);
	}

	@Override
	public void saveSecurity(Security security) {
		securityDao.saveSecurity(security);
	}

}
