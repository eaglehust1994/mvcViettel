/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CurrentStateHandoverListDTO;
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

@Entity(name ="currentStateHandoverList")
@Table(name = "CURRENT_STATE_HANDOVER_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CurrentStateHandoverListBO extends BaseFWModelImpl {

	private java.lang.Long currentStateHandoverListId;
	private java.lang.String handoverContent;
	private java.lang.String unit;
	private java.lang.Double quantity;
	private java.lang.String currentState;
	private java.lang.Long constructionRequest;
	private java.lang.String notes;
	private java.lang.Long currentStateHandoverId;
	private CurrentStateHandoverBO currentStateHandoverBO;

	public CurrentStateHandoverListBO() {
		setColId("currentStateHandoverListId");
		setColName("currentStateHandoverListId");
		setUniqueColumn(new String[] { "currentStateHandoverListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CURRENT_ST_HANDOVER_LIST_SEQ") })
	@Column(name = "CURRENT_STATE_HANDOVER_LIST_ID", length = 22)
	public java.lang.Long getCurrentStateHandoverListId() {
		return currentStateHandoverListId;
	}

	public void setCurrentStateHandoverListId(java.lang.Long currentStateHandoverListId) {
		this.currentStateHandoverListId = currentStateHandoverListId;
	}

	@Column(name = "HANDOVER_CONTENT", length = 2000)
	public java.lang.String getHandoverContent() {
		return handoverContent;
	}

	public void setHandoverContent(java.lang.String handoverContent) {
		this.handoverContent = handoverContent;
	}

	@Column(name = "UNIT", length = 400)
	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	@Column(name = "QUANTITY", length = 22)
	public java.lang.Double getQuantity() {
		return quantity;
	}

	public void setQuantity(java.lang.Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "CURRENT_STATE", length = 2000)
	public java.lang.String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(java.lang.String currentState) {
		this.currentState = currentState;
	}

	@Column(name = "CONSTRUCTION_REQUEST", length = 22)
	public java.lang.Long getConstructionRequest() {
		return constructionRequest;
	}

	public void setConstructionRequest(java.lang.Long constructionRequest) {
		this.constructionRequest = constructionRequest;
	}

	@Column(name = "NOTES", length = 1000)
	public java.lang.String getNotes() {
		return notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENT_STATE_HANDOVER_ID")
	public CurrentStateHandoverBO getCurrentStateHandoverBO() {
		return currentStateHandoverBO;
	}

	public void setCurrentStateHandoverBO(CurrentStateHandoverBO currentStateHandoverBO) {
		this.currentStateHandoverBO = currentStateHandoverBO;
	}

	@Override
	public CurrentStateHandoverListDTO toDTO() {
		CurrentStateHandoverListDTO currentStateHandoverListDTO = new CurrentStateHandoverListDTO();
		// set cac gia tri
		currentStateHandoverListDTO.setCurrentStateHandoverListId(this.currentStateHandoverListId);
		currentStateHandoverListDTO.setHandoverContent(this.handoverContent);
		currentStateHandoverListDTO.setUnit(this.unit);
		currentStateHandoverListDTO.setQuantity(this.quantity);
		currentStateHandoverListDTO.setCurrentState(this.currentState);
		currentStateHandoverListDTO.setConstructionRequest(this.constructionRequest);
		currentStateHandoverListDTO.setNotes(this.notes);
		currentStateHandoverListDTO.setCurrentStateHandoverId(this.currentStateHandoverId);
		return currentStateHandoverListDTO;
	}
}
