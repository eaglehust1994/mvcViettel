/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "currentStHandover")
@Table(name = "CURRENT_STATE_HANDOVER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CurrentStateHandoverBO extends BaseFWModelImpl {

	private java.lang.Long constructId;
	private java.lang.Long currentStateHandoverId;
	private java.lang.String code;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bInChargeConstructId;
	private java.util.Date handoverDate;
	private java.lang.String handoverPlace;
	private java.lang.String groundHandoverContent;
	private java.lang.String otherDocuments;
	private java.lang.String otherComments;
	private java.lang.Long conclusion;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private List<CurrentStateHandoverListBO> listCurrentStateHandover = Lists.newArrayList();

	public CurrentStateHandoverBO() {
		setColId("currentStateHandoverId");
		setColName("currentStateHandoverId");
		setUniqueColumn(new String[] { "currentStateHandoverId" });
	}

	public CurrentStateHandoverBO(Long currentStateHandoverId2) {
		this.currentStateHandoverId = currentStateHandoverId2;
	}

	@Column(name = "CONSTRUCT_ID", length = 22)
	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CURRENT_STATE_HANDOVER_SEQ") })
	@Column(name = "CURRENT_STATE_HANDOVER_ID", length = 22)
	public java.lang.Long getCurrentStateHandoverId() {
		return currentStateHandoverId;
	}

	public void setCurrentStateHandoverId(java.lang.Long currentStateHandoverId) {
		this.currentStateHandoverId = currentStateHandoverId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "A_IN_CHARGE_MONITOR_ID", length = 22)
	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	@Column(name = "HANDOVER_DATE", length = 7)
	public java.util.Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	@Column(name = "HANDOVER_PLACE", length = 2000)
	public java.lang.String getHandoverPlace() {
		return handoverPlace;
	}

	public void setHandoverPlace(java.lang.String handoverPlace) {
		this.handoverPlace = handoverPlace;
	}

	@Column(name = "GROUND_HANDOVER_CONTENT", length = 1000)
	public java.lang.String getGroundHandoverContent() {
		return groundHandoverContent;
	}

	public void setGroundHandoverContent(java.lang.String groundHandoverContent) {
		this.groundHandoverContent = groundHandoverContent;
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

	@OneToMany(mappedBy = "currentStateHandoverBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<CurrentStateHandoverListBO> getListCurrentStateHandover() {
		return listCurrentStateHandover;
	}

	public void setListCurrentStateHandover(List<CurrentStateHandoverListBO> listCurrentStateHandover) {
		this.listCurrentStateHandover = listCurrentStateHandover;
	}

	@Override
	public CurrentStateHandoverDTO toDTO() {
		CurrentStateHandoverDTO currentStateHandoverDTO = new CurrentStateHandoverDTO();
		// set cac gia tri
		currentStateHandoverDTO.setConstructId(this.constructId);
		currentStateHandoverDTO.setCurrentStateHandoverId(this.currentStateHandoverId);
		currentStateHandoverDTO.setCode(this.code);
		currentStateHandoverDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
		currentStateHandoverDTO.setBInChargeConstructId(this.bInChargeConstructId);
		currentStateHandoverDTO.setHandoverDate(this.handoverDate);
		currentStateHandoverDTO.setHandoverPlace(this.handoverPlace);
		currentStateHandoverDTO.setGroundHandoverContent(this.groundHandoverContent);
		currentStateHandoverDTO.setOtherDocuments(this.otherDocuments);
		currentStateHandoverDTO.setOtherComments(this.otherComments);
		currentStateHandoverDTO.setConclusion(this.conclusion);
		currentStateHandoverDTO.setCreatedDate(this.createdDate);
		currentStateHandoverDTO.setCreatedUserId(this.createdUserId);
		currentStateHandoverDTO.setApprovalDate(this.approvalDate);
		currentStateHandoverDTO.setStatusCa(this.statusCa);
		currentStateHandoverDTO.setIsActive(this.isActive);
		return currentStateHandoverDTO;
	}
}
