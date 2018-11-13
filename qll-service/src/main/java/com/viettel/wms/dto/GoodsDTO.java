/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.GoodsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "GOODSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsDTO extends wmsBaseDTO<GoodsBO> {

private java.util.Date updatedDate;
private java.lang.Long goodsId;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long updatedBy;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.String producingCountryName;
private java.lang.String manufacturerName;
private java.lang.Long producingCountryId;
private java.lang.Long manufacturerId;
private java.lang.String description;
private java.lang.Double volumeReal;
private java.lang.Double volumeOrigin;
private java.lang.Double weight;
private java.lang.Double originSize;
private java.lang.Double originPrice;
private java.lang.String isSerial;
private java.lang.String goodsType;
private java.lang.String goodsTypeName;
private Long stockId;
public Long getStockId() {
	return stockId;
}

public void setStockId(Long stockId) {
	this.stockId = stockId;
}

public java.lang.String getGoodsTypeName() {
	return goodsTypeName;
}

public void setGoodsTypeName(java.lang.String goodsTypeName) {
	this.goodsTypeName = goodsTypeName;
}

private java.lang.Long unitType;
private java.lang.String unitTypeName;
private java.lang.String status;

private List<OrderGoodsExelDTO> lstErrorGoods;
private java.lang.String partNumber;
private java.lang.String contractNumber;
private java.lang.String serial;
private java.lang.Double amount;
private java.lang.Long id;
private java.lang.Double estimatePrice;

private java.lang.String filePathError;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.String goodsState;
private java.lang.String goodsStateName;


    public java.lang.String getGoodsUnitName() {
	return goodsUnitName;
}

public void setGoodsUnitName(java.lang.String goodsUnitName) {
	this.goodsUnitName = goodsUnitName;
}

	@Override
    public GoodsBO toModel() {
        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setUpdatedDate(this.updatedDate);
        goodsBO.setGoodsId(this.goodsId);
        goodsBO.setCode(this.code);
        goodsBO.setName(this.name);
        goodsBO.setUpdatedBy(this.updatedBy);
        goodsBO.setCreatedDate(this.createdDate);
        goodsBO.setCreatedBy(this.createdBy);
        goodsBO.setProducingCountryName(this.producingCountryName);
        goodsBO.setManufacturerName(this.manufacturerName);
        goodsBO.setProducingCountryId(this.producingCountryId);
        goodsBO.setManufacturerId(this.manufacturerId);
        goodsBO.setDescription(this.description);
        goodsBO.setVolumeReal(this.volumeReal);
        goodsBO.setVolumeOrigin(this.volumeOrigin);
        goodsBO.setWeight(this.weight);
        goodsBO.setOriginSize(this.originSize);
        goodsBO.setOriginPrice(this.originPrice);
        goodsBO.setIsSerial(this.isSerial);
        goodsBO.setGoodsType(this.goodsType);
        goodsBO.setUnitType(this.unitType);
        goodsBO.setStatus(this.status);
        goodsBO.setUnitTypeName(this.unitTypeName);
        return goodsBO;
    }

    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    	if(code!=null){
    		this.code = code.toUpperCase();
    	}else{
    		this.code = null;
    	}
    
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.lang.String getProducingCountryName(){
    return producingCountryName;
    }
    public void setProducingCountryName(java.lang.String producingCountryName)
    {
    this.producingCountryName = producingCountryName;
    }
    
    public java.lang.String getManufacturerName(){
    return manufacturerName;
    }
    public void setManufacturerName(java.lang.String manufacturerName)
    {
    this.manufacturerName = manufacturerName;
    }
    
    public java.lang.Long getProducingCountryId(){
    return producingCountryId;
    }
    public void setProducingCountryId(java.lang.Long producingCountryId)
    {
    this.producingCountryId = producingCountryId;
    }
    
    public java.lang.Long getManufacturerId(){
    return manufacturerId;
    }
    public void setManufacturerId(java.lang.Long manufacturerId)
    {
    this.manufacturerId = manufacturerId;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Double getVolumeReal(){
    return volumeReal;
    }
    public void setVolumeReal(java.lang.Double volumeReal)
    {
    this.volumeReal = volumeReal;
    }
    
    public java.lang.Double getVolumeOrigin(){
    return volumeOrigin;
    }
    public void setVolumeOrigin(java.lang.Double volumeOrigin)
    {
    this.volumeOrigin = volumeOrigin;
    }
    
    public java.lang.Double getWeight(){
    return weight;
    }
    public void setWeight(java.lang.Double weight)
    {
    this.weight = weight;
    }
    
    public java.lang.Double getOriginSize(){
    return originSize;
    }
    public void setOriginSize(java.lang.Double originSize)
    {
    this.originSize = originSize;
    }
    
    public java.lang.Double getOriginPrice(){
    return originPrice;
    }
    public void setOriginPrice(java.lang.Double originPrice)
    {
    this.originPrice = originPrice;
    }
    
    public java.lang.String getIsSerial(){
    return isSerial;
    }
    public void setIsSerial(java.lang.String isSerial)
    {
    this.isSerial = isSerial;
    }
    
    public java.lang.String getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.String goodsType)
    {
    this.goodsType = goodsType;
    }
    
    public java.lang.Long getUnitType(){
    return unitType;
    }
    public void setUnitType(java.lang.Long unitType)
    {
    this.unitType = unitType;
    }
    
    public java.lang.String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(java.lang.String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    @Override
    public Long getFWModelId() {
       return manufacturerId;
    }
  
    @Override
    public String catchName() {
       return getManufacturerId().toString();
    }

	public List<OrderGoodsExelDTO> getLstErrorGoods() {
		return lstErrorGoods;
	}

	public void setLstErrorGoods(List<OrderGoodsExelDTO> lstErrorGoods) {
		this.lstErrorGoods = lstErrorGoods;
	}

	public java.lang.String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(java.lang.String partNumber) {
		this.partNumber = partNumber;
	}

	public java.lang.String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(java.lang.String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		if(serial!=null){
			this.serial = serial.toUpperCase();
		}else{
			this.serial = null;
		}
		
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

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}

	public java.lang.Double getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(java.lang.Double estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
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
    
    
}
