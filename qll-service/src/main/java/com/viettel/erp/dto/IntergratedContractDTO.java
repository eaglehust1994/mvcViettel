/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.IntergratedContractBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "INTERGRATED_CONTRACTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntergratedContractDTO extends BaseFWDTOImpl<IntergratedContractBO> {

private java.lang.Long integratedContractId;
private java.lang.Long negotiationMinutesId;
private java.lang.Long bidPackageBlockId;
private java.lang.Long bidPackageId;
private java.lang.Long type;
private java.lang.String contractName;
private java.lang.String contractCode;
private java.lang.String contractNo;
private java.util.Date signDate;
private java.util.Date handoverTime;
private java.lang.String handoverPlace;
private java.lang.String place;
private java.lang.Long aRepInvestorId;
private java.lang.Long bPartnerId;
private java.lang.String contractContent;
private java.lang.Long contractValueBfTax;
private java.lang.Long tax;
private java.lang.Long contractValueAftTax;
private java.lang.Long paymentCurrencyId;
private java.lang.String performStartDate;
private java.lang.Long executionTime;
private java.lang.Long paymentStyle;
private java.lang.String paymentDeadline;
private java.lang.String latePaymentPenaltyClause;
private java.lang.Long contractGuaranteeMethod;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long createdGroupId;
private java.lang.String waterMarkCode;
private java.lang.Long status;
private java.lang.Long signStatus;
private java.util.Date approvalDate;
private java.lang.Long approvalGroupId;
private java.lang.Long approvalUserId;
private java.lang.Long isActive;
private java.lang.Long paymentTimeNumber;
private java.lang.Long currencyCount;
private java.lang.Long paymentCurrencyForeignId;
private java.lang.Long contractValueBfTaxF;
private java.lang.Long taxF;
private java.lang.Long contractValueAftTaxF;
private java.lang.Long isAppendix;
private java.lang.Long parentId;
private java.lang.String appendixNo;
private java.util.Date appendixSignDate;
private java.lang.String appendixContent;
private java.util.Date appendixCreatedDate;
private java.lang.Long appendixCreatedUserId;
private java.lang.Long isLastAppendix;
private java.util.Date expectStartDate;
private java.util.Date expectFinishDate;
private java.lang.Long statusCa;
private java.lang.String commentCa;
private java.lang.Long documentCaId;
private java.lang.String constrtCode;
private java.lang.String constrtName;
private java.lang.String constrtAddress;
private java.lang.String code;
private java.lang.Long constructId;

public java.lang.Long getConstructId() {
	return constructId;
}

public void setConstructId(java.lang.Long constructId) {
	this.constructId = constructId;
}

public java.lang.String getConstrtCode() {
	return constrtCode;
}

public java.lang.String getCode() {
	return code;
}

public void setCode(java.lang.String code) {
	this.code = code;
}

public void setConstrtCode(java.lang.String constrtCode) {
	this.constrtCode = constrtCode;
}

public java.lang.String getConstrtName() {
	return constrtName;
}

public void setConstrtName(java.lang.String constrtName) {
	this.constrtName = constrtName;
}

public java.lang.String getConstrtAddress() {
	return constrtAddress;
}

public void setConstrtAddress(java.lang.String constrtAddress) {
	this.constrtAddress = constrtAddress;
}

	@Override
    public IntergratedContractBO toModel() {
        IntergratedContractBO intergratedContractBO = new IntergratedContractBO();
        intergratedContractBO.setIntegratedContractId(this.integratedContractId);
        intergratedContractBO.setNegotiationMinutesId(this.negotiationMinutesId);
        intergratedContractBO.setBidPackageBlockId(this.bidPackageBlockId);
        intergratedContractBO.setBidPackageId(this.bidPackageId);
        intergratedContractBO.setType(this.type);
        intergratedContractBO.setContractName(this.contractName);
        intergratedContractBO.setContractCode(this.contractCode);
        intergratedContractBO.setContractNo(this.contractNo);
        intergratedContractBO.setSignDate(this.signDate);
        intergratedContractBO.setHandoverTime(this.handoverTime);
        intergratedContractBO.setHandoverPlace(this.handoverPlace);
        intergratedContractBO.setPlace(this.place);
        intergratedContractBO.setARepInvestorId(this.aRepInvestorId);
        intergratedContractBO.setBPartnerId(this.bPartnerId);
        intergratedContractBO.setContractContent(this.contractContent);
        intergratedContractBO.setContractValueBfTax(this.contractValueBfTax);
        intergratedContractBO.setTax(this.tax);
        intergratedContractBO.setContractValueAftTax(this.contractValueAftTax);
        intergratedContractBO.setPaymentCurrencyId(this.paymentCurrencyId);
        intergratedContractBO.setPerformStartDate(this.performStartDate);
        intergratedContractBO.setExecutionTime(this.executionTime);
        intergratedContractBO.setPaymentStyle(this.paymentStyle);
        intergratedContractBO.setPaymentDeadline(this.paymentDeadline);
        intergratedContractBO.setLatePaymentPenaltyClause(this.latePaymentPenaltyClause);
        intergratedContractBO.setContractGuaranteeMethod(this.contractGuaranteeMethod);
        intergratedContractBO.setCreatedDate(this.createdDate);
        intergratedContractBO.setCreatedUserId(this.createdUserId);
        intergratedContractBO.setCreatedGroupId(this.createdGroupId);
        intergratedContractBO.setWaterMarkCode(this.waterMarkCode);
        intergratedContractBO.setStatus(this.status);
        intergratedContractBO.setSignStatus(this.signStatus);
        intergratedContractBO.setApprovalDate(this.approvalDate);
        intergratedContractBO.setApprovalGroupId(this.approvalGroupId);
        intergratedContractBO.setApprovalUserId(this.approvalUserId);
        intergratedContractBO.setIsActive(this.isActive);
        intergratedContractBO.setPaymentTimeNumber(this.paymentTimeNumber);
        intergratedContractBO.setCurrencyCount(this.currencyCount);
        intergratedContractBO.setPaymentCurrencyForeignId(this.paymentCurrencyForeignId);
        intergratedContractBO.setContractValueBfTaxF(this.contractValueBfTaxF);
        intergratedContractBO.setTaxF(this.taxF);
        intergratedContractBO.setContractValueAftTaxF(this.contractValueAftTaxF);
        intergratedContractBO.setIsAppendix(this.isAppendix);
        intergratedContractBO.setParentId(this.parentId);
        intergratedContractBO.setAppendixNo(this.appendixNo);
        intergratedContractBO.setAppendixSignDate(this.appendixSignDate);
        intergratedContractBO.setAppendixContent(this.appendixContent);
        intergratedContractBO.setAppendixCreatedDate(this.appendixCreatedDate);
        intergratedContractBO.setAppendixCreatedUserId(this.appendixCreatedUserId);
        intergratedContractBO.setIsLastAppendix(this.isLastAppendix);
        intergratedContractBO.setExpectStartDate(this.expectStartDate);
        intergratedContractBO.setExpectFinishDate(this.expectFinishDate);
        intergratedContractBO.setStatusCa(this.statusCa);
        intergratedContractBO.setCommentCa(this.commentCa);
        intergratedContractBO.setDocumentCaId(this.documentCaId);
        return intergratedContractBO;
    }

    @Override
     public Long getFWModelId() {
        return integratedContractId;
    }
   
    @Override
    public String catchName() {
        return getIntegratedContractId().toString();
    }
    public java.lang.Long getIntegratedContractId(){
    return integratedContractId;
    }
    public void setIntegratedContractId(java.lang.Long integratedContractId)
    {
    this.integratedContractId = integratedContractId;
    }
    
    public java.lang.Long getNegotiationMinutesId(){
    return negotiationMinutesId;
    }
    public void setNegotiationMinutesId(java.lang.Long negotiationMinutesId)
    {
    this.negotiationMinutesId = negotiationMinutesId;
    }
    
    public java.lang.Long getBidPackageBlockId(){
    return bidPackageBlockId;
    }
    public void setBidPackageBlockId(java.lang.Long bidPackageBlockId)
    {
    this.bidPackageBlockId = bidPackageBlockId;
    }
    
    public java.lang.Long getBidPackageId(){
    return bidPackageId;
    }
    public void setBidPackageId(java.lang.Long bidPackageId)
    {
    this.bidPackageId = bidPackageId;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.String getContractName(){
    return contractName;
    }
    public void setContractName(java.lang.String contractName)
    {
    this.contractName = contractName;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
    public java.lang.String getContractNo(){
    return contractNo;
    }
    public void setContractNo(java.lang.String contractNo)
    {
    this.contractNo = contractNo;
    }
    
    public java.util.Date getSignDate(){
    return signDate;
    }
    public void setSignDate(java.util.Date signDate)
    {
    this.signDate = signDate;
    }
    
    public java.util.Date getHandoverTime(){
    return handoverTime;
    }
    public void setHandoverTime(java.util.Date handoverTime)
    {
    this.handoverTime = handoverTime;
    }
    
    public java.lang.String getHandoverPlace(){
    return handoverPlace;
    }
    public void setHandoverPlace(java.lang.String handoverPlace)
    {
    this.handoverPlace = handoverPlace;
    }
    
    public java.lang.String getPlace(){
    return place;
    }
    public void setPlace(java.lang.String place)
    {
    this.place = place;
    }
    
    public java.lang.Long getARepInvestorId(){
    return aRepInvestorId;
    }
    public void setARepInvestorId(java.lang.Long aRepInvestorId)
    {
    this.aRepInvestorId = aRepInvestorId;
    }
    
    public java.lang.Long getBPartnerId(){
    return bPartnerId;
    }
    public void setBPartnerId(java.lang.Long bPartnerId)
    {
    this.bPartnerId = bPartnerId;
    }
    
    public java.lang.String getContractContent(){
    return contractContent;
    }
    public void setContractContent(java.lang.String contractContent)
    {
    this.contractContent = contractContent;
    }
    
    public java.lang.Long getContractValueBfTax(){
    return contractValueBfTax;
    }
    public void setContractValueBfTax(java.lang.Long contractValueBfTax)
    {
    this.contractValueBfTax = contractValueBfTax;
    }
    
    public java.lang.Long getTax(){
    return tax;
    }
    public void setTax(java.lang.Long tax)
    {
    this.tax = tax;
    }
    
    public java.lang.Long getContractValueAftTax(){
    return contractValueAftTax;
    }
    public void setContractValueAftTax(java.lang.Long contractValueAftTax)
    {
    this.contractValueAftTax = contractValueAftTax;
    }
    
    public java.lang.Long getPaymentCurrencyId(){
    return paymentCurrencyId;
    }
    public void setPaymentCurrencyId(java.lang.Long paymentCurrencyId)
    {
    this.paymentCurrencyId = paymentCurrencyId;
    }
    
    public java.lang.String getPerformStartDate(){
    return performStartDate;
    }
    public void setPerformStartDate(java.lang.String performStartDate)
    {
    this.performStartDate = performStartDate;
    }
    
    public java.lang.Long getExecutionTime(){
    return executionTime;
    }
    public void setExecutionTime(java.lang.Long executionTime)
    {
    this.executionTime = executionTime;
    }
    
    public java.lang.Long getPaymentStyle(){
    return paymentStyle;
    }
    public void setPaymentStyle(java.lang.Long paymentStyle)
    {
    this.paymentStyle = paymentStyle;
    }
    
    public java.lang.String getPaymentDeadline(){
    return paymentDeadline;
    }
    public void setPaymentDeadline(java.lang.String paymentDeadline)
    {
    this.paymentDeadline = paymentDeadline;
    }
    
    public java.lang.String getLatePaymentPenaltyClause(){
    return latePaymentPenaltyClause;
    }
    public void setLatePaymentPenaltyClause(java.lang.String latePaymentPenaltyClause)
    {
    this.latePaymentPenaltyClause = latePaymentPenaltyClause;
    }
    
    public java.lang.Long getContractGuaranteeMethod(){
    return contractGuaranteeMethod;
    }
    public void setContractGuaranteeMethod(java.lang.Long contractGuaranteeMethod)
    {
    this.contractGuaranteeMethod = contractGuaranteeMethod;
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
    
    public java.lang.Long getCreatedGroupId(){
    return createdGroupId;
    }
    public void setCreatedGroupId(java.lang.Long createdGroupId)
    {
    this.createdGroupId = createdGroupId;
    }
    
    public java.lang.String getWaterMarkCode(){
    return waterMarkCode;
    }
    public void setWaterMarkCode(java.lang.String waterMarkCode)
    {
    this.waterMarkCode = waterMarkCode;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Long getSignStatus(){
    return signStatus;
    }
    public void setSignStatus(java.lang.Long signStatus)
    {
    this.signStatus = signStatus;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getApprovalGroupId(){
    return approvalGroupId;
    }
    public void setApprovalGroupId(java.lang.Long approvalGroupId)
    {
    this.approvalGroupId = approvalGroupId;
    }
    
    public java.lang.Long getApprovalUserId(){
    return approvalUserId;
    }
    public void setApprovalUserId(java.lang.Long approvalUserId)
    {
    this.approvalUserId = approvalUserId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getPaymentTimeNumber(){
    return paymentTimeNumber;
    }
    public void setPaymentTimeNumber(java.lang.Long paymentTimeNumber)
    {
    this.paymentTimeNumber = paymentTimeNumber;
    }
    
    public java.lang.Long getCurrencyCount(){
    return currencyCount;
    }
    public void setCurrencyCount(java.lang.Long currencyCount)
    {
    this.currencyCount = currencyCount;
    }
    
    public java.lang.Long getPaymentCurrencyForeignId(){
    return paymentCurrencyForeignId;
    }
    public void setPaymentCurrencyForeignId(java.lang.Long paymentCurrencyForeignId)
    {
    this.paymentCurrencyForeignId = paymentCurrencyForeignId;
    }
    
    public java.lang.Long getContractValueBfTaxF(){
    return contractValueBfTaxF;
    }
    public void setContractValueBfTaxF(java.lang.Long contractValueBfTaxF)
    {
    this.contractValueBfTaxF = contractValueBfTaxF;
    }
    
    public java.lang.Long getTaxF(){
    return taxF;
    }
    public void setTaxF(java.lang.Long taxF)
    {
    this.taxF = taxF;
    }
    
    public java.lang.Long getContractValueAftTaxF(){
    return contractValueAftTaxF;
    }
    public void setContractValueAftTaxF(java.lang.Long contractValueAftTaxF)
    {
    this.contractValueAftTaxF = contractValueAftTaxF;
    }
    
    public java.lang.Long getIsAppendix(){
    return isAppendix;
    }
    public void setIsAppendix(java.lang.Long isAppendix)
    {
    this.isAppendix = isAppendix;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.String getAppendixNo(){
    return appendixNo;
    }
    public void setAppendixNo(java.lang.String appendixNo)
    {
    this.appendixNo = appendixNo;
    }
    
    public java.util.Date getAppendixSignDate(){
    return appendixSignDate;
    }
    public void setAppendixSignDate(java.util.Date appendixSignDate)
    {
    this.appendixSignDate = appendixSignDate;
    }
    
    public java.lang.String getAppendixContent(){
    return appendixContent;
    }
    public void setAppendixContent(java.lang.String appendixContent)
    {
    this.appendixContent = appendixContent;
    }
    
    public java.util.Date getAppendixCreatedDate(){
    return appendixCreatedDate;
    }
    public void setAppendixCreatedDate(java.util.Date appendixCreatedDate)
    {
    this.appendixCreatedDate = appendixCreatedDate;
    }
    
    public java.lang.Long getAppendixCreatedUserId(){
    return appendixCreatedUserId;
    }
    public void setAppendixCreatedUserId(java.lang.Long appendixCreatedUserId)
    {
    this.appendixCreatedUserId = appendixCreatedUserId;
    }
    
    public java.lang.Long getIsLastAppendix(){
    return isLastAppendix;
    }
    public void setIsLastAppendix(java.lang.Long isLastAppendix)
    {
    this.isLastAppendix = isLastAppendix;
    }
    
    public java.util.Date getExpectStartDate(){
    return expectStartDate;
    }
    public void setExpectStartDate(java.util.Date expectStartDate)
    {
    this.expectStartDate = expectStartDate;
    }
    
    public java.util.Date getExpectFinishDate(){
    return expectFinishDate;
    }
    public void setExpectFinishDate(java.util.Date expectFinishDate)
    {
    this.expectFinishDate = expectFinishDate;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.String getCommentCa(){
    return commentCa;
    }
    public void setCommentCa(java.lang.String commentCa)
    {
    this.commentCa = commentCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
   
}
