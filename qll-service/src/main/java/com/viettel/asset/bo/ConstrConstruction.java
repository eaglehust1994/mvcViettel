package com.viettel.asset.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONSTR_CONSTRUCTIONS")
public class ConstrConstruction implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String CONSTRUCT_ID = "constructId";
        public static final String CONSTR_TYPE = "constrType";
        public static final String CONSTRT_CODE = "constrtCode";
        public static final String CONSTRT_NAME = "constrtName";
        public static final String CONSTRT_ADDRESS = "constrtAddress";
        public static final String CONSTRT_FORM = "constrtForm";
        public static final String SUPER_FORM = "superForm";
        public static final String CONSTRUCTOR_ID = "constructorId";
        public static final String SUPERVISOR_ID = "supervisorId";
        public static final String STARTING_DATE = "startingDate";
        public static final String COMPLETE_DATE = "completeDate";
        public static final String SUGGESTOR_ID = "suggestorId";
        public static final String SUGGESTOR_GROUP_ID = "suggestorGroupId";
        public static final String SUGGEST_DATE = "suggestDate";
        public static final String SYS_USER_ID = "sysUserId";
        public static final String SYS_GROUP_ID = "sysGroupId";
        public static final String SYS_CREATED_DATE = "sysCreatedDate";
        public static final String INVEST_PROJECT_ID = "investProjectId";
        public static final String CAT_CURRENCY_ID = "catCurrencyId";
        public static final String EXPECTED_COMPLETE_DATE = "expectedCompleteDate";
        public static final String START_COMMENT = "startComment";
        public static final String PROJECT_INVEST_COMPONENT_ID = "projectInvestComponentId";
        public static final String IS_ACTIVE = "isActive";
        public static final String PROVINCE_ID = "provinceId";
        public static final String DISTRICT_ID = "districtId";
        public static final String STATION_ID = "stationId";
        public static final String START_BY = "startBy";
        public static final String UPDATE_STATUS_BY = "updateStatusBy";
        public static final String ACCEPT_START_DATE = "acceptStartDate";
        public static final String ACCEPT_FINISH_DATE = "acceptFinishDate";
        public static final String PLAN_ID = "planId";
        public static final String ADD_TIME = "addTime";
        public static final String STATUS_ID = "statusId";
        public static final String IS_OBSTRUCTED = "isObstructed";
        public static final String LAND_TRANSFERED_DATE = "landTransferedDate";
        public static final String INTEGRATED_DATE = "integratedDate";
        public static final String EMISSION_DATE = "emissionDate";
        public static final String IS_ACCEPTED = "isAccepted";
        public static final String PRE_STARTING_DATE = "preStartingDate";
        public static final String STATUS = "status";
        public static final String CONSTRUCTOR_PERSON = "constructorPerson";
        public static final String CONSTRUCTOR_PHONE = "constructorPhone";
        public static final String SUPERVISOR_PERSON = "supervisorPerson";
        public static final String SUPERVISOR_PHONE = "supervisorPhone";
        public static final String PROCEDURE = "procedure";
        public static final String PROCEDURE_DATE = "procedureDate";
        public static final String PROCEDURE_COMMENT = "procedureComment";
        public static final String VIBA_FIRST = "vibaFirst";
        public static final String HANDOVER_DATE = "handoverDate";
        public static final String HANDOVER_COMMENT = "handoverComment";
        public static final String LINE_MODE = "lineMode";
        public static final String COMPLETE_COMMENT = "completeComment";
        public static final String LAST_UPDATE_PROGRESS = "lastUpdateProgress";
        public static final String PROJECT_TYPE = "projectType";
        public static final String PILLAR_PROGRESS_PERCENT = "pillarProgressPercent";
        public static final String CABLE_PROGRESS_PERCENT = "cableProgressPercent";
        public static final String TIME_PERCENT = "timePercent";
        public static final String PROGRESS_STATUS = "progressStatus";
        public static final String MER_STATUS = "merStatus";
        public static final String DESCRIPTION = "description";
        public static final String SLOW_CAUSE = "slowCause";
        public static final String SCOPE = "scope";
        public static final String LINE_TYPE = "lineType";
        public static final String CABLE_TYPE_ID = "cableTypeId";
        public static final String SURVEY_ID = "surveyId";
        public static final String LINE_LENGTH = "lineLength";
        public static final String CABLE_MER_PERCENT = "cableMerPercent";
        public static final String PILLAR_MER_PERCENT = "pillarMerPercent";
        public static final String EXECUTE_GROUP = "executeGroup";
        public static final String EXECUTER = "executer";
        public static final String EXECUTE_PHONE = "executePhone";
        public static final String IS_INVENTORY = "isInventory";
        public static final String PM_PER = "pmPer";
        public static final String CM_PER = "cmPer";
        public static final String AP_CONSTR_TYPE_ID = "apConstrTypeId";
        public static final String SUB_AP_CONSTR_TYPE_ID = "subApConstrTypeId";
        public static final String MIGRATE_DATE = "migrateDate";
        public static final String CAT_AP_PROJECT_ID = "catApProjectId";
        public static final String ADSL_NUM = "adslNum";
        public static final String PSTN_NUM = "pstnNum";
        public static final String CONSTR_TYPE_ID = "constrTypeId";
        public static final String CAT_PROJECT_ID = "catProjectId";
        public static final String UPDATE_PROCESS = "updateProcess";
        public static final String ACCEPT_STATUS_LOST = "acceptStatusLost";
        public static final String IS_AP_LINE = "isApLine";
        public static final String SERVICE_PROVIDE_DATE = "serviceProvideDate";
        public static final String FINISH_TEST = "finishTest";
        public static final String PERCENT_FINISH = "percentFinish";
        public static final String PREPARED_PREMISES = "preparedPremises";
        public static final String HANDOVER_MATERIALS = "handoverMaterials";
        public static final String PROGRESS_COMMITMENT = "progressCommitment";
        public static final String IS_HCQT = "isHcqt";
        public static final String IS_OFF = "isOff";
        public static final String IS_ACCEPTED_CONTRACT = "isAcceptedContract";
        public static final String CONSTR_LINE_TYPE = "constrLineType";
        public static final String LINE_LENGTH1 = "lineLength1";
        public static final String IS_FEE = "isFee";
        public static final String ON_DATE = "onDate";
        public static final String UPDATOR_ID = "updatorId";
        public static final String HIRING_MONITOR_CONSULT = "hiringMonitorConsult";
        public static final String IS_ESTIMATED = "isEstimated";
        public static final String IS_ACCEPTED_HANDOVER_USE = "isAcceptedHandoverUse";

    }

    public interface Constants {

        public static final String TABLE_NAME = "CONSTR_CONSTRUCTIONS";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "CONSTR_CONSTRUCTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "CONSTRUCT_ID")
    private Long constructId;
    @Column(name = "CONSTR_TYPE")
    private Long constrType;
    @Column(name = "CONSTRT_CODE")
    private String constrtCode;
    @Column(name = "CONSTRT_NAME")
    private String constrtName;
    @Column(name = "CONSTRT_ADDRESS")
    private String constrtAddress;
    @Column(name = "CONSTRT_FORM")
    private Long constrtForm;
    @Column(name = "SUPER_FORM")
    private Long superForm;
    @Column(name = "CONSTRUCTOR_ID")
    private Long constructorId;
    @Column(name = "SUPERVISOR_ID")
    private Long supervisorId;
    @Column(name = "STARTING_DATE")
    private Date startingDate;
    @Column(name = "COMPLETE_DATE")
    private Date completeDate;
    @Column(name = "SUGGESTOR_ID")
    private Long suggestorId;
    @Column(name = "SUGGESTOR_GROUP_ID")
    private Long suggestorGroupId;
    @Column(name = "SUGGEST_DATE")
    private Date suggestDate;
    @Column(name = "SYS_USER_ID")
    private Long sysUserId;
    @Column(name = "SYS_GROUP_ID")
    private Long sysGroupId;
    @Column(name = "SYS_CREATED_DATE")
    private Date sysCreatedDate;
    @Column(name = "INVEST_PROJECT_ID")
    private Long investProjectId;
    @Column(name = "CAT_CURRENCY_ID")
    private Long catCurrencyId;
    @Column(name = "EXPECTED_COMPLETE_DATE")
    private Date expectedCompleteDate;
    @Column(name = "START_COMMENT")
    private String startComment;
    @Column(name = "PROJECT_INVEST_COMPONENT_ID")
    private Long projectInvestComponentId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "DISTRICT_ID")
    private Long districtId;
    @Column(name = "STATION_ID")
    private Long stationId;
    @Column(name = "START_BY")
    private Long startBy;
    @Column(name = "UPDATE_STATUS_BY")
    private Long updateStatusBy;
    @Column(name = "ACCEPT_START_DATE")
    private Date acceptStartDate;
    @Column(name = "ACCEPT_FINISH_DATE")
    private Date acceptFinishDate;
    @Column(name = "PLAN_ID")
    private Long planId;
    @Column(name = "ADD_TIME")
    private Date addTime;
    @Column(name = "STATUS_ID")
    private Long statusId;
    @Column(name = "IS_OBSTRUCTED")
    private Long isObstructed;
    @Column(name = "LAND_TRANSFERED_DATE")
    private Date landTransferedDate;
    @Column(name = "INTEGRATED_DATE")
    private Date integratedDate;
    @Column(name = "EMISSION_DATE")
    private Date emissionDate;
    @Column(name = "IS_ACCEPTED")
    private Long isAccepted;
    @Column(name = "PRE_STARTING_DATE")
    private Date preStartingDate;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CONSTRUCTOR_PERSON")
    private String constructorPerson;
    @Column(name = "CONSTRUCTOR_PHONE")
    private String constructorPhone;
    @Column(name = "SUPERVISOR_PERSON")
    private String supervisorPerson;
    @Column(name = "SUPERVISOR_PHONE")
    private String supervisorPhone;
    @Column(name = "PROCEDURE")
    private Long procedure;
    @Column(name = "PROCEDURE_DATE")
    private Date procedureDate;
    @Column(name = "PROCEDURE_COMMENT")
    private String procedureComment;
    @Column(name = "VIBA_FIRST")
    private Long vibaFirst;
    @Column(name = "HANDOVER_DATE")
    private Date handoverDate;
    @Column(name = "HANDOVER_COMMENT")
    private String handoverComment;
    @Column(name = "LINE_MODE")
    private Long lineMode;
    @Column(name = "COMPLETE_COMMENT")
    private String completeComment;
    @Column(name = "LAST_UPDATE_PROGRESS")
    private Date lastUpdateProgress;
    @Column(name = "PROJECT_TYPE")
    private Long projectType;
    @Column(name = "PILLAR_PROGRESS_PERCENT")
    private Long pillarProgressPercent;
    @Column(name = "CABLE_PROGRESS_PERCENT")
    private Long cableProgressPercent;
    @Column(name = "TIME_PERCENT")
    private Long timePercent;
    @Column(name = "PROGRESS_STATUS")
    private Long progressStatus;
    @Column(name = "MER_STATUS")
    private Long merStatus;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SLOW_CAUSE")
    private String slowCause;
    @Column(name = "SCOPE")
    private Long scope;
    @Column(name = "LINE_TYPE")
    private Long lineType;
    @Column(name = "CABLE_TYPE_ID")
    private Long cableTypeId;
    @Column(name = "SURVEY_ID")
    private Long surveyId;
    @Column(name = "LINE_LENGTH")
    private Long lineLength;
    @Column(name = "CABLE_MER_PERCENT")
    private Long cableMerPercent;
    @Column(name = "PILLAR_MER_PERCENT")
    private Long pillarMerPercent;
    @Column(name = "EXECUTE_GROUP")
    private Long executeGroup;
    @Column(name = "EXECUTER")
    private String executer;
    @Column(name = "EXECUTE_PHONE")
    private String executePhone;
    @Column(name = "IS_INVENTORY")
    private Long isInventory;
    @Column(name = "PM_PER")
    private String pmPer;
    @Column(name = "CM_PER")
    private String cmPer;
    @Column(name = "AP_CONSTR_TYPE_ID")
    private Long apConstrTypeId;
    @Column(name = "SUB_AP_CONSTR_TYPE_ID")
    private Long subApConstrTypeId;
    @Column(name = "MIGRATE_DATE")
    private Date migrateDate;
    @Column(name = "CAT_AP_PROJECT_ID")
    private Long catApProjectId;
    @Column(name = "ADSL_NUM")
    private Long adslNum;
    @Column(name = "PSTN_NUM")
    private Long pstnNum;
    @Column(name = "CONSTR_TYPE_ID")
    private Long constrTypeId;
    @Column(name = "CAT_PROJECT_ID")
    private Long catProjectId;
    @Column(name = "UPDATE_PROCESS")
    private Long updateProcess;
    @Column(name = "ACCEPT_STATUS_LOST")
    private Long acceptStatusLost;
    @Column(name = "IS_AP_LINE")
    private Long isApLine;
    @Column(name = "SERVICE_PROVIDE_DATE")
    private Date serviceProvideDate;
    @Column(name = "FINISH_TEST")
    private Long finishTest;
    @Column(name = "PERCENT_FINISH")
    private Long percentFinish;
    @Column(name = "PREPARED_PREMISES")
    private Long preparedPremises;
    @Column(name = "HANDOVER_MATERIALS")
    private Long handoverMaterials;
    @Column(name = "PROGRESS_COMMITMENT")
    private Long progressCommitment;
    @Column(name = "IS_HCQT")
    private Long isHcqt;
    @Column(name = "IS_OFF")
    private Long isOff;
    @Column(name = "IS_ACCEPTED_CONTRACT")
    private Long isAcceptedContract;
    @Column(name = "CONSTR_LINE_TYPE")
    private Long constrLineType;
    @Column(name = "LINE_LENGTH1")
    private Long lineLength1;
    @Column(name = "IS_FEE")
    private Long isFee;
    @Column(name = "ON_DATE")
    private Date onDate;
    @Column(name = "UPDATOR_ID")
    private Long updatorId;
    @Column(name = "HIRING_MONITOR_CONSULT")
    private Long hiringMonitorConsult;
    @Column(name = "IS_ESTIMATED")
    private Long isEstimated;
    @Column(name = "IS_ACCEPTED_HANDOVER_USE")
    private Long isAcceptedHandoverUse;

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
