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
@Table(name = "LONG_TERM_ASSETS")
public class LongTermAsset implements Serializable{


    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String LONG_TERM_ASSET_ID = "longTermAssetId";
        public static final String CAT_ASSET_CODE_ID = "catAssetCodeId";
        public static final String LOTA_CODE = "lotaCode";
        public static final String LOTA_INDEX = "lotaIndex";
        public static final String CONSTRUCT_ID = "constructId";
        public static final String STATION_ID = "stationId";
        public static final String EMPLOYEE_ID = "employeeId";
        public static final String GROUP_ID = "groupId";
        public static final String USE_GROUP_ID = "useGroupId";
        public static final String ORIGINAL_PRICE = "originalPrice";
        public static final String RESIDUAL_PRICE = "residualPrice";
        public static final String DEPRECIATION_START_DATE = "depreciationStartDate";
        public static final String DEPRECIATION_TIME = "depreciationTime";
        public static final String DEPRECIATION_RATE = "depreciationRate";
        public static final String DEPRECIATED_TIME = "depreciatedTime";
        public static final String DEPRECIATIED_VALUE = "depreciatiedValue";
        public static final String LOTA_TYPE = "lotaType";
        public static final String IS_ACTIVE = "isActive";
        public static final String CREATOR_ID = "creatorId";
        public static final String CREATED_GROUP_ID = "createdGroupId";
        public static final String CREATOR_DATE = "creatorDate";
        public static final String UPDATOR_ID = "updatorId";
        public static final String UPDATED_GROUP_ID = "updatedGroupId";
        public static final String UPDATOR_DATE = "updatorDate";
        public static final String WH_EXP_REQ_ID = "whExpReqId";
        public static final String VOUCHER_TYPE = "voucherType";
        public static final String CREATED_SOURCE = "createdSource";
        public static final String LOTA_STATUS = "lotaStatus";
        public static final String HANDOVER_CODE = "handoverCode";
        public static final String IS_SENT_ERP = "isSentErp";

    }

    public interface Constants {

        public static final String TABLE_NAME = "LONG_TERM_ASSETS";
        public static final Long VOUCHER_TYPE_DTMS = 1L;
        public static final Long VOUCHER_TYPE_XDCB = 2L;
        public static final Long IS_ACTIVE = 1l;
        public static final Long LOTA_TYPE_TSCD = 1l;

    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LONG_TERM_ASSET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LONG_TERM_ASSET_ID")
    private Long longTermAssetId;
    @Column(name = "CAT_ASSET_CODE_ID")
    private Long catAssetCodeId;
    @Column(name = "LOTA_CODE")
    private String lotaCode;
    @Column(name = "LOTA_INDEX")
    private Long lotaIndex;
    @Column(name = "CONSTRUCT_ID")
    private Long constructId;
    @Column(name = "STATION_ID")
    private Long stationId;
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "USE_GROUP_ID")
    private Long useGroupId;
    @Column(name = "ORIGINAL_PRICE")
    private Long originalPrice;
    @Column(name = "RESIDUAL_PRICE")
    private Long residualPrice;
    @Column(name = "DEPRECIATION_START_DATE")
    private Date depreciationStartDate;
    @Column(name = "DEPRECIATION_TIME")
    private Long depreciationTime;
    @Column(name = "DEPRECIATION_RATE")
    private Long depreciationRate;
    @Column(name = "DEPRECIATED_TIME")
    private Long depreciatedTime;
    @Column(name = "DEPRECIATIED_VALUE")
    private Long depreciatiedValue;
    @Column(name = "LOTA_TYPE")
    private Long lotaType;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    @Column(name = "CREATED_GROUP_ID")
    private Long createdGroupId;
    @Column(name = "CREATOR_DATE")
    private Date creatorDate;
    @Column(name = "UPDATOR_ID")
    private Long updatorId;
    @Column(name = "UPDATED_GROUP_ID")
    private Long updatedGroupId;
    @Column(name = "UPDATOR_DATE")
    private Date updatorDate;
    @Column(name = "WH_EXP_REQ_ID")
    private Long whExpReqId;
    @Column(name = "VOUCHER_TYPE")
    private Long voucherType;
    @Column(name = "CREATED_SOURCE")
    private String createdSource;
    @Column(name = "LOTA_STATUS")
    private Long lotaStatus;
    @Column(name = "HANDOVER_CODE")
    private String handoverCode;
    @Column(name = "IS_SENT_ERP")
    private Long isSentErp;
    @Column(name = "MADE_YEAR")
    private Long madeYear;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "NATIONAL_NAME")
    private String nationalName;
    @Column(name = "UPGRADE_STATUS")
    private Long upgradeStatus;
    /**
     * Thêm cột lý do từ chối (length=200).
     */
    @Column(name = "ERP_FAIL_REASON")
    private String erpFailReason;
    
    /*
     * Hành thành từ phiếu đề nghị
     * 1: hình thành từ phiếu đề nghị
     * 0, null: còn lại
     */
    @Column(name = "IS_OFFER_SLIP")
    private Long isOfferSlip;
    
    

    public Long getIsOfferSlip() {
		return isOfferSlip;
	}

	public void setIsOfferSlip(Long isOfferSlip) {
		this.isOfferSlip = isOfferSlip;
	}

	public String getErpFailReason() {
		return erpFailReason;
	}

	public void setErpFailReason(String erpFailReason) {
		this.erpFailReason = erpFailReason;
	}

	public Long getUpgradeStatus() {
		return upgradeStatus;
	}

	public void setUpgradeStatus(Long upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}

	public Long getMadeYear() {
		return madeYear;
	}

	public void setMadeYear(Long madeYear) {
		this.madeYear = madeYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNationalName() {
		return nationalName;
	}

	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}

	public Long getIsSentErp() {
        return isSentErp;
    }

    public void setIsSentErp(Long isSentErp) {
        this.isSentErp = isSentErp;
    }

    public Long getLongTermAssetId() {
        return longTermAssetId;
    }

    public void setLongTermAssetId(Long longTermAssetId) {
        this.longTermAssetId = longTermAssetId;
    }

    public Long getCatAssetCodeId() {
        return catAssetCodeId;
    }

    public void setCatAssetCodeId(Long catAssetCodeId) {
        this.catAssetCodeId = catAssetCodeId;
    }

    public String getLotaCode() {
        return lotaCode;
    }

    public void setLotaCode(String lotaCode) {
        this.lotaCode = lotaCode;
    }

    public Long getLotaIndex() {
        return lotaIndex;
    }

    public void setLotaIndex(Long lotaIndex) {
        this.lotaIndex = lotaIndex;
    }

    public Long getConstructId() {
        return constructId;
    }

    public void setConstructId(Long constructId) {
        this.constructId = constructId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUseGroupId() {
        return useGroupId;
    }

    public void setUseGroupId(Long useGroupId) {
        this.useGroupId = useGroupId;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getResidualPrice() {
        return residualPrice;
    }

    public void setResidualPrice(Long residualPrice) {
        this.residualPrice = residualPrice;
    }

    public Date getDepreciationStartDate() {
        return depreciationStartDate;
    }

    public void setDepreciationStartDate(Date depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }

    public Long getDepreciationTime() {
        return depreciationTime;
    }

    public void setDepreciationTime(Long depreciationTime) {
        this.depreciationTime = depreciationTime;
    }

    public Long getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(Long depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public Long getDepreciatedTime() {
        return depreciatedTime;
    }

    public void setDepreciatedTime(Long depreciatedTime) {
        this.depreciatedTime = depreciatedTime;
    }

    public Long getDepreciatiedValue() {
        return depreciatiedValue;
    }

    public void setDepreciatiedValue(Long depreciatiedValue) {
        this.depreciatiedValue = depreciatiedValue;
    }

    public Long getLotaType() {
        return lotaType;
    }

    public void setLotaType(Long lotaType) {
        this.lotaType = lotaType;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatedGroupId() {
        return createdGroupId;
    }

    public void setCreatedGroupId(Long createdGroupId) {
        this.createdGroupId = createdGroupId;
    }

    public Date getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(Date creatorDate) {
        this.creatorDate = creatorDate;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public Long getUpdatedGroupId() {
        return updatedGroupId;
    }

    public void setUpdatedGroupId(Long updatedGroupId) {
        this.updatedGroupId = updatedGroupId;
    }

    public Date getUpdatorDate() {
        return updatorDate;
    }

    public void setUpdatorDate(Date updatorDate) {
        this.updatorDate = updatorDate;
    }

    public Long getWhExpReqId() {
        return whExpReqId;
    }

    public void setWhExpReqId(Long whExpReqId) {
        this.whExpReqId = whExpReqId;
    }

    public Long getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Long voucherType) {
        this.voucherType = voucherType;
    }

    public String getCreatedSource() {
        return createdSource;
    }

    public void setCreatedSource(String createdSource) {
        this.createdSource = createdSource;
    }

    public Long getLotaStatus() {
        return lotaStatus;
    }

    public void setLotaStatus(Long lotaStatus) {
        this.lotaStatus = lotaStatus;
    }

    public String getHandoverCode() {
        return handoverCode;
    }

    public void setHandoverCode(String handoverCode) {
        this.handoverCode = handoverCode;
    }

}
