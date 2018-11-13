/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.DomainDataDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "VPS_OWNER.DOMAIN_DATA")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class DomainDataBO extends BaseFWModelImpl {
     
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

 public DomainDataBO() {
        setColId("domainTypeId");
        setColName("domainTypeId");
        setUniqueColumn(new String[]{"domainTypeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VPS_OWNER.DOMAIN_DATA_SEQ")
            }
    )
@Column(name = "DOMAIN_DATA_ID", length = 22)
public java.lang.Long getDomainDataId(){
return domainDataId;
}
public void setDomainDataId(java.lang.Long domainDataId)
{
this.domainDataId = domainDataId;
}
@Column(name = "DATA_ID", length = 22)
public java.lang.Long getDataId(){
return dataId;
}
public void setDataId(java.lang.Long dataId)
{
this.dataId = dataId;
}
@Column(name = "DATA_CODE", length = 100)
public java.lang.String getDataCode(){
return dataCode;
}
public void setDataCode(java.lang.String dataCode)
{
this.dataCode = dataCode;
}
@Column(name = "DATA_NAME", length = 400)
public java.lang.String getDataName(){
return dataName;
}
public void setDataName(java.lang.String dataName)
{
this.dataName = dataName;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "PATH", length = 200)
public java.lang.String getPath(){
return path;
}
public void setPath(java.lang.String path)
{
this.path = path;
}
@Column(name = "FULL_PATH", length = 2000)
public java.lang.String getFullPath(){
return fullPath;
}
public void setFullPath(java.lang.String fullPath)
{
this.fullPath = fullPath;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}

@Column(name = "DOMAIN_TYPE_ID", length = 22)
public java.lang.Long getDomainTypeId(){
return domainTypeId;
}
public void setDomainTypeId(java.lang.Long domainTypeId)
{
this.domainTypeId = domainTypeId;
}
@Column(name = "START_DATE", length = 7)
public java.util.Date getStartDate(){
return startDate;
}
public void setStartDate(java.util.Date startDate)
{
this.startDate = startDate;
}
@Column(name = "END_DATE", length = 7)
public java.util.Date getEndDate(){
return endDate;
}
public void setEndDate(java.util.Date endDate)
{
this.endDate = endDate;
}
@Column(name = "PATH_LEVEL", length = 22)
public java.lang.Long getPathLevel(){
return pathLevel;
}
public void setPathLevel(java.lang.Long pathLevel)
{
this.pathLevel = pathLevel;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
   

    @Override
    public DomainDataDTO toDTO() {
        DomainDataDTO domainDataDTO = new DomainDataDTO();
        //set cac gia tri 
        domainDataDTO.setDomainDataId(this.domainDataId);
        domainDataDTO.setDataId(this.dataId);
        domainDataDTO.setDataCode(this.dataCode);
        domainDataDTO.setDataName(this.dataName);
        domainDataDTO.setParentId(this.parentId);
        domainDataDTO.setPath(this.path);
        domainDataDTO.setFullPath(this.fullPath);
        domainDataDTO.setStatus(this.status);
        domainDataDTO.setDomainTypeId(this.domainTypeId);
        domainDataDTO.setStartDate(this.startDate);
        domainDataDTO.setEndDate(this.endDate);
        domainDataDTO.setPathLevel(this.pathLevel);
        domainDataDTO.setIsActive(this.isActive);
        return domainDataDTO;
    }
}
