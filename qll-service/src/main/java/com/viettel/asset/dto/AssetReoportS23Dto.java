package com.viettel.asset.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.ktts2.common.UDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AssetReoportS23Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long depriciationTime;
	private String voucherCode;
	private Date voucherDate;
	private Long originalPrice;
	private Date depreciationStartDate;	
	private Long depreciatedValue;
	private String reasonChange;
	private Long depreciatedYearValue;
	private Long depreciatedYear;
	private String depreciatedYearString;
	private Long depreciatedMonth;
	private Long depreciatedMonthValue;
	private Long ltahType;
	private Long longTermAssetHistoryId;
	private String depreciationStartDateString;
	public String getDepreciationStartDateString() {
		return depreciationStartDateString;
	}
	public void setDepreciationStartDateString(String depreciationStartDateString) {
		this.depreciationStartDateString = depreciationStartDateString;
	}
	public Long getDepriciationTime() {
		return depriciationTime;
	}
	public void setDepriciationTime(Long depriciationTime) {
		this.depriciationTime = depriciationTime;
	}
	public String getVoucherCode() {
		return voucherCode == null?"":voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public Date getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}
	public Long getOriginalPrice() {
		return originalPrice==null?0:originalPrice;
	}
	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Date getDepreciationStartDate() {
		return depreciationStartDate;
	}
	public void setDepreciationStartDate(Date depreciationStartDate) {
		depreciationStartDateString=UDate.toSimpleFormat(depreciationStartDate);
		this.depreciationStartDate = depreciationStartDate;
	}
	public Long getDepreciatedValue() {
		return depreciatedValue==null?0:depreciatedValue;
	}
	public String getDepreciatedValueString() {
		return depreciatedValue==null?"":depreciatedValue.toString();
	}
	public void setDepreciatedValue(Long depreciatedValue) {
		this.depreciatedValue = depreciatedValue;
	}
	public String getReasonChange() {
		return reasonChange==null?"":reasonChange;
	}
	public void setReasonChange(String reasonChange) {
		this.reasonChange = reasonChange;
	}
	public Long getDepreciatedYearValue() {
		return depreciatedYearValue==null?0l:depreciatedYearValue;
	}
	public void setDepreciatedYearValue(Long depreciatedYearValue) {
		this.depreciatedYearValue = depreciatedYearValue;
	}
	
	public String getDepreciatedYearValueString(){
		return depreciatedYearValue==null?"":depreciatedYearValue.toString();
	}
	public Long getDepreciatedYear() {
		return depreciatedYear==null?0l:depreciatedYear;
	}
	public void setDepreciatedYear(Long depreciatedYear) {
		this.depreciatedYear = depreciatedYear;
	}
	public String getDepreciatedYearString() {
		return depreciatedYear==null?"":depreciatedYear.toString();
	}
	
	public Long getDepreciatedMonth() {
		return depreciatedMonth;
	}
	public void setDepreciatedMonth(Long depreciatedMonth) {
		this.depreciatedMonth = depreciatedMonth;
	}
	public Long getDepreciatedMonthValue() {
		return depreciatedMonthValue;
	}
	public void setDepreciatedMonthValue(Long depreciatedMonthValue) {
		this.depreciatedMonthValue = depreciatedMonthValue;
	}
	public Long getLtahType() {
		return ltahType;
	}
	public void setLtahType(Long ltahType) {
		this.ltahType = ltahType;
	}
	public Long getLongTermAssetHistoryId() {
		return longTermAssetHistoryId;
	}
	public void setLongTermAssetHistoryId(Long longTermAssetHistoryId) {
		this.longTermAssetHistoryId = longTermAssetHistoryId;
	}
	

}
