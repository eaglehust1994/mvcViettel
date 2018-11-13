package com.viettel.asset.dto.search;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.erp.utils.CustomJsonDateSerializer;

public class AssetMerHandoverEntityDto extends MerHandOverEntityDto{
	private Integer rowNum;
	private Long madeYear;
	private Long nationalId;
	private String description;
	private String nationalName;
	private Long companyId;
	private String companyName;
	private String partNumber;
	private String unitName;
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date expiredWarrantyDate;
	private String statusName;
	private String stationCode;
	
	
	public Integer getRowNum(){
		return rowNum;
	}
	public void setRowNum(Integer rownum){
		this.rowNum=rownum;
	}
	public Date getExpiredWarrantyDate() {
		return expiredWarrantyDate;
	}
	public void setExpiredWarrantyDate(Date expiredWarrantyDate) {
		this.expiredWarrantyDate = expiredWarrantyDate;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public Long getMadeYear() {
		return madeYear;
	}
	public void setMadeYear(Long madeYear) {
		this.madeYear = madeYear;
	}
	public Long getNationalId() {
		return nationalId;
	}
	public void setNationalId(Long nationalId) {
		this.nationalId = nationalId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNationalName() {
		return nationalName;
	}
	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
