/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.OBJECT_REFERENCE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ObjectReferenceBO extends BaseFWModelImpl {
     
private java.lang.Long objectReferenceId;
private java.lang.Long parentId;
private java.lang.Long objectId;
private java.lang.String type;
private java.lang.String objectCode;
private java.lang.String objectName;
private java.lang.String objectDescription;
private java.lang.String objectStatus;
private java.lang.String objectCreatedDate;
private java.lang.String objectCreator;

 public ObjectReferenceBO() {
        setColId("objectReferenceId");
        setColName("objectReferenceId");
        setUniqueColumn(new String[]{"objectReferenceId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.OBJECT_REFERENCE_SEQ")
            }
    )
@Column(name = "OBJECT_REFERENCE_ID", length = 22)
public java.lang.Long getObjectReferenceId(){
return objectReferenceId;
}
public void setObjectReferenceId(java.lang.Long objectReferenceId)
{
this.objectReferenceId = objectReferenceId;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "OBJECT_ID", length = 22)
public java.lang.Long getObjectId(){
return objectId;
}
public void setObjectId(java.lang.Long objectId)
{
this.objectId = objectId;
}
@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "OBJECT_CODE", length = 200)
public java.lang.String getObjectCode(){
return objectCode;
}
public void setObjectCode(java.lang.String objectCode)
{
this.objectCode = objectCode;
}
@Column(name = "OBJECT_NAME", length = 2000)
public java.lang.String getObjectName(){
return objectName;
}
public void setObjectName(java.lang.String objectName)
{
this.objectName = objectName;
}
@Column(name = "OBJECT_DESCRIPTION", length = 2000)
public java.lang.String getObjectDescription(){
return objectDescription;
}
public void setObjectDescription(java.lang.String objectDescription)
{
this.objectDescription = objectDescription;
}
@Column(name = "OBJECT_STATUS", length = 4)
public java.lang.String getObjectStatus(){
return objectStatus;
}
public void setObjectStatus(java.lang.String objectStatus)
{
this.objectStatus = objectStatus;
}
@Column(name = "OBJECT_CREATED_DATE", length = 20)
public java.lang.String getObjectCreatedDate(){
return objectCreatedDate;
}
public void setObjectCreatedDate(java.lang.String objectCreatedDate)
{
this.objectCreatedDate = objectCreatedDate;
}
@Column(name = "OBJECT_CREATOR", length = 200)
public java.lang.String getObjectCreator(){
return objectCreator;
}
public void setObjectCreator(java.lang.String objectCreator)
{
this.objectCreator = objectCreator;
}
   

    @Override
    public ObjectReferenceDTO toDTO() {
        ObjectReferenceDTO objectReferenceDTO = new ObjectReferenceDTO();
        //set cac gia tri 
        objectReferenceDTO.setObjectReferenceId(this.objectReferenceId);
        objectReferenceDTO.setParentId(this.parentId);
        objectReferenceDTO.setObjectId(this.objectId);
        objectReferenceDTO.setType(this.type);
        objectReferenceDTO.setObjectCode(this.objectCode);
        objectReferenceDTO.setObjectName(this.objectName);
        objectReferenceDTO.setObjectDescription(this.objectDescription);
        objectReferenceDTO.setObjectStatus(this.objectStatus);
        objectReferenceDTO.setObjectCreatedDate(this.objectCreatedDate);
        objectReferenceDTO.setObjectCreator(this.objectCreator);
        return objectReferenceDTO;
    }
}
