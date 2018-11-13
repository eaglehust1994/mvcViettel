/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializerDateFormat;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.wms.bo.StockDailyRemainBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_DAILY_REMAINBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDailyRemainDTO extends wmsBaseDTO<StockDailyRemainBO> {

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

//++++

private String createdByName;
private String imCode;
private String exCode;
private String description;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = CustomJsonDateDeserializerDateFormat.class)
private Date realIeTransDate;
private Long imAmount;
private Long exAmount;

    @Override
    public StockDailyRemainBO toModel() {
        StockDailyRemainBO stockDailyRemainBO = new StockDailyRemainBO();
        stockDailyRemainBO.setStockDailyRemainId(this.stockDailyRemainId);
        stockDailyRemainBO.setRemainDate(this.remainDate);
        stockDailyRemainBO.setStockId(this.stockId);
        stockDailyRemainBO.setStockCode(this.stockCode);
        stockDailyRemainBO.setStockName(this.stockName);
        stockDailyRemainBO.setGoodsId(this.goodsId);
        stockDailyRemainBO.setGoodsGroup(this.goodsGroup);
        stockDailyRemainBO.setGoodsGroupName(this.goodsGroupName);
        stockDailyRemainBO.setGoodsState(this.goodsState);
        stockDailyRemainBO.setGoodsStateName(this.goodsStateName);
        stockDailyRemainBO.setGoodsCode(this.goodsCode);
        stockDailyRemainBO.setGoodsName(this.goodsName);
        stockDailyRemainBO.setGoodsType(this.goodsType);
        stockDailyRemainBO.setGoodsIsSerial(this.goodsIsSerial);
        stockDailyRemainBO.setGoodsUnitName(this.goodsUnitName);
        stockDailyRemainBO.setAmount(this.amount);
        stockDailyRemainBO.setTotalPrice(this.totalPrice);
        stockDailyRemainBO.setCreateDateTime(this.createDateTime);
        return stockDailyRemainBO;
    }

    @Override
     public Long getFWModelId() {
        return stockDailyRemainId;
    }
   
    @Override
    public String catchName() {
        return getStockDailyRemainId().toString();
    }
    public java.lang.Long getStockDailyRemainId(){
    return stockDailyRemainId;
    }
    public void setStockDailyRemainId(java.lang.Long stockDailyRemainId)
    {
    this.stockDailyRemainId = stockDailyRemainId;
    }
    
    public java.util.Date getRemainDate(){
    return remainDate;
    }
    public void setRemainDate(java.util.Date remainDate)
    {
    this.remainDate = remainDate;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.String getStockCode(){
    return stockCode;
    }
    public void setStockCode(java.lang.String stockCode)
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
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getGoodsGroup(){
    return goodsGroup;
    }
    public void setGoodsGroup(java.lang.String goodsGroup)
    {
    this.goodsGroup = goodsGroup;
    }
    
    public java.lang.String getGoodsGroupName(){
    return goodsGroupName;
    }
    public void setGoodsGroupName(java.lang.String goodsGroupName)
    {
    this.goodsGroupName = goodsGroupName;
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
    
    public java.lang.Long getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.Long goodsType)
    {
    this.goodsType = goodsType;
    }
    
    public java.lang.String getGoodsIsSerial(){
    return goodsIsSerial;
    }
    public void setGoodsIsSerial(java.lang.String goodsIsSerial)
    {
    this.goodsIsSerial = goodsIsSerial;
    }
    
    public java.lang.String getGoodsUnitName(){
    return goodsUnitName;
    }
    public void setGoodsUnitName(java.lang.String goodsUnitName)
    {
    this.goodsUnitName = goodsUnitName;
    }
    
    public java.lang.Double getAmount(){
    return amount;
    }
    public void setAmount(java.lang.Double amount)
    {
    this.amount = amount;
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
    
    
    public String getCreatedByName() {
    	return createdByName;
    }

    public void setCreatedByName(String createdByName) {
    	this.createdByName = createdByName;
    }

    public String getImCode() {
    	return imCode;
    }

    public void setImCode(String imCode) {
    	this.imCode = imCode;
    }

    public String getExCode() {
    	return exCode;
    }

    public void setExCode(String exCode) {
    	this.exCode = exCode;
    }

    public String getDescription() {
    	return description;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

    public Date getRealIeTransDate() {
    	return realIeTransDate;
    }

    public void setRealIeTransDate(Date realIeTransDate) {
    	this.realIeTransDate = realIeTransDate;
    }

    public Long getImAmount() {
    	return imAmount;
    }

    public void setImAmount(Long imAmount) {
    	this.imAmount = imAmount;
    }

    public Long getExAmount() {
    	return exAmount;
    }

    public void setExAmount(Long exAmount) {
    	this.exAmount = exAmount;
    }
   
}
