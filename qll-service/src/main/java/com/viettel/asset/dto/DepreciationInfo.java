package com.viettel.asset.dto;

import java.util.Date;

public class DepreciationInfo {

	private String lotaCode;
	private Long depreciatedMonth;
	private Long depreciatedYear;
	private Long depreciatedValue;
	private Long depreciatedMonthValue;
	private Date depreciatedDate;
	private Long depreciatedTime;

	public Long getDepreciatedTime() {
		return depreciatedTime;
	}

	public void setDepreciatedTime(Long depreciatedTime) {
		this.depreciatedTime = depreciatedTime;
	}

	public String getLotaCode() {
		return lotaCode;
	}

	public void setLotaCode(String lotaCode) {
		this.lotaCode = lotaCode;
	}

	public Long getDepreciatedMonth() {
		return depreciatedMonth;
	}

	public void setDepreciatedMonth(Long depreciatedMonth) {
		this.depreciatedMonth = depreciatedMonth;
	}

	public Long getDepreciatedYear() {
		return depreciatedYear;
	}

	public void setDepreciatedYear(Long depreciatedYear) {
		this.depreciatedYear = depreciatedYear;
	}

	public Long getDepreciatedValue() {
		return depreciatedValue;
	}

	public void setDepreciatedValue(Long depreciatedValue) {
		this.depreciatedValue = depreciatedValue;
	}

	public Long getDepreciatedMonthValue() {
		return depreciatedMonthValue;
	}

	public void setDepreciatedMonthValue(Long depreciatedMonthValue) {
		this.depreciatedMonthValue = depreciatedMonthValue;
	}

	public Date getDepreciatedDate() {
		return depreciatedDate;
	}

	public void setDepreciatedDate(Date depreciatedDate) {
		this.depreciatedDate = depreciatedDate;
	}

}
