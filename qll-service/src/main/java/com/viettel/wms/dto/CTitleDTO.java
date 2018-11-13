/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.wms.bo.CTitleBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "C_TITLEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CTitleDTO extends BaseFWDTOImpl<CTitleBO> {

	private java.lang.Long titleId;
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
	private java.lang.String isDeleted;
	private java.lang.String adOrgName;
	private java.lang.String createName;
	private java.lang.String text;
	
	public java.lang.String getText() {
		if (this.value != null && this.name != null) {
			if (!this.value.contains("Tìm kiếm thêm") && !this.name.contains("Tìm kiếm thêm")) {
				return this.value != null ? this.value + " - " + this.name : this.name;
			} else {
				return "Tìm kiếm thêm";
			}
		} else {
			return this.text;
		}
	}

	public void setText(java.lang.String text) {
		this.text = text;
	}

	public java.lang.String getCreateName() {
		return createName;
	}

	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	public java.lang.String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}

	private java.lang.String updateName;

	@Override
	public CTitleBO toModel() {
		CTitleBO cTitleBO = new CTitleBO();
		cTitleBO.setTitleId(this.titleId);
		cTitleBO.setAdOrgId(this.adOrgId);
		cTitleBO.setAdClientId(this.adClientId);
		cTitleBO.setCreated(this.created);
		cTitleBO.setCreatedby(this.createdby);
		cTitleBO.setUpdated(this.updated);
		cTitleBO.setUpdatedby(this.updatedby);
		cTitleBO.setName(this.name);
		cTitleBO.setValue(this.value);
		cTitleBO.setDescription(this.description);
		cTitleBO.setIsactive(this.isactive);
		cTitleBO.setIsDeleted(this.isDeleted);
		if (this.isactive == null || this.isactive.equalsIgnoreCase("true") ||this.isactive.equalsIgnoreCase("Y")) {
			cTitleBO.setIsactive("Y");
		} else {
			cTitleBO.setIsactive("N");
		}
				
		if (this.isDeleted == null || this.isDeleted.equalsIgnoreCase("N")) {
			cTitleBO.setIsDeleted("N");
		} else {
			cTitleBO.setIsDeleted("Y");
		
		}
		return cTitleBO;
	}

	@Override
	public Long getFWModelId() {
		return titleId;
	}

	@Override
	public String catchName() {
		return getTitleId().toString();
	}

	/**
	 * @return the titleId
	 */
	public java.lang.Long getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId the titleId to set
	 */
	public void setTitleId(java.lang.Long titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return the adOrgId
	 */
	public java.lang.Long getAdOrgId() {
		return adOrgId;
	}

	/**
	 * @param adOrgId the adOrgId to set
	 */
	public void setAdOrgId(java.lang.Long adOrgId) {
		this.adOrgId = adOrgId;
	}

	/**
	 * @return the adClientId
	 */
	public java.lang.Long getAdClientId() {
		return adClientId;
	}

	/**
	 * @param adClientId the adClientId to set
	 */
	public void setAdClientId(java.lang.Long adClientId) {
		this.adClientId = adClientId;
	}

	/**
	 * @return the created
	 */
	public java.util.Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	/**
	 * @return the createdby
	 */
	public java.lang.Long getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(java.lang.Long createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the updated
	 */
	public java.util.Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the updatedby
	 */
	public java.lang.Long getUpdatedby() {
		return updatedby;
	}

	/**
	 * @param updatedby the updatedby to set
	 */
	public void setUpdatedby(java.lang.Long updatedby) {
		this.updatedby = updatedby;
	}

	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public java.lang.String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * @return the isactive
	 */
	public java.lang.String getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(java.lang.String isactive) {
		this.isactive = isactive;
	}

	/**
	 * @return the isDeleted
	 */
	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public java.lang.String getAdOrgName() {
		return adOrgName;
	}

	public void setAdOrgName(java.lang.String adOrgName) {
		this.adOrgName = adOrgName;
	}

	
}
