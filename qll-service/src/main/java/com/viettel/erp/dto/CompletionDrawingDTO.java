/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialRecoveryMinutesModelBO;
import com.viettel.erp.bo.CompletionDrawingBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapCompletionDrawingBO;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "COMPLETION_DRAWINGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletionDrawingDTO extends BaseFWDTOImpl<CompletionDrawingBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 687474123686044671L;
	private Long constructId;
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

	private String constrtCode;
	private String contractName;
	private String contractCode;
	private String documentPath;
	private String documentName;
	private Long attachId;
	private java.lang.Long constrCompReMapId;
	
	private String amonitorPath;
	private String bdirectorPath;
	private String creatorPath;
	private java.lang.Double contractId;
	private java.lang.String comments;

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	private List<UtilAttachedDocumentsDTO> utilAttachedDocuments = Lists.newArrayList();
	private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;
	
	

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}

	@Override
	public CompletionDrawingBO toModel() {
		CompletionDrawingBO completionDrawingBO = new CompletionDrawingBO();
		for (UtilAttachedDocumentsDTO obj : utilAttachedDocuments) {
			UtilAttachedDocumentsBO uad = obj.toModel();
			uad.setCompletionDrawingBO(completionDrawingBO);
			completionDrawingBO.getUtilAttachedDocuments().add(uad);
		}
		// completionDrawingBO.setConstructId(this.constructId);
		completionDrawingBO
				.setconstrconstructions(this.constructId == null ? null : new ConstrConstructionsBO(this.constructId));
		completionDrawingBO.setAMonitorId(this.amonitorId);
		completionDrawingBO.setBDirectorId(this.bdirectorId);
		completionDrawingBO.setCreatorId(this.creatorId);
		completionDrawingBO.setCompletionDrawingId(this.completionDrawingId);
		completionDrawingBO.setDrawName(this.drawName);
		completionDrawingBO.setDrawCode(this.drawCode);
		completionDrawingBO.setCreatedDate(this.createdDate);
		completionDrawingBO.setCreatedUserId(this.createdUserId);
		completionDrawingBO.setStatusCa(this.statusCa);
		completionDrawingBO.setDocumentCaId(this.documentCaId);
		completionDrawingBO.setApprovalDate(this.approvalDate);
		completionDrawingBO.setIsActive(this.isActive);
		
/*		if(constrCompleteRecordMap != null){
			
			ConstrCompleteRecordsMapCompletionDrawingBO childModelBO = constrCompleteRecordMap.toModelDrawing();
				childModelBO.setDrawingBO(completionDrawingBO);
				completionDrawingBO.setMapCompletionDrawingBO(childModelBO);
			}*/
		
		return completionDrawingBO;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	public Long getAMonitorId() {
		return amonitorId;
	}

	public void setAMonitorId(Long amonitorId) {
		this.amonitorId = amonitorId;
	}

	public Long getBDirectorId() {
		return bdirectorId;
	}

	public void setBDirectorId(Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public Long getFWModelId() {
		return completionDrawingId;
	}

	@Override
	public String catchName() {
		return getCompletionDrawingId().toString();
	}

	public Long getCompletionDrawingId() {
		return completionDrawingId;
	}

	public void setCompletionDrawingId(Long completionDrawingId) {
		this.completionDrawingId = completionDrawingId;
	}

	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	public String getDrawCode() {
		return drawCode;
	}

	public void setDrawCode(String drawCode) {
		this.drawCode = drawCode;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	public Long getDocumentCaId() {
		return documentCaId;
	}

	public void setDocumentCaId(Long documentCaId) {
		this.documentCaId = documentCaId;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public List<UtilAttachedDocumentsDTO> getUtilAttachedDocuments() {
		return utilAttachedDocuments;
	}

	public void setUtilAttachedDocuments(List<UtilAttachedDocumentsDTO> utilAttachedDocuments) {
		this.utilAttachedDocuments = utilAttachedDocuments;
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public String getCreatorPath() {
		return creatorPath;
	}

	public void setCreatorPath(String creatorPath) {
		this.creatorPath = creatorPath;
	}

	public String getBdirectorPath() {
		return bdirectorPath;
	}

	public void setBdirectorPath(String bdirectorPath) {
		this.bdirectorPath = bdirectorPath;
	}

	public String getAmonitorPath() {
		return amonitorPath;
	}

	public void setAmonitorPath(String amonitorPath) {
		this.amonitorPath = amonitorPath;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}

}
