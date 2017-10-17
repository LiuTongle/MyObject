package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback", catalog = "windmill")
public class FeedBack implements Serializable {
	private Long ID;
	private Long userId;
	private Integer userType;
	private String content;
	private Long createTime;
	private Long lastModTime;
	
	public FeedBack() {
		super();
	}
	
	public FeedBack(Long iD, Long userId, Integer userType, String content, Long createTime, Long lastModTime) {
		super();
		ID = iD;
		this.userId = userId;
		this.userType = userType;
		this.content = content;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	@Column(name = "USER_TYPE")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
