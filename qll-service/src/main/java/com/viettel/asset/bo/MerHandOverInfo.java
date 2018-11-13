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
@Table(name = "MER_HAND_OVER_INFO")
public class MerHandOverInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String HAND_OVER_ID = "handOverId";
        public static final String GROUP_ID = "groupId";
        public static final String HAND_OVER_DATE = "handOverDate";
        public static final String DESCRIPTION = "description";
        public static final String CREATED_DATE = "createdDate";
        public static final String CREATOR_ID = "creatorId";
        public static final String RECEIVER_NAME = "receiverName";
        public static final String TYPE = "type";
        public static final String CODE = "code";
        public static final String DELIVERY_NOTE_ID = "deliveryNoteId";
        public static final String CREATE_BY = "createBy";
        public static final String RECEIVER_POSITION = "receiverPosition";
        public static final String DELIVERY_POSITION = "deliveryPosition";
        public static final String DELIVERY_NAME = "deliveryName";
        public static final String HAND_OVER_USE_ID = "handOverUseId";
        public static final String DELIVERY_GROUP_ID = "deliveryGroupId";
        public static final String CONSTRUCTION_ID = "constructionId";
        public static final String IS_TRANFER_TO_EXPLOIT = "isTranferToExploit";
        public static final String HAND_OVER_USE_CODE = "handOverUseCode";
        public static final String IS_ACCEPTED = "isAccepted";
        public static final String ACCEPTOR_ID = "acceptorId";
        public static final String ACCEPTED_DATE = "acceptedDate";
        public static final String FOUNDATION = "foundation";
        public static final String CONTRACT_ID = "contractId";
        public static final String HAND_OVER_REQ_ID = "handOverReqId";
        public static final String FINANCE_NAME = "financeName";
        public static final String FINANCE_POSITION = "financePosition";
        public static final String RESTORE_DATE = "restoreDate";
        public static final String IS_DUMMY = "isDummy";
        public static final String WAREHOUSE_ID = "warehouseId";
        public static final String TRANSFER_GROUP_ID = "transferGroupId";
        public static final String STATUS_MAPPING = "statusMapping";
        public static final String TYPE_DELIVERY_NOTE = "typeDeliveryNote";
        public static final String IS_ACTIVE = "isActive";
        public static final String ASSET_TYPE = "assetType";
        public static final String REASON = "reason";
        public static final String CONFIRM_DATE = "confirmDate";
        public static final String STATUS_CA = "statusCa";
        public static final String COMMENT_CA = "commentCa";
        public static final String DOCUMENT_CA_ID = "documentCaId";
        public static final String LAST_UPDATE = "lastUpdate";
        public static final String IS_SYNC_ERP = "isSyncErp";
        public static final String REASON_ERP = "reasonErp";
        public static final String IS_TO_ASSET = "isToAsset";

    }

    public interface Constants {

        public static final String TABLE_NAME = "MER_HAND_OVER_INFO";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "MER_HAND_OVER_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "HAND_OVER_ID")
    private Long handOverId;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "HAND_OVER_DATE")
    private Date handOverDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    @Column(name = "RECEIVER_NAME")
    private String receiverName;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "CODE")
    private String code;
    @Column(name = "DELIVERY_NOTE_ID")
    private Long deliveryNoteId;
    @Column(name = "CREATE_BY")
    private Long createBy;
    @Column(name = "RECEIVER_POSITION")
    private String receiverPosition;
    @Column(name = "DELIVERY_POSITION")
    private String deliveryPosition;
    @Column(name = "DELIVERY_NAME")
    private String deliveryName;
    @Column(name = "HAND_OVER_USE_ID")
    private Long handOverUseId;
    @Column(name = "DELIVERY_GROUP_ID")
    private Long deliveryGroupId;
    @Column(name = "CONSTRUCTION_ID")
    private Long constructionId;
    @Column(name = "IS_TRANFER_TO_EXPLOIT")
    private Long isTranferToExploit;
    @Column(name = "HAND_OVER_USE_CODE")
    private String handOverUseCode;
    @Column(name = "IS_ACCEPTED")
    private Long isAccepted;
    @Column(name = "ACCEPTOR_ID")
    private Long acceptorId;
    @Column(name = "ACCEPTED_DATE")
    private Date acceptedDate;
    @Column(name = "FOUNDATION")
    private String foundation;
    @Column(name = "CONTRACT_ID")
    private Long contractId;
    @Column(name = "HAND_OVER_REQ_ID")
    private Long handOverReqId;
    @Column(name = "FINANCE_NAME")
    private String financeName;
    @Column(name = "FINANCE_POSITION")
    private String financePosition;
    @Column(name = "RESTORE_DATE")
    private Date restoreDate;
    @Column(name = "IS_DUMMY")
    private Long isDummy;
    @Column(name = "WAREHOUSE_ID")
    private Long warehouseId;
    @Column(name = "TRANSFER_GROUP_ID")
    private Long transferGroupId;
    @Column(name = "STATUS_MAPPING")
    private Long statusMapping;
    @Column(name = "TYPE_DELIVERY_NOTE")
    private Long typeDeliveryNote;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "ASSET_TYPE")
    private Long assetType;
    @Column(name = "REASON")
    private String reason;
    @Column(name = "CONFIRM_DATE")
    private Date confirmDate;
    @Column(name = "STATUS_CA")
    private Long statusCa;
    @Column(name = "COMMENT_CA")
    private String commentCa;
    @Column(name = "DOCUMENT_CA_ID")
    private Long documentCaId;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;
    @Column(name = "IS_SYNC_ERP")
    private Long isSyncErp;
    @Column(name = "REASON_ERP")
    private String reasonErp;
    @Column(name = "IS_TO_ASSET")
    private Long isToAsset;

    public Long getHandOverId() {
        return handOverId;
    }

    public void setHandOverId(Long handOverId) {
        this.handOverId = handOverId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getHandOverDate() {
        return handOverDate;
    }

    public void setHandOverDate(Date handOverDate) {
        this.handOverDate = handOverDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(Long deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getReceiverPosition() {
        return receiverPosition;
    }

    public void setReceiverPosition(String receiverPosition) {
        this.receiverPosition = receiverPosition;
    }

    public String getDeliveryPosition() {
        return deliveryPosition;
    }

    public void setDeliveryPosition(String deliveryPosition) {
        this.deliveryPosition = deliveryPosition;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public Long getHandOverUseId() {
        return handOverUseId;
    }

    public void setHandOverUseId(Long handOverUseId) {
        this.handOverUseId = handOverUseId;
    }

    public Long getDeliveryGroupId() {
        return deliveryGroupId;
    }

    public void setDeliveryGroupId(Long deliveryGroupId) {
        this.deliveryGroupId = deliveryGroupId;
    }

    public Long getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Long constructionId) {
        this.constructionId = constructionId;
    }

    public Long getIsTranferToExploit() {
        return isTranferToExploit;
    }

    public void setIsTranferToExploit(Long isTranferToExploit) {
        this.isTranferToExploit = isTranferToExploit;
    }

    public String getHandOverUseCode() {
        return handOverUseCode;
    }

    public void setHandOverUseCode(String handOverUseCode) {
        this.handOverUseCode = handOverUseCode;
    }

    public Long getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Long isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Long getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(Long acceptorId) {
        this.acceptorId = acceptorId;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getHandOverReqId() {
        return handOverReqId;
    }

    public void setHandOverReqId(Long handOverReqId) {
        this.handOverReqId = handOverReqId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getFinancePosition() {
        return financePosition;
    }

    public void setFinancePosition(String financePosition) {
        this.financePosition = financePosition;
    }

    public Date getRestoreDate() {
        return restoreDate;
    }

    public void setRestoreDate(Date restoreDate) {
        this.restoreDate = restoreDate;
    }

    public Long getIsDummy() {
        return isDummy;
    }

    public void setIsDummy(Long isDummy) {
        this.isDummy = isDummy;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getTransferGroupId() {
        return transferGroupId;
    }

    public void setTransferGroupId(Long transferGroupId) {
        this.transferGroupId = transferGroupId;
    }

    public Long getStatusMapping() {
        return statusMapping;
    }

    public void setStatusMapping(Long statusMapping) {
        this.statusMapping = statusMapping;
    }

    public Long getTypeDeliveryNote() {
        return typeDeliveryNote;
    }

    public void setTypeDeliveryNote(Long typeDeliveryNote) {
        this.typeDeliveryNote = typeDeliveryNote;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getAssetType() {
        return assetType;
    }

    public void setAssetType(Long assetType) {
        this.assetType = assetType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Long getStatusCa() {
        return statusCa;
    }

    public void setStatusCa(Long statusCa) {
        this.statusCa = statusCa;
    }

    public String getCommentCa() {
        return commentCa;
    }

    public void setCommentCa(String commentCa) {
        this.commentCa = commentCa;
    }

    public Long getDocumentCaId() {
        return documentCaId;
    }

    public void setDocumentCaId(Long documentCaId) {
        this.documentCaId = documentCaId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getIsSyncErp() {
        return isSyncErp;
    }

    public void setIsSyncErp(Long isSyncErp) {
        this.isSyncErp = isSyncErp;
    }

    public String getReasonErp() {
        return reasonErp;
    }

    public void setReasonErp(String reasonErp) {
        this.reasonErp = reasonErp;
    }

    public Long getIsToAsset() {
        return isToAsset;
    }

    public void setIsToAsset(Long isToAsset) {
        this.isToAsset = isToAsset;
    }

}
