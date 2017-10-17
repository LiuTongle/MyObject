package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收藏表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "collect", catalog = "windmill")
public class Collect implements Serializable {
	private Long collectId;
	private Long userId;
	private Integer collectType;
	private Long collectContentId;
	private Long createTime;
	private Long lastModTime;

	public Collect(Long collectId, Long userId, Integer collectType, Long collectContentId, Long createTime,
			Long lastModTime) {
		this.collectId = collectId;
		this.userId = userId;
		this.collectType = collectType;
		this.collectContentId = collectContentId;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}

	public Collect() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "COLLECT_ID", unique = true, nullable = false)
	public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "COLLECT_TYPE")
	public Integer getCollectType() {
		return collectType;
	}

	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}

	@Column(name = "COLLECT_CONTENT_ID")
	public Long getCollectContentId() {
		return collectContentId;
	}

	public void setCollectContentId(Long collectContentId) {
		this.collectContentId = collectContentId;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_MOD_TIME")
	public Long getLastModTime() {
		return lastModTime;
	}

	public void setLastModTime(Long lastModTime) {
		this.lastModTime = lastModTime;
	}

}
