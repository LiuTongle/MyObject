package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.ICollectionDao;
import com.mavenMVC.entity.Collection;
import com.mavenMVC.util.CollectionCode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/7/23.
 */
@Repository
@Transactional
public class CollectionDaoImpl extends GenericDaoHibernate<Collection,Long> implements ICollectionDao {

    public CollectionDaoImpl() {
        super(Collection.class);
    }

    @Override
    public List<Collection> getCollectionsByUserIdAndType(Long userId, Integer type, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Collection.class);
        Criterion criterion = Restrictions.eq("userId", userId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("collectionType", type);
        query.add(criterion1);
        if ((receivedIds != null) && (receivedIds.size() > 0)) {
            Criterion criterion3 = Restrictions.not(Restrictions.in("collectionId", receivedIds));
            query.add(criterion3);
        }
        query.addOrder(Order.desc("createTime"));
        List<Collection> results = getHibernateTemplate().findByCriteria(query,start,offset);
        return results;
    }

    @Override
    public Collection getCollectByArticleIdAndUserId(Long articleId, Long userId) {
        DetachedCriteria query = DetachedCriteria.forClass(Collection.class);
        Criterion criterion = Restrictions.eq("articleId", articleId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("userId", userId);
        query.add(criterion1);
        query.addOrder(Order.desc("createTime"));
        List<Collection> results = getHibernateTemplate().findByCriteria(query);
        if(results != null && results.size()>0){
            return results.get(0);
        }
        return null;
    }

    @Override
    public List<Long> getCollectArticleIds(Long userId) {
        DetachedCriteria query = DetachedCriteria.forClass(Collection.class);
        Criterion criterion = Restrictions.eq("userId", userId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("collectionType", CollectionCode.COLLECTION_ARTICLE);
        query.add(criterion1);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Property.forName("articleId"));
        query.setProjection(projectionList);
        query.addOrder(Order.desc("createTime"));
        List<Long> results = getHibernateTemplate().findByCriteria(query);
        if(results != null && results.size()>0)
            return results;
        else
            return null;
    }

    @Override
    public List<Long> getCollectDoctorIds(Long userId) {
        DetachedCriteria query = DetachedCriteria.forClass(Collection.class);
        Criterion criterion = Restrictions.eq("userId", userId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("collectionType", CollectionCode.COLLECTION_DOCTOR);
        query.add(criterion1);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Property.forName("doctorId"));
        query.setProjection(projectionList);
        query.addOrder(Order.desc("createTime"));
        List<Long> results = getHibernateTemplate().findByCriteria(query);
        if(results != null && results.size()>0)
            return results;
        else
            return null;
    }

    @Override
    public void saveOrUpdateCollection(Collection collection) {
        if(collection.getCreateTime() == null){
            collection.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        collection.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(collection);
    }

    @Override
    public void removeCollection(Collection collection) {
        getHibernateTemplate().delete(collection);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
