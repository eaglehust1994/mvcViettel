/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "titaziconstraccept")
@Table(name = "TIT_AZI_CONSTR_ACCEPT")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE TIT_AZI_CONSTR_ACCEPT c SET c.IS_ACTIVE = 0 WHERE c.TIT_AZI_CONSTR_ACCEPT_ID = ? AND STATUS_CA IN (0,3)")
@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class TitAziConstrAcceptBO extends BaseFWModelImpl {

	private static final long serialVersionUID = 7388804907887254694L;
	private java.lang.Long titAziConstrAcceptId;
	private java.lang.String code;
	private java.lang.Long aDirectorId;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bInChargeConstructId;
	private java.util.Date acceptFromDate;
	private java.util.Date acceptToDate;
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
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private Long constructId;
	
	/*private ConstrConstructionsBO constrConstructions;*/
	@Column(name = "CONSTRUCT_ID")
	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	private List<TitAziConstrAcceptListBO> titAziConstrAcceptList = Lists.newArrayList();

	@OneToMany(mappedBy = "titAziConstrAccept", cascade = CascadeType.ALL)
	public List<TitAziConstrAcceptListBO> getTitAziConstrAcceptList() {
		return titAziConstrAcceptList;
	}

	public void setTitAziConstrAcceptList(List<TitAziConstrAcceptListBO> titAziConstrAcceptList) {
		this.titAziConstrAcceptList = titAziConstrAcceptList;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "TIT_AZI_CONSTR_ACCEPT_SEQ") })
	@Column(name = "TIT_AZI_CONSTR_ACCEPT_ID", length = 22)
	public java.lang.Long getTitAziConstrAcceptId() {
		return titAziConstrAcceptId;
	}

	public void setTitAziConstrAcceptId(java.lang.Long titAziConstrAcceptId) {
		this.titAziConstrAcceptId = titAziConstrAcceptId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "A_DIRECTOR_ID", length = 22)
	public java.lang.Long getADirectorId() {
		return aDirectorId;
	}

	public void setADirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	@Column(name = "A_IN_CHARGE_MONITOR_ID", length = 22)
	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	@Column(name = "B_DIRECTOR_ID", length = 22)
	public java.lang.Long getBDirectorId() {
		return bDirectorId;
	}

	public void setBDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}

	@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	@Column(name = "ACCEPT_FROM_DATE", length = 7)
	public java.util.Date getAcceptFromDate() {
		return acceptFromDate;
	}

	public void setAcceptFromDate(java.util.Date acceptFromDate) {
		this.acceptFromDate = acceptFromDate;
	}

	@Column(name = "ACCEPT_TO_DATE", length = 7)
	public java.util.Date getAcceptToDate() {
		return acceptToDate;
	}

	public void setAcceptToDate(java.util.Date acceptToDate) {
		this.acceptToDate = acceptToDate;
	}

	@Column(name = "ACCEPT_PLACE", length = 2000)
	public java.lang.String getAcceptPlace() {
		return acceptPlace;
	}

	public void setAcceptPlace(java.lang.String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	@Column(name = "APPLY_BENCHMARK", length = 1000)
	public java.lang.String getApplyBenchmark() {
		return applyBenchmark;
	}

	public void setApplyBenchmark(java.lang.String applyBenchmark) {
		this.applyBenchmark = applyBenchmark;
	}

	@Column(name = "CONSTRUCTION_QUALITY", length = 400)
	public java.lang.String getConstructionQuality() {
		return constructionQuality;
	}

	public void setConstructionQuality(java.lang.String constructionQuality) {
		this.constructionQuality = constructionQuality;
	}

	@Column(name = "OTHER_DOCUMENTS", length = 1000)
	public java.lang.String getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(java.lang.String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	@Column(name = "OTHER_COMMENTS", length = 2000)
	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	@Column(name = "CONCLUSION", length = 22)
	public java.lang.Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.Long conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "COMPLETE_REQUEST", length = 2000)
	public java.lang.String getCompleteRequest() {
		return completeRequest;
	}

	public void setCompleteRequest(java.lang.String completeRequest) {
		this.completeRequest = completeRequest;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_USER_ID", length = 22)
	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
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
	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}	

	@Column(name = "SIGN_DATE", length = 7)
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGN_PLACE" , length = 400)
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONSTRUCT_ID", nullable = false, insertable = false, updatable = false)
	public ConstrConstructionsBO getConstrConstructions() {
		return constrConstructions;
	}

	public void setConstrConstructions(ConstrConstructionsBO constrConstructions) {
		this.constrConstructions = constrConstructions;
	}
*/
	public TitAziConstrAcceptBO() {
		setColId("titAziConstrAcceptId");
		setColName("titAziConstrAcceptId");
		setUniqueColumn(new String[] { "titAziConstrAcceptId" });
	}

	public TitAziConstrAcceptBO(Long titAziConstrAcceptId2) {
		this.titAziConstrAcceptId = titAziConstrAcceptId2;
	}

	@Override
	public TitAziConstrAcceptDTO toDTO() {
		TitAziConstrAcceptDTO titAziConstrAcceptDTO = new TitAziConstrAcceptDTO();
		// set cac gia tri
		titAziConstrAcceptDTO.setTitAziConstrAcceptId(this.titAziConstrAcceptId);
		titAziConstrAcceptDTO.setCode(this.code);
		titAziConstrAcceptDTO.setAdirectorId(this.aDirectorId);
		titAziConstrAcceptDTO.setAinchargemonitorid(this.aInChargeMonitorId);
		titAziConstrAcceptDTO.setBdirectorId(this.bDirectorId);
		titAziConstrAcceptDTO.setBinchargeConstructId(this.bInChargeConstructId);
		titAziConstrAcceptDTO.setAcceptFromDate(this.acceptFromDate);
		titAziConstrAcceptDTO.setAcceptToDate(this.acceptToDate);
		titAziConstrAcceptDTO.setAcceptPlace(this.acceptPlace);
		titAziConstrAcceptDTO.setApplyBenchmark(this.applyBenchmark);
		titAziConstrAcceptDTO.setConstructionQuality(this.constructionQuality);
		titAziConstrAcceptDTO.setOtherDocuments(this.otherDocuments);
		titAziConstrAcceptDTO.setOtherComments(this.otherComments);
		titAziConstrAcceptDTO.setConclusion(this.conclusion);
		titAziConstrAcceptDTO.setCompleteRequest(this.completeRequest);
		titAziConstrAcceptDTO.setCreatedDate(this.createdDate);
		titAziConstrAcceptDTO.setCreatedUserId(this.createdUserId);
		titAziConstrAcceptDTO.setApprovalDate(this.approvalDate);
		titAziConstrAcceptDTO.setStatusCa(this.statusCa);
		titAziConstrAcceptDTO.setIsActive(this.isActive);
		titAziConstrAcceptDTO.setSignDate(this.signDate);
		titAziConstrAcceptDTO.setSignPlace(this.signPlace);
		/*
		 * titAziConstrAcceptDTO.setConstructId(this.constrConstructions == null
		 * ? null :constrConstructions.getConstructId());
		 */
		return titAziConstrAcceptDTO;
	}
}
