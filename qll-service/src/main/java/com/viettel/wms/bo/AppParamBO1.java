/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.AppParamDTO1;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.APP_PARAM")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class AppParamBO1 extends BaseFWModelImpl {
     
private java.util.Date updatedDate;
private java.lang.Long updatedBy;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.String description;
private java.lang.Long appParamId;
private java.lang.String parOrder;
private java.lang.String parType;
private java.lang.String name;
private java.lang.String code;
private java.lang.String status;

 public AppParamBO1() {
        setColId("appParamId");
        setColName("appParamId");
        setUniqueColumn(new String[]{"appParamId"});
}

@Column(name = "UPDATED_DATE", length = 7)
public java.util.Date getUpdatedDate(){
return updatedDate;
}
public void setUpdatedDate(java.util.Date updatedDate)
{
this.updatedDate = updatedDate;
}
@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
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
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.APP_PARAM_SEQ")
            }
    )
@Column(name = "APP_PARAM_ID", length = 22)
public java.lang.Long getAppParamId(){
return appParamId;
}
public void setAppParamId(java.lang.Long appParamId)
{
this.appParamId = appParamId;
}
@Column(name = "PAR_ORDER", length = 4)
public java.lang.String getParOrder(){
return parOrder;
}
public void setParOrder(java.lang.String parOrder)
{
this.parOrder = parOrder;
}
@Column(name = "PAR_TYPE", length = 4)
public java.lang.String getParType(){
return parType;
}
public void setParType(java.lang.String parType)
{
this.parType = parType;
}
@Column(name = "NAME", length = 100)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
   

    @Override
    public AppParamDTO1 toDTO() {
        AppParamDTO1 appParamDTO = new AppParamDTO1();
        //set cac gia tri 
        appParamDTO.setUpdatedDate(this.updatedDate);
        appParamDTO.setUpdatedBy(this.updatedBy);
        appParamDTO.setCreatedDate(this.createdDate);
        appParamDTO.setCreatedBy(this.createdBy);
        appParamDTO.setDescription(this.description);
        appParamDTO.setAppParamId(this.appParamId);
        appParamDTO.setParOrder(this.parOrder);
        appParamDTO.setParType(this.parType);
        appParamDTO.setName(this.name);
        appParamDTO.setCode(this.code);
        appParamDTO.setStatus(this.status);
        return appParamDTO;
    }
}
