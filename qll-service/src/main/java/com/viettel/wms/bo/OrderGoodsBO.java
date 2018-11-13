/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_GOODS")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OrderGoodsBO extends BaseFWModelImpl {
     
private java.lang.Long orderGoodsId;
private java.lang.Long orderId;
private java.lang.String goodsType;
private java.lang.String goodsTypeName;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsIsSerial;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amount;
private java.lang.Double totalPrice;

 public OrderGoodsBO() {
        setColId("orderId");
        setColName("orderId");
        setUniqueColumn(new String[]{"orderId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_GOODS_SEQ")
            }
    )
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
@Column(name = "GOODS_TYPE", length = 100)
public java.lang.String getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.String goodsType)
{
this.goodsType = goodsType;
}
@Column(name = "GOODS_TYPE_NAME", length = 200)
public java.lang.String getGoodsTypeName(){
return goodsTypeName;
}
public void setGoodsTypeName(java.lang.String goodsTypeName)
{
this.goodsTypeName = goodsTypeName;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "GOODS_CODE", length = 100)
public java.lang.String getGoodsCode(){
return goodsCode;
}
public void setGoodsCode(java.lang.String goodsCode)
{
this.goodsCode = goodsCode;
}
@Column(name = "GOODS_NAME", length = 400)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "GOODS_IS_SERIAL", length = 2)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
}
@Column(name = "GOODS_STATE", length = 2)
public java.lang.String getGoodsState(){
return goodsState;
}
public void setGoodsState(java.lang.String goodsState)
{
this.goodsState = goodsState;
}
@Column(name = "GOODS_STATE_NAME", length = 200)
public java.lang.String getGoodsStateName(){
return goodsStateName;
}
public void setGoodsStateName(java.lang.String goodsStateName)
{
this.goodsStateName = goodsStateName;
}
@Column(name = "GOODS_UNIT_NAME", length = 200)
public java.lang.String getGoodsUnitName(){
return goodsUnitName;
}
public void setGoodsUnitName(java.lang.String goodsUnitName)
{
this.goodsUnitName = goodsUnitName;
}
@Column(name = "GOODS_UNIT_ID", length = 22)
public java.lang.Long getGoodsUnitId(){
return goodsUnitId;
}
public void setGoodsUnitId(java.lang.Long goodsUnitId)
{
this.goodsUnitId = goodsUnitId;
}
@Column(name = "AMOUNT", length = 22)
public java.lang.Double getAmount(){
return amount;
}
public void setAmount(java.lang.Double amount)
{
this.amount = amount;
}
@Column(name = "TOTAL_PRICE", length = 22)
public java.lang.Double getTotalPrice(){
return totalPrice;
}
public void setTotalPrice(java.lang.Double totalPrice)
{
this.totalPrice = totalPrice;
}
   

    @Override
    public OrderGoodsDTO toDTO() {
        OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
        //set cac gia tri 
        orderGoodsDTO.setOrderGoodsId(this.orderGoodsId);
        orderGoodsDTO.setOrderId(this.orderId);
        orderGoodsDTO.setGoodsType(this.goodsType);
        orderGoodsDTO.setGoodsTypeName(this.goodsTypeName);
        orderGoodsDTO.setGoodsId(this.goodsId);
        orderGoodsDTO.setGoodsCode(this.goodsCode);
        orderGoodsDTO.setGoodsName(this.goodsName);
        orderGoodsDTO.setGoodsIsSerial(this.goodsIsSerial);
        orderGoodsDTO.setGoodsState(this.goodsState);
        orderGoodsDTO.setGoodsStateName(this.goodsStateName);
        orderGoodsDTO.setGoodsUnitName(this.goodsUnitName);
        orderGoodsDTO.setGoodsUnitId(this.goodsUnitId);
        orderGoodsDTO.setAmount(this.amount);
        orderGoodsDTO.setTotalPrice(this.totalPrice);
        return orderGoodsDTO;
    }
}
