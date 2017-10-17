package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.SellerUser;

public interface ISellerUserDao {
	public SellerUser getById(Long id);

	public SellerUser getUserByName(String name);

	public void saveUser(SellerUser user);

	public void updateUser(SellerUser user);

	public SellerUser getUserByToken(String token);

	public boolean ifUserExists(String cellphone);

	public SellerUser ifUserByWxIdExists(String wxId);

	public SellerUser getUserByCellphone(String cellphone);

	public SellerUser getUserByCellphoneAndPassword(String cellphone, String password);

	public List<SellerUser> getUsersByIds(List<Long> ids);
}
