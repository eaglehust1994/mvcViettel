/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ObjectReferenceHistoryBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "OBJECT_REFERENCE_HISTORYBO")
public class ObjectReferenceHistoryDTO extends BaseFWDTOImpl<ObjectReferenceHistoryBO> {

private java.lang.Long objectReferenceHistoryId;
private java.lang.Long parentId;
private java.lang.String parentCode;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.Long objectReferenceId;

    @Override
    public ObjectReferenceHistoryBO toModel() {
        ObjectReferenceHistoryBO objectReferenceHistoryBO = new ObjectReferenceHistoryBO();
        objectReferenceHistoryBO.setObjectReferenceHistoryId(this.objectReferenceHistoryId);
        objectReferenceHistoryBO.setParentId(this.parentId);
        objectReferenceHistoryBO.setParentCode(this.parentCode);
        objectReferenceHistoryBO.setCreatedDate(this.createdDate);
        objectReferenceHistoryBO.setCreatedBy(this.createdBy);
        objectReferenceHistoryBO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceHistoryBO;
    }

    public java.lang.Long getObjectReferenceHistoryId(){
    return objectReferenceHistoryId;
    }
    public void setObjectReferenceHistoryId(java.lang.Long objectReferenceHistoryId)
    {
    this.objectReferenceHistoryId = objectReferenceHistoryId;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.String getParentCode(){
    return parentCode;
    }
    public void setParentCode(java.lang.String parentCode)
    {
    this.parentCode = parentCode;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.lang.Long getObjectReferenceId(){
    return objectReferenceId;
    }
    public void setObjectReferenceId(java.lang.Long objectReferenceId)
    {
    this.objectReferenceId = objectReferenceId;
    }
    @Override
    public Long getFWModelId() {
       return objectReferenceId;
   }
  
   @Override
   public String catchName() {
       return getObjectReferenceId().toString();
   }
   
}
