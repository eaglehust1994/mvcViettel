/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.OrderBO;
import com.viettel.wms.bo.OrderGoodsDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDER_GOODS_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderGoodsDetailDTO extends wmsBaseDTO<OrderGoodsDetailBO> {

	private java.lang.Long orderGoodsDetailId;
	private java.lang.String serial;
	private java.lang.String contractCode;
	private java.lang.String partNumber;
	private java.lang.Long manufacturerId;
	private java.lang.Long producingCountryId;
	private java.lang.String manufacturerName;
	private java.lang.String producingCountryName;
	private java.lang.Double price;
	private java.lang.Long orderGoodsId;
	private java.lang.Long orderId;
	private java.lang.String goodsIsSerial;
	private java.lang.String isSerial;
	private String codePosition; 
	private List<OrderGoodsExelDTO> lstErrorOrderGoods;
	
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String cellCode;
	private java.lang.Double amount;
	
	private java.lang.String filePathError;
	private java.lang.Long goodsId;

	public java.lang.Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	@Override
	public OrderGoodsDetailBO toModel() {
		OrderGoodsDetailBO orderGoodsDetailBO = new OrderGoodsDetailBO();
		orderGoodsDetailBO.setOrderGoodsDetailId(this.orderGoodsDetailId);
		orderGoodsDetailBO.setSerial(this.serial);
		orderGoodsDetailBO.setContractCode(this.contractCode);
		orderGoodsDetailBO.setPartNumber(this.partNumber);
		orderGoodsDetailBO.setManufacturerId(this.manufacturerId);
		orderGoodsDetailBO.setProducingCountryId(this.producingCountryId);
		orderGoodsDetailBO.setManufacturerName(this.manufacturerName);
		orderGoodsDetailBO.setProducingCountryName(this.producingCountryName);
		orderGoodsDetailBO.setPrice(this.price);
		orderGoodsDetailBO.setOrderGoodsId(this.orderGoodsId);
		orderGoodsDetailBO.setOrderId(this.orderId);
		return orderGoodsDetailBO;
	}

	public java.lang.Long getOrderGoodsDetailId() {
		return orderGoodsDetailId;
	}

	public void setOrderGoodsDetailId(java.lang.Long orderGoodsDetailId) {
		this.orderGoodsDetailId = orderGoodsDetailId;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial =  org.apache.commons.lang3.StringUtils.isNotEmpty(serial) ? serial.toUpperCase():null;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(java.lang.String partNumber) {
		this.partNumber = partNumber;
	}

	public java.lang.Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(java.lang.Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public java.lang.Long getProducingCountryId() {
		return producingCountryId;
	}

	public void setProducingCountryId(java.lang.Long producingCountryId) {
		this.producingCountryId = producingCountryId;
	}

	public java.lang.String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(java.lang.String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public java.lang.String getProducingCountryName() {
		return producingCountryName;
	}

	public void setProducingCountryName(java.lang.String producingCountryName) {
		this.producingCountryName = producingCountryName;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public java.lang.Long getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(java.lang.Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public java.lang.Long getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Long getFWModelId() {
		return orderId;
	}

	@Override
	public String catchName() {
		return getOrderId().toString();
	}

	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}

	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}


	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
		return lstErrorOrderGoods;
	}

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
		this.lstErrorOrderGoods = lstErrorOrderGoods;
	}

	public String getCodePosition() {
		return codePosition;
	}

	public void setCodePosition(String codePosition) {
		this.codePosition = codePosition;
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

	public java.lang.String getCellCode() {
		return cellCode;
	}

	public void setCellCode(java.lang.String cellCode) {
		this.cellCode = cellCode;
	}

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}

	public java.lang.String getIsSerial() {
		return isSerial;
	}

	public void setIsSerial(java.lang.String isSerial) {
		this.isSerial = isSerial;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}
	
}
