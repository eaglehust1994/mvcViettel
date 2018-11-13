/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_FILE_INVOICE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatFileInvoiceBO extends BaseFWModelImpl {
     
private java.lang.String dataTableName;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long catFileInvoiceId;
private java.lang.String fileInvoiceCode;
private java.lang.String fileInvoiceName;
private java.lang.String type;
private java.lang.Long contractType;
private java.lang.Long isActive;
private java.lang.String note;
private java.lang.String softwareLink;

    private java.lang.String isExistProfile;

 public CatFileInvoiceBO() {
        setColId("catFileInvoiceId");
        setColName("catFileInvoiceId");
        setUniqueColumn(new String[]{"catFileInvoiceId"});
}

@Column(name = "DATA_TABLE_NAME", length = 400)
public java.lang.String getDataTableName(){
return dataTableName;
}
public void setDataTableName(java.lang.String dataTableName)
{
this.dataTableName = dataTableName;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_USER_ID", length = 22)
public java.lang.Long getCreatedUserId(){
return createdUserId;
}
public void setCreatedUserId(java.lang.Long createdUserId)
{
this.createdUserId = createdUserId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_FILE_INVOICE_SEQ")
            }
    )
@Column(name = "CAT_FILE_INVOICE_ID", length = 22)
public java.lang.Long getCatFileInvoiceId(){
return catFileInvoiceId;
}
public void setCatFileInvoiceId(java.lang.Long catFileInvoiceId)
{
this.catFileInvoiceId = catFileInvoiceId;
}
@Column(name = "FILE_INVOICE_CODE", length = 400)
public java.lang.String getFileInvoiceCode(){
return fileInvoiceCode;
}
public void setFileInvoiceCode(java.lang.String fileInvoiceCode)
{
this.fileInvoiceCode = fileInvoiceCode;
}
@Column(name = "FILE_INVOICE_NAME", length = 1000)
public java.lang.String getFileInvoiceName(){
return fileInvoiceName;
}
public void setFileInvoiceName(java.lang.String fileInvoiceName)
{
this.fileInvoiceName = fileInvoiceName;
}
@Column(name = "TYPE", length = 10)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "CONTRACT_TYPE", length = 22)
public java.lang.Long getContractType(){
return contractType;
}
public void setContractType(java.lang.Long contractType)
{
this.contractType = contractType;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "NOTE", length = 2000)
public java.lang.String getNote(){
return note;
}
public void setNote(java.lang.String note)
{
this.note = note;
}
@Column(name = "SOFTWARE_LINK", length = 2000)
public java.lang.String getSoftwareLink(){
return softwareLink;
}
public void setSoftwareLink(java.lang.String softwareLink)
{
this.softwareLink = softwareLink;
}
   

    @Override
    public CatFileInvoiceDTO toDTO() {
        CatFileInvoiceDTO catFileInvoiceDTO = new CatFileInvoiceDTO();
        //set cac gia tri 
        catFileInvoiceDTO.setDataTableName(this.dataTableName);
        catFileInvoiceDTO.setCreatedDate(this.createdDate);
        catFileInvoiceDTO.setCreatedUserId(this.createdUserId);
        catFileInvoiceDTO.setCatFileInvoiceId(this.catFileInvoiceId);
        catFileInvoiceDTO.setFileInvoiceCode(this.fileInvoiceCode);
        catFileInvoiceDTO.setFileInvoiceName(this.fileInvoiceName);
        catFileInvoiceDTO.setType(this.type);
        catFileInvoiceDTO.setContractType(this.contractType);
        catFileInvoiceDTO.setIsActive(this.isActive);
        catFileInvoiceDTO.setNote(this.note);
        catFileInvoiceDTO.setSoftwareLink(this.softwareLink);
        return catFileInvoiceDTO;
    }
}
