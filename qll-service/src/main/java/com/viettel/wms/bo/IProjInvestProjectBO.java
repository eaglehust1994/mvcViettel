/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "IMS_OWNER.I_PROJ_INVEST_PROJECT")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class IProjInvestProjectBO extends BaseFWModelImpl {
     
private java.lang.Long otherSpendingFCurrencyT;
private java.lang.Long transRate;
private java.lang.Long totalSpendingBt;
private java.lang.Long totalSpendingBtF;
private java.lang.String capitalDescription;
private java.lang.Long constructionType;
private java.lang.Long holdPartnerId;
private java.lang.Double discountRate;
private java.lang.Long returnDay;
private java.lang.Double internalRateOfReturn;
private java.lang.Double netPresentValue;
private java.lang.Long investType;
private java.lang.String verifyComment;
private java.lang.String otherEffect;
private java.lang.String serviceType;
private java.util.Date lawProfileDate;
private java.lang.Long projectType;
private java.lang.String researchManager;
private java.lang.String researchArea;
private java.lang.String researchType;
private java.lang.Long settlementGroupId;
private java.lang.Long approvalGroupId;
private java.lang.Double royaltyCost;
private java.lang.Double laborCost;
private java.lang.Double materialCost;
private java.lang.Double instrumentFixAssetCost;
private java.lang.Double outHireCost;
private java.lang.Double testCost;
private java.lang.Double otherCost;
private java.lang.Double insureCost;
private java.lang.Long status;
private java.lang.Long projInvestProjectId;
private java.lang.String name;
private java.lang.String code;
private java.lang.String investmentOwner;
private java.lang.Double equipmentSpending;
private java.lang.Double constructionSpending;
private java.lang.Double otherSpending;
private java.lang.Double spareSpending;
private java.lang.Long creatorId;
private java.lang.Long creatorGroupId;
private java.lang.Long statusCode;
private java.lang.String approvalContent;
private java.lang.String appraisalContent;
private java.lang.Long currencyId;
private java.util.Date beginDate;
private java.util.Date endDate;
private java.lang.String closeComment;
private java.lang.Long closeReasonId;
private java.lang.Long deploymentGroupId;
private java.lang.Long catServiceId;
private java.lang.Long catInvestProjectType;
private java.lang.String deploymentLocation;
private java.lang.Long setUpGroupId;
private java.lang.Double manageProjectSpending;
private java.util.Date createdDate;
private java.lang.Long createType;
private java.lang.Long originalId;
private java.lang.String reason;
private java.lang.Long ableToPlan;
private java.lang.String adjustContent;
private java.lang.Long parentId;
private java.util.Date appraisalDate;
private java.util.Date approvalDate;
private java.lang.String appraisalCode;
private java.lang.String appraisalSigner;
private java.lang.String approvalCode;
private java.lang.String approvalSigner;
private java.lang.String reqApprovalSigner;
private java.util.Date reqApprovalDate;
private java.lang.String reqApprovalCode;
private java.lang.Long holdGroupId;
private java.lang.Long decisionGroupId;
private java.lang.String investGoal;
private java.lang.String investLocation;
private java.lang.Long useGroupId;
private java.lang.Long headCreateEmployeeId;
private java.lang.Long compensateCostBt;
private java.lang.Long compensateCostT;
private java.lang.Long equipmentSpendingFCurrency;
private java.lang.Long equipmentSpendingTax;
private java.lang.Long advisoryCostBt;
private java.lang.Long advisoryCostT;
private java.lang.Long otherSpendingFCurrency;
private java.lang.Long otherSpendingTax;
private java.lang.Long totalSpendingTax;
private java.lang.Long totalSpending;
private java.lang.Long capital;
private java.lang.Long investQuality;
private java.lang.Long type;
private java.lang.Long constructionLevel;
private java.util.Date estimateStartDate;
private java.util.Date estimateEndDate;
private java.util.Date projectSubmitDate;
private java.lang.Long assignEmployeeId;
private java.lang.Long reviewEmployeeId;
private java.lang.String reviewContent;
private java.util.Date reviewDeadlineDate;
private java.util.Date reviewFinishDate;
private java.util.Date investDecisionDate;
private java.lang.Long investSignEmployeeId;
private java.util.Date investFinishDate;
private java.util.Date projectFinishDate;
private java.lang.String settlementDocumentNo;
private java.util.Date settlementDate;
private java.lang.Long settlementSignEmployeeId;
private java.lang.Long constructionSpendingT;
private java.lang.Long manageProjectSpendingT;
private java.lang.Long spareSpendingT;
private java.lang.Long equipmentSpendingFTax;

 public IProjInvestProjectBO() {
        setColId("projInvestProjectId");
        setColName("projInvestProjectId");
        setUniqueColumn(new String[]{"projInvestProjectId"});
}

@Column(name = "OTHER_SPENDING_F_CURRENCY_T", length = 22)
public java.lang.Long getOtherSpendingFCurrencyT(){
return otherSpendingFCurrencyT;
}
public void setOtherSpendingFCurrencyT(java.lang.Long otherSpendingFCurrencyT)
{
this.otherSpendingFCurrencyT = otherSpendingFCurrencyT;
}
@Column(name = "TRANS_RATE", length = 22)
public java.lang.Long getTransRate(){
return transRate;
}
public void setTransRate(java.lang.Long transRate)
{
this.transRate = transRate;
}
@Column(name = "TOTAL_SPENDING_BT", length = 22)
public java.lang.Long getTotalSpendingBt(){
return totalSpendingBt;
}
public void setTotalSpendingBt(java.lang.Long totalSpendingBt)
{
this.totalSpendingBt = totalSpendingBt;
}
@Column(name = "TOTAL_SPENDING_BT_F", length = 22)
public java.lang.Long getTotalSpendingBtF(){
return totalSpendingBtF;
}
public void setTotalSpendingBtF(java.lang.Long totalSpendingBtF)
{
this.totalSpendingBtF = totalSpendingBtF;
}
@Column(name = "CAPITAL_DESCRIPTION", length = 600)
public java.lang.String getCapitalDescription(){
return capitalDescription;
}
public void setCapitalDescription(java.lang.String capitalDescription)
{
this.capitalDescription = capitalDescription;
}
@Column(name = "CONSTRUCTION_TYPE", length = 22)
public java.lang.Long getConstructionType(){
return constructionType;
}
public void setConstructionType(java.lang.Long constructionType)
{
this.constructionType = constructionType;
}
@Column(name = "HOLD_PARTNER_ID", length = 22)
public java.lang.Long getHoldPartnerId(){
return holdPartnerId;
}
public void setHoldPartnerId(java.lang.Long holdPartnerId)
{
this.holdPartnerId = holdPartnerId;
}
@Column(name = "DISCOUNT_RATE", length = 22)
public java.lang.Double getDiscountRate(){
return discountRate;
}
public void setDiscountRate(java.lang.Double discountRate)
{
this.discountRate = discountRate;
}
@Column(name = "RETURN_DAY", length = 22)
public java.lang.Long getReturnDay(){
return returnDay;
}
public void setReturnDay(java.lang.Long returnDay)
{
this.returnDay = returnDay;
}
@Column(name = "INTERNAL_RATE_OF_RETURN", length = 22)
public java.lang.Double getInternalRateOfReturn(){
return internalRateOfReturn;
}
public void setInternalRateOfReturn(java.lang.Double internalRateOfReturn)
{
this.internalRateOfReturn = internalRateOfReturn;
}
@Column(name = "NET_PRESENT_VALUE", length = 22)
public java.lang.Double getNetPresentValue(){
return netPresentValue;
}
public void setNetPresentValue(java.lang.Double netPresentValue)
{
this.netPresentValue = netPresentValue;
}
@Column(name = "INVEST_TYPE", length = 22)
public java.lang.Long getInvestType(){
return investType;
}
public void setInvestType(java.lang.Long investType)
{
this.investType = investType;
}
@Column(name = "VERIFY_COMMENT", length = 1000)
public java.lang.String getVerifyComment(){
return verifyComment;
}
public void setVerifyComment(java.lang.String verifyComment)
{
this.verifyComment = verifyComment;
}
@Column(name = "OTHER_EFFECT", length = 400)
public java.lang.String getOtherEffect(){
return otherEffect;
}
public void setOtherEffect(java.lang.String otherEffect)
{
this.otherEffect = otherEffect;
}
@Column(name = "SERVICE_TYPE", length = 60)
public java.lang.String getServiceType(){
return serviceType;
}
public void setServiceType(java.lang.String serviceType)
{
this.serviceType = serviceType;
}
@Column(name = "LAW_PROFILE_DATE", length = 7)
public java.util.Date getLawProfileDate(){
return lawProfileDate;
}
public void setLawProfileDate(java.util.Date lawProfileDate)
{
this.lawProfileDate = lawProfileDate;
}
@Column(name = "PROJECT_TYPE", length = 22)
public java.lang.Long getProjectType(){
return projectType;
}
public void setProjectType(java.lang.Long projectType)
{
this.projectType = projectType;
}
@Column(name = "RESEARCH_MANAGER", length = 1000)
public java.lang.String getResearchManager(){
return researchManager;
}
public void setResearchManager(java.lang.String researchManager)
{
this.researchManager = researchManager;
}
@Column(name = "RESEARCH_AREA", length = 2000)
public java.lang.String getResearchArea(){
return researchArea;
}
public void setResearchArea(java.lang.String researchArea)
{
this.researchArea = researchArea;
}
@Column(name = "RESEARCH_TYPE", length = 400)
public java.lang.String getResearchType(){
return researchType;
}
public void setResearchType(java.lang.String researchType)
{
this.researchType = researchType;
}
@Column(name = "SETTLEMENT_GROUP_ID", length = 22)
public java.lang.Long getSettlementGroupId(){
return settlementGroupId;
}
public void setSettlementGroupId(java.lang.Long settlementGroupId)
{
this.settlementGroupId = settlementGroupId;
}
@Column(name = "APPROVAL_GROUP_ID", length = 22)
public java.lang.Long getApprovalGroupId(){
return approvalGroupId;
}
public void setApprovalGroupId(java.lang.Long approvalGroupId)
{
this.approvalGroupId = approvalGroupId;
}
@Column(name = "ROYALTY_COST", length = 22)
public java.lang.Double getRoyaltyCost(){
return royaltyCost;
}
public void setRoyaltyCost(java.lang.Double royaltyCost)
{
this.royaltyCost = royaltyCost;
}
@Column(name = "LABOR_COST", length = 22)
public java.lang.Double getLaborCost(){
return laborCost;
}
public void setLaborCost(java.lang.Double laborCost)
{
this.laborCost = laborCost;
}
@Column(name = "MATERIAL_COST", length = 22)
public java.lang.Double getMaterialCost(){
return materialCost;
}
public void setMaterialCost(java.lang.Double materialCost)
{
this.materialCost = materialCost;
}
@Column(name = "INSTRUMENT_FIX_ASSET_COST", length = 22)
public java.lang.Double getInstrumentFixAssetCost(){
return instrumentFixAssetCost;
}
public void setInstrumentFixAssetCost(java.lang.Double instrumentFixAssetCost)
{
this.instrumentFixAssetCost = instrumentFixAssetCost;
}
@Column(name = "OUT_HIRE_COST", length = 22)
public java.lang.Double getOutHireCost(){
return outHireCost;
}
public void setOutHireCost(java.lang.Double outHireCost)
{
this.outHireCost = outHireCost;
}
@Column(name = "TEST_COST", length = 22)
public java.lang.Double getTestCost(){
return testCost;
}
public void setTestCost(java.lang.Double testCost)
{
this.testCost = testCost;
}
@Column(name = "OTHER_COST", length = 22)
public java.lang.Double getOtherCost(){
return otherCost;
}
public void setOtherCost(java.lang.Double otherCost)
{
this.otherCost = otherCost;
}
@Column(name = "INSURE_COST", length = 22)
public java.lang.Double getInsureCost(){
return insureCost;
}
public void setInsureCost(java.lang.Double insureCost)
{
this.insureCost = insureCost;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "IMS_OWNER.I_PROJ_INVEST_PROJECT_SEQ")
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
@Column(name = "NAME", length = 600)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "INVESTMENT_OWNER", length = 200)
public java.lang.String getInvestmentOwner(){
return investmentOwner;
}
public void setInvestmentOwner(java.lang.String investmentOwner)
{
this.investmentOwner = investmentOwner;
}
@Column(name = "EQUIPMENT_SPENDING", length = 22)
public java.lang.Double getEquipmentSpending(){
return equipmentSpending;
}
public void setEquipmentSpending(java.lang.Double equipmentSpending)
{
this.equipmentSpending = equipmentSpending;
}
@Column(name = "CONSTRUCTION_SPENDING", length = 22)
public java.lang.Double getConstructionSpending(){
return constructionSpending;
}
public void setConstructionSpending(java.lang.Double constructionSpending)
{
this.constructionSpending = constructionSpending;
}
@Column(name = "OTHER_SPENDING", length = 22)
public java.lang.Double getOtherSpending(){
return otherSpending;
}
public void setOtherSpending(java.lang.Double otherSpending)
{
this.otherSpending = otherSpending;
}
@Column(name = "SPARE_SPENDING", length = 22)
public java.lang.Double getSpareSpending(){
return spareSpending;
}
public void setSpareSpending(java.lang.Double spareSpending)
{
this.spareSpending = spareSpending;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CREATOR_GROUP_ID", length = 22)
public java.lang.Long getCreatorGroupId(){
return creatorGroupId;
}
public void setCreatorGroupId(java.lang.Long creatorGroupId)
{
this.creatorGroupId = creatorGroupId;
}
@Column(name = "STATUS_CODE", length = 22)
public java.lang.Long getStatusCode(){
return statusCode;
}
public void setStatusCode(java.lang.Long statusCode)
{
this.statusCode = statusCode;
}
@Column(name = "APPROVAL_CONTENT", length = 1200)
public java.lang.String getApprovalContent(){
return approvalContent;
}
public void setApprovalContent(java.lang.String approvalContent)
{
this.approvalContent = approvalContent;
}
@Column(name = "APPRAISAL_CONTENT", length = 1200)
public java.lang.String getAppraisalContent(){
return appraisalContent;
}
public void setAppraisalContent(java.lang.String appraisalContent)
{
this.appraisalContent = appraisalContent;
}
@Column(name = "CURRENCY_ID", length = 22)
public java.lang.Long getCurrencyId(){
return currencyId;
}
public void setCurrencyId(java.lang.Long currencyId)
{
this.currencyId = currencyId;
}
@Column(name = "BEGIN_DATE", length = 7)
public java.util.Date getBeginDate(){
return beginDate;
}
public void setBeginDate(java.util.Date beginDate)
{
this.beginDate = beginDate;
}
@Column(name = "END_DATE", length = 7)
public java.util.Date getEndDate(){
return endDate;
}
public void setEndDate(java.util.Date endDate)
{
this.endDate = endDate;
}
@Column(name = "CLOSE_COMMENT", length = 1200)
public java.lang.String getCloseComment(){
return closeComment;
}
public void setCloseComment(java.lang.String closeComment)
{
this.closeComment = closeComment;
}
@Column(name = "CLOSE_REASON_ID", length = 22)
public java.lang.Long getCloseReasonId(){
return closeReasonId;
}
public void setCloseReasonId(java.lang.Long closeReasonId)
{
this.closeReasonId = closeReasonId;
}
@Column(name = "DEPLOYMENT_GROUP_ID", length = 22)
public java.lang.Long getDeploymentGroupId(){
return deploymentGroupId;
}
public void setDeploymentGroupId(java.lang.Long deploymentGroupId)
{
this.deploymentGroupId = deploymentGroupId;
}
@Column(name = "CAT_SERVICE_ID", length = 22)
public java.lang.Long getCatServiceId(){
return catServiceId;
}
public void setCatServiceId(java.lang.Long catServiceId)
{
this.catServiceId = catServiceId;
}
@Column(name = "CAT_INVEST_PROJECT_TYPE", length = 22)
public java.lang.Long getCatInvestProjectType(){
return catInvestProjectType;
}
public void setCatInvestProjectType(java.lang.Long catInvestProjectType)
{
this.catInvestProjectType = catInvestProjectType;
}
@Column(name = "DEPLOYMENT_LOCATION", length = 1200)
public java.lang.String getDeploymentLocation(){
return deploymentLocation;
}
public void setDeploymentLocation(java.lang.String deploymentLocation)
{
this.deploymentLocation = deploymentLocation;
}
@Column(name = "SET_UP_GROUP_ID", length = 22)
public java.lang.Long getSetUpGroupId(){
return setUpGroupId;
}
public void setSetUpGroupId(java.lang.Long setUpGroupId)
{
this.setUpGroupId = setUpGroupId;
}
@Column(name = "MANAGE_PROJECT_SPENDING", length = 22)
public java.lang.Double getManageProjectSpending(){
return manageProjectSpending;
}
public void setManageProjectSpending(java.lang.Double manageProjectSpending)
{
this.manageProjectSpending = manageProjectSpending;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATE_TYPE", length = 22)
public java.lang.Long getCreateType(){
return createType;
}
public void setCreateType(java.lang.Long createType)
{
this.createType = createType;
}
@Column(name = "ORIGINAL_ID", length = 22)
public java.lang.Long getOriginalId(){
return originalId;
}
public void setOriginalId(java.lang.Long originalId)
{
this.originalId = originalId;
}
@Column(name = "REASON", length = 1200)
public java.lang.String getReason(){
return reason;
}
public void setReason(java.lang.String reason)
{
this.reason = reason;
}
@Column(name = "ABLE_TO_PLAN", length = 22)
public java.lang.Long getAbleToPlan(){
return ableToPlan;
}
public void setAbleToPlan(java.lang.Long ableToPlan)
{
this.ableToPlan = ableToPlan;
}
@Column(name = "ADJUST_CONTENT", length = 1200)
public java.lang.String getAdjustContent(){
return adjustContent;
}
public void setAdjustContent(java.lang.String adjustContent)
{
this.adjustContent = adjustContent;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "APPRAISAL_DATE", length = 7)
public java.util.Date getAppraisalDate(){
return appraisalDate;
}
public void setAppraisalDate(java.util.Date appraisalDate)
{
this.appraisalDate = appraisalDate;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "APPRAISAL_CODE", length = 40)
public java.lang.String getAppraisalCode(){
return appraisalCode;
}
public void setAppraisalCode(java.lang.String appraisalCode)
{
this.appraisalCode = appraisalCode;
}
@Column(name = "APPRAISAL_SIGNER", length = 40)
public java.lang.String getAppraisalSigner(){
return appraisalSigner;
}
public void setAppraisalSigner(java.lang.String appraisalSigner)
{
this.appraisalSigner = appraisalSigner;
}
@Column(name = "APPROVAL_CODE", length = 40)
public java.lang.String getApprovalCode(){
return approvalCode;
}
public void setApprovalCode(java.lang.String approvalCode)
{
this.approvalCode = approvalCode;
}
@Column(name = "APPROVAL_SIGNER", length = 40)
public java.lang.String getApprovalSigner(){
return approvalSigner;
}
public void setApprovalSigner(java.lang.String approvalSigner)
{
this.approvalSigner = approvalSigner;
}
@Column(name = "REQ_APPROVAL_SIGNER", length = 40)
public java.lang.String getReqApprovalSigner(){
return reqApprovalSigner;
}
public void setReqApprovalSigner(java.lang.String reqApprovalSigner)
{
this.reqApprovalSigner = reqApprovalSigner;
}
@Column(name = "REQ_APPROVAL_DATE", length = 7)
public java.util.Date getReqApprovalDate(){
return reqApprovalDate;
}
public void setReqApprovalDate(java.util.Date reqApprovalDate)
{
this.reqApprovalDate = reqApprovalDate;
}
@Column(name = "REQ_APPROVAL_CODE", length = 40)
public java.lang.String getReqApprovalCode(){
return reqApprovalCode;
}
public void setReqApprovalCode(java.lang.String reqApprovalCode)
{
this.reqApprovalCode = reqApprovalCode;
}
@Column(name = "HOLD_GROUP_ID", length = 22)
public java.lang.Long getHoldGroupId(){
return holdGroupId;
}
public void setHoldGroupId(java.lang.Long holdGroupId)
{
this.holdGroupId = holdGroupId;
}
@Column(name = "DECISION_GROUP_ID", length = 22)
public java.lang.Long getDecisionGroupId(){
return decisionGroupId;
}
public void setDecisionGroupId(java.lang.Long decisionGroupId)
{
this.decisionGroupId = decisionGroupId;
}
@Column(name = "INVEST_GOAL", length = 2000)
public java.lang.String getInvestGoal(){
return investGoal;
}
public void setInvestGoal(java.lang.String investGoal)
{
this.investGoal = investGoal;
}
@Column(name = "INVEST_LOCATION", length = 1000)
public java.lang.String getInvestLocation(){
return investLocation;
}
public void setInvestLocation(java.lang.String investLocation)
{
this.investLocation = investLocation;
}
@Column(name = "USE_GROUP_ID", length = 22)
public java.lang.Long getUseGroupId(){
return useGroupId;
}
public void setUseGroupId(java.lang.Long useGroupId)
{
this.useGroupId = useGroupId;
}
@Column(name = "HEAD_CREATE_EMPLOYEE_ID", length = 22)
public java.lang.Long getHeadCreateEmployeeId(){
return headCreateEmployeeId;
}
public void setHeadCreateEmployeeId(java.lang.Long headCreateEmployeeId)
{
this.headCreateEmployeeId = headCreateEmployeeId;
}
@Column(name = "COMPENSATE_COST_BT", length = 22)
public java.lang.Long getCompensateCostBt(){
return compensateCostBt;
}
public void setCompensateCostBt(java.lang.Long compensateCostBt)
{
this.compensateCostBt = compensateCostBt;
}
@Column(name = "COMPENSATE_COST_T", length = 22)
public java.lang.Long getCompensateCostT(){
return compensateCostT;
}
public void setCompensateCostT(java.lang.Long compensateCostT)
{
this.compensateCostT = compensateCostT;
}
@Column(name = "EQUIPMENT_SPENDING_F_CURRENCY", length = 22)
public java.lang.Long getEquipmentSpendingFCurrency(){
return equipmentSpendingFCurrency;
}
public void setEquipmentSpendingFCurrency(java.lang.Long equipmentSpendingFCurrency)
{
this.equipmentSpendingFCurrency = equipmentSpendingFCurrency;
}
@Column(name = "EQUIPMENT_SPENDING_TAX", length = 22)
public java.lang.Long getEquipmentSpendingTax(){
return equipmentSpendingTax;
}
public void setEquipmentSpendingTax(java.lang.Long equipmentSpendingTax)
{
this.equipmentSpendingTax = equipmentSpendingTax;
}
@Column(name = "ADVISORY_COST_BT", length = 22)
public java.lang.Long getAdvisoryCostBt(){
return advisoryCostBt;
}
public void setAdvisoryCostBt(java.lang.Long advisoryCostBt)
{
this.advisoryCostBt = advisoryCostBt;
}
@Column(name = "ADVISORY_COST_T", length = 22)
public java.lang.Long getAdvisoryCostT(){
return advisoryCostT;
}
public void setAdvisoryCostT(java.lang.Long advisoryCostT)
{
this.advisoryCostT = advisoryCostT;
}
@Column(name = "OTHER_SPENDING_F_CURRENCY", length = 22)
public java.lang.Long getOtherSpendingFCurrency(){
return otherSpendingFCurrency;
}
public void setOtherSpendingFCurrency(java.lang.Long otherSpendingFCurrency)
{
this.otherSpendingFCurrency = otherSpendingFCurrency;
}
@Column(name = "OTHER_SPENDING_TAX", length = 22)
public java.lang.Long getOtherSpendingTax(){
return otherSpendingTax;
}
public void setOtherSpendingTax(java.lang.Long otherSpendingTax)
{
this.otherSpendingTax = otherSpendingTax;
}
@Column(name = "TOTAL_SPENDING_TAX", length = 22)
public java.lang.Long getTotalSpendingTax(){
return totalSpendingTax;
}
public void setTotalSpendingTax(java.lang.Long totalSpendingTax)
{
this.totalSpendingTax = totalSpendingTax;
}
@Column(name = "TOTAL_SPENDING", length = 22)
public java.lang.Long getTotalSpending(){
return totalSpending;
}
public void setTotalSpending(java.lang.Long totalSpending)
{
this.totalSpending = totalSpending;
}
@Column(name = "CAPITAL", length = 22)
public java.lang.Long getCapital(){
return capital;
}
public void setCapital(java.lang.Long capital)
{
this.capital = capital;
}
@Column(name = "INVEST_QUALITY", length = 22)
public java.lang.Long getInvestQuality(){
return investQuality;
}
public void setInvestQuality(java.lang.Long investQuality)
{
this.investQuality = investQuality;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "CONSTRUCTION_LEVEL", length = 22)
public java.lang.Long getConstructionLevel(){
return constructionLevel;
}
public void setConstructionLevel(java.lang.Long constructionLevel)
{
this.constructionLevel = constructionLevel;
}
@Column(name = "ESTIMATE_START_DATE", length = 7)
public java.util.Date getEstimateStartDate(){
return estimateStartDate;
}
public void setEstimateStartDate(java.util.Date estimateStartDate)
{
this.estimateStartDate = estimateStartDate;
}
@Column(name = "ESTIMATE_END_DATE", length = 7)
public java.util.Date getEstimateEndDate(){
return estimateEndDate;
}
public void setEstimateEndDate(java.util.Date estimateEndDate)
{
this.estimateEndDate = estimateEndDate;
}
@Column(name = "PROJECT_SUBMIT_DATE", length = 7)
public java.util.Date getProjectSubmitDate(){
return projectSubmitDate;
}
public void setProjectSubmitDate(java.util.Date projectSubmitDate)
{
this.projectSubmitDate = projectSubmitDate;
}
@Column(name = "ASSIGN_EMPLOYEE_ID", length = 22)
public java.lang.Long getAssignEmployeeId(){
return assignEmployeeId;
}
public void setAssignEmployeeId(java.lang.Long assignEmployeeId)
{
this.assignEmployeeId = assignEmployeeId;
}
@Column(name = "REVIEW_EMPLOYEE_ID", length = 22)
public java.lang.Long getReviewEmployeeId(){
return reviewEmployeeId;
}
public void setReviewEmployeeId(java.lang.Long reviewEmployeeId)
{
this.reviewEmployeeId = reviewEmployeeId;
}
@Column(name = "REVIEW_CONTENT", length = 1000)
public java.lang.String getReviewContent(){
return reviewContent;
}
public void setReviewContent(java.lang.String reviewContent)
{
this.reviewContent = reviewContent;
}
@Column(name = "REVIEW_DEADLINE_DATE", length = 7)
public java.util.Date getReviewDeadlineDate(){
return reviewDeadlineDate;
}
public void setReviewDeadlineDate(java.util.Date reviewDeadlineDate)
{
this.reviewDeadlineDate = reviewDeadlineDate;
}
@Column(name = "REVIEW_FINISH_DATE", length = 7)
public java.util.Date getReviewFinishDate(){
return reviewFinishDate;
}
public void setReviewFinishDate(java.util.Date reviewFinishDate)
{
this.reviewFinishDate = reviewFinishDate;
}
@Column(name = "INVEST_DECISION_DATE", length = 7)
public java.util.Date getInvestDecisionDate(){
return investDecisionDate;
}
public void setInvestDecisionDate(java.util.Date investDecisionDate)
{
this.investDecisionDate = investDecisionDate;
}
@Column(name = "INVEST_SIGN_EMPLOYEE_ID", length = 22)
public java.lang.Long getInvestSignEmployeeId(){
return investSignEmployeeId;
}
public void setInvestSignEmployeeId(java.lang.Long investSignEmployeeId)
{
this.investSignEmployeeId = investSignEmployeeId;
}
@Column(name = "INVEST_FINISH_DATE", length = 7)
public java.util.Date getInvestFinishDate(){
return investFinishDate;
}
public void setInvestFinishDate(java.util.Date investFinishDate)
{
this.investFinishDate = investFinishDate;
}
@Column(name = "PROJECT_FINISH_DATE", length = 7)
public java.util.Date getProjectFinishDate(){
return projectFinishDate;
}
public void setProjectFinishDate(java.util.Date projectFinishDate)
{
this.projectFinishDate = projectFinishDate;
}
@Column(name = "SETTLEMENT_DOCUMENT_NO", length = 300)
public java.lang.String getSettlementDocumentNo(){
return settlementDocumentNo;
}
public void setSettlementDocumentNo(java.lang.String settlementDocumentNo)
{
this.settlementDocumentNo = settlementDocumentNo;
}
@Column(name = "SETTLEMENT_DATE", length = 7)
public java.util.Date getSettlementDate(){
return settlementDate;
}
public void setSettlementDate(java.util.Date settlementDate)
{
this.settlementDate = settlementDate;
}
@Column(name = "SETTLEMENT_SIGN_EMPLOYEE_ID", length = 22)
public java.lang.Long getSettlementSignEmployeeId(){
return settlementSignEmployeeId;
}
public void setSettlementSignEmployeeId(java.lang.Long settlementSignEmployeeId)
{
this.settlementSignEmployeeId = settlementSignEmployeeId;
}
@Column(name = "CONSTRUCTION_SPENDING_T", length = 22)
public java.lang.Long getConstructionSpendingT(){
return constructionSpendingT;
}
public void setConstructionSpendingT(java.lang.Long constructionSpendingT)
{
this.constructionSpendingT = constructionSpendingT;
}
@Column(name = "MANAGE_PROJECT_SPENDING_T", length = 22)
public java.lang.Long getManageProjectSpendingT(){
return manageProjectSpendingT;
}
public void setManageProjectSpendingT(java.lang.Long manageProjectSpendingT)
{
this.manageProjectSpendingT = manageProjectSpendingT;
}
@Column(name = "SPARE_SPENDING_T", length = 22)
public java.lang.Long getSpareSpendingT(){
return spareSpendingT;
}
public void setSpareSpendingT(java.lang.Long spareSpendingT)
{
this.spareSpendingT = spareSpendingT;
}
@Column(name = "EQUIPMENT_SPENDING_F_TAX", length = 22)
public java.lang.Long getEquipmentSpendingFTax(){
return equipmentSpendingFTax;
}
public void setEquipmentSpendingFTax(java.lang.Long equipmentSpendingFTax)
{
this.equipmentSpendingFTax = equipmentSpendingFTax;
}
   

    @Override
    public IProjInvestProjectDTO toDTO() {
        IProjInvestProjectDTO iProjInvestProjectDTO = new IProjInvestProjectDTO();
        //set cac gia tri 
        iProjInvestProjectDTO.setOtherSpendingFCurrencyT(this.otherSpendingFCurrencyT);
        iProjInvestProjectDTO.setTransRate(this.transRate);
        iProjInvestProjectDTO.setTotalSpendingBt(this.totalSpendingBt);
        iProjInvestProjectDTO.setTotalSpendingBtF(this.totalSpendingBtF);
        iProjInvestProjectDTO.setCapitalDescription(this.capitalDescription);
        iProjInvestProjectDTO.setConstructionType(this.constructionType);
        iProjInvestProjectDTO.setHoldPartnerId(this.holdPartnerId);
        iProjInvestProjectDTO.setDiscountRate(this.discountRate);
        iProjInvestProjectDTO.setReturnDay(this.returnDay);
        iProjInvestProjectDTO.setInternalRateOfReturn(this.internalRateOfReturn);
        iProjInvestProjectDTO.setNetPresentValue(this.netPresentValue);
        iProjInvestProjectDTO.setInvestType(this.investType);
        iProjInvestProjectDTO.setVerifyComment(this.verifyComment);
        iProjInvestProjectDTO.setOtherEffect(this.otherEffect);
        iProjInvestProjectDTO.setServiceType(this.serviceType);
        iProjInvestProjectDTO.setLawProfileDate(this.lawProfileDate);
        iProjInvestProjectDTO.setProjectType(this.projectType);
        iProjInvestProjectDTO.setResearchManager(this.researchManager);
        iProjInvestProjectDTO.setResearchArea(this.researchArea);
        iProjInvestProjectDTO.setResearchType(this.researchType);
        iProjInvestProjectDTO.setSettlementGroupId(this.settlementGroupId);
        iProjInvestProjectDTO.setApprovalGroupId(this.approvalGroupId);
        iProjInvestProjectDTO.setRoyaltyCost(this.royaltyCost);
        iProjInvestProjectDTO.setLaborCost(this.laborCost);
        iProjInvestProjectDTO.setMaterialCost(this.materialCost);
        iProjInvestProjectDTO.setInstrumentFixAssetCost(this.instrumentFixAssetCost);
        iProjInvestProjectDTO.setOutHireCost(this.outHireCost);
        iProjInvestProjectDTO.setTestCost(this.testCost);
        iProjInvestProjectDTO.setOtherCost(this.otherCost);
        iProjInvestProjectDTO.setInsureCost(this.insureCost);
        iProjInvestProjectDTO.setStatus(this.status);
        iProjInvestProjectDTO.setProjInvestProjectId(this.projInvestProjectId);
        iProjInvestProjectDTO.setName(this.name);
        iProjInvestProjectDTO.setCode(this.code);
        iProjInvestProjectDTO.setInvestmentOwner(this.investmentOwner);
        iProjInvestProjectDTO.setEquipmentSpending(this.equipmentSpending);
        iProjInvestProjectDTO.setConstructionSpending(this.constructionSpending);
        iProjInvestProjectDTO.setOtherSpending(this.otherSpending);
        iProjInvestProjectDTO.setSpareSpending(this.spareSpending);
        iProjInvestProjectDTO.setCreatorId(this.creatorId);
        iProjInvestProjectDTO.setCreatorGroupId(this.creatorGroupId);
        iProjInvestProjectDTO.setStatusCode(this.statusCode);
        iProjInvestProjectDTO.setApprovalContent(this.approvalContent);
        iProjInvestProjectDTO.setAppraisalContent(this.appraisalContent);
        iProjInvestProjectDTO.setCurrencyId(this.currencyId);
        iProjInvestProjectDTO.setBeginDate(this.beginDate);
        iProjInvestProjectDTO.setEndDate(this.endDate);
        iProjInvestProjectDTO.setCloseComment(this.closeComment);
        iProjInvestProjectDTO.setCloseReasonId(this.closeReasonId);
        iProjInvestProjectDTO.setDeploymentGroupId(this.deploymentGroupId);
        iProjInvestProjectDTO.setCatServiceId(this.catServiceId);
        iProjInvestProjectDTO.setCatInvestProjectType(this.catInvestProjectType);
        iProjInvestProjectDTO.setDeploymentLocation(this.deploymentLocation);
        iProjInvestProjectDTO.setSetUpGroupId(this.setUpGroupId);
        iProjInvestProjectDTO.setManageProjectSpending(this.manageProjectSpending);
        iProjInvestProjectDTO.setCreatedDate(this.createdDate);
        iProjInvestProjectDTO.setCreateType(this.createType);
        iProjInvestProjectDTO.setOriginalId(this.originalId);
        iProjInvestProjectDTO.setReason(this.reason);
        iProjInvestProjectDTO.setAbleToPlan(this.ableToPlan);
        iProjInvestProjectDTO.setAdjustContent(this.adjustContent);
        iProjInvestProjectDTO.setParentId(this.parentId);
        iProjInvestProjectDTO.setAppraisalDate(this.appraisalDate);
        iProjInvestProjectDTO.setApprovalDate(this.approvalDate);
        iProjInvestProjectDTO.setAppraisalCode(this.appraisalCode);
        iProjInvestProjectDTO.setAppraisalSigner(this.appraisalSigner);
        iProjInvestProjectDTO.setApprovalCode(this.approvalCode);
        iProjInvestProjectDTO.setApprovalSigner(this.approvalSigner);
        iProjInvestProjectDTO.setReqApprovalSigner(this.reqApprovalSigner);
        iProjInvestProjectDTO.setReqApprovalDate(this.reqApprovalDate);
        iProjInvestProjectDTO.setReqApprovalCode(this.reqApprovalCode);
        iProjInvestProjectDTO.setHoldGroupId(this.holdGroupId);
        iProjInvestProjectDTO.setDecisionGroupId(this.decisionGroupId);
        iProjInvestProjectDTO.setInvestGoal(this.investGoal);
        iProjInvestProjectDTO.setInvestLocation(this.investLocation);
        iProjInvestProjectDTO.setUseGroupId(this.useGroupId);
        iProjInvestProjectDTO.setHeadCreateEmployeeId(this.headCreateEmployeeId);
        iProjInvestProjectDTO.setCompensateCostBt(this.compensateCostBt);
        iProjInvestProjectDTO.setCompensateCostT(this.compensateCostT);
        iProjInvestProjectDTO.setEquipmentSpendingFCurrency(this.equipmentSpendingFCurrency);
        iProjInvestProjectDTO.setEquipmentSpendingTax(this.equipmentSpendingTax);
        iProjInvestProjectDTO.setAdvisoryCostBt(this.advisoryCostBt);
        iProjInvestProjectDTO.setAdvisoryCostT(this.advisoryCostT);
        iProjInvestProjectDTO.setOtherSpendingFCurrency(this.otherSpendingFCurrency);
        iProjInvestProjectDTO.setOtherSpendingTax(this.otherSpendingTax);
        iProjInvestProjectDTO.setTotalSpendingTax(this.totalSpendingTax);
        iProjInvestProjectDTO.setTotalSpending(this.totalSpending);
        iProjInvestProjectDTO.setCapital(this.capital);
        iProjInvestProjectDTO.setInvestQuality(this.investQuality);
        iProjInvestProjectDTO.setType(this.type);
        iProjInvestProjectDTO.setConstructionLevel(this.constructionLevel);
        iProjInvestProjectDTO.setEstimateStartDate(this.estimateStartDate);
        iProjInvestProjectDTO.setEstimateEndDate(this.estimateEndDate);
        iProjInvestProjectDTO.setProjectSubmitDate(this.projectSubmitDate);
        iProjInvestProjectDTO.setAssignEmployeeId(this.assignEmployeeId);
        iProjInvestProjectDTO.setReviewEmployeeId(this.reviewEmployeeId);
        iProjInvestProjectDTO.setReviewContent(this.reviewContent);
        iProjInvestProjectDTO.setReviewDeadlineDate(this.reviewDeadlineDate);
        iProjInvestProjectDTO.setReviewFinishDate(this.reviewFinishDate);
        iProjInvestProjectDTO.setInvestDecisionDate(this.investDecisionDate);
        iProjInvestProjectDTO.setInvestSignEmployeeId(this.investSignEmployeeId);
        iProjInvestProjectDTO.setInvestFinishDate(this.investFinishDate);
        iProjInvestProjectDTO.setProjectFinishDate(this.projectFinishDate);
        iProjInvestProjectDTO.setSettlementDocumentNo(this.settlementDocumentNo);
        iProjInvestProjectDTO.setSettlementDate(this.settlementDate);
        iProjInvestProjectDTO.setSettlementSignEmployeeId(this.settlementSignEmployeeId);
        iProjInvestProjectDTO.setConstructionSpendingT(this.constructionSpendingT);
        iProjInvestProjectDTO.setManageProjectSpendingT(this.manageProjectSpendingT);
        iProjInvestProjectDTO.setSpareSpendingT(this.spareSpendingT);
        iProjInvestProjectDTO.setEquipmentSpendingFTax(this.equipmentSpendingFTax);
        return iProjInvestProjectDTO;
    }
}
