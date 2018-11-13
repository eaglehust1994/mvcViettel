/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.OrderChangeGoodsBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDER_CHANGE_GOODSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderChangeGoodsDTO extends wmsBaseDTO<OrderChangeGoodsBO> {

	private java.lang.Long orderChangeGoodsId;
	private java.lang.String code;
	private java.lang.Long stockId;
	private java.lang.String status;
	private java.lang.String description;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDate;
	private java.lang.Long createdBy;
	private java.lang.String signState;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date cancelDate;
	private java.lang.Long cancelUserId;
	private java.lang.String cancelReasonName;
	private java.lang.String cancelDescription;
	private java.lang.Long createdDeptId;
	private java.lang.String createdDeptName;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date updatedDate;
	private java.lang.String stockCode;
	private OrderChangeGoodsDetailDTO orderChangeGoodsDetailDTO;
	private List<OrderChangeGoodsDetailDTO> listorderChangeGoodsDetailDTO;
	private java.lang.String goodsCode;
	private java.lang.Double amountChange;
	private java.lang.String goodsUnitName;
	private java.lang.String newGoodsCode;
	private java.lang.String newSerial;
	private java.lang.String goodsStateName;
	private java.lang.String serial;
	private java.lang.String fullName;
	private java.lang.String sysUserId;
	
	
	

	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = org.apache.commons.lang3.StringUtils.isNotEmpty(stockCode) ? stockCode.toUpperCase():null;
	}

	public List<OrderChangeGoodsDetailDTO> listorderChangeGoodsDetailDTO() {
		return listorderChangeGoodsDetailDTO;
	}

	public void setListDetail(List<OrderChangeGoodsDetailDTO> listorderChangeGoodsDetailDTO) {
		this.listorderChangeGoodsDetailDTO = listorderChangeGoodsDetailDTO;
	}

	public OrderChangeGoodsDetailDTO getOrderChangeGoodsDetailDTO() {
		return orderChangeGoodsDetailDTO;
	}

	public void setOrderChangeGoodsDetailDTO(OrderChangeGoodsDetailDTO orderChangeGoodsDetailDTO) {
		this.orderChangeGoodsDetailDTO = orderChangeGoodsDetailDTO;
	}

	public List<OrderChangeGoodsDetailDTO> getListorderChangeGoodsDetailDTO() {
		return listorderChangeGoodsDetailDTO;
	}

	public void setListorderChangeGoodsDetailDTO(List<OrderChangeGoodsDetailDTO> listorderChangeGoodsDetailDTO) {
		this.listorderChangeGoodsDetailDTO = listorderChangeGoodsDetailDTO;
	}

	public void setCreatedBy(java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	public java.lang.Long getCreatedDeptId() {
		return createdDeptId;
	}

	public void setCreatedDeptId(java.lang.Long createdDeptId) {
		this.createdDeptId = createdDeptId;
	}

	public java.lang.String getCreatedDeptName() {
		return createdDeptName;
	}

	public void setCreatedDeptName(java.lang.String createdDeptName) {
		this.createdDeptName = createdDeptName;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.lang.Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(java.lang.Long updateBy) {
		this.updateBy = updateBy;
	}

	private java.lang.Long updateBy;
	private List<String> listStatus;
	private List<String> listStockId;

	@Override
	public OrderChangeGoodsBO toModel() {
		OrderChangeGoodsBO orderChangeGoodsBO = new OrderChangeGoodsBO();
		orderChangeGoodsBO.setOrderChangeGoodsId(this.orderChangeGoodsId);
		orderChangeGoodsBO.setCode(this.code);
		orderChangeGoodsBO.setStockId(this.stockId);
		orderChangeGoodsBO.setStatus(this.status);
		orderChangeGoodsBO.setDescription(this.description);
		orderChangeGoodsBO.setCreatedDate(this.createdDate);
		orderChangeGoodsBO.setCreatedBy(this.createdBy);
		orderChangeGoodsBO.setSignState(this.signState);
		orderChangeGoodsBO.setCancelDate(this.cancelDate);
		orderChangeGoodsBO.setCancelUserId(this.cancelUserId);
		orderChangeGoodsBO.setCancelReasonName(this.cancelReasonName);
		orderChangeGoodsBO.setCancelDescription(this.cancelDescription);
		orderChangeGoodsBO.setUpdateBy(this.updateBy);
		orderChangeGoodsBO.setUpdatedDate(this.updatedDate);
		orderChangeGoodsBO.setCreatedDeptId(this.createdDeptId);
		orderChangeGoodsBO.setCreatedDeptName(this.createdDeptName);

		return orderChangeGoodsBO;
	}

	@Override
	public Long getFWModelId() {
		return orderChangeGoodsId;
	}

	@Override
	public String catchName() {
		return getOrderChangeGoodsId().toString();
	}

	public java.lang.Long getOrderChangeGoodsId() {
		return orderChangeGoodsId;
	}

	public void setOrderChangeGoodsId(java.lang.Long orderChangeGoodsId) {
		this.orderChangeGoodsId = orderChangeGoodsId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
	}

	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
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

	public java.lang.String getSignState() {
		return signState;
	}

	public void setSignState(java.lang.String signState) {
		this.signState = signState;
	}

	public java.util.Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public java.lang.Long getCancelUserId() {
		return cancelUserId;
	}

	public void setCancelUserId(java.lang.Long cancelUserId) {
		this.cancelUserId = cancelUserId;
	}

	public java.lang.String getCancelReasonName() {
		return cancelReasonName;
	}

	public void setCancelReasonName(java.lang.String cancelReasonName) {
		this.cancelReasonName = cancelReasonName;
	}

	public java.lang.String getCancelDescription() {
		return cancelDescription;
	}

	public void setCancelDescription(java.lang.String cancelDescription) {
		this.cancelDescription = cancelDescription;
	}

	private java.lang.String goodsName;

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDateFrom;

	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date createdDateTo;

	public java.util.Date getCreatedDateFrom() {
		return createdDateFrom;
	}

	public void setCreatedDateFrom(java.util.Date createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	public java.util.Date getCreatedDateTo() {
		return createdDateTo;
	}

	public void setCreatedDateTo(java.util.Date createdDateTo) {
		this.createdDateTo = createdDateTo;
	}

	public List<String> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}

	private java.lang.String stockName;

	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	private java.lang.String createdName;

	public java.lang.String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(java.lang.String createdName) {
		this.createdName = createdName;
	}

	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public java.lang.Double getAmountChange() {
		return amountChange;
	}

	public void setAmountChange(java.lang.Double amountChange) {
		this.amountChange = amountChange;
	}

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public java.lang.String getNewGoodsCode() {
		return newGoodsCode;
	}

	public void setNewGoodsCode(java.lang.String newGoodsCode) {
		this.newGoodsCode = newGoodsCode;
	}

	public java.lang.String getNewSerial() {
		return newSerial;
	}

	public void setNewSerial(java.lang.String newSerial) {
		this.newSerial = newSerial;
	}

	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getSysUserId() {
		return String.valueOf(createdBy);
	}

	public List<String> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<String> listStockId) {
		this.listStockId = listStockId;
	}
	
	

}
