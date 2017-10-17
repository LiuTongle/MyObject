package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUserInfo", catalog = "xdws")
public class MUserInfo implements Serializable {
	
	private Long userId;//用户ID
	private String token;
	private String userName;
	private String cellPhone;
	private String password;
	private Long createTime;
	private Long lastModTime;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "cellPhone")
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "createTime")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Column(name = "lastModTime")
	public Long getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(Long lastModTime) {
		this.lastModTime = lastModTime;
	}
	
	public MUserInfo(Long userId, String token, String userName, String cellPhone, String password, Long createTime,
			Long lastModTime) {
		super();
		this.userId = userId;
		this.token = token;
		this.userName = userName;
		this.cellPhone = cellPhone;
		this.password = password;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}
	
	public MUserInfo() {
		super();
	}
	
}
