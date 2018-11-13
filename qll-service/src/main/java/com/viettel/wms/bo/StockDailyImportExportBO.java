/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockDailyImportExportDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_DAILY_IMPORT_EXPORT")
/**
 *
 * @author: TuanNB
 * @version: 1.0
 * @since: 1.0
 */
public class StockDailyImportExportBO extends BaseFWModelImpl {
     
private java.lang.Long stockDailyImportExportId;
private java.util.Date ieDate;
private java.lang.String stockTransType;
private java.lang.Long stockId;
private java.lang.Long stockCode;
private java.lang.String stockName;
private java.lang.String goodsType;
private java.lang.String goodsTypeName;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsIsSerial;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsUnitName;
private java.lang.String stockTransUserName;
private java.lang.String stockTransDescription;
private java.lang.Long goodsUnitId;
private java.lang.Double amountTotal;
private java.lang.Double totalPrice;
private java.util.Date createDateTime;


 public StockDailyImportExportBO() {
        setColId("stockDailyImportExport");
        setColName("stockDailyImportExport");
        setUniqueColumn(new String[]{"stockDailyImportExport"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_DAILY_IMPORT_EXPORT_SEQ")
            }
    )
@Column(name = "STOCK_DAILY_IMPORT_EXPORT", length = 22)
public java.lang.Long getStockDailyImportExportId(){
return stockDailyImportExportId;
}
public void setStockDailyImportExportId(java.lang.Long stockDailyImportExportId)
{
this.stockDailyImportExportId = stockDailyImportExportId;
}
@Column(name = "IE_DATE", length = 7)
public java.util.Date getIeDate(){
return ieDate;
}
public void setIeDate(java.util.Date ieDate)
{
this.ieDate = ieDate;
}
@Column(name = "STOCK_TRANS_TYPE", length = 2)
public java.lang.String getStockTransType(){
return stockTransType;
}
public void setStockTransType(java.lang.String stockTransType)
{
this.stockTransType = stockTransType;
}
@Column(name = "STOCK_ID", length = 10)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "STOCK_CODE", length = 22)
public java.lang.Long getStockCode(){
return stockCode;
}
public void setStockCode(java.lang.Long stockCode)
{
this.stockCode = stockCode;
}
@Column(name = "STOCK_NAME", length = 20)
public java.lang.String getStockName(){
return stockName;
}
public void setStockName(java.lang.String stockName)
{
this.stockName = stockName;
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
@Column(name = "GOODS_NAME", length = 1000)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "GOODS_IS_SERIAL", length = 1)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
}
@Column(name = "GOODS_STATE", length = 1)
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
@Column(name = "STOCK_TRANS_USER_NAME", length = 100)
public java.lang.String getStockTransUserName(){
return stockTransUserName;
}
public void setStockTransUserName(java.lang.String stockTransUserName)
{
this.stockTransUserName = stockTransUserName;
}
@Column(name = "STOCK_TRANS_DESCRIPTION", length = 500)
public java.lang.String getStockTransDescription(){
return stockTransDescription;
}
public void setStockTransDescription(java.lang.String stockTransDescription)
{
this.stockTransDescription = stockTransDescription;
}
@Column(name = "GOODS_UNIT_ID", length = 22)
public java.lang.Long getGoodsUnitId(){
return goodsUnitId;
}
public void setGoodsUnitId(java.lang.Long goodsUnitId)
{
this.goodsUnitId = goodsUnitId;
}
@Column(name = "AMOUNT_TOTAL", length = 22)
public java.lang.Double getAmountTotal(){
return amountTotal;
}
public void setAmountTotal(java.lang.Double amountTotal)
{
this.amountTotal = amountTotal;
}
@Column(name = "TOTAL_PRICE", length = 22)
public java.lang.Double getTotalPrice(){
return totalPrice;
}
public void setTotalPrice(java.lang.Double totalPrice)
{
this.totalPrice = totalPrice;
}
@Column(name = "CREATE_DATE_TIME", length = 13)
public java.util.Date getCreateDateTime(){
return createDateTime;
}
public void setCreateDateTime(java.util.Date createDateTime)
{
this.createDateTime = createDateTime;
}

	@Override
    public StockDailyImportExportDTO toDTO() {
        StockDailyImportExportDTO stockDailyImportExportDTO = new StockDailyImportExportDTO();
        //set cac gia tri 
        stockDailyImportExportDTO.setStockDailyImportExportId(this.stockDailyImportExportId);
        stockDailyImportExportDTO.setIeDate(this.ieDate);
        stockDailyImportExportDTO.setStockTransType(this.stockTransType);
        stockDailyImportExportDTO.setStockId(this.stockId);
        stockDailyImportExportDTO.setStockCode(this.stockCode);
        stockDailyImportExportDTO.setStockName(this.stockName);
        stockDailyImportExportDTO.setGoodsType(this.goodsType);
        stockDailyImportExportDTO.setGoodsTypeName(this.goodsTypeName);
        stockDailyImportExportDTO.setGoodsId(this.goodsId);
        stockDailyImportExportDTO.setGoodsCode(this.goodsCode);
        stockDailyImportExportDTO.setGoodsName(this.goodsName);
        stockDailyImportExportDTO.setGoodsIsSerial(this.goodsIsSerial);
        stockDailyImportExportDTO.setGoodsState(this.goodsState);
        stockDailyImportExportDTO.setGoodsStateName(this.goodsStateName);
        stockDailyImportExportDTO.setGoodsUnitName(this.goodsUnitName);
        stockDailyImportExportDTO.setStockTransUserName(this.stockTransUserName);
        stockDailyImportExportDTO.setStockTransDescription(this.stockTransDescription);
        stockDailyImportExportDTO.setGoodsUnitId(this.goodsUnitId);
        stockDailyImportExportDTO.setAmountTotal(this.amountTotal);
        stockDailyImportExportDTO.setTotalPrice(this.totalPrice);
        stockDailyImportExportDTO.setCreateDateTime(this.createDateTime);
        return stockDailyImportExportDTO;
    }
}
