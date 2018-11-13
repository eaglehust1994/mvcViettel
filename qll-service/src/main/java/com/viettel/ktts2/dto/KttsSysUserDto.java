package com.viettel.ktts2.dto;

public class KttsSysUserDto {
	private Long sysUserId;
	private Long groupId;
	private String groupName;
	private String groupCode;
	private String vOfficeName;
	
	public String getVOfficeName() {
		return vOfficeName;
	}
	public void setVOfficeName(String vOficeName) {
		this.vOfficeName = vOficeName;
	}
	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	
	
	
	
}
