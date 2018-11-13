/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockGoodsTotalReponseDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_GOODS_TOTAL_REPONSE")
/**
 *
 * @author: TuanNB
 * @version: 1.0
 * @since: 1.0
 */
public class StockGoodsTotalReponseBO extends BaseFWModelImpl {
	
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

	public StockGoodsTotalReponseBO() {
		setColId("stockGoodsTotalReponseId");
		setColName("stockGoodsTotalReponseId");
		setUniqueColumn(new String[] { "stockGoodsTotalReponseId" });
	}
	@Id
	@GeneratedValue(generator = "sequence")
	    @GenericGenerator(name = "sequence", strategy = "sequence",
	            parameters = {
	                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_GOODS_TOTAL_REPONSE_SEQ")
	            }
	    )
	@Column(name = "STOCK_GOODS_TOTAL_REPONSE_ID", length = 22)
	public java.lang.Long getStockGoodsTotalReponseId() {
		return stockGoodsTotalReponseId;
	}
	public void setStockGoodsTotalReponseId(java.lang.Long stockGoodsTotalReponseId) {
		this.stockGoodsTotalReponseId = stockGoodsTotalReponseId;
	}
	
	@Column(name = "STOCK_ID", length = 22)
	public java.lang.Long getStockId() {
		return stockId;
	}
	
	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}
	
	@Column(name = "STOCK_CODE", length = 50)
	public java.lang.String getStockCode() {
		return stockCode;
	}
	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}
	
	@Column(name = "STOCK_NAME", length = 500)
	public java.lang.String getStockName() {
		return stockName;
	}
	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
	}
	
	@Column(name = "GOODS_ID", length = 22)
	public java.lang.Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name = "GOODS_STATE", length = 2)
	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}
	
	@Column(name = "GOODS_STATE_NAME", length = 100)
	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}
	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}
	
	@Column(name = "GOODS_CODE", length = 20)
	public java.lang.String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(java.lang.String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	@Column(name = "GOODS_NAME", length = 500)
	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Column(name = "GOODS_TYPE", length = 2)
	public java.lang.String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(java.lang.String goodsType) {
		this.goodsType = goodsType;
	}
	
	@Column(name = "GOODS_TYPE_NAME", length = 100)
	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	
	@Column(name = "GOODS_IS_SERIAL", length = 1)
	public java.lang.String getGoodsIsSerial() {
		return goodsIsSerial;
	}
	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}
	
	@Column(name = "GOODS_UNIT_ID", length = 10)
	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}
	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}
	
	@Column(name = "GOODS_UNIT_NAME", length = 50)
	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}
	
	@Column(name = "AMOUNT_REMAIN", length = 22)
	public java.lang.Double getAmountRemain() {
		return amountRemain;
	}
	public void setAmountRemain(java.lang.Double amountRemain) {
		this.amountRemain = amountRemain;
	}
	@Column(name = "AMOUNT_ORDER", length = 22)
	public java.lang.Double getAmountOrder() {
		return amountOrder;
	}
	public void setAmountOrder(java.lang.Double amountOrder) {
		this.amountOrder = amountOrder;
	}
	@Column(name = "AMOUNT_ISSUE", length = 22)
	public java.lang.Double getAmountIssue() {
		return amountIssue;
	}
	public void setAmountIssue(java.lang.Double amountIssue) {
		this.amountIssue = amountIssue;
	}
	@Column(name = "CHANGE_DATE", length = 7)
	public java.util.Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}
	@Override
	public StockGoodsTotalReponseDTO toDTO() {
		StockGoodsTotalReponseDTO stockGoodsTotalReponseDTO = new StockGoodsTotalReponseDTO();
		// set cac gia tri
		stockGoodsTotalReponseDTO.setStockGoodsTotalReponseId(this.stockGoodsTotalReponseId);
		stockGoodsTotalReponseDTO.setStockId(this.stockId);
		stockGoodsTotalReponseDTO.setStockCode(this.stockCode);
		stockGoodsTotalReponseDTO.setStockName(this.stockName);
		stockGoodsTotalReponseDTO.setGoodsId(this.goodsId);
		stockGoodsTotalReponseDTO.setGoodsState(this.goodsState);
		stockGoodsTotalReponseDTO.setGoodsStateName(this.goodsStateName);
		stockGoodsTotalReponseDTO.setGoodsCode(this.goodsCode);
		stockGoodsTotalReponseDTO.setGoodsName(this.goodsName);
		stockGoodsTotalReponseDTO.setGoodsType(this.goodsType);
		stockGoodsTotalReponseDTO.setGoodsTypeName(this.goodsTypeName);
		stockGoodsTotalReponseDTO.setGoodsIsSerial(this.goodsIsSerial);
		stockGoodsTotalReponseDTO.setGoodsUnitId(this.goodsUnitId);
		stockGoodsTotalReponseDTO.setGoodsUnitName(this.goodsUnitName);
		stockGoodsTotalReponseDTO.setAmountRemain(this.amountRemain);
		stockGoodsTotalReponseDTO.setAmountOrder(this.amountOrder);
		stockGoodsTotalReponseDTO.setAmountIssue(this.amountIssue);
		stockGoodsTotalReponseDTO.setChangeDate(this.changeDate);
		return stockGoodsTotalReponseDTO;
	}
}
