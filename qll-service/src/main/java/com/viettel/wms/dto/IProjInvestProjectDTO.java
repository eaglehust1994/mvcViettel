/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.IProjInvestProjectBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "I_PROJ_INVEST_PROJECTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IProjInvestProjectDTO extends wmsBaseDTO<IProjInvestProjectBO> {

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
private java.lang.String projectCode;
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

private java.lang.String value;

    public java.lang.String getProjectCode() {
	return projectCode;
}

public void setProjectCode(java.lang.String projectCode) {
	this.projectCode = projectCode;
}

	@Override
    public IProjInvestProjectBO toModel() {
        IProjInvestProjectBO iProjInvestProjectBO = new IProjInvestProjectBO();
        iProjInvestProjectBO.setOtherSpendingFCurrencyT(this.otherSpendingFCurrencyT);
        iProjInvestProjectBO.setTransRate(this.transRate);
        iProjInvestProjectBO.setTotalSpendingBt(this.totalSpendingBt);
        iProjInvestProjectBO.setTotalSpendingBtF(this.totalSpendingBtF);
        iProjInvestProjectBO.setCapitalDescription(this.capitalDescription);
        iProjInvestProjectBO.setConstructionType(this.constructionType);
        iProjInvestProjectBO.setHoldPartnerId(this.holdPartnerId);
        iProjInvestProjectBO.setDiscountRate(this.discountRate);
        iProjInvestProjectBO.setReturnDay(this.returnDay);
        iProjInvestProjectBO.setInternalRateOfReturn(this.internalRateOfReturn);
        iProjInvestProjectBO.setNetPresentValue(this.netPresentValue);
        iProjInvestProjectBO.setInvestType(this.investType);
        iProjInvestProjectBO.setVerifyComment(this.verifyComment);
        iProjInvestProjectBO.setOtherEffect(this.otherEffect);
        iProjInvestProjectBO.setServiceType(this.serviceType);
        iProjInvestProjectBO.setLawProfileDate(this.lawProfileDate);
        iProjInvestProjectBO.setProjectType(this.projectType);
        iProjInvestProjectBO.setResearchManager(this.researchManager);
        iProjInvestProjectBO.setResearchArea(this.researchArea);
        iProjInvestProjectBO.setResearchType(this.researchType);
        iProjInvestProjectBO.setSettlementGroupId(this.settlementGroupId);
        iProjInvestProjectBO.setApprovalGroupId(this.approvalGroupId);
        iProjInvestProjectBO.setRoyaltyCost(this.royaltyCost);
        iProjInvestProjectBO.setLaborCost(this.laborCost);
        iProjInvestProjectBO.setMaterialCost(this.materialCost);
        iProjInvestProjectBO.setInstrumentFixAssetCost(this.instrumentFixAssetCost);
        iProjInvestProjectBO.setOutHireCost(this.outHireCost);
        iProjInvestProjectBO.setTestCost(this.testCost);
        iProjInvestProjectBO.setOtherCost(this.otherCost);
        iProjInvestProjectBO.setInsureCost(this.insureCost);
        iProjInvestProjectBO.setStatus(this.status);
        iProjInvestProjectBO.setProjInvestProjectId(this.projInvestProjectId);
        iProjInvestProjectBO.setName(this.name);
        iProjInvestProjectBO.setCode(this.code);
        iProjInvestProjectBO.setInvestmentOwner(this.investmentOwner);
        iProjInvestProjectBO.setEquipmentSpending(this.equipmentSpending);
        iProjInvestProjectBO.setConstructionSpending(this.constructionSpending);
        iProjInvestProjectBO.setOtherSpending(this.otherSpending);
        iProjInvestProjectBO.setSpareSpending(this.spareSpending);
        iProjInvestProjectBO.setCreatorId(this.creatorId);
        iProjInvestProjectBO.setCreatorGroupId(this.creatorGroupId);
        iProjInvestProjectBO.setStatusCode(this.statusCode);
        iProjInvestProjectBO.setApprovalContent(this.approvalContent);
        iProjInvestProjectBO.setAppraisalContent(this.appraisalContent);
        iProjInvestProjectBO.setCurrencyId(this.currencyId);
        iProjInvestProjectBO.setBeginDate(this.beginDate);
        iProjInvestProjectBO.setEndDate(this.endDate);
        iProjInvestProjectBO.setCloseComment(this.closeComment);
        iProjInvestProjectBO.setCloseReasonId(this.closeReasonId);
        iProjInvestProjectBO.setDeploymentGroupId(this.deploymentGroupId);
        iProjInvestProjectBO.setCatServiceId(this.catServiceId);
        iProjInvestProjectBO.setCatInvestProjectType(this.catInvestProjectType);
        iProjInvestProjectBO.setDeploymentLocation(this.deploymentLocation);
        iProjInvestProjectBO.setSetUpGroupId(this.setUpGroupId);
        iProjInvestProjectBO.setManageProjectSpending(this.manageProjectSpending);
        iProjInvestProjectBO.setCreatedDate(this.createdDate);
        iProjInvestProjectBO.setCreateType(this.createType);
        iProjInvestProjectBO.setOriginalId(this.originalId);
        iProjInvestProjectBO.setReason(this.reason);
        iProjInvestProjectBO.setAbleToPlan(this.ableToPlan);
        iProjInvestProjectBO.setAdjustContent(this.adjustContent);
        iProjInvestProjectBO.setParentId(this.parentId);
        iProjInvestProjectBO.setAppraisalDate(this.appraisalDate);
        iProjInvestProjectBO.setApprovalDate(this.approvalDate);
        iProjInvestProjectBO.setAppraisalCode(this.appraisalCode);
        iProjInvestProjectBO.setAppraisalSigner(this.appraisalSigner);
        iProjInvestProjectBO.setApprovalCode(this.approvalCode);
        iProjInvestProjectBO.setApprovalSigner(this.approvalSigner);
        iProjInvestProjectBO.setReqApprovalSigner(this.reqApprovalSigner);
        iProjInvestProjectBO.setReqApprovalDate(this.reqApprovalDate);
        iProjInvestProjectBO.setReqApprovalCode(this.reqApprovalCode);
        iProjInvestProjectBO.setHoldGroupId(this.holdGroupId);
        iProjInvestProjectBO.setDecisionGroupId(this.decisionGroupId);
        iProjInvestProjectBO.setInvestGoal(this.investGoal);
        iProjInvestProjectBO.setInvestLocation(this.investLocation);
        iProjInvestProjectBO.setUseGroupId(this.useGroupId);
        iProjInvestProjectBO.setHeadCreateEmployeeId(this.headCreateEmployeeId);
        iProjInvestProjectBO.setCompensateCostBt(this.compensateCostBt);
        iProjInvestProjectBO.setCompensateCostT(this.compensateCostT);
        iProjInvestProjectBO.setEquipmentSpendingFCurrency(this.equipmentSpendingFCurrency);
        iProjInvestProjectBO.setEquipmentSpendingTax(this.equipmentSpendingTax);
        iProjInvestProjectBO.setAdvisoryCostBt(this.advisoryCostBt);
        iProjInvestProjectBO.setAdvisoryCostT(this.advisoryCostT);
        iProjInvestProjectBO.setOtherSpendingFCurrency(this.otherSpendingFCurrency);
        iProjInvestProjectBO.setOtherSpendingTax(this.otherSpendingTax);
        iProjInvestProjectBO.setTotalSpendingTax(this.totalSpendingTax);
        iProjInvestProjectBO.setTotalSpending(this.totalSpending);
        iProjInvestProjectBO.setCapital(this.capital);
        iProjInvestProjectBO.setInvestQuality(this.investQuality);
        iProjInvestProjectBO.setType(this.type);
        iProjInvestProjectBO.setConstructionLevel(this.constructionLevel);
        iProjInvestProjectBO.setEstimateStartDate(this.estimateStartDate);
        iProjInvestProjectBO.setEstimateEndDate(this.estimateEndDate);
        iProjInvestProjectBO.setProjectSubmitDate(this.projectSubmitDate);
        iProjInvestProjectBO.setAssignEmployeeId(this.assignEmployeeId);
        iProjInvestProjectBO.setReviewEmployeeId(this.reviewEmployeeId);
        iProjInvestProjectBO.setReviewContent(this.reviewContent);
        iProjInvestProjectBO.setReviewDeadlineDate(this.reviewDeadlineDate);
        iProjInvestProjectBO.setReviewFinishDate(this.reviewFinishDate);
        iProjInvestProjectBO.setInvestDecisionDate(this.investDecisionDate);
        iProjInvestProjectBO.setInvestSignEmployeeId(this.investSignEmployeeId);
        iProjInvestProjectBO.setInvestFinishDate(this.investFinishDate);
        iProjInvestProjectBO.setProjectFinishDate(this.projectFinishDate);
        iProjInvestProjectBO.setSettlementDocumentNo(this.settlementDocumentNo);
        iProjInvestProjectBO.setSettlementDate(this.settlementDate);
        iProjInvestProjectBO.setSettlementSignEmployeeId(this.settlementSignEmployeeId);
        iProjInvestProjectBO.setConstructionSpendingT(this.constructionSpendingT);
        iProjInvestProjectBO.setManageProjectSpendingT(this.manageProjectSpendingT);
        iProjInvestProjectBO.setSpareSpendingT(this.spareSpendingT);
        iProjInvestProjectBO.setEquipmentSpendingFTax(this.equipmentSpendingFTax);
        return iProjInvestProjectBO;
    }

    public java.lang.Long getOtherSpendingFCurrencyT(){
    return otherSpendingFCurrencyT;
    }
    public void setOtherSpendingFCurrencyT(java.lang.Long otherSpendingFCurrencyT)
    {
    this.otherSpendingFCurrencyT = otherSpendingFCurrencyT;
    }
    
    public java.lang.Long getTransRate(){
    return transRate;
    }
    public void setTransRate(java.lang.Long transRate)
    {
    this.transRate = transRate;
    }
    
    public java.lang.Long getTotalSpendingBt(){
    return totalSpendingBt;
    }
    public void setTotalSpendingBt(java.lang.Long totalSpendingBt)
    {
    this.totalSpendingBt = totalSpendingBt;
    }
    
    public java.lang.Long getTotalSpendingBtF(){
    return totalSpendingBtF;
    }
    public void setTotalSpendingBtF(java.lang.Long totalSpendingBtF)
    {
    this.totalSpendingBtF = totalSpendingBtF;
    }
    
    public java.lang.String getCapitalDescription(){
    return capitalDescription;
    }
    public void setCapitalDescription(java.lang.String capitalDescription)
    {
    this.capitalDescription = capitalDescription;
    }
    
    public java.lang.Long getConstructionType(){
    return constructionType;
    }
    public void setConstructionType(java.lang.Long constructionType)
    {
    this.constructionType = constructionType;
    }
    
    public java.lang.Long getHoldPartnerId(){
    return holdPartnerId;
    }
    public void setHoldPartnerId(java.lang.Long holdPartnerId)
    {
    this.holdPartnerId = holdPartnerId;
    }
    
    public java.lang.Double getDiscountRate(){
    return discountRate;
    }
    public void setDiscountRate(java.lang.Double discountRate)
    {
    this.discountRate = discountRate;
    }
    
    public java.lang.Long getReturnDay(){
    return returnDay;
    }
    public void setReturnDay(java.lang.Long returnDay)
    {
    this.returnDay = returnDay;
    }
    
    public java.lang.Double getInternalRateOfReturn(){
    return internalRateOfReturn;
    }
    public void setInternalRateOfReturn(java.lang.Double internalRateOfReturn)
    {
    this.internalRateOfReturn = internalRateOfReturn;
    }
    
    public java.lang.Double getNetPresentValue(){
    return netPresentValue;
    }
    public void setNetPresentValue(java.lang.Double netPresentValue)
    {
    this.netPresentValue = netPresentValue;
    }
    
    public java.lang.Long getInvestType(){
    return investType;
    }
    public void setInvestType(java.lang.Long investType)
    {
    this.investType = investType;
    }
    
    public java.lang.String getVerifyComment(){
    return verifyComment;
    }
    public void setVerifyComment(java.lang.String verifyComment)
    {
    this.verifyComment = verifyComment;
    }
    
    public java.lang.String getOtherEffect(){
    return otherEffect;
    }
    public void setOtherEffect(java.lang.String otherEffect)
    {
    this.otherEffect = otherEffect;
    }
    
    public java.lang.String getServiceType(){
    return serviceType;
    }
    public void setServiceType(java.lang.String serviceType)
    {
    this.serviceType = serviceType;
    }
    
    public java.util.Date getLawProfileDate(){
    return lawProfileDate;
    }
    public void setLawProfileDate(java.util.Date lawProfileDate)
    {
    this.lawProfileDate = lawProfileDate;
    }
    
    public java.lang.Long getProjectType(){
    return projectType;
    }
    public void setProjectType(java.lang.Long projectType)
    {
    this.projectType = projectType;
    }
    
    public java.lang.String getResearchManager(){
    return researchManager;
    }
    public void setResearchManager(java.lang.String researchManager)
    {
    this.researchManager = researchManager;
    }
    
    public java.lang.String getResearchArea(){
    return researchArea;
    }
    public void setResearchArea(java.lang.String researchArea)
    {
    this.researchArea = researchArea;
    }
    
    public java.lang.String getResearchType(){
    return researchType;
    }
    public void setResearchType(java.lang.String researchType)
    {
    this.researchType = researchType;
    }
    
    public java.lang.Long getSettlementGroupId(){
    return settlementGroupId;
    }
    public void setSettlementGroupId(java.lang.Long settlementGroupId)
    {
    this.settlementGroupId = settlementGroupId;
    }
    
    public java.lang.Long getApprovalGroupId(){
    return approvalGroupId;
    }
    public void setApprovalGroupId(java.lang.Long approvalGroupId)
    {
    this.approvalGroupId = approvalGroupId;
    }
    
    public java.lang.Double getRoyaltyCost(){
    return royaltyCost;
    }
    public void setRoyaltyCost(java.lang.Double royaltyCost)
    {
    this.royaltyCost = royaltyCost;
    }
    
    public java.lang.Double getLaborCost(){
    return laborCost;
    }
    public void setLaborCost(java.lang.Double laborCost)
    {
    this.laborCost = laborCost;
    }
    
    public java.lang.Double getMaterialCost(){
    return materialCost;
    }
    public void setMaterialCost(java.lang.Double materialCost)
    {
    this.materialCost = materialCost;
    }
    
    public java.lang.Double getInstrumentFixAssetCost(){
    return instrumentFixAssetCost;
    }
    public void setInstrumentFixAssetCost(java.lang.Double instrumentFixAssetCost)
    {
    this.instrumentFixAssetCost = instrumentFixAssetCost;
    }
    
    public java.lang.Double getOutHireCost(){
    return outHireCost;
    }
    public void setOutHireCost(java.lang.Double outHireCost)
    {
    this.outHireCost = outHireCost;
    }
    
    public java.lang.Double getTestCost(){
    return testCost;
    }
    public void setTestCost(java.lang.Double testCost)
    {
    this.testCost = testCost;
    }
    
    public java.lang.Double getOtherCost(){
    return otherCost;
    }
    public void setOtherCost(java.lang.Double otherCost)
    {
    this.otherCost = otherCost;
    }
    
    public java.lang.Double getInsureCost(){
    return insureCost;
    }
    public void setInsureCost(java.lang.Double insureCost)
    {
    this.insureCost = insureCost;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
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
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getInvestmentOwner(){
    return investmentOwner;
    }
    public void setInvestmentOwner(java.lang.String investmentOwner)
    {
    this.investmentOwner = investmentOwner;
    }
    
    public java.lang.Double getEquipmentSpending(){
    return equipmentSpending;
    }
    public void setEquipmentSpending(java.lang.Double equipmentSpending)
    {
    this.equipmentSpending = equipmentSpending;
    }
    
    public java.lang.Double getConstructionSpending(){
    return constructionSpending;
    }
    public void setConstructionSpending(java.lang.Double constructionSpending)
    {
    this.constructionSpending = constructionSpending;
    }
    
    public java.lang.Double getOtherSpending(){
    return otherSpending;
    }
    public void setOtherSpending(java.lang.Double otherSpending)
    {
    this.otherSpending = otherSpending;
    }
    
    public java.lang.Double getSpareSpending(){
    return spareSpending;
    }
    public void setSpareSpending(java.lang.Double spareSpending)
    {
    this.spareSpending = spareSpending;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.lang.Long getCreatorGroupId(){
    return creatorGroupId;
    }
    public void setCreatorGroupId(java.lang.Long creatorGroupId)
    {
    this.creatorGroupId = creatorGroupId;
    }
    
    public java.lang.Long getStatusCode(){
    return statusCode;
    }
    public void setStatusCode(java.lang.Long statusCode)
    {
    this.statusCode = statusCode;
    }
    
    public java.lang.String getApprovalContent(){
    return approvalContent;
    }
    public void setApprovalContent(java.lang.String approvalContent)
    {
    this.approvalContent = approvalContent;
    }
    
    public java.lang.String getAppraisalContent(){
    return appraisalContent;
    }
    public void setAppraisalContent(java.lang.String appraisalContent)
    {
    this.appraisalContent = appraisalContent;
    }
    
    public java.lang.Long getCurrencyId(){
    return currencyId;
    }
    public void setCurrencyId(java.lang.Long currencyId)
    {
    this.currencyId = currencyId;
    }
    
    public java.util.Date getBeginDate(){
    return beginDate;
    }
    public void setBeginDate(java.util.Date beginDate)
    {
    this.beginDate = beginDate;
    }
    
    public java.util.Date getEndDate(){
    return endDate;
    }
    public void setEndDate(java.util.Date endDate)
    {
    this.endDate = endDate;
    }
    
    public java.lang.String getCloseComment(){
    return closeComment;
    }
    public void setCloseComment(java.lang.String closeComment)
    {
    this.closeComment = closeComment;
    }
    
    public java.lang.Long getCloseReasonId(){
    return closeReasonId;
    }
    public void setCloseReasonId(java.lang.Long closeReasonId)
    {
    this.closeReasonId = closeReasonId;
    }
    
    public java.lang.Long getDeploymentGroupId(){
    return deploymentGroupId;
    }
    public void setDeploymentGroupId(java.lang.Long deploymentGroupId)
    {
    this.deploymentGroupId = deploymentGroupId;
    }
    
    public java.lang.Long getCatServiceId(){
    return catServiceId;
    }
    public void setCatServiceId(java.lang.Long catServiceId)
    {
    this.catServiceId = catServiceId;
    }
    
    public java.lang.Long getCatInvestProjectType(){
    return catInvestProjectType;
    }
    public void setCatInvestProjectType(java.lang.Long catInvestProjectType)
    {
    this.catInvestProjectType = catInvestProjectType;
    }
    
    public java.lang.String getDeploymentLocation(){
    return deploymentLocation;
    }
    public void setDeploymentLocation(java.lang.String deploymentLocation)
    {
    this.deploymentLocation = deploymentLocation;
    }
    
    public java.lang.Long getSetUpGroupId(){
    return setUpGroupId;
    }
    public void setSetUpGroupId(java.lang.Long setUpGroupId)
    {
    this.setUpGroupId = setUpGroupId;
    }
    
    public java.lang.Double getManageProjectSpending(){
    return manageProjectSpending;
    }
    public void setManageProjectSpending(java.lang.Double manageProjectSpending)
    {
    this.manageProjectSpending = manageProjectSpending;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreateType(){
    return createType;
    }
    public void setCreateType(java.lang.Long createType)
    {
    this.createType = createType;
    }
    
    public java.lang.Long getOriginalId(){
    return originalId;
    }
    public void setOriginalId(java.lang.Long originalId)
    {
    this.originalId = originalId;
    }
    
    public java.lang.String getReason(){
    return reason;
    }
    public void setReason(java.lang.String reason)
    {
    this.reason = reason;
    }
    
    public java.lang.Long getAbleToPlan(){
    return ableToPlan;
    }
    public void setAbleToPlan(java.lang.Long ableToPlan)
    {
    this.ableToPlan = ableToPlan;
    }
    
    public java.lang.String getAdjustContent(){
    return adjustContent;
    }
    public void setAdjustContent(java.lang.String adjustContent)
    {
    this.adjustContent = adjustContent;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.util.Date getAppraisalDate(){
    return appraisalDate;
    }
    public void setAppraisalDate(java.util.Date appraisalDate)
    {
    this.appraisalDate = appraisalDate;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.String getAppraisalCode(){
    return appraisalCode;
    }
    public void setAppraisalCode(java.lang.String appraisalCode)
    {
    this.appraisalCode = appraisalCode;
    }
    
    public java.lang.String getAppraisalSigner(){
    return appraisalSigner;
    }
    public void setAppraisalSigner(java.lang.String appraisalSigner)
    {
    this.appraisalSigner = appraisalSigner;
    }
    
    public java.lang.String getApprovalCode(){
    return approvalCode;
    }
    public void setApprovalCode(java.lang.String approvalCode)
    {
    this.approvalCode = approvalCode;
    }
    
    public java.lang.String getApprovalSigner(){
    return approvalSigner;
    }
    public void setApprovalSigner(java.lang.String approvalSigner)
    {
    this.approvalSigner = approvalSigner;
    }
    
    public java.lang.String getReqApprovalSigner(){
    return reqApprovalSigner;
    }
    public void setReqApprovalSigner(java.lang.String reqApprovalSigner)
    {
    this.reqApprovalSigner = reqApprovalSigner;
    }
    
    public java.util.Date getReqApprovalDate(){
    return reqApprovalDate;
    }
    public void setReqApprovalDate(java.util.Date reqApprovalDate)
    {
    this.reqApprovalDate = reqApprovalDate;
    }
    
    public java.lang.String getReqApprovalCode(){
    return reqApprovalCode;
    }
    public void setReqApprovalCode(java.lang.String reqApprovalCode)
    {
    this.reqApprovalCode = reqApprovalCode;
    }
    
    public java.lang.Long getHoldGroupId(){
    return holdGroupId;
    }
    public void setHoldGroupId(java.lang.Long holdGroupId)
    {
    this.holdGroupId = holdGroupId;
    }
    
    public java.lang.Long getDecisionGroupId(){
    return decisionGroupId;
    }
    public void setDecisionGroupId(java.lang.Long decisionGroupId)
    {
    this.decisionGroupId = decisionGroupId;
    }
    
    public java.lang.String getInvestGoal(){
    return investGoal;
    }
    public void setInvestGoal(java.lang.String investGoal)
    {
    this.investGoal = investGoal;
    }
    
    public java.lang.String getInvestLocation(){
    return investLocation;
    }
    public void setInvestLocation(java.lang.String investLocation)
    {
    this.investLocation = investLocation;
    }
    
    public java.lang.Long getUseGroupId(){
    return useGroupId;
    }
    public void setUseGroupId(java.lang.Long useGroupId)
    {
    this.useGroupId = useGroupId;
    }
    
    public java.lang.Long getHeadCreateEmployeeId(){
    return headCreateEmployeeId;
    }
    public void setHeadCreateEmployeeId(java.lang.Long headCreateEmployeeId)
    {
    this.headCreateEmployeeId = headCreateEmployeeId;
    }
    
    public java.lang.Long getCompensateCostBt(){
    return compensateCostBt;
    }
    public void setCompensateCostBt(java.lang.Long compensateCostBt)
    {
    this.compensateCostBt = compensateCostBt;
    }
    
    public java.lang.Long getCompensateCostT(){
    return compensateCostT;
    }
    public void setCompensateCostT(java.lang.Long compensateCostT)
    {
    this.compensateCostT = compensateCostT;
    }
    
    public java.lang.Long getEquipmentSpendingFCurrency(){
    return equipmentSpendingFCurrency;
    }
    public void setEquipmentSpendingFCurrency(java.lang.Long equipmentSpendingFCurrency)
    {
    this.equipmentSpendingFCurrency = equipmentSpendingFCurrency;
    }
    
    public java.lang.Long getEquipmentSpendingTax(){
    return equipmentSpendingTax;
    }
    public void setEquipmentSpendingTax(java.lang.Long equipmentSpendingTax)
    {
    this.equipmentSpendingTax = equipmentSpendingTax;
    }
    
    public java.lang.Long getAdvisoryCostBt(){
    return advisoryCostBt;
    }
    public void setAdvisoryCostBt(java.lang.Long advisoryCostBt)
    {
    this.advisoryCostBt = advisoryCostBt;
    }
    
    public java.lang.Long getAdvisoryCostT(){
    return advisoryCostT;
    }
    public void setAdvisoryCostT(java.lang.Long advisoryCostT)
    {
    this.advisoryCostT = advisoryCostT;
    }
    
    public java.lang.Long getOtherSpendingFCurrency(){
    return otherSpendingFCurrency;
    }
    public void setOtherSpendingFCurrency(java.lang.Long otherSpendingFCurrency)
    {
    this.otherSpendingFCurrency = otherSpendingFCurrency;
    }
    
    public java.lang.Long getOtherSpendingTax(){
    return otherSpendingTax;
    }
    public void setOtherSpendingTax(java.lang.Long otherSpendingTax)
    {
    this.otherSpendingTax = otherSpendingTax;
    }
    
    public java.lang.Long getTotalSpendingTax(){
    return totalSpendingTax;
    }
    public void setTotalSpendingTax(java.lang.Long totalSpendingTax)
    {
    this.totalSpendingTax = totalSpendingTax;
    }
    
    public java.lang.Long getTotalSpending(){
    return totalSpending;
    }
    public void setTotalSpending(java.lang.Long totalSpending)
    {
    this.totalSpending = totalSpending;
    }
    
    public java.lang.Long getCapital(){
    return capital;
    }
    public void setCapital(java.lang.Long capital)
    {
    this.capital = capital;
    }
    
    public java.lang.Long getInvestQuality(){
    return investQuality;
    }
    public void setInvestQuality(java.lang.Long investQuality)
    {
    this.investQuality = investQuality;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.Long getConstructionLevel(){
    return constructionLevel;
    }
    public void setConstructionLevel(java.lang.Long constructionLevel)
    {
    this.constructionLevel = constructionLevel;
    }
    
    public java.util.Date getEstimateStartDate(){
    return estimateStartDate;
    }
    public void setEstimateStartDate(java.util.Date estimateStartDate)
    {
    this.estimateStartDate = estimateStartDate;
    }
    
    public java.util.Date getEstimateEndDate(){
    return estimateEndDate;
    }
    public void setEstimateEndDate(java.util.Date estimateEndDate)
    {
    this.estimateEndDate = estimateEndDate;
    }
    
    public java.util.Date getProjectSubmitDate(){
    return projectSubmitDate;
    }
    public void setProjectSubmitDate(java.util.Date projectSubmitDate)
    {
    this.projectSubmitDate = projectSubmitDate;
    }
    
    public java.lang.Long getAssignEmployeeId(){
    return assignEmployeeId;
    }
    public void setAssignEmployeeId(java.lang.Long assignEmployeeId)
    {
    this.assignEmployeeId = assignEmployeeId;
    }
    
    public java.lang.Long getReviewEmployeeId(){
    return reviewEmployeeId;
    }
    public void setReviewEmployeeId(java.lang.Long reviewEmployeeId)
    {
    this.reviewEmployeeId = reviewEmployeeId;
    }
    
    public java.lang.String getReviewContent(){
    return reviewContent;
    }
    public void setReviewContent(java.lang.String reviewContent)
    {
    this.reviewContent = reviewContent;
    }
    
    public java.util.Date getReviewDeadlineDate(){
    return reviewDeadlineDate;
    }
    public void setReviewDeadlineDate(java.util.Date reviewDeadlineDate)
    {
    this.reviewDeadlineDate = reviewDeadlineDate;
    }
    
    public java.util.Date getReviewFinishDate(){
    return reviewFinishDate;
    }
    public void setReviewFinishDate(java.util.Date reviewFinishDate)
    {
    this.reviewFinishDate = reviewFinishDate;
    }
    
    public java.util.Date getInvestDecisionDate(){
    return investDecisionDate;
    }
    public void setInvestDecisionDate(java.util.Date investDecisionDate)
    {
    this.investDecisionDate = investDecisionDate;
    }
    
    public java.lang.Long getInvestSignEmployeeId(){
    return investSignEmployeeId;
    }
    public void setInvestSignEmployeeId(java.lang.Long investSignEmployeeId)
    {
    this.investSignEmployeeId = investSignEmployeeId;
    }
    
    public java.util.Date getInvestFinishDate(){
    return investFinishDate;
    }
    public void setInvestFinishDate(java.util.Date investFinishDate)
    {
    this.investFinishDate = investFinishDate;
    }
    
    public java.util.Date getProjectFinishDate(){
    return projectFinishDate;
    }
    public void setProjectFinishDate(java.util.Date projectFinishDate)
    {
    this.projectFinishDate = projectFinishDate;
    }
    
    public java.lang.String getSettlementDocumentNo(){
    return settlementDocumentNo;
    }
    public void setSettlementDocumentNo(java.lang.String settlementDocumentNo)
    {
    this.settlementDocumentNo = settlementDocumentNo;
    }
    
    public java.util.Date getSettlementDate(){
    return settlementDate;
    }
    public void setSettlementDate(java.util.Date settlementDate)
    {
    this.settlementDate = settlementDate;
    }
    
    public java.lang.Long getSettlementSignEmployeeId(){
    return settlementSignEmployeeId;
    }
    public void setSettlementSignEmployeeId(java.lang.Long settlementSignEmployeeId)
    {
    this.settlementSignEmployeeId = settlementSignEmployeeId;
    }
    
    public java.lang.Long getConstructionSpendingT(){
    return constructionSpendingT;
    }
    public void setConstructionSpendingT(java.lang.Long constructionSpendingT)
    {
    this.constructionSpendingT = constructionSpendingT;
    }
    
    public java.lang.Long getManageProjectSpendingT(){
    return manageProjectSpendingT;
    }
    public void setManageProjectSpendingT(java.lang.Long manageProjectSpendingT)
    {
    this.manageProjectSpendingT = manageProjectSpendingT;
    }
    
    public java.lang.Long getSpareSpendingT(){
    return spareSpendingT;
    }
    public void setSpareSpendingT(java.lang.Long spareSpendingT)
    {
    this.spareSpendingT = spareSpendingT;
    }
    
    public java.lang.Long getEquipmentSpendingFTax(){
    return equipmentSpendingFTax;
    }
    public void setEquipmentSpendingFTax(java.lang.Long equipmentSpendingFTax)
    {
    this.equipmentSpendingFTax = equipmentSpendingFTax;
    }

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}
    
}
