package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.LongTermAssetCost;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "longTermAssetCost")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LongTermAssetCostDto {
	private Long longTermAssetCostId;
	private Long loacType;
	private Long loacValue;
	private Long longTermAssetId;
	private String voucherList;
	private Date createdDate;
	
	private String voucherCode;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date voucherDate;
	private Long voucherValue;
	private Long voucherType;
	private String attachName;
	private String hasUpload;
	private Long attachId;
	private String attachIdEncrypted;
	private String attachDisplayName;
	//Add for offersclip
	private String contentCost;
	private Long estimateLoacValue;
	private Long voucherStatus;
	
	
	public Long getVoucherStatus() {
		return voucherStatus;
	}

	public void setVoucherStatus(Long voucherStatus) {
		this.voucherStatus = voucherStatus;
	}

	public String getContentCost() {
		return contentCost;
	}

	public void setContentCost(String contentCost) {
		this.contentCost = contentCost;
	}

	public Long getEstimateLoacValue() {
		return estimateLoacValue;
	}

	public void setEstimateLoacValue(Long estimateLoacValue) {
		this.estimateLoacValue = estimateLoacValue;
	}


	public String getAttachDisplayName() {
		return attachDisplayName;
	}

	public void setAttachDisplayName(String attachDisplayName) {
		this.attachDisplayName = attachDisplayName;
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public String getAttachIdEncrypted() {
		return attachIdEncrypted;
	}

	public void setAttachIdEncrypted(String attachIdEncrypted) {
		this.attachIdEncrypted = attachIdEncrypted;
	}

	public LongTermAssetCostDto() {
		
	}
	
	public LongTermAssetCostDto(LongTermAssetCost entity) {
		this.longTermAssetCostId = entity.getLongTermAssetCostId();
		this.loacType = entity.getLoacType();
		this.loacValue = entity.getLoacValue();
		this.longTermAssetId = entity.getLongTermAssetId();
		this.voucherList = entity.getVoucherList();
		this.createdDate = entity.getCreatedDate();
	}
	
	public LongTermAssetCost toEntity() {
		LongTermAssetCost entity = new LongTermAssetCost();
		entity.setLongTermAssetCostId(this.longTermAssetCostId);
		entity.setLoacType(this.loacType);
		entity.setLoacValue(this.loacValue);
		entity.setLongTermAssetId(this.longTermAssetId);
		entity.setVoucherList(this.voucherList);
		entity.setCreatedDate(this.createdDate);
		entity.setContentCost(contentCost);
		entity.setEstimateLoacValue(estimateLoacValue);
		entity.setVoucherStatus(voucherStatus);
		return entity;
	}
	
	public void updateEntity(LongTermAssetCost entity) {
		entity.setLoacType(this.loacType);
		entity.setLoacValue(this.loacValue);
		entity.setLongTermAssetId(this.longTermAssetId);
		entity.setVoucherList(this.voucherList);
		entity.setCreatedDate(this.createdDate);
	}
	
	public Long getLongTermAssetCostId() {
		return longTermAssetCostId;
	}
	public void setLongTermAssetCostId(Long longTermAssetCostId) {
		this.longTermAssetCostId = longTermAssetCostId;
	}		
	public Long getLoacType() {
		return loacType;
	}
	public void setLoacType(Long loacType) {
		this.loacType = loacType;
	}		
	public Long getLoacValue() {
		return loacValue;
	}
	public void setLoacValue(Long loacValue) {
		this.loacValue = loacValue;
	}		
	public Long getLongTermAssetId() {
		return longTermAssetId;
	}
	public void setLongTermAssetId(Long longTermAssetId) {
		this.longTermAssetId = longTermAssetId;
	}		
	public String getVoucherList() {
		return voucherList;
	}
	public void setVoucherList(String voucherList) {
		this.voucherList = voucherList;
	}		
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getVoucherCode() {
		return voucherCode;
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

	public Long getVoucherValue() {
		return voucherValue;
	}

	public void setVoucherValue(Long voucherValue) {
		this.voucherValue = voucherValue;
	}

	public Long getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(Long voucherType) {
		this.voucherType = voucherType;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getHasUpload() {
		return hasUpload;
	}

	public void setHasUpload(String hasUpload) {
		this.hasUpload = hasUpload;
	}
	
}
