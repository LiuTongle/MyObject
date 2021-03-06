package com.mavenMVC.entity;
// Generated 2016-5-23 18:01:24 by Hibernate Tools 3.2.2.GA


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name = "Article"
        , catalog = "cloudHospital"
)
public class Article implements java.io.Serializable {


    private Long articleId;
    private String articleTitle;
    private String articleImage;
    private String articleUrl;
    private String articleContent;
    private Integer articleTop;
    private Integer articleStatus;
    private Integer articleType;
    private Integer articleCollection = 0;
    private Integer articleShow = 0;
    private String articleCategory;
    private Long createTime;
    private Long lastModTime;

    public Article() {
    }

    public Article(String articleTitle, String articleImage, String articleUrl, String articleContent, Integer articleTop, Integer articleStatus, Integer articleType, Integer articleCollection, Integer articleShow, String articleCategory, Long createTime, Long lastModTime) {
        this.articleTitle = articleTitle;
        this.articleImage = articleImage;
        this.articleUrl = articleUrl;
        this.articleContent = articleContent;
        this.articleTop = articleTop;
        this.articleStatus = articleStatus;
        this.articleType = articleType;
        this.articleCollection = articleCollection;
        this.articleShow = articleShow;
        this.articleCategory = articleCategory;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ARTICLE_ID", unique = true, nullable = false)
    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "ARTICLE_TITLE")
    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Column(name = "ARTICLE_IMAGE", length = 1022)
    public String getArticleImage() {
        return this.articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    @Column(name = "ARTICLE_URL", length = 1022)
    public String getArticleUrl() {
        return this.articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    @Column(name = "ARTICLE_CONTENT", length = 65535)
    public String getArticleContent() {
        return this.articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Column(name = "ARTICLE_TOP")
    public Integer getArticleTop() {
        return this.articleTop;
    }

    public void setArticleTop(Integer articleTop) {
        this.articleTop = articleTop;
    }

    @Column(name = "ARTICLE_STATUS")
    public Integer getArticleStatus() {
        return this.articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    @Column(name = "ARTICLE_TYPE")
    public Integer getArticleType() {
        return this.articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    @Column(name = "ARTICLE_COLLECTION")
    public Integer getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Integer articleCollection) {
        this.articleCollection = articleCollection;
    }

    @Column(name = "ARTICLE_SHOW")
    public Integer getArticleShow() {
        return articleShow;
    }

    public void setArticleShow(Integer articleShow) {
        this.articleShow = articleShow;
    }

    @Column(name = "ARTICLE_CATEGORY")
    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    @Column(name = "CREATE_TIME")
    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "LAST_MOD_TIME")
    public Long getLastModTime() {
        return this.lastModTime;
    }

    public void setLastModTime(Long lastModTime) {
        this.lastModTime = lastModTime;
    }


}


