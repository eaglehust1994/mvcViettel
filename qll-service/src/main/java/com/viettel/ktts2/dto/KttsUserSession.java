package com.viettel.ktts2.dto;

import java.io.Serializable;

import com.viettel.ktts.vps.IUserToken;
import com.viettel.wms.dto.SysUserQLKDTO;

public class KttsUserSession implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5346393082865055531L;

	private IUserToken userToken;
	
	private KttsSysUserDto kttsSysUserDto;
	 private SysUserQLKDTO vpsUserInfo;
	

	
	
	public SysUserQLKDTO getVpsUserInfo() {
		return vpsUserInfo;
	}

	public void setVpsUserInfo(SysUserQLKDTO vpsUserInfo) {
		this.vpsUserInfo = vpsUserInfo;
	}

	public KttsSysUserDto getKttsSysUserDto() {
		return kttsSysUserDto;
	}

	public void setKttsSysUserDto(KttsSysUserDto kttsSysUserDto) {
		this.kttsSysUserDto = kttsSysUserDto;
	}

	public IUserToken getUserToken() {
		return userToken;
	}

	public void setUserToken(IUserToken userToken) {
		this.userToken = userToken;
	}

	public String getEmail() {	
		return userToken.getEmail();
	}
	
	public String getEmployeeCode() {
		return userToken.getEmployeeCode();
	}
	
	public String getFullName() {
		return userToken.getFullName();
	}
	
	public String getUserName() {
		return userToken.getUserName();
	}
	
	public String getPhoneNumber() {
		return userToken.getPhoneNumber();
		
	}
	
	public Long getSysUserId() {
		return userToken.getSysUserId();
		
	}
	
	public Long getGroupId(){
		return kttsSysUserDto != null ? kttsSysUserDto.getGroupId() : null;
	}

	
}
