/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.KpiStorageAmountDTO;
import com.viettel.wms.dto.KpiStorageTimeDTO;
import com.viettel.wms.dto.StockGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.KPI_STORAGE_AMOUNT")
/**
 *
 * @author: VietLT
 * @version: 1.0
 * @since: 1.0
 */
public class KpiStorageAmountBO extends BaseFWModelImpl {
	
	private java.lang.Long kpiStorageId;
	private java.lang.Long stockId;
	private java.lang.String stockCode;
	private java.lang.String stockName;
	private java.lang.Long goodsId;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsState;
	private java.lang.String goodsStateName;
	private java.lang.String goodsUnitName;
	private java.lang.Long goodsType;
	private java.lang.String goodsTypeName;
	private java.lang.String goodsIsSerial;
	private java.lang.Long goodsUnitId;
	private java.lang.Double amountRemain;
	private java.lang.Double amountQuato;
	private java.lang.Double amountKpi;
	private java.util.Date changeDate;

	public KpiStorageAmountBO() {
		setColId("kpiStorageId");
		setColName("kpiStorageId");
		setUniqueColumn(new String[] { "kpiStorageId" });
	}
	@Id
	@GeneratedValue(generator = "sequence")
	    @GenericGenerator(name = "sequence", strategy = "sequence",
	            parameters = {
	                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.KPI_STORAGE_AMOUNT_SEQ")
	            }
	    )
	@Column(name = "KPI_STORAGE_ID", length = 22)
	public java.lang.Long getKpiStorageId() {
		return kpiStorageId;
	}

	public void setKpiStorageId(java.lang.Long kpiStorageId) {
		this.kpiStorageId = kpiStorageId;
	}
	@Column(name = "STOCK_ID", length = 22)
	public java.lang.Long getStockId() {
		return stockId;
	}
	public void setStockId(java.lang.Long stockId) {
		this.stockId = stockId;
	}
	@Column(name = "GOODS_ID", length = 22)
	public java.lang.Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}
	@Column(name = "GOODS_TYPE_NAME", length = 100)
	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
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
	@Column(name = "GOODS_STATE", length = 2)
	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}
	@Column(name = "GOODS_UNIT_NAME", length = 50)
	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
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
	@Column(name = "GOODS_STATE_NAME", length = 100)
	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}
	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}
	@Column(name = "GOODS_TYPE", length = 10)
	public java.lang.Long getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(java.lang.Long goodsType) {
		this.goodsType = goodsType;
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
	@Column(name = "AMOUNT_REMAIN", length = 22)
	public java.lang.Double getAmountRemain() {
		return amountRemain;
	}
	public void setAmountRemain(java.lang.Double amountRemain) {
		this.amountRemain = amountRemain;
	}
	@Column(name = "AMOUNT_QUATO", length = 22)
	public java.lang.Double getAmountQuato() {
		return amountQuato;
	}
	public void setAmountQuato(java.lang.Double amountQuato) {
		this.amountQuato = amountQuato;
	}
	@Column(name = "AMOUNT_KPI", length = 22)
	public java.lang.Double getAmountKpi() {
		return amountKpi;
	}
	public void setAmountKpi(java.lang.Double amountKpi) {
		this.amountKpi = amountKpi;
	}
	@Column(name = "CHANGE_DATE", length = 7)
	public java.util.Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}
	@Override
	public KpiStorageAmountDTO toDTO() {
		KpiStorageAmountDTO kpiStorageAmountDTO = new KpiStorageAmountDTO();
		// set cac gia tri
		kpiStorageAmountDTO.setGoodsTypeName(this.goodsTypeName);
		kpiStorageAmountDTO.setAmountKpi(this.amountKpi);
		kpiStorageAmountDTO.setAmountQuato(this.amountQuato);
		kpiStorageAmountDTO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageAmountDTO.setGoodsCode(this.goodsCode);
		kpiStorageAmountDTO.setGoodsName(this.goodsName);
		kpiStorageAmountDTO.setGoodsIsSerial(this.goodsIsSerial);
		kpiStorageAmountDTO.setChangeDate(this.changeDate);
		kpiStorageAmountDTO.setAmountRemain(this.amountRemain);
		kpiStorageAmountDTO.setGoodsStateName(this.goodsStateName);
		kpiStorageAmountDTO.setStockCode(this.stockCode);
		kpiStorageAmountDTO.setStockName(this.stockName);
		kpiStorageAmountDTO.setStockId(this.stockId);
		kpiStorageAmountDTO.setGoodsUnitId(this.goodsUnitId);
		kpiStorageAmountDTO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageAmountDTO.setKpiStorageId(this.kpiStorageId);
		kpiStorageAmountDTO.setGoodsId(this.goodsId);
		kpiStorageAmountDTO.setGoodsState(this.goodsState);
		return kpiStorageAmountDTO;
	}
}
