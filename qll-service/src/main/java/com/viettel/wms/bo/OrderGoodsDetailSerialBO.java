/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderGoodsDetailSerialDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ORDER_GOODS_DETAIL_SERIAL")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class OrderGoodsDetailSerialBO extends BaseFWModelImpl {
     
private java.lang.Long orderGoodsDetailId;
private java.lang.Long id;
private java.lang.Long goodsId;
private java.lang.String serial;
private java.lang.String contractNumber;
private java.lang.String partNumber;
private java.lang.String manufacturer;
private java.lang.String producerContry;
private java.lang.Double price;

 public OrderGoodsDetailSerialBO() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
}

@Column(name = "ORDER_GOODS_DETAIL_ID", length = 22)
public java.lang.Long getOrderGoodsDetailId(){
return orderGoodsDetailId;
}
public void setOrderGoodsDetailId(java.lang.Long orderGoodsDetailId)
{
this.orderGoodsDetailId = orderGoodsDetailId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "ORDER_GOODS_DETAIL_SERIAL_SEQ")
            }
    )
@Column(name = "ID", length = 22)
public java.lang.Long getId(){
return id;
}
public void setId(java.lang.Long id)
{
this.id = id;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "SERIAL", length = 200)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "CONTRACT_NUMBER", length = 200)
public java.lang.String getContractNumber(){
return contractNumber;
}
public void setContractNumber(java.lang.String contractNumber)
{
this.contractNumber = contractNumber;
}
@Column(name = "PART_NUMBER", length = 200)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
}
@Column(name = "MANUFACTURER", length = 200)
public java.lang.String getManufacturer(){
return manufacturer;
}
public void setManufacturer(java.lang.String manufacturer)
{
this.manufacturer = manufacturer;
}
@Column(name = "PRODUCER_CONTRY", length = 200)
public java.lang.String getProducerContry(){
return producerContry;
}
public void setProducerContry(java.lang.String producerContry)
{
this.producerContry = producerContry;
}
@Column(name = "PRICE", length = 22)
public java.lang.Double getPrice(){
return price;
}
public void setPrice(java.lang.Double price)
{
this.price = price;
}
   

    @Override
    public OrderGoodsDetailSerialDTO toDTO() {
        OrderGoodsDetailSerialDTO orderGoodsDetailSerialDTO = new OrderGoodsDetailSerialDTO();
        //set cac gia tri 
        orderGoodsDetailSerialDTO.setOrderGoodsDetailId(this.orderGoodsDetailId);
        orderGoodsDetailSerialDTO.setId(this.id);
        orderGoodsDetailSerialDTO.setGoodsId(this.goodsId);
        orderGoodsDetailSerialDTO.setSerial(this.serial);
        orderGoodsDetailSerialDTO.setContractNumber(this.contractNumber);
        orderGoodsDetailSerialDTO.setPartNumber(this.partNumber);
        orderGoodsDetailSerialDTO.setManufacturer(this.manufacturer);
        orderGoodsDetailSerialDTO.setProducerContry(this.producerContry);
        orderGoodsDetailSerialDTO.setPrice(this.price);
        return orderGoodsDetailSerialDTO;
    }
}
