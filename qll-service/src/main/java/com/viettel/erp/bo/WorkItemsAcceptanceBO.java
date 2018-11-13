/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WORK_ITEMS_ACCEPTANCE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class WorkItemsAcceptanceBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6675137504782493760L;
	private Long workItemsAcceptanceId;
	private String code;
	private Long monitorId;
	private Long inChargeConstructId;
	private java.util.Date acceptFromDate;
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
	private Long constructId;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	
	private List<EstimatesWorkItemsBO> estimatesWorkItems = Lists.newArrayList();
	
	/**
	 * @return the estimatesWorkItems
	 */
	@ManyToMany(cascade=CascadeType.ALL, mappedBy = "workItemsAcceptances")
	public List<EstimatesWorkItemsBO> getEstimatesWorkItems() {
		return estimatesWorkItems;
	}

	/**
	 * @param estimatesWorkItems the estimatesWorkItems to set
	 */
	public void setEstimatesWorkItems(List<EstimatesWorkItemsBO> estimatesWorkItems) {
		this.estimatesWorkItems = estimatesWorkItems;
	}

	public WorkItemsAcceptanceBO() {
		setColId("workItemsAcceptanceId");
		setColName("workItemsAcceptanceId");
		setUniqueColumn(new String[] { "workItemsAcceptanceId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "WORK_ITEMS_ACCEPTANCE_SEQ") })
	@Column(name = "WORK_ITEMS_ACCEPTANCE_ID", length = 22)
	public Long getWorkItemsAcceptanceId() {
		return workItemsAcceptanceId;
	}

	public void setWorkItemsAcceptanceId(Long workItemsAcceptanceId) {
		this.workItemsAcceptanceId = workItemsAcceptanceId;
	}

	@Column(name = "CODE", length = 400)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "A_MONITOR_ID", length = 22)
	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
	public Long getInChargeConstructId() {
		return inChargeConstructId;
	}

	public void setInChargeConstructId(Long inChargeConstructId) {
		this.inChargeConstructId = inChargeConstructId;
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
	public String getAcceptPlace() {
		return acceptPlace;
	}

	public void setAcceptPlace(String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	@Column(name = "APPLY_BENCHMARK", length = 1000)
	public String getApplyBenchmark() {
		return applyBenchmark;
	}

	public void setApplyBenchmark(String applyBenchmark) {
		this.applyBenchmark = applyBenchmark;
	}

	@Column(name = "CONSTRUCTION_QUALITY", length = 400)
	public String getConstructionQuality() {
		return constructionQuality;
	}

	public void setConstructionQuality(String constructionQuality) {
		this.constructionQuality = constructionQuality;
	}

	@Column(name = "OTHER_DOCUMENTS", length = 1000)
	public String getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	@Column(name = "OTHER_COMMENTS", length = 2000)
	public String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(String otherComments) {
		this.otherComments = otherComments;
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

	@Column(name = "CONCLUSION", length = 22)
	public Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(Long conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "COMPLETE_REQUEST", length = 2000)
	public String getCompleteRequest() {
		return completeRequest;
	}

	public void setCompleteRequest(String completeRequest) {
		this.completeRequest = completeRequest;
	}

	@Column(name = "CONSTRUCT_ID", length = 22)
	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	@Column(name = "SIGN_DATE", length = 7)
	public java.util.Date getSignDate(){
	return signDate;
	}
	public void setSignDate(java.util.Date signDate)
	{
	this.signDate = signDate;
	}
	@Column(name = "SIGN_PLACE", length = 2000)
	public java.lang.String getSignPlace(){
	return signPlace;
	}
	public void setSignPlace(java.lang.String signPlace)
	{
	this.signPlace = signPlace;
	}
	
	@Override
	public WorkItemsAcceptanceDTO toDTO() {
		WorkItemsAcceptanceDTO workItemsAcceptanceDTO = new WorkItemsAcceptanceDTO();
		
		// set cac gia tri
		workItemsAcceptanceDTO.setWorkItemsAcceptanceId(this.workItemsAcceptanceId);
		workItemsAcceptanceDTO.setCode(this.code);
		workItemsAcceptanceDTO.setMonitorId(this.monitorId);
		workItemsAcceptanceDTO.setInChargeConstructId(this.inChargeConstructId);
		workItemsAcceptanceDTO.setAcceptFromDate(this.acceptFromDate);
		workItemsAcceptanceDTO.setAcceptToDate(this.acceptToDate);
		workItemsAcceptanceDTO.setAcceptPlace(this.acceptPlace);
		workItemsAcceptanceDTO.setApplyBenchmark(this.applyBenchmark);
		workItemsAcceptanceDTO.setConstructionQuality(this.constructionQuality);
		workItemsAcceptanceDTO.setOtherDocuments(this.otherDocuments);
		workItemsAcceptanceDTO.setOtherComments(this.otherComments);
		workItemsAcceptanceDTO.setCreatedDate(this.createdDate);
		workItemsAcceptanceDTO.setCreatedUserId(this.createdUserId);
		workItemsAcceptanceDTO.setApprovalDate(this.approvalDate);
		workItemsAcceptanceDTO.setStatusCa(this.statusCa);
		workItemsAcceptanceDTO.setIsActive(this.isActive);
		workItemsAcceptanceDTO.setConclusion(this.conclusion);
		workItemsAcceptanceDTO.setCompleteRequest(this.completeRequest);
		workItemsAcceptanceDTO.setConstructId(this.constructId);
		workItemsAcceptanceDTO.setSignDate(this.signDate);
		workItemsAcceptanceDTO.setSignPlace(this.signPlace);
		return workItemsAcceptanceDTO;
	}
}
