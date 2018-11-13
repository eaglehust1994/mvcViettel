/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.ConstrGroundHandoverBO;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_GROUND_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrGroundHandoverDTO extends BaseFWDTOImpl<ConstrGroundHandoverBO> {

	private java.lang.Long constructId;
	private java.lang.Long constrGroundHandoverId;
	private java.lang.String code;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private java.util.Date handoverDate;
	private java.lang.Long aDirectorId;
	private java.lang.Long aMonitorId;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String groundCurrentStatus;
	private java.lang.String benchmark;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long constrCompReMapId;
	

	private java.lang.Long isActive;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private java.lang.String adirectorName;
	private java.lang.String amonitorName;
	private java.lang.String ainChargeMonitorName;
	private java.lang.String bdirectorName;
	private java.lang.String binChargeConstructName;
	private java.lang.String contractCode;
	private java.lang.String contractName;
	private java.lang.String constrtCode;
	private java.lang.String constrtName;
	private java.lang.String constrtAddress;
	private java.lang.String stationCode;
	

	private java.lang.String date;
	private java.lang.Boolean docOrPdf;
	
	private java.lang.String adirectorNamePath;
	private java.lang.String amonitorNamePath;
	private java.lang.String bdirectorNamePath;
	private java.lang.String binChargeConstructNamePath;
	
	private java.lang.String adirectorNameSign;
	private java.lang.String amonitorNameSign;
	private java.lang.String ainChargeMonitorNameSign;
	private java.lang.String bdirectorNameSign;
	private java.lang.String binChargeConstructNameSign;
	
	private java.lang.String comments;
	
	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	private java.lang.Double contractId;
	
	public java.lang.String getAdirectorNameSign() {
		return adirectorNameSign;
	}

	public void setAdirectorNameSign(java.lang.String adirectorNameSign) {
		this.adirectorNameSign = adirectorNameSign;
	}

	public java.lang.String getAmonitorNameSign() {
		return amonitorNameSign;
	}

	public void setAmonitorNameSign(java.lang.String amonitorNameSign) {
		this.amonitorNameSign = amonitorNameSign;
	}

	public java.lang.String getAinChargeMonitorNameSign() {
		return ainChargeMonitorNameSign;
	}

	public void setAinChargeMonitorNameSign(java.lang.String ainChargeMonitorNameSign) {
		this.ainChargeMonitorNameSign = ainChargeMonitorNameSign;
	}

	public java.lang.String getBdirectorNameSign() {
		return bdirectorNameSign;
	}

	public void setBdirectorNameSign(java.lang.String bdirectorNameSign) {
		this.bdirectorNameSign = bdirectorNameSign;
	}

	public java.lang.String getBinChargeConstructNameSign() {
		return binChargeConstructNameSign;
	}

	public void setBinChargeConstructNameSign(java.lang.String binChargeConstructNameSign) {
		this.binChargeConstructNameSign = binChargeConstructNameSign;
	}

	private String handoverDay = "";
	private String handoverMonth = "";
	private String handoverYear = "";
	
	public String getHandoverDay() {
		if(handoverDate != null){
			handoverDay = DateFormatUtils.format(handoverDate, "dd");
		}
		return handoverDay;
	}

	public void setHandoverDay(String handoverDay) {
		this.handoverDay = handoverDay;
	}

	public String getHandoverMonth() {
		if(handoverDate != null){
			handoverMonth = DateFormatUtils.format(handoverDate, "MM");
		}
		return handoverMonth;
	}

	public void setHandoverMonth(String handoverMonth) {
		this.handoverMonth = handoverMonth;
	}

	public String getHandoverYear() {
		if(handoverDate != null){
			handoverYear = DateFormatUtils.format(handoverDate, "yyyy");
		}
		return handoverYear;
	}

	public void setHandoverYear(String handoverYear) {
		this.handoverYear = handoverYear;
	}
	
	public java.lang.String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	public void setStationCode(java.lang.String stationCode) {
		this.stationCode = stationCode;
	}
	
	public java.lang.Boolean getDocOrPdf() {
		return docOrPdf;
	}

	public void setDocOrPdf(java.lang.Boolean docOrPdf) {
		this.docOrPdf = docOrPdf;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}
	
	public java.lang.String getDate() {
		return date;
	}

	public void setDate(java.lang.String date) {
		this.date = date;
	}

	public java.lang.String getMonth() {
		return month;
	}

	public void setMonth(java.lang.String month) {
		this.month = month;
	}

	public java.lang.String getYear() {
		return year;
	}

	public void setYear(java.lang.String year) {
		this.year = year;
	}

	private java.lang.String month;
	private java.lang.String year;

	@Override
	public ConstrGroundHandoverBO toModel() {
		ConstrGroundHandoverBO constrGroundHandoverBO = new ConstrGroundHandoverBO();
		constrGroundHandoverBO.setConstructId(this.constructId);
		constrGroundHandoverBO.setConstrGroundHandoverId(this.constrGroundHandoverId);
		constrGroundHandoverBO.setCode(this.code);
		constrGroundHandoverBO.setHandoverDate(this.handoverDate);
		constrGroundHandoverBO.setADirectorId(this.aDirectorId);
		constrGroundHandoverBO.setAMonitorId(this.aMonitorId);
		constrGroundHandoverBO.setAInChargeMonitorId(this.aInChargeMonitorId);
		constrGroundHandoverBO.setBDirectorId(this.bDirectorId);
		constrGroundHandoverBO.setBInChargeConstructId(this.bInChargeConstructId);
		constrGroundHandoverBO.setGroundCurrentStatus(this.groundCurrentStatus);
		constrGroundHandoverBO.setBenchmark(this.benchmark);
		constrGroundHandoverBO.setCreatedDate(this.createdDate);
		constrGroundHandoverBO.setCreatedUserId(this.createdUserId);
		constrGroundHandoverBO.setApprovalDate(this.approvalDate);

		if (this.statusCa == null || "".equals(this.statusCa)) {
			constrGroundHandoverBO.setStatusCa(0l);
		} else {
			constrGroundHandoverBO.setStatusCa(this.statusCa);
		}

		if (this.isActive == null || "".equals(this.isActive)) {
			constrGroundHandoverBO.setIsActive(1l);
		} else {
			constrGroundHandoverBO.setIsActive(this.isActive);
		}

		constrGroundHandoverBO.setSignDate(this.signDate);
		constrGroundHandoverBO.setSignPlace(this.signPlace);
		return constrGroundHandoverBO;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return constrGroundHandoverId;
	}

	@Override
	public String catchName() {
		return getConstrGroundHandoverId().toString();
	}

	public java.lang.Long getConstrGroundHandoverId() {
		return constrGroundHandoverId;
	}

	public void setConstrGroundHandoverId(java.lang.Long constrGroundHandoverId) {
		this.constrGroundHandoverId = constrGroundHandoverId;
	}

	public java.lang.String getCode() {
		return code;
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

	public java.lang.Long getADirectorId() {
		return aDirectorId;
	}

	public void setADirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	public java.lang.Long getAMonitorId() {
		return aMonitorId;
	}

	public void setAMonitorId(java.lang.Long aMonitorId) {
		this.aMonitorId = aMonitorId;
	}

	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	public java.lang.Long getBDirectorId() {
		return bDirectorId;
	}

	public void setBDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}

	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	public java.lang.String getGroundCurrentStatus() {
		return Strings.nullToEmpty(groundCurrentStatus);
	}

	public void setGroundCurrentStatus(java.lang.String groundCurrentStatus) {
		this.groundCurrentStatus = groundCurrentStatus;
	}

	public java.lang.String getBenchmark() {
		return Strings.nullToEmpty(benchmark);
	}

	public void setBenchmark(java.lang.String benchmark) {
		this.benchmark = benchmark;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
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

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public java.lang.String getAdirectorName() {
		return adirectorName;
	}

	public void setAdirectorName(java.lang.String adirectorName) {
		this.adirectorName = adirectorName;
	}

	public java.lang.String getAmonitorName() {
		return amonitorName;
	}

	public void setAmonitorName(java.lang.String amonitorName) {
		this.amonitorName = amonitorName;
	}

	public java.lang.String getAinChargeMonitorName() {
		return ainChargeMonitorName;
	}

	public void setAinChargeMonitorName(java.lang.String ainChargeMonitorName) {
		this.ainChargeMonitorName = ainChargeMonitorName;
	}

	public java.lang.String getBdirectorName() {
		return bdirectorName;
	}

	public void setBdirectorName(java.lang.String bdirectorName) {
		this.bdirectorName = bdirectorName;
	}

	public java.lang.String getBinChargeConstructName() {
		return binChargeConstructName;
	}

	public void setBinChargeConstructName(java.lang.String binChargeConstructName) {
		this.binChargeConstructName = binChargeConstructName;
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

	public java.lang.String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

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

	public java.lang.String getAdirectorNamePath() {
		return adirectorNamePath;
	}

	public void setAdirectorNamePath(java.lang.String adirectorNamePath) {
		this.adirectorNamePath = adirectorNamePath;
	}

	public java.lang.String getAmonitorNamePath() {
		return amonitorNamePath;
	}

	public void setAmonitorNamePath(java.lang.String amonitorNamePath) {
		this.amonitorNamePath = amonitorNamePath;
	}

	public java.lang.String getBdirectorNamePath() {
		return bdirectorNamePath;
	}

	public void setBdirectorNamePath(java.lang.String bdirectorNamePath) {
		this.bdirectorNamePath = bdirectorNamePath;
	}

	public java.lang.String getBinChargeConstructNamePath() {
		return binChargeConstructNamePath;
	}

	public void setBinChargeConstructNamePath(java.lang.String binChargeConstructNamePath) {
		this.binChargeConstructNamePath = binChargeConstructNamePath;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}
}
