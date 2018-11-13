package com.viettel.asset.dto.service;

public class AAssetTypeDto {
	private String name;
	
	private String description;
	private String value;
	private String groupValue;
	private Long month;
	
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
