/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatFileInvoiceBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.type.LongType;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_FILE_INVOICEBO")
public class CatFileInvoiceDTO extends BaseFWDTOImpl<CatFileInvoiceBO> {

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
private java.lang.Long typeConstruction;
private java.lang.String loginName;

    private java.lang.String isExistProfile;

    @Override
    public CatFileInvoiceBO toModel() {
        CatFileInvoiceBO catFileInvoiceBO = new CatFileInvoiceBO();
        catFileInvoiceBO.setDataTableName(this.dataTableName);
        catFileInvoiceBO.setCreatedDate(this.createdDate);
        catFileInvoiceBO.setCreatedUserId(this.createdUserId);
        catFileInvoiceBO.setCatFileInvoiceId(this.catFileInvoiceId);
        catFileInvoiceBO.setFileInvoiceCode(this.fileInvoiceCode);
        catFileInvoiceBO.setFileInvoiceName(this.fileInvoiceName);
        catFileInvoiceBO.setType(this.type);
        catFileInvoiceBO.setContractType(this.contractType);
        catFileInvoiceBO.setIsActive(this.isActive);
        catFileInvoiceBO.setNote(this.note);
        catFileInvoiceBO.setSoftwareLink(this.softwareLink);
        return catFileInvoiceBO;
    }

    public java.lang.String getDataTableName(){
    return dataTableName;
    }
    public void setDataTableName(java.lang.String dataTableName)
    {
    this.dataTableName = dataTableName;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
    @Override
     public Long getFWModelId() {
        return catFileInvoiceId;
    }
   
    @Override
    public String catchName() {
        return getCatFileInvoiceId().toString();
    }
    public java.lang.Long getCatFileInvoiceId(){
    return catFileInvoiceId;
    }
    public void setCatFileInvoiceId(java.lang.Long catFileInvoiceId)
    {
    this.catFileInvoiceId = catFileInvoiceId;
    }
    
    public java.lang.String getFileInvoiceCode(){
    return fileInvoiceCode;
    }
    public void setFileInvoiceCode(java.lang.String fileInvoiceCode)
    {
    this.fileInvoiceCode = fileInvoiceCode;
    }
    
    public java.lang.String getFileInvoiceName(){
    return fileInvoiceName;
    }
    public void setFileInvoiceName(java.lang.String fileInvoiceName)
    {
    this.fileInvoiceName = fileInvoiceName;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.Long getContractType(){
    return contractType;
    }
    public void setContractType(java.lang.Long contractType)
    {
    this.contractType = contractType;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.String getNote(){
    return note;
    }
    public void setNote(java.lang.String note)
    {
    this.note = note;
    }
    
    public java.lang.String getSoftwareLink(){
    return softwareLink;
    }

    public String getIsExistProfile() {
        return isExistProfile;
    }

    public void setIsExistProfile(String isExistProfile) {
        this.isExistProfile = isExistProfile;
    }

    public void setSoftwareLink(java.lang.String softwareLink)
    {
    this.softwareLink = softwareLink;
    }

	public java.lang.Long getTypeConstruction() {
		return typeConstruction;
	}

	public void setTypeConstruction(java.lang.Long typeConstruction) {
		this.typeConstruction = typeConstruction;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}
    
    
   
}
