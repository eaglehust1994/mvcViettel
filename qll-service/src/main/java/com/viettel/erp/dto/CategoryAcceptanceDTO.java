/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.text.DateFormat;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.base.Strings;
import com.viettel.erp.bo.AMaterialRecoveryMinutesModelBO;
import com.viettel.erp.bo.CategoryAcceptanceBO;
import com.viettel.erp.bo.CategoryAcceptanceModelBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapReportBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CATEGORY_ACCEPTANCEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryAcceptanceDTO extends BaseFWDTOImpl<CategoryAcceptanceBO> {

private java.lang.Long constructId;
private java.lang.String constructName;
private java.lang.Long categoryAcceptanceId;
private java.lang.String code;
private java.lang.Long aMonitorId;
private java.lang.Long bInChargeConstructId;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date acceptFromDate;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date acceptToDate;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date signDate;
private java.lang.String acceptPlace;
private java.lang.String applyBenchmark;
private java.lang.String constructionQuality;
private java.lang.String otherDocuments;
private java.lang.String otherComments;
private java.lang.Long conclusion;
private java.lang.String completeRequest;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
@JsonSerialize(using = CustomJsonDateSerializer.class)
private java.util.Date contractDateSign;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.lang.Long estimatesItemChildId;
private java.lang.String itemCode;
private java.lang.String itemName;
private java.lang.String contractCode;
private java.lang.String contractName;
private java.lang.String constrtCode;
private java.lang.String constructAddress;
private java.lang.String amonitorName;
private java.lang.String categoryAcceptanceName;
private java.lang.String binChargeConstructName;
private java.lang.String estimatesItemChildName;
private java.lang.String date;
private java.lang.String month;
private java.lang.String year;
private java.lang.Long constrcompreMapId;
private java.lang.Long employeId;
private java.lang.String signPlace;
private java.lang.String stationCode;
private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

private String acceptFromDateHour = "";
private String acceptFromDateMinute = "";
private String acceptFromDateDay = "";
private String acceptFromDateMonth = "";
private String acceptFromDateYear = "";

private String acceptToDateHour = "";
private String acceptToDateMinute = "";
private String acceptToDateDay = "";
private String acceptToDateMonth = "";
private String acceptToDateYear = "";


private String conclusionaccept = "";
private String conclusionnotaccept = "";


private String signDateDay = "";
private String signDateMonth = "";
private String signDateYear = "";

private String contractDateSignDay = "";
private String contractDateSignMonth = "";
private String contractDateSignYear = "";

private java.lang.String amonitorNamePath;
private java.lang.String binChargeConstructNamePath;

private java.lang.String amonitorNameSign;
private java.lang.String binChargeConstructNameSign;

private java.lang.Double contractId;

private java.lang.String comments;
public java.lang.String getComments() {
	return comments;
}

public void setComments(java.lang.String comments) {
	this.comments = comments;
}

public java.lang.String getAmonitorNameSign() {
	return Strings.nullToEmpty(amonitorNameSign);
}

public void setAmonitorNameSign(java.lang.String amonitorNameSign) {
	this.amonitorNameSign = amonitorNameSign;
}

public java.lang.String getBinChargeConstructNameSign() {
	return Strings.nullToEmpty(binChargeConstructNameSign);
}

public void setBinChargeConstructNameSign(java.lang.String binChargeConstructNameSign) {
	this.binChargeConstructNameSign = binChargeConstructNameSign;
}

public java.lang.String getAmonitorNamePath() {
	return amonitorNamePath;
}

public void setAmonitorNamePath(java.lang.String amonitorNamePath) {
	this.amonitorNamePath = amonitorNamePath;
}

public java.lang.String getBinChargeConstructNamePath() {
	return binChargeConstructNamePath;
}

public void setBinChargeConstructNamePath(java.lang.String binChargeConstructNamePath) {
	this.binChargeConstructNamePath = binChargeConstructNamePath;
}

public String getContractDateSignDay() {
	if(contractDateSign != null){
		contractDateSignDay = DateFormatUtils.format(contractDateSign, "dd");
	}
	return contractDateSignDay;
}

public void setContractDateSignDay(String contractDateSignDay) {
	this.contractDateSignDay = contractDateSignDay;
}

public String getContractDateSignMonth() {
	if(contractDateSign != null){
		contractDateSignMonth = DateFormatUtils.format(contractDateSign, "MM");
	}
	return contractDateSignMonth;
}

public void setContractDateSignMonth(String contractDateSignMonth) {
	this.contractDateSignMonth = contractDateSignMonth;
}

public String getContractDateSignYear() {
	if(contractDateSign != null){
		contractDateSignYear = DateFormatUtils.format(contractDateSign, "yyyy");
	}
	return contractDateSignYear;
}

public void setContractDateSignYear(String contractDateSignYear) {
	this.contractDateSignYear = contractDateSignYear;
}




public java.lang.Long getaMonitorId() {
	return aMonitorId;
}

public void setaMonitorId(java.lang.Long aMonitorId) {
	this.aMonitorId = aMonitorId;
}

public java.lang.Long getbInChargeConstructId() {
	return bInChargeConstructId;
}

public void setbInChargeConstructId(java.lang.Long bInChargeConstructId) {
	this.bInChargeConstructId = bInChargeConstructId;
}

public String getAcceptFromDateHour() {
	if(acceptFromDate != null){
		acceptFromDateHour = acceptFromDate.toString().substring(11, 13);
	}	
	return acceptFromDateHour;
}

public void setAcceptFromDateHour(String acceptFromDateHour) {
	this.acceptFromDateHour = acceptFromDateHour;
}

public String getAcceptFromDateMinute() {
	if (acceptFromDate != null) {
		acceptFromDateMinute = DateFormatUtils.format(acceptFromDate, "mm");
	}
	return acceptFromDateMinute;
}

public void setAcceptFromDateMinute(String acceptFromDateMinute) {
	this.acceptFromDateMinute = acceptFromDateMinute;
}

public String getAcceptFromDateDay() {
	if (acceptFromDate != null) {
		acceptFromDateDay = DateFormatUtils.format(acceptFromDate, "dd");
	}
	return acceptFromDateDay;
}

public void setAcceptFromDateDay(String acceptFromDateDay) {
	this.acceptFromDateDay = acceptFromDateDay;
}

public String getAcceptFromDateMonth() {
	if (acceptFromDate != null) {
		acceptFromDateMonth = DateFormatUtils.format(acceptFromDate, "MM");
	}
	return acceptFromDateMonth;	
}
public void setAcceptFromDateMonth(String acceptFromDateMonth) {
	this.acceptFromDateMonth = acceptFromDateMonth;
}

public String getAcceptFromDateYear() {
	if (acceptFromDate != null) {
		acceptFromDateYear = DateFormatUtils.format(acceptFromDate, "yyyy");
	}
	return acceptFromDateYear;
}

public void setAcceptFromDateYear(String acceptFromDateYear) {
	this.acceptFromDateYear = acceptFromDateYear;
}

public String getAcceptToDateHour() {
	if (acceptToDate != null) {
		acceptToDateHour = acceptToDate.toString().substring(11, 13);
	}
	return acceptToDateHour;
}

public void setAcceptToDateHour(String acceptToDateHour) {
	this.acceptToDateHour = acceptToDateHour;
}

public String getAcceptToDateMinute() {
	if (acceptToDate != null) {
		acceptToDateMinute = DateFormatUtils.format(acceptToDate, "mm");
	}
	return acceptToDateMinute;
}

public void setAcceptToDateMinute(String acceptToDateMinute) {
	this.acceptToDateMinute = acceptToDateMinute;
}

public String getAcceptToDateDay() {
	if (acceptToDate != null) {
		acceptToDateDay = DateFormatUtils.format(acceptToDate, "dd");
	}
	return acceptToDateDay;
}

public void setAcceptToDateDay(String acceptToDateDay) {
	this.acceptToDateDay = acceptToDateDay;
}

public String getAcceptToDateMonth() {
	if (acceptToDate != null) {
		acceptToDateMonth = DateFormatUtils.format(acceptToDate, "MM");
	}
	return acceptToDateMonth;
}

public void setAcceptToDateMonth(String acceptToDateMonth) {
	this.acceptToDateMonth = acceptToDateMonth;
}

public String getAcceptToDateYear() {
	if (acceptToDate != null) {
		acceptToDateYear = DateFormatUtils.format(acceptToDate, "yyyy");
	}
	return acceptToDateYear;
}

public void setAcceptToDateYear(String acceptToDateYear) {
	this.acceptToDateYear = acceptToDateYear;
}

public String getConclusionaccept() {
	if(Float.floatToRawIntBits(conclusion) == 0){
		conclusionaccept = "V" ;
	}
	return conclusionaccept;
}

public void setConclusionaccept(String conclusionaccept) {
	this.conclusionaccept = conclusionaccept;
}

public String getConclusionnotaccept() {
	if(Float.floatToRawIntBits(conclusion) == 1){
		conclusionnotaccept = "V" ;
	}
	return conclusionnotaccept;
}

public void setConclusionnotaccept(String conclusionnotaccept) {
	this.conclusionnotaccept = conclusionnotaccept;
}

public String getSignDateDay() {
	if(signDate != null){
		signDateDay = DateFormatUtils.format(signDate, "dd");
	}
	return signDateDay;
}

public void setSignDateDay(String signDateDay) {
	this.signDateDay = signDateDay;
}

public String getSignDateMonth() {
	if(signDate != null){
		signDateMonth = DateFormatUtils.format(signDate, "MM");
	}
	return signDateMonth;
}

public void setSignDateMonth(String signDateMonth) {
	this.signDateMonth = signDateMonth;
}

public String getSignDateYear() {
	if(signDate != null){
		signDateYear = DateFormatUtils.format(signDate, "yyyy");
	}
	return signDateYear;
}

public void setSignDateYear(String signDateYear) {
	this.signDateYear = signDateYear;
}

public EstimatesItemsChildDTO getEitemChildDTO() {
	return eitemChildDTO;
}

public void setEitemChildDTO(EstimatesItemsChildDTO eitemChildDTO) {
	this.eitemChildDTO = eitemChildDTO;
}

private EstimatesItemsChildDTO eitemChildDTO;
private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;

public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
	return constrCompleteRecordMap;
}

public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
	this.constrCompleteRecordMap = constrCompleteRecordMap;
}

    @Override
    public CategoryAcceptanceBO toModel() {
        CategoryAcceptanceBO categoryAcceptanceBO = new CategoryAcceptanceBO();
        categoryAcceptanceBO.setConstructId(this.constructId);
        categoryAcceptanceBO.setCategoryAcceptanceId(this.categoryAcceptanceId);
        categoryAcceptanceBO.setCode(this.code);
        categoryAcceptanceBO.setAMonitorId(this.aMonitorId);
        categoryAcceptanceBO.setBInChargeConstructId(this.bInChargeConstructId);
        categoryAcceptanceBO.setAcceptFromDate(this.acceptFromDate);
        categoryAcceptanceBO.setAcceptToDate(this.acceptToDate);
        categoryAcceptanceBO.setAcceptPlace(this.acceptPlace);
        categoryAcceptanceBO.setApplyBenchmark(this.applyBenchmark);
        categoryAcceptanceBO.setConstructionQuality(this.constructionQuality);
        categoryAcceptanceBO.setOtherDocuments(this.otherDocuments);
        categoryAcceptanceBO.setOtherComments(this.otherComments);
        categoryAcceptanceBO.setConclusion(this.conclusion);
        categoryAcceptanceBO.setCompleteRequest(this.completeRequest);
        categoryAcceptanceBO.setCreatedDate(this.createdDate);
        categoryAcceptanceBO.setCreatedUserId(this.createdUserId);
        categoryAcceptanceBO.setApprovalDate(this.approvalDate);
        categoryAcceptanceBO.setStatusCa(this.statusCa);
        categoryAcceptanceBO.setIsActive(this.isActive);
        categoryAcceptanceBO.setSignPlace(this.signPlace);
        categoryAcceptanceBO.setSignDate(this.signDate);
        categoryAcceptanceBO.setEstimatesItemChildId(this.estimatesItemChildId);
        
        if(constrCompleteRecordMap != null){
        	CategoryAcceptanceModelBO chilModelBO = constrCompleteRecordMap.toModelCategoryAcceptance();
        	chilModelBO.setCategoryAcceptaceBO(categoryAcceptanceBO);
        	categoryAcceptanceBO.setCategoryAcceptanceModelBO(chilModelBO);       	
        	
    	}
        return categoryAcceptanceBO;
    }   

    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    @Override
     public Long getFWModelId() {
        return categoryAcceptanceId;
    }
   
    @Override
    public String catchName() {
        return getCategoryAcceptanceId().toString();
    }
    public java.lang.Long getCategoryAcceptanceId(){
    return categoryAcceptanceId;
    }
    public void setCategoryAcceptanceId(java.lang.Long categoryAcceptanceId)
    {
    this.categoryAcceptanceId = categoryAcceptanceId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getAMonitorId(){
    return aMonitorId;
    }
    public void setAMonitorId(java.lang.Long aMonitorId)
    {
    this.aMonitorId = aMonitorId;
    }
    
    public java.lang.Long getBInChargeConstructId(){
    return bInChargeConstructId;
    }
    public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
    {
    this.bInChargeConstructId = bInChargeConstructId;
    }
    
    public java.util.Date getAcceptFromDate(){
    return acceptFromDate;
    }
    public void setAcceptFromDate(java.util.Date acceptFromDate)
    {
    this.acceptFromDate = acceptFromDate;
    }
    
    public java.util.Date getAcceptToDate(){
    return acceptToDate;
    }
    public void setAcceptToDate(java.util.Date acceptToDate)
    {
    this.acceptToDate = acceptToDate;
    }
    
    public java.lang.String getAcceptPlace(){
    return acceptPlace;
    }
    public void setAcceptPlace(java.lang.String acceptPlace)
    {
    this.acceptPlace = acceptPlace;
    }
    
    public java.lang.String getApplyBenchmark(){
    return Strings.nullToEmpty(applyBenchmark);
    }
    public void setApplyBenchmark(java.lang.String applyBenchmark)
    {
    this.applyBenchmark = applyBenchmark;
    }
    
    public java.lang.String getConstructionQuality(){
    return Strings.nullToEmpty(constructionQuality);
    }
    public void setConstructionQuality(java.lang.String constructionQuality)
    {
    this.constructionQuality = constructionQuality;
    }
    
    public java.lang.String getOtherDocuments(){
    return  Strings.nullToEmpty(otherDocuments);
    }
    public void setOtherDocuments(java.lang.String otherDocuments)
    {
    this.otherDocuments = otherDocuments;
    }
    
    public java.lang.String getOtherComments(){
    return Strings.nullToEmpty(otherComments);
    }
    public void setOtherComments(java.lang.String otherComments)
    {
    this.otherComments = otherComments;
    }
    
    public java.lang.Long getConclusion(){
    return conclusion;
    }
    public void setConclusion(java.lang.Long conclusion)
    {
    this.conclusion = conclusion;
    }
    
    public java.lang.String getCompleteRequest(){
    return Strings.nullToEmpty(completeRequest);
    }
    public void setCompleteRequest(java.lang.String completeRequest)
    {
    this.completeRequest = completeRequest;
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
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getEstimatesItemChildId(){
    return estimatesItemChildId;
    }
    public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId)
    {
    this.estimatesItemChildId = estimatesItemChildId;
    }

	public java.lang.String getItemCode() {
		return itemCode;
	}

	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}

	public java.lang.String getItemName() {
		return itemName;
	}

	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	public java.lang.String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}

	public java.lang.String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getConstructAddress() {
		return Strings.nullToEmpty(constructAddress);
	}

	public void setConstructAddress(java.lang.String constructAddress) {
		this.constructAddress = constructAddress;
	}

	public java.lang.String getConstructName() {
		return Strings.nullToEmpty(constructName);
	}

	public void setConstructName(java.lang.String constructName) {
		this.constructName = constructName;
	}

	public java.util.Date getContractDateSign() {
		return contractDateSign;
	}

	public void setContractDateSign(java.util.Date contractDateSign) {
		this.contractDateSign = contractDateSign;
	}

	public java.lang.String getBinChargeConstructName() {
		return binChargeConstructName;
	}

	public void setBinChargeConstructName(java.lang.String binChargeConstructName) {
		this.binChargeConstructName = binChargeConstructName;
	}

	public java.lang.String getAmonitorName() {
		return amonitorName;
	}

	public void setAmonitorName(java.lang.String amonitorName) {
		this.amonitorName = amonitorName;
	}

	public java.lang.String getCategoryAcceptanceName() {
		return categoryAcceptanceName;
	}

	public void setCategoryAcceptanceName(java.lang.String categoryAcceptanceName) {
		this.categoryAcceptanceName = categoryAcceptanceName;
	}

	public java.lang.String getEstimatesItemChildName() {
		return estimatesItemChildName;
	}

	public void setEstimatesItemChildName(java.lang.String estimatesItemChildName) {
		this.estimatesItemChildName = estimatesItemChildName;
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

	public java.lang.Long getConstrcompreMapId() {
		return constrcompreMapId;
	}

	public void setConstrcompreMapId(java.lang.Long constrcompreMapId) {
		this.constrcompreMapId = constrcompreMapId;
	}

	public java.lang.Long getEmployeId() {
		return employeId;
	}

	public void setEmployeId(java.lang.Long employeId) {
		this.employeId = employeId;
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

	public java.lang.String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public java.lang.String getStationCode() {
		return stationCode;
	}

	public void setStationCode(java.lang.String stationCode) {
		this.stationCode = stationCode;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}   
}
