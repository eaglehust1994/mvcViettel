/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.google.common.collect.Lists;
import com.viettel.wms.bo.OrderBO;
import com.viettel.wms.bo.OrderGoodsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ORDER_GOODSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderGoodsDTO extends wmsBaseDTO<OrderGoodsBO> implements Cloneable {

	private java.lang.Long orderGoodsId;
	private java.lang.Long orderId;
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
	private java.lang.Double amountReal;
	private java.lang.Double totalPrice;
	private java.lang.String serial;
	private java.lang.String producingCountryName;
	private java.lang.String manufacturerName;
	private OrderGoodsDetailDTO orderGoodsDetailDTO;
	private List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO = Lists.newArrayList();
	private List<OrderGoodsExelDTO> lstErrorOrderGoods;
	private List<OrderGoodsExelDTO> lstErrorGoods;

	private java.lang.Long manufacturerId;
	private java.lang.Long producingCountryId;
	

	private java.lang.Integer stt;
	//OrderListDetailSerial
	
		private List<OrderGoodsDetailDTO> listDetailSerial= Lists.newArrayList();
	
		public List<OrderGoodsDetailDTO> getListDetailSerial() {
			return listDetailSerial;
		}

		public void setListDetailSerial(List<OrderGoodsDetailDTO> listDetailSerial) {
			this.listDetailSerial = listDetailSerial;
		}
		
	public java.lang.Long getOrderGoodsDetailId() {
		return orderGoodsDetailId;
	}

	public void setOrderGoodsDetailId(java.lang.Long orderGoodsDetailId) {
		this.orderGoodsDetailId = orderGoodsDetailId;
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

	public java.lang.Double getPrice() {
		return price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	private java.lang.Long orderGoodsDetailId;
	private java.lang.String contractCode;
	private java.lang.String partNumber;
	private java.lang.Double price;
	
	@Override
	public OrderGoodsBO toModel() {
		OrderGoodsBO orderGoodsBO = new OrderGoodsBO();
		orderGoodsBO.setOrderGoodsId(this.orderGoodsId);
		orderGoodsBO.setOrderId(this.orderId);
		orderGoodsBO.setGoodsType(this.goodsType);
		orderGoodsBO.setGoodsTypeName(this.goodsTypeName);
		orderGoodsBO.setGoodsId(this.goodsId);
		orderGoodsBO.setGoodsCode(this.goodsCode);
		orderGoodsBO.setGoodsName(this.goodsName);
		orderGoodsBO.setGoodsIsSerial(this.goodsIsSerial);
		orderGoodsBO.setGoodsState(this.goodsState);
		orderGoodsBO.setGoodsStateName(this.goodsStateName);
		orderGoodsBO.setGoodsUnitName(this.goodsUnitName);
		orderGoodsBO.setGoodsUnitId(this.goodsUnitId);
		orderGoodsBO.setAmount(this.amount);
		orderGoodsBO.setTotalPrice(this.totalPrice);
		return orderGoodsBO;
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

	public java.lang.String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(java.lang.String goodsType) {
		this.goodsType = goodsType;
	}

	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}
	
	public java.lang.Double getAmountReal() {
		return amountReal;
	}

	public void setAmountReal(java.lang.Double amountReal) {
		this.amountReal = amountReal;
	}
	
	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = org.apache.commons.lang3.StringUtils.isNotEmpty(serial) ? serial.toUpperCase():null;
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

	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
		return lstErrorOrderGoods;
	}

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
		this.lstErrorOrderGoods = lstErrorOrderGoods;
	}
	
	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = org.apache.commons.lang3.StringUtils.isNotEmpty(goodsCode) ? goodsCode.toUpperCase():null;
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

	public java.lang.Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(java.lang.Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public Long getFWModelId() {
		return orderId;
	}

	@Override
	public String catchName() {
		return getOrderId().toString();
	}

	public OrderGoodsDetailDTO getOrderGoodsDetailDTO() {
		return orderGoodsDetailDTO;
	}

	public void setOrderGoodsDetailDTO(OrderGoodsDetailDTO orderGoodsDetailDTO) {
		this.orderGoodsDetailDTO = orderGoodsDetailDTO;
	}

	public List<OrderGoodsDetailDTO> getListOrderGoodsDetailDTO() {
		return listOrderGoodsDetailDTO;
	}

	public void setListOrderGoodsDetailDTO(List<OrderGoodsDetailDTO> listOrderGoodsDetailDTO) {
		this.listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}
	
	public Object clone() throws  CloneNotSupportedException
	{
	return super.clone();
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

	public List<OrderGoodsExelDTO> getLstErrorGoods() {
		return lstErrorGoods;
	}

	public void setLstErrorGoods(List<OrderGoodsExelDTO> lstErrorGoods) {
		this.lstErrorGoods = lstErrorGoods;
	}

	public java.lang.String getProducingCountryName() {
		return producingCountryName;
	}

	public void setProducingCountryName(java.lang.String producingCountryName) {
		this.producingCountryName = producingCountryName;
	}

	public java.lang.String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(java.lang.String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	
	
	
	
	
}
