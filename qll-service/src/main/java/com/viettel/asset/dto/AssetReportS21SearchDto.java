package com.viettel.asset.dto;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetReportS21SearchDto  extends BaseSearchDto{
	private Long groupId;
	private Long catAssetCodeId;//Id loai tai san
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fromDate;
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date toDate;
	
	
	
	
			
	private String groupName;
	private String groupAddress;	
	private String reportYear;
	private String caacName;
	private String caacFullCode;
	private String orginalPriceTotal;
	private Long longTermAssetId;
	
	private Long lotaType;
	
	public Long getLotaType() {
		return lotaType;
	}
	public void setLotaType(Long lotaType) {
		this.lotaType = lotaType;
	}
	public Long getLongTermAssetId() {
		return longTermAssetId;
	}
	public void setLongTermAssetId(Long longTermAssetId) {
		this.longTermAssetId = longTermAssetId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupAddress() {
		return groupAddress;
	}
	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getCatAssetCodeId() {
		return catAssetCodeId;
	}
	public void setCatAssetCodeId(Long catAssetCodeId) {
		this.catAssetCodeId = catAssetCodeId;
	}
	
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Date getToDate() {
		return toDate;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getReportYear() {
		return reportYear;
	}
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	public String getCaacName() {
		return caacName;
	}
	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}
	public String getCaacFullCode() {
		return caacFullCode;
	}
	public void setCaacFullCode(String caacFullCode) {
		this.caacFullCode = caacFullCode;
	}
	public String getOrginalPriceTotal() {
		return orginalPriceTotal;
	}
	public void setOrginalPriceTotal(String orginalPriceTotal) {
		this.orginalPriceTotal = orginalPriceTotal;
	}
	

}
