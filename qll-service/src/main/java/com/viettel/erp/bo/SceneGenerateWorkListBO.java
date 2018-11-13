/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.SceneGenerateWorkListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SCENE_GENERATE_WORK_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class SceneGenerateWorkListBO extends BaseFWModelImpl {

	/**
		 * 
		 */
	private static final long serialVersionUID = -2556002148044971440L;
	private java.lang.Long sceneGenWorkListId;
	private java.lang.String incurredContent;
	private java.lang.String unit;
	private java.lang.Double incurredQuantity;
	private java.lang.String note;
	private java.lang.Long workItemType;
	private java.lang.Long sceneGenerateWorkId;
	private java.lang.Long estimatesItemChildId;
	private java.lang.Long status;
	private SceneGenerateWorkBO sceneGenerateWorkBO;
	private java.lang.Long type;
	private java.lang.Double designQuantity;

	/**
	 * @return the sceneGenerateWorkBO
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENE_GENERATE_WORK_ID")
	public SceneGenerateWorkBO getSceneGenerateWorkBO() {
		return sceneGenerateWorkBO;
	}

	/**
	 * @param sceneGenerateWorkBO
	 *            the sceneGenerateWorkBO to set
	 */
	public void setSceneGenerateWorkBO(SceneGenerateWorkBO sceneGenerateWorkBO) {
		this.sceneGenerateWorkBO = sceneGenerateWorkBO;
	}

	public SceneGenerateWorkListBO() {
		setColId("sceneGenWorkListId");
		setColName("sceneGenWorkListId");
		setUniqueColumn(new String[] { "sceneGenWorkListId" });
	}

	public SceneGenerateWorkListBO(Long sceneGenWorkListId) {
		this.sceneGenWorkListId = sceneGenWorkListId;
	}

	@Column(name = "TYPE", length = 22)
	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	@Column(name = "DESIGN_QUANTITY", length = 22)
	public java.lang.Double getDesignQuantity() {
		return designQuantity;
	}

	public void setDesignQuantity(java.lang.Double designQuantity) {
		this.designQuantity = designQuantity;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "SCENE_GENERATE_WORK_LIST_SEQ") })
	@Column(name = "SCENE_GEN_WORK_LIST_ID", length = 22)
	public java.lang.Long getSceneGenWorkListId() {
		return sceneGenWorkListId;
	}

	public void setSceneGenWorkListId(java.lang.Long sceneGenWorkListId) {
		this.sceneGenWorkListId = sceneGenWorkListId;
	}

	@Column(name = "INCURRED_CONTENT", length = 400)
	public java.lang.String getIncurredContent() {
		return incurredContent;
	}

	public void setIncurredContent(java.lang.String incurredContent) {
		this.incurredContent = incurredContent;
	}

	@Column(name = "UNIT", length = 400)
	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	@Column(name = "INCURRED_QUANTITY", length = 22)
	public java.lang.Double getIncurredQuantity() {
		return incurredQuantity;
	}

	public void setIncurredQuantity(java.lang.Double incurredQuantity) {
		this.incurredQuantity = incurredQuantity;
	}

	@Column(name = "NOTE", length = 1000)
	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	@Column(name = "WORK_ITEM_TYPE", length = 22)
	public java.lang.Long getWorkItemType() {
		return workItemType;
	}

	public void setWorkItemType(java.lang.Long workItemType) {
		this.workItemType = workItemType;
	}

	// @Column(name = "SCENE_GENERATE_WORK_ID", length = 22)
	// public java.lang.Long getSceneGenerateWorkId(){
	// return sceneGenerateWorkId;
	// }
	// public void setSceneGenerateWorkId(java.lang.Long sceneGenerateWorkId)
	// {
	// this.sceneGenerateWorkId = sceneGenerateWorkId;
	// }
	@Column(name = "ESTIMATES_ITEM_CHILD_ID", length = 22)
	public java.lang.Long getEstimatesItemChildId() {
		return estimatesItemChildId;
	}

	public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId) {
		this.estimatesItemChildId = estimatesItemChildId;
	}

	@Column(name = "STATUS", length = 22)
	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	@Override
	public SceneGenerateWorkListDTO toDTO() {
		SceneGenerateWorkListDTO sceneGenerateWorkListDTO = new SceneGenerateWorkListDTO();
		// set cac gia tri
		sceneGenerateWorkListDTO.setType(this.type);
        sceneGenerateWorkListDTO.setDesignQuantity(this.designQuantity);
		sceneGenerateWorkListDTO.setSceneGenWorkListId(this.sceneGenWorkListId);
		sceneGenerateWorkListDTO.setIncurredContent(this.incurredContent);
		sceneGenerateWorkListDTO.setUnit(this.unit);
		sceneGenerateWorkListDTO.setIncurredQuantity(this.incurredQuantity);
		sceneGenerateWorkListDTO.setNote(this.note);
		sceneGenerateWorkListDTO.setWorkItemType(this.workItemType);
		sceneGenerateWorkListDTO.setSceneGenerateWorkId(this.sceneGenerateWorkId);
		sceneGenerateWorkListDTO.setEstimatesItemChildId(this.estimatesItemChildId);
		sceneGenerateWorkListDTO.setStatus(this.status);
		return sceneGenerateWorkListDTO;
	}
}
