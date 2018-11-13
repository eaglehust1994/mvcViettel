package com.viettel.qll.dto;

public class ErrExcelDTO {

	private String lineError;
	
	private String columnError;
	
	private String serial;
	
	private String DetailError;
	
	public String getLineError() {
		return lineError;
	}

	public void setLineError(String lineError) {
		this.lineError = lineError;
	}

	public String getColumnError() {
		return columnError;
	}

	public void setColumnError(String columnError) {
		this.columnError = columnError;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDetailError() {
		return DetailError;
	}

	public void setDetailError(String detailError) {
		DetailError = detailError;
	}

}