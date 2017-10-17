package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 提现表
 * @author Amor
 *
 */
@Entity
@Table(name = "extractmoney", catalog = "windmill")
public class ExtractMoney implements Serializable {
	
	private Long id;
	private Long createTime;
	private Long lastModTime;
	private Long userId;
	private String wxId;
	private Double money;
	
	public ExtractMoney(Long id, Long createTime, Long lastModTime, Long userId, String wxId, Double money) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
		this.userId = userId;
		this.wxId = wxId;
		this.money = money;
	}
	
	public ExtractMoney() {
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "WX_ID")
	public String getWxId() {
		return wxId;
	}
	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	
	@Column(name = "MONEY")
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
