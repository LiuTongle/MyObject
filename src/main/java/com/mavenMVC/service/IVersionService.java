package com.mavenMVC.service;

import com.mavenMVC.entity.Version;

/**
 * Created by lizai on 16/5/24.
 */
public interface IVersionService {

    public void saveOrUpdateVersion(Version version);

    public Version getVersionById(Long id);

    public Long getMaxId();

}
