/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ReasonDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.REASON")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ReasonBO extends BaseFWModelImpl {
     
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.Long reasonId;
private java.lang.String code;
private java.lang.String apply;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.String status;
private java.lang.String name;

 public ReasonBO() {
        setColId("reasonId");
        setColName("reasonId");
        setUniqueColumn(new String[]{"reasonId"});
}

@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
}
@Column(name = "UPDATED_DATE", length = 7)
public java.util.Date getUpdatedDate(){
return updatedDate;
}
public void setUpdatedDate(java.util.Date updatedDate)
{
this.updatedDate = updatedDate;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.REASON_SEQ")
            }
    )
@Column(name = "REASON_ID", length = 22)
public java.lang.Long getReasonId(){
return reasonId;
}
public void setReasonId(java.lang.Long reasonId)
{
this.reasonId = reasonId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "APPLY", length = 2)
public java.lang.String getApply(){
return apply;
}
public void setApply(java.lang.String apply)
{
this.apply = apply;
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
@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
   

    @Override
    public ReasonDTO toDTO() {
        ReasonDTO reasonDTO = new ReasonDTO();
        //set cac gia tri 
        reasonDTO.setUpdatedBy(this.updatedBy);
        reasonDTO.setUpdatedDate(this.updatedDate);
        reasonDTO.setReasonId(this.reasonId);
        reasonDTO.setCode(this.code);
        reasonDTO.setApply(this.apply);
        reasonDTO.setCreatedDate(this.createdDate);
        reasonDTO.setCreatedBy(this.createdBy);
        reasonDTO.setStatus(this.status);
        reasonDTO.setName(this.name);
        return reasonDTO;
    }
}
