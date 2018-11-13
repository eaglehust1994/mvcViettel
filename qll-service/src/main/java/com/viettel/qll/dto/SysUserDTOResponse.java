package com.viettel.qll.dto;

import java.util.List;

import com.viettel.asset.dto.ResultInfo;
import com.viettel.vps.webservice.SysUserBO;

public class SysUserDTOResponse {
	private List<SysUserDTO> lstSysUserComDTO;
	private ResultInfo resultInfo;
	private SysUserDTO userLogin;
	private SysUserBO user;
	private Long userRole;
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	
	public List<SysUserDTO> getListUser() {
		return lstSysUserComDTO;
	}
	
	public void setListUser(List<SysUserDTO> lstSysUserComDTO) {
		this.lstSysUserComDTO = lstSysUserComDTO;
	}
	public List<SysUserDTO> getLstSysUserComDTO() {
		return lstSysUserComDTO;
	}
	public void setLstSysUserComDTO(List<SysUserDTO> lstSysUserComDTO) {
		this.lstSysUserComDTO = lstSysUserComDTO;
	}
	public SysUserDTO getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(SysUserDTO userLogin) {
		this.userLogin = userLogin;
	}
	public SysUserBO getUser() {
		return user;
	}
	public void setUser(SysUserBO user) {
		this.user = user;
	}
	public Long getUserRole() {
		return userRole;
	}
	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}
	
	
	
}
