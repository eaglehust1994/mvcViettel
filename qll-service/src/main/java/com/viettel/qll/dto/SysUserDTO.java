package com.viettel.qll.dto;

import com.viettel.qll.bo.SysUserBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
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
@XmlRootElement(name = "SYS_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserDTO extends BaseFWDTOImpl<SysUserBO> {

	private java.lang.Long sysUserId;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date changePasswordDateTo;
	private java.lang.String email;
	private java.lang.String employeeCode;
	private java.lang.String fullName;
	private java.lang.String loginName;
	private java.lang.Long needChangePassword;
	private java.lang.Long newId;
	private java.lang.String newName;
	private java.lang.String password;
	private java.lang.String phoneNumber;
	private java.lang.Long status;
	private java.lang.Long sysGroupId;
	private java.lang.Long departmentId;
	private java.lang.String sysGroupName;
	private String departmentName;
	private String username;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public SysUserBO toModel() {
        SysUserBO sysUserBO = new SysUserBO();
        sysUserBO.setSysUserId(this.sysUserId);
        sysUserBO.setChangePasswordDate(this.changePasswordDate);
        sysUserBO.setEmail(this.email);
        sysUserBO.setEmployeeCode(this.employeeCode);
        sysUserBO.setFullName(this.fullName);
        sysUserBO.setLoginName(this.loginName);
        sysUserBO.setNeedChangePassword(this.needChangePassword);
        sysUserBO.setNewId(this.newId);
        sysUserBO.setPassword(this.password);
        sysUserBO.setPhoneNumber(this.phoneNumber);
        sysUserBO.setStatus(this.status);
        sysUserBO.setSysGroupId(this.sysGroupId);
        return sysUserBO;
    }

    @Override
     public Long getFWModelId() {
        return sysUserId;
    }
   
    @Override
    public String catchName() {
        return getSysUserId().toString();
    }
	
	@JsonProperty("sysUserId")
    public java.lang.Long getSysUserId(){
		return sysUserId;
    }
	
    public void setSysUserId(java.lang.Long sysUserId){
		this.sysUserId = sysUserId;
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
	
	@JsonProperty("needChangePassword")
    public java.lang.Long getNeedChangePassword(){
		return needChangePassword;
    }
	
    public void setNeedChangePassword(java.lang.Long needChangePassword){
		this.needChangePassword = needChangePassword;
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
	
	@JsonProperty("password")
    public java.lang.String getPassword(){
		return password;
    }
	
    public void setPassword(java.lang.String password){
		this.password = password;
    }	
	
	@JsonProperty("phoneNumber")
    public java.lang.String getPhoneNumber(){
		return phoneNumber;
    }
	
    public void setPhoneNumber(java.lang.String phoneNumber){
		this.phoneNumber = phoneNumber;
    }	
	
	@JsonProperty("status")
    public java.lang.Long getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.Long status){
		this.status = status;
    }	
	
	@JsonProperty("sysGroupId")
    public java.lang.Long getSysGroupId(){
		return sysGroupId;
    }
	
    public void setSysGroupId(java.lang.Long sysGroupId){
		this.sysGroupId = sysGroupId;
    }	
	
	@JsonProperty("sysGroupName")
    public java.lang.String getSysGroupName(){
		return sysGroupName;
    }
	
    public void setSysGroupName(java.lang.String sysGroupName){
		this.sysGroupName = sysGroupName;
    }	
	
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	

	
	public void setText(String text) {
		this.text = text;
	}

	public java.lang.Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(java.lang.Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
