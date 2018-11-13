/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheSignCADTO  {

    private String code;
    private String constructId;
    private String constrCompReMapId;
    private String stringEmployee;
    private String isSign;
    private String path;
    private String nameFile;
    private List<String> roleId;
    private List<String> roleName;
    private Long userCurentId;
    private String flag;
    
    private String user;
    private String password;
    private Long userId;
    private Long groupId;
    
	public TheSignCADTO() {
		
	}
	
	public Long getUserCurentId() {
		return userCurentId;
	}

	public void setUserCurentId(Long userCurentId) {
		this.userCurentId = userCurentId;
	}

	public Long getGroupId() {
		if(groupId == null){
			return groupId = 0l;
		}else{
		return groupId;}
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getConstructId() {
		return constructId;
	}
	public void setConstructId(String constructId) {
		this.constructId = constructId;
	}
	public String getConstrCompReMapId() {
		return constrCompReMapId;
	}
	public void setConstrCompReMapId(String constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}
	public String getIsSign() {
		return isSign;
	}
	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	public String getStringEmployee() {
		return stringEmployee;
	}
	public void setStringEmployee(String stringEmployee) {
		this.stringEmployee = stringEmployee;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public List<String> getRoleId() {
		return roleId;
	}
	public void setRoleId(List<String> roleId) {
		this.roleId = roleId;
	}
	public List<String> getRoleName() {
		return roleName;
	}
	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
