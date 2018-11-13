/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.SignVofficeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SIGN_VOFFICEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignVofficeDTO extends BaseFWDTOImpl<SignVofficeBO> {

private java.lang.Long signVofficeId;
private java.lang.Long sysUserId;
private java.lang.Long stockId;
private java.lang.String type;
private java.lang.Long oder;
private java.lang.String oderName;
private java.lang.String status;
private java.lang.Long objectId;
private java.lang.String objectCode;
private java.lang.Long adOrgId;
private java.lang.String errorCode;
private java.util.Date createdDate;
private java.lang.String transCode;
private String vofficePass;
private Long createdBy;
private String email;
private String signState;
private Double totalPrice;
public Double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}

public String getSignState() {
	return signState;
}

public void setSignState(String signState) {
	this.signState = signState;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
}

public String getVofficePass() {
	return vofficePass;
}

public void setVofficePass(String vofficePass) {
	this.vofficePass = vofficePass;
}

public java.lang.String getTransCode() {
	return transCode;
}

public void setTransCode(java.lang.String transCode) {
	this.transCode = transCode;
}

public java.lang.Long getAdOrgId() {
	return adOrgId;
}

public void setAdOrgId(java.lang.Long adOrgId) {
	this.adOrgId = adOrgId;
}

public java.lang.Long getSysRoleId() {
	return sysRoleId;
}

public void setSysRoleId(java.lang.Long sysRoleId) {
	this.sysRoleId = sysRoleId;
}

private java.lang.Long sysRoleId;
private java.lang.String sysRoleName;
public java.lang.String getSysRoleName() {
	return sysRoleName;
}

public void setSysRoleName(java.lang.String sysRoleName) {
	this.sysRoleName = sysRoleName;
}

public java.lang.String getFullName() {
	return fullName;
}

public void setFullName(java.lang.String fullName) {
	this.fullName = fullName;
}

private java.lang.String fullName;

    @Override
    public SignVofficeBO toModel() {
        SignVofficeBO signVofficeBO = new SignVofficeBO();
        signVofficeBO.setSignVofficeId(this.signVofficeId);
        signVofficeBO.setSysUserId(this.sysUserId);
        signVofficeBO.setStockId(this.stockId);
        signVofficeBO.setType(this.type);
        signVofficeBO.setOder(this.oder);
        signVofficeBO.setOderName(this.oderName);
        signVofficeBO.setStatus(this.status);
        signVofficeBO.setObjectId(this.objectId);
        signVofficeBO.setErrorCode(this.errorCode);
        signVofficeBO.setCreatedDate(this.createdDate);
        signVofficeBO.setTransCode(this.transCode);
        return signVofficeBO;
    }

    @Override
     public Long getFWModelId() {
        return signVofficeId;
    }
   
    @Override
    public String catchName() {
        return getSignVofficeId().toString();
    }
    public java.lang.Long getSignVofficeId(){
    return signVofficeId;
    }
    public void setSignVofficeId(java.lang.Long signVofficeId)
    {
    this.signVofficeId = signVofficeId;
    }
    
    public java.lang.Long getSysUserId(){
    return sysUserId;
    }
    public void setSysUserId(java.lang.Long sysUserId)
    {
    this.sysUserId = sysUserId;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.Long getOder(){
    return oder;
    }
    public void setOder(java.lang.Long oder)
    {
    this.oder = oder;
    }
    
    public java.lang.String getOderName(){
    return oderName;
    }
    public void setOderName(java.lang.String oderName)
    {
    this.oderName = oderName;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.Long getObjectId() {
    	return objectId;
    }

    public void setObjectId(java.lang.Long objectId) {
    	this.objectId = objectId;
    }

    public java.lang.String getObjectCode() {
    	return objectCode;
    }

    public void setObjectCode(java.lang.String objectCode) {
    	this.objectCode = objectCode;
    }
   
    public java.lang.String getErrorCode() {
    	return errorCode;
    }

    public void setErrorCode(java.lang.String errorCode) {
    	this.errorCode = errorCode;
    }

    public java.util.Date getCreatedDate() {
    	return createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {
    	this.createdDate = createdDate;
    }

}
