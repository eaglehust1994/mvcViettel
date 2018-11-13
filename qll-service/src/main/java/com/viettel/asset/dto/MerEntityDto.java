package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.MerEntity;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "merEntity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerEntityDto {
	
	
		private Long merEntityId;
		private String serialNumber;
		private Long unitPrice;
		private Long companyId;
		private Long nationalId;
		private Long madeYear;
		private Double maxUsedTime;
		private Long merId;
		private Long contractId;
		private Long groupId;
		private Long warehouseId;
		private Long constructionId;
		private Long isMerchandise;
		private Long whPosId;
		private Long statusId;
		private Long parentId;
		private Long isContractAssigned;
		private Long changeAction;
		private Long userId;
		private String addedPart;
		private Long upgradeParentId;
		private String partNumber;
		private Long count;
		private Date amortStartDate;
		private Long isTemp;
		private Double merWeight;
		private String serialNumberOwner;
		private String partNumberOwner;
		private String path;
		private Long workItemId;
		private Long originalPrice;
		private Date expiredWarrantyDate;
		private Long warrantPartnerId;
		private Long projectId;
		private Long proposalId;
		private Date nextMaintainDate;
		private Long packageId;
		private Long sourceId;
		private String managerName;
		private Long isExpense;
		private Long useGroupId;
		private Long merRootId;
		private Long taxRate;
		private Long vndUnitPrice;
		private Long parentConfigId;
		private Long fileId;
		private Long plIndId;
		private String shipmentNo;
		private String plCode;
		private String boxNo;
		private Long keySearch;
		private String contractCode;
		private Long isCheckedKcs;
		private Long oldMerEntityId;
		private Long isMapped;
		private String description;
		private Long plImportId;
		private Long stationId;
		private Long isInventory;
		private Date usedDate;
		private Date latestExportDate;
		private Date latestImportDate;
		private Long mapNo;
		private Long accountType;
		private Date depreciationDate;
		private Date latestMaintainDate;
		private Date latestMaintainDateBk;
		private Long countMaintain;
		private Date nextMaintainDateBk;
		private Long twinMerEntityId;
		private Long oldMerId;
		private Long oldContractId;
		private Long typeDefineHis;
		private Long entityType;
		private String usedName;
		private String usedGroup;
		private String merModel;
		private Long ownerMerId;
		private String tel;
		private String email;
		private Long markNum;
		private String propertyDetail;
		private String serialNumberParent;
		private Long splitMer;
		private Long quality;
		private Long isOriginal;
		private Long nodeId;
		private Long catEmployeeId;
		private Long oldMerContractId;
		private Long typeOutSide;
		private Date createdDate;
		private Double unitPriceDomestic;
		private Double unitPriceForeign;
		private Long isLock;
		private Long isAppo;
		private Long isProcessCompare;
		private Date importDate;
		private String realSerialNumber;
		private Long isvalid;
		private Date executeDateKdhc;
		private Date executeDateBdsc;
		private Long typeStationMaintain;
		private Long executeMonthTimeUsed;
		private Long assetPrice;
		private Long isCheckImp;
		private Long assetPlanUseId;
		private Long isExpired;
		private Long objLevel;
		private Long longTermAssetId;
		
		private String code;
		private String name;
		private Long unitId;
		private String unitName;
		
		
	
	public String getUnitName() {
			return unitName;
		}

		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

	public MerEntityDto() {
		
	}
	
	public MerEntityDto(MerEntity entity) {
		this.merEntityId = entity.getMerEntityId();
		this.serialNumber = entity.getSerialNumber();
		this.unitPrice = entity.getUnitPrice();
		this.companyId = entity.getCompanyId();
		this.nationalId = entity.getNationalId();
		this.madeYear = entity.getMadeYear();
		this.maxUsedTime = entity.getMaxUsedTime();
		this.merId = entity.getMerId();
		this.contractId = entity.getContractId();
		this.groupId = entity.getGroupId();
		this.warehouseId = entity.getWarehouseId();
		this.constructionId = entity.getConstructionId();
		this.isMerchandise = entity.getIsMerchandise();
		this.whPosId = entity.getWhPosId();
		this.statusId = entity.getStatusId();
		this.parentId = entity.getParentId();
		this.isContractAssigned = entity.getIsContractAssigned();
		this.changeAction = entity.getChangeAction();
		this.userId = entity.getUserId();
		this.addedPart = entity.getAddedPart();
		this.upgradeParentId = entity.getUpgradeParentId();
		this.partNumber = entity.getPartNumber();
		this.count = entity.getCount();
		this.amortStartDate = entity.getAmortStartDate();
		this.isTemp = entity.getIsTemp();
		this.merWeight = entity.getMerWeight();
		this.serialNumberOwner = entity.getSerialNumberOwner();
		this.partNumberOwner = entity.getPartNumberOwner();
		this.path = entity.getPath();
		this.workItemId = entity.getWorkItemId();
		this.originalPrice = entity.getOriginalPrice();
		this.expiredWarrantyDate = entity.getExpiredWarrantyDate();
		this.warrantPartnerId = entity.getWarrantPartnerId();
		this.projectId = entity.getProjectId();
		this.proposalId = entity.getProposalId();
		this.nextMaintainDate = entity.getNextMaintainDate();
		this.packageId = entity.getPackageId();
		this.sourceId = entity.getSourceId();
		this.managerName = entity.getManagerName();
		this.isExpense = entity.getIsExpense();
		this.useGroupId = entity.getUseGroupId();
		this.merRootId = entity.getMerRootId();
		this.taxRate = entity.getTaxRate();
		this.vndUnitPrice = entity.getVndUnitPrice();
		this.parentConfigId = entity.getParentConfigId();
		this.fileId = entity.getFileId();
		this.plIndId = entity.getPlIndId();
		this.shipmentNo = entity.getShipmentNo();
		this.plCode = entity.getPlCode();
		this.boxNo = entity.getBoxNo();
		this.keySearch = entity.getKeySearch();
		this.contractCode = entity.getContractCode();
		this.isCheckedKcs = entity.getIsCheckedKcs();
		this.oldMerEntityId = entity.getOldMerEntityId();
		this.isMapped = entity.getIsMapped();
		this.description = entity.getDescription();
		this.plImportId = entity.getPlImportId();
		this.stationId = entity.getStationId();
		this.isInventory = entity.getIsInventory();
		this.usedDate = entity.getUsedDate();
		this.latestExportDate = entity.getLatestExportDate();
		this.latestImportDate = entity.getLatestImportDate();
		this.mapNo = entity.getMapNo();
		this.accountType = entity.getAccountType();
		this.depreciationDate = entity.getDepreciationDate();
		this.latestMaintainDate = entity.getLatestMaintainDate();
		this.latestMaintainDateBk = entity.getLatestMaintainDateBk();
		this.countMaintain = entity.getCountMaintain();
		this.nextMaintainDateBk = entity.getNextMaintainDateBk();
		this.twinMerEntityId = entity.getTwinMerEntityId();
		this.oldMerId = entity.getOldMerId();
		this.oldContractId = entity.getOldContractId();
		this.typeDefineHis = entity.getTypeDefineHis();
		this.entityType = entity.getEntityType();
//		this.usedName = entity.getUsedName();
		this.usedGroup = entity.getUsedGroup();
		this.merModel = entity.getMerModel();
//		this.ownerMerId = entity.getOwnerMerId();
		this.tel = entity.getTel();
		this.email = entity.getEmail();
		this.markNum = entity.getMarkNum();
		this.propertyDetail = entity.getPropertyDetail();
		this.serialNumberParent = entity.getSerialNumberParent();
		this.splitMer = entity.getSplitMer();
		this.quality = entity.getQuality();
		this.isOriginal = entity.getIsOriginal();
		this.nodeId = entity.getNodeId();
		this.catEmployeeId = entity.getCatEmployeeId();
//		this.oldMerContractId = entity.getOldMerContractId();
		this.typeOutSide = entity.getTypeOutSide();
		this.createdDate = entity.getCreatedDate();
		this.unitPriceDomestic = entity.getUnitPriceDomestic();
		this.unitPriceForeign = entity.getUnitPriceForeign();
		this.isLock = entity.getIsLock();
		this.isAppo = entity.getIsAppo();
		this.isProcessCompare = entity.getIsProcessCompare();
		this.importDate = entity.getImportDate();
		this.realSerialNumber = entity.getRealSerialNumber();
		this.isvalid = entity.getIsvalid();
		this.executeDateKdhc = entity.getExecuteDateKdhc();
		this.executeDateBdsc = entity.getExecuteDateBdsc();
		this.typeStationMaintain = entity.getTypeStationMaintain();
		this.executeMonthTimeUsed = entity.getExecuteMonthTimeUsed();
		this.assetPrice = entity.getAssetPrice();
		this.isCheckImp = entity.getIsCheckImp();
		this.assetPlanUseId = entity.getAssetPlanUseId();
		this.isExpired = entity.getIsExpired();
		this.objLevel = entity.getObjLevel();
		this.longTermAssetId = entity.getLongTermAssetId();
	}
	
	public MerEntity toEntity() {
		MerEntity entity = new MerEntity();
		entity.setMerEntityId(this.merEntityId);
		entity.setSerialNumber(this.serialNumber);
		entity.setUnitPrice(this.unitPrice);
		entity.setCompanyId(this.companyId);
		entity.setNationalId(this.nationalId);
		entity.setMadeYear(this.madeYear);
		entity.setMaxUsedTime(this.maxUsedTime);
		entity.setMerId(this.merId);
		entity.setContractId(this.contractId);
		entity.setGroupId(this.groupId);
		entity.setWarehouseId(this.warehouseId);
		entity.setConstructionId(this.constructionId);
		entity.setIsMerchandise(this.isMerchandise);
		entity.setWhPosId(this.whPosId);
		entity.setStatusId(this.statusId);
		entity.setParentId(this.parentId);
		entity.setIsContractAssigned(this.isContractAssigned);
		entity.setChangeAction(this.changeAction);
		entity.setUserId(this.userId);
		entity.setAddedPart(this.addedPart);
		entity.setUpgradeParentId(this.upgradeParentId);
		entity.setPartNumber(this.partNumber);
		entity.setCount(this.count);
		entity.setAmortStartDate(this.amortStartDate);
		entity.setIsTemp(this.isTemp);
		entity.setMerWeight(this.merWeight);
		entity.setSerialNumberOwner(this.serialNumberOwner);
		entity.setPartNumberOwner(this.partNumberOwner);
		entity.setPath(this.path);
		entity.setWorkItemId(this.workItemId);
		entity.setOriginalPrice(this.originalPrice);
		entity.setExpiredWarrantyDate(this.expiredWarrantyDate);
		entity.setWarrantPartnerId(this.warrantPartnerId);
		entity.setProjectId(this.projectId);
		entity.setProposalId(this.proposalId);
		entity.setNextMaintainDate(this.nextMaintainDate);
		entity.setPackageId(this.packageId);
		entity.setSourceId(this.sourceId);
		entity.setManagerName(this.managerName);
		entity.setIsExpense(this.isExpense);
		entity.setUseGroupId(this.useGroupId);
		entity.setMerRootId(this.merRootId);
		entity.setTaxRate(this.taxRate);
		entity.setVndUnitPrice(this.vndUnitPrice);
		entity.setParentConfigId(this.parentConfigId);
		entity.setFileId(this.fileId);
		entity.setPlIndId(this.plIndId);
		entity.setShipmentNo(this.shipmentNo);
		entity.setPlCode(this.plCode);
		entity.setBoxNo(this.boxNo);
		entity.setKeySearch(this.keySearch);
		entity.setContractCode(this.contractCode);
		entity.setIsCheckedKcs(this.isCheckedKcs);
		entity.setOldMerEntityId(this.oldMerEntityId);
		entity.setIsMapped(this.isMapped);
		entity.setDescription(this.description);
		entity.setPlImportId(this.plImportId);
		entity.setStationId(this.stationId);
		entity.setIsInventory(this.isInventory);
		entity.setUsedDate(this.usedDate);
		entity.setLatestExportDate(this.latestExportDate);
		entity.setLatestImportDate(this.latestImportDate);
		entity.setMapNo(this.mapNo);
		entity.setAccountType(this.accountType);
		entity.setDepreciationDate(this.depreciationDate);
		entity.setLatestMaintainDate(this.latestMaintainDate);
		entity.setLatestMaintainDateBk(this.latestMaintainDateBk);
		entity.setCountMaintain(this.countMaintain);
		entity.setNextMaintainDateBk(this.nextMaintainDateBk);
		entity.setTwinMerEntityId(this.twinMerEntityId);
		entity.setOldMerId(this.oldMerId);
		entity.setOldContractId(this.oldContractId);
		entity.setTypeDefineHis(this.typeDefineHis);
		entity.setEntityType(this.entityType);
//		entity.setUsedName(this.usedName);
		entity.setUsedGroup(this.usedGroup);
		entity.setMerModel(this.merModel);
//		entity.setOwnerMerId(this.ownerMerId);
		entity.setTel(this.tel);
		entity.setEmail(this.email);
		entity.setMarkNum(this.markNum);
		entity.setPropertyDetail(this.propertyDetail);
		entity.setSerialNumberParent(this.serialNumberParent);
		entity.setSplitMer(this.splitMer);
		entity.setQuality(this.quality);
		entity.setIsOriginal(this.isOriginal);
		entity.setNodeId(this.nodeId);
		entity.setCatEmployeeId(this.catEmployeeId);
//		entity.setOldMerContractId(this.oldMerContractId);
		entity.setTypeOutSide(this.typeOutSide);
		entity.setCreatedDate(this.createdDate);
		entity.setUnitPriceDomestic(this.unitPriceDomestic);
		entity.setUnitPriceForeign(this.unitPriceForeign);
		entity.setIsLock(this.isLock);
		entity.setIsAppo(this.isAppo);
		entity.setIsProcessCompare(this.isProcessCompare);
		entity.setImportDate(this.importDate);
		entity.setRealSerialNumber(this.realSerialNumber);
		entity.setIsvalid(this.isvalid);
		entity.setExecuteDateKdhc(this.executeDateKdhc);
		entity.setExecuteDateBdsc(this.executeDateBdsc);
		entity.setTypeStationMaintain(this.typeStationMaintain);
		entity.setExecuteMonthTimeUsed(this.executeMonthTimeUsed);
		entity.setAssetPrice(this.assetPrice);
		entity.setIsCheckImp(this.isCheckImp);
		entity.setAssetPlanUseId(this.assetPlanUseId);
		entity.setIsExpired(this.isExpired);
		entity.setObjLevel(this.objLevel);
		entity.setLongTermAssetId(this.longTermAssetId);
		return entity;
	}
	
	public void updateEntity(MerEntity entity) {
			entity.setSerialNumber(this.serialNumber);
			entity.setUnitPrice(this.unitPrice);
			entity.setCompanyId(this.companyId);
			entity.setNationalId(this.nationalId);
			entity.setMadeYear(this.madeYear);
			entity.setMaxUsedTime(this.maxUsedTime);
			entity.setMerId(this.merId);
			entity.setContractId(this.contractId);
			entity.setGroupId(this.groupId);
			entity.setWarehouseId(this.warehouseId);
			entity.setConstructionId(this.constructionId);
			entity.setIsMerchandise(this.isMerchandise);
			entity.setWhPosId(this.whPosId);
			entity.setStatusId(this.statusId);
			entity.setParentId(this.parentId);
			entity.setIsContractAssigned(this.isContractAssigned);
			entity.setChangeAction(this.changeAction);
			entity.setUserId(this.userId);
			entity.setAddedPart(this.addedPart);
			entity.setUpgradeParentId(this.upgradeParentId);
			entity.setPartNumber(this.partNumber);
			entity.setCount(this.count);
			entity.setAmortStartDate(this.amortStartDate);
			entity.setIsTemp(this.isTemp);
			entity.setMerWeight(this.merWeight);
			entity.setSerialNumberOwner(this.serialNumberOwner);
			entity.setPartNumberOwner(this.partNumberOwner);
			entity.setPath(this.path);
			entity.setWorkItemId(this.workItemId);
			entity.setOriginalPrice(this.originalPrice);
			entity.setExpiredWarrantyDate(this.expiredWarrantyDate);
			entity.setWarrantPartnerId(this.warrantPartnerId);
			entity.setProjectId(this.projectId);
			entity.setProposalId(this.proposalId);
			entity.setNextMaintainDate(this.nextMaintainDate);
			entity.setPackageId(this.packageId);
			entity.setSourceId(this.sourceId);
			entity.setManagerName(this.managerName);
			entity.setIsExpense(this.isExpense);
			entity.setUseGroupId(this.useGroupId);
			entity.setMerRootId(this.merRootId);
			entity.setTaxRate(this.taxRate);
			entity.setVndUnitPrice(this.vndUnitPrice);
			entity.setParentConfigId(this.parentConfigId);
			entity.setFileId(this.fileId);
			entity.setPlIndId(this.plIndId);
			entity.setShipmentNo(this.shipmentNo);
			entity.setPlCode(this.plCode);
			entity.setBoxNo(this.boxNo);
			entity.setKeySearch(this.keySearch);
			entity.setContractCode(this.contractCode);
			entity.setIsCheckedKcs(this.isCheckedKcs);
			entity.setOldMerEntityId(this.oldMerEntityId);
			entity.setIsMapped(this.isMapped);
			entity.setDescription(this.description);
			entity.setPlImportId(this.plImportId);
			entity.setStationId(this.stationId);
			entity.setIsInventory(this.isInventory);
			entity.setUsedDate(this.usedDate);
			entity.setLatestExportDate(this.latestExportDate);
			entity.setLatestImportDate(this.latestImportDate);
			entity.setMapNo(this.mapNo);
			entity.setAccountType(this.accountType);
			entity.setDepreciationDate(this.depreciationDate);
			entity.setLatestMaintainDate(this.latestMaintainDate);
			entity.setLatestMaintainDateBk(this.latestMaintainDateBk);
			entity.setCountMaintain(this.countMaintain);
			entity.setNextMaintainDateBk(this.nextMaintainDateBk);
			entity.setTwinMerEntityId(this.twinMerEntityId);
			entity.setOldMerId(this.oldMerId);
			entity.setOldContractId(this.oldContractId);
			entity.setTypeDefineHis(this.typeDefineHis);
			entity.setEntityType(this.entityType);
//			entity.setUsedName(this.usedName);
			entity.setUsedGroup(this.usedGroup);
			entity.setMerModel(this.merModel);
//			entity.setOwnerMerId(this.ownerMerId);
			entity.setTel(this.tel);
			entity.setEmail(this.email);
			entity.setMarkNum(this.markNum);
			entity.setPropertyDetail(this.propertyDetail);
			entity.setSerialNumberParent(this.serialNumberParent);
			entity.setSplitMer(this.splitMer);
			entity.setQuality(this.quality);
			entity.setIsOriginal(this.isOriginal);
			entity.setNodeId(this.nodeId);
			entity.setCatEmployeeId(this.catEmployeeId);
//			entity.setOldMerContractId(this.oldMerContractId);
			entity.setTypeOutSide(this.typeOutSide);
			entity.setCreatedDate(this.createdDate);
			entity.setUnitPriceDomestic(this.unitPriceDomestic);
			entity.setUnitPriceForeign(this.unitPriceForeign);
			entity.setIsLock(this.isLock);
			entity.setIsAppo(this.isAppo);
			entity.setIsProcessCompare(this.isProcessCompare);
			entity.setImportDate(this.importDate);
			entity.setRealSerialNumber(this.realSerialNumber);
			entity.setIsvalid(this.isvalid);
			entity.setExecuteDateKdhc(this.executeDateKdhc);
			entity.setExecuteDateBdsc(this.executeDateBdsc);
			entity.setTypeStationMaintain(this.typeStationMaintain);
			entity.setExecuteMonthTimeUsed(this.executeMonthTimeUsed);
			entity.setAssetPrice(this.assetPrice);
			entity.setIsCheckImp(this.isCheckImp);
			entity.setAssetPlanUseId(this.assetPlanUseId);
			entity.setIsExpired(this.isExpired);
			entity.setObjLevel(this.objLevel);
			entity.setLongTermAssetId(this.longTermAssetId);
	}
	
		public Long getMerEntityId() {
			return merEntityId;
		}
		public void setMerEntityId(Long merEntityId) {
			this.merEntityId = merEntityId;
		}		
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}		
		public Long getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(Long unitPrice) {
			this.unitPrice = unitPrice;
		}		
		public Long getCompanyId() {
			return companyId;
		}
		public void setCompanyId(Long companyId) {
			this.companyId = companyId;
		}		
		public Long getNationalId() {
			return nationalId;
		}
		public void setNationalId(Long nationalId) {
			this.nationalId = nationalId;
		}		
		public Long getMadeYear() {
			return madeYear;
		}
		public void setMadeYear(Long madeYear) {
			this.madeYear = madeYear;
		}		
		public Double getMaxUsedTime() {
			return maxUsedTime;
		}
		public void setMaxUsedTime(Double maxUsedTime) {
			this.maxUsedTime = maxUsedTime;
		}		
		public Long getMerId() {
			return merId;
		}
		public void setMerId(Long merId) {
			this.merId = merId;
		}		
		public Long getContractId() {
			return contractId;
		}
		public void setContractId(Long contractId) {
			this.contractId = contractId;
		}		
		public Long getGroupId() {
			return groupId;
		}
		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}		
		public Long getWarehouseId() {
			return warehouseId;
		}
		public void setWarehouseId(Long warehouseId) {
			this.warehouseId = warehouseId;
		}		
		public Long getConstructionId() {
			return constructionId;
		}
		public void setConstructionId(Long constructionId) {
			this.constructionId = constructionId;
		}		
		public Long getIsMerchandise() {
			return isMerchandise;
		}
		public void setIsMerchandise(Long isMerchandise) {
			this.isMerchandise = isMerchandise;
		}		
		public Long getWhPosId() {
			return whPosId;
		}
		public void setWhPosId(Long whPosId) {
			this.whPosId = whPosId;
		}		
		public Long getStatusId() {
			return statusId;
		}
		public void setStatusId(Long statusId) {
			this.statusId = statusId;
		}		
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public Long getIsContractAssigned() {
			return isContractAssigned;
		}
		public void setIsContractAssigned(Long isContractAssigned) {
			this.isContractAssigned = isContractAssigned;
		}		
		public Long getChangeAction() {
			return changeAction;
		}
		public void setChangeAction(Long changeAction) {
			this.changeAction = changeAction;
		}		
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}		
		public String getAddedPart() {
			return addedPart;
		}
		public void setAddedPart(String addedPart) {
			this.addedPart = addedPart;
		}		
		public Long getUpgradeParentId() {
			return upgradeParentId;
		}
		public void setUpgradeParentId(Long upgradeParentId) {
			this.upgradeParentId = upgradeParentId;
		}		
		public String getPartNumber() {
			return partNumber;
		}
		public void setPartNumber(String partNumber) {
			this.partNumber = partNumber;
		}		
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}		
		public Date getAmortStartDate() {
			return amortStartDate;
		}
		public void setAmortStartDate(Date amortStartDate) {
			this.amortStartDate = amortStartDate;
		}		
		public Long getIsTemp() {
			return isTemp;
		}
		public void setIsTemp(Long isTemp) {
			this.isTemp = isTemp;
		}		
		public Double getMerWeight() {
			return merWeight;
		}
		public void setMerWeight(Double merWeight) {
			this.merWeight = merWeight;
		}		
		public String getSerialNumberOwner() {
			return serialNumberOwner;
		}
		public void setSerialNumberOwner(String serialNumberOwner) {
			this.serialNumberOwner = serialNumberOwner;
		}		
		public String getPartNumberOwner() {
			return partNumberOwner;
		}
		public void setPartNumberOwner(String partNumberOwner) {
			this.partNumberOwner = partNumberOwner;
		}		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}		
		public Long getWorkItemId() {
			return workItemId;
		}
		public void setWorkItemId(Long workItemId) {
			this.workItemId = workItemId;
		}		
		public Long getOriginalPrice() {
			return originalPrice;
		}
		public void setOriginalPrice(Long originalPrice) {
			this.originalPrice = originalPrice;
		}		
		public Date getExpiredWarrantyDate() {
			return expiredWarrantyDate;
		}
		public void setExpiredWarrantyDate(Date expiredWarrantyDate) {
			this.expiredWarrantyDate = expiredWarrantyDate;
		}		
		public Long getWarrantPartnerId() {
			return warrantPartnerId;
		}
		public void setWarrantPartnerId(Long warrantPartnerId) {
			this.warrantPartnerId = warrantPartnerId;
		}		
		public Long getProjectId() {
			return projectId;
		}
		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}		
		public Long getProposalId() {
			return proposalId;
		}
		public void setProposalId(Long proposalId) {
			this.proposalId = proposalId;
		}		
		public Date getNextMaintainDate() {
			return nextMaintainDate;
		}
		public void setNextMaintainDate(Date nextMaintainDate) {
			this.nextMaintainDate = nextMaintainDate;
		}		
		public Long getPackageId() {
			return packageId;
		}
		public void setPackageId(Long packageId) {
			this.packageId = packageId;
		}		
		public Long getSourceId() {
			return sourceId;
		}
		public void setSourceId(Long sourceId) {
			this.sourceId = sourceId;
		}		
		public String getManagerName() {
			return managerName;
		}
		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}		
		public Long getIsExpense() {
			return isExpense;
		}
		public void setIsExpense(Long isExpense) {
			this.isExpense = isExpense;
		}		
		public Long getUseGroupId() {
			return useGroupId;
		}
		public void setUseGroupId(Long useGroupId) {
			this.useGroupId = useGroupId;
		}		
		public Long getMerRootId() {
			return merRootId;
		}
		public void setMerRootId(Long merRootId) {
			this.merRootId = merRootId;
		}		
		public Long getTaxRate() {
			return taxRate;
		}
		public void setTaxRate(Long taxRate) {
			this.taxRate = taxRate;
		}		
		public Long getVndUnitPrice() {
			return vndUnitPrice;
		}
		public void setVndUnitPrice(Long vndUnitPrice) {
			this.vndUnitPrice = vndUnitPrice;
		}		
		public Long getParentConfigId() {
			return parentConfigId;
		}
		public void setParentConfigId(Long parentConfigId) {
			this.parentConfigId = parentConfigId;
		}		
		public Long getFileId() {
			return fileId;
		}
		public void setFileId(Long fileId) {
			this.fileId = fileId;
		}		
		public Long getPlIndId() {
			return plIndId;
		}
		public void setPlIndId(Long plIndId) {
			this.plIndId = plIndId;
		}		
		public String getShipmentNo() {
			return shipmentNo;
		}
		public void setShipmentNo(String shipmentNo) {
			this.shipmentNo = shipmentNo;
		}		
		public String getPlCode() {
			return plCode;
		}
		public void setPlCode(String plCode) {
			this.plCode = plCode;
		}		
		public String getBoxNo() {
			return boxNo;
		}
		public void setBoxNo(String boxNo) {
			this.boxNo = boxNo;
		}		
		public Long getKeySearch() {
			return keySearch;
		}
		public void setKeySearch(Long keySearch) {
			this.keySearch = keySearch;
		}		
		public String getContractCode() {
			return contractCode;
		}
		public void setContractCode(String contractCode) {
			this.contractCode = contractCode;
		}		
		public Long getIsCheckedKcs() {
			return isCheckedKcs;
		}
		public void setIsCheckedKcs(Long isCheckedKcs) {
			this.isCheckedKcs = isCheckedKcs;
		}		
		public Long getOldMerEntityId() {
			return oldMerEntityId;
		}
		public void setOldMerEntityId(Long oldMerEntityId) {
			this.oldMerEntityId = oldMerEntityId;
		}		
		public Long getIsMapped() {
			return isMapped;
		}
		public void setIsMapped(Long isMapped) {
			this.isMapped = isMapped;
		}		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
		public Long getPlImportId() {
			return plImportId;
		}
		public void setPlImportId(Long plImportId) {
			this.plImportId = plImportId;
		}		
		public Long getStationId() {
			return stationId;
		}
		public void setStationId(Long stationId) {
			this.stationId = stationId;
		}		
		public Long getIsInventory() {
			return isInventory;
		}
		public void setIsInventory(Long isInventory) {
			this.isInventory = isInventory;
		}		
		public Date getUsedDate() {
			return usedDate;
		}
		public void setUsedDate(Date usedDate) {
			this.usedDate = usedDate;
		}		
		public Date getLatestExportDate() {
			return latestExportDate;
		}
		public void setLatestExportDate(Date latestExportDate) {
			this.latestExportDate = latestExportDate;
		}		
		public Date getLatestImportDate() {
			return latestImportDate;
		}
		public void setLatestImportDate(Date latestImportDate) {
			this.latestImportDate = latestImportDate;
		}		
		public Long getMapNo() {
			return mapNo;
		}
		public void setMapNo(Long mapNo) {
			this.mapNo = mapNo;
		}		
		public Long getAccountType() {
			return accountType;
		}
		public void setAccountType(Long accountType) {
			this.accountType = accountType;
		}		
		public Date getDepreciationDate() {
			return depreciationDate;
		}
		public void setDepreciationDate(Date depreciationDate) {
			this.depreciationDate = depreciationDate;
		}		
		public Date getLatestMaintainDate() {
			return latestMaintainDate;
		}
		public void setLatestMaintainDate(Date latestMaintainDate) {
			this.latestMaintainDate = latestMaintainDate;
		}		
		public Date getLatestMaintainDateBk() {
			return latestMaintainDateBk;
		}
		public void setLatestMaintainDateBk(Date latestMaintainDateBk) {
			this.latestMaintainDateBk = latestMaintainDateBk;
		}		
		public Long getCountMaintain() {
			return countMaintain;
		}
		public void setCountMaintain(Long countMaintain) {
			this.countMaintain = countMaintain;
		}		
		public Date getNextMaintainDateBk() {
			return nextMaintainDateBk;
		}
		public void setNextMaintainDateBk(Date nextMaintainDateBk) {
			this.nextMaintainDateBk = nextMaintainDateBk;
		}		
		public Long getTwinMerEntityId() {
			return twinMerEntityId;
		}
		public void setTwinMerEntityId(Long twinMerEntityId) {
			this.twinMerEntityId = twinMerEntityId;
		}		
		public Long getOldMerId() {
			return oldMerId;
		}
		public void setOldMerId(Long oldMerId) {
			this.oldMerId = oldMerId;
		}		
		public Long getOldContractId() {
			return oldContractId;
		}
		public void setOldContractId(Long oldContractId) {
			this.oldContractId = oldContractId;
		}		
		public Long getTypeDefineHis() {
			return typeDefineHis;
		}
		public void setTypeDefineHis(Long typeDefineHis) {
			this.typeDefineHis = typeDefineHis;
		}		
		public Long getEntityType() {
			return entityType;
		}
		public void setEntityType(Long entityType) {
			this.entityType = entityType;
		}		
		public String getUsedName() {
			return usedName;
		}
		public void setUsedName(String usedName) {
			this.usedName = usedName;
		}		
		public String getUsedGroup() {
			return usedGroup;
		}
		public void setUsedGroup(String usedGroup) {
			this.usedGroup = usedGroup;
		}		
		public String getMerModel() {
			return merModel;
		}
		public void setMerModel(String merModel) {
			this.merModel = merModel;
		}		
		public Long getOwnerMerId() {
			return ownerMerId;
		}
		public void setOwnerMerId(Long ownerMerId) {
			this.ownerMerId = ownerMerId;
		}		
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}		
		public Long getMarkNum() {
			return markNum;
		}
		public void setMarkNum(Long markNum) {
			this.markNum = markNum;
		}		
		public String getPropertyDetail() {
			return propertyDetail;
		}
		public void setPropertyDetail(String propertyDetail) {
			this.propertyDetail = propertyDetail;
		}		
		public String getSerialNumberParent() {
			return serialNumberParent;
		}
		public void setSerialNumberParent(String serialNumberParent) {
			this.serialNumberParent = serialNumberParent;
		}		
		public Long getSplitMer() {
			return splitMer;
		}
		public void setSplitMer(Long splitMer) {
			this.splitMer = splitMer;
		}		
		public Long getQuality() {
			return quality;
		}
		public void setQuality(Long quality) {
			this.quality = quality;
		}		
		public Long getIsOriginal() {
			return isOriginal;
		}
		public void setIsOriginal(Long isOriginal) {
			this.isOriginal = isOriginal;
		}		
		public Long getNodeId() {
			return nodeId;
		}
		public void setNodeId(Long nodeId) {
			this.nodeId = nodeId;
		}		
		public Long getCatEmployeeId() {
			return catEmployeeId;
		}
		public void setCatEmployeeId(Long catEmployeeId) {
			this.catEmployeeId = catEmployeeId;
		}		
		public Long getOldMerContractId() {
			return oldMerContractId;
		}
		public void setOldMerContractId(Long oldMerContractId) {
			this.oldMerContractId = oldMerContractId;
		}		
		public Long getTypeOutSide() {
			return typeOutSide;
		}
		public void setTypeOutSide(Long typeOutSide) {
			this.typeOutSide = typeOutSide;
		}		
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}		
		public Double getUnitPriceDomestic() {
			return unitPriceDomestic;
		}
		public void setUnitPriceDomestic(Double unitPriceDomestic) {
			this.unitPriceDomestic = unitPriceDomestic;
		}		
		public Double getUnitPriceForeign() {
			return unitPriceForeign;
		}
		public void setUnitPriceForeign(Double unitPriceForeign) {
			this.unitPriceForeign = unitPriceForeign;
		}		
		public Long getIsLock() {
			return isLock;
		}
		public void setIsLock(Long isLock) {
			this.isLock = isLock;
		}		
		public Long getIsAppo() {
			return isAppo;
		}
		public void setIsAppo(Long isAppo) {
			this.isAppo = isAppo;
		}		
		public Long getIsProcessCompare() {
			return isProcessCompare;
		}
		public void setIsProcessCompare(Long isProcessCompare) {
			this.isProcessCompare = isProcessCompare;
		}		
		public Date getImportDate() {
			return importDate;
		}
		public void setImportDate(Date importDate) {
			this.importDate = importDate;
		}		
		public String getRealSerialNumber() {
			return realSerialNumber;
		}
		public void setRealSerialNumber(String realSerialNumber) {
			this.realSerialNumber = realSerialNumber;
		}		
		public Long getIsvalid() {
			return isvalid;
		}
		public void setIsvalid(Long isvalid) {
			this.isvalid = isvalid;
		}		
		public Date getExecuteDateKdhc() {
			return executeDateKdhc;
		}
		public void setExecuteDateKdhc(Date executeDateKdhc) {
			this.executeDateKdhc = executeDateKdhc;
		}		
		public Date getExecuteDateBdsc() {
			return executeDateBdsc;
		}
		public void setExecuteDateBdsc(Date executeDateBdsc) {
			this.executeDateBdsc = executeDateBdsc;
		}		
		public Long getTypeStationMaintain() {
			return typeStationMaintain;
		}
		public void setTypeStationMaintain(Long typeStationMaintain) {
			this.typeStationMaintain = typeStationMaintain;
		}		
		public Long getExecuteMonthTimeUsed() {
			return executeMonthTimeUsed;
		}
		public void setExecuteMonthTimeUsed(Long executeMonthTimeUsed) {
			this.executeMonthTimeUsed = executeMonthTimeUsed;
		}		
		public Long getAssetPrice() {
			return assetPrice;
		}
		public void setAssetPrice(Long assetPrice) {
			this.assetPrice = assetPrice;
		}		
		public Long getIsCheckImp() {
			return isCheckImp;
		}
		public void setIsCheckImp(Long isCheckImp) {
			this.isCheckImp = isCheckImp;
		}		
		public Long getAssetPlanUseId() {
			return assetPlanUseId;
		}
		public void setAssetPlanUseId(Long assetPlanUseId) {
			this.assetPlanUseId = assetPlanUseId;
		}		
		public Long getIsExpired() {
			return isExpired;
		}
		public void setIsExpired(Long isExpired) {
			this.isExpired = isExpired;
		}		
		public Long getObjLevel() {
			return objLevel;
		}
		public void setObjLevel(Long objLevel) {
			this.objLevel = objLevel;
		}		
		public Long getLongTermAssetId() {
			return longTermAssetId;
		}
		public void setLongTermAssetId(Long longTermAssetId) {
			this.longTermAssetId = longTermAssetId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getUnitId() {
			return unitId;
		}

		public void setUnitId(Long unitId) {
			this.unitId = unitId;
		}
	
}
