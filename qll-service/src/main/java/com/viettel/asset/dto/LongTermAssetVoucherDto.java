package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.LongTermAssetVoucher;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "longTermAssetVoucher")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LongTermAssetVoucherDto {
	private Long longTermAssetVoucherId;
	private String voucherCode;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date voucherDate;
	private Long voucherValue;
	private Long attachId;
	private Long voucherType;
	private Long objectId;
	private Long loacType;
	private String attachName;

	public LongTermAssetVoucherDto() {
		
	}
	
	public LongTermAssetVoucherDto(LongTermAssetVoucher entity) {
		this.longTermAssetVoucherId = entity.getLongTermAssetVoucherId();
		this.voucherCode = entity.getVoucherCode();
		this.voucherDate = entity.getVoucherDate();
		this.voucherValue = entity.getVoucherValue();
		this.attachId = entity.getAttachId();
	}
	
	public LongTermAssetVoucher toEntity() {
		LongTermAssetVoucher entity = new LongTermAssetVoucher();
		entity.setLongTermAssetVoucherId(this.longTermAssetVoucherId);
		entity.setVoucherCode(this.voucherCode);
		entity.setVoucherDate(this.voucherDate);
		entity.setVoucherValue(this.voucherValue);
		entity.setAttachId(this.attachId);
		entity.setVoucherType(this.voucherType);
		entity.setObjectId(this.objectId);
		return entity;
	}
	
	public void updateEntity(LongTermAssetVoucher entity) {
		entity.setVoucherCode(this.voucherCode);
		entity.setVoucherDate(this.voucherDate);
		entity.setVoucherValue(this.voucherValue);
		entity.setAttachId(this.attachId);
		entity.setVoucherType(this.voucherType);
		entity.setObjectId(this.objectId);
	}
	
	public Long getLongTermAssetVoucherId() {
		return longTermAssetVoucherId;
	}
	public void setLongTermAssetVoucherId(Long longTermAssetVoucherId) {
		this.longTermAssetVoucherId = longTermAssetVoucherId;
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
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}		

	
	public Long getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(Long voucherType) {
		this.voucherType = voucherType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	public Long getLoacType() {
		return loacType;
	}

	public void setLoacType(Long loacType) {
		this.loacType = loacType;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
}
