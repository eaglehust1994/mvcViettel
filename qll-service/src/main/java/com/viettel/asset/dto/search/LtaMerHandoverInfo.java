package com.viettel.asset.dto.search;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateSerializer;

public class LtaMerHandoverInfo {
	private Long handoverId;
	private String code;
	private String catWarehouseName;
	private String sysGroupName;
	private String receiverName;
	@JsonSerialize(using=CustomJsonDateSerializer.class)
	private java.util.Date handoverDate;
	
	public Long getHandoverId() {
		return handoverId;
	}
	public void setHandoverId(Long handoverId) {
		this.handoverId = handoverId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCatWarehouseName() {
		return catWarehouseName;
	}
	public void setCatWarehouseName(String catWarehouseName) {
		this.catWarehouseName = catWarehouseName;
	}
	public String getSysGroupName() {
		return sysGroupName;
	}
	public void setSysGroupName(String sysGroupName) {
		this.sysGroupName = sysGroupName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public java.util.Date getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}
	
	//private List<>
}
