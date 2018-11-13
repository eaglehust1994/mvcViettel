package com.viettel.asset.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONSTR_MERCHANDISE")
public class ConstrMerchandise implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String ID = "id";
        public static final String QUANTITY = "quantity";
        public static final String IS_ACTIVE = "isActive";
        public static final String MER_ENTITY_ID = "merEntityId";
        public static final String WORK_ITEM_ID = "workItemId";
        public static final String CONSTRUCT_ID = "constructId";
        public static final String ROOT_MER_ENTITY_ID = "rootMerEntityId";
        public static final String REMAIN_COUNT = "remainCount";
        public static final String MER_ID = "merId";
        public static final String UPGRADE_PARENT_ID = "upgradeParentId";
        public static final String NODE_ID = "nodeId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "CONSTR_MERCHANDISE";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "CONSTR_MERCHANDISE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "MER_ENTITY_ID")
    private Long merEntityId;
    @Column(name = "WORK_ITEM_ID")
    private Long workItemId;
    @Column(name = "CONSTRUCT_ID")
    private Long constructId;
    @Column(name = "ROOT_MER_ENTITY_ID")
    private Long rootMerEntityId;
    @Column(name = "REMAIN_COUNT")
    private Long remainCount;
    @Column(name = "MER_ID")
    private Long merId;
    @Column(name = "UPGRADE_PARENT_ID")
    private Long upgradeParentId;
    @Column(name = "NODE_ID")
    private Long nodeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getMerEntityId() {
        return merEntityId;
    }

    public void setMerEntityId(Long merEntityId) {
        this.merEntityId = merEntityId;
    }

    public Long getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(Long workItemId) {
        this.workItemId = workItemId;
    }

    public Long getConstructId() {
        return constructId;
    }

    public void setConstructId(Long constructId) {
        this.constructId = constructId;
    }

    public Long getRootMerEntityId() {
        return rootMerEntityId;
    }

    public void setRootMerEntityId(Long rootMerEntityId) {
        this.rootMerEntityId = rootMerEntityId;
    }

    public Long getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Long remainCount) {
        this.remainCount = remainCount;
    }

    public Long getMerId() {
        return merId;
    }

    public void setMerId(Long merId) {
        this.merId = merId;
    }

    public Long getUpgradeParentId() {
        return upgradeParentId;
    }

    public void setUpgradeParentId(Long upgradeParentId) {
        this.upgradeParentId = upgradeParentId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

}
