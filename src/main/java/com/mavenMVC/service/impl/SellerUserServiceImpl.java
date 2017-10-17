package com.mavenMVC.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ISellerUserDao;
import com.mavenMVC.entity.SellerUser;
import com.mavenMVC.service.ISellerUserService;
import com.mavenMVC.util.MD5;

/**
 * 
 * @author hcd
 *
 */
@Service("SellerUserServiceImpl")
@Transactional
public class SellerUserServiceImpl implements ISellerUserService {

	@Autowired
	private ISellerUserDao sellerUserDao;

	@Override
	public SellerUser registerUser(String userName, String password, String cellphone) {
		if (cellphone != null) {
			SellerUser userEntity = new SellerUser();
			userEntity.setUserCellphone(cellphone);
			if (userName != null) {
				userEntity.setUserName(userName);
			}
			if (password != null) {
				userEntity.setUserPassword(password);
			}
			if (cellphone != null) {
				userEntity.setUserCellphone(cellphone);
			}
			userEntity.setUserStatus(0);
			userEntity.setUserLogin(1);
			userEntity.setUserToken(MD5.GetMD5Code(cellphone + Calendar.getInstance().getTimeInMillis()));
			sellerUserDao.saveUser(userEntity);
			return userEntity;
		} else {
			return null;
		}
	}

	@Override
	public SellerUser registerUserByWx(String userName, String wxId) {
		if (wxId != null) {
			SellerUser sellerUser = new SellerUser();
			if (userName != null) {
				sellerUser.setUserName(userName);
			}
			sellerUser.setUserStatus(0);
			sellerUser.setUserLogin(1);
			sellerUser.setUserToken(MD5.GetMD5Code(wxId + Calendar.getInstance().getTimeInMillis()));
			sellerUser.setUserWXId(wxId);
			sellerUserDao.saveUser(sellerUser);
			return sellerUser;
		} else {
			return null;
		}
	}

	@Override
	public boolean ifUserCellphoneRegisted(String cellphone) {
		return sellerUserDao.ifUserExists(cellphone);
	}

	@Override
	public SellerUser ifUserWxIdRegisted(String wxId) {
		return sellerUserDao.ifUserByWxIdExists(wxId);
	}

	@Override
	public boolean resetPassword(String cellphone, String password) {
		if (cellphone != null) {
			SellerUser userEntity = sellerUserDao.getUserByCellphone(cellphone);
			if (userEntity != null) {
				userEntity.setUserPassword(password);
				sellerUserDao.updateUser(userEntity);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean useVerifyCode(String cellphone, int verifyCode, int type) {
		return true;
		// if ((cellphone != null) && (verifyCode > 0)) {
		// String result = HttpRequestUtil.sendPost(
		// "https://api.leancloud.cn/1.1/verifySmsCode/" + verifyCode +
		// "?mobilePhoneNumber=" + cellphone, "",
		// type);
		// if (result.equals("{}"))
		// return true;
		// else
		// return false;
		// } else {
		// return false;
		// }
	}

	@Override
	public SellerUser getUserByToken(String token) {
		return sellerUserDao.getUserByToken(token);
	}

	@Override
	public SellerUser getUserById(Long id) {
		return sellerUserDao.getById(id);
	}

	@Override
	public SellerUser loginValid(String cellphone, String password) {
		SellerUser user = sellerUserDao.getUserByCellphoneAndPassword(cellphone, password);
		if (user != null) {
			user.setUserLogin(1);
			sellerUserDao.updateUser(user);
		}
		return user;
	}

	@Override
	public void updateUser(SellerUser userEntity) {
		sellerUserDao.updateUser(userEntity);
	}

	@Override
	public SellerUser getUserByCellphone(String cellphone) {
		return sellerUserDao.getUserByCellphone(cellphone);
	}

}
