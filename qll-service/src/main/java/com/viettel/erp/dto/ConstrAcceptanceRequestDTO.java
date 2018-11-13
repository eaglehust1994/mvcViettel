/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.bo.ConstrAcceptanceRequestBO;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_ACCEPTANCE_REQUESTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptanceRequestDTO extends BaseFWDTOImpl<ConstrAcceptanceRequestBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3247660966179235369L;
	private Long constructId;
	private Long constAcceptanceRequestId;
	private String code;
	private Long sendPersonId;
	private Long receivePersonId;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date acceptanceDate;
	
	private String acceptancePlace;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDate;
	private Long createdUserId;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date approvalDate;
	private Long statusCa;
	private Long isActive;
	private String dearGroup;
	private String constrtCode;
	private String contractCode;
	private String contractName;
	// du lieu lay bang khac
	private String constrtName = "";
	private String constrtAddress = "";
	private String stationCode = "";
	
	private String comments;
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private String statusName;
	private Long partnerId;
	private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;
	private Long constrCompReMapId;
	private Long createUserId;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date signDate;
	private String signPlace;
	private String toGroup;

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	@Override
	public ConstrAcceptanceRequestBO toModel() {
		ConstrAcceptanceRequestBO constrAcceptanceRequestBO = new ConstrAcceptanceRequestBO();
		constrAcceptanceRequestBO.setConstructId(this.constructId);
		constrAcceptanceRequestBO.setConstAcceptanceRequestId(this.constAcceptanceRequestId);
		constrAcceptanceRequestBO.setCode(this.code);
		constrAcceptanceRequestBO.setSendPersonId(this.sendPersonId);
		constrAcceptanceRequestBO.setReceivePersonId(this.receivePersonId);
		constrAcceptanceRequestBO.setAcceptanceDate(this.acceptanceDate);
		constrAcceptanceRequestBO.setAcceptancePlace(this.acceptancePlace);
		constrAcceptanceRequestBO.setCreatedDate(this.createdDate);
		constrAcceptanceRequestBO.setCreatedUserId(this.createdUserId);
		constrAcceptanceRequestBO.setApprovalDate(this.approvalDate);
		constrAcceptanceRequestBO.setStatusCa(this.statusCa);
		constrAcceptanceRequestBO.setIsActive(this.isActive);
		constrAcceptanceRequestBO.setDearGroup(this.dearGroup);
		constrAcceptanceRequestBO.setSignDate(this.signDate);
		constrAcceptanceRequestBO.setSignPlace(this.signPlace);
		constrAcceptanceRequestBO.setToGroup(this.toGroup);

		return constrAcceptanceRequestBO;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return constAcceptanceRequestId;
	}

	@Override
	public String catchName() {
		return getConstAcceptanceRequestId().toString();
	}

	public Long getConstAcceptanceRequestId() {
		return constAcceptanceRequestId;
	}

	public void setConstAcceptanceRequestId(Long constAcceptanceRequestId) {
		this.constAcceptanceRequestId = constAcceptanceRequestId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSendPersonId() {
		return sendPersonId;
	}

	public void setSendPersonId(Long sendPersonId) {
		this.sendPersonId = sendPersonId;
	}

	public Long getReceivePersonId() {
		return receivePersonId;
	}

	public void setReceivePersonId(Long receivePersonId) {
		this.receivePersonId = receivePersonId;
	}

	public java.util.Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(java.util.Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getAcceptancePlace() {
		return acceptancePlace;
	}

	public void setAcceptancePlace(String acceptancePlace) {
		this.acceptancePlace = acceptancePlace;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public String getDearGroup() {
		return dearGroup;
	}

	public void setDearGroup(String dearGroup) {
		this.dearGroup = dearGroup;
	}

	public String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @return the signPlace
	 */
	public String getSignPlace() {
		return signPlace;
	}

	/**
	 * @param signPlace the signPlace to set
	 */
	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	/**
	 * @return the toGroup
	 */
	public String getToGroup() {
		return toGroup;
	}

	/**
	 * @param toGroup the toGroup to set
	 */
	public void setToGroup(String toGroup) {
		this.toGroup = toGroup;
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

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	
}
