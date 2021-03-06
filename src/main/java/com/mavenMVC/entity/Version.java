package com.mavenMVC.entity;
// Generated 2016-4-11 17:07:57 by Hibernate Tools 3.2.2.GA


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Admin generated by hbm2java
 */
@Entity
@Table(name = "Version"
        , catalog = "cloudHospital"
)
public class Version implements java.io.Serializable {


    private Long versionId;
    private Integer versionCode;
    private String downloadUrl;
    private String updateText;
    private String versionName;
    private Integer versionSize;
    private Long createTime;
    private Long lastModTime;

    public Version() {
    }

    public Version(Integer versionCurrent, String versionAndroidDownloadUrl, String updateText, String versionName, Integer versionSize, Long createTime, Long lastModTime) {
        this.versionCode = versionCurrent;
        this.downloadUrl = versionAndroidDownloadUrl;
        updateText = updateText;
        versionName = versionName;
        versionSize = versionSize;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "VERSION_ID", unique = true, nullable = false)
    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    @Column(name = "VERSION_CURRENT")
    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    @Column(name = "VERSION_ANDROID_DOWNLOAD_URL")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Column(name = "VERSION_UPDATE_TEXT")
    public String getUpdateText() {
        return updateText;
    }

    public void setUpdateText(String updateText) {
        this.updateText = updateText;
    }

    @Column(name = "VERSION_NAME")
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Column(name = "VERSION_SIZE")
    public Integer getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(Integer versionSize) {
        this.versionSize = versionSize;
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


