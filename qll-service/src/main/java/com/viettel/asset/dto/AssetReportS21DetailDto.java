package com.viettel.asset.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.ktts2.common.UDate;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetReportS21DetailDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long num;
	private String increaseVoucherCode;// Mã chứng từ tăng tài sản cố định
	private Date increaseVoucherDate; // Ngày chứng từ tăng TSCĐ
	private String assetName="";
	private String assetProductNation=""; //productNation 
	private String lotaCode; // mã tài sản
	private Long originalPrice; // Nguyên giá
	private Date depreciationStartDate;//Ngày bắt đầu khấu hao- ngày sử dụng
	private String depreciateRateYearlyPercent; // tỷ lệ % khấu hao theo năm
	private Long depreciateRateYearlyValue;// tỷ lệ khấu hao theo năm về giá trị
	private Long depreciatiedValue; // tỷ lệ khấu hao tính đến thời điểm hiện tại
	private String decreaseVoucherCode; // Mã Chứng từ giảm TSCĐ
	private Date decreaseVoucherDate; // Ngày Chứng từ giảm TSCĐ
	private String decreaseReasonChange;// Lý do khấu hao
	private transient String increaseVoucherDateString;
	private transient String depreciationStartDateString;
	private transient String decreaseVoucherDateString;
	private String caacName;
	private String nationalName;
	
	public String getNationalName() {
		if(nationalName==null){
			return "";
		}
		return nationalName;
	}
	public void setNationalName(String nationalName) {
		
		this.nationalName = nationalName;
	}
	public String getDepreciationStartDateString() {		
		return depreciationStartDateString;
	}
	public void setDepreciationStartDateString(String depreciationStartDateString) {
		this.depreciationStartDateString = depreciationStartDateString;
	}
	public String getDecreaseVoucherDateString() {
		return decreaseVoucherDateString;
	}
	public void setDecreaseVoucherDateString(String decreaseVoucherDateString) {
		this.decreaseVoucherDateString = decreaseVoucherDateString;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getIncreaseVoucherCode() {
		
		return increaseVoucherCode==null?"":increaseVoucherCode;
	}
	public void setIncreaseVoucherCode(String increaseVoucherCode) {
		this.increaseVoucherCode = increaseVoucherCode;
	}
	public Date getIncreaseVoucherDate() {		
		return increaseVoucherDate;
	}
	public void setIncreaseVoucherDate(Date increaseVoucherDate) {
		increaseVoucherDateString=UDate.toSimpleFormat(increaseVoucherDate);
		this.increaseVoucherDate = increaseVoucherDate;
	}
	public String getAssetName() {
		return assetName==null?"":assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetProductNation() {
		return assetProductNation==null?"":assetProductNation;
	}
	public void setAssetProductNation(String assetProductNation) {
		this.assetProductNation = assetProductNation;
	}
	public String getLotaCode() {
		return lotaCode;
	}
	public void setLotaCode(String lotaCode) {
		this.lotaCode = lotaCode;
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
	public String getDepreciateRateYearlyPercent() {
		return depreciateRateYearlyPercent==null?"":depreciateRateYearlyPercent;
	}
	public void setDepreciateRateYearlyPercent(String depreciateRateYearlyPercent) {
		this.depreciateRateYearlyPercent = depreciateRateYearlyPercent;
	}
	public Long getDepreciateRateYearlyValue() {
		return depreciateRateYearlyValue==null?0:depreciateRateYearlyValue;
	}
	public void setDepreciateRateYearlyValue(Long depreciateRateYearlyValue) {
		this.depreciateRateYearlyValue = depreciateRateYearlyValue;
	}
	public Long getDepreciatiedValue() {
		return depreciatiedValue==null?0:depreciatiedValue;
	}
	public void setDepreciatiedValue(Long depreciatiedValue) {
		this.depreciatiedValue = depreciatiedValue;
	}
	public String getDecreaseVoucherCode() {
		return decreaseVoucherCode==null?"":decreaseVoucherCode;
	}
	public void setDecreaseVoucherCode(String decreaseVoucherCode) {
		this.decreaseVoucherCode = decreaseVoucherCode;
	}
	public Date getDecreaseVoucherDate() {
		return decreaseVoucherDate;
	}
	public void setDecreaseVoucherDate(Date decreaseVoucherDate) {
		decreaseVoucherDateString=UDate.toSimpleFormat(decreaseVoucherDate);
		this.decreaseVoucherDate = decreaseVoucherDate;
	}
	public String getDecreaseReasonChange() {
		return decreaseReasonChange==null?"":decreaseReasonChange.trim();
	}
	public void setDecreaseReasonChange(String decreaseReasonChange) {
		this.decreaseReasonChange = decreaseReasonChange;
	}
	public String getIncreaseVoucherDateString() {
		return increaseVoucherDateString;
	}
	public void setIncreaseVoucherDateString(String increaseVoucherDateString) {
		this.increaseVoucherDateString = increaseVoucherDateString;
	}
	public String getCaacName() {		
		return caacName==null?"":caacName;
	}
	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}
}
