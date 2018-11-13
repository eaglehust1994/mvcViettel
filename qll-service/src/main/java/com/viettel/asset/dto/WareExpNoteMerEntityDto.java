package com.viettel.asset.dto;

import com.viettel.asset.bo.WareExpNoteMerEntity;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "wareExpNoteMerEntity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WareExpNoteMerEntityDto {
	
	
		private Long merEntityId;
		private Long deliveryNoteId;
		private Long id;
		private Long originalPrice;
		private Long orderCode;
		private Double quantity;
		private Long statusId;
		private Long quality;
		private Long merId;
		private String serialNumber;
	
	public WareExpNoteMerEntityDto() {
		
	}
	
	public WareExpNoteMerEntityDto(WareExpNoteMerEntity entity) {
		this.merEntityId = entity.getMerEntityId();
		this.deliveryNoteId = entity.getDeliveryNoteId();
		this.id = entity.getId();
		this.originalPrice = entity.getOriginalPrice();
		this.orderCode = entity.getOrderCode();
		this.quantity = entity.getQuantity();
		this.statusId = entity.getStatusId();
		this.quality = entity.getQuality();
		this.merId = entity.getMerId();
		this.serialNumber = entity.getSerialNumber();
	}
	
	public WareExpNoteMerEntity toEntity() {
		WareExpNoteMerEntity entity = new WareExpNoteMerEntity();
		entity.setMerEntityId(this.merEntityId);
		entity.setDeliveryNoteId(this.deliveryNoteId);
		entity.setId(this.id);
		entity.setOriginalPrice(this.originalPrice);
		entity.setOrderCode(this.orderCode);
		entity.setQuantity(this.quantity);
		entity.setStatusId(this.statusId);
		entity.setQuality(this.quality);
		entity.setMerId(this.merId);
		entity.setSerialNumber(this.serialNumber);
		return entity;
	}
	
	public void updateEntity(WareExpNoteMerEntity entity) {
			entity.setMerEntityId(this.merEntityId);
			entity.setDeliveryNoteId(this.deliveryNoteId);
			entity.setOriginalPrice(this.originalPrice);
			entity.setOrderCode(this.orderCode);
			entity.setQuantity(this.quantity);
			entity.setStatusId(this.statusId);
			entity.setQuality(this.quality);
			entity.setMerId(this.merId);
			entity.setSerialNumber(this.serialNumber);
	}
	
		public Long getMerEntityId() {
			return merEntityId;
		}
		public void setMerEntityId(Long merEntityId) {
			this.merEntityId = merEntityId;
		}		
		public Long getDeliveryNoteId() {
			return deliveryNoteId;
		}
		public void setDeliveryNoteId(Long deliveryNoteId) {
			this.deliveryNoteId = deliveryNoteId;
		}		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}		
		public Long getOriginalPrice() {
			return originalPrice;
		}
		public void setOriginalPrice(Long originalPrice) {
			this.originalPrice = originalPrice;
		}		
		public Long getOrderCode() {
			return orderCode;
		}
		public void setOrderCode(Long orderCode) {
			this.orderCode = orderCode;
		}		
		public Double getQuantity() {
			return quantity;
		}
		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}		
		public Long getStatusId() {
			return statusId;
		}
		public void setStatusId(Long statusId) {
			this.statusId = statusId;
		}		
		public Long getQuality() {
			return quality;
		}
		public void setQuality(Long quality) {
			this.quality = quality;
		}		
		public Long getMerId() {
			return merId;
		}
		public void setMerId(Long merId) {
			this.merId = merId;
		}		
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}		
	
}
