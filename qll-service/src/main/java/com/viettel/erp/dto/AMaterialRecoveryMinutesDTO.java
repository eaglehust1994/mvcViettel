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
import com.viettel.erp.bo.AMaterialRecoveryListBO;
import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.bo.AMaterialRecoveryMinutesModelBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_MATERIAL_RECOVERY_MINUTESBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AMaterialRecoveryMinutesDTO extends BaseFWDTOImpl<AMaterialRecoveryMinutesBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String signPlace;
	private java.lang.Long constructId;
	private java.lang.Long amaterialRecoveryMinutesId;
	private java.lang.String code;
	private java.lang.Long adirectorId;
	private java.lang.Long ahandoverPersonId;
	private java.lang.Long bdirectorId;
	private java.lang.Long breceivePersonId;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;

	private List<AMaterialRecoveryListDTO> amaterialRecoveryList = Lists.newArrayList();
	private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;
	

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}

	public List<AMaterialRecoveryListDTO> getAmaterialRecoveryList() {
		return amaterialRecoveryList;
	}

	public void setAmaterialRecoveryList(List<AMaterialRecoveryListDTO> amaterialRecoveryList) {
		this.amaterialRecoveryList = amaterialRecoveryList;
	}

	@Override
	public AMaterialRecoveryMinutesBO toModel() {
		AMaterialRecoveryMinutesBO aMaterialRecoveryMinutesBO = new AMaterialRecoveryMinutesBO();
		aMaterialRecoveryMinutesBO.setSignPlace(this.signPlace);
		aMaterialRecoveryMinutesBO.setConstructId(this.constructId);
		aMaterialRecoveryMinutesBO.setAMaterialRecoveryMinutesId(this.amaterialRecoveryMinutesId);
		aMaterialRecoveryMinutesBO.setCode(this.code);
		aMaterialRecoveryMinutesBO.setADirectorId(this.adirectorId);
		aMaterialRecoveryMinutesBO.setAHandoverPersonId(this.ahandoverPersonId);
		aMaterialRecoveryMinutesBO.setBDirectorId(this.bdirectorId);
		aMaterialRecoveryMinutesBO.setBReceivePersonId(this.breceivePersonId);
		aMaterialRecoveryMinutesBO.setCreatedDate(this.createdDate);
		aMaterialRecoveryMinutesBO.setCreatedUserId(this.createdUserId);
		aMaterialRecoveryMinutesBO.setApprovalDate(this.approvalDate);
		aMaterialRecoveryMinutesBO.setStatusCa(this.statusCa);
		aMaterialRecoveryMinutesBO.setIsActive(this.isActive);
		for (AMaterialRecoveryListDTO aMaterialRecoveryListDTO : amaterialRecoveryList) {
			AMaterialRecoveryListBO childBO = aMaterialRecoveryListDTO.toModel();
			childBO.setAmaterialRecoveryMinutesBO(aMaterialRecoveryMinutesBO);
			aMaterialRecoveryMinutesBO.getAmaterialRecoveryList().add(childBO);
		}
		
//		if(constrCompleteRecordMap != null){
//		
//		AMaterialRecoveryMinutesModelBO childModelBO = constrCompleteRecordMap.toModelRecoverryMinutes();
//			childModelBO.setRecoveryMinutesBO(aMaterialRecoveryMinutesBO);
//			aMaterialRecoveryMinutesBO.setRecoveryMinutesModelBO(childModelBO);
//		}
//		
		return aMaterialRecoveryMinutesBO;
	}

	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return amaterialRecoveryMinutesId;
	}

	@Override
	public String catchName() {
		return getamaterialRecoveryMinutesId().toString();
	}

	public java.lang.Long getamaterialRecoveryMinutesId() {
		return amaterialRecoveryMinutesId;
	}

	public void setamaterialRecoveryMinutesId(java.lang.Long amaterialRecoveryMinutesId) {
		this.amaterialRecoveryMinutesId = amaterialRecoveryMinutesId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getadirectorId() {
		return adirectorId;
	}

	public void setadirectorId(java.lang.Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public java.lang.Long getahandoverPersonId() {
		return ahandoverPersonId;
	}

	public void setahandoverPersonId(java.lang.Long ahandoverPersonId) {
		this.ahandoverPersonId = ahandoverPersonId;
	}

	public java.lang.Long getbdirectorId() {
		return bdirectorId;
	}

	public void setbdirectorId(java.lang.Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public java.lang.Long getbreceivePersonId() {
		return breceivePersonId;
	}

	public void setbreceivePersonId(java.lang.Long breceivePersonId) {
		this.breceivePersonId = breceivePersonId;
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

}
