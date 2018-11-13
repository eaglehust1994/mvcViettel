package com.viettel.asset.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WARE_EXP_NOTE_MER_ENTITY")
public class WareExpNoteMerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String MER_ENTITY_ID = "merEntityId";
        public static final String DELIVERY_NOTE_ID = "deliveryNoteId";
        public static final String ID = "id";
        public static final String ORIGINAL_PRICE = "originalPrice";
        public static final String ORDER_CODE = "orderCode";
        public static final String QUANTITY = "quantity";
        public static final String STATUS_ID = "statusId";
        public static final String QUALITY = "quality";
        public static final String MER_ID = "merId";
        public static final String SERIAL_NUMBER = "serialNumber";

    }

    public interface Constants {

        public static final String TABLE_NAME = "WARE_EXP_NOTE_MER_ENTITY";
    }

    @Column(name = "MER_ENTITY_ID")
    private Long merEntityId;
    @Column(name = "DELIVERY_NOTE_ID")
    private Long deliveryNoteId;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ORIGINAL_PRICE")
    private Long originalPrice;
    @Column(name = "ORDER_CODE")
    private Long orderCode;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "STATUS_ID")
    private Long statusId;
    @Column(name = "QUALITY")
    private Long quality;
    @Column(name = "MER_ID")
    private Long merId;
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    public Long getMerEntityId() {
        return merEntityId;
    }

    public void setMerEntityId(Long merEntityId) {
        this.merEntityId = merEntityId;
    }

    public Long getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(Long deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getQuality() {
        return quality;
    }

    public void setQuality(Long quality) {
        this.quality = quality;
    }

    public Long getMerId() {
        return merId;
    }

    public void setMerId(Long merId) {
        this.merId = merId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
