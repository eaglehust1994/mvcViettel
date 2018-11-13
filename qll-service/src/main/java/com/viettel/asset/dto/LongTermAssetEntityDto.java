package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.LongTermAssetEntity;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "longTermAssetEntity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LongTermAssetEntityDto {
	
	
		private Long longTermAssetEntityId;
		private Long merEntityId;
		private Double quantity;
		private Long originalPrice;
		private Date createdDateAsset;
		private Long longTermAssetId;
		private String serialNumber;
		private String unit;
		private String name;
		private String code;
		private Double total;
		
	
	public LongTermAssetEntityDto() {
		
	}
	
	public LongTermAssetEntityDto(LongTermAssetEntity entity) {
		this.longTermAssetEntityId = entity.getLongTermAssetEntityId();
		this.merEntityId = entity.getMerEntityId();
		this.quantity = entity.getQuantity();
		this.originalPrice = entity.getOriginalPrice();
		this.createdDateAsset = entity.getCreatedDateAsset();
		this.longTermAssetId = entity.getLongTermAssetId();
	}
	
	public LongTermAssetEntity toEntity() {
		LongTermAssetEntity entity = new LongTermAssetEntity();
		entity.setLongTermAssetEntityId(this.longTermAssetEntityId);
		entity.setMerEntityId(this.merEntityId);
		entity.setQuantity(this.quantity);
		entity.setOriginalPrice(this.originalPrice);
		entity.setCreatedDateAsset(this.createdDateAsset);
		entity.setLongTermAssetId(this.longTermAssetId);
		return entity;
	}
	
	public void updateEntity(LongTermAssetEntity entity) {
			entity.setMerEntityId(this.merEntityId);
			entity.setQuantity(this.quantity);
			entity.setOriginalPrice(this.originalPrice);
			entity.setCreatedDateAsset(this.createdDateAsset);
			entity.setLongTermAssetId(this.longTermAssetId);
	}
	
		public Long getLongTermAssetEntityId() {
			return longTermAssetEntityId;
		}
		public void setLongTermAssetEntityId(Long longTermAssetEntityId) {
			this.longTermAssetEntityId = longTermAssetEntityId;
		}		
		public Long getMerEntityId() {
			return merEntityId;
		}
		public void setMerEntityId(Long merEntityId) {
			this.merEntityId = merEntityId;
		}		
		public Double getQuantity() {
			return quantity;
		}
		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}		
		public Long getOriginalPrice() {
			return originalPrice;
		}
		public void setOriginalPrice(Long originalPrice) {
			this.originalPrice = originalPrice;
		}		
		public Date getCreatedDateAsset() {
			return createdDateAsset;
		}
		public void setCreatedDateAsset(Date createdDateAsset) {
			this.createdDateAsset = createdDateAsset;
		}		
		public Long getLongTermAssetId() {
			return longTermAssetId;
		}
		public void setLongTermAssetId(Long longTermAssetId) {
			this.longTermAssetId = longTermAssetId;
		}		
		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}
}
