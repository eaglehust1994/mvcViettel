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
@Table(name = "MER_HAND_OVER_ENTITY")
public class MerHandOverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String MER_HAND_OVER_ENTITY_ID = "merHandOverEntityId";
        public static final String HAND_OVER_ID = "handOverId";
        public static final String STATUS_ID = "statusId";
        public static final String STATION_ID = "stationId";
        public static final String COUNT = "count";
        public static final String REMAIN = "remain";
        public static final String MER_ENTITY_ID = "merEntityId";
        public static final String EXPIRED_WARRANTY_DATE = "expiredWarrantyDate";
        public static final String WORK_ITEM_ID = "workItemId";
        public static final String NODE_ID = "nodeId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "MER_HAND_OVER_ENTITY";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "MER_HAND_OVER_ENTITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "MER_HAND_OVER_ENTITY_ID")
    private Long merHandOverEntityId;
    @Column(name = "HAND_OVER_ID")
    private Long handOverId;
    @Column(name = "STATUS_ID")
    private Long statusId;
    @Column(name = "STATION_ID")
    private Long stationId;
    @Column(name = "COUNT")
    private Double count;
    @Column(name = "REMAIN")
    private Double remain;
    @Column(name = "MER_ENTITY_ID")
    private Long merEntityId;
    @Column(name = "EXPIRED_WARRANTY_DATE")
    private Date expiredWarrantyDate;
    @Column(name = "WORK_ITEM_ID")
    private Long workItemId;
    @Column(name = "NODE_ID")
    private Long nodeId;

    public Long getMerHandOverEntityId() {
        return merHandOverEntityId;
    }

    public void setMerHandOverEntityId(Long merHandOverEntityId) {
        this.merHandOverEntityId = merHandOverEntityId;
    }

    public Long getHandOverId() {
        return handOverId;
    }

    public void setHandOverId(Long handOverId) {
        this.handOverId = handOverId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public Long getMerEntityId() {
        return merEntityId;
    }

    public void setMerEntityId(Long merEntityId) {
        this.merEntityId = merEntityId;
    }

    public Date getExpiredWarrantyDate() {
        return expiredWarrantyDate;
    }

    public void setExpiredWarrantyDate(Date expiredWarrantyDate) {
        this.expiredWarrantyDate = expiredWarrantyDate;
    }

    public Long getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(Long workItemId) {
        this.workItemId = workItemId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

}
