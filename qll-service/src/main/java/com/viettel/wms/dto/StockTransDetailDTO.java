/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.google.common.collect.Lists;
import com.viettel.wms.bo.OrderBO;
import com.viettel.wms.bo.StockTransDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_TRANS_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockTransDetailDTO extends wmsBaseDTO<StockTransDetailBO> {

	private java.lang.Long stockTransDetailId;
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
	private java.lang.Double amountOrder;
	private java.lang.Double amountReal;
	private java.lang.Double totalPrice;
	private java.lang.Long stockTransId;
	private java.lang.String belowZeroWarning;
	private java.lang.Long id;
	private java.lang.Double amount;
	private Long stockId;
	private java.lang.String stockName;
	private java.lang.String stockCode;
	private java.lang.String isSerial;
	private String deptReceiveName;
	public String getDeptReceiveName() {
		return deptReceiveName;
	}

	public void setDeptReceiveName(String deptReceiveName) {
		this.deptReceiveName = deptReceiveName;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	private StockTransDetailSerialDTO detailSerialDTO;
	private List<StockTransDetailSerialDTO> listStockTransDetailSerialDTO;
	private java.lang.String filePathError;

	private List<OrderGoodsExelDTO> lstErrorOrderGoods;
	private String fileName;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
		return lstErrorOrderGoods;
	}

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
		this.lstErrorOrderGoods = lstErrorOrderGoods;
	}

	@Override
	public StockTransDetailBO toModel() {
		StockTransDetailBO stockTransDetailBO = new StockTransDetailBO();
		stockTransDetailBO.setStockTransDetailId(this.stockTransDetailId);
		stockTransDetailBO.setOrderId(this.orderId);
		stockTransDetailBO.setGoodsType(this.goodsType);
		stockTransDetailBO.setGoodsTypeName(this.goodsTypeName);
		stockTransDetailBO.setGoodsId(this.goodsId);
		stockTransDetailBO.setGoodsCode(this.goodsCode);
		stockTransDetailBO.setGoodsName(this.goodsName);
		stockTransDetailBO.setGoodsIsSerial(this.goodsIsSerial);
		stockTransDetailBO.setGoodsState(this.goodsState);
		stockTransDetailBO.setGoodsStateName(this.goodsStateName);
		stockTransDetailBO.setGoodsUnitName(this.goodsUnitName);
		stockTransDetailBO.setGoodsUnitId(this.goodsUnitId);
		stockTransDetailBO.setAmountOrder(this.amountOrder);
		stockTransDetailBO.setAmountReal(this.amountReal);
		stockTransDetailBO.setTotalPrice(this.totalPrice);
		stockTransDetailBO.setStockTransId(this.stockTransId);
		return stockTransDetailBO;
	}

	public StockTransDetailSerialDTO getDetailSerialDTO() {
		return detailSerialDTO;
	}

	public void setDetailSerialDTO(StockTransDetailSerialDTO detailSerialDTO) {
		this.detailSerialDTO = detailSerialDTO;
	}

	@Override
	public Long getFWModelId() {
		return stockTransDetailId;
	}

	@Override
	public String catchName() {
		return getStockTransDetailId().toString();
	}

	public java.lang.Long getStockTransDetailId() {
		return stockTransDetailId;
	}

	public void setStockTransDetailId(java.lang.Long stockTransDetailId) {
		this.stockTransDetailId = stockTransDetailId;
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

	public java.lang.Double getAmountOrder() {
		return amountOrder;
	}

	public void setAmountOrder(java.lang.Double amountOrder) {
		this.amountOrder = amountOrder;
	}

	public java.lang.Double getAmountReal() {
		return amountReal;
	}

	public void setAmountReal(java.lang.Double amountReal) {
		this.amountReal = amountReal;
	}

	public java.lang.Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(java.lang.Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public java.lang.Long getStockTransId() {
		return stockTransId;
	}

	public void setStockTransId(java.lang.Long stockTransId) {
		this.stockTransId = stockTransId;
	}

	public List<StockTransDetailSerialDTO> getListStockTransDetailSerialDTO() {
		return listStockTransDetailSerialDTO;
	}

	public void setListStockTransDetailSerialDTO(
			List<StockTransDetailSerialDTO> listStockTransDetailSerialDTO) {
		this.listStockTransDetailSerialDTO = listStockTransDetailSerialDTO;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.String getBelowZeroWarning() {
		return belowZeroWarning;
	}

	public void setBelowZeroWarning(java.lang.String belowZeroWarning) {
		this.belowZeroWarning = belowZeroWarning;
	}

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}


	//StockTransDetailSerial
	
	private List<StockTransDetailSerialDTO> listDetailSerial= Lists.newArrayList();
	

	public List<StockTransDetailSerialDTO> getListDetailSerial() {
		return listDetailSerial;
	}

	public void setListDetailSerial(List<StockTransDetailSerialDTO> listDetailSerial) {
		this.listDetailSerial = listDetailSerial;
	}

	private java.lang.Long stockTransDetailSerialId;
	public java.lang.Long getStockTransDetailSerialId() {
		return stockTransDetailSerialId;
	}

	public void setStockTransDetailSerialId(java.lang.Long stockTransDetailSerialId) {
		this.stockTransDetailSerialId = stockTransDetailSerialId;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
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

	public java.lang.String getCellCode() {
		return cellCode;
	}

	public void setCellCode(java.lang.String cellCode) {
		this.cellCode = cellCode;
	}
	
	


	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}

	public java.lang.String getIsSerial() {
		return isSerial;
	}

	public void setIsSerial(java.lang.String isSerial) {
		this.isSerial = isSerial;
	}

	private java.lang.String serial;
	private java.lang.String contractCode;
	private java.lang.String partNumber;
	private java.lang.Long manufacturerId;
	private java.lang.Long producingCountryId;
	private java.lang.String manufacturerName;
	private java.lang.String producingCountryName;
	private java.lang.Double price;
	private java.lang.String cellCode;
	
}
