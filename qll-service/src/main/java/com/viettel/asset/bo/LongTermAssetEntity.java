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
@Table(name = "LONG_TERM_ASSET_ENTITIES")
public class LongTermAssetEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String LONG_TERM_ASSET_ENTITY_ID = "longTermAssetEntityId";
        public static final String MER_ENTITY_ID = "merEntityId";
        public static final String QUANTITY = "quantity";
        public static final String ORIGINAL_PRICE = "originalPrice";
        public static final String CREATED_DATE_ASSET = "createdDateAsset";
        public static final String LONG_TERM_ASSET_ID = "longTermAssetId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "LONG_TERM_ASSET_ENTITYS";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LONG_TERM_ASSET_ENTITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LONG_TERM_ASSET_ENTITY_ID")
    private Long longTermAssetEntityId;
    @Column(name = "MER_ENTITY_ID")
    private Long merEntityId;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "ORIGINAL_PRICE")
    private Long originalPrice;
    @Column(name = "CREATED_DATE_ASSET")
    private Date createdDateAsset;
    @Column(name = "LONG_TERM_ASSET_ID")
    private Long longTermAssetId;
    
    
    @Column(name = "UPGRADE_STATUS")
    private Long upgradeStatus;
    
    @Column(name = "HAND_OVER_UPGRADE_ID")
    private Long handOverUpgradeId;

    public Long getHandOverUpgradeId() {
		return handOverUpgradeId;
	}

	public void setHandOverUpgradeId(Long handOverUpgradeId) {
		this.handOverUpgradeId = handOverUpgradeId;
	}

	public Long getUpgradeStatus() {
		return upgradeStatus;
	}

	public void setUpgradeStatus(Long upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}

	public Long getLongTermAssetEntityId() {
        return longTermAssetEntityId;
    }

    public void setLongTermAssetEntityId(Long longTermAssetEntityId) {
        this.longTermAssetEntityId = longTermAssetEntityId;
    }

    public Long getMerEntityId() {
        return merEntityId;
    }

    public void setMerEntityId(Long merEntityId) {
        this.merEntityId = merEntityId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Date getCreatedDateAsset() {
        return createdDateAsset;
    }

    public void setCreatedDateAsset(Date createdDateAsset) {
        this.createdDateAsset = createdDateAsset;
    }

    public Long getLongTermAssetId() {
        return longTermAssetId;
    }

    public void setLongTermAssetId(Long longTermAssetId) {
        this.longTermAssetId = longTermAssetId;
    }

}
