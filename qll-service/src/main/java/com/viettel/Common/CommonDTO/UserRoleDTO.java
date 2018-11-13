/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.Common.CommonDTO;

import com.viettel.Common.CommonBO.UserRoleBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "USER_ROLEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleDTO extends imsBaseDTO<UserRoleBO> {

private java.lang.Long isDefault;
private java.lang.Long domainDataId;
private java.lang.Long sysRoleId;
private java.lang.Long sysUserId;
private java.lang.Long userRoleId;
private java.lang.Long isActive;
private java.lang.String user;
private java.lang.String code;
private java.lang.String name;
private java.lang.String description;
private java.lang.String loginName;
private java.lang.String dataCode;
private java.lang.String dataName;

    @Override
    public UserRoleBO toModel() {
        UserRoleBO userRoleBO = new UserRoleBO();
        userRoleBO.setSysRoleId(this.sysRoleId);
        userRoleBO.setSysUserId(this.sysUserId);
        userRoleBO.setUserRoleId(this.userRoleId);
        userRoleBO.setIsActive(this.isActive);
        return userRoleBO;
    }

    
	public java.lang.Long getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(java.lang.Long isDefault) {
		this.isDefault = isDefault;
	}


	public java.lang.Long getDomainDataId() {
		return domainDataId;
	}


	public void setDomainDataId(java.lang.Long domainDataId) {
		this.domainDataId = domainDataId;
	}


	public java.lang.String getDataCode() {
		return dataCode;
	}


	public void setDataCode(java.lang.String dataCode) {
		this.dataCode = dataCode;
	}


	public java.lang.String getDataName() {
		return dataName;
	}


	public void setDataName(java.lang.String dataName) {
		this.dataName = dataName;
	}


	public java.lang.String getLoginName() {
		return loginName;
	}



	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}



	public java.lang.String getCode() {
		return code;
	}



	public void setCode(java.lang.String code) {
		this.code = code;
	}



	public java.lang.String getName() {
		return name;
	}



	public void setName(java.lang.String name) {
		this.name = name;
	}



	public java.lang.String getDescription() {
		return description;
	}



	public void setDescription(java.lang.String description) {
		this.description = description;
	}



	public java.lang.String getUser() {
		return user;
	}



	public void setUser(java.lang.String user) {
		this.user = user;
	}



	public java.lang.Long getSysRoleId(){
    return sysRoleId;
    }
    public void setSysRoleId(java.lang.Long sysRoleId)
    {
    this.sysRoleId = sysRoleId;
    }
    
    public java.lang.Long getSysUserId(){
    return sysUserId;
    }
    public void setSysUserId(java.lang.Long sysUserId)
    {
    this.sysUserId = sysUserId;
    }
    

    public java.lang.Long getUserRoleId(){
    return userRoleId;
    }
    public void setUserRoleId(java.lang.Long userRoleId)
    {
    this.userRoleId = userRoleId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    @Override
    public Long getFWModelId() {
       return userRoleId;
   }
  
   @Override
   public String catchName() {
       return getUserRoleId().toString();
   }
   
}
