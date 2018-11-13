package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.MerHandOverEntity;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "merHandOverEntity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerHandOverEntityDto {
	
	
		private Long merHandOverEntityId;
		private Long handOverId;
		private Long statusId;
		private Long stationId;
		private Double count;
		private Double remain;
		private Long merEntityId;
		private Date expiredWarrantyDate;
		private Long workItemId;
		private Long nodeId;
		private String checkUsing;
		//longMerHandEntity
		private Long originalPrice;
		private Long isDevice;
		private String name;
		private String code;
		private String serialNumber;
		private Long longTermAssetId;
		private Long longTermAssetEntityId;
	public MerHandOverEntityDto() {
		
	}
	
	public MerHandOverEntityDto(MerHandOverEntity entity) {
		this.merHandOverEntityId = entity.getMerHandOverEntityId();
		this.handOverId = entity.getHandOverId();
		this.statusId = entity.getStatusId();
		this.stationId = entity.getStationId();
		this.count = entity.getCount();
		this.remain = entity.getRemain();
		this.merEntityId = entity.getMerEntityId();
		this.expiredWarrantyDate = entity.getExpiredWarrantyDate();
		this.workItemId = entity.getWorkItemId();
		this.nodeId = entity.getNodeId();
	}
	
	public MerHandOverEntity toEntity() {
		MerHandOverEntity entity = new MerHandOverEntity();
		entity.setMerHandOverEntityId(this.merHandOverEntityId);
		entity.setHandOverId(this.handOverId);
		entity.setStatusId(this.statusId);
		entity.setStationId(this.stationId);
		entity.setCount(this.count);
		entity.setRemain(this.remain);
		entity.setMerEntityId(this.merEntityId);
		entity.setExpiredWarrantyDate(this.expiredWarrantyDate);
		entity.setWorkItemId(this.workItemId);
		entity.setNodeId(this.nodeId);
		return entity;
	}
	
	public void updateEntity(MerHandOverEntity entity) {
			entity.setHandOverId(this.handOverId);
			entity.setStatusId(this.statusId);
			entity.setStationId(this.stationId);
			entity.setCount(this.count);
			entity.setRemain(this.remain);
			entity.setMerEntityId(this.merEntityId);
			entity.setExpiredWarrantyDate(this.expiredWarrantyDate);
			entity.setWorkItemId(this.workItemId);
			entity.setNodeId(this.nodeId);
	}
	
		public Long getMerHandOverEntityId() {
			return merHandOverEntityId;
		}
		public void setMerHandOverEntityId(Long merHandOverEntityId) {
			this.merHandOverEntityId = merHandOverEntityId;
		}		
		public Long getHandOverId() {
			return handOverId;
		}
		public void setHandOverId(Long handOverId) {
			this.handOverId = handOverId;
		}		
		public Long getStatusId() {
			return statusId;
		}
		public void setStatusId(Long statusId) {
			this.statusId = statusId;
		}		
		public Long getStationId() {
			return stationId;
		}
		public void setStationId(Long stationId) {
			this.stationId = stationId;
		}		
		public Double getCount() {
			return count;
		}
		public void setCount(Double count) {
			this.count = count;
		}		
		public Double getRemain() {
			return remain;
		}
		public void setRemain(Double remain) {
			this.remain = remain;
		}		
		public Long getMerEntityId() {
			return merEntityId;
		}
		public void setMerEntityId(Long merEntityId) {
			this.merEntityId = merEntityId;
		}		
		public Date getExpiredWarrantyDate() {
			return expiredWarrantyDate;
		}
		public void setExpiredWarrantyDate(Date expiredWarrantyDate) {
			this.expiredWarrantyDate = expiredWarrantyDate;
		}		
		public Long getWorkItemId() {
			return workItemId;
		}
		public void setWorkItemId(Long workItemId) {
			this.workItemId = workItemId;
		}		
		public Long getNodeId() {
			return nodeId;
		}
		public void setNodeId(Long nodeId) {
			this.nodeId = nodeId;
		}

		public Long getOriginalPrice() {
			return originalPrice;
		}

		public void setOriginalPrice(Long originalPrice) {
			this.originalPrice = originalPrice;
		}

		public Long getIsDevice() {
			return isDevice;
		}

		public void setIsDevice(Long isDevice) {
			this.isDevice = isDevice;
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

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		public Long getLongTermAssetId() {
			return longTermAssetId;
		}

		public void setLongTermAssetId(Long longTermAssetId) {
			this.longTermAssetId = longTermAssetId;
		}

		public Long getLongTermAssetEntityId() {
			return longTermAssetEntityId;
		}

		public void setLongTermAssetEntityId(Long longTermAssetEntityId) {
			this.longTermAssetEntityId = longTermAssetEntityId;
		}

		public String getCheckUsing() {
			return checkUsing;
		}

		public void setCheckUsing(String checkUsing) {
			this.checkUsing = checkUsing;
		}		
	
}
