package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "census_register", catalog = "xdws")
public class CensusRegister implements Serializable {
	private Long ID;//ID
	private Long userId;//用户ID
	private String identification;//身份证
	private String name;//姓名
	private String nickName;//曾用名
	private String gender;//性别
	private String birthday;//出生日期
	private String ethnic;//名族
	private String nativePlace;//籍贯
	private String marriageType;//婚姻状况
	private String politicalStatus;//政治面貌
	private String educationalBackground;//学历
	private String Religion;//宗教信仰
	private String occupationType;//职业类别
	private String occupation;//职业
	private String UnitOfService;//服务处所
	private String cellPhone;//联系方式
	private String placeOfDomicile;//户籍地
	private String placeLocation;//户籍楼详细地址
	private String currentResidence;//现住地
	private String currentLocation;//现住地楼详细地址
	private String populationConsistentIdentifier;//人口一致标识
	private String householdNumber;//户号
	private String householdIdentification;//户主公民身份证号
	private String householdName;//户主姓名
	private String householdRelation;//与户主关系
	private String householdPhone;//户主联系方式
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
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	@Column(name = "occupation")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	@Column(name = "populationConsistentIdentifier")
	public String getPopulationConsistentIdentifier() {
		return populationConsistentIdentifier;
	}

	public void setPopulationConsistentIdentifier(String populationConsistentIdentifier) {
		this.populationConsistentIdentifier = populationConsistentIdentifier;
	}

	@Column(name = "householdNumber")
	public String getHouseholdNumber() {
		return householdNumber;
	}

	public void setHouseholdNumber(String householdNumber) {
		this.householdNumber = householdNumber;
	}

	@Column(name = "householdIdentification")
	public String getHouseholdIdentification() {
		return householdIdentification;
	}

	public void setHouseholdIdentification(String householdIdentification) {
		this.householdIdentification = householdIdentification;
	}

	@Column(name = "householdName")
	public String getHouseholdName() {
		return householdName;
	}

	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}

	@Column(name = "householdRelation")
	public String getHouseholdRelation() {
		return householdRelation;
	}

	public void setHouseholdRelation(String householdRelation) {
		this.householdRelation = householdRelation;
	}

	@Column(name = "householdPhone")
	public String getHouseholdPhone() {
		return householdPhone;
	}

	public void setHouseholdPhone(String householdPhone) {
		this.householdPhone = householdPhone;
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

	public CensusRegister() {
		super();
	}

	public CensusRegister(Long iD, Long userId, String identification, String name, String nickName, String gender,
			String birthday, String ethnic, String nativePlace, String marriageType, String politicalStatus,
			String educationalBackground, String religion, String occupationType, String occupation,
			String unitOfService, String cellPhone, String placeOfDomicile, String placeLocation,
			String currentResidence, String currentLocation, String populationConsistentIdentifier,
			String householdNumber, String householdIdentification, String householdName, String householdRelation,
			String householdPhone, Double longitude, Double latitude, Long createTime, Long lastModTime) {
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
		this.occupation = occupation;
		UnitOfService = unitOfService;
		this.cellPhone = cellPhone;
		this.placeOfDomicile = placeOfDomicile;
		this.placeLocation = placeLocation;
		this.currentResidence = currentResidence;
		this.currentLocation = currentLocation;
		this.populationConsistentIdentifier = populationConsistentIdentifier;
		this.householdNumber = householdNumber;
		this.householdIdentification = householdIdentification;
		this.householdName = householdName;
		this.householdRelation = householdRelation;
		this.householdPhone = householdPhone;
		this.longitude = longitude;
		this.latitude = latitude;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
	}

}
