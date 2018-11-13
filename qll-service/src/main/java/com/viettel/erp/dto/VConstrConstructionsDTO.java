/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.VConstrConstructionsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "V_CONSTR_CONSTRUCTIONSBO")
public class VConstrConstructionsDTO extends BaseFWDTOImpl<VConstrConstructionsBO> {

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

private String currentYear; 

    @Override
    public VConstrConstructionsBO toModel() {
        VConstrConstructionsBO vConstrConstructionsBO = new VConstrConstructionsBO();
        vConstrConstructionsBO.setInvestProjectId(this.investProjectId);
        vConstrConstructionsBO.setInvestProjectName(this.investProjectName);
        vConstrConstructionsBO.setInvestProjectCode(this.investProjectCode);
        vConstrConstructionsBO.setPlanId(this.planId);
        vConstrConstructionsBO.setPlanName(this.planName);
        vConstrConstructionsBO.setPlanCode(this.planCode);
        vConstrConstructionsBO.setStationId(this.stationId);
        vConstrConstructionsBO.setTransferenceForm(this.transferenceForm);
        vConstrConstructionsBO.setTransferDate(this.transferDate);
        vConstrConstructionsBO.setDistrictCode(this.districtCode);
        vConstrConstructionsBO.setDistrictName(this.districtName);
        vConstrConstructionsBO.setTransfererName(this.transfererName);
        vConstrConstructionsBO.setRecipientName(this.recipientName);
        vConstrConstructionsBO.setAfterAppCost(this.afterAppCost);
        vConstrConstructionsBO.setApprovalDate(this.approvalDate);
        vConstrConstructionsBO.setFinalBalanceCostApproval(this.finalBalanceCostApproval);
        vConstrConstructionsBO.setBalanceDateApproval(this.balanceDateApproval);
        vConstrConstructionsBO.setFinalBalanceCost(this.finalBalanceCost);
        vConstrConstructionsBO.setBalanceDate(this.balanceDate);
        vConstrConstructionsBO.setStationCode(this.stationCode);
        vConstrConstructionsBO.setStationAddress(this.stationAddress);
        vConstrConstructionsBO.setStationDescription(this.stationDescription);
        vConstrConstructionsBO.setProvinceCode(this.provinceCode);
        vConstrConstructionsBO.setProvinceName(this.provinceName);
        vConstrConstructionsBO.setStartPointId(this.startPointId);
        vConstrConstructionsBO.setStatusName(this.statusName);
        vConstrConstructionsBO.setUpdateProcess(this.updateProcess);
        vConstrConstructionsBO.setIsApLine(this.isApLine);
        vConstrConstructionsBO.setNumrow(this.numrow);
        vConstrConstructionsBO.setConstructId(this.constructId);
        vConstrConstructionsBO.setConstrtCode(this.constrtCode);
        vConstrConstructionsBO.setConstrType(this.constrType);
        vConstrConstructionsBO.setConstrTypeName(this.constrTypeName);
        vConstrConstructionsBO.setConstrtName(this.constrtName);
        vConstrConstructionsBO.setConstrtAddress(this.constrtAddress);
        vConstrConstructionsBO.setConstrtForm(this.constrtForm);
        vConstrConstructionsBO.setSuperForm(this.superForm);
        vConstrConstructionsBO.setConstructorId(this.constructorId);
        vConstrConstructionsBO.setAcceptStartDate(this.acceptStartDate);
        vConstrConstructionsBO.setAcceptFinishDate(this.acceptFinishDate);
        vConstrConstructionsBO.setIsAccepted(this.isAccepted);
        vConstrConstructionsBO.setStatusId(this.statusId);
        vConstrConstructionsBO.setConstructorName(this.constructorName);
        vConstrConstructionsBO.setSupervisorId(this.supervisorId);
        vConstrConstructionsBO.setSupervisorName(this.supervisorName);
        vConstrConstructionsBO.setStartingDate(this.startingDate);
        vConstrConstructionsBO.setCompleteDate(this.completeDate);
        vConstrConstructionsBO.setSuggestorId(this.suggestorId);
        vConstrConstructionsBO.setSuggestorName(this.suggestorName);
        vConstrConstructionsBO.setSuggestorGroupId(this.suggestorGroupId);
        vConstrConstructionsBO.setSuggestorGroupName(this.suggestorGroupName);
        vConstrConstructionsBO.setSuggestDate(this.suggestDate);
        vConstrConstructionsBO.setSysUserId(this.sysUserId);
        vConstrConstructionsBO.setSysUserName(this.sysUserName);
        vConstrConstructionsBO.setSysGroupId(this.sysGroupId);
        vConstrConstructionsBO.setSysGroupName(this.sysGroupName);
        vConstrConstructionsBO.setSysCreatedDate(this.sysCreatedDate);
        vConstrConstructionsBO.setStatus(this.status);
        vConstrConstructionsBO.setExpectedCompleteDate(this.expectedCompleteDate);
        vConstrConstructionsBO.setStartComment(this.startComment);
        vConstrConstructionsBO.setIsActive(this.isActive);
        vConstrConstructionsBO.setCatCurrencyId(this.catCurrencyId);
        vConstrConstructionsBO.setCatCurrencyCode(this.catCurrencyCode);
        vConstrConstructionsBO.setCatCurrencyName(this.catCurrencyName);
        vConstrConstructionsBO.setProvinceId(this.provinceId);
        vConstrConstructionsBO.setDistrictId(this.districtId);
        return vConstrConstructionsBO;
    }

    public java.lang.Long getInvestProjectId(){
    return investProjectId;
    }
    public void setInvestProjectId(java.lang.Long investProjectId)
    {
    this.investProjectId = investProjectId;
    }
    
    public java.lang.String getInvestProjectName(){
    return investProjectName;
    }
    public void setInvestProjectName(java.lang.String investProjectName)
    {
    this.investProjectName = investProjectName;
    }
    
    public java.lang.String getInvestProjectCode(){
    return investProjectCode;
    }
    public void setInvestProjectCode(java.lang.String investProjectCode)
    {
    this.investProjectCode = investProjectCode;
    }
    
    public java.lang.Long getPlanId(){
    return planId;
    }
    public void setPlanId(java.lang.Long planId)
    {
    this.planId = planId;
    }
    
    public java.lang.String getPlanName(){
    return planName;
    }
    public void setPlanName(java.lang.String planName)
    {
    this.planName = planName;
    }
    
    public java.lang.String getPlanCode(){
    return planCode;
    }
    public void setPlanCode(java.lang.String planCode)
    {
    this.planCode = planCode;
    }
    
    public java.lang.Long getStationId(){
    return stationId;
    }
    public void setStationId(java.lang.Long stationId)
    {
    this.stationId = stationId;
    }
    
    public java.lang.Long getTransferenceForm(){
    return transferenceForm;
    }
    public void setTransferenceForm(java.lang.Long transferenceForm)
    {
    this.transferenceForm = transferenceForm;
    }
    
    public java.util.Date getTransferDate(){
    return transferDate;
    }
    public void setTransferDate(java.util.Date transferDate)
    {
    this.transferDate = transferDate;
    }
    
    public java.lang.String getDistrictCode(){
    return districtCode;
    }
    public void setDistrictCode(java.lang.String districtCode)
    {
    this.districtCode = districtCode;
    }
    
    public java.lang.String getDistrictName(){
    return districtName;
    }
    public void setDistrictName(java.lang.String districtName)
    {
    this.districtName = districtName;
    }
    
    public java.lang.String getTransfererName(){
    return transfererName;
    }
    public void setTransfererName(java.lang.String transfererName)
    {
    this.transfererName = transfererName;
    }
    
    public java.lang.String getRecipientName(){
    return recipientName;
    }
    public void setRecipientName(java.lang.String recipientName)
    {
    this.recipientName = recipientName;
    }
    
    public java.lang.Long getAfterAppCost(){
    return afterAppCost;
    }
    public void setAfterAppCost(java.lang.Long afterAppCost)
    {
    this.afterAppCost = afterAppCost;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getFinalBalanceCostApproval(){
    return finalBalanceCostApproval;
    }
    public void setFinalBalanceCostApproval(java.lang.Long finalBalanceCostApproval)
    {
    this.finalBalanceCostApproval = finalBalanceCostApproval;
    }
    
    public java.util.Date getBalanceDateApproval(){
    return balanceDateApproval;
    }
    public void setBalanceDateApproval(java.util.Date balanceDateApproval)
    {
    this.balanceDateApproval = balanceDateApproval;
    }
    
    public java.lang.Long getFinalBalanceCost(){
    return finalBalanceCost;
    }
    public void setFinalBalanceCost(java.lang.Long finalBalanceCost)
    {
    this.finalBalanceCost = finalBalanceCost;
    }
    
    public java.util.Date getBalanceDate(){
    return balanceDate;
    }
    public void setBalanceDate(java.util.Date balanceDate)
    {
    this.balanceDate = balanceDate;
    }
    
    public java.lang.String getStationCode(){
    return stationCode;
    }
    public void setStationCode(java.lang.String stationCode)
    {
    this.stationCode = stationCode;
    }
    
    public java.lang.String getStationAddress(){
    return stationAddress;
    }
    public void setStationAddress(java.lang.String stationAddress)
    {
    this.stationAddress = stationAddress;
    }
    
    public java.lang.String getStationDescription(){
    return stationDescription;
    }
    public void setStationDescription(java.lang.String stationDescription)
    {
    this.stationDescription = stationDescription;
    }
    
    public java.lang.String getProvinceCode(){
    return provinceCode;
    }
    public void setProvinceCode(java.lang.String provinceCode)
    {
    this.provinceCode = provinceCode;
    }
    
    public java.lang.String getProvinceName(){
    return provinceName;
    }
    public void setProvinceName(java.lang.String provinceName)
    {
    this.provinceName = provinceName;
    }
    
    public java.lang.Long getStartPointId(){
    return startPointId;
    }
    public void setStartPointId(java.lang.Long startPointId)
    {
    this.startPointId = startPointId;
    }
    
    public java.lang.String getStatusName(){
    return statusName;
    }
    public void setStatusName(java.lang.String statusName)
    {
    this.statusName = statusName;
    }
    
    public java.lang.Long getUpdateProcess(){
    return updateProcess;
    }
    public void setUpdateProcess(java.lang.Long updateProcess)
    {
    this.updateProcess = updateProcess;
    }
    
    public java.lang.Long getIsApLine(){
    return isApLine;
    }
    public void setIsApLine(java.lang.Long isApLine)
    {
    this.isApLine = isApLine;
    }
    
    public java.lang.Long getNumrow(){
    return numrow;
    }
    public void setNumrow(java.lang.Long numrow)
    {
    this.numrow = numrow;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.String getConstrtCode(){
    return constrtCode;
    }
    public void setConstrtCode(java.lang.String constrtCode)
    {
    this.constrtCode = constrtCode;
    }
    
    public java.lang.Long getConstrType(){
    return constrType;
    }
    public void setConstrType(java.lang.Long constrType)
    {
    this.constrType = constrType;
    }
    
    public java.lang.String getConstrTypeName(){
    return constrTypeName;
    }
    public void setConstrTypeName(java.lang.String constrTypeName)
    {
    this.constrTypeName = constrTypeName;
    }
    
    public java.lang.String getConstrtName(){
    return constrtName;
    }
    public void setConstrtName(java.lang.String constrtName)
    {
    this.constrtName = constrtName;
    }
    
    public java.lang.String getConstrtAddress(){
    return constrtAddress;
    }
    public void setConstrtAddress(java.lang.String constrtAddress)
    {
    this.constrtAddress = constrtAddress;
    }
    
    public java.lang.Long getConstrtForm(){
    return constrtForm;
    }
    public void setConstrtForm(java.lang.Long constrtForm)
    {
    this.constrtForm = constrtForm;
    }
    
    public java.lang.Long getSuperForm(){
    return superForm;
    }
    public void setSuperForm(java.lang.Long superForm)
    {
    this.superForm = superForm;
    }
    
    public java.lang.Long getConstructorId(){
    return constructorId;
    }
    public void setConstructorId(java.lang.Long constructorId)
    {
    this.constructorId = constructorId;
    }
    
    public java.util.Date getAcceptStartDate(){
    return acceptStartDate;
    }
    public void setAcceptStartDate(java.util.Date acceptStartDate)
    {
    this.acceptStartDate = acceptStartDate;
    }
    
    public java.util.Date getAcceptFinishDate(){
    return acceptFinishDate;
    }
    public void setAcceptFinishDate(java.util.Date acceptFinishDate)
    {
    this.acceptFinishDate = acceptFinishDate;
    }
    
    public java.lang.Long getIsAccepted(){
    return isAccepted;
    }
    public void setIsAccepted(java.lang.Long isAccepted)
    {
    this.isAccepted = isAccepted;
    }
    
    public java.lang.Long getStatusId(){
    return statusId;
    }
    public void setStatusId(java.lang.Long statusId)
    {
    this.statusId = statusId;
    }
    
    public java.lang.String getConstructorName(){
    return constructorName;
    }
    public void setConstructorName(java.lang.String constructorName)
    {
    this.constructorName = constructorName;
    }
    
    public java.lang.Long getSupervisorId(){
    return supervisorId;
    }
    public void setSupervisorId(java.lang.Long supervisorId)
    {
    this.supervisorId = supervisorId;
    }
    
    public java.lang.String getSupervisorName(){
    return supervisorName;
    }
    public void setSupervisorName(java.lang.String supervisorName)
    {
    this.supervisorName = supervisorName;
    }
    
    public java.util.Date getStartingDate(){
    return startingDate;
    }
    public void setStartingDate(java.util.Date startingDate)
    {
    this.startingDate = startingDate;
    }
    
    public java.util.Date getCompleteDate(){
    return completeDate;
    }
    public void setCompleteDate(java.util.Date completeDate)
    {
    this.completeDate = completeDate;
    }
    
    public java.lang.Long getSuggestorId(){
    return suggestorId;
    }
    public void setSuggestorId(java.lang.Long suggestorId)
    {
    this.suggestorId = suggestorId;
    }
    
    public java.lang.String getSuggestorName(){
    return suggestorName;
    }
    public void setSuggestorName(java.lang.String suggestorName)
    {
    this.suggestorName = suggestorName;
    }
    
    public java.lang.Long getSuggestorGroupId(){
    return suggestorGroupId;
    }
    public void setSuggestorGroupId(java.lang.Long suggestorGroupId)
    {
    this.suggestorGroupId = suggestorGroupId;
    }
    
    public java.lang.String getSuggestorGroupName(){
    return suggestorGroupName;
    }
    public void setSuggestorGroupName(java.lang.String suggestorGroupName)
    {
    this.suggestorGroupName = suggestorGroupName;
    }
    
    public java.util.Date getSuggestDate(){
    return suggestDate;
    }
    public void setSuggestDate(java.util.Date suggestDate)
    {
    this.suggestDate = suggestDate;
    }
    
    public java.lang.Long getSysUserId(){
    return sysUserId;
    }
    public void setSysUserId(java.lang.Long sysUserId)
    {
    this.sysUserId = sysUserId;
    }
    
    public java.lang.String getSysUserName(){
    return sysUserName;
    }
    public void setSysUserName(java.lang.String sysUserName)
    {
    this.sysUserName = sysUserName;
    }
    
    public java.lang.Long getSysGroupId(){
    return sysGroupId;
    }
    public void setSysGroupId(java.lang.Long sysGroupId)
    {
    this.sysGroupId = sysGroupId;
    }
    
    public java.lang.String getSysGroupName(){
    return sysGroupName;
    }
    public void setSysGroupName(java.lang.String sysGroupName)
    {
    this.sysGroupName = sysGroupName;
    }
    
    public java.util.Date getSysCreatedDate(){
    return sysCreatedDate;
    }
    public void setSysCreatedDate(java.util.Date sysCreatedDate)
    {
    this.sysCreatedDate = sysCreatedDate;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.util.Date getExpectedCompleteDate(){
    return expectedCompleteDate;
    }
    public void setExpectedCompleteDate(java.util.Date expectedCompleteDate)
    {
    this.expectedCompleteDate = expectedCompleteDate;
    }
    
    public java.lang.String getStartComment(){
    return startComment;
    }
    public void setStartComment(java.lang.String startComment)
    {
    this.startComment = startComment;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getCatCurrencyId(){
    return catCurrencyId;
    }
    public void setCatCurrencyId(java.lang.Long catCurrencyId)
    {
    this.catCurrencyId = catCurrencyId;
    }
    
    public java.lang.String getCatCurrencyCode(){
    return catCurrencyCode;
    }
    public void setCatCurrencyCode(java.lang.String catCurrencyCode)
    {
    this.catCurrencyCode = catCurrencyCode;
    }
    
    public java.lang.String getCatCurrencyName(){
    return catCurrencyName;
    }
    public void setCatCurrencyName(java.lang.String catCurrencyName)
    {
    this.catCurrencyName = catCurrencyName;
    }
    
    public java.lang.Long getProvinceId(){
    return provinceId;
    }
    public void setProvinceId(java.lang.Long provinceId)
    {
    this.provinceId = provinceId;
    }
    
    public java.lang.Long getDistrictId(){
    return districtId;
    }
    public void setDistrictId(java.lang.Long districtId)
    {
    this.districtId = districtId;
    }

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return constructId;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		 return getConstructId().toString();
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
    
   
}
