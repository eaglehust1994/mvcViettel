/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.TaxDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.TAX")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class TaxBO extends BaseFWModelImpl {
     
private java.lang.String name;
private java.lang.Double value;
private java.lang.String type;
private java.lang.String code;
private java.lang.String ignore;
private java.lang.String apply;
private java.lang.String status;
private java.lang.Long createdBy;
private java.util.Date createdDate;
private java.lang.Long taxId;
private java.util.Date updatedDate;
private java.lang.Long updatedBy;

 public TaxBO() {
        setColId("taxId");
        setColName("taxId");
        setUniqueColumn(new String[]{"taxId"});
}

@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "VALUE", length = 22)
public java.lang.Double getValue(){
return value;
}
public void setValue(java.lang.Double value)
{
this.value = value;
}
@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "IGNORE", length = 2)
public java.lang.String getIgnore(){
return ignore;
}
public void setIgnore(java.lang.String ignore)
{
this.ignore = ignore;
}
@Column(name = "APPLY", length = 2)
public java.lang.String getApply(){
return apply;
}
public void setApply(java.lang.String apply)
{
this.apply = apply;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.TAX_SEQ")
            }
    )
@Column(name = "TAX_ID", length = 22)
public java.lang.Long getTaxId(){
return taxId;
}
public void setTaxId(java.lang.Long taxId)
{
this.taxId = taxId;
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
   

    @Override
    public TaxDTO toDTO() {
        TaxDTO taxDTO = new TaxDTO();
        //set cac gia tri 
        taxDTO.setName(this.name);
        taxDTO.setValue(this.value);
        taxDTO.setType(this.type);
        taxDTO.setCode(this.code);
        taxDTO.setIgnore(this.ignore);
        taxDTO.setApply(this.apply);
        taxDTO.setStatus(this.status);
        taxDTO.setCreatedBy(this.createdBy);
        taxDTO.setCreatedDate(this.createdDate);
        taxDTO.setTaxId(this.taxId);
        taxDTO.setUpdatedDate(this.updatedDate);
        taxDTO.setUpdatedBy(this.updatedBy);
        return taxDTO;
    }
}
