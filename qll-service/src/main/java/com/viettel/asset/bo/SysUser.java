package com.viettel.asset.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String USER_ID = "userId";
        public static final String LOGIN_NAME = "loginName";
        public static final String PASSWORD = "password";
        public static final String FULL_NAME = "fullName";
        public static final String JOB_TITLE = "jobTitle";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String NATIVE_PLACE = "nativePlace";
        public static final String COMPANY_JOIN_DATE = "companyJoinDate";
        public static final String CARD_ID = "cardId";
        public static final String MOBILE = "mobile";
        public static final String STATUS = "status";
        public static final String APP_ID = "appId";
        public static final String OLD_ID = "oldId";
        public static final String MAC = "mac";
        public static final String CREATED_DATE = "createdDate";
        public static final String OLD_USER_ID = "oldUserId";
        public static final String CAT_EMPLOYEE_ID = "catEmployeeId";
        public static final String ROLE = "role";
        public static final String FLAG_SMS = "flagSms";
        public static final String FLAG_MAIL = "flagMail";
        public static final String IS_USER_MASTER = "isUserMaster";
        public static final String VOFFICE_NAME = "vofficeName";

    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SYS_USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @Column(name = "NATIVE_PLACE")
    private String nativePlace;
    @Column(name = "COMPANY_JOIN_DATE")
    private Date companyJoinDate;
    @Column(name = "CARD_ID")
    private String cardId;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "OLD_ID")
    private Long oldId;
    @Column(name = "MAC")
    private String mac;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "OLD_USER_ID")
    private Long oldUserId;
    @Column(name = "CAT_EMPLOYEE_ID")
    private Long catEmployeeId;
    @Column(name = "ROLE")
    private Long role;
    @Column(name = "FLAG_SMS")
    private Long flagSms;
    @Column(name = "FLAG_MAIL")
    private Long flagMail;
    @Column(name = "IS_USER_MASTER")
    private Long isUserMaster;
    @Column(name = "VOFFICE_NAME")
    private String vofficeName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Date getCompanyJoinDate() {
        return companyJoinDate;
    }

    public void setCompanyJoinDate(Date companyJoinDate) {
        this.companyJoinDate = companyJoinDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(Long oldUserId) {
        this.oldUserId = oldUserId;
    }

    public Long getCatEmployeeId() {
        return catEmployeeId;
    }

    public void setCatEmployeeId(Long catEmployeeId) {
        this.catEmployeeId = catEmployeeId;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getFlagSms() {
        return flagSms;
    }

    public void setFlagSms(Long flagSms) {
        this.flagSms = flagSms;
    }

    public Long getFlagMail() {
        return flagMail;
    }

    public void setFlagMail(Long flagMail) {
        this.flagMail = flagMail;
    }

    public Long getIsUserMaster() {
        return isUserMaster;
    }

    public void setIsUserMaster(Long isUserMaster) {
        this.isUserMaster = isUserMaster;
    }

    public String getVofficeName() {
        return vofficeName;
    }

    public void setVofficeName(String vofficeName) {
        this.vofficeName = vofficeName;
    }

}
