/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.OrderChangeGoodsDetailBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDER_CHANGE_GOODS_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderChangeGoodsDetailDTO extends wmsBaseDTO<OrderChangeGoodsDetailBO> {

	private java.lang.Long orderChangeGoodsDetailId;
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
	private java.lang.Double amountChange;
	private java.lang.String newGoodsCode;
	private java.lang.String newGoodsName;
	private java.lang.Long newGoodsId;
	private java.lang.String newSerial;
	private java.lang.Long orderChangeGoodsId;
	private java.lang.String codeChange;
	private List<OrderGoodsExelDTO> lstErrorOrderGoods;
	private java.lang.String serial;
	
	private java.lang.String filePathError;

	
	public java.lang.String getCodeChange() {
		return codeChange;
	}

	public void setCodeChange(java.lang.String codeChange) {
		this.codeChange = codeChange;
	}

	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
		return lstErrorOrderGoods;
	}

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
		this.lstErrorOrderGoods = lstErrorOrderGoods;
	}
	

	@Override
	public OrderChangeGoodsDetailBO toModel() {
		OrderChangeGoodsDetailBO orderChangeGoodsDetailBO = new OrderChangeGoodsDetailBO();
		orderChangeGoodsDetailBO.setOrderChangeGoodsDetailId(this.orderChangeGoodsDetailId);
		orderChangeGoodsDetailBO.setGoodsType(this.goodsType);
		orderChangeGoodsDetailBO.setGoodsTypeName(this.goodsTypeName);
		orderChangeGoodsDetailBO.setGoodsId(this.goodsId);
		orderChangeGoodsDetailBO.setGoodsCode(this.goodsCode);
		orderChangeGoodsDetailBO.setGoodsName(this.goodsName);
		orderChangeGoodsDetailBO.setGoodsIsSerial(this.goodsIsSerial);
		orderChangeGoodsDetailBO.setGoodsState(this.goodsState);
		orderChangeGoodsDetailBO.setGoodsStateName(this.goodsStateName);
		orderChangeGoodsDetailBO.setGoodsUnitName(this.goodsUnitName);
		orderChangeGoodsDetailBO.setGoodsUnitId(this.goodsUnitId);
		orderChangeGoodsDetailBO.setAmountChange(this.amountChange);
		orderChangeGoodsDetailBO.setNewGoodsCode(this.newGoodsCode);
		orderChangeGoodsDetailBO.setNewGoodsName(this.newGoodsName);
		orderChangeGoodsDetailBO.setNewGoodsId(this.newGoodsId);
		orderChangeGoodsDetailBO.setNewSerial(this.newSerial);
		orderChangeGoodsDetailBO.setOrderChangeGoodsId(this.orderChangeGoodsId);
		orderChangeGoodsDetailBO.setSerial(this.serial);
		return orderChangeGoodsDetailBO;
	}

	public java.lang.Long getOrderChangeGoodsDetailId() {
		return orderChangeGoodsDetailId;
	}

	public void setOrderChangeGoodsDetailId(java.lang.Long orderChangeGoodsDetailId) {
		this.orderChangeGoodsDetailId = orderChangeGoodsDetailId;
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
			this.goodsCode =org.apache.commons.lang3.StringUtils.isNotEmpty(goodsCode) ? goodsCode.toUpperCase():null;
		
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

	public java.lang.Double getAmountChange() {
		return amountChange;
	}

	public void setAmountChange(java.lang.Double amountChange) {
		this.amountChange = amountChange;
	}

	public java.lang.String getNewGoodsCode() {
		return newGoodsCode;
	}

	public void setNewGoodsCode(java.lang.String newGoodsCode) {
			this.newGoodsCode = org.apache.commons.lang3.StringUtils.isNotEmpty(newGoodsCode) ? newGoodsCode.toUpperCase():null;
		
	}

	public java.lang.String getNewGoodsName() {
		return newGoodsName;
	}

	public void setNewGoodsName(java.lang.String newGoodsName) {
		this.newGoodsName = newGoodsName;
	}

	public java.lang.Long getNewGoodsId() {
		return newGoodsId;
	}

	public void setNewGoodsId(java.lang.Long newGoodsId) {
		this.newGoodsId = newGoodsId;
	}

	public java.lang.String getNewSerial() {
		return newSerial;
	}

	public void setNewSerial(java.lang.String newSerial) {
		if(newSerial!=null){
			this.newSerial = newSerial;
		}else{
			this.newSerial = null;
		}
		
	}

	public java.lang.Long getOrderChangeGoodsId() {
		return orderChangeGoodsId;
	}

	public void setOrderChangeGoodsId(java.lang.Long orderChangeGoodsId) {
		this.orderChangeGoodsId = orderChangeGoodsId;
	}

	@Override
	public Long getFWModelId() {
		return orderChangeGoodsId;
	}

	@Override
	public String catchName() {
		return getOrderChangeGoodsId().toString();
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		if(serial!=null){
			this.serial = serial;
		}else{
			this.serial = null;
		}
		
	}

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}
	
	
}
