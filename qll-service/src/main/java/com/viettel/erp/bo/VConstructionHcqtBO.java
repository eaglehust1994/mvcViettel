/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "v_constructions_hcqt")
@Table(name = "V_CONSTRUCTION_HCQT")
@Where( clause = "IS_ACTIVE = '1'" )
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class VConstructionHcqtBO extends BaseFWModelImpl {

	
	
//private List<ConstructionAcceptanceBO> constructionacceptance = Lists.newArrayList();
//private List<SettlementRightBO> settlementright = Lists.newArrayList();

private java.lang.Double Id;
private java.lang.Double catProjectId;
private java.lang.String catApProjectName;
private java.lang.Long constructId;
private java.lang.String constrtCode;
private java.lang.Double constrType;
private java.lang.String constrTypeName;
private java.lang.String constrTypeCode;
private java.lang.String constrtName;
private java.lang.String constrtAddress;
private java.lang.Double constructorId;
private java.lang.Double supervisorId;
private java.util.Date startingDate;
private java.util.Date completeDate;
private java.lang.Double sysUserId;
private java.lang.Double catApProjectId;
private java.lang.String sysUserName;
private java.lang.Double sysGroupId;
private java.lang.String sysGroupName;
private java.util.Date sysCreatedDate;
private java.lang.Double status;
private java.util.Date expectedCompleteDate;
private java.lang.String startComment;
private java.lang.Double isActive;
private java.lang.Double provinceId;
private java.lang.Double districtId;
private java.lang.Double investProjectId;
private java.lang.String investProjectName;
private java.lang.String investProjectCode;

private java.lang.Double stationId;
private java.util.Date serviceProvideDate;
private java.lang.String districtCode;
private java.lang.String districtName;
private java.util.Date handoverDate;
private java.lang.String stationCode;
private java.lang.String stationAddress;
private java.lang.String provinceCode;
private java.lang.String constructorName;
private java.lang.String supervisorName;
private java.lang.String provinceName;
private java.lang.Double superForm;
private java.lang.Double constrtForm;
private java.lang.Double isObstructed;
private java.lang.Double statusId;
private java.lang.String statusName;
private java.lang.String statusCode;
private java.lang.Double isAccepted;
private java.util.Date preStartingDate;
/*private java.util.Date acceptStartDate;
private java.util.Date acceptFinishDate;*/
private java.lang.Double lineMode;
private java.lang.Double projectType;
private java.lang.String constructorPerson;
private java.lang.String constructorPhone;
private java.lang.String supervisorPerson;
private java.lang.String supervisorPhone;

private java.lang.Double cableTypeId;
private java.lang.Double lineLength;
private java.lang.Double lineType;

private java.lang.String executer;

private java.lang.Double executeGroup;
private java.lang.String executePhone;
private java.lang.Double finishTest;
private java.lang.Double percentFinish;
private java.lang.Double preparedPremises;
private java.lang.Double handoverMaterials;
private java.lang.Double progressCommitment;
private java.lang.String contractCode;

private java.lang.String contractName;

private java.util.Date signed_date;
private java.lang.Double contract_total_value;
private java.lang.Double contractId;
private java.lang.Double isAcceptedContract;
private java.lang.Double isOff;

private java.lang.Double hiring_monitor_consult;



 public VConstructionHcqtBO() {
        setColId("Id");
        setColName("Id");
        setUniqueColumn(new String[]{"Id"});
}
 public VConstructionHcqtBO(Long constructId) {
     this.constructId  = constructId;
}

@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Id
@Column(name = "ID", length = 22)
public java.lang.Double getId() {
	return Id;
}
public void setId(java.lang.Double id) {
	Id = id;
}

@Column(name = "CAT_PROJECT_ID", length = 22)
public java.lang.Double getCatProjectId(){
return catProjectId;
}
public void setCatProjectId(java.lang.Double catProjectId)
{
this.catProjectId = catProjectId;
}
@Column(name = "CAT_AP_PROJECT_NAME", length = 600)
public java.lang.String getCatApProjectName(){
return catApProjectName;
}
public void setCatApProjectName(java.lang.String catApProjectName)
{
this.catApProjectName = catApProjectName;
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
public java.lang.Double getConstrType(){
return constrType;
}
public void setConstrType(java.lang.Double constrType)
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
@Column(name = "CONSTR_TYPE_CODE", length = 50)
public java.lang.String getConstrTypeCode(){
return constrTypeCode;
}
public void setConstrTypeCode(java.lang.String constrTypeCode)
{
this.constrTypeCode = constrTypeCode;
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
@Column(name = "CONSTRUCTOR_ID", length = 22)
public java.lang.Double getConstructorId(){
return constructorId;
}
public void setConstructorId(java.lang.Double constructorId)
{
this.constructorId = constructorId;
}
@Column(name = "SUPERVISOR_ID", length = 22)
public java.lang.Double getSupervisorId(){
return supervisorId;
}
public void setSupervisorId(java.lang.Double supervisorId)
{
this.supervisorId = supervisorId;
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
@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Double getSysUserId(){
return sysUserId;
}
public void setSysUserId(java.lang.Double sysUserId)
{
this.sysUserId = sysUserId;
}
@Column(name = "CAT_AP_PROJECT_ID", length = 22)
public java.lang.Double getCatApProjectId(){
return catApProjectId;
}
public void setCatApProjectId(java.lang.Double catApProjectId)
{
this.catApProjectId = catApProjectId;
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
public java.lang.Double getSysGroupId(){
return sysGroupId;
}
public void setSysGroupId(java.lang.Double sysGroupId)
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
public java.lang.Double getStatus(){
return status;
}
public void setStatus(java.lang.Double status)
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
public java.lang.Double getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Double isActive)
{
this.isActive = isActive;
}
@Column(name = "PROVINCE_ID", length = 22)
public java.lang.Double getProvinceId(){
return provinceId;
}
public void setProvinceId(java.lang.Double provinceId)
{
this.provinceId = provinceId;
}
@Column(name = "DISTRICT_ID", length = 22)
public java.lang.Double getDistrictId(){
return districtId;
}
public void setDistrictId(java.lang.Double districtId)
{
this.districtId = districtId;
}
@Column(name = "INVEST_PROJECT_ID", length = 22)
public java.lang.Double getInvestProjectId(){
return investProjectId;
}
public void setInvestProjectId(java.lang.Double investProjectId)
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
@Column(name = "STATION_ID", length = 22)
public java.lang.Double getStationId(){
return stationId;
}
public void setStationId(java.lang.Double stationId)
{
this.stationId = stationId;
}
@Column(name = "SERVICE_PROVIDE_DATE", length = 7)
public java.util.Date getServiceProvideDate(){
return serviceProvideDate;
}
public void setServiceProvideDate(java.util.Date serviceProvideDate)
{
this.serviceProvideDate = serviceProvideDate;
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
@Column(name = "HANDOVER_DATE", length = 7)
public java.util.Date getHandoverDate(){
return handoverDate;
}
public void setHandoverDate(java.util.Date handoverDate)
{
this.handoverDate = handoverDate;
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
@Column(name = "PROVINCE_CODE", length = 40)
public java.lang.String getProvinceCode(){
return provinceCode;
}
public void setProvinceCode(java.lang.String provinceCode)
{
this.provinceCode = provinceCode;
}
@Column(name = "CONSTRUCTOR_NAME", length = 1400)
public java.lang.String getConstructorName(){
return constructorName;
}
public void setConstructorName(java.lang.String constructorName)
{
this.constructorName = constructorName;
}
@Column(name = "SUPERVISOR_NAME", length = 100)
public java.lang.String getSupervisorName(){
return supervisorName;
}
public void setSupervisorName(java.lang.String supervisorName)
{
this.supervisorName = supervisorName;
}
@Column(name = "PROVINCE_NAME", length = 22)
public java.lang.String getProvinceName(){
return provinceName;
}
public void setProvinceName(java.lang.String provinceName)
{
this.provinceName = provinceName;
}
@Column(name = "SUPER_FORM", length = 22)
public java.lang.Double getSuperForm(){
return superForm;
}
public void setSuperForm(java.lang.Double superForm)
{
this.superForm = superForm;
}
@Column(name = "CONSTRT_FORM", length = 22)
public java.lang.Double getConstrtForm(){
return constrtForm;
}
public void setConstrtForm(java.lang.Double constrtForm)
{
this.constrtForm = constrtForm;
}
@Column(name = "IS_OBSTRUCTED", length = 22)
public java.lang.Double getIsObstructed(){
return isObstructed;
}
public void setIsObstructed(java.lang.Double isObstructed)
{
this.isObstructed = isObstructed;
}
@Column(name = "STATUS_ID", length = 400)
public java.lang.Double getStatusId(){
return statusId;
}
public void setStatusId(java.lang.Double statusId)
{
this.statusId = statusId;
}
@Column(name = "STATUS_NAME", length = 20)
public java.lang.String getStatusName(){
return statusName;
}
public void setStatusName(java.lang.String statusName)
{
this.statusName = statusName;
}
@Column(name = "STATUS_CODE", length = 22)
public java.lang.String getStatusCode(){
return statusCode;
}
public void setStatusCode(java.lang.String statusCode)
{
this.statusCode = statusCode;
}
@Column(name = "IS_ACCEPTED", length = 7)
public java.lang.Double getIsAccepted(){
return isAccepted;
}
public void setIsAccepted(java.lang.Double isAccepted)
{
this.isAccepted = isAccepted;
}
@Column(name = "PRE_STARTING_DATE", length = 7)
public java.util.Date getPreStartingDate(){
return preStartingDate;
}
public void setPreStartingDate(java.util.Date preStartingDate)
{
this.preStartingDate = preStartingDate;
}
/*@Column(name = "ACCEPT_START_DATE", length = 7)
public java.util.Date getAcceptStartDate(){
return acceptStartDate;
}
public void setAcceptStartDate(java.util.Date acceptStartDate)
{
this.acceptStartDate = acceptStartDate;
}
@Column(name = "ACCEPT_FINISH_DATE", length = 22)
public java.util.Date getAcceptFinishDate(){
return acceptFinishDate;
}
public void setAcceptFinishDate(java.util.Date acceptFinishDate)
{
this.acceptFinishDate = acceptFinishDate;
}*/
@Column(name = "LINE_MODE", length = 22)
public java.lang.Double getLineMode(){
return lineMode;
}
public void setLineMode(java.lang.Double lineMode)
{
this.lineMode = lineMode;
}
@Column(name = "PROJECT_TYPE", length = 200)
public java.lang.Double getProjectType(){
return projectType;
}
public void setProjectType(java.lang.Double projectType)
{
this.projectType = projectType;
}
@Column(name = "CONSTRUCTOR_PERSON", length = 40)
public java.lang.String getConstructorPerson(){
return constructorPerson;
}
public void setConstructorPerson(java.lang.String constructorPerson)
{
this.constructorPerson = constructorPerson;
}
@Column(name = "CONSTRUCTOR_PHONE", length = 200)
public java.lang.String getConstructorPhone(){
return constructorPhone;
}
public void setConstructorPhone(java.lang.String constructorPhone)
{
this.constructorPhone = constructorPhone;
}
@Column(name = "SUPERVISOR_PERSON", length = 20)
public java.lang.String getSupervisorPerson(){
return supervisorPerson;
}
public void setSupervisorPerson(java.lang.String supervisorPerson)
{
this.supervisorPerson = supervisorPerson;
}
@Column(name = "SUPERVISOR_PHONE", length = 22)
public java.lang.String getSupervisorPhone(){
return supervisorPhone;
}
public void setSupervisorPhone(java.lang.String supervisorPhone)
{
this.supervisorPhone = supervisorPhone;
}
@Column(name = "CABLE_TYPE_ID", length = 22)
public java.lang.Double getCableTypeId(){
return cableTypeId;
}
public void setCableTypeId(java.lang.Double cableTypeId)
{
this.cableTypeId = cableTypeId;
}
@Column(name = "LINE_LENGTH", length = 22)
public java.lang.Double getLineLength(){
return lineLength;
}
public void setLineLength(java.lang.Double lineLength)
{
this.lineLength = lineLength;
}
@Column(name = "LINE_TYPE", length = 100)
public java.lang.Double getLineType(){
return lineType;
}
public void setLineType(java.lang.Double lineType)
{
this.lineType = lineType;
}
@Column(name = "EXECUTER", length = 22)
public java.lang.String getExecuter(){
return executer;
}
public void setExecuter(java.lang.String executer)
{
this.executer = executer;
}
@Column(name = "EXECUTE_GROUP", length = 30)
public java.lang.Double getExecuteGroup(){
return executeGroup;
}
public void setExecuteGroup(java.lang.Double executeGroup)
{
this.executeGroup = executeGroup;
}
@Column(name = "EXECUTE_PHONE", length = 22)
public java.lang.String getExecutePhone(){
return executePhone;
}
public void setExecutePhone(java.lang.String executePhone)
{
this.executePhone = executePhone;
}
@Column(name = "FINISH_TEST", length = 22)
public java.lang.Double getFinishTest(){
return finishTest;
}
public void setFinishTest(java.lang.Double finishTest)
{
this.finishTest = finishTest;
}
@Column(name = "PERCENT_FINISH", length = 22)
public java.lang.Double getPercentFinish(){
return percentFinish;
}
public void setPercentFinish(java.lang.Double percentFinish)
{
this.percentFinish = percentFinish;
}
@Column(name = "PREPARED_PREMISES", length = 22)
public java.lang.Double getPreparedPremises(){
return preparedPremises;
}
public void setPreparedPremises(java.lang.Double preparedPremises)
{
this.preparedPremises = preparedPremises;
}
@Column(name = "HANDOVER_MATERIALS", length = 22)
public java.lang.Double getHandoverMaterials(){
return handoverMaterials;
}
public void setHandoverMaterials(java.lang.Double handoverMaterials)
{
this.handoverMaterials = handoverMaterials;
}
@Column(name = "PROGRESS_COMMITMENT", length = 1000)
public java.lang.Double getProgressCommitment(){
return progressCommitment;
}
public void setProgressCommitment(java.lang.Double progressCommitment)
{
this.progressCommitment = progressCommitment;
}
@Column(name = "CONTRACT_CODE", length = 1000)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "CONTRACT_NAME", length = 1000)
public java.lang.String getContractName() {
	return contractName;
}

public void setContractName(java.lang.String contractName) {
	this.contractName = contractName;
}
@Column(name = "SIGNED_DATE", length = 7)
public java.util.Date getSigned_date() {
	return signed_date;
}

public void setSigned_date(java.util.Date signed_date) {
	this.signed_date = signed_date;
}

@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Double getContractId(){
return contractId;
}
public void setContractId(java.lang.Double contractId)
{
this.contractId = contractId;
}
@Column(name = "IS_ACCEPTED_CONTRACT", length = 22)
public java.lang.Double getIsAcceptedContract(){
return isAcceptedContract;
}
public void setIsAcceptedContract(java.lang.Double isAcceptedContract)
{
this.isAcceptedContract = isAcceptedContract;
}
@Column(name = "IS_OFF", length = 22)
public java.lang.Double getIsOff(){
return isOff;
}
public void setIsOff(java.lang.Double isOff)
{
this.isOff = isOff;
}
   
@Column(name = "CONTRACT_TOTAL_VALUE", length = 22)
public java.lang.Double getContract_total_value() {
	return contract_total_value;
}
public void setContract_total_value(java.lang.Double contract_total_value) {
	this.contract_total_value = contract_total_value;
}
/*@OneToMany(mappedBy = "vconstructionHcqt" , fetch = FetchType.LAZY, orphanRemoval = true)
public List<ConstructionAcceptanceBO> getConstructionacceptance() {
	return constructionacceptance;
}
public void setConstructionacceptance(List<ConstructionAcceptanceBO> constructionacceptance) {
	this.constructionacceptance = constructionacceptance;
}*/
/*@OneToMany(mappedBy = "vconstructionhcqt", fetch = FetchType.LAZY)
public List<SettlementRightBO> getSettlementright() {
	return settlementright;
}
public void setSettlementright(List<SettlementRightBO> settlementright) {
	this.settlementright = settlementright;
}*/


@Column(name = "HIRING_MONITOR_CONSULT", length = 22)
public java.lang.Double getHiring_monitor_consult() {
	return hiring_monitor_consult;
}
public void setHiring_monitor_consult(java.lang.Double hiring_monitor_consult) {
	this.hiring_monitor_consult = hiring_monitor_consult;
}


    @Override
    public VConstructionHcqtDTO toDTO() {
        VConstructionHcqtDTO vConstructionHcqtDTO = new VConstructionHcqtDTO();
        //set cac gia tri 
        vConstructionHcqtDTO.setCatProjectId(this.catProjectId);
        vConstructionHcqtDTO.setCatApProjectName(this.catApProjectName);
        vConstructionHcqtDTO.setConstructId(this.constructId);
        vConstructionHcqtDTO.setConstrtCode(this.constrtCode);
        vConstructionHcqtDTO.setConstrType(this.constrType);
        vConstructionHcqtDTO.setConstrTypeName(this.constrTypeName);
        vConstructionHcqtDTO.setConstrTypeCode(this.constrTypeCode);
        vConstructionHcqtDTO.setConstrtName(this.constrtName);
        vConstructionHcqtDTO.setConstrtAddress(this.constrtAddress);
        vConstructionHcqtDTO.setConstructorId(this.constructorId);
        vConstructionHcqtDTO.setSupervisorId(this.supervisorId);
        vConstructionHcqtDTO.setStartingDate(this.startingDate);
        vConstructionHcqtDTO.setCompleteDate(this.completeDate);
        vConstructionHcqtDTO.setSysUserId(this.sysUserId);
        vConstructionHcqtDTO.setCatApProjectId(this.catApProjectId);
        vConstructionHcqtDTO.setSysUserName(this.sysUserName);
        vConstructionHcqtDTO.setSysGroupId(this.sysGroupId);
        vConstructionHcqtDTO.setSysGroupName(this.sysGroupName);
        vConstructionHcqtDTO.setSysCreatedDate(this.sysCreatedDate);
        vConstructionHcqtDTO.setStatus(this.status);
        vConstructionHcqtDTO.setExpectedCompleteDate(this.expectedCompleteDate);
        vConstructionHcqtDTO.setStartComment(this.startComment);
        vConstructionHcqtDTO.setIsActive(this.isActive);
        vConstructionHcqtDTO.setProvinceId(this.provinceId);
        vConstructionHcqtDTO.setDistrictId(this.districtId);
        vConstructionHcqtDTO.setInvestProjectId(this.investProjectId);
        vConstructionHcqtDTO.setInvestProjectName(this.investProjectName);
        vConstructionHcqtDTO.setInvestProjectCode(this.investProjectCode);
        vConstructionHcqtDTO.setStationId(this.stationId);
        vConstructionHcqtDTO.setServiceProvideDate(this.serviceProvideDate);
        vConstructionHcqtDTO.setDistrictCode(this.districtCode);
        vConstructionHcqtDTO.setDistrictName(this.districtName);
        vConstructionHcqtDTO.setHandoverDate(this.handoverDate);
        vConstructionHcqtDTO.setStationCode(this.stationCode);
        vConstructionHcqtDTO.setStationAddress(this.stationAddress);
        vConstructionHcqtDTO.setProvinceCode(this.provinceCode);
        vConstructionHcqtDTO.setConstructorName(this.constructorName);
        vConstructionHcqtDTO.setSupervisorName(this.supervisorName);
        vConstructionHcqtDTO.setProvinceName(this.provinceName);
        vConstructionHcqtDTO.setSuperForm(this.superForm);
        vConstructionHcqtDTO.setConstrtForm(this.constrtForm);
        vConstructionHcqtDTO.setIsObstructed(this.isObstructed);
        vConstructionHcqtDTO.setStatusId(this.statusId);
        vConstructionHcqtDTO.setStatusName(this.statusName);
        vConstructionHcqtDTO.setStatusCode(this.statusCode);
        vConstructionHcqtDTO.setIsAccepted(this.isAccepted);
        vConstructionHcqtDTO.setPreStartingDate(this.preStartingDate);
       // vConstructionHcqtDTO.setAcceptStartDate(this.acceptStartDate);
       // vConstructionHcqtDTO.setAcceptFinishDate(this.acceptFinishDate);
        vConstructionHcqtDTO.setLineMode(this.lineMode);
        vConstructionHcqtDTO.setProjectType(this.projectType);
        vConstructionHcqtDTO.setConstructorPerson(this.constructorPerson);
        vConstructionHcqtDTO.setConstructorPhone(this.constructorPhone);
        vConstructionHcqtDTO.setSupervisorPerson(this.supervisorPerson);
        vConstructionHcqtDTO.setSupervisorPhone(this.supervisorPhone);
        vConstructionHcqtDTO.setCableTypeId(this.cableTypeId);
        vConstructionHcqtDTO.setLineLength(this.lineLength);
        vConstructionHcqtDTO.setLineType(this.lineType);
        vConstructionHcqtDTO.setExecuter(this.executer);
        vConstructionHcqtDTO.setExecuteGroup(this.executeGroup);
        vConstructionHcqtDTO.setExecutePhone(this.executePhone);
        vConstructionHcqtDTO.setFinishTest(this.finishTest);
        vConstructionHcqtDTO.setPercentFinish(this.percentFinish);
        vConstructionHcqtDTO.setPreparedPremises(this.preparedPremises);
        vConstructionHcqtDTO.setHandoverMaterials(this.handoverMaterials);
        vConstructionHcqtDTO.setProgressCommitment(this.progressCommitment);
        vConstructionHcqtDTO.setContractCode(this.contractCode);
        vConstructionHcqtDTO.setContractName(this.contractName);
        vConstructionHcqtDTO.setContract_total_value(this.contract_total_value);
        vConstructionHcqtDTO.setSigned_date(this.signed_date);
        vConstructionHcqtDTO.setContractId(this.contractId);
        vConstructionHcqtDTO.setIsAcceptedContract(this.isAcceptedContract);
        vConstructionHcqtDTO.setIsOff(this.isOff);
        return vConstructionHcqtDTO;
    }







}
