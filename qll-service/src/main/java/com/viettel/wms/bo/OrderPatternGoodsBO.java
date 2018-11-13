/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_PATTERN_GOODS")
/**
 * @author TuanNB
 * @version 1.0
 * @since 09-Sep-17 09:09 PM
 */
public class OrderPatternGoodsBO extends BaseFWModelImpl {
     
private java.lang.Long orderPatternGoodsId;
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
private java.lang.Long orderPatternId;

 public OrderPatternGoodsBO() {
        setColId("orderPatternId");
        setColName("orderPatternId");
        setUniqueColumn(new String[]{"orderPatternId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_PATTERN_GOODS_SEQ")
            }
    )
@Column(name = "ORDER_PATTERN_GOODS_ID", length = 22)
public java.lang.Long getOrderPatternGoodsId(){
return orderPatternGoodsId;
}
public void setOrderPatternGoodsId(java.lang.Long orderPatternGoodsId)
{
this.orderPatternGoodsId = orderPatternGoodsId;
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
@Column(name = "ORDER_PATTERN_ID", length = 22)
public java.lang.Long getOrderPatternId(){
return orderPatternId;
}
public void setOrderPatternId(java.lang.Long orderPatternId)
{
this.orderPatternId = orderPatternId;
}
   

    @Override
    public OrderPatternGoodsDTO toDTO() {
        OrderPatternGoodsDTO orderPatternGoodsDTO = new OrderPatternGoodsDTO();
        //set cac gia tri 
        orderPatternGoodsDTO.setOrderPatternGoodsId(this.orderPatternGoodsId);
        orderPatternGoodsDTO.setGoodsType(this.goodsType);
        orderPatternGoodsDTO.setGoodsTypeName(this.goodsTypeName);
        orderPatternGoodsDTO.setGoodsId(this.goodsId);
        orderPatternGoodsDTO.setGoodsCode(this.goodsCode);
        orderPatternGoodsDTO.setGoodsName(this.goodsName);
        orderPatternGoodsDTO.setGoodsIsSerial(this.goodsIsSerial);
        orderPatternGoodsDTO.setGoodsState(this.goodsState);
        orderPatternGoodsDTO.setGoodsStateName(this.goodsStateName);
        orderPatternGoodsDTO.setGoodsUnitName(this.goodsUnitName);
        orderPatternGoodsDTO.setGoodsUnitId(this.goodsUnitId);
        orderPatternGoodsDTO.setAmount(this.amount);
        orderPatternGoodsDTO.setOrderPatternId(this.orderPatternId);
        return orderPatternGoodsDTO;
    }
}
