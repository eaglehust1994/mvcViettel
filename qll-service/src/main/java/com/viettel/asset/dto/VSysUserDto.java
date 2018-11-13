package com.viettel.asset.dto;

import java.io.Serializable;
import java.util.Date;
import com.viettel.asset.bo.VSysUser;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "vSysUser")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VSysUserDto implements Serializable{
	
	
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
		private Date createdDate;
		private String jobTitle;
		private Long catEmployeeId;
		private Long role;
		private Long flagSms;
		private Long flagMail;
		private String vofficeName;
	
	public VSysUserDto() {
		
	}
	
	public VSysUserDto(VSysUser entity) {
		this.isUserMaster = entity.getIsUserMaster();
		this.loginName = entity.getLoginName();
		this.fullName = entity.getFullName();
		this.email = entity.getEmail();
		this.userId = entity.getUserId();
		this.groupId = entity.getGroupId();
		this.posId = entity.getPosId();
		this.parentId = entity.getParentId();
		this.groupName = entity.getGroupName();
		this.groupCode = entity.getGroupCode();
		this.provinceId = entity.getProvinceId();
		this.phone = entity.getPhone();
		this.createdDate = entity.getCreatedDate();
		this.jobTitle = entity.getJobTitle();
		this.catEmployeeId = entity.getCatEmployeeId();
		this.role = entity.getRole();
		this.flagSms = entity.getFlagSms();
		this.flagMail = entity.getFlagMail();
		this.vofficeName = entity.getVofficeName();
	}
	
	public VSysUser toEntity() {
		VSysUser entity = new VSysUser();
		entity.setIsUserMaster(this.isUserMaster);
		entity.setLoginName(this.loginName);
		entity.setFullName(this.fullName);
		entity.setEmail(this.email);
		entity.setUserId(this.userId);
		entity.setGroupId(this.groupId);
		entity.setPosId(this.posId);
		entity.setParentId(this.parentId);
		entity.setGroupName(this.groupName);
		entity.setGroupCode(this.groupCode);
		entity.setProvinceId(this.provinceId);
		entity.setPhone(this.phone);
		entity.setCreatedDate(this.createdDate);
		entity.setJobTitle(this.jobTitle);
		entity.setCatEmployeeId(this.catEmployeeId);
		entity.setRole(this.role);
		entity.setFlagSms(this.flagSms);
		entity.setFlagMail(this.flagMail);
		entity.setVofficeName(this.vofficeName);
		return entity;
	}
	
	public void updateEntity(VSysUser entity) {
			entity.setIsUserMaster(this.isUserMaster);
			entity.setLoginName(this.loginName);
			entity.setFullName(this.fullName);
			entity.setEmail(this.email);
			entity.setUserId(this.userId);
			entity.setGroupId(this.groupId);
			entity.setPosId(this.posId);
			entity.setParentId(this.parentId);
			entity.setGroupName(this.groupName);
			entity.setGroupCode(this.groupCode);
			entity.setProvinceId(this.provinceId);
			entity.setPhone(this.phone);
			entity.setCreatedDate(this.createdDate);
			entity.setJobTitle(this.jobTitle);
			entity.setCatEmployeeId(this.catEmployeeId);
			entity.setRole(this.role);
			entity.setFlagSms(this.flagSms);
			entity.setFlagMail(this.flagMail);
			entity.setVofficeName(this.vofficeName);
	}
	
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
