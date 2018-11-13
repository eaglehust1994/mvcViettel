/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.SignVofficeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.SIGN_VOFFICE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class SignVofficeBO extends BaseFWModelImpl {
     
private java.lang.Long signVofficeId;
private java.lang.Long sysUserId;
private java.lang.Long stockId;
private java.lang.String type;
private java.lang.Long oder;
private java.lang.String oderName;
private java.lang.String status;
private java.lang.Long objectId;
private java.lang.String errorCode;
private java.util.Date createdDate;
private java.lang.String transCode;


public SignVofficeBO() {
        setColId("signVofficeId");
        setColName("signVofficeId");
        setUniqueColumn(new String[]{"signVofficeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SIGN_VOFFICE_SEQ")
            }
    )
@Column(name = "SIGN_VOFFICE_ID", length = 22)
public java.lang.Long getSignVofficeId(){
return signVofficeId;
}
public void setSignVofficeId(java.lang.Long signVofficeId)
{
this.signVofficeId = signVofficeId;
}
@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Long getSysUserId(){
return sysUserId;
}
public void setSysUserId(java.lang.Long sysUserId)
{
this.sysUserId = sysUserId;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "TYPE", length = 4)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "ODER", length = 22)
public java.lang.Long getOder(){
return oder;
}
public void setOder(java.lang.Long oder)
{
this.oder = oder;
}
@Column(name = "ODER_NAME", length = 200)
public java.lang.String getOderName(){
return oderName;
}
public void setOderName(java.lang.String oderName)
{
this.oderName = oderName;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "OBJECT_ID", length = 10)
public java.lang.Long getObjectId() {
	return objectId;
}

public void setObjectId(java.lang.Long objectId) {
	this.objectId = objectId;
}

@Column(name = "ERROR_CODE", length = 100)
public java.lang.String getErrorCode() {
	return errorCode;
}

public void setErrorCode(java.lang.String errorCode) {
	this.errorCode = errorCode;
}

@Column(name = "CREATED_DATE", length = 100)
public java.util.Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(java.util.Date createdDate) {
	this.createdDate = createdDate;
}
@Column(name = "TRANS_CODE", length = 100)
public java.lang.String getTransCode() {
	return transCode;
}

public void setTransCode(java.lang.String transCode) {
	this.transCode = transCode;
}

    @Override
    public SignVofficeDTO toDTO() {
        SignVofficeDTO signVofficeDTO = new SignVofficeDTO();
        //set cac gia tri 
        signVofficeDTO.setSignVofficeId(this.signVofficeId);
        signVofficeDTO.setSysUserId(this.sysUserId);
        signVofficeDTO.setStockId(this.stockId);
        signVofficeDTO.setType(this.type);
        signVofficeDTO.setOder(this.oder);
        signVofficeDTO.setOderName(this.oderName);
        signVofficeDTO.setStatus(this.status);
        signVofficeDTO.setObjectId(this.objectId);
        signVofficeDTO.setErrorCode(this.errorCode);
        signVofficeDTO.setCreatedDate(this.createdDate);
        signVofficeDTO.setTransCode(this.transCode);
        return signVofficeDTO;
    }
}
