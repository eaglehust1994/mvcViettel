/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.viettel.erp.dto.VSysUserDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "user")
@Table(name = "V_SYS_USER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class VSysUserBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419009104201504439L;
	private Long isUserMaster;
	private String loginName;
	private String fullName;
	private String email;
	private Long userId;
	private Long groupId;
	private Long posId;
	private Long parentId;
	private String groupName;
	private String groupCode;
	private Long provinceId;
	private String phone;
	private java.util.Date createdDate;
	private String jobTitle;
	private Long catEmployeeId;
	private Long role;
	private Long flagSms;
	private Long flagMail;
	private String vofficeName;

	public VSysUserBO() {
		setColId("isUserMaster");
		setColName("isUserMaster");
		setUniqueColumn(new String[] { "isUserMaster" });
	}

	@Column(name = "IS_USER_MASTER", length = 22)
	public Long getIsUserMaster() {
		return isUserMaster;
	}

	public void setIsUserMaster(Long isUserMaster) {
		this.isUserMaster = isUserMaster;
	}
	
	@Id
	@Column(name = "LOGIN_NAME", length = 200)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "FULL_NAME", length = 200)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "EMAIL", length = 2000)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "USER_ID", length = 22)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "GROUP_ID", length = 22)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "POS_ID", length = 22)
	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	@Column(name = "PARENT_ID", length = 22)
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "GROUP_NAME", length = 200)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "GROUP_CODE", length = 100)
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(name = "PROVINCE_ID", length = 22)
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PHONE", length = 40)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "JOB_TITLE", length = 200)
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "CAT_EMPLOYEE_ID", length = 22)
	public Long getCatEmployeeId() {
		return catEmployeeId;
	}

	public void setCatEmployeeId(Long catEmployeeId) {
		this.catEmployeeId = catEmployeeId;
	}

	@Column(name = "ROLE", length = 22)
	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	@Column(name = "FLAG_SMS", length = 22)
	public Long getFlagSms() {
		return flagSms;
	}

	public void setFlagSms(Long flagSms) {
		this.flagSms = flagSms;
	}

	@Column(name = "FLAG_MAIL", length = 22)
	public Long getFlagMail() {
		return flagMail;
	}

	public void setFlagMail(Long flagMail) {
		this.flagMail = flagMail;
	}

	@Column(name = "VOFFICE_NAME", length = 200)
	public String getVofficeName() {
		return vofficeName;
	}

	public void setVofficeName(String vofficeName) {
		this.vofficeName = vofficeName;
	}

	@Override
	public VSysUserDTO toDTO() {
		VSysUserDTO vSysUserDTO = new VSysUserDTO();
		// set cac gia tri
		vSysUserDTO.setIsUserMaster(this.isUserMaster);
		vSysUserDTO.setLoginName(this.loginName);
		vSysUserDTO.setFullName(this.fullName);
		vSysUserDTO.setEmail(this.email);
		vSysUserDTO.setUserId(this.userId);
		vSysUserDTO.setGroupId(this.groupId);
		vSysUserDTO.setPosId(this.posId);
		vSysUserDTO.setParentId(this.parentId);
		vSysUserDTO.setGroupName(this.groupName);
		vSysUserDTO.setGroupCode(this.groupCode);
		vSysUserDTO.setProvinceId(this.provinceId);
		vSysUserDTO.setPhone(this.phone);
		vSysUserDTO.setCreatedDate(this.createdDate);
		vSysUserDTO.setJobTitle(this.jobTitle);
		vSysUserDTO.setCatEmployeeId(this.catEmployeeId);
		vSysUserDTO.setRole(this.role);
		vSysUserDTO.setFlagSms(this.flagSms);
		vSysUserDTO.setFlagMail(this.flagMail);
		vSysUserDTO.setVofficeName(this.vofficeName);
		return vSysUserDTO;
	}
}
