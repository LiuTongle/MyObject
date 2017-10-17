package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.BuyerUser;

public interface IBuyerUserDao {
	public BuyerUser getById(Long id);

	public BuyerUser getUserByName(String name);

	public void saveUser(BuyerUser user);

	public void updateUser(BuyerUser user);

	public BuyerUser getUserByToken(String token);

	public boolean ifUserExists(String cellphone);

	public BuyerUser ifUserByWxIdExists(String wxId);

	public BuyerUser getUserByCellphone(String cellphone);

	public BuyerUser getUserByCellphoneAndPassword(String cellphone, String password);

	public List<BuyerUser> getUsersByIds(List<Long> ids);
}
