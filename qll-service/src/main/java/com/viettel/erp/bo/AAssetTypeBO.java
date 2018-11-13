/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AAssetTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "A_ASSET_TYPE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AAssetTypeBO extends BaseFWModelImpl {
     
private java.lang.Long aAssetTypeId;
private java.lang.Long adOrgId;
private java.lang.String value;
private java.lang.String name;
private java.lang.String isactive;
private java.lang.Long createdby;

private java.lang.Long updatedby;
private java.util.Date created;
private java.util.Date updated;
private java.lang.String isDeleted;
@Id
@GeneratedValue(generator = "sequence")
@GenericGenerator(name = "sequence", strategy = "sequence",
        parameters = {
            @Parameter(name = "sequence", value = "A_ASSET_TYPE_SEQ")
        }
)
@Column(name = "A_ASSET_TYPE_ID", length = 22)
public java.lang.Long getAAssetTypeId(){
return aAssetTypeId;
}
public void setAAssetTypeId(java.lang.Long aAssetTypeId)
{
this.aAssetTypeId = aAssetTypeId;
}
@Column(name = "AD_ORG_ID", length = 22)
public java.lang.Long getAdOrgId(){
return adOrgId;
}
public void setAdOrgId(java.lang.Long adOrgId)
{
this.adOrgId = adOrgId;
}
@Column(name = "VALUE", length = 40)
public java.lang.String getValue(){
return value;
}
public void setValue(java.lang.String value)
{
this.value = value;
}
@Column(name = "NAME", length = 510)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "ISACTIVE", length = 1)
public java.lang.String getIsactive(){
return isactive;
}
public void setIsactive(java.lang.String isactive)
{
this.isactive = isactive;
}
@Column(name = "CREATEDBY", length = 22)
public java.lang.Long getCreatedby(){
return createdby;
}
public void setCreatedby(java.lang.Long createdby)
{
this.createdby = createdby;
}
@Column(name = "UPDATEDBY", length = 22)
public java.lang.Long getUpdatedby(){
return updatedby;
}
public void setUpdatedby(java.lang.Long updatedby)
{
this.updatedby = updatedby;
}
@Column(name = "CREATED", length = 7)
public java.util.Date getCreated(){
return created;
}
public void setCreated(java.util.Date created)
{
this.created = created;
}
@Column(name = "UPDATED", length = 7)
public java.util.Date getUpdated(){
return updated;
}
public void setUpdated(java.util.Date updated)
{
this.updated = updated;
}
@Column(name = "IS_DELETED", length = 1)
public java.lang.String getIsDeleted(){
return isDeleted;
}
public void setIsDeleted(java.lang.String isDeleted)
{
this.isDeleted = isDeleted;
}


    @Override
    public AAssetTypeDTO toDTO() {
        AAssetTypeDTO aAssetTypeDTO = new AAssetTypeDTO();
        //set cac gia tri 
        aAssetTypeDTO.setAAssetTypeId(this.aAssetTypeId);
        aAssetTypeDTO.setAdOrgId(this.adOrgId);
        aAssetTypeDTO.setValue(this.value);
        aAssetTypeDTO.setName(this.name);
        aAssetTypeDTO.setIsactive(this.isactive);
        aAssetTypeDTO.setCreatedby(this.createdby);
        aAssetTypeDTO.setUpdatedby(this.updatedby);
        aAssetTypeDTO.setCreated(this.created);
        aAssetTypeDTO.setUpdated(this.updated);     
        aAssetTypeDTO.setIsDeleted(this.isDeleted);
        return aAssetTypeDTO;
    }
}
