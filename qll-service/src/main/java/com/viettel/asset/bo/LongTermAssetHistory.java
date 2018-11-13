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
@Table(name = "LONG_TERM_ASSET_HISTORIES")
public class LongTermAssetHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String LONG_TERM_ASSET_HISTORY_ID = "longTermAssetHistoryId";
        public static final String LONG_TERM_ASSET_ID = "longTermAssetId";
        public static final String LOTA_CODE = "lotaCode";
        public static final String LTAH_DATE = "ltahDate";
        public static final String LTAH_TYPE = "ltahType";
        public static final String LTAH_VALUE = "ltahValue";
        public static final String REASON_CHANGE = "reasonChange";
        public static final String LTAH_OLD_VALUE = "ltahOldValue";
        public static final String LTAH_NEW_VALUE = "ltahNewValue";
        public static final String LONG_TERM_ASSET_VOUCHER_ID = "longTermAssetVoucherId";
        public static final String DEPRECIATED_VALUE = "depreciatedValue";
        public static final String DEPRECIATED_TIME = "depreciatedTime";
        public static final String DEPRECIATED_MONTH_VALUE = "depreciatedMonthValue";
        public static final String DEPRECIATED_MONTH = "depreciatedMonth";
        public static final String DEPRECIATED_YEAR = "depreciatedYear";
        public static final String DEPRECIATED_DATE = "depreciatedDate";
        public static final String GROUP_ID = "groupId";
        public static final String USE_GROUP_ID = "useGroupId";
        public static final String TO_GROUP_ID = "toGroupId";
        public static final String TO_USE_GROUP_ID = "toUseGroupId";
        public static final String CREATED_DATE = "createdDate";
        public static final String HANDOVER_CODE = "handoverCode";

    }

    public interface Constants {

        public static final String TABLE_NAME = "LONG_TERM_ASSET_HISTORY";
        public static final Long LTAH_TYPE_DEPRECIATION = 7l;
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LONG_TERM_ASSET_HISTORY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LONG_TERM_ASSET_HISTORY_ID")
    private Long longTermAssetHistoryId;
    @Column(name = "LONG_TERM_ASSET_ID")
    private Long longTermAssetId;
    @Column(name = "LOTA_CODE")
    private String lotaCode;
    @Column(name = "LTAH_DATE")
    private Date ltahDate;
    @Column(name = "LTAH_TYPE")
    private Long ltahType;
    @Column(name = "LTAH_VALUE")
    private Long ltahValue;
    @Column(name = "REASON_CHANGE")
    private String reasonChange;
    @Column(name = "LTAH_OLD_VALUE")
    private Long ltahOldValue;
    @Column(name = "LTAH_NEW_VALUE")
    private Long ltahNewValue;
    @Column(name = "LONG_TERM_ASSET_VOUCHER_ID")
    private Long longTermAssetVoucherId;
    @Column(name = "DEPRECIATED_VALUE")
    private Long depreciatedValue;
    @Column(name = "DEPRECIATED_TIME")
    private Long depreciatedTime;
    @Column(name = "DEPRECIATED_MONTH_VALUE")
    private Long depreciatedMonthValue;
    @Column(name = "DEPRECIATED_MONTH")
    private Long depreciatedMonth;
    @Column(name = "DEPRECIATED_YEAR")
    private Long depreciatedYear;
    @Column(name = "DEPRECIATED_DATE")
    private Date depreciatedDate;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "USE_GROUP_ID")
    private Long useGroupId;
    @Column(name = "TO_GROUP_ID")
    private Long toGroupId;
    @Column(name = "TO_USE_GROUP_ID")
    private Long toUseGroupId;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "HANDOVER_CODE")
    private String handoverCode;

    public Long getLongTermAssetHistoryId() {
        return longTermAssetHistoryId;
    }

    public void setLongTermAssetHistoryId(Long longTermAssetHistoryId) {
        this.longTermAssetHistoryId = longTermAssetHistoryId;
    }

    public Long getLongTermAssetId() {
        return longTermAssetId;
    }

    public void setLongTermAssetId(Long longTermAssetId) {
        this.longTermAssetId = longTermAssetId;
    }

    public String getLotaCode() {
        return lotaCode;
    }

    public void setLotaCode(String lotaCode) {
        this.lotaCode = lotaCode;
    }

    public Date getLtahDate() {
        return ltahDate;
    }

    public void setLtahDate(Date ltahDate) {
        this.ltahDate = ltahDate;
    }

    public Long getLtahType() {
        return ltahType;
    }

    public void setLtahType(Long ltahType) {
        this.ltahType = ltahType;
    }

    public Long getLtahValue() {
        return ltahValue;
    }

    public void setLtahValue(Long ltahValue) {
        this.ltahValue = ltahValue;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public Long getLtahOldValue() {
        return ltahOldValue;
    }

    public void setLtahOldValue(Long ltahOldValue) {
        this.ltahOldValue = ltahOldValue;
    }

    public Long getLtahNewValue() {
        return ltahNewValue;
    }

    public void setLtahNewValue(Long ltahNewValue) {
        this.ltahNewValue = ltahNewValue;
    }

    public Long getLongTermAssetVoucherId() {
        return longTermAssetVoucherId;
    }

    public void setLongTermAssetVoucherId(Long longTermAssetVoucherId) {
        this.longTermAssetVoucherId = longTermAssetVoucherId;
    }

    public Long getDepreciatedValue() {
        return depreciatedValue;
    }

    public void setDepreciatedValue(Long depreciatedValue) {
        this.depreciatedValue = depreciatedValue;
    }

    public Long getDepreciatedTime() {
        return depreciatedTime;
    }

    public void setDepreciatedTime(Long depreciatedTime) {
        this.depreciatedTime = depreciatedTime;
    }

    public Long getDepreciatedMonthValue() {
        return depreciatedMonthValue;
    }

    public void setDepreciatedMonthValue(Long depreciatedMonthValue) {
        this.depreciatedMonthValue = depreciatedMonthValue;
    }

    public Long getDepreciatedMonth() {
        return depreciatedMonth;
    }

    public void setDepreciatedMonth(Long depreciatedMonth) {
        this.depreciatedMonth = depreciatedMonth;
    }

    public Long getDepreciatedYear() {
        return depreciatedYear;
    }

    public void setDepreciatedYear(Long depreciatedYear) {
        this.depreciatedYear = depreciatedYear;
    }

    public Date getDepreciatedDate() {
        return depreciatedDate;
    }

    public void setDepreciatedDate(Date depreciatedDate) {
        this.depreciatedDate = depreciatedDate;
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

    public Long getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Long toGroupId) {
        this.toGroupId = toGroupId;
    }

    public Long getToUseGroupId() {
        return toUseGroupId;
    }

    public void setToUseGroupId(Long toUseGroupId) {
        this.toUseGroupId = toUseGroupId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getHandoverCode() {
        return handoverCode;
    }

    public void setHandoverCode(String handoverCode) {
        this.handoverCode = handoverCode;
    }

}
