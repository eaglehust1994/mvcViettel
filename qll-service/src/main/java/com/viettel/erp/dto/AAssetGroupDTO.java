/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.AAssetGroupBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_ASSET_GROUPBO")
public class AAssetGroupDTO extends BaseFWDTOImpl<AAssetGroupBO> {

private java.lang.String isDeleted;
private java.lang.Long aAssetGroupId;
private java.lang.Long adOrgId;
private java.lang.String value;
private java.lang.String name;
private java.lang.String isactive;
private java.lang.Long createdby;
private java.lang.Long updatedby;
private java.util.Date created;
private java.util.Date updated;
private java.lang.String adOrgName;

    @Override
    public AAssetGroupBO toModel() {
        AAssetGroupBO aAssetGroupBO = new AAssetGroupBO();
        aAssetGroupBO.setIsDeleted(this.isDeleted);
        aAssetGroupBO.setAAssetGroupId(this.aAssetGroupId);
        aAssetGroupBO.setAdOrgId(this.adOrgId);
        aAssetGroupBO.setValue(this.value);
        aAssetGroupBO.setName(this.name);
        aAssetGroupBO.setIsactive(this.isactive);
        aAssetGroupBO.setCreatedby(this.createdby);
        aAssetGroupBO.setUpdatedby(this.updatedby);
        aAssetGroupBO.setCreated(this.created);
        aAssetGroupBO.setUpdated(this.updated);
        return aAssetGroupBO;
    }

    public java.lang.String getIsDeleted(){
    return isDeleted;
    }
    public void setIsDeleted(java.lang.String isDeleted)
    {
    this.isDeleted = isDeleted;
    }
    
    @Override
     public Long getFWModelId() {
        return aAssetGroupId;
    }
   
    @Override
    public String catchName() {
        return getAAssetGroupId().toString();
    }
    public java.lang.Long getAAssetGroupId(){
    return aAssetGroupId;
    }
    public void setAAssetGroupId(java.lang.Long aAssetGroupId)
    {
    this.aAssetGroupId = aAssetGroupId;
    }
    
    public java.lang.Long getAdOrgId(){
    return adOrgId;
    }
    public void setAdOrgId(java.lang.Long adOrgId)
    {
    this.adOrgId = adOrgId;
    }
    
    public java.lang.String getValue(){
    return value;
    }
    public void setValue(java.lang.String value)
    {
    this.value = value;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getIsactive(){
    return isactive;
    }
    public void setIsactive(java.lang.String isactive)
    {
    this.isactive = isactive;
    }
    
    public java.lang.Long getCreatedby(){
    return createdby;
    }
    public void setCreatedby(java.lang.Long createdby)
    {
    this.createdby = createdby;
    }
    
    public java.lang.Long getUpdatedby(){
    return updatedby;
    }
    public void setUpdatedby(java.lang.Long updatedby)
    {
    this.updatedby = updatedby;
    }
    
    public java.util.Date getCreated(){
    return created;
    }
    public void setCreated(java.util.Date created)
    {
    this.created = created;
    }
    
    public java.util.Date getUpdated(){
    return updated;
    }
    public void setUpdated(java.util.Date updated)
    {
    this.updated = updated;
    }
    public java.lang.String getAdOrgName() {
		return adOrgName;
	}

	public void setAdOrgName(java.lang.String adOrgName) {
		this.adOrgName = adOrgName;
	}
   
}
