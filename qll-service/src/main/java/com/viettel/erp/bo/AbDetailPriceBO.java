/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbDetailPriceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AB_DETAIL_PRICE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbDetailPriceBO extends BaseFWModelImpl {
     
private java.lang.Long abDetailPriceId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long adirectorId;
private java.lang.Long bdirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

 public AbDetailPriceBO() {
        setColId("abDetailPriceId");
        setColName("abDetailPriceId");
        setUniqueColumn(new String[]{"abDetailPriceId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_DETAIL_PRICE_SEQ")
            }
    )
@Column(name = "AB_DETAIL_PRICE_ID", length = 22)
public java.lang.Long getAbDetailPriceId(){
return abDetailPriceId;
}
public void setAbDetailPriceId(java.lang.Long abDetailPriceId)
{
this.abDetailPriceId = abDetailPriceId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
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
@Column(name = "A_DIRECTOR_ID", length = 22)
public java.lang.Long getAdirectorId() {
	return adirectorId;
}

public void setAdirectorId(java.lang.Long adirectorId) {
	this.adirectorId = adirectorId;
}


@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBdirectorId() {
	return bdirectorId;
}

public void setBdirectorId(java.lang.Long bdirectorId) {
	this.bdirectorId = bdirectorId;
}

@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
   

    @Override
    public AbDetailPriceDTO toDTO() {
        AbDetailPriceDTO abDetailPriceDTO = new AbDetailPriceDTO();
        //set cac gia tri 
        abDetailPriceDTO.setAbDetailPriceId(this.abDetailPriceId);
        abDetailPriceDTO.setCode(this.code);
        abDetailPriceDTO.setCreatedDate(this.createdDate);
        abDetailPriceDTO.setCreatedUserId(this.createdUserId);
        abDetailPriceDTO.setAdirectorId(this.adirectorId);
        abDetailPriceDTO.setBdirectorId(this.bdirectorId);
        abDetailPriceDTO.setStatusCa(this.statusCa);
        abDetailPriceDTO.setDocumentCaId(this.documentCaId);
        abDetailPriceDTO.setIsActive(this.isActive);
        abDetailPriceDTO.setConstructId(this.constructId);
        return abDetailPriceDTO;
    }
}
