package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.Commodity;

/**
 * 
 * @author hcd
 *
 */
public interface ICommodityDao {
	Commodity getById(Long id);

	void saveCommodity(Commodity commodity);

	void updateCommodity(Commodity commodity);

	boolean ifCommodityExists(Long id);

	List<Commodity> searchCommodity(String query, int start, Integer offset, List<Long> receivedIds);

	List<Commodity> getCommoditys(int start, Integer offset, List<Long> receivedIds);

	List<Commodity> getCommoditysByBuyerUser(Long id, int start, Integer offset, List<Long> receivedIds);

	List<Commodity> getCommoditysByTab(Integer tab, int start, Integer offset, List<Long> receivedIds);

}
