package com.viettel.wms.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.SignVofficeDetailBO;
@XmlRootElement(name = "SIGN_VOFFICE_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignVofficeDetailDTO extends wmsBaseDTO<SignVofficeDetailBO>{

	private java.lang.Long signVofficeDetailId;
	private java.lang.Long odrer;
	private java.lang.String odrerName;
	private java.lang.Long roleId;
	private java.lang.String roleName;
	private java.lang.Long signVofficeId;
	private java.lang.Long sysUserId;
	private java.lang.String sysUserName;
	
	

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

	@Override
	public SignVofficeDetailBO toModel() {
		SignVofficeDetailBO signVofficeDetailBO=new SignVofficeDetailBO();
		signVofficeDetailBO.setSignVofficeDetailId(this.signVofficeDetailId);
		signVofficeDetailBO.setOdrer(this.odrer);
		signVofficeDetailBO.setOdrerName(this.odrerName);
		signVofficeDetailBO.setRoleId(this.roleId);
		signVofficeDetailBO.setRoleName(this.roleName);
		signVofficeDetailBO.setSignVofficeId(this.signVofficeId);
		signVofficeDetailBO.setSysUserId(this.sysUserId);
		return signVofficeDetailBO;
	}
	
	public java.lang.Long getSignVofficeDetailId() {
		return signVofficeDetailId;
	}

	public void setSignVofficeDetailId(java.lang.Long signVofficeDetailId) {
		this.signVofficeDetailId = signVofficeDetailId;
	}

	public java.lang.Long getOdrer() {
		return odrer;
	}

	public void setOdrer(java.lang.Long odrer) {
		this.odrer = odrer;
	}

	public java.lang.String getOdrerName() {
		return odrerName;
	}

	public void setOdrerName(java.lang.String odrerName) {
		this.odrerName = odrerName;
	}

	public java.lang.Long getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getRoleName() {
		return roleName;
	}

	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	public java.lang.Long getSignVofficeId() {
		return signVofficeId;
	}

	public void setSignVofficeId(java.lang.Long signVofficeId) {
		this.signVofficeId = signVofficeId;
	}

	public java.lang.Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(java.lang.Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	public java.lang.String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(java.lang.String sysUserName) {
		this.sysUserName = sysUserName;
	}

}
