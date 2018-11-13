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
@Table(name = "V_SYS_USER")
public class VSysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String IS_USER_MASTER = "isUserMaster";
        public static final String LOGIN_NAME = "loginName";
        public static final String FULL_NAME = "fullName";
        public static final String EMAIL = "email";
        public static final String USER_ID = "userId";
        public static final String GROUP_ID = "groupId";
        public static final String POS_ID = "posId";
        public static final String PARENT_ID = "parentId";
        public static final String GROUP_NAME = "groupName";
        public static final String GROUP_CODE = "groupCode";
        public static final String PROVINCE_ID = "provinceId";
        public static final String PHONE = "phone";
        public static final String CREATED_DATE = "createdDate";
        public static final String JOB_TITLE = "jobTitle";
        public static final String CAT_EMPLOYEE_ID = "catEmployeeId";
        public static final String ROLE = "role";
        public static final String FLAG_SMS = "flagSms";
        public static final String FLAG_MAIL = "flagMail";
        public static final String VOFFICE_NAME = "vofficeName";

    }

    public interface Constants {

        public static final String TABLE_NAME = "V_SYS_USER";
    }

    @Column(name = "IS_USER_MASTER")
    private Long isUserMaster;
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL")
    private String email;
    @Id
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "POS_ID")
    private Long posId;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Column(name = "GROUP_CODE")
    private String groupCode;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "CAT_EMPLOYEE_ID")
    private Long catEmployeeId;
    @Column(name = "ROLE")
    private Long role;
    @Column(name = "FLAG_SMS")
    private Long flagSms;
    @Column(name = "FLAG_MAIL")
    private Long flagMail;
    @Column(name = "VOFFICE_NAME")
    private String vofficeName;

    public Long getIsUserMaster() {
        return isUserMaster;
    }

    public void setIsUserMaster(Long isUserMaster) {
        this.isUserMaster = isUserMaster;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getVofficeName() {
        return vofficeName;
    }

    public void setVofficeName(String vofficeName) {
        this.vofficeName = vofficeName;
    }

}
