/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialRecoveryMinutesModelBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapReportBO;
import com.viettel.erp.bo.QualityCableMeaReportBO;
import com.viettel.erp.bo.QualityCableMeaResultBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "QUALITY_CABLE_MEA_REPORTBO")
public class QualityCableMeaReportDTO extends BaseFWDTOImpl<QualityCableMeaReportBO> {

	private java.lang.Long constructId;
	private java.lang.Long qualityCableMeaReportId;
	private java.lang.String code;
	private java.lang.Long aMonitorId;
	private java.lang.Long bConstructId;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private List<QualityCableMeaResultDTO> qualityCableMeaResult = Lists.newArrayList();
	private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}

	public List<QualityCableMeaResultDTO> getQualityCableMeaResult() {
		return qualityCableMeaResult;
	}

	public void setQualityCableMeaResult(List<QualityCableMeaResultDTO> qualityCableMeaResult) {
		this.qualityCableMeaResult = qualityCableMeaResult;
	}

	@Override
	public QualityCableMeaReportBO toModel() {
		QualityCableMeaReportBO qualityCableMeaReportBO = new QualityCableMeaReportBO();
		qualityCableMeaReportBO.setConstructId(this.constructId);
		qualityCableMeaReportBO.setQualityCableMeaReportId(this.qualityCableMeaReportId);
		qualityCableMeaReportBO.setCode(this.code);
		qualityCableMeaReportBO.setSignDate(this.signDate);
		qualityCableMeaReportBO.setSignPlace(this.signPlace);
		qualityCableMeaReportBO.setAMonitorId(this.aMonitorId);
		qualityCableMeaReportBO.setBConstructId(this.bConstructId);
		qualityCableMeaReportBO.setCreatedDate(this.createdDate);
		qualityCableMeaReportBO.setCreatedUserId(this.createdUserId);
		qualityCableMeaReportBO.setApprovalDate(this.approvalDate);
		qualityCableMeaReportBO.setStatusCa(this.statusCa);
		qualityCableMeaReportBO.setIsActive(this.isActive);

		for (QualityCableMeaResultDTO qualityCableMeaResultDTO : qualityCableMeaResult) {
			QualityCableMeaResultBO childBO = qualityCableMeaResultDTO.toModel();
			childBO.setQualityCableMeaReport(qualityCableMeaReportBO);
			qualityCableMeaReportBO.getQualityCableMeaResult().add(childBO);
		}

//		if (constrCompleteRecordMap != null) {
//
//			ConstrCompleteRecordsMapReportBO childModelBO = constrCompleteRecordMap.toModelCableMeaReport();
//			childModelBO.setReportBO(qualityCableMeaReportBO);
//			qualityCableMeaReportBO.setConstrCompleteRecordsMapReportBO(childModelBO);
//		}

		return qualityCableMeaReportBO;
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

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return qualityCableMeaReportId;
	}

	@Override
	public String catchName() {
		return getQualityCableMeaReportId().toString();
	}

	public java.lang.Long getQualityCableMeaReportId() {
		return qualityCableMeaReportId;
	}

	public void setQualityCableMeaReportId(java.lang.Long qualityCableMeaReportId) {
		this.qualityCableMeaReportId = qualityCableMeaReportId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getAMonitorId() {
		return aMonitorId;
	}

	public void setAMonitorId(java.lang.Long aMonitorId) {
		this.aMonitorId = aMonitorId;
	}

	public java.lang.Long getBConstructId() {
		return bConstructId;
	}

	public void setBConstructId(java.lang.Long bConstructId) {
		this.bConstructId = bConstructId;
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

}
