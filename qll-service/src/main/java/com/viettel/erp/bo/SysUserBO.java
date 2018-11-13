/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.SysUserDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SYS_USER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class SysUserBO extends BaseFWModelImpl {
     
private java.lang.Long userId;
private java.lang.String loginName;
private java.lang.String password;
private java.lang.String fullName;
private java.lang.String jobTitle;
private java.lang.String phone;
private java.lang.String email;
private java.util.Date dateOfBirth;
private java.lang.String nativePlace;
private java.util.Date companyJoinDate;
private java.lang.String cardId;
private java.lang.String mobile;
private java.lang.Long status;
private java.lang.Long appId;
private java.lang.Long oldId;
private java.lang.String mac;
private java.util.Date createdDate;
private java.lang.Long oldUserId;
private java.lang.Long catEmployeeId;
private java.lang.Long role;
private java.lang.Long flagSms;
private java.lang.Long flagMail;
private java.lang.Long isUserMaster;
private java.lang.String vofficeName;

 public SysUserBO() {
        setColId("userId");
        setColName("userId");
        setUniqueColumn(new String[]{"userId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "SYS_USER_SEQ")
            }
    )
@Column(name = "USER_ID", length = 22)
public java.lang.Long getUserId(){
return userId;
}
public void setUserId(java.lang.Long userId)
{
this.userId = userId;
}
@Column(name = "LOGIN_NAME", length = 200)
public java.lang.String getLoginName(){
return loginName;
}
public void setLoginName(java.lang.String loginName)
{
this.loginName = loginName;
}
@Column(name = "PASSWORD", length = 200)
public java.lang.String getPassword(){
return password;
}
public void setPassword(java.lang.String password)
{
this.password = password;
}
@Column(name = "FULL_NAME", length = 200)
public java.lang.String getFullName(){
return fullName;
}
public void setFullName(java.lang.String fullName)
{
this.fullName = fullName;
}
@Column(name = "JOB_TITLE", length = 200)
public java.lang.String getJobTitle(){
return jobTitle;
}
public void setJobTitle(java.lang.String jobTitle)
{
this.jobTitle = jobTitle;
}
@Column(name = "PHONE", length = 40)
public java.lang.String getPhone(){
return phone;
}
public void setPhone(java.lang.String phone)
{
this.phone = phone;
}
@Column(name = "EMAIL", length = 2000)
public java.lang.String getEmail(){
return email;
}
public void setEmail(java.lang.String email)
{
this.email = email;
}
@Column(name = "DATE_OF_BIRTH", length = 7)
public java.util.Date getDateOfBirth(){
return dateOfBirth;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth = dateOfBirth;
}
@Column(name = "NATIVE_PLACE", length = 400)
public java.lang.String getNativePlace(){
return nativePlace;
}
public void setNativePlace(java.lang.String nativePlace)
{
this.nativePlace = nativePlace;
}
@Column(name = "COMPANY_JOIN_DATE", length = 7)
public java.util.Date getCompanyJoinDate(){
return companyJoinDate;
}
public void setCompanyJoinDate(java.util.Date companyJoinDate)
{
this.companyJoinDate = companyJoinDate;
}
@Column(name = "CARD_ID", length = 40)
public java.lang.String getCardId(){
return cardId;
}
public void setCardId(java.lang.String cardId)
{
this.cardId = cardId;
}
@Column(name = "MOBILE", length = 40)
public java.lang.String getMobile(){
return mobile;
}
public void setMobile(java.lang.String mobile)
{
this.mobile = mobile;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "APP_ID", length = 22)
public java.lang.Long getAppId(){
return appId;
}
public void setAppId(java.lang.Long appId)
{
this.appId = appId;
}
@Column(name = "OLD_ID", length = 22)
public java.lang.Long getOldId(){
return oldId;
}
public void setOldId(java.lang.Long oldId)
{
this.oldId = oldId;
}
@Column(name = "MAC", length = 200)
public java.lang.String getMac(){
return mac;
}
public void setMac(java.lang.String mac)
{
this.mac = mac;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "OLD_USER_ID", length = 22)
public java.lang.Long getOldUserId(){
return oldUserId;
}
public void setOldUserId(java.lang.Long oldUserId)
{
this.oldUserId = oldUserId;
}
@Column(name = "CAT_EMPLOYEE_ID", length = 22)
public java.lang.Long getCatEmployeeId(){
return catEmployeeId;
}
public void setCatEmployeeId(java.lang.Long catEmployeeId)
{
this.catEmployeeId = catEmployeeId;
}
@Column(name = "ROLE", length = 22)
public java.lang.Long getRole(){
return role;
}
public void setRole(java.lang.Long role)
{
this.role = role;
}
@Column(name = "FLAG_SMS", length = 22)
public java.lang.Long getFlagSms(){
return flagSms;
}
public void setFlagSms(java.lang.Long flagSms)
{
this.flagSms = flagSms;
}
@Column(name = "FLAG_MAIL", length = 22)
public java.lang.Long getFlagMail(){
return flagMail;
}
public void setFlagMail(java.lang.Long flagMail)
{
this.flagMail = flagMail;
}
@Column(name = "IS_USER_MASTER", length = 22)
public java.lang.Long getIsUserMaster(){
return isUserMaster;
}
public void setIsUserMaster(java.lang.Long isUserMaster)
{
this.isUserMaster = isUserMaster;
}
@Column(name = "VOFFICE_NAME", length = 200)
public java.lang.String getVofficeName(){
return vofficeName;
}
public void setVofficeName(java.lang.String vofficeName)
{
this.vofficeName = vofficeName;
}
   

    @Override
    public SysUserDTO toDTO() {
        SysUserDTO sysUserDTO = new SysUserDTO();
        //set cac gia tri 
        sysUserDTO.setUserId(this.userId);
        sysUserDTO.setLoginName(this.loginName);
        sysUserDTO.setPassword(this.password);
        sysUserDTO.setFullName(this.fullName);
        sysUserDTO.setJobTitle(this.jobTitle);
        sysUserDTO.setPhone(this.phone);
        sysUserDTO.setEmail(this.email);
        sysUserDTO.setDateOfBirth(this.dateOfBirth);
        sysUserDTO.setNativePlace(this.nativePlace);
        sysUserDTO.setCompanyJoinDate(this.companyJoinDate);
        sysUserDTO.setCardId(this.cardId);
        sysUserDTO.setMobile(this.mobile);
        sysUserDTO.setStatus(this.status);
        sysUserDTO.setAppId(this.appId);
        sysUserDTO.setOldId(this.oldId);
        sysUserDTO.setMac(this.mac);
        sysUserDTO.setCreatedDate(this.createdDate);
        sysUserDTO.setOldUserId(this.oldUserId);
        sysUserDTO.setCatEmployeeId(this.catEmployeeId);
        sysUserDTO.setRole(this.role);
        sysUserDTO.setFlagSms(this.flagSms);
        sysUserDTO.setFlagMail(this.flagMail);
        sysUserDTO.setIsUserMaster(this.isUserMaster);
        sysUserDTO.setVofficeName(this.vofficeName);
        return sysUserDTO;
    }
}
