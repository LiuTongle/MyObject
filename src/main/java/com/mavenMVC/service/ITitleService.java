package com.mavenMVC.service;

import com.mavenMVC.entity.Title;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface ITitleService {

    public Title getTitleById(Long id);

    public Title searchTitleByName(String name);

    public List<Title> getAllTitles();

}