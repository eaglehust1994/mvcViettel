/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.AdvancePaymentProposalBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ADVANCE_PAYMENT_PROPOSALBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvancePaymentProposalDTO extends BaseFWDTOImpl<AdvancePaymentProposalBO> {

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

    @Override
    public AdvancePaymentProposalBO toModel() {
        AdvancePaymentProposalBO advancePaymentProposalBO = new AdvancePaymentProposalBO();
        advancePaymentProposalBO.setInvestorRepresentive(this.investorRepresentive);
        advancePaymentProposalBO.setConstructId(this.constructId);
        advancePaymentProposalBO.setAdvancePaymentProposalId(this.advancePaymentProposalId);
        advancePaymentProposalBO.setCode(this.code);
        advancePaymentProposalBO.setReceiveDepartment(this.receiveDepartment);
        advancePaymentProposalBO.setAdvancePaymentNumber(this.advancePaymentNumber);
        advancePaymentProposalBO.setAdvancePaymentPercent(this.advancePaymentPercent);
        advancePaymentProposalBO.setAdvancePaymentAmount(this.advancePaymentAmount);
        advancePaymentProposalBO.setTranferToText(this.tranferToText);
        advancePaymentProposalBO.setCreatedDate(this.createdDate);
        advancePaymentProposalBO.setCreatedUserId(this.createdUserId);
        advancePaymentProposalBO.setApprovalDate(this.approvalDate);
        advancePaymentProposalBO.setStatusCa(this.statusCa);
        advancePaymentProposalBO.setCompanyName(this.companyName);
        advancePaymentProposalBO.setAccountNo(this.accountNo);
        advancePaymentProposalBO.setBankName(this.bankName);
        advancePaymentProposalBO.setRequestDate(this.requestDate);
        advancePaymentProposalBO.setIsActive(this.isActive);
        return advancePaymentProposalBO;
    }

    public java.lang.String getInvestorRepresentive(){
    return investorRepresentive;
    }
    public void setInvestorRepresentive(java.lang.String investorRepresentive)
    {
    this.investorRepresentive = investorRepresentive;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    @Override
     public Long getFWModelId() {
        return advancePaymentProposalId;
    }
   
    @Override
    public String catchName() {
        return getAdvancePaymentProposalId().toString();
    }
    public java.lang.Long getAdvancePaymentProposalId(){
    return advancePaymentProposalId;
    }
    public void setAdvancePaymentProposalId(java.lang.Long advancePaymentProposalId)
    {
    this.advancePaymentProposalId = advancePaymentProposalId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getReceiveDepartment(){
    return receiveDepartment;
    }
    public void setReceiveDepartment(java.lang.String receiveDepartment)
    {
    this.receiveDepartment = receiveDepartment;
    }
    
    public java.lang.Long getAdvancePaymentNumber(){
    return advancePaymentNumber;
    }
    public void setAdvancePaymentNumber(java.lang.Long advancePaymentNumber)
    {
    this.advancePaymentNumber = advancePaymentNumber;
    }
    
    public java.lang.Long getAdvancePaymentPercent(){
    return advancePaymentPercent;
    }
    public void setAdvancePaymentPercent(java.lang.Long advancePaymentPercent)
    {
    this.advancePaymentPercent = advancePaymentPercent;
    }
    
    public java.lang.Double getAdvancePaymentAmount(){
    return advancePaymentAmount;
    }
    public void setAdvancePaymentAmount(java.lang.Double advancePaymentAmount)
    {
    this.advancePaymentAmount = advancePaymentAmount;
    }
    
    public java.lang.String getTranferToText(){
    return tranferToText;
    }
    public void setTranferToText(java.lang.String tranferToText)
    {
    this.tranferToText = tranferToText;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.String getCompanyName(){
    return companyName;
    }
    public void setCompanyName(java.lang.String companyName)
    {
    this.companyName = companyName;
    }
    
    public java.lang.String getAccountNo(){
    return accountNo;
    }
    public void setAccountNo(java.lang.String accountNo)
    {
    this.accountNo = accountNo;
    }
    
    public java.lang.String getBankName(){
    return bankName;
    }
    public void setBankName(java.lang.String bankName)
    {
    this.bankName = bankName;
    }
    
    public java.util.Date getRequestDate(){
    return requestDate;
    }
    public void setRequestDate(java.util.Date requestDate)
    {
    this.requestDate = requestDate;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
   
}
