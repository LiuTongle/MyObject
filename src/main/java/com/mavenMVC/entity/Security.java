package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security", catalog = "windmill")
public class Security implements Serializable {
	private Long securityId;
	private Long createTime;
	private Long lastModTime;
	private Integer userType;
	private Long userId;
	private String securityProblem;
	private String securityResult;

	public Security() {
	}

	public Security(Long securityId, Long createTime, Long lastModTime, Integer userType, Long userId,
			String securityProblem, String securityResult) {
		this.securityId = securityId;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
		this.userType = userType;
		this.userId = userId;
		this.securityProblem = securityProblem;
		this.securityResult = securityResult;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SECURITY_ID", unique = true, nullable = false)
	public Long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(Long securityId) {
		this.securityId = securityId;
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

	@Column(name = "SECURITY_PROBLEM")
	public String getSecurityProblem() {
		return securityProblem;
	}

	public void setSecurityProblem(String securityProblem) {
		this.securityProblem = securityProblem;
	}

	@Column(name = "SECURITY_RESULT")
	public String getSecurityResult() {
		return securityResult;
	}

	public void setSecurityResult(String securityResult) {
		this.securityResult = securityResult;
	}

}
