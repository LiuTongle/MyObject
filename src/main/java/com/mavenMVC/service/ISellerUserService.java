package com.mavenMVC.service;

import com.mavenMVC.entity.SellerUser;

/**
 * Created by hcd on 15/6/10.
 */
public interface ISellerUserService {

	public SellerUser getUserByToken(String token);

	public SellerUser getUserById(Long id);

	public SellerUser getUserByCellphone(String cellphone);

	public SellerUser registerUser(String userName, String password, String cellphone);

	public SellerUser registerUserByWx(String userName, String wxId);

	public boolean ifUserCellphoneRegisted(String cellphone);

	public SellerUser ifUserWxIdRegisted(String wxId);

	public boolean resetPassword(String cellphone, String password);

	public SellerUser loginValid(String cellphone, String password);

	public void updateUser(SellerUser userEntity);

	public boolean useVerifyCode(String cellphone, int verifyCode, int type);

}
