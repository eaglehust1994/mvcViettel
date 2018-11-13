/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AB_COMPLEMENT_WORK")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbComplementWorkBO extends BaseFWModelImpl {
     
private java.lang.Long abComplementWorkId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long adirectorId;
private java.lang.Long bdirectorId;
private java.lang.Long cdesignDirectionId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

 public AbComplementWorkBO() {
        setColId("abComplementWorkId");
        setColName("abComplementWorkId");
        setUniqueColumn(new String[]{"abComplementWorkId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_COMPLEMENT_WORK_SEQ")
            }
    )
@Column(name = "AB_COMPLEMENT_WORK_ID", length = 22)
public java.lang.Long getAbComplementWorkId(){
return abComplementWorkId;
}
public void setAbComplementWorkId(java.lang.Long abComplementWorkId)
{
this.abComplementWorkId = abComplementWorkId;
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
return adirectorId;
}
public void setADirectorId(java.lang.Long adirectorId)
{
this.adirectorId = adirectorId;
}
@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBDirectorId(){
return bdirectorId;
}
public void setBDirectorId(java.lang.Long bdirectorId)
{
this.bdirectorId = bdirectorId;
}
@Column(name = "C_DESIGN_DIRECTION_ID", length = 22)
public java.lang.Long getCDesignDirectionId(){
return cdesignDirectionId;
}
public void setCDesignDirectionId(java.lang.Long cdesignDirectionId)
{
this.cdesignDirectionId = cdesignDirectionId;
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
public java.lang.Long getConstructId() {
	return constructId;
}

public void setConstructId(java.lang.Long constructId) {
	this.constructId = constructId;
}
   

    @Override
    public AbComplementWorkDTO toDTO() {
        AbComplementWorkDTO abComplementWorkDTO = new AbComplementWorkDTO();
        //set cac gia tri 
        abComplementWorkDTO.setAbComplementWorkId(this.abComplementWorkId);
        abComplementWorkDTO.setCode(this.code);
        abComplementWorkDTO.setCreatedDate(this.createdDate);
        abComplementWorkDTO.setCreatedUserId(this.createdUserId);
        abComplementWorkDTO.setAdirectorId(this.adirectorId);
        abComplementWorkDTO.setBdirectorId(this.bdirectorId);
        abComplementWorkDTO.setCdesignDirectionId(this.cdesignDirectionId);
        abComplementWorkDTO.setStatusCa(this.statusCa);
        abComplementWorkDTO.setDocumentCaId(this.documentCaId);
        abComplementWorkDTO.setIsActive(this.isActive);
        abComplementWorkDTO.setConstructId(this.constructId);
        return abComplementWorkDTO;
    }




}
