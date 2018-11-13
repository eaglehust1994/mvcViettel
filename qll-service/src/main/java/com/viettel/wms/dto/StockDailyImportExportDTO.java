/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.StockDailyImportExportBO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_DAILY_IMPORT_EXPORTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class StockDailyImportExportDTO extends wmsBaseDTO<StockDailyImportExportBO> {

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
private List<String> listStockTransType;
private java.util.Date remainDate;
private java.lang.Double amount;
private java.lang.Double amountTotalImport;
private java.lang.Double amountTotalExport;
private java.lang.Double amountFinal;
private List<Long> listStockId;

@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date startDate;

@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date endDate;

    @Override
    public StockDailyImportExportBO toModel() {
        StockDailyImportExportBO stockDailyImportExportBO = new StockDailyImportExportBO();
        stockDailyImportExportBO.setStockDailyImportExportId(this.stockDailyImportExportId);
        stockDailyImportExportBO.setIeDate(this.ieDate);
        stockDailyImportExportBO.setStockTransType(this.stockTransType);
        stockDailyImportExportBO.setStockId(this.stockId);
        stockDailyImportExportBO.setStockCode(this.stockCode);
        stockDailyImportExportBO.setStockName(this.stockName);
        stockDailyImportExportBO.setGoodsType(this.goodsType);
        stockDailyImportExportBO.setGoodsTypeName(this.goodsTypeName);
        stockDailyImportExportBO.setGoodsId(this.goodsId);
        stockDailyImportExportBO.setGoodsCode(this.goodsCode);
        stockDailyImportExportBO.setGoodsName(this.goodsName);
        stockDailyImportExportBO.setGoodsIsSerial(this.goodsIsSerial);
        stockDailyImportExportBO.setGoodsState(this.goodsState);
        stockDailyImportExportBO.setGoodsStateName(this.goodsStateName);
        stockDailyImportExportBO.setGoodsUnitName(this.goodsUnitName);
        stockDailyImportExportBO.setStockTransUserName(this.stockTransUserName);
        stockDailyImportExportBO.setStockTransDescription(this.stockTransDescription);
        stockDailyImportExportBO.setGoodsUnitId(this.goodsUnitId);
        stockDailyImportExportBO.setAmountTotal(this.amountTotal);
        stockDailyImportExportBO.setTotalPrice(this.totalPrice);
        stockDailyImportExportBO.setCreateDateTime(this.createDateTime);
        return stockDailyImportExportBO;
    }

    @Override
     public Long getFWModelId() {
        return stockDailyImportExportId;
    }
   
    @Override
    public String catchName() {
        return getStockDailyImportExportId().toString();
    }
    public java.lang.Long getStockDailyImportExportId(){
    return stockDailyImportExportId;
    }
    public void setStockDailyImportExportId(java.lang.Long stockDailyImportExportId)
    {
    this.stockDailyImportExportId = stockDailyImportExportId;
    }
    
    public java.util.Date getIeDate(){
    return ieDate;
    }
    public void setIeDate(java.util.Date ieDate)
    {
    this.ieDate = ieDate;
    }
    
    public java.lang.String getStockTransType(){
    return stockTransType;
    }
    public void setStockTransType(java.lang.String stockTransType)
    {
    this.stockTransType = stockTransType;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.Long getStockCode(){
    return stockCode;
    }
    public void setStockCode(java.lang.Long stockCode)
    {
    this.stockCode = stockCode;
    }
    
    public java.lang.String getStockName(){
    return stockName;
    }
    public void setStockName(java.lang.String stockName)
    {
    this.stockName = stockName;
    }
    
    public java.lang.String getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.String goodsType)
    {
    this.goodsType = goodsType;
    }
    
    public java.lang.String getGoodsTypeName(){
    return goodsTypeName;
    }
    public void setGoodsTypeName(java.lang.String goodsTypeName)
    {
    this.goodsTypeName = goodsTypeName;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public java.lang.String getGoodsName(){
    return goodsName;
    }
    public void setGoodsName(java.lang.String goodsName)
    {
    this.goodsName = goodsName;
    }
    
    public java.lang.String getGoodsIsSerial(){
    return goodsIsSerial;
    }
    public void setGoodsIsSerial(java.lang.String goodsIsSerial)
    {
    this.goodsIsSerial = goodsIsSerial;
    }
    
    public java.lang.String getGoodsState(){
    return goodsState;
    }
    public void setGoodsState(java.lang.String goodsState)
    {
    this.goodsState = goodsState;
    }
    
    public java.lang.String getGoodsStateName(){
    return goodsStateName;
    }
    public void setGoodsStateName(java.lang.String goodsStateName)
    {
    this.goodsStateName = goodsStateName;
    }
    
    public java.lang.String getGoodsUnitName(){
    return goodsUnitName;
    }
    public void setGoodsUnitName(java.lang.String goodsUnitName)
    {
    this.goodsUnitName = goodsUnitName;
    }
    
    
    public java.lang.String getStockTransUserName() {
		return stockTransUserName;
	}

	public void setStockTransUserName(java.lang.String stockTransUserName) {
		this.stockTransUserName = stockTransUserName;
	}

	public java.lang.String getStockTransDescription() {
		return stockTransDescription;
	}

	public void setStockTransDescription(java.lang.String stockTransDescription) {
		this.stockTransDescription = stockTransDescription;
	}

	public java.lang.Long getGoodsUnitId(){
    return goodsUnitId;
    }
    public void setGoodsUnitId(java.lang.Long goodsUnitId)
    {
    this.goodsUnitId = goodsUnitId;
    }
    
    public java.lang.Double getAmountTotal(){
    return amountTotal;
    }
    public void setAmountTotal(java.lang.Double amountTotal)
    {
    this.amountTotal = amountTotal;
    }
    
    public java.lang.Double getTotalPrice(){
    return totalPrice;
    }
    public void setTotalPrice(java.lang.Double totalPrice)
    {
    this.totalPrice = totalPrice;
    }
    
    public java.util.Date getCreateDateTime(){
    return createDateTime;
    }
    public void setCreateDateTime(java.util.Date createDateTime)
    {
    this.createDateTime = createDateTime;
    }

	public List<String> getListStockTransType() {
		return listStockTransType;
	}

	public void setListStockTransType(List<String> listStockTransType) {
		this.listStockTransType = listStockTransType;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public java.util.Date getRemainDate() {
		return remainDate;
	}

	public void setRemainDate(java.util.Date remainDate) {
		this.remainDate = remainDate;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.Double getAmountTotalImport() {
		return amountTotalImport;
	}

	public void setAmountTotalImport(java.lang.Double amountTotalImport) {
		this.amountTotalImport = amountTotalImport;
	}

	public java.lang.Double getAmountTotalExport() {
		return amountTotalExport;
	}

	public void setAmountTotalExport(java.lang.Double amountTotalExport) {
		this.amountTotalExport = amountTotalExport;
	}

	public java.lang.Double getAmountFinal() {
		return amountFinal;
	}

	public void setAmountFinal(java.lang.Double amountFinal) {
		this.amountFinal = amountFinal;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}

    
}
