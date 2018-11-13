/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.SysUserQLKBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SYS_USERwmsBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserQLKDTO extends wmsBaseDTO<SysUserQLKBO> {

private java.lang.Long sysUserId;
private java.lang.String loginName;
private java.lang.String fullName;
private java.lang.String password;
private java.lang.String employeeCode;
private java.lang.String email;
private java.lang.String phoneNumber;
private java.lang.String status;
private java.util.Date birthday;
private java.lang.String position;
private java.lang.String namePhone;
private java.lang.Long departmentId;
private java.lang.String departmentName;
private String vofficePass;
public String getVofficePass() {
	return vofficePass;
}

public void setVofficePass(String vofficePass) {
	this.vofficePass = vofficePass;
}

public java.lang.String getNamePhone() {
	return fullName+" - "+phoneNumber;
}

private java.lang.String name;

    @Override
    public SysUserQLKBO toModel() {
    	SysUserQLKBO sysUserwmsBO = new SysUserQLKBO();
        sysUserwmsBO.setSysUserId(this.sysUserId);
        sysUserwmsBO.setLoginName(this.loginName);
        sysUserwmsBO.setFullName(this.fullName);
        sysUserwmsBO.setPassword(this.password);
        sysUserwmsBO.setEmployeeCode(this.employeeCode);
        sysUserwmsBO.setEmail(this.email);
        sysUserwmsBO.setPhoneNumber(this.phoneNumber);
        sysUserwmsBO.setStatus(this.status);
        sysUserwmsBO.setBirthday(this.birthday);
        sysUserwmsBO.setPosition(this.position);
        sysUserwmsBO.setSysGroupId(this.departmentId);
        return sysUserwmsBO;
    }

    @Override
     public Long getFWModelId() {
        return sysUserId;
    }
   
    @Override
    public String catchName() {
        return getSysUserId().toString();
    }
    public java.lang.Long getSysUserId(){
    return sysUserId;
    }
    public void setSysUserId(java.lang.Long sysUserId)
    {
    this.sysUserId = sysUserId;
    }
    
    public java.lang.String getLoginName(){
    return loginName;
    }
    public void setLoginName(java.lang.String loginName)
    {
    this.loginName = loginName;
    }
    
    public java.lang.String getFullName(){
    return fullName;
    }
    public void setFullName(java.lang.String fullName)
    {
    this.fullName = fullName;
    }
    
    public java.lang.String getPassword(){
    return password;
    }
    public void setPassword(java.lang.String password)
    {
    this.password = password;
    }
    
    public java.lang.String getEmployeeCode(){
    return employeeCode;
    }
    public void setEmployeeCode(java.lang.String employeeCode)
    {
    this.employeeCode = employeeCode;
    }
    
    public java.lang.String getEmail(){
    return email;
    }
    public void setEmail(java.lang.String email)
    {
    this.email = email;
    }
    
    public java.lang.String getPhoneNumber(){
    return phoneNumber;
    }
    public void setPhoneNumber(java.lang.String phoneNumber)
    {
    this.phoneNumber = phoneNumber;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.util.Date getBirthday(){
    return birthday;
    }
    public void setBirthday(java.util.Date birthday)
    {
    this.birthday = birthday;
    }
    
    public java.lang.String getPosition(){
    return position;
    }
    public void setPosition(java.lang.String position)
    {
    this.position = position;
    }

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
    
	public java.lang.Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(java.lang.Long departmentId) {
		this.departmentId = departmentId;
	}

	public java.lang.String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(java.lang.String departmentName) {
		this.departmentName = departmentName;
	}
}
