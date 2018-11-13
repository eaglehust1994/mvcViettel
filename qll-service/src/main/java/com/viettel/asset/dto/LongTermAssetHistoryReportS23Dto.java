package com.viettel.asset.dto;
import java.util.Calendar;
import java.util.Date;

public class LongTermAssetHistoryReportS23Dto{
	
	private String useGroupName;
	private String groupAddress;
	private String groupName;
	private String lotaCode;	
	private String handoverCode;
	private Date handoverDate;
	private Date depreciationStartDate;//ngày sử dụng
	private String caacName;
	private String caacFullCode;
	private String decreaseReasonChange;
	
	private Date decreaseVoucherDate;
	private String depreciationStartYear;//-- transient
	private String decreaseVoucherCode;
	//transient
	private String decreaseDay;
	private String decreaseMonth;
	private String decreaseYear;
	
	private String handoverDay;
	private String handoverMonth;
	private String handOverYear;
	
	private String nationalName;
	private Long madeYear;
	private String description;
	
	public String getNationalName() {
		if(nationalName==null){
			return "";
		}
		return nationalName;
	}
	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}
	public String getMadeYear() {
		if(madeYear==null){
			return "";
		}
		return madeYear.toString();
	}
	public void setMadeYear(Long madeYear) {
		this.madeYear = madeYear;
	}
	public String getDescription() {
		if(description==null){
			return "";
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHandoverDay() {
		if(handoverDate==null){
			return "";
		}
		tempCal.setTime(handoverDate);
		return String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH));		
	}
	public void setHandoverDay(String handoverDay) {
		this.handoverDay = handoverDay;
	}
	public String getHandoverMonth() {
		if(handoverDate==null){
			return "";
		}
		tempCal.setTime(handoverDate);
		return String.valueOf(tempCal.get(Calendar.MONTH));
	}
	public void setHandoverMonth(String handoverMonth) {
		this.handoverMonth = handoverMonth;
	}
	public String getHandoverYear() {
		if(handoverDate==null){
			return "";
		}
		tempCal.setTime(handoverDate);
		return String.valueOf(tempCal.get(Calendar.YEAR));
	}
	public void setHandoverYear(String handOverYear) {
		this.handOverYear = handOverYear;
	}
	private Calendar tempCal=Calendar.getInstance();
	public String getDecreaseDay() {
		if(decreaseVoucherDate==null){
			return "";
		}
		tempCal.setTime(decreaseVoucherDate);
		return String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH));		
	}
	public void setDecreaseDay(String decreaseDay) {
		this.decreaseDay = decreaseDay;
	}
	public String getDecreaseMonth() {
		if(decreaseVoucherDate==null){
			return "";
		}
		tempCal.setTime(decreaseVoucherDate);
		return String.valueOf(tempCal.get(Calendar.MONTH));		
	}
	public void setDecreaseMonth(String decreaseMonth) {
		this.decreaseMonth = decreaseMonth;
	}
	public String getDecreaseYear() {
		if(decreaseVoucherDate==null){
			return "";
		}
		tempCal.setTime(decreaseVoucherDate);
		return String.valueOf(tempCal.get(Calendar.YEAR));		
	}
	public void setDecreaseYear(String decreaseYear) {
		this.decreaseYear = decreaseYear;
	}
	public String getDecreaseReasonChange() {
		return decreaseReasonChange==null?"":decreaseReasonChange;
	}
	public void setDecreaseReasonChange(String decreaseReasonChange) {
		this.decreaseReasonChange = decreaseReasonChange;
	}
	public String getGroupAddress() {
		return groupAddress==null?"":groupAddress;
	}
	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}
	public String getGroupName() {
		return groupName==null?"":groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getHandoverCode() {
		return handoverCode==null?"":handoverCode;
	}
	public void setHandoverCode(String handoverCode) {
		this.handoverCode = handoverCode;
	}
	public Date getDepreciationStartDate() {		
		return depreciationStartDate;
	}
	public void setDepreciationStartDate(Date depreciationStartDate) {
		this.depreciationStartDate = depreciationStartDate;
	}
	public String getCaacFullCode() {
		return caacFullCode==null?"":caacFullCode;
	}
	public void setCaacFullCode(String caacFullCode) {
		this.caacFullCode = caacFullCode;
	}
	
	
	
	public Date getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}
	public String getCaacName() {
		return caacName==null?"":caacName;
	}
	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}
	public String getLotaCode() {
		return lotaCode;
	}
	public void setLotaCode(String lotaCode) {
		this.lotaCode = lotaCode;
	}
	public String getUseGroupName() {
		return useGroupName;
	}
	public void setUseGroupName(String useGroupName) {
		this.useGroupName = useGroupName;
	}
	public String getDepreciationStartYear() {
		if(depreciationStartDate==null){
			return "";
		}
		tempCal.setTime(depreciationStartDate);
		return String.valueOf(tempCal.get(Calendar.YEAR));
	}
	public void setDepreciationStartYear(String depreciationStartYear) {
		this.depreciationStartYear = depreciationStartYear;
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
		this.decreaseVoucherDate = decreaseVoucherDate;
	}
	
	

}
