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
@Table(name = "CAT_WAREHOUSE")
public class CatWarehouse implements Serializable{

   
    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String WAREHOUSE_ID = "warehouseId";
        public static final String NAME = "name";
        public static final String CODE = "code";
        public static final String ADDRESS = "address";
        public static final String GROUP_ID = "groupId";
        public static final String IS_ACTIVE = "isActive";
        public static final String PARENT_ID = "parentId";
        public static final String TITLE = "title";
        public static final String THE_LEVEL = "theLevel";
        public static final String PATH = "path";
        public static final String WH_TYPE = "whType";
        public static final String OWN_GROUP_ID = "ownGroupId";
        public static final String COMPANY_TYPE = "companyType";
        public static final String VIRTUAL_TYPE = "virtualType";
        public static final String WAREHOUSE_CLONE_ID = "warehouseCloneId";
        public static final String PROVINCE_ID = "provinceId";
        public static final String IS_OPEN = "isOpen";
        public static final String CREATED_DATE = "createdDate";
        public static final String CAT_WAREHOUSE_TYPE_ID = "catWarehouseTypeId";
        public static final String EXPORT_EACH_OTHER = "exportEachOther";
        public static final String LOCK_STATUS = "lockStatus";
        public static final String CAT_WAREHOUSE_PHYSIC_ID = "catWarehousePhysicId";
        public static final String WAREHOUSE_LOGISTIC_ID = "warehouseLogisticId";
        public static final String MODIFIELD_DATE = "modifieldDate";
        public static final String IS_INVENTORY = "isInventory";

    }

    public interface Constants {

        public static final String TABLE_NAME = "CAT_WAREHOUSE";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "CAT_WAREHOUSE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "WAREHOUSE_ID")
    private Long warehouseId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CODE")
    private String code;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "THE_LEVEL")
    private Long theLevel;
    @Column(name = "PATH")
    private String path;
    @Column(name = "WH_TYPE")
    private String whType;
    @Column(name = "OWN_GROUP_ID")
    private Long ownGroupId;
    @Column(name = "COMPANY_TYPE")
    private Long companyType;
    @Column(name = "VIRTUAL_TYPE")
    private Long virtualType;
    @Column(name = "WAREHOUSE_CLONE_ID")
    private Long warehouseCloneId;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "IS_OPEN")
    private Long isOpen;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CAT_WAREHOUSE_TYPE_ID")
    private Long catWarehouseTypeId;
    @Column(name = "EXPORT_EACH_OTHER")
    private Long exportEachOther;
    @Column(name = "LOCK_STATUS")
    private Long lockStatus;
    @Column(name = "CAT_WAREHOUSE_PHYSIC_ID")
    private Long catWarehousePhysicId;
    @Column(name = "WAREHOUSE_LOGISTIC_ID")
    private Long warehouseLogisticId;
    @Column(name = "MODIFIELD_DATE")
    private Date modifieldDate;
    @Column(name = "IS_INVENTORY")
    private Long isInventory;

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
