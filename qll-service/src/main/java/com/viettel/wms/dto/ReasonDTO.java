/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.ReasonBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "REASONBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReasonDTO extends wmsBaseDTO<ReasonBO> {

	private java.lang.Long updatedBy;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date updatedDate;
	private java.lang.Long reasonId;
	private java.lang.String code;
	private java.lang.String apply;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDate;
	private java.lang.Long createdBy;
	private java.lang.String status;
	private java.lang.String name;
	private List<String> listApply;
	private java.lang.String nameApply;

	@Override
	public ReasonBO toModel() {
		ReasonBO reasonBO = new ReasonBO();
		reasonBO.setUpdatedBy(this.updatedBy);
		reasonBO.setUpdatedDate(this.updatedDate);
		reasonBO.setReasonId(this.reasonId);
		reasonBO.setCode(this.code);
		reasonBO.setApply(this.apply);
		reasonBO.setCreatedDate(this.createdDate);
		reasonBO.setCreatedBy(this.createdBy);
		reasonBO.setStatus(this.status);
		reasonBO.setName(this.name);
		return reasonBO;
	}

	public java.lang.Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public Long getFWModelId() {
		return reasonId;
	}

	@Override
	public String catchName() {
		return getReasonId().toString();
	}

	public java.lang.Long getReasonId() {
		return reasonId;
	}

	public void setReasonId(java.lang.Long reasonId) {
		this.reasonId = reasonId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
	}

	public java.lang.String getApply() {
		return apply;
	}

	public void setApply(java.lang.String apply) {
		this.apply = apply;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public List<String> getListApply() {
		return listApply;
	}

	public void setListApply(List<String> listApply) {
		this.listApply = listApply;
	}

	public java.lang.String getNameApply() {
		return nameApply;
	}

	public void setNameApply(java.lang.String nameApply) {

			this.nameApply = org.apache.commons.lang3.StringUtils.isNotEmpty(nameApply) ? nameApply.toUpperCase():null;
		
	}
	
}
