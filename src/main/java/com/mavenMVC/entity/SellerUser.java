package com.mavenMVC.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 买家用户
 * 
 * @author hcd
 *
 */

@Entity
@Table(name = "selleruser", catalog = "windmill")
public class SellerUser implements Serializable {
	private Long userId;
	private Long createTime;
	private Long lastModTime;
	private String name;
	private String userName;
	private Integer userStatus;
	private String userPassword;
	private String userHeadImage;
	private String userCellphone;
	private Integer userSex;
	private Integer userAge;
	private Integer userMoney;
	private String userInstallationId;
	private Integer userOSType;
	private String userWXId;
	private Integer userLogin;
	private String userBirthday;
	private String userCertificateImages;
	private String userAddress;
	private String userEmail;
	private String userIDNum;
	private String userToken;
	private String userCity;

	public SellerUser() {
	}

	public SellerUser(Long userId, Long createTime, Long lastModTime, String name, String userName, Integer userStatus,
			String userPassword, String userHeadImage, String userCellphone, Integer userSex, Integer userAge,
			Integer userMoney, String userInstallationId, Integer userOSType, String userWXId, Integer userLogin,
			String userBirthday, String userCertificateImages, String userAddress, String userEmail, String userIDNum,
			String userToken, String userCity) {
		super();
		this.userId = userId;
		this.createTime = createTime;
		this.lastModTime = lastModTime;
		this.name = name;
		this.userName = userName;
		this.userStatus = userStatus;
		this.userPassword = userPassword;
		this.userHeadImage = userHeadImage;
		this.userCellphone = userCellphone;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userMoney = userMoney;
		this.userInstallationId = userInstallationId;
		this.userOSType = userOSType;
		this.userWXId = userWXId;
		this.userLogin = userLogin;
		this.userBirthday = userBirthday;
		this.userCertificateImages = userCertificateImages;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userIDNum = userIDNum;
		this.userToken = userToken;
		this.userCity = userCity;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "USER_ID", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	@Column(name = "USER_CITY")
	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "USER_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USER_USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_STATUS")
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "USER_PASSWORD")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "USER_HEADIMAGE")
	public String getUserHeadImage() {
		return userHeadImage;
	}

	public void setUserHeadImage(String userHeadImage) {
		this.userHeadImage = userHeadImage;
	}

	@Column(name = "USER_CELLPHONE")
	public String getUserCellphone() {
		return userCellphone;
	}

	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}

	@Column(name = "USER_SEX")
	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	@Column(name = "USER_AGE")
	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Column(name = "USER_MONEY")
	public Integer getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(Integer userMoney) {
		this.userMoney = userMoney;
	}

	@Column(name = "USER_INSTALLATION_ID")
	public String getUserInstallationId() {
		return userInstallationId;
	}

	public void setUserInstallationId(String userInstallationId) {
		this.userInstallationId = userInstallationId;
	}

	@Column(name = "USER_OS_TYPE")
	public Integer getUserOSType() {
		return userOSType;
	}

	public void setUserOSType(Integer userOSType) {
		this.userOSType = userOSType;
	}

	@Column(name = "USER_WXID")
	public String getUserWXId() {
		return userWXId;
	}

	public void setUserWXId(String userWXId) {
		this.userWXId = userWXId;
	}

	@Column(name = "USER_LOGIN")
	public Integer getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Integer userLogin) {
		this.userLogin = userLogin;
	}

	@Column(name = "USER_BIRTHDAY")
	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	@Column(name = "USER_CERTIFICATEIMAGES")
	public String getUserCertificateImages() {
		return userCertificateImages;
	}

	public void setUserCertificateImages(String userCertificateImages) {
		this.userCertificateImages = userCertificateImages;
	}

	@Column(name = "USER_ADDRESS")
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "USER_EMAIL")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "USER_IDNUM")
	public String getUserIDNum() {
		return userIDNum;
	}

	public void setUserIDNum(String userIDNum) {
		this.userIDNum = userIDNum;
	}

	@Column(name = "USER_TOKEN")
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
