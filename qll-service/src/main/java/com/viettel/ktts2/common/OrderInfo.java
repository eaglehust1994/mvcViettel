package com.viettel.ktts2.common;

public class OrderInfo {

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private String attribute;
	private String type;
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
