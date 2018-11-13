/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

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

@Entity
@Table(name = "SCENE_GENERATE_WORK")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class SceneGenerateWorkBO extends BaseFWModelImpl {

	private java.lang.Long type;
	private java.lang.Long constructId;
	private java.lang.Long sceneGenerateWorkId;
	private java.lang.String code;
	private java.lang.Long aDirectorId;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bInChargeConstructId;
	private java.lang.Long cDesignDirectionId;
	private java.lang.Long cDesignManagerId;
	private java.lang.String incidentConfirm;
	private java.lang.String otherComments;
	private java.lang.String conclusion;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private List<SceneGenerateWorkListBO> listSceneGenerateWork = Lists.newArrayList();

	/**
	 * @return the listSceneGenerateWork
	 */
	@OneToMany(mappedBy = "sceneGenerateWorkBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<SceneGenerateWorkListBO> getListSceneGenerateWork() {
		return listSceneGenerateWork;
	}

	/**
	 * @param listSceneGenerateWork
	 *            the listSceneGenerateWork to set
	 */
	public void setListSceneGenerateWork(List<SceneGenerateWorkListBO> listSceneGenerateWork) {
		this.listSceneGenerateWork = listSceneGenerateWork;
	}

	public SceneGenerateWorkBO(Long sceneGenerateWorkId) {
		super();
		this.sceneGenerateWorkId = sceneGenerateWorkId;
	}

	public SceneGenerateWorkBO() {
		setColId("sceneGenerateWorkId");
		setColName("sceneGenerateWorkId");
		setUniqueColumn(new String[] { "sceneGenerateWorkId" });
	}
	
	@Column(name = "TYPE", length = 22)
	public java.lang.Long getType(){
	return type;
	}
	public void setType(java.lang.Long type)
	{
	this.type = type;
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
			@Parameter(name = "sequence", value = "SCENE_GENERATE_WORK_SEQ") })
	@Column(name = "SCENE_GENERATE_WORK_ID", length = 22)
	public java.lang.Long getSceneGenerateWorkId() {
		return sceneGenerateWorkId;
	}

	public void setSceneGenerateWorkId(java.lang.Long sceneGenerateWorkId) {
		this.sceneGenerateWorkId = sceneGenerateWorkId;
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

	@Column(name = "C_DESIGN_DIRECTION_ID", length = 22)
	public java.lang.Long getCDesignDirectionId() {
		return cDesignDirectionId;
	}

	public void setCDesignDirectionId(java.lang.Long cDesignDirectionId) {
		this.cDesignDirectionId = cDesignDirectionId;
	}

	@Column(name = "C_DESIGN_MANAGER_ID", length = 22)
	public java.lang.Long getCDesignManagerId() {
		return cDesignManagerId;
	}

	public void setCDesignManagerId(java.lang.Long cDesignManagerId) {
		this.cDesignManagerId = cDesignManagerId;
	}

	@Column(name = "INCIDENT_CONFIRM", length = 1000)
	public java.lang.String getIncidentConfirm() {
		return incidentConfirm;
	}

	public void setIncidentConfirm(java.lang.String incidentConfirm) {
		this.incidentConfirm = incidentConfirm;
	}

	@Column(name = "OTHER_COMMENTS", length = 2000)
	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	@Column(name = "CONCLUSION", length = 1000)
	public java.lang.String getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.String conclusion) {
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

	@Column(name = "SIGN_DATE", length = 7)
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGN_PLACE", length = 2000)
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	@Override
	public SceneGenerateWorkDTO toDTO() {
		SceneGenerateWorkDTO sceneGenerateWorkDTO = new SceneGenerateWorkDTO();
		// set cac gia tri
		sceneGenerateWorkDTO.setConstructId(this.constructId);
		sceneGenerateWorkDTO.setSceneGenerateWorkId(this.sceneGenerateWorkId);
		sceneGenerateWorkDTO.setCode(this.code);
		sceneGenerateWorkDTO.setType(this.type);
		sceneGenerateWorkDTO.setADirectorId(this.aDirectorId);
		sceneGenerateWorkDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
		sceneGenerateWorkDTO.setBDirectorId(this.bDirectorId);
		sceneGenerateWorkDTO.setBInChargeConstructId(this.bInChargeConstructId);
		sceneGenerateWorkDTO.setCDesignDirectionId(this.cDesignDirectionId);
		sceneGenerateWorkDTO.setCDesignManagerId(this.cDesignManagerId);
		sceneGenerateWorkDTO.setIncidentConfirm(this.incidentConfirm);
		sceneGenerateWorkDTO.setOtherComments(this.otherComments);
		sceneGenerateWorkDTO.setConclusion(this.conclusion);
		sceneGenerateWorkDTO.setCreatedDate(this.createdDate);
		sceneGenerateWorkDTO.setCreatedUserId(this.createdUserId);
		sceneGenerateWorkDTO.setApprovalDate(this.approvalDate);
		sceneGenerateWorkDTO.setStatusCa(this.statusCa);
		sceneGenerateWorkDTO.setIsActive(this.isActive);
		sceneGenerateWorkDTO.setSignDate(this.signDate);
		sceneGenerateWorkDTO.setSignPlace(this.signPlace);
		return sceneGenerateWorkDTO;
	}
}
