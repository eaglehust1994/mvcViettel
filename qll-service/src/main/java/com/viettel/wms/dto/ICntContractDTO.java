/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ICntContractBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "I_CNT_CONTRACTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICntContractDTO extends wmsBaseDTO<ICntContractBO> {

private java.lang.String agreementCode;
private java.lang.Long groupUsingId;
private java.lang.Long catServiceId;
private java.lang.Long contractAgreement;
private java.lang.String reason;
private java.lang.String contractCode;
private java.lang.Long statusSign;
private java.lang.String commentStatusSign;
private java.lang.Long contractNature;
private java.lang.Long nextStepGroupId;
private java.lang.Long procPackageId;
private java.lang.Long procComponentId;
private java.lang.String handoverPlace;
private java.util.Date handoverTime;
private java.lang.Long aRepInvestorId;
private java.lang.Double contractValueBfTax;
private java.lang.Double tax;
private java.lang.String paymentDeadline;
private java.lang.String workScope;
private java.lang.String licensingProcedureConstr;
private java.util.Date performStartDate;
private java.lang.Long executionTime;
private java.lang.String latePaymentPenaltyClause;
private java.lang.Long contractGuaranteeMethod;
private java.lang.Long paymentTimeNumber;
private java.lang.Long paymentStyle;
private java.lang.Long currencyCount;
private java.lang.Long status;
private java.lang.Long contractType;
private java.lang.Long negotiationMinutesId;
private java.lang.Long bidPackageId;
private java.lang.Long bidPackageBlockId;
private java.lang.Long contractId;
private java.lang.String code;
private java.lang.String description;
private java.lang.Long goithauId;
private java.lang.Long partnerId;
private java.lang.Long payModeId;
private java.util.Date signedDate;
private java.lang.Long statusId;
private java.lang.Long isPayment;
private java.util.Date createdDate;
private java.lang.Long creatorId;
private java.lang.Long merStatus;
private java.lang.Long lothauId;
private java.lang.Long cntNgtId;
private java.lang.Long currencyId;
private java.lang.Long cntTypeId;
private java.lang.Double taxRate;
private java.lang.Long constructorCost;
private java.lang.Long spareCost;
private java.lang.Long otherCost;
private java.lang.Long merCost;
private java.lang.Long discount;
private java.lang.Long liquidateCost;
private java.lang.Long signGroupId;
private java.lang.Double currencyRate;
private java.lang.Long hasWeight;
private java.lang.Long originalId;
private java.lang.Long discountUnit;
private java.lang.String merDescription;
private java.lang.Long totalValue;
private java.lang.Long projectMngCost;
private java.lang.Long warrantyBegin;
private java.util.Date realLiquidateDate;
private java.lang.Long importGroupId;
private java.lang.String supervisor;
private java.lang.Long importMerGroupId;
private java.lang.Long paymentGroupId;
private java.lang.Long payCurrencyId;
private java.lang.Long requireChangeMer;
private java.lang.Long isOld;
private java.util.Date warrantyBeginDate;
private java.lang.String packageCode;
private java.lang.Long merCostRemain;
private java.lang.Long contractFrameId;
private java.lang.Long accountType;
private java.util.Date depreciationDate;
private java.lang.Long projectId;
private java.lang.Long warrantyPeriod;
private java.lang.Long warrantyPartnerId;
private java.lang.Long groupPricePercentId;
private java.lang.Long groupPriceShipmentId;
private java.lang.Long useGroupId;
private java.lang.Long isProposal;
private java.lang.Long isConfig;
private java.util.Date expireDate;
private java.lang.Long revenueLimit;
private java.lang.String signer;
private java.lang.Long paymentExpire;
private java.lang.String partnerName;
private java.util.Date cancelDate;
private java.lang.String cancelReason;
private java.lang.String liquidateReason;
private java.util.Date mapDate;
private java.lang.Long duration;
private java.lang.String isActive;
private java.lang.Long isRealValue;
private java.lang.Long isExtension;
private java.lang.Long isExtended;
private java.lang.String trackingCode;
private java.lang.String content;
private java.util.Date proposalFacDate;
private java.lang.Long proposalFacValue;
private java.util.Date acceptDate;
private java.lang.Long processGroupId;
private java.lang.String liquidateCode;
private java.lang.String singerLiquidate;
private java.lang.String acceptCode;
private java.lang.String singerAccept;
private java.lang.Long kcsDocStatus;
private java.lang.Long checkApproval;
private java.lang.String billNumber;
private java.lang.Long catFormPaymentId;
private java.lang.String financeCntCode;
private java.lang.Double financeCntValue;
private java.lang.Double financeCntPercentMer;
private java.lang.Long cntSubType;
private java.util.Date guaranteeDateFrom;
private java.util.Date finishedDate;
private java.lang.Long formsId;
private java.util.Date guaranteeDateTo;
private java.lang.String poCode;
private java.lang.Double guaranteeMerNo;
private java.lang.Long isType;
private java.lang.Long ratioTypeId;
private java.lang.Double errRatio;
private java.lang.String errRatioComment;
private java.lang.Double guaranteeMerPercent;
private java.lang.Double merQuantity;
private java.lang.String merType;
private java.lang.Long haveHshc;
private java.lang.Long projInvestProjectId;
private java.lang.Long cntKindType;
private java.lang.Long isApprove;
private java.lang.Long provinceId;
private java.lang.Long isLock;
private java.lang.String reasonDeny;
private java.lang.Long investmentProposalGroupId;
private java.lang.Long integratedContractId;
private java.lang.Long capitalSourceType;
private java.lang.Long extendStatusId;
private java.lang.Long paymentStatusId;
private java.lang.Long lockPayment;
private java.lang.String contractName;
private java.lang.Long agreementType;
private java.lang.Long contractPriceFc;
private java.lang.String transRate;
private java.lang.Long foreignCurrentId;
private java.lang.String name;
private java.lang.String value;

    public java.lang.String getContractCode() {
	return contractCode;
}

public void setContractCode(java.lang.String contractCode) {
	this.contractCode = contractCode;
}

	@Override
    public ICntContractBO toModel() {
        ICntContractBO iCntContractBO = new ICntContractBO();
        iCntContractBO.setAgreementCode(this.agreementCode);
        iCntContractBO.setGroupUsingId(this.groupUsingId);
        iCntContractBO.setCatServiceId(this.catServiceId);
        iCntContractBO.setContractAgreement(this.contractAgreement);
        iCntContractBO.setReason(this.reason);
        iCntContractBO.setStatusSign(this.statusSign);
        iCntContractBO.setCommentStatusSign(this.commentStatusSign);
        iCntContractBO.setContractNature(this.contractNature);
        iCntContractBO.setNextStepGroupId(this.nextStepGroupId);
        iCntContractBO.setProcPackageId(this.procPackageId);
        iCntContractBO.setProcComponentId(this.procComponentId);
        iCntContractBO.setHandoverPlace(this.handoverPlace);
        iCntContractBO.setHandoverTime(this.handoverTime);
        iCntContractBO.setARepInvestorId(this.aRepInvestorId);
        iCntContractBO.setContractValueBfTax(this.contractValueBfTax);
        iCntContractBO.setTax(this.tax);
        iCntContractBO.setPaymentDeadline(this.paymentDeadline);
        iCntContractBO.setWorkScope(this.workScope);
        iCntContractBO.setLicensingProcedureConstr(this.licensingProcedureConstr);
        iCntContractBO.setPerformStartDate(this.performStartDate);
        iCntContractBO.setExecutionTime(this.executionTime);
        iCntContractBO.setLatePaymentPenaltyClause(this.latePaymentPenaltyClause);
        iCntContractBO.setContractGuaranteeMethod(this.contractGuaranteeMethod);
        iCntContractBO.setPaymentTimeNumber(this.paymentTimeNumber);
        iCntContractBO.setPaymentStyle(this.paymentStyle);
        iCntContractBO.setCurrencyCount(this.currencyCount);
        iCntContractBO.setStatus(this.status);
        iCntContractBO.setContractType(this.contractType);
        iCntContractBO.setNegotiationMinutesId(this.negotiationMinutesId);
        iCntContractBO.setBidPackageId(this.bidPackageId);
        iCntContractBO.setBidPackageBlockId(this.bidPackageBlockId);
        iCntContractBO.setContractId(this.contractId);
        iCntContractBO.setCode(this.code);
        iCntContractBO.setDescription(this.description);
        iCntContractBO.setGoithauId(this.goithauId);
        iCntContractBO.setPartnerId(this.partnerId);
        iCntContractBO.setPayModeId(this.payModeId);
        iCntContractBO.setSignedDate(this.signedDate);
        iCntContractBO.setStatusId(this.statusId);
        iCntContractBO.setIsPayment(this.isPayment);
        iCntContractBO.setCreatedDate(this.createdDate);
        iCntContractBO.setCreatorId(this.creatorId);
        iCntContractBO.setMerStatus(this.merStatus);
        iCntContractBO.setLothauId(this.lothauId);
        iCntContractBO.setCntNgtId(this.cntNgtId);
        iCntContractBO.setCurrencyId(this.currencyId);
        iCntContractBO.setCntTypeId(this.cntTypeId);
        iCntContractBO.setTaxRate(this.taxRate);
        iCntContractBO.setConstructorCost(this.constructorCost);
        iCntContractBO.setSpareCost(this.spareCost);
        iCntContractBO.setOtherCost(this.otherCost);
        iCntContractBO.setMerCost(this.merCost);
        iCntContractBO.setDiscount(this.discount);
        iCntContractBO.setLiquidateCost(this.liquidateCost);
        iCntContractBO.setSignGroupId(this.signGroupId);
        iCntContractBO.setCurrencyRate(this.currencyRate);
        iCntContractBO.setHasWeight(this.hasWeight);
        iCntContractBO.setOriginalId(this.originalId);
        iCntContractBO.setDiscountUnit(this.discountUnit);
        iCntContractBO.setMerDescription(this.merDescription);
        iCntContractBO.setTotalValue(this.totalValue);
        iCntContractBO.setProjectMngCost(this.projectMngCost);
        iCntContractBO.setWarrantyBegin(this.warrantyBegin);
        iCntContractBO.setRealLiquidateDate(this.realLiquidateDate);
        iCntContractBO.setImportGroupId(this.importGroupId);
        iCntContractBO.setSupervisor(this.supervisor);
        iCntContractBO.setImportMerGroupId(this.importMerGroupId);
        iCntContractBO.setPaymentGroupId(this.paymentGroupId);
        iCntContractBO.setPayCurrencyId(this.payCurrencyId);
        iCntContractBO.setRequireChangeMer(this.requireChangeMer);
        iCntContractBO.setIsOld(this.isOld);
        iCntContractBO.setWarrantyBeginDate(this.warrantyBeginDate);
        iCntContractBO.setPackageCode(this.packageCode);
        iCntContractBO.setMerCostRemain(this.merCostRemain);
        iCntContractBO.setContractFrameId(this.contractFrameId);
        iCntContractBO.setAccountType(this.accountType);
        iCntContractBO.setDepreciationDate(this.depreciationDate);
        iCntContractBO.setProjectId(this.projectId);
        iCntContractBO.setWarrantyPeriod(this.warrantyPeriod);
        iCntContractBO.setWarrantyPartnerId(this.warrantyPartnerId);
        iCntContractBO.setGroupPricePercentId(this.groupPricePercentId);
        iCntContractBO.setGroupPriceShipmentId(this.groupPriceShipmentId);
        iCntContractBO.setUseGroupId(this.useGroupId);
        iCntContractBO.setIsProposal(this.isProposal);
        iCntContractBO.setIsConfig(this.isConfig);
        iCntContractBO.setExpireDate(this.expireDate);
        iCntContractBO.setRevenueLimit(this.revenueLimit);
        iCntContractBO.setSigner(this.signer);
        iCntContractBO.setPaymentExpire(this.paymentExpire);
        iCntContractBO.setPartnerName(this.partnerName);
        iCntContractBO.setCancelDate(this.cancelDate);
        iCntContractBO.setCancelReason(this.cancelReason);
        iCntContractBO.setLiquidateReason(this.liquidateReason);
        iCntContractBO.setMapDate(this.mapDate);
        iCntContractBO.setDuration(this.duration);
        iCntContractBO.setIsActive(this.isActive);
        iCntContractBO.setIsRealValue(this.isRealValue);
        iCntContractBO.setIsExtension(this.isExtension);
        iCntContractBO.setIsExtended(this.isExtended);
        iCntContractBO.setTrackingCode(this.trackingCode);
        iCntContractBO.setContent(this.content);
        iCntContractBO.setProposalFacDate(this.proposalFacDate);
        iCntContractBO.setProposalFacValue(this.proposalFacValue);
        iCntContractBO.setAcceptDate(this.acceptDate);
        iCntContractBO.setProcessGroupId(this.processGroupId);
        iCntContractBO.setLiquidateCode(this.liquidateCode);
        iCntContractBO.setSingerLiquidate(this.singerLiquidate);
        iCntContractBO.setAcceptCode(this.acceptCode);
        iCntContractBO.setSingerAccept(this.singerAccept);
        iCntContractBO.setKcsDocStatus(this.kcsDocStatus);
        iCntContractBO.setCheckApproval(this.checkApproval);
        iCntContractBO.setBillNumber(this.billNumber);
        iCntContractBO.setCatFormPaymentId(this.catFormPaymentId);
        iCntContractBO.setFinanceCntCode(this.financeCntCode);
        iCntContractBO.setFinanceCntValue(this.financeCntValue);
        iCntContractBO.setFinanceCntPercentMer(this.financeCntPercentMer);
        iCntContractBO.setCntSubType(this.cntSubType);
        iCntContractBO.setGuaranteeDateFrom(this.guaranteeDateFrom);
        iCntContractBO.setFinishedDate(this.finishedDate);
        iCntContractBO.setFormsId(this.formsId);
        iCntContractBO.setGuaranteeDateTo(this.guaranteeDateTo);
        iCntContractBO.setPoCode(this.poCode);
        iCntContractBO.setGuaranteeMerNo(this.guaranteeMerNo);
        iCntContractBO.setIsType(this.isType);
        iCntContractBO.setRatioTypeId(this.ratioTypeId);
        iCntContractBO.setErrRatio(this.errRatio);
        iCntContractBO.setErrRatioComment(this.errRatioComment);
        iCntContractBO.setGuaranteeMerPercent(this.guaranteeMerPercent);
        iCntContractBO.setMerQuantity(this.merQuantity);
        iCntContractBO.setMerType(this.merType);
        iCntContractBO.setHaveHshc(this.haveHshc);
        iCntContractBO.setProjInvestProjectId(this.projInvestProjectId);
        iCntContractBO.setCntKindType(this.cntKindType);
        iCntContractBO.setIsApprove(this.isApprove);
        iCntContractBO.setProvinceId(this.provinceId);
        iCntContractBO.setIsLock(this.isLock);
        iCntContractBO.setReasonDeny(this.reasonDeny);
        iCntContractBO.setInvestmentProposalGroupId(this.investmentProposalGroupId);
        iCntContractBO.setIntegratedContractId(this.integratedContractId);
        iCntContractBO.setCapitalSourceType(this.capitalSourceType);
        iCntContractBO.setExtendStatusId(this.extendStatusId);
        iCntContractBO.setPaymentStatusId(this.paymentStatusId);
        iCntContractBO.setLockPayment(this.lockPayment);
        iCntContractBO.setContractName(this.contractName);
        iCntContractBO.setAgreementType(this.agreementType);
        iCntContractBO.setContractPriceFc(this.contractPriceFc);
        iCntContractBO.setTransRate(this.transRate);
        iCntContractBO.setForeignCurrentId(this.foreignCurrentId);
        return iCntContractBO;
    }

    public java.lang.String getAgreementCode(){
    return agreementCode;
    }
    public void setAgreementCode(java.lang.String agreementCode)
    {
    this.agreementCode = agreementCode;
    }
    
    public java.lang.Long getGroupUsingId(){
    return groupUsingId;
    }
    public void setGroupUsingId(java.lang.Long groupUsingId)
    {
    this.groupUsingId = groupUsingId;
    }
    
    public java.lang.Long getCatServiceId(){
    return catServiceId;
    }
    public void setCatServiceId(java.lang.Long catServiceId)
    {
    this.catServiceId = catServiceId;
    }
    
    public java.lang.Long getContractAgreement(){
    return contractAgreement;
    }
    public void setContractAgreement(java.lang.Long contractAgreement)
    {
    this.contractAgreement = contractAgreement;
    }
    
    public java.lang.String getReason(){
    return reason;
    }
    public void setReason(java.lang.String reason)
    {
    this.reason = reason;
    }
    
    public java.lang.Long getStatusSign(){
    return statusSign;
    }
    public void setStatusSign(java.lang.Long statusSign)
    {
    this.statusSign = statusSign;
    }
    
    public java.lang.String getCommentStatusSign(){
    return commentStatusSign;
    }
    public void setCommentStatusSign(java.lang.String commentStatusSign)
    {
    this.commentStatusSign = commentStatusSign;
    }
    
    public java.lang.Long getContractNature(){
    return contractNature;
    }
    public void setContractNature(java.lang.Long contractNature)
    {
    this.contractNature = contractNature;
    }
    
    public java.lang.Long getNextStepGroupId(){
    return nextStepGroupId;
    }
    public void setNextStepGroupId(java.lang.Long nextStepGroupId)
    {
    this.nextStepGroupId = nextStepGroupId;
    }
    
    public java.lang.Long getProcPackageId(){
    return procPackageId;
    }
    public void setProcPackageId(java.lang.Long procPackageId)
    {
    this.procPackageId = procPackageId;
    }
    
    public java.lang.Long getProcComponentId(){
    return procComponentId;
    }
    public void setProcComponentId(java.lang.Long procComponentId)
    {
    this.procComponentId = procComponentId;
    }
    
    public java.lang.String getHandoverPlace(){
    return handoverPlace;
    }
    public void setHandoverPlace(java.lang.String handoverPlace)
    {
    this.handoverPlace = handoverPlace;
    }
    
    public java.util.Date getHandoverTime(){
    return handoverTime;
    }
    public void setHandoverTime(java.util.Date handoverTime)
    {
    this.handoverTime = handoverTime;
    }
    
    public java.lang.Long getARepInvestorId(){
    return aRepInvestorId;
    }
    public void setARepInvestorId(java.lang.Long aRepInvestorId)
    {
    this.aRepInvestorId = aRepInvestorId;
    }
    
    public java.lang.Double getContractValueBfTax(){
    return contractValueBfTax;
    }
    public void setContractValueBfTax(java.lang.Double contractValueBfTax)
    {
    this.contractValueBfTax = contractValueBfTax;
    }
    
    public java.lang.Double getTax(){
    return tax;
    }
    public void setTax(java.lang.Double tax)
    {
    this.tax = tax;
    }
    
    public java.lang.String getPaymentDeadline(){
    return paymentDeadline;
    }
    public void setPaymentDeadline(java.lang.String paymentDeadline)
    {
    this.paymentDeadline = paymentDeadline;
    }
    
    public java.lang.String getWorkScope(){
    return workScope;
    }
    public void setWorkScope(java.lang.String workScope)
    {
    this.workScope = workScope;
    }
    
    public java.lang.String getLicensingProcedureConstr(){
    return licensingProcedureConstr;
    }
    public void setLicensingProcedureConstr(java.lang.String licensingProcedureConstr)
    {
    this.licensingProcedureConstr = licensingProcedureConstr;
    }
    
    public java.util.Date getPerformStartDate(){
    return performStartDate;
    }
    public void setPerformStartDate(java.util.Date performStartDate)
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
    
    public java.lang.Long getPaymentTimeNumber(){
    return paymentTimeNumber;
    }
    public void setPaymentTimeNumber(java.lang.Long paymentTimeNumber)
    {
    this.paymentTimeNumber = paymentTimeNumber;
    }
    
    public java.lang.Long getPaymentStyle(){
    return paymentStyle;
    }
    public void setPaymentStyle(java.lang.Long paymentStyle)
    {
    this.paymentStyle = paymentStyle;
    }
    
    public java.lang.Long getCurrencyCount(){
    return currencyCount;
    }
    public void setCurrencyCount(java.lang.Long currencyCount)
    {
    this.currencyCount = currencyCount;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Long getContractType(){
    return contractType;
    }
    public void setContractType(java.lang.Long contractType)
    {
    this.contractType = contractType;
    }
    
    public java.lang.Long getNegotiationMinutesId(){
    return negotiationMinutesId;
    }
    public void setNegotiationMinutesId(java.lang.Long negotiationMinutesId)
    {
    this.negotiationMinutesId = negotiationMinutesId;
    }
    
    public java.lang.Long getBidPackageId(){
    return bidPackageId;
    }
    public void setBidPackageId(java.lang.Long bidPackageId)
    {
    this.bidPackageId = bidPackageId;
    }
    
    public java.lang.Long getBidPackageBlockId(){
    return bidPackageBlockId;
    }
    public void setBidPackageBlockId(java.lang.Long bidPackageBlockId)
    {
    this.bidPackageBlockId = bidPackageBlockId;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getGoithauId(){
    return goithauId;
    }
    public void setGoithauId(java.lang.Long goithauId)
    {
    this.goithauId = goithauId;
    }
    
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
    public java.lang.Long getPayModeId(){
    return payModeId;
    }
    public void setPayModeId(java.lang.Long payModeId)
    {
    this.payModeId = payModeId;
    }
    
    public java.util.Date getSignedDate(){
    return signedDate;
    }
    public void setSignedDate(java.util.Date signedDate)
    {
    this.signedDate = signedDate;
    }
    
    public java.lang.Long getStatusId(){
    return statusId;
    }
    public void setStatusId(java.lang.Long statusId)
    {
    this.statusId = statusId;
    }
    
    public java.lang.Long getIsPayment(){
    return isPayment;
    }
    public void setIsPayment(java.lang.Long isPayment)
    {
    this.isPayment = isPayment;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.lang.Long getMerStatus(){
    return merStatus;
    }
    public void setMerStatus(java.lang.Long merStatus)
    {
    this.merStatus = merStatus;
    }
    
    public java.lang.Long getLothauId(){
    return lothauId;
    }
    public void setLothauId(java.lang.Long lothauId)
    {
    this.lothauId = lothauId;
    }
    
    public java.lang.Long getCntNgtId(){
    return cntNgtId;
    }
    public void setCntNgtId(java.lang.Long cntNgtId)
    {
    this.cntNgtId = cntNgtId;
    }
    
    public java.lang.Long getCurrencyId(){
    return currencyId;
    }
    public void setCurrencyId(java.lang.Long currencyId)
    {
    this.currencyId = currencyId;
    }
    
    public java.lang.Long getCntTypeId(){
    return cntTypeId;
    }
    public void setCntTypeId(java.lang.Long cntTypeId)
    {
    this.cntTypeId = cntTypeId;
    }
    
    public java.lang.Double getTaxRate(){
    return taxRate;
    }
    public void setTaxRate(java.lang.Double taxRate)
    {
    this.taxRate = taxRate;
    }
    
    public java.lang.Long getConstructorCost(){
    return constructorCost;
    }
    public void setConstructorCost(java.lang.Long constructorCost)
    {
    this.constructorCost = constructorCost;
    }
    
    public java.lang.Long getSpareCost(){
    return spareCost;
    }
    public void setSpareCost(java.lang.Long spareCost)
    {
    this.spareCost = spareCost;
    }
    
    public java.lang.Long getOtherCost(){
    return otherCost;
    }
    public void setOtherCost(java.lang.Long otherCost)
    {
    this.otherCost = otherCost;
    }
    
    public java.lang.Long getMerCost(){
    return merCost;
    }
    public void setMerCost(java.lang.Long merCost)
    {
    this.merCost = merCost;
    }
    
    public java.lang.Long getDiscount(){
    return discount;
    }
    public void setDiscount(java.lang.Long discount)
    {
    this.discount = discount;
    }
    
    public java.lang.Long getLiquidateCost(){
    return liquidateCost;
    }
    public void setLiquidateCost(java.lang.Long liquidateCost)
    {
    this.liquidateCost = liquidateCost;
    }
    
    public java.lang.Long getSignGroupId(){
    return signGroupId;
    }
    public void setSignGroupId(java.lang.Long signGroupId)
    {
    this.signGroupId = signGroupId;
    }
    
    public java.lang.Double getCurrencyRate(){
    return currencyRate;
    }
    public void setCurrencyRate(java.lang.Double currencyRate)
    {
    this.currencyRate = currencyRate;
    }
    
    public java.lang.Long getHasWeight(){
    return hasWeight;
    }
    public void setHasWeight(java.lang.Long hasWeight)
    {
    this.hasWeight = hasWeight;
    }
    
    public java.lang.Long getOriginalId(){
    return originalId;
    }
    public void setOriginalId(java.lang.Long originalId)
    {
    this.originalId = originalId;
    }
    
    public java.lang.Long getDiscountUnit(){
    return discountUnit;
    }
    public void setDiscountUnit(java.lang.Long discountUnit)
    {
    this.discountUnit = discountUnit;
    }
    
    public java.lang.String getMerDescription(){
    return merDescription;
    }
    public void setMerDescription(java.lang.String merDescription)
    {
    this.merDescription = merDescription;
    }
    
    public java.lang.Long getTotalValue(){
    return totalValue;
    }
    public void setTotalValue(java.lang.Long totalValue)
    {
    this.totalValue = totalValue;
    }
    
    public java.lang.Long getProjectMngCost(){
    return projectMngCost;
    }
    public void setProjectMngCost(java.lang.Long projectMngCost)
    {
    this.projectMngCost = projectMngCost;
    }
    
    public java.lang.Long getWarrantyBegin(){
    return warrantyBegin;
    }
    public void setWarrantyBegin(java.lang.Long warrantyBegin)
    {
    this.warrantyBegin = warrantyBegin;
    }
    
    public java.util.Date getRealLiquidateDate(){
    return realLiquidateDate;
    }
    public void setRealLiquidateDate(java.util.Date realLiquidateDate)
    {
    this.realLiquidateDate = realLiquidateDate;
    }
    
    public java.lang.Long getImportGroupId(){
    return importGroupId;
    }
    public void setImportGroupId(java.lang.Long importGroupId)
    {
    this.importGroupId = importGroupId;
    }
    
    public java.lang.String getSupervisor(){
    return supervisor;
    }
    public void setSupervisor(java.lang.String supervisor)
    {
    this.supervisor = supervisor;
    }
    
    public java.lang.Long getImportMerGroupId(){
    return importMerGroupId;
    }
    public void setImportMerGroupId(java.lang.Long importMerGroupId)
    {
    this.importMerGroupId = importMerGroupId;
    }
    
    public java.lang.Long getPaymentGroupId(){
    return paymentGroupId;
    }
    public void setPaymentGroupId(java.lang.Long paymentGroupId)
    {
    this.paymentGroupId = paymentGroupId;
    }
    
    public java.lang.Long getPayCurrencyId(){
    return payCurrencyId;
    }
    public void setPayCurrencyId(java.lang.Long payCurrencyId)
    {
    this.payCurrencyId = payCurrencyId;
    }
    
    public java.lang.Long getRequireChangeMer(){
    return requireChangeMer;
    }
    public void setRequireChangeMer(java.lang.Long requireChangeMer)
    {
    this.requireChangeMer = requireChangeMer;
    }
    
    public java.lang.Long getIsOld(){
    return isOld;
    }
    public void setIsOld(java.lang.Long isOld)
    {
    this.isOld = isOld;
    }
    
    public java.util.Date getWarrantyBeginDate(){
    return warrantyBeginDate;
    }
    public void setWarrantyBeginDate(java.util.Date warrantyBeginDate)
    {
    this.warrantyBeginDate = warrantyBeginDate;
    }
    
    public java.lang.String getPackageCode(){
    return packageCode;
    }
    public void setPackageCode(java.lang.String packageCode)
    {
    this.packageCode = packageCode;
    }
    
    public java.lang.Long getMerCostRemain(){
    return merCostRemain;
    }
    public void setMerCostRemain(java.lang.Long merCostRemain)
    {
    this.merCostRemain = merCostRemain;
    }
    
    public java.lang.Long getContractFrameId(){
    return contractFrameId;
    }
    public void setContractFrameId(java.lang.Long contractFrameId)
    {
    this.contractFrameId = contractFrameId;
    }
    
    public java.lang.Long getAccountType(){
    return accountType;
    }
    public void setAccountType(java.lang.Long accountType)
    {
    this.accountType = accountType;
    }
    
    public java.util.Date getDepreciationDate(){
    return depreciationDate;
    }
    public void setDepreciationDate(java.util.Date depreciationDate)
    {
    this.depreciationDate = depreciationDate;
    }
    
    public java.lang.Long getProjectId(){
    return projectId;
    }
    public void setProjectId(java.lang.Long projectId)
    {
    this.projectId = projectId;
    }
    
    public java.lang.Long getWarrantyPeriod(){
    return warrantyPeriod;
    }
    public void setWarrantyPeriod(java.lang.Long warrantyPeriod)
    {
    this.warrantyPeriod = warrantyPeriod;
    }
    
    public java.lang.Long getWarrantyPartnerId(){
    return warrantyPartnerId;
    }
    public void setWarrantyPartnerId(java.lang.Long warrantyPartnerId)
    {
    this.warrantyPartnerId = warrantyPartnerId;
    }
    
    public java.lang.Long getGroupPricePercentId(){
    return groupPricePercentId;
    }
    public void setGroupPricePercentId(java.lang.Long groupPricePercentId)
    {
    this.groupPricePercentId = groupPricePercentId;
    }
    
    public java.lang.Long getGroupPriceShipmentId(){
    return groupPriceShipmentId;
    }
    public void setGroupPriceShipmentId(java.lang.Long groupPriceShipmentId)
    {
    this.groupPriceShipmentId = groupPriceShipmentId;
    }
    
    public java.lang.Long getUseGroupId(){
    return useGroupId;
    }
    public void setUseGroupId(java.lang.Long useGroupId)
    {
    this.useGroupId = useGroupId;
    }
    
    public java.lang.Long getIsProposal(){
    return isProposal;
    }
    public void setIsProposal(java.lang.Long isProposal)
    {
    this.isProposal = isProposal;
    }
    
    public java.lang.Long getIsConfig(){
    return isConfig;
    }
    public void setIsConfig(java.lang.Long isConfig)
    {
    this.isConfig = isConfig;
    }
    
    public java.util.Date getExpireDate(){
    return expireDate;
    }
    public void setExpireDate(java.util.Date expireDate)
    {
    this.expireDate = expireDate;
    }
    
    public java.lang.Long getRevenueLimit(){
    return revenueLimit;
    }
    public void setRevenueLimit(java.lang.Long revenueLimit)
    {
    this.revenueLimit = revenueLimit;
    }
    
    public java.lang.String getSigner(){
    return signer;
    }
    public void setSigner(java.lang.String signer)
    {
    this.signer = signer;
    }
    
    public java.lang.Long getPaymentExpire(){
    return paymentExpire;
    }
    public void setPaymentExpire(java.lang.Long paymentExpire)
    {
    this.paymentExpire = paymentExpire;
    }
    
    public java.lang.String getPartnerName(){
    return partnerName;
    }
    public void setPartnerName(java.lang.String partnerName)
    {
    this.partnerName = partnerName;
    }
    
    public java.util.Date getCancelDate(){
    return cancelDate;
    }
    public void setCancelDate(java.util.Date cancelDate)
    {
    this.cancelDate = cancelDate;
    }
    
    public java.lang.String getCancelReason(){
    return cancelReason;
    }
    public void setCancelReason(java.lang.String cancelReason)
    {
    this.cancelReason = cancelReason;
    }
    
    public java.lang.String getLiquidateReason(){
    return liquidateReason;
    }
    public void setLiquidateReason(java.lang.String liquidateReason)
    {
    this.liquidateReason = liquidateReason;
    }
    
    public java.util.Date getMapDate(){
    return mapDate;
    }
    public void setMapDate(java.util.Date mapDate)
    {
    this.mapDate = mapDate;
    }
    
    public java.lang.Long getDuration(){
    return duration;
    }
    public void setDuration(java.lang.Long duration)
    {
    this.duration = duration;
    }
    
    public java.lang.String getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.String isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getIsRealValue(){
    return isRealValue;
    }
    public void setIsRealValue(java.lang.Long isRealValue)
    {
    this.isRealValue = isRealValue;
    }
    
    public java.lang.Long getIsExtension(){
    return isExtension;
    }
    public void setIsExtension(java.lang.Long isExtension)
    {
    this.isExtension = isExtension;
    }
    
    public java.lang.Long getIsExtended(){
    return isExtended;
    }
    public void setIsExtended(java.lang.Long isExtended)
    {
    this.isExtended = isExtended;
    }
    
    public java.lang.String getTrackingCode(){
    return trackingCode;
    }
    public void setTrackingCode(java.lang.String trackingCode)
    {
    this.trackingCode = trackingCode;
    }
    
    public java.lang.String getContent(){
    return content;
    }
    public void setContent(java.lang.String content)
    {
    this.content = content;
    }
    
    public java.util.Date getProposalFacDate(){
    return proposalFacDate;
    }
    public void setProposalFacDate(java.util.Date proposalFacDate)
    {
    this.proposalFacDate = proposalFacDate;
    }
    
    public java.lang.Long getProposalFacValue(){
    return proposalFacValue;
    }
    public void setProposalFacValue(java.lang.Long proposalFacValue)
    {
    this.proposalFacValue = proposalFacValue;
    }
    
    public java.util.Date getAcceptDate(){
    return acceptDate;
    }
    public void setAcceptDate(java.util.Date acceptDate)
    {
    this.acceptDate = acceptDate;
    }
    
    public java.lang.Long getProcessGroupId(){
    return processGroupId;
    }
    public void setProcessGroupId(java.lang.Long processGroupId)
    {
    this.processGroupId = processGroupId;
    }
    
    public java.lang.String getLiquidateCode(){
    return liquidateCode;
    }
    public void setLiquidateCode(java.lang.String liquidateCode)
    {
    this.liquidateCode = liquidateCode;
    }
    
    public java.lang.String getSingerLiquidate(){
    return singerLiquidate;
    }
    public void setSingerLiquidate(java.lang.String singerLiquidate)
    {
    this.singerLiquidate = singerLiquidate;
    }
    
    public java.lang.String getAcceptCode(){
    return acceptCode;
    }
    public void setAcceptCode(java.lang.String acceptCode)
    {
    this.acceptCode = acceptCode;
    }
    
    public java.lang.String getSingerAccept(){
    return singerAccept;
    }
    public void setSingerAccept(java.lang.String singerAccept)
    {
    this.singerAccept = singerAccept;
    }
    
    public java.lang.Long getKcsDocStatus(){
    return kcsDocStatus;
    }
    public void setKcsDocStatus(java.lang.Long kcsDocStatus)
    {
    this.kcsDocStatus = kcsDocStatus;
    }
    
    public java.lang.Long getCheckApproval(){
    return checkApproval;
    }
    public void setCheckApproval(java.lang.Long checkApproval)
    {
    this.checkApproval = checkApproval;
    }
    
    public java.lang.String getBillNumber(){
    return billNumber;
    }
    public void setBillNumber(java.lang.String billNumber)
    {
    this.billNumber = billNumber;
    }
    
    public java.lang.Long getCatFormPaymentId(){
    return catFormPaymentId;
    }
    public void setCatFormPaymentId(java.lang.Long catFormPaymentId)
    {
    this.catFormPaymentId = catFormPaymentId;
    }
    
    public java.lang.String getFinanceCntCode(){
    return financeCntCode;
    }
    public void setFinanceCntCode(java.lang.String financeCntCode)
    {
    this.financeCntCode = financeCntCode;
    }
    
    public java.lang.Double getFinanceCntValue(){
    return financeCntValue;
    }
    public void setFinanceCntValue(java.lang.Double financeCntValue)
    {
    this.financeCntValue = financeCntValue;
    }
    
    public java.lang.Double getFinanceCntPercentMer(){
    return financeCntPercentMer;
    }
    public void setFinanceCntPercentMer(java.lang.Double financeCntPercentMer)
    {
    this.financeCntPercentMer = financeCntPercentMer;
    }
    
    public java.lang.Long getCntSubType(){
    return cntSubType;
    }
    public void setCntSubType(java.lang.Long cntSubType)
    {
    this.cntSubType = cntSubType;
    }
    
    public java.util.Date getGuaranteeDateFrom(){
    return guaranteeDateFrom;
    }
    public void setGuaranteeDateFrom(java.util.Date guaranteeDateFrom)
    {
    this.guaranteeDateFrom = guaranteeDateFrom;
    }
    
    public java.util.Date getFinishedDate(){
    return finishedDate;
    }
    public void setFinishedDate(java.util.Date finishedDate)
    {
    this.finishedDate = finishedDate;
    }
    
    public java.lang.Long getFormsId(){
    return formsId;
    }
    public void setFormsId(java.lang.Long formsId)
    {
    this.formsId = formsId;
    }
    
    public java.util.Date getGuaranteeDateTo(){
    return guaranteeDateTo;
    }
    public void setGuaranteeDateTo(java.util.Date guaranteeDateTo)
    {
    this.guaranteeDateTo = guaranteeDateTo;
    }
    
    public java.lang.String getPoCode(){
    return poCode;
    }
    public void setPoCode(java.lang.String poCode)
    {
    this.poCode = poCode;
    }
    
    public java.lang.Double getGuaranteeMerNo(){
    return guaranteeMerNo;
    }
    public void setGuaranteeMerNo(java.lang.Double guaranteeMerNo)
    {
    this.guaranteeMerNo = guaranteeMerNo;
    }
    
    public java.lang.Long getIsType(){
    return isType;
    }
    public void setIsType(java.lang.Long isType)
    {
    this.isType = isType;
    }
    
    public java.lang.Long getRatioTypeId(){
    return ratioTypeId;
    }
    public void setRatioTypeId(java.lang.Long ratioTypeId)
    {
    this.ratioTypeId = ratioTypeId;
    }
    
    public java.lang.Double getErrRatio(){
    return errRatio;
    }
    public void setErrRatio(java.lang.Double errRatio)
    {
    this.errRatio = errRatio;
    }
    
    public java.lang.String getErrRatioComment(){
    return errRatioComment;
    }
    public void setErrRatioComment(java.lang.String errRatioComment)
    {
    this.errRatioComment = errRatioComment;
    }
    
    public java.lang.Double getGuaranteeMerPercent(){
    return guaranteeMerPercent;
    }
    public void setGuaranteeMerPercent(java.lang.Double guaranteeMerPercent)
    {
    this.guaranteeMerPercent = guaranteeMerPercent;
    }
    
    public java.lang.Double getMerQuantity(){
    return merQuantity;
    }
    public void setMerQuantity(java.lang.Double merQuantity)
    {
    this.merQuantity = merQuantity;
    }
    
    public java.lang.String getMerType(){
    return merType;
    }
    public void setMerType(java.lang.String merType)
    {
    this.merType = merType;
    }
    
    public java.lang.Long getHaveHshc(){
    return haveHshc;
    }
    public void setHaveHshc(java.lang.Long haveHshc)
    {
    this.haveHshc = haveHshc;
    }
    
    @Override
     public Long getFWModelId() {
        return projInvestProjectId;
    }
   
    @Override
    public String catchName() {
        return getProjInvestProjectId().toString();
    }
    public java.lang.Long getProjInvestProjectId(){
    return projInvestProjectId;
    }
    public void setProjInvestProjectId(java.lang.Long projInvestProjectId)
    {
    this.projInvestProjectId = projInvestProjectId;
    }
    
    public java.lang.Long getCntKindType(){
    return cntKindType;
    }
    public void setCntKindType(java.lang.Long cntKindType)
    {
    this.cntKindType = cntKindType;
    }
    
    public java.lang.Long getIsApprove(){
    return isApprove;
    }
    public void setIsApprove(java.lang.Long isApprove)
    {
    this.isApprove = isApprove;
    }
    
    public java.lang.Long getProvinceId(){
    return provinceId;
    }
    public void setProvinceId(java.lang.Long provinceId)
    {
    this.provinceId = provinceId;
    }
    
    public java.lang.Long getIsLock(){
    return isLock;
    }
    public void setIsLock(java.lang.Long isLock)
    {
    this.isLock = isLock;
    }
    
    public java.lang.String getReasonDeny(){
    return reasonDeny;
    }
    public void setReasonDeny(java.lang.String reasonDeny)
    {
    this.reasonDeny = reasonDeny;
    }
    
    public java.lang.Long getInvestmentProposalGroupId(){
    return investmentProposalGroupId;
    }
    public void setInvestmentProposalGroupId(java.lang.Long investmentProposalGroupId)
    {
    this.investmentProposalGroupId = investmentProposalGroupId;
    }
    
    public java.lang.Long getIntegratedContractId(){
    return integratedContractId;
    }
    public void setIntegratedContractId(java.lang.Long integratedContractId)
    {
    this.integratedContractId = integratedContractId;
    }
    
    public java.lang.Long getCapitalSourceType(){
    return capitalSourceType;
    }
    public void setCapitalSourceType(java.lang.Long capitalSourceType)
    {
    this.capitalSourceType = capitalSourceType;
    }
    
    public java.lang.Long getExtendStatusId(){
    return extendStatusId;
    }
    public void setExtendStatusId(java.lang.Long extendStatusId)
    {
    this.extendStatusId = extendStatusId;
    }
    
    public java.lang.Long getPaymentStatusId(){
    return paymentStatusId;
    }
    public void setPaymentStatusId(java.lang.Long paymentStatusId)
    {
    this.paymentStatusId = paymentStatusId;
    }
    
    public java.lang.Long getLockPayment(){
    return lockPayment;
    }
    public void setLockPayment(java.lang.Long lockPayment)
    {
    this.lockPayment = lockPayment;
    }
    
    public java.lang.String getContractName(){
    return contractName;
    }
    public void setContractName(java.lang.String contractName)
    {
    this.contractName = contractName;
    }
    
    public java.lang.Long getAgreementType(){
    return agreementType;
    }
    public void setAgreementType(java.lang.Long agreementType)
    {
    this.agreementType = agreementType;
    }
    
    public java.lang.Long getContractPriceFc(){
    return contractPriceFc;
    }
    public void setContractPriceFc(java.lang.Long contractPriceFc)
    {
    this.contractPriceFc = contractPriceFc;
    }
    
    public java.lang.String getTransRate(){
    return transRate;
    }
    public void setTransRate(java.lang.String transRate)
    {
    this.transRate = transRate;
    }
    
    public java.lang.Long getForeignCurrentId(){
    return foreignCurrentId;
    }
    public void setForeignCurrentId(java.lang.Long foreignCurrentId)
    {
    this.foreignCurrentId = foreignCurrentId;
    }

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}
    
}
