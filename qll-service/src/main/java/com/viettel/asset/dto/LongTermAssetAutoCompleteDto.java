package com.viettel.asset.dto;

public class LongTermAssetAutoCompleteDto {
	private Long longTermAssetId;
	private String lotaCode;
	private String caacName;
	
	public Long getLongTermAssetId() {
		return longTermAssetId;
	}
	public void setLongTermAssetId(Long longTermAssetId) {
		this.longTermAssetId = longTermAssetId;
	}
	public String getLotaCode() {
		return lotaCode;
	}
	public void setLotaCode(String lotaCode) {
		this.lotaCode = lotaCode;
	}
	public String getCaacName() {
		return caacName;
	}
	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}
}
