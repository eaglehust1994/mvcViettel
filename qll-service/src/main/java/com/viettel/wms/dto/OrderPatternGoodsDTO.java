/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.OrderPatternBO;
import com.viettel.wms.bo.OrderPatternGoodsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDER_PATTERN_GOODSBO")
public class OrderPatternGoodsDTO extends wmsBaseDTO<OrderPatternGoodsBO> {

	private java.lang.Long orderPatternGoodsId;
	private java.lang.String goodsType;
	private java.lang.String goodsTypeName;
	private java.lang.Long goodsId;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsIsSerial;
	private java.lang.String goodsState;
	private java.lang.String goodsStateName;
	private java.lang.String goodsUnitName;
	private java.lang.Long goodsUnitId;
	private java.lang.Double amount;
	private java.lang.Long orderPatternId;
	private java.lang.Long fwmodelId;
	private java.lang.String code;
	private java.lang.String name;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;

	@Override
	public OrderPatternGoodsBO toModel() {
		OrderPatternGoodsBO orderPatternGoodsBO = new OrderPatternGoodsBO();
		orderPatternGoodsBO.setOrderPatternGoodsId(this.orderPatternGoodsId);
		orderPatternGoodsBO.setGoodsType(this.goodsType);
		orderPatternGoodsBO.setGoodsTypeName(this.goodsTypeName);
		orderPatternGoodsBO.setGoodsId(this.goodsId);
		orderPatternGoodsBO.setGoodsCode(this.goodsCode);
		orderPatternGoodsBO.setGoodsName(this.goodsName);
		orderPatternGoodsBO.setGoodsIsSerial(this.goodsIsSerial);
		orderPatternGoodsBO.setGoodsState(this.goodsState);
		orderPatternGoodsBO.setGoodsStateName(this.goodsStateName);
		orderPatternGoodsBO.setGoodsUnitName(this.goodsUnitName);
		orderPatternGoodsBO.setGoodsUnitId(this.goodsUnitId);
		orderPatternGoodsBO.setAmount(this.amount);
		orderPatternGoodsBO.setOrderPatternId(this.orderPatternId);
		return orderPatternGoodsBO;
	}

	public java.lang.Long getOrderPatternGoodsId() {
		return orderPatternGoodsId;
	}

	public void setOrderPatternGoodsId(java.lang.Long orderPatternGoodsId) {
		this.orderPatternGoodsId = orderPatternGoodsId;
	}

	public java.lang.String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(java.lang.String goodsType) {
		this.goodsType = goodsType;
	}

	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public java.lang.Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}

	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}

	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}

	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.Long getOrderPatternId() {
		return orderPatternId;
	}

	public void setOrderPatternId(java.lang.Long orderPatternId) {
		this.orderPatternId = orderPatternId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Override
	public Long getFWModelId() {
		return orderPatternId;
	}

	@Override
	public String catchName() {
		return getOrderPatternId().toString();
	}
}
