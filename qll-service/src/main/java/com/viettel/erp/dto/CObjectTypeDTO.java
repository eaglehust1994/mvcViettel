/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CObjectTypeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "C_OBJECT_TYPEBO")
public class CObjectTypeDTO extends BaseFWDTOImpl<CObjectTypeBO> {

private java.lang.Long cObjectTypeId;
private java.lang.Long adClientId;
private java.lang.Long adOrgId;
private java.util.Date created;
private java.lang.Long createdby;
private java.lang.String isDeleted;
private java.lang.String isactive;
private java.lang.String name;
private java.lang.Long type;
private java.util.Date updated;
private java.lang.Long updatedby;
private java.lang.String value;

    @Override
    public CObjectTypeBO toModel() {
        CObjectTypeBO cObjectTypeBO = new CObjectTypeBO();
        cObjectTypeBO.setCObjectTypeId(this.cObjectTypeId);
        cObjectTypeBO.setAdClientId(this.adClientId);
        cObjectTypeBO.setAdOrgId(this.adOrgId);
        cObjectTypeBO.setCreated(this.created);
        cObjectTypeBO.setCreatedby(this.createdby);
        cObjectTypeBO.setIsDeleted(this.isDeleted);
        cObjectTypeBO.setIsactive(this.isactive);
        cObjectTypeBO.setName(this.name);
        cObjectTypeBO.setType(this.type);
        cObjectTypeBO.setUpdated(this.updated);
        cObjectTypeBO.setUpdatedby(this.updatedby);
        cObjectTypeBO.setValue(this.value);
        return cObjectTypeBO;
    }

    @Override
     public Long getFWModelId() {
        return cObjectTypeId;
    }
   
    @Override
    public String catchName() {
        return getCObjectTypeId().toString();
    }
    public java.lang.Long getCObjectTypeId(){
    return cObjectTypeId;
    }
    public void setCObjectTypeId(java.lang.Long cObjectTypeId)
    {
    this.cObjectTypeId = cObjectTypeId;
    }
    
    public java.lang.Long getAdClientId(){
    return adClientId;
    }
    public void setAdClientId(java.lang.Long adClientId)
    {
    this.adClientId = adClientId;
    }
    
    public java.lang.Long getAdOrgId(){
    return adOrgId;
    }
    public void setAdOrgId(java.lang.Long adOrgId)
    {
    this.adOrgId = adOrgId;
    }
    
    public java.util.Date getCreated(){
    return created;
    }
    public void setCreated(java.util.Date created)
    {
    this.created = created;
    }
    
    public java.lang.Long getCreatedby(){
    return createdby;
    }
    public void setCreatedby(java.lang.Long createdby)
    {
    this.createdby = createdby;
    }
    
    public java.lang.String getIsDeleted(){
    return isDeleted;
    }
    public void setIsDeleted(java.lang.String isDeleted)
    {
    this.isDeleted = isDeleted;
    }
    
    public java.lang.String getIsactive(){
    return isactive;
    }
    public void setIsactive(java.lang.String isactive)
    {
    this.isactive = isactive;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.util.Date getUpdated(){
    return updated;
    }
    public void setUpdated(java.util.Date updated)
    {
    this.updated = updated;
    }
    
    public java.lang.Long getUpdatedby(){
    return updatedby;
    }
    public void setUpdatedby(java.lang.Long updatedby)
    {
    this.updatedby = updatedby;
    }
    
    public java.lang.String getValue(){
    return value;
    }
    public void setValue(java.lang.String value)
    {
    this.value = value;
    }
    
   
}
