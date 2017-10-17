package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "orderform", catalog = "windmill")
public class OrderForm implements Serializable {
	private Long orderformId;
	private Integer orderformStatus;
	private Long createTime;
	private Long sellerUserId;
	private Long buyeruserId;
	private Long competeId;
	private Long lastmodTime;
	private String orderformTitle;
	private String orderformDetailed;
	private String orderformImages;
	private Double orderformMoney;
	private Integer orderformNumber;
	private Double orderformFreight;
	private String orderformAddress;
	private String orderformFastMail;
	private String orderformFastMailNumber;
	private String orderPayChannel;
	private String orderChargeId;
	private Integer orderTab;
	private Integer orderPaidMoney;

	public OrderForm() {
	}

	public OrderForm(Long orderformId, Integer orderformStatus, Long createTime, Long sellerUserId, Long buyeruserId,
			Long competeId, Long lastmodTime, String orderformTitle, String orderformDetailed, String orderformImages,
			Double orderformMoney, Integer orderformNumber, Double orderformFreight, String orderformAddress,
			String orderformFastMail, String orderformFastMailNumber, String orderPayChannel, String orderChargeId,
			Integer orderTab, Integer orderPaidMoney) {
		super();
		this.orderformId = orderformId;
		this.orderformStatus = orderformStatus;
		this.createTime = createTime;
		this.sellerUserId = sellerUserId;
		this.buyeruserId = buyeruserId;
		this.competeId = competeId;
		this.lastmodTime = lastmodTime;
		this.orderformTitle = orderformTitle;
		this.orderformDetailed = orderformDetailed;
		this.orderformImages = orderformImages;
		this.orderformMoney = orderformMoney;
		this.orderformNumber = orderformNumber;
		this.orderformFreight = orderformFreight;
		this.orderformAddress = orderformAddress;
		this.orderformFastMail = orderformFastMail;
		this.orderformFastMailNumber = orderformFastMailNumber;
		this.orderPayChannel = orderPayChannel;
		this.orderChargeId = orderChargeId;
		this.orderTab = orderTab;
		this.orderPaidMoney = orderPaidMoney;
	}

//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "ORDERFORM_ID", unique = true, nullable = false)
	@Id
	@Column(name = "ORDERFORM_ID", unique = true, nullable = false)
	public Long getOrderformId() {
		return orderformId;
	}

	public void setOrderformId(Long orderformId) {
		this.orderformId = orderformId;
	}

	@Column(name = "ORDERFORM_PAIDMONEY")
	public Integer getOrderPaidMoney() {
		return orderPaidMoney;
	}

	public void setOrderPaidMoney(Integer orderPaidMoney) {
		this.orderPaidMoney = orderPaidMoney;
	}

	@Column(name = "ORDERFORM_PAYCHANNEL")
	public String getOrderPayChannel() {
		return orderPayChannel;
	}

	public void setOrderPayChannel(String orderPayChannel) {
		this.orderPayChannel = orderPayChannel;
	}

	@Column(name = "ORDERFORM_CHARGEID")
	public String getOrderChargeId() {
		return orderChargeId;
	}

	public void setOrderChargeId(String orderChargeId) {
		this.orderChargeId = orderChargeId;
	}

	@Column(name = "ORDERFORM_TAB")
	public Integer getOrderTab() {
		return orderTab;
	}

	public void setOrderTab(Integer orderTab) {
		this.orderTab = orderTab;
	}

	@Column(name = "ORDERFORM_FASTMAIL")
	public String getOrderformFastMail() {
		return orderformFastMail;
	}

	public void setOrderformFastMail(String orderformFastMail) {
		this.orderformFastMail = orderformFastMail;
	}

	@Column(name = "ORDERFORM_FASTMAIL_NUMBER")
	public String getOrderformFastMailNumber() {
		return orderformFastMailNumber;
	}

	public void setOrderformFastMailNumber(String orderformFastMailNumber) {
		this.orderformFastMailNumber = orderformFastMailNumber;
	}

	@Column(name = "ORDERFORM_NUMBER")
	public Integer getOrderformNumber() {
		return orderformNumber;
	}

	public void setOrderformNumber(Integer orderformNumber) {
		this.orderformNumber = orderformNumber;
	}

	@Column(name = "ORDERFORM_FREIGHT")
	public Double getOrderformFreight() {
		return orderformFreight;
	}

	public void setOrderformFreight(Double orderformFreight) {
		this.orderformFreight = orderformFreight;
	}

	@Column(name = "ORDERFORM_ADDRESS")
	public String getOrderformAddress() {
		return orderformAddress;
	}

	public void setOrderformAddress(String orderformAddress) {
		this.orderformAddress = orderformAddress;
	}

	@Column(name = "ORDERFORM_STATUS")
	public Integer getOrderformStatus() {
		return orderformStatus;
	}

	public void setOrderformStatus(Integer orderformStatus) {
		this.orderformStatus = orderformStatus;
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

	@Column(name = "BUYER_USERID")
	public Long getBuyeruserId() {
		return buyeruserId;
	}

	public void setBuyeruserId(Long buyeruserId) {
		this.buyeruserId = buyeruserId;
	}

	@Column(name = "COMPETE_ID")
	public Long getCompeteId() {
		return competeId;
	}

	public void setCompeteId(Long competeId) {
		this.competeId = competeId;
	}

	@Column(name = "LAST_MOD_TIME")
	public Long getLastmodTime() {
		return lastmodTime;
	}

	public void setLastmodTime(Long lastmodTime) {
		this.lastmodTime = lastmodTime;
	}

	@Column(name = "ORDERFORM_TITLE")
	public String getOrderformTitle() {
		return orderformTitle;
	}

	public void setOrderformTitle(String orderformTitle) {
		this.orderformTitle = orderformTitle;
	}

	@Column(name = "ORDERFORM_DETAILED")
	public String getOrderformDetailed() {
		return orderformDetailed;
	}

	public void setOrderformDetailed(String orderformDetailed) {
		this.orderformDetailed = orderformDetailed;
	}

	@Column(name = "ORDERFORM_IMAGES")
	public String getOrderformImages() {
		return orderformImages;
	}

	public void setOrderformImages(String orderformImages) {
		this.orderformImages = orderformImages;
	}

	@Column(name = "ORDERFORM_MONEY")
	public Double getOrderformMoney() {
		return orderformMoney;
	}

	public void setOrderformMoney(Double orderformMoney) {
		this.orderformMoney = orderformMoney;
	}

}
