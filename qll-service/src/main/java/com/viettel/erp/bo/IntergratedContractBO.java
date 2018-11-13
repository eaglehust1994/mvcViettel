/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.IntergratedContractDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "intergratedcontract")
@Table(name = "INTERGRATED_CONTRACT")
@DynamicInsert
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class IntergratedContractBO extends BaseFWModelImpl {
     
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

 public IntergratedContractBO() {
        setColId("integratedContractId");
        setColName("integratedContractId");
        setUniqueColumn(new String[]{"integratedContractId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "INTERGRATED_CONTRACT_SEQ")
            }
    )
@Column(name = "INTEGRATED_CONTRACT_ID", length = 22)
public java.lang.Long getIntegratedContractId(){
return integratedContractId;
}
public void setIntegratedContractId(java.lang.Long integratedContractId)
{
this.integratedContractId = integratedContractId;
}
@Column(name = "NEGOTIATION_MINUTES_ID", length = 22)
public java.lang.Long getNegotiationMinutesId(){
return negotiationMinutesId;
}
public void setNegotiationMinutesId(java.lang.Long negotiationMinutesId)
{
this.negotiationMinutesId = negotiationMinutesId;
}
@Column(name = "BID_PACKAGE_BLOCK_ID", length = 22)
public java.lang.Long getBidPackageBlockId(){
return bidPackageBlockId;
}
public void setBidPackageBlockId(java.lang.Long bidPackageBlockId)
{
this.bidPackageBlockId = bidPackageBlockId;
}
@Column(name = "BID_PACKAGE_ID", length = 22)
public java.lang.Long getBidPackageId(){
return bidPackageId;
}
public void setBidPackageId(java.lang.Long bidPackageId)
{
this.bidPackageId = bidPackageId;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "CONTRACT_NAME", length = 1000)
public java.lang.String getContractName(){
return contractName;
}
public void setContractName(java.lang.String contractName)
{
this.contractName = contractName;
}
@Column(name = "CONTRACT_CODE", length = 100)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "CONTRACT_NO", length = 1000)
public java.lang.String getContractNo(){
return contractNo;
}
public void setContractNo(java.lang.String contractNo)
{
this.contractNo = contractNo;
}
@Column(name = "SIGN_DATE", length = 7)
public java.util.Date getSignDate(){
return signDate;
}
public void setSignDate(java.util.Date signDate)
{
this.signDate = signDate;
}
@Column(name = "HANDOVER_TIME", length = 7)
public java.util.Date getHandoverTime(){
return handoverTime;
}
public void setHandoverTime(java.util.Date handoverTime)
{
this.handoverTime = handoverTime;
}
@Column(name = "HANDOVER_PLACE", length = 1000)
public java.lang.String getHandoverPlace(){
return handoverPlace;
}
public void setHandoverPlace(java.lang.String handoverPlace)
{
this.handoverPlace = handoverPlace;
}
@Column(name = "PLACE", length = 1000)
public java.lang.String getPlace(){
return place;
}
public void setPlace(java.lang.String place)
{
this.place = place;
}
@Column(name = "A_REP_INVESTOR_ID", length = 22)
public java.lang.Long getARepInvestorId(){
return aRepInvestorId;
}
public void setARepInvestorId(java.lang.Long aRepInvestorId)
{
this.aRepInvestorId = aRepInvestorId;
}
@Column(name = "B_PARTNER_ID", length = 22)
public java.lang.Long getBPartnerId(){
return bPartnerId;
}
public void setBPartnerId(java.lang.Long bPartnerId)
{
this.bPartnerId = bPartnerId;
}
@Column(name = "CONTRACT_CONTENT", length = 4000)
public java.lang.String getContractContent(){
return contractContent;
}
public void setContractContent(java.lang.String contractContent)
{
this.contractContent = contractContent;
}
@Column(name = "CONTRACT_VALUE_BF_TAX", length = 22)
public java.lang.Long getContractValueBfTax(){
return contractValueBfTax;
}
public void setContractValueBfTax(java.lang.Long contractValueBfTax)
{
this.contractValueBfTax = contractValueBfTax;
}
@Column(name = "TAX", length = 22)
public java.lang.Long getTax(){
return tax;
}
public void setTax(java.lang.Long tax)
{
this.tax = tax;
}
@Column(name = "CONTRACT_VALUE_AFT_TAX", length = 22)
public java.lang.Long getContractValueAftTax(){
return contractValueAftTax;
}
public void setContractValueAftTax(java.lang.Long contractValueAftTax)
{
this.contractValueAftTax = contractValueAftTax;
}
@Column(name = "PAYMENT_CURRENCY_ID", length = 22)
public java.lang.Long getPaymentCurrencyId(){
return paymentCurrencyId;
}
public void setPaymentCurrencyId(java.lang.Long paymentCurrencyId)
{
this.paymentCurrencyId = paymentCurrencyId;
}
@Column(name = "PERFORM_START_DATE", length = 1000)
public java.lang.String getPerformStartDate(){
return performStartDate;
}
public void setPerformStartDate(java.lang.String performStartDate)
{
this.performStartDate = performStartDate;
}
@Column(name = "EXECUTION_TIME", length = 22)
public java.lang.Long getExecutionTime(){
return executionTime;
}
public void setExecutionTime(java.lang.Long executionTime)
{
this.executionTime = executionTime;
}
@Column(name = "PAYMENT_STYLE", length = 22)
public java.lang.Long getPaymentStyle(){
return paymentStyle;
}
public void setPaymentStyle(java.lang.Long paymentStyle)
{
this.paymentStyle = paymentStyle;
}
@Column(name = "PAYMENT_DEADLINE", length = 1000)
public java.lang.String getPaymentDeadline(){
return paymentDeadline;
}
public void setPaymentDeadline(java.lang.String paymentDeadline)
{
this.paymentDeadline = paymentDeadline;
}
@Column(name = "LATE_PAYMENT_PENALTY_CLAUSE", length = 2000)
public java.lang.String getLatePaymentPenaltyClause(){
return latePaymentPenaltyClause;
}
public void setLatePaymentPenaltyClause(java.lang.String latePaymentPenaltyClause)
{
this.latePaymentPenaltyClause = latePaymentPenaltyClause;
}
@Column(name = "CONTRACT_GUARANTEE_METHOD", length = 22)
public java.lang.Long getContractGuaranteeMethod(){
return contractGuaranteeMethod;
}
public void setContractGuaranteeMethod(java.lang.Long contractGuaranteeMethod)
{
this.contractGuaranteeMethod = contractGuaranteeMethod;
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
@Column(name = "CREATED_GROUP_ID", length = 22)
public java.lang.Long getCreatedGroupId(){
return createdGroupId;
}
public void setCreatedGroupId(java.lang.Long createdGroupId)
{
this.createdGroupId = createdGroupId;
}
@Column(name = "WATER_MARK_CODE", length = 20)
public java.lang.String getWaterMarkCode(){
return waterMarkCode;
}
public void setWaterMarkCode(java.lang.String waterMarkCode)
{
this.waterMarkCode = waterMarkCode;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "SIGN_STATUS", length = 22)
public java.lang.Long getSignStatus(){
return signStatus;
}
public void setSignStatus(java.lang.Long signStatus)
{
this.signStatus = signStatus;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "APPROVAL_GROUP_ID", length = 22)
public java.lang.Long getApprovalGroupId(){
return approvalGroupId;
}
public void setApprovalGroupId(java.lang.Long approvalGroupId)
{
this.approvalGroupId = approvalGroupId;
}
@Column(name = "APPROVAL_USER_ID", length = 22)
public java.lang.Long getApprovalUserId(){
return approvalUserId;
}
public void setApprovalUserId(java.lang.Long approvalUserId)
{
this.approvalUserId = approvalUserId;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "PAYMENT_TIME_NUMBER", length = 22)
public java.lang.Long getPaymentTimeNumber(){
return paymentTimeNumber;
}
public void setPaymentTimeNumber(java.lang.Long paymentTimeNumber)
{
this.paymentTimeNumber = paymentTimeNumber;
}
@Column(name = "CURRENCY_COUNT", length = 22)
public java.lang.Long getCurrencyCount(){
return currencyCount;
}
public void setCurrencyCount(java.lang.Long currencyCount)
{
this.currencyCount = currencyCount;
}
@Column(name = "PAYMENT_CURRENCY_FOREIGN_ID", length = 22)
public java.lang.Long getPaymentCurrencyForeignId(){
return paymentCurrencyForeignId;
}
public void setPaymentCurrencyForeignId(java.lang.Long paymentCurrencyForeignId)
{
this.paymentCurrencyForeignId = paymentCurrencyForeignId;
}
@Column(name = "CONTRACT_VALUE_BF_TAX_F", length = 22)
public java.lang.Long getContractValueBfTaxF(){
return contractValueBfTaxF;
}
public void setContractValueBfTaxF(java.lang.Long contractValueBfTaxF)
{
this.contractValueBfTaxF = contractValueBfTaxF;
}
@Column(name = "TAX_F", length = 22)
public java.lang.Long getTaxF(){
return taxF;
}
public void setTaxF(java.lang.Long taxF)
{
this.taxF = taxF;
}
@Column(name = "CONTRACT_VALUE_AFT_TAX_F", length = 22)
public java.lang.Long getContractValueAftTaxF(){
return contractValueAftTaxF;
}
public void setContractValueAftTaxF(java.lang.Long contractValueAftTaxF)
{
this.contractValueAftTaxF = contractValueAftTaxF;
}
@Column(name = "IS_APPENDIX", length = 22)
public java.lang.Long getIsAppendix(){
return isAppendix;
}
public void setIsAppendix(java.lang.Long isAppendix)
{
this.isAppendix = isAppendix;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "APPENDIX_NO", length = 1000)
public java.lang.String getAppendixNo(){
return appendixNo;
}
public void setAppendixNo(java.lang.String appendixNo)
{
this.appendixNo = appendixNo;
}
@Column(name = "APPENDIX_SIGN_DATE", length = 7)
public java.util.Date getAppendixSignDate(){
return appendixSignDate;
}
public void setAppendixSignDate(java.util.Date appendixSignDate)
{
this.appendixSignDate = appendixSignDate;
}
@Column(name = "APPENDIX_CONTENT", length = 1000)
public java.lang.String getAppendixContent(){
return appendixContent;
}
public void setAppendixContent(java.lang.String appendixContent)
{
this.appendixContent = appendixContent;
}
@Column(name = "APPENDIX_CREATED_DATE", length = 7)
public java.util.Date getAppendixCreatedDate(){
return appendixCreatedDate;
}
public void setAppendixCreatedDate(java.util.Date appendixCreatedDate)
{
this.appendixCreatedDate = appendixCreatedDate;
}
@Column(name = "APPENDIX_CREATED_USER_ID", length = 22)
public java.lang.Long getAppendixCreatedUserId(){
return appendixCreatedUserId;
}
public void setAppendixCreatedUserId(java.lang.Long appendixCreatedUserId)
{
this.appendixCreatedUserId = appendixCreatedUserId;
}
@Column(name = "IS_LAST_APPENDIX", length = 22)
public java.lang.Long getIsLastAppendix(){
return isLastAppendix;
}
public void setIsLastAppendix(java.lang.Long isLastAppendix)
{
this.isLastAppendix = isLastAppendix;
}
@Column(name = "EXPECT_START_DATE", length = 7)
public java.util.Date getExpectStartDate(){
return expectStartDate;
}
public void setExpectStartDate(java.util.Date expectStartDate)
{
this.expectStartDate = expectStartDate;
}
@Column(name = "EXPECT_FINISH_DATE", length = 7)
public java.util.Date getExpectFinishDate(){
return expectFinishDate;
}
public void setExpectFinishDate(java.util.Date expectFinishDate)
{
this.expectFinishDate = expectFinishDate;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "COMMENT_CA", length = 1000)
public java.lang.String getCommentCa(){
return commentCa;
}
public void setCommentCa(java.lang.String commentCa)
{
this.commentCa = commentCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
   

    @Override
    public IntergratedContractDTO toDTO() {
        IntergratedContractDTO intergratedContractDTO = new IntergratedContractDTO();
        //set cac gia tri 
        intergratedContractDTO.setIntegratedContractId(this.integratedContractId);
        intergratedContractDTO.setNegotiationMinutesId(this.negotiationMinutesId);
        intergratedContractDTO.setBidPackageBlockId(this.bidPackageBlockId);
        intergratedContractDTO.setBidPackageId(this.bidPackageId);
        intergratedContractDTO.setType(this.type);
        intergratedContractDTO.setContractName(this.contractName);
        intergratedContractDTO.setContractCode(this.contractCode);
        intergratedContractDTO.setContractNo(this.contractNo);
        intergratedContractDTO.setSignDate(this.signDate);
        intergratedContractDTO.setHandoverTime(this.handoverTime);
        intergratedContractDTO.setHandoverPlace(this.handoverPlace);
        intergratedContractDTO.setPlace(this.place);
        intergratedContractDTO.setARepInvestorId(this.aRepInvestorId);
        intergratedContractDTO.setBPartnerId(this.bPartnerId);
        intergratedContractDTO.setContractContent(this.contractContent);
        intergratedContractDTO.setContractValueBfTax(this.contractValueBfTax);
        intergratedContractDTO.setTax(this.tax);
        intergratedContractDTO.setContractValueAftTax(this.contractValueAftTax);
        intergratedContractDTO.setPaymentCurrencyId(this.paymentCurrencyId);
        intergratedContractDTO.setPerformStartDate(this.performStartDate);
        intergratedContractDTO.setExecutionTime(this.executionTime);
        intergratedContractDTO.setPaymentStyle(this.paymentStyle);
        intergratedContractDTO.setPaymentDeadline(this.paymentDeadline);
        intergratedContractDTO.setLatePaymentPenaltyClause(this.latePaymentPenaltyClause);
        intergratedContractDTO.setContractGuaranteeMethod(this.contractGuaranteeMethod);
        intergratedContractDTO.setCreatedDate(this.createdDate);
        intergratedContractDTO.setCreatedUserId(this.createdUserId);
        intergratedContractDTO.setCreatedGroupId(this.createdGroupId);
        intergratedContractDTO.setWaterMarkCode(this.waterMarkCode);
        intergratedContractDTO.setStatus(this.status);
        intergratedContractDTO.setSignStatus(this.signStatus);
        intergratedContractDTO.setApprovalDate(this.approvalDate);
        intergratedContractDTO.setApprovalGroupId(this.approvalGroupId);
        intergratedContractDTO.setApprovalUserId(this.approvalUserId);
        intergratedContractDTO.setIsActive(this.isActive);
        intergratedContractDTO.setPaymentTimeNumber(this.paymentTimeNumber);
        intergratedContractDTO.setCurrencyCount(this.currencyCount);
        intergratedContractDTO.setPaymentCurrencyForeignId(this.paymentCurrencyForeignId);
        intergratedContractDTO.setContractValueBfTaxF(this.contractValueBfTaxF);
        intergratedContractDTO.setTaxF(this.taxF);
        intergratedContractDTO.setContractValueAftTaxF(this.contractValueAftTaxF);
        intergratedContractDTO.setIsAppendix(this.isAppendix);
        intergratedContractDTO.setParentId(this.parentId);
        intergratedContractDTO.setAppendixNo(this.appendixNo);
        intergratedContractDTO.setAppendixSignDate(this.appendixSignDate);
        intergratedContractDTO.setAppendixContent(this.appendixContent);
        intergratedContractDTO.setAppendixCreatedDate(this.appendixCreatedDate);
        intergratedContractDTO.setAppendixCreatedUserId(this.appendixCreatedUserId);
        intergratedContractDTO.setIsLastAppendix(this.isLastAppendix);
        intergratedContractDTO.setExpectStartDate(this.expectStartDate);
        intergratedContractDTO.setExpectFinishDate(this.expectFinishDate);
        intergratedContractDTO.setStatusCa(this.statusCa);
        intergratedContractDTO.setCommentCa(this.commentCa);
        intergratedContractDTO.setDocumentCaId(this.documentCaId);
        return intergratedContractDTO;
    }
}
