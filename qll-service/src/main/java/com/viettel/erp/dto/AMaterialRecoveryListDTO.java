/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.AMaterialRecoveryListBO;
import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_MATERIAL_RECOVERY_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AMaterialRecoveryListDTO extends BaseFWDTOImpl<AMaterialRecoveryListBO> {
	private static final long serialVersionUID = 1L;
	private java.lang.Long amaterialRecoveryListId;
	private java.lang.Long merEntityId;
	private java.lang.String merName;
	private java.lang.String serialNumber;
	private java.lang.Long unitId;
	private java.lang.Double recoveryQuantity;
	private java.lang.Long qualityStatus;
	private java.lang.String comments;
	private java.lang.String unitName;
	private java.lang.Double handoverQuantity;
	private java.lang.Double acceptQuantity;
	private java.lang.Long amaterialRecoveryMinutesId;
	private java.lang.String qualityStatusText;
	private java.lang.Long merID;
	private java.lang.String name;
	private java.lang.Double checkQuantity;
	private java.lang.Double sumRecoveryQuantity;
	private java.lang.Long  constructId;
	public java.lang.Double getSumRecoveryQuantity() {
		return sumRecoveryQuantity;
	}

	public void setSumRecoveryQuantity(java.lang.Double sumRecoveryQuantity) {
		this.sumRecoveryQuantity = sumRecoveryQuantity;
	}
	

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.Long getMerID() {
		return merID;
	}

	public void setMerID(java.lang.Long merID) {
		this.merID = merID;
	}

	private java.lang.Integer stt;

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Override
	public AMaterialRecoveryListBO toModel() {
		AMaterialRecoveryListBO aMaterialRecoveryListBO = new AMaterialRecoveryListBO();
		aMaterialRecoveryListBO.setAMaterialRecoveryListId(this.amaterialRecoveryListId);
		aMaterialRecoveryListBO.setMerEntityId(this.merEntityId == null ? this.merID : this.merEntityId);
		aMaterialRecoveryListBO.setMerName(this.merName);
		aMaterialRecoveryListBO.setSerialNumber(this.serialNumber);
		aMaterialRecoveryListBO.setUnitId(this.unitId);
		aMaterialRecoveryListBO.setRecoveryQuantity(this.recoveryQuantity);
		aMaterialRecoveryListBO.setQualityStatus(this.qualityStatus);
		aMaterialRecoveryListBO.setComments(this.comments);
		aMaterialRecoveryListBO.setUnitName(this.unitName);
		aMaterialRecoveryListBO.setHandoverQuantity(this.handoverQuantity);
		aMaterialRecoveryListBO.setAcceptQuantity(this.acceptQuantity);
		aMaterialRecoveryListBO.setAmaterialRecoveryMinutesBO(this.amaterialRecoveryMinutesId == null ? null
				: new AMaterialRecoveryMinutesBO(amaterialRecoveryMinutesId));
		return aMaterialRecoveryListBO;
	}

	public java.lang.String getQualityStatusText() {
		return Strings.nullToEmpty(qualityStatusText);
	}

	public void setQualityStatusText(java.lang.String qualityStatusText) {
		this.qualityStatusText = qualityStatusText;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.String getUnitName() {
		return Strings.nullToEmpty(unitName);
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	@Override
	public Long getFWModelId() {
		return amaterialRecoveryListId;
	}

	@Override
	public String catchName() {
		return getAMaterialRecoveryListId().toString();
	}

	public java.lang.Long getAMaterialRecoveryListId() {
		return amaterialRecoveryListId;
	}

	public void setAMaterialRecoveryListId(java.lang.Long amaterialRecoveryListId) {
		this.amaterialRecoveryListId = amaterialRecoveryListId;
	}

	public java.lang.Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	public java.lang.Double getHandoverQuantity() {
		if (handoverQuantity == null) {
			return 0D;
		}
		return handoverQuantity;
	}

	public void setHandoverQuantity(java.lang.Double handoverQuantity) {
		this.handoverQuantity = handoverQuantity;
	}

	public java.lang.Double getAcceptQuantity() {
		if (acceptQuantity == null) {
			return 0D;
		}
		return acceptQuantity;
	}

	public void setAcceptQuantity(java.lang.Double acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	public Double getOriginRecoveryQuantity() {
		return getRecoveryQuantity();
	}

	public java.lang.Double getCheckQuantity() {
		return checkQuantity;
	}

	public void setCheckQuantity(java.lang.Double checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public java.lang.String getSerialNumber() {
		return Strings.nullToEmpty(serialNumber);
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public java.lang.Long getUnitId() {
		return unitId;
	}

	public void setUnitId(java.lang.Long unitId) {
		this.unitId = unitId;
	}

	public java.lang.Double getRecoveryQuantity() {
		if (recoveryQuantity == null) {
			return 0D;
		}
		return recoveryQuantity;
	}

	public void setRecoveryQuantity(java.lang.Double recoveryQuantity) {
		this.recoveryQuantity = recoveryQuantity;
	}

	public java.lang.Long getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(java.lang.Long qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	public java.lang.String getComments() {
		return Strings.nullToEmpty(comments);
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.Long getAMaterialRecoveryMinutesId() {
		return amaterialRecoveryMinutesId;
	}

	public void setAMaterialRecoveryMinutesId(java.lang.Long amaterialRecoveryMinutesId) {
		this.amaterialRecoveryMinutesId = amaterialRecoveryMinutesId;
	}

}
