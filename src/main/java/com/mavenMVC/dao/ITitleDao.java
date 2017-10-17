package com.mavenMVC.dao;

import com.mavenMVC.entity.Title;

import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */
public interface ITitleDao {

    public Title getTitleById(Long id);

    public List<Title> getAllTitles();

    public Title getTitleByName(String name);

    public void saveOrUpdateTitle(Title title);
}
