/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ICntContractDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "IMS_OWNER.I_CNT_CONTRACT")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ICntContractBO extends BaseFWModelImpl {
     
private java.lang.String agreementCode;
private java.lang.Long groupUsingId;
private java.lang.Long catServiceId;
private java.lang.Long contractAgreement;
private java.lang.String reason;
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

 public ICntContractBO() {
        setColId("projInvestProjectId");
        setColName("projInvestProjectId");
        setUniqueColumn(new String[]{"projInvestProjectId"});
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
@Column(name = "STATUS_SIGN", length = 22)
public java.lang.Long getStatusSign(){
return statusSign;
}
public void setStatusSign(java.lang.Long statusSign)
{
this.statusSign = statusSign;
}
@Column(name = "COMMENT_STATUS_SIGN", length = 4000)
public java.lang.String getCommentStatusSign(){
return commentStatusSign;
}
public void setCommentStatusSign(java.lang.String commentStatusSign)
{
this.commentStatusSign = commentStatusSign;
}
@Column(name = "CONTRACT_NATURE", length = 22)
public java.lang.Long getContractNature(){
return contractNature;
}
public void setContractNature(java.lang.Long contractNature)
{
this.contractNature = contractNature;
}
@Column(name = "NEXT_STEP_GROUP_ID", length = 22)
public java.lang.Long getNextStepGroupId(){
return nextStepGroupId;
}
public void setNextStepGroupId(java.lang.Long nextStepGroupId)
{
this.nextStepGroupId = nextStepGroupId;
}
@Column(name = "PROC_PACKAGE_ID", length = 22)
public java.lang.Long getProcPackageId(){
return procPackageId;
}
public void setProcPackageId(java.lang.Long procPackageId)
{
this.procPackageId = procPackageId;
}
@Column(name = "PROC_COMPONENT_ID", length = 22)
public java.lang.Long getProcComponentId(){
return procComponentId;
}
public void setProcComponentId(java.lang.Long procComponentId)
{
this.procComponentId = procComponentId;
}
@Column(name = "HANDOVER_PLACE", length = 2000)
public java.lang.String getHandoverPlace(){
return handoverPlace;
}
public void setHandoverPlace(java.lang.String handoverPlace)
{
this.handoverPlace = handoverPlace;
}
@Column(name = "HANDOVER_TIME", length = 7)
public java.util.Date getHandoverTime(){
return handoverTime;
}
public void setHandoverTime(java.util.Date handoverTime)
{
this.handoverTime = handoverTime;
}
@Column(name = "A_REP_INVESTOR_ID", length = 22)
public java.lang.Long getARepInvestorId(){
return aRepInvestorId;
}
public void setARepInvestorId(java.lang.Long aRepInvestorId)
{
this.aRepInvestorId = aRepInvestorId;
}
@Column(name = "CONTRACT_VALUE_BF_TAX", length = 22)
public java.lang.Double getContractValueBfTax(){
return contractValueBfTax;
}
public void setContractValueBfTax(java.lang.Double contractValueBfTax)
{
this.contractValueBfTax = contractValueBfTax;
}
@Column(name = "TAX", length = 22)
public java.lang.Double getTax(){
return tax;
}
public void setTax(java.lang.Double tax)
{
this.tax = tax;
}
@Column(name = "PAYMENT_DEADLINE", length = 400)
public java.lang.String getPaymentDeadline(){
return paymentDeadline;
}
public void setPaymentDeadline(java.lang.String paymentDeadline)
{
this.paymentDeadline = paymentDeadline;
}
@Column(name = "WORK_SCOPE", length = 4000)
public java.lang.String getWorkScope(){
return workScope;
}
public void setWorkScope(java.lang.String workScope)
{
this.workScope = workScope;
}
@Column(name = "LICENSING_PROCEDURE_CONSTR", length = 2000)
public java.lang.String getLicensingProcedureConstr(){
return licensingProcedureConstr;
}
public void setLicensingProcedureConstr(java.lang.String licensingProcedureConstr)
{
this.licensingProcedureConstr = licensingProcedureConstr;
}
@Column(name = "PERFORM_START_DATE", length = 7)
public java.util.Date getPerformStartDate(){
return performStartDate;
}
public void setPerformStartDate(java.util.Date performStartDate)
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
@Column(name = "PAYMENT_TIME_NUMBER", length = 22)
public java.lang.Long getPaymentTimeNumber(){
return paymentTimeNumber;
}
public void setPaymentTimeNumber(java.lang.Long paymentTimeNumber)
{
this.paymentTimeNumber = paymentTimeNumber;
}
@Column(name = "PAYMENT_STYLE", length = 22)
public java.lang.Long getPaymentStyle(){
return paymentStyle;
}
public void setPaymentStyle(java.lang.Long paymentStyle)
{
this.paymentStyle = paymentStyle;
}
@Column(name = "CURRENCY_COUNT", length = 22)
public java.lang.Long getCurrencyCount(){
return currencyCount;
}
public void setCurrencyCount(java.lang.Long currencyCount)
{
this.currencyCount = currencyCount;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "CONTRACT_TYPE", length = 22)
public java.lang.Long getContractType(){
return contractType;
}
public void setContractType(java.lang.Long contractType)
{
this.contractType = contractType;
}
@Column(name = "NEGOTIATION_MINUTES_ID", length = 22)
public java.lang.Long getNegotiationMinutesId(){
return negotiationMinutesId;
}
public void setNegotiationMinutesId(java.lang.Long negotiationMinutesId)
{
this.negotiationMinutesId = negotiationMinutesId;
}
@Column(name = "BID_PACKAGE_ID", length = 22)
public java.lang.Long getBidPackageId(){
return bidPackageId;
}
public void setBidPackageId(java.lang.Long bidPackageId)
{
this.bidPackageId = bidPackageId;
}
@Column(name = "BID_PACKAGE_BLOCK_ID", length = 22)
public java.lang.Long getBidPackageBlockId(){
return bidPackageBlockId;
}
public void setBidPackageBlockId(java.lang.Long bidPackageBlockId)
{
this.bidPackageBlockId = bidPackageBlockId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "IMS_OWNER.I_CNT_CONTRACT_SEQ")
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
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
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
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "IMS_OWNER.I_CNT_CONTRACT_SEQ")
            }
    )
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
@Column(name = "INVESTMENT_PROPOSAL_GROUP_ID", length = 22)
public java.lang.Long getInvestmentProposalGroupId(){
return investmentProposalGroupId;
}
public void setInvestmentProposalGroupId(java.lang.Long investmentProposalGroupId)
{
this.investmentProposalGroupId = investmentProposalGroupId;
}
@Column(name = "INTEGRATED_CONTRACT_ID", length = 22)
public java.lang.Long getIntegratedContractId(){
return integratedContractId;
}
public void setIntegratedContractId(java.lang.Long integratedContractId)
{
this.integratedContractId = integratedContractId;
}
@Column(name = "CAPITAL_SOURCE_TYPE", length = 22)
public java.lang.Long getCapitalSourceType(){
return capitalSourceType;
}
public void setCapitalSourceType(java.lang.Long capitalSourceType)
{
this.capitalSourceType = capitalSourceType;
}
@Column(name = "EXTEND_STATUS_ID", length = 22)
public java.lang.Long getExtendStatusId(){
return extendStatusId;
}
public void setExtendStatusId(java.lang.Long extendStatusId)
{
this.extendStatusId = extendStatusId;
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
@Column(name = "CONTRACT_NAME", length = 500)
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
   

    @Override
    public ICntContractDTO toDTO() {
        ICntContractDTO iCntContractDTO = new ICntContractDTO();
        //set cac gia tri 
        iCntContractDTO.setAgreementCode(this.agreementCode);
        iCntContractDTO.setGroupUsingId(this.groupUsingId);
        iCntContractDTO.setCatServiceId(this.catServiceId);
        iCntContractDTO.setContractAgreement(this.contractAgreement);
        iCntContractDTO.setReason(this.reason);
        iCntContractDTO.setStatusSign(this.statusSign);
        iCntContractDTO.setCommentStatusSign(this.commentStatusSign);
        iCntContractDTO.setContractNature(this.contractNature);
        iCntContractDTO.setNextStepGroupId(this.nextStepGroupId);
        iCntContractDTO.setProcPackageId(this.procPackageId);
        iCntContractDTO.setProcComponentId(this.procComponentId);
        iCntContractDTO.setHandoverPlace(this.handoverPlace);
        iCntContractDTO.setHandoverTime(this.handoverTime);
        iCntContractDTO.setARepInvestorId(this.aRepInvestorId);
        iCntContractDTO.setContractValueBfTax(this.contractValueBfTax);
        iCntContractDTO.setTax(this.tax);
        iCntContractDTO.setPaymentDeadline(this.paymentDeadline);
        iCntContractDTO.setWorkScope(this.workScope);
        iCntContractDTO.setLicensingProcedureConstr(this.licensingProcedureConstr);
        iCntContractDTO.setPerformStartDate(this.performStartDate);
        iCntContractDTO.setExecutionTime(this.executionTime);
        iCntContractDTO.setLatePaymentPenaltyClause(this.latePaymentPenaltyClause);
        iCntContractDTO.setContractGuaranteeMethod(this.contractGuaranteeMethod);
        iCntContractDTO.setPaymentTimeNumber(this.paymentTimeNumber);
        iCntContractDTO.setPaymentStyle(this.paymentStyle);
        iCntContractDTO.setCurrencyCount(this.currencyCount);
        iCntContractDTO.setStatus(this.status);
        iCntContractDTO.setContractType(this.contractType);
        iCntContractDTO.setNegotiationMinutesId(this.negotiationMinutesId);
        iCntContractDTO.setBidPackageId(this.bidPackageId);
        iCntContractDTO.setBidPackageBlockId(this.bidPackageBlockId);
        iCntContractDTO.setContractId(this.contractId);
        iCntContractDTO.setCode(this.code);
        iCntContractDTO.setDescription(this.description);
        iCntContractDTO.setGoithauId(this.goithauId);
        iCntContractDTO.setPartnerId(this.partnerId);
        iCntContractDTO.setPayModeId(this.payModeId);
        iCntContractDTO.setSignedDate(this.signedDate);
        iCntContractDTO.setStatusId(this.statusId);
        iCntContractDTO.setIsPayment(this.isPayment);
        iCntContractDTO.setCreatedDate(this.createdDate);
        iCntContractDTO.setCreatorId(this.creatorId);
        iCntContractDTO.setMerStatus(this.merStatus);
        iCntContractDTO.setLothauId(this.lothauId);
        iCntContractDTO.setCntNgtId(this.cntNgtId);
        iCntContractDTO.setCurrencyId(this.currencyId);
        iCntContractDTO.setCntTypeId(this.cntTypeId);
        iCntContractDTO.setTaxRate(this.taxRate);
        iCntContractDTO.setConstructorCost(this.constructorCost);
        iCntContractDTO.setSpareCost(this.spareCost);
        iCntContractDTO.setOtherCost(this.otherCost);
        iCntContractDTO.setMerCost(this.merCost);
        iCntContractDTO.setDiscount(this.discount);
        iCntContractDTO.setLiquidateCost(this.liquidateCost);
        iCntContractDTO.setSignGroupId(this.signGroupId);
        iCntContractDTO.setCurrencyRate(this.currencyRate);
        iCntContractDTO.setHasWeight(this.hasWeight);
        iCntContractDTO.setOriginalId(this.originalId);
        iCntContractDTO.setDiscountUnit(this.discountUnit);
        iCntContractDTO.setMerDescription(this.merDescription);
        iCntContractDTO.setTotalValue(this.totalValue);
        iCntContractDTO.setProjectMngCost(this.projectMngCost);
        iCntContractDTO.setWarrantyBegin(this.warrantyBegin);
        iCntContractDTO.setRealLiquidateDate(this.realLiquidateDate);
        iCntContractDTO.setImportGroupId(this.importGroupId);
        iCntContractDTO.setSupervisor(this.supervisor);
        iCntContractDTO.setImportMerGroupId(this.importMerGroupId);
        iCntContractDTO.setPaymentGroupId(this.paymentGroupId);
        iCntContractDTO.setPayCurrencyId(this.payCurrencyId);
        iCntContractDTO.setRequireChangeMer(this.requireChangeMer);
        iCntContractDTO.setIsOld(this.isOld);
        iCntContractDTO.setWarrantyBeginDate(this.warrantyBeginDate);
        iCntContractDTO.setPackageCode(this.packageCode);
        iCntContractDTO.setMerCostRemain(this.merCostRemain);
        iCntContractDTO.setContractFrameId(this.contractFrameId);
        iCntContractDTO.setAccountType(this.accountType);
        iCntContractDTO.setDepreciationDate(this.depreciationDate);
        iCntContractDTO.setProjectId(this.projectId);
        iCntContractDTO.setWarrantyPeriod(this.warrantyPeriod);
        iCntContractDTO.setWarrantyPartnerId(this.warrantyPartnerId);
        iCntContractDTO.setGroupPricePercentId(this.groupPricePercentId);
        iCntContractDTO.setGroupPriceShipmentId(this.groupPriceShipmentId);
        iCntContractDTO.setUseGroupId(this.useGroupId);
        iCntContractDTO.setIsProposal(this.isProposal);
        iCntContractDTO.setIsConfig(this.isConfig);
        iCntContractDTO.setExpireDate(this.expireDate);
        iCntContractDTO.setRevenueLimit(this.revenueLimit);
        iCntContractDTO.setSigner(this.signer);
        iCntContractDTO.setPaymentExpire(this.paymentExpire);
        iCntContractDTO.setPartnerName(this.partnerName);
        iCntContractDTO.setCancelDate(this.cancelDate);
        iCntContractDTO.setCancelReason(this.cancelReason);
        iCntContractDTO.setLiquidateReason(this.liquidateReason);
        iCntContractDTO.setMapDate(this.mapDate);
        iCntContractDTO.setDuration(this.duration);
        iCntContractDTO.setIsActive(this.isActive);
        iCntContractDTO.setIsRealValue(this.isRealValue);
        iCntContractDTO.setIsExtension(this.isExtension);
        iCntContractDTO.setIsExtended(this.isExtended);
        iCntContractDTO.setTrackingCode(this.trackingCode);
        iCntContractDTO.setContent(this.content);
        iCntContractDTO.setProposalFacDate(this.proposalFacDate);
        iCntContractDTO.setProposalFacValue(this.proposalFacValue);
        iCntContractDTO.setAcceptDate(this.acceptDate);
        iCntContractDTO.setProcessGroupId(this.processGroupId);
        iCntContractDTO.setLiquidateCode(this.liquidateCode);
        iCntContractDTO.setSingerLiquidate(this.singerLiquidate);
        iCntContractDTO.setAcceptCode(this.acceptCode);
        iCntContractDTO.setSingerAccept(this.singerAccept);
        iCntContractDTO.setKcsDocStatus(this.kcsDocStatus);
        iCntContractDTO.setCheckApproval(this.checkApproval);
        iCntContractDTO.setBillNumber(this.billNumber);
        iCntContractDTO.setCatFormPaymentId(this.catFormPaymentId);
        iCntContractDTO.setFinanceCntCode(this.financeCntCode);
        iCntContractDTO.setFinanceCntValue(this.financeCntValue);
        iCntContractDTO.setFinanceCntPercentMer(this.financeCntPercentMer);
        iCntContractDTO.setCntSubType(this.cntSubType);
        iCntContractDTO.setGuaranteeDateFrom(this.guaranteeDateFrom);
        iCntContractDTO.setFinishedDate(this.finishedDate);
        iCntContractDTO.setFormsId(this.formsId);
        iCntContractDTO.setGuaranteeDateTo(this.guaranteeDateTo);
        iCntContractDTO.setPoCode(this.poCode);
        iCntContractDTO.setGuaranteeMerNo(this.guaranteeMerNo);
        iCntContractDTO.setIsType(this.isType);
        iCntContractDTO.setRatioTypeId(this.ratioTypeId);
        iCntContractDTO.setErrRatio(this.errRatio);
        iCntContractDTO.setErrRatioComment(this.errRatioComment);
        iCntContractDTO.setGuaranteeMerPercent(this.guaranteeMerPercent);
        iCntContractDTO.setMerQuantity(this.merQuantity);
        iCntContractDTO.setMerType(this.merType);
        iCntContractDTO.setHaveHshc(this.haveHshc);
        iCntContractDTO.setProjInvestProjectId(this.projInvestProjectId);
        iCntContractDTO.setCntKindType(this.cntKindType);
        iCntContractDTO.setIsApprove(this.isApprove);
        iCntContractDTO.setProvinceId(this.provinceId);
        iCntContractDTO.setIsLock(this.isLock);
        iCntContractDTO.setReasonDeny(this.reasonDeny);
        iCntContractDTO.setInvestmentProposalGroupId(this.investmentProposalGroupId);
        iCntContractDTO.setIntegratedContractId(this.integratedContractId);
        iCntContractDTO.setCapitalSourceType(this.capitalSourceType);
        iCntContractDTO.setExtendStatusId(this.extendStatusId);
        iCntContractDTO.setPaymentStatusId(this.paymentStatusId);
        iCntContractDTO.setLockPayment(this.lockPayment);
        iCntContractDTO.setContractName(this.contractName);
        iCntContractDTO.setAgreementType(this.agreementType);
        iCntContractDTO.setContractPriceFc(this.contractPriceFc);
        iCntContractDTO.setTransRate(this.transRate);
        iCntContractDTO.setForeignCurrentId(this.foreignCurrentId);
        return iCntContractDTO;
    }
}
