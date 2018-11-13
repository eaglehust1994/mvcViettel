/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.Common.CommonBO;

import com.viettel.Common.CommonDTO.SysUserQLKDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "VPS_OWNER.SYS_USER")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class SysUserQLKBO extends BaseFWModelImpl {
     
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
private java.lang.Long sysGroupId;




public SysUserQLKBO() {
        setColId("sysUserId");
        setColName("sysUserId");
        setUniqueColumn(new String[]{"sysUserId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VPS_OWNER.SYS_USER_SEQ")
            }
    )
@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Long getSysUserId(){
return sysUserId;
}
public void setSysUserId(java.lang.Long sysUserId)
{
this.sysUserId = sysUserId;
}
@Column(name = "LOGIN_NAME", length = 100)
public java.lang.String getLoginName(){
return loginName;
}
public void setLoginName(java.lang.String loginName)
{
this.loginName = loginName;
}
@Column(name = "FULL_NAME", length = 1000)
public java.lang.String getFullName(){
return fullName;
}
public void setFullName(java.lang.String fullName)
{
this.fullName = fullName;
}
@Column(name = "PASSWORD", length = 200)
public java.lang.String getPassword(){
return password;
}
public void setPassword(java.lang.String password)
{
this.password = password;
}
@Column(name = "EMPLOYEE_CODE", length = 100)
public java.lang.String getEmployeeCode(){
return employeeCode;
}
public void setEmployeeCode(java.lang.String employeeCode)
{
this.employeeCode = employeeCode;
}
@Column(name = "EMAIL", length = 100)
public java.lang.String getEmail(){
return email;
}
public void setEmail(java.lang.String email)
{
this.email = email;
}
@Column(name = "PHONE_NUMBER", length = 100)
public java.lang.String getPhoneNumber(){
return phoneNumber;
}
public void setPhoneNumber(java.lang.String phoneNumber)
{
this.phoneNumber = phoneNumber;
}
@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "BIRTHDAY", length = 7)
public java.util.Date getBirthday(){
return birthday;
}
public void setBirthday(java.util.Date birthday)
{
this.birthday = birthday;
}
@Column(name = "POSITION", length = 400)
public java.lang.String getPosition(){
return position;
}
public void setPosition(java.lang.String position)
{
this.position = position;
}

@Column(name = "SYS_GROUP_ID", length = 10)
public java.lang.Long getSysGroupId() {
	return sysGroupId;
}

public void setSysGroupId(java.lang.Long sysGroupId) {
	this.sysGroupId = sysGroupId;
}
    @Override
    public SysUserQLKDTO toDTO() {
    	SysUserQLKDTO sysUserwmsDTO = new SysUserQLKDTO();
        //set cac gia tri 
        sysUserwmsDTO.setSysUserId(this.sysUserId);
        sysUserwmsDTO.setLoginName(this.loginName);
        sysUserwmsDTO.setFullName(this.fullName);
        sysUserwmsDTO.setPassword(this.password);
        sysUserwmsDTO.setEmployeeCode(this.employeeCode);
        sysUserwmsDTO.setEmail(this.email);
        sysUserwmsDTO.setPhoneNumber(this.phoneNumber);
        sysUserwmsDTO.setStatus(this.status);
        sysUserwmsDTO.setBirthday(this.birthday);
        sysUserwmsDTO.setPosition(this.position);
        sysUserwmsDTO.setDepartmentId(this.sysGroupId);
        return sysUserwmsDTO;
    }
}
