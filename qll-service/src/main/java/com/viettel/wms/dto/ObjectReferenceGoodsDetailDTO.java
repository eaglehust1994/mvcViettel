/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ObjectReferenceGoodsDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "OBJECT_REFERENCE_GOODS_DETAILBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectReferenceGoodsDetailDTO extends BaseFWDTOImpl<ObjectReferenceGoodsDetailBO> {

private java.lang.Long objectRefGoodsDetailId;
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
    public ObjectReferenceGoodsDetailBO toModel() {
        ObjectReferenceGoodsDetailBO objectReferenceGoodsDetailBO = new ObjectReferenceGoodsDetailBO();
        objectReferenceGoodsDetailBO.setObjectRefGoodsDetailId(this.objectRefGoodsDetailId);
        objectReferenceGoodsDetailBO.setGoodsId(this.goodsId);
        objectReferenceGoodsDetailBO.setGoodsCode(this.goodsCode);
        objectReferenceGoodsDetailBO.setGoodsName(this.goodsName);
        objectReferenceGoodsDetailBO.setAmount(this.amount);
        objectReferenceGoodsDetailBO.setSerial(this.serial);
        objectReferenceGoodsDetailBO.setUnitTypeId(this.unitTypeId);
        objectReferenceGoodsDetailBO.setType(this.type);
        objectReferenceGoodsDetailBO.setManufacturer(this.manufacturer);
        objectReferenceGoodsDetailBO.setProducerCountry(this.producerCountry);
        objectReferenceGoodsDetailBO.setCustomsProcedure(this.customsProcedure);
        objectReferenceGoodsDetailBO.setPartNumber(this.partNumber);
        objectReferenceGoodsDetailBO.setPrice(this.price);
        objectReferenceGoodsDetailBO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceGoodsDetailBO;
    }

    @Override
     public Long getFWModelId() {
        return objectRefGoodsDetailId;
    }
   
    @Override
    public String catchName() {
        return getObjectRefGoodsDetailId().toString();
    }
    public java.lang.Long getObjectRefGoodsDetailId(){
    return objectRefGoodsDetailId;
    }
    public void setObjectRefGoodsDetailId(java.lang.Long objectRefGoodsDetailId)
    {
    this.objectRefGoodsDetailId = objectRefGoodsDetailId;
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
    
   
}
