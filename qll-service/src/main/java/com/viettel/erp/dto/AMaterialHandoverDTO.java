/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialHandoverBO;
import com.viettel.erp.bo.AMaterialHandoverMerListBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_MATERIAL_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AMaterialHandoverDTO extends BaseFWDTOImpl<AMaterialHandoverBO> {

	private java.lang.Long ahandoverPersonId;
	private java.lang.Long breceivePersonId;
	private java.lang.Long bdirectorId;
	private java.lang.Long createdUserId;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date handoverFromDate;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date handoverToDate;

	private java.lang.String handoverPlace;
	private java.lang.String otherComments;
	private java.lang.Long statusCa;
	private java.lang.Long documentCaId;
	private java.util.Date approvalDate;
	private java.lang.Long isActive;
	private java.lang.String constrtCode;
	private java.lang.Long constructId;
	private java.lang.Long adirectorId;
	// private java.lang.Long aMaterialHandoverId;
	private java.lang.Long amaterialHandoverId;
	private java.lang.String code;
	private java.lang.String statusName;
	private java.util.Date actualExpDate;
	private java.lang.Long deliveryNoteId;
	private java.lang.String serialNumber;
	private java.lang.String expNoteCode;
	private java.lang.String merName;
	private java.lang.Double handoverQuantity;
	private java.lang.Double actualReceiveQuantity;
	private java.lang.Long unitID;
	private java.lang.Long quanlityStatus;
	private java.lang.String otherComment;
	private java.lang.String comments;
	private java.lang.Long merEntityId;
	private java.util.Date createDate;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDate;
	
	private java.lang.String signPlace;
	
	private java.lang.String contractCode;
	private java.lang.Integer stt;

	private Long constrCompReMapId;
	private java.lang.Long sldabangiao;
	
	

	private List<AMaterialHandoverMerListDTO> listAmaterialMerlist = Lists.newArrayList();
	private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;

	private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}
	
	
	private java.lang.String signcomments;
	public java.lang.String getSigncomments() {
		return signcomments;
	}

	public void setSigncomments(java.lang.String signcomments) {
		this.signcomments = signcomments;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public AMaterialHandoverBO toModel() {
		AMaterialHandoverBO aMaterialHandoverBO = new AMaterialHandoverBO();
		aMaterialHandoverBO.setAhandoverPersonId(this.ahandoverPersonId);
		aMaterialHandoverBO.setBreceivePersonId(this.breceivePersonId);
		aMaterialHandoverBO.setBdirectorId(this.bdirectorId);
		aMaterialHandoverBO.setHandoverFromDate(this.handoverFromDate);
		aMaterialHandoverBO.setHandoverToDate(this.handoverToDate);
		aMaterialHandoverBO.setHandoverPlace(this.handoverPlace);
		aMaterialHandoverBO.setOtherComments(this.otherComments);
		aMaterialHandoverBO.setStatusCa(this.statusCa);
		aMaterialHandoverBO.setDocumentCaId(this.documentCaId);
		aMaterialHandoverBO.setApprovalDate(this.approvalDate);
		aMaterialHandoverBO.setIsActive(this.isActive);
		aMaterialHandoverBO.setConstrtCode(this.constrtCode);
		aMaterialHandoverBO.setSignDate(this.signDate);
        aMaterialHandoverBO.setSignPlace(this.signPlace);

		// aMaterialHandoverBO.setConstructId(this.constructId);
		aMaterialHandoverBO
				.setconstrconstructions(this.constructId == null ? null : new ConstrConstructionsBO(constructId));
		aMaterialHandoverBO.setAdirectorId(this.adirectorId);
		aMaterialHandoverBO.setAmaterialHandoverId(this.amaterialHandoverId);
		aMaterialHandoverBO.setCode(this.code);
		aMaterialHandoverBO.setCreatedUserId(this.createdUserId);
		aMaterialHandoverBO.setCreateDate(this.createDate);

		for (AMaterialHandoverMerListDTO dto : listAmaterialMerlist) {
			AMaterialHandoverMerListBO childBO = dto.toModel();
			childBO.setAmaterialhandover(aMaterialHandoverBO);
			aMaterialHandoverBO.getAmaterialhandovermerlist().add(childBO);
		}

		// ConstrCompleteRecordsMapBO childBO =
		// constrCompleteRecordMap.toModel();
		// childBO.setAmaterialhandover(aMaterialHandoverBO);
		// aMaterialHandoverBO.setConstrCompleteRecordMap(childBO);

		return aMaterialHandoverBO;
	}

	public java.lang.Long getAdirectorId() {
		return adirectorId;
	}

	public void setAdirectorId(java.lang.Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getStatusName() {
		return statusName;
	}

	public void setStatusName(java.lang.String statusName) {
		this.statusName = statusName;
	}

	public java.util.Date getActualExpDate() {
		return actualExpDate;
	}

	public void setActualExpDate(java.util.Date actualExpDate) {
		this.actualExpDate = actualExpDate;
	}

	public java.lang.Long getDeliveryNoteId() {
		return deliveryNoteId;
	}

	public void setDeliveryNoteId(java.lang.Long deliveryNoteId) {
		this.deliveryNoteId = deliveryNoteId;
	}

	public java.lang.String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public java.lang.String getExpNoteCode() {
		return expNoteCode;
	}

	public void setExpNoteCode(java.lang.String expNoteCode) {
		this.expNoteCode = expNoteCode;
	}

	public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public java.lang.Double getHandoverQuantity() {
		return handoverQuantity;
	}

	public void setHandoverQuantity(java.lang.Double handoverQuantity) {
		this.handoverQuantity = handoverQuantity;
	}

	public java.lang.Double getActualReceiveQuantity() {
		return actualReceiveQuantity;
	}

	public void setActualReceiveQuantity(java.lang.Double actualReceiveQuantity) {
		this.actualReceiveQuantity = actualReceiveQuantity;
	}

	public java.lang.Long getUnitID() {
		return unitID;
	}

	public void setUnitID(java.lang.Long unitID) {
		this.unitID = unitID;
	}

	public java.lang.Long getQuanlityStatus() {
		return quanlityStatus;
	}

	public void setQuanlityStatus(java.lang.Long quanlityStatus) {
		this.quanlityStatus = quanlityStatus;
	}

	public java.lang.String getOtherComment() {
		return otherComment;
	}

	public void setOtherComment(java.lang.String otherComment) {
		this.otherComment = otherComment;
	}

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}

	public List<AMaterialHandoverMerListDTO> getListAmaterialMerlist() {
		return listAmaterialMerlist;
	}

	public void setListAmaterialMerlist(List<AMaterialHandoverMerListDTO> listAmaterialMerlist) {
		this.listAmaterialMerlist = listAmaterialMerlist;
	}

	public java.util.Date getHandoverFromDate() {
		return handoverFromDate;
	}

	public void setHandoverFromDate(java.util.Date handoverFromDate) {
		this.handoverFromDate = handoverFromDate;
	}

	public java.util.Date getHandoverToDate() {
		return handoverToDate;
	}

	public void setHandoverToDate(java.util.Date handoverToDate) {
		this.handoverToDate = handoverToDate;
	}

	public java.lang.String getHandoverPlace() {
		return handoverPlace;
	}

	public void setHandoverPlace(java.lang.String handoverPlace) {
		this.handoverPlace = handoverPlace;
	}

	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	public java.lang.Long getDocumentCaId() {
		return documentCaId;
	}

	public void setDocumentCaId(java.lang.Long documentCaId) {
		this.documentCaId = documentCaId;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		// return aMaterialHandoverId;
		return amaterialHandoverId;
	}

	@Override
	public String catchName() {
		return getAmaterialHandoverId().toString();
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getAmaterialHandoverId() {
		return amaterialHandoverId;
	}

	public void setAmaterialHandoverId(java.lang.Long amaterialHandoverId) {
		this.amaterialHandoverId = amaterialHandoverId;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.Long getAhandoverPersonId() {
		return ahandoverPersonId;
	}

	public void setAhandoverPersonId(java.lang.Long ahandoverPersonId) {
		this.ahandoverPersonId = ahandoverPersonId;
	}

	public java.lang.Long getBreceivePersonId() {
		return breceivePersonId;
	}

	public void setBreceivePersonId(java.lang.Long breceivePersonId) {
		this.breceivePersonId = breceivePersonId;
	}

	public java.lang.Long getBdirectorId() {
		return bdirectorId;
	}

	public void setBdirectorId(java.lang.Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public java.lang.Long getSldabangiao() {
		return sldabangiao;
	}

	public void setSldabangiao(java.lang.Long sldabangiao) {
		this.sldabangiao = sldabangiao;
	}
	
	
	
}
