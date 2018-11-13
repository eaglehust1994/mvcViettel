package com.viettel.asset.dto;

import com.viettel.asset.bo.ConstrMerchandise;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "constrMerchandise")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrMerchandiseDto {
	
	
		private Long id;
		private Double quantity;
		private Long isActive;
		private Long merEntityId;
		private Long workItemId;
		private Long constructId;
		private Long rootMerEntityId;
		private Long remainCount;
		private Long merId;
		private Long upgradeParentId;
		private Long nodeId;
	
	public ConstrMerchandiseDto() {
		
	}
	
	public ConstrMerchandiseDto(ConstrMerchandise entity) {
		this.id = entity.getId();
		this.quantity = entity.getQuantity();
		this.isActive = entity.getIsActive();
		this.merEntityId = entity.getMerEntityId();
		this.workItemId = entity.getWorkItemId();
		this.constructId = entity.getConstructId();
		this.rootMerEntityId = entity.getRootMerEntityId();
		this.remainCount = entity.getRemainCount();
		this.merId = entity.getMerId();
		this.upgradeParentId = entity.getUpgradeParentId();
		this.nodeId = entity.getNodeId();
	}
	
	public ConstrMerchandise toEntity() {
		ConstrMerchandise entity = new ConstrMerchandise();
		entity.setId(this.id);
		entity.setQuantity(this.quantity);
		entity.setIsActive(this.isActive);
		entity.setMerEntityId(this.merEntityId);
		entity.setWorkItemId(this.workItemId);
		entity.setConstructId(this.constructId);
		entity.setRootMerEntityId(this.rootMerEntityId);
		entity.setRemainCount(this.remainCount);
		entity.setMerId(this.merId);
		entity.setUpgradeParentId(this.upgradeParentId);
		entity.setNodeId(this.nodeId);
		return entity;
	}
	
	public void updateEntity(ConstrMerchandise entity) {
			entity.setQuantity(this.quantity);
			entity.setIsActive(this.isActive);
			entity.setMerEntityId(this.merEntityId);
			entity.setWorkItemId(this.workItemId);
			entity.setConstructId(this.constructId);
			entity.setRootMerEntityId(this.rootMerEntityId);
			entity.setRemainCount(this.remainCount);
			entity.setMerId(this.merId);
			entity.setUpgradeParentId(this.upgradeParentId);
			entity.setNodeId(this.nodeId);
	}
	
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}		
		public Double getQuantity() {
			return quantity;
		}
		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}		
		public Long getIsActive() {
			return isActive;
		}
		public void setIsActive(Long isActive) {
			this.isActive = isActive;
		}		
		public Long getMerEntityId() {
			return merEntityId;
		}
		public void setMerEntityId(Long merEntityId) {
			this.merEntityId = merEntityId;
		}		
		public Long getWorkItemId() {
			return workItemId;
		}
		public void setWorkItemId(Long workItemId) {
			this.workItemId = workItemId;
		}		
		public Long getConstructId() {
			return constructId;
		}
		public void setConstructId(Long constructId) {
			this.constructId = constructId;
		}		
		public Long getRootMerEntityId() {
			return rootMerEntityId;
		}
		public void setRootMerEntityId(Long rootMerEntityId) {
			this.rootMerEntityId = rootMerEntityId;
		}		
		public Long getRemainCount() {
			return remainCount;
		}
		public void setRemainCount(Long remainCount) {
			this.remainCount = remainCount;
		}		
		public Long getMerId() {
			return merId;
		}
		public void setMerId(Long merId) {
			this.merId = merId;
		}		
		public Long getUpgradeParentId() {
			return upgradeParentId;
		}
		public void setUpgradeParentId(Long upgradeParentId) {
			this.upgradeParentId = upgradeParentId;
		}		
		public Long getNodeId() {
			return nodeId;
		}
		public void setNodeId(Long nodeId) {
			this.nodeId = nodeId;
		}		
	
}
