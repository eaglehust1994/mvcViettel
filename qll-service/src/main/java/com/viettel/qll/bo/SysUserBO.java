package com.viettel.qll.bo;

import com.viettel.qll.dto.SysUserDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.SysUserBO")
@Table(name = "SYS_USER")
/**
 *
 * @author: hailh10
 */
public class SysUserBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SYS_USER_SEQ") })
	@Column(name = "SYS_USER_ID", length = 22)
	private java.lang.Long sysUserId;
	@Column(name = "CHANGE_PASSWORD_DATE", length = 11)
	private java.util.Date changePasswordDate;
	@Column(name = "EMAIL", length = 400)
	private java.lang.String email;
	@Column(name = "EMPLOYEE_CODE", length = 400)
	private java.lang.String employeeCode;
	@Column(name = "FULL_NAME", length = 1600)
	private java.lang.String fullName;
	@Column(name = "LOGIN_NAME", length = 400)
	private java.lang.String loginName;
	@Column(name = "NEED_CHANGE_PASSWORD", length = 22)
	private java.lang.Long needChangePassword;
	@Column(name = "NEW_ID", length = 22)
	private java.lang.Long newId;
	@Column(name = "PASSWORD", length = 1600)
	private java.lang.String password;
	@Column(name = "PHONE_NUMBER", length = 400)
	private java.lang.String phoneNumber;
	@Column(name = "STATUS", length = 22)
	private java.lang.Long status;
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long sysGroupId;

	
	public java.lang.Long getSysUserId(){
		return sysUserId;
	}
	
	public void setSysUserId(java.lang.Long sysUserId)
	{
		this.sysUserId = sysUserId;
	}
	
	public java.util.Date getChangePasswordDate(){
		return changePasswordDate;
	}
	
	public void setChangePasswordDate(java.util.Date changePasswordDate)
	{
		this.changePasswordDate = changePasswordDate;
	}
	
	public java.lang.String getEmail(){
		return email;
	}
	
	public void setEmail(java.lang.String email)
	{
		this.email = email;
	}
	
	public java.lang.String getEmployeeCode(){
		return employeeCode;
	}
	
	public void setEmployeeCode(java.lang.String employeeCode)
	{
		this.employeeCode = employeeCode;
	}
	
	public java.lang.String getFullName(){
		return fullName;
	}
	
	public void setFullName(java.lang.String fullName)
	{
		this.fullName = fullName;
	}
	
	public java.lang.String getLoginName(){
		return loginName;
	}
	
	public void setLoginName(java.lang.String loginName)
	{
		this.loginName = loginName;
	}
	
	public java.lang.Long getNeedChangePassword(){
		return needChangePassword;
	}
	
	public void setNeedChangePassword(java.lang.Long needChangePassword)
	{
		this.needChangePassword = needChangePassword;
	}
	
	public java.lang.Long getNewId(){
		return newId;
	}
	
	public void setNewId(java.lang.Long newId)
	{
		this.newId = newId;
	}
	
	public java.lang.String getPassword(){
		return password;
	}
	
	public void setPassword(java.lang.String password)
	{
		this.password = password;
	}
	
	public java.lang.String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(java.lang.String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	public java.lang.Long getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.Long status)
	{
		this.status = status;
	}
	
	public java.lang.Long getSysGroupId(){
		return sysGroupId;
	}
	
	public void setSysGroupId(java.lang.Long sysGroupId)
	{
		this.sysGroupId = sysGroupId;
	}
   
    @Override
    public SysUserDTO toDTO() {
        SysUserDTO sysUserDTO = new SysUserDTO(); 
        sysUserDTO.setSysUserId(this.sysUserId);		
        sysUserDTO.setChangePasswordDate(this.changePasswordDate);		
        sysUserDTO.setEmail(this.email);		
        sysUserDTO.setEmployeeCode(this.employeeCode);		
        sysUserDTO.setFullName(this.fullName);		
        sysUserDTO.setLoginName(this.loginName);		
        sysUserDTO.setNeedChangePassword(this.needChangePassword);		
        sysUserDTO.setNewId(this.newId);		
        sysUserDTO.setPassword(this.password);		
        sysUserDTO.setPhoneNumber(this.phoneNumber);		
        sysUserDTO.setStatus(this.status);		
        sysUserDTO.setSysGroupId(this.sysGroupId);		
        return sysUserDTO;
    }
}
