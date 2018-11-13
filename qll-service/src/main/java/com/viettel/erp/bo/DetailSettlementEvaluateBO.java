/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "DETAIL_SETTLEMENT_EVALUATE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class DetailSettlementEvaluateBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long detailSettlementEvaluateId;
private java.lang.String code;
private java.lang.Long createdEvaluatePerId;
private java.lang.Long checkEvaluatePerId;
private java.lang.Long sendPersonId;
private java.lang.Long bRepresentativeId;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.lang.Long aDirectorId; 

 

public DetailSettlementEvaluateBO() {
        setColId("detailSettlementEvaluateId");
        setColName("detailSettlementEvaluateId");
        setUniqueColumn(new String[]{"detailSettlementEvaluateId"});
}

@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "DETAIL_SETTLEMENT_EVALUATE_SEQ")
            }
    )
@Column(name = "DETAIL_SETTLEMENT_EVALUATE_ID", length = 22)
public java.lang.Long getDetailSettlementEvaluateId(){
return detailSettlementEvaluateId;
}
public void setDetailSettlementEvaluateId(java.lang.Long detailSettlementEvaluateId)
{
this.detailSettlementEvaluateId = detailSettlementEvaluateId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "CREATED_EVALUATE_PER_ID", length = 22)
public java.lang.Long getCreatedEvaluatePerId(){
return createdEvaluatePerId;
}
public void setCreatedEvaluatePerId(java.lang.Long createdEvaluatePerId)
{
this.createdEvaluatePerId = createdEvaluatePerId;
}
@Column(name = "CHECK_EVALUATE_PER_ID", length = 22)
public java.lang.Long getCheckEvaluatePerId(){
return checkEvaluatePerId;
}
public void setCheckEvaluatePerId(java.lang.Long checkEvaluatePerId)
{
this.checkEvaluatePerId = checkEvaluatePerId;
}
@Column(name = "SEND_PERSON_ID", length = 22)
public java.lang.Long getSendPersonId(){
return sendPersonId;
}
public void setSendPersonId(java.lang.Long sendPersonId)
{
this.sendPersonId = sendPersonId;
}
@Column(name = "B_REPRESENTATIVE_ID", length = 22)
public java.lang.Long getBRepresentativeId(){
return bRepresentativeId;
}
public void setBRepresentativeId(java.lang.Long bRepresentativeId)
{
this.bRepresentativeId = bRepresentativeId;
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
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "A_DIRECTOR_ID", length = 22)
public java.lang.Long getaDirectorId() {
	return aDirectorId;
}

public void setaDirectorId(java.lang.Long aDirectorId) {
	this.aDirectorId = aDirectorId;
}
   

    @Override
    public DetailSettlementEvaluateDTO toDTO() {
        DetailSettlementEvaluateDTO detailSettlementEvaluateDTO = new DetailSettlementEvaluateDTO();
        //set cac gia tri 
        detailSettlementEvaluateDTO.setConstructId(this.constructId);
        detailSettlementEvaluateDTO.setDetailSettlementEvaluateId(this.detailSettlementEvaluateId);
        detailSettlementEvaluateDTO.setCode(this.code);
        detailSettlementEvaluateDTO.setCreatedEvaluatePerId(this.createdEvaluatePerId);
        detailSettlementEvaluateDTO.setCheckEvaluatePerId(this.checkEvaluatePerId);
        detailSettlementEvaluateDTO.setSendPersonId(this.sendPersonId);
        detailSettlementEvaluateDTO.setBRepresentativeId(this.bRepresentativeId);
        detailSettlementEvaluateDTO.setCreatedDate(this.createdDate);
        detailSettlementEvaluateDTO.setCreatedUserId(this.createdUserId);
        detailSettlementEvaluateDTO.setApprovalDate(this.approvalDate);
        detailSettlementEvaluateDTO.setStatusCa(this.statusCa);
        detailSettlementEvaluateDTO.setIsActive(this.isActive);
        detailSettlementEvaluateDTO.setaDirectorId(this.aDirectorId);
        return detailSettlementEvaluateDTO;
    }
}
