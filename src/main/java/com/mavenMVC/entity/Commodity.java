package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 买家商品表
 * 
 * @author hcd
 */
@Entity
@Table(name = "commodity", catalog = "windmill")
public class Commodity implements Serializable {
	private Long commodityId;
	private Long createTime;
	private Long lastmodTime;
	private Long buyerUserId;
	private String commodityTitle;
	private String commodityDetailed;
	private String commodityImages;
	private String commodityMoney;
	private Integer commodityTab;
	private Integer commodityStatus;
	private Integer commodityLength;
	private String commodityLocation;

	public Commodity() {
	}

	public Commodity(Long commodityId, Long createTime, Long lastmodTime, Long buyerUserId, String commodityTitle,
			String commodityDetailed, String commodityImages, String commodityMoney, Integer commodityTab,
			Integer commodityLength, String commodityLocation, Integer commodityStatus) {
		super();
		this.commodityId = commodityId;
		this.createTime = createTime;
		this.lastmodTime = lastmodTime;
		this.buyerUserId = buyerUserId;
		this.commodityTitle = commodityTitle;
		this.commodityDetailed = commodityDetailed;
		this.commodityImages = commodityImages;
		this.commodityMoney = commodityMoney;
		this.commodityTab = commodityTab;
		this.commodityLength = commodityLength;
		this.commodityLocation = commodityLocation;
		this.commodityStatus = commodityStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "COMMODITY_ID", unique = true, nullable = false)
	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	@Column(name = "CREATE_TIME")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "COMMODITY_STATUS")
	public Integer getCommodityStatus() {
		return commodityStatus;
	}

	public void setCommodityStatus(Integer commodityStatus) {
		this.commodityStatus = commodityStatus;
	}

	@Column(name = "LAST_MOD_TIME")
	public Long getLastmodTime() {
		return lastmodTime;
	}

	public void setLastmodTime(Long lastmodTime) {
		this.lastmodTime = lastmodTime;
	}

	@Column(name = "COMMODITY_LENGTH")
	public Integer getCommodityLength() {
		return commodityLength;
	}

	public void setCommodityLength(Integer commodityLength) {
		this.commodityLength = commodityLength;
	}

	@Column(name = "COMMODITY_LOCATION")
	public String getCommodityLocation() {
		return commodityLocation;
	}

	public void setCommodityLocation(String commodityLocation) {
		this.commodityLocation = commodityLocation;
	}

	@Column(name = "COMMODITY_TAB")
	public Integer getCommodityTab() {
		return commodityTab;
	}

	public void setCommodityTab(Integer commodityTab) {
		this.commodityTab = commodityTab;
	}

	@Column(name = "BUYER_USERID")
	public Long getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(Long buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	@Column(name = "COMMODITY_TITLE")
	public String getCommodityTitle() {
		return commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	@Column(name = "COMMODITY_DETAILED")
	public String getCommodityDetailed() {
		return commodityDetailed;
	}

	public void setCommodityDetailed(String commodityDetailed) {
		this.commodityDetailed = commodityDetailed;
	}

	@Column(name = "COMMODITY_IMAGES")
	public String getCommodityImages() {
		return commodityImages;
	}

	public void setCommodityImages(String commodityImages) {
		this.commodityImages = commodityImages;
	}

	@Column(name = "COMMODITY_MONEY")
	public String getCommodityMoney() {
		return commodityMoney;
	}

	public void setCommodityMoney(String commodityMoney) {
		this.commodityMoney = commodityMoney;
	}

}
