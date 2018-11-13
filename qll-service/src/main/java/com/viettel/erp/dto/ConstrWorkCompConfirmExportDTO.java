package com.viettel.erp.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
@JsonIgnoreProperties(ignoreUnknown= true)
public class ConstrWorkCompConfirmExportDTO {
	private java.lang.Long constructId;
	private java.lang.String code;
	private java.lang.String station_code;
	private java.lang.String contractCode;
	private java.lang.String constrtName;
	private java.lang.String constrtAddress;
	private java.lang.String chargeMonitorName;
	private java.lang.String chargeConstructName;
	private java.util.Date handoverDate;
	private java.lang.String handoverPlace;
	private java.lang.String groundHandoverContent;
	private java.lang.String otherDocuments;
	private java.lang.String otherComments;
	private java.lang.Long startdate;
	private java.lang.Long startMonth;
	private java.lang.String startYear;
	private java.lang.Long stopdate;
	private java.lang.Long stopMonth;
	private java.lang.String stopYear;
	private java.lang.String startHour;
	private java.lang.String stopHour;
	private java.lang.Long constrWorkCompConfirmId;
	private java.lang.Long aDirectorId;
	private java.lang.String aDirectorName; 
	private java.lang.String aDirectorPath;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.String aInChargeMonitorName;
	private java.lang.String aInChargeMonitorPath;
	private java.lang.Long bDirectorId;
	private java.lang.String bDirectorName;
	private java.lang.String bDirectorPath;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String bInChargeConstructName;
	private java.lang.String bInChargeConstructPath;
	private java.util.Date confirmFromDate;
	private java.util.Date confirmToDate;
	private java.lang.String confirmPlace;
	private java.lang.Long conclusion;
	private java.lang.String conclusionText;
	private java.util.Date createdDate;
	private java.lang.String ohterComments;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private java.lang.Long signDateInMonth;
	private java.lang.Long signMonth;
	private java.lang.String signYear;
	
	private java.lang.String aDirectorNameSign;
	private java.lang.String aInChargeMonitorNameSign;
	private java.lang.String bDirectorNameSign;
	private java.lang.String bInChargeConstructNameSign;
	
	
	private List<ConstrWorkCompConfListLessDTO> listConstrWorkCompConfLessDTO = Lists.newArrayList();
	public java.lang.Long getConstructId() {
		return constructId;
	}
	
	
	public java.lang.String getaDirectorNameSign() {
		return Strings.nullToEmpty(aDirectorNameSign);
	}


	public void setaDirectorNameSign(java.lang.String aDirectorNameSign) {
		this.aDirectorNameSign = aDirectorNameSign;
	}


	public java.lang.String getaInChargeMonitorNameSign() {
		return Strings.nullToEmpty(aInChargeMonitorNameSign);
	}


	public void setaInChargeMonitorNameSign(java.lang.String aInChargeMonitorNameSign) {
		this.aInChargeMonitorNameSign = aInChargeMonitorNameSign;
	}


	public java.lang.String getbDirectorNameSign() {
		return Strings.nullToEmpty(bDirectorNameSign);
	}


	public void setbDirectorNameSign(java.lang.String bDirectorNameSign) {
		this.bDirectorNameSign = bDirectorNameSign;
	}


	public java.lang.String getbInChargeConstructNameSign() {
		return Strings.nullToEmpty(bInChargeConstructNameSign);
	}


	public void setbInChargeConstructNameSign(java.lang.String bInChargeConstructNameSign) {
		this.bInChargeConstructNameSign = bInChargeConstructNameSign;
	}


	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}
	public java.lang.String getCode() {
		return Strings.nullToEmpty(code);
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	public java.lang.String getStation_code() {
		return Strings.nullToEmpty(station_code);
	}
	public void setStation_code(java.lang.String station_code) {
		this.station_code = station_code;
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
	public java.lang.String getChargeMonitorName() {
		return Strings.nullToEmpty(chargeMonitorName);
	}
	public void setChargeMonitorName(java.lang.String chargeMonitorName) {
		this.chargeMonitorName = chargeMonitorName;
	}
	public java.lang.String getChargeConstructName() {
		return Strings.nullToEmpty(chargeConstructName);
	}
	public void setChargeConstructName(java.lang.String chargeConstructName) {
		this.chargeConstructName = chargeConstructName;
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
		return Strings.nullToEmpty(groundHandoverContent);
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
		return  Strings.nullToEmpty(otherComments);
	}
	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}
	public java.lang.Long getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(java.lang.Long startMonth) {
		this.startMonth = startMonth;
	}
	public java.lang.String getStartYear() {
		return Strings.nullToEmpty(startYear);
	}
	public void setStartYear(java.lang.Long startYear) {
		this.startYear = String.valueOf(startYear).replace(",", "");
	}

	public java.lang.Long getStartdate() {
		return startdate;
	}
	public void setStartdate(java.lang.Long startdate) {
		this.startdate = startdate;
	}
	public java.lang.Long getStopdate() {
		return stopdate;
	}
	public void setStopdate(java.lang.Long stopdate) {
		this.stopdate = stopdate;
	}
	public java.lang.Long getStopMonth() {
		return stopMonth;
	}
	public void setStopMonth(java.lang.Long stopMonth) {
		this.stopMonth = stopMonth;
	}
	public java.lang.String getStopYear() {
		return stopYear;
	}
	public void setStopYear(java.lang.Long stopYear) {
		this.stopYear = String.valueOf(stopYear).replace(",","");
	}
	public java.lang.Long getConstrWorkCompConfirmId() {
		return constrWorkCompConfirmId;
	}
	public void setConstrWorkCompConfirmId(java.lang.Long constrWorkCompConfirmId) {
		this.constrWorkCompConfirmId = constrWorkCompConfirmId;
	}
	
	public java.lang.String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}
	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}
	public java.lang.Long getaDirectorId() {
		return aDirectorId;
	}
	public void setaDirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}
	public java.lang.Long getaInChargeMonitorId() {
		return aInChargeMonitorId;
	}
	public void setaInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}
	public java.lang.Long getbDirectorId() {
		return bDirectorId;
	}
	public void setbDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}
	public java.lang.Long getbInChargeConstructId() {
		return bInChargeConstructId;
	}
	public void setbInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}
	public java.util.Date getConfirmFromDate() {
		return confirmFromDate;
	}
	public void setConfirmFromDate(java.util.Date confirmFromDate) {
		this.confirmFromDate = confirmFromDate;
	}
	public java.util.Date getConfirmToDate() {
		return confirmToDate;
	}
	public void setConfirmToDate(java.util.Date confirmToDate) {
		this.confirmToDate = confirmToDate;
	}
	public java.lang.String getConfirmPlace() {
		return Strings.nullToEmpty(confirmPlace);
	}
	public void setConfirmPlace(java.lang.String confirmPlace) {
		this.confirmPlace = confirmPlace;
	}
	public java.lang.Long getConclusion() {
		return conclusion;
	}
	public void setConclusion(java.lang.Long conclusion) {
		this.conclusion = conclusion;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.lang.String getOhterComments() {
		return Strings.nullToEmpty(ohterComments) ;
	}
	public void setOhterComments(java.lang.String ohterComments) {
		this.ohterComments = ohterComments;
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
	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkCompConfLessDTO() {
		return listConstrWorkCompConfLessDTO;
	}
	public void setListConstrWorkCompConfLessDTO(List<ConstrWorkCompConfListLessDTO> listConstrWorkCompConfLessDTO) {
		this.listConstrWorkCompConfLessDTO = listConstrWorkCompConfLessDTO;
	}
	public java.lang.String getaDirectorName() {
		return Strings.nullToEmpty(aDirectorName);
	}
	public void setaDirectorName(java.lang.String aDirectorName) {
		this.aDirectorName = aDirectorName;
	}
	public java.lang.String getaInChargeMonitorName() {
		return Strings.nullToEmpty(aInChargeMonitorName) ;
	}
	public void setaInChargeMonitorName(java.lang.String aInChargeMonitorName) {
		this.aInChargeMonitorName = aInChargeMonitorName;
	}
	public java.lang.String getbDirectorName() {
		return Strings.nullToEmpty(bDirectorName);
	}
	public void setbDirectorName(java.lang.String bDirectorName) {
		this.bDirectorName = bDirectorName;
	}
	public java.lang.String getbInChargeConstructName() {
		return Strings.nullToEmpty(bInChargeConstructName);
	}
	public void setbInChargeConstructName(java.lang.String bInChargeConstructName) {
		this.bInChargeConstructName = bInChargeConstructName;
	}
	public java.lang.String getConclusionText() {
		return Strings.nullToEmpty(conclusionText);
	}
	public void setConclusionText(java.lang.String conclusionText) {
		this.conclusionText = conclusionText;
	}
	public java.lang.String getStartHour() {
		return startHour;
	}
	public void setStartHour(int hh,int mm) {
		this.startHour = hh+":"+mm;
	}
	public java.lang.String getStopHour() {
		return stopHour;
	}
	public void setStopHour(int hh,int mm) {
		this.stopHour = hh+":"+mm;
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
	public java.lang.Long getSignDateInMonth() {
		return signDateInMonth;
	}
	public void setSignDateInMonth(java.lang.Long signDateInMonth) {
		this.signDateInMonth = signDateInMonth;
	}
	public java.lang.Long getSignMonth() {
		return signMonth;
	}
	public void setSignMonth(java.lang.Long signMonth) {
		this.signMonth = signMonth;
	}
	public java.lang.String getSignYear() {
		return signYear;
	}
	public void setSignYear(java.lang.Long signYear) {
		this.signYear = String.valueOf(signYear).replace(",", "");
	}
	public java.lang.String getaDirectorPath() {
		return aDirectorPath;
	}
	public void setaDirectorPath(java.lang.String aDirectorPath) {
		this.aDirectorPath = aDirectorPath;
	}
	public java.lang.String getaInChargeMonitorPath() {
		return aInChargeMonitorPath;
	}
	public void setaInChargeMonitorPath(java.lang.String aInChargeMonitorPath) {
		this.aInChargeMonitorPath = aInChargeMonitorPath;
	}
	public java.lang.String getbDirectorPath() {
		return bDirectorPath;
	}
	public void setbDirectorPath(java.lang.String bDirectorPath) {
		this.bDirectorPath = bDirectorPath;
	}
	public java.lang.String getbInChargeConstructPath() {
		return bInChargeConstructPath;
	}
	public void setbInChargeConstructPath(java.lang.String bInChargeConstructPath) {
		this.bInChargeConstructPath = bInChargeConstructPath;
	}
	

}
