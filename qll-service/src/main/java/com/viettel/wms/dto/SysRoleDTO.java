/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.SysRoleBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SYS_ROLEBO")
public class SysRoleDTO extends wmsBaseDTO<SysRoleBO> {

private java.lang.Long sysRoleId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long newId;
private java.lang.Long user;

    @Override
    public SysRoleBO toModel() {
        SysRoleBO sysRoleBO = new SysRoleBO();
        sysRoleBO.setSysRoleId(this.sysRoleId);
        sysRoleBO.setCode(this.code);
        sysRoleBO.setName(this.name);
        sysRoleBO.setDescription(this.description);
        sysRoleBO.setNewId(this.newId);
        return sysRoleBO;
    }

    @Override
     public Long getFWModelId() {
        return sysRoleId;
    }
   
    @Override
    public String catchName() {
        return getSysRoleId().toString();
    }
    
	public java.lang.Long getUser() {
		return user;
	}

	public void setUser(java.lang.Long user) {
		this.user = user;
	}

	public java.lang.Long getSysRoleId(){
    return sysRoleId;
    }
    public void setSysRoleId(java.lang.Long sysRoleId)
    {
    this.sysRoleId = sysRoleId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getNewId(){
    return newId;
    }
    public void setNewId(java.lang.Long newId)
    {
    this.newId = newId;
    }
    
   
}
