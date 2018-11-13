/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ObjectReferenceHistoryDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.OBJECT_REFERENCE_HISTORY")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ObjectReferenceHistoryBO extends BaseFWModelImpl {
     
private java.lang.Long objectReferenceHistoryId;
private java.lang.Long parentId;
private java.lang.String parentCode;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.Long objectReferenceId;

 public ObjectReferenceHistoryBO() {
        setColId("objectReferenceId");
        setColName("objectReferenceId");
        setUniqueColumn(new String[]{"objectReferenceId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.OBJECT_REFERENCE_HISTORY_SEQ")
            }
    )
@Column(name = "OBJECT_REFERENCE_HISTORY_ID", length = 22)
public java.lang.Long getObjectReferenceHistoryId(){
return objectReferenceHistoryId;
}
public void setObjectReferenceHistoryId(java.lang.Long objectReferenceHistoryId)
{
this.objectReferenceHistoryId = objectReferenceHistoryId;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "PARENT_CODE", length = 10)
public java.lang.String getParentCode(){
return parentCode;
}
public void setParentCode(java.lang.String parentCode)
{
this.parentCode = parentCode;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.OBJECT_REFERENCE_HISTORY_SEQ")
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
   

    @Override
    public ObjectReferenceHistoryDTO toDTO() {
        ObjectReferenceHistoryDTO objectReferenceHistoryDTO = new ObjectReferenceHistoryDTO();
        //set cac gia tri 
        objectReferenceHistoryDTO.setObjectReferenceHistoryId(this.objectReferenceHistoryId);
        objectReferenceHistoryDTO.setParentId(this.parentId);
        objectReferenceHistoryDTO.setParentCode(this.parentCode);
        objectReferenceHistoryDTO.setCreatedDate(this.createdDate);
        objectReferenceHistoryDTO.setCreatedBy(this.createdBy);
        objectReferenceHistoryDTO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceHistoryDTO;
    }
}
