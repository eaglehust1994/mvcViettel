/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.MerEntityDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "merentity")
@Table(name = "MER_ENTITY")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class MerEntityBO extends BaseFWModelImpl {

	private CatMerchandiseBO catmerchandise;
	private List<ConstrMerchandiseBO> constrmerchandise = Lists.newArrayList();
	private List<AMaterialHandoverMerListBO> amaterialhandovermerlist = Lists.newArrayList();

	private java.lang.Long merEntityId;
	private java.lang.String serialNumber;
	private java.lang.Long unitPrice;
	private java.lang.Long companyId;
	private java.lang.Long nationalId;
	private java.lang.Long madeYear;
	private java.lang.Double maxUsedTime;
	private java.lang.Long merId;
	private java.lang.Long contractId;
	private java.lang.Long groupId;
	private java.lang.Long warehouseId;
	private java.lang.Long constructionId;
	private java.lang.Long isMerchandise;
	private java.lang.Long whPosId;
	private java.lang.Long statusId;
	private java.lang.Long parentId;
	private java.lang.Long isContractAssigned;
	private java.lang.Long changeAction;
	private java.lang.Long userId;
	private java.lang.String addedPart;
	private java.lang.Long upgradeParentId;
	private java.lang.String partNumber;
	private java.lang.Long count;
	private java.util.Date amortStartDate;
	private java.lang.Long isTemp;
	private java.lang.Double merWeight;
	private java.lang.String serialNumberOwner;
	private java.lang.String partNumberOwner;
	private java.lang.String path;
	private java.lang.Long workItemId;
	private java.lang.Long originalPrice;
	private java.util.Date expiredWarrantyDate;
	private java.lang.Long warrantPartnerId;
	private java.lang.Long projectId;
	private java.lang.Long proposalId;
	private java.util.Date nextMaintainDate;
	private java.lang.Long packageId;
	private java.lang.Long sourceId;
	private java.lang.String managerName;
	private java.lang.Long isExpense;
	private java.lang.Long useGroupId;
	private java.lang.Long merRootId;
	private java.lang.Long taxRate;
	private java.lang.Long vndUnitPrice;
	private java.lang.Long parentConfigId;
	private java.lang.Long fileId;
	private java.lang.Long plIndId;
	private java.lang.String shipmentNo;
	private java.lang.String plCode;
	private java.lang.String boxNo;
	private java.lang.Long keySearch;
	private java.lang.String contractCode;
	private java.lang.Long isCheckedKcs;
	private java.lang.Long oldMerEntityId;
	private java.lang.Long isMapped;
	private java.lang.String description;
	private java.lang.Long plImportId;
	private java.lang.Long stationId;
	private java.lang.Long isInventory;
	private java.util.Date usedDate;
	private java.util.Date latestExportDate;
	private java.util.Date latestImportDate;
	private java.lang.Long mapNo;
	private java.lang.Long accountType;
	private java.util.Date depreciationDate;
	private java.util.Date latestMaintainDate;
	private java.util.Date latestMaintainDateBk;
	private java.lang.Long countMaintain;
	private java.util.Date nextMaintainDateBk;
	private java.lang.Long twinMerEntityId;
	private java.lang.Long oldMerId;
	private java.lang.Long oldContractId;
	private java.lang.Long typeDefineHis;
	private java.lang.Long entityType;
	private java.lang.String usedName;
	private java.lang.String usedGroup;
	private java.lang.String merModel;
	private java.lang.Long ownerMerId;
	private java.lang.String tel;
	private java.lang.String email;
	private java.lang.Long markNum;
	private java.lang.String propertyDetail;
	private java.lang.String serialNumberParent;
	private java.lang.Long splitMer;
	private java.lang.Long quality;
	private java.lang.Long isOriginal;
	private java.lang.Long nodeId;
	private java.lang.Long catEmployeeId;
	private java.lang.Long oldMerContractId;
	private java.lang.Long typeOutSide;
	private java.util.Date createdDate;
	private java.lang.Double unitPriceDomestic;
	private java.lang.Double unitPriceForeign;
	private java.lang.Long isLock;
	private java.lang.Long isAppo;
	private java.lang.Long isProcessCompare;
	private java.util.Date importDate;
	private java.lang.String realSerialNumber;
	private java.lang.Long isvalid;
	private java.util.Date executeDateKdhc;
	private java.util.Date executeDateBdsc;
	private java.lang.Long typeStationMaintain;
	private java.lang.Long executeMonthTimeUsed;
	private java.lang.Long assetPrice;
	private java.lang.Long isCheckImp;
	private java.lang.Long assetPlanUseId;
	private java.lang.Long isExpired;
	private java.lang.Long objLevel;

	public MerEntityBO() {
		setColId("merEntityId");
		setColName("merEntityId");
		setUniqueColumn(new String[] { "merEntityId" });
	}

	public MerEntityBO(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "MER_ENTITY_SEQ") })
	@Column(name = "MER_ENTITY_ID", length = 22)
	public java.lang.Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(java.lang.Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	@Column(name = "SERIAL_NUMBER", length = 1000)
	public java.lang.String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "UNIT_PRICE", length = 22)
	public java.lang.Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(java.lang.Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "COMPANY_ID", length = 22)
	public java.lang.Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(java.lang.Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "NATIONAL_ID", length = 22)
	public java.lang.Long getNationalId() {
		return nationalId;
	}

	public void setNationalId(java.lang.Long nationalId) {
		this.nationalId = nationalId;
	}

	@Column(name = "MADE_YEAR", length = 22)
	public java.lang.Long getMadeYear() {
		return madeYear;
	}

	public void setMadeYear(java.lang.Long madeYear) {
		this.madeYear = madeYear;
	}

	@Column(name = "MAX_USED_TIME", length = 22)
	public java.lang.Double getMaxUsedTime() {
		return maxUsedTime;
	}

	public void setMaxUsedTime(java.lang.Double maxUsedTime) {
		this.maxUsedTime = maxUsedTime;
	}
	/*
	 * @Column(name = "MER_ID", length = 22) public java.lang.Long getMerId(){
	 * return merId; } public void setMerId(java.lang.Long merId) { this.merId =
	 * merId; }
	 */

	@ManyToOne
	@JoinColumn(name = "MER_ID", referencedColumnName = "MERCHANDISE_ID")
	public CatMerchandiseBO getCatmerchandise() {
		return catmerchandise;
	}

	public void setCatmerchandise(CatMerchandiseBO catmerchandise) {
		this.catmerchandise = catmerchandise;
	}

	/*
	 * @OneToMany(mappedBy = "merentity") public List<ConstrMerchandiseBO>
	 * getConstrmerchandise(){ return constrmerchandise; } public void
	 * setConstrmerchandise(List<ConstrMerchandiseBO> constrmerchandise){
	 * this.constrmerchandise = constrmerchandise; }
	 */
	@OneToMany(mappedBy = "merentity")
	public List<AMaterialHandoverMerListBO> getAmaterialhandovermerlist() {
		return amaterialhandovermerlist;
	}

	public void setAmaterialhandovermerlist(List<AMaterialHandoverMerListBO> amaterialhandovermerlist) {
		this.amaterialhandovermerlist = amaterialhandovermerlist;
	}

	@Column(name = "CONTRACT_ID", length = 22)
	public java.lang.Long getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "GROUP_ID", length = 22)
	public java.lang.Long getGroupId() {
		return groupId;
	}

	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "WAREHOUSE_ID", length = 22)
	public java.lang.Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(java.lang.Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Column(name = "CONSTRUCTION_ID", length = 22)
	public java.lang.Long getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(java.lang.Long constructionId) {
		this.constructionId = constructionId;
	}

	@Column(name = "IS_MERCHANDISE", length = 22)
	public java.lang.Long getIsMerchandise() {
		return isMerchandise;
	}

	public void setIsMerchandise(java.lang.Long isMerchandise) {
		this.isMerchandise = isMerchandise;
	}

	@Column(name = "WH_POS_ID", length = 22)
	public java.lang.Long getWhPosId() {
		return whPosId;
	}

	public void setWhPosId(java.lang.Long whPosId) {
		this.whPosId = whPosId;
	}

	@Column(name = "STATUS_ID", length = 22)
	public java.lang.Long getStatusId() {
		return statusId;
	}

	public void setStatusId(java.lang.Long statusId) {
		this.statusId = statusId;
	}

	@Column(name = "PARENT_ID", length = 22)
	public java.lang.Long getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "IS_CONTRACT_ASSIGNED", length = 22)
	public java.lang.Long getIsContractAssigned() {
		return isContractAssigned;
	}

	public void setIsContractAssigned(java.lang.Long isContractAssigned) {
		this.isContractAssigned = isContractAssigned;
	}

	@Column(name = "CHANGE_ACTION", length = 22)
	public java.lang.Long getChangeAction() {
		return changeAction;
	}

	public void setChangeAction(java.lang.Long changeAction) {
		this.changeAction = changeAction;
	}

	@Column(name = "USER_ID", length = 22)
	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	@Column(name = "ADDED_PART", length = 1000)
	public java.lang.String getAddedPart() {
		return addedPart;
	}

	public void setAddedPart(java.lang.String addedPart) {
		this.addedPart = addedPart;
	}

	@Column(name = "UPGRADE_PARENT_ID", length = 22)
	public java.lang.Long getUpgradeParentId() {
		return upgradeParentId;
	}

	public void setUpgradeParentId(java.lang.Long upgradeParentId) {
		this.upgradeParentId = upgradeParentId;
	}

	@Column(name = "PART_NUMBER", length = 130)
	public java.lang.String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(java.lang.String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "COUNT", length = 22)
	public java.lang.Long getCount() {
		return count;
	}

	public void setCount(java.lang.Long count) {
		this.count = count;
	}

	@Column(name = "AMORT_START_DATE", length = 7)
	public java.util.Date getAmortStartDate() {
		return amortStartDate;
	}

	public void setAmortStartDate(java.util.Date amortStartDate) {
		this.amortStartDate = amortStartDate;
	}

	@Column(name = "IS_TEMP", length = 22)
	public java.lang.Long getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(java.lang.Long isTemp) {
		this.isTemp = isTemp;
	}

	@Column(name = "MER_WEIGHT", length = 22)
	public java.lang.Double getMerWeight() {
		return merWeight;
	}

	public void setMerWeight(java.lang.Double merWeight) {
		this.merWeight = merWeight;
	}

	@Column(name = "SERIAL_NUMBER_OWNER", length = 100)
	public java.lang.String getSerialNumberOwner() {
		return serialNumberOwner;
	}

	public void setSerialNumberOwner(java.lang.String serialNumberOwner) {
		this.serialNumberOwner = serialNumberOwner;
	}

	@Column(name = "PART_NUMBER_OWNER", length = 100)
	public java.lang.String getPartNumberOwner() {
		return partNumberOwner;
	}

	public void setPartNumberOwner(java.lang.String partNumberOwner) {
		this.partNumberOwner = partNumberOwner;
	}

	@Column(name = "PATH", length = 2000)
	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}

	@Column(name = "WORK_ITEM_ID", length = 22)
	public java.lang.Long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(java.lang.Long workItemId) {
		this.workItemId = workItemId;
	}

	@Column(name = "ORIGINAL_PRICE", length = 22)
	public java.lang.Long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(java.lang.Long originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name = "EXPIRED_WARRANTY_DATE", length = 7)
	public java.util.Date getExpiredWarrantyDate() {
		return expiredWarrantyDate;
	}

	public void setExpiredWarrantyDate(java.util.Date expiredWarrantyDate) {
		this.expiredWarrantyDate = expiredWarrantyDate;
	}

	@Column(name = "WARRANT_PARTNER_ID", length = 22)
	public java.lang.Long getWarrantPartnerId() {
		return warrantPartnerId;
	}

	public void setWarrantPartnerId(java.lang.Long warrantPartnerId) {
		this.warrantPartnerId = warrantPartnerId;
	}

	@Column(name = "PROJECT_ID", length = 22)
	public java.lang.Long getProjectId() {
		return projectId;
	}

	public void setProjectId(java.lang.Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROPOSAL_ID", length = 22)
	public java.lang.Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(java.lang.Long proposalId) {
		this.proposalId = proposalId;
	}

	@Column(name = "NEXT_MAINTAIN_DATE", length = 7)
	public java.util.Date getNextMaintainDate() {
		return nextMaintainDate;
	}

	public void setNextMaintainDate(java.util.Date nextMaintainDate) {
		this.nextMaintainDate = nextMaintainDate;
	}

	@Column(name = "PACKAGE_ID", length = 22)
	public java.lang.Long getPackageId() {
		return packageId;
	}

	public void setPackageId(java.lang.Long packageId) {
		this.packageId = packageId;
	}

	@Column(name = "SOURCE_ID", length = 22)
	public java.lang.Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(java.lang.Long sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "MANAGER_NAME", length = 300)
	public java.lang.String getManagerName() {
		return managerName;
	}

	public void setManagerName(java.lang.String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "IS_EXPENSE", length = 22)
	public java.lang.Long getIsExpense() {
		return isExpense;
	}

	public void setIsExpense(java.lang.Long isExpense) {
		this.isExpense = isExpense;
	}

	@Column(name = "USE_GROUP_ID", length = 22)
	public java.lang.Long getUseGroupId() {
		return useGroupId;
	}

	public void setUseGroupId(java.lang.Long useGroupId) {
		this.useGroupId = useGroupId;
	}

	@Column(name = "MER_ROOT_ID", length = 22)
	public java.lang.Long getMerRootId() {
		return merRootId;
	}

	public void setMerRootId(java.lang.Long merRootId) {
		this.merRootId = merRootId;
	}

	@Column(name = "TAX_RATE", length = 22)
	public java.lang.Long getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(java.lang.Long taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "VND_UNIT_PRICE", length = 22)
	public java.lang.Long getVndUnitPrice() {
		return vndUnitPrice;
	}

	public void setVndUnitPrice(java.lang.Long vndUnitPrice) {
		this.vndUnitPrice = vndUnitPrice;
	}

	@Column(name = "PARENT_CONFIG_ID", length = 22)
	public java.lang.Long getParentConfigId() {
		return parentConfigId;
	}

	public void setParentConfigId(java.lang.Long parentConfigId) {
		this.parentConfigId = parentConfigId;
	}

	@Column(name = "FILE_ID", length = 22)
	public java.lang.Long getFileId() {
		return fileId;
	}

	public void setFileId(java.lang.Long fileId) {
		this.fileId = fileId;
	}

	@Column(name = "PL_IND_ID", length = 22)
	public java.lang.Long getPlIndId() {
		return plIndId;
	}

	public void setPlIndId(java.lang.Long plIndId) {
		this.plIndId = plIndId;
	}

	@Column(name = "SHIPMENT_NO", length = 200)
	public java.lang.String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(java.lang.String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	@Column(name = "PL_CODE", length = 100)
	public java.lang.String getPlCode() {
		return plCode;
	}

	public void setPlCode(java.lang.String plCode) {
		this.plCode = plCode;
	}

	@Column(name = "BOX_NO", length = 500)
	public java.lang.String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(java.lang.String boxNo) {
		this.boxNo = boxNo;
	}

	@Column(name = "KEY_SEARCH", length = 22)
	public java.lang.Long getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(java.lang.Long keySearch) {
		this.keySearch = keySearch;
	}

	@Column(name = "CONTRACT_CODE", length = 100)
	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	@Column(name = "IS_CHECKED_KCS", length = 22)
	public java.lang.Long getIsCheckedKcs() {
		return isCheckedKcs;
	}

	public void setIsCheckedKcs(java.lang.Long isCheckedKcs) {
		this.isCheckedKcs = isCheckedKcs;
	}

	@Column(name = "OLD_MER_ENTITY_ID", length = 22)
	public java.lang.Long getOldMerEntityId() {
		return oldMerEntityId;
	}

	public void setOldMerEntityId(java.lang.Long oldMerEntityId) {
		this.oldMerEntityId = oldMerEntityId;
	}

	@Column(name = "IS_MAPPED", length = 22)
	public java.lang.Long getIsMapped() {
		return isMapped;
	}

	public void setIsMapped(java.lang.Long isMapped) {
		this.isMapped = isMapped;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Column(name = "PL_IMPORT_ID", length = 22)
	public java.lang.Long getPlImportId() {
		return plImportId;
	}

	public void setPlImportId(java.lang.Long plImportId) {
		this.plImportId = plImportId;
	}

	@Column(name = "STATION_ID", length = 22)
	public java.lang.Long getStationId() {
		return stationId;
	}

	public void setStationId(java.lang.Long stationId) {
		this.stationId = stationId;
	}

	@Column(name = "IS_INVENTORY", length = 22)
	public java.lang.Long getIsInventory() {
		return isInventory;
	}

	public void setIsInventory(java.lang.Long isInventory) {
		this.isInventory = isInventory;
	}

	@Column(name = "USED_DATE", length = 7)
	public java.util.Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(java.util.Date usedDate) {
		this.usedDate = usedDate;
	}

	@Column(name = "LATEST_EXPORT_DATE", length = 7)
	public java.util.Date getLatestExportDate() {
		return latestExportDate;
	}

	public void setLatestExportDate(java.util.Date latestExportDate) {
		this.latestExportDate = latestExportDate;
	}

	@Column(name = "LATEST_IMPORT_DATE", length = 7)
	public java.util.Date getLatestImportDate() {
		return latestImportDate;
	}

	public void setLatestImportDate(java.util.Date latestImportDate) {
		this.latestImportDate = latestImportDate;
	}

	@Column(name = "MAP_NO", length = 22)
	public java.lang.Long getMapNo() {
		return mapNo;
	}

	public void setMapNo(java.lang.Long mapNo) {
		this.mapNo = mapNo;
	}

	@Column(name = "ACCOUNT_TYPE", length = 22)
	public java.lang.Long getAccountType() {
		return accountType;
	}

	public void setAccountType(java.lang.Long accountType) {
		this.accountType = accountType;
	}

	@Column(name = "DEPRECIATION_DATE", length = 7)
	public java.util.Date getDepreciationDate() {
		return depreciationDate;
	}

	public void setDepreciationDate(java.util.Date depreciationDate) {
		this.depreciationDate = depreciationDate;
	}

	@Column(name = "LATEST_MAINTAIN_DATE", length = 7)
	public java.util.Date getLatestMaintainDate() {
		return latestMaintainDate;
	}

	public void setLatestMaintainDate(java.util.Date latestMaintainDate) {
		this.latestMaintainDate = latestMaintainDate;
	}

	@Column(name = "LATEST_MAINTAIN_DATE_BK", length = 7)
	public java.util.Date getLatestMaintainDateBk() {
		return latestMaintainDateBk;
	}

	public void setLatestMaintainDateBk(java.util.Date latestMaintainDateBk) {
		this.latestMaintainDateBk = latestMaintainDateBk;
	}

	@Column(name = "COUNT_MAINTAIN", length = 22)
	public java.lang.Long getCountMaintain() {
		return countMaintain;
	}

	public void setCountMaintain(java.lang.Long countMaintain) {
		this.countMaintain = countMaintain;
	}

	@Column(name = "NEXT_MAINTAIN_DATE_BK", length = 7)
	public java.util.Date getNextMaintainDateBk() {
		return nextMaintainDateBk;
	}

	public void setNextMaintainDateBk(java.util.Date nextMaintainDateBk) {
		this.nextMaintainDateBk = nextMaintainDateBk;
	}

	@Column(name = "TWIN_MER_ENTITY_ID", length = 22)
	public java.lang.Long getTwinMerEntityId() {
		return twinMerEntityId;
	}

	public void setTwinMerEntityId(java.lang.Long twinMerEntityId) {
		this.twinMerEntityId = twinMerEntityId;
	}

	@Column(name = "OLD_MER_ID", length = 22)
	public java.lang.Long getOldMerId() {
		return oldMerId;
	}

	public void setOldMerId(java.lang.Long oldMerId) {
		this.oldMerId = oldMerId;
	}

	@Column(name = "OLD_CONTRACT_ID", length = 22)
	public java.lang.Long getOldContractId() {
		return oldContractId;
	}

	public void setOldContractId(java.lang.Long oldContractId) {
		this.oldContractId = oldContractId;
	}

	@Column(name = "TYPE_DEFINE_HIS", length = 22)
	public java.lang.Long getTypeDefineHis() {
		return typeDefineHis;
	}

	public void setTypeDefineHis(java.lang.Long typeDefineHis) {
		this.typeDefineHis = typeDefineHis;
	}

	@Column(name = "ENTITY_TYPE", length = 22)
	public java.lang.Long getEntityType() {
		return entityType;
	}

	public void setEntityType(java.lang.Long entityType) {
		this.entityType = entityType;
	}

	// @Column(name = "USED_NAME", length = 500)
	// public java.lang.String getUsedName(){
	// return usedName;
	// }
	// public void setUsedName(java.lang.String usedName)
	// {
	// this.usedName = usedName;
	// }
	@Column(name = "USED_GROUP", length = 500)
	public java.lang.String getUsedGroup() {
		return usedGroup;
	}

	public void setUsedGroup(java.lang.String usedGroup) {
		this.usedGroup = usedGroup;
	}

	@Column(name = "MER_MODEL", length = 100)
	public java.lang.String getMerModel() {
		return merModel;
	}

	public void setMerModel(java.lang.String merModel) {
		this.merModel = merModel;
	}

	// @Column(name = "OWNER_MER_ID", length = 22)
	// public java.lang.Long getOwnerMerId(){
	// return ownerMerId;
	// }
	// public void setOwnerMerId(java.lang.Long ownerMerId)
	// {
	// this.ownerMerId = ownerMerId;
	// }
	@Column(name = "TEL", length = 50)
	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL", length = 100)
	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	@Column(name = "MARK_NUM", length = 22)
	public java.lang.Long getMarkNum() {
		return markNum;
	}

	public void setMarkNum(java.lang.Long markNum) {
		this.markNum = markNum;
	}

	@Column(name = "PROPERTY_DETAIL", length = 4000)
	public java.lang.String getPropertyDetail() {
		return propertyDetail;
	}

	public void setPropertyDetail(java.lang.String propertyDetail) {
		this.propertyDetail = propertyDetail;
	}

	@Column(name = "SERIAL_NUMBER_PARENT", length = 400)
	public java.lang.String getSerialNumberParent() {
		return serialNumberParent;
	}

	public void setSerialNumberParent(java.lang.String serialNumberParent) {
		this.serialNumberParent = serialNumberParent;
	}

	@Column(name = "SPLIT_MER", length = 22)
	public java.lang.Long getSplitMer() {
		return splitMer;
	}

	public void setSplitMer(java.lang.Long splitMer) {
		this.splitMer = splitMer;
	}

	@Column(name = "QUALITY", length = 22)
	public java.lang.Long getQuality() {
		return quality;
	}

	public void setQuality(java.lang.Long quality) {
		this.quality = quality;
	}

	@Column(name = "IS_ORIGINAL", length = 22)
	public java.lang.Long getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(java.lang.Long isOriginal) {
		this.isOriginal = isOriginal;
	}

	@Column(name = "NODE_ID", length = 22)
	public java.lang.Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(java.lang.Long nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "CAT_EMPLOYEE_ID", length = 22)
	public java.lang.Long getCatEmployeeId() {
		return catEmployeeId;
	}

	public void setCatEmployeeId(java.lang.Long catEmployeeId) {
		this.catEmployeeId = catEmployeeId;
	}

	// @Column(name = "OLD_MER_CONTRACT_ID", length = 22)
	// public java.lang.Long getOldMerContractId(){
	// return oldMerContractId;
	// }
	// public void setOldMerContractId(java.lang.Long oldMerContractId)
	// {
	// this.oldMerContractId = oldMerContractId;
	// }
	@Column(name = "TYPE_OUT_SIDE", length = 22)
	public java.lang.Long getTypeOutSide() {
		return typeOutSide;
	}

	public void setTypeOutSide(java.lang.Long typeOutSide) {
		this.typeOutSide = typeOutSide;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "UNIT_PRICE_DOMESTIC", length = 22)
	public java.lang.Double getUnitPriceDomestic() {
		return unitPriceDomestic;
	}

	public void setUnitPriceDomestic(java.lang.Double unitPriceDomestic) {
		this.unitPriceDomestic = unitPriceDomestic;
	}

	@Column(name = "UNIT_PRICE_FOREIGN", length = 22)
	public java.lang.Double getUnitPriceForeign() {
		return unitPriceForeign;
	}

	public void setUnitPriceForeign(java.lang.Double unitPriceForeign) {
		this.unitPriceForeign = unitPriceForeign;
	}

	@Column(name = "IS_LOCK", length = 22)
	public java.lang.Long getIsLock() {
		return isLock;
	}

	public void setIsLock(java.lang.Long isLock) {
		this.isLock = isLock;
	}

	@Column(name = "IS_APPO", length = 22)
	public java.lang.Long getIsAppo() {
		return isAppo;
	}

	public void setIsAppo(java.lang.Long isAppo) {
		this.isAppo = isAppo;
	}

	@Column(name = "IS_PROCESS_COMPARE", length = 22)
	public java.lang.Long getIsProcessCompare() {
		return isProcessCompare;
	}

	public void setIsProcessCompare(java.lang.Long isProcessCompare) {
		this.isProcessCompare = isProcessCompare;
	}

	@Column(name = "IMPORT_DATE", length = 7)
	public java.util.Date getImportDate() {
		return importDate;
	}

	public void setImportDate(java.util.Date importDate) {
		this.importDate = importDate;
	}

	@Column(name = "REAL_SERIAL_NUMBER", length = 1000)
	public java.lang.String getRealSerialNumber() {
		return realSerialNumber;
	}

	public void setRealSerialNumber(java.lang.String realSerialNumber) {
		this.realSerialNumber = realSerialNumber;
	}

	@Column(name = "ISVALID", length = 22)
	public java.lang.Long getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(java.lang.Long isvalid) {
		this.isvalid = isvalid;
	}

	@Column(name = "EXECUTE_DATE_KDHC", length = 7)
	public java.util.Date getExecuteDateKdhc() {
		return executeDateKdhc;
	}

	public void setExecuteDateKdhc(java.util.Date executeDateKdhc) {
		this.executeDateKdhc = executeDateKdhc;
	}

	@Column(name = "EXECUTE_DATE_BDSC", length = 7)
	public java.util.Date getExecuteDateBdsc() {
		return executeDateBdsc;
	}

	public void setExecuteDateBdsc(java.util.Date executeDateBdsc) {
		this.executeDateBdsc = executeDateBdsc;
	}

	@Column(name = "TYPE_STATION_MAINTAIN", length = 22)
	public java.lang.Long getTypeStationMaintain() {
		return typeStationMaintain;
	}

	public void setTypeStationMaintain(java.lang.Long typeStationMaintain) {
		this.typeStationMaintain = typeStationMaintain;
	}

	@Column(name = "EXECUTE_MONTH_TIME_USED", length = 22)
	public java.lang.Long getExecuteMonthTimeUsed() {
		return executeMonthTimeUsed;
	}

	public void setExecuteMonthTimeUsed(java.lang.Long executeMonthTimeUsed) {
		this.executeMonthTimeUsed = executeMonthTimeUsed;
	}

	@Column(name = "ASSET_PRICE", length = 22)
	public java.lang.Long getAssetPrice() {
		return assetPrice;
	}

	public void setAssetPrice(java.lang.Long assetPrice) {
		this.assetPrice = assetPrice;
	}

	@Column(name = "IS_CHECK_IMP", length = 22)
	public java.lang.Long getIsCheckImp() {
		return isCheckImp;
	}

	public void setIsCheckImp(java.lang.Long isCheckImp) {
		this.isCheckImp = isCheckImp;
	}

	@Column(name = "ASSET_PLAN_USE_ID", length = 22)
	public java.lang.Long getAssetPlanUseId() {
		return assetPlanUseId;
	}

	public void setAssetPlanUseId(java.lang.Long assetPlanUseId) {
		this.assetPlanUseId = assetPlanUseId;
	}

	@Column(name = "IS_EXPIRED", length = 22)
	public java.lang.Long getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(java.lang.Long isExpired) {
		this.isExpired = isExpired;
	}

	@Column(name = "OBJ_LEVEL", length = 22)
	public java.lang.Long getObjLevel() {
		return objLevel;
	}

	public void setObjLevel(java.lang.Long objLevel) {
		this.objLevel = objLevel;
	}

	@Override
	public MerEntityDTO toDTO() {
		MerEntityDTO merEntityDTO = new MerEntityDTO();
		// set cac gia tri
		merEntityDTO.setMerEntityId(this.merEntityId);
		merEntityDTO.setSerialNumber(this.serialNumber);
		merEntityDTO.setUnitPrice(this.unitPrice);
		merEntityDTO.setCompanyId(this.companyId);
		merEntityDTO.setNationalId(this.nationalId);
		merEntityDTO.setMadeYear(this.madeYear);
		merEntityDTO.setMaxUsedTime(this.maxUsedTime);
		merEntityDTO.setMerId(this.catmerchandise == null ? null : this.catmerchandise.getMerchandiseId());
		merEntityDTO.setContractId(this.contractId);
		merEntityDTO.setGroupId(this.groupId);
		merEntityDTO.setWarehouseId(this.warehouseId);
		merEntityDTO.setConstructionId(this.constructionId);
		merEntityDTO.setIsMerchandise(this.isMerchandise);
		merEntityDTO.setWhPosId(this.whPosId);
		merEntityDTO.setStatusId(this.statusId);
		merEntityDTO.setParentId(this.parentId);
		merEntityDTO.setIsContractAssigned(this.isContractAssigned);
		merEntityDTO.setChangeAction(this.changeAction);
		merEntityDTO.setUserId(this.userId);
		merEntityDTO.setAddedPart(this.addedPart);
		merEntityDTO.setUpgradeParentId(this.upgradeParentId);
		merEntityDTO.setPartNumber(this.partNumber);
		merEntityDTO.setCount(this.count);
		merEntityDTO.setAmortStartDate(this.amortStartDate);
		merEntityDTO.setIsTemp(this.isTemp);
		merEntityDTO.setMerWeight(this.merWeight);
		merEntityDTO.setSerialNumberOwner(this.serialNumberOwner);
		merEntityDTO.setPartNumberOwner(this.partNumberOwner);
		merEntityDTO.setPath(this.path);
		merEntityDTO.setWorkItemId(this.workItemId);
		merEntityDTO.setOriginalPrice(this.originalPrice);
		merEntityDTO.setExpiredWarrantyDate(this.expiredWarrantyDate);
		merEntityDTO.setWarrantPartnerId(this.warrantPartnerId);
		merEntityDTO.setProjectId(this.projectId);
		merEntityDTO.setProposalId(this.proposalId);
		merEntityDTO.setNextMaintainDate(this.nextMaintainDate);
		merEntityDTO.setPackageId(this.packageId);
		merEntityDTO.setSourceId(this.sourceId);
		merEntityDTO.setManagerName(this.managerName);
		merEntityDTO.setIsExpense(this.isExpense);
		merEntityDTO.setUseGroupId(this.useGroupId);
		merEntityDTO.setMerRootId(this.merRootId);
		merEntityDTO.setTaxRate(this.taxRate);
		merEntityDTO.setVndUnitPrice(this.vndUnitPrice);
		merEntityDTO.setParentConfigId(this.parentConfigId);
		merEntityDTO.setFileId(this.fileId);
		merEntityDTO.setPlIndId(this.plIndId);
		merEntityDTO.setShipmentNo(this.shipmentNo);
		merEntityDTO.setPlCode(this.plCode);
		merEntityDTO.setBoxNo(this.boxNo);
		merEntityDTO.setKeySearch(this.keySearch);
		merEntityDTO.setContractCode(this.contractCode);
		merEntityDTO.setIsCheckedKcs(this.isCheckedKcs);
		merEntityDTO.setOldMerEntityId(this.oldMerEntityId);
		merEntityDTO.setIsMapped(this.isMapped);
		merEntityDTO.setDescription(this.description);
		merEntityDTO.setPlImportId(this.plImportId);
		merEntityDTO.setStationId(this.stationId);
		merEntityDTO.setIsInventory(this.isInventory);
		merEntityDTO.setUsedDate(this.usedDate);
		merEntityDTO.setLatestExportDate(this.latestExportDate);
		merEntityDTO.setLatestImportDate(this.latestImportDate);
		merEntityDTO.setMapNo(this.mapNo);
		merEntityDTO.setAccountType(this.accountType);
		merEntityDTO.setDepreciationDate(this.depreciationDate);
		merEntityDTO.setLatestMaintainDate(this.latestMaintainDate);
		merEntityDTO.setLatestMaintainDateBk(this.latestMaintainDateBk);
		merEntityDTO.setCountMaintain(this.countMaintain);
		merEntityDTO.setNextMaintainDateBk(this.nextMaintainDateBk);
		merEntityDTO.setTwinMerEntityId(this.twinMerEntityId);
		merEntityDTO.setOldMerId(this.oldMerId);
		merEntityDTO.setOldContractId(this.oldContractId);
		merEntityDTO.setTypeDefineHis(this.typeDefineHis);
		merEntityDTO.setEntityType(this.entityType);
		merEntityDTO.setUsedName(this.usedName);
		merEntityDTO.setUsedGroup(this.usedGroup);
		merEntityDTO.setMerModel(this.merModel);
		merEntityDTO.setOwnerMerId(this.ownerMerId);
		merEntityDTO.setTel(this.tel);
		merEntityDTO.setEmail(this.email);
		merEntityDTO.setMarkNum(this.markNum);
		merEntityDTO.setPropertyDetail(this.propertyDetail);
		merEntityDTO.setSerialNumberParent(this.serialNumberParent);
		merEntityDTO.setSplitMer(this.splitMer);
		merEntityDTO.setQuality(this.quality);
		merEntityDTO.setIsOriginal(this.isOriginal);
		merEntityDTO.setNodeId(this.nodeId);
		merEntityDTO.setCatEmployeeId(this.catEmployeeId);
		merEntityDTO.setOldMerContractId(this.oldMerContractId);
		merEntityDTO.setTypeOutSide(this.typeOutSide);
		merEntityDTO.setCreatedDate(this.createdDate);
		merEntityDTO.setUnitPriceDomestic(this.unitPriceDomestic);
		merEntityDTO.setUnitPriceForeign(this.unitPriceForeign);
		merEntityDTO.setIsLock(this.isLock);
		merEntityDTO.setIsAppo(this.isAppo);
		merEntityDTO.setIsProcessCompare(this.isProcessCompare);
		merEntityDTO.setImportDate(this.importDate);
		merEntityDTO.setRealSerialNumber(this.realSerialNumber);
		merEntityDTO.setIsvalid(this.isvalid);
		merEntityDTO.setExecuteDateKdhc(this.executeDateKdhc);
		merEntityDTO.setExecuteDateBdsc(this.executeDateBdsc);
		merEntityDTO.setTypeStationMaintain(this.typeStationMaintain);
		merEntityDTO.setExecuteMonthTimeUsed(this.executeMonthTimeUsed);
		merEntityDTO.setAssetPrice(this.assetPrice);
		merEntityDTO.setIsCheckImp(this.isCheckImp);
		merEntityDTO.setAssetPlanUseId(this.assetPlanUseId);
		merEntityDTO.setIsExpired(this.isExpired);
		merEntityDTO.setObjLevel(this.objLevel);

		return merEntityDTO;
	}
}
