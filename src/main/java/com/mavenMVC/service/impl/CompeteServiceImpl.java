package com.mavenMVC.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ICollectDao;
import com.mavenMVC.dao.ICompeteDao;
import com.mavenMVC.entity.Collect;
import com.mavenMVC.entity.Compete;
import com.mavenMVC.service.ICompeteService;

@Service("CompeteServiceImpl")
@Transactional
public class CompeteServiceImpl implements ICompeteService {

	@Autowired
	private ICompeteDao competeService;

	@Autowired
	private ICollectDao collectDao;

	@Override
	public Compete getById(Long id) {
		return competeService.getById(id);
	}

	@Override
	public void saveCompete(Compete Compete) {
		competeService.saveCompete(Compete);
	}

	@Override
	public boolean competeIfCollect(Long CompeteId, Long userId) {
		return collectDao.ifCollectExists(userId, CompeteId, 1);
	}

	@Override
	public boolean collectCompete(Long competeId, Long userId) {
		if (collectDao.ifCollectExists(userId, competeId, 1)) {
			// 已收藏
			Collect collect = new Collect();
			collect.setCollectContentId(competeId);
			collect.setUserId(userId);
			collect.setCollectType(1);

			List<Collect> collectBycontent = collectDao.getCollectBycontent(userId, competeId, 1);
			for (int i = 0; i < collectBycontent.size(); i++)
				collectDao.deleteCollent(collectBycontent.get(i));

			// 取消收藏成功
			return false;
		} else {
			// 未收藏
			Collect collect = new Collect();
			collect.setCollectContentId(competeId);
			collect.setUserId(userId);
			collect.setCollectType(1);
			collectDao.saveCollent(collect);
			// 收藏成功
			return true;
		}
	}

	@Override
	public void updateCompete(Compete Compete) {
		competeService.updateCompete(Compete);
	}

	@Override
	public boolean ifCompeteExists(Long id) {
		return competeService.ifCompeteExists(id);
	}

	@Override
	public List<Compete> getCompetesByCommodity(Long id) {
		return competeService.getCompetesByCommodity(id);
	}

	@Override
	public Integer getCompeteCountByTab(Integer tab) {
		Long competeCountByTab = competeService.getCompeteCountByTab(tab);
		return competeCountByTab.intValue();
	}
 
	@Override
	public List<Compete> searchCompete(String query, int start, Integer offset, List<Long> receivedIds) {
		return competeService.searchCompete(query, start, offset, receivedIds);
	}

	@Override
	public List<Compete> getCompetes(int start, Integer offset, List<Long> receivedIds) {
		return competeService.getCompetes(start, offset, receivedIds);
	}

	@Override
	public List<Compete> getCompetesByTab(Integer tab, int start, Integer offset, List<Long> receivedIds) {
		return competeService.getCompetesByTab(tab, start, offset, receivedIds);
	}
	
	@Override
	public List<Compete> getCompetesByCommodity(Long userId, int start, Integer offset, List<Long> receivedIds) {
		return competeService.getCompetesByCommodity(userId, start, offset, receivedIds);
	}

}
