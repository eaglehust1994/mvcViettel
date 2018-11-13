/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

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
import java.util.List;

@Entity(name = "completiondrawing")
@Table(name = "COMPLETION_DRAWING")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CompletionDrawingBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296487848025042065L;
	private ConstrConstructionsBO constrconstructions;
	private Long amonitorId;
	private Long bdirectorId;
	private Long creatorId;
	private Long completionDrawingId;
	private String drawName;
	private String drawCode;
	private java.util.Date createdDate;
	private Long createdUserId;
	private Long statusCa;
	private Long documentCaId;
	private java.util.Date approvalDate;
	private Long isActive;

	private List<UtilAttachedDocumentsBO> utilAttachedDocuments = Lists.newArrayList();
	//private ConstrCompleteRecordsMapCompletionDrawingBO mapCompletionDrawingBO;

	public CompletionDrawingBO() {
		setColId("completionDrawingId");
		setColName("completionDrawingId");
		setUniqueColumn(new String[] { "completionDrawingId" });
	}

	public CompletionDrawingBO(Long completionDrawingId) {
		super();
		this.completionDrawingId = completionDrawingId;
	}

/*	@OneToOne(mappedBy = "drawingBO",fetch= FetchType.EAGER,cascade=CascadeType.ALL )
	public ConstrCompleteRecordsMapCompletionDrawingBO getMapCompletionDrawingBO() {
		return mapCompletionDrawingBO;
	}

	public void setMapCompletionDrawingBO(ConstrCompleteRecordsMapCompletionDrawingBO mapCompletionDrawingBO) {
		this.mapCompletionDrawingBO = mapCompletionDrawingBO;
	}
	*/

	@ManyToOne
	@JoinColumn(name = "CONSTRUCT_ID")
	public ConstrConstructionsBO getconstrconstructions() {
		return constrconstructions;
	}

	public void setconstrconstructions(ConstrConstructionsBO constrconstructions) {
		this.constrconstructions = constrconstructions;
	}

	@Column(name = "A_MONITOR_ID", length = 22)
	public Long getAMonitorId() {
		return amonitorId;
	}

	public void setAMonitorId(Long amonitorId) {
		this.amonitorId = amonitorId;
	}

	@Column(name = "B_DIRECTOR_ID", length = 22)
	public Long getBDirectorId() {
		return bdirectorId;
	}

	public void setBDirectorId(Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	@Column(name = "CREATOR_ID", length = 22)
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "COMPLETION_DRAWING_SEQ") })
	@Column(name = "COMPLETION_DRAWING_ID", length = 22)
	public Long getCompletionDrawingId() {
		return completionDrawingId;
	}

	public void setCompletionDrawingId(Long completionDrawingId) {
		this.completionDrawingId = completionDrawingId;
	}

	@Column(name = "DRAW_NAME", length = 1000)
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Column(name = "DRAW_CODE", length = 400)
	public String getDrawCode() {
		return drawCode;
	}

	public void setDrawCode(String drawCode) {
		this.drawCode = drawCode;
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

	@Column(name = "STATUS_CA", length = 22)
	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	@Column(name = "DOCUMENT_CA_ID", length = 22)
	public Long getDocumentCaId() {
		return documentCaId;
	}

	public void setDocumentCaId(Long documentCaId) {
		this.documentCaId = documentCaId;
	}

	@Column(name = "APPROVAL_DATE", length = 7)
	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	@Override
	public CompletionDrawingDTO toDTO() {
		CompletionDrawingDTO completionDrawingDTO = new CompletionDrawingDTO();
		// set cac gia tri
		// completionDrawingDTO.setConstructId(this.constructId);

		completionDrawingDTO
				.setConstructId(this.constrconstructions == null ? null : constrconstructions.getConstructId());
		completionDrawingDTO.setAMonitorId(this.amonitorId);
		completionDrawingDTO.setBDirectorId(this.bdirectorId);
		completionDrawingDTO.setCreatorId(this.creatorId);
		completionDrawingDTO.setCompletionDrawingId(this.completionDrawingId);
		completionDrawingDTO.setDrawName(this.drawName);
		completionDrawingDTO.setDrawCode(this.drawCode);
		completionDrawingDTO.setCreatedDate(this.createdDate);
		completionDrawingDTO.setCreatedUserId(this.createdUserId);
		completionDrawingDTO.setStatusCa(this.statusCa);
		completionDrawingDTO.setDocumentCaId(this.documentCaId);
		completionDrawingDTO.setApprovalDate(this.approvalDate);
		completionDrawingDTO.setIsActive(this.isActive);
		return completionDrawingDTO;
	}

	@OneToMany(mappedBy = "completionDrawingBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<UtilAttachedDocumentsBO> getUtilAttachedDocuments() {
		return utilAttachedDocuments;
	}

	public void setUtilAttachedDocuments(List<UtilAttachedDocumentsBO> utilAttachedDocuments) {
		this.utilAttachedDocuments = utilAttachedDocuments;
	}
}
