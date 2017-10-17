package com.mavenMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IFeedBackDao;
import com.mavenMVC.entity.FeedBack;
import com.mavenMVC.service.IFeedBackService;

@Service("FeedBackServiceImpl")
@Transactional
public class FeedBackServiceImpl implements IFeedBackService {

	@Autowired
	IFeedBackDao feedBackDao;
	
	@Override
	public void saveFeedBack(FeedBack feedBack) {
		feedBackDao.saveFeedBack(feedBack);
	}

}
