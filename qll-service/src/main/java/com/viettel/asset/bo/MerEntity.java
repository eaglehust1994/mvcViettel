package com.viettel.asset.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MER_ENTITY")
public class MerEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String MER_ENTITY_ID = "merEntityId";
        public static final String SERIAL_NUMBER = "serialNumber";
        public static final String UNIT_PRICE = "unitPrice";
        public static final String COMPANY_ID = "companyId";
        public static final String NATIONAL_ID = "nationalId";
        public static final String MADE_YEAR = "madeYear";
        public static final String MAX_USED_TIME = "maxUsedTime";
        public static final String MER_ID = "merId";
        public static final String CONTRACT_ID = "contractId";
        public static final String GROUP_ID = "groupId";
        public static final String WAREHOUSE_ID = "warehouseId";
        public static final String CONSTRUCTION_ID = "constructionId";
        public static final String IS_MERCHANDISE = "isMerchandise";
        public static final String WH_POS_ID = "whPosId";
        public static final String STATUS_ID = "statusId";
        public static final String PARENT_ID = "parentId";
        public static final String IS_CONTRACT_ASSIGNED = "isContractAssigned";
        public static final String CHANGE_ACTION = "changeAction";
        public static final String USER_ID = "userId";
        public static final String ADDED_PART = "addedPart";
        public static final String UPGRADE_PARENT_ID = "upgradeParentId";
        public static final String PART_NUMBER = "partNumber";
        public static final String COUNT = "count";
        public static final String AMORT_START_DATE = "amortStartDate";
        public static final String IS_TEMP = "isTemp";
        public static final String MER_WEIGHT = "merWeight";
        public static final String SERIAL_NUMBER_OWNER = "serialNumberOwner";
        public static final String PART_NUMBER_OWNER = "partNumberOwner";
        public static final String PATH = "path";
        public static final String WORK_ITEM_ID = "workItemId";
        public static final String ORIGINAL_PRICE = "originalPrice";
        public static final String EXPIRED_WARRANTY_DATE = "expiredWarrantyDate";
        public static final String WARRANT_PARTNER_ID = "warrantPartnerId";
        public static final String PROJECT_ID = "projectId";
        public static final String PROPOSAL_ID = "proposalId";
        public static final String NEXT_MAINTAIN_DATE = "nextMaintainDate";
        public static final String PACKAGE_ID = "packageId";
        public static final String SOURCE_ID = "sourceId";
        public static final String MANAGER_NAME = "managerName";
        public static final String IS_EXPENSE = "isExpense";
        public static final String USE_GROUP_ID = "useGroupId";
        public static final String MER_ROOT_ID = "merRootId";
        public static final String TAX_RATE = "taxRate";
        public static final String VND_UNIT_PRICE = "vndUnitPrice";
        public static final String PARENT_CONFIG_ID = "parentConfigId";
        public static final String FILE_ID = "fileId";
        public static final String PL_IND_ID = "plIndId";
        public static final String SHIPMENT_NO = "shipmentNo";
        public static final String PL_CODE = "plCode";
        public static final String BOX_NO = "boxNo";
        public static final String KEY_SEARCH = "keySearch";
        public static final String CONTRACT_CODE = "contractCode";
        public static final String IS_CHECKED_KCS = "isCheckedKcs";
        public static final String OLD_MER_ENTITY_ID = "oldMerEntityId";
        public static final String IS_MAPPED = "isMapped";
        public static final String DESCRIPTION = "description";
        public static final String PL_IMPORT_ID = "plImportId";
        public static final String STATION_ID = "stationId";
        public static final String IS_INVENTORY = "isInventory";
        public static final String USED_DATE = "usedDate";
        public static final String LATEST_EXPORT_DATE = "latestExportDate";
        public static final String LATEST_IMPORT_DATE = "latestImportDate";
        public static final String MAP_NO = "mapNo";
        public static final String ACCOUNT_TYPE = "accountType";
        public static final String DEPRECIATION_DATE = "depreciationDate";
        public static final String LATEST_MAINTAIN_DATE = "latestMaintainDate";
        public static final String LATEST_MAINTAIN_DATE_BK = "latestMaintainDateBk";
        public static final String COUNT_MAINTAIN = "countMaintain";
        public static final String NEXT_MAINTAIN_DATE_BK = "nextMaintainDateBk";
        public static final String TWIN_MER_ENTITY_ID = "twinMerEntityId";
        public static final String OLD_MER_ID = "oldMerId";
        public static final String OLD_CONTRACT_ID = "oldContractId";
        public static final String TYPE_DEFINE_HIS = "typeDefineHis";
        public static final String ENTITY_TYPE = "entityType";
        public static final String USED_NAME = "usedName";
        public static final String USED_GROUP = "usedGroup";
        public static final String MER_MODEL = "merModel";
        public static final String OWNER_MER_ID = "ownerMerId";
        public static final String TEL = "tel";
        public static final String EMAIL = "email";
        public static final String MARK_NUM = "markNum";
        public static final String PROPERTY_DETAIL = "propertyDetail";
        public static final String SERIAL_NUMBER_PARENT = "serialNumberParent";
        public static final String SPLIT_MER = "splitMer";
        public static final String QUALITY = "quality";
        public static final String IS_ORIGINAL = "isOriginal";
        public static final String NODE_ID = "nodeId";
        public static final String CAT_EMPLOYEE_ID = "catEmployeeId";
        public static final String OLD_MER_CONTRACT_ID = "oldMerContractId";
        public static final String TYPE_OUT_SIDE = "typeOutSide";
        public static final String CREATED_DATE = "createdDate";
        public static final String UNIT_PRICE_DOMESTIC = "unitPriceDomestic";
        public static final String UNIT_PRICE_FOREIGN = "unitPriceForeign";
        public static final String IS_LOCK = "isLock";
        public static final String IS_APPO = "isAppo";
        public static final String IS_PROCESS_COMPARE = "isProcessCompare";
        public static final String IMPORT_DATE = "importDate";
        public static final String REAL_SERIAL_NUMBER = "realSerialNumber";
        public static final String ISVALID = "isvalid";
        public static final String EXECUTE_DATE_KDHC = "executeDateKdhc";
        public static final String EXECUTE_DATE_BDSC = "executeDateBdsc";
        public static final String TYPE_STATION_MAINTAIN = "typeStationMaintain";
        public static final String EXECUTE_MONTH_TIME_USED = "executeMonthTimeUsed";
        public static final String ASSET_PRICE = "assetPrice";
        public static final String IS_CHECK_IMP = "isCheckImp";
        public static final String ASSET_PLAN_USE_ID = "assetPlanUseId";
        public static final String IS_EXPIRED = "isExpired";
        public static final String OBJ_LEVEL = "objLevel";
        public static final String LONG_TERM_ASSET_ID = "longTermAssetId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "MER_ENTITY";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "MER_ENTITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "MER_ENTITY_ID")
    private Long merEntityId;
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;
    @Column(name = "UNIT_PRICE")
    private Long unitPrice;
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Column(name = "NATIONAL_ID")
    private Long nationalId;
    @Column(name = "MADE_YEAR")
    private Long madeYear;
    @Column(name = "MAX_USED_TIME")
    private Double maxUsedTime;
    @Column(name = "MER_ID")
    private Long merId;
    @Column(name = "CONTRACT_ID")
    private Long contractId;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "WAREHOUSE_ID")
    private Long warehouseId;
    @Column(name = "CONSTRUCTION_ID")
    private Long constructionId;
    @Column(name = "IS_MERCHANDISE")
    private Long isMerchandise;
    @Column(name = "WH_POS_ID")
    private Long whPosId;
    @Column(name = "STATUS_ID")
    private Long statusId;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "IS_CONTRACT_ASSIGNED")
    private Long isContractAssigned;
    @Column(name = "CHANGE_ACTION")
    private Long changeAction;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "ADDED_PART")
    private String addedPart;
    @Column(name = "UPGRADE_PARENT_ID")
    private Long upgradeParentId;
    @Column(name = "PART_NUMBER")
    private String partNumber;
    @Column(name = "COUNT")
    private Long count;
    @Column(name = "AMORT_START_DATE")
    private Date amortStartDate;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "MER_WEIGHT")
    private Double merWeight;
    @Column(name = "SERIAL_NUMBER_OWNER")
    private String serialNumberOwner;
    @Column(name = "PART_NUMBER_OWNER")
    private String partNumberOwner;
    @Column(name = "PATH")
    private String path;
    @Column(name = "WORK_ITEM_ID")
    private Long workItemId;
    @Column(name = "ORIGINAL_PRICE")
    private Long originalPrice;
    @Column(name = "EXPIRED_WARRANTY_DATE")
    private Date expiredWarrantyDate;
    @Column(name = "WARRANT_PARTNER_ID")
    private Long warrantPartnerId;
    @Column(name = "PROJECT_ID")
    private Long projectId;
    @Column(name = "PROPOSAL_ID")
    private Long proposalId;
    @Column(name = "NEXT_MAINTAIN_DATE")
    private Date nextMaintainDate;
    @Column(name = "PACKAGE_ID")
    private Long packageId;
    @Column(name = "SOURCE_ID")
    private Long sourceId;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @Column(name = "IS_EXPENSE")
    private Long isExpense;
    @Column(name = "USE_GROUP_ID")
    private Long useGroupId;
    @Column(name = "MER_ROOT_ID")
    private Long merRootId;
    @Column(name = "TAX_RATE")
    private Long taxRate;
    @Column(name = "VND_UNIT_PRICE")
    private Long vndUnitPrice;
    @Column(name = "PARENT_CONFIG_ID")
    private Long parentConfigId;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "PL_IND_ID")
    private Long plIndId;
    @Column(name = "SHIPMENT_NO")
    private String shipmentNo;
    @Column(name = "PL_CODE")
    private String plCode;
    @Column(name = "BOX_NO")
    private String boxNo;
    @Column(name = "KEY_SEARCH")
    private Long keySearch;
    @Column(name = "CONTRACT_CODE")
    private String contractCode;
    @Column(name = "IS_CHECKED_KCS")
    private Long isCheckedKcs;
    @Column(name = "OLD_MER_ENTITY_ID")
    private Long oldMerEntityId;
    @Column(name = "IS_MAPPED")
    private Long isMapped;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PL_IMPORT_ID")
    private Long plImportId;
    @Column(name = "STATION_ID")
    private Long stationId;
    @Column(name = "IS_INVENTORY")
    private Long isInventory;
    @Column(name = "USED_DATE")
    private Date usedDate;
    @Column(name = "LATEST_EXPORT_DATE")
    private Date latestExportDate;
    @Column(name = "LATEST_IMPORT_DATE")
    private Date latestImportDate;
    @Column(name = "MAP_NO")
    private Long mapNo;
    @Column(name = "ACCOUNT_TYPE")
    private Long accountType;
    @Column(name = "DEPRECIATION_DATE")
    private Date depreciationDate;
    @Column(name = "LATEST_MAINTAIN_DATE")
    private Date latestMaintainDate;
    @Column(name = "LATEST_MAINTAIN_DATE_BK")
    private Date latestMaintainDateBk;
    @Column(name = "COUNT_MAINTAIN")
    private Long countMaintain;
    @Column(name = "NEXT_MAINTAIN_DATE_BK")
    private Date nextMaintainDateBk;
    @Column(name = "TWIN_MER_ENTITY_ID")
    private Long twinMerEntityId;
    @Column(name = "OLD_MER_ID")
    private Long oldMerId;
    @Column(name = "OLD_CONTRACT_ID")
    private Long oldContractId;
    @Column(name = "TYPE_DEFINE_HIS")
    private Long typeDefineHis;
    @Column(name = "ENTITY_TYPE")
    private Long entityType;
 /*   @Column(name = "USED_NAME")
    private String usedName;*/
    @Column(name = "USED_GROUP")
    private String usedGroup;
    @Column(name = "MER_MODEL")
    private String merModel;
    /*@Column(name = "OWNER_MER_ID")
    private Long ownerMerId;*/
    @Column(name = "TEL")
    private String tel;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MARK_NUM")
    private Long markNum;
    @Column(name = "PROPERTY_DETAIL")
    private String propertyDetail;
    @Column(name = "SERIAL_NUMBER_PARENT")
    private String serialNumberParent;
    @Column(name = "SPLIT_MER")
    private Long splitMer;
    @Column(name = "QUALITY")
    private Long quality;
    @Column(name = "IS_ORIGINAL")
    private Long isOriginal;
    @Column(name = "NODE_ID")
    private Long nodeId;
    @Column(name = "CAT_EMPLOYEE_ID")
    private Long catEmployeeId;
//    @Column(name = "OLD_MER_CONTRACT_ID")
//    private Long oldMerContractId;
    @Column(name = "TYPE_OUT_SIDE")
    private Long typeOutSide;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UNIT_PRICE_DOMESTIC")
    private Double unitPriceDomestic;
    @Column(name = "UNIT_PRICE_FOREIGN")
    private Double unitPriceForeign;
    @Column(name = "IS_LOCK")
    private Long isLock;
    @Column(name = "IS_APPO")
    private Long isAppo;
    @Column(name = "IS_PROCESS_COMPARE")
    private Long isProcessCompare;
    @Column(name = "IMPORT_DATE")
    private Date importDate;
    @Column(name = "REAL_SERIAL_NUMBER")
    private String realSerialNumber;
    @Column(name = "ISVALID")
    private Long isvalid;
    @Column(name = "EXECUTE_DATE_KDHC")
    private Date executeDateKdhc;
    @Column(name = "EXECUTE_DATE_BDSC")
    private Date executeDateBdsc;
    @Column(name = "TYPE_STATION_MAINTAIN")
    private Long typeStationMaintain;
    @Column(name = "EXECUTE_MONTH_TIME_USED")
    private Long executeMonthTimeUsed;
    @Column(name = "ASSET_PRICE")
    private Long assetPrice;
    @Column(name = "IS_CHECK_IMP")
    private Long isCheckImp;
    @Column(name = "ASSET_PLAN_USE_ID")
    private Long assetPlanUseId;
    @Column(name = "IS_EXPIRED")
    private Long isExpired;
    @Column(name = "OBJ_LEVEL")
    private Long objLevel;
    @Column(name = "LONG_TERM_ASSET_ID")
    private Long longTermAssetId;

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

//    public String getUsedName() {
//        return usedName;
//    }
//
//    public void setUsedName(String usedName) {
//        this.usedName = usedName;
//    }

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

/*    public Long getOwnerMerId() {
        return ownerMerId;
    }*/
/*
    public void setOwnerMerId(Long ownerMerId) {
        this.ownerMerId = ownerMerId;
    }*/

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

   /* public Long getOldMerContractId() {
        return oldMerContractId;
    }

    public void setOldMerContractId(Long oldMerContractId) {
        this.oldMerContractId = oldMerContractId;
    }*/

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

}
