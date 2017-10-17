package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IVersionDao;
import com.mavenMVC.entity.Version;
import com.mavenMVC.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lizai on 16/5/24.
 */
@Service("VersionServiceImpl")
@Transactional
public class VersionServiceImpl implements IVersionService {

    @Autowired
    private IVersionDao versionDao;

    @Override
    public void saveOrUpdateVersion(Version version) {
        versionDao.saveOrUpdateVersion(version);
    }

    @Override
    public Version getVersionById(Long id) {
        return versionDao.getVersionById(id);
    }

    @Override
    public Long getMaxId() {
        return versionDao.getMaxId();
    }
}
