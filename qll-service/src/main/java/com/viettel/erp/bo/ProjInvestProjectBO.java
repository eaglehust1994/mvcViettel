/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ProjInvestProjectDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SuppressWarnings("serial")
@Entity(name = "proj_invest_project")
@Table(name = "PROJ_INVEST_PROJECT")
@DynamicInsert
@DynamicUpdate
@SQLDelete(
	    sql = "UPDATE PROJ_INVEST_PROJECT c SET c.IS_ACTIVE = 1 WHERE c.ID = ? ")
@Cacheable
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ProjInvestProjectBO extends BaseFWModelImpl {
     
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

 public ProjInvestProjectBO() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "PROJ_INVEST_PROJECT_SEQ")
            }
    )
@Column(name = "ID", length = 22)
public java.lang.Long getId(){
return id;
}
public void setId(java.lang.Long id)
{
this.id = id;
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
public java.lang.Double getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Double creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CREATOR_GROUP_ID", length = 22)
public java.lang.Double getCreatorGroupId(){
return creatorGroupId;
}
public void setCreatorGroupId(java.lang.Double creatorGroupId)
{
this.creatorGroupId = creatorGroupId;
}
@Column(name = "STATUS_CODE", length = 22)
public java.lang.Double getStatusCode(){
return statusCode;
}
public void setStatusCode(java.lang.Double statusCode)
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
public java.lang.Double getCurrencyId(){
return currencyId;
}
public void setCurrencyId(java.lang.Double currencyId)
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
public java.lang.Double getCloseReasonId(){
return closeReasonId;
}
public void setCloseReasonId(java.lang.Double closeReasonId)
{
this.closeReasonId = closeReasonId;
}
@Column(name = "DEPLOYMENT_GROUP_ID", length = 22)
public java.lang.Double getDeploymentGroupId(){
return deploymentGroupId;
}
public void setDeploymentGroupId(java.lang.Double deploymentGroupId)
{
this.deploymentGroupId = deploymentGroupId;
}
@Column(name = "CAT_SERVICE_ID", length = 22)
public java.lang.Double getCatServiceId(){
return catServiceId;
}
public void setCatServiceId(java.lang.Double catServiceId)
{
this.catServiceId = catServiceId;
}
@Column(name = "CAT_INVEST_PROJECT_TYPE", length = 22)
public java.lang.Double getCatInvestProjectType(){
return catInvestProjectType;
}
public void setCatInvestProjectType(java.lang.Double catInvestProjectType)
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
public java.lang.Double getSetUpGroupId(){
return setUpGroupId;
}
public void setSetUpGroupId(java.lang.Double setUpGroupId)
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
public java.lang.Double getCreateType(){
return createType;
}
public void setCreateType(java.lang.Double createType)
{
this.createType = createType;
}
@Column(name = "ORIGINAL_ID", length = 22)
public java.lang.Double getOriginalId(){
return originalId;
}
public void setOriginalId(java.lang.Double originalId)
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
public java.lang.Double getAbleToPlan(){
return ableToPlan;
}
public void setAbleToPlan(java.lang.Double ableToPlan)
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
public java.lang.Double getParentId(){
return parentId;
}
public void setParentId(java.lang.Double parentId)
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
public java.lang.Double getHoldGroupId(){
return holdGroupId;
}
public void setHoldGroupId(java.lang.Double holdGroupId)
{
this.holdGroupId = holdGroupId;
}
@Column(name = "DECISION_GROUP_ID", length = 22)
public java.lang.Double getDecisionGroupId(){
return decisionGroupId;
}
public void setDecisionGroupId(java.lang.Double decisionGroupId)
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
public java.lang.Double getUseGroupId(){
return useGroupId;
}
public void setUseGroupId(java.lang.Double useGroupId)
{
this.useGroupId = useGroupId;
}
@Column(name = "HEAD_CREATE_EMPLOYEE_ID", length = 22)
public java.lang.Double getHeadCreateEmployeeId(){
return headCreateEmployeeId;
}
public void setHeadCreateEmployeeId(java.lang.Double headCreateEmployeeId)
{
this.headCreateEmployeeId = headCreateEmployeeId;
}
@Column(name = "COMPENSATE_COST_BT", length = 22)
public java.lang.Double getCompensateCostBt(){
return compensateCostBt;
}
public void setCompensateCostBt(java.lang.Double compensateCostBt)
{
this.compensateCostBt = compensateCostBt;
}
@Column(name = "COMPENSATE_COST_T", length = 22)
public java.lang.Double getCompensateCostT(){
return compensateCostT;
}
public void setCompensateCostT(java.lang.Double compensateCostT)
{
this.compensateCostT = compensateCostT;
}
@Column(name = "EQUIPMENT_SPENDING_F_CURRENCY", length = 22)
public java.lang.Double getEquipmentSpendingFCurrency(){
return equipmentSpendingFCurrency;
}
public void setEquipmentSpendingFCurrency(java.lang.Double equipmentSpendingFCurrency)
{
this.equipmentSpendingFCurrency = equipmentSpendingFCurrency;
}
@Column(name = "EQUIPMENT_SPENDING_TAX", length = 22)
public java.lang.Double getEquipmentSpendingTax(){
return equipmentSpendingTax;
}
public void setEquipmentSpendingTax(java.lang.Double equipmentSpendingTax)
{
this.equipmentSpendingTax = equipmentSpendingTax;
}
@Column(name = "ADVISORY_COST_BT", length = 22)
public java.lang.Double getAdvisoryCostBt(){
return advisoryCostBt;
}
public void setAdvisoryCostBt(java.lang.Double advisoryCostBt)
{
this.advisoryCostBt = advisoryCostBt;
}
@Column(name = "ADVISORY_COST_T", length = 22)
public java.lang.Double getAdvisoryCostT(){
return advisoryCostT;
}
public void setAdvisoryCostT(java.lang.Double advisoryCostT)
{
this.advisoryCostT = advisoryCostT;
}
@Column(name = "OTHER_SPENDING_F_CURRENCY", length = 22)
public java.lang.Double getOtherSpendingFCurrency(){
return otherSpendingFCurrency;
}
public void setOtherSpendingFCurrency(java.lang.Double otherSpendingFCurrency)
{
this.otherSpendingFCurrency = otherSpendingFCurrency;
}
@Column(name = "OTHER_SPENDING_TAX", length = 22)
public java.lang.Double getOtherSpendingTax(){
return otherSpendingTax;
}
public void setOtherSpendingTax(java.lang.Double otherSpendingTax)
{
this.otherSpendingTax = otherSpendingTax;
}
@Column(name = "TOTAL_SPENDING_TAX", length = 22)
public java.lang.Double getTotalSpendingTax(){
return totalSpendingTax;
}
public void setTotalSpendingTax(java.lang.Double totalSpendingTax)
{
this.totalSpendingTax = totalSpendingTax;
}
@Column(name = "TOTAL_SPENDING", length = 22)
public java.lang.Double getTotalSpending(){
return totalSpending;
}
public void setTotalSpending(java.lang.Double totalSpending)
{
this.totalSpending = totalSpending;
}
@Column(name = "CAPITAL", length = 22)
public java.lang.Double getCapital(){
return capital;
}
public void setCapital(java.lang.Double capital)
{
this.capital = capital;
}
@Column(name = "INVEST_QUALITY", length = 22)
public java.lang.Double getInvestQuality(){
return investQuality;
}
public void setInvestQuality(java.lang.Double investQuality)
{
this.investQuality = investQuality;
}
@Column(name = "TYPE", length = 22)
public java.lang.Double getType(){
return type;
}
public void setType(java.lang.Double type)
{
this.type = type;
}
@Column(name = "CONSTRUCTION_LEVEL", length = 22)
public java.lang.Double getConstructionLevel(){
return constructionLevel;
}
public void setConstructionLevel(java.lang.Double constructionLevel)
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
public java.lang.Double getAssignEmployeeId(){
return assignEmployeeId;
}
public void setAssignEmployeeId(java.lang.Double assignEmployeeId)
{
this.assignEmployeeId = assignEmployeeId;
}
@Column(name = "REVIEW_EMPLOYEE_ID", length = 22)
public java.lang.Double getReviewEmployeeId(){
return reviewEmployeeId;
}
public void setReviewEmployeeId(java.lang.Double reviewEmployeeId)
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
public java.lang.Double getInvestSignEmployeeId(){
return investSignEmployeeId;
}
public void setInvestSignEmployeeId(java.lang.Double investSignEmployeeId)
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
public java.lang.Double getSettlementSignEmployeeId(){
return settlementSignEmployeeId;
}
public void setSettlementSignEmployeeId(java.lang.Double settlementSignEmployeeId)
{
this.settlementSignEmployeeId = settlementSignEmployeeId;
}
@Column(name = "CONSTRUCTION_SPENDING_T", length = 22)
public java.lang.Double getConstructionSpendingT(){
return constructionSpendingT;
}
public void setConstructionSpendingT(java.lang.Double constructionSpendingT)
{
this.constructionSpendingT = constructionSpendingT;
}
@Column(name = "MANAGE_PROJECT_SPENDING_T", length = 22)
public java.lang.Double getManageProjectSpendingT(){
return manageProjectSpendingT;
}
public void setManageProjectSpendingT(java.lang.Double manageProjectSpendingT)
{
this.manageProjectSpendingT = manageProjectSpendingT;
}
@Column(name = "SPARE_SPENDING_T", length = 22)
public java.lang.Double getSpareSpendingT(){
return spareSpendingT;
}
public void setSpareSpendingT(java.lang.Double spareSpendingT)
{
this.spareSpendingT = spareSpendingT;
}
@Column(name = "EQUIPMENT_SPENDING_F_TAX", length = 22)
public java.lang.Double getEquipmentSpendingFTax(){
return equipmentSpendingFTax;
}
public void setEquipmentSpendingFTax(java.lang.Double equipmentSpendingFTax)
{
this.equipmentSpendingFTax = equipmentSpendingFTax;
}
@Column(name = "OTHER_SPENDING_F_CURRENCY_T", length = 22)
public java.lang.Double getOtherSpendingFCurrencyT(){
return otherSpendingFCurrencyT;
}
public void setOtherSpendingFCurrencyT(java.lang.Double otherSpendingFCurrencyT)
{
this.otherSpendingFCurrencyT = otherSpendingFCurrencyT;
}
@Column(name = "TRANS_RATE", length = 22)
public java.lang.Double getTransRate(){
return transRate;
}
public void setTransRate(java.lang.Double transRate)
{
this.transRate = transRate;
}
@Column(name = "TOTAL_SPENDING_BT", length = 22)
public java.lang.Double getTotalSpendingBt(){
return totalSpendingBt;
}
public void setTotalSpendingBt(java.lang.Double totalSpendingBt)
{
this.totalSpendingBt = totalSpendingBt;
}
@Column(name = "TOTAL_SPENDING_BT_F", length = 22)
public java.lang.Double getTotalSpendingBtF(){
return totalSpendingBtF;
}
public void setTotalSpendingBtF(java.lang.Double totalSpendingBtF)
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
public java.lang.Double getConstructionType(){
return constructionType;
}
public void setConstructionType(java.lang.Double constructionType)
{
this.constructionType = constructionType;
}
@Column(name = "HOLD_PARTNER_ID", length = 22)
public java.lang.Double getHoldPartnerId(){
return holdPartnerId;
}
public void setHoldPartnerId(java.lang.Double holdPartnerId)
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
public java.lang.Double getReturnDay(){
return returnDay;
}
public void setReturnDay(java.lang.Double returnDay)
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
public java.lang.Double getInvestType(){
return investType;
}
public void setInvestType(java.lang.Double investType)
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
   

    @Override
    public ProjInvestProjectDTO toDTO() {
        ProjInvestProjectDTO projInvestProjectDTO = new ProjInvestProjectDTO();
        //set cac gia tri 
        projInvestProjectDTO.setId(this.id);
        projInvestProjectDTO.setName(this.name);
        projInvestProjectDTO.setCode(this.code);
        projInvestProjectDTO.setInvestmentOwner(this.investmentOwner);
        projInvestProjectDTO.setEquipmentSpending(this.equipmentSpending);
        projInvestProjectDTO.setConstructionSpending(this.constructionSpending);
        projInvestProjectDTO.setOtherSpending(this.otherSpending);
        projInvestProjectDTO.setSpareSpending(this.spareSpending);
        projInvestProjectDTO.setCreatorId(this.creatorId);
        projInvestProjectDTO.setCreatorGroupId(this.creatorGroupId);
        projInvestProjectDTO.setStatusCode(this.statusCode);
        projInvestProjectDTO.setApprovalContent(this.approvalContent);
        projInvestProjectDTO.setAppraisalContent(this.appraisalContent);
        projInvestProjectDTO.setCurrencyId(this.currencyId);
        projInvestProjectDTO.setBeginDate(this.beginDate);
        projInvestProjectDTO.setEndDate(this.endDate);
        projInvestProjectDTO.setCloseComment(this.closeComment);
        projInvestProjectDTO.setCloseReasonId(this.closeReasonId);
        projInvestProjectDTO.setDeploymentGroupId(this.deploymentGroupId);
        projInvestProjectDTO.setCatServiceId(this.catServiceId);
        projInvestProjectDTO.setCatInvestProjectType(this.catInvestProjectType);
        projInvestProjectDTO.setDeploymentLocation(this.deploymentLocation);
        projInvestProjectDTO.setSetUpGroupId(this.setUpGroupId);
        projInvestProjectDTO.setManageProjectSpending(this.manageProjectSpending);
        projInvestProjectDTO.setCreatedDate(this.createdDate);
        projInvestProjectDTO.setCreateType(this.createType);
        projInvestProjectDTO.setOriginalId(this.originalId);
        projInvestProjectDTO.setReason(this.reason);
        projInvestProjectDTO.setAbleToPlan(this.ableToPlan);
        projInvestProjectDTO.setAdjustContent(this.adjustContent);
        projInvestProjectDTO.setParentId(this.parentId);
        projInvestProjectDTO.setAppraisalDate(this.appraisalDate);
        projInvestProjectDTO.setApprovalDate(this.approvalDate);
        projInvestProjectDTO.setAppraisalCode(this.appraisalCode);
        projInvestProjectDTO.setAppraisalSigner(this.appraisalSigner);
        projInvestProjectDTO.setApprovalCode(this.approvalCode);
        projInvestProjectDTO.setApprovalSigner(this.approvalSigner);
        projInvestProjectDTO.setReqApprovalSigner(this.reqApprovalSigner);
        projInvestProjectDTO.setReqApprovalDate(this.reqApprovalDate);
        projInvestProjectDTO.setReqApprovalCode(this.reqApprovalCode);
        projInvestProjectDTO.setHoldGroupId(this.holdGroupId);
        projInvestProjectDTO.setDecisionGroupId(this.decisionGroupId);
        projInvestProjectDTO.setInvestGoal(this.investGoal);
        projInvestProjectDTO.setInvestLocation(this.investLocation);
        projInvestProjectDTO.setUseGroupId(this.useGroupId);
        projInvestProjectDTO.setHeadCreateEmployeeId(this.headCreateEmployeeId);
        projInvestProjectDTO.setCompensateCostBt(this.compensateCostBt);
        projInvestProjectDTO.setCompensateCostT(this.compensateCostT);
        projInvestProjectDTO.setEquipmentSpendingFCurrency(this.equipmentSpendingFCurrency);
        projInvestProjectDTO.setEquipmentSpendingTax(this.equipmentSpendingTax);
        projInvestProjectDTO.setAdvisoryCostBt(this.advisoryCostBt);
        projInvestProjectDTO.setAdvisoryCostT(this.advisoryCostT);
        projInvestProjectDTO.setOtherSpendingFCurrency(this.otherSpendingFCurrency);
        projInvestProjectDTO.setOtherSpendingTax(this.otherSpendingTax);
        projInvestProjectDTO.setTotalSpendingTax(this.totalSpendingTax);
        projInvestProjectDTO.setTotalSpending(this.totalSpending);
        projInvestProjectDTO.setCapital(this.capital);
        projInvestProjectDTO.setInvestQuality(this.investQuality);
        projInvestProjectDTO.setType(this.type);
        projInvestProjectDTO.setConstructionLevel(this.constructionLevel);
        projInvestProjectDTO.setEstimateStartDate(this.estimateStartDate);
        projInvestProjectDTO.setEstimateEndDate(this.estimateEndDate);
        projInvestProjectDTO.setProjectSubmitDate(this.projectSubmitDate);
        projInvestProjectDTO.setAssignEmployeeId(this.assignEmployeeId);
        projInvestProjectDTO.setReviewEmployeeId(this.reviewEmployeeId);
        projInvestProjectDTO.setReviewContent(this.reviewContent);
        projInvestProjectDTO.setReviewDeadlineDate(this.reviewDeadlineDate);
        projInvestProjectDTO.setReviewFinishDate(this.reviewFinishDate);
        projInvestProjectDTO.setInvestDecisionDate(this.investDecisionDate);
        projInvestProjectDTO.setInvestSignEmployeeId(this.investSignEmployeeId);
        projInvestProjectDTO.setInvestFinishDate(this.investFinishDate);
        projInvestProjectDTO.setProjectFinishDate(this.projectFinishDate);
        projInvestProjectDTO.setSettlementDocumentNo(this.settlementDocumentNo);
        projInvestProjectDTO.setSettlementDate(this.settlementDate);
        projInvestProjectDTO.setSettlementSignEmployeeId(this.settlementSignEmployeeId);
        projInvestProjectDTO.setConstructionSpendingT(this.constructionSpendingT);
        projInvestProjectDTO.setManageProjectSpendingT(this.manageProjectSpendingT);
        projInvestProjectDTO.setSpareSpendingT(this.spareSpendingT);
        projInvestProjectDTO.setEquipmentSpendingFTax(this.equipmentSpendingFTax);
        projInvestProjectDTO.setOtherSpendingFCurrencyT(this.otherSpendingFCurrencyT);
        projInvestProjectDTO.setTransRate(this.transRate);
        projInvestProjectDTO.setTotalSpendingBt(this.totalSpendingBt);
        projInvestProjectDTO.setTotalSpendingBtF(this.totalSpendingBtF);
        projInvestProjectDTO.setCapitalDescription(this.capitalDescription);
        projInvestProjectDTO.setConstructionType(this.constructionType);
        projInvestProjectDTO.setHoldPartnerId(this.holdPartnerId);
        projInvestProjectDTO.setDiscountRate(this.discountRate);
        projInvestProjectDTO.setReturnDay(this.returnDay);
        projInvestProjectDTO.setInternalRateOfReturn(this.internalRateOfReturn);
        projInvestProjectDTO.setNetPresentValue(this.netPresentValue);
        projInvestProjectDTO.setInvestType(this.investType);
        projInvestProjectDTO.setVerifyComment(this.verifyComment);
        projInvestProjectDTO.setOtherEffect(this.otherEffect);
        projInvestProjectDTO.setServiceType(this.serviceType);
        return projInvestProjectDTO;
    }
}
