package com.mavenMVC.entity;
// Generated 2016-4-11 17:07:57 by Hibernate Tools 3.2.2.GA


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "Patient"
        , catalog = "cloudHospital"
)
public class Patient implements java.io.Serializable {


    private Long patientId;
    private String patientName;
    private Integer patientSex;
    private String patientAddress;
    private Long patientUserId;
    private String patientCellphone;
    private String patientDescription;
    private Integer patientCertificateType;
    private String patientCertificateDetail;
    private String patientHeadimage;
    private String patientBirthday;
    private Long createTime;
    private Long lastModTime;

    public Patient() {
    }

    public Patient(String patientName, Integer patientSex, String patientAddress, Long patientUserId, String patientCellphone, String patientDescription, Integer patientCertificateType, String patientCertificateDetail, String patientHeadimage, String patientBirthday, Long createTime, Long lastModTime) {
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.patientAddress = patientAddress;
        this.patientUserId = patientUserId;
        this.patientCellphone = patientCellphone;
        this.patientDescription = patientDescription;
        this.patientCertificateType = patientCertificateType;
        this.patientCertificateDetail = patientCertificateDetail;
        this.patientHeadimage = patientHeadimage;
        this.patientBirthday = patientBirthday;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PATIENT_ID", unique = true, nullable = false)
    public Long getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Column(name = "PATIENT_NAME")
    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "PATIENT_SEX")
    public Integer getPatientSex() {
        return this.patientSex;
    }

    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    @Column(name = "PATIENT_ADDRESS", length = 65535)
    public String getPatientAddress() {
        return this.patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    @Column(name = "PATIENT_USER_ID")
    public Long getPatientUserId() {
        return this.patientUserId;
    }

    public void setPatientUserId(Long patientUserId) {
        this.patientUserId = patientUserId;
    }

    @Column(name = "PATIENT_CELLPHONE")
    public String getPatientCellphone() {
        return this.patientCellphone;
    }

    public void setPatientCellphone(String patientCellphone) {
        this.patientCellphone = patientCellphone;
    }

    @Column(name = "PATIENT_DESCRIPTION", length = 65535)
    public String getPatientDescription() {
        return this.patientDescription;
    }

    public void setPatientDescription(String patientDescription) {
        this.patientDescription = patientDescription;
    }

    @Column(name = "PATIENT_CERTIFICATE_TYPE")
    public Integer getPatientCertificateType() {
        return this.patientCertificateType;
    }

    public void setPatientCertificateType(Integer patientCertificateType) {
        this.patientCertificateType = patientCertificateType;
    }

    @Column(name = "PATIENT_CERTIFICATE_DETAIL", length = 1022)
    public String getPatientCertificateDetail() {
        return this.patientCertificateDetail;
    }

    public void setPatientCertificateDetail(String patientCertificateDetail) {
        this.patientCertificateDetail = patientCertificateDetail;
    }

    @Column(name = "PATIENT_HEADIMAGE", length = 1022)
    public String getPatientHeadimage() {
        return this.patientHeadimage;
    }

    public void setPatientHeadimage(String patientHeadimage) {
        this.patientHeadimage = patientHeadimage;
    }

    @Column(name = "PATIENT_BIRTHDAY", length = 45)
    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
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


}


