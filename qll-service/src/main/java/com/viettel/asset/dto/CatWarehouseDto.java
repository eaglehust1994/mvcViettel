package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.CatWarehouse;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "catWarehouse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatWarehouseDto {
	
	
		private Long warehouseId;
		private String name;
		private String code;
		private String address;
		private Long groupId;
		private Long isActive;
		private Long parentId;
		private String title;
		private Long theLevel;
		private String path;
		private String whType;
		private Long ownGroupId;
		private Long companyType;
		private Long virtualType;
		private Long warehouseCloneId;
		private Long provinceId;
		private Long isOpen;
		private Date createdDate;
		private Long catWarehouseTypeId;
		private Long exportEachOther;
		private Long lockStatus;
		private Long catWarehousePhysicId;
		private Long warehouseLogisticId;
		private Date modifieldDate;
		private Long isInventory;
	
	public CatWarehouseDto() {
		
	}
	
	public CatWarehouseDto(CatWarehouse entity) {
		this.warehouseId = entity.getWarehouseId();
		this.name = entity.getName();
		this.code = entity.getCode();
		this.address = entity.getAddress();
		this.groupId = entity.getGroupId();
		this.isActive = entity.getIsActive();
		this.parentId = entity.getParentId();
		this.title = entity.getTitle();
		this.theLevel = entity.getTheLevel();
		this.path = entity.getPath();
		this.whType = entity.getWhType();
		this.ownGroupId = entity.getOwnGroupId();
		this.companyType = entity.getCompanyType();
		this.virtualType = entity.getVirtualType();
		this.warehouseCloneId = entity.getWarehouseCloneId();
		this.provinceId = entity.getProvinceId();
		this.isOpen = entity.getIsOpen();
		this.createdDate = entity.getCreatedDate();
		this.catWarehouseTypeId = entity.getCatWarehouseTypeId();
		this.exportEachOther = entity.getExportEachOther();
		this.lockStatus = entity.getLockStatus();
		this.catWarehousePhysicId = entity.getCatWarehousePhysicId();
		this.warehouseLogisticId = entity.getWarehouseLogisticId();
		this.modifieldDate = entity.getModifieldDate();
		this.isInventory = entity.getIsInventory();
	}
	
	public CatWarehouse toEntity() {
		CatWarehouse entity = new CatWarehouse();
		entity.setWarehouseId(this.warehouseId);
		entity.setName(this.name);
		entity.setCode(this.code);
		entity.setAddress(this.address);
		entity.setGroupId(this.groupId);
		entity.setIsActive(this.isActive);
		entity.setParentId(this.parentId);
		entity.setTitle(this.title);
		entity.setTheLevel(this.theLevel);
		entity.setPath(this.path);
		entity.setWhType(this.whType);
		entity.setOwnGroupId(this.ownGroupId);
		entity.setCompanyType(this.companyType);
		entity.setVirtualType(this.virtualType);
		entity.setWarehouseCloneId(this.warehouseCloneId);
		entity.setProvinceId(this.provinceId);
		entity.setIsOpen(this.isOpen);
		entity.setCreatedDate(this.createdDate);
		entity.setCatWarehouseTypeId(this.catWarehouseTypeId);
		entity.setExportEachOther(this.exportEachOther);
		entity.setLockStatus(this.lockStatus);
		entity.setCatWarehousePhysicId(this.catWarehousePhysicId);
		entity.setWarehouseLogisticId(this.warehouseLogisticId);
		entity.setModifieldDate(this.modifieldDate);
		entity.setIsInventory(this.isInventory);
		return entity;
	}
	
	public void updateEntity(CatWarehouse entity) {
			entity.setName(this.name);
			entity.setCode(this.code);
			entity.setAddress(this.address);
			entity.setGroupId(this.groupId);
			entity.setIsActive(this.isActive);
			entity.setParentId(this.parentId);
			entity.setTitle(this.title);
			entity.setTheLevel(this.theLevel);
			entity.setPath(this.path);
			entity.setWhType(this.whType);
			entity.setOwnGroupId(this.ownGroupId);
			entity.setCompanyType(this.companyType);
			entity.setVirtualType(this.virtualType);
			entity.setWarehouseCloneId(this.warehouseCloneId);
			entity.setProvinceId(this.provinceId);
			entity.setIsOpen(this.isOpen);
			entity.setCreatedDate(this.createdDate);
			entity.setCatWarehouseTypeId(this.catWarehouseTypeId);
			entity.setExportEachOther(this.exportEachOther);
			entity.setLockStatus(this.lockStatus);
			entity.setCatWarehousePhysicId(this.catWarehousePhysicId);
			entity.setWarehouseLogisticId(this.warehouseLogisticId);
			entity.setModifieldDate(this.modifieldDate);
			entity.setIsInventory(this.isInventory);
	}
	
		public Long getWarehouseId() {
			return warehouseId;
		}
		public void setWarehouseId(Long warehouseId) {
			this.warehouseId = warehouseId;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}		
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}		
		public Long getIsActive() {
			return isActive;
		}
		public void setIsActive(Long isActive) {
			this.isActive = isActive;
		}		
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}		
		public Long getTheLevel() {
			return theLevel;
		}
		public void setTheLevel(Long theLevel) {
			this.theLevel = theLevel;
		}		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}		
		public String getWhType() {
			return whType;
		}
		public void setWhType(String whType) {
			this.whType = whType;
		}		
		public Long getOwnGroupId() {
			return ownGroupId;
		}
		public void setOwnGroupId(Long ownGroupId) {
			this.ownGroupId = ownGroupId;
		}		
		public Long getCompanyType() {
			return companyType;
		}
		public void setCompanyType(Long companyType) {
			this.companyType = companyType;
		}		
		public Long getVirtualType() {
			return virtualType;
		}
		public void setVirtualType(Long virtualType) {
			this.virtualType = virtualType;
		}		
		public Long getWarehouseCloneId() {
			return warehouseCloneId;
		}
		public void setWarehouseCloneId(Long warehouseCloneId) {
			this.warehouseCloneId = warehouseCloneId;
		}		
		public Long getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(Long provinceId) {
			this.provinceId = provinceId;
		}		
		public Long getIsOpen() {
			return isOpen;
		}
		public void setIsOpen(Long isOpen) {
			this.isOpen = isOpen;
		}		
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}		
		public Long getCatWarehouseTypeId() {
			return catWarehouseTypeId;
		}
		public void setCatWarehouseTypeId(Long catWarehouseTypeId) {
			this.catWarehouseTypeId = catWarehouseTypeId;
		}		
		public Long getExportEachOther() {
			return exportEachOther;
		}
		public void setExportEachOther(Long exportEachOther) {
			this.exportEachOther = exportEachOther;
		}		
		public Long getLockStatus() {
			return lockStatus;
		}
		public void setLockStatus(Long lockStatus) {
			this.lockStatus = lockStatus;
		}		
		public Long getCatWarehousePhysicId() {
			return catWarehousePhysicId;
		}
		public void setCatWarehousePhysicId(Long catWarehousePhysicId) {
			this.catWarehousePhysicId = catWarehousePhysicId;
		}		
		public Long getWarehouseLogisticId() {
			return warehouseLogisticId;
		}
		public void setWarehouseLogisticId(Long warehouseLogisticId) {
			this.warehouseLogisticId = warehouseLogisticId;
		}		
		public Date getModifieldDate() {
			return modifieldDate;
		}
		public void setModifieldDate(Date modifieldDate) {
			this.modifieldDate = modifieldDate;
		}		
		public Long getIsInventory() {
			return isInventory;
		}
		public void setIsInventory(Long isInventory) {
			this.isInventory = isInventory;
		}		
	
}
