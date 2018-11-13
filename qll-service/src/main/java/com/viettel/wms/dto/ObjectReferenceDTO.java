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

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.ObjectReferenceBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "OBJECT_REFERENCEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectReferenceDTO extends wmsBaseDTO<ObjectReferenceBO> {

	private java.lang.Long objectReferenceId;
	private java.lang.Long parentId;
	private java.lang.Long objectId;
	private java.lang.String type;
	private java.lang.String objectCode;
	private java.lang.String objectName;
	private java.lang.String objectDescription;
	private java.lang.String objectStatus;
	private java.lang.String objectCreatedDate;
	private java.lang.String objectCreator;

	@Override
	public ObjectReferenceBO toModel() {
		ObjectReferenceBO objectReferenceBO = new ObjectReferenceBO();
		objectReferenceBO.setObjectReferenceId(this.objectReferenceId);
		objectReferenceBO.setParentId(this.parentId);
		objectReferenceBO.setObjectId(this.objectId);
		objectReferenceBO.setType(this.type);
		objectReferenceBO.setObjectCode(this.objectCode);
		objectReferenceBO.setObjectName(this.objectName);
		objectReferenceBO.setObjectDescription(this.objectDescription);
		objectReferenceBO.setObjectStatus(this.objectStatus);
		objectReferenceBO.setObjectCreatedDate(this.objectCreatedDate);
		objectReferenceBO.setObjectCreator(this.objectCreator);
		return objectReferenceBO;
	}

	@Override
	public Long getFWModelId() {
		return objectReferenceId;
	}

	@Override
	public String catchName() {
		return getObjectReferenceId().toString();
	}

	public java.lang.Long getObjectReferenceId() {
		return objectReferenceId;
	}

	public void setObjectReferenceId(java.lang.Long objectReferenceId) {
		this.objectReferenceId = objectReferenceId;
	}

	public java.lang.Long getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	public java.lang.Long getObjectId() {
		return objectId;
	}

	public void setObjectId(java.lang.Long objectId) {
		this.objectId = objectId;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(java.lang.String objectCode) {
		this.objectCode = objectCode;
	}

	public java.lang.String getObjectName() {
		return objectName;
	}

	public void setObjectName(java.lang.String objectName) {
		this.objectName = objectName;
	}

	public java.lang.String getObjectDescription() {
		return objectDescription;
	}

	public void setObjectDescription(java.lang.String objectDescription) {
		this.objectDescription = objectDescription;
	}

	public java.lang.String getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(java.lang.String objectStatus) {
		this.objectStatus = objectStatus;
	}

	public java.lang.String getObjectCreatedDate() {
		return objectCreatedDate;
	}

	public void setObjectCreatedDate(java.lang.String objectCreatedDate) {
		this.objectCreatedDate = objectCreatedDate;
	}

	public java.lang.String getObjectCreator() {
		return objectCreator;
	}

	public void setObjectCreator(java.lang.String objectCreator) {
		this.objectCreator = objectCreator;
	}

}
