/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.bo.VSysUserBO;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "V_SYS_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VSysUserDTO extends BaseFWDTOImpl<VSysUserBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719516829752786258L;
	private java.lang.Long isUserMaster;
	private java.lang.String loginName;
	private java.lang.String fullName;
	private java.lang.String email;
	private java.lang.Long userId;
	private java.lang.Long groupId;
	private java.lang.Long posId;
	private java.lang.Long parentId;
	private java.lang.String groupName;
	private java.lang.String groupCode;
	private java.lang.Long provinceId;
	private java.lang.String phone;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	private java.util.Date createdDate;
	private java.lang.String jobTitle;
	private java.lang.Long catEmployeeId;
	private java.lang.Long role;
	private java.lang.Long flagSms;
	private java.lang.Long flagMail;
	private java.lang.String vofficeName;

	@Override
	public VSysUserBO toModel() {
		VSysUserBO vSysUserBO = new VSysUserBO();
		vSysUserBO.setIsUserMaster(this.isUserMaster);
		vSysUserBO.setLoginName(this.loginName);
		vSysUserBO.setFullName(this.fullName);
		vSysUserBO.setEmail(this.email);
		vSysUserBO.setUserId(this.userId);
		vSysUserBO.setGroupId(this.groupId);
		vSysUserBO.setPosId(this.posId);
		vSysUserBO.setParentId(this.parentId);
		vSysUserBO.setGroupName(this.groupName);
		vSysUserBO.setGroupCode(this.groupCode);
		vSysUserBO.setProvinceId(this.provinceId);
		vSysUserBO.setPhone(this.phone);
		vSysUserBO.setCreatedDate(this.createdDate);
		vSysUserBO.setJobTitle(this.jobTitle);
		vSysUserBO.setCatEmployeeId(this.catEmployeeId);
		vSysUserBO.setRole(this.role);
		vSysUserBO.setFlagSms(this.flagSms);
		vSysUserBO.setFlagMail(this.flagMail);
		vSysUserBO.setVofficeName(this.vofficeName);
		return vSysUserBO;
	}

	public java.lang.Long getIsUserMaster() {
		return isUserMaster;
	}

	public void setIsUserMaster(java.lang.Long isUserMaster) {
		this.isUserMaster = isUserMaster;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.Long getGroupId() {
		return groupId;
	}

	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
	}

	public java.lang.Long getPosId() {
		return posId;
	}

	public void setPosId(java.lang.Long posId) {
		this.posId = posId;
	}

	public java.lang.Long getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	public java.lang.String getGroupName() {
		return groupName;
	}

	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	public java.lang.String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(java.lang.String groupCode) {
		this.groupCode = groupCode;
	}

	public java.lang.Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.Long provinceId) {
		this.provinceId = provinceId;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(java.lang.String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public java.lang.Long getCatEmployeeId() {
		return catEmployeeId;
	}

	public void setCatEmployeeId(java.lang.Long catEmployeeId) {
		this.catEmployeeId = catEmployeeId;
	}

	public java.lang.Long getRole() {
		return role;
	}

	public void setRole(java.lang.Long role) {
		this.role = role;
	}

	public java.lang.Long getFlagSms() {
		return flagSms;
	}

	public void setFlagSms(java.lang.Long flagSms) {
		this.flagSms = flagSms;
	}

	public java.lang.Long getFlagMail() {
		return flagMail;
	}

	public void setFlagMail(java.lang.Long flagMail) {
		this.flagMail = flagMail;
	}

	public java.lang.String getVofficeName() {
		return vofficeName;
	}

	public void setVofficeName(java.lang.String vofficeName) {
		this.vofficeName = vofficeName;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}

}
