/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

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
@Table(name = "WMS_OWNER_KTTS.KPI_STORAGE_TIME")
/**
 *
 * @author: VietLT
 * @version: 1.0
 * @since: 1.0
 */
public class KpiStorageTimeBO extends BaseFWModelImpl {
	
	private java.lang.Long stockGoodsSerialId;
	private java.lang.Long stockId;
	private java.lang.Long goodsId;
	private java.lang.String goodsTypeName;
	private java.lang.String goodsCode;
	private java.lang.String goodsName;
	private java.lang.String goodsState;
	private java.lang.String goodsUnitName;
	private java.lang.String contractCode;
	private java.lang.String projectCode;
	private java.lang.Double amount;
	private java.lang.String serial;
	private java.lang.Long timeQuato;
	private java.lang.Long timeStorage;
	private java.lang.Long timeKpi;

	public KpiStorageTimeBO() {
		setColId("stockGoodsSerialId");
		setColName("stockGoodsSerialId");
		setUniqueColumn(new String[] { "stockGoodsSerialId" });
	}
	@Id
	@GeneratedValue(generator = "sequence")
	    @GenericGenerator(name = "sequence", strategy = "sequence",
	            parameters = {
	                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.KPI_STORAGE_TIME_SEQ")
	            }
	    )
	@Column(name = "STOCK_GOODS_SERIAL_ID", length = 22)
	public java.lang.Long getStockGoodsSerialId() {
		return stockGoodsSerialId;
	}

	public void setStockGoodsSerialId(java.lang.Long stockGoodsSerialId) {
		this.stockGoodsSerialId = stockGoodsSerialId;
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
	@Column(name = "CONTRACT_CODE", length = 100)
	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}
	@Column(name = "PROJECT_CODE", length = 100)
	public java.lang.String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(java.lang.String projectCode) {
		this.projectCode = projectCode;
	}
	@Column(name = "AMOUNT", length = 22)
	public java.lang.Double getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}
	@Column(name = "SERIAL", length = 100)
	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}
	@Column(name = "TIME_QUATO", length = 22)
	public java.lang.Long getTimeQuato() {
		return timeQuato;
	}

	public void setTimeQuato(java.lang.Long timeQuato) {
		this.timeQuato = timeQuato;
	}
	@Column(name = "TIME_STORAGE", length = 22)
	public java.lang.Long getTimeStorage() {
		return timeStorage;
	}

	public void setTimeStorage(java.lang.Long timeStorage) {
		this.timeStorage = timeStorage;
	}
	@Column(name = "TIME_KPI", length = 22)
	public java.lang.Long getTimeKpi() {
		return timeKpi;
	}

	public void setTimeKpi(java.lang.Long timeKpi) {
		this.timeKpi = timeKpi;
	}

	@Override
	public KpiStorageTimeDTO toDTO() {
		KpiStorageTimeDTO kpiStorageTimeDTO = new KpiStorageTimeDTO();
		// set cac gia tri
		kpiStorageTimeDTO.setGoodsTypeName(this.goodsTypeName);
		kpiStorageTimeDTO.setAmount(this.amount);
		kpiStorageTimeDTO.setContractCode(this.contractCode);
		kpiStorageTimeDTO.setGoodsUnitName(this.goodsUnitName);
		kpiStorageTimeDTO.setGoodsCode(this.goodsCode);
		kpiStorageTimeDTO.setGoodsName(this.goodsName);
		kpiStorageTimeDTO.setProjectCode(this.projectCode);
		kpiStorageTimeDTO.setTimeKpi(this.timeKpi);
		kpiStorageTimeDTO.setTimeQuato(this.timeQuato);
		kpiStorageTimeDTO.setTimeStorage(this.timeStorage);
		kpiStorageTimeDTO.setSerial(this.serial);
		kpiStorageTimeDTO.setGoodsState(this.goodsState);
		
		return kpiStorageTimeDTO;
	}
}
