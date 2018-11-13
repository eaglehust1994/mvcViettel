/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.dto;

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
@XmlRootElement(name = "KPI_STORAGE_TIMEBO")
public class KpiStorageTimeDTO extends wmsBaseDTO<KpiStorageTimeBO> {
	
	private java.lang.String name;
	private java.lang.String code;
	private java.lang.Long stockGoodsSerialId;
	private java.lang.Long stockId;
	private java.lang.Long goodsId;
	private java.lang.Long goodsType;
	private java.lang.String goodsTypeName;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsState;
	private java.lang.String goodsStateName;
	private java.lang.String goodsUnitName;
	private java.lang.String contractCode;
	private java.lang.String projectCode;
	private java.lang.Double amount;
	private java.lang.String serial;
	private java.lang.Long timeQuato;
	private java.lang.Long timeStorage;
	private java.lang.Long timeKpi;
	private java.lang.String stockName;
	private java.lang.String stockCode;
	private java.util.List<String> listGoodsType;
	private java.lang.String typeKpi;
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



	public java.lang.Long getStockGoodsSerialId() {
		return stockGoodsSerialId;
	}



	public void setStockGoodsSerialId(java.lang.Long stockGoodsSerialId) {
		this.stockGoodsSerialId = stockGoodsSerialId;
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



	public java.lang.String getContractCode() {
		return contractCode;
	}



	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}



	public java.lang.String getProjectCode() {
		return projectCode;
	}



	public void setProjectCode(java.lang.String projectCode) {
		this.projectCode = projectCode;
	}



	public java.lang.Double getAmount() {
		return amount;
	}



	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}



	public java.lang.String getSerial() {
		return serial;
	}



	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}



	public java.lang.Long getTimeQuato() {
		return timeQuato;
	}



	public void setTimeQuato(java.lang.Long timeQuato) {
		this.timeQuato = timeQuato;
	}



	public java.lang.Long getTimeStorage() {
		return timeStorage;
	}



	public void setTimeStorage(java.lang.Long timeStorage) {
		this.timeStorage = timeStorage;
	}



	public java.lang.Long getTimeKpi() {
		return timeKpi;
	}



	public void setTimeKpi(java.lang.Long timeKpi) {
		this.timeKpi = timeKpi;
	}


	public java.lang.Long getGoodsType() {
		return goodsType;
	}



	public void setGoodsType(java.lang.Long goodsType) {
		this.goodsType = goodsType;
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
	public KpiStorageTimeBO toModel() {
		KpiStorageTimeBO kpiStorageTimeBO = new KpiStorageTimeBO();
		
		kpiStorageTimeBO.setGoodsTypeName(this.goodsTypeName);
		kpiStorageTimeBO.setGoodsCode(this.goodsCode);
		kpiStorageTimeBO.setGoodsName(this.goodsName);
		kpiStorageTimeBO.setGoodsState(this.goodsState);
		kpiStorageTimeBO.setAmount(this.amount);
		kpiStorageTimeBO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageTimeBO.setProjectCode(this.projectCode);
		kpiStorageTimeBO.setContractCode(this.contractCode);
		kpiStorageTimeBO.setTimeKpi(this.timeKpi);
		kpiStorageTimeBO.setTimeQuato(this.timeQuato);
		kpiStorageTimeBO.setTimeStorage(this.timeStorage);
		kpiStorageTimeBO.setSerial(this.serial);
		
		
		return kpiStorageTimeBO;
	}
}
