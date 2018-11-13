/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.bo.WorkItemsAcceptanceBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "WORK_ITEMS_ACCEPTANCEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkItemsAcceptanceDTO extends BaseFWDTOImpl<WorkItemsAcceptanceBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9194451638593899420L;
	private Long workItemsAcceptanceId;
	private String code;
	private Long monitorId;
	private Long inChargeConstructId;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date acceptFromDate;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date acceptToDate;
	
	private String acceptPlace;
	private String applyBenchmark;
	private String constructionQuality;
	private String otherDocuments;
	private String otherComments;
	private java.util.Date createdDate;
	private Long createdUserId;
	private java.util.Date approvalDate;
	private Long statusCa;
	private Long isActive;
	private Long conclusion;
	private String completeRequest;
	
	private String comments;
	
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date signDate;
	
	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";
	
	private java.lang.String signPlace;
	// tungpv
	private String constrtCode;
	private String constrtName;
	private String constrtAddress;
	private String contractCode;
	private String contractName;
	private Long constructId;
	private String stationCode;
	private Long contractId;
	
	private List<EstimatesWorkItemsDTO> cvntList = Lists.newArrayList();
	
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
	
	private String monitorIdName;
	private String inChargeConstructIdName;
	private java.lang.Long constrCompReMapId;
	private String monitorIdNamePath;
	private String inChargeConstructIdNamePath;
	
	private String monitorIdSign;
	private String inChargeConstructIdSign;
	
	private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;
	
	
	public String getMonitorIdSign() {
		return Strings.nullToEmpty(monitorIdSign);
	}

	public void setMonitorIdSign(String monitorIdSign) {
		this.monitorIdSign = monitorIdSign;
	}

	public String getInChargeConstructIdSign() {
		return Strings.nullToEmpty(inChargeConstructIdSign);
	}

	public void setInChargeConstructIdSign(String inChargeConstructIdSign) {
		this.inChargeConstructIdSign = inChargeConstructIdSign;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getMonitorIdNamePath() {
		return monitorIdNamePath;
	}

	public void setMonitorIdNamePath(String monitorIdNamePath) {
		this.monitorIdNamePath = monitorIdNamePath;
	}

	public String getInChargeConstructIdNamePath() {
		return inChargeConstructIdNamePath;
	}

	public void setInChargeConstructIdNamePath(String inChargeConstructIdNamePath) {
		this.inChargeConstructIdNamePath = inChargeConstructIdNamePath;
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

	public String getSignDateDay() {
		if (signDate != null) {
			signDateDay = DateFormatUtils.format(signDate, "dd");
		}
		return signDateDay;
	}

	public String getSignDateMonth() {
		if (signDate != null) {
			signDateMonth = DateFormatUtils.format(signDate, "MM");
		}
		return signDateMonth;
	}

	public String getSignDateYear() {
		if (signDate != null) {
			signDateYear = DateFormatUtils.format(signDate, "yyyy");
		}
		return signDateYear;
	}

	public String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}

	public void setConstrtAddress(String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	/**
	 * @return the monitorIdName
	 */
	public String getMonitorIdName() {
		return monitorIdName;
	}

	/**
	 * @param monitorIdName the monitorIdName to set
	 */
	public void setMonitorIdName(String monitorIdName) {
		this.monitorIdName = monitorIdName;
	}

	/**
	 * @return the inChargeConstructIdName
	 */
	public String getInChargeConstructIdName() {
		return inChargeConstructIdName;
	}

	/**
	 * @param inChargeConstructIdName the inChargeConstructIdName to set
	 */
	public void setInChargeConstructIdName(String inChargeConstructIdName) {
		this.inChargeConstructIdName = inChargeConstructIdName;
	}

	/**
	 * @return the acceptFromDateHour
	 */
	public String getAcceptFromDateHour() {
		if (acceptFromDate != null) {
			acceptFromDateHour = acceptFromDate.toString().substring(11, 13);
		}
		return acceptFromDateHour;
	}

	/**
	 * @return the acceptFromDateMinute
	 */
	public String getAcceptFromDateMinute() {
		if (acceptFromDate != null) {
			acceptFromDateMinute = DateFormatUtils.format(acceptFromDate, "mm");
		}
		return acceptFromDateMinute;
	}

	/**
	 * @return the acceptFromDateDay
	 */
	public String getAcceptFromDateDay() {
		if (acceptFromDate != null) {
			acceptFromDateDay = DateFormatUtils.format(acceptFromDate, "dd");
		}
		return acceptFromDateDay;
	}

	/**
	 * @return the acceptFromDateMonth
	 */
	public String getAcceptFromDateMonth() {
		if (acceptFromDate != null) {
			acceptFromDateMonth = DateFormatUtils.format(acceptFromDate, "MM");
		}
		return acceptFromDateMonth;
	}

	/**
	 * @return the acceptFromDateYear
	 */
	public String getAcceptFromDateYear() {
		if (acceptFromDate != null) {
			acceptFromDateYear = DateFormatUtils.format(acceptFromDate, "yyyy");
		}
		return acceptFromDateYear;
	}

	/**
	 * @return the acceptToDateHour
	 */
	public String getAcceptToDateHour() {
		if (acceptToDate != null) {
			acceptToDateHour = acceptToDate.toString().substring(11, 13);
		}
		return acceptToDateHour;
	}

	/**
	 * @return the acceptToDateMinute
	 */
	public String getAcceptToDateMinute() {
		if (acceptToDate != null) {
			acceptToDateMinute = DateFormatUtils.format(acceptToDate, "mm");
		}
		return acceptToDateMinute;
	}

	/**
	 * @return the acceptToDateDay
	 */
	public String getAcceptToDateDay() {
		if (acceptToDate != null) {
			acceptToDateDay = DateFormatUtils.format(acceptToDate, "dd");
		}
		return acceptToDateDay;
	}


	/**
	 * @return the acceptToDateMonth
	 */
	public String getAcceptToDateMonth() {
		if (acceptToDate != null) {
			acceptToDateMonth = DateFormatUtils.format(acceptToDate, "MM");
		}
		return acceptToDateMonth;
	}

	/**
	 * @return the acceptToDateYear
	 */
	public String getAcceptToDateYear() {
		if (acceptToDate != null) {
			acceptToDateYear = DateFormatUtils.format(acceptToDate, "yyyy");
		}
		return acceptToDateYear;
	}

	/**
	 * @return the cvntList
	 */
	public List<EstimatesWorkItemsDTO> getCvntList() {
		return cvntList;
	}

	/**
	 * @param cvntList the cvntList to set
	 */
	public void setCvntList(List<EstimatesWorkItemsDTO> cvntList) {
		this.cvntList = cvntList;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the constrtName
	 */
	public String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	/**
	 * @param constrtName the constrtName to set
	 */
	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	public String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Override
	public WorkItemsAcceptanceBO toModel() {
		WorkItemsAcceptanceBO workItemsAcceptanceBO = new WorkItemsAcceptanceBO();
		
		for (EstimatesWorkItemsDTO dto : cvntList) {
			EstimatesWorkItemsBO bo = dto.toModel();
			bo.getWorkItemsAcceptances().add(workItemsAcceptanceBO);
			workItemsAcceptanceBO.getEstimatesWorkItems().add(bo);
		}
		
		workItemsAcceptanceBO.setWorkItemsAcceptanceId(this.workItemsAcceptanceId);
		workItemsAcceptanceBO.setCode(this.code);
		workItemsAcceptanceBO.setMonitorId(this.monitorId);
		workItemsAcceptanceBO.setInChargeConstructId(this.inChargeConstructId);
		workItemsAcceptanceBO.setAcceptFromDate(this.acceptFromDate);
		workItemsAcceptanceBO.setAcceptToDate(this.acceptToDate);
		workItemsAcceptanceBO.setAcceptPlace(this.acceptPlace);
		workItemsAcceptanceBO.setApplyBenchmark(this.applyBenchmark);
		workItemsAcceptanceBO.setConstructionQuality(this.constructionQuality);
		workItemsAcceptanceBO.setOtherDocuments(this.otherDocuments);
		workItemsAcceptanceBO.setOtherComments(this.otherComments);
		workItemsAcceptanceBO.setCreatedDate(this.createdDate);
		workItemsAcceptanceBO.setCreatedUserId(this.createdUserId);
		workItemsAcceptanceBO.setApprovalDate(this.approvalDate);
		workItemsAcceptanceBO.setStatusCa(this.statusCa);
		workItemsAcceptanceBO.setIsActive(this.isActive);
		workItemsAcceptanceBO.setConclusion(this.conclusion);
		workItemsAcceptanceBO.setCompleteRequest(this.completeRequest);
		workItemsAcceptanceBO.setConstructId(this.constructId);
		workItemsAcceptanceBO.setSignDate(this.signDate);
		workItemsAcceptanceBO.setSignPlace(this.signPlace);
		return workItemsAcceptanceBO;
	}

	@Override
	public Long getFWModelId() {
		return workItemsAcceptanceId;
	}

	@Override
	public String catchName() {
		return getWorkItemsAcceptanceId().toString();
	}

	public Long getWorkItemsAcceptanceId() {
		return workItemsAcceptanceId;
	}

	public void setWorkItemsAcceptanceId(Long workItemsAcceptanceId) {
		this.workItemsAcceptanceId = workItemsAcceptanceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the monitorId
	 */
	public Long getMonitorId() {
		return monitorId;
	}

	/**
	 * @param monitorId the monitorId to set
	 */
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	/**
	 * @return the inChargeConstructId
	 */
	public Long getInChargeConstructId() {
		return inChargeConstructId;
	}

	/**
	 * @param inChargeConstructId the inChargeConstructId to set
	 */
	public void setInChargeConstructId(Long inChargeConstructId) {
		this.inChargeConstructId = inChargeConstructId;
	}

	public java.util.Date getAcceptFromDate() {
		return acceptFromDate;
	}

	public void setAcceptFromDate(java.util.Date acceptFromDate) {
		this.acceptFromDate = acceptFromDate;
	}

	public java.util.Date getAcceptToDate() {
		return acceptToDate;
	}

	public void setAcceptToDate(java.util.Date acceptToDate) {
		this.acceptToDate = acceptToDate;
	}

	public String getAcceptPlace() {
		return Strings.nullToEmpty(acceptPlace);
	}

	public void setAcceptPlace(String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	public String getApplyBenchmark() {
		return applyBenchmark;
	}

	public void setApplyBenchmark(String applyBenchmark) {
		this.applyBenchmark = applyBenchmark;
	}

	public String getConstructionQuality() {
		return constructionQuality;
	}

	public void setConstructionQuality(String constructionQuality) {
		this.constructionQuality = constructionQuality;
	}

	public String getOtherDocuments() {
		return Strings.nullToEmpty(otherDocuments);
	}

	public void setOtherDocuments(String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public String getOtherComments() {
		return Strings.nullToEmpty(otherComments);
	}

	public void setOtherComments(String otherComments) {
		this.otherComments = otherComments;
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

	public Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(Long conclusion) {
		this.conclusion = conclusion;
	}

	public String getCompleteRequest() {
		return Strings.nullToEmpty(completeRequest);
	}

	public void setCompleteRequest(String completeRequest) {
		this.completeRequest = completeRequest;
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
}
