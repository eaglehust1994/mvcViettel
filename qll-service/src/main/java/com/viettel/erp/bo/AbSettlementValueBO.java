/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "AB_SETTLEMENT_VALUE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbSettlementValueBO extends BaseFWModelImpl {
     
private java.lang.Long abSettlementValueId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long exportMaterialValue;
private java.lang.Long acceptMaterialValue;
private java.lang.Long lostMaterialValue;
private java.lang.Long recoveryMaterialValue;
private java.lang.Long unrecoveryMaterialValue;
private java.lang.Long paidValue;
private java.lang.Long constructId;

 public AbSettlementValueBO() {
        setColId("abSettlementValueId");
        setColName("abSettlementValueId");
        setUniqueColumn(new String[]{"abSettlementValueId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_SETTLEMENT_VALUE_SEQ")
            }
    )
@Column(name = "AB_SETTLEMENT_VALUE_ID", length = 22)
public java.lang.Long getAbSettlementValueId(){
return abSettlementValueId;
}
public void setAbSettlementValueId(java.lang.Long abSettlementValueId)
{
this.abSettlementValueId = abSettlementValueId;
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
@Column(name = "EXPORT_MATERIAL_VALUE", length = 22)
public java.lang.Long getExportMaterialValue(){
return exportMaterialValue;
}
public void setExportMaterialValue(java.lang.Long exportMaterialValue)
{
this.exportMaterialValue = exportMaterialValue;
}
@Column(name = "ACCEPT_MATERIAL_VALUE", length = 22)
public java.lang.Long getAcceptMaterialValue(){
return acceptMaterialValue;
}
public void setAcceptMaterialValue(java.lang.Long acceptMaterialValue)
{
this.acceptMaterialValue = acceptMaterialValue;
}
@Column(name = "LOST_MATERIAL_VALUE", length = 22)
public java.lang.Long getLostMaterialValue(){
return lostMaterialValue;
}
public void setLostMaterialValue(java.lang.Long lostMaterialValue)
{
this.lostMaterialValue = lostMaterialValue;
}
@Column(name = "RECOVERY_MATERIAL_VALUE", length = 22)
public java.lang.Long getRecoveryMaterialValue(){
return recoveryMaterialValue;
}
public void setRecoveryMaterialValue(java.lang.Long recoveryMaterialValue)
{
this.recoveryMaterialValue = recoveryMaterialValue;
}
@Column(name = "UNRECOVERY_MATERIAL_VALUE", length = 22)
public java.lang.Long getUnrecoveryMaterialValue(){
return unrecoveryMaterialValue;
}
public void setUnrecoveryMaterialValue(java.lang.Long unrecoveryMaterialValue)
{
this.unrecoveryMaterialValue = unrecoveryMaterialValue;
}
@Column(name = "PAID_VALUE", length = 22)
public java.lang.Long getPaidValue(){
return paidValue;
}
public void setPaidValue(java.lang.Long paidValue)
{
this.paidValue = paidValue;
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
    public AbSettlementValueDTO toDTO() {
        AbSettlementValueDTO abSettlementValueDTO = new AbSettlementValueDTO();
        //set cac gia tri 
        abSettlementValueDTO.setAbSettlementValueId(this.abSettlementValueId);
        abSettlementValueDTO.setCode(this.code);
        abSettlementValueDTO.setCreatedDate(this.createdDate);
        abSettlementValueDTO.setCreatedUserId(this.createdUserId);
        abSettlementValueDTO.setADirectorId(this.aDirectorId);
        abSettlementValueDTO.setBDirectorId(this.bDirectorId);
        abSettlementValueDTO.setStatusCa(this.statusCa);
        abSettlementValueDTO.setDocumentCaId(this.documentCaId);
        abSettlementValueDTO.setIsActive(this.isActive);
        abSettlementValueDTO.setExportMaterialValue(this.exportMaterialValue);
        abSettlementValueDTO.setAcceptMaterialValue(this.acceptMaterialValue);
        abSettlementValueDTO.setLostMaterialValue(this.lostMaterialValue);
        abSettlementValueDTO.setRecoveryMaterialValue(this.recoveryMaterialValue);
        abSettlementValueDTO.setUnrecoveryMaterialValue(this.unrecoveryMaterialValue);
        abSettlementValueDTO.setPaidValue(this.paidValue);
        abSettlementValueDTO.setConstructId(this.constructId);
        return abSettlementValueDTO;
    }
}
