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
@Table(name = "LONG_TERM_ASSET_VOUCHERS")
public class LongTermAssetVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String LONG_TERM_ASSET_VOUCHER_ID = "longTermAssetVoucherId";
        public static final String VOUCHER_CODE = "voucherCode";
        public static final String VOUCHER_DATE = "voucherDate";
        public static final String VOUCHER_VALUE = "voucherValue";
        public static final String ATTACH_ID = "attachId";
        public static final String VOUCHER_TYPE = "voucherType";
        public static final String OBJECT_ID = "objectId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "LONG_TERM_ASSET_VOUCHERS";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LONG_TERM_ASSET_VOUCHER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LONG_TERM_ASSET_VOUCHER_ID")
    private Long longTermAssetVoucherId;
    @Column(name = "VOUCHER_CODE")
    private String voucherCode;
    @Column(name = "VOUCHER_DATE")
    private Date voucherDate;
    @Column(name = "VOUCHER_VALUE")
    private Long voucherValue;
    @Column(name = "ATTACH_ID")
    private Long attachId;
    @Column(name = "VOUCHER_TYPE")
    private Long voucherType;
    @Column(name = "OBJECT_ID")
    private Long objectId;

    public Long getLongTermAssetVoucherId() {
        return longTermAssetVoucherId;
    }

    public void setLongTermAssetVoucherId(Long longTermAssetVoucherId) {
        this.longTermAssetVoucherId = longTermAssetVoucherId;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Long getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(Long voucherValue) {
        this.voucherValue = voucherValue;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public Long getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Long voucherType) {
        this.voucherType = voucherType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

}
