/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CURRENT_STATE_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown =true)
public class CurrentStateHandoverExtraDTO extends BaseFWDTOImpl<CurrentStateHandoverBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6247142083676770302L;
	private java.lang.Long constructId;
	private java.lang.Long currentStateHandoverId;
	private java.lang.String code;
	private java.lang.String station_code;
	private java.lang.String contractCode;
	private java.lang.String constrtName;
	private java.lang.String constrtAddress;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String chargeMonitorName;
	private java.lang.String chargeConstructName;
	private java.util.Date handoverDate;
	private java.lang.String handoverPlace;
	private java.lang.String groundHandoverContent;
	private java.lang.String otherDocuments;
	private java.lang.String otherComments;
	private java.lang.String conclusion;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private java.lang.Long date;
	private java.lang.Long month;
	private java.lang.String year;
	private java.lang.String aInChargeMonitorPath;
	private java.lang.String bInChargeConstructPath;
	private List<CurrentStateHandoverListDTO> listCurrentStateHandover;
	private java.lang.String chargeMonitorNameSign;
	private java.lang.String chargeConstructNameSign;

	@Override
	public CurrentStateHandoverBO toModel() {
		CurrentStateHandoverBO currentStateHandoverBO = new CurrentStateHandoverBO();
		currentStateHandoverBO.setConstructId(this.constructId);
		currentStateHandoverBO.setCurrentStateHandoverId(this.currentStateHandoverId);
		currentStateHandoverBO.setCode(this.code);
		currentStateHandoverBO.setAInChargeMonitorId(new Long(1));
		currentStateHandoverBO.setBInChargeConstructId(new Long(1));
		currentStateHandoverBO.setHandoverDate(this.handoverDate);
		currentStateHandoverBO.setHandoverPlace(this.handoverPlace);
		currentStateHandoverBO.setGroundHandoverContent(this.groundHandoverContent);
		currentStateHandoverBO.setOtherDocuments(this.otherDocuments);
		currentStateHandoverBO.setOtherComments(this.otherComments);
		currentStateHandoverBO.setApprovalDate(this.approvalDate);
		currentStateHandoverBO.setStatusCa(this.statusCa);
		currentStateHandoverBO.setIsActive(this.isActive);
		return currentStateHandoverBO;
	}

	
	public java.lang.String getChargeMonitorNameSign() {
		return Strings.nullToEmpty(chargeMonitorNameSign);
	}


	public void setChargeMonitorNameSign(java.lang.String chargeMonitorNameSign) {
		this.chargeMonitorNameSign = chargeMonitorNameSign;
	}


	public java.lang.String getChargeConstructNameSign() {
		return Strings.nullToEmpty(chargeConstructNameSign);
	}


	public void setChargeConstructNameSign(java.lang.String chargeConstructNameSign) {
		this.chargeConstructNameSign = chargeConstructNameSign;
	}


	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return currentStateHandoverId;
	}

	@Override
	public String catchName() {
		return getCurrentStateHandoverId().toString();
	}

	public java.lang.Long getCurrentStateHandoverId() {
		return currentStateHandoverId;
	}

	public void setCurrentStateHandoverId(java.lang.Long currentStateHandoverId) {
		this.currentStateHandoverId = currentStateHandoverId;
	}

	public java.lang.String getCode() {
		return Strings.nullToEmpty(code);
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.util.Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	public java.lang.String getHandoverPlace() {
		return Strings.nullToEmpty(handoverPlace);
	}

	public void setHandoverPlace(java.lang.String handoverPlace) {
		this.handoverPlace = handoverPlace;
	}

	public java.lang.String getGroundHandoverContent() {
		return groundHandoverContent;
	}

	public void setGroundHandoverContent(java.lang.String groundHandoverContent) {
		this.groundHandoverContent = groundHandoverContent;
	}

	public java.lang.String getOtherDocuments() {
		return Strings.nullToEmpty(otherDocuments);
	}

	public void setOtherDocuments(java.lang.String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public java.lang.String getOtherComments() {
		return Strings.nullToEmpty(otherComments) ;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	public java.lang.String getConclusion() {
		return Strings.nullToEmpty(conclusion);
	}

	public void setConclusion(java.lang.String conclusion) {
		this.conclusion = conclusion;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	public List<CurrentStateHandoverListDTO> getListCurrentStateHandover() {
		return listCurrentStateHandover;
	}

	public void setListCurrentStateHandover(List<CurrentStateHandoverListDTO> listCurrentStateHandover) {
		this.listCurrentStateHandover = listCurrentStateHandover;
	}

	public java.lang.String getChargeMonitorName() {
		return Strings.nullToEmpty(chargeMonitorName);
	}

	public void setChargeMonitorName(java.lang.String chargeMonitorName) {
		this.chargeMonitorName = chargeMonitorName;
	}

	public java.lang.String getChargeConstructName() {
		return Strings.nullToEmpty(chargeConstructName) ;
	}

	public void setChargeConstructName(java.lang.String chargeConstructName) {
		this.chargeConstructName = chargeConstructName;
	}

	public java.lang.String getStation_code() {
		return Strings.nullToEmpty(station_code) ;
	}

	public void setStation_code(java.lang.String station_code) {
		this.station_code = station_code;
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
		return Strings.nullToEmpty(year);
	}

	public void setYear(java.lang.Long year) {
		this.year = String.valueOf(year);
	}

	public java.lang.String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}

	public java.lang.String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress) ;
	}

	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	public java.lang.Long getaInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setaInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	public java.lang.Long getbInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setbInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	public java.lang.String getaInChargeMonitorPath() {
		return aInChargeMonitorPath;
	}

	public void setaInChargeMonitorPath(java.lang.String aInChargeMonitorPath) {
		this.aInChargeMonitorPath = aInChargeMonitorPath;
	}

	public java.lang.String getbInChargeConstructPath() {
		return bInChargeConstructPath;
	}

	public void setbInChargeConstructPath(java.lang.String bInChargeConstructPath) {
		this.bInChargeConstructPath = bInChargeConstructPath;
	}

	 
	

}
