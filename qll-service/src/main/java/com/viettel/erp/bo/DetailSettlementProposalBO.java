/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "DETAIL_SETTLEMENT_PROPOSAL")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class DetailSettlementProposalBO extends BaseFWModelImpl {
     
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private java.lang.Long constructId;
private java.lang.Long detailSettlementProposalId;
private java.lang.String code;
private java.lang.Long sendPersonId;
private java.lang.Long bRepresentativeId;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long evaluatePersonId;
private java.lang.String evaluateComments;
private java.util.Date evaluateFinishDate;
private java.lang.Long evaluateStatus;
private java.lang.Long isActive;

 public DetailSettlementProposalBO() {
        setColId("detailSettlementProposalId");
        setColName("detailSettlementProposalId");
        setUniqueColumn(new String[]{"detailSettlementProposalId"});
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
                @Parameter(name = "sequence", value = "DETAIL_SETTLEMENT_PROPOSAL_SEQ")
            }
    )
@Column(name = "DETAIL_SETTLEMENT_PROPOSAL_ID", length = 22)
public java.lang.Long getDetailSettlementProposalId(){
return detailSettlementProposalId;
}
public void setDetailSettlementProposalId(java.lang.Long detailSettlementProposalId)
{
this.detailSettlementProposalId = detailSettlementProposalId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
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
@Column(name = "EVALUATE_PERSON_ID", length = 22)
public java.lang.Long getEvaluatePersonId(){
return evaluatePersonId;
}
public void setEvaluatePersonId(java.lang.Long evaluatePersonId)
{
this.evaluatePersonId = evaluatePersonId;
}
@Column(name = "EVALUATE_COMMENTS", length = 2000)
public java.lang.String getEvaluateComments(){
return evaluateComments;
}
public void setEvaluateComments(java.lang.String evaluateComments)
{
this.evaluateComments = evaluateComments;
}
@Column(name = "EVALUATE_FINISH_DATE", length = 7)
public java.util.Date getEvaluateFinishDate(){
return evaluateFinishDate;
}
public void setEvaluateFinishDate(java.util.Date evaluateFinishDate)
{
this.evaluateFinishDate = evaluateFinishDate;
}
@Column(name = "EVALUATE_STATUS", length = 22)
public java.lang.Long getEvaluateStatus(){
return evaluateStatus;
}
public void setEvaluateStatus(java.lang.Long evaluateStatus)
{
this.evaluateStatus = evaluateStatus;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
   

    @Override
    public DetailSettlementProposalDTO toDTO() {
        DetailSettlementProposalDTO detailSettlementProposalDTO = new DetailSettlementProposalDTO();
        //set cac gia tri 
        detailSettlementProposalDTO.setConstructId(this.constructId);
        detailSettlementProposalDTO.setDetailSettlementProposalId(this.detailSettlementProposalId);
        detailSettlementProposalDTO.setCode(this.code);
        detailSettlementProposalDTO.setSendPersonId(this.sendPersonId);
        detailSettlementProposalDTO.setBRepresentativeId(this.bRepresentativeId);
        detailSettlementProposalDTO.setCreatedDate(this.createdDate);
        detailSettlementProposalDTO.setCreatedUserId(this.createdUserId);
        detailSettlementProposalDTO.setApprovalDate(this.approvalDate);
        detailSettlementProposalDTO.setStatusCa(this.statusCa);
        detailSettlementProposalDTO.setEvaluatePersonId(this.evaluatePersonId);
        detailSettlementProposalDTO.setEvaluateComments(this.evaluateComments);
        detailSettlementProposalDTO.setEvaluateFinishDate(this.evaluateFinishDate);
        detailSettlementProposalDTO.setEvaluateStatus(this.evaluateStatus);
        detailSettlementProposalDTO.setIsActive(this.isActive);
        return detailSettlementProposalDTO;
    }
}
