/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.DistanceUnloadConstrMinutesBO;
import com.viettel.erp.bo.DistanceUnloadListBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.bo.SceneGenerateWorkListBO;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author phongpv
 */

@SuppressWarnings("serial")
@XmlRootElement(name = "DISTANCE_UNLOAD_CONSTR_MINUTESBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceUnloadConstrMinutesDTO extends BaseFWDTOImpl<DistanceUnloadConstrMinutesBO> {

	private java.lang.Long constructId;
	private java.lang.Long sceneGenWorkListId;
	private java.lang.Long isActive;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private java.lang.Long disUnloadConsMinId;
	private java.lang.String code;
	private java.lang.Long estimatesWorkItemId;
	private java.lang.String estimatesWorkItemName;
	private java.lang.Long aDirectorId;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String carGatherPlace;
	private java.lang.String carDesPlace;
	private java.lang.String wheelbarrowGatherPlace;
	private java.lang.String wheelbarrowDesPlace;
	private java.lang.String handmadeGatherPlace;
	private java.lang.String handmadeDesPlace;
	private java.lang.String conclusion;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long type;
	// them du lieu tu view v_contruction_qlhc
	private String constrtCode;
	private String contractCode;
	private String contractName;
	private String constrtName;
	private String stationCode;
	private String constrtAddress;
	private java.lang.Double contractId;

	private java.lang.Long constrCompReMapId;
	
	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	private java.lang.String incurredContent;
	private java.lang.String workItemName;
	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";

	private java.lang.String aDirectorIdPath;
	private java.lang.String aInChargeMonitorIdPath;
	private java.lang.String bDirectorIdPath;
	private java.lang.String bInChargeConstructIdPath;
	
	private java.lang.String aDirectorIdNameSign;
	private java.lang.String aInChargeMonitorIdNameSign;
	private java.lang.String bDirectorIdNameSign;
	private java.lang.String bInChargeConstructIdNameSign;

	private java.lang.String comments;
	
	
	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.String getaDirectorIdNameSign() {
		return aDirectorIdNameSign;
	}

	public void setaDirectorIdNameSign(java.lang.String aDirectorIdNameSign) {
		this.aDirectorIdNameSign = aDirectorIdNameSign;
	}

	public java.lang.String getaInChargeMonitorIdNameSign() {
		return aInChargeMonitorIdNameSign;
	}

	public void setaInChargeMonitorIdNameSign(java.lang.String aInChargeMonitorIdNameSign) {
		this.aInChargeMonitorIdNameSign = aInChargeMonitorIdNameSign;
	}

	public java.lang.String getbDirectorIdNameSign() {
		return bDirectorIdNameSign;
	}

	public void setbDirectorIdNameSign(java.lang.String bDirectorIdNameSign) {
		this.bDirectorIdNameSign = bDirectorIdNameSign;
	}

	public java.lang.String getbInChargeConstructIdNameSign() {
		return bInChargeConstructIdNameSign;
	}

	public void setbInChargeConstructIdNameSign(java.lang.String bInChargeConstructIdNameSign) {
		this.bInChargeConstructIdNameSign = bInChargeConstructIdNameSign;
	}

	public java.lang.String getWorkItemName() {
		return workItemName;
	}

	public void setWorkItemName(java.lang.String workItemName) {
		this.workItemName = workItemName;
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

	public String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}

	public void setConstrtAddress(String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	public String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public String getSignDateDay() {
		if (signDate != null) {
			signDateDay = DateFormatUtils.format(signDate, "dd");
		}
		return signDateDay;
	}

	public String getSignDateMonth() {
		if (signDate != null) {
			signDateMonth = DateFormatUtils.format(signDate, "MM");
		}
		return signDateMonth;
	}

	public String getSignDateYear() {
		if (signDate != null) {
			signDateYear = DateFormatUtils.format(signDate, "yyyy");
		}
		return signDateYear;
	}

	private java.lang.String aDirectorIdName;
	private java.lang.String aInChargeMonitorIdName;
	private java.lang.String bDirectorIdName;
	private java.lang.String bInChargeConstructIdName;
	private ConstrCompleteRecordsMapDTO constrcompleterecordsmap;
	private ApprovalSignManagementDTO approvalsignmanagement;

	private List<DistanceUnloadListDTO> distanceUnloadList = Lists.newArrayList();

	@Override
	public DistanceUnloadConstrMinutesBO toModel() {
		DistanceUnloadConstrMinutesBO distanceUnloadConstrMinutesBO = new DistanceUnloadConstrMinutesBO();

		for (DistanceUnloadListDTO obj : distanceUnloadList) {
			DistanceUnloadListBO cust = obj.toModel();
			cust.setDistanceUnloadConstrMinutesBO(distanceUnloadConstrMinutesBO);
			distanceUnloadConstrMinutesBO.getDistanceUnloadList().add(cust);
		}
		distanceUnloadConstrMinutesBO
				.setVconstructionHcqt(this.constructId == null ? null : new VConstructionHcqtBO(this.constructId));
		distanceUnloadConstrMinutesBO.setScenegenerateworklist(
				this.sceneGenWorkListId == null ? null : new SceneGenerateWorkListBO(this.sceneGenWorkListId));
		distanceUnloadConstrMinutesBO.setIsActive(this.isActive);
		distanceUnloadConstrMinutesBO.setType(this.type);
		distanceUnloadConstrMinutesBO.setSignDate(this.signDate);
		distanceUnloadConstrMinutesBO.setSignPlace(this.signPlace);
		distanceUnloadConstrMinutesBO.setDisUnloadConsMinId(this.disUnloadConsMinId);
		distanceUnloadConstrMinutesBO.setCode(this.code);
		//distanceUnloadConstrMinutesBO.setEstimatesWorkItemId(this.estimatesWorkItemId);
		distanceUnloadConstrMinutesBO.setEstimatesworkitems(this.estimatesWorkItemId == null? null : new EstimatesWorkItemsBO(estimatesWorkItemId));
		distanceUnloadConstrMinutesBO.setADirectorId(this.aDirectorId);
		distanceUnloadConstrMinutesBO.setAInChargeMonitorId(this.aInChargeMonitorId);
		distanceUnloadConstrMinutesBO.setBDirectorId(this.bDirectorId);
		distanceUnloadConstrMinutesBO.setBInChargeConstructId(this.bInChargeConstructId);
		distanceUnloadConstrMinutesBO.setCarGatherPlace(this.carGatherPlace);
		distanceUnloadConstrMinutesBO.setCarDesPlace(this.carDesPlace);
		distanceUnloadConstrMinutesBO.setWheelbarrowGatherPlace(this.wheelbarrowGatherPlace);
		distanceUnloadConstrMinutesBO.setWheelbarrowDesPlace(this.wheelbarrowDesPlace);
		distanceUnloadConstrMinutesBO.setHandmadeGatherPlace(this.handmadeGatherPlace);
		distanceUnloadConstrMinutesBO.setHandmadeDesPlace(this.handmadeDesPlace);
		distanceUnloadConstrMinutesBO.setConclusion(this.conclusion);
		distanceUnloadConstrMinutesBO.setCreatedDate(this.createdDate);
		distanceUnloadConstrMinutesBO.setCreatedUserId(this.createdUserId);
		distanceUnloadConstrMinutesBO.setApprovalDate(this.approvalDate);
		distanceUnloadConstrMinutesBO.setStatusCa(this.statusCa);
		return distanceUnloadConstrMinutesBO;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
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

	@Override
	public Long getFWModelId() {
		return disUnloadConsMinId;
	}

	@Override
	public String catchName() {
		return getDisUnloadConsMinId().toString();
	}

	public java.lang.Long getDisUnloadConsMinId() {
		return disUnloadConsMinId;
	}

	public void setDisUnloadConsMinId(java.lang.Long disUnloadConsMinId) {
		this.disUnloadConsMinId = disUnloadConsMinId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
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

	public java.lang.String getCarGatherPlace() {
		return Strings.nullToEmpty(carGatherPlace);
	}

	public void setCarGatherPlace(java.lang.String carGatherPlace) {
		this.carGatherPlace = carGatherPlace;
	}

	public java.lang.String getCarDesPlace() {
		return Strings.nullToEmpty(carDesPlace);
	}

	public void setCarDesPlace(java.lang.String carDesPlace) {
		this.carDesPlace = carDesPlace;
	}

	public java.lang.String getWheelbarrowGatherPlace() {
		return Strings.nullToEmpty(wheelbarrowGatherPlace);
	}

	public void setWheelbarrowGatherPlace(java.lang.String wheelbarrowGatherPlace) {
		this.wheelbarrowGatherPlace = wheelbarrowGatherPlace;
	}

	public java.lang.String getWheelbarrowDesPlace() {
		return Strings.nullToEmpty(wheelbarrowDesPlace);
	}

	public void setWheelbarrowDesPlace(java.lang.String wheelbarrowDesPlace) {
		this.wheelbarrowDesPlace = wheelbarrowDesPlace;
	}

	public java.lang.String getHandmadeGatherPlace() {
		return Strings.nullToEmpty(handmadeGatherPlace);
	}

	public void setHandmadeGatherPlace(java.lang.String handmadeGatherPlace) {
		this.handmadeGatherPlace = handmadeGatherPlace;
	}

	public java.lang.String getHandmadeDesPlace() {
		return Strings.nullToEmpty(handmadeDesPlace);
	}

	public void setHandmadeDesPlace(java.lang.String handmadeDesPlace) {
		this.handmadeDesPlace = handmadeDesPlace;
	}

	public java.lang.String getConclusion() {
		return conclusion;
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

	public String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public List<DistanceUnloadListDTO> getDistanceUnloadList() {
		return distanceUnloadList;
	}

	public void setDistanceUnloadList(List<DistanceUnloadListDTO> distanceUnloadList) {
		this.distanceUnloadList = distanceUnloadList;
	}

	public java.lang.Long getSceneGenWorkListId() {
		return sceneGenWorkListId;
	}

	public void setSceneGenWorkListId(java.lang.Long sceneGenWorkListId) {
		this.sceneGenWorkListId = sceneGenWorkListId;
	}

	public java.lang.String getaDirectorIdName() {
		return aDirectorIdName;
	}

	public void setaDirectorIdName(java.lang.String aDirectorIdName) {
		this.aDirectorIdName = aDirectorIdName;
	}

	public java.lang.String getaInChargeMonitorIdName() {
		return aInChargeMonitorIdName;
	}

	public void setaInChargeMonitorIdName(java.lang.String aInChargeMonitorIdName) {
		this.aInChargeMonitorIdName = aInChargeMonitorIdName;
	}

	public java.lang.String getbDirectorIdName() {
		return bDirectorIdName;
	}

	public void setbDirectorIdName(java.lang.String bDirectorIdName) {
		this.bDirectorIdName = bDirectorIdName;
	}

	public java.lang.String getbInChargeConstructIdName() {
		return bInChargeConstructIdName;
	}

	public void setbInChargeConstructIdName(java.lang.String bInChargeConstructIdName) {
		this.bInChargeConstructIdName = bInChargeConstructIdName;
	}

	public java.lang.String getIncurredContent() {
		return incurredContent;
	}

	public void setIncurredContent(java.lang.String incurredContent) {
		this.incurredContent = incurredContent;
	}

	public ApprovalSignManagementDTO getApprovalsignmanagement() {
		return approvalsignmanagement;
	}

	public void setApprovalsignmanagement(ApprovalSignManagementDTO approvalsignmanagement) {
		this.approvalsignmanagement = approvalsignmanagement;
	}
	
	public ConstrCompleteRecordsMapDTO getConstrcompleterecordsmap() {
		return constrcompleterecordsmap;
	}

	public void setConstrcompleterecordsmap(ConstrCompleteRecordsMapDTO constrcompleterecordsmap) {
		this.constrcompleterecordsmap = constrcompleterecordsmap;
	}

	public java.lang.String getEstimatesWorkItemName() {
		return estimatesWorkItemName;
	}

	public void setEstimatesWorkItemName(java.lang.String estimatesWorkItemName) {
		this.estimatesWorkItemName = estimatesWorkItemName;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}
}
