/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "constrConstructions")
@Table(name = "CONSTR_CONSTRUCTIONS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrConstructionsBO extends BaseFWModelImpl {

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
	//private java.lang.Long lineLength1;
	//private java.lang.Long isFee;
	private java.util.Date onDate;
	private java.lang.Long updatorId;

	public ConstrConstructionsBO() {
		setColId("constructId");
		setColName("constructId");
		setUniqueColumn(new String[] { "constructId" });
	}

	public ConstrConstructionsBO(Long constructId2) {
		this.constructId = constructId2;
	}

	@Column(name = "HIRING_MONITOR_CONSULT", length = 22)
	public java.lang.Long getHiringMonitorConsult() {
		return hiringMonitorConsult;
	}

	public void setHiringMonitorConsult(java.lang.Long hiringMonitorConsult) {
		this.hiringMonitorConsult = hiringMonitorConsult;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTR_CONSTRUCTIONS_SEQ") })
	@Column(name = "CONSTRUCT_ID", length = 22)
	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Column(name = "CONSTR_TYPE", length = 22)
	public java.lang.Long getConstrType() {
		return constrType;
	}

	public void setConstrType(java.lang.Long constrType) {
		this.constrType = constrType;
	}

	@Column(name = "CONSTRT_CODE", length = 200)
	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	@Column(name = "CONSTRT_NAME", length = 600)
	public java.lang.String getConstrtName() {
		return constrtName;
	}

	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}

	@Column(name = "CONSTRT_ADDRESS", length = 1000)
	public java.lang.String getConstrtAddress() {
		return constrtAddress;
	}

	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	@Column(name = "CONSTRT_FORM", length = 22)
	public java.lang.Long getConstrtForm() {
		return constrtForm;
	}

	public void setConstrtForm(java.lang.Long constrtForm) {
		this.constrtForm = constrtForm;
	}

	@Column(name = "SUPER_FORM", length = 22)
	public java.lang.Long getSuperForm() {
		return superForm;
	}

	public void setSuperForm(java.lang.Long superForm) {
		this.superForm = superForm;
	}

	@Column(name = "CONSTRUCTOR_ID", length = 22)
	public java.lang.Long getConstructorId() {
		return constructorId;
	}

	public void setConstructorId(java.lang.Long constructorId) {
		this.constructorId = constructorId;
	}

	@Column(name = "SUPERVISOR_ID", length = 22)
	public java.lang.Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(java.lang.Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	@Column(name = "STARTING_DATE", length = 7)
	public java.util.Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(java.util.Date startingDate) {
		this.startingDate = startingDate;
	}

	@Column(name = "COMPLETE_DATE", length = 7)
	public java.util.Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(java.util.Date completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "SUGGESTOR_ID", length = 22)
	public java.lang.Long getSuggestorId() {
		return suggestorId;
	}

	public void setSuggestorId(java.lang.Long suggestorId) {
		this.suggestorId = suggestorId;
	}

	@Column(name = "SUGGESTOR_GROUP_ID", length = 22)
	public java.lang.Long getSuggestorGroupId() {
		return suggestorGroupId;
	}

	public void setSuggestorGroupId(java.lang.Long suggestorGroupId) {
		this.suggestorGroupId = suggestorGroupId;
	}

	@Column(name = "SUGGEST_DATE", length = 7)
	public java.util.Date getSuggestDate() {
		return suggestDate;
	}

	public void setSuggestDate(java.util.Date suggestDate) {
		this.suggestDate = suggestDate;
	}

	@Column(name = "SYS_USER_ID", length = 22)
	public java.lang.Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(java.lang.Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Column(name = "SYS_GROUP_ID", length = 22)
	public java.lang.Long getSysGroupId() {
		return sysGroupId;
	}

	public void setSysGroupId(java.lang.Long sysGroupId) {
		this.sysGroupId = sysGroupId;
	}

	@Column(name = "SYS_CREATED_DATE", length = 7)
	public java.util.Date getSysCreatedDate() {
		return sysCreatedDate;
	}

	public void setSysCreatedDate(java.util.Date sysCreatedDate) {
		this.sysCreatedDate = sysCreatedDate;
	}

	@Column(name = "INVEST_PROJECT_ID", length = 22)
	public java.lang.Long getInvestProjectId() {
		return investProjectId;
	}

	public void setInvestProjectId(java.lang.Long investProjectId) {
		this.investProjectId = investProjectId;
	}

	@Column(name = "CAT_CURRENCY_ID", length = 22)
	public java.lang.Long getCatCurrencyId() {
		return catCurrencyId;
	}

	public void setCatCurrencyId(java.lang.Long catCurrencyId) {
		this.catCurrencyId = catCurrencyId;
	}

	@Column(name = "EXPECTED_COMPLETE_DATE", length = 7)
	public java.util.Date getExpectedCompleteDate() {
		return expectedCompleteDate;
	}

	public void setExpectedCompleteDate(java.util.Date expectedCompleteDate) {
		this.expectedCompleteDate = expectedCompleteDate;
	}

	@Column(name = "START_COMMENT", length = 1000)
	public java.lang.String getStartComment() {
		return startComment;
	}

	public void setStartComment(java.lang.String startComment) {
		this.startComment = startComment;
	}

	@Column(name = "PROJECT_INVEST_COMPONENT_ID", length = 22)
	public java.lang.Double getProjectInvestComponentId() {
		return projectInvestComponentId;
	}

	public void setProjectInvestComponentId(java.lang.Double projectInvestComponentId) {
		this.projectInvestComponentId = projectInvestComponentId;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "PROVINCE_ID", length = 22)
	public java.lang.Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "DISTRICT_ID", length = 22)
	public java.lang.Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(java.lang.Long districtId) {
		this.districtId = districtId;
	}

	@Column(name = "STATION_ID", length = 22)
	public java.lang.Long getStationId() {
		return stationId;
	}

	public void setStationId(java.lang.Long stationId) {
		this.stationId = stationId;
	}

	@Column(name = "START_BY", length = 22)
	public java.lang.Long getStartBy() {
		return startBy;
	}

	public void setStartBy(java.lang.Long startBy) {
		this.startBy = startBy;
	}

	@Column(name = "UPDATE_STATUS_BY", length = 22)
	public java.lang.Long getUpdateStatusBy() {
		return updateStatusBy;
	}

	public void setUpdateStatusBy(java.lang.Long updateStatusBy) {
		this.updateStatusBy = updateStatusBy;
	}

	@Column(name = "ACCEPT_START_DATE", length = 7)
	public java.util.Date getAcceptStartDate() {
		return acceptStartDate;
	}

	public void setAcceptStartDate(java.util.Date acceptStartDate) {
		this.acceptStartDate = acceptStartDate;
	}

	@Column(name = "ACCEPT_FINISH_DATE", length = 7)
	public java.util.Date getAcceptFinishDate() {
		return acceptFinishDate;
	}

	public void setAcceptFinishDate(java.util.Date acceptFinishDate) {
		this.acceptFinishDate = acceptFinishDate;
	}

	@Column(name = "PLAN_ID", length = 22)
	public java.lang.Long getPlanId() {
		return planId;
	}

	public void setPlanId(java.lang.Long planId) {
		this.planId = planId;
	}

	@Column(name = "ADD_TIME", length = 7)
	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "STATUS_ID", length = 22)
	public java.lang.Long getStatusId() {
		return statusId;
	}

	public void setStatusId(java.lang.Long statusId) {
		this.statusId = statusId;
	}

	@Column(name = "IS_OBSTRUCTED", length = 22)
	public java.lang.Long getIsObstructed() {
		return isObstructed;
	}

	public void setIsObstructed(java.lang.Long isObstructed) {
		this.isObstructed = isObstructed;
	}

	@Column(name = "LAND_TRANSFERED_DATE", length = 7)
	public java.util.Date getLandTransferedDate() {
		return landTransferedDate;
	}

	public void setLandTransferedDate(java.util.Date landTransferedDate) {
		this.landTransferedDate = landTransferedDate;
	}

	@Column(name = "INTEGRATED_DATE", length = 7)
	public java.util.Date getIntegratedDate() {
		return integratedDate;
	}

	public void setIntegratedDate(java.util.Date integratedDate) {
		this.integratedDate = integratedDate;
	}

	@Column(name = "EMISSION_DATE", length = 7)
	public java.util.Date getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(java.util.Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	@Column(name = "IS_ACCEPTED", length = 22)
	public java.lang.Long getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(java.lang.Long isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Column(name = "PRE_STARTING_DATE", length = 7)
	public java.util.Date getPreStartingDate() {
		return preStartingDate;
	}

	public void setPreStartingDate(java.util.Date preStartingDate) {
		this.preStartingDate = preStartingDate;
	}

	@Column(name = "STATUS", length = 22)
	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	@Column(name = "CONSTRUCTOR_PERSON", length = 200)
	public java.lang.String getConstructorPerson() {
		return constructorPerson;
	}

	public void setConstructorPerson(java.lang.String constructorPerson) {
		this.constructorPerson = constructorPerson;
	}

	@Column(name = "CONSTRUCTOR_PHONE", length = 40)
	public java.lang.String getConstructorPhone() {
		return constructorPhone;
	}

	public void setConstructorPhone(java.lang.String constructorPhone) {
		this.constructorPhone = constructorPhone;
	}

	@Column(name = "SUPERVISOR_PERSON", length = 200)
	public java.lang.String getSupervisorPerson() {
		return supervisorPerson;
	}

	public void setSupervisorPerson(java.lang.String supervisorPerson) {
		this.supervisorPerson = supervisorPerson;
	}

	@Column(name = "SUPERVISOR_PHONE", length = 20)
	public java.lang.String getSupervisorPhone() {
		return supervisorPhone;
	}

	public void setSupervisorPhone(java.lang.String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}

	@Column(name = "PROCEDURE", length = 22)
	public java.lang.Long getProcedure() {
		return procedure;
	}

	public void setProcedure(java.lang.Long procedure) {
		this.procedure = procedure;
	}

	@Column(name = "PROCEDURE_DATE", length = 7)
	public java.util.Date getProcedureDate() {
		return procedureDate;
	}

	public void setProcedureDate(java.util.Date procedureDate) {
		this.procedureDate = procedureDate;
	}

	@Column(name = "PROCEDURE_COMMENT", length = 600)
	public java.lang.String getProcedureComment() {
		return procedureComment;
	}

	public void setProcedureComment(java.lang.String procedureComment) {
		this.procedureComment = procedureComment;
	}

	@Column(name = "VIBA_FIRST", length = 22)
	public java.lang.Long getVibaFirst() {
		return vibaFirst;
	}

	public void setVibaFirst(java.lang.Long vibaFirst) {
		this.vibaFirst = vibaFirst;
	}

	@Column(name = "HANDOVER_DATE", length = 7)
	public java.util.Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	@Column(name = "HANDOVER_COMMENT", length = 2000)
	public java.lang.String getHandoverComment() {
		return handoverComment;
	}

	public void setHandoverComment(java.lang.String handoverComment) {
		this.handoverComment = handoverComment;
	}

	@Column(name = "LINE_MODE", length = 22)
	public java.lang.Long getLineMode() {
		return lineMode;
	}

	public void setLineMode(java.lang.Long lineMode) {
		this.lineMode = lineMode;
	}

	@Column(name = "COMPLETE_COMMENT", length = 400)
	public java.lang.String getCompleteComment() {
		return completeComment;
	}

	public void setCompleteComment(java.lang.String completeComment) {
		this.completeComment = completeComment;
	}

	@Column(name = "LAST_UPDATE_PROGRESS", length = 7)
	public java.util.Date getLastUpdateProgress() {
		return lastUpdateProgress;
	}

	public void setLastUpdateProgress(java.util.Date lastUpdateProgress) {
		this.lastUpdateProgress = lastUpdateProgress;
	}

	@Column(name = "PROJECT_TYPE", length = 22)
	public java.lang.Long getProjectType() {
		return projectType;
	}

	public void setProjectType(java.lang.Long projectType) {
		this.projectType = projectType;
	}

	@Column(name = "PILLAR_PROGRESS_PERCENT", length = 22)
	public java.lang.Long getPillarProgressPercent() {
		return pillarProgressPercent;
	}

	public void setPillarProgressPercent(java.lang.Long pillarProgressPercent) {
		this.pillarProgressPercent = pillarProgressPercent;
	}

	@Column(name = "CABLE_PROGRESS_PERCENT", length = 22)
	public java.lang.Long getCableProgressPercent() {
		return cableProgressPercent;
	}

	public void setCableProgressPercent(java.lang.Long cableProgressPercent) {
		this.cableProgressPercent = cableProgressPercent;
	}

	@Column(name = "TIME_PERCENT", length = 22)
	public java.lang.Long getTimePercent() {
		return timePercent;
	}

	public void setTimePercent(java.lang.Long timePercent) {
		this.timePercent = timePercent;
	}

	@Column(name = "PROGRESS_STATUS", length = 22)
	public java.lang.Long getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(java.lang.Long progressStatus) {
		this.progressStatus = progressStatus;
	}

	@Column(name = "MER_STATUS", length = 22)
	public java.lang.Long getMerStatus() {
		return merStatus;
	}

	public void setMerStatus(java.lang.Long merStatus) {
		this.merStatus = merStatus;
	}

	@Column(name = "DESCRIPTION", length = 1000)
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Column(name = "SLOW_CAUSE", length = 1000)
	public java.lang.String getSlowCause() {
		return slowCause;
	}

	public void setSlowCause(java.lang.String slowCause) {
		this.slowCause = slowCause;
	}

	@Column(name = "SCOPE", length = 22)
	public java.lang.Long getScope() {
		return scope;
	}

	public void setScope(java.lang.Long scope) {
		this.scope = scope;
	}

	@Column(name = "LINE_TYPE", length = 22)
	public java.lang.Long getLineType() {
		return lineType;
	}

	public void setLineType(java.lang.Long lineType) {
		this.lineType = lineType;
	}

	@Column(name = "CABLE_TYPE_ID", length = 22)
	public java.lang.Long getCableTypeId() {
		return cableTypeId;
	}

	public void setCableTypeId(java.lang.Long cableTypeId) {
		this.cableTypeId = cableTypeId;
	}

	@Column(name = "SURVEY_ID", length = 22)
	public java.lang.Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(java.lang.Long surveyId) {
		this.surveyId = surveyId;
	}

	@Column(name = "LINE_LENGTH", length = 22)
	public java.lang.Long getLineLength() {
		return lineLength;
	}

	public void setLineLength(java.lang.Long lineLength) {
		this.lineLength = lineLength;
	}

	@Column(name = "CABLE_MER_PERCENT", length = 22)
	public java.lang.Long getCableMerPercent() {
		return cableMerPercent;
	}

	public void setCableMerPercent(java.lang.Long cableMerPercent) {
		this.cableMerPercent = cableMerPercent;
	}

	@Column(name = "PILLAR_MER_PERCENT", length = 22)
	public java.lang.Long getPillarMerPercent() {
		return pillarMerPercent;
	}

	public void setPillarMerPercent(java.lang.Long pillarMerPercent) {
		this.pillarMerPercent = pillarMerPercent;
	}

	@Column(name = "EXECUTE_GROUP", length = 22)
	public java.lang.Long getExecuteGroup() {
		return executeGroup;
	}

	public void setExecuteGroup(java.lang.Long executeGroup) {
		this.executeGroup = executeGroup;
	}

	@Column(name = "EXECUTER", length = 100)
	public java.lang.String getExecuter() {
		return executer;
	}

	public void setExecuter(java.lang.String executer) {
		this.executer = executer;
	}

	@Column(name = "EXECUTE_PHONE", length = 30)
	public java.lang.String getExecutePhone() {
		return executePhone;
	}

	public void setExecutePhone(java.lang.String executePhone) {
		this.executePhone = executePhone;
	}

	@Column(name = "IS_INVENTORY", length = 22)
	public java.lang.Long getIsInventory() {
		return isInventory;
	}

	public void setIsInventory(java.lang.Long isInventory) {
		this.isInventory = isInventory;
	}

	@Column(name = "PM_PER", length = 20)
	public java.lang.String getPmPer() {
		return pmPer;
	}

	public void setPmPer(java.lang.String pmPer) {
		this.pmPer = pmPer;
	}

	@Column(name = "CM_PER", length = 20)
	public java.lang.String getCmPer() {
		return cmPer;
	}

	public void setCmPer(java.lang.String cmPer) {
		this.cmPer = cmPer;
	}

	@Column(name = "AP_CONSTR_TYPE_ID", length = 22)
	public java.lang.Long getApConstrTypeId() {
		return apConstrTypeId;
	}

	public void setApConstrTypeId(java.lang.Long apConstrTypeId) {
		this.apConstrTypeId = apConstrTypeId;
	}

	@Column(name = "SUB_AP_CONSTR_TYPE_ID", length = 22)
	public java.lang.Long getSubApConstrTypeId() {
		return subApConstrTypeId;
	}

	public void setSubApConstrTypeId(java.lang.Long subApConstrTypeId) {
		this.subApConstrTypeId = subApConstrTypeId;
	}

	@Column(name = "MIGRATE_DATE", length = 7)
	public java.util.Date getMigrateDate() {
		return migrateDate;
	}

	public void setMigrateDate(java.util.Date migrateDate) {
		this.migrateDate = migrateDate;
	}

	@Column(name = "CAT_AP_PROJECT_ID", length = 22)
	public java.lang.Long getCatApProjectId() {
		return catApProjectId;
	}

	public void setCatApProjectId(java.lang.Long catApProjectId) {
		this.catApProjectId = catApProjectId;
	}

	@Column(name = "ADSL_NUM", length = 22)
	public java.lang.Long getAdslNum() {
		return adslNum;
	}

	public void setAdslNum(java.lang.Long adslNum) {
		this.adslNum = adslNum;
	}

	@Column(name = "PSTN_NUM", length = 22)
	public java.lang.Long getPstnNum() {
		return pstnNum;
	}

	public void setPstnNum(java.lang.Long pstnNum) {
		this.pstnNum = pstnNum;
	}

	@Column(name = "CONSTR_TYPE_ID", length = 22)
	public java.lang.Long getConstrTypeId() {
		return constrTypeId;
	}

	public void setConstrTypeId(java.lang.Long constrTypeId) {
		this.constrTypeId = constrTypeId;
	}

	@Column(name = "CAT_PROJECT_ID", length = 22)
	public java.lang.Long getCatProjectId() {
		return catProjectId;
	}

	public void setCatProjectId(java.lang.Long catProjectId) {
		this.catProjectId = catProjectId;
	}

	@Column(name = "UPDATE_PROCESS", length = 22)
	public java.lang.Long getUpdateProcess() {
		return updateProcess;
	}

	public void setUpdateProcess(java.lang.Long updateProcess) {
		this.updateProcess = updateProcess;
	}

	@Column(name = "ACCEPT_STATUS_LOST", length = 22)
	public java.lang.Long getAcceptStatusLost() {
		return acceptStatusLost;
	}

	public void setAcceptStatusLost(java.lang.Long acceptStatusLost) {
		this.acceptStatusLost = acceptStatusLost;
	}

	@Column(name = "IS_AP_LINE", length = 22)
	public java.lang.Long getIsApLine() {
		return isApLine;
	}

	public void setIsApLine(java.lang.Long isApLine) {
		this.isApLine = isApLine;
	}

	@Column(name = "SERVICE_PROVIDE_DATE", length = 7)
	public java.util.Date getServiceProvideDate() {
		return serviceProvideDate;
	}

	public void setServiceProvideDate(java.util.Date serviceProvideDate) {
		this.serviceProvideDate = serviceProvideDate;
	}

	@Column(name = "FINISH_TEST", length = 22)
	public java.lang.Long getFinishTest() {
		return finishTest;
	}

	public void setFinishTest(java.lang.Long finishTest) {
		this.finishTest = finishTest;
	}

	@Column(name = "PERCENT_FINISH", length = 22)
	public java.lang.Long getPercentFinish() {
		return percentFinish;
	}

	public void setPercentFinish(java.lang.Long percentFinish) {
		this.percentFinish = percentFinish;
	}

	@Column(name = "PREPARED_PREMISES", length = 22)
	public java.lang.Long getPreparedPremises() {
		return preparedPremises;
	}

	public void setPreparedPremises(java.lang.Long preparedPremises) {
		this.preparedPremises = preparedPremises;
	}

	@Column(name = "HANDOVER_MATERIALS", length = 22)
	public java.lang.Long getHandoverMaterials() {
		return handoverMaterials;
	}

	public void setHandoverMaterials(java.lang.Long handoverMaterials) {
		this.handoverMaterials = handoverMaterials;
	}

	@Column(name = "PROGRESS_COMMITMENT", length = 22)
	public java.lang.Long getProgressCommitment() {
		return progressCommitment;
	}

	public void setProgressCommitment(java.lang.Long progressCommitment) {
		this.progressCommitment = progressCommitment;
	}

	@Column(name = "IS_HCQT", length = 22)
	public java.lang.Long getIsHcqt() {
		return isHcqt;
	}

	public void setIsHcqt(java.lang.Long isHcqt) {
		this.isHcqt = isHcqt;
	}

	@Column(name = "IS_OFF", length = 22)
	public java.lang.Long getIsOff() {
		return isOff;
	}

	public void setIsOff(java.lang.Long isOff) {
		this.isOff = isOff;
	}

	@Column(name = "IS_ACCEPTED_CONTRACT", length = 22)
	public java.lang.Long getIsAcceptedContract() {
		return isAcceptedContract;
	}

	public void setIsAcceptedContract(java.lang.Long isAcceptedContract) {
		this.isAcceptedContract = isAcceptedContract;
	}

	@Column(name = "CONSTR_LINE_TYPE", length = 22)
	public java.lang.Long getConstrLineType() {
		return constrLineType;
	}

	public void setConstrLineType(java.lang.Long constrLineType) {
		this.constrLineType = constrLineType;
	}

/*	@Column(name = "LINE_LENGTH1", length = 22)
	public java.lang.Long getLineLength1() {
		return lineLength1;
	}

	public void setLineLength1(java.lang.Long lineLength1) {
		this.lineLength1 = lineLength1;
	}

	@Column(name = "IS_FEE", length = 22)
	public java.lang.Long getIsFee() {
		return isFee;
	}

	public void setIsFee(java.lang.Long isFee) {
		this.isFee = isFee;
	}*/

	@Column(name = "ON_DATE", length = 7)
	public java.util.Date getOnDate() {
		return onDate;
	}

	public void setOnDate(java.util.Date onDate) {
		this.onDate = onDate;
	}

	@Column(name = "UPDATOR_ID", length = 22)
	public java.lang.Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(java.lang.Long updatorId) {
		this.updatorId = updatorId;
	}

	@Override
	public ConstrConstructionsDTO toDTO() {
		ConstrConstructionsDTO constrConstructionsDTO = new ConstrConstructionsDTO();
		// set cac gia tri
		constrConstructionsDTO.setHiringMonitorConsult(this.hiringMonitorConsult);
		constrConstructionsDTO.setConstructId(this.constructId);
		constrConstructionsDTO.setConstrType(this.constrType);
		constrConstructionsDTO.setConstrtCode(this.constrtCode);
		constrConstructionsDTO.setConstrtName(this.constrtName);
		constrConstructionsDTO.setConstrtAddress(this.constrtAddress);
		constrConstructionsDTO.setConstrtForm(this.constrtForm);
		constrConstructionsDTO.setSuperForm(this.superForm);
		constrConstructionsDTO.setConstructorId(this.constructorId);
		constrConstructionsDTO.setSupervisorId(this.supervisorId);
		constrConstructionsDTO.setStartingDate(this.startingDate);
		constrConstructionsDTO.setCompleteDate(this.completeDate);
		constrConstructionsDTO.setSuggestorId(this.suggestorId);
		constrConstructionsDTO.setSuggestorGroupId(this.suggestorGroupId);
		constrConstructionsDTO.setSuggestDate(this.suggestDate);
		constrConstructionsDTO.setSysUserId(this.sysUserId);
		constrConstructionsDTO.setSysGroupId(this.sysGroupId);
		constrConstructionsDTO.setSysCreatedDate(this.sysCreatedDate);
		constrConstructionsDTO.setInvestProjectId(this.investProjectId);
		constrConstructionsDTO.setCatCurrencyId(this.catCurrencyId);
		constrConstructionsDTO.setExpectedCompleteDate(this.expectedCompleteDate);
		constrConstructionsDTO.setStartComment(this.startComment);
		constrConstructionsDTO.setProjectInvestComponentId(this.projectInvestComponentId);
		constrConstructionsDTO.setIsActive(this.isActive);
		constrConstructionsDTO.setProvinceId(this.provinceId);
		constrConstructionsDTO.setDistrictId(this.districtId);
		constrConstructionsDTO.setStationId(this.stationId);
		constrConstructionsDTO.setStartBy(this.startBy);
		constrConstructionsDTO.setUpdateStatusBy(this.updateStatusBy);
		constrConstructionsDTO.setAcceptStartDate(this.acceptStartDate);
		constrConstructionsDTO.setAcceptFinishDate(this.acceptFinishDate);
		constrConstructionsDTO.setPlanId(this.planId);
		constrConstructionsDTO.setAddTime(this.addTime);
		constrConstructionsDTO.setStatusId(this.statusId);
		constrConstructionsDTO.setIsObstructed(this.isObstructed);
		constrConstructionsDTO.setLandTransferedDate(this.landTransferedDate);
		constrConstructionsDTO.setIntegratedDate(this.integratedDate);
		constrConstructionsDTO.setEmissionDate(this.emissionDate);
		constrConstructionsDTO.setIsAccepted(this.isAccepted);
		constrConstructionsDTO.setPreStartingDate(this.preStartingDate);
		constrConstructionsDTO.setStatus(this.status);
		constrConstructionsDTO.setConstructorPerson(this.constructorPerson);
		constrConstructionsDTO.setConstructorPhone(this.constructorPhone);
		constrConstructionsDTO.setSupervisorPerson(this.supervisorPerson);
		constrConstructionsDTO.setSupervisorPhone(this.supervisorPhone);
		constrConstructionsDTO.setProcedure(this.procedure);
		constrConstructionsDTO.setProcedureDate(this.procedureDate);
		constrConstructionsDTO.setProcedureComment(this.procedureComment);
		constrConstructionsDTO.setVibaFirst(this.vibaFirst);
		constrConstructionsDTO.setHandoverDate(this.handoverDate);
		constrConstructionsDTO.setHandoverComment(this.handoverComment);
		constrConstructionsDTO.setLineMode(this.lineMode);
		constrConstructionsDTO.setCompleteComment(this.completeComment);
		constrConstructionsDTO.setLastUpdateProgress(this.lastUpdateProgress);
		constrConstructionsDTO.setProjectType(this.projectType);
		constrConstructionsDTO.setPillarProgressPercent(this.pillarProgressPercent);
		constrConstructionsDTO.setCableProgressPercent(this.cableProgressPercent);
		constrConstructionsDTO.setTimePercent(this.timePercent);
		constrConstructionsDTO.setProgressStatus(this.progressStatus);
		constrConstructionsDTO.setMerStatus(this.merStatus);
		constrConstructionsDTO.setDescription(this.description);
		constrConstructionsDTO.setSlowCause(this.slowCause);
		constrConstructionsDTO.setScope(this.scope);
		constrConstructionsDTO.setLineType(this.lineType);
		constrConstructionsDTO.setCableTypeId(this.cableTypeId);
		constrConstructionsDTO.setSurveyId(this.surveyId);
		constrConstructionsDTO.setLineLength(this.lineLength);
		constrConstructionsDTO.setCableMerPercent(this.cableMerPercent);
		constrConstructionsDTO.setPillarMerPercent(this.pillarMerPercent);
		constrConstructionsDTO.setExecuteGroup(this.executeGroup);
		constrConstructionsDTO.setExecuter(this.executer);
		constrConstructionsDTO.setExecutePhone(this.executePhone);
		constrConstructionsDTO.setIsInventory(this.isInventory);
		constrConstructionsDTO.setPmPer(this.pmPer);
		constrConstructionsDTO.setCmPer(this.cmPer);
		constrConstructionsDTO.setApConstrTypeId(this.apConstrTypeId);
		constrConstructionsDTO.setSubApConstrTypeId(this.subApConstrTypeId);
		constrConstructionsDTO.setMigrateDate(this.migrateDate);
		constrConstructionsDTO.setCatApProjectId(this.catApProjectId);
		constrConstructionsDTO.setAdslNum(this.adslNum);
		constrConstructionsDTO.setPstnNum(this.pstnNum);
		constrConstructionsDTO.setConstrTypeId(this.constrTypeId);
		constrConstructionsDTO.setCatProjectId(this.catProjectId);
		constrConstructionsDTO.setUpdateProcess(this.updateProcess);
		constrConstructionsDTO.setAcceptStatusLost(this.acceptStatusLost);
		constrConstructionsDTO.setIsApLine(this.isApLine);
		constrConstructionsDTO.setServiceProvideDate(this.serviceProvideDate);
		constrConstructionsDTO.setFinishTest(this.finishTest);
		constrConstructionsDTO.setPercentFinish(this.percentFinish);
		constrConstructionsDTO.setPreparedPremises(this.preparedPremises);
		constrConstructionsDTO.setHandoverMaterials(this.handoverMaterials);
		constrConstructionsDTO.setProgressCommitment(this.progressCommitment);
		constrConstructionsDTO.setIsHcqt(this.isHcqt);
		constrConstructionsDTO.setIsOff(this.isOff);
		constrConstructionsDTO.setIsAcceptedContract(this.isAcceptedContract);
		constrConstructionsDTO.setConstrLineType(this.constrLineType);
		//constrConstructionsDTO.setLineLength1(this.lineLength1);
		//constrConstructionsDTO.setIsFee(this.isFee);
		constrConstructionsDTO.setOnDate(this.onDate);
		constrConstructionsDTO.setUpdatorId(this.updatorId);
		return constrConstructionsDTO;
	}
}
