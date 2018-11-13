/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.KpiStorageAmountBO;
import com.viettel.wms.bo.KpiStorageTimeBO;
import com.viettel.wms.bo.StockTransBO;
import com.viettel.wms.dto.StockGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 *
 * @author: VietLT
 * @version: 1.0
 * @since: 1.0
 */
@XmlRootElement(name = "KPI_STORAGE_AMOUNTBO")
public class KpiStorageAmountDTO extends wmsBaseDTO<KpiStorageAmountBO> {
	private java.lang.String code;
	private String reportName;
	private String reportType;
	private java.lang.Long kpiStorageId;
	private java.lang.Long stockId;
	private java.lang.String stockCode;
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
	private java.lang.String stockName;
	private java.util.List<String> listGoodsType;
	private java.lang.String typeKpi;
	private java.lang.String name;
	private java.util.List<Long> listStockId;
	
	
	public java.lang.String getName() {
		return name;
	}



	public void setName(java.lang.String name) {
		this.name = name;
	}



	public java.lang.String getCode() {
		return code;
	}



	public void setCode(java.lang.String code) {
		this.code = code;
	}



	public String getReportName() {
		return reportName;
	}



	public void setReportName(String reportName) {
		this.reportName = reportName;
	}



	public String getReportType() {
		return reportType;
	}



	public void setReportType(String reportType) {
		this.reportType = reportType;
	}



	public java.lang.String getTypeKpi() {
		return typeKpi;
	}



	public void setTypeKpi(java.lang.String typeKPI) {
		this.typeKpi = typeKpi;
	}



	public java.util.List<String> getListGoodsType() {
		return listGoodsType;
	}



	public void setListGoodsType(java.util.List<String> listGoodsType) {
		this.listGoodsType = listGoodsType;
	}



	public java.lang.String getStockName() {
		return stockName;
	}



	public void setStockName(java.lang.String stockName) {
		this.stockName = stockName;
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



	public java.lang.String getGoodsTypeName() {
		return goodsTypeName;
	}



	public void setGoodsTypeName(java.lang.String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
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



	public java.lang.String getGoodsState() {
		return goodsState;
	}



	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}



	public java.lang.String getGoodsUnitName() {
		return goodsUnitName;
	}



	public void setGoodsUnitName(java.lang.String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	
	public java.lang.Long getKpiStorageId() {
		return kpiStorageId;
	}



	public void setKpiStorageId(java.lang.Long kpiStorageId) {
		this.kpiStorageId = kpiStorageId;
	}



	public java.lang.String getStockCode() {
		return stockCode;
	}



	public void setStockCode(java.lang.String stockCode) {
		this.stockCode = stockCode;
	}



	public java.lang.String getGoodsStateName() {
		return goodsStateName;
	}



	public void setGoodsStateName(java.lang.String goodsStateName) {
		this.goodsStateName = goodsStateName;
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



	public void setGoodsIsSerial(java.lang.String goodsIsSerial) {
		this.goodsIsSerial = goodsIsSerial;
	}



	public java.lang.Long getGoodsUnitId() {
		return goodsUnitId;
	}



	public void setGoodsUnitId(java.lang.Long goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}



	public java.lang.Double getAmountRemain() {
		return amountRemain;
	}



	public void setAmountRemain(java.lang.Double amountRemain) {
		this.amountRemain = amountRemain;
	}



	public java.lang.Double getAmountQuato() {
		return amountQuato;
	}



	public void setAmountQuato(java.lang.Double amountQuato) {
		this.amountQuato = amountQuato;
	}



	public java.lang.Double getAmountKpi() {
		return amountKpi;
	}



	public void setAmountKpi(java.lang.Double amountKpi) {
		this.amountKpi = amountKpi;
	}



	public java.util.Date getChangeDate() {
		return changeDate;
	}



	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
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
	public KpiStorageAmountBO toModel() {
		KpiStorageAmountBO kpiStorageAmountBO = new KpiStorageAmountBO();
		
		kpiStorageAmountBO.setGoodsTypeName(this.goodsTypeName);
		kpiStorageAmountBO.setAmountKpi(this.amountKpi);
		kpiStorageAmountBO.setAmountQuato(this.amountQuato);
		kpiStorageAmountBO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageAmountBO.setGoodsCode(this.goodsCode);
		kpiStorageAmountBO.setGoodsName(this.goodsName);
		kpiStorageAmountBO.setGoodsIsSerial(this.goodsIsSerial);
		kpiStorageAmountBO.setChangeDate(this.changeDate);
		kpiStorageAmountBO.setAmountRemain(this.amountRemain);
		kpiStorageAmountBO.setGoodsStateName(this.goodsStateName);
		kpiStorageAmountBO.setStockCode(this.stockCode);
		kpiStorageAmountBO.setStockName(this.stockName);
		kpiStorageAmountBO.setStockId(this.stockId);
		kpiStorageAmountBO.setGoodsUnitId(this.goodsUnitId);
		kpiStorageAmountBO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageAmountBO.setKpiStorageId(this.kpiStorageId);
		kpiStorageAmountBO.setGoodsId(this.goodsId);
		kpiStorageAmountBO.setGoodsState(this.goodsState);
		
		return kpiStorageAmountBO;
	}
}
