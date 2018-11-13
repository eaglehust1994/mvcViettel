/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ObjectReferenceDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "OBJECT_REFERENCE_DETAILBO")
public class ObjectReferenceDetailDTO extends BaseFWDTOImpl<ObjectReferenceDetailBO> {

private java.lang.Long objectReferenceDetailId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.String serial;
private java.lang.Long unitTypeId;
private java.lang.String type;
private java.lang.String manufacturer;
private java.lang.String producerCountry;
private java.lang.String customsProcedure;
private java.lang.String partNumber;
private java.lang.Double price;
private java.lang.Long objectReferenceId;

    @Override
    public ObjectReferenceDetailBO toModel() {
        ObjectReferenceDetailBO objectReferenceDetailBO = new ObjectReferenceDetailBO();
        objectReferenceDetailBO.setObjectReferenceDetailId(this.objectReferenceDetailId);
        objectReferenceDetailBO.setGoodsId(this.goodsId);
        objectReferenceDetailBO.setGoodsCode(this.goodsCode);
        objectReferenceDetailBO.setGoodsName(this.goodsName);
        objectReferenceDetailBO.setAmount(this.amount);
        objectReferenceDetailBO.setSerial(this.serial);
        objectReferenceDetailBO.setUnitTypeId(this.unitTypeId);
        objectReferenceDetailBO.setType(this.type);
        objectReferenceDetailBO.setManufacturer(this.manufacturer);
        objectReferenceDetailBO.setProducerCountry(this.producerCountry);
        objectReferenceDetailBO.setCustomsProcedure(this.customsProcedure);
        objectReferenceDetailBO.setPartNumber(this.partNumber);
        objectReferenceDetailBO.setPrice(this.price);
        objectReferenceDetailBO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceDetailBO;
    }

    public java.lang.Long getObjectReferenceDetailId(){
    return objectReferenceDetailId;
    }
    public void setObjectReferenceDetailId(java.lang.Long objectReferenceDetailId)
    {
    this.objectReferenceDetailId = objectReferenceDetailId;
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
    this.goodsCode = goodsCode;
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
    this.serial = serial;
    }
    
    public java.lang.Long getUnitTypeId(){
    return unitTypeId;
    }
    public void setUnitTypeId(java.lang.Long unitTypeId)
    {
    this.unitTypeId = unitTypeId;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.String getManufacturer(){
    return manufacturer;
    }
    public void setManufacturer(java.lang.String manufacturer)
    {
    this.manufacturer = manufacturer;
    }
    
    public java.lang.String getProducerCountry(){
    return producerCountry;
    }
    public void setProducerCountry(java.lang.String producerCountry)
    {
    this.producerCountry = producerCountry;
    }
    
    public java.lang.String getCustomsProcedure(){
    return customsProcedure;
    }
    public void setCustomsProcedure(java.lang.String customsProcedure)
    {
    this.customsProcedure = customsProcedure;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
    public java.lang.Long getObjectReferenceId(){
    return objectReferenceId;
    }
    public void setObjectReferenceId(java.lang.Long objectReferenceId)
    {
    this.objectReferenceId = objectReferenceId;
    }
    @Override
    public Long getFWModelId() {
       return objectReferenceId;
    }
  
    @Override
    public String catchName() {
       return getObjectReferenceId().toString();
    }
   
}
