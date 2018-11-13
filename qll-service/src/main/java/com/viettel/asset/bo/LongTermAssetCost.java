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
@Table(name = "LONG_TERM_ASSET_COSTS")
public class LongTermAssetCost implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String LONG_TERM_ASSET_COST_ID = "longTermAssetCostId";
        public static final String LOAC_TYPE = "loacType";
        public static final String LOAC_VALUE = "loacValue";
        public static final String LONG_TERM_ASSET_ID = "longTermAssetId";
        public static final String VOUCHER_LIST = "voucherList";
        public static final String CREATED_DATE = "createdDate";

    }

    public interface Constants {

        public static final String TABLE_NAME = "LONG_TERM_ASSET_COSTS";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LONG_TERM_ASSET_COST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LONG_TERM_ASSET_COST_ID")
    private Long longTermAssetCostId;
    @Column(name = "LOAC_TYPE")
    private Long loacType;
    @Column(name = "LOAC_VALUE")
    private Long loacValue;
    @Column(name = "LONG_TERM_ASSET_ID")
    private Long longTermAssetId;
    @Column(name = "VOUCHER_LIST")
    private String voucherList;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CONTENT_COST")
    private String contentCost;
    @Column(name = "ESTIMATE_LOAC_VALUE")
    private Long estimateLoacValue;
    @Column(name = "VOUCHER_STATUS")
    private Long voucherStatus;

    public Long getVoucherStatus() {
		return voucherStatus;
	}

	public void setVoucherStatus(Long voucherStatus) {
		this.voucherStatus = voucherStatus;
	}

	public String getContentCost() {
		return contentCost;
	}

	public void setContentCost(String contentCost) {
		this.contentCost = contentCost;
	}

	public Long getEstimateLoacValue() {
		return estimateLoacValue;
	}

	public void setEstimateLoacValue(Long estimateLoacValue) {
		this.estimateLoacValue = estimateLoacValue;
	}



	public Long getLongTermAssetCostId() {
        return longTermAssetCostId;
    }

    public void setLongTermAssetCostId(Long longTermAssetCostId) {
        this.longTermAssetCostId = longTermAssetCostId;
    }

    public Long getLoacType() {
        return loacType;
    }

    public void setLoacType(Long loacType) {
        this.loacType = loacType;
    }

    public Long getLoacValue() {
        return loacValue;
    }

    public void setLoacValue(Long loacValue) {
        this.loacValue = loacValue;
    }

    public Long getLongTermAssetId() {
        return longTermAssetId;
    }

    public void setLongTermAssetId(Long longTermAssetId) {
        this.longTermAssetId = longTermAssetId;
    }

    public String getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(String voucherList) {
        this.voucherList = voucherList;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
