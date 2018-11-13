/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.viettel.wms.bo.StockGoodsTotalReponseBO;


/**
 *
 * @author: TuanNB
 * @version: 1.0
 * @since: 1.0
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "STOCK_GOODS_TOTAL_REPONSEBO")
public class StockGoodsTotalReponseDTO extends wmsBaseDTO<StockGoodsTotalReponseBO> {
	
	private java.lang.Long stockGoodsTotalReponseId;
	private java.lang.Long stockId;
	private java.lang.String stockCode;
	private java.lang.String stockName;
	private java.lang.Long goodsId;
	private java.lang.String goodsState;
	private java.lang.String goodsStateName;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsType;
	private java.lang.String goodsTypeName;
	private java.lang.String goodsIsSerial;
	private java.lang.Long goodsUnitId;
	private java.lang.String goodsUnitName;
	private java.lang.Double amountRemain;
	private java.lang.Double amountOrder;
	private java.lang.Double amountIssue;
	private java.util.Date changeDate;
	private java.util.List<Long> listGoodsType;
	private java.lang.String reponseStatus;
	private java.lang.String code;
	private java.lang.String name;
	private java.util.List<Long> listStockId;
	
	public java.lang.Long getStockGoodsTotalReponseId() {
		return stockGoodsTotalReponseId;
	}

	public void setStockGoodsTotalReponseId(java.lang.Long stockGoodsTotalReponseId) {
		this.stockGoodsTotalReponseId = stockGoodsTotalReponseId;
	}

	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}

	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
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

	public java.lang.String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(java.lang.String goodsType) {
		this.goodsType = goodsType;
	}

	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}

	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
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

	public java.lang.Double getAmountRemain() {
		return amountRemain;
	}

	public void setAmountRemain(java.lang.Double amountRemain) {
		this.amountRemain = amountRemain;
	}

	public java.lang.Double getAmountOrder() {
		return amountOrder;
	}

	public void setAmountOrder(java.lang.Double amountOrder) {
		this.amountOrder = amountOrder;
	}

	public java.lang.Double getAmountIssue() {
		return amountIssue;
	}

	public void setAmountIssue(java.lang.Double amountIssue) {
		this.amountIssue = amountIssue;
	}

	public java.util.Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}

	public java.util.List<Long> getListGoodsType() {
		return listGoodsType;
	}

	public void setListGoodsType(java.util.List<Long> listGoodsType) {
		this.listGoodsType = listGoodsType;
	}

	public java.lang.String getReponseStatus() {
		return reponseStatus;
	}

	public void setReponseStatus(java.lang.String reponseStatus) {
		this.reponseStatus = reponseStatus;
	}
	
	
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.List<Long> getListStockId() {
		return listStockId;
	}

	public void setListStockId(java.util.List<Long> listStockId) {
		this.listStockId = listStockId;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockGoodsTotalReponseBO toModel() {
		StockGoodsTotalReponseBO stockGoodsTotalReponseBO = new StockGoodsTotalReponseBO();
		
		stockGoodsTotalReponseBO.setStockGoodsTotalReponseId(this.stockGoodsTotalReponseId);
		stockGoodsTotalReponseBO.setStockId(this.stockId);
		stockGoodsTotalReponseBO.setStockCode(this.stockCode);
		stockGoodsTotalReponseBO.setStockName(this.stockName);
		stockGoodsTotalReponseBO.setGoodsId(this.goodsId);
		stockGoodsTotalReponseBO.setGoodsState(this.goodsState);
		stockGoodsTotalReponseBO.setGoodsStateName(this.goodsStateName);
		stockGoodsTotalReponseBO.setGoodsCode(this.goodsCode);
		stockGoodsTotalReponseBO.setGoodsName(this.goodsName);
		stockGoodsTotalReponseBO.setGoodsType(this.goodsType);
		stockGoodsTotalReponseBO.setGoodsTypeName(this.goodsTypeName);
		stockGoodsTotalReponseBO.setGoodsIsSerial(this.goodsIsSerial);
		stockGoodsTotalReponseBO.setGoodsUnitId(this.goodsUnitId);
		stockGoodsTotalReponseBO.setGoodsUnitName(this.goodsUnitName);
		stockGoodsTotalReponseBO.setAmountRemain(this.amountRemain);
		stockGoodsTotalReponseBO.setAmountOrder(this.amountOrder);
		stockGoodsTotalReponseBO.setAmountIssue(this.amountIssue);
		stockGoodsTotalReponseBO.setChangeDate(this.changeDate);
		
		return stockGoodsTotalReponseBO;
	}
}
