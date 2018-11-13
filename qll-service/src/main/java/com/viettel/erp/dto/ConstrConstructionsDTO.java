/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_CONSTRUCTIONSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrConstructionsDTO extends BaseFWDTOImpl<ConstrConstructionsBO> {

private java.lang.Long hiringMonitorConsult;
private java.lang.Long constructId;
private java.lang.Long constrType;
private java.lang.String constrtCode;
private java.lang.String constrtName;
private java.lang.String constrtAddress;
private java.lang.Long constrtForm;
private java.lang.Long superForm;
private java.lang.Long constructorId;
private java.lang.Long supervisorId;
private java.util.Date startingDate;
private java.util.Date completeDate;
private java.lang.Long suggestorId;
private java.lang.Long suggestorGroupId;
private java.util.Date suggestDate;
private java.lang.Long sysUserId;
private java.lang.Long sysGroupId;
private java.util.Date sysCreatedDate;
private java.lang.Long investProjectId;
private java.lang.Long catCurrencyId;
private java.util.Date expectedCompleteDate;
private java.lang.String startComment;
private java.lang.Double projectInvestComponentId;
private java.lang.Long isActive;
private java.lang.Long provinceId;
private java.lang.Long districtId;
private java.lang.Long stationId;
private java.lang.Long startBy;
private java.lang.Long updateStatusBy;
private java.util.Date acceptStartDate;
private java.util.Date acceptFinishDate;
private java.lang.Long planId;
private java.util.Date addTime;
private java.lang.Long statusId;
private java.lang.Long isObstructed;
private java.util.Date landTransferedDate;
private java.util.Date integratedDate;
private java.util.Date emissionDate;
private java.lang.Long isAccepted;
private java.util.Date preStartingDate;
private java.lang.Long status;
private java.lang.String constructorPerson;
private java.lang.String constructorPhone;
private java.lang.String supervisorPerson;
private java.lang.String supervisorPhone;
private java.lang.Long procedure;
private java.util.Date procedureDate;
private java.lang.String procedureComment;
private java.lang.Long vibaFirst;
private java.util.Date handoverDate;
private java.lang.String handoverComment;
private java.lang.Long lineMode;
private java.lang.String completeComment;
private java.util.Date lastUpdateProgress;
private java.lang.Long projectType;
private java.lang.Long pillarProgressPercent;
private java.lang.Long cableProgressPercent;
private java.lang.Long timePercent;
private java.lang.Long progressStatus;
private java.lang.Long merStatus;
private java.lang.String description;
private java.lang.String slowCause;
private java.lang.Long scope;
private java.lang.Long lineType;
private java.lang.Long cableTypeId;
private java.lang.Long surveyId;
private java.lang.Long lineLength;
private java.lang.Long cableMerPercent;
private java.lang.Long pillarMerPercent;
private java.lang.Long executeGroup;
private java.lang.String executer;
private java.lang.String executePhone;
private java.lang.Long isInventory;
private java.lang.String pmPer;
private java.lang.String cmPer;
private java.lang.Long apConstrTypeId;
private java.lang.Long subApConstrTypeId;
private java.util.Date migrateDate;
private java.lang.Long catApProjectId;
private java.lang.Long adslNum;
private java.lang.Long pstnNum;
private java.lang.Long constrTypeId;
private java.lang.Long catProjectId;
private java.lang.Long updateProcess;
private java.lang.Long acceptStatusLost;
private java.lang.Long isApLine;
private java.util.Date serviceProvideDate;
private java.lang.Long finishTest;
private java.lang.Long percentFinish;
private java.lang.Long preparedPremises;
private java.lang.Long handoverMaterials;
private java.lang.Long progressCommitment;
private java.lang.Long isHcqt;
private java.lang.Long isOff;
private java.lang.Long isAcceptedContract;
private java.lang.Long constrLineType;
private java.lang.Long lineLength1;
private java.lang.Long isFee;
private java.util.Date onDate;
private java.lang.Long updatorId;
private java.lang.String contractCode;
private java.util.Date signDate;
private java.lang.String constrTypeName;
private java.lang.String provinceName;
private java.lang.String partnerName;
private java.lang.String stationCode;





	@Override
    public ConstrConstructionsBO toModel() {
        ConstrConstructionsBO constrConstructionsBO = new ConstrConstructionsBO();
        constrConstructionsBO.setHiringMonitorConsult(this.hiringMonitorConsult);
        constrConstructionsBO.setConstructId(this.constructId);
        constrConstructionsBO.setConstrType(this.constrType);
        constrConstructionsBO.setConstrtCode(this.constrtCode);
        constrConstructionsBO.setConstrtName(this.constrtName);
        constrConstructionsBO.setConstrtAddress(this.constrtAddress);
        constrConstructionsBO.setConstrtForm(this.constrtForm);
        constrConstructionsBO.setSuperForm(this.superForm);
        constrConstructionsBO.setConstructorId(this.constructorId);
        constrConstructionsBO.setSupervisorId(this.supervisorId);
        constrConstructionsBO.setStartingDate(this.startingDate);
        constrConstructionsBO.setCompleteDate(this.completeDate);
        constrConstructionsBO.setSuggestorId(this.suggestorId);
        constrConstructionsBO.setSuggestorGroupId(this.suggestorGroupId);
        constrConstructionsBO.setSuggestDate(this.suggestDate);
        constrConstructionsBO.setSysUserId(this.sysUserId);
        constrConstructionsBO.setSysGroupId(this.sysGroupId);
        constrConstructionsBO.setSysCreatedDate(this.sysCreatedDate);
        constrConstructionsBO.setInvestProjectId(this.investProjectId);
        constrConstructionsBO.setCatCurrencyId(this.catCurrencyId);
        constrConstructionsBO.setExpectedCompleteDate(this.expectedCompleteDate);
        constrConstructionsBO.setStartComment(this.startComment);
        constrConstructionsBO.setProjectInvestComponentId(this.projectInvestComponentId);
        constrConstructionsBO.setIsActive(this.isActive);
        constrConstructionsBO.setProvinceId(this.provinceId);
        constrConstructionsBO.setDistrictId(this.districtId);
        constrConstructionsBO.setStationId(this.stationId);
        constrConstructionsBO.setStartBy(this.startBy);
        constrConstructionsBO.setUpdateStatusBy(this.updateStatusBy);
        constrConstructionsBO.setAcceptStartDate(this.acceptStartDate);
        constrConstructionsBO.setAcceptFinishDate(this.acceptFinishDate);
        constrConstructionsBO.setPlanId(this.planId);
        constrConstructionsBO.setAddTime(this.addTime);
        constrConstructionsBO.setStatusId(this.statusId);
        constrConstructionsBO.setIsObstructed(this.isObstructed);
        constrConstructionsBO.setLandTransferedDate(this.landTransferedDate);
        constrConstructionsBO.setIntegratedDate(this.integratedDate);
        constrConstructionsBO.setEmissionDate(this.emissionDate);
        constrConstructionsBO.setIsAccepted(this.isAccepted);
        constrConstructionsBO.setPreStartingDate(this.preStartingDate);
        constrConstructionsBO.setStatus(this.status);
        constrConstructionsBO.setConstructorPerson(this.constructorPerson);
        constrConstructionsBO.setConstructorPhone(this.constructorPhone);
        constrConstructionsBO.setSupervisorPerson(this.supervisorPerson);
        constrConstructionsBO.setSupervisorPhone(this.supervisorPhone);
        constrConstructionsBO.setProcedure(this.procedure);
        constrConstructionsBO.setProcedureDate(this.procedureDate);
        constrConstructionsBO.setProcedureComment(this.procedureComment);
        constrConstructionsBO.setVibaFirst(this.vibaFirst);
        constrConstructionsBO.setHandoverDate(this.handoverDate);
        constrConstructionsBO.setHandoverComment(this.handoverComment);
        constrConstructionsBO.setLineMode(this.lineMode);
        constrConstructionsBO.setCompleteComment(this.completeComment);
        constrConstructionsBO.setLastUpdateProgress(this.lastUpdateProgress);
        constrConstructionsBO.setProjectType(this.projectType);
        constrConstructionsBO.setPillarProgressPercent(this.pillarProgressPercent);
        constrConstructionsBO.setCableProgressPercent(this.cableProgressPercent);
        constrConstructionsBO.setTimePercent(this.timePercent);
        constrConstructionsBO.setProgressStatus(this.progressStatus);
        constrConstructionsBO.setMerStatus(this.merStatus);
        constrConstructionsBO.setDescription(this.description);
        constrConstructionsBO.setSlowCause(this.slowCause);
        constrConstructionsBO.setScope(this.scope);
        constrConstructionsBO.setLineType(this.lineType);
        constrConstructionsBO.setCableTypeId(this.cableTypeId);
        constrConstructionsBO.setSurveyId(this.surveyId);
        constrConstructionsBO.setLineLength(this.lineLength);
        constrConstructionsBO.setCableMerPercent(this.cableMerPercent);
        constrConstructionsBO.setPillarMerPercent(this.pillarMerPercent);
        constrConstructionsBO.setExecuteGroup(this.executeGroup);
        constrConstructionsBO.setExecuter(this.executer);
        constrConstructionsBO.setExecutePhone(this.executePhone);
        constrConstructionsBO.setIsInventory(this.isInventory);
        constrConstructionsBO.setPmPer(this.pmPer);
        constrConstructionsBO.setCmPer(this.cmPer);
        constrConstructionsBO.setApConstrTypeId(this.apConstrTypeId);
        constrConstructionsBO.setSubApConstrTypeId(this.subApConstrTypeId);
        constrConstructionsBO.setMigrateDate(this.migrateDate);
        constrConstructionsBO.setCatApProjectId(this.catApProjectId);
        constrConstructionsBO.setAdslNum(this.adslNum);
        constrConstructionsBO.setPstnNum(this.pstnNum);
        constrConstructionsBO.setConstrTypeId(this.constrTypeId);
        constrConstructionsBO.setCatProjectId(this.catProjectId);
        constrConstructionsBO.setUpdateProcess(this.updateProcess);
        constrConstructionsBO.setAcceptStatusLost(this.acceptStatusLost);
        constrConstructionsBO.setIsApLine(this.isApLine);
        constrConstructionsBO.setServiceProvideDate(this.serviceProvideDate);
        constrConstructionsBO.setFinishTest(this.finishTest);
        constrConstructionsBO.setPercentFinish(this.percentFinish);
        constrConstructionsBO.setPreparedPremises(this.preparedPremises);
        constrConstructionsBO.setHandoverMaterials(this.handoverMaterials);
        constrConstructionsBO.setProgressCommitment(this.progressCommitment);
        constrConstructionsBO.setIsHcqt(this.isHcqt);
        constrConstructionsBO.setIsOff(this.isOff);
        constrConstructionsBO.setIsAcceptedContract(this.isAcceptedContract);
        constrConstructionsBO.setConstrLineType(this.constrLineType);
      //  constrConstructionsBO.setLineLength1(this.lineLength1);
     //  constrConstructionsBO.setIsFee(this.isFee);
        constrConstructionsBO.setOnDate(this.onDate);
        constrConstructionsBO.setUpdatorId(this.updatorId);
        return constrConstructionsBO;
    }

    public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.Long getHiringMonitorConsult(){
    return hiringMonitorConsult;
    }
    public void setHiringMonitorConsult(java.lang.Long hiringMonitorConsult)
    {
    this.hiringMonitorConsult = hiringMonitorConsult;
    }
    
    @Override
     public Long getFWModelId() {
        return constructId;
    }
   
    @Override
    public String catchName() {
        return getConstructId().toString();
    }
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.Long getConstrType(){
    return constrType;
    }
    public void setConstrType(java.lang.Long constrType)
    {
    this.constrType = constrType;
    }
    
    public java.lang.String getConstrtCode(){
    return constrtCode;
    }
    public void setConstrtCode(java.lang.String constrtCode)
    {
    this.constrtCode = constrtCode;
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
    
    public java.lang.Long getSupervisorId(){
    return supervisorId;
    }
    public void setSupervisorId(java.lang.Long supervisorId)
    {
    this.supervisorId = supervisorId;
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
    
    public java.lang.Long getSuggestorGroupId(){
    return suggestorGroupId;
    }
    public void setSuggestorGroupId(java.lang.Long suggestorGroupId)
    {
    this.suggestorGroupId = suggestorGroupId;
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
    
    public java.lang.Long getSysGroupId(){
    return sysGroupId;
    }
    public void setSysGroupId(java.lang.Long sysGroupId)
    {
    this.sysGroupId = sysGroupId;
    }
    
    public java.util.Date getSysCreatedDate(){
    return sysCreatedDate;
    }
    public void setSysCreatedDate(java.util.Date sysCreatedDate)
    {
    this.sysCreatedDate = sysCreatedDate;
    }
    
    public java.lang.Long getInvestProjectId(){
    return investProjectId;
    }
    public void setInvestProjectId(java.lang.Long investProjectId)
    {
    this.investProjectId = investProjectId;
    }
    
    public java.lang.Long getCatCurrencyId(){
    return catCurrencyId;
    }
    public void setCatCurrencyId(java.lang.Long catCurrencyId)
    {
    this.catCurrencyId = catCurrencyId;
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
    
    public java.lang.Double getProjectInvestComponentId(){
    return projectInvestComponentId;
    }
    public void setProjectInvestComponentId(java.lang.Double projectInvestComponentId)
    {
    this.projectInvestComponentId = projectInvestComponentId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
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
    
    public java.lang.Long getStationId(){
    return stationId;
    }
    public void setStationId(java.lang.Long stationId)
    {
    this.stationId = stationId;
    }
    
    public java.lang.Long getStartBy(){
    return startBy;
    }
    public void setStartBy(java.lang.Long startBy)
    {
    this.startBy = startBy;
    }
    
    public java.lang.Long getUpdateStatusBy(){
    return updateStatusBy;
    }
    public void setUpdateStatusBy(java.lang.Long updateStatusBy)
    {
    this.updateStatusBy = updateStatusBy;
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
    
    public java.lang.Long getPlanId(){
    return planId;
    }
    public void setPlanId(java.lang.Long planId)
    {
    this.planId = planId;
    }
    
    public java.util.Date getAddTime(){
    return addTime;
    }
    public void setAddTime(java.util.Date addTime)
    {
    this.addTime = addTime;
    }
    
    public java.lang.Long getStatusId(){
    return statusId;
    }
    public void setStatusId(java.lang.Long statusId)
    {
    this.statusId = statusId;
    }
    
    public java.lang.Long getIsObstructed(){
    return isObstructed;
    }
    public void setIsObstructed(java.lang.Long isObstructed)
    {
    this.isObstructed = isObstructed;
    }
    
    public java.util.Date getLandTransferedDate(){
    return landTransferedDate;
    }
    public void setLandTransferedDate(java.util.Date landTransferedDate)
    {
    this.landTransferedDate = landTransferedDate;
    }
    
    public java.util.Date getIntegratedDate(){
    return integratedDate;
    }
    public void setIntegratedDate(java.util.Date integratedDate)
    {
    this.integratedDate = integratedDate;
    }
    
    public java.util.Date getEmissionDate(){
    return emissionDate;
    }
    public void setEmissionDate(java.util.Date emissionDate)
    {
    this.emissionDate = emissionDate;
    }
    
    public java.lang.Long getIsAccepted(){
    return isAccepted;
    }
    public void setIsAccepted(java.lang.Long isAccepted)
    {
    this.isAccepted = isAccepted;
    }
    
    public java.util.Date getPreStartingDate(){
    return preStartingDate;
    }
    public void setPreStartingDate(java.util.Date preStartingDate)
    {
    this.preStartingDate = preStartingDate;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.String getConstructorPerson(){
    return constructorPerson;
    }
    public void setConstructorPerson(java.lang.String constructorPerson)
    {
    this.constructorPerson = constructorPerson;
    }
    
    public java.lang.String getConstructorPhone(){
    return constructorPhone;
    }
    public void setConstructorPhone(java.lang.String constructorPhone)
    {
    this.constructorPhone = constructorPhone;
    }
    
    public java.lang.String getSupervisorPerson(){
    return supervisorPerson;
    }
    public void setSupervisorPerson(java.lang.String supervisorPerson)
    {
    this.supervisorPerson = supervisorPerson;
    }
    
    public java.lang.String getSupervisorPhone(){
    return supervisorPhone;
    }
    public void setSupervisorPhone(java.lang.String supervisorPhone)
    {
    this.supervisorPhone = supervisorPhone;
    }
    
    public java.lang.Long getProcedure(){
    return procedure;
    }
    public void setProcedure(java.lang.Long procedure)
    {
    this.procedure = procedure;
    }
    
    public java.util.Date getProcedureDate(){
    return procedureDate;
    }
    public void setProcedureDate(java.util.Date procedureDate)
    {
    this.procedureDate = procedureDate;
    }
    
    public java.lang.String getProcedureComment(){
    return procedureComment;
    }
    public void setProcedureComment(java.lang.String procedureComment)
    {
    this.procedureComment = procedureComment;
    }
    
    public java.lang.Long getVibaFirst(){
    return vibaFirst;
    }
    public void setVibaFirst(java.lang.Long vibaFirst)
    {
    this.vibaFirst = vibaFirst;
    }
    
    public java.util.Date getHandoverDate(){
    return handoverDate;
    }
    public void setHandoverDate(java.util.Date handoverDate)
    {
    this.handoverDate = handoverDate;
    }
    
    public java.lang.String getHandoverComment(){
    return handoverComment;
    }
    public void setHandoverComment(java.lang.String handoverComment)
    {
    this.handoverComment = handoverComment;
    }
    
    public java.lang.Long getLineMode(){
    return lineMode;
    }
    public void setLineMode(java.lang.Long lineMode)
    {
    this.lineMode = lineMode;
    }
    
    public java.lang.String getCompleteComment(){
    return completeComment;
    }
    public void setCompleteComment(java.lang.String completeComment)
    {
    this.completeComment = completeComment;
    }
    
    public java.util.Date getLastUpdateProgress(){
    return lastUpdateProgress;
    }
    public void setLastUpdateProgress(java.util.Date lastUpdateProgress)
    {
    this.lastUpdateProgress = lastUpdateProgress;
    }
    
    public java.lang.Long getProjectType(){
    return projectType;
    }
    public void setProjectType(java.lang.Long projectType)
    {
    this.projectType = projectType;
    }
    
    public java.lang.Long getPillarProgressPercent(){
    return pillarProgressPercent;
    }
    public void setPillarProgressPercent(java.lang.Long pillarProgressPercent)
    {
    this.pillarProgressPercent = pillarProgressPercent;
    }
    
    public java.lang.Long getCableProgressPercent(){
    return cableProgressPercent;
    }
    public void setCableProgressPercent(java.lang.Long cableProgressPercent)
    {
    this.cableProgressPercent = cableProgressPercent;
    }
    
    public java.lang.Long getTimePercent(){
    return timePercent;
    }
    public void setTimePercent(java.lang.Long timePercent)
    {
    this.timePercent = timePercent;
    }
    
    public java.lang.Long getProgressStatus(){
    return progressStatus;
    }
    public void setProgressStatus(java.lang.Long progressStatus)
    {
    this.progressStatus = progressStatus;
    }
    
    public java.lang.Long getMerStatus(){
    return merStatus;
    }
    public void setMerStatus(java.lang.Long merStatus)
    {
    this.merStatus = merStatus;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.String getSlowCause(){
    return slowCause;
    }
    public void setSlowCause(java.lang.String slowCause)
    {
    this.slowCause = slowCause;
    }
    
    public java.lang.Long getScope(){
    return scope;
    }
    public void setScope(java.lang.Long scope)
    {
    this.scope = scope;
    }
    
    public java.lang.Long getLineType(){
    return lineType;
    }
    public void setLineType(java.lang.Long lineType)
    {
    this.lineType = lineType;
    }
    
    public java.lang.Long getCableTypeId(){
    return cableTypeId;
    }
    public void setCableTypeId(java.lang.Long cableTypeId)
    {
    this.cableTypeId = cableTypeId;
    }
    
    public java.lang.Long getSurveyId(){
    return surveyId;
    }
    public void setSurveyId(java.lang.Long surveyId)
    {
    this.surveyId = surveyId;
    }
    
    public java.lang.Long getLineLength(){
    return lineLength;
    }
    public void setLineLength(java.lang.Long lineLength)
    {
    this.lineLength = lineLength;
    }
    
    public java.lang.Long getCableMerPercent(){
    return cableMerPercent;
    }
    public void setCableMerPercent(java.lang.Long cableMerPercent)
    {
    this.cableMerPercent = cableMerPercent;
    }
    
    public java.lang.Long getPillarMerPercent(){
    return pillarMerPercent;
    }
    public void setPillarMerPercent(java.lang.Long pillarMerPercent)
    {
    this.pillarMerPercent = pillarMerPercent;
    }
    
    public java.lang.Long getExecuteGroup(){
    return executeGroup;
    }
    public void setExecuteGroup(java.lang.Long executeGroup)
    {
    this.executeGroup = executeGroup;
    }
    
    public java.lang.String getExecuter(){
    return executer;
    }
    public void setExecuter(java.lang.String executer)
    {
    this.executer = executer;
    }
    
    public java.lang.String getExecutePhone(){
    return executePhone;
    }
    public void setExecutePhone(java.lang.String executePhone)
    {
    this.executePhone = executePhone;
    }
    
    public java.lang.Long getIsInventory(){
    return isInventory;
    }
    public void setIsInventory(java.lang.Long isInventory)
    {
    this.isInventory = isInventory;
    }
    
    public java.lang.String getPmPer(){
    return pmPer;
    }
    public void setPmPer(java.lang.String pmPer)
    {
    this.pmPer = pmPer;
    }
    
    public java.lang.String getCmPer(){
    return cmPer;
    }
    public void setCmPer(java.lang.String cmPer)
    {
    this.cmPer = cmPer;
    }
    
    public java.lang.Long getApConstrTypeId(){
    return apConstrTypeId;
    }
    public void setApConstrTypeId(java.lang.Long apConstrTypeId)
    {
    this.apConstrTypeId = apConstrTypeId;
    }
    
    public java.lang.Long getSubApConstrTypeId(){
    return subApConstrTypeId;
    }
    public void setSubApConstrTypeId(java.lang.Long subApConstrTypeId)
    {
    this.subApConstrTypeId = subApConstrTypeId;
    }
    
    public java.util.Date getMigrateDate(){
    return migrateDate;
    }
    public void setMigrateDate(java.util.Date migrateDate)
    {
    this.migrateDate = migrateDate;
    }
    
    public java.lang.Long getCatApProjectId(){
    return catApProjectId;
    }
    public void setCatApProjectId(java.lang.Long catApProjectId)
    {
    this.catApProjectId = catApProjectId;
    }
    
    public java.lang.Long getAdslNum(){
    return adslNum;
    }
    public void setAdslNum(java.lang.Long adslNum)
    {
    this.adslNum = adslNum;
    }
    
    public java.lang.Long getPstnNum(){
    return pstnNum;
    }
    public void setPstnNum(java.lang.Long pstnNum)
    {
    this.pstnNum = pstnNum;
    }
    
    public java.lang.Long getConstrTypeId(){
    return constrTypeId;
    }
    public void setConstrTypeId(java.lang.Long constrTypeId)
    {
    this.constrTypeId = constrTypeId;
    }
    
    public java.lang.Long getCatProjectId(){
    return catProjectId;
    }
    public void setCatProjectId(java.lang.Long catProjectId)
    {
    this.catProjectId = catProjectId;
    }
    
    public java.lang.Long getUpdateProcess(){
    return updateProcess;
    }
    public void setUpdateProcess(java.lang.Long updateProcess)
    {
    this.updateProcess = updateProcess;
    }
    
    public java.lang.Long getAcceptStatusLost(){
    return acceptStatusLost;
    }
    public void setAcceptStatusLost(java.lang.Long acceptStatusLost)
    {
    this.acceptStatusLost = acceptStatusLost;
    }
    
    public java.lang.Long getIsApLine(){
    return isApLine;
    }
    public void setIsApLine(java.lang.Long isApLine)
    {
    this.isApLine = isApLine;
    }
    
    public java.util.Date getServiceProvideDate(){
    return serviceProvideDate;
    }
    public void setServiceProvideDate(java.util.Date serviceProvideDate)
    {
    this.serviceProvideDate = serviceProvideDate;
    }
    
    public java.lang.Long getFinishTest(){
    return finishTest;
    }
    public void setFinishTest(java.lang.Long finishTest)
    {
    this.finishTest = finishTest;
    }
    
    public java.lang.Long getPercentFinish(){
    return percentFinish;
    }
    public void setPercentFinish(java.lang.Long percentFinish)
    {
    this.percentFinish = percentFinish;
    }
    
    public java.lang.Long getPreparedPremises(){
    return preparedPremises;
    }
    public void setPreparedPremises(java.lang.Long preparedPremises)
    {
    this.preparedPremises = preparedPremises;
    }
    
    public java.lang.Long getHandoverMaterials(){
    return handoverMaterials;
    }
    public void setHandoverMaterials(java.lang.Long handoverMaterials)
    {
    this.handoverMaterials = handoverMaterials;
    }
    
    public java.lang.Long getProgressCommitment(){
    return progressCommitment;
    }
    public void setProgressCommitment(java.lang.Long progressCommitment)
    {
    this.progressCommitment = progressCommitment;
    }
    
    public java.lang.Long getIsHcqt(){
    return isHcqt;
    }
    public void setIsHcqt(java.lang.Long isHcqt)
    {
    this.isHcqt = isHcqt;
    }
    
    public java.lang.Long getIsOff(){
    return isOff;
    }
    public void setIsOff(java.lang.Long isOff)
    {
    this.isOff = isOff;
    }
    
    public java.lang.Long getIsAcceptedContract(){
    return isAcceptedContract;
    }
    public void setIsAcceptedContract(java.lang.Long isAcceptedContract)
    {
    this.isAcceptedContract = isAcceptedContract;
    }
    
    public java.lang.Long getConstrLineType(){
    return constrLineType;
    }
    public void setConstrLineType(java.lang.Long constrLineType)
    {
    this.constrLineType = constrLineType;
    }
    
    public java.lang.Long getLineLength1(){
    return lineLength1;
    }
    public void setLineLength1(java.lang.Long lineLength1)
    {
    this.lineLength1 = lineLength1;
    }
    
    public java.lang.Long getIsFee(){
    return isFee;
    }
    public void setIsFee(java.lang.Long isFee)
    {
    this.isFee = isFee;
    }
    
    public java.util.Date getOnDate(){
    return onDate;
    }
    public void setOnDate(java.util.Date onDate)
    {
    this.onDate = onDate;
    }
    
    public java.lang.Long getUpdatorId(){
    return updatorId;
    }
    public void setUpdatorId(java.lang.Long updatorId)
    {
    this.updatorId = updatorId;
    }
    
    public java.lang.String getProvinceName() {
    	return provinceName;
    }

    public void setProvinceName(java.lang.String provinceName) {
    	this.provinceName = provinceName;
    }

    public java.lang.String getConstrTypeName() {
    	return constrTypeName;
    }

    public void setConstrTypeName(java.lang.String constrTypeName) {
    	this.constrTypeName = constrTypeName;
    }
    
    public java.lang.String getPartnerName() {
    	return partnerName;
    }

    public void setPartnerName(java.lang.String partnerName) {
    	this.partnerName = partnerName;
    }

	public java.lang.String getStationCode() {
		return stationCode;
	}

	public void setStationCode(java.lang.String stationCode) {
		this.stationCode = stationCode;
	}
    
   
}
