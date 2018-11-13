package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.LongTermAssetHistory;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "longTermAssetHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LongTermAssetHistoryDto {
	
	
		private Long longTermAssetHistoryId;
		private Long longTermAssetId;
		private String lotaCode;
		private Date ltahDate;
		private Long ltahType;
		private Long ltahValue;
		private String reasonChange;
		private Long ltahOldValue;
		private Long ltahNewValue;
		private Long longTermAssetVoucherId;
		private Long depreciatedValue;
		private Long depreciatedTime;
		private Long depreciatedMonthValue;
		private Long depreciatedMonth;
		private Long depreciatedYear;
		private Date depreciatedDate;
		private Long groupId;
		private Long useGroupId;
		private Long toGroupId;
		private Long toUseGroupId;
		private Date createdDate;
		private String handoverCode;
	
	public LongTermAssetHistoryDto() {
		
	}
	
	public LongTermAssetHistoryDto(LongTermAssetHistory entity) {
		this.longTermAssetHistoryId = entity.getLongTermAssetHistoryId();
		this.longTermAssetId = entity.getLongTermAssetId();
		this.lotaCode = entity.getLotaCode();
		this.ltahDate = entity.getLtahDate();
		this.ltahType = entity.getLtahType();
		this.ltahValue = entity.getLtahValue();
		this.reasonChange = entity.getReasonChange();
		this.ltahOldValue = entity.getLtahOldValue();
		this.ltahNewValue = entity.getLtahNewValue();
		this.longTermAssetVoucherId = entity.getLongTermAssetVoucherId();
		this.depreciatedValue = entity.getDepreciatedValue();
		this.depreciatedTime = entity.getDepreciatedTime();
		this.depreciatedMonthValue = entity.getDepreciatedMonthValue();
		this.depreciatedMonth = entity.getDepreciatedMonth();
		this.depreciatedYear = entity.getDepreciatedYear();
		this.depreciatedDate = entity.getDepreciatedDate();
		this.groupId = entity.getGroupId();
		this.useGroupId = entity.getUseGroupId();
		this.toGroupId = entity.getToGroupId();
		this.toUseGroupId = entity.getToUseGroupId();
		this.createdDate = entity.getCreatedDate();
		this.handoverCode = entity.getHandoverCode();
	}
	
	public LongTermAssetHistory toEntity() {
		LongTermAssetHistory entity = new LongTermAssetHistory();
		entity.setLongTermAssetHistoryId(this.longTermAssetHistoryId);
		entity.setLongTermAssetId(this.longTermAssetId);
		entity.setLotaCode(this.lotaCode);
		entity.setLtahDate(this.ltahDate);
		entity.setLtahType(this.ltahType);
		entity.setLtahValue(this.ltahValue);
		entity.setReasonChange(this.reasonChange);
		entity.setLtahOldValue(this.ltahOldValue);
		entity.setLtahNewValue(this.ltahNewValue);
		entity.setLongTermAssetVoucherId(this.longTermAssetVoucherId);
		entity.setDepreciatedValue(this.depreciatedValue);
		entity.setDepreciatedTime(this.depreciatedTime);
		entity.setDepreciatedMonthValue(this.depreciatedMonthValue);
		entity.setDepreciatedMonth(this.depreciatedMonth);
		entity.setDepreciatedYear(this.depreciatedYear);
		entity.setDepreciatedDate(this.depreciatedDate);
		entity.setGroupId(this.groupId);
		entity.setUseGroupId(this.useGroupId);
		entity.setToGroupId(this.toGroupId);
		entity.setToUseGroupId(this.toUseGroupId);
		entity.setCreatedDate(this.createdDate);
		entity.setHandoverCode(this.handoverCode);
		return entity;
	}
	
	public void updateEntity(LongTermAssetHistory entity) {
			entity.setLongTermAssetId(this.longTermAssetId);
			entity.setLotaCode(this.lotaCode);
			entity.setLtahDate(this.ltahDate);
			entity.setLtahType(this.ltahType);
			entity.setLtahValue(this.ltahValue);
			entity.setReasonChange(this.reasonChange);
			entity.setLtahOldValue(this.ltahOldValue);
			entity.setLtahNewValue(this.ltahNewValue);
			entity.setLongTermAssetVoucherId(this.longTermAssetVoucherId);
			entity.setDepreciatedValue(this.depreciatedValue);
			entity.setDepreciatedTime(this.depreciatedTime);
			entity.setDepreciatedMonthValue(this.depreciatedMonthValue);
			entity.setDepreciatedMonth(this.depreciatedMonth);
			entity.setDepreciatedYear(this.depreciatedYear);
			entity.setDepreciatedDate(this.depreciatedDate);
			entity.setGroupId(this.groupId);
			entity.setUseGroupId(this.useGroupId);
			entity.setToGroupId(this.toGroupId);
			entity.setToUseGroupId(this.toUseGroupId);
			entity.setCreatedDate(this.createdDate);
			entity.setHandoverCode(this.handoverCode);
	}
	
		public Long getLongTermAssetHistoryId() {
			return longTermAssetHistoryId;
		}
		public void setLongTermAssetHistoryId(Long longTermAssetHistoryId) {
			this.longTermAssetHistoryId = longTermAssetHistoryId;
		}		
		public Long getLongTermAssetId() {
			return longTermAssetId;
		}
		public void setLongTermAssetId(Long longTermAssetId) {
			this.longTermAssetId = longTermAssetId;
		}		
		public String getLotaCode() {
			return lotaCode;
		}
		public void setLotaCode(String lotaCode) {
			this.lotaCode = lotaCode;
		}		
		public Date getLtahDate() {
			return ltahDate;
		}
		public void setLtahDate(Date ltahDate) {
			this.ltahDate = ltahDate;
		}		
		public Long getLtahType() {
			return ltahType;
		}
		public void setLtahType(Long ltahType) {
			this.ltahType = ltahType;
		}		
		public Long getLtahValue() {
			return ltahValue;
		}
		public void setLtahValue(Long ltahValue) {
			this.ltahValue = ltahValue;
		}		
		public String getReasonChange() {
			return reasonChange;
		}
		public void setReasonChange(String reasonChange) {
			this.reasonChange = reasonChange;
		}		
		public Long getLtahOldValue() {
			return ltahOldValue;
		}
		public void setLtahOldValue(Long ltahOldValue) {
			this.ltahOldValue = ltahOldValue;
		}		
		public Long getLtahNewValue() {
			return ltahNewValue;
		}
		public void setLtahNewValue(Long ltahNewValue) {
			this.ltahNewValue = ltahNewValue;
		}		
		public Long getLongTermAssetVoucherId() {
			return longTermAssetVoucherId;
		}
		public void setLongTermAssetVoucherId(Long longTermAssetVoucherId) {
			this.longTermAssetVoucherId = longTermAssetVoucherId;
		}		
		public Long getDepreciatedValue() {
			return depreciatedValue;
		}
		public void setDepreciatedValue(Long depreciatedValue) {
			this.depreciatedValue = depreciatedValue;
		}		
		public Long getDepreciatedTime() {
			return depreciatedTime;
		}
		public void setDepreciatedTime(Long depreciatedTime) {
			this.depreciatedTime = depreciatedTime;
		}		
		public Long getDepreciatedMonthValue() {
			return depreciatedMonthValue;
		}
		public void setDepreciatedMonthValue(Long depreciatedMonthValue) {
			this.depreciatedMonthValue = depreciatedMonthValue;
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
		public Date getDepreciatedDate() {
			return depreciatedDate;
		}
		public void setDepreciatedDate(Date depreciatedDate) {
			this.depreciatedDate = depreciatedDate;
		}		
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}		
		public Long getUseGroupId() {
			return useGroupId;
		}
		public void setUseGroupId(Long useGroupId) {
			this.useGroupId = useGroupId;
		}		
		public Long getToGroupId() {
			return toGroupId;
		}
		public void setToGroupId(Long toGroupId) {
			this.toGroupId = toGroupId;
		}		
		public Long getToUseGroupId() {
			return toUseGroupId;
		}
		public void setToUseGroupId(Long toUseGroupId) {
			this.toUseGroupId = toUseGroupId;
		}		
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}		
		public String getHandoverCode() {
			return handoverCode;
		}
		public void setHandoverCode(String handoverCode) {
			this.handoverCode = handoverCode;
		}		
	
}
