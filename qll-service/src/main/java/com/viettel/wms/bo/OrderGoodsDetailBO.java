/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_GOODS_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OrderGoodsDetailBO extends BaseFWModelImpl {
     
private java.lang.Long orderGoodsDetailId;
private java.lang.String serial;
private java.lang.String contractCode;
private java.lang.String partNumber;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double price;
private java.lang.Long orderGoodsId;
private java.lang.Long orderId;

 public OrderGoodsDetailBO() {
        setColId("orderId");
        setColName("orderId");
        setUniqueColumn(new String[]{"orderId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_GOODS_DETAIL_SEQ")
            }
    )
@Column(name = "ORDER_GOODS_DETAIL_ID", length = 22)
public java.lang.Long getOrderGoodsDetailId(){
return orderGoodsDetailId;
}
public void setOrderGoodsDetailId(java.lang.Long orderGoodsDetailId)
{
this.orderGoodsDetailId = orderGoodsDetailId;
}
@Column(name = "SERIAL", length = 200)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "CONTRACT_CODE", length = 200)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "PART_NUMBER", length = 200)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
}
@Column(name = "MANUFACTURER_ID", length = 22)
public java.lang.Long getManufacturerId(){
return manufacturerId;
}
public void setManufacturerId(java.lang.Long manufacturerId)
{
this.manufacturerId = manufacturerId;
}
@Column(name = "PRODUCING_COUNTRY_ID", length = 22)
public java.lang.Long getProducingCountryId(){
return producingCountryId;
}
public void setProducingCountryId(java.lang.Long producingCountryId)
{
this.producingCountryId = producingCountryId;
}
@Column(name = "MANUFACTURER_NAME", length = 2000)
public java.lang.String getManufacturerName(){
return manufacturerName;
}
public void setManufacturerName(java.lang.String manufacturerName)
{
this.manufacturerName = manufacturerName;
}
@Column(name = "PRODUCING_COUNTRY_NAME", length = 2000)
public java.lang.String getProducingCountryName(){
return producingCountryName;
}
public void setProducingCountryName(java.lang.String producingCountryName)
{
this.producingCountryName = producingCountryName;
}
@Column(name = "PRICE", length = 22)
public java.lang.Double getPrice(){
return price;
}
public void setPrice(java.lang.Double price)
{
this.price = price;
}

@Column(name = "ORDER_GOODS_ID", length = 22)
public java.lang.Long getOrderGoodsId(){
return orderGoodsId;
}
public void setOrderGoodsId(java.lang.Long orderGoodsId)
{
this.orderGoodsId = orderGoodsId;
}

@Column(name = "ORDER_ID", length = 22)
public java.lang.Long getOrderId(){
return orderId;
}
public void setOrderId(java.lang.Long orderId)
{
this.orderId = orderId;
}
   

    @Override
    public OrderGoodsDetailDTO toDTO() {
        OrderGoodsDetailDTO orderGoodsDetailDTO = new OrderGoodsDetailDTO();
        //set cac gia tri 
        orderGoodsDetailDTO.setOrderGoodsDetailId(this.orderGoodsDetailId);
        orderGoodsDetailDTO.setSerial(this.serial);
        orderGoodsDetailDTO.setContractCode(this.contractCode);
        orderGoodsDetailDTO.setPartNumber(this.partNumber);
        orderGoodsDetailDTO.setManufacturerId(this.manufacturerId);
        orderGoodsDetailDTO.setProducingCountryId(this.producingCountryId);
        orderGoodsDetailDTO.setManufacturerName(this.manufacturerName);
        orderGoodsDetailDTO.setProducingCountryName(this.producingCountryName);
        orderGoodsDetailDTO.setPrice(this.price);
        orderGoodsDetailDTO.setOrderGoodsId(this.orderGoodsId);
        orderGoodsDetailDTO.setOrderId(this.orderId);
        return orderGoodsDetailDTO;
    }
}
