package com.viettel.ims.dto;

import com.viettel.ims.bo.SysUserBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "SYS_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserDTO extends imsBaseDTO<SysUserBO> {

	private java.lang.Double sysGroupId;
	private java.lang.String sysGroupName;
	private java.lang.Long needChangePassword;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDateTo;
	private java.lang.Long newId;
	private java.lang.String newName;
	private java.lang.Long status;
	private java.lang.String phoneNumber;
	private java.lang.String email;
	private java.lang.String employeeCode;
	private java.lang.String password;
	private java.lang.String fullName;
	private java.lang.String loginName;
	private java.lang.Long sysUserId;
	private java.lang.String sysUserName;

    @Override
    public SysUserBO toModel() {
        SysUserBO sysUserBO = new SysUserBO();
        sysUserBO.setSysGroupId(this.sysGroupId);
        sysUserBO.setNeedChangePassword(this.needChangePassword);
        sysUserBO.setChangePasswordDate(this.changePasswordDate);
        sysUserBO.setNewId(this.newId);
        sysUserBO.setStatus(this.status);
        sysUserBO.setPhoneNumber(this.phoneNumber);
        sysUserBO.setEmail(this.email);
        sysUserBO.setEmployeeCode(this.employeeCode);
        sysUserBO.setPassword(this.password);
        sysUserBO.setFullName(this.fullName);
        sysUserBO.setLoginName(this.loginName);
        sysUserBO.setSysUserId(this.sysUserId);
        return sysUserBO;
    }

	@JsonProperty("sysGroupId")
    public java.lang.Double getSysGroupId(){
		return sysGroupId;
    }
	
    public void setSysGroupId(java.lang.Double sysGroupId){
		this.sysGroupId = sysGroupId;
    }	
	
	@JsonProperty("sysGroupName")
    public java.lang.String getSysGroupName(){
		return sysGroupName;
    }
	
    public void setSysGroupName(java.lang.String sysGroupName){
		this.sysGroupName = sysGroupName;
    }	
	
	@JsonProperty("needChangePassword")
    public java.lang.Long getNeedChangePassword(){
		return needChangePassword;
    }
	
    public void setNeedChangePassword(java.lang.Long needChangePassword){
		this.needChangePassword = needChangePassword;
    }	
	
	@JsonProperty("changePasswordDate")
    public java.util.Date getChangePasswordDate(){
		return changePasswordDate;
    }
	
    public void setChangePasswordDate(java.util.Date changePasswordDate){
		this.changePasswordDate = changePasswordDate;
    }	
	
	public java.util.Date getChangePasswordDateFrom() {
    	return changePasswordDateFrom;
    }
	
    public void setChangePasswordDateFrom(java.util.Date changePasswordDateFrom) {
    	this.changePasswordDateFrom = changePasswordDateFrom;
    }
	
	public java.util.Date getChangePasswordDateTo() {
    	return changePasswordDateTo;
    }
	
    public void setChangePasswordDateTo(java.util.Date changePasswordDateTo) {
    	this.changePasswordDateTo = changePasswordDateTo;
    }
	
	@JsonProperty("newId")
    public java.lang.Long getNewId(){
		return newId;
    }
	
    public void setNewId(java.lang.Long newId){
		this.newId = newId;
    }	
	
	@JsonProperty("newName")
    public java.lang.String getNewName(){
		return newName;
    }
	
    public void setNewName(java.lang.String newName){
		this.newName = newName;
    }	
	
	@JsonProperty("status")
    public java.lang.Long getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.Long status){
		this.status = status;
    }	
	
	@JsonProperty("phoneNumber")
    public java.lang.String getPhoneNumber(){
		return phoneNumber;
    }
	
    public void setPhoneNumber(java.lang.String phoneNumber){
		this.phoneNumber = phoneNumber;
    }	
	
	@JsonProperty("email")
    public java.lang.String getEmail(){
		return email;
    }
	
    public void setEmail(java.lang.String email){
		this.email = email;
    }	
	
	@JsonProperty("employeeCode")
    public java.lang.String getEmployeeCode(){
		return employeeCode;
    }
	
    public void setEmployeeCode(java.lang.String employeeCode){
		this.employeeCode = employeeCode;
    }	
	
	@JsonProperty("password")
    public java.lang.String getPassword(){
		return password;
    }
	
    public void setPassword(java.lang.String password){
		this.password = password;
    }	
	
	@JsonProperty("fullName")
    public java.lang.String getFullName(){
		return fullName;
    }
	
    public void setFullName(java.lang.String fullName){
		this.fullName = fullName;
    }	
	
	@JsonProperty("loginName")
    public java.lang.String getLoginName(){
		return loginName;
    }
	
    public void setLoginName(java.lang.String loginName){
		this.loginName = loginName;
    }	
	
	@JsonProperty("sysUserId")
    public java.lang.Long getSysUserId(){
		return sysUserId;
    }
	
    public void setSysUserId(java.lang.Long sysUserId){
		this.sysUserId = sysUserId;
    }	
	
	@JsonProperty("sysUserName")
    public java.lang.String getSysUserName(){
		return sysUserName;
    }
	
    public void setSysUserName(java.lang.String sysUserName){
		this.sysUserName = sysUserName;
    }

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		return this.sysUserId;
	}	
	

}
