/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.viettel.erp.bo.AbSettlementValueBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_SETTLEMENT_VALUEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbSettlementValueDTO extends BaseFWDTOImpl<AbSettlementValueBO> {

private java.lang.Long abSettlementValueId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long exportMaterialValue;
private java.lang.Long acceptMaterialValue;
private java.lang.Long lostMaterialValue;
private java.lang.Long recoveryMaterialValue;
private java.lang.Long unrecoveryMaterialValue;
private java.lang.Long paidValue;
private java.lang.Long constructId;

private java.lang.Long valueProposed;
private java.lang.Long valueOutProposed;
private java.lang.Long contractTotalValue;
private java.lang.Long valueFinalizationContractors;
private java.lang.Long totalPrice;
private java.lang.String constructName;
private java.lang.String constrtName;
private java.lang.String stationcode;
private java.lang.String constructAddress;
private java.lang.String constrtAddress;
private java.lang.String contractCode;
private java.lang.String investProjectName;
private java.lang.Long date;
private java.lang.Long month;
private java.lang.String year;
private java.lang.Long valueResidual;
private java.lang.Long valueDeductionSupplies;
private java.lang.Long sumSettlementConstruction;

private java.lang.String aDirectorIdPath;
private java.lang.String bDirectorIdPath;
private java.lang.Long statusCaSettlementEvaluate;


public java.lang.String getConstrtName() {
	return Strings.nullToEmpty(constrtName);
}

public void setConstrtName(java.lang.String constrtName) {
	this.constrtName = constrtName;
}

public java.lang.String getConstrtAddress() {
	return Strings.nullToEmpty(constrtAddress);
}

public void setConstrtAddress(java.lang.String constrtAddress) {
	this.constrtAddress = constrtAddress;
}

public java.lang.String getaDirectorIdPath() {
	return aDirectorIdPath;
}

public void setaDirectorIdPath(java.lang.String aDirectorIdPath) {
	this.aDirectorIdPath = aDirectorIdPath;
}

public java.lang.String getbDirectorIdPath() {
	return bDirectorIdPath;
}

public void setbDirectorIdPath(java.lang.String bDirectorIdPath) {
	this.bDirectorIdPath = bDirectorIdPath;
}

public java.lang.Long getaDirectorId() {
	return aDirectorId;
}

public void setaDirectorId(java.lang.Long aDirectorId) {
	this.aDirectorId = aDirectorId;
}

public java.lang.Long getbDirectorId() {
	return bDirectorId;
}

public void setbDirectorId(java.lang.Long bDirectorId) {
	this.bDirectorId = bDirectorId;
}

public java.lang.Long getValueProposed() {
	if(valueProposed==null){
		return 0l;
	}		
	return valueProposed;
}

public void setValueProposed(java.lang.Long valueProposed) {
	this.valueProposed = valueProposed;
}

public java.lang.Long getValueOutProposed() {
	if(valueOutProposed==null){
		return 0l;
	}
	return valueOutProposed;
}

public void setValueOutProposed(java.lang.Long valueOutProposed) {
	this.valueOutProposed = valueOutProposed;
}

public java.lang.Long getContractTotalValue() {
	if(contractTotalValue == null){
		return 0l;
	}
	return contractTotalValue;
}

public void setContractTotalValue(java.lang.Long contractTotalValue) {
	this.contractTotalValue = contractTotalValue;
}

public java.lang.Long getValueFinalizationContractors() {
	if(valueFinalizationContractors==null){
		return 0l;
	}
	return valueFinalizationContractors;
}

public void setValueFinalizationContractors(java.lang.Long valueFinalizationContractors) {
	this.valueFinalizationContractors = valueFinalizationContractors;
}

public java.lang.Long getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(java.lang.Long totalPrice) {
	this.totalPrice = totalPrice;
}

public java.lang.String getConstructName() {
	return Strings.nullToEmpty(constructName);
}

public void setConstructName(java.lang.String constructName) {
	this.constructName = constructName;
}

public java.lang.String getStationcode() {
	return Strings.nullToEmpty(stationcode) ;
}

public void setStationcode(java.lang.String stationcode) {
	this.stationcode = stationcode;
}

public java.lang.String getConstructAddress() {
	return Strings.nullToEmpty(constructAddress);
}

public void setConstructAddress(java.lang.String constructAddress) {
	this.constructAddress = constructAddress;
}

public java.lang.String getContractCode() {
	return Strings.nullToEmpty(contractCode);
}

public void setContractCode(java.lang.String contractCode) {
	this.contractCode = contractCode;
}

public java.lang.String getInvestProjectName() {
	return Strings.nullToEmpty(investProjectName);
}

public void setInvestProjectName(java.lang.String investProjectName) {
	this.investProjectName = investProjectName;
}

public java.lang.Long getDate() {
	return date;
}

public void setDate(java.lang.Long date) {
	this.date = date;
}

public java.lang.Long getMonth() {
	return month;
}

public void setMonth(java.lang.Long month) {
	this.month = month;
}

public java.lang.String getYear() {
	return year;
}

public void setYear(java.lang.String year) {
	this.year = year;
}

public java.lang.Long getValueResidual() {
	if(valueResidual==null){
		return 0l;
	}
	return valueResidual;
}

public void setValueResidual(java.lang.Long valueResidual) {
	this.valueResidual = valueResidual;
}

public java.lang.Long getValueDeductionSupplies() {
	if(valueDeductionSupplies==null){
		return 0l;
	}
	
	return valueDeductionSupplies;
}

public void setValueDeductionSupplies(java.lang.Long valueDeductionSupplies) {
	this.valueDeductionSupplies = valueDeductionSupplies;
}

public java.lang.Long getSumSettlementConstruction() {
	if(sumSettlementConstruction==null){
		return 0l;
	}
	return sumSettlementConstruction;
}

public void setSumSettlementConstruction(java.lang.Long sumSettlementConstruction) {
	this.sumSettlementConstruction = sumSettlementConstruction;
}

private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

    @Override
    public AbSettlementValueBO toModel() {
        AbSettlementValueBO abSettlementValueBO = new AbSettlementValueBO();
        abSettlementValueBO.setAbSettlementValueId(this.abSettlementValueId);
        abSettlementValueBO.setCode(this.code);
        abSettlementValueBO.setCreatedDate(this.createdDate);
        abSettlementValueBO.setCreatedUserId(this.createdUserId);
        abSettlementValueBO.setADirectorId(this.aDirectorId);
        abSettlementValueBO.setBDirectorId(this.bDirectorId);
        abSettlementValueBO.setStatusCa(this.statusCa);
        abSettlementValueBO.setDocumentCaId(this.documentCaId);
        abSettlementValueBO.setIsActive(this.isActive);
        abSettlementValueBO.setExportMaterialValue(this.exportMaterialValue);
        abSettlementValueBO.setAcceptMaterialValue(this.acceptMaterialValue);
        abSettlementValueBO.setLostMaterialValue(this.lostMaterialValue);
        abSettlementValueBO.setRecoveryMaterialValue(this.recoveryMaterialValue);
        abSettlementValueBO.setUnrecoveryMaterialValue(this.unrecoveryMaterialValue);
        abSettlementValueBO.setPaidValue(this.paidValue);
        abSettlementValueBO.setConstructId(this.constructId);
        return abSettlementValueBO;
    }

    @Override
     public Long getFWModelId() {
        return abSettlementValueId;
    }
   
    @Override
    public String catchName() {
        return getAbSettlementValueId().toString();
    }
    public java.lang.Long getAbSettlementValueId(){
    return abSettlementValueId;
    }
    public void setAbSettlementValueId(java.lang.Long abSettlementValueId)
    {
    this.abSettlementValueId = abSettlementValueId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
    public java.lang.Long getADirectorId(){
    return aDirectorId;
    }
    public void setADirectorId(java.lang.Long aDirectorId)
    {
    this.aDirectorId = aDirectorId;
    }
    
    public java.lang.Long getBDirectorId(){
    return bDirectorId;
    }
    public void setBDirectorId(java.lang.Long bDirectorId)
    {
    this.bDirectorId = bDirectorId;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getExportMaterialValue(){
    	if(exportMaterialValue==null){
    		return 0l;
    	}
    return exportMaterialValue;
    }
    public void setExportMaterialValue(java.lang.Long exportMaterialValue)
    {
    this.exportMaterialValue = exportMaterialValue;
    }
    
    public java.lang.Long getAcceptMaterialValue(){
    	if(acceptMaterialValue==null){
    		return 0l;
    	}
    return acceptMaterialValue;
    }
    public void setAcceptMaterialValue(java.lang.Long acceptMaterialValue)
    {
    this.acceptMaterialValue = acceptMaterialValue;
    }
    
    public java.lang.Long getLostMaterialValue(){
    	if(lostMaterialValue==null){
    		return 0l;
    	}
    return lostMaterialValue;
    }
    public void setLostMaterialValue(java.lang.Long lostMaterialValue)
    {
    this.lostMaterialValue = lostMaterialValue;
    }
    
    public java.lang.Long getRecoveryMaterialValue(){
    	if(recoveryMaterialValue==null){
    		return 0l;
    	}
    return recoveryMaterialValue;
    }
    public void setRecoveryMaterialValue(java.lang.Long recoveryMaterialValue)
    {
    this.recoveryMaterialValue = recoveryMaterialValue;
    }
    
    public java.lang.Long getUnrecoveryMaterialValue(){
    	if(unrecoveryMaterialValue==null){
    		return 0l;
    	}
    return unrecoveryMaterialValue;
    }
    public void setUnrecoveryMaterialValue(java.lang.Long unrecoveryMaterialValue)
    {
    this.unrecoveryMaterialValue = unrecoveryMaterialValue;
    }
    
    public java.lang.Long getPaidValue(){
    	if(paidValue==null){
    		return 0l;
    	}
    return paidValue;
    }
    public void setPaidValue(java.lang.Long paidValue)
    {
    this.paidValue = paidValue;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public java.lang.Long getStatusCaSettlementEvaluate() {
		return statusCaSettlementEvaluate;
	}

	public void setStatusCaSettlementEvaluate(java.lang.Long statusCaSettlementEvaluate) {
		this.statusCaSettlementEvaluate = statusCaSettlementEvaluate;
	}
    
   
}
