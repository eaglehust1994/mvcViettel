/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.CompletionDrawingBO;
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "UTIL_ATTACHED_DOCUMENTSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilAttachedDocumentsDTO extends BaseFWDTOImpl<UtilAttachedDocumentsBO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8073213193564612845L;
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

	
	@Override
	public UtilAttachedDocumentsBO toModel() {
		UtilAttachedDocumentsBO utilAttachedDocumentsBO = new UtilAttachedDocumentsBO();
		utilAttachedDocumentsBO.setAttachId(this.attachId);
		utilAttachedDocumentsBO.setDocumentName(this.documentName);
		utilAttachedDocumentsBO.setDocumentPath(this.documentPath);
		utilAttachedDocumentsBO.setType(this.type);
		utilAttachedDocumentsBO.setCreatedDate(this.createdDate);
		utilAttachedDocumentsBO.setLevelId(this.levelId);
		utilAttachedDocumentsBO.setSigner(this.signer);
		utilAttachedDocumentsBO.setCode(this.code);
		utilAttachedDocumentsBO.setSignedDate(this.signedDate);
		utilAttachedDocumentsBO.setDescription(this.description);
		utilAttachedDocumentsBO.setUserId(this.userId);
		utilAttachedDocumentsBO.setFileType(this.fileType);
		utilAttachedDocumentsBO.setDocumenTypeId(this.documenTypeId);
		utilAttachedDocumentsBO.setDocumentTypeName(this.documentTypeName);

		utilAttachedDocumentsBO
				.setCompletionDrawingBO(this.parentId == null ? null : new CompletionDrawingBO(this.parentId));

		return utilAttachedDocumentsBO;
	}

	@Override
	public Long getFWModelId() {
		return attachId;
	}

	
	@Override
	public String catchName() {
		return getAttachId().toString();
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public java.util.Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(java.util.Date signedDate) {
		this.signedDate = signedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFileType() {
		return fileType;
	}

	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}

	public Long getDocumenTypeId() {
		return documenTypeId;
	}

	public void setDocumenTypeId(Long documenTypeId) {
		this.documenTypeId = documenTypeId;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

}
