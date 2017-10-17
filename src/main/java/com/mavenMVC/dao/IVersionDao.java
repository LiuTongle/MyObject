package com.mavenMVC.dao;

import com.mavenMVC.entity.Version;

/**
 * Created by lizai on 16/4/11.
 */
public interface IVersionDao {

    public void saveOrUpdateVersion(Version version);

    public Version getVersionById(Long id);

    public Long getMaxId();

}
