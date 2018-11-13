/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "A_MATERIAL_RECOVERY_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AMaterialRecoveryListBO extends BaseFWModelImpl {

	private java.lang.Long aMaterialRecoveryListId;
	private java.lang.Long merEntityId;
	private java.lang.String merName;
	private java.lang.String serialNumber;
	private java.lang.Long unitId;
	private java.lang.Double recoveryQuantity;
	private java.lang.Long qualityStatus;
	private java.lang.String comments;
	private java.lang.Long aMaterialRecoveryMinutesId;
	private java.lang.String unitName;
	private java.lang.Double handoverQuantity;
	private java.lang.Double acceptQuantity;
	private AMaterialRecoveryMinutesBO amaterialRecoveryMinutesBO;

	@ManyToOne
	@JoinColumn(name = "A_MATERIAL_RECOVERY_MINUTES_ID")
	public AMaterialRecoveryMinutesBO getAmaterialRecoveryMinutesBO() {
		return amaterialRecoveryMinutesBO;
	}

	public AMaterialRecoveryListBO() {
		setColId("aMaterialRecoveryListId");
		setColName("aMaterialRecoveryListId");
		setUniqueColumn(new String[] { "aMaterialRecoveryListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "A_MATERIAL_RECOVERY_LIST_SEQ") })
	@Column(name = "A_MATERIAL_RECOVERY_LIST_ID", length = 22)
	public java.lang.Long getAMaterialRecoveryListId() {
		return aMaterialRecoveryListId;
	}

	public void setAMaterialRecoveryListId(java.lang.Long aMaterialRecoveryListId) {
		this.aMaterialRecoveryListId = aMaterialRecoveryListId;
	}

	@Column(name = "HANDOVER_QUANTITY", length = 22)
	public java.lang.Double getHandoverQuantity() {
		return handoverQuantity;
	}

	public void setHandoverQuantity(java.lang.Double handoverQuantity) {
		this.handoverQuantity = handoverQuantity;
	}

	@Column(name = "ACCEPT_QUANTITY", length = 22)
	public java.lang.Double getAcceptQuantity() {
		return acceptQuantity;
	}

	public void setAcceptQuantity(java.lang.Double acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	public void setAmaterialRecoveryMinutesBO(AMaterialRecoveryMinutesBO amaterialRecoveryMinutesBO) {
		this.amaterialRecoveryMinutesBO = amaterialRecoveryMinutesBO;
	}

	@Column(name = "MER_ENTITY_ID", length = 22)
	public java.lang.Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	@Column(name = "MER_NAME", length = 1000)
	public java.lang.String getMerName() {
		return merName;
	}

	public void setMerName(java.lang.String merName) {
		this.merName = merName;
	}

	@Column(name = "SERIAL_NUMBER", length = 400)
	public java.lang.String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "UNIT_ID", length = 22)
	public java.lang.Long getUnitId() {
		return unitId;
	}

	public void setUnitId(java.lang.Long unitId) {
		this.unitId = unitId;
	}

	@Column(name = "RECOVERY_QUANTITY", length = 22)
	public java.lang.Double getRecoveryQuantity() {
		return recoveryQuantity;
	}

	public void setRecoveryQuantity(java.lang.Double recoveryQuantity) {
		this.recoveryQuantity = recoveryQuantity;
	}

	@Column(name = "QUALITY_STATUS", length = 22)
	public java.lang.Long getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(java.lang.Long qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	@Column(name = "COMMENTS", length = 1000)
	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	@Column(name = "UNIT_NAME", length = 1000)
	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	@Override
	public AMaterialRecoveryListDTO toDTO() {
		AMaterialRecoveryListDTO aMaterialRecoveryListDTO = new AMaterialRecoveryListDTO();
		// set cac gia tri
		aMaterialRecoveryListDTO.setAMaterialRecoveryListId(this.aMaterialRecoveryListId);
		aMaterialRecoveryListDTO.setMerEntityId(this.merEntityId);
		aMaterialRecoveryListDTO.setMerName(this.merName);
		aMaterialRecoveryListDTO.setSerialNumber(this.serialNumber);
		aMaterialRecoveryListDTO.setUnitId(this.unitId);
		aMaterialRecoveryListDTO.setRecoveryQuantity(this.recoveryQuantity);
		aMaterialRecoveryListDTO.setQualityStatus(this.qualityStatus);
		aMaterialRecoveryListDTO.setComments(this.comments);
		aMaterialRecoveryListDTO.setUnitName(this.unitName);
		aMaterialRecoveryListDTO.setHandoverQuantity(this.handoverQuantity);
		aMaterialRecoveryListDTO.setAcceptQuantity(this.acceptQuantity);
		aMaterialRecoveryListDTO.setAMaterialRecoveryMinutesId(this.amaterialRecoveryMinutesBO == null ? null
				: amaterialRecoveryMinutesBO.getAMaterialRecoveryMinutesId());
		return aMaterialRecoveryListDTO;
	}
}
