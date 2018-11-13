package com.viettel.asset.dto;

import java.util.Date;

public class CatAssetCodeHistoryRequest extends BaseWsRequest {

	private Date lastUpdatedTime; 
	private Long caacLevel;

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Long getCaacLevel() {
		return caacLevel;
	}
	public void setCaacLevel(Long caacLevel) {
		this.caacLevel = caacLevel;
	}
	
	
}
