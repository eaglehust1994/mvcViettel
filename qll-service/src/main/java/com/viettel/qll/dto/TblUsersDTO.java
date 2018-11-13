package com.viettel.qll.dto;

import com.viettel.qll.bo.TblUsersBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.List;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_USERSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblUsersDTO extends QllBaseDTO<TblUsersBO> {

	private java.lang.Long tblUsersId;
	private java.lang.String userName;
	private java.lang.String pwd;
	private java.lang.String userCode;
	private java.lang.Long isActive;
	private String roleCode;
	private java.lang.String fullName;
	private java.lang.String donVi;
	private java.lang.String chucDanh;
	private java.lang.String phongBan;
	private java.lang.String ipAdrr;
	private List<TblUsersDTO> lstRole;
	private String tblRolesName;
	
	private checkRole checkRole;
	private List<TblUsersDTO> lstUser;
	@Override
	public TblUsersBO toModel() {
		TblUsersBO tblUsersBO = new TblUsersBO();
		tblUsersBO.setTblUsersId(this.tblUsersId);
		tblUsersBO.setUserName(this.userName);
		tblUsersBO.setPwd(this.pwd);
		tblUsersBO.setUserCode(this.userCode);
		tblUsersBO.setIsActive(this.isActive);
		tblUsersBO.setFullName(this.fullName);
		tblUsersBO.setDonVi(this.donVi);
		tblUsersBO.setChucDanh(this.chucDanh);
		tblUsersBO.setPhongBan(this.phongBan);
		// if (this.isActive == null || "".equalsIgnoreCase(this.isActive) ||
		// "N".equalsIgnoreCase(this.isActive)) {
		// tblUsersBO.setIsActive("N");
		// } else {
		// tblUsersBO.setIsActive("Y");
		// }
		return tblUsersBO;
	}

	@Override
	public Long getFWModelId() {
		return tblUsersId;
	}

	@Override
	public String catchName() {
		return getTblUsersId().toString();
	}

	@JsonProperty("tblUsersId")
	public java.lang.Long getTblUsersId() {
		return tblUsersId;
	}

	public void setTblUsersId(java.lang.Long tblUsersId) {
		this.tblUsersId = tblUsersId;
	}

	@JsonProperty("userName")
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	@JsonProperty("pwd")
	public java.lang.String getPwd() {
		return pwd;
	}

	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}

	@JsonProperty("userCode")
	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	@JsonProperty("isActive")
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}
	@JsonProperty("roleCode")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@JsonProperty("fullName")
	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}
	@JsonProperty("donVi")
	public java.lang.String getDonVi() {
		return donVi;
	}

	public void setDonVi(java.lang.String donVi) {
		this.donVi = donVi;
	}
	@JsonProperty("chucDanh")
	public java.lang.String getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(java.lang.String chucDanh) {
		this.chucDanh = chucDanh;
	}
	@JsonProperty("phongBan")
	public java.lang.String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(java.lang.String phongBan) {
		this.phongBan = phongBan;
	}

	public java.lang.String getIpAdrr() {
		return ipAdrr;
	}

	public void setIpAdrr(java.lang.String ipAdrr) {
		this.ipAdrr = ipAdrr;
	}


	public checkRole getCheckRole() {
		return checkRole;
	}

	public void setCheckRole(checkRole checkRole) {
		this.checkRole = checkRole;
	}

	public List<TblUsersDTO> getLstUser() {
		return lstUser;
	}

	public void setLstUser(List<TblUsersDTO> lstUser) {
		this.lstUser = lstUser;
	}

	public List<TblUsersDTO> getLstRole() {
		return lstRole;
	}

	public void setLstRole(List<TblUsersDTO> lstRole) {
		this.lstRole = lstRole;
	}

	public String getTblRolesName() {
		return tblRolesName;
	}

	public void setTblRolesName(String tblRolesName) {
		this.tblRolesName = tblRolesName;
	}

	
}
