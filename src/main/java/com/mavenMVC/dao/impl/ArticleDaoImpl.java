package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IArticleDao;
import com.mavenMVC.entity.Article;
import com.mavenMVC.util.Code;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/7/23.
 */
@Repository
@Transactional
public class ArticleDaoImpl extends GenericDaoHibernate<Article,Long> implements IArticleDao {

    public ArticleDaoImpl() {
        super(Article.class);
    }

    @Override
    public List<Article> getAllTopArticles(int start, int offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Article.class);
        Criterion criterion = Restrictions.eq("articleStatus", Code.ARTICLE_VALID);
        Criterion criterion1 = Restrictions.gt("articleTop", 0);
        query.add(criterion);
        query.add(criterion1);
        if((receivedIds!=null)&&(receivedIds.size()>0)){
            Criterion criterion2 = Restrictions.not(Restrictions.in("articleId", receivedIds));
            query.add(criterion2);
        }
        query.addOrder(Order.asc("articleTop"));
        List<Article> results = new ArrayList();
        if((start>=0)&&(offset>0)){
            results = getHibernateTemplate().findByCriteria(query, start, offset);
        }
        return results;
    }

    @Override
    public List<Article> getAllUnTopArticles(int start, int offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Article.class);
        Criterion criterion = Restrictions.eq("articleStatus", Code.ARTICLE_VALID);
        Criterion criterion1 = Restrictions.le("articleTop", 0);
        query.add(criterion);
        query.add(criterion1);
        if((receivedIds!=null)&&(receivedIds.size()>0)){
            Criterion criterion2 = Restrictions.not(Restrictions.in("articleId", receivedIds));
            query.add(criterion2);
        }
        query.addOrder(Order.desc("createTime"));
        List<Article> results = new ArrayList();
        if((start>=0)&&(offset>0)){
            results = getHibernateTemplate().findByCriteria(query, start, offset);
        }
        return results;
    }

    @Override
    public List<Article> getArticlesByType(int type, int start, int offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Article.class);
        Criterion criterion = Restrictions.eq("articleStatus", Code.ARTICLE_VALID);
        Criterion criterion1 = Restrictions.le("articleTop", 0);
        Criterion criterion2 = Restrictions.eq("articleType", type);
        query.add(criterion);
        query.add(criterion1);
        query.add(criterion2);
        if((receivedIds!=null)&&(receivedIds.size()>0)){
            Criterion criterion3 = Restrictions.not(Restrictions.in("articleId", receivedIds));
            query.add(criterion3);
        }
        query.addOrder(Order.desc("createTime"));
        List<Article> results = new ArrayList();
        if((start>=0)&&(offset>0)){
            results = getHibernateTemplate().findByCriteria(query,start,offset);
        }
        return results;
    }

    @Override
    public Article getArticleById(Long id) {
        return get(id);
    }

    @Override
    public List<Article> getArticlesByIds(List<Long> ids) {
        DetachedCriteria query = DetachedCriteria.forClass(Article.class);
        List<Article> results = new ArrayList<Article>();
        if(ids!=null && ids.size()>0){
            Criterion criterion = Restrictions.in("articleId", ids);
            query.add(criterion);
            query.addOrder(Order.desc("createTime"));
            results = getHibernateTemplate().findByCriteria(query);
        }
        return results;
    }

    @Override
    public List<Article> getAllArticleRegardlessStatus() {
        DetachedCriteria query = DetachedCriteria.forClass(Article.class);
        return getHibernateTemplate().findByCriteria(query);
    }

    @Override
    public void saveOrUpdateArticle(Article article) {
        if(article.getCreateTime() == null){
            article.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        article.setLastModTime(Calendar.getInstance().getTimeInMillis());
        article.setArticleTop(0);
        saveOrUpdate(article);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
