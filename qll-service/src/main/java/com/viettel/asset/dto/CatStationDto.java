package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.CatStation;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "catStation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatStationDto {
	
	
		private Long id;
		private String stationCode;
		private String address;
		private String description;
		private Long isActive;
		private Long provinceId;
		private Long startPointId;
		private Long endPointId;
		private Long lineTypeId;
		private Long lineLength;
		private Date emissionDate;
		private Long scope;
		private Long endPointType;
		private Long isInventory;
		private Long parentId;
		private Long preParentId;
		private Long catStationTypeId;
		private Long importForYear;
		private Long isAttach;
		private Long attachId;
		private Long statusTktu;
		private String processStatus;
		private Double latitude;
		private Double longitude;
		private Double distanceOdd;
		private Date usedDate;
		private Long isDelete;
		private String descriptDel;
		private Long creatorId;
		private Long groupId;
		private Date addTime;
		private Date updateDate;
		private Long isBase;
		private String areaLocation;
		private Long startPointType;
		private Long isEvn;
		private Long createGroupId;
		private Long stationBtsId;
		private String oldStationCode;
		private Long isOff;
		private Long type;
		private Long isLock;
		private Long catStationHouseId;
		private Long isSfone;
		private Long updatorId;
		private Date onDate;
		private Date dateOnStation;
		private Long userOnStation;
		private Long planId;
		private String statusCode;
		private String crNumber;
		private Date syncTime;
	
	public CatStationDto() {
		
	}
	
	public CatStationDto(CatStation entity) {
		this.id = entity.getId();
		this.stationCode = entity.getStationCode();
		this.address = entity.getAddress();
		this.description = entity.getDescription();
		this.isActive = entity.getIsActive();
		this.provinceId = entity.getProvinceId();
		this.startPointId = entity.getStartPointId();
		this.endPointId = entity.getEndPointId();
		this.lineTypeId = entity.getLineTypeId();
		this.lineLength = entity.getLineLength();
		this.emissionDate = entity.getEmissionDate();
		this.scope = entity.getScope();
		this.endPointType = entity.getEndPointType();
		this.isInventory = entity.getIsInventory();
		this.parentId = entity.getParentId();
		this.preParentId = entity.getPreParentId();
		this.catStationTypeId = entity.getCatStationTypeId();
		this.importForYear = entity.getImportForYear();
		this.isAttach = entity.getIsAttach();
		this.attachId = entity.getAttachId();
		this.statusTktu = entity.getStatusTktu();
		this.processStatus = entity.getProcessStatus();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
		this.distanceOdd = entity.getDistanceOdd();
		this.usedDate = entity.getUsedDate();
		this.isDelete = entity.getIsDelete();
		this.descriptDel = entity.getDescriptDel();
		this.creatorId = entity.getCreatorId();
		this.groupId = entity.getGroupId();
		this.addTime = entity.getAddTime();
		this.updateDate = entity.getUpdateDate();
		this.isBase = entity.getIsBase();
		this.areaLocation = entity.getAreaLocation();
		this.startPointType = entity.getStartPointType();
		this.isEvn = entity.getIsEvn();
		this.createGroupId = entity.getCreateGroupId();
		this.stationBtsId = entity.getStationBtsId();
		this.oldStationCode = entity.getOldStationCode();
		this.isOff = entity.getIsOff();
//		this.type = entity.getType();
		this.isLock = entity.getIsLock();
		this.catStationHouseId = entity.getCatStationHouseId();
		this.isSfone = entity.getIsSfone();
		this.updatorId = entity.getUpdatorId();
		this.onDate = entity.getOnDate();
		this.dateOnStation = entity.getDateOnStation();
		this.userOnStation = entity.getUserOnStation();
		this.planId = entity.getPlanId();
		this.statusCode = entity.getStatusCode();
		this.crNumber = entity.getCrNumber();
		this.syncTime = entity.getSyncTime();
	}
	
	public CatStation toEntity() {
		CatStation entity = new CatStation();
		entity.setId(this.id);
		entity.setStationCode(this.stationCode);
		entity.setAddress(this.address);
		entity.setDescription(this.description);
		entity.setIsActive(this.isActive);
		entity.setProvinceId(this.provinceId);
		entity.setStartPointId(this.startPointId);
		entity.setEndPointId(this.endPointId);
		entity.setLineTypeId(this.lineTypeId);
		entity.setLineLength(this.lineLength);
		entity.setEmissionDate(this.emissionDate);
		entity.setScope(this.scope);
		entity.setEndPointType(this.endPointType);
		entity.setIsInventory(this.isInventory);
		entity.setParentId(this.parentId);
		entity.setPreParentId(this.preParentId);
		entity.setCatStationTypeId(this.catStationTypeId);
		entity.setImportForYear(this.importForYear);
		entity.setIsAttach(this.isAttach);
		entity.setAttachId(this.attachId);
		entity.setStatusTktu(this.statusTktu);
		entity.setProcessStatus(this.processStatus);
		entity.setLatitude(this.latitude);
		entity.setLongitude(this.longitude);
		entity.setDistanceOdd(this.distanceOdd);
		entity.setUsedDate(this.usedDate);
		entity.setIsDelete(this.isDelete);
		entity.setDescriptDel(this.descriptDel);
		entity.setCreatorId(this.creatorId);
		entity.setGroupId(this.groupId);
		entity.setAddTime(this.addTime);
		entity.setUpdateDate(this.updateDate);
		entity.setIsBase(this.isBase);
		entity.setAreaLocation(this.areaLocation);
		entity.setStartPointType(this.startPointType);
		entity.setIsEvn(this.isEvn);
		entity.setCreateGroupId(this.createGroupId);
		entity.setStationBtsId(this.stationBtsId);
		entity.setOldStationCode(this.oldStationCode);
		entity.setIsOff(this.isOff);
//		entity.setType(this.type);
		entity.setIsLock(this.isLock);
		entity.setCatStationHouseId(this.catStationHouseId);
		entity.setIsSfone(this.isSfone);
		entity.setUpdatorId(this.updatorId);
		entity.setOnDate(this.onDate);
		entity.setDateOnStation(this.dateOnStation);
		entity.setUserOnStation(this.userOnStation);
		entity.setPlanId(this.planId);
		entity.setStatusCode(this.statusCode);
		entity.setCrNumber(this.crNumber);
		entity.setSyncTime(this.syncTime);
		return entity;
	}
	
	public void updateEntity(CatStation entity) {
			entity.setStationCode(this.stationCode);
			entity.setAddress(this.address);
			entity.setDescription(this.description);
			entity.setIsActive(this.isActive);
			entity.setProvinceId(this.provinceId);
			entity.setStartPointId(this.startPointId);
			entity.setEndPointId(this.endPointId);
			entity.setLineTypeId(this.lineTypeId);
			entity.setLineLength(this.lineLength);
			entity.setEmissionDate(this.emissionDate);
			entity.setScope(this.scope);
			entity.setEndPointType(this.endPointType);
			entity.setIsInventory(this.isInventory);
			entity.setParentId(this.parentId);
			entity.setPreParentId(this.preParentId);
			entity.setCatStationTypeId(this.catStationTypeId);
			entity.setImportForYear(this.importForYear);
			entity.setIsAttach(this.isAttach);
			entity.setAttachId(this.attachId);
			entity.setStatusTktu(this.statusTktu);
			entity.setProcessStatus(this.processStatus);
			entity.setLatitude(this.latitude);
			entity.setLongitude(this.longitude);
			entity.setDistanceOdd(this.distanceOdd);
			entity.setUsedDate(this.usedDate);
			entity.setIsDelete(this.isDelete);
			entity.setDescriptDel(this.descriptDel);
			entity.setCreatorId(this.creatorId);
			entity.setGroupId(this.groupId);
			entity.setAddTime(this.addTime);
			entity.setUpdateDate(this.updateDate);
			entity.setIsBase(this.isBase);
			entity.setAreaLocation(this.areaLocation);
			entity.setStartPointType(this.startPointType);
			entity.setIsEvn(this.isEvn);
			entity.setCreateGroupId(this.createGroupId);
			entity.setStationBtsId(this.stationBtsId);
			entity.setOldStationCode(this.oldStationCode);
			entity.setIsOff(this.isOff);
//			entity.setType(this.type);
			entity.setIsLock(this.isLock);
			entity.setCatStationHouseId(this.catStationHouseId);
			entity.setIsSfone(this.isSfone);
			entity.setUpdatorId(this.updatorId);
			entity.setOnDate(this.onDate);
			entity.setDateOnStation(this.dateOnStation);
			entity.setUserOnStation(this.userOnStation);
			entity.setPlanId(this.planId);
			entity.setStatusCode(this.statusCode);
			entity.setCrNumber(this.crNumber);
			entity.setSyncTime(this.syncTime);
	}
	
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}		
		public String getStationCode() {
			return stationCode;
		}
		public void setStationCode(String stationCode) {
			this.stationCode = stationCode;
		}		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
		public Long getIsActive() {
			return isActive;
		}
		public void setIsActive(Long isActive) {
			this.isActive = isActive;
		}		
		public Long getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(Long provinceId) {
			this.provinceId = provinceId;
		}		
		public Long getStartPointId() {
			return startPointId;
		}
		public void setStartPointId(Long startPointId) {
			this.startPointId = startPointId;
		}		
		public Long getEndPointId() {
			return endPointId;
		}
		public void setEndPointId(Long endPointId) {
			this.endPointId = endPointId;
		}		
		public Long getLineTypeId() {
			return lineTypeId;
		}
		public void setLineTypeId(Long lineTypeId) {
			this.lineTypeId = lineTypeId;
		}		
		public Long getLineLength() {
			return lineLength;
		}
		public void setLineLength(Long lineLength) {
			this.lineLength = lineLength;
		}		
		public Date getEmissionDate() {
			return emissionDate;
		}
		public void setEmissionDate(Date emissionDate) {
			this.emissionDate = emissionDate;
		}		
		public Long getScope() {
			return scope;
		}
		public void setScope(Long scope) {
			this.scope = scope;
		}		
		public Long getEndPointType() {
			return endPointType;
		}
		public void setEndPointType(Long endPointType) {
			this.endPointType = endPointType;
		}		
		public Long getIsInventory() {
			return isInventory;
		}
		public void setIsInventory(Long isInventory) {
			this.isInventory = isInventory;
		}		
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public Long getPreParentId() {
			return preParentId;
		}
		public void setPreParentId(Long preParentId) {
			this.preParentId = preParentId;
		}		
		public Long getCatStationTypeId() {
			return catStationTypeId;
		}
		public void setCatStationTypeId(Long catStationTypeId) {
			this.catStationTypeId = catStationTypeId;
		}		
		public Long getImportForYear() {
			return importForYear;
		}
		public void setImportForYear(Long importForYear) {
			this.importForYear = importForYear;
		}		
		public Long getIsAttach() {
			return isAttach;
		}
		public void setIsAttach(Long isAttach) {
			this.isAttach = isAttach;
		}		
		public Long getAttachId() {
			return attachId;
		}
		public void setAttachId(Long attachId) {
			this.attachId = attachId;
		}		
		public Long getStatusTktu() {
			return statusTktu;
		}
		public void setStatusTktu(Long statusTktu) {
			this.statusTktu = statusTktu;
		}		
		public String getProcessStatus() {
			return processStatus;
		}
		public void setProcessStatus(String processStatus) {
			this.processStatus = processStatus;
		}		
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}		
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}		
		public Double getDistanceOdd() {
			return distanceOdd;
		}
		public void setDistanceOdd(Double distanceOdd) {
			this.distanceOdd = distanceOdd;
		}		
		public Date getUsedDate() {
			return usedDate;
		}
		public void setUsedDate(Date usedDate) {
			this.usedDate = usedDate;
		}		
		public Long getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Long isDelete) {
			this.isDelete = isDelete;
		}		
		public String getDescriptDel() {
			return descriptDel;
		}
		public void setDescriptDel(String descriptDel) {
			this.descriptDel = descriptDel;
		}		
		public Long getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(Long creatorId) {
			this.creatorId = creatorId;
		}		
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}		
		public Date getAddTime() {
			return addTime;
		}
		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}		
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}		
		public Long getIsBase() {
			return isBase;
		}
		public void setIsBase(Long isBase) {
			this.isBase = isBase;
		}		
		public String getAreaLocation() {
			return areaLocation;
		}
		public void setAreaLocation(String areaLocation) {
			this.areaLocation = areaLocation;
		}		
		public Long getStartPointType() {
			return startPointType;
		}
		public void setStartPointType(Long startPointType) {
			this.startPointType = startPointType;
		}		
		public Long getIsEvn() {
			return isEvn;
		}
		public void setIsEvn(Long isEvn) {
			this.isEvn = isEvn;
		}		
		public Long getCreateGroupId() {
			return createGroupId;
		}
		public void setCreateGroupId(Long createGroupId) {
			this.createGroupId = createGroupId;
		}		
		public Long getStationBtsId() {
			return stationBtsId;
		}
		public void setStationBtsId(Long stationBtsId) {
			this.stationBtsId = stationBtsId;
		}		
		public String getOldStationCode() {
			return oldStationCode;
		}
		public void setOldStationCode(String oldStationCode) {
			this.oldStationCode = oldStationCode;
		}		
		public Long getIsOff() {
			return isOff;
		}
		public void setIsOff(Long isOff) {
			this.isOff = isOff;
		}		
		public Long getType() {
			return type;
		}
		public void setType(Long type) {
			this.type = type;
		}		
		public Long getIsLock() {
			return isLock;
		}
		public void setIsLock(Long isLock) {
			this.isLock = isLock;
		}		
		public Long getCatStationHouseId() {
			return catStationHouseId;
		}
		public void setCatStationHouseId(Long catStationHouseId) {
			this.catStationHouseId = catStationHouseId;
		}		
		public Long getIsSfone() {
			return isSfone;
		}
		public void setIsSfone(Long isSfone) {
			this.isSfone = isSfone;
		}		
		public Long getUpdatorId() {
			return updatorId;
		}
		public void setUpdatorId(Long updatorId) {
			this.updatorId = updatorId;
		}		
		public Date getOnDate() {
			return onDate;
		}
		public void setOnDate(Date onDate) {
			this.onDate = onDate;
		}		
		public Date getDateOnStation() {
			return dateOnStation;
		}
		public void setDateOnStation(Date dateOnStation) {
			this.dateOnStation = dateOnStation;
		}		
		public Long getUserOnStation() {
			return userOnStation;
		}
		public void setUserOnStation(Long userOnStation) {
			this.userOnStation = userOnStation;
		}		
		public Long getPlanId() {
			return planId;
		}
		public void setPlanId(Long planId) {
			this.planId = planId;
		}		
		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}		
		public String getCrNumber() {
			return crNumber;
		}
		public void setCrNumber(String crNumber) {
			this.crNumber = crNumber;
		}		
		public Date getSyncTime() {
			return syncTime;
		}
		public void setSyncTime(Date syncTime) {
			this.syncTime = syncTime;
		}		
	
}
