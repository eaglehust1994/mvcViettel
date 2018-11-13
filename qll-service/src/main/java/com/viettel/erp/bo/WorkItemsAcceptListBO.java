/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.WorkItemsAcceptListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WORK_ITEMS_ACCEPT_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class WorkItemsAcceptListBO extends BaseFWModelImpl {

	private java.lang.Long workItemsAcceptListId;
	private java.lang.Long workItemsAcceptanceId;
	private java.lang.Long estimatesWorkItemId;

	public WorkItemsAcceptListBO() {
		setColId("workItemsAcceptListId");
		setColName("workItemsAcceptListId");
		setUniqueColumn(new String[] { "workItemsAcceptListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "WORK_ITEMS_ACCEPT_LIST_SEQ") })
	@Column(name = "WORK_ITEMS_ACCEPT_LIST_ID", length = 22)
	public java.lang.Long getWorkItemsAcceptListId() {
		return workItemsAcceptListId;
	}

	public void setWorkItemsAcceptListId(java.lang.Long workItemsAcceptListId) {
		this.workItemsAcceptListId = workItemsAcceptListId;
	}

	@Column(name = "WORK_ITEMS_ACCEPTANCE_ID", length = 22)
	public java.lang.Long getWorkItemsAcceptanceId() {
		return workItemsAcceptanceId;
	}

	public void setWorkItemsAcceptanceId(java.lang.Long workItemsAcceptanceId) {
		this.workItemsAcceptanceId = workItemsAcceptanceId;
	}

	@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	@Override
	public WorkItemsAcceptListDTO toDTO() {
		WorkItemsAcceptListDTO workItemsAcceptListDTO = new WorkItemsAcceptListDTO();
		// set cac gia tri
		workItemsAcceptListDTO.setWorkItemsAcceptListId(this.workItemsAcceptListId);
		workItemsAcceptListDTO.setWorkItemsAcceptanceId(this.workItemsAcceptanceId);
		workItemsAcceptListDTO.setEstimatesWorkItemId(this.estimatesWorkItemId);
		return workItemsAcceptListDTO;
	}
}
