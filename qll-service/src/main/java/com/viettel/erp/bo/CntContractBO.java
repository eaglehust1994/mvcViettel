/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CntContractDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CNT_CONTRACT")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CntContractBO extends BaseFWModelImpl {
     
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
private java.lang.Long paymentStatusId;
private java.lang.Long lockPayment;
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
private java.lang.String contractName;
private java.lang.Long agreementType;
private java.lang.Long contractPriceFc;
private java.lang.String transRate;
private java.lang.Long foreignCurrentId;
private java.lang.String agreementCode;
private java.lang.Long groupUsingId;
private java.lang.Long catServiceId;
private java.lang.Long contractAgreement;
private java.lang.String reason;
private java.lang.Long contractId;
private java.lang.String contractCode;
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
private java.lang.Long extendStatusId;
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

 public CntContractBO() {
        setColId("contractId");
        setColName("contractId");
        setUniqueColumn(new String[]{"contractId"});
}

@Column(name = "MAP_DATE", length = 7)
public java.util.Date getMapDate(){
return mapDate;
}
public void setMapDate(java.util.Date mapDate)
{
this.mapDate = mapDate;
}
@Column(name = "DURATION", length = 22)
public java.lang.Long getDuration(){
return duration;
}
public void setDuration(java.lang.Long duration)
{
this.duration = duration;
}
@Column(name = "IS_ACTIVE", length = 1)
public java.lang.String getIsActive(){
return isActive;
}
public void setIsActive(java.lang.String isActive)
{
this.isActive = isActive;
}
@Column(name = "IS_REAL_VALUE", length = 22)
public java.lang.Long getIsRealValue(){
return isRealValue;
}
public void setIsRealValue(java.lang.Long isRealValue)
{
this.isRealValue = isRealValue;
}
@Column(name = "IS_EXTENSION", length = 22)
public java.lang.Long getIsExtension(){
return isExtension;
}
public void setIsExtension(java.lang.Long isExtension)
{
this.isExtension = isExtension;
}
@Column(name = "IS_EXTENDED", length = 22)
public java.lang.Long getIsExtended(){
return isExtended;
}
public void setIsExtended(java.lang.Long isExtended)
{
this.isExtended = isExtended;
}
@Column(name = "TRACKING_CODE", length = 400)
public java.lang.String getTrackingCode(){
return trackingCode;
}
public void setTrackingCode(java.lang.String trackingCode)
{
this.trackingCode = trackingCode;
}
@Column(name = "CONTENT", length = 2000)
public java.lang.String getContent(){
return content;
}
public void setContent(java.lang.String content)
{
this.content = content;
}
@Column(name = "PROPOSAL_FAC_DATE", length = 7)
public java.util.Date getProposalFacDate(){
return proposalFacDate;
}
public void setProposalFacDate(java.util.Date proposalFacDate)
{
this.proposalFacDate = proposalFacDate;
}
@Column(name = "PROPOSAL_FAC_VALUE", length = 22)
public java.lang.Long getProposalFacValue(){
return proposalFacValue;
}
public void setProposalFacValue(java.lang.Long proposalFacValue)
{
this.proposalFacValue = proposalFacValue;
}
@Column(name = "ACCEPT_DATE", length = 7)
public java.util.Date getAcceptDate(){
return acceptDate;
}
public void setAcceptDate(java.util.Date acceptDate)
{
this.acceptDate = acceptDate;
}
@Column(name = "PROCESS_GROUP_ID", length = 22)
public java.lang.Long getProcessGroupId(){
return processGroupId;
}
public void setProcessGroupId(java.lang.Long processGroupId)
{
this.processGroupId = processGroupId;
}
@Column(name = "LIQUIDATE_CODE", length = 60)
public java.lang.String getLiquidateCode(){
return liquidateCode;
}
public void setLiquidateCode(java.lang.String liquidateCode)
{
this.liquidateCode = liquidateCode;
}
@Column(name = "SINGER_LIQUIDATE", length = 60)
public java.lang.String getSingerLiquidate(){
return singerLiquidate;
}
public void setSingerLiquidate(java.lang.String singerLiquidate)
{
this.singerLiquidate = singerLiquidate;
}
@Column(name = "ACCEPT_CODE", length = 60)
public java.lang.String getAcceptCode(){
return acceptCode;
}
public void setAcceptCode(java.lang.String acceptCode)
{
this.acceptCode = acceptCode;
}
@Column(name = "SINGER_ACCEPT", length = 60)
public java.lang.String getSingerAccept(){
return singerAccept;
}
public void setSingerAccept(java.lang.String singerAccept)
{
this.singerAccept = singerAccept;
}
@Column(name = "KCS_DOC_STATUS", length = 22)
public java.lang.Long getKcsDocStatus(){
return kcsDocStatus;
}
public void setKcsDocStatus(java.lang.Long kcsDocStatus)
{
this.kcsDocStatus = kcsDocStatus;
}
@Column(name = "CHECK_APPROVAL", length = 22)
public java.lang.Long getCheckApproval(){
return checkApproval;
}
public void setCheckApproval(java.lang.Long checkApproval)
{
this.checkApproval = checkApproval;
}
@Column(name = "BILL_NUMBER", length = 200)
public java.lang.String getBillNumber(){
return billNumber;
}
public void setBillNumber(java.lang.String billNumber)
{
this.billNumber = billNumber;
}
@Column(name = "CAT_FORM_PAYMENT_ID", length = 22)
public java.lang.Long getCatFormPaymentId(){
return catFormPaymentId;
}
public void setCatFormPaymentId(java.lang.Long catFormPaymentId)
{
this.catFormPaymentId = catFormPaymentId;
}
@Column(name = "FINANCE_CNT_CODE", length = 1000)
public java.lang.String getFinanceCntCode(){
return financeCntCode;
}
public void setFinanceCntCode(java.lang.String financeCntCode)
{
this.financeCntCode = financeCntCode;
}
@Column(name = "FINANCE_CNT_VALUE", length = 22)
public java.lang.Double getFinanceCntValue(){
return financeCntValue;
}
public void setFinanceCntValue(java.lang.Double financeCntValue)
{
this.financeCntValue = financeCntValue;
}
@Column(name = "FINANCE_CNT_PERCENT_MER", length = 22)
public java.lang.Double getFinanceCntPercentMer(){
return financeCntPercentMer;
}
public void setFinanceCntPercentMer(java.lang.Double financeCntPercentMer)
{
this.financeCntPercentMer = financeCntPercentMer;
}
@Column(name = "PAYMENT_STATUS_ID", length = 22)
public java.lang.Long getPaymentStatusId(){
return paymentStatusId;
}
public void setPaymentStatusId(java.lang.Long paymentStatusId)
{
this.paymentStatusId = paymentStatusId;
}
@Column(name = "LOCK_PAYMENT", length = 22)
public java.lang.Long getLockPayment(){
return lockPayment;
}
public void setLockPayment(java.lang.Long lockPayment)
{
this.lockPayment = lockPayment;
}
@Column(name = "CNT_SUB_TYPE", length = 22)
public java.lang.Long getCntSubType(){
return cntSubType;
}
public void setCntSubType(java.lang.Long cntSubType)
{
this.cntSubType = cntSubType;
}
@Column(name = "GUARANTEE_DATE_FROM", length = 7)
public java.util.Date getGuaranteeDateFrom(){
return guaranteeDateFrom;
}
public void setGuaranteeDateFrom(java.util.Date guaranteeDateFrom)
{
this.guaranteeDateFrom = guaranteeDateFrom;
}
@Column(name = "FINISHED_DATE", length = 7)
public java.util.Date getFinishedDate(){
return finishedDate;
}
public void setFinishedDate(java.util.Date finishedDate)
{
this.finishedDate = finishedDate;
}
@Column(name = "FORMS_ID", length = 22)
public java.lang.Long getFormsId(){
return formsId;
}
public void setFormsId(java.lang.Long formsId)
{
this.formsId = formsId;
}
@Column(name = "GUARANTEE_DATE_TO", length = 7)
public java.util.Date getGuaranteeDateTo(){
return guaranteeDateTo;
}
public void setGuaranteeDateTo(java.util.Date guaranteeDateTo)
{
this.guaranteeDateTo = guaranteeDateTo;
}
@Column(name = "PO_CODE", length = 200)
public java.lang.String getPoCode(){
return poCode;
}
public void setPoCode(java.lang.String poCode)
{
this.poCode = poCode;
}
@Column(name = "GUARANTEE_MER_NO", length = 22)
public java.lang.Double getGuaranteeMerNo(){
return guaranteeMerNo;
}
public void setGuaranteeMerNo(java.lang.Double guaranteeMerNo)
{
this.guaranteeMerNo = guaranteeMerNo;
}
@Column(name = "IS_TYPE", length = 22)
public java.lang.Long getIsType(){
return isType;
}
public void setIsType(java.lang.Long isType)
{
this.isType = isType;
}
@Column(name = "RATIO_TYPE_ID", length = 22)
public java.lang.Long getRatioTypeId(){
return ratioTypeId;
}
public void setRatioTypeId(java.lang.Long ratioTypeId)
{
this.ratioTypeId = ratioTypeId;
}
@Column(name = "ERR_RATIO", length = 22)
public java.lang.Double getErrRatio(){
return errRatio;
}
public void setErrRatio(java.lang.Double errRatio)
{
this.errRatio = errRatio;
}
@Column(name = "ERR_RATIO_COMMENT", length = 1000)
public java.lang.String getErrRatioComment(){
return errRatioComment;
}
public void setErrRatioComment(java.lang.String errRatioComment)
{
this.errRatioComment = errRatioComment;
}
@Column(name = "GUARANTEE_MER_PERCENT", length = 22)
public java.lang.Double getGuaranteeMerPercent(){
return guaranteeMerPercent;
}
public void setGuaranteeMerPercent(java.lang.Double guaranteeMerPercent)
{
this.guaranteeMerPercent = guaranteeMerPercent;
}
@Column(name = "MER_QUANTITY", length = 22)
public java.lang.Double getMerQuantity(){
return merQuantity;
}
public void setMerQuantity(java.lang.Double merQuantity)
{
this.merQuantity = merQuantity;
}
@Column(name = "MER_TYPE", length = 1000)
public java.lang.String getMerType(){
return merType;
}
public void setMerType(java.lang.String merType)
{
this.merType = merType;
}
@Column(name = "HAVE_HSHC", length = 22)
public java.lang.Long getHaveHshc(){
return haveHshc;
}
public void setHaveHshc(java.lang.Long haveHshc)
{
this.haveHshc = haveHshc;
}
@Column(name = "PROJ_INVEST_PROJECT_ID", length = 22)
public java.lang.Long getProjInvestProjectId(){
return projInvestProjectId;
}
public void setProjInvestProjectId(java.lang.Long projInvestProjectId)
{
this.projInvestProjectId = projInvestProjectId;
}
@Column(name = "CNT_KIND_TYPE", length = 22)
public java.lang.Long getCntKindType(){
return cntKindType;
}
public void setCntKindType(java.lang.Long cntKindType)
{
this.cntKindType = cntKindType;
}
@Column(name = "IS_APPROVE", length = 22)
public java.lang.Long getIsApprove(){
return isApprove;
}
public void setIsApprove(java.lang.Long isApprove)
{
this.isApprove = isApprove;
}
@Column(name = "PROVINCE_ID", length = 22)
public java.lang.Long getProvinceId(){
return provinceId;
}
public void setProvinceId(java.lang.Long provinceId)
{
this.provinceId = provinceId;
}
@Column(name = "IS_LOCK", length = 22)
public java.lang.Long getIsLock(){
return isLock;
}
public void setIsLock(java.lang.Long isLock)
{
this.isLock = isLock;
}
@Column(name = "REASON_DENY", length = 1000)
public java.lang.String getReasonDeny(){
return reasonDeny;
}
public void setReasonDeny(java.lang.String reasonDeny)
{
this.reasonDeny = reasonDeny;
}
@Column(name = "CONTRACT_NAME", length = 2000)
public java.lang.String getContractName(){
return contractName;
}
public void setContractName(java.lang.String contractName)
{
this.contractName = contractName;
}
@Column(name = "AGREEMENT_TYPE", length = 22)
public java.lang.Long getAgreementType(){
return agreementType;
}
public void setAgreementType(java.lang.Long agreementType)
{
this.agreementType = agreementType;
}
@Column(name = "CONTRACT_PRICE_FC", length = 22)
public java.lang.Long getContractPriceFc(){
return contractPriceFc;
}
public void setContractPriceFc(java.lang.Long contractPriceFc)
{
this.contractPriceFc = contractPriceFc;
}
@Column(name = "TRANS_RATE", length = 20)
public java.lang.String getTransRate(){
return transRate;
}
public void setTransRate(java.lang.String transRate)
{
this.transRate = transRate;
}
@Column(name = "FOREIGN_CURRENT_ID", length = 22)
public java.lang.Long getForeignCurrentId(){
return foreignCurrentId;
}
public void setForeignCurrentId(java.lang.Long foreignCurrentId)
{
this.foreignCurrentId = foreignCurrentId;
}
@Column(name = "AGREEMENT_CODE", length = 1000)
public java.lang.String getAgreementCode(){
return agreementCode;
}
public void setAgreementCode(java.lang.String agreementCode)
{
this.agreementCode = agreementCode;
}
@Column(name = "GROUP_USING_ID", length = 22)
public java.lang.Long getGroupUsingId(){
return groupUsingId;
}
public void setGroupUsingId(java.lang.Long groupUsingId)
{
this.groupUsingId = groupUsingId;
}
@Column(name = "CAT_SERVICE_ID", length = 22)
public java.lang.Long getCatServiceId(){
return catServiceId;
}
public void setCatServiceId(java.lang.Long catServiceId)
{
this.catServiceId = catServiceId;
}
@Column(name = "CONTRACT_AGREEMENT", length = 22)
public java.lang.Long getContractAgreement(){
return contractAgreement;
}
public void setContractAgreement(java.lang.Long contractAgreement)
{
this.contractAgreement = contractAgreement;
}
@Column(name = "REASON", length = 1000)
public java.lang.String getReason(){
return reason;
}
public void setReason(java.lang.String reason)
{
this.reason = reason;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CNT_CONTRACT_SEQ")
            }
    )
@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Long getContractId(){
return contractId;
}
public void setContractId(java.lang.Long contractId)
{
this.contractId = contractId;
}
@Column(name = "CODE", length = 1000)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "DESCRIPTION", length = 3000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "GOITHAU_ID", length = 22)
public java.lang.Long getGoithauId(){
return goithauId;
}
public void setGoithauId(java.lang.Long goithauId)
{
this.goithauId = goithauId;
}
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "PAY_MODE_ID", length = 22)
public java.lang.Long getPayModeId(){
return payModeId;
}
public void setPayModeId(java.lang.Long payModeId)
{
this.payModeId = payModeId;
}
@Column(name = "SIGNED_DATE", length = 7)
public java.util.Date getSignedDate(){
return signedDate;
}
public void setSignedDate(java.util.Date signedDate)
{
this.signedDate = signedDate;
}
@Column(name = "STATUS_ID", length = 22)
public java.lang.Long getStatusId(){
return statusId;
}
public void setStatusId(java.lang.Long statusId)
{
this.statusId = statusId;
}
@Column(name = "IS_PAYMENT", length = 22)
public java.lang.Long getIsPayment(){
return isPayment;
}
public void setIsPayment(java.lang.Long isPayment)
{
this.isPayment = isPayment;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "MER_STATUS", length = 22)
public java.lang.Long getMerStatus(){
return merStatus;
}
public void setMerStatus(java.lang.Long merStatus)
{
this.merStatus = merStatus;
}
@Column(name = "LOTHAU_ID", length = 22)
public java.lang.Long getLothauId(){
return lothauId;
}
public void setLothauId(java.lang.Long lothauId)
{
this.lothauId = lothauId;
}
@Column(name = "CNT_NGT_ID", length = 22)
public java.lang.Long getCntNgtId(){
return cntNgtId;
}
public void setCntNgtId(java.lang.Long cntNgtId)
{
this.cntNgtId = cntNgtId;
}
@Column(name = "CURRENCY_ID", length = 22)
public java.lang.Long getCurrencyId(){
return currencyId;
}
public void setCurrencyId(java.lang.Long currencyId)
{
this.currencyId = currencyId;
}
@Column(name = "CNT_TYPE_ID", length = 22)
public java.lang.Long getCntTypeId(){
return cntTypeId;
}
public void setCntTypeId(java.lang.Long cntTypeId)
{
this.cntTypeId = cntTypeId;
}
@Column(name = "TAX_RATE", length = 22)
public java.lang.Double getTaxRate(){
return taxRate;
}
public void setTaxRate(java.lang.Double taxRate)
{
this.taxRate = taxRate;
}
@Column(name = "CONSTRUCTOR_COST", length = 22)
public java.lang.Long getConstructorCost(){
return constructorCost;
}
public void setConstructorCost(java.lang.Long constructorCost)
{
this.constructorCost = constructorCost;
}
@Column(name = "SPARE_COST", length = 22)
public java.lang.Long getSpareCost(){
return spareCost;
}
public void setSpareCost(java.lang.Long spareCost)
{
this.spareCost = spareCost;
}
@Column(name = "OTHER_COST", length = 22)
public java.lang.Long getOtherCost(){
return otherCost;
}
public void setOtherCost(java.lang.Long otherCost)
{
this.otherCost = otherCost;
}
@Column(name = "MER_COST", length = 22)
public java.lang.Long getMerCost(){
return merCost;
}
public void setMerCost(java.lang.Long merCost)
{
this.merCost = merCost;
}
@Column(name = "DISCOUNT", length = 22)
public java.lang.Long getDiscount(){
return discount;
}
public void setDiscount(java.lang.Long discount)
{
this.discount = discount;
}
@Column(name = "LIQUIDATE_COST", length = 22)
public java.lang.Long getLiquidateCost(){
return liquidateCost;
}
public void setLiquidateCost(java.lang.Long liquidateCost)
{
this.liquidateCost = liquidateCost;
}
@Column(name = "SIGN_GROUP_ID", length = 22)
public java.lang.Long getSignGroupId(){
return signGroupId;
}
public void setSignGroupId(java.lang.Long signGroupId)
{
this.signGroupId = signGroupId;
}
@Column(name = "CURRENCY_RATE", length = 22)
public java.lang.Double getCurrencyRate(){
return currencyRate;
}
public void setCurrencyRate(java.lang.Double currencyRate)
{
this.currencyRate = currencyRate;
}
@Column(name = "HAS_WEIGHT", length = 22)
public java.lang.Long getHasWeight(){
return hasWeight;
}
public void setHasWeight(java.lang.Long hasWeight)
{
this.hasWeight = hasWeight;
}
@Column(name = "ORIGINAL_ID", length = 22)
public java.lang.Long getOriginalId(){
return originalId;
}
public void setOriginalId(java.lang.Long originalId)
{
this.originalId = originalId;
}
@Column(name = "DISCOUNT_UNIT", length = 22)
public java.lang.Long getDiscountUnit(){
return discountUnit;
}
public void setDiscountUnit(java.lang.Long discountUnit)
{
this.discountUnit = discountUnit;
}
@Column(name = "MER_DESCRIPTION", length = 2400)
public java.lang.String getMerDescription(){
return merDescription;
}
public void setMerDescription(java.lang.String merDescription)
{
this.merDescription = merDescription;
}
@Column(name = "TOTAL_VALUE", length = 22)
public java.lang.Long getTotalValue(){
return totalValue;
}
public void setTotalValue(java.lang.Long totalValue)
{
this.totalValue = totalValue;
}
@Column(name = "PROJECT_MNG_COST", length = 22)
public java.lang.Long getProjectMngCost(){
return projectMngCost;
}
public void setProjectMngCost(java.lang.Long projectMngCost)
{
this.projectMngCost = projectMngCost;
}
@Column(name = "WARRANTY_BEGIN", length = 22)
public java.lang.Long getWarrantyBegin(){
return warrantyBegin;
}
public void setWarrantyBegin(java.lang.Long warrantyBegin)
{
this.warrantyBegin = warrantyBegin;
}
@Column(name = "REAL_LIQUIDATE_DATE", length = 7)
public java.util.Date getRealLiquidateDate(){
return realLiquidateDate;
}
public void setRealLiquidateDate(java.util.Date realLiquidateDate)
{
this.realLiquidateDate = realLiquidateDate;
}
@Column(name = "IMPORT_GROUP_ID", length = 22)
public java.lang.Long getImportGroupId(){
return importGroupId;
}
public void setImportGroupId(java.lang.Long importGroupId)
{
this.importGroupId = importGroupId;
}
@Column(name = "SUPERVISOR", length = 200)
public java.lang.String getSupervisor(){
return supervisor;
}
public void setSupervisor(java.lang.String supervisor)
{
this.supervisor = supervisor;
}
@Column(name = "IMPORT_MER_GROUP_ID", length = 22)
public java.lang.Long getImportMerGroupId(){
return importMerGroupId;
}
public void setImportMerGroupId(java.lang.Long importMerGroupId)
{
this.importMerGroupId = importMerGroupId;
}
@Column(name = "PAYMENT_GROUP_ID", length = 22)
public java.lang.Long getPaymentGroupId(){
return paymentGroupId;
}
public void setPaymentGroupId(java.lang.Long paymentGroupId)
{
this.paymentGroupId = paymentGroupId;
}
@Column(name = "PAY_CURRENCY_ID", length = 22)
public java.lang.Long getPayCurrencyId(){
return payCurrencyId;
}
public void setPayCurrencyId(java.lang.Long payCurrencyId)
{
this.payCurrencyId = payCurrencyId;
}
@Column(name = "REQUIRE_CHANGE_MER", length = 22)
public java.lang.Long getRequireChangeMer(){
return requireChangeMer;
}
public void setRequireChangeMer(java.lang.Long requireChangeMer)
{
this.requireChangeMer = requireChangeMer;
}
@Column(name = "IS_OLD", length = 22)
public java.lang.Long getIsOld(){
return isOld;
}
public void setIsOld(java.lang.Long isOld)
{
this.isOld = isOld;
}
@Column(name = "WARRANTY_BEGIN_DATE", length = 7)
public java.util.Date getWarrantyBeginDate(){
return warrantyBeginDate;
}
public void setWarrantyBeginDate(java.util.Date warrantyBeginDate)
{
this.warrantyBeginDate = warrantyBeginDate;
}
@Column(name = "PACKAGE_CODE", length = 400)
public java.lang.String getPackageCode(){
return packageCode;
}
public void setPackageCode(java.lang.String packageCode)
{
this.packageCode = packageCode;
}
@Column(name = "MER_COST_REMAIN", length = 22)
public java.lang.Long getMerCostRemain(){
return merCostRemain;
}
public void setMerCostRemain(java.lang.Long merCostRemain)
{
this.merCostRemain = merCostRemain;
}
@Column(name = "CONTRACT_FRAME_ID", length = 22)
public java.lang.Long getContractFrameId(){
return contractFrameId;
}
public void setContractFrameId(java.lang.Long contractFrameId)
{
this.contractFrameId = contractFrameId;
}
@Column(name = "EXTEND_STATUS_ID", length = 22)
public java.lang.Long getExtendStatusId(){
return extendStatusId;
}
public void setExtendStatusId(java.lang.Long extendStatusId)
{
this.extendStatusId = extendStatusId;
}
@Column(name = "ACCOUNT_TYPE", length = 22)
public java.lang.Long getAccountType(){
return accountType;
}
public void setAccountType(java.lang.Long accountType)
{
this.accountType = accountType;
}
@Column(name = "DEPRECIATION_DATE", length = 7)
public java.util.Date getDepreciationDate(){
return depreciationDate;
}
public void setDepreciationDate(java.util.Date depreciationDate)
{
this.depreciationDate = depreciationDate;
}
@Column(name = "PROJECT_ID", length = 22)
public java.lang.Long getProjectId(){
return projectId;
}
public void setProjectId(java.lang.Long projectId)
{
this.projectId = projectId;
}
@Column(name = "WARRANTY_PERIOD", length = 22)
public java.lang.Long getWarrantyPeriod(){
return warrantyPeriod;
}
public void setWarrantyPeriod(java.lang.Long warrantyPeriod)
{
this.warrantyPeriod = warrantyPeriod;
}
@Column(name = "WARRANTY_PARTNER_ID", length = 22)
public java.lang.Long getWarrantyPartnerId(){
return warrantyPartnerId;
}
public void setWarrantyPartnerId(java.lang.Long warrantyPartnerId)
{
this.warrantyPartnerId = warrantyPartnerId;
}
@Column(name = "GROUP_PRICE_PERCENT_ID", length = 22)
public java.lang.Long getGroupPricePercentId(){
return groupPricePercentId;
}
public void setGroupPricePercentId(java.lang.Long groupPricePercentId)
{
this.groupPricePercentId = groupPricePercentId;
}
@Column(name = "GROUP_PRICE_SHIPMENT_ID", length = 22)
public java.lang.Long getGroupPriceShipmentId(){
return groupPriceShipmentId;
}
public void setGroupPriceShipmentId(java.lang.Long groupPriceShipmentId)
{
this.groupPriceShipmentId = groupPriceShipmentId;
}
@Column(name = "USE_GROUP_ID", length = 22)
public java.lang.Long getUseGroupId(){
return useGroupId;
}
public void setUseGroupId(java.lang.Long useGroupId)
{
this.useGroupId = useGroupId;
}
@Column(name = "IS_PROPOSAL", length = 22)
public java.lang.Long getIsProposal(){
return isProposal;
}
public void setIsProposal(java.lang.Long isProposal)
{
this.isProposal = isProposal;
}
@Column(name = "IS_CONFIG", length = 22)
public java.lang.Long getIsConfig(){
return isConfig;
}
public void setIsConfig(java.lang.Long isConfig)
{
this.isConfig = isConfig;
}
@Column(name = "EXPIRE_DATE", length = 7)
public java.util.Date getExpireDate(){
return expireDate;
}
public void setExpireDate(java.util.Date expireDate)
{
this.expireDate = expireDate;
}
@Column(name = "REVENUE_LIMIT", length = 22)
public java.lang.Long getRevenueLimit(){
return revenueLimit;
}
public void setRevenueLimit(java.lang.Long revenueLimit)
{
this.revenueLimit = revenueLimit;
}
@Column(name = "SIGNER", length = 100)
public java.lang.String getSigner(){
return signer;
}
public void setSigner(java.lang.String signer)
{
this.signer = signer;
}
@Column(name = "PAYMENT_EXPIRE", length = 22)
public java.lang.Long getPaymentExpire(){
return paymentExpire;
}
public void setPaymentExpire(java.lang.Long paymentExpire)
{
this.paymentExpire = paymentExpire;
}
@Column(name = "PARTNER_NAME", length = 1000)
public java.lang.String getPartnerName(){
return partnerName;
}
public void setPartnerName(java.lang.String partnerName)
{
this.partnerName = partnerName;
}
@Column(name = "CANCEL_DATE", length = 7)
public java.util.Date getCancelDate(){
return cancelDate;
}
public void setCancelDate(java.util.Date cancelDate)
{
this.cancelDate = cancelDate;
}
@Column(name = "CANCEL_REASON", length = 1000)
public java.lang.String getCancelReason(){
return cancelReason;
}
public void setCancelReason(java.lang.String cancelReason)
{
this.cancelReason = cancelReason;
}
@Column(name = "LIQUIDATE_REASON", length = 1000)
public java.lang.String getLiquidateReason(){
return liquidateReason;
}
public void setLiquidateReason(java.lang.String liquidateReason)
{
this.liquidateReason = liquidateReason;
}
   

    @Override
    public CntContractDTO toDTO() {
        CntContractDTO cntContractDTO = new CntContractDTO();
        //set cac gia tri 
        cntContractDTO.setMapDate(this.mapDate);
        cntContractDTO.setDuration(this.duration);
        cntContractDTO.setIsActive(this.isActive);
        cntContractDTO.setIsRealValue(this.isRealValue);
        cntContractDTO.setIsExtension(this.isExtension);
        cntContractDTO.setIsExtended(this.isExtended);
        cntContractDTO.setTrackingCode(this.trackingCode);
        cntContractDTO.setContent(this.content);
        cntContractDTO.setProposalFacDate(this.proposalFacDate);
        cntContractDTO.setProposalFacValue(this.proposalFacValue);
        cntContractDTO.setAcceptDate(this.acceptDate);
        cntContractDTO.setProcessGroupId(this.processGroupId);
        cntContractDTO.setLiquidateCode(this.liquidateCode);
        cntContractDTO.setSingerLiquidate(this.singerLiquidate);
        cntContractDTO.setAcceptCode(this.acceptCode);
        cntContractDTO.setSingerAccept(this.singerAccept);
        cntContractDTO.setKcsDocStatus(this.kcsDocStatus);
        cntContractDTO.setCheckApproval(this.checkApproval);
        cntContractDTO.setBillNumber(this.billNumber);
        cntContractDTO.setCatFormPaymentId(this.catFormPaymentId);
        cntContractDTO.setFinanceCntCode(this.financeCntCode);
        cntContractDTO.setFinanceCntValue(this.financeCntValue);
        cntContractDTO.setFinanceCntPercentMer(this.financeCntPercentMer);
        cntContractDTO.setPaymentStatusId(this.paymentStatusId);
        cntContractDTO.setLockPayment(this.lockPayment);
        cntContractDTO.setCntSubType(this.cntSubType);
        cntContractDTO.setGuaranteeDateFrom(this.guaranteeDateFrom);
        cntContractDTO.setFinishedDate(this.finishedDate);
        cntContractDTO.setFormsId(this.formsId);
        cntContractDTO.setGuaranteeDateTo(this.guaranteeDateTo);
        cntContractDTO.setPoCode(this.poCode);
        cntContractDTO.setGuaranteeMerNo(this.guaranteeMerNo);
        cntContractDTO.setIsType(this.isType);
        cntContractDTO.setRatioTypeId(this.ratioTypeId);
        cntContractDTO.setErrRatio(this.errRatio);
        cntContractDTO.setErrRatioComment(this.errRatioComment);
        cntContractDTO.setGuaranteeMerPercent(this.guaranteeMerPercent);
        cntContractDTO.setMerQuantity(this.merQuantity);
        cntContractDTO.setMerType(this.merType);
        cntContractDTO.setHaveHshc(this.haveHshc);
        cntContractDTO.setProjInvestProjectId(this.projInvestProjectId);
        cntContractDTO.setCntKindType(this.cntKindType);
        cntContractDTO.setIsApprove(this.isApprove);
        cntContractDTO.setProvinceId(this.provinceId);
        cntContractDTO.setIsLock(this.isLock);
        cntContractDTO.setReasonDeny(this.reasonDeny);
        cntContractDTO.setContractName(this.contractName);
        cntContractDTO.setAgreementType(this.agreementType);
        cntContractDTO.setContractPriceFc(this.contractPriceFc);
        cntContractDTO.setTransRate(this.transRate);
        cntContractDTO.setForeignCurrentId(this.foreignCurrentId);
        cntContractDTO.setAgreementCode(this.agreementCode);
        cntContractDTO.setGroupUsingId(this.groupUsingId);
        cntContractDTO.setCatServiceId(this.catServiceId);
        cntContractDTO.setContractAgreement(this.contractAgreement);
        cntContractDTO.setReason(this.reason);
        cntContractDTO.setContractId(this.contractId);
        cntContractDTO.setContractCode(this.contractCode);
        cntContractDTO.setDescription(this.description);
        cntContractDTO.setGoithauId(this.goithauId);
        cntContractDTO.setPartnerId(this.partnerId);
        cntContractDTO.setPayModeId(this.payModeId);
        cntContractDTO.setSignedDate(this.signedDate);
        cntContractDTO.setStatusId(this.statusId);
        cntContractDTO.setIsPayment(this.isPayment);
        cntContractDTO.setCreatedDate(this.createdDate);
        cntContractDTO.setCreatorId(this.creatorId);
        cntContractDTO.setMerStatus(this.merStatus);
        cntContractDTO.setLothauId(this.lothauId);
        cntContractDTO.setCntNgtId(this.cntNgtId);
        cntContractDTO.setCurrencyId(this.currencyId);
        cntContractDTO.setCntTypeId(this.cntTypeId);
        cntContractDTO.setTaxRate(this.taxRate);
        cntContractDTO.setConstructorCost(this.constructorCost);
        cntContractDTO.setSpareCost(this.spareCost);
        cntContractDTO.setOtherCost(this.otherCost);
        cntContractDTO.setMerCost(this.merCost);
        cntContractDTO.setDiscount(this.discount);
        cntContractDTO.setLiquidateCost(this.liquidateCost);
        cntContractDTO.setSignGroupId(this.signGroupId);
        cntContractDTO.setCurrencyRate(this.currencyRate);
        cntContractDTO.setHasWeight(this.hasWeight);
        cntContractDTO.setOriginalId(this.originalId);
        cntContractDTO.setDiscountUnit(this.discountUnit);
        cntContractDTO.setMerDescription(this.merDescription);
        cntContractDTO.setTotalValue(this.totalValue);
        cntContractDTO.setProjectMngCost(this.projectMngCost);
        cntContractDTO.setWarrantyBegin(this.warrantyBegin);
        cntContractDTO.setRealLiquidateDate(this.realLiquidateDate);
        cntContractDTO.setImportGroupId(this.importGroupId);
        cntContractDTO.setSupervisor(this.supervisor);
        cntContractDTO.setImportMerGroupId(this.importMerGroupId);
        cntContractDTO.setPaymentGroupId(this.paymentGroupId);
        cntContractDTO.setPayCurrencyId(this.payCurrencyId);
        cntContractDTO.setRequireChangeMer(this.requireChangeMer);
        cntContractDTO.setIsOld(this.isOld);
        cntContractDTO.setWarrantyBeginDate(this.warrantyBeginDate);
        cntContractDTO.setPackageCode(this.packageCode);
        cntContractDTO.setMerCostRemain(this.merCostRemain);
        cntContractDTO.setContractFrameId(this.contractFrameId);
        cntContractDTO.setExtendStatusId(this.extendStatusId);
        cntContractDTO.setAccountType(this.accountType);
        cntContractDTO.setDepreciationDate(this.depreciationDate);
        cntContractDTO.setProjectId(this.projectId);
        cntContractDTO.setWarrantyPeriod(this.warrantyPeriod);
        cntContractDTO.setWarrantyPartnerId(this.warrantyPartnerId);
        cntContractDTO.setGroupPricePercentId(this.groupPricePercentId);
        cntContractDTO.setGroupPriceShipmentId(this.groupPriceShipmentId);
        cntContractDTO.setUseGroupId(this.useGroupId);
        cntContractDTO.setIsProposal(this.isProposal);
        cntContractDTO.setIsConfig(this.isConfig);
        cntContractDTO.setExpireDate(this.expireDate);
        cntContractDTO.setRevenueLimit(this.revenueLimit);
        cntContractDTO.setSigner(this.signer);
        cntContractDTO.setPaymentExpire(this.paymentExpire);
        cntContractDTO.setPartnerName(this.partnerName);
        cntContractDTO.setCancelDate(this.cancelDate);
        cntContractDTO.setCancelReason(this.cancelReason);
        cntContractDTO.setLiquidateReason(this.liquidateReason);
        return cntContractDTO;
    }
}
