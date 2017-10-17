package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 卖家竞标表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "compete", catalog = "windmill")
public class Compete implements Serializable {
	private Long competeId;
	private Long createTime;
	private Long sellerUserId;
	private Long lastmodTime;
	private Long commodityId;
	private String competeTitle;
	private String competeDetailed;
	private String competeImages;
	private Double competeMoney;
	private Double competeFreight;
	private Integer competeNumber;
	private Integer competeDealNumber;
	private Integer competeStatus;
	private Integer competeTab;
	private String competeTime;
	private Integer competeCollect;

	public Compete() {
	}

	public Compete(Long competeId, Long createTime, Long sellerUserId, Long lastmodTime, Long commodityId,
			String competeTitle, String competeDetailed, String competeImages, Double competeMoney,
			Double competeFreight, Integer competeNumber, Integer competeDealNumber, Integer competeStatus,
			Integer competeTab, String competeTime, Integer competeCollect) {
		super();
		this.competeId = competeId;
		this.createTime = createTime;
		this.sellerUserId = sellerUserId;
		this.lastmodTime = lastmodTime;
		this.commodityId = commodityId;
		this.competeTitle = competeTitle;
		this.competeDetailed = competeDetailed;
		this.competeImages = competeImages;
		this.competeMoney = competeMoney;
		this.competeFreight = competeFreight;
		this.competeNumber = competeNumber;
		this.competeDealNumber = competeDealNumber;
		this.competeStatus = competeStatus;
		this.competeTab = competeTab;
		this.competeTime = competeTime;
		this.competeCollect = competeCollect;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "COMPETE_ID", unique = true, nullable = false)
	public Long getCompeteId() {
		return competeId;
	}

	public void setCompeteId(Long competeId) {
		this.competeId = competeId;
	}

	@Column(name = "COMPETE_TIME")
	public String getCompeteTime() {
		return competeTime;
	}

	public void setCompeteTime(String competeTime) {
		this.competeTime = competeTime;
	}

	@Column(name = "COMPETE_STATUS")
	public Integer getCompeteStatus() {
		return competeStatus;
	}

	@Column(name = "COMPETE_TAB")
	public Integer getCompeteTab() {
		return competeTab;
	}

	public void setCompeteTab(Integer competeTab) {
		this.competeTab = competeTab;
	}

	public Integer getCompeteCollect() {
		return competeCollect;
	}

	public void setCompeteCollect(Integer competeCollect) {
		this.competeCollect = competeCollect;
	}

	public void setCompeteStatus(Integer competeStatus) {
		this.competeStatus = competeStatus;
	}

	@Column(name = "COMPETE_FREIGHT")
	public Double getCompeteFreight() {
		return competeFreight;
	}

	public void setCompeteFreight(Double competeFreight) {
		this.competeFreight = competeFreight;
	}

	@Column(name = "COMPETE_NUMBER")
	public Integer getCompeteNumber() {
		return competeNumber;
	}

	public void setCompeteNumber(Integer competeNumber) {
		this.competeNumber = competeNumber;
	}

	@Column(name = "COMPETE_DEAL_NUMBER")
	public Integer getCompeteDealNumber() {
		return competeDealNumber;
	}

	public void setCompeteDealNumber(Integer competeDealNumber) {
		this.competeDealNumber = competeDealNumber;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "SELLER_USERID")
	public Long getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(Long sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	@Column(name = "LAST_MOD_TIME")
	public Long getLastmodTime() {
		return lastmodTime;
	}

	public void setLastmodTime(Long lastmodTime) {
		this.lastmodTime = lastmodTime;
	}

	@Column(name = "COMMODITY_ID")
	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	@Column(name = "COMPETE_TITLE")
	public String getCompeteTitle() {
		return competeTitle;
	}

	public void setCompeteTitle(String competeTitle) {
		this.competeTitle = competeTitle;
	}

	@Column(name = "COMPETE_DETAILED")
	public String getCompeteDetailed() {
		return competeDetailed;
	}

	public void setCompeteDetailed(String competeDetailed) {
		this.competeDetailed = competeDetailed;
	}

	@Column(name = "COMPETE_IMAGES")
	public String getCompeteImages() {
		return competeImages;
	}

	public void setCompeteImages(String competeImages) {
		this.competeImages = competeImages;
	}

	@Column(name = "COMPETE_MONEY")
	public Double getCompeteMoney() {
		return competeMoney;
	}

	public void setCompeteMoney(Double competeMoney) {
		this.competeMoney = competeMoney;
	}

}
