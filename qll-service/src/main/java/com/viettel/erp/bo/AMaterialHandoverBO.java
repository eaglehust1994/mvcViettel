/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "amaterialhandover")
@Table(name = "A_MATERIAL_HANDOVER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AMaterialHandoverBO extends BaseFWModelImpl {

	private List<AMaterialHandoverMerListBO> amaterialhandovermerlist = Lists.newArrayList();
	private ConstrCompleteRecordsMapBO constrCompleteRecordMap;
	private ConstrConstructionsBO constrconstructions;
	private java.lang.Long ahandoverPersonId;
	private java.lang.Long breceivePersonId;
	private java.lang.Long bdirectorId;
	private java.util.Date handoverFromDate;
	private java.util.Date handoverToDate;
	private java.lang.String handoverPlace;
	private java.lang.String otherComments;
	private java.lang.Long statusCa;
	private java.lang.Long documentCaId;
	private java.util.Date approvalDate;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private java.lang.Long isActive;
	private java.lang.String constrtCode;
	private java.lang.Long adirectorId;
	private java.lang.Long amaterialHandoverId;
	private java.lang.String code;
	private java.lang.Long createdUserId;
	private java.util.Date createDate;

	public AMaterialHandoverBO() {
		setColId("amaterialHandoverId");
		setColName("amaterialHandoverId");
		setUniqueColumn(new String[] { "amaterialHandoverId" });
	}

	public AMaterialHandoverBO(java.lang.Long aMaterialHandoverId) {
		this.amaterialHandoverId = aMaterialHandoverId;
	}

	@Column(name = "HANDOVER_FROM_DATE", length = 7)
	public java.util.Date getHandoverFromDate() {
		return handoverFromDate;
	}

	public void setHandoverFromDate(java.util.Date handoverFromDate) {
		this.handoverFromDate = handoverFromDate;
	}

	@Column(name = "HANDOVER_TO_DATE", length = 7)
	public java.util.Date getHandoverToDate() {
		return handoverToDate;
	}

	public void setHandoverToDate(java.util.Date handoverToDate) {
		this.handoverToDate = handoverToDate;
	}

	@Column(name = "HANDOVER_PLACE", length = 2000)
	public java.lang.String getHandoverPlace() {
		return handoverPlace;
	}

	public void setHandoverPlace(java.lang.String handoverPlace) {
		this.handoverPlace = handoverPlace;
	}

	@Column(name = "OTHER_COMMENTS", length = 2000)
	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	@Column(name = "STATUS_CA", length = 22)
	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	@Column(name = "DOCUMENT_CA_ID", length = 22)
	public java.lang.Long getDocumentCaId() {
		return documentCaId;
	}

	public void setDocumentCaId(java.lang.Long documentCaId) {
		this.documentCaId = documentCaId;
	}

	@Column(name = "APPROVAL_DATE", length = 7)
	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "IS_ACTIVE", length = 7)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "CONSTRT_CODE", length = 200)
	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "A_MATERIAL_HANDOVER_SEQ") })
	@Column(name = "A_MATERIAL_HANDOVER_ID", length = 22)
	public java.lang.Long getAmaterialHandoverId() {
		return amaterialHandoverId;
	}

	@Column(name = "A_HANDOVER_PERSON_ID", length = 22)
	public java.lang.Long getAhandoverPersonId() {
		return ahandoverPersonId;
	}

	public void setAhandoverPersonId(java.lang.Long ahandoverPersonId) {
		this.ahandoverPersonId = ahandoverPersonId;
	}

	@Column(name = "B_RECEIVE_PERSON_ID", length = 22)
	public java.lang.Long getBreceivePersonId() {
		return breceivePersonId;
	}

	public void setBreceivePersonId(java.lang.Long breceivePersonId) {
		this.breceivePersonId = breceivePersonId;
	}

	@Column(name = "B_DIRECTOR_ID", length = 22)
	public java.lang.Long getBdirectorId() {
		return bdirectorId;
	}

	public void setBdirectorId(java.lang.Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	@Column(name = "A_DIRECTOR_ID", length = 38)
	public java.lang.Long getAdirectorId() {
		return adirectorId;
	}

	public void setAdirectorId(java.lang.Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public void setAmaterialHandoverId(java.lang.Long aMaterialHandoverId) {
		this.amaterialHandoverId = aMaterialHandoverId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/*
	 * @OneToOne(mappedBy = "amaterialhandover",fetch=
	 * FetchType.EAGER,cascade=CascadeType.ALL ) public
	 * ConstrCompleteRecordsMapBO getConstrCompleteRecordMap() { return
	 * constrCompleteRecordMap; }
	 * 
	 * public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapBO
	 * constrCompleteRecordMap) { this.constrCompleteRecordMap =
	 * constrCompleteRecordMap; }
	 */
	


	@OneToMany(mappedBy = "amaterialhandover", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<AMaterialHandoverMerListBO> getAmaterialhandovermerlist() {
		return amaterialhandovermerlist;
	}

	public void setAmaterialhandovermerlist(List<AMaterialHandoverMerListBO> amaterialhandovermerlist) {
		this.amaterialhandovermerlist = amaterialhandovermerlist;
	}

	@ManyToOne
	@JoinColumn(name = "CONSTRUCT_ID")
	public ConstrConstructionsBO getconstrconstructions() {
		return constrconstructions;
	}

	public void setconstrconstructions(ConstrConstructionsBO constrconstructions) {
		this.constrconstructions = constrconstructions;
	}

	@Column(name = "SIGN_DATE", length = 7)
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGN_PLACE", length = 400)
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}
	
	
	@Column(name = "CREATE_USER_ID", length = 38)
	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public AMaterialHandoverDTO toDTO() {
		AMaterialHandoverDTO aMaterialHandoverDTO = new AMaterialHandoverDTO();
		// set cac gia tri
		aMaterialHandoverDTO.setAhandoverPersonId(this.ahandoverPersonId);
		aMaterialHandoverDTO.setBreceivePersonId(this.breceivePersonId);
		aMaterialHandoverDTO.setBdirectorId(this.bdirectorId);
		aMaterialHandoverDTO.setHandoverFromDate(this.handoverFromDate);
		aMaterialHandoverDTO.setHandoverToDate(this.handoverToDate);
		aMaterialHandoverDTO.setHandoverPlace(this.handoverPlace);
		aMaterialHandoverDTO.setOtherComments(this.otherComments);
		aMaterialHandoverDTO.setStatusCa(this.statusCa);
		aMaterialHandoverDTO.setDocumentCaId(this.documentCaId);
		aMaterialHandoverDTO.setApprovalDate(this.approvalDate);
		aMaterialHandoverDTO.setSignDate(this.signDate);
		aMaterialHandoverDTO.setSignPlace(this.signPlace);
		aMaterialHandoverDTO.setIsActive(this.isActive);
		aMaterialHandoverDTO.setConstrtCode(this.constrtCode);
		aMaterialHandoverDTO.setConstructId(this.constrconstructions.getConstructId());
		aMaterialHandoverDTO.setAdirectorId(this.adirectorId);
		aMaterialHandoverDTO.setAmaterialHandoverId(this.amaterialHandoverId);
		aMaterialHandoverDTO.setCode(this.code);
		aMaterialHandoverDTO.setCreatedUserId(this.createdUserId);
		aMaterialHandoverDTO.setCreateDate(this.createDate);
		return aMaterialHandoverDTO;
	}
}
