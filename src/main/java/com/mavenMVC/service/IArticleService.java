package com.mavenMVC.service;

import com.mavenMVC.entity.Article;
import com.mavenMVC.entity.Collection;

import java.util.List;

/**
 * Created by lizai on 16/5/24.
 */
public interface IArticleService {

    public void collectArticle(Long userId, Long articleId);

    public void unCollectArticle(Long userId, Long articleId);

    public Collection getCollectArticleAndUser(Long articleId, Long userId);

    public Article getArticleDetail(Long articleId);

    public List<Article> getMyCollectionArticles(Long userId, Integer start, Integer offset, List<Long> receivedIds);

    public List<Long> getMyCollectionArticleIds(Long userId);

    public List<Article> getAllTopArticles(int start, int offset, List<Long> receivedIds);

    public List<Article> getAllUnTopArticles(int start, int offset, List<Long> receivedIds);

    public List<Article> getArticlesByType(int type, int start, int offset, List<Long> receivedIds);

}
