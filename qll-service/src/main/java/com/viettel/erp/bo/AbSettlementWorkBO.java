/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbSettlementWorkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AB_SETTLEMENT_WORK")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbSettlementWorkBO extends BaseFWModelImpl {
     
private java.lang.Long abSettlementWorkId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

 public AbSettlementWorkBO() {
        setColId("abSettlementWorkId");
        setColName("abSettlementWorkId");
        setUniqueColumn(new String[]{"abSettlementWorkId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_SETTLEMENT_WORK_SEQ")
            }
    )
@Column(name = "AB_SETTLEMENT_WORK_ID", length = 22)
public java.lang.Long getAbSettlementWorkId(){
return abSettlementWorkId;
}
public void setAbSettlementWorkId(java.lang.Long abSettlementWorkId)
{
this.abSettlementWorkId = abSettlementWorkId;
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
    public AbSettlementWorkDTO toDTO() {
        AbSettlementWorkDTO abSettlementWorkDTO = new AbSettlementWorkDTO();
        //set cac gia tri 
        abSettlementWorkDTO.setAbSettlementWorkId(this.abSettlementWorkId);
        abSettlementWorkDTO.setCode(this.code);
        abSettlementWorkDTO.setCreatedDate(this.createdDate);
        abSettlementWorkDTO.setCreatedUserId(this.createdUserId);
        abSettlementWorkDTO.setADirectorId(this.aDirectorId);
        abSettlementWorkDTO.setBDirectorId(this.bDirectorId);
        abSettlementWorkDTO.setStatusCa(this.statusCa);
        abSettlementWorkDTO.setDocumentCaId(this.documentCaId);
        abSettlementWorkDTO.setIsActive(this.isActive);
        abSettlementWorkDTO.setConstructId(this.constructId);
        return abSettlementWorkDTO;
    }
}
