/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.AppParamBO1;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "APP_PARAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppParamDTO1 extends wmsBaseDTO<AppParamBO1> {
	
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date updatedDate;
	private java.lang.Long updatedBy;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDate;
	private java.lang.Long createdBy;
	private java.lang.String description;
	private java.lang.Long appParamId;
	private java.lang.String parOrder;
	private java.lang.String parType;
	private java.lang.String name;
	private java.lang.String code;
	private java.lang.String status;

	@Override
	public AppParamBO1 toModel() {
		AppParamBO1 appParamBO = new AppParamBO1();
		appParamBO.setUpdatedDate(this.updatedDate);
		appParamBO.setUpdatedBy(this.updatedBy);
		appParamBO.setCreatedDate(this.createdDate);
		appParamBO.setCreatedBy(this.createdBy);
		appParamBO.setDescription(this.description);
		appParamBO.setAppParamId(this.appParamId);
		appParamBO.setParOrder(this.parOrder);
		appParamBO.setParType(this.parType);
		appParamBO.setName(this.name);
		appParamBO.setCode(this.code);
		appParamBO.setStatus(this.status);
		return appParamBO;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.lang.Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.Long updatedBy) {
		this.updatedBy = updatedBy;
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

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Override
	public Long getFWModelId() {
		return appParamId;
	}

	@Override
	public String catchName() {
		return getAppParamId().toString();
	}

	public java.lang.Long getAppParamId() {
		return appParamId;
	}

	public void setAppParamId(java.lang.Long appParamId) {
		this.appParamId = appParamId;
	}

	public java.lang.String getParOrder() {
		return parOrder;
	}

	public void setParOrder(java.lang.String parOrder) {
		this.parOrder = parOrder;
	}

	public java.lang.String getParType() {
		return parType;
	}

	public void setParType(java.lang.String parType) {
		this.parType = org.apache.commons.lang3.StringUtils.isNotEmpty(parType) ? parType.toUpperCase():null;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

}
