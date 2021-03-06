package com.mavenMVC.entity;

/**
 * Created by lizai on 16/7/24.
 */
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Title generated by hbm2java
 */
@Entity
@Table(name = "SeeCash"
        , catalog = "cloudHospital"
)
public class SeeCash implements java.io.Serializable {

    private Long seeCashId;
    private Long doctorId;
    private String bankId;
    private String seeCashChannel;
    private String wxUserName;
    private String wxUserOpenId;
    private String openBank;
    private String bankUserName;
    private String bankProv;
    private String bankCity;
    /*
        0：新建
        1：成功
        2：取消
        3：失败
     */
    private Integer seeCashStatus;
    private Integer seeCashAmount;
    private String transferId;
    private Long createTime;
    private Long lastModTime;

    public SeeCash() {
    }

    public SeeCash(Long doctorId, String bankId, String seeCashChannel, String wxUserName, String wxUserOpenId,
                   String openBank, String bankUserName, String bankProv, String bankCity,
                   Integer seeCashStatus, Integer seeCashAmount, String transferId, Long createTime, Long lastModTime) {
        this.doctorId = doctorId;
        this.bankId = bankId;
        this.seeCashChannel = seeCashChannel;
        this.wxUserName = wxUserName;
        this.wxUserOpenId = wxUserOpenId;
        this.openBank = openBank;
        this.bankUserName = bankUserName;
        this.bankProv = bankProv;
        this.bankCity = bankCity;
        this.seeCashStatus = seeCashStatus;
        this.seeCashAmount = seeCashAmount;
        this.transferId = transferId;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SEE_CASH_ID")
    public Long getSeeCashId() {
        return seeCashId;
    }

    public void setSeeCashId(Long seeCashId) {
        this.seeCashId = seeCashId;
    }

    @Column(name = "DOCTOR_ID")
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "BANK_ID")
    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Column(name = "SEE_CASH_CHANNEL")
    public String getSeeCashChannel() {
        return seeCashChannel;
    }

    public void setSeeCashChannel(String seeCashChannel) {
        this.seeCashChannel = seeCashChannel;
    }

    @Column(name = "WX_USER_NAME")
    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

    @Column(name = "WX_USER_OPENID")
    public String getWxUserOpenId() {
        return wxUserOpenId;
    }

    public void setWxUserOpenId(String wxUserOpenId) {
        this.wxUserOpenId = wxUserOpenId;
    }

    @Column(name = "OPEN_BANK_NAME")
    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    @Column(name = "BANK_USER_NAME")
    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    @Column(name = "BANK_PROV")
    public String getBankProv() {
        return bankProv;
    }

    public void setBankProv(String bankProv) {
        this.bankProv = bankProv;
    }

    @Column(name = "BANK_CITY")
    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    @Column(name = "SEE_CASH_STATUS")
    public Integer getSeeCashStatus() {
        return seeCashStatus;
    }

    public void setSeeCashStatus(Integer seeCashStatus) {
        this.seeCashStatus = seeCashStatus;
    }

    @Column(name = "SEE_CASH_AMOUNT")
    public Integer getSeeCashAmount() {
        return seeCashAmount;
    }

    public void setSeeCashAmount(Integer seeCashAmount) {
        this.seeCashAmount = seeCashAmount;
    }

    @Column(name = "TRANSFER_ID")
    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
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


