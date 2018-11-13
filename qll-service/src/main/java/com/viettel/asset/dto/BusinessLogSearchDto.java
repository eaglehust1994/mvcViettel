package com.viettel.asset.dto;

import java.util.Date;

public class BusinessLogSearchDto extends BaseSearchDto {

	private String dbTable;
	private Date fromCreatedDate;
	public String getDbTable() {
		return dbTable;
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	public Date getFromCreatedDate() {
		return fromCreatedDate;
	}
	public void setFromCreatedDate(Date fromCreatedDate) {
		this.fromCreatedDate = fromCreatedDate;
	}
	
}
