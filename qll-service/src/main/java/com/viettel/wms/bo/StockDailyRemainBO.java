/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockDailyRemainDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_DAILY_REMAIN")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockDailyRemainBO extends BaseFWModelImpl {
     
private java.lang.Long stockDailyRemainId;
private java.util.Date remainDate;
private java.lang.Long stockId;
private java.lang.String stockCode;
private java.lang.String stockName;
private java.lang.Long goodsId;
private java.lang.String goodsGroup;
private java.lang.String goodsGroupName;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Long goodsType;
private java.lang.String goodsIsSerial;
private java.lang.String goodsUnitName;
private java.lang.Double amount;
private java.lang.Double totalPrice;
private java.util.Date createDateTime;

 public StockDailyRemainBO() {
        setColId("stockDailyRemainId");
        setColName("stockDailyRemainId");
        setUniqueColumn(new String[]{"stockDailyRemainId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_DAILY_REMAIN_SEQ")
            }
    )
@Column(name = "STOCK_DAILY_REMAIN_ID", length = 22)
public java.lang.Long getStockDailyRemainId(){
return stockDailyRemainId;
}
public void setStockDailyRemainId(java.lang.Long stockDailyRemainId)
{
this.stockDailyRemainId = stockDailyRemainId;
}
@Column(name = "REMAIN_DATE", length = 7)
public java.util.Date getRemainDate(){
return remainDate;
}
public void setRemainDate(java.util.Date remainDate)
{
this.remainDate = remainDate;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "STOCK_CODE", length = 100)
public java.lang.String getStockCode(){
return stockCode;
}
public void setStockCode(java.lang.String stockCode)
{
this.stockCode = stockCode;
}
@Column(name = "STOCK_NAME", length = 2)
public java.lang.String getStockName(){
return stockName;
}
public void setStockName(java.lang.String stockName)
{
this.stockName = stockName;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "GOODS_GROUP", length = 2)
public java.lang.String getGoodsGroup(){
return goodsGroup;
}
public void setGoodsGroup(java.lang.String goodsGroup)
{
this.goodsGroup = goodsGroup;
}
@Column(name = "GOODS_GROUP_NAME", length = 200)
public java.lang.String getGoodsGroupName(){
return goodsGroupName;
}
public void setGoodsGroupName(java.lang.String goodsGroupName)
{
this.goodsGroupName = goodsGroupName;
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
@Column(name = "GOODS_CODE", length = 40)
public java.lang.String getGoodsCode(){
return goodsCode;
}
public void setGoodsCode(java.lang.String goodsCode)
{
this.goodsCode = goodsCode;
}
@Column(name = "GOODS_NAME", length = 1000)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "GOODS_TYPE", length = 22)
public java.lang.Long getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.Long goodsType)
{
this.goodsType = goodsType;
}
@Column(name = "GOODS_IS_SERIAL", length = 1)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
}
@Column(name = "GOODS_UNIT_NAME", length = 100)
public java.lang.String getGoodsUnitName(){
return goodsUnitName;
}
public void setGoodsUnitName(java.lang.String goodsUnitName)
{
this.goodsUnitName = goodsUnitName;
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
@Column(name = "CREATE_DATE_TIME", length = 7)
public java.util.Date getCreateDateTime(){
return createDateTime;
}
public void setCreateDateTime(java.util.Date createDateTime)
{
this.createDateTime = createDateTime;
}
   

    @Override
    public StockDailyRemainDTO toDTO() {
        StockDailyRemainDTO stockDailyRemainDTO = new StockDailyRemainDTO();
        //set cac gia tri 
        stockDailyRemainDTO.setStockDailyRemainId(this.stockDailyRemainId);
        stockDailyRemainDTO.setRemainDate(this.remainDate);
        stockDailyRemainDTO.setStockId(this.stockId);
        stockDailyRemainDTO.setStockCode(this.stockCode);
        stockDailyRemainDTO.setStockName(this.stockName);
        stockDailyRemainDTO.setGoodsId(this.goodsId);
        stockDailyRemainDTO.setGoodsGroup(this.goodsGroup);
        stockDailyRemainDTO.setGoodsGroupName(this.goodsGroupName);
        stockDailyRemainDTO.setGoodsState(this.goodsState);
        stockDailyRemainDTO.setGoodsStateName(this.goodsStateName);
        stockDailyRemainDTO.setGoodsCode(this.goodsCode);
        stockDailyRemainDTO.setGoodsName(this.goodsName);
        stockDailyRemainDTO.setGoodsType(this.goodsType);
        stockDailyRemainDTO.setGoodsIsSerial(this.goodsIsSerial);
        stockDailyRemainDTO.setGoodsUnitName(this.goodsUnitName);
        stockDailyRemainDTO.setAmount(this.amount);
        stockDailyRemainDTO.setTotalPrice(this.totalPrice);
        stockDailyRemainDTO.setCreateDateTime(this.createDateTime);
        return stockDailyRemainDTO;
    }
}
