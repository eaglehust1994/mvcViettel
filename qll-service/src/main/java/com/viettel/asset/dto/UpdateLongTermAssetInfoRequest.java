package com.viettel.asset.dto;

public class UpdateLongTermAssetInfoRequest extends BaseWsRequest {

	private LongTermAssetDto longTermAssetDto;

	public LongTermAssetDto getLongTermAssetDto() {
		return longTermAssetDto;
	}

	public void setLongTermAssetDto(LongTermAssetDto longTermAssetDto) {
		this.longTermAssetDto = longTermAssetDto;
	}
}
