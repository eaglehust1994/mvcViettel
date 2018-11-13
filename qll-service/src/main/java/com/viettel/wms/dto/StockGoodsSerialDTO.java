/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.StockGoodsSerialBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_GOODS_SERIALBO")
public class StockGoodsSerialDTO extends wmsBaseDTO<StockGoodsSerialBO> {

private java.lang.Long stockGoodsId;
private java.lang.Long stockId;
private java.lang.Long goodsId;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Long goodsType;
private java.lang.String goodsTypeName;
private java.lang.String goodsIsSerial;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amount;
private java.util.Date changeDate;
private java.util.Date importDate;
private java.lang.Long orderId;
private java.lang.Long importStockTransId;
private java.lang.String status;
private java.lang.Double price;
private java.lang.String contractCode;
private java.lang.String shipmentCode;
private java.lang.String projectCode;
private java.lang.String serial;
private java.lang.String partNumber;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.util.List<Long> listGoodsType;
private java.lang.String stockName;
private java.lang.String name;
private java.lang.String code;
private java.util.List<String> listSerial;
private java.lang.String serialString;
private java.lang.Double totalPrice;

    @Override
    public StockGoodsSerialBO toModel() {
        StockGoodsSerialBO stockGoodsSerialBO = new StockGoodsSerialBO();
        stockGoodsSerialBO.setStockGoodsId(this.stockGoodsId);
        stockGoodsSerialBO.setStockId(this.stockId);
        stockGoodsSerialBO.setGoodsId(this.goodsId);
        stockGoodsSerialBO.setGoodsState(this.goodsState);
        stockGoodsSerialBO.setGoodsStateName(this.goodsStateName);
        stockGoodsSerialBO.setGoodsCode(this.goodsCode);
        stockGoodsSerialBO.setGoodsName(this.goodsName);
        stockGoodsSerialBO.setGoodsType(this.goodsType);
        stockGoodsSerialBO.setGoodsIsSerial(this.goodsIsSerial);
        stockGoodsSerialBO.setGoodsUnitName(this.goodsUnitName);
        stockGoodsSerialBO.setGoodsUnitId(this.goodsUnitId);
        stockGoodsSerialBO.setAmount(this.amount);
        stockGoodsSerialBO.setChangeDate(this.changeDate);
        stockGoodsSerialBO.setImportDate(this.importDate);
        stockGoodsSerialBO.setOrderId(this.orderId);
        stockGoodsSerialBO.setImportStockTransId(this.importStockTransId);
        stockGoodsSerialBO.setStatus(this.status);
        stockGoodsSerialBO.setPrice(this.price);
        stockGoodsSerialBO.setContractCode(this.contractCode);
        stockGoodsSerialBO.setShipmentCode(this.shipmentCode);
        stockGoodsSerialBO.setProjectCode(this.projectCode);
        stockGoodsSerialBO.setSerial(this.serial);
        stockGoodsSerialBO.setPartNumber(this.partNumber);
        stockGoodsSerialBO.setManufacturerId(this.manufacturerId);
        stockGoodsSerialBO.setProducingCountryId(this.producingCountryId);
        stockGoodsSerialBO.setManufacturerName(this.manufacturerName);
        stockGoodsSerialBO.setProducingCountryName(this.producingCountryName);
        return stockGoodsSerialBO;
    }

    @Override
     public Long getFWModelId() {
        return stockGoodsId;
    }
   
    @Override
    public String catchName() {
        return getStockGoodsId().toString();
    }
    public java.lang.Long getStockGoodsId(){
    return stockGoodsId;
    }
    public void setStockGoodsId(java.lang.Long stockGoodsId)
    {
    this.stockGoodsId = stockGoodsId;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getGoodsState(){
    return goodsState;
    }
    public void setGoodsState(java.lang.String goodsState)
    {
    this.goodsState = goodsState;
    }
    
    public java.lang.String getGoodsStateName(){
    return goodsStateName;
    }
    public void setGoodsStateName(java.lang.String goodsStateName)
    {
    this.goodsStateName = goodsStateName;
    }
    
    public java.lang.String getGoodsCode(){
    return goodsCode;
    }
    public void setGoodsCode(java.lang.String goodsCode)
    {
    this.goodsCode = goodsCode;
    }
    
    public java.lang.String getGoodsName(){
    return goodsName;
    }
    public void setGoodsName(java.lang.String goodsName)
    {
    this.goodsName = goodsName;
    }
    
    public java.lang.Long getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.Long goodsType)
    {
    this.goodsType = goodsType;
    }
    
    public java.lang.String getGoodsIsSerial(){
    return goodsIsSerial;
    }
    public void setGoodsIsSerial(java.lang.String goodsIsSerial)
    {
    this.goodsIsSerial = goodsIsSerial;
    }
    
    public java.lang.String getGoodsUnitName(){
    return goodsUnitName;
    }
    public void setGoodsUnitName(java.lang.String goodsUnitName)
    {
    this.goodsUnitName = goodsUnitName;
    }
    
    public java.lang.Long getGoodsUnitId(){
    return goodsUnitId;
    }
    public void setGoodsUnitId(java.lang.Long goodsUnitId)
    {
    this.goodsUnitId = goodsUnitId;
    }
    
    public java.lang.Double getAmount(){
    return amount;
    }
    public void setAmount(java.lang.Double amount)
    {
    this.amount = amount;
    }
    
    public java.util.Date getChangeDate(){
    return changeDate;
    }
    public void setChangeDate(java.util.Date changeDate)
    {
    this.changeDate = changeDate;
    }
    
    public java.util.Date getImportDate(){
    return importDate;
    }
    public void setImportDate(java.util.Date importDate)
    {
    this.importDate = importDate;
    }
    
    public java.lang.Long getOrderId(){
    return orderId;
    }
    public void setOrderId(java.lang.Long orderId)
    {
    this.orderId = orderId;
    }
    
    public java.lang.Long getImportStockTransId(){
    return importStockTransId;
    }
    public void setImportStockTransId(java.lang.Long importStockTransId)
    {
    this.importStockTransId = importStockTransId;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
    public java.lang.String getShipmentCode(){
    return shipmentCode;
    }
    public void setShipmentCode(java.lang.String shipmentCode)
    {
    this.shipmentCode = shipmentCode;
    }
    
    public java.lang.String getProjectCode(){
    return projectCode;
    }
    public void setProjectCode(java.lang.String projectCode)
    {
    this.projectCode = projectCode;
    }
    
    public java.lang.String getSerial(){
    return serial;
    }
    public void setSerial(java.lang.String serial)
    {
    this.serial = StringUtils.isNotEmpty(serial) ? serial.toUpperCase() : null;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.Long getManufacturerId(){
    return manufacturerId;
    }
    public void setManufacturerId(java.lang.Long manufacturerId)
    {
    this.manufacturerId = manufacturerId;
    }
    
    public java.lang.Long getProducingCountryId(){
    return producingCountryId;
    }
    public void setProducingCountryId(java.lang.Long producingCountryId)
    {
    this.producingCountryId = producingCountryId;
    }
    
    public java.lang.String getManufacturerName(){
    return manufacturerName;
    }
    public void setManufacturerName(java.lang.String manufacturerName)
    {
    this.manufacturerName = manufacturerName;
    }
    
    public java.lang.String getProducingCountryName(){
    return producingCountryName;
    }
    public void setProducingCountryName(java.lang.String producingCountryName)
    {
    this.producingCountryName = producingCountryName;
    }

	public java.util.List<Long> getListGoodsType() {
		return listGoodsType;
	}

	public void setListGoodsType(java.util.List<Long> listGoodsType) {
		this.listGoodsType = listGoodsType;
	}
    
	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.util.List<String> getListSerial() {
		return listSerial;
	}

	public void setListSerial(java.util.List<String> listSerial) {
		this.listSerial = listSerial;
	}

	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public java.lang.Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(java.lang.Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public java.lang.String getSerialString() {	
		return serialString;
	}

	public void setSerialString(java.lang.String serialString) {
		if(serialString.indexOf(";")!=-1)
		{
			serialString = serialString.replaceAll(";", ",");
		}
		if(serialString.indexOf("\n")!=-1)
		{
			serialString = serialString.replaceAll("\n", ",");
		}
		this.serialString = serialString;
	}
   
	
}
