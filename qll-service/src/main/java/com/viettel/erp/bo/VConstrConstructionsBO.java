/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.VConstrConstructionsDTO;
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

@Entity(name = "v_constr_constructions")
@Table(name = "V_CONSTR_CONSTRUCTIONS")
@DynamicInsert
@DynamicUpdate
@SQLDelete(
	    sql = "UPDATE V_CONSTR_CONSTRUCTIONS c SET c.IS_ACTIVE = 1 WHERE c.CONSTRUCT_ID = ? ")
@Where( clause = "IS_ACTIVE = '1'" )
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class VConstrConstructionsBO extends BaseFWModelImpl {
     
private java.lang.Long investProjectId;
private java.lang.String investProjectName;
private java.lang.String investProjectCode;
private java.lang.Long planId;
private java.lang.String planName;
private java.lang.String planCode;
private java.lang.Long stationId;
private java.lang.Long transferenceForm;
private java.util.Date transferDate;
private java.lang.String districtCode;
private java.lang.String districtName;
private java.lang.String transfererName;
private java.lang.String recipientName;
private java.lang.Long afterAppCost;
private java.util.Date approvalDate;
private java.lang.Long finalBalanceCostApproval;
private java.util.Date balanceDateApproval;
private java.lang.Long finalBalanceCost;
private java.util.Date balanceDate;
private java.lang.String stationCode;
private java.lang.String stationAddress;
private java.lang.String stationDescription;
private java.lang.String provinceCode;
private java.lang.String provinceName;
private java.lang.Long startPointId;
private java.lang.String statusName;
private java.lang.Long updateProcess;
private java.lang.Long isApLine;
private java.lang.Long numrow;
private java.lang.Long constructId;
private java.lang.String constrtCode;
private java.lang.Long constrType;
private java.lang.String constrTypeName;
private java.lang.String constrtName;
private java.lang.String constrtAddress;
private java.lang.Long constrtForm;
private java.lang.Long superForm;
private java.lang.Long constructorId;
private java.util.Date acceptStartDate;
private java.util.Date acceptFinishDate;
private java.lang.Long isAccepted;
private java.lang.Long statusId;
private java.lang.String constructorName;
private java.lang.Long supervisorId;
private java.lang.String supervisorName;
private java.util.Date startingDate;
private java.util.Date completeDate;
private java.lang.Long suggestorId;
private java.lang.String suggestorName;
private java.lang.Long suggestorGroupId;
private java.lang.String suggestorGroupName;
private java.util.Date suggestDate;
private java.lang.Long sysUserId;
private java.lang.String sysUserName;
private java.lang.Long sysGroupId;
private java.lang.String sysGroupName;
private java.util.Date sysCreatedDate;
private java.lang.Long status;
private java.util.Date expectedCompleteDate;
private java.lang.String startComment;
private java.lang.Long isActive;
private java.lang.Long catCurrencyId;
private java.lang.String catCurrencyCode;
private java.lang.String catCurrencyName;
private java.lang.Long provinceId;
private java.lang.Long districtId;

 public VConstrConstructionsBO() {
        setColId("investProjectId");
        setColName("investProjectId");
        setUniqueColumn(new String[]{"investProjectId"});
}
@Id
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "INVEST_PROJECT_ID", length = 22)
public java.lang.Long getInvestProjectId(){
return investProjectId;
}
public void setInvestProjectId(java.lang.Long investProjectId)
{
this.investProjectId = investProjectId;
}
@Column(name = "INVEST_PROJECT_NAME", length = 600)
public java.lang.String getInvestProjectName(){
return investProjectName;
}
public void setInvestProjectName(java.lang.String investProjectName)
{
this.investProjectName = investProjectName;
}
@Column(name = "INVEST_PROJECT_CODE", length = 100)
public java.lang.String getInvestProjectCode(){
return investProjectCode;
}
public void setInvestProjectCode(java.lang.String investProjectCode)
{
this.investProjectCode = investProjectCode;
}
@Column(name = "PLAN_ID", length = 22)
public java.lang.Long getPlanId(){
return planId;
}
public void setPlanId(java.lang.Long planId)
{
this.planId = planId;
}
@Column(name = "PLAN_NAME", length = 1000)
public java.lang.String getPlanName(){
return planName;
}
public void setPlanName(java.lang.String planName)
{
this.planName = planName;
}
@Column(name = "PLAN_CODE", length = 200)
public java.lang.String getPlanCode(){
return planCode;
}
public void setPlanCode(java.lang.String planCode)
{
this.planCode = planCode;
}
@Column(name = "STATION_ID", length = 22)
public java.lang.Long getStationId(){
return stationId;
}
public void setStationId(java.lang.Long stationId)
{
this.stationId = stationId;
}
@Column(name = "TRANSFERENCE_FORM", length = 22)
public java.lang.Long getTransferenceForm(){
return transferenceForm;
}
public void setTransferenceForm(java.lang.Long transferenceForm)
{
this.transferenceForm = transferenceForm;
}
@Column(name = "TRANSFER_DATE", length = 7)
public java.util.Date getTransferDate(){
return transferDate;
}
public void setTransferDate(java.util.Date transferDate)
{
this.transferDate = transferDate;
}
@Column(name = "DISTRICT_CODE", length = 40)
public java.lang.String getDistrictCode(){
return districtCode;
}
public void setDistrictCode(java.lang.String districtCode)
{
this.districtCode = districtCode;
}
@Column(name = "DISTRICT_NAME", length = 200)
public java.lang.String getDistrictName(){
return districtName;
}
public void setDistrictName(java.lang.String districtName)
{
this.districtName = districtName;
}
@Column(name = "TRANSFERER_NAME", length = 200)
public java.lang.String getTransfererName(){
return transfererName;
}
public void setTransfererName(java.lang.String transfererName)
{
this.transfererName = transfererName;
}
@Column(name = "RECIPIENT_NAME", length = 200)
public java.lang.String getRecipientName(){
return recipientName;
}
public void setRecipientName(java.lang.String recipientName)
{
this.recipientName = recipientName;
}
@Column(name = "AFTER_APP_COST", length = 22)
public java.lang.Long getAfterAppCost(){
return afterAppCost;
}
public void setAfterAppCost(java.lang.Long afterAppCost)
{
this.afterAppCost = afterAppCost;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "FINAL_BALANCE_COST_APPROVAL", length = 22)
public java.lang.Long getFinalBalanceCostApproval(){
return finalBalanceCostApproval;
}
public void setFinalBalanceCostApproval(java.lang.Long finalBalanceCostApproval)
{
this.finalBalanceCostApproval = finalBalanceCostApproval;
}
@Column(name = "BALANCE_DATE_APPROVAL", length = 7)
public java.util.Date getBalanceDateApproval(){
return balanceDateApproval;
}
public void setBalanceDateApproval(java.util.Date balanceDateApproval)
{
this.balanceDateApproval = balanceDateApproval;
}
@Column(name = "FINAL_BALANCE_COST", length = 22)
public java.lang.Long getFinalBalanceCost(){
return finalBalanceCost;
}
public void setFinalBalanceCost(java.lang.Long finalBalanceCost)
{
this.finalBalanceCost = finalBalanceCost;
}
@Column(name = "BALANCE_DATE", length = 7)
public java.util.Date getBalanceDate(){
return balanceDate;
}
public void setBalanceDate(java.util.Date balanceDate)
{
this.balanceDate = balanceDate;
}
@Column(name = "STATION_CODE", length = 1200)
public java.lang.String getStationCode(){
return stationCode;
}
public void setStationCode(java.lang.String stationCode)
{
this.stationCode = stationCode;
}
@Column(name = "STATION_ADDRESS", length = 1000)
public java.lang.String getStationAddress(){
return stationAddress;
}
public void setStationAddress(java.lang.String stationAddress)
{
this.stationAddress = stationAddress;
}
@Column(name = "STATION_DESCRIPTION", length = 1000)
public java.lang.String getStationDescription(){
return stationDescription;
}
public void setStationDescription(java.lang.String stationDescription)
{
this.stationDescription = stationDescription;
}
@Column(name = "PROVINCE_CODE", length = 40)
public java.lang.String getProvinceCode(){
return provinceCode;
}
public void setProvinceCode(java.lang.String provinceCode)
{
this.provinceCode = provinceCode;
}
@Column(name = "PROVINCE_NAME", length = 100)
public java.lang.String getProvinceName(){
return provinceName;
}
public void setProvinceName(java.lang.String provinceName)
{
this.provinceName = provinceName;
}
@Column(name = "START_POINT_ID", length = 22)
public java.lang.Long getStartPointId(){
return startPointId;
}
public void setStartPointId(java.lang.Long startPointId)
{
this.startPointId = startPointId;
}
@Column(name = "STATUS_NAME", length = 400)
public java.lang.String getStatusName(){
return statusName;
}
public void setStatusName(java.lang.String statusName)
{
this.statusName = statusName;
}
@Column(name = "UPDATE_PROCESS", length = 22)
public java.lang.Long getUpdateProcess(){
return updateProcess;
}
public void setUpdateProcess(java.lang.Long updateProcess)
{
this.updateProcess = updateProcess;
}
@Column(name = "IS_AP_LINE", length = 22)
public java.lang.Long getIsApLine(){
return isApLine;
}
public void setIsApLine(java.lang.Long isApLine)
{
this.isApLine = isApLine;
}
@Column(name = "NUMROW", length = 22)
public java.lang.Long getNumrow(){
return numrow;
}
public void setNumrow(java.lang.Long numrow)
{
this.numrow = numrow;
}

@Column(name = "CONSTRT_CODE", length = 200)
public java.lang.String getConstrtCode(){
return constrtCode;
}
public void setConstrtCode(java.lang.String constrtCode)
{
this.constrtCode = constrtCode;
}
@Column(name = "CONSTR_TYPE", length = 22)
public java.lang.Long getConstrType(){
return constrType;
}
public void setConstrType(java.lang.Long constrType)
{
this.constrType = constrType;
}
@Column(name = "CONSTR_TYPE_NAME", length = 100)
public java.lang.String getConstrTypeName(){
return constrTypeName;
}
public void setConstrTypeName(java.lang.String constrTypeName)
{
this.constrTypeName = constrTypeName;
}
@Column(name = "CONSTRT_NAME", length = 600)
public java.lang.String getConstrtName(){
return constrtName;
}
public void setConstrtName(java.lang.String constrtName)
{
this.constrtName = constrtName;
}
@Column(name = "CONSTRT_ADDRESS", length = 1000)
public java.lang.String getConstrtAddress(){
return constrtAddress;
}
public void setConstrtAddress(java.lang.String constrtAddress)
{
this.constrtAddress = constrtAddress;
}
@Column(name = "CONSTRT_FORM", length = 22)
public java.lang.Long getConstrtForm(){
return constrtForm;
}
public void setConstrtForm(java.lang.Long constrtForm)
{
this.constrtForm = constrtForm;
}
@Column(name = "SUPER_FORM", length = 22)
public java.lang.Long getSuperForm(){
return superForm;
}
public void setSuperForm(java.lang.Long superForm)
{
this.superForm = superForm;
}
@Column(name = "CONSTRUCTOR_ID", length = 22)
public java.lang.Long getConstructorId(){
return constructorId;
}
public void setConstructorId(java.lang.Long constructorId)
{
this.constructorId = constructorId;
}
@Column(name = "ACCEPT_START_DATE", length = 7)
public java.util.Date getAcceptStartDate(){
return acceptStartDate;
}
public void setAcceptStartDate(java.util.Date acceptStartDate)
{
this.acceptStartDate = acceptStartDate;
}
@Column(name = "ACCEPT_FINISH_DATE", length = 7)
public java.util.Date getAcceptFinishDate(){
return acceptFinishDate;
}
public void setAcceptFinishDate(java.util.Date acceptFinishDate)
{
this.acceptFinishDate = acceptFinishDate;
}
@Column(name = "IS_ACCEPTED", length = 22)
public java.lang.Long getIsAccepted(){
return isAccepted;
}
public void setIsAccepted(java.lang.Long isAccepted)
{
this.isAccepted = isAccepted;
}
@Column(name = "STATUS_ID", length = 22)
public java.lang.Long getStatusId(){
return statusId;
}
public void setStatusId(java.lang.Long statusId)
{
this.statusId = statusId;
}
@Column(name = "CONSTRUCTOR_NAME", length = 1400)
public java.lang.String getConstructorName(){
return constructorName;
}
public void setConstructorName(java.lang.String constructorName)
{
this.constructorName = constructorName;
}
@Column(name = "SUPERVISOR_ID", length = 22)
public java.lang.Long getSupervisorId(){
return supervisorId;
}
public void setSupervisorId(java.lang.Long supervisorId)
{
this.supervisorId = supervisorId;
}
@Column(name = "SUPERVISOR_NAME", length = 1400)
public java.lang.String getSupervisorName(){
return supervisorName;
}
public void setSupervisorName(java.lang.String supervisorName)
{
this.supervisorName = supervisorName;
}
@Column(name = "STARTING_DATE", length = 7)
public java.util.Date getStartingDate(){
return startingDate;
}
public void setStartingDate(java.util.Date startingDate)
{
this.startingDate = startingDate;
}
@Column(name = "COMPLETE_DATE", length = 7)
public java.util.Date getCompleteDate(){
return completeDate;
}
public void setCompleteDate(java.util.Date completeDate)
{
this.completeDate = completeDate;
}
@Column(name = "SUGGESTOR_ID", length = 22)
public java.lang.Long getSuggestorId(){
return suggestorId;
}
public void setSuggestorId(java.lang.Long suggestorId)
{
this.suggestorId = suggestorId;
}
@Column(name = "SUGGESTOR_NAME", length = 200)
public java.lang.String getSuggestorName(){
return suggestorName;
}
public void setSuggestorName(java.lang.String suggestorName)
{
this.suggestorName = suggestorName;
}
@Column(name = "SUGGESTOR_GROUP_ID", length = 22)
public java.lang.Long getSuggestorGroupId(){
return suggestorGroupId;
}
public void setSuggestorGroupId(java.lang.Long suggestorGroupId)
{
this.suggestorGroupId = suggestorGroupId;
}
@Column(name = "SUGGESTOR_GROUP_NAME", length = 200)
public java.lang.String getSuggestorGroupName(){
return suggestorGroupName;
}
public void setSuggestorGroupName(java.lang.String suggestorGroupName)
{
this.suggestorGroupName = suggestorGroupName;
}
@Column(name = "SUGGEST_DATE", length = 7)
public java.util.Date getSuggestDate(){
return suggestDate;
}
public void setSuggestDate(java.util.Date suggestDate)
{
this.suggestDate = suggestDate;
}
@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Long getSysUserId(){
return sysUserId;
}
public void setSysUserId(java.lang.Long sysUserId)
{
this.sysUserId = sysUserId;
}
@Column(name = "SYS_USER_NAME", length = 200)
public java.lang.String getSysUserName(){
return sysUserName;
}
public void setSysUserName(java.lang.String sysUserName)
{
this.sysUserName = sysUserName;
}
@Column(name = "SYS_GROUP_ID", length = 22)
public java.lang.Long getSysGroupId(){
return sysGroupId;
}
public void setSysGroupId(java.lang.Long sysGroupId)
{
this.sysGroupId = sysGroupId;
}
@Column(name = "SYS_GROUP_NAME", length = 200)
public java.lang.String getSysGroupName(){
return sysGroupName;
}
public void setSysGroupName(java.lang.String sysGroupName)
{
this.sysGroupName = sysGroupName;
}
@Column(name = "SYS_CREATED_DATE", length = 7)
public java.util.Date getSysCreatedDate(){
return sysCreatedDate;
}
public void setSysCreatedDate(java.util.Date sysCreatedDate)
{
this.sysCreatedDate = sysCreatedDate;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "EXPECTED_COMPLETE_DATE", length = 7)
public java.util.Date getExpectedCompleteDate(){
return expectedCompleteDate;
}
public void setExpectedCompleteDate(java.util.Date expectedCompleteDate)
{
this.expectedCompleteDate = expectedCompleteDate;
}
@Column(name = "START_COMMENT", length = 1000)
public java.lang.String getStartComment(){
return startComment;
}
public void setStartComment(java.lang.String startComment)
{
this.startComment = startComment;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "CAT_CURRENCY_ID", length = 22)
public java.lang.Long getCatCurrencyId(){
return catCurrencyId;
}
public void setCatCurrencyId(java.lang.Long catCurrencyId)
{
this.catCurrencyId = catCurrencyId;
}
@Column(name = "CAT_CURRENCY_CODE", length = 20)
public java.lang.String getCatCurrencyCode(){
return catCurrencyCode;
}
public void setCatCurrencyCode(java.lang.String catCurrencyCode)
{
this.catCurrencyCode = catCurrencyCode;
}
@Column(name = "CAT_CURRENCY_NAME", length = 400)
public java.lang.String getCatCurrencyName(){
return catCurrencyName;
}
public void setCatCurrencyName(java.lang.String catCurrencyName)
{
this.catCurrencyName = catCurrencyName;
}
@Column(name = "PROVINCE_ID", length = 22)
public java.lang.Long getProvinceId(){
return provinceId;
}
public void setProvinceId(java.lang.Long provinceId)
{
this.provinceId = provinceId;
}
@Column(name = "DISTRICT_ID", length = 22)
public java.lang.Long getDistrictId(){
return districtId;
}
public void setDistrictId(java.lang.Long districtId)
{
this.districtId = districtId;
}
   

    @Override
    public VConstrConstructionsDTO toDTO() {
        VConstrConstructionsDTO vConstrConstructionsDTO = new VConstrConstructionsDTO();
        //set cac gia tri 
        vConstrConstructionsDTO.setInvestProjectId(this.investProjectId);
        vConstrConstructionsDTO.setInvestProjectName(this.investProjectName);
        vConstrConstructionsDTO.setInvestProjectCode(this.investProjectCode);
        vConstrConstructionsDTO.setPlanId(this.planId);
        vConstrConstructionsDTO.setPlanName(this.planName);
        vConstrConstructionsDTO.setPlanCode(this.planCode);
        vConstrConstructionsDTO.setStationId(this.stationId);
        vConstrConstructionsDTO.setTransferenceForm(this.transferenceForm);
        vConstrConstructionsDTO.setTransferDate(this.transferDate);
        vConstrConstructionsDTO.setDistrictCode(this.districtCode);
        vConstrConstructionsDTO.setDistrictName(this.districtName);
        vConstrConstructionsDTO.setTransfererName(this.transfererName);
        vConstrConstructionsDTO.setRecipientName(this.recipientName);
        vConstrConstructionsDTO.setAfterAppCost(this.afterAppCost);
        vConstrConstructionsDTO.setApprovalDate(this.approvalDate);
        vConstrConstructionsDTO.setFinalBalanceCostApproval(this.finalBalanceCostApproval);
        vConstrConstructionsDTO.setBalanceDateApproval(this.balanceDateApproval);
        vConstrConstructionsDTO.setFinalBalanceCost(this.finalBalanceCost);
        vConstrConstructionsDTO.setBalanceDate(this.balanceDate);
        vConstrConstructionsDTO.setStationCode(this.stationCode);
        vConstrConstructionsDTO.setStationAddress(this.stationAddress);
        vConstrConstructionsDTO.setStationDescription(this.stationDescription);
        vConstrConstructionsDTO.setProvinceCode(this.provinceCode);
        vConstrConstructionsDTO.setProvinceName(this.provinceName);
        vConstrConstructionsDTO.setStartPointId(this.startPointId);
        vConstrConstructionsDTO.setStatusName(this.statusName);
        vConstrConstructionsDTO.setUpdateProcess(this.updateProcess);
        vConstrConstructionsDTO.setIsApLine(this.isApLine);
        vConstrConstructionsDTO.setNumrow(this.numrow);
        vConstrConstructionsDTO.setConstructId(this.constructId);
        vConstrConstructionsDTO.setConstrtCode(this.constrtCode);
        vConstrConstructionsDTO.setConstrType(this.constrType);
        vConstrConstructionsDTO.setConstrTypeName(this.constrTypeName);
        vConstrConstructionsDTO.setConstrtName(this.constrtName);
        vConstrConstructionsDTO.setConstrtAddress(this.constrtAddress);
        vConstrConstructionsDTO.setConstrtForm(this.constrtForm);
        vConstrConstructionsDTO.setSuperForm(this.superForm);
        vConstrConstructionsDTO.setConstructorId(this.constructorId);
        vConstrConstructionsDTO.setAcceptStartDate(this.acceptStartDate);
        vConstrConstructionsDTO.setAcceptFinishDate(this.acceptFinishDate);
        vConstrConstructionsDTO.setIsAccepted(this.isAccepted);
        vConstrConstructionsDTO.setStatusId(this.statusId);
        vConstrConstructionsDTO.setConstructorName(this.constructorName);
        vConstrConstructionsDTO.setSupervisorId(this.supervisorId);
        vConstrConstructionsDTO.setSupervisorName(this.supervisorName);
        vConstrConstructionsDTO.setStartingDate(this.startingDate);
        vConstrConstructionsDTO.setCompleteDate(this.completeDate);
        vConstrConstructionsDTO.setSuggestorId(this.suggestorId);
        vConstrConstructionsDTO.setSuggestorName(this.suggestorName);
        vConstrConstructionsDTO.setSuggestorGroupId(this.suggestorGroupId);
        vConstrConstructionsDTO.setSuggestorGroupName(this.suggestorGroupName);
        vConstrConstructionsDTO.setSuggestDate(this.suggestDate);
        vConstrConstructionsDTO.setSysUserId(this.sysUserId);
        vConstrConstructionsDTO.setSysUserName(this.sysUserName);
        vConstrConstructionsDTO.setSysGroupId(this.sysGroupId);
        vConstrConstructionsDTO.setSysGroupName(this.sysGroupName);
        vConstrConstructionsDTO.setSysCreatedDate(this.sysCreatedDate);
        vConstrConstructionsDTO.setStatus(this.status);
        vConstrConstructionsDTO.setExpectedCompleteDate(this.expectedCompleteDate);
        vConstrConstructionsDTO.setStartComment(this.startComment);
        vConstrConstructionsDTO.setIsActive(this.isActive);
        vConstrConstructionsDTO.setCatCurrencyId(this.catCurrencyId);
        vConstrConstructionsDTO.setCatCurrencyCode(this.catCurrencyCode);
        vConstrConstructionsDTO.setCatCurrencyName(this.catCurrencyName);
        vConstrConstructionsDTO.setProvinceId(this.provinceId);
        vConstrConstructionsDTO.setDistrictId(this.districtId);
        return vConstrConstructionsDTO;
    }
}
