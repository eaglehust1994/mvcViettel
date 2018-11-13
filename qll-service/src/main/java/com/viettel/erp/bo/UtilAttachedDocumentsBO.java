/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "util_attached_documents")
@Table(name = "UTIL_ATTACHED_DOCUMENTS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class UtilAttachedDocumentsBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -692185439554178698L;
	private Long attachId;
	private String documentName;
	private String documentPath;
	private Long type;
	private Long parentId;
	private java.util.Date createdDate;
	private Long levelId;
	private String signer;
	private String code;
	private java.util.Date signedDate;
	private String description;
	private Long userId;
	private Long fileType;
	private Long documenTypeId;
	private String documentTypeName;

	private CompletionDrawingBO completionDrawingBO;
	// private ConstrOrganizationPlanBO constrOrganizationPlanBO;

	public UtilAttachedDocumentsBO() {
		setColId("attachId");
		setColName("attachId");
		setUniqueColumn(new String[] { "attachId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "UTIL_ATTACHED_DOCUMENTS_SEQ") })
	@Column(name = "ATTACH_ID", length = 22)
	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	@Column(name = "DOCUMENT_NAME", length = 600)
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Column(name = "DOCUMENT_PATH", length = 1200)
	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	@Column(name = "TYPE", length = 22)
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public CompletionDrawingBO getCompletionDrawingBO() {
		return completionDrawingBO;
	}

	public void setCompletionDrawingBO(CompletionDrawingBO completionDrawingBO) {
		this.completionDrawingBO = completionDrawingBO;
	}

	/*
	 * @Column(name = "PARENT_ID", length = 22) public Long getParentId() {
	 * return parentId; }
	 * 
	 * public void setParentId(Long parentId) { this.parentId = parentId; }
	 */

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "PARENT_ID")
	// public ConstrOrganizationPlanBO getConstrOrganizationPlanBO() {
	// return constrOrganizationPlanBO;
	// }
	//
	// public void setConstrOrganizationPlanBO(ConstrOrganizationPlanBO
	// constrOrganizationPlanBO) {
	// this.constrOrganizationPlanBO = constrOrganizationPlanBO;
	// }

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LEVEL_ID", length = 22)
	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	@Column(name = "SIGNER", length = 598)
	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	@Column(name = "CODE", length = 718)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "SIGNED_DATE", length = 7)
	public java.util.Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(java.util.Date signedDate) {
		this.signedDate = signedDate;
	}

	@Column(name = "DESCRIPTION", length = 700)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "USER_ID", length = 22)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "FILE_TYPE", length = 22)
	public Long getFileType() {
		return fileType;
	}

	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}

	@Column(name = "DOCUMEN_TYPE_ID", length = 22)
	public Long getDocumenTypeId() {
		return documenTypeId;
	}

	public void setDocumenTypeId(Long documenTypeId) {
		this.documenTypeId = documenTypeId;
	}

	@Column(name = "DOCUMENT_TYPE_NAME", length = 400)
	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	
	

	@Override
	public UtilAttachedDocumentsDTO toDTO() {
		UtilAttachedDocumentsDTO utilAttachedDocumentsDTO = new UtilAttachedDocumentsDTO();
		// set cac gia tri
		utilAttachedDocumentsDTO.setAttachId(this.attachId);
		utilAttachedDocumentsDTO.setDocumentName(this.documentName);
		utilAttachedDocumentsDTO.setDocumentPath(this.documentPath);
		utilAttachedDocumentsDTO.setType(this.type);
		utilAttachedDocumentsDTO.setParentId(this.parentId);
		utilAttachedDocumentsDTO.setCreatedDate(this.createdDate);
		utilAttachedDocumentsDTO.setLevelId(this.levelId);
		utilAttachedDocumentsDTO.setSigner(this.signer);
		utilAttachedDocumentsDTO.setCode(this.code);
		utilAttachedDocumentsDTO.setSignedDate(this.signedDate);
		utilAttachedDocumentsDTO.setDescription(this.description);
		utilAttachedDocumentsDTO.setUserId(this.userId);
		utilAttachedDocumentsDTO.setFileType(this.fileType);
		utilAttachedDocumentsDTO.setDocumenTypeId(this.documenTypeId);
		utilAttachedDocumentsDTO.setDocumentTypeName(this.documentTypeName);
		return utilAttachedDocumentsDTO;
	}

}
