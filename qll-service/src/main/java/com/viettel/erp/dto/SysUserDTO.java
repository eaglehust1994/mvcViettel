/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.SysUserBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "SYS_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserDTO extends BaseFWDTOImpl<SysUserBO> {

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
private java.lang.Boolean isSize;
private java.lang.String name;

    @Override
    public SysUserBO toModel() {
        SysUserBO sysUserBO = new SysUserBO();
        sysUserBO.setUserId(this.userId);
        sysUserBO.setLoginName(this.loginName);
        sysUserBO.setPassword(this.password);
        sysUserBO.setFullName(this.fullName);
        sysUserBO.setJobTitle(this.jobTitle);
        sysUserBO.setPhone(this.phone);
        sysUserBO.setEmail(this.email);
        sysUserBO.setDateOfBirth(this.dateOfBirth);
        sysUserBO.setNativePlace(this.nativePlace);
        sysUserBO.setCompanyJoinDate(this.companyJoinDate);
        sysUserBO.setCardId(this.cardId);
        sysUserBO.setMobile(this.mobile);
        sysUserBO.setStatus(this.status);
        sysUserBO.setAppId(this.appId);
        sysUserBO.setOldId(this.oldId);
        sysUserBO.setMac(this.mac);
        sysUserBO.setCreatedDate(this.createdDate);
        sysUserBO.setOldUserId(this.oldUserId);
        sysUserBO.setCatEmployeeId(this.catEmployeeId);
        sysUserBO.setRole(this.role);
        sysUserBO.setFlagSms(this.flagSms);
        sysUserBO.setFlagMail(this.flagMail);
        sysUserBO.setIsUserMaster(this.isUserMaster);
        sysUserBO.setVofficeName(this.vofficeName);
        return sysUserBO;
    }

    @Override
     public Long getFWModelId() {
        return userId;
    }
   
    @Override
    public String catchName() {
        return getUserId().toString();
    }
    public java.lang.Long getUserId(){
    return userId;
    }
    public void setUserId(java.lang.Long userId)
    {
    this.userId = userId;
    }
    
    public java.lang.String getLoginName(){
    return loginName;
    }
    public void setLoginName(java.lang.String loginName)
    {
    this.loginName = loginName;
    }
    
    public java.lang.String getPassword(){
    return password;
    }
    public void setPassword(java.lang.String password)
    {
    this.password = password;
    }
    
    public java.lang.String getFullName(){
    return fullName;
    }
    public void setFullName(java.lang.String fullName)
    {
    this.fullName = fullName;
    }
    
    public java.lang.String getJobTitle(){
    return jobTitle;
    }
    public void setJobTitle(java.lang.String jobTitle)
    {
    this.jobTitle = jobTitle;
    }
    
    public java.lang.String getPhone(){
    return phone;
    }
    public void setPhone(java.lang.String phone)
    {
    this.phone = phone;
    }
    
    public java.lang.String getEmail(){
    return email;
    }
    public void setEmail(java.lang.String email)
    {
    this.email = email;
    }
    
    public java.util.Date getDateOfBirth(){
    return dateOfBirth;
    }
    public void setDateOfBirth(java.util.Date dateOfBirth)
    {
    this.dateOfBirth = dateOfBirth;
    }
    
    public java.lang.String getNativePlace(){
    return nativePlace;
    }
    public void setNativePlace(java.lang.String nativePlace)
    {
    this.nativePlace = nativePlace;
    }
    
    public java.util.Date getCompanyJoinDate(){
    return companyJoinDate;
    }
    public void setCompanyJoinDate(java.util.Date companyJoinDate)
    {
    this.companyJoinDate = companyJoinDate;
    }
    
    public java.lang.String getCardId(){
    return cardId;
    }
    public void setCardId(java.lang.String cardId)
    {
    this.cardId = cardId;
    }
    
    public java.lang.String getMobile(){
    return mobile;
    }
    public void setMobile(java.lang.String mobile)
    {
    this.mobile = mobile;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Long getAppId(){
    return appId;
    }
    public void setAppId(java.lang.Long appId)
    {
    this.appId = appId;
    }
    
    public java.lang.Long getOldId(){
    return oldId;
    }
    public void setOldId(java.lang.Long oldId)
    {
    this.oldId = oldId;
    }
    
    public java.lang.String getMac(){
    return mac;
    }
    public void setMac(java.lang.String mac)
    {
    this.mac = mac;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getOldUserId(){
    return oldUserId;
    }
    public void setOldUserId(java.lang.Long oldUserId)
    {
    this.oldUserId = oldUserId;
    }
    
    public java.lang.Long getCatEmployeeId(){
    return catEmployeeId;
    }
    public void setCatEmployeeId(java.lang.Long catEmployeeId)
    {
    this.catEmployeeId = catEmployeeId;
    }
    
    public java.lang.Long getRole(){
    return role;
    }
    public void setRole(java.lang.Long role)
    {
    this.role = role;
    }
    
    public java.lang.Long getFlagSms(){
    return flagSms;
    }
    public void setFlagSms(java.lang.Long flagSms)
    {
    this.flagSms = flagSms;
    }
    
    public java.lang.Long getFlagMail(){
    return flagMail;
    }
    public void setFlagMail(java.lang.Long flagMail)
    {
    this.flagMail = flagMail;
    }
    
    public java.lang.Long getIsUserMaster(){
    return isUserMaster;
    }
    public void setIsUserMaster(java.lang.Long isUserMaster)
    {
    this.isUserMaster = isUserMaster;
    }
    
    public java.lang.String getVofficeName(){
    return vofficeName;
    }
    public void setVofficeName(java.lang.String vofficeName)
    {
    this.vofficeName = vofficeName;
    }

	public java.lang.Boolean getIsSize() {
		return isSize;
	}

	public void setIsSize(java.lang.Boolean isSize) {
		this.isSize = isSize;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
   
}
