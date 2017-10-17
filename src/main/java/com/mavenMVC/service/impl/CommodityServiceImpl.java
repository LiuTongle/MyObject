package com.mavenMVC.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ICollectDao;
import com.mavenMVC.dao.ICommodityDao;
import com.mavenMVC.entity.Collect;
import com.mavenMVC.entity.Commodity;
import com.mavenMVC.service.ICommodityService;

@Service("CommodityServiceImpl")
@Transactional
public class CommodityServiceImpl implements ICommodityService {

	@Autowired
	private ICommodityDao commodityDao;
	@Autowired
	private ICollectDao collectDao;

	@Override
	public Commodity getById(Long id) {
		return commodityDao.getById(id);
	}

	@Override
	public void saveCommodity(Commodity commodity) {
		commodityDao.saveCommodity(commodity);
	}

	@Override
	public void updateCommodity(Commodity commodity) {
		commodityDao.updateCommodity(commodity);
	}

	@Override
	public boolean ifCommodityExists(Long id) {
		return commodityDao.ifCommodityExists(id);
	}

	@Override
	public List<Commodity> searchCommodity(String query, int start, Integer offset, List<Long> receivedIds) {
		return commodityDao.searchCommodity(query, start, offset, receivedIds);
	}

	@Override
	public List<Commodity> getCommoditys(int start, Integer offset, List<Long> receivedIds) {
		return commodityDao.getCommoditys(start, offset, receivedIds);
	}
	
	@Override
	public List<Collect> getCommoditysByCollect(Long userId, int start, Integer offset, List<Long> receivedIds) {
		return collectDao.getCommodityByCollect(userId, 1, start,offset,receivedIds);
	}
	
	@Override
	public List<Commodity> getCommoditysByBuyerUser(Long id, int start, Integer offset, List<Long> receivedIds) {
		return commodityDao.getCommoditysByBuyerUser(id, start, offset, receivedIds);
	}

	@Override
	public List<Commodity> getCommoditysByTab(Integer tab, int start, Integer offset, List<Long> receivedIds) {
		return commodityDao.getCommoditysByTab(tab, start, offset, receivedIds);
	}

}
