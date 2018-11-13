/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.SceneGenerateWorkBO;
import com.viettel.erp.bo.SceneGenerateWorkListBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "SCENE_GENERATE_WORKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SceneGenerateWorkDTO extends BaseFWDTOImpl<SceneGenerateWorkBO> {

	private java.lang.Long type;
	private java.lang.Long constructId;
	private java.lang.Long sceneGenerateWorkId;
	private java.lang.String code;
	private java.lang.Long aDirectorId;
	private java.lang.String aDirectorIdName;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.String aInChargeMonitorIdName;
	private java.lang.Long bDirectorId;
	private java.lang.String bDirectorIdName;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String bInChargeConstructIdName;
	private java.lang.Long cDesignDirectionId;
	private java.lang.String cDesignDirectionIdName;
	private java.lang.Long cDesignManagerId;
	private java.lang.String cDesignManagerIdName;
	private java.lang.String incidentConfirm;
	private java.lang.String otherComments;
	private java.lang.String conclusion;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date signDate;

	private java.lang.String signPlace;
	// tungpv
	private java.lang.String constrtCode;
	private java.lang.String constrtName;
	private java.lang.String contractCode;
	private java.lang.String contractName;
	private java.lang.String stationCode;
	private java.lang.String constrtAddress;
	private java.lang.String itemName;
	private java.lang.Long estimatesItemChildId;
	private java.lang.Long constrCompReMapId;
	private Long contractId;
	
	private java.lang.String aDirectorIdNameSign;
	private java.lang.String aInChargeMonitorIdNameSign;
	private java.lang.String bDirectorIdNameSign;
	private java.lang.String bInChargeConstructIdNameSign;
	private java.lang.String cDesignDirectionIdNameSign;
	private java.lang.String cDesignManagerIdNameSign;

	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";

	private List<SceneGenerateWorkListDTO> bbhtpsklList = Lists.newArrayList();

	private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

	private java.lang.String aDirectorIdPath;
	private java.lang.String aInChargeMonitorIdPath;
	private java.lang.String bDirectorIdPath;
	private java.lang.String bInChargeConstructIdPath;
	private java.lang.String cDesignDirectionIdPath;
	private java.lang.String cDesignManagerIdPath;
	
	private java.lang.String comments;

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.String getaDirectorIdNameSign() {
		return Strings.nullToEmpty(aDirectorIdNameSign);
	}

	public void setaDirectorIdNameSign(java.lang.String aDirectorIdNameSign) {
		this.aDirectorIdNameSign = aDirectorIdNameSign;
	}

	public java.lang.String getaInChargeMonitorIdNameSign() {
		return Strings.nullToEmpty(aInChargeMonitorIdNameSign);
	}

	public void setaInChargeMonitorIdNameSign(java.lang.String aInChargeMonitorIdNameSign) {
		this.aInChargeMonitorIdNameSign = aInChargeMonitorIdNameSign;
	}

	public java.lang.String getbDirectorIdNameSign() {
		return Strings.nullToEmpty(bDirectorIdNameSign);
	}

	public void setbDirectorIdNameSign(java.lang.String bDirectorIdNameSign) {
		this.bDirectorIdNameSign = bDirectorIdNameSign;
	}

	public java.lang.String getbInChargeConstructIdNameSign() {
		return Strings.nullToEmpty(bInChargeConstructIdNameSign);
	}

	public void setbInChargeConstructIdNameSign(java.lang.String bInChargeConstructIdNameSign) {
		this.bInChargeConstructIdNameSign = bInChargeConstructIdNameSign;
	}

	public java.lang.String getcDesignDirectionIdNameSign() {
		return Strings.nullToEmpty(cDesignDirectionIdNameSign);
	}

	public void setcDesignDirectionIdNameSign(java.lang.String cDesignDirectionIdNameSign) {
		this.cDesignDirectionIdNameSign = cDesignDirectionIdNameSign;
	}

	public java.lang.String getcDesignManagerIdNameSign() {
		return Strings.nullToEmpty(cDesignManagerIdNameSign);
	}

	public void setcDesignManagerIdNameSign(java.lang.String cDesignManagerIdNameSign) {
		this.cDesignManagerIdNameSign = cDesignManagerIdNameSign;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public java.lang.String getaDirectorIdPath() {
		return aDirectorIdPath;
	}

	public void setaDirectorIdPath(java.lang.String aDirectorIdPath) {
		this.aDirectorIdPath = aDirectorIdPath;
	}

	public java.lang.String getaInChargeMonitorIdPath() {
		return aInChargeMonitorIdPath;
	}

	public void setaInChargeMonitorIdPath(java.lang.String aInChargeMonitorIdPath) {
		this.aInChargeMonitorIdPath = aInChargeMonitorIdPath;
	}

	public java.lang.String getbDirectorIdPath() {
		return bDirectorIdPath;
	}

	public void setbDirectorIdPath(java.lang.String bDirectorIdPath) {
		this.bDirectorIdPath = bDirectorIdPath;
	}

	public java.lang.String getbInChargeConstructIdPath() {
		return bInChargeConstructIdPath;
	}

	public void setbInChargeConstructIdPath(java.lang.String bInChargeConstructIdPath) {
		this.bInChargeConstructIdPath = bInChargeConstructIdPath;
	}

	public java.lang.String getcDesignDirectionIdPath() {
		return cDesignDirectionIdPath;
	}

	public void setcDesignDirectionIdPath(java.lang.String cDesignDirectionIdPath) {
		this.cDesignDirectionIdPath = cDesignDirectionIdPath;
	}

	public java.lang.String getcDesignManagerIdPath() {
		return cDesignManagerIdPath;
	}

	public void setcDesignManagerIdPath(java.lang.String cDesignManagerIdPath) {
		this.cDesignManagerIdPath = cDesignManagerIdPath;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public java.lang.Long getEstimatesItemChildId() {
		return estimatesItemChildId;
	}

	public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId) {
		this.estimatesItemChildId = estimatesItemChildId;
	}

	/**
	 * @return the itemName
	 */
	public java.lang.String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the constrtAddress
	 */
	public java.lang.String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}

	/**
	 * @param constrtAddress
	 *            the constrtAddress to set
	 */
	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	/**
	 * @return the bbhtpsklList
	 */
	public List<SceneGenerateWorkListDTO> getBbhtpsklList() {
		return bbhtpsklList;
	}

	/**
	 * @param bbhtpsklList
	 *            the bbhtpsklList to set
	 */
	public void setBbhtpsklList(List<SceneGenerateWorkListDTO> bbhtpsklList) {
		this.bbhtpsklList = bbhtpsklList;
	}

	/**
	 * @return the signDateDay
	 */
	public String getSignDateDay() {
		if (signDate != null) {
			signDateDay = DateFormatUtils.format(signDate, "dd");
		}
		return signDateDay;
	}

	/**
	 * @return the signDateMonth
	 */
	public String getSignDateMonth() {
		if (signDate != null) {
			signDateMonth = DateFormatUtils.format(signDate, "MM");
		}
		return signDateMonth;
	}

	/**
	 * @return the signDateYear
	 */
	public String getSignDateYear() {
		if (signDate != null) {
			signDateYear = DateFormatUtils.format(signDate, "yyyy");
		}
		return signDateYear;
	}

	/**
	 * @return the aDirectorIdName
	 */
	public java.lang.String getaDirectorIdName() {
		return aDirectorIdName;
	}

	/**
	 * @param aDirectorIdName
	 *            the aDirectorIdName to set
	 */
	public void setaDirectorIdName(java.lang.String aDirectorIdName) {
		this.aDirectorIdName = aDirectorIdName;
	}

	/**
	 * @return the aInChargeMonitorIdName
	 */
	public java.lang.String getaInChargeMonitorIdName() {
		return aInChargeMonitorIdName;
	}

	/**
	 * @param aInChargeMonitorIdName
	 *            the aInChargeMonitorIdName to set
	 */
	public void setaInChargeMonitorIdName(java.lang.String aInChargeMonitorIdName) {
		this.aInChargeMonitorIdName = aInChargeMonitorIdName;
	}

	/**
	 * @return the bDirectorIdName
	 */
	public java.lang.String getbDirectorIdName() {
		return bDirectorIdName;
	}

	/**
	 * @param bDirectorIdName
	 *            the bDirectorIdName to set
	 */
	public void setbDirectorIdName(java.lang.String bDirectorIdName) {
		this.bDirectorIdName = bDirectorIdName;
	}

	/**
	 * @return the bInChargeConstructIdName
	 */
	public java.lang.String getbInChargeConstructIdName() {
		return bInChargeConstructIdName;
	}

	/**
	 * @param bInChargeConstructIdName
	 *            the bInChargeConstructIdName to set
	 */
	public void setbInChargeConstructIdName(java.lang.String bInChargeConstructIdName) {
		this.bInChargeConstructIdName = bInChargeConstructIdName;
	}

	/**
	 * @return the cDesignDirectionIdName
	 */
	public java.lang.String getcDesignDirectionIdName() {
		return cDesignDirectionIdName;
	}

	/**
	 * @param cDesignDirectionIdName
	 *            the cDesignDirectionIdName to set
	 */
	public void setcDesignDirectionIdName(java.lang.String cDesignDirectionIdName) {
		this.cDesignDirectionIdName = cDesignDirectionIdName;
	}

	/**
	 * @return the cDesignManagerIdName
	 */
	public java.lang.String getcDesignManagerIdName() {
		return cDesignManagerIdName;
	}

	/**
	 * @param cDesignManagerIdName
	 *            the cDesignManagerIdName to set
	 */
	public void setcDesignManagerIdName(java.lang.String cDesignManagerIdName) {
		this.cDesignManagerIdName = cDesignManagerIdName;
	}

	/**
	 * @return the stationCode
	 */
	public java.lang.String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	/**
	 * @param stationCode
	 *            the stationCode to set
	 */
	public void setStationCode(java.lang.String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the constrtCode
	 */
	public java.lang.String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	/**
	 * @param constrtCode
	 *            the constrtCode to set
	 */
	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	/**
	 * @return the constrtName
	 */
	public java.lang.String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	/**
	 * @param constrtName
	 *            the constrtName to set
	 */
	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}

	/**
	 * @return the contractCode
	 */
	public java.lang.String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	/**
	 * @param contractCode
	 *            the contractCode to set
	 */
	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * @return the contractName
	 */
	public java.lang.String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	/**
	 * @param contractName
	 *            the contractName to set
	 */
	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}

	@Override
	public SceneGenerateWorkBO toModel() {
		SceneGenerateWorkBO sceneGenerateWorkBO = new SceneGenerateWorkBO();
		for (SceneGenerateWorkListDTO sceneGenerateWorkListDTO : bbhtpsklList) {
			SceneGenerateWorkListBO sgw = sceneGenerateWorkListDTO.toModel();
			sgw.setSceneGenerateWorkBO(sceneGenerateWorkBO);
			sceneGenerateWorkBO.getListSceneGenerateWork().add(sgw);
		}
		sceneGenerateWorkBO.setConstructId(this.constructId);
		sceneGenerateWorkBO.setSceneGenerateWorkId(this.sceneGenerateWorkId);
		sceneGenerateWorkBO.setCode(this.code);
		sceneGenerateWorkBO.setType(this.type);
		sceneGenerateWorkBO.setADirectorId(this.aDirectorId);
		sceneGenerateWorkBO.setAInChargeMonitorId(this.aInChargeMonitorId);
		sceneGenerateWorkBO.setBDirectorId(this.bDirectorId);
		sceneGenerateWorkBO.setBInChargeConstructId(this.bInChargeConstructId);
		sceneGenerateWorkBO.setCDesignDirectionId(this.cDesignDirectionId);
		sceneGenerateWorkBO.setCDesignManagerId(this.cDesignManagerId);
		sceneGenerateWorkBO.setIncidentConfirm(this.incidentConfirm);
		sceneGenerateWorkBO.setOtherComments(this.otherComments);
		sceneGenerateWorkBO.setConclusion(this.conclusion);
		sceneGenerateWorkBO.setCreatedDate(this.createdDate);
		sceneGenerateWorkBO.setCreatedUserId(this.createdUserId);
		sceneGenerateWorkBO.setApprovalDate(this.approvalDate);
		sceneGenerateWorkBO.setStatusCa(this.statusCa);
		sceneGenerateWorkBO.setIsActive(this.isActive);
		sceneGenerateWorkBO.setSignDate(this.signDate);
		sceneGenerateWorkBO.setSignPlace(this.signPlace);
		return sceneGenerateWorkBO;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public Long getFWModelId() {
		return sceneGenerateWorkId;
	}

	@Override
	public String catchName() {
		return getSceneGenerateWorkId().toString();
	}

	public java.lang.Long getSceneGenerateWorkId() {
		return sceneGenerateWorkId;
	}

	public void setSceneGenerateWorkId(java.lang.Long sceneGenerateWorkId) {
		this.sceneGenerateWorkId = sceneGenerateWorkId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getADirectorId() {
		return aDirectorId;
	}

	public void setADirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	public java.lang.Long getBDirectorId() {
		return bDirectorId;
	}

	public void setBDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}

	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	public java.lang.Long getCDesignDirectionId() {
		return cDesignDirectionId;
	}

	public void setCDesignDirectionId(java.lang.Long cDesignDirectionId) {
		this.cDesignDirectionId = cDesignDirectionId;
	}

	public java.lang.Long getCDesignManagerId() {
		return cDesignManagerId;
	}

	public void setCDesignManagerId(java.lang.Long cDesignManagerId) {
		this.cDesignManagerId = cDesignManagerId;
	}

	public java.lang.String getIncidentConfirm() {
		return incidentConfirm;
	}

	public void setIncidentConfirm(java.lang.String incidentConfirm) {
		this.incidentConfirm = incidentConfirm;
	}

	public java.lang.String getOtherComments() {
		return Strings.nullToEmpty(otherComments);
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	public java.lang.String getConclusion() {
		return Strings.nullToEmpty(conclusion);
	}

	public void setConclusion(java.lang.String conclusion) {
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

}
