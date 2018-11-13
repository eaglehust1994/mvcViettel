/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ConfigSignVofficeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.CONFIG_SIGN_VOFFICE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ConfigSignVofficeBO extends BaseFWModelImpl {
     
private java.lang.Long configSignVofficeId;
private java.lang.Long sysUserId;
private java.lang.Long stockId;
private java.lang.String bussTypeId;
private java.lang.Long oder;
private java.lang.String oderName;
private java.lang.Long sysRoleId;
private java.lang.String sysRoleName;
private java.lang.Long VofficeUserId;
private java.lang.Long VofficeAdOrgId;
private java.lang.Long bussinessTypeConfigId;


@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.CONFIG_SIGN_VOFFICE_SEQ")
            }
    )
@Column(name = "CONFIG_SIGN_VOFFICE_ID", length = 22)
public java.lang.Long getConfigSignVofficeId(){
return configSignVofficeId;
}
public void setConfigSignVofficeId(java.lang.Long configSignVofficeId)
{
this.configSignVofficeId = configSignVofficeId;
}
@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Long getSysUserId(){
return sysUserId;
}
public void setSysUserId(java.lang.Long sysUserId)
{
this.sysUserId = sysUserId;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "BUSS_TYPE_ID", length = 4)
public java.lang.String getBussTypeId() {
	return bussTypeId;
}

public void setBussTypeId(java.lang.String bussTypeId) {
	this.bussTypeId = bussTypeId;
}
@Column(name = "ODER", length = 22)
public java.lang.Long getOder(){
return oder;
}
public void setOder(java.lang.Long oder)
{
this.oder = oder;
}
@Column(name = "ODER_NAME", length = 200)
public java.lang.String getOderName(){
return oderName;
}
public void setOderName(java.lang.String oderName)
{
this.oderName = oderName;
}
@Column(name = "ROLE_ID", length = 22)
public java.lang.Long getSysRoleId() {
	return sysRoleId;
}
public void setSysRoleId(java.lang.Long sysRoleId) {
	this.sysRoleId = sysRoleId;
}
@Column(name = "ROLE_NAME", length = 200)
public java.lang.String getSysRoleName() {
	return sysRoleName;
}
public void setSysRoleName(java.lang.String sysRoleName) {
	this.sysRoleName = sysRoleName;
}
    @Override
    public ConfigSignVofficeDTO toDTO() {
        ConfigSignVofficeDTO configSignVofficeDTO = new ConfigSignVofficeDTO();
        //set cac gia tri 
        configSignVofficeDTO.setConfigSignVofficeId(this.configSignVofficeId);
        configSignVofficeDTO.setSysUserId(this.sysUserId);
        configSignVofficeDTO.setStockId(this.stockId);
        configSignVofficeDTO.setBussTypeId(this.bussTypeId);
        configSignVofficeDTO.setOder(this.oder);
        configSignVofficeDTO.setOderName(this.oderName);
        configSignVofficeDTO.setSysRoleId(this.sysRoleId);
        configSignVofficeDTO.setSysRoleName(this.sysRoleName);
        configSignVofficeDTO.setVofficeUserId(this.VofficeUserId);
        configSignVofficeDTO.setVofficeAdOrgId(this.VofficeAdOrgId);
        configSignVofficeDTO.setBussinessTypeConfigId(this.bussinessTypeConfigId);
        return configSignVofficeDTO;
    }
    
	@Column(name = "VOFFICE_USER_ID", length = 22)
    public java.lang.Long getVofficeUserId() {
    	return VofficeUserId;
    }
	public void setVofficeUserId(Long vofficeUserId) {
		this.VofficeUserId = vofficeUserId;		
	}
	
	@Column(name = "VOFFICE_ADORGID", length = 22)
	public java.lang.Long getVofficeAdOrgId() {
		return VofficeAdOrgId;
	}
	
	public void setVofficeAdOrgId(Long vofficeAdOrgId) {
		this.VofficeAdOrgId = vofficeAdOrgId;
	}
	
	@Column(name = "BUSSINESS_TYPE_CONFIG_ID", length = 22)
	public java.lang.Long getBussinessTypeConfigId() {
		return bussinessTypeConfigId;
	}

	public void setBussinessTypeConfigId(java.lang.Long bussinessTypeConfigId) {
		this.bussinessTypeConfigId = bussinessTypeConfigId;
	}

	public ConfigSignVofficeBO() {
	        setColId("configSignVofficeId");
	        setColName("configSignVofficeId");
	        setUniqueColumn(new String[]{"configSignVofficeId"});
	}
}
