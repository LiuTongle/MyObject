package com.mavenMVC.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IBuyerUserDao;
import com.mavenMVC.entity.BuyerUser;
import com.mavenMVC.service.IBuyerUserService;
import com.mavenMVC.util.MD5;

/**
 * 
 * @author hcd
 *
 */
@Service("BuyerUserServiceImpl")
@Transactional
public class BuyerUserServiceImpl implements IBuyerUserService {

	@Autowired
	private IBuyerUserDao buyerUserDao;

	@Override
	public BuyerUser registerUser(String userName, String password, String cellphone) {
		if (cellphone != null) {
			BuyerUser userEntity = new BuyerUser();
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
			buyerUserDao.saveUser(userEntity);
			return userEntity;
		} else {
			return null;
		}
	}

	@Override
	public BuyerUser registerUserByWx(String userName, String wxId) {
		if (wxId != null) {
			BuyerUser userEntity = new BuyerUser();
			if (userName != null) {
				userEntity.setUserName(userName);
			}
			userEntity.setUserStatus(0);
			userEntity.setUserLogin(1);
			userEntity.setUserToken(MD5.GetMD5Code(wxId + Calendar.getInstance().getTimeInMillis()));
			userEntity.setUserWXId(wxId);
			buyerUserDao.saveUser(userEntity);
			return userEntity;
		} else {
			return null;
		}
	}

	@Override
	public boolean ifUserCellphoneRegisted(String cellphone) {
		return buyerUserDao.ifUserExists(cellphone);
	}

	@Override
	public BuyerUser ifUserWxIdRegisted(String wxId) {
		return buyerUserDao.ifUserByWxIdExists(wxId);
	}

	@Override
	public boolean resetPassword(String cellphone, String password) {
		if (cellphone != null) {
			BuyerUser userEntity = buyerUserDao.getUserByCellphone(cellphone);
			if (userEntity != null) {
				userEntity.setUserPassword(password);
				buyerUserDao.updateUser(userEntity);
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
	public BuyerUser getUserByToken(String token) {
		return buyerUserDao.getUserByToken(token);
	}

	@Override
	public BuyerUser getUserById(Long id) {
		return buyerUserDao.getById(id);
	}

	@Override
	public BuyerUser loginValid(String cellphone, String password) {
		BuyerUser user = buyerUserDao.getUserByCellphoneAndPassword(cellphone, password);
		if (user != null) {
			user.setUserLogin(1);
			buyerUserDao.updateUser(user);
		}
		return user;
	}

	@Override
	public void updateUser(BuyerUser userEntity) {
		buyerUserDao.updateUser(userEntity);
	}

	@Override
	public BuyerUser getUserByCellphone(String cellphone) {
		return buyerUserDao.getUserByCellphone(cellphone);
	}

}
