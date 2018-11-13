/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.MonitorMissionAssignBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateDeserializerDOng;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.CustomJsonDateSerializerDOng;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "MONITOR_MISSION_ASSIGNBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitorMissionAssignDTO extends BaseFWDTOImpl<MonitorMissionAssignBO> {

private java.lang.Long monitorMissionAssignId;
private java.lang.Long constructId;
private java.lang.String code;
private java.lang.Long aDirectorId;
private java.lang.Long aMonitorId;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date signDate;
private java.lang.String signPlace;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date missionDate;
private java.lang.String monitorDocument;
private java.lang.String assignNote;
private java.lang.Long statusCa;
private java.util.Date approvalDate;
private java.lang.Long isActive;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Double contractId;

private String documentPath;
private String documentName;
private Long attachId;
private java.lang.Long constrCompReMapId;
private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;
private java.lang.String contractCode;
private java.lang.String contractName;

private java.lang.String constrtAddress;
private java.lang.String constrtCode;
private java.lang.String aDirectorIdName;
private java.lang.String aDirectorIdNamePath;
private java.lang.String aDirectorIdRoleName;
private java.lang.String aDirectorIdNameSign;
private java.lang.String aMonitorIdName;
private java.lang.String aMonitorIdNamePath;
private java.lang.String aMonitorIdRoleName;
private java.lang.String aMonitorIdNameSign;
private java.lang.String signDateDay;
private java.lang.String signDateMonth;
private java.lang.String signDateYear;

private java.lang.String missionDateDay;
private java.lang.String missionDateMonth;
private java.lang.String missionDateYear;

private java.lang.Integer stt;
private java.lang.String comments;



    public java.lang.String getComments() {
	return comments;
}

public void setComments(java.lang.String comments) {
	this.comments = comments;
}

	@Override
    public MonitorMissionAssignBO toModel() {
        MonitorMissionAssignBO monitorMissionAssignBO = new MonitorMissionAssignBO();
        monitorMissionAssignBO.setMonitorMissionAssignId(this.monitorMissionAssignId);
        monitorMissionAssignBO.setConstructId(this.constructId);
        monitorMissionAssignBO.setCode(this.code);
        monitorMissionAssignBO.setADirectorId(this.aDirectorId);
        monitorMissionAssignBO.setAMonitorId(this.aMonitorId);
        monitorMissionAssignBO.setSignDate(this.signDate);
        monitorMissionAssignBO.setSignPlace(this.signPlace);
        monitorMissionAssignBO.setMissionDate(this.missionDate);
        monitorMissionAssignBO.setMonitorDocument(this.monitorDocument);
        monitorMissionAssignBO.setAssignNote(this.assignNote);
        monitorMissionAssignBO.setStatusCa(this.statusCa);
        monitorMissionAssignBO.setApprovalDate(this.approvalDate);
        monitorMissionAssignBO.setIsActive(this.isActive);
        monitorMissionAssignBO.setCreatedDate(this.createdDate);
        monitorMissionAssignBO.setCreatedUserId(this.createdUserId);
        return monitorMissionAssignBO;
    }

    @Override
     public Long getFWModelId() {
        return monitorMissionAssignId;
    }
   
    @Override
    public String catchName() {
        return getMonitorMissionAssignId().toString();
    }
    public java.lang.Long getMonitorMissionAssignId(){
    return monitorMissionAssignId;
    }
    public void setMonitorMissionAssignId(java.lang.Long monitorMissionAssignId)
    {
    this.monitorMissionAssignId = monitorMissionAssignId;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getADirectorId(){
    return aDirectorId;
    }
    public void setADirectorId(java.lang.Long aDirectorId)
    {
    this.aDirectorId = aDirectorId;
    }
    
    public java.lang.Long getAMonitorId(){
    return aMonitorId;
    }
    public void setAMonitorId(java.lang.Long aMonitorId)
    {
    this.aMonitorId = aMonitorId;
    }
    
    public java.util.Date getSignDate(){
    return signDate;
    }
    public void setSignDate(java.util.Date signDate)
    {
    this.signDate = signDate;
    }
    
    public java.lang.String getSignPlace(){
    return signPlace;
    }
    public void setSignPlace(java.lang.String signPlace)
    {
    this.signPlace = signPlace;
    }
    
    public java.util.Date getMissionDate(){
    return missionDate;
    }
    public void setMissionDate(java.util.Date missionDate)
    {
    this.missionDate = missionDate;
    }
    
    public java.lang.String getMonitorDocument(){
    	return Strings.nullToEmpty(monitorDocument);
    }
    public void setMonitorDocument(java.lang.String monitorDocument)
    {
    this.monitorDocument = monitorDocument;
    }
    
    public java.lang.String getAssignNote(){
    	return Strings.nullToEmpty(assignNote);
    }
    public void setAssignNote(java.lang.String assignNote)
    {
    this.assignNote = assignNote;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
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

	public java.lang.Long getaDirectorId() {
		return aDirectorId;
	}

	public void setaDirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	public java.lang.Long getaMonitorId() {
		return aMonitorId;
	}

	public void setaMonitorId(java.lang.Long aMonitorId) {
		this.aMonitorId = aMonitorId;
	}

	public java.lang.String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}

	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public java.lang.String getaDirectorIdName() {
		return aDirectorIdName;
	}

	public void setaDirectorIdName(java.lang.String aDirectorIdName) {
		this.aDirectorIdName = aDirectorIdName;
	}

	public java.lang.String getaDirectorIdNamePath() {
		return aDirectorIdNamePath;
	}

	public void setaDirectorIdNamePath(java.lang.String aDirectorIdNamePath) {
		this.aDirectorIdNamePath = aDirectorIdNamePath;
	}

	public java.lang.String getaDirectorIdRoleName() {
		return aDirectorIdRoleName;
	}

	public void setaDirectorIdRoleName(java.lang.String aDirectorIdRoleName) {
		this.aDirectorIdRoleName = aDirectorIdRoleName;
	}

	public java.lang.String getaMonitorIdName() {
		return aMonitorIdName;
	}

	public void setaMonitorIdName(java.lang.String aMonitorIdName) {
		this.aMonitorIdName = aMonitorIdName;
	}

	public java.lang.String getaMonitorIdNamePath() {
		return aMonitorIdNamePath;
	}

	public void setaMonitorIdNamePath(java.lang.String aMonitorIdNamePath) {
		this.aMonitorIdNamePath = aMonitorIdNamePath;
	}

	public java.lang.String getaMonitorIdRoleName() {
		return aMonitorIdRoleName;
	}

	public void setaMonitorIdRoleName(java.lang.String aMonitorIdRoleName) {
		this.aMonitorIdRoleName = aMonitorIdRoleName;
	}
    
	public java.lang.String getSignDateDay() {
		if (signDate != null) {
			signDateDay = DateFormatUtils.format(signDate, "dd");
		}
		return signDateDay;
	}

	public java.lang.String getSignDateMonth() {
		if (signDate != null) {
			signDateMonth = DateFormatUtils.format(signDate, "MM");
		}
		return signDateMonth;
	}

	public java.lang.String getSignDateYear() {
		if (signDate != null) {
			signDateYear = DateFormatUtils.format(signDate, "yyyy");
		}
		return signDateYear;
	}
	
	public java.lang.String getMissionDateDay() {
		if (missionDate != null) {
			missionDateDay = DateFormatUtils.format(missionDate, "dd");
		}
		return missionDateDay;
	}

	public java.lang.String getMissionDateMonth() {
		if (missionDate != null) {
			missionDateMonth = DateFormatUtils.format(missionDate, "MM");
		}
		return missionDateMonth;
	}

	public java.lang.String getMissionDateYear() {
		if (missionDate != null) {
			missionDateYear = DateFormatUtils.format(missionDate, "yyyy");
		}
		return missionDateYear;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.String getaDirectorIdNameSign() {
		return aDirectorIdNameSign;
	}

	public void setaDirectorIdNameSign(java.lang.String aDirectorIdNameSign) {
		this.aDirectorIdNameSign = aDirectorIdNameSign;
	}

	public java.lang.String getaMonitorIdNameSign() {
		return aMonitorIdNameSign;
	}

	public void setaMonitorIdNameSign(java.lang.String aMonitorIdNameSign) {
		this.aMonitorIdNameSign = aMonitorIdNameSign;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getContractName() {
		return contractName;
	}

	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}
	
}
