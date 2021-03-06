package com.mavenMVC.entity;
// Generated 2016-5-9 17:10:42 by Hibernate Tools 3.2.2.GA


import net.sf.json.JSONObject;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Doctor generated by hbm2java
 */
@Entity
@Table(name = "Doctor"
        , catalog = "cloudHospital"
)
public class Doctor implements java.io.Serializable {


    private Long doctorId;
    private String doctorName;
    private String doctorPassword;
    private Integer doctorStatus;
    private Integer doctorSex;
    private Long doctorHospital;
    private Long doctorDepartment;
    private Long doctorTitle;
    private String doctorCellphone;
    private String doctorHeadimage;
    private String doctorIDNum;
    private Integer doctorCertificateType;
    private String doctorCertificateDetail;
    private String doctorIntroduction;
    private String doctorToken;
    private Integer doctorPrice;
    private Integer doctorTop;
    private String doctorInstallationId;
    private Integer doctorOsType;
    private Integer doctorLogin;
    private Integer doctorScore;
    private Boolean doctorSwitch;
    private Integer doctorMoney;
    private String doctorImToken;
    private Long createTime;
    private Long lastModTime;

    public Doctor() {
    }

    public Doctor(String doctorName, String doctorPassword, Integer doctorStatus, Integer doctorSex, Long doctorHospital, Long doctorDepartment, Long doctorTitle, String doctorCellphone, String doctorHeadimage, String doctorIDNum, Integer doctorCertificateType, String doctorCertificateDetail, String doctorIntroduction, String doctorToken, Integer doctorPrice, Integer doctorTop, String doctorInstallationId, Integer doctorOsType, Integer doctorLogin, Integer doctorScore, Boolean doctorSwitch, Integer doctorMoney, String doctorImToken, Long createTime, Long lastModTime) {
        this.doctorName = doctorName;
        this.doctorPassword = doctorPassword;
        this.doctorStatus = doctorStatus;
        this.doctorSex = doctorSex;
        this.doctorHospital = doctorHospital;
        this.doctorDepartment = doctorDepartment;
        this.doctorTitle = doctorTitle;
        this.doctorCellphone = doctorCellphone;
        this.doctorHeadimage = doctorHeadimage;
        this.doctorIDNum = doctorIDNum;
        this.doctorCertificateType = doctorCertificateType;
        this.doctorCertificateDetail = doctorCertificateDetail;
        this.doctorIntroduction = doctorIntroduction;
        this.doctorToken = doctorToken;
        this.doctorPrice = doctorPrice;
        this.doctorTop = doctorTop;
        this.doctorInstallationId = doctorInstallationId;
        this.doctorOsType = doctorOsType;
        this.doctorLogin = doctorLogin;
        this.doctorScore = doctorScore;
        this.doctorSwitch = doctorSwitch;
        this.doctorMoney = doctorMoney;
        this.doctorImToken = doctorImToken;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "DOCTOR_ID", unique = true, nullable = false)
    public Long getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "DOCTOR_NAME")
    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "DOCTOR_PASSWORD")
    public String getDoctorPassword() {
        return this.doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    @Column(name = "DOCTOR_STATUS")
    public Integer getDoctorStatus() {
        return this.doctorStatus;
    }

    public void setDoctorStatus(Integer doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    @Column(name = "DOCTOR_SEX")
    public Integer getDoctorSex() {
        return this.doctorSex;
    }

    public void setDoctorSex(Integer doctorSex) {
        this.doctorSex = doctorSex;
    }

    @Column(name = "DOCTOR_HOSPITAL")
    public Long getDoctorHospital() {
        return this.doctorHospital;
    }

    public void setDoctorHospital(Long doctorHospital) {
        this.doctorHospital = doctorHospital;
    }

    @Column(name = "DOCTOR_DEPARTMENT")
    public Long getDoctorDepartment() {
        return this.doctorDepartment;
    }

    public void setDoctorDepartment(Long doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }

    @Column(name = "DOCTOR_TITLE")
    public Long getDoctorTitle() {
        return this.doctorTitle;
    }

    public void setDoctorTitle(Long doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    @Column(name = "DOCTOR_CELLPHONE", length = 45)
    public String getDoctorCellphone() {
        return this.doctorCellphone;
    }

    public void setDoctorCellphone(String doctorCellphone) {
        this.doctorCellphone = doctorCellphone;
    }

    @Column(name = "DOCTOR_HEADIMAGE", length = 1022)
    public String getDoctorHeadimage() {
        return this.doctorHeadimage;
    }

    public void setDoctorHeadimage(String doctorHeadimage) {
        this.doctorHeadimage = doctorHeadimage;
    }

    @Column(name = "DOCTOR_IDNUM", length = 45)
    public String getDoctorIDNum() {
        return doctorIDNum;
    }

    public void setDoctorIDNum(String doctorIDNum) {
        this.doctorIDNum = doctorIDNum;
    }

    @Column(name = "DOCTOR_CERTIFICATE_TYPE")
    public Integer getDoctorCertificateType() {
        return this.doctorCertificateType;
    }

    public void setDoctorCertificateType(Integer doctorCertificateType) {
        this.doctorCertificateType = doctorCertificateType;
    }

    @Column(name = "DOCTOR_CERTIFICATE_DETAIL", length = 1022)
    public String getDoctorCertificateDetail() {
        return this.doctorCertificateDetail;
    }

    public void setDoctorCertificateDetail(String doctorCertificateDetail) {
        this.doctorCertificateDetail = doctorCertificateDetail;
    }

    @Column(name = "DOCTOR_INTRODUCTION")
    public String getDoctorIntroduction() {
        return doctorIntroduction;
    }

    public void setDoctorIntroduction(String doctorIntroduction) {
        this.doctorIntroduction = doctorIntroduction;
    }

    @Column(name = "DOCTOR_TOKEN")
    public String getDoctorToken() {
        return this.doctorToken;
    }

    public void setDoctorToken(String doctorToken) {
        this.doctorToken = doctorToken;
    }

    @Column(name = "DOCTOR_PRICE")
    public Integer getDoctorPrice() {
        return doctorPrice;
    }

    public void setDoctorPrice(Integer doctorPrice) {
        this.doctorPrice = doctorPrice;
    }

    @Column(name = "DOCTOR_TOP")
    public Integer getDoctorTop() {
        return doctorTop;
    }

    public void setDoctorTop(Integer doctorTop) {
        this.doctorTop = doctorTop;
    }

    @Column(name = "DOCTOR_INSTALLATION_ID")
    public String getDoctorInstallationId() {
        return doctorInstallationId;
    }

    public void setDoctorInstallationId(String doctorInstallationId) {
        this.doctorInstallationId = doctorInstallationId;
    }

    @Column(name = "DOCTOR_OS_TYPE")
    public Integer getDoctorOsType() {
        return doctorOsType;
    }

    public void setDoctorOsType(Integer doctorOsType) {
        this.doctorOsType = doctorOsType;
    }

    @Column(name = "DOCTOR_LOGIN")
    public Integer getDoctorLogin() {
        return doctorLogin;
    }

    public void setDoctorLogin(Integer doctorLogin) {
        this.doctorLogin = doctorLogin;
    }

    @Column(name = "DOCTOR_SCORE")
    public Integer getDoctorScore() {
        return doctorScore;
    }

    public void setDoctorScore(Integer doctorScore) {
        this.doctorScore = doctorScore;
    }

    @Column(name = "DOCTOR_SWITCH")
    public Boolean getDoctorSwitch() {
        return doctorSwitch;
    }

    public void setDoctorSwitch(Boolean doctorSwitch) {
        this.doctorSwitch = doctorSwitch;
    }

    @Column(name = "DOCTOR_MONEY", columnDefinition="INT default 0")
    public Integer getDoctorMoney() {
        return doctorMoney;
    }

    public void setDoctorMoney(Integer doctorMoney) {
        this.doctorMoney = doctorMoney;
    }

    @Column(name = "DOCTOR_IM_TOKEN")
    public String getDoctorImToken() {
        return doctorImToken;
    }

    public void setDoctorImToken(String doctorImToken) {
        this.doctorImToken = doctorImToken;
    }

    @Column(name = "CREATE_TIME")
    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "LAST_MOD_TIME")
    public Long getLastModTime() {
        return this.lastModTime;
    }

    public void setLastModTime(Long lastModTime) {
        this.lastModTime = lastModTime;
    }

    public JSONObject turnJson(){
        JSONObject jo = new JSONObject();
        jo.put("doctorId",this.doctorId);
        jo.put("doctorName",this.doctorName);
        jo.put("doctorHospital",this.doctorHospital);
        jo.put("doctorSex",this.doctorSex);
        jo.put("doctorHeadimage",this.doctorHeadimage);
        jo.put("doctorDepartment",this.doctorDepartment);
        jo.put("doctorTitle",this.doctorTitle);
        jo.put("doctorIntroduction",this.doctorIntroduction);
        jo.put("doctorPrice",this.doctorPrice);
        jo.put("doctorTop",this.doctorTop);
        jo.put("doctorMoney",this.doctorMoney);
        jo.put("doctorScore",this.doctorScore);
        jo.put("doctorImToken",this.doctorImToken);
        return jo;
    }


}


