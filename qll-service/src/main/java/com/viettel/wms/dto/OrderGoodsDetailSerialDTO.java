/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.OrderGoodsDetailSerialBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ORDER_GOODS_DETAIL_SERIALBO")
public class OrderGoodsDetailSerialDTO extends wmsBaseDTO<OrderGoodsDetailSerialBO> {

private java.lang.Long orderGoodsDetailId;
private java.lang.Long id;
private java.lang.Long goodsId;
private java.lang.String serial;
private java.lang.String contractNumber;
private java.lang.String partNumber;
private java.lang.String manufacturer;
private java.lang.String producerContry;
private java.lang.Double price;

    @Override
    public OrderGoodsDetailSerialBO toModel() {
        OrderGoodsDetailSerialBO orderGoodsDetailSerialBO = new OrderGoodsDetailSerialBO();
        orderGoodsDetailSerialBO.setOrderGoodsDetailId(this.orderGoodsDetailId);
        orderGoodsDetailSerialBO.setId(this.id);
        orderGoodsDetailSerialBO.setGoodsId(this.goodsId);
        orderGoodsDetailSerialBO.setSerial(this.serial);
        orderGoodsDetailSerialBO.setContractNumber(this.contractNumber);
        orderGoodsDetailSerialBO.setPartNumber(this.partNumber);
        orderGoodsDetailSerialBO.setManufacturer(this.manufacturer);
        orderGoodsDetailSerialBO.setProducerContry(this.producerContry);
        orderGoodsDetailSerialBO.setPrice(this.price);
        return orderGoodsDetailSerialBO;
    }

    public java.lang.Long getOrderGoodsDetailId(){
    return orderGoodsDetailId;
    }
    public void setOrderGoodsDetailId(java.lang.Long orderGoodsDetailId)
    {
    this.orderGoodsDetailId = orderGoodsDetailId;
    }
    
    @Override
     public Long getFWModelId() {
        return id;
    }
   
    @Override
    public String catchName() {
        return getId().toString();
    }
    public java.lang.Long getId(){
    return id;
    }
    public void setId(java.lang.Long id)
    {
    this.id = id;
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
    this.serial = serial.toUpperCase();
    }
    
    public java.lang.String getContractNumber(){
    return contractNumber;
    }
    public void setContractNumber(java.lang.String contractNumber)
    {
    this.contractNumber = contractNumber;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.String getManufacturer(){
    return manufacturer;
    }
    public void setManufacturer(java.lang.String manufacturer)
    {
    this.manufacturer = manufacturer;
    }
    
    public java.lang.String getProducerContry(){
    return producerContry;
    }
    public void setProducerContry(java.lang.String producerContry)
    {
    this.producerContry = producerContry;
    }
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
   
}
