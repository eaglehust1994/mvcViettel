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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.service.base.model.BaseFWModelImpl;
import com.viettel.wms.dto.OrderChangeGoodsDTO;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_CHANGE_GOODS")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderChangeGoodsBO extends BaseFWModelImpl {
	private java.lang.Long orderChangeGoodsId;
	private java.lang.String code;
	private java.lang.Long stockId;
	private java.lang.String status;
	private java.lang.String description;
	private java.util.Date createdDate;
	private java.lang.Long createdBy;
	private java.lang.String signState;
	private java.util.Date cancelDate;
	private java.lang.Long cancelUserId;
	private java.lang.String cancelReasonName;
	private java.lang.String cancelDescription;
	private java.lang.Long createdDeptId;
	private java.lang.String createdDeptName;
	private java.util.Date updatedDate;
	private java.lang.Long updateBy;

	public OrderChangeGoodsBO() {
		setColId("orderChangeGoodsId");
		setColName("orderChangeGoodsId");
		setUniqueColumn(new String[] { "orderChangeGoodsId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_SEQ") })
	@Column(name = "ORDER_CHANGE_GOODS_ID", length = 22)
	public java.lang.Long getOrderChangeGoodsId() {
		return orderChangeGoodsId;
	}

	public void setOrderChangeGoodsId(java.lang.Long orderChangeGoodsId) {
		this.orderChangeGoodsId = orderChangeGoodsId;
	}

	@Column(name = "CODE", length = 200)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "STOCK_ID", length = 22)
	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	@Column(name = "STATUS", length = 4)
	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY", length = 22)
	public java.lang.Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "SIGN_STATE", length = 4)
	public java.lang.String getSignState() {
		return signState;
	}

	public void setSignState(java.lang.String signState) {
		this.signState = signState;
	}

	@Column(name = "CANCEL_DATE", length = 7)
	public java.util.Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	@Column(name = "CANCEL_USER_ID", length = 22)
	public java.lang.Long getCancelUserId() {
		return cancelUserId;
	}

	public void setCancelUserId(java.lang.Long cancelUserId) {
		this.cancelUserId = cancelUserId;
	}

	@Column(name = "CANCEL_REASON_NAME", length = 22)
	public java.lang.String getCancelReasonName() {
		return cancelReasonName;
	}

	public void setCancelReasonName(java.lang.String cancelReasonName) {
		this.cancelReasonName = cancelReasonName;
	}

	@Column(name = "CANCEL_DESCRIPTION", length = 2000)
	public java.lang.String getCancelDescription() {
		return cancelDescription;
	}

	public void setCancelDescription(java.lang.String cancelDescription) {
		this.cancelDescription = cancelDescription;
	}

	@Column(name = "CREATED_DEPT_ID", length = 22)
	public java.lang.Long getCreatedDeptId() {
		return createdDeptId;
	}

	public void setCreatedDeptId(java.lang.Long createdDeptId) {
		this.createdDeptId = createdDeptId;
	}

	@Column(name = "CREATED_DEPT_NAME", length = 22)
	public java.lang.String getCreatedDeptName() {
		return createdDeptName;
	}

	public void setCreatedDeptName(java.lang.String createdDeptName) {
		this.createdDeptName = createdDeptName;
	}

	@Column(name = "UPDATED_DATE", length = 22)
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "UPDATED_BY", length = 22)
	public java.lang.Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(java.lang.Long updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public OrderChangeGoodsDTO toDTO() {
		OrderChangeGoodsDTO orderChangeGoodsDTO = new OrderChangeGoodsDTO();
		// set cac gia tri
		orderChangeGoodsDTO.setOrderChangeGoodsId(this.orderChangeGoodsId);
		orderChangeGoodsDTO.setCode(this.code);
		orderChangeGoodsDTO.setStockId(this.stockId);
		orderChangeGoodsDTO.setStatus(this.status);
		orderChangeGoodsDTO.setDescription(this.description);
		orderChangeGoodsDTO.setCreatedDate(this.createdDate);
		orderChangeGoodsDTO.setCreatedBy(this.createdBy);
		orderChangeGoodsDTO.setSignState(this.signState);
		orderChangeGoodsDTO.setCancelDate(this.cancelDate);
		orderChangeGoodsDTO.setCancelUserId(this.cancelUserId);
		orderChangeGoodsDTO.setCancelReasonName(this.cancelReasonName);
		orderChangeGoodsDTO.setCancelDescription(this.cancelDescription);
		orderChangeGoodsDTO.setUpdateBy(this.updateBy);
		orderChangeGoodsDTO.setUpdatedDate(this.updatedDate);
		orderChangeGoodsDTO.setCreatedDeptId(this.createdDeptId);
		orderChangeGoodsDTO.setCreatedDeptName(this.createdDeptName);
		return orderChangeGoodsDTO;
	}
}
