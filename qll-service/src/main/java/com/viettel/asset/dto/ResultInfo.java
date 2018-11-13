package com.viettel.asset.dto;

public class ResultInfo {

	public static  final String RESULT_OK = "OK";
	public static  final String RESULT_NOK = "NOK";
	
	
	private String status;
	private String message;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
