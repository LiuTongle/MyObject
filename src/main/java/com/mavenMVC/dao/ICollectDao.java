package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.Collect;

public interface ICollectDao {
	void saveCollent(Collect collect);

	void deleteCollent(Collect collect);

	List<Collect> getCollectBycontent(Long userId, Long contentId, Integer collectType);

	boolean ifCollectExists(Long userId, Long contentId, Integer collectType);

	List<Collect> getCommodityByCollect(Long userId, Integer collectType, int start, Integer offset, List<Long> receivedIds);
}
