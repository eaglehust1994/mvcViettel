/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockTransDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_TRANS_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockTransDetailBO extends BaseFWModelImpl {
     
private java.lang.Long stockTransDetailId;
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
private java.lang.Double amountOrder;
private java.lang.Double amountReal;
private java.lang.Double totalPrice;
private java.lang.Long stockTransId;

 public StockTransDetailBO() {
        setColId("stockTransId");
        setColName("stockTransId");
        setUniqueColumn(new String[]{"stockTransId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SEQ")
            }
    )
@Column(name = "STOCK_TRANS_DETAIL_ID", length = 22)
public java.lang.Long getStockTransDetailId(){
return stockTransDetailId;
}
public void setStockTransDetailId(java.lang.Long stockTransDetailId)
{
this.stockTransDetailId = stockTransDetailId;
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
@Column(name = "AMOUNT_ORDER", length = 22)
public java.lang.Double getAmountOrder(){
return amountOrder;
}
public void setAmountOrder(java.lang.Double amountOrder)
{
this.amountOrder = amountOrder;
}
@Column(name = "AMOUNT_REAL", length = 22)
public java.lang.Double getAmountReal(){
return amountReal;
}
public void setAmountReal(java.lang.Double amountReal)
{
this.amountReal = amountReal;
}
@Column(name = "TOTAL_PRICE", length = 22)
public java.lang.Double getTotalPrice(){
return totalPrice;
}
public void setTotalPrice(java.lang.Double totalPrice)
{
this.totalPrice = totalPrice;
}

@Column(name = "STOCK_TRANS_ID", length = 22)
public java.lang.Long getStockTransId(){
return stockTransId;
}
public void setStockTransId(java.lang.Long stockTransId)
{
this.stockTransId = stockTransId;
}
   

    @Override
    public StockTransDetailDTO toDTO() {
        StockTransDetailDTO stockTransDetailDTO = new StockTransDetailDTO();
        //set cac gia tri 
        stockTransDetailDTO.setStockTransDetailId(this.stockTransDetailId);
        stockTransDetailDTO.setOrderId(this.orderId);
        stockTransDetailDTO.setGoodsType(this.goodsType);
        stockTransDetailDTO.setGoodsTypeName(this.goodsTypeName);
        stockTransDetailDTO.setGoodsId(this.goodsId);
        stockTransDetailDTO.setGoodsCode(this.goodsCode);
        stockTransDetailDTO.setGoodsName(this.goodsName);
        stockTransDetailDTO.setGoodsIsSerial(this.goodsIsSerial);
        stockTransDetailDTO.setGoodsState(this.goodsState);
        stockTransDetailDTO.setGoodsStateName(this.goodsStateName);
        stockTransDetailDTO.setGoodsUnitName(this.goodsUnitName);
        stockTransDetailDTO.setGoodsUnitId(this.goodsUnitId);
        stockTransDetailDTO.setAmountOrder(this.amountOrder);
        stockTransDetailDTO.setAmountReal(this.amountReal);
        stockTransDetailDTO.setTotalPrice(this.totalPrice);
        stockTransDetailDTO.setStockTransId(this.stockTransId);
        return stockTransDetailDTO;
    }
}
