/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.CWorkTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "C_WORK_TYPE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CWorkTypeBO extends BaseFWModelImpl {

	private java.lang.String isBase;
	private java.util.Date validFrom;
	private java.util.Date validTo;
	private java.lang.String isDeleted;
	private java.lang.Long cWorkTypeId;
	private java.lang.Long adOrgId;
	private java.lang.Long adClientId;
	private java.util.Date created;
	private java.lang.Long createdby;
	private java.util.Date updated;
	private java.lang.Long updatedby;
	private java.lang.String name;
	private java.lang.String value;
	private java.lang.String description;
	private java.lang.String isactive;
	private java.lang.String adOrgName;

	public CWorkTypeBO() {
		setColId("cWorkTypeId");
		setColName("cWorkTypeId");
		setUniqueColumn(new String[] { "cWorkTypeId" });
	}

	@Column(name = "IS_BASE", length = 1)
	public java.lang.String getIsBase() {
		return isBase;
	}

	public void setIsBase(java.lang.String isBase) {
		this.isBase = isBase;
	}

	@Column(name = "VALID_FROM", length = 7)
	public java.util.Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(java.util.Date validFrom) {
		this.validFrom = validFrom;
	}

	@Column(name = "VALID_TO", length = 7)
	public java.util.Date getValidTo() {
		return validTo;
	}

	public void setValidTo(java.util.Date validTo) {
		this.validTo = validTo;
	}

	@Column(name = "IS_DELETED", length = 1)
	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "C_WORK_TYPE_SEQ") })
	@Column(name = "C_WORK_TYPE_ID", length = 22)
	public java.lang.Long getCWorkTypeId() {
		return cWorkTypeId;
	}

	public void setCWorkTypeId(java.lang.Long cWorkTypeId) {
		this.cWorkTypeId = cWorkTypeId;
	}

	@Column(name = "AD_ORG_ID", length = 22)
	public java.lang.Long getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(java.lang.Long adOrgId) {
		this.adOrgId = adOrgId;
	}

	@Column(name = "AD_CLIENT_ID", length = 22)
	public java.lang.Long getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(java.lang.Long adClientId) {
		this.adClientId = adClientId;
	}

	@Column(name = "CREATED", length = 7)
	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	@Column(name = "CREATEDBY", length = 22)
	public java.lang.Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(java.lang.Long createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATED", length = 7)
	public java.util.Date getUpdated() {
		return updated;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	@Column(name = "UPDATEDBY", length = 22)
	public java.lang.Long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(java.lang.Long updatedby) {
		this.updatedby = updatedby;
	}

	@Column(name = "NAME", length = 510)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Column(name = "VALUE", length = 80)
	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Column(name = "ISACTIVE", length = 1)
	public java.lang.String getIsactive() {
		return isactive;
	}

	public void setIsactive(java.lang.String isactive) {
		this.isactive = isactive;
	}

	@Override
	public CWorkTypeDTO toDTO() {
		CWorkTypeDTO cWorkTypeDTO = new CWorkTypeDTO();
		// set cac gia tri
		cWorkTypeDTO.setIsBase(this.isBase);
		cWorkTypeDTO.setValidFrom(this.validFrom);
		cWorkTypeDTO.setValidTo(this.validTo);
		cWorkTypeDTO.setIsDeleted(this.isDeleted);
		cWorkTypeDTO.setWorkTypeId(this.cWorkTypeId);
		cWorkTypeDTO.setAdOrgId(this.adOrgId);
		cWorkTypeDTO.setAdClientId(this.adClientId);
		cWorkTypeDTO.setCreated(this.created);
		cWorkTypeDTO.setCreatedby(this.createdby);
		cWorkTypeDTO.setUpdated(this.updated);
		cWorkTypeDTO.setUpdatedby(this.updatedby);
		cWorkTypeDTO.setName(this.name);
		cWorkTypeDTO.setValue(this.value);
		cWorkTypeDTO.setDescription(this.description);
		cWorkTypeDTO.setIsactive(this.isactive);
		cWorkTypeDTO.setAdOrgName(this.adOrgName);
		
		return cWorkTypeDTO;
	}
}
