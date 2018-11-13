/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.StockGoodsTotalBO;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_GOODS_TOTALBO")
public class StockGoodsTotalDTO extends wmsBaseDTO<StockGoodsTotalBO> {

	private java.lang.Long stockGoodsTotalId;
	private java.lang.Long stockId;
	private java.lang.Long goodsId;
	private java.lang.String goodsState;
	private java.lang.String goodsStateName;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.Long goodsType;
	private java.lang.String goodsIsSerial;
	private java.lang.Long goodsUnitId;
	private java.lang.String goodsUnitName;
	private java.lang.Double amount;
	private java.util.Date changeDate;
	private java.lang.String goodsTypeName;
	private java.lang.Double amountIssue;
	private java.lang.String stockCode;
	private java.lang.String stockName;

	private java.lang.String partNumber;
	private java.lang.String manufacturerName;
	private java.lang.String producingCountryName;
	private java.lang.String createFromStockTrade;
	private java.lang.String serial;
	private java.lang.String contractCode;
	private java.lang.String createToStockTrade;
	private java.lang.String name;
	private java.lang.String code;
	
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date startDate;

	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date endDate;
	
	 private java.util.List<Long> listGoodsType; 
	 private List<Long> listStockId;
	@Override
	public StockGoodsTotalBO toModel() {
		StockGoodsTotalBO stockGoodsTotalBO = new StockGoodsTotalBO();
		stockGoodsTotalBO.setGoodsTypeName(this.goodsTypeName);
		stockGoodsTotalBO.setStockGoodsTotalId(this.stockGoodsTotalId);
		stockGoodsTotalBO.setStockId(this.stockId);
		stockGoodsTotalBO.setStockName(this.stockName);
		stockGoodsTotalBO.setStockCode(this.stockCode);
		stockGoodsTotalBO.setGoodsId(this.goodsId);
		stockGoodsTotalBO.setGoodsState(this.goodsState);
		stockGoodsTotalBO.setGoodsStateName(this.goodsStateName);
		stockGoodsTotalBO.setGoodsCode(this.goodsCode);
		stockGoodsTotalBO.setGoodsName(this.goodsName);
		stockGoodsTotalBO.setGoodsType(this.goodsType);
		stockGoodsTotalBO.setGoodsIsSerial(this.goodsIsSerial);
		stockGoodsTotalBO.setGoodsUnitId(this.goodsUnitId);
		stockGoodsTotalBO.setGoodsUnitName(this.goodsUnitName);
		stockGoodsTotalBO.setAmount(this.amount);
		stockGoodsTotalBO.setChangeDate(this.changeDate);
		stockGoodsTotalBO.setAmountIssue(this.amountIssue);
		return stockGoodsTotalBO;
	}

	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	@Override
	public Long getFWModelId() {
		return stockGoodsTotalId;
	}

	@Override
	public String catchName() {
		return getStockGoodsTotalId().toString();
	}


	public java.lang.Long getStockGoodsTotalId() {
		return stockGoodsTotalId;
	}

	public void setStockGoodsTotalId(java.lang.Long stockGoodsTotalId) {
		this.stockGoodsTotalId = stockGoodsTotalId;
	}

	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	public java.lang.Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}

	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public java.lang.Long getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(java.lang.Long goodsType) {
		this.goodsType = goodsType;
	}

	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}

	public void setGoodsIsSerial(String string) {
		this.goodsIsSerial = string;
	}

	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}

	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.util.Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}

	public java.lang.Double getAmountIssue() {
		return amountIssue;
	}

	public void setAmountIssue(java.lang.Double amountIssue) {
		this.amountIssue = amountIssue;
	}

	
	 public java.util.List<Long> getListGoodsType() { return listGoodsType; }
	 
	 public void setListGoodsType(java.util.List<Long> listGoodsType) {
	 this.listGoodsType = listGoodsType; }
	

	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(java.lang.String partNumber) {
		this.partNumber = partNumber;
	}

	public java.lang.String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(java.lang.String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public java.lang.String getProducingCountryName() {
		return producingCountryName;
	}

	public void setProducingCountryName(java.lang.String producingCountryName) {
		this.producingCountryName = producingCountryName;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}

	public java.lang.String getCreateFromStockTrade() {
		return createFromStockTrade;
	}

	public void setCreateFromStockTrade(java.lang.String createFromStockTrade) {
		this.createFromStockTrade = createFromStockTrade;
	}

	public java.lang.String getCreateToStockTrade() {
		return createToStockTrade;
	}

	public void setCreateToStockTrade(java.lang.String createToStockTrade) {
		this.createToStockTrade = createToStockTrade;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
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

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}
	
	
}
