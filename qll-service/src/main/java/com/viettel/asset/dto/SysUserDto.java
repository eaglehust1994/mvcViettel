package com.viettel.asset.dto;

import java.util.Date;

import com.viettel.asset.bo.SysUser;

public class SysUserDto {

	private Long userId;
	private String loginName;
	private String password;
	private String fullName;
	private String jobTitle;
	private String phone;
	private String email;
	private Date dateOfBirth;
	private String nativePlace;
	private Date companyJoinDate;
	private String cardId;
	private String mobile;
	private Long status;
	private Long appId;
	private Long oldId;
	private String mac;
	private Date createdDate;
	private Long oldUserId;
	private Long catEmployeeId;
	private Long role;
	private Long flagSms;
	private Long flagMail;
	private Long isUserMaster;
	private String vofficeName;

	public SysUserDto() {

	}

	public SysUserDto(SysUser entity) {
		this.userId = entity.getUserId();
		this.loginName = entity.getLoginName();
		this.password = entity.getPassword();
		this.fullName = entity.getFullName();
		this.jobTitle = entity.getJobTitle();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.dateOfBirth = entity.getDateOfBirth();
		this.nativePlace = entity.getNativePlace();
		this.companyJoinDate = entity.getCompanyJoinDate();
		this.cardId = entity.getCardId();
		this.mobile = entity.getMobile();
		this.status = entity.getStatus();
		this.appId = entity.getAppId();
		this.oldId = entity.getOldId();
		this.mac = entity.getMac();
		this.createdDate = entity.getCreatedDate();
		this.oldUserId = entity.getOldUserId();
		this.catEmployeeId = entity.getCatEmployeeId();
		this.role = entity.getRole();
		this.flagSms = entity.getFlagSms();
		this.flagMail = entity.getFlagMail();
		this.isUserMaster = entity.getIsUserMaster();
		this.vofficeName = entity.getVofficeName();
	}

	public SysUser toEntity() {
		SysUser entity = new SysUser();
		entity.setUserId(this.userId);
		entity.setLoginName(this.loginName);
		entity.setPassword(this.password);
		entity.setFullName(this.fullName);
		entity.setJobTitle(this.jobTitle);
		entity.setPhone(this.phone);
		entity.setEmail(this.email);
		entity.setDateOfBirth(this.dateOfBirth);
		entity.setNativePlace(this.nativePlace);
		entity.setCompanyJoinDate(this.companyJoinDate);
		entity.setCardId(this.cardId);
		entity.setMobile(this.mobile);
		entity.setStatus(this.status);
		entity.setAppId(this.appId);
		entity.setOldId(this.oldId);
		entity.setMac(this.mac);
		entity.setCreatedDate(this.createdDate);
		entity.setOldUserId(this.oldUserId);
		entity.setCatEmployeeId(this.catEmployeeId);
		entity.setRole(this.role);
		entity.setFlagSms(this.flagSms);
		entity.setFlagMail(this.flagMail);
		entity.setIsUserMaster(this.isUserMaster);
		entity.setVofficeName(this.vofficeName);
		return entity;
	}
	
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
