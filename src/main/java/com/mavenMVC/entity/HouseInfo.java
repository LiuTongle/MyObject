package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house_info", catalog = "xdws")
public class HouseInfo {
	private Long ID;//房屋id
	private Long userId;//用户ID
	private String number;//房屋编号
	private String address;//房屋地址
	private String purpose;//房屋用途
	private Double area;//建筑面积
	private String IDCode;//证件代码
	private String IDNumber;//证件号码
	private String masterName;//房主姓名
	private String masterCellNumber;//房主联系方式
	private String masterCurrentLocation;//房主现居详情
	private String masterPurpose;//出租用途
	private String hiddenType;//隐患类型
	private String LesseeIDNumber;//承租人身份证号
	private String LesseeName;//承租人姓名
	private String LesseeCellNumber;//承租人联系方式
	private Double longitude;//经度
	private Double latitude;//纬度
	private Long createTime;
	private Long lastModTime;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name = "area")
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Column(name = "IDCode")
	public String getIDCode() {
		return IDCode;
	}
	public void setIDCode(String iDCode) {
		IDCode = iDCode;
	}
	@Column(name = "IDNumber")
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	@Column(name = "masterName")
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	@Column(name = "masterCellNumber")
	public String getMasterCellNumber() {
		return masterCellNumber;
	}
	public void setMasterCellNumber(String masterCellNumber) {
		this.masterCellNumber = masterCellNumber;
	}
	@Column(name = "masterCurrentLocation")
	public String getMasterCurrentLocation() {
		return masterCurrentLocation;
	}
	public void setMasterCurrentLocation(String masterCurrentLocation) {
		this.masterCurrentLocation = masterCurrentLocation;
	}
	@Column(name = "masterPurpose")
	public String getMasterPurpose() {
		return masterPurpose;
	}
	public void setMasterPurpose(String masterPurpose) {
		this.masterPurpose = masterPurpose;
	}
	@Column(name = "hiddenType")
	public String getHiddenType() {
		return hiddenType;
	}
	public void setHiddenType(String hiddenType) {
		this.hiddenType = hiddenType;
	}
	@Column(name = "LesseeIDNumber")
	public String getLesseeIDNumber() {
		return LesseeIDNumber;
	}
	public void setLesseeIDNumber(String lesseeIDNumber) {
		LesseeIDNumber = lesseeIDNumber;
	}
	@Column(name = "LesseeName")
	public String getLesseeName() {
		return LesseeName;
	}
	public void setLesseeName(String lesseeName) {
		LesseeName = lesseeName;
	}
	@Column(name = "LesseeCellNumber")
	public String getLesseeCellNumber() {
		return LesseeCellNumber;
	}
	public void setLesseeCellNumber(String lesseeCellNumber) {
		LesseeCellNumber = lesseeCellNumber;
	}
	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	
	public HouseInfo(Long iD, Long userId, String number, String address, String purpose, Double area, String iDCode,
			String iDNumber, String masterName, String masterCellNumber, String masterCurrentLocation,
			String masterPurpose, String hiddenType, String lesseeIDNumber, String lesseeName, String lesseeCellNumber,
			Double longitude, Double latitude, Long createTime, Long lastModTime) {
		super();
		ID = iD;
		this.userId = userId;
		this.number = number;
		this.address = address;
		this.purpose = purpose;
		this.area = area;
		IDCode = iDCode;
		IDNumber = iDNumber;
		this.masterName = masterName;
		this.masterCellNumber = masterCellNumber;
		this.masterCurrentLocation = masterCurrentLocation;
		this.masterPurpose = masterPurpose;
		this.hiddenType = hiddenType;
		LesseeIDNumber = lesseeIDNumber;
		LesseeName = lesseeName;
		LesseeCellNumber = lesseeCellNumber;
		this.longitude = longitude;
		this.latitude = latitude;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}
	public HouseInfo() {
		super();
	}

}
