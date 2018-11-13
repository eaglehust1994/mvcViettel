package com.viettel.ktts2.common;

public class ResponseMessage {

	public static final String RESULT_OK = "200";
	public static final String RESULT_ERROR_BUSINESS = "400";
	private String errorMessage;
	private String status;
	private Object data;
	
	public ResponseMessage() {
		
	}
	
	public ResponseMessage(BusinessException ex) {
		this.status = RESULT_ERROR_BUSINESS;
		this.errorMessage = ex.getMessage();
		this.data=ex.getLstParam();
	}
	
	public ResponseMessage(Object data) {
		this.data = data;
		this.status = RESULT_OK;
	}
	
	public static ResponseMessage success(String message) {
		ResponseMessage rm = new ResponseMessage();
		rm.setStatus(RESULT_OK);
		rm.setErrorMessage(message);
		return rm;
	}
	
	public static ResponseMessage success(String message, Object data) {
		ResponseMessage rm = new ResponseMessage(message);
		rm.setData(data);
		return rm;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
