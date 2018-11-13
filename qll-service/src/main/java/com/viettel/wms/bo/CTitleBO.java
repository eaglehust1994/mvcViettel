/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.viettel.wms.dto.CTitleDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "C_TITLE")
@Where( clause = "IS_DELETED = 'N' OR IS_DELETED is null" )
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="wms-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CTitleBO extends BaseFWModelImpl {

	private java.lang.Long titleId;
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
	private java.lang.String isDeleted;

	public CTitleBO() {
		setColId("titleId");
		setColName("titleId");
		setUniqueColumn(new String[] { "titleId" });
	}

	public CTitleBO(Long id) {
		this.titleId = id;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "C_TITLE_SEQ") })
	@Column(name = "C_TITLE_ID", length = 22)
	public java.lang.Long getTitleId() {
		return titleId;
	}

	public void setTitleId(java.lang.Long titleId) {
		this.titleId = titleId;
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

	@Column(name = "IS_DELETED", length = 1)
	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public CTitleDTO toDTO() {
		CTitleDTO cTitleDTO = new CTitleDTO();
		// set cac gia tri
		cTitleDTO.setTitleId(this.titleId);
		cTitleDTO.setAdOrgId(this.adOrgId);
		cTitleDTO.setAdClientId(this.adClientId);
		cTitleDTO.setCreated(this.created);
		cTitleDTO.setCreatedby(this.createdby);
		cTitleDTO.setUpdated(this.updated);
		cTitleDTO.setUpdatedby(this.updatedby);
		cTitleDTO.setName(this.name);
		cTitleDTO.setValue(this.value);
		cTitleDTO.setDescription(this.description);
		cTitleDTO.setIsactive(this.isactive);
		cTitleDTO.setIsDeleted(this.isDeleted);
		return cTitleDTO;
	}
}
