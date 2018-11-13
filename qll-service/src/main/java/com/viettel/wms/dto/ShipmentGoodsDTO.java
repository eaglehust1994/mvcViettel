/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.ShipmentGoodsBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SHIPMENT_GOODSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentGoodsDTO extends wmsBaseDTO<ShipmentGoodsBO> {

private java.lang.Long shipmentGoodsId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.Long unitTypeId;
private java.lang.String unitTypeName;
private java.lang.Double originPrice;
private java.lang.Double totalOriginPrice;
private java.lang.Double estimatePrice;
private java.lang.Double estimatePriceSum;
private java.lang.Double estimatePricePercent;
private java.lang.Double applyPrice;
private java.lang.Double applyTotalMoney;
private java.lang.Long shipmentId;
private List<OrderGoodsExelDTO> lstErrorGoods;
private java.lang.String filePathError;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private String fileInput;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.String partNumber;
private java.lang.String serial;




	public java.lang.String getPartNumber() {
	return partNumber;
}

public void setPartNumber(java.lang.String partNumber) {
	this.partNumber = partNumber;
}

public java.lang.String getSerial() {
	return serial;
}

public void setSerial(java.lang.String serial) {
	this.serial = serial;
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

	@Override
    public ShipmentGoodsBO toModel() {
        ShipmentGoodsBO shipmentGoodsBO = new ShipmentGoodsBO();
        shipmentGoodsBO.setShipmentGoodsId(this.shipmentGoodsId);
        shipmentGoodsBO.setGoodsId(this.goodsId);
        shipmentGoodsBO.setGoodsCode(this.goodsCode);
        shipmentGoodsBO.setGoodsName(this.goodsName);
        shipmentGoodsBO.setAmount(this.amount);
        shipmentGoodsBO.setUnitTypeId(this.unitTypeId);
        shipmentGoodsBO.setUnitTypeName(this.unitTypeName);
        shipmentGoodsBO.setOriginPrice(this.originPrice);
        shipmentGoodsBO.setTotalOriginPrice(this.totalOriginPrice);
        shipmentGoodsBO.setEstimatePrice(this.estimatePrice);
        shipmentGoodsBO.setEstimatePriceSum(this.estimatePriceSum);
        shipmentGoodsBO.setEstimatePricePercent(this.estimatePricePercent);
        shipmentGoodsBO.setApplyPrice(this.applyPrice);
        shipmentGoodsBO.setApplyTotalMoney(this.applyTotalMoney);
        shipmentGoodsBO.setShipmentId(this.shipmentId);
        return shipmentGoodsBO;
    }

    public List<OrderGoodsExelDTO> getLstErrorGoods() {
		return lstErrorGoods;
	}

	public void setLstErrorGoods(List<OrderGoodsExelDTO> lstErrorGoods) {
		this.lstErrorGoods = lstErrorGoods;
	}

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}

	public java.lang.Long getShipmentGoodsId(){
    return shipmentGoodsId;
    }
    public void setShipmentGoodsId(java.lang.Long shipmentGoodsId)
    {
    this.shipmentGoodsId = shipmentGoodsId;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getGoodsCode(){
    return goodsCode;
    }
    public void setGoodsCode(java.lang.String goodsCode)
    {
    this.goodsCode = org.apache.commons.lang3.StringUtils.isNotEmpty(goodsCode) ? goodsCode.toUpperCase():null;
    }
    
    public java.lang.String getGoodsName(){
    return goodsName;
    }
    public void setGoodsName(java.lang.String goodsName)
    {
    this.goodsName = goodsName;
    }
    
    public java.lang.Double getAmount(){
    return amount;
    }
    public void setAmount(java.lang.Double amount)
    {
    this.amount = amount;
    }
    
    public java.lang.Long getUnitTypeId(){
    return unitTypeId;
    }
    public void setUnitTypeId(java.lang.Long unitTypeId)
    {
    this.unitTypeId = unitTypeId;
    }
    
    public java.lang.String getUnitTypeName(){
    return unitTypeName;
    }
    public void setUnitTypeName(java.lang.String unitTypeName)
    {
    this.unitTypeName = unitTypeName;
    }
    
    public java.lang.Double getOriginPrice(){
    return originPrice;
    }
    public void setOriginPrice(java.lang.Double originPrice)
    {
    this.originPrice = originPrice;
    }
    
    public java.lang.Double getTotalOriginPrice(){
    return totalOriginPrice;
    }
    public void setTotalOriginPrice(java.lang.Double totalOriginPrice)
    {
    this.totalOriginPrice = totalOriginPrice;
    }
    
    public java.lang.Double getEstimatePrice(){
    return estimatePrice;
    }
    public void setEstimatePrice(java.lang.Double estimatePrice)
    {
    this.estimatePrice = estimatePrice;
    }
    
    public java.lang.Double getEstimatePriceSum(){
    return estimatePriceSum;
    }
    public void setEstimatePriceSum(java.lang.Double estimatePriceSum)
    {
    this.estimatePriceSum = estimatePriceSum;
    }
    
    public java.lang.Double getEstimatePricePercent(){
    return estimatePricePercent;
    }
    public void setEstimatePricePercent(java.lang.Double estimatePricePercent)
    {
    this.estimatePricePercent = estimatePricePercent;
    }
    
    public java.lang.Double getApplyPrice(){
    return applyPrice;
    }
    public void setApplyPrice(java.lang.Double applyPrice)
    {
    this.applyPrice = applyPrice;
    }
    
    public java.lang.Double getApplyTotalMoney(){
    return applyTotalMoney;
    }
    public void setApplyTotalMoney(java.lang.Double applyTotalMoney)
    {
    this.applyTotalMoney = applyTotalMoney;
    }
    
    @Override
     public Long getFWModelId() {
        return shipmentId;
    }
   
    @Override
    public String catchName() {
        return getShipmentId().toString();
    }
    
    public java.lang.Long getShipmentId(){
    return shipmentId;
    }
    
    public void setShipmentId(java.lang.Long shipmentId)
    {
    this.shipmentId = shipmentId;
    }

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public String getFileInput() {
		return fileInput;
	}

	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}

	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId =goodsUnitId;
	}
    
    
   
}
