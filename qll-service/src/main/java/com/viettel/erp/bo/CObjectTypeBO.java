/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CObjectTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "C_OBJECT_TYPE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CObjectTypeBO extends BaseFWModelImpl {
     
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

 public CObjectTypeBO() {
        setColId("cObjectTypeId");
        setColName("cObjectTypeId");
        setUniqueColumn(new String[]{"cObjectTypeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "C_OBJECT_TYPE_SEQ")
            }
    )
@Column(name = "C_OBJECT_TYPE_ID", length = 22)
public java.lang.Long getCObjectTypeId(){
return cObjectTypeId;
}
public void setCObjectTypeId(java.lang.Long cObjectTypeId)
{
this.cObjectTypeId = cObjectTypeId;
}
@Column(name = "AD_CLIENT_ID", length = 22)
public java.lang.Long getAdClientId(){
return adClientId;
}
public void setAdClientId(java.lang.Long adClientId)
{
this.adClientId = adClientId;
}
@Column(name = "AD_ORG_ID", length = 22)
public java.lang.Long getAdOrgId(){
return adOrgId;
}
public void setAdOrgId(java.lang.Long adOrgId)
{
this.adOrgId = adOrgId;
}
@Column(name = "CREATED", length = 7)
public java.util.Date getCreated(){
return created;
}
public void setCreated(java.util.Date created)
{
this.created = created;
}
@Column(name = "CREATEDBY", length = 22)
public java.lang.Long getCreatedby(){
return createdby;
}
public void setCreatedby(java.lang.Long createdby)
{
this.createdby = createdby;
}
@Column(name = "IS_DELETED", length = 1)
public java.lang.String getIsDeleted(){
return isDeleted;
}
public void setIsDeleted(java.lang.String isDeleted)
{
this.isDeleted = isDeleted;
}
@Column(name = "ISACTIVE", length = 1)
public java.lang.String getIsactive(){
return isactive;
}
public void setIsactive(java.lang.String isactive)
{
this.isactive = isactive;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "UPDATED", length = 7)
public java.util.Date getUpdated(){
return updated;
}
public void setUpdated(java.util.Date updated)
{
this.updated = updated;
}
@Column(name = "UPDATEDBY", length = 22)
public java.lang.Long getUpdatedby(){
return updatedby;
}
public void setUpdatedby(java.lang.Long updatedby)
{
this.updatedby = updatedby;
}
@Column(name = "VALUE", length = 80)
public java.lang.String getValue(){
return value;
}
public void setValue(java.lang.String value)
{
this.value = value;
}
   

    @Override
    public CObjectTypeDTO toDTO() {
        CObjectTypeDTO cObjectTypeDTO = new CObjectTypeDTO();
        //set cac gia tri 
        cObjectTypeDTO.setCObjectTypeId(this.cObjectTypeId);
        cObjectTypeDTO.setAdClientId(this.adClientId);
        cObjectTypeDTO.setAdOrgId(this.adOrgId);
        cObjectTypeDTO.setCreated(this.created);
        cObjectTypeDTO.setCreatedby(this.createdby);
        cObjectTypeDTO.setIsDeleted(this.isDeleted);
        cObjectTypeDTO.setIsactive(this.isactive);
        cObjectTypeDTO.setName(this.name);
        cObjectTypeDTO.setType(this.type);
        cObjectTypeDTO.setUpdated(this.updated);
        cObjectTypeDTO.setUpdatedby(this.updatedby);
        cObjectTypeDTO.setValue(this.value);
        return cObjectTypeDTO;
    }
}
