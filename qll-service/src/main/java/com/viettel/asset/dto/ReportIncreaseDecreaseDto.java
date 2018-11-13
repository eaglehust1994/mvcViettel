package com.viettel.asset.dto;

public class ReportIncreaseDecreaseDto {
	private String stt;
	private Long sttNhom;
	private Long sttLoai;
	
	private Long increaseOriginalPrice;
	private Long decreaseOriginalPrice;
	private Long depreciatedValue;
	
	private Long startOriginalPrice;
	private Long startResidualPrice;
	private Long startDepreciatedValue;
	private Long endOriginalPrice;
	private Long endResidualPrice;
	private Long endDepreciatedValue;
	
	private Long startOriginalPriceSync;
	private Long startResidualPriceSync;
	private Long startDepreciatedValueSync;
	private Long endOriginalPriceSync;
	private Long endResidualPriceSync;
	private Long endDepreciatedValueSync;
	
	private Long startOriginalPriceTotal;
	private Long startResidualPriceTotal;
	private Long startDepreciatedValueTotal;
	private Long endOriginalPriceTotal;
	private Long endResidualPriceTotal;
	private Long endDepreciatedValueTotal;
	
	private String caacName;
	private Long caacLevel;
	private String code;// ma code
	
	private Long groupId;
	private String groupCode;
	private String groupName;
	
	private Long depreciationTime;
	
	public String getDepreciationTime() {
		if(depreciationTime==null){
			return "";
		}
		return depreciationTime.toString();
	}
	public void setDepreciationTime(Long depreciationTime) {
		this.depreciationTime = depreciationTime;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public Long getSttNhom() {
		return sttNhom;
	}
	public void setSttNhom(Long sttNhom) {
		this.sttNhom = sttNhom;
	}
	public Long getSttLoai() {
		return sttLoai;
	}
	public void setSttLoai(Long sttLoai) {
		this.sttLoai = sttLoai;
	}
	public Long getIncreaseOriginalPrice() {
		return increaseOriginalPrice;
	}
	public void setIncreaseOriginalPrice(Long increaseOriginalPrice) {
		this.increaseOriginalPrice = increaseOriginalPrice;
	}
	public Long getDecreaseOriginalPrice() {
		return decreaseOriginalPrice;
	}
	public void setDecreaseOriginalPrice(Long decreaseOriginalPrice) {
		this.decreaseOriginalPrice = decreaseOriginalPrice;
	}
	public Long getDepreciatedValue() {
		return depreciatedValue;
	}
	public void setDepreciatedValue(Long depreciatedValue) {
		this.depreciatedValue = depreciatedValue;
	}
	public Long getStartOriginalPrice() {
		return startOriginalPrice;
	}
	public void setStartOriginalPrice(Long startOriginalPrice) {
		this.startOriginalPrice = startOriginalPrice;
	}
	public Long getStartResidualPrice() {
		return startResidualPrice;
	}
	public void setStartResidualPrice(Long startResidualPrice) {
		this.startResidualPrice = startResidualPrice;
	}
	public Long getStartDepreciatedValue() {
		return startDepreciatedValue;
	}
	public void setStartDepreciatedValue(Long startDepreciatedValue) {
		this.startDepreciatedValue = startDepreciatedValue;
	}
	public Long getEndOriginalPrice() {
		return endOriginalPrice;
	}
	public void setEndOriginalPrice(Long endOriginalPrice) {
		this.endOriginalPrice = endOriginalPrice;
	}
	public Long getEndResidualPrice() {
		return endResidualPrice;
	}
	public void setEndResidualPrice(Long endResidualPrice) {
		this.endResidualPrice = endResidualPrice;
	}
	public Long getEndDepreciatedValue() {
		return endDepreciatedValue;
	}
	public void setEndDepreciatedValue(Long endDepreciatedValue) {
		this.endDepreciatedValue = endDepreciatedValue;
	}
	public Long getStartOriginalPriceSync() {
		return startOriginalPriceSync;
	}
	public void setStartOriginalPriceSync(Long startOriginalPriceSync) {
		this.startOriginalPriceSync = startOriginalPriceSync;
	}
	public Long getStartResidualPriceSync() {
		return startResidualPriceSync;
	}
	public void setStartResidualPriceSync(Long startResidualPriceSync) {
		this.startResidualPriceSync = startResidualPriceSync;
	}
	public Long getStartDepreciatedValueSync() {
		return startDepreciatedValueSync;
	}
	public void setStartDepreciatedValueSync(Long startDepreciatedValueSync) {
		this.startDepreciatedValueSync = startDepreciatedValueSync;
	}
	public Long getEndOriginalPriceSync() {
		return endOriginalPriceSync;
	}
	public void setEndOriginalPriceSync(Long endOriginalPriceSync) {
		this.endOriginalPriceSync = endOriginalPriceSync;
	}
	public Long getEndResidualPriceSync() {
		return endResidualPriceSync;
	}
	public void setEndResidualPriceSync(Long endResidualPriceSync) {
		this.endResidualPriceSync = endResidualPriceSync;
	}
	public Long getEndDepreciatedValueSync() {
		return endDepreciatedValueSync;
	}
	public void setEndDepreciatedValueSync(Long endDepreciatedValueSync) {
		this.endDepreciatedValueSync = endDepreciatedValueSync;
	}
	public Long getStartOriginalPriceTotal() {
		return startOriginalPriceTotal;
	}
	public void setStartOriginalPriceTotal(Long startOriginalPriceTotal) {
		this.startOriginalPriceTotal = startOriginalPriceTotal;
	}
	public Long getStartResidualPriceTotal() {
		return startResidualPriceTotal;
	}
	public void setStartResidualPriceTotal(Long startResidualPriceTotal) {
		this.startResidualPriceTotal = startResidualPriceTotal;
	}
	public Long getStartDepreciatedValueTotal() {
		return startDepreciatedValueTotal;
	}
	public void setStartDepreciatedValueTotal(Long startDepreciatedValueTotal) {
		this.startDepreciatedValueTotal = startDepreciatedValueTotal;
	}
	public Long getEndOriginalPriceTotal() {
		return endOriginalPriceTotal;
	}
	public void setEndOriginalPriceTotal(Long endOriginalPriceTotal) {
		this.endOriginalPriceTotal = endOriginalPriceTotal;
	}
	public Long getEndResidualPriceTotal() {
		return endResidualPriceTotal;
	}
	public void setEndResidualPriceTotal(Long endResidualPriceTotal) {
		this.endResidualPriceTotal = endResidualPriceTotal;
	}
	public Long getEndDepreciatedValueTotal() {
		return endDepreciatedValueTotal;
	}
	public void setEndDepreciatedValueTotal(Long endDepreciatedValueTotal) {
		this.endDepreciatedValueTotal = endDepreciatedValueTotal;
	}
	public String getCaacName() {
		return caacName;
	}
	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}
	public Long getCaacLevel() {
		return caacLevel;
	}
	public void setCaacLevel(Long caacLevel) {
		this.caacLevel = caacLevel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
