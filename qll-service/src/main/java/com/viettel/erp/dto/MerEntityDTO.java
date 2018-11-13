/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatMerchandiseBO;
import com.viettel.erp.bo.MerEntityBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "MER_ENTITYBO")
public class MerEntityDTO extends BaseFWDTOImpl<MerEntityBO> {

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

    @Override
    public MerEntityBO toModel() {
        MerEntityBO merEntityBO = new MerEntityBO();
        merEntityBO.setMerEntityId(this.merEntityId);
        merEntityBO.setSerialNumber(this.serialNumber);
        merEntityBO.setUnitPrice(this.unitPrice);
        merEntityBO.setCompanyId(this.companyId);
        merEntityBO.setNationalId(this.nationalId);
        merEntityBO.setMadeYear(this.madeYear);
        merEntityBO.setMaxUsedTime(this.maxUsedTime);
       // merEntityBO.setMerId(this.merId);
        merEntityBO.setCatmerchandise(this.merId == null ?null : new CatMerchandiseBO(this.merId) );        
        merEntityBO.setContractId(this.contractId);
        merEntityBO.setGroupId(this.groupId);
        merEntityBO.setWarehouseId(this.warehouseId);
        merEntityBO.setConstructionId(this.constructionId);
        merEntityBO.setIsMerchandise(this.isMerchandise);
        merEntityBO.setWhPosId(this.whPosId);
        merEntityBO.setStatusId(this.statusId);
        merEntityBO.setParentId(this.parentId);
        merEntityBO.setIsContractAssigned(this.isContractAssigned);
        merEntityBO.setChangeAction(this.changeAction);
        merEntityBO.setUserId(this.userId);
        merEntityBO.setAddedPart(this.addedPart);
        merEntityBO.setUpgradeParentId(this.upgradeParentId);
        merEntityBO.setPartNumber(this.partNumber);
        merEntityBO.setCount(this.count);
        merEntityBO.setAmortStartDate(this.amortStartDate);
        merEntityBO.setIsTemp(this.isTemp);
        merEntityBO.setMerWeight(this.merWeight);
        merEntityBO.setSerialNumberOwner(this.serialNumberOwner);
        merEntityBO.setPartNumberOwner(this.partNumberOwner);
        merEntityBO.setPath(this.path);
        merEntityBO.setWorkItemId(this.workItemId);
        merEntityBO.setOriginalPrice(this.originalPrice);
        merEntityBO.setExpiredWarrantyDate(this.expiredWarrantyDate);
        merEntityBO.setWarrantPartnerId(this.warrantPartnerId);
        merEntityBO.setProjectId(this.projectId);
        merEntityBO.setProposalId(this.proposalId);
        merEntityBO.setNextMaintainDate(this.nextMaintainDate);
        merEntityBO.setPackageId(this.packageId);
        merEntityBO.setSourceId(this.sourceId);
        merEntityBO.setManagerName(this.managerName);
        merEntityBO.setIsExpense(this.isExpense);
        merEntityBO.setUseGroupId(this.useGroupId);
        merEntityBO.setMerRootId(this.merRootId);
        merEntityBO.setTaxRate(this.taxRate);
        merEntityBO.setVndUnitPrice(this.vndUnitPrice);
        merEntityBO.setParentConfigId(this.parentConfigId);
        merEntityBO.setFileId(this.fileId);
        merEntityBO.setPlIndId(this.plIndId);
        merEntityBO.setShipmentNo(this.shipmentNo);
        merEntityBO.setPlCode(this.plCode);
        merEntityBO.setBoxNo(this.boxNo);
        merEntityBO.setKeySearch(this.keySearch);
        merEntityBO.setContractCode(this.contractCode);
        merEntityBO.setIsCheckedKcs(this.isCheckedKcs);
        merEntityBO.setOldMerEntityId(this.oldMerEntityId);
        merEntityBO.setIsMapped(this.isMapped);
        merEntityBO.setDescription(this.description);
        merEntityBO.setPlImportId(this.plImportId);
        merEntityBO.setStationId(this.stationId);
        merEntityBO.setIsInventory(this.isInventory);
        merEntityBO.setUsedDate(this.usedDate);
        merEntityBO.setLatestExportDate(this.latestExportDate);
        merEntityBO.setLatestImportDate(this.latestImportDate);
        merEntityBO.setMapNo(this.mapNo);
        merEntityBO.setAccountType(this.accountType);
        merEntityBO.setDepreciationDate(this.depreciationDate);
        merEntityBO.setLatestMaintainDate(this.latestMaintainDate);
        merEntityBO.setLatestMaintainDateBk(this.latestMaintainDateBk);
        merEntityBO.setCountMaintain(this.countMaintain);
        merEntityBO.setNextMaintainDateBk(this.nextMaintainDateBk);
        merEntityBO.setTwinMerEntityId(this.twinMerEntityId);
        merEntityBO.setOldMerId(this.oldMerId);
        merEntityBO.setOldContractId(this.oldContractId);
        merEntityBO.setTypeDefineHis(this.typeDefineHis);
        merEntityBO.setEntityType(this.entityType);
//        merEntityBO.setUsedName(this.usedName);
        merEntityBO.setUsedGroup(this.usedGroup);
        merEntityBO.setMerModel(this.merModel);
//        merEntityBO.setOwnerMerId(this.ownerMerId);
        merEntityBO.setTel(this.tel);
        merEntityBO.setEmail(this.email);
        merEntityBO.setMarkNum(this.markNum);
        merEntityBO.setPropertyDetail(this.propertyDetail);
        merEntityBO.setSerialNumberParent(this.serialNumberParent);
        merEntityBO.setSplitMer(this.splitMer);
        merEntityBO.setQuality(this.quality);
        merEntityBO.setIsOriginal(this.isOriginal);
        merEntityBO.setNodeId(this.nodeId);
        merEntityBO.setCatEmployeeId(this.catEmployeeId);
//        merEntityBO.setOldMerContractId(this.oldMerContractId);
        merEntityBO.setTypeOutSide(this.typeOutSide);
        merEntityBO.setCreatedDate(this.createdDate);
        merEntityBO.setUnitPriceDomestic(this.unitPriceDomestic);
        merEntityBO.setUnitPriceForeign(this.unitPriceForeign);
        merEntityBO.setIsLock(this.isLock);
        merEntityBO.setIsAppo(this.isAppo);
        merEntityBO.setIsProcessCompare(this.isProcessCompare);
        merEntityBO.setImportDate(this.importDate);
        merEntityBO.setRealSerialNumber(this.realSerialNumber);
        merEntityBO.setIsvalid(this.isvalid);
        merEntityBO.setExecuteDateKdhc(this.executeDateKdhc);
        merEntityBO.setExecuteDateBdsc(this.executeDateBdsc);
        merEntityBO.setTypeStationMaintain(this.typeStationMaintain);
        merEntityBO.setExecuteMonthTimeUsed(this.executeMonthTimeUsed);
        merEntityBO.setAssetPrice(this.assetPrice);
        merEntityBO.setIsCheckImp(this.isCheckImp);
        merEntityBO.setAssetPlanUseId(this.assetPlanUseId);
        merEntityBO.setIsExpired(this.isExpired);
        merEntityBO.setObjLevel(this.objLevel);
        return merEntityBO;
    }

    @Override
     public Long getFWModelId() {
        return merEntityId;
    }
   
    @Override
    public String catchName() {
        return getMerEntityId().toString();
    }
    public java.lang.Long getMerEntityId(){
    return merEntityId;
    }
    public void setMerEntityId(java.lang.Long merEntityId)
    {
    this.merEntityId = merEntityId;
    }
    
    public java.lang.String getSerialNumber(){
    return serialNumber;
    }
    public void setSerialNumber(java.lang.String serialNumber)
    {
    this.serialNumber = serialNumber;
    }
    
    public java.lang.Long getUnitPrice(){
    return unitPrice;
    }
    public void setUnitPrice(java.lang.Long unitPrice)
    {
    this.unitPrice = unitPrice;
    }
    
    public java.lang.Long getCompanyId(){
    return companyId;
    }
    public void setCompanyId(java.lang.Long companyId)
    {
    this.companyId = companyId;
    }
    
    public java.lang.Long getNationalId(){
    return nationalId;
    }
    public void setNationalId(java.lang.Long nationalId)
    {
    this.nationalId = nationalId;
    }
    
    public java.lang.Long getMadeYear(){
    return madeYear;
    }
    public void setMadeYear(java.lang.Long madeYear)
    {
    this.madeYear = madeYear;
    }
    
    public java.lang.Double getMaxUsedTime(){
    return maxUsedTime;
    }
    public void setMaxUsedTime(java.lang.Double maxUsedTime)
    {
    this.maxUsedTime = maxUsedTime;
    }
    
    public java.lang.Long getMerId(){
    return merId;
    }
    public void setMerId(java.lang.Long merId)
    {
    this.merId = merId;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.Long getGroupId(){
    return groupId;
    }
    public void setGroupId(java.lang.Long groupId)
    {
    this.groupId = groupId;
    }
    
    public java.lang.Long getWarehouseId(){
    return warehouseId;
    }
    public void setWarehouseId(java.lang.Long warehouseId)
    {
    this.warehouseId = warehouseId;
    }
    
    public java.lang.Long getConstructionId(){
    return constructionId;
    }
    public void setConstructionId(java.lang.Long constructionId)
    {
    this.constructionId = constructionId;
    }
    
    public java.lang.Long getIsMerchandise(){
    return isMerchandise;
    }
    public void setIsMerchandise(java.lang.Long isMerchandise)
    {
    this.isMerchandise = isMerchandise;
    }
    
    public java.lang.Long getWhPosId(){
    return whPosId;
    }
    public void setWhPosId(java.lang.Long whPosId)
    {
    this.whPosId = whPosId;
    }
    
    public java.lang.Long getStatusId(){
    return statusId;
    }
    public void setStatusId(java.lang.Long statusId)
    {
    this.statusId = statusId;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.Long getIsContractAssigned(){
    return isContractAssigned;
    }
    public void setIsContractAssigned(java.lang.Long isContractAssigned)
    {
    this.isContractAssigned = isContractAssigned;
    }
    
    public java.lang.Long getChangeAction(){
    return changeAction;
    }
    public void setChangeAction(java.lang.Long changeAction)
    {
    this.changeAction = changeAction;
    }
    
    public java.lang.Long getUserId(){
    return userId;
    }
    public void setUserId(java.lang.Long userId)
    {
    this.userId = userId;
    }
    
    public java.lang.String getAddedPart(){
    return addedPart;
    }
    public void setAddedPart(java.lang.String addedPart)
    {
    this.addedPart = addedPart;
    }
    
    public java.lang.Long getUpgradeParentId(){
    return upgradeParentId;
    }
    public void setUpgradeParentId(java.lang.Long upgradeParentId)
    {
    this.upgradeParentId = upgradeParentId;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.Long getCount(){
    return count;
    }
    public void setCount(java.lang.Long count)
    {
    this.count = count;
    }
    
    public java.util.Date getAmortStartDate(){
    return amortStartDate;
    }
    public void setAmortStartDate(java.util.Date amortStartDate)
    {
    this.amortStartDate = amortStartDate;
    }
    
    public java.lang.Long getIsTemp(){
    return isTemp;
    }
    public void setIsTemp(java.lang.Long isTemp)
    {
    this.isTemp = isTemp;
    }
    
    public java.lang.Double getMerWeight(){
    return merWeight;
    }
    public void setMerWeight(java.lang.Double merWeight)
    {
    this.merWeight = merWeight;
    }
    
    public java.lang.String getSerialNumberOwner(){
    return serialNumberOwner;
    }
    public void setSerialNumberOwner(java.lang.String serialNumberOwner)
    {
    this.serialNumberOwner = serialNumberOwner;
    }
    
    public java.lang.String getPartNumberOwner(){
    return partNumberOwner;
    }
    public void setPartNumberOwner(java.lang.String partNumberOwner)
    {
    this.partNumberOwner = partNumberOwner;
    }
    
    public java.lang.String getPath(){
    return path;
    }
    public void setPath(java.lang.String path)
    {
    this.path = path;
    }
    
    public java.lang.Long getWorkItemId(){
    return workItemId;
    }
    public void setWorkItemId(java.lang.Long workItemId)
    {
    this.workItemId = workItemId;
    }
    
    public java.lang.Long getOriginalPrice(){
    return originalPrice;
    }
    public void setOriginalPrice(java.lang.Long originalPrice)
    {
    this.originalPrice = originalPrice;
    }
    
    public java.util.Date getExpiredWarrantyDate(){
    return expiredWarrantyDate;
    }
    public void setExpiredWarrantyDate(java.util.Date expiredWarrantyDate)
    {
    this.expiredWarrantyDate = expiredWarrantyDate;
    }
    
    public java.lang.Long getWarrantPartnerId(){
    return warrantPartnerId;
    }
    public void setWarrantPartnerId(java.lang.Long warrantPartnerId)
    {
    this.warrantPartnerId = warrantPartnerId;
    }
    
    public java.lang.Long getProjectId(){
    return projectId;
    }
    public void setProjectId(java.lang.Long projectId)
    {
    this.projectId = projectId;
    }
    
    public java.lang.Long getProposalId(){
    return proposalId;
    }
    public void setProposalId(java.lang.Long proposalId)
    {
    this.proposalId = proposalId;
    }
    
    public java.util.Date getNextMaintainDate(){
    return nextMaintainDate;
    }
    public void setNextMaintainDate(java.util.Date nextMaintainDate)
    {
    this.nextMaintainDate = nextMaintainDate;
    }
    
    public java.lang.Long getPackageId(){
    return packageId;
    }
    public void setPackageId(java.lang.Long packageId)
    {
    this.packageId = packageId;
    }
    
    public java.lang.Long getSourceId(){
    return sourceId;
    }
    public void setSourceId(java.lang.Long sourceId)
    {
    this.sourceId = sourceId;
    }
    
    public java.lang.String getManagerName(){
    return managerName;
    }
    public void setManagerName(java.lang.String managerName)
    {
    this.managerName = managerName;
    }
    
    public java.lang.Long getIsExpense(){
    return isExpense;
    }
    public void setIsExpense(java.lang.Long isExpense)
    {
    this.isExpense = isExpense;
    }
    
    public java.lang.Long getUseGroupId(){
    return useGroupId;
    }
    public void setUseGroupId(java.lang.Long useGroupId)
    {
    this.useGroupId = useGroupId;
    }
    
    public java.lang.Long getMerRootId(){
    return merRootId;
    }
    public void setMerRootId(java.lang.Long merRootId)
    {
    this.merRootId = merRootId;
    }
    
    public java.lang.Long getTaxRate(){
    return taxRate;
    }
    public void setTaxRate(java.lang.Long taxRate)
    {
    this.taxRate = taxRate;
    }
    
    public java.lang.Long getVndUnitPrice(){
    return vndUnitPrice;
    }
    public void setVndUnitPrice(java.lang.Long vndUnitPrice)
    {
    this.vndUnitPrice = vndUnitPrice;
    }
    
    public java.lang.Long getParentConfigId(){
    return parentConfigId;
    }
    public void setParentConfigId(java.lang.Long parentConfigId)
    {
    this.parentConfigId = parentConfigId;
    }
    
    public java.lang.Long getFileId(){
    return fileId;
    }
    public void setFileId(java.lang.Long fileId)
    {
    this.fileId = fileId;
    }
    
    public java.lang.Long getPlIndId(){
    return plIndId;
    }
    public void setPlIndId(java.lang.Long plIndId)
    {
    this.plIndId = plIndId;
    }
    
    public java.lang.String getShipmentNo(){
    return shipmentNo;
    }
    public void setShipmentNo(java.lang.String shipmentNo)
    {
    this.shipmentNo = shipmentNo;
    }
    
    public java.lang.String getPlCode(){
    return plCode;
    }
    public void setPlCode(java.lang.String plCode)
    {
    this.plCode = plCode;
    }
    
    public java.lang.String getBoxNo(){
    return boxNo;
    }
    public void setBoxNo(java.lang.String boxNo)
    {
    this.boxNo = boxNo;
    }
    
    public java.lang.Long getKeySearch(){
    return keySearch;
    }
    public void setKeySearch(java.lang.Long keySearch)
    {
    this.keySearch = keySearch;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
    public java.lang.Long getIsCheckedKcs(){
    return isCheckedKcs;
    }
    public void setIsCheckedKcs(java.lang.Long isCheckedKcs)
    {
    this.isCheckedKcs = isCheckedKcs;
    }
    
    public java.lang.Long getOldMerEntityId(){
    return oldMerEntityId;
    }
    public void setOldMerEntityId(java.lang.Long oldMerEntityId)
    {
    this.oldMerEntityId = oldMerEntityId;
    }
    
    public java.lang.Long getIsMapped(){
    return isMapped;
    }
    public void setIsMapped(java.lang.Long isMapped)
    {
    this.isMapped = isMapped;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getPlImportId(){
    return plImportId;
    }
    public void setPlImportId(java.lang.Long plImportId)
    {
    this.plImportId = plImportId;
    }
    
    public java.lang.Long getStationId(){
    return stationId;
    }
    public void setStationId(java.lang.Long stationId)
    {
    this.stationId = stationId;
    }
    
    public java.lang.Long getIsInventory(){
    return isInventory;
    }
    public void setIsInventory(java.lang.Long isInventory)
    {
    this.isInventory = isInventory;
    }
    
    public java.util.Date getUsedDate(){
    return usedDate;
    }
    public void setUsedDate(java.util.Date usedDate)
    {
    this.usedDate = usedDate;
    }
    
    public java.util.Date getLatestExportDate(){
    return latestExportDate;
    }
    public void setLatestExportDate(java.util.Date latestExportDate)
    {
    this.latestExportDate = latestExportDate;
    }
    
    public java.util.Date getLatestImportDate(){
    return latestImportDate;
    }
    public void setLatestImportDate(java.util.Date latestImportDate)
    {
    this.latestImportDate = latestImportDate;
    }
    
    public java.lang.Long getMapNo(){
    return mapNo;
    }
    public void setMapNo(java.lang.Long mapNo)
    {
    this.mapNo = mapNo;
    }
    
    public java.lang.Long getAccountType(){
    return accountType;
    }
    public void setAccountType(java.lang.Long accountType)
    {
    this.accountType = accountType;
    }
    
    public java.util.Date getDepreciationDate(){
    return depreciationDate;
    }
    public void setDepreciationDate(java.util.Date depreciationDate)
    {
    this.depreciationDate = depreciationDate;
    }
    
    public java.util.Date getLatestMaintainDate(){
    return latestMaintainDate;
    }
    public void setLatestMaintainDate(java.util.Date latestMaintainDate)
    {
    this.latestMaintainDate = latestMaintainDate;
    }
    
    public java.util.Date getLatestMaintainDateBk(){
    return latestMaintainDateBk;
    }
    public void setLatestMaintainDateBk(java.util.Date latestMaintainDateBk)
    {
    this.latestMaintainDateBk = latestMaintainDateBk;
    }
    
    public java.lang.Long getCountMaintain(){
    return countMaintain;
    }
    public void setCountMaintain(java.lang.Long countMaintain)
    {
    this.countMaintain = countMaintain;
    }
    
    public java.util.Date getNextMaintainDateBk(){
    return nextMaintainDateBk;
    }
    public void setNextMaintainDateBk(java.util.Date nextMaintainDateBk)
    {
    this.nextMaintainDateBk = nextMaintainDateBk;
    }
    
    public java.lang.Long getTwinMerEntityId(){
    return twinMerEntityId;
    }
    public void setTwinMerEntityId(java.lang.Long twinMerEntityId)
    {
    this.twinMerEntityId = twinMerEntityId;
    }
    
    public java.lang.Long getOldMerId(){
    return oldMerId;
    }
    public void setOldMerId(java.lang.Long oldMerId)
    {
    this.oldMerId = oldMerId;
    }
    
    public java.lang.Long getOldContractId(){
    return oldContractId;
    }
    public void setOldContractId(java.lang.Long oldContractId)
    {
    this.oldContractId = oldContractId;
    }
    
    public java.lang.Long getTypeDefineHis(){
    return typeDefineHis;
    }
    public void setTypeDefineHis(java.lang.Long typeDefineHis)
    {
    this.typeDefineHis = typeDefineHis;
    }
    
    public java.lang.Long getEntityType(){
    return entityType;
    }
    public void setEntityType(java.lang.Long entityType)
    {
    this.entityType = entityType;
    }
    
    public java.lang.String getUsedName(){
    return usedName;
    }
    public void setUsedName(java.lang.String usedName)
    {
    this.usedName = usedName;
    }
    
    public java.lang.String getUsedGroup(){
    return usedGroup;
    }
    public void setUsedGroup(java.lang.String usedGroup)
    {
    this.usedGroup = usedGroup;
    }
    
    public java.lang.String getMerModel(){
    return merModel;
    }
    public void setMerModel(java.lang.String merModel)
    {
    this.merModel = merModel;
    }
    
    public java.lang.Long getOwnerMerId(){
    return ownerMerId;
    }
    public void setOwnerMerId(java.lang.Long ownerMerId)
    {
    this.ownerMerId = ownerMerId;
    }
    
    public java.lang.String getTel(){
    return tel;
    }
    public void setTel(java.lang.String tel)
    {
    this.tel = tel;
    }
    
    public java.lang.String getEmail(){
    return email;
    }
    public void setEmail(java.lang.String email)
    {
    this.email = email;
    }
    
    public java.lang.Long getMarkNum(){
    return markNum;
    }
    public void setMarkNum(java.lang.Long markNum)
    {
    this.markNum = markNum;
    }
    
    public java.lang.String getPropertyDetail(){
    return propertyDetail;
    }
    public void setPropertyDetail(java.lang.String propertyDetail)
    {
    this.propertyDetail = propertyDetail;
    }
    
    public java.lang.String getSerialNumberParent(){
    return serialNumberParent;
    }
    public void setSerialNumberParent(java.lang.String serialNumberParent)
    {
    this.serialNumberParent = serialNumberParent;
    }
    
    public java.lang.Long getSplitMer(){
    return splitMer;
    }
    public void setSplitMer(java.lang.Long splitMer)
    {
    this.splitMer = splitMer;
    }
    
    public java.lang.Long getQuality(){
    return quality;
    }
    public void setQuality(java.lang.Long quality)
    {
    this.quality = quality;
    }
    
    public java.lang.Long getIsOriginal(){
    return isOriginal;
    }
    public void setIsOriginal(java.lang.Long isOriginal)
    {
    this.isOriginal = isOriginal;
    }
    
    public java.lang.Long getNodeId(){
    return nodeId;
    }
    public void setNodeId(java.lang.Long nodeId)
    {
    this.nodeId = nodeId;
    }
    
    public java.lang.Long getCatEmployeeId(){
    return catEmployeeId;
    }
    public void setCatEmployeeId(java.lang.Long catEmployeeId)
    {
    this.catEmployeeId = catEmployeeId;
    }
    
    public java.lang.Long getOldMerContractId(){
    return oldMerContractId;
    }
    public void setOldMerContractId(java.lang.Long oldMerContractId)
    {
    this.oldMerContractId = oldMerContractId;
    }
    
    public java.lang.Long getTypeOutSide(){
    return typeOutSide;
    }
    public void setTypeOutSide(java.lang.Long typeOutSide)
    {
    this.typeOutSide = typeOutSide;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Double getUnitPriceDomestic(){
    return unitPriceDomestic;
    }
    public void setUnitPriceDomestic(java.lang.Double unitPriceDomestic)
    {
    this.unitPriceDomestic = unitPriceDomestic;
    }
    
    public java.lang.Double getUnitPriceForeign(){
    return unitPriceForeign;
    }
    public void setUnitPriceForeign(java.lang.Double unitPriceForeign)
    {
    this.unitPriceForeign = unitPriceForeign;
    }
    
    public java.lang.Long getIsLock(){
    return isLock;
    }
    public void setIsLock(java.lang.Long isLock)
    {
    this.isLock = isLock;
    }
    
    public java.lang.Long getIsAppo(){
    return isAppo;
    }
    public void setIsAppo(java.lang.Long isAppo)
    {
    this.isAppo = isAppo;
    }
    
    public java.lang.Long getIsProcessCompare(){
    return isProcessCompare;
    }
    public void setIsProcessCompare(java.lang.Long isProcessCompare)
    {
    this.isProcessCompare = isProcessCompare;
    }
    
    public java.util.Date getImportDate(){
    return importDate;
    }
    public void setImportDate(java.util.Date importDate)
    {
    this.importDate = importDate;
    }
    
    public java.lang.String getRealSerialNumber(){
    return realSerialNumber;
    }
    public void setRealSerialNumber(java.lang.String realSerialNumber)
    {
    this.realSerialNumber = realSerialNumber;
    }
    
    public java.lang.Long getIsvalid(){
    return isvalid;
    }
    public void setIsvalid(java.lang.Long isvalid)
    {
    this.isvalid = isvalid;
    }
    
    public java.util.Date getExecuteDateKdhc(){
    return executeDateKdhc;
    }
    public void setExecuteDateKdhc(java.util.Date executeDateKdhc)
    {
    this.executeDateKdhc = executeDateKdhc;
    }
    
    public java.util.Date getExecuteDateBdsc(){
    return executeDateBdsc;
    }
    public void setExecuteDateBdsc(java.util.Date executeDateBdsc)
    {
    this.executeDateBdsc = executeDateBdsc;
    }
    
    public java.lang.Long getTypeStationMaintain(){
    return typeStationMaintain;
    }
    public void setTypeStationMaintain(java.lang.Long typeStationMaintain)
    {
    this.typeStationMaintain = typeStationMaintain;
    }
    
    public java.lang.Long getExecuteMonthTimeUsed(){
    return executeMonthTimeUsed;
    }
    public void setExecuteMonthTimeUsed(java.lang.Long executeMonthTimeUsed)
    {
    this.executeMonthTimeUsed = executeMonthTimeUsed;
    }
    
    public java.lang.Long getAssetPrice(){
    return assetPrice;
    }
    public void setAssetPrice(java.lang.Long assetPrice)
    {
    this.assetPrice = assetPrice;
    }
    
    public java.lang.Long getIsCheckImp(){
    return isCheckImp;
    }
    public void setIsCheckImp(java.lang.Long isCheckImp)
    {
    this.isCheckImp = isCheckImp;
    }
    
    public java.lang.Long getAssetPlanUseId(){
    return assetPlanUseId;
    }
    public void setAssetPlanUseId(java.lang.Long assetPlanUseId)
    {
    this.assetPlanUseId = assetPlanUseId;
    }
    
    public java.lang.Long getIsExpired(){
    return isExpired;
    }
    public void setIsExpired(java.lang.Long isExpired)
    {
    this.isExpired = isExpired;
    }
    
    public java.lang.Long getObjLevel(){
    return objLevel;
    }
    public void setObjLevel(java.lang.Long objLevel)
    {
    this.objLevel = objLevel;
    }
    
   
}
