/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "QUALITY_CABLE_MEA_REPORT")
// dinh dang truong is_active trong DB de remove
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE QUALITY_CABLE_MEA_REPORT q SET q.IS_ACTIVE = 0 WHERE q.QUALITY_CABLE_MEA_REPORT_ID = ? ")
@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class QualityCableMeaReportBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7388804907887254694L;
	private Long constructId;
	private Long qualityCableMeaReportId;
	private String code;
	private Long aMonitorId;
	private Long bConstructId;
	private java.util.Date createdDate;
	private Long createdUserId;
	private java.util.Date approvalDate;
	private Long statusCa;
	private Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;

	private List<QualityCableMeaResultBO> qualityCableMeaResult = Lists.newArrayList();
	private ConstrCompleteRecordsMapReportBO constrCompleteRecordsMapReportBO;

	@Column(name = "SIGN_DATE")
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGN_PLACE")
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	@OneToOne(mappedBy = "reportBO", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public ConstrCompleteRecordsMapReportBO getConstrCompleteRecordsMapReportBO() {
		return constrCompleteRecordsMapReportBO;
	}

	public void setConstrCompleteRecordsMapReportBO(ConstrCompleteRecordsMapReportBO constrCompleteRecordsMapReportBO) {
		this.constrCompleteRecordsMapReportBO = constrCompleteRecordsMapReportBO;
	}

	@OneToMany(mappedBy = "qualityCableMeaReport", cascade = CascadeType.ALL)
	public List<QualityCableMeaResultBO> getQualityCableMeaResult() {
		return qualityCableMeaResult;
	}

	public void setQualityCableMeaResult(List<QualityCableMeaResultBO> qualityCableMeaResult) {
		this.qualityCableMeaResult = qualityCableMeaResult;
	}

	public QualityCableMeaReportBO() {
		setColId("qualityCableMeaReportId");
		setColName("qualityCableMeaReportId");
		setUniqueColumn(new String[] { "qualityCableMeaReportId" });
	}

	public QualityCableMeaReportBO(Long qualityCableMeaReportId2) {
		this.qualityCableMeaReportId = qualityCableMeaReportId2;
	}

	@Column(name = "CONSTRUCT_ID", length = 22)
	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "QUALITY_CABLE_MEA_REPORT_SEQ") })
	@Column(name = "QUALITY_CABLE_MEA_REPORT_ID", length = 22)
	public Long getQualityCableMeaReportId() {
		return qualityCableMeaReportId;
	}

	public void setQualityCableMeaReportId(Long qualityCableMeaReportId) {
		this.qualityCableMeaReportId = qualityCableMeaReportId;
	}

	@Column(name = "CODE", length = 400)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "A_MONITOR_ID", length = 22)
	public Long getAMonitorId() {
		return aMonitorId;
	}

	public void setAMonitorId(Long aMonitorId) {
		this.aMonitorId = aMonitorId;
	}

	@Column(name = "B_CONSTRUCT_ID", length = 22)
	public Long getBConstructId() {
		return bConstructId;
	}

	public void setBConstructId(Long bConstructId) {
		this.bConstructId = bConstructId;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_USER_ID", length = 22)
	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Column(name = "APPROVAL_DATE", length = 7)
	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "STATUS_CA", length = 22)
	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	@Override
	public QualityCableMeaReportDTO toDTO() {
		QualityCableMeaReportDTO qualityCableMeaReportDTO = new QualityCableMeaReportDTO();
		// set cac gia tri
		qualityCableMeaReportDTO.setConstructId(this.constructId);
		qualityCableMeaReportDTO.setQualityCableMeaReportId(this.qualityCableMeaReportId);
		qualityCableMeaReportDTO.setSignDate(this.signDate);
        qualityCableMeaReportDTO.setSignPlace(this.signPlace);
		qualityCableMeaReportDTO.setCode(this.code);
		qualityCableMeaReportDTO.setAMonitorId(this.aMonitorId);
		qualityCableMeaReportDTO.setBConstructId(this.bConstructId);
		qualityCableMeaReportDTO.setCreatedDate(this.createdDate);
		qualityCableMeaReportDTO.setCreatedUserId(this.createdUserId);
		qualityCableMeaReportDTO.setApprovalDate(this.approvalDate);
		qualityCableMeaReportDTO.setStatusCa(this.statusCa);
		qualityCableMeaReportDTO.setIsActive(this.isActive);
		return qualityCableMeaReportDTO;
	}
}
