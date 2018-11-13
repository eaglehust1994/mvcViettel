/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "B_MATERIAL_ACCEPT_MER_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class BMaterialAcceptMerListBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3031817666158352412L;
	private java.lang.Long bMatAccMerListId;
	private java.lang.String materialName;
	private java.lang.String unit;
	private java.lang.Double quantity;
	private java.lang.String specificationOrigin;
	private java.lang.String quality;
	private java.lang.Double  contractQuantity;


	@Column(name = "CONTRACT_QUANTITY")
	public java.lang.Double getContractQuantity() {
		return contractQuantity;
	}

	public void setContractQuantity(java.lang.Double contractQuantity) {
		this.contractQuantity = contractQuantity;
	}

	private BMaterialAcceptanceBO bMaterialAcceptance;

	@ManyToOne
	@JoinColumn(name = "B_MATERIAL_ACCEPTANCE_ID")
	public BMaterialAcceptanceBO getbMaterialAcceptance() {
		return bMaterialAcceptance;
	}

	public void setbMaterialAcceptance(BMaterialAcceptanceBO bMaterialAcceptance) {
		this.bMaterialAcceptance = bMaterialAcceptance;
	}

	public BMaterialAcceptMerListBO() {
		setColId("bMatAccMerListId");
		setColName("bMatAccMerListId");
		setUniqueColumn(new String[] { "bMatAccMerListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "B_MATERIAL_ACCEPT_MER_LIST_SEQ") })
	@Column(name = "B_MAT_ACC_MER_LIST_ID", length = 22)
	public java.lang.Long getBMatAccMerListId() {
		return bMatAccMerListId;
	}

	public void setBMatAccMerListId(java.lang.Long bMatAccMerListId) {
		this.bMatAccMerListId = bMatAccMerListId;
	}

	@Column(name = "MATERIAL_NAME", length = 1000)
	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "UNIT", length = 400)
	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	@Column(name = "QUANTITY")
	public java.lang.Double getQuantity() {
		return quantity;
	}

	public void setQuantity(java.lang.Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "SPECIFICATION_ORIGIN", length = 1000)
	public java.lang.String getSpecificationOrigin() {
		return specificationOrigin;
	}

	public void setSpecificationOrigin(java.lang.String specificationOrigin) {
		this.specificationOrigin = specificationOrigin;
	}

	@Column(name = "QUALITY", length = 400)
	public java.lang.String getQuality() {
		return quality;
	}

	public void setQuality(java.lang.String quality) {
		this.quality = quality;
	}

	@Override
	public BMaterialAcceptMerListDTO toDTO() {
		BMaterialAcceptMerListDTO bMaterialAcceptMerListDTO = new BMaterialAcceptMerListDTO();
		// set cac gia tri
		bMaterialAcceptMerListDTO.setBMatAccMerListId(this.bMatAccMerListId);
		bMaterialAcceptMerListDTO.setMaterialName(this.materialName);
		bMaterialAcceptMerListDTO.setUnit(this.unit);
		bMaterialAcceptMerListDTO.setQuantity(this.quantity);
		bMaterialAcceptMerListDTO.setSpecificationOrigin(this.specificationOrigin);
		bMaterialAcceptMerListDTO.setQuality(this.quality);
		bMaterialAcceptMerListDTO.setContractQuantity(this.contractQuantity);
		
		// bMaterialAcceptMerListDTO.setBMaterialAcceptanceId(this.bMaterialAcceptanceId);
		bMaterialAcceptMerListDTO.setBMaterialAcceptanceId(
				this.bMaterialAcceptance == null ? null : bMaterialAcceptance.getBMaterialAcceptanceId());
		return bMaterialAcceptMerListDTO;
	}
}
