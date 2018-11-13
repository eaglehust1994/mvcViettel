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
import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CURRENT_STATE_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentStateHandoverDTO extends BaseFWDTOImpl<CurrentStateHandoverBO> {

	private java.lang.Long constructId;
	private java.lang.Long currentStateHandoverId;
	private java.lang.String code;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bInChargeConstructId;
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
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
	private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;
	private List<CurrentStateHandoverListDTO> listCurrentStateHandover = Lists.newArrayList();

	
	private String comments;
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private Long constrCompReMapId;
	
	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	@Override
	public CurrentStateHandoverBO toModel() {
		CurrentStateHandoverBO currentStateHandoverBO = new CurrentStateHandoverBO();
          for(CurrentStateHandoverListDTO obj : listCurrentStateHandover){
        	  CurrentStateHandoverListBO cust = obj.toModel();
        	  cust.setCurrentStateHandoverBO(currentStateHandoverBO);
      		currentStateHandoverBO.getListCurrentStateHandover().add(cust);
          }


		currentStateHandoverBO.setConstructId(this.constructId);
		currentStateHandoverBO.setCurrentStateHandoverId(this.currentStateHandoverId);
		currentStateHandoverBO.setCode(this.code);
		currentStateHandoverBO.setAInChargeMonitorId(this.aInChargeMonitorId);
		currentStateHandoverBO.setBInChargeConstructId(this.bInChargeConstructId);
		currentStateHandoverBO.setHandoverDate(this.handoverDate);
		currentStateHandoverBO.setHandoverPlace(this.handoverPlace);
		currentStateHandoverBO.setGroundHandoverContent(this.groundHandoverContent);
		currentStateHandoverBO.setOtherDocuments(this.otherDocuments);
		currentStateHandoverBO.setOtherComments(this.otherComments);
		currentStateHandoverBO.setConclusion(this.conclusion);
		currentStateHandoverBO.setCreatedDate(this.createdDate);
		currentStateHandoverBO.setCreatedUserId(this.createdUserId);
		currentStateHandoverBO.setApprovalDate(this.approvalDate);
		currentStateHandoverBO.setStatusCa(this.statusCa);
		currentStateHandoverBO.setIsActive(this.isActive);
		return currentStateHandoverBO;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return currentStateHandoverId;
	}

	@Override
	public String catchName() {
		return getCurrentStateHandoverId().toString();
	}

	public java.lang.Long getCurrentStateHandoverId() {
		return currentStateHandoverId;
	}

	public void setCurrentStateHandoverId(java.lang.Long currentStateHandoverId) {
		this.currentStateHandoverId = currentStateHandoverId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	public java.util.Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	public java.lang.String getHandoverPlace() {
		return handoverPlace;
	}

	public void setHandoverPlace(java.lang.String handoverPlace) {
		this.handoverPlace = handoverPlace;
	}

	public java.lang.String getGroundHandoverContent() {
		return groundHandoverContent;
	}

	public void setGroundHandoverContent(java.lang.String groundHandoverContent) {
		this.groundHandoverContent = groundHandoverContent;
	}

	public java.lang.String getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(java.lang.String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	public java.lang.Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.Long conclusion) {
		this.conclusion = conclusion;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	public List<CurrentStateHandoverListDTO> getListCurrentStateHandover() {
		return listCurrentStateHandover;
	}

	public void setListCurrentStateHandover(List<CurrentStateHandoverListDTO> listCurrentStateHandover) {
		this.listCurrentStateHandover = listCurrentStateHandover;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}
	

}
