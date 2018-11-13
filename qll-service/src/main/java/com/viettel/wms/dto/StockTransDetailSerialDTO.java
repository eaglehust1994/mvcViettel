/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.StockTransDetailSerialBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_TRANS_DETAIL_SERIALBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockTransDetailSerialDTO extends wmsBaseDTO<StockTransDetailSerialBO> {

private java.lang.Long stockTransDetailSerialId;
private java.lang.Long goodsId;
private java.lang.String serial;
private java.lang.String contractCode;
private java.lang.String partNumber;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double price;
private java.lang.String cellCode;
private java.lang.Long stockTransId;
private java.lang.Long stockTransDetailId;

private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsUnitName;
private java.lang.String unitTypeName;
private java.lang.Long amountReal;
private java.lang.String goodState;


private java.lang.Double amount;
private java.lang.Long id;
private java.lang.String manufacturer;
private java.lang.String producerCountry;
private List<OrderGoodsExelDTO> lstErrorOrderGoods;
private java.lang.String filePathError;
    public java.lang.String getFilePathError() {
	return filePathError;
}

public void setFilePathError(java.lang.String filePathError) {
	this.filePathError = filePathError;
}

	public List<OrderGoodsExelDTO> getLstErrorOrderGoods() {
	return lstErrorOrderGoods;
}

public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrderGoods) {
	this.lstErrorOrderGoods = lstErrorOrderGoods;
}

	@Override
    public StockTransDetailSerialBO toModel() {
        StockTransDetailSerialBO stockTransDetailSerialBO = new StockTransDetailSerialBO();
        stockTransDetailSerialBO.setStockTransDetailSerialId(this.stockTransDetailSerialId);
        stockTransDetailSerialBO.setGoodsId(this.goodsId);
        stockTransDetailSerialBO.setSerial(this.serial);
        stockTransDetailSerialBO.setContractCode(this.contractCode);
        stockTransDetailSerialBO.setPartNumber(this.partNumber);
        stockTransDetailSerialBO.setManufacturerId(this.manufacturerId);
        stockTransDetailSerialBO.setProducingCountryId(this.producingCountryId);
        stockTransDetailSerialBO.setManufacturerName(this.manufacturerName);
        stockTransDetailSerialBO.setProducingCountryName(this.producingCountryName);
        stockTransDetailSerialBO.setPrice(this.price);
        stockTransDetailSerialBO.setCellCode(this.cellCode);
        stockTransDetailSerialBO.setStockTransId(this.stockTransId);
        stockTransDetailSerialBO.setStockTransDetailId(this.stockTransDetailId);
        return stockTransDetailSerialBO;
    }

    @Override
     public Long getFWModelId() {
        return stockTransDetailSerialId;
    }
   
    @Override
    public String catchName() {
        return getStockTransDetailSerialId().toString();
    }
    public java.lang.Long getStockTransDetailSerialId(){
    return stockTransDetailSerialId;
    }
    public void setStockTransDetailSerialId(java.lang.Long stockTransDetailSerialId)
    {
    this.stockTransDetailSerialId = stockTransDetailSerialId;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getSerial(){
    return serial;
    }
    public void setSerial(java.lang.String serial)
    {
    this.serial =StringUtils.isNotEmpty(serial) ? serial.toUpperCase() : null;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
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
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
    public java.lang.String getCellCode(){
    return cellCode;
    }
    public void setCellCode(java.lang.String cellCode)
    {
    this.cellCode = cellCode;
    }
    
    public java.lang.Long getStockTransId(){
    return stockTransId;
    }
    public void setStockTransId(java.lang.Long stockTransId)
    {
    this.stockTransId = stockTransId;
    }
    
    public java.lang.Long getStockTransDetailId(){
    return stockTransDetailId;
    }
    public void setStockTransDetailId(java.lang.Long stockTransDetailId)
    {
    this.stockTransDetailId = stockTransDetailId;
    }

	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(goodsCode)){
			this.goodsCode = goodsCode.toUpperCase();
		}else{
			this.goodsCode = null;
		}
		
	}

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public java.lang.String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(java.lang.String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(java.lang.String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public java.lang.String getProducerCountry() {
		return producerCountry;
	}

	public void setProducerCountry(java.lang.String producerCountry) {
		this.producerCountry = producerCountry;
	}

	public java.lang.Long getAmountReal() {
		return amountReal;
	}

	public void setAmountReal(java.lang.Long amountReal) {
		this.amountReal = amountReal;
	}

	public java.lang.String getGoodState() {
		return goodState;
	}

	public void setGoodState(java.lang.String goodState) {
		this.goodState = goodState;
	}

    
   
}
