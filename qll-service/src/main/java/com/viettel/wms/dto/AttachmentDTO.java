/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.AttachmentBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ATTACHMENTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDTO extends wmsBaseDTO<AttachmentBO> {

private java.lang.Long attactmentId;
private java.lang.Long objectId;
private java.lang.String type;
private java.lang.String appParamCode;
private java.lang.String code;
private java.lang.String name;
private java.lang.String encrytName;
private java.lang.String description;
private java.lang.String status;
private String appParamName; 
private String filePath; 


    public String getFilePath() {
	return filePath;
}

public void setFilePath(String filePath) {
	this.filePath = filePath;
}

	public String getAppParamName() {
	return appParamName;
}

public void setAppParamName(String appParamName) {
//	this.appParam.setName(appParamName);
	this.appParamName = appParamName;
}

	@Override
    public AttachmentBO toModel() {
        AttachmentBO attachmentBO = new AttachmentBO();
        attachmentBO.setAttactmentId(this.attactmentId);
        attachmentBO.setObjectId(this.objectId);
        attachmentBO.setType(this.type);
        attachmentBO.setAppParamCode(this.appParamCode);
        attachmentBO.setCode(this.code);
        attachmentBO.setName(this.name);
        attachmentBO.setEncrytName(this.encrytName);
        attachmentBO.setDescription(this.description);
        attachmentBO.setStatus(this.status);
        attachmentBO.setFilePath(this.filePath);
        return attachmentBO;
    }

    @Override
     public Long getFWModelId() {
        return attactmentId;
    }
   
    @Override
    public String catchName() {
        return getAttactmentId().toString();
    }
    public java.lang.Long getAttactmentId(){
    return attactmentId;
    }
    public void setAttactmentId(java.lang.Long attactmentId)
    {
    this.attactmentId = attactmentId;
    }
    
    public java.lang.Long getObjectId(){
    return objectId;
    }
    public void setObjectId(java.lang.Long objectId)
    {
    this.objectId = objectId;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.String getAppParamCode(){
    return appParamCode;
    }
    public void setAppParamCode(java.lang.String appParamCode)
    {
//    	this.appParam.setCode(appParamCode);
    this.appParamCode = appParamCode;
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
    
    public java.lang.String getEncrytName(){
    return encrytName;
    }
    public void setEncrytName(java.lang.String encrytName)
    {
    this.encrytName = encrytName;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
//    private AppParamDTO appParam = new AppParamDTO();
//
//	public AppParamDTO getAppParam() {
//		return appParam;
//	}
//
//	public void setAppParam(AppParamDTO appParam) {
//		this.appParam = appParam;
//	}
   

    
}
