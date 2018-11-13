/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.AAssetBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "A_ASSETBO")
public class AAssetDTO extends BaseFWDTOImpl<AAssetBO> {

	private java.lang.String isDeleted;
	private java.lang.Long aAssetId;
	private java.lang.Long aAssetTypeId;
	private java.lang.Long aAssetGroupId;
	private java.lang.Long adOrgId;
	private java.lang.String value;
	private java.lang.String name;
	private java.util.Date dateused;
	private java.util.Date repairdate;
	private java.lang.String manufactturer;
	private java.lang.String model;
	private java.lang.String serial;
	private java.lang.String power;
	private java.lang.String gasType;
	private java.lang.String fuel;
	private java.lang.String isactive;
	private java.lang.Long createdby;
	private java.lang.Long updatedby;
	private java.util.Date created;
	private java.util.Date updated;
	private java.lang.String adOrgName;
	private java.lang.String aassetGroupName;
	private java.lang.String aassetTypeName;
	private java.lang.String dateusedstr;
	private java.lang.String repairdatestr;

	@Override
	public AAssetBO toModel() {
		AAssetBO aAssetBO = new AAssetBO();
		aAssetBO.setIsDeleted(this.isDeleted);
		aAssetBO.setAAssetId(this.aAssetId);
		aAssetBO.setAAssetTypeId(this.aAssetTypeId);
		aAssetBO.setAAssetGroupId(this.aAssetGroupId);
		aAssetBO.setAdOrgId(this.adOrgId);
		aAssetBO.setValue(this.value);
		aAssetBO.setName(this.name);
		aAssetBO.setDateused(this.dateused);
		aAssetBO.setRepairdate(this.repairdate);
		aAssetBO.setManufactturer(this.manufactturer);
		aAssetBO.setModel(this.model);
		aAssetBO.setSerial(this.serial);
		aAssetBO.setPower(this.power);
		aAssetBO.setGasType(this.gasType);
		aAssetBO.setFuel(this.fuel);
		aAssetBO.setIsactive(this.isactive);
		aAssetBO.setCreatedby(this.createdby);
		aAssetBO.setUpdatedby(this.updatedby);
		aAssetBO.setCreated(this.created);
		aAssetBO.setUpdated(this.updated);
		return aAssetBO;
	}

	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public Long getFWModelId() {
		return aAssetId;
	}

	@Override
	public String catchName() {
		return getAAssetId().toString();
	}

	public java.lang.Long getAAssetId() {
		return aAssetId;
	}

	public void setAAssetId(java.lang.Long aAssetId) {
		this.aAssetId = aAssetId;
	}

	public java.lang.Long getAAssetTypeId() {
		return aAssetTypeId;
	}

	public void setAAssetTypeId(java.lang.Long aAssetTypeId) {
		this.aAssetTypeId = aAssetTypeId;
	}

	public java.lang.Long getAAssetGroupId() {
		return aAssetGroupId;
	}

	public void setAAssetGroupId(java.lang.Long aAssetGroupId) {
		this.aAssetGroupId = aAssetGroupId;
	}

	public java.lang.Long getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(java.lang.Long adOrgId) {
		this.adOrgId = adOrgId;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.Date getDateused() {
		return dateused;
	}

	public void setDateused(java.util.Date dateused) {
		this.dateused = dateused;
	}

	public java.util.Date getRepairdate() {
		return repairdate;
	}

	public void setRepairdate(java.util.Date repairdate) {
		this.repairdate = repairdate;
	}

	public java.lang.String getManufactturer() {
		return manufactturer;
	}

	public void setManufactturer(java.lang.String manufactturer) {
		this.manufactturer = manufactturer;
	}

	public java.lang.String getModel() {
		return model;
	}

	public void setModel(java.lang.String model) {
		this.model = model;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}

	public java.lang.String getPower() {
		return power;
	}

	public void setPower(java.lang.String power) {
		this.power = power;
	}

	public java.lang.String getGasType() {
		return gasType;
	}

	public void setGasType(java.lang.String gasType) {
		this.gasType = gasType;
	}

	public java.lang.String getFuel() {
		return fuel;
	}

	public void setFuel(java.lang.String fuel) {
		this.fuel = fuel;
	}

	public java.lang.String getIsactive() {
		return isactive;
	}

	public void setIsactive(java.lang.String isactive) {
		this.isactive = isactive;
	}

	public java.lang.Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(java.lang.Long createdby) {
		this.createdby = createdby;
	}

	public java.lang.Long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(java.lang.Long updatedby) {
		this.updatedby = updatedby;
	}

	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.util.Date getUpdated() {
		return updated;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	public java.lang.String getAdOrgName() {
		return adOrgName;
	}

	public void setAdOrgName(java.lang.String adOrgName) {
		this.adOrgName = adOrgName;
	}

	public java.lang.String getAassetGroupName() {
		return aassetGroupName;
	}

	public void setAassetGroupName(java.lang.String aassetGroupName) {
		this.aassetGroupName = aassetGroupName;
	}

	public java.lang.String getAassetTypeName() {
		return aassetTypeName;
	}

	public void setAassetTypeName(java.lang.String aassetTypeName) {
		this.aassetTypeName = aassetTypeName;
	}
	public java.lang.String getDateusedstr() {
		return dateusedstr;
	}

	public void setDateusedstr(java.lang.String dateusedstr) {
		this.dateusedstr = dateusedstr;
	}

	public java.lang.String getRepairdatestr() {
		return repairdatestr;
	}

	public void setRepairdatestr(java.lang.String repairdatestr) {
		this.repairdatestr = repairdatestr;
	}
}
