package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.ConstrConstruction;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "constrConstruction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrConstructionDto {
	private Long constructId;
	private Long constrType;
	private String constrtCode;
	private String constrtName;
	private String constrtAddress;
	private Long constrtForm;
	private Long superForm;
	private Long constructorId;
	private Long supervisorId;
	private Date startingDate;
	private Date completeDate;
	private Long suggestorId;
	private Long suggestorGroupId;
	private Date suggestDate;
	private Long sysUserId;
	private Long sysGroupId;
	private Date sysCreatedDate;
	private Long investProjectId;
	private Long catCurrencyId;
	private Date expectedCompleteDate;
	private String startComment;
	private Long projectInvestComponentId;
	private Long isActive;
	private Long provinceId;
	private Long districtId;
	private Long stationId;
	private Long startBy;
	private Long updateStatusBy;
	private Date acceptStartDate;
	private Date acceptFinishDate;
	private Long planId;
	private Date addTime;
	private Long statusId;
	private Long isObstructed;
	private Date landTransferedDate;
	private Date integratedDate;
	private Date emissionDate;
	private Long isAccepted;
	private Date preStartingDate;
	private Long status;
	private String constructorPerson;
	private String constructorPhone;
	private String supervisorPerson;
	private String supervisorPhone;
	private Long procedure;
	private Date procedureDate;
	private String procedureComment;
	private Long vibaFirst;
	private Date handoverDate;
	private String handoverComment;
	private Long lineMode;
	private String completeComment;
	private Date lastUpdateProgress;
	private Long projectType;
	private Long pillarProgressPercent;
	private Long cableProgressPercent;
	private Long timePercent;
	private Long progressStatus;
	private Long merStatus;
	private String description;
	private String slowCause;
	private Long scope;
	private Long lineType;
	private Long cableTypeId;
	private Long surveyId;
	private Long lineLength;
	private Long cableMerPercent;
	private Long pillarMerPercent;
	private Long executeGroup;
	private String executer;
	private String executePhone;
	private Long isInventory;
	private String pmPer;
	private String cmPer;
	private Long apConstrTypeId;
	private Long subApConstrTypeId;
	private Date migrateDate;
	private Long catApProjectId;
	private Long adslNum;
	private Long pstnNum;
	private Long constrTypeId;
	private Long catProjectId;
	private Long updateProcess;
	private Long acceptStatusLost;
	private Long isApLine;
	private Date serviceProvideDate;
	private Long finishTest;
	private Long percentFinish;
	private Long preparedPremises;
	private Long handoverMaterials;
	private Long progressCommitment;
	private Long isHcqt;
	private Long isOff;
	private Long isAcceptedContract;
	private Long constrLineType;
	private Long lineLength1;
	private Long isFee;
	private Date onDate;
	private Long updatorId;
	private Long hiringMonitorConsult;
	private Long isEstimated;
	private Long isAcceptedHandoverUse;
	
	public ConstrConstructionDto() {
		
	}
	
	public ConstrConstructionDto(ConstrConstruction entity) {
		this.constructId = entity.getConstructId();
		this.constrType = entity.getConstrType();
		this.constrtCode = entity.getConstrtCode();
		this.constrtName = entity.getConstrtName();
		this.constrtAddress = entity.getConstrtAddress();
		this.constrtForm = entity.getConstrtForm();
		this.superForm = entity.getSuperForm();
		this.constructorId = entity.getConstructorId();
		this.supervisorId = entity.getSupervisorId();
		this.startingDate = entity.getStartingDate();
		this.completeDate = entity.getCompleteDate();
		this.suggestorId = entity.getSuggestorId();
		this.suggestorGroupId = entity.getSuggestorGroupId();
		this.suggestDate = entity.getSuggestDate();
		this.sysUserId = entity.getSysUserId();
		this.sysGroupId = entity.getSysGroupId();
		this.sysCreatedDate = entity.getSysCreatedDate();
		this.investProjectId = entity.getInvestProjectId();
		this.catCurrencyId = entity.getCatCurrencyId();
		this.expectedCompleteDate = entity.getExpectedCompleteDate();
		this.startComment = entity.getStartComment();
		this.projectInvestComponentId = entity.getProjectInvestComponentId();
		this.isActive = entity.getIsActive();
		this.provinceId = entity.getProvinceId();
		this.districtId = entity.getDistrictId();
		this.stationId = entity.getStationId();
		this.startBy = entity.getStartBy();
		this.updateStatusBy = entity.getUpdateStatusBy();
		this.acceptStartDate = entity.getAcceptStartDate();
		this.acceptFinishDate = entity.getAcceptFinishDate();
		this.planId = entity.getPlanId();
		this.addTime = entity.getAddTime();
		this.statusId = entity.getStatusId();
		this.isObstructed = entity.getIsObstructed();
		this.landTransferedDate = entity.getLandTransferedDate();
		this.integratedDate = entity.getIntegratedDate();
		this.emissionDate = entity.getEmissionDate();
		this.isAccepted = entity.getIsAccepted();
		this.preStartingDate = entity.getPreStartingDate();
		this.status = entity.getStatus();
		this.constructorPerson = entity.getConstructorPerson();
		this.constructorPhone = entity.getConstructorPhone();
		this.supervisorPerson = entity.getSupervisorPerson();
		this.supervisorPhone = entity.getSupervisorPhone();
		this.procedure = entity.getProcedure();
		this.procedureDate = entity.getProcedureDate();
		this.procedureComment = entity.getProcedureComment();
		this.vibaFirst = entity.getVibaFirst();
		this.handoverDate = entity.getHandoverDate();
		this.handoverComment = entity.getHandoverComment();
		this.lineMode = entity.getLineMode();
		this.completeComment = entity.getCompleteComment();
		this.lastUpdateProgress = entity.getLastUpdateProgress();
		this.projectType = entity.getProjectType();
		this.pillarProgressPercent = entity.getPillarProgressPercent();
		this.cableProgressPercent = entity.getCableProgressPercent();
		this.timePercent = entity.getTimePercent();
		this.progressStatus = entity.getProgressStatus();
		this.merStatus = entity.getMerStatus();
		this.description = entity.getDescription();
		this.slowCause = entity.getSlowCause();
		this.scope = entity.getScope();
		this.lineType = entity.getLineType();
		this.cableTypeId = entity.getCableTypeId();
		this.surveyId = entity.getSurveyId();
		this.lineLength = entity.getLineLength();
		this.cableMerPercent = entity.getCableMerPercent();
		this.pillarMerPercent = entity.getPillarMerPercent();
		this.executeGroup = entity.getExecuteGroup();
		this.executer = entity.getExecuter();
		this.executePhone = entity.getExecutePhone();
		this.isInventory = entity.getIsInventory();
		this.pmPer = entity.getPmPer();
		this.cmPer = entity.getCmPer();
		this.apConstrTypeId = entity.getApConstrTypeId();
		this.subApConstrTypeId = entity.getSubApConstrTypeId();
		this.migrateDate = entity.getMigrateDate();
		this.catApProjectId = entity.getCatApProjectId();
		this.adslNum = entity.getAdslNum();
		this.pstnNum = entity.getPstnNum();
		this.constrTypeId = entity.getConstrTypeId();
		this.catProjectId = entity.getCatProjectId();
		this.updateProcess = entity.getUpdateProcess();
		this.acceptStatusLost = entity.getAcceptStatusLost();
		this.isApLine = entity.getIsApLine();
		this.serviceProvideDate = entity.getServiceProvideDate();
		this.finishTest = entity.getFinishTest();
		this.percentFinish = entity.getPercentFinish();
		this.preparedPremises = entity.getPreparedPremises();
		this.handoverMaterials = entity.getHandoverMaterials();
		this.progressCommitment = entity.getProgressCommitment();
		this.isHcqt = entity.getIsHcqt();
		this.isOff = entity.getIsOff();
		this.isAcceptedContract = entity.getIsAcceptedContract();
		this.constrLineType = entity.getConstrLineType();
		this.lineLength1 = entity.getLineLength1();
		this.isFee = entity.getIsFee();
		this.onDate = entity.getOnDate();
		this.updatorId = entity.getUpdatorId();
		this.hiringMonitorConsult = entity.getHiringMonitorConsult();
		this.isEstimated = entity.getIsEstimated();
		this.isAcceptedHandoverUse = entity.getIsAcceptedHandoverUse();
	}
	
	public ConstrConstruction toEntity() {
		ConstrConstruction entity = new ConstrConstruction();
		entity.setConstructId(this.constructId);
		entity.setConstrType(this.constrType);
		entity.setConstrtCode(this.constrtCode);
		entity.setConstrtName(this.constrtName);
		entity.setConstrtAddress(this.constrtAddress);
		entity.setConstrtForm(this.constrtForm);
		entity.setSuperForm(this.superForm);
		entity.setConstructorId(this.constructorId);
		entity.setSupervisorId(this.supervisorId);
		entity.setStartingDate(this.startingDate);
		entity.setCompleteDate(this.completeDate);
		entity.setSuggestorId(this.suggestorId);
		entity.setSuggestorGroupId(this.suggestorGroupId);
		entity.setSuggestDate(this.suggestDate);
		entity.setSysUserId(this.sysUserId);
		entity.setSysGroupId(this.sysGroupId);
		entity.setSysCreatedDate(this.sysCreatedDate);
		entity.setInvestProjectId(this.investProjectId);
		entity.setCatCurrencyId(this.catCurrencyId);
		entity.setExpectedCompleteDate(this.expectedCompleteDate);
		entity.setStartComment(this.startComment);
		entity.setProjectInvestComponentId(this.projectInvestComponentId);
		entity.setIsActive(this.isActive);
		entity.setProvinceId(this.provinceId);
		entity.setDistrictId(this.districtId);
		entity.setStationId(this.stationId);
		entity.setStartBy(this.startBy);
		entity.setUpdateStatusBy(this.updateStatusBy);
		entity.setAcceptStartDate(this.acceptStartDate);
		entity.setAcceptFinishDate(this.acceptFinishDate);
		entity.setPlanId(this.planId);
		entity.setAddTime(this.addTime);
		entity.setStatusId(this.statusId);
		entity.setIsObstructed(this.isObstructed);
		entity.setLandTransferedDate(this.landTransferedDate);
		entity.setIntegratedDate(this.integratedDate);
		entity.setEmissionDate(this.emissionDate);
		entity.setIsAccepted(this.isAccepted);
		entity.setPreStartingDate(this.preStartingDate);
		entity.setStatus(this.status);
		entity.setConstructorPerson(this.constructorPerson);
		entity.setConstructorPhone(this.constructorPhone);
		entity.setSupervisorPerson(this.supervisorPerson);
		entity.setSupervisorPhone(this.supervisorPhone);
		entity.setProcedure(this.procedure);
		entity.setProcedureDate(this.procedureDate);
		entity.setProcedureComment(this.procedureComment);
		entity.setVibaFirst(this.vibaFirst);
		entity.setHandoverDate(this.handoverDate);
		entity.setHandoverComment(this.handoverComment);
		entity.setLineMode(this.lineMode);
		entity.setCompleteComment(this.completeComment);
		entity.setLastUpdateProgress(this.lastUpdateProgress);
		entity.setProjectType(this.projectType);
		entity.setPillarProgressPercent(this.pillarProgressPercent);
		entity.setCableProgressPercent(this.cableProgressPercent);
		entity.setTimePercent(this.timePercent);
		entity.setProgressStatus(this.progressStatus);
		entity.setMerStatus(this.merStatus);
		entity.setDescription(this.description);
		entity.setSlowCause(this.slowCause);
		entity.setScope(this.scope);
		entity.setLineType(this.lineType);
		entity.setCableTypeId(this.cableTypeId);
		entity.setSurveyId(this.surveyId);
		entity.setLineLength(this.lineLength);
		entity.setCableMerPercent(this.cableMerPercent);
		entity.setPillarMerPercent(this.pillarMerPercent);
		entity.setExecuteGroup(this.executeGroup);
		entity.setExecuter(this.executer);
		entity.setExecutePhone(this.executePhone);
		entity.setIsInventory(this.isInventory);
		entity.setPmPer(this.pmPer);
		entity.setCmPer(this.cmPer);
		entity.setApConstrTypeId(this.apConstrTypeId);
		entity.setSubApConstrTypeId(this.subApConstrTypeId);
		entity.setMigrateDate(this.migrateDate);
		entity.setCatApProjectId(this.catApProjectId);
		entity.setAdslNum(this.adslNum);
		entity.setPstnNum(this.pstnNum);
		entity.setConstrTypeId(this.constrTypeId);
		entity.setCatProjectId(this.catProjectId);
		entity.setUpdateProcess(this.updateProcess);
		entity.setAcceptStatusLost(this.acceptStatusLost);
		entity.setIsApLine(this.isApLine);
		entity.setServiceProvideDate(this.serviceProvideDate);
		entity.setFinishTest(this.finishTest);
		entity.setPercentFinish(this.percentFinish);
		entity.setPreparedPremises(this.preparedPremises);
		entity.setHandoverMaterials(this.handoverMaterials);
		entity.setProgressCommitment(this.progressCommitment);
		entity.setIsHcqt(this.isHcqt);
		entity.setIsOff(this.isOff);
		entity.setIsAcceptedContract(this.isAcceptedContract);
		entity.setConstrLineType(this.constrLineType);
		entity.setLineLength1(this.lineLength1);
		entity.setIsFee(this.isFee);
		entity.setOnDate(this.onDate);
		entity.setUpdatorId(this.updatorId);
		entity.setHiringMonitorConsult(this.hiringMonitorConsult);
		entity.setIsEstimated(this.isEstimated);
		entity.setIsAcceptedHandoverUse(this.isAcceptedHandoverUse);
		return entity;
	}
	
	public void updateEntity(ConstrConstruction entity) {
		entity.setConstrType(this.constrType);
		entity.setConstrtCode(this.constrtCode);
		entity.setConstrtName(this.constrtName);
		entity.setConstrtAddress(this.constrtAddress);
		entity.setConstrtForm(this.constrtForm);
		entity.setSuperForm(this.superForm);
		entity.setConstructorId(this.constructorId);
		entity.setSupervisorId(this.supervisorId);
		entity.setStartingDate(this.startingDate);
		entity.setCompleteDate(this.completeDate);
		entity.setSuggestorId(this.suggestorId);
		entity.setSuggestorGroupId(this.suggestorGroupId);
		entity.setSuggestDate(this.suggestDate);
		entity.setSysUserId(this.sysUserId);
		entity.setSysGroupId(this.sysGroupId);
		entity.setSysCreatedDate(this.sysCreatedDate);
		entity.setInvestProjectId(this.investProjectId);
		entity.setCatCurrencyId(this.catCurrencyId);
		entity.setExpectedCompleteDate(this.expectedCompleteDate);
		entity.setStartComment(this.startComment);
		entity.setProjectInvestComponentId(this.projectInvestComponentId);
		entity.setIsActive(this.isActive);
		entity.setProvinceId(this.provinceId);
		entity.setDistrictId(this.districtId);
		entity.setStationId(this.stationId);
		entity.setStartBy(this.startBy);
		entity.setUpdateStatusBy(this.updateStatusBy);
		entity.setAcceptStartDate(this.acceptStartDate);
		entity.setAcceptFinishDate(this.acceptFinishDate);
		entity.setPlanId(this.planId);
		entity.setAddTime(this.addTime);
		entity.setStatusId(this.statusId);
		entity.setIsObstructed(this.isObstructed);
		entity.setLandTransferedDate(this.landTransferedDate);
		entity.setIntegratedDate(this.integratedDate);
		entity.setEmissionDate(this.emissionDate);
		entity.setIsAccepted(this.isAccepted);
		entity.setPreStartingDate(this.preStartingDate);
		entity.setStatus(this.status);
		entity.setConstructorPerson(this.constructorPerson);
		entity.setConstructorPhone(this.constructorPhone);
		entity.setSupervisorPerson(this.supervisorPerson);
		entity.setSupervisorPhone(this.supervisorPhone);
		entity.setProcedure(this.procedure);
		entity.setProcedureDate(this.procedureDate);
		entity.setProcedureComment(this.procedureComment);
		entity.setVibaFirst(this.vibaFirst);
		entity.setHandoverDate(this.handoverDate);
		entity.setHandoverComment(this.handoverComment);
		entity.setLineMode(this.lineMode);
		entity.setCompleteComment(this.completeComment);
		entity.setLastUpdateProgress(this.lastUpdateProgress);
		entity.setProjectType(this.projectType);
		entity.setPillarProgressPercent(this.pillarProgressPercent);
		entity.setCableProgressPercent(this.cableProgressPercent);
		entity.setTimePercent(this.timePercent);
		entity.setProgressStatus(this.progressStatus);
		entity.setMerStatus(this.merStatus);
		entity.setDescription(this.description);
		entity.setSlowCause(this.slowCause);
		entity.setScope(this.scope);
		entity.setLineType(this.lineType);
		entity.setCableTypeId(this.cableTypeId);
		entity.setSurveyId(this.surveyId);
		entity.setLineLength(this.lineLength);
		entity.setCableMerPercent(this.cableMerPercent);
		entity.setPillarMerPercent(this.pillarMerPercent);
		entity.setExecuteGroup(this.executeGroup);
		entity.setExecuter(this.executer);
		entity.setExecutePhone(this.executePhone);
		entity.setIsInventory(this.isInventory);
		entity.setPmPer(this.pmPer);
		entity.setCmPer(this.cmPer);
		entity.setApConstrTypeId(this.apConstrTypeId);
		entity.setSubApConstrTypeId(this.subApConstrTypeId);
		entity.setMigrateDate(this.migrateDate);
		entity.setCatApProjectId(this.catApProjectId);
		entity.setAdslNum(this.adslNum);
		entity.setPstnNum(this.pstnNum);
		entity.setConstrTypeId(this.constrTypeId);
		entity.setCatProjectId(this.catProjectId);
		entity.setUpdateProcess(this.updateProcess);
		entity.setAcceptStatusLost(this.acceptStatusLost);
		entity.setIsApLine(this.isApLine);
		entity.setServiceProvideDate(this.serviceProvideDate);
		entity.setFinishTest(this.finishTest);
		entity.setPercentFinish(this.percentFinish);
		entity.setPreparedPremises(this.preparedPremises);
		entity.setHandoverMaterials(this.handoverMaterials);
		entity.setProgressCommitment(this.progressCommitment);
		entity.setIsHcqt(this.isHcqt);
		entity.setIsOff(this.isOff);
		entity.setIsAcceptedContract(this.isAcceptedContract);
		entity.setConstrLineType(this.constrLineType);
		entity.setLineLength1(this.lineLength1);
		entity.setIsFee(this.isFee);
		entity.setOnDate(this.onDate);
		entity.setUpdatorId(this.updatorId);
		entity.setHiringMonitorConsult(this.hiringMonitorConsult);
		entity.setIsEstimated(this.isEstimated);
		entity.setIsAcceptedHandoverUse(this.isAcceptedHandoverUse);
	}
	
	public Long getConstructId() {
		return constructId;
	}
	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}		
	public Long getConstrType() {
		return constrType;
	}
	public void setConstrType(Long constrType) {
		this.constrType = constrType;
	}		
	public String getConstrtCode() {
		return constrtCode;
	}
	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}		
	public String getConstrtName() {
		return constrtName;
	}
	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}		
	public String getConstrtAddress() {
		return constrtAddress;
	}
	public void setConstrtAddress(String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}		
	public Long getConstrtForm() {
		return constrtForm;
	}
	public void setConstrtForm(Long constrtForm) {
		this.constrtForm = constrtForm;
	}		
	public Long getSuperForm() {
		return superForm;
	}
	public void setSuperForm(Long superForm) {
		this.superForm = superForm;
	}		
	public Long getConstructorId() {
		return constructorId;
	}
	public void setConstructorId(Long constructorId) {
		this.constructorId = constructorId;
	}		
	public Long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}		
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}		
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}		
	public Long getSuggestorId() {
		return suggestorId;
	}
	public void setSuggestorId(Long suggestorId) {
		this.suggestorId = suggestorId;
	}		
	public Long getSuggestorGroupId() {
		return suggestorGroupId;
	}
	public void setSuggestorGroupId(Long suggestorGroupId) {
		this.suggestorGroupId = suggestorGroupId;
	}		
	public Date getSuggestDate() {
		return suggestDate;
	}
	public void setSuggestDate(Date suggestDate) {
		this.suggestDate = suggestDate;
	}		
	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}		
	public Long getSysGroupId() {
		return sysGroupId;
	}
	public void setSysGroupId(Long sysGroupId) {
		this.sysGroupId = sysGroupId;
	}		
	public Date getSysCreatedDate() {
		return sysCreatedDate;
	}
	public void setSysCreatedDate(Date sysCreatedDate) {
		this.sysCreatedDate = sysCreatedDate;
	}		
	public Long getInvestProjectId() {
		return investProjectId;
	}
	public void setInvestProjectId(Long investProjectId) {
		this.investProjectId = investProjectId;
	}		
	public Long getCatCurrencyId() {
		return catCurrencyId;
	}
	public void setCatCurrencyId(Long catCurrencyId) {
		this.catCurrencyId = catCurrencyId;
	}		
	public Date getExpectedCompleteDate() {
		return expectedCompleteDate;
	}
	public void setExpectedCompleteDate(Date expectedCompleteDate) {
		this.expectedCompleteDate = expectedCompleteDate;
	}		
	public String getStartComment() {
		return startComment;
	}
	public void setStartComment(String startComment) {
		this.startComment = startComment;
	}		
	public Long getProjectInvestComponentId() {
		return projectInvestComponentId;
	}
	public void setProjectInvestComponentId(Long projectInvestComponentId) {
		this.projectInvestComponentId = projectInvestComponentId;
	}		
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}		
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}		
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}		
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}		
	public Long getStartBy() {
		return startBy;
	}
	public void setStartBy(Long startBy) {
		this.startBy = startBy;
	}		
	public Long getUpdateStatusBy() {
		return updateStatusBy;
	}
	public void setUpdateStatusBy(Long updateStatusBy) {
		this.updateStatusBy = updateStatusBy;
	}		
	public Date getAcceptStartDate() {
		return acceptStartDate;
	}
	public void setAcceptStartDate(Date acceptStartDate) {
		this.acceptStartDate = acceptStartDate;
	}		
	public Date getAcceptFinishDate() {
		return acceptFinishDate;
	}
	public void setAcceptFinishDate(Date acceptFinishDate) {
		this.acceptFinishDate = acceptFinishDate;
	}		
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}		
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}		
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}		
	public Long getIsObstructed() {
		return isObstructed;
	}
	public void setIsObstructed(Long isObstructed) {
		this.isObstructed = isObstructed;
	}		
	public Date getLandTransferedDate() {
		return landTransferedDate;
	}
	public void setLandTransferedDate(Date landTransferedDate) {
		this.landTransferedDate = landTransferedDate;
	}		
	public Date getIntegratedDate() {
		return integratedDate;
	}
	public void setIntegratedDate(Date integratedDate) {
		this.integratedDate = integratedDate;
	}		
	public Date getEmissionDate() {
		return emissionDate;
	}
	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}		
	public Long getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Long isAccepted) {
		this.isAccepted = isAccepted;
	}		
	public Date getPreStartingDate() {
		return preStartingDate;
	}
	public void setPreStartingDate(Date preStartingDate) {
		this.preStartingDate = preStartingDate;
	}		
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}		
	public String getConstructorPerson() {
		return constructorPerson;
	}
	public void setConstructorPerson(String constructorPerson) {
		this.constructorPerson = constructorPerson;
	}		
	public String getConstructorPhone() {
		return constructorPhone;
	}
	public void setConstructorPhone(String constructorPhone) {
		this.constructorPhone = constructorPhone;
	}		
	public String getSupervisorPerson() {
		return supervisorPerson;
	}
	public void setSupervisorPerson(String supervisorPerson) {
		this.supervisorPerson = supervisorPerson;
	}		
	public String getSupervisorPhone() {
		return supervisorPhone;
	}
	public void setSupervisorPhone(String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}		
	public Long getProcedure() {
		return procedure;
	}
	public void setProcedure(Long procedure) {
		this.procedure = procedure;
	}		
	public Date getProcedureDate() {
		return procedureDate;
	}
	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}		
	public String getProcedureComment() {
		return procedureComment;
	}
	public void setProcedureComment(String procedureComment) {
		this.procedureComment = procedureComment;
	}		
	public Long getVibaFirst() {
		return vibaFirst;
	}
	public void setVibaFirst(Long vibaFirst) {
		this.vibaFirst = vibaFirst;
	}		
	public Date getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}		
	public String getHandoverComment() {
		return handoverComment;
	}
	public void setHandoverComment(String handoverComment) {
		this.handoverComment = handoverComment;
	}		
	public Long getLineMode() {
		return lineMode;
	}
	public void setLineMode(Long lineMode) {
		this.lineMode = lineMode;
	}		
	public String getCompleteComment() {
		return completeComment;
	}
	public void setCompleteComment(String completeComment) {
		this.completeComment = completeComment;
	}		
	public Date getLastUpdateProgress() {
		return lastUpdateProgress;
	}
	public void setLastUpdateProgress(Date lastUpdateProgress) {
		this.lastUpdateProgress = lastUpdateProgress;
	}		
	public Long getProjectType() {
		return projectType;
	}
	public void setProjectType(Long projectType) {
		this.projectType = projectType;
	}		
	public Long getPillarProgressPercent() {
		return pillarProgressPercent;
	}
	public void setPillarProgressPercent(Long pillarProgressPercent) {
		this.pillarProgressPercent = pillarProgressPercent;
	}		
	public Long getCableProgressPercent() {
		return cableProgressPercent;
	}
	public void setCableProgressPercent(Long cableProgressPercent) {
		this.cableProgressPercent = cableProgressPercent;
	}		
	public Long getTimePercent() {
		return timePercent;
	}
	public void setTimePercent(Long timePercent) {
		this.timePercent = timePercent;
	}		
	public Long getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(Long progressStatus) {
		this.progressStatus = progressStatus;
	}		
	public Long getMerStatus() {
		return merStatus;
	}
	public void setMerStatus(Long merStatus) {
		this.merStatus = merStatus;
	}		
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}		
	public String getSlowCause() {
		return slowCause;
	}
	public void setSlowCause(String slowCause) {
		this.slowCause = slowCause;
	}		
	public Long getScope() {
		return scope;
	}
	public void setScope(Long scope) {
		this.scope = scope;
	}		
	public Long getLineType() {
		return lineType;
	}
	public void setLineType(Long lineType) {
		this.lineType = lineType;
	}		
	public Long getCableTypeId() {
		return cableTypeId;
	}
	public void setCableTypeId(Long cableTypeId) {
		this.cableTypeId = cableTypeId;
	}		
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}		
	public Long getLineLength() {
		return lineLength;
	}
	public void setLineLength(Long lineLength) {
		this.lineLength = lineLength;
	}		
	public Long getCableMerPercent() {
		return cableMerPercent;
	}
	public void setCableMerPercent(Long cableMerPercent) {
		this.cableMerPercent = cableMerPercent;
	}		
	public Long getPillarMerPercent() {
		return pillarMerPercent;
	}
	public void setPillarMerPercent(Long pillarMerPercent) {
		this.pillarMerPercent = pillarMerPercent;
	}		
	public Long getExecuteGroup() {
		return executeGroup;
	}
	public void setExecuteGroup(Long executeGroup) {
		this.executeGroup = executeGroup;
	}		
	public String getExecuter() {
		return executer;
	}
	public void setExecuter(String executer) {
		this.executer = executer;
	}		
	public String getExecutePhone() {
		return executePhone;
	}
	public void setExecutePhone(String executePhone) {
		this.executePhone = executePhone;
	}		
	public Long getIsInventory() {
		return isInventory;
	}
	public void setIsInventory(Long isInventory) {
		this.isInventory = isInventory;
	}		
	public String getPmPer() {
		return pmPer;
	}
	public void setPmPer(String pmPer) {
		this.pmPer = pmPer;
	}		
	public String getCmPer() {
		return cmPer;
	}
	public void setCmPer(String cmPer) {
		this.cmPer = cmPer;
	}		
	public Long getApConstrTypeId() {
		return apConstrTypeId;
	}
	public void setApConstrTypeId(Long apConstrTypeId) {
		this.apConstrTypeId = apConstrTypeId;
	}		
	public Long getSubApConstrTypeId() {
		return subApConstrTypeId;
	}
	public void setSubApConstrTypeId(Long subApConstrTypeId) {
		this.subApConstrTypeId = subApConstrTypeId;
	}		
	public Date getMigrateDate() {
		return migrateDate;
	}
	public void setMigrateDate(Date migrateDate) {
		this.migrateDate = migrateDate;
	}		
	public Long getCatApProjectId() {
		return catApProjectId;
	}
	public void setCatApProjectId(Long catApProjectId) {
		this.catApProjectId = catApProjectId;
	}		
	public Long getAdslNum() {
		return adslNum;
	}
	public void setAdslNum(Long adslNum) {
		this.adslNum = adslNum;
	}		
	public Long getPstnNum() {
		return pstnNum;
	}
	public void setPstnNum(Long pstnNum) {
		this.pstnNum = pstnNum;
	}		
	public Long getConstrTypeId() {
		return constrTypeId;
	}
	public void setConstrTypeId(Long constrTypeId) {
		this.constrTypeId = constrTypeId;
	}		
	public Long getCatProjectId() {
		return catProjectId;
	}
	public void setCatProjectId(Long catProjectId) {
		this.catProjectId = catProjectId;
	}		
	public Long getUpdateProcess() {
		return updateProcess;
	}
	public void setUpdateProcess(Long updateProcess) {
		this.updateProcess = updateProcess;
	}		
	public Long getAcceptStatusLost() {
		return acceptStatusLost;
	}
	public void setAcceptStatusLost(Long acceptStatusLost) {
		this.acceptStatusLost = acceptStatusLost;
	}		
	public Long getIsApLine() {
		return isApLine;
	}
	public void setIsApLine(Long isApLine) {
		this.isApLine = isApLine;
	}		
	public Date getServiceProvideDate() {
		return serviceProvideDate;
	}
	public void setServiceProvideDate(Date serviceProvideDate) {
		this.serviceProvideDate = serviceProvideDate;
	}		
	public Long getFinishTest() {
		return finishTest;
	}
	public void setFinishTest(Long finishTest) {
		this.finishTest = finishTest;
	}		
	public Long getPercentFinish() {
		return percentFinish;
	}
	public void setPercentFinish(Long percentFinish) {
		this.percentFinish = percentFinish;
	}		
	public Long getPreparedPremises() {
		return preparedPremises;
	}
	public void setPreparedPremises(Long preparedPremises) {
		this.preparedPremises = preparedPremises;
	}		
	public Long getHandoverMaterials() {
		return handoverMaterials;
	}
	public void setHandoverMaterials(Long handoverMaterials) {
		this.handoverMaterials = handoverMaterials;
	}		
	public Long getProgressCommitment() {
		return progressCommitment;
	}
	public void setProgressCommitment(Long progressCommitment) {
		this.progressCommitment = progressCommitment;
	}		
	public Long getIsHcqt() {
		return isHcqt;
	}
	public void setIsHcqt(Long isHcqt) {
		this.isHcqt = isHcqt;
	}		
	public Long getIsOff() {
		return isOff;
	}
	public void setIsOff(Long isOff) {
		this.isOff = isOff;
	}		
	public Long getIsAcceptedContract() {
		return isAcceptedContract;
	}
	public void setIsAcceptedContract(Long isAcceptedContract) {
		this.isAcceptedContract = isAcceptedContract;
	}		
	public Long getConstrLineType() {
		return constrLineType;
	}
	public void setConstrLineType(Long constrLineType) {
		this.constrLineType = constrLineType;
	}		
	public Long getLineLength1() {
		return lineLength1;
	}
	public void setLineLength1(Long lineLength1) {
		this.lineLength1 = lineLength1;
	}		
	public Long getIsFee() {
		return isFee;
	}
	public void setIsFee(Long isFee) {
		this.isFee = isFee;
	}		
	public Date getOnDate() {
		return onDate;
	}
	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}		
	public Long getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}		
	public Long getHiringMonitorConsult() {
		return hiringMonitorConsult;
	}
	public void setHiringMonitorConsult(Long hiringMonitorConsult) {
		this.hiringMonitorConsult = hiringMonitorConsult;
	}		
	public Long getIsEstimated() {
		return isEstimated;
	}
	public void setIsEstimated(Long isEstimated) {
		this.isEstimated = isEstimated;
	}		
	public Long getIsAcceptedHandoverUse() {
		return isAcceptedHandoverUse;
	}
	public void setIsAcceptedHandoverUse(Long isAcceptedHandoverUse) {
		this.isAcceptedHandoverUse = isAcceptedHandoverUse;
	}		
	
}
