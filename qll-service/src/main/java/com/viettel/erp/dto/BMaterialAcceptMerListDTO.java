/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.viettel.erp.bo.BMaterialAcceptMerListBO;
import com.viettel.erp.bo.BMaterialAcceptanceBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "B_MATERIAL_ACCEPT_MER_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BMaterialAcceptMerListDTO extends BaseFWDTOImpl<BMaterialAcceptMerListBO> {

	private java.lang.Long bMatAccMerListId;
	private java.lang.String materialName;
	private java.lang.String unit;
	private java.lang.Double quantity;
	private java.lang.String specificationOrigin;
	private java.lang.String quality;
	private java.lang.Long bMaterialAcceptanceId;
	private java.lang.Double  contractQuantity;



	public java.lang.Double getContractQuantity() {
		return contractQuantity;
	}

	public void setContractQuantity(java.lang.Double contractQuantity) {
		this.contractQuantity = contractQuantity;
	}

	private Integer stt;

	public Integer getStt() {
		return stt;
	}

	public void setStt(Integer stt) {
		this.stt = stt;
	}

	@Override
	public BMaterialAcceptMerListBO toModel() {
		BMaterialAcceptMerListBO bMaterialAcceptMerListBO = new BMaterialAcceptMerListBO();
		bMaterialAcceptMerListBO.setBMatAccMerListId(this.bMatAccMerListId);
		bMaterialAcceptMerListBO.setMaterialName(this.materialName);
		bMaterialAcceptMerListBO.setUnit(this.unit);
		bMaterialAcceptMerListBO.setQuantity(this.quantity);
		bMaterialAcceptMerListBO.setSpecificationOrigin(this.specificationOrigin);
		bMaterialAcceptMerListBO.setQuality(this.quality);
		// bMaterialAcceptMerListBO.setBMaterialAcceptanceId(this.bMaterialAcceptanceId);
		bMaterialAcceptMerListBO.setContractQuantity(this.contractQuantity);		
		bMaterialAcceptMerListBO.setbMaterialAcceptance(
		this.bMaterialAcceptanceId == null ? null : new BMaterialAcceptanceBO(bMaterialAcceptanceId));
		return bMaterialAcceptMerListBO;
	}

	@Override
	public Long getFWModelId() {
		return bMatAccMerListId;
	}

	@Override
	public String catchName() {
		return getBMatAccMerListId().toString();
	}

	public java.lang.Long getBMatAccMerListId() {
		return bMatAccMerListId;
	}

	public void setBMatAccMerListId(java.lang.Long bMatAccMerListId) {
		this.bMatAccMerListId = bMatAccMerListId;
	}

	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.Double getQuantity() {
		return quantity;
	}

	public void setQuantity(java.lang.Double quantity) {
		this.quantity = quantity;
	}

	public java.lang.String getSpecificationOrigin() {
		return Strings.nullToEmpty(specificationOrigin)  ;
	}

	public void setSpecificationOrigin(java.lang.String specificationOrigin) {
		this.specificationOrigin = specificationOrigin;
	}

	public java.lang.String getQuality() {
		return Strings.nullToEmpty(quality);
	}

	public void setQuality(java.lang.String quality) {
		this.quality = quality;
	}

	public java.lang.Long getBMaterialAcceptanceId() {
		return bMaterialAcceptanceId;
	}

	public void setBMaterialAcceptanceId(java.lang.Long bMaterialAcceptanceId) {
		this.bMaterialAcceptanceId = bMaterialAcceptanceId;
	}

}
