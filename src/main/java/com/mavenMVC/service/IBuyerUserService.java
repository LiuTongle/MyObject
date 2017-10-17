package com.mavenMVC.service;

import com.mavenMVC.entity.BuyerUser;

/**
 * Created by hcd on 15/6/10.
 */
public interface IBuyerUserService {

	public BuyerUser getUserByToken(String token);

	public BuyerUser getUserById(Long id);

	public BuyerUser getUserByCellphone(String cellphone);

	public BuyerUser registerUser(String userName, String password, String cellphone);

	public BuyerUser registerUserByWx(String userName, String wxId);

	public boolean ifUserCellphoneRegisted(String cellphone);

	public BuyerUser ifUserWxIdRegisted(String wxId);

	public boolean resetPassword(String cellphone, String password);

	public BuyerUser loginValid(String cellphone, String password);

	public void updateUser(BuyerUser userEntity);

	public boolean useVerifyCode(String cellphone, int verifyCode, int type);

}
