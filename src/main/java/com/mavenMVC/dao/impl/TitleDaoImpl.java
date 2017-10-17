package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.ITitleDao;
import com.mavenMVC.entity.Title;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
public class TitleDaoImpl extends GenericDaoHibernate<Title, Long> implements ITitleDao {

    public TitleDaoImpl() {
        super(Title.class);
    }

    @Override
    public Title getTitleById(Long id) {
        return get(id);
    }

    @Override
    public List<Title> getAllTitles() {
        DetachedCriteria query = DetachedCriteria.forClass(Title.class);
        return getHibernateTemplate().findByCriteria(query);
    }

    @Override
    public Title getTitleByName(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Title.class);
        Criterion criterion = Restrictions.eq("titleName", name);
        query.add(criterion);
        if (getHibernateTemplate().findByCriteria(query).size() > 0) {
            return (Title) getHibernateTemplate().findByCriteria(query).get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveOrUpdateTitle(Title title) {
        if (title.getCreateTime() == null) {
            title.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        title.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(title);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

}
