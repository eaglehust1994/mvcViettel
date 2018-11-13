/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.AbSettlementWorkBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_SETTLEMENT_WORKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbSettlementWorkDTO extends BaseFWDTOImpl<AbSettlementWorkBO> {

private java.lang.Long abSettlementWorkId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long adirectorId;
private java.lang.Long bdirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;
private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

private java.lang.Long constrCompReMapId;

    @Override
    public AbSettlementWorkBO toModel() {
        AbSettlementWorkBO abSettlementWorkBO = new AbSettlementWorkBO();
        abSettlementWorkBO.setAbSettlementWorkId(this.abSettlementWorkId);
        abSettlementWorkBO.setCode(this.code);
        abSettlementWorkBO.setCreatedDate(this.createdDate);
        abSettlementWorkBO.setCreatedUserId(this.createdUserId);
        abSettlementWorkBO.setADirectorId(this.adirectorId);
        abSettlementWorkBO.setBDirectorId(this.bdirectorId);
        abSettlementWorkBO.setStatusCa(this.statusCa);
        abSettlementWorkBO.setDocumentCaId(this.documentCaId);
        abSettlementWorkBO.setIsActive(this.isActive);
        abSettlementWorkBO.setConstructId(this.constructId);
        return abSettlementWorkBO;
    }

    @Override
     public Long getFWModelId() {
        return abSettlementWorkId;
    }
   
    @Override
    public String catchName() {
        return getAbSettlementWorkId().toString();
    }
    public java.lang.Long getAbSettlementWorkId(){
    return abSettlementWorkId;
    }
    public void setAbSettlementWorkId(java.lang.Long abSettlementWorkId)
    {
    this.abSettlementWorkId = abSettlementWorkId;
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
    return adirectorId;
    }
    public void setADirectorId(java.lang.Long aDirectorId)
    {
    this.adirectorId = aDirectorId;
    }
    
    public java.lang.Long getBDirectorId(){
    return bdirectorId;
    }
    public void setBDirectorId(java.lang.Long bDirectorId)
    {
    this.bdirectorId = bDirectorId;
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

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}
    
    
}
