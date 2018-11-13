/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ShipmentGoodsDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SHIPMENT_GOODS_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentGoodsDetailDTO extends wmsBaseDTO<ShipmentGoodsDetailBO> {

private java.lang.Long shipmentGoodsDetailId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.String serial;
private java.lang.Long unitTypeId;
private java.lang.String unitTypeName;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double applyPrice;
private java.lang.String partNumber;
private java.lang.Double price;
private java.lang.Long shipmentId;
private java.lang.Long shipmentGoodsId;
private java.lang.String isSerial;
private java.lang.String manufacturer;
public java.lang.String getIsSerial() {
	return isSerial;
}

public void setIsSerial(java.lang.String isSerial) {
	this.isSerial = isSerial;
}

private java.lang.String producerCountry;

    @Override
    public ShipmentGoodsDetailBO toModel() {
        ShipmentGoodsDetailBO shipmentGoodsDetailBO = new ShipmentGoodsDetailBO();
        shipmentGoodsDetailBO.setShipmentGoodsDetailId(this.shipmentGoodsDetailId);
        shipmentGoodsDetailBO.setGoodsId(this.goodsId);
        shipmentGoodsDetailBO.setGoodsCode(this.goodsCode);
        shipmentGoodsDetailBO.setGoodsName(this.goodsName);
        shipmentGoodsDetailBO.setAmount(this.amount);
        shipmentGoodsDetailBO.setSerial(this.serial);
        shipmentGoodsDetailBO.setUnitTypeId(this.unitTypeId);
        shipmentGoodsDetailBO.setUnitTypeName(this.unitTypeName);
        shipmentGoodsDetailBO.setManufacturerId(this.manufacturerId);
        shipmentGoodsDetailBO.setProducingCountryId(this.producingCountryId);
        shipmentGoodsDetailBO.setManufacturerName(this.manufacturerName);
        shipmentGoodsDetailBO.setProducingCountryName(this.producingCountryName);
        shipmentGoodsDetailBO.setApplyPrice(this.applyPrice);
        shipmentGoodsDetailBO.setPartNumber(this.partNumber);
        shipmentGoodsDetailBO.setPrice(this.price);
        shipmentGoodsDetailBO.setShipmentId(this.shipmentId);
        shipmentGoodsDetailBO.setShipmentGoodsId(this.shipmentGoodsId);
        return shipmentGoodsDetailBO;
    }

    @Override
     public Long getFWModelId() {
        return shipmentGoodsDetailId;
    }
   
    @Override
    public String catchName() {
        return getShipmentGoodsDetailId().toString();
    }
    public java.lang.Long getShipmentGoodsDetailId(){
    return shipmentGoodsDetailId;
    }
    public void setShipmentGoodsDetailId(java.lang.Long shipmentGoodsDetailId)
    {
    this.shipmentGoodsDetailId = shipmentGoodsDetailId;
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
    
    public java.lang.String getSerial(){
    return serial;
    }
    public void setSerial(java.lang.String serial)
    {
    this.serial = org.apache.commons.lang3.StringUtils.isNotEmpty(serial) ? serial.toUpperCase().trim():null;
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
    
    public java.lang.Double getApplyPrice(){
    return applyPrice;
    }
    public void setApplyPrice(java.lang.Double applyPrice)
    {
    this.applyPrice = applyPrice;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = org.apache.commons.lang3.StringUtils.isNotEmpty(partNumber) ? partNumber.trim():null;
    }
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
    public java.lang.Long getShipmentId(){
    return shipmentId;
    }
    public void setShipmentId(java.lang.Long shipmentId)
    {
    this.shipmentId = shipmentId;
    }
    
    public java.lang.Long getShipmentGoodsId(){
    return shipmentGoodsId;
    }
    public void setShipmentGoodsId(java.lang.Long shipmentGoodsId)
    {
    this.shipmentGoodsId = shipmentGoodsId;
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

	public void setLstErrorOrderGoods(List<OrderGoodsExelDTO> lstErrorOrder) {
		// TODO Auto-generated method stub
		
	}
    
   
}
