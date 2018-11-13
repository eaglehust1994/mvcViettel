/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.SceneGenerateWorkBO;
import com.viettel.erp.bo.SceneGenerateWorkListBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "SCENE_GENERATE_WORK_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SceneGenerateWorkListDTO extends BaseFWDTOImpl<SceneGenerateWorkListBO> {

	private java.lang.Long sceneGenWorkListId;
	private java.lang.Long type;
	private java.lang.Double designQuantity;
	private java.lang.String incurredContent;
	private java.lang.String unit;
	private java.lang.Double incurredQuantity;
	private java.lang.String note;
	private java.lang.Long workItemType;
	private java.lang.Long sceneGenerateWorkId;
	private java.lang.Long estimatesItemChildId;
	private java.lang.Long status;
	private java.lang.Long typeEstimateWork;
	private java.lang.Integer stt;
	private java.lang.Long estimatesWorkItemId;
	
	
	DecimalFormat df = new DecimalFormat("###.###");
	private java.lang.String valueUp;
	private java.lang.String valueDown;
	
	
	public java.lang.String getValueUp() {
		if(designQuantity!=null && incurredQuantity !=null){
		if(incurredQuantity >= designQuantity){
			valueUp = String.valueOf(df.format(incurredQuantity - designQuantity));
		}else{
			valueUp = "";
		    }
		}
		return valueUp;
	}

	public void setValueUp(java.lang.String valueUp) {
		this.valueUp = valueUp;
	}

	public java.lang.String getValueDown() {

		if(designQuantity!=null && incurredQuantity !=null){
		if(designQuantity >= incurredQuantity){
			valueDown = String.valueOf(df.format(designQuantity - incurredQuantity));
		}else{
			valueDown = "";
		    }
		}
		return valueDown;
	
	}

	public void setValueDown(java.lang.String valueDown) {
		this.valueDown = valueDown;
	}

	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public java.lang.Long getTypeEstimateWork() {
		return typeEstimateWork;
	}

	public void setTypeEstimateWork(java.lang.Long typeEstimateWork) {
		this.typeEstimateWork = typeEstimateWork;
	}

	/**
	 * @return the status
	 */
	public java.lang.Long getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	/**
	 * @return the stt
	 */
	public java.lang.Integer getStt() {
		return stt;
	}

	/**
	 * @param stt
	 *            the stt to set
	 */
	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	// Dữ liệu lấy từ bảng khác
	private String itemName;
	private Long constructId;

	// End
	@Override
	public SceneGenerateWorkListBO toModel() {
		SceneGenerateWorkListBO sceneGenerateWorkListBO = new SceneGenerateWorkListBO();
		sceneGenerateWorkListBO.setSceneGenWorkListId(this.sceneGenWorkListId);
		sceneGenerateWorkListBO.setIncurredContent(this.incurredContent);
		sceneGenerateWorkListBO.setType(this.type);
		sceneGenerateWorkListBO.setDesignQuantity(this.designQuantity);
		sceneGenerateWorkListBO.setUnit(this.unit);
		sceneGenerateWorkListBO.setIncurredQuantity(this.incurredQuantity);
		sceneGenerateWorkListBO.setNote(this.note);
		sceneGenerateWorkListBO.setWorkItemType(this.workItemType);
		// sceneGenerateWorkListBO.setSceneGenerateWorkId(this.sceneGenerateWorkId);
		sceneGenerateWorkListBO.setEstimatesItemChildId(this.estimatesItemChildId);
		sceneGenerateWorkListBO.setStatus(this.status);
		sceneGenerateWorkListBO.setSceneGenerateWorkBO(
				this.sceneGenerateWorkId == null ? null : new SceneGenerateWorkBO(this.sceneGenerateWorkId));
		return sceneGenerateWorkListBO;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.Double getDesignQuantity() {
		if(designQuantity==null){
			return 0d;
		}
		return designQuantity;
	}

	public void setDesignQuantity(java.lang.Double designQuantity) {
		this.designQuantity = designQuantity;
	}

	@Override
	public Long getFWModelId() {
		return sceneGenWorkListId;
	}

	@Override
	public String catchName() {
		return getSceneGenWorkListId().toString();
	}

	public java.lang.Long getSceneGenWorkListId() {
		return sceneGenWorkListId;
	}

	public void setSceneGenWorkListId(java.lang.Long sceneGenWorkListId) {
		this.sceneGenWorkListId = sceneGenWorkListId;
	}

	public java.lang.String getIncurredContent() {
		return Strings.nullToEmpty(incurredContent);
	}

	public void setIncurredContent(java.lang.String incurredContent) {
		this.incurredContent = incurredContent;
	}

	public java.lang.String getUnit() {
		return Strings.nullToEmpty(unit);
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.Double getIncurredQuantity() {
		if(incurredQuantity==null){
			return 0d;
		}
		return incurredQuantity;
	}

	public void setIncurredQuantity(java.lang.Double incurredQuantity) {
		this.incurredQuantity = incurredQuantity;
	}

	public java.lang.String getNote() {
		return Strings.nullToEmpty(note);
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.Long getWorkItemType() {
		return workItemType;
	}

	public void setWorkItemType(java.lang.Long workItemType) {
		this.workItemType = workItemType;
	}

	public java.lang.Long getSceneGenerateWorkId() {
		return sceneGenerateWorkId;
	}

	public void setSceneGenerateWorkId(java.lang.Long sceneGenerateWorkId) {
		this.sceneGenerateWorkId = sceneGenerateWorkId;
	}

	public java.lang.Long getEstimatesItemChildId() {
		if(estimatesItemChildId ==null){
			return 0l;
		}
		return estimatesItemChildId;
	}

	public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId) {
		this.estimatesItemChildId = estimatesItemChildId;
	}

	// dữ liệu lấy từ bảng khác
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}
	// End
}
