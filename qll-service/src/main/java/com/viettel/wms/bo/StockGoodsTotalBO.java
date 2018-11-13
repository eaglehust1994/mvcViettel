/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockGoodsTotalDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_GOODS_TOTAL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockGoodsTotalBO extends BaseFWModelImpl {

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

	public StockGoodsTotalBO() {
		setColId("stockGoodsTotalId");
		setColName("stockGoodsTotalId");
		setUniqueColumn(new String[] { "stockGoodsTotalId" });
	}

	@Column(name = "GOODS_TYPE_NAME")
	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_GOODS_TOTAL_SEQ") })
	@Column(name = "STOCK_GOODS_TOTAL_ID")
	public java.lang.Long getStockGoodsTotalId() {
		return stockGoodsTotalId;
	}

	public void setStockGoodsTotalId(java.lang.Long stockGoodsTotalId) {
		this.stockGoodsTotalId = stockGoodsTotalId;
	}

	@Column(name = "STOCK_ID")
	public java.lang.Long getStockId() {
		return stockId;
	}

	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}

	@Column(name = "GOODS_ID")
	public java.lang.Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "GOODS_STATE")
	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}

	@Column(name = "GOODS_STATE_NAME")
	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	@Column(name = "GOODS_CODE")
	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "GOODS_NAME")
	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "GOODS_TYPE")
	public java.lang.Long getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(java.lang.Long goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name = "GOODS_IS_SERIAL")
	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}

	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}

	@Column(name = "GOODS_UNIT_ID")
	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}

	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}

	@Column(name = "GOODS_UNIT_NAME")
	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	@Column(name = "AMOUNT")
	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	@Column(name = "CHANGE_DATE")
	public java.util.Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}

	@Column(name = "AMOUNT_ISSUE")
	public java.lang.Double getAmountIssue() {
		return amountIssue;
	}

	public void setAmountIssue(java.lang.Double amountIssue) {
		this.amountIssue = amountIssue;
	}

	@Column(name = "STOCK_CODE")
	public java.lang.String getStockCode() {
		return stockCode;
	}

	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}

	@Column(name = "STOCK_NAME")
	public java.lang.String getStockName() {
		return stockName;
	}

	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}

	@Override
	public StockGoodsTotalDTO toDTO() {
		StockGoodsTotalDTO stockGoodsTotalDTO = new StockGoodsTotalDTO();
		// set cac gia tri
		stockGoodsTotalDTO.setGoodsTypeName(this.goodsTypeName);
		stockGoodsTotalDTO.setStockGoodsTotalId(this.stockGoodsTotalId);
		stockGoodsTotalDTO.setStockId(this.stockId);
		stockGoodsTotalDTO.setGoodsId(this.goodsId);
		stockGoodsTotalDTO.setGoodsState(this.goodsState);
		stockGoodsTotalDTO.setGoodsStateName(this.goodsStateName);
		stockGoodsTotalDTO.setGoodsCode(this.goodsCode);
		stockGoodsTotalDTO.setGoodsName(this.goodsName);
		stockGoodsTotalDTO.setStockCode(this.stockCode);
		stockGoodsTotalDTO.setStockName(this.stockName);
		stockGoodsTotalDTO.setGoodsType(this.goodsType);
		stockGoodsTotalDTO.setGoodsIsSerial(this.goodsIsSerial);
		stockGoodsTotalDTO.setGoodsUnitId(this.goodsUnitId);
		stockGoodsTotalDTO.setGoodsUnitName(this.goodsUnitName);
		stockGoodsTotalDTO.setAmount(this.amount);
		stockGoodsTotalDTO.setChangeDate(this.changeDate);
		stockGoodsTotalDTO.setAmountIssue(this.amountIssue);
		return stockGoodsTotalDTO;
	}
}
