/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ATTACHMENT")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class AttachmentBO extends BaseFWModelImpl {
     
private java.lang.Long attactmentId;
private java.lang.Long objectId;
private java.lang.String type;
private java.lang.String appParamCode;
private java.lang.String code;
private java.lang.String name;
private java.lang.String encrytName;
private java.lang.String description;
private java.lang.String status;
private java.lang.String filePath;

 public AttachmentBO() {
        setColId("attactmentId");
        setColName("attactmentId");
        setUniqueColumn(new String[]{"attactmentId"});
}
 @Column(name = "FILEPATH", length = 1000)
public java.lang.String getFilePath() {
	return filePath;
}

public void setFilePath(java.lang.String filePath) {
	this.filePath = filePath;
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ATTACHMENT_SEQ")
            }
    )
@Column(name = "ATTACTMENT_ID", length = 22)
public java.lang.Long getAttactmentId(){
return attactmentId;
}
public void setAttactmentId(java.lang.Long attactmentId)
{
this.attactmentId = attactmentId;
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
@Column(name = "APP_PARAM_CODE", length = 400)
public java.lang.String getAppParamCode(){
return appParamCode;
}
public void setAppParamCode(java.lang.String appParamCode)
{
this.appParamCode = appParamCode;
}
@Column(name = "CODE", length = 200)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "NAME", length = 2000)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "ENCRYT_NAME", length = 400)
public java.lang.String getEncrytName(){
return encrytName;
}
public void setEncrytName(java.lang.String encrytName)
{
this.encrytName = encrytName;
}
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
   

    @Override
    public AttachmentDTO toDTO() {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        //set cac gia tri 
        attachmentDTO.setAttactmentId(this.attactmentId);
        attachmentDTO.setObjectId(this.objectId);
        attachmentDTO.setType(this.type);
        attachmentDTO.setAppParamCode(this.appParamCode);
        attachmentDTO.setCode(this.code);
        attachmentDTO.setName(this.name);
        attachmentDTO.setEncrytName(this.encrytName);
        attachmentDTO.setDescription(this.description);
        attachmentDTO.setStatus(this.status);
        attachmentDTO.setFilePath(this.filePath);
        return attachmentDTO;
    }
}
