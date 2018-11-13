/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbMaterialCompareDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AB_MATERIAL_COMPARE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbMaterialCompareBO extends BaseFWModelImpl {
     
private java.lang.Long abMaterialCompareId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long aHeadConstructId;
private java.lang.Long aHeadTechnicalId;
private java.lang.Long aHeadFinanceId;
private java.lang.Long bHeadConstructId;
private java.lang.Long bHeadAccountId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

 public AbMaterialCompareBO() {
        setColId("abMaterialCompareId");
        setColName("abMaterialCompareId");
        setUniqueColumn(new String[]{"abMaterialCompareId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_MATERIAL_COMPARE_SEQ")
            }
    )
@Column(name = "AB_MATERIAL_COMPARE_ID", length = 22)
public java.lang.Long getAbMaterialCompareId(){
return abMaterialCompareId;
}
public void setAbMaterialCompareId(java.lang.Long abMaterialCompareId)
{
this.abMaterialCompareId = abMaterialCompareId;
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
public java.lang.Long getADirectorId(){
return aDirectorId;
}
public void setADirectorId(java.lang.Long aDirectorId)
{
this.aDirectorId = aDirectorId;
}
@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBDirectorId(){
return bDirectorId;
}
public void setBDirectorId(java.lang.Long bDirectorId)
{
this.bDirectorId = bDirectorId;
}
@Column(name = "A_HEAD_CONSTRUCT_ID", length = 22)
public java.lang.Long getAHeadConstructId(){
return aHeadConstructId;
}
public void setAHeadConstructId(java.lang.Long aHeadConstructId)
{
this.aHeadConstructId = aHeadConstructId;
}
@Column(name = "A_HEAD_TECHNICAL_ID", length = 22)
public java.lang.Long getAHeadTechnicalId(){
return aHeadTechnicalId;
}
public void setAHeadTechnicalId(java.lang.Long aHeadTechnicalId)
{
this.aHeadTechnicalId = aHeadTechnicalId;
}
@Column(name = "A_HEAD_FINANCE_ID", length = 22)
public java.lang.Long getAHeadFinanceId(){
return aHeadFinanceId;
}
public void setAHeadFinanceId(java.lang.Long aHeadFinanceId)
{
this.aHeadFinanceId = aHeadFinanceId;
}
@Column(name = "B_HEAD_CONSTRUCT_ID", length = 22)
public java.lang.Long getBHeadConstructId(){
return bHeadConstructId;
}
public void setBHeadConstructId(java.lang.Long bHeadConstructId)
{
this.bHeadConstructId = bHeadConstructId;
}
@Column(name = "B_HEAD_ACCOUNT_ID", length = 22)
public java.lang.Long getBHeadAccountId(){
return bHeadAccountId;
}
public void setBHeadAccountId(java.lang.Long bHeadAccountId)
{
this.bHeadAccountId = bHeadAccountId;
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
    public AbMaterialCompareDTO toDTO() {
        AbMaterialCompareDTO abMaterialCompareDTO = new AbMaterialCompareDTO();
        //set cac gia tri 
        abMaterialCompareDTO.setAbMaterialCompareId(this.abMaterialCompareId);
        abMaterialCompareDTO.setCode(this.code);
        abMaterialCompareDTO.setCreatedDate(this.createdDate);
        abMaterialCompareDTO.setCreatedUserId(this.createdUserId);
        abMaterialCompareDTO.setADirectorId(this.aDirectorId);
        abMaterialCompareDTO.setBDirectorId(this.bDirectorId);
        abMaterialCompareDTO.setAHeadConstructId(this.aHeadConstructId);
        abMaterialCompareDTO.setAHeadTechnicalId(this.aHeadTechnicalId);
        abMaterialCompareDTO.setAHeadFinanceId(this.aHeadFinanceId);
        abMaterialCompareDTO.setBHeadConstructId(this.bHeadConstructId);
        abMaterialCompareDTO.setBHeadAccountId(this.bHeadAccountId);
        abMaterialCompareDTO.setStatusCa(this.statusCa);
        abMaterialCompareDTO.setDocumentCaId(this.documentCaId);
        abMaterialCompareDTO.setIsActive(this.isActive);
        abMaterialCompareDTO.setConstructId(this.constructId);
        return abMaterialCompareDTO;
    }
}
