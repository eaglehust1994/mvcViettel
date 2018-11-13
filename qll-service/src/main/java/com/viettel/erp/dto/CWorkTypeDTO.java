/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.bo.CWorkTypeBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "C_WORK_TYPEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CWorkTypeDTO extends BaseFWDTOImpl<CWorkTypeBO> {

	private java.lang.String isBase;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date validFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date validTo;
	private java.lang.String isDeleted;
	private java.lang.Long workTypeId;
	private java.lang.Long adOrgId;
	private java.lang.Long adClientId;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date created;
	private java.lang.Long createdby;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updated;
	private java.lang.Long updatedby;
	private java.lang.String name;
	private java.lang.String value;
	private java.lang.String description;
	private java.lang.String isactive;
	private java.lang.String adOrgName;

	@Override
	public CWorkTypeBO toModel() {
		CWorkTypeBO cWorkTypeBO = new CWorkTypeBO();
		cWorkTypeBO.setIsBase(this.isBase);
		cWorkTypeBO.setValidFrom(this.validFrom);
		cWorkTypeBO.setValidTo(this.validTo);
		cWorkTypeBO.setIsDeleted(this.isDeleted);
		cWorkTypeBO.setCWorkTypeId(this.workTypeId);
		cWorkTypeBO.setAdOrgId(this.adOrgId);
		cWorkTypeBO.setAdClientId(this.adClientId);
		cWorkTypeBO.setCreated(this.created);
		cWorkTypeBO.setCreatedby(this.createdby);
		cWorkTypeBO.setUpdated(this.updated);
		cWorkTypeBO.setUpdatedby(this.updatedby);
		cWorkTypeBO.setName(this.name);
		cWorkTypeBO.setValue(this.value);
		cWorkTypeBO.setDescription(this.description);
		cWorkTypeBO.setIsactive(this.isactive);
		return cWorkTypeBO;
	}

	public java.lang.String getIsBase() {
		return isBase;
	}

	public void setIsBase(java.lang.String isBase) {
		this.isBase = isBase;
	}

	public java.util.Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(java.util.Date validFrom) {
		this.validFrom = validFrom;
	}

	public java.util.Date getValidTo() {
		return validTo;
	}

	public void setValidTo(java.util.Date validTo) {
		this.validTo = validTo;
	}

	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public Long getFWModelId() {
		return workTypeId;
	}

	@Override
	public String catchName() {
		return getWorkTypeId().toString();
	}


	public java.lang.Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(java.lang.Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public java.lang.Long getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(java.lang.Long adOrgId) {
		this.adOrgId = adOrgId;
	}

	public java.lang.Long getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(java.lang.Long adClientId) {
		this.adClientId = adClientId;
	}

	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.lang.Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(java.lang.Long createdby) {
		this.createdby = createdby;
	}

	public java.util.Date getUpdated() {
		return updated;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	public java.lang.Long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(java.lang.Long updatedby) {
		this.updatedby = updatedby;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getIsactive() {
		return isactive;
	}

	public void setIsactive(java.lang.String isactive) {
		this.isactive = isactive;
	}

	public java.lang.String getAdOrgName() {
		return adOrgName;
	}

	public void setAdOrgName(java.lang.String adOrgName) {
		this.adOrgName = adOrgName;
	}
	
	

}
