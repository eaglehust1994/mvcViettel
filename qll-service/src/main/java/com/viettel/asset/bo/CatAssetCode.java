package com.viettel.asset.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.collect.ImmutableMap;

@Entity
@Table(name = "CAT_ASSET_CODES")
public class CatAssetCode implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String CAT_ASSET_CODE_ID = "catAssetCodeId";
        public static final String CAAC_CODE = "caacCode";
        public static final String CAAC_NAME = "caacName";
        public static final String CAAC_PATH = "caacPath";
        public static final String CAAC_PARENT_ID = "caacParentId";
        public static final String CAAC_LEVEL = "caacLevel";
        public static final String CAAC_INDEX = "caacIndex";
        public static final String IS_ACTIVE = "isActive";
        public static final String CAAC_FULL_CODE = "caacFullCode";
        public static final String CREATOR_ID = "creatorId";
        public static final String CREATED_DATE = "createdDate";
        public static final String LAST_UPDATER_ID = "lastUpdaterId";
        public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
        public static final String IS_FIXED_ASSET = "isFixedAsset";
        public static final String DEPRECIATION_TIME = "depreciationTime";
        public static final String USE_DURATION = "useDuration";
    }

    public interface Constants {

        public static final String TABLE_NAME = "CAT_ASSET_CODES";
        public static final long CAAC_LEVEL_NHOM = 1L;
        public static final long CAAC_LEVEL_LOAI = 2L;
        public static final long CAAC_LEVEL_DONG = 3L;
        public static final long CAAC_LEVEL_TEN = 4L;
        public static final String PATH_SEPARATOR = "/";

        public static final long ACTIVE = 1L;
        public static final long INACTIVE = 0L;

        public static final Map<Long, Integer> THRESHOLD_MAX_MAP = ImmutableMap.of(CAAC_LEVEL_NHOM, 26, CAAC_LEVEL_LOAI,
                999, CAAC_LEVEL_DONG, 9999, CAAC_LEVEL_TEN, 99);

        public static final Map<Long, Integer> THRESHOLD_MIN_MAP = ImmutableMap.of(CAAC_LEVEL_NHOM, 1, CAAC_LEVEL_LOAI,
                101, CAAC_LEVEL_DONG, 2001, CAAC_LEVEL_TEN, 1);
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "CAT_ASSET_CODE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "CAT_ASSET_CODE_ID")
    private Long catAssetCodeId;
    @Column(name = "CAAC_CODE")
    private String caacCode;
    @Column(name = "CAAC_NAME")
    private String caacName;
    @Column(name = "CAAC_PATH")
    private String caacPath;
    @Column(name = "CAAC_PARENT_ID")
    private Long caacParentId;
    @Column(name = "CAAC_LEVEL")
    private Long caacLevel;
    @Column(name = "CAAC_INDEX")
    private Long caacIndex;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "CAAC_FULL_CODE")
    private String caacFullCode;
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "LAST_UPDATER_ID")
    private Long lastUpdaterId;
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;
    @Column(name = "IS_FIXED_ASSET")
    private Long isFixedAsset;
    @Column(name = "DEPRECIATION_TIME")
    private Long depreciationTime;
    @Column(name = "USE_DURATION")
    private Long useDuration;

    public Long getUseDuration() {
        return useDuration;
    }

    public void setUseDuration(Long useDuration) {
        this.useDuration = useDuration;
    }

    public Long getIsFixedAsset() {
        return isFixedAsset;
    }

    public void setIsFixedAsset(Long isFixedAsset) {
        this.isFixedAsset = isFixedAsset;
    }

    public Long getDepreciationTime() {
        return depreciationTime;
    }

    public void setDepreciationTime(Long depreciationTime) {
        this.depreciationTime = depreciationTime;
    }

    public Long getCatAssetCodeId() {
        return catAssetCodeId;
    }

    public void setCatAssetCodeId(Long catAssetCodeId) {
        this.catAssetCodeId = catAssetCodeId;
    }

    public String getCaacCode() {
        return caacCode;
    }

    public void setCaacCode(String caacCode) {
        this.caacCode = caacCode;
    }

    public String getCaacName() {
        return caacName;
    }

    public void setCaacName(String caacName) {
        this.caacName = caacName;
    }

    public String getCaacPath() {
        return caacPath;
    }

    public void setCaacPath(String caacPath) {
        this.caacPath = caacPath;
    }

    public Long getCaacParentId() {
        return caacParentId;
    }

    public void setCaacParentId(Long caacParentId) {
        this.caacParentId = caacParentId;
    }

    public Long getCaacLevel() {
        return caacLevel;
    }

    public void setCaacLevel(Long caacLevel) {
        this.caacLevel = caacLevel;
    }

    public Long getCaacIndex() {
        return caacIndex;
    }

    public void setCaacIndex(Long caacIndex) {
        this.caacIndex = caacIndex;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getCaacFullCode() {
        return caacFullCode;
    }

    public void setCaacFullCode(String caacFullCode) {
        this.caacFullCode = caacFullCode;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdaterId() {
        return lastUpdaterId;
    }

    public void setLastUpdaterId(Long lastUpdaterId) {
        this.lastUpdaterId = lastUpdaterId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
