/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.viettel.wms.bo.ConfigSignVofficeBO;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CONFIG_SIGN_VOFFICEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigSignVofficeDTO extends wmsBaseDTO<ConfigSignVofficeBO> {

private java.lang.Long configSignVofficeId;
private java.lang.Long sysUserId;
private java.lang.Long stockId;
private java.lang.String bussTypeId;
private java.lang.Long oder;
private java.lang.String oderName;
private java.lang.Long sysRoleId;
private java.lang.String sysRoleName;
private java.lang.String fullName;
private java.lang.String email;
private java.lang.Long VofficeUserId;
private java.lang.Long VofficeAdOrgId;
private java.lang.Long bussinessTypeConfigId;
private List<Long> listStockId;
private List<ConfigSignVofficeDTO> listSignVoffice= Lists.newArrayList();

    public java.lang.Long getBussinessTypeConfigId() {
	return bussinessTypeConfigId;
}

public void setBussinessTypeConfigId(java.lang.Long bussinessTypeConfigId) {
	this.bussinessTypeConfigId = bussinessTypeConfigId;
}

	@Override
    public ConfigSignVofficeBO toModel() {
        ConfigSignVofficeBO configSignVofficeBO = new ConfigSignVofficeBO();
        configSignVofficeBO.setConfigSignVofficeId(this.configSignVofficeId);
        configSignVofficeBO.setSysUserId(this.sysUserId);
        configSignVofficeBO.setStockId(this.stockId);
        configSignVofficeBO.setBussTypeId(this.bussTypeId);
        configSignVofficeBO.setOder(this.oder);
        configSignVofficeBO.setOderName(this.oderName);
        configSignVofficeBO.setSysRoleId(this.sysRoleId);
        configSignVofficeBO.setSysRoleName(this.sysRoleName);
        configSignVofficeBO.setVofficeUserId(this.VofficeUserId);
        configSignVofficeBO.setVofficeAdOrgId(this.VofficeAdOrgId);
        configSignVofficeBO.setBussinessTypeConfigId(this.bussinessTypeConfigId);
        return configSignVofficeBO;
    }

    @Override
     public Long getFWModelId() {
        return configSignVofficeId;
    }
   
    @Override
    public String catchName() {
        return getConfigSignVofficeId().toString();
    }
    public java.lang.Long getConfigSignVofficeId(){
    return configSignVofficeId;
    }
    public void setConfigSignVofficeId(java.lang.Long configSignVofficeId)
    {
    this.configSignVofficeId = configSignVofficeId;
    }
    
    public java.lang.Long getSysUserId(){
    return sysUserId;
    }
    public void setSysUserId(java.lang.Long sysUserId)
    {
    this.sysUserId = sysUserId;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.String getBussTypeId() {
		return bussTypeId;
	}

	public void setBussTypeId(java.lang.String bussTypeId) {
		this.bussTypeId = bussTypeId;
	}

	public java.lang.Long getOder(){
    return oder;
    }
    public void setOder(java.lang.Long oder)
    {
    this.oder = oder;
    }
    
    public java.lang.String getOderName(){
    return oderName;
    }
    public void setOderName(java.lang.String oderName)
    {
    this.oderName = oderName;
    }
	
	public java.lang.Long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(java.lang.Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public java.lang.String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(java.lang.String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.Long getVofficeUserId() {
		return VofficeUserId;
	}

	public void setVofficeUserId(java.lang.Long vofficeUserId) {
		VofficeUserId = vofficeUserId;
	}

	public java.lang.Long getVofficeAdOrgId() {
		return VofficeAdOrgId;
	}

	public void setVofficeAdOrgId(java.lang.Long vofficeAdOrgId) {
		VofficeAdOrgId = vofficeAdOrgId;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}

	public List<ConfigSignVofficeDTO> getListSignVoffice() {
		return listSignVoffice;
	}

	public void setListSignVoffice(List<ConfigSignVofficeDTO> listSignVoffice) {
		this.listSignVoffice = listSignVoffice;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}
     
}
