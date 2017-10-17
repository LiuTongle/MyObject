package com.mavenMVC.dao;

import com.mavenMVC.entity.Article;

import java.util.List;

/**
 * Created by lizai on 16/5/23.
 */
public interface IArticleDao {

    public List<Article> getAllTopArticles(int start, int offset, List<Long> receivedIds);

    public List<Article> getAllUnTopArticles(int start, int offset, List<Long> receivedIds);

    public List<Article> getArticlesByType(int type, int start, int offset, List<Long> receivedIds);

    public Article getArticleById(Long id);

    public List<Article> getArticlesByIds(List<Long> ids);

    public List<Article> getAllArticleRegardlessStatus();

    public void saveOrUpdateArticle(Article article);

}
