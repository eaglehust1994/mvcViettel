package com.viettel.asset.dto;

import java.util.Date;

import com.viettel.asset.bo.CatAssetCode;

public class CatAssetCodeHistory {

	private String action;
	private Date updatedTime;
	private Long id;
	private CatAssetCode oldValue;
	private CatAssetCode newValue;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CatAssetCode getOldValue() {
		return oldValue;
	}
	public void setOldValue(CatAssetCode oldValue) {
		this.oldValue = oldValue;
	}
	public CatAssetCode getNewValue() {
		return newValue;
	}
	public void setNewValue(CatAssetCode newValue) {
		this.newValue = newValue;
	}
	
	
}
