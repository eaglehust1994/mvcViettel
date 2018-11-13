/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.DomainDataBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "DOMAIN_DATABO")
public class DomainDataDTO extends wmsBaseDTO<DomainDataBO> {

private java.lang.Long domainDataId;
private java.lang.Long dataId;
private java.lang.String dataCode;
private java.lang.String dataName;
private java.lang.Long parentId;
private java.lang.String path;
private java.lang.String fullPath;
private java.lang.Long status;
private java.lang.Long domainTypeId;
private java.util.Date startDate;
private java.util.Date endDate;
private java.lang.Long pathLevel;
private java.lang.Long isActive;


    public java.lang.Long getDomainDataId() {
	return domainDataId;
}


public void setDomainDataId(java.lang.Long domainDataId) {
	this.domainDataId = domainDataId;
}


public java.lang.Long getDataId() {
	return dataId;
}


public void setDataId(java.lang.Long dataId) {
	this.dataId = dataId;
}


public java.lang.String getDataCode() {
	return dataCode;
}


public void setDataCode(java.lang.String dataCode) {
	this.dataCode = dataCode;
}


public java.lang.String getDataName() {
	return dataName;
}


public void setDataName(java.lang.String dataName) {
	this.dataName = dataName;
}


public java.lang.Long getParentId() {
	return parentId;
}


public void setParentId(java.lang.Long parentId) {
	this.parentId = parentId;
}


public java.lang.String getPath() {
	return path;
}


public void setPath(java.lang.String path) {
	this.path = path;
}


public java.lang.String getFullPath() {
	return fullPath;
}


public void setFullPath(java.lang.String fullPath) {
	this.fullPath = fullPath;
}


public java.lang.Long getStatus() {
	return status;
}


public void setStatus(java.lang.Long status) {
	this.status = status;
}


public java.lang.Long getDomainTypeId() {
	return domainTypeId;
}


public void setDomainTypeId(java.lang.Long domainTypeId) {
	this.domainTypeId = domainTypeId;
}


public java.util.Date getStartDate() {
	return startDate;
}


public void setStartDate(java.util.Date startDate) {
	this.startDate = startDate;
}


public java.util.Date getEndDate() {
	return endDate;
}


public void setEndDate(java.util.Date endDate) {
	this.endDate = endDate;
}


public java.lang.Long getPathLevel() {
	return pathLevel;
}


public void setPathLevel(java.lang.Long pathLevel) {
	this.pathLevel = pathLevel;
}


public java.lang.Long getIsActive() {
	return isActive;
}


public void setIsActive(java.lang.Long isActive) {
	this.isActive = isActive;
}


	@Override
    public DomainDataBO toModel() {
        DomainDataBO domainDataBO = new DomainDataBO();
        domainDataBO.setDomainDataId(this.domainDataId);
        domainDataBO.setDataId(this.dataId);
        domainDataBO.setDataCode(this.dataCode);
        domainDataBO.setDataName(this.dataName);
        domainDataBO.setParentId(this.parentId);
        domainDataBO.setPath(this.path);
        domainDataBO.setFullPath(this.fullPath);
        domainDataBO.setStatus(this.status);
        domainDataBO.setDomainTypeId(this.domainTypeId);
        domainDataBO.setStartDate(this.startDate);
        domainDataBO.setEndDate(this.endDate);
        domainDataBO.setPathLevel(this.pathLevel);
        domainDataBO.setIsActive(this.isActive);
        return domainDataBO;
    }


	@Override
	public String catchName() {
		return getDomainDataId().toString();
	}


	@Override
	public Long getFWModelId() {
		return domainDataId;

	}

}
