/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ProjInvestProjectBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "PROJ_INVEST_PROJECTBO")
public class ProjInvestProjectDTO extends BaseFWDTOImpl<ProjInvestProjectBO> {

private java.lang.Long id;
private java.lang.String name;
private java.lang.String code;
private java.lang.String investmentOwner;
private java.lang.Double equipmentSpending;
private java.lang.Double constructionSpending;
private java.lang.Double otherSpending;
private java.lang.Double spareSpending;
private java.lang.Double creatorId;
private java.lang.Double creatorGroupId;
private java.lang.Double statusCode;
private java.lang.String approvalContent;
private java.lang.String appraisalContent;
private java.lang.Double currencyId;
private java.util.Date beginDate;
private java.util.Date endDate;
private java.lang.String closeComment;
private java.lang.Double closeReasonId;
private java.lang.Double deploymentGroupId;
private java.lang.Double catServiceId;
private java.lang.Double catInvestProjectType;
private java.lang.String deploymentLocation;
private java.lang.Double setUpGroupId;
private java.lang.Double manageProjectSpending;
private java.util.Date createdDate;
private java.lang.Double createType;
private java.lang.Double originalId;
private java.lang.String reason;
private java.lang.Double ableToPlan;
private java.lang.String adjustContent;
private java.lang.Double parentId;
private java.util.Date appraisalDate;
private java.util.Date approvalDate;
private java.lang.String appraisalCode;
private java.lang.String appraisalSigner;
private java.lang.String approvalCode;
private java.lang.String approvalSigner;
private java.lang.String reqApprovalSigner;
private java.util.Date reqApprovalDate;
private java.lang.String reqApprovalCode;
private java.lang.Double holdGroupId;
private java.lang.Double decisionGroupId;
private java.lang.String investGoal;
private java.lang.String investLocation;
private java.lang.Double useGroupId;
private java.lang.Double headCreateEmployeeId;
private java.lang.Double compensateCostBt;
private java.lang.Double compensateCostT;
private java.lang.Double equipmentSpendingFCurrency;
private java.lang.Double equipmentSpendingTax;
private java.lang.Double advisoryCostBt;
private java.lang.Double advisoryCostT;
private java.lang.Double otherSpendingFCurrency;
private java.lang.Double otherSpendingTax;
private java.lang.Double totalSpendingTax;
private java.lang.Double totalSpending;
private java.lang.Double capital;
private java.lang.Double investQuality;
private java.lang.Double type;
private java.lang.Double constructionLevel;
private java.util.Date estimateStartDate;
private java.util.Date estimateEndDate;
private java.util.Date projectSubmitDate;
private java.lang.Double assignEmployeeId;
private java.lang.Double reviewEmployeeId;
private java.lang.String reviewContent;
private java.util.Date reviewDeadlineDate;
private java.util.Date reviewFinishDate;
private java.util.Date investDecisionDate;
private java.lang.Double investSignEmployeeId;
private java.util.Date investFinishDate;
private java.util.Date projectFinishDate;
private java.lang.String settlementDocumentNo;
private java.util.Date settlementDate;
private java.lang.Double settlementSignEmployeeId;
private java.lang.Double constructionSpendingT;
private java.lang.Double manageProjectSpendingT;
private java.lang.Double spareSpendingT;
private java.lang.Double equipmentSpendingFTax;
private java.lang.Double otherSpendingFCurrencyT;
private java.lang.Double transRate;
private java.lang.Double totalSpendingBt;
private java.lang.Double totalSpendingBtF;
private java.lang.String capitalDescription;
private java.lang.Double constructionType;
private java.lang.Double holdPartnerId;
private java.lang.Double discountRate;
private java.lang.Double returnDay;
private java.lang.Double internalRateOfReturn;
private java.lang.Double netPresentValue;
private java.lang.Double investType;
private java.lang.String verifyComment;
private java.lang.String otherEffect;
private java.lang.String serviceType;

    @Override
    public ProjInvestProjectBO toModel() {
        ProjInvestProjectBO projInvestProjectBO = new ProjInvestProjectBO();
        projInvestProjectBO.setId(this.id);
        projInvestProjectBO.setName(this.name);
        projInvestProjectBO.setCode(this.code);
        projInvestProjectBO.setInvestmentOwner(this.investmentOwner);
        projInvestProjectBO.setEquipmentSpending(this.equipmentSpending);
        projInvestProjectBO.setConstructionSpending(this.constructionSpending);
        projInvestProjectBO.setOtherSpending(this.otherSpending);
        projInvestProjectBO.setSpareSpending(this.spareSpending);
        projInvestProjectBO.setCreatorId(this.creatorId);
        projInvestProjectBO.setCreatorGroupId(this.creatorGroupId);
        projInvestProjectBO.setStatusCode(this.statusCode);
        projInvestProjectBO.setApprovalContent(this.approvalContent);
        projInvestProjectBO.setAppraisalContent(this.appraisalContent);
        projInvestProjectBO.setCurrencyId(this.currencyId);
        projInvestProjectBO.setBeginDate(this.beginDate);
        projInvestProjectBO.setEndDate(this.endDate);
        projInvestProjectBO.setCloseComment(this.closeComment);
        projInvestProjectBO.setCloseReasonId(this.closeReasonId);
        projInvestProjectBO.setDeploymentGroupId(this.deploymentGroupId);
        projInvestProjectBO.setCatServiceId(this.catServiceId);
        projInvestProjectBO.setCatInvestProjectType(this.catInvestProjectType);
        projInvestProjectBO.setDeploymentLocation(this.deploymentLocation);
        projInvestProjectBO.setSetUpGroupId(this.setUpGroupId);
        projInvestProjectBO.setManageProjectSpending(this.manageProjectSpending);
        projInvestProjectBO.setCreatedDate(this.createdDate);
        projInvestProjectBO.setCreateType(this.createType);
        projInvestProjectBO.setOriginalId(this.originalId);
        projInvestProjectBO.setReason(this.reason);
        projInvestProjectBO.setAbleToPlan(this.ableToPlan);
        projInvestProjectBO.setAdjustContent(this.adjustContent);
        projInvestProjectBO.setParentId(this.parentId);
        projInvestProjectBO.setAppraisalDate(this.appraisalDate);
        projInvestProjectBO.setApprovalDate(this.approvalDate);
        projInvestProjectBO.setAppraisalCode(this.appraisalCode);
        projInvestProjectBO.setAppraisalSigner(this.appraisalSigner);
        projInvestProjectBO.setApprovalCode(this.approvalCode);
        projInvestProjectBO.setApprovalSigner(this.approvalSigner);
        projInvestProjectBO.setReqApprovalSigner(this.reqApprovalSigner);
        projInvestProjectBO.setReqApprovalDate(this.reqApprovalDate);
        projInvestProjectBO.setReqApprovalCode(this.reqApprovalCode);
        projInvestProjectBO.setHoldGroupId(this.holdGroupId);
        projInvestProjectBO.setDecisionGroupId(this.decisionGroupId);
        projInvestProjectBO.setInvestGoal(this.investGoal);
        projInvestProjectBO.setInvestLocation(this.investLocation);
        projInvestProjectBO.setUseGroupId(this.useGroupId);
        projInvestProjectBO.setHeadCreateEmployeeId(this.headCreateEmployeeId);
        projInvestProjectBO.setCompensateCostBt(this.compensateCostBt);
        projInvestProjectBO.setCompensateCostT(this.compensateCostT);
        projInvestProjectBO.setEquipmentSpendingFCurrency(this.equipmentSpendingFCurrency);
        projInvestProjectBO.setEquipmentSpendingTax(this.equipmentSpendingTax);
        projInvestProjectBO.setAdvisoryCostBt(this.advisoryCostBt);
        projInvestProjectBO.setAdvisoryCostT(this.advisoryCostT);
        projInvestProjectBO.setOtherSpendingFCurrency(this.otherSpendingFCurrency);
        projInvestProjectBO.setOtherSpendingTax(this.otherSpendingTax);
        projInvestProjectBO.setTotalSpendingTax(this.totalSpendingTax);
        projInvestProjectBO.setTotalSpending(this.totalSpending);
        projInvestProjectBO.setCapital(this.capital);
        projInvestProjectBO.setInvestQuality(this.investQuality);
        projInvestProjectBO.setType(this.type);
        projInvestProjectBO.setConstructionLevel(this.constructionLevel);
        projInvestProjectBO.setEstimateStartDate(this.estimateStartDate);
        projInvestProjectBO.setEstimateEndDate(this.estimateEndDate);
        projInvestProjectBO.setProjectSubmitDate(this.projectSubmitDate);
        projInvestProjectBO.setAssignEmployeeId(this.assignEmployeeId);
        projInvestProjectBO.setReviewEmployeeId(this.reviewEmployeeId);
        projInvestProjectBO.setReviewContent(this.reviewContent);
        projInvestProjectBO.setReviewDeadlineDate(this.reviewDeadlineDate);
        projInvestProjectBO.setReviewFinishDate(this.reviewFinishDate);
        projInvestProjectBO.setInvestDecisionDate(this.investDecisionDate);
        projInvestProjectBO.setInvestSignEmployeeId(this.investSignEmployeeId);
        projInvestProjectBO.setInvestFinishDate(this.investFinishDate);
        projInvestProjectBO.setProjectFinishDate(this.projectFinishDate);
        projInvestProjectBO.setSettlementDocumentNo(this.settlementDocumentNo);
        projInvestProjectBO.setSettlementDate(this.settlementDate);
        projInvestProjectBO.setSettlementSignEmployeeId(this.settlementSignEmployeeId);
        projInvestProjectBO.setConstructionSpendingT(this.constructionSpendingT);
        projInvestProjectBO.setManageProjectSpendingT(this.manageProjectSpendingT);
        projInvestProjectBO.setSpareSpendingT(this.spareSpendingT);
        projInvestProjectBO.setEquipmentSpendingFTax(this.equipmentSpendingFTax);
        projInvestProjectBO.setOtherSpendingFCurrencyT(this.otherSpendingFCurrencyT);
        projInvestProjectBO.setTransRate(this.transRate);
        projInvestProjectBO.setTotalSpendingBt(this.totalSpendingBt);
        projInvestProjectBO.setTotalSpendingBtF(this.totalSpendingBtF);
        projInvestProjectBO.setCapitalDescription(this.capitalDescription);
        projInvestProjectBO.setConstructionType(this.constructionType);
        projInvestProjectBO.setHoldPartnerId(this.holdPartnerId);
        projInvestProjectBO.setDiscountRate(this.discountRate);
        projInvestProjectBO.setReturnDay(this.returnDay);
        projInvestProjectBO.setInternalRateOfReturn(this.internalRateOfReturn);
        projInvestProjectBO.setNetPresentValue(this.netPresentValue);
        projInvestProjectBO.setInvestType(this.investType);
        projInvestProjectBO.setVerifyComment(this.verifyComment);
        projInvestProjectBO.setOtherEffect(this.otherEffect);
        projInvestProjectBO.setServiceType(this.serviceType);
        return projInvestProjectBO;
    }

    @Override
     public Long getFWModelId() {
        return id;
    }
   
    @Override
    public String catchName() {
        return getId().toString();
    }
    public java.lang.Long getId(){
    return id;
    }
    public void setId(java.lang.Long id)
    {
    this.id = id;
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
    
    public java.lang.Double getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Double creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.lang.Double getCreatorGroupId(){
    return creatorGroupId;
    }
    public void setCreatorGroupId(java.lang.Double creatorGroupId)
    {
    this.creatorGroupId = creatorGroupId;
    }
    
    public java.lang.Double getStatusCode(){
    return statusCode;
    }
    public void setStatusCode(java.lang.Double statusCode)
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
    
    public java.lang.Double getCurrencyId(){
    return currencyId;
    }
    public void setCurrencyId(java.lang.Double currencyId)
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
    
    public java.lang.Double getCloseReasonId(){
    return closeReasonId;
    }
    public void setCloseReasonId(java.lang.Double closeReasonId)
    {
    this.closeReasonId = closeReasonId;
    }
    
    public java.lang.Double getDeploymentGroupId(){
    return deploymentGroupId;
    }
    public void setDeploymentGroupId(java.lang.Double deploymentGroupId)
    {
    this.deploymentGroupId = deploymentGroupId;
    }
    
    public java.lang.Double getCatServiceId(){
    return catServiceId;
    }
    public void setCatServiceId(java.lang.Double catServiceId)
    {
    this.catServiceId = catServiceId;
    }
    
    public java.lang.Double getCatInvestProjectType(){
    return catInvestProjectType;
    }
    public void setCatInvestProjectType(java.lang.Double catInvestProjectType)
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
    
    public java.lang.Double getSetUpGroupId(){
    return setUpGroupId;
    }
    public void setSetUpGroupId(java.lang.Double setUpGroupId)
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
    
    public java.lang.Double getCreateType(){
    return createType;
    }
    public void setCreateType(java.lang.Double createType)
    {
    this.createType = createType;
    }
    
    public java.lang.Double getOriginalId(){
    return originalId;
    }
    public void setOriginalId(java.lang.Double originalId)
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
    
    public java.lang.Double getAbleToPlan(){
    return ableToPlan;
    }
    public void setAbleToPlan(java.lang.Double ableToPlan)
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
    
    public java.lang.Double getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Double parentId)
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
    
    public java.lang.Double getHoldGroupId(){
    return holdGroupId;
    }
    public void setHoldGroupId(java.lang.Double holdGroupId)
    {
    this.holdGroupId = holdGroupId;
    }
    
    public java.lang.Double getDecisionGroupId(){
    return decisionGroupId;
    }
    public void setDecisionGroupId(java.lang.Double decisionGroupId)
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
    
    public java.lang.Double getUseGroupId(){
    return useGroupId;
    }
    public void setUseGroupId(java.lang.Double useGroupId)
    {
    this.useGroupId = useGroupId;
    }
    
    public java.lang.Double getHeadCreateEmployeeId(){
    return headCreateEmployeeId;
    }
    public void setHeadCreateEmployeeId(java.lang.Double headCreateEmployeeId)
    {
    this.headCreateEmployeeId = headCreateEmployeeId;
    }
    
    public java.lang.Double getCompensateCostBt(){
    return compensateCostBt;
    }
    public void setCompensateCostBt(java.lang.Double compensateCostBt)
    {
    this.compensateCostBt = compensateCostBt;
    }
    
    public java.lang.Double getCompensateCostT(){
    return compensateCostT;
    }
    public void setCompensateCostT(java.lang.Double compensateCostT)
    {
    this.compensateCostT = compensateCostT;
    }
    
    public java.lang.Double getEquipmentSpendingFCurrency(){
    return equipmentSpendingFCurrency;
    }
    public void setEquipmentSpendingFCurrency(java.lang.Double equipmentSpendingFCurrency)
    {
    this.equipmentSpendingFCurrency = equipmentSpendingFCurrency;
    }
    
    public java.lang.Double getEquipmentSpendingTax(){
    return equipmentSpendingTax;
    }
    public void setEquipmentSpendingTax(java.lang.Double equipmentSpendingTax)
    {
    this.equipmentSpendingTax = equipmentSpendingTax;
    }
    
    public java.lang.Double getAdvisoryCostBt(){
    return advisoryCostBt;
    }
    public void setAdvisoryCostBt(java.lang.Double advisoryCostBt)
    {
    this.advisoryCostBt = advisoryCostBt;
    }
    
    public java.lang.Double getAdvisoryCostT(){
    return advisoryCostT;
    }
    public void setAdvisoryCostT(java.lang.Double advisoryCostT)
    {
    this.advisoryCostT = advisoryCostT;
    }
    
    public java.lang.Double getOtherSpendingFCurrency(){
    return otherSpendingFCurrency;
    }
    public void setOtherSpendingFCurrency(java.lang.Double otherSpendingFCurrency)
    {
    this.otherSpendingFCurrency = otherSpendingFCurrency;
    }
    
    public java.lang.Double getOtherSpendingTax(){
    return otherSpendingTax;
    }
    public void setOtherSpendingTax(java.lang.Double otherSpendingTax)
    {
    this.otherSpendingTax = otherSpendingTax;
    }
    
    public java.lang.Double getTotalSpendingTax(){
    return totalSpendingTax;
    }
    public void setTotalSpendingTax(java.lang.Double totalSpendingTax)
    {
    this.totalSpendingTax = totalSpendingTax;
    }
    
    public java.lang.Double getTotalSpending(){
    return totalSpending;
    }
    public void setTotalSpending(java.lang.Double totalSpending)
    {
    this.totalSpending = totalSpending;
    }
    
    public java.lang.Double getCapital(){
    return capital;
    }
    public void setCapital(java.lang.Double capital)
    {
    this.capital = capital;
    }
    
    public java.lang.Double getInvestQuality(){
    return investQuality;
    }
    public void setInvestQuality(java.lang.Double investQuality)
    {
    this.investQuality = investQuality;
    }
    
    public java.lang.Double getType(){
    return type;
    }
    public void setType(java.lang.Double type)
    {
    this.type = type;
    }
    
    public java.lang.Double getConstructionLevel(){
    return constructionLevel;
    }
    public void setConstructionLevel(java.lang.Double constructionLevel)
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
    
    public java.lang.Double getAssignEmployeeId(){
    return assignEmployeeId;
    }
    public void setAssignEmployeeId(java.lang.Double assignEmployeeId)
    {
    this.assignEmployeeId = assignEmployeeId;
    }
    
    public java.lang.Double getReviewEmployeeId(){
    return reviewEmployeeId;
    }
    public void setReviewEmployeeId(java.lang.Double reviewEmployeeId)
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
    
    public java.lang.Double getInvestSignEmployeeId(){
    return investSignEmployeeId;
    }
    public void setInvestSignEmployeeId(java.lang.Double investSignEmployeeId)
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
    
    public java.lang.Double getSettlementSignEmployeeId(){
    return settlementSignEmployeeId;
    }
    public void setSettlementSignEmployeeId(java.lang.Double settlementSignEmployeeId)
    {
    this.settlementSignEmployeeId = settlementSignEmployeeId;
    }
    
    public java.lang.Double getConstructionSpendingT(){
    return constructionSpendingT;
    }
    public void setConstructionSpendingT(java.lang.Double constructionSpendingT)
    {
    this.constructionSpendingT = constructionSpendingT;
    }
    
    public java.lang.Double getManageProjectSpendingT(){
    return manageProjectSpendingT;
    }
    public void setManageProjectSpendingT(java.lang.Double manageProjectSpendingT)
    {
    this.manageProjectSpendingT = manageProjectSpendingT;
    }
    
    public java.lang.Double getSpareSpendingT(){
    return spareSpendingT;
    }
    public void setSpareSpendingT(java.lang.Double spareSpendingT)
    {
    this.spareSpendingT = spareSpendingT;
    }
    
    public java.lang.Double getEquipmentSpendingFTax(){
    return equipmentSpendingFTax;
    }
    public void setEquipmentSpendingFTax(java.lang.Double equipmentSpendingFTax)
    {
    this.equipmentSpendingFTax = equipmentSpendingFTax;
    }
    
    public java.lang.Double getOtherSpendingFCurrencyT(){
    return otherSpendingFCurrencyT;
    }
    public void setOtherSpendingFCurrencyT(java.lang.Double otherSpendingFCurrencyT)
    {
    this.otherSpendingFCurrencyT = otherSpendingFCurrencyT;
    }
    
    public java.lang.Double getTransRate(){
    return transRate;
    }
    public void setTransRate(java.lang.Double transRate)
    {
    this.transRate = transRate;
    }
    
    public java.lang.Double getTotalSpendingBt(){
    return totalSpendingBt;
    }
    public void setTotalSpendingBt(java.lang.Double totalSpendingBt)
    {
    this.totalSpendingBt = totalSpendingBt;
    }
    
    public java.lang.Double getTotalSpendingBtF(){
    return totalSpendingBtF;
    }
    public void setTotalSpendingBtF(java.lang.Double totalSpendingBtF)
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
    
    public java.lang.Double getConstructionType(){
    return constructionType;
    }
    public void setConstructionType(java.lang.Double constructionType)
    {
    this.constructionType = constructionType;
    }
    
    public java.lang.Double getHoldPartnerId(){
    return holdPartnerId;
    }
    public void setHoldPartnerId(java.lang.Double holdPartnerId)
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
    
    public java.lang.Double getReturnDay(){
    return returnDay;
    }
    public void setReturnDay(java.lang.Double returnDay)
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
    
    public java.lang.Double getInvestType(){
    return investType;
    }
    public void setInvestType(java.lang.Double investType)
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
    
   
}
