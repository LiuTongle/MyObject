package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IArticleDao;
import com.mavenMVC.dao.ICollectionDao;
import com.mavenMVC.entity.Article;
import com.mavenMVC.entity.Collection;
import com.mavenMVC.service.IArticleService;
import com.mavenMVC.util.CollectionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizai on 16/5/24.
 */
@Service("ArticleServiceImpl")
@Transactional
public class ArticleServiceImpl implements IArticleService{

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private ICollectionDao collectionDao;

    @Override
    public void collectArticle(Long userId, Long articleId) {
        Article article = articleDao.getArticleById(articleId);
        if(article != null){
            Collection collection = new Collection();
            collection.setUserId(userId);
            collection.setArticleId(articleId);
            collection.setCollectionType(CollectionCode.COLLECTION_ARTICLE);
            collectionDao.saveOrUpdateCollection(collection);
            if(article.getArticleCollection() == null){
                article.setArticleCollection(1);
            }else{
                article.setArticleCollection(article.getArticleCollection() + 1);
            }
            articleDao.saveOrUpdateArticle(article);
        }
    }

    @Override
    public void unCollectArticle(Long userId, Long articleId) {
        Collection collection = collectionDao.getCollectByArticleIdAndUserId(articleId, userId);
        collectionDao.removeCollection(collection);
        Article article = articleDao.getArticleById(articleId);
        if(article != null){
            if(article.getArticleCollection() == null || article.getArticleCollection() < 1){
                article.setArticleCollection(0);
            }else{
                article.setArticleCollection(article.getArticleCollection() - 1);
            }
            articleDao.saveOrUpdateArticle(article);
        }
    }

    @Override
    public Collection getCollectArticleAndUser(Long articleId, Long userId) {
        return collectionDao.getCollectByArticleIdAndUserId(articleId,userId);
    }

    @Override
    public Article getArticleDetail(Long articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Override
    public List<Article> getMyCollectionArticles(Long userId, Integer start, Integer offset, List<Long> receiveIds) {
        List<Collection> collections = collectionDao.getCollectionsByUserIdAndType(userId, CollectionCode.COLLECTION_ARTICLE, start, offset,receiveIds);
        List<Long> aIds = new ArrayList<Long>();
        for(Collection collection : collections){
            aIds.add(collection.getArticleId());
        }
        List<Article> articles = articleDao.getArticlesByIds(aIds);
        return articles;
    }

    @Override
    public List<Long> getMyCollectionArticleIds(Long userId) {
        List<Long> articleIds = collectionDao.getCollectArticleIds(userId);
        return articleIds;
    }

    @Override
    public List<Article> getAllTopArticles(int start, int offset, List<Long> receivedIds) {
        return articleDao.getAllTopArticles(start,offset,receivedIds);
    }

    @Override
    public List<Article> getAllUnTopArticles(int start, int offset, List<Long> receivedIds) {
        return articleDao.getAllUnTopArticles(start, offset, receivedIds);
    }

    @Override
    public List<Article> getArticlesByType(int type, int start, int offset, List<Long> receivedIds) {
        return articleDao.getArticlesByType(type,start,offset,receivedIds);
    }
}
