package com.mavenMVC.service.impl;

import com.mavenMVC.dao.ITitleDao;
import com.mavenMVC.entity.Hospital;
import com.mavenMVC.entity.Title;
import com.mavenMVC.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizai on 16/5/9.
 */

@Service("TitleServiceImpl")
@Transactional
public class TitleServiceImpl implements ITitleService {

    @Autowired
    private ITitleDao iTitleDao;

    @Override
    public Title getTitleById(Long id) {
        return iTitleDao.getTitleById(id);
    }

    @Override
    public Title searchTitleByName(String name) {
        Title title = iTitleDao.getTitleByName(name);
        if(title == null){
            title = new Title();
            title.setTitleName(name);
            iTitleDao.saveOrUpdateTitle(title);
        }
        return title;
    }

    @Override
    public List<Title> getAllTitles() {
        return iTitleDao.getAllTitles();
    }
}
