package com.viettel.qll.bo;

import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblUsersBO")
@Table(name = "TBL_USERS")
/**
 *
 * @author: hailh10
 */
public class TblUsersBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_USERS_SEQ") })
	@Column(name = "TBL_USERS_ID", length = 22)
	private java.lang.Long tblUsersId;
	@Column(name = "USER_NAME", length = 4000)
	private java.lang.String userName;
	@Column(name = "PWD", length = 4000)
	private java.lang.String pwd;
	@Column(name = "USER_CODE", length = 4000)
	private java.lang.String userCode;
	@Column(name = "IS_ACTIVE", length = 22)
	private java.lang.Long isActive;

	@Column(name = "FULL_NAME", length = 4000)
	private java.lang.String fullName;
	
	@Column(name = "DON_VI", length = 4000)
	private java.lang.String donVi;
	
	@Column(name = "CHUC_DANH", length = 4000)
	private java.lang.String chucDanh;
	
	@Column(name = "PHONG_BAN", length = 4000)
	private java.lang.String phongBan;
	
	public java.lang.Long getTblUsersId(){
		return tblUsersId;
	}
	
	public void setTblUsersId(java.lang.Long tblUsersId)
	{
		this.tblUsersId = tblUsersId;
	}
	
	public java.lang.String getUserName(){
		return userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.String getPwd(){
		return pwd;
	}
	
	public void setPwd(java.lang.String pwd)
	{
		this.pwd = pwd;
	}
	
	public java.lang.String getUserCode(){
		return userCode;
	}
	
	public void setUserCode(java.lang.String userCode)
	{
		this.userCode = userCode;
	}
	
	public java.lang.Long getIsActive(){
		return isActive;
	}
	
	public void setIsActive(java.lang.Long isActive)
	{
		this.isActive = isActive;
	}
   
    @Override
    public TblUsersDTO toDTO() {
        TblUsersDTO tblUsersDTO = new TblUsersDTO(); 
        tblUsersDTO.setTblUsersId(this.tblUsersId);		
        tblUsersDTO.setUserName(this.userName);		
        tblUsersDTO.setPwd(this.pwd);		
        tblUsersDTO.setUserCode(this.userCode);		
        tblUsersDTO.setIsActive(this.isActive);	
        tblUsersDTO.setFullName(this.fullName);
        tblUsersDTO.setDonVi(this.donVi);
        tblUsersDTO.setChucDanh(this.chucDanh);
        tblUsersDTO.setPhongBan(this.phongBan);
        return tblUsersDTO;
    }

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getDonVi() {
		return donVi;
	}

	public void setDonVi(java.lang.String donVi) {
		this.donVi = donVi;
	}

	public java.lang.String getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(java.lang.String chucDanh) {
		this.chucDanh = chucDanh;
	}

	public java.lang.String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(java.lang.String phongBan) {
		this.phongBan = phongBan;
	}
    
    
    
}
