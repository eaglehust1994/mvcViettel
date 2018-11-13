/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AdvancePaymentProposalDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name ="adPaymentProposal")
@Table(name = "ADVANCE_PAYMENT_PROPOSAL")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AdvancePaymentProposalBO extends BaseFWModelImpl {
     
private java.lang.String investorRepresentive;
private java.lang.Long constructId;
private java.lang.Long advancePaymentProposalId;
private java.lang.String code;
private java.lang.String receiveDepartment;
private java.lang.Long advancePaymentNumber;
private java.lang.Long advancePaymentPercent;
private java.lang.Double advancePaymentAmount;
private java.lang.String tranferToText;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.String companyName;
private java.lang.String accountNo;
private java.lang.String bankName;
private java.util.Date requestDate;
private java.lang.Long isActive;

 public AdvancePaymentProposalBO() {
        setColId("advancePaymentProposalId");
        setColName("advancePaymentProposalId");
        setUniqueColumn(new String[]{"advancePaymentProposalId"});
}

@Column(name = "INVESTOR_REPRESENTIVE", length = 200)
public java.lang.String getInvestorRepresentive(){
return investorRepresentive;
}
public void setInvestorRepresentive(java.lang.String investorRepresentive)
{
this.investorRepresentive = investorRepresentive;
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
                @Parameter(name = "sequence", value = "ADVANCE_PAYMENT_PROPOSAL_SEQ")
            }
    )
@Column(name = "ADVANCE_PAYMENT_PROPOSAL_ID", length = 22)
public java.lang.Long getAdvancePaymentProposalId(){
return advancePaymentProposalId;
}
public void setAdvancePaymentProposalId(java.lang.Long advancePaymentProposalId)
{
this.advancePaymentProposalId = advancePaymentProposalId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "RECEIVE_DEPARTMENT", length = 2000)
public java.lang.String getReceiveDepartment(){
return receiveDepartment;
}
public void setReceiveDepartment(java.lang.String receiveDepartment)
{
this.receiveDepartment = receiveDepartment;
}
@Column(name = "ADVANCE_PAYMENT_NUMBER", length = 22)
public java.lang.Long getAdvancePaymentNumber(){
return advancePaymentNumber;
}
public void setAdvancePaymentNumber(java.lang.Long advancePaymentNumber)
{
this.advancePaymentNumber = advancePaymentNumber;
}
@Column(name = "ADVANCE_PAYMENT_PERCENT", length = 22)
public java.lang.Long getAdvancePaymentPercent(){
return advancePaymentPercent;
}
public void setAdvancePaymentPercent(java.lang.Long advancePaymentPercent)
{
this.advancePaymentPercent = advancePaymentPercent;
}
@Column(name = "ADVANCE_PAYMENT_AMOUNT", length = 22)
public java.lang.Double getAdvancePaymentAmount(){
return advancePaymentAmount;
}
public void setAdvancePaymentAmount(java.lang.Double advancePaymentAmount)
{
this.advancePaymentAmount = advancePaymentAmount;
}
@Column(name = "TRANFER_TO_TEXT", length = 400)
public java.lang.String getTranferToText(){
return tranferToText;
}
public void setTranferToText(java.lang.String tranferToText)
{
this.tranferToText = tranferToText;
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
@Column(name = "COMPANY_NAME", length = 1000)
public java.lang.String getCompanyName(){
return companyName;
}
public void setCompanyName(java.lang.String companyName)
{
this.companyName = companyName;
}
@Column(name = "ACCOUNT_NO", length = 400)
public java.lang.String getAccountNo(){
return accountNo;
}
public void setAccountNo(java.lang.String accountNo)
{
this.accountNo = accountNo;
}
@Column(name = "BANK_NAME", length = 1000)
public java.lang.String getBankName(){
return bankName;
}
public void setBankName(java.lang.String bankName)
{
this.bankName = bankName;
}
@Column(name = "REQUEST_DATE", length = 7)
public java.util.Date getRequestDate(){
return requestDate;
}
public void setRequestDate(java.util.Date requestDate)
{
this.requestDate = requestDate;
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
    public AdvancePaymentProposalDTO toDTO() {
        AdvancePaymentProposalDTO advancePaymentProposalDTO = new AdvancePaymentProposalDTO();
        //set cac gia tri 
        advancePaymentProposalDTO.setInvestorRepresentive(this.investorRepresentive);
        advancePaymentProposalDTO.setConstructId(this.constructId);
        advancePaymentProposalDTO.setAdvancePaymentProposalId(this.advancePaymentProposalId);
        advancePaymentProposalDTO.setCode(this.code);
        advancePaymentProposalDTO.setReceiveDepartment(this.receiveDepartment);
        advancePaymentProposalDTO.setAdvancePaymentNumber(this.advancePaymentNumber);
        advancePaymentProposalDTO.setAdvancePaymentPercent(this.advancePaymentPercent);
        advancePaymentProposalDTO.setAdvancePaymentAmount(this.advancePaymentAmount);
        advancePaymentProposalDTO.setTranferToText(this.tranferToText);
        advancePaymentProposalDTO.setCreatedDate(this.createdDate);
        advancePaymentProposalDTO.setCreatedUserId(this.createdUserId);
        advancePaymentProposalDTO.setApprovalDate(this.approvalDate);
        advancePaymentProposalDTO.setStatusCa(this.statusCa);
        advancePaymentProposalDTO.setCompanyName(this.companyName);
        advancePaymentProposalDTO.setAccountNo(this.accountNo);
        advancePaymentProposalDTO.setBankName(this.bankName);
        advancePaymentProposalDTO.setRequestDate(this.requestDate);
        advancePaymentProposalDTO.setIsActive(this.isActive);
        return advancePaymentProposalDTO;
    }
}
