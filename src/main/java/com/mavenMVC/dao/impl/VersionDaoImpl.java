package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IVersionDao;
import com.mavenMVC.entity.Version;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */

@Repository
@Transactional
public class VersionDaoImpl extends GenericDaoHibernate<Version, Long> implements IVersionDao {

    public VersionDaoImpl() {
        super(Version.class);
    }

    @Override
    public void saveOrUpdateVersion(Version version) {
        if(version.getCreateTime() == null){
            version.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        version.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(version);
    }

    @Override
    public Version getVersionById(Long id) {
        return get(id);
    }

    @Override
    public Long getMaxId() {
        DetachedCriteria query = DetachedCriteria.forClass(Version.class);
        ProjectionList projectionList = Projections.projectionList();
//        projectionList.add(Property.forName("versionId"));
        projectionList.add(Projections.max("versionId"));
        query.setProjection(projectionList);
        List<Long> results = getHibernateTemplate().findByCriteria(query);
        if(results!=null){
            return results.get(0);
        }
        return Long.valueOf(0);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
