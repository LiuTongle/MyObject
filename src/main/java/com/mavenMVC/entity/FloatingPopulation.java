package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "floating_population", catalog = "xdws")
public class FloatingPopulation implements Serializable {
	private Long ID;//ID
	private Long userId;//用户ID
	private String identification;//身份证
	private String name;//姓名
	private String nickName;//曾用名
	private String gender;//性别
	private Long birthday;//出生日期
	private String ethnic;//名族
	private String nativePlace;//籍贯
	private String marriageType;//婚姻状况
	private String politicalStatus;//政治面貌
	private String educationalBackground;//学历
	private String Religion;//宗教信仰
	private String occupationType;//职业类别
	private String UnitOfService;//服务处所
	private String cellPhone;//联系方式
	private String placeOfDomicile;//户籍地
	private String placeLocation;//户籍楼详细地址
	private String currentResidence;//现住地
	private String currentLocation;//现住地楼详细地址
	private String flowReason;//流人原因
	private String IDType;//办证类型
	private String IDNumber;//证件号码
	private Long dateOfRegistration;//登记日期
	private Long IDExpirationDate;
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
	@Column(name = "identification")
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "nickName")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "birthday")
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "ethnic")
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	@Column(name = "nativePlace")
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	@Column(name = "marriageType")
	public String getMarriageType() {
		return marriageType;
	}
	public void setMarriageType(String marriageType) {
		this.marriageType = marriageType;
	}
	@Column(name = "politicalStatus")
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	@Column(name = "educationalBackground")
	public String getEducationalBackground() {
		return educationalBackground;
	}
	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}
	@Column(name = "Religion")
	public String getReligion() {
		return Religion;
	}
	public void setReligion(String religion) {
		Religion = religion;
	}
	@Column(name = "occupationType")
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	@Column(name = "UnitOfService")
	public String getUnitOfService() {
		return UnitOfService;
	}
	public void setUnitOfService(String unitOfService) {
		UnitOfService = unitOfService;
	}
	@Column(name = "cellPhone")
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	@Column(name = "placeOfDomicile")
	public String getPlaceOfDomicile() {
		return placeOfDomicile;
	}
	public void setPlaceOfDomicile(String placeOfDomicile) {
		this.placeOfDomicile = placeOfDomicile;
	}
	@Column(name = "placeLocation")
	public String getPlaceLocation() {
		return placeLocation;
	}
	public void setPlaceLocation(String placeLocation) {
		this.placeLocation = placeLocation;
	}
	@Column(name = "currentResidence")
	public String getCurrentResidence() {
		return currentResidence;
	}
	public void setCurrentResidence(String currentResidence) {
		this.currentResidence = currentResidence;
	}
	@Column(name = "currentLocation")
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	@Column(name = "flowReason")
	public String getFlowReason() {
		return flowReason;
	}
	public void setFlowReason(String flowReason) {
		this.flowReason = flowReason;
	}
	@Column(name = "IDType")
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	@Column(name = "IDNumber")
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	@Column(name = "dateOfRegistration")
	public Long getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Long dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	@Column(name = "IDExpirationDate")
	public Long getIDExpirationDate() {
		return IDExpirationDate;
	}
	public void setIDExpirationDate(Long iDExpirationDate) {
		IDExpirationDate = iDExpirationDate;
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
	public FloatingPopulation(Long iD, Long userId, String identification, String name, String nickName, String gender,
			Long birthday, String ethnic, String nativePlace, String marriageType, String politicalStatus,
			String educationalBackground, String religion, String occupationType, String unitOfService,
			String cellPhone, String placeOfDomicile, String placeLocation, String currentResidence,
			String currentLocation, String flowReason, String iDType, String iDNumber, Long dateOfRegistration,
			Long iDExpirationDate, Double longitude, Double latitude, Long createTime, Long lastModTime) {
		super();
		ID = iD;
		this.userId = userId;
		this.identification = identification;
		this.name = name;
		this.nickName = nickName;
		this.gender = gender;
		this.birthday = birthday;
		this.ethnic = ethnic;
		this.nativePlace = nativePlace;
		this.marriageType = marriageType;
		this.politicalStatus = politicalStatus;
		this.educationalBackground = educationalBackground;
		Religion = religion;
		this.occupationType = occupationType;
		UnitOfService = unitOfService;
		this.cellPhone = cellPhone;
		this.placeOfDomicile = placeOfDomicile;
		this.placeLocation = placeLocation;
		this.currentResidence = currentResidence;
		this.currentLocation = currentLocation;
		this.flowReason = flowReason;
		IDType = iDType;
		IDNumber = iDNumber;
		this.dateOfRegistration = dateOfRegistration;
		IDExpirationDate = iDExpirationDate;
		this.longitude = longitude;
		this.latitude = latitude;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}
	public FloatingPopulation() {
		super();
	}
	
	
}
