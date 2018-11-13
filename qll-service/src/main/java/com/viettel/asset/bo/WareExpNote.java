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
@Table(name = "WARE_EXP_NOTE")
public class WareExpNote implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String DELIVERY_NOTE_ID = "deliveryNoteId";
        public static final String SOURCE_WH_ID = "sourceWhId";
        public static final String DEST_WH_ID = "destWhId";
        public static final String RECV_GROUP_ID = "recvGroupId";
        public static final String CREATED_DATE = "createdDate";
        public static final String CREATOR_ID = "creatorId";
        public static final String EXP_CMD_ID = "expCmdId";
        public static final String DELIVERY_GROUP_ID = "deliveryGroupId";
        public static final String RECV_PERSON_ID = "recvPersonId";
        public static final String CODE = "code";
        public static final String STATUS = "status";
        public static final String CONFIRM_STATUS = "confirmStatus";
        public static final String CONSTRUCTION_ID = "constructionId";
        public static final String ACTUAL_EXP_DATE = "actualExpDate";
        public static final String WORK_ITEM_ID = "workItemId";
        public static final String RECEIVER_NAME = "receiverName";
        public static final String IS_HAND_OVER_ALL = "isHandOverAll";
        public static final String ACTUAL_EXP_BY = "actualExpBy";
        public static final String HAND_OVER_BY = "handOverBy";
        public static final String HAND_OVER_INFO_ID = "handOverInfoId";
        public static final String EXP_NOTE_TYPE = "expNoteType";
        public static final String RECV_GROUP_NAME = "recvGroupName";
        public static final String IS_UPDATE_STATION_ALL = "isUpdateStationAll";
        public static final String CONSTRUCT_EXP_TYPE = "constructExpType";
        public static final String IS_RETRIEVED = "isRetrieved";
        public static final String APP_ACTUAL_DATE = "appActualDate";
        public static final String DELETABLE = "deletable";
        public static final String MER_EXP_TYPE = "merExpType";
        public static final String PARTNER_ID = "partnerId";
        public static final String DEST_TYPE = "destType";
        public static final String REGULAR_STATUS = "regularStatus";
        public static final String RECEIVE_CONFIRM = "receiveConfirm";
        public static final String RECEIVE_CONFIRM_DATE = "receiveConfirmDate";
        public static final String REAL_RECEIVE_DATE = "realReceiveDate";
        public static final String IS_SYNC_INVENTORY = "isSyncInventory";
        public static final String USER_ID_DEL = "userIdDel";
        public static final String DEL_CONFIRM_DATE = "delConfirmDate";
        public static final String IS_CA = "isCa";
        public static final String DOCUMENT_CA_ID = "documentCaId";
        public static final String RECEIVER_CODE = "receiverCode";
        public static final String SMS_STATUS = "smsStatus";
        public static final String LOGISTIC_ACTUAL_PER = "logisticActualPer";

    }

    public interface Constants {

        public static final String TABLE_NAME = "WARE_EXP_NOTE";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "WARE_EXP_NOTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "DELIVERY_NOTE_ID")
    private Long deliveryNoteId;
    @Column(name = "SOURCE_WH_ID")
    private Long sourceWhId;
    @Column(name = "DEST_WH_ID")
    private Long destWhId;
    @Column(name = "RECV_GROUP_ID")
    private Long recvGroupId;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    @Column(name = "EXP_CMD_ID")
    private Long expCmdId;
    @Column(name = "DELIVERY_GROUP_ID")
    private Long deliveryGroupId;
    @Column(name = "RECV_PERSON_ID")
    private Long recvPersonId;
    @Column(name = "CODE")
    private String code;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CONFIRM_STATUS")
    private Long confirmStatus;
    @Column(name = "CONSTRUCTION_ID")
    private Long constructionId;
    @Column(name = "ACTUAL_EXP_DATE")
    private Date actualExpDate;
    @Column(name = "WORK_ITEM_ID")
    private Long workItemId;
    @Column(name = "RECEIVER_NAME")
    private String receiverName;
    @Column(name = "IS_HAND_OVER_ALL")
    private Long isHandOverAll;
    @Column(name = "ACTUAL_EXP_BY")
    private Long actualExpBy;
    @Column(name = "HAND_OVER_BY")
    private Long handOverBy;
    @Column(name = "HAND_OVER_INFO_ID")
    private Long handOverInfoId;
    @Column(name = "EXP_NOTE_TYPE")
    private Long expNoteType;
    @Column(name = "RECV_GROUP_NAME")
    private String recvGroupName;
    @Column(name = "IS_UPDATE_STATION_ALL")
    private Long isUpdateStationAll;
    @Column(name = "CONSTRUCT_EXP_TYPE")
    private Long constructExpType;
    @Column(name = "IS_RETRIEVED")
    private Long isRetrieved;
    @Column(name = "APP_ACTUAL_DATE")
    private Date appActualDate;
    @Column(name = "DELETABLE")
    private Long deletable;
    @Column(name = "MER_EXP_TYPE")
    private Long merExpType;
    @Column(name = "PARTNER_ID")
    private Long partnerId;
    @Column(name = "DEST_TYPE")
    private Long destType;
    @Column(name = "REGULAR_STATUS")
    private Long regularStatus;
    @Column(name = "RECEIVE_CONFIRM")
    private Long receiveConfirm;
    @Column(name = "RECEIVE_CONFIRM_DATE")
    private Date receiveConfirmDate;
    @Column(name = "REAL_RECEIVE_DATE")
    private Date realReceiveDate;
    @Column(name = "IS_SYNC_INVENTORY")
    private Long isSyncInventory;
    @Column(name = "USER_ID_DEL")
    private Long userIdDel;
    @Column(name = "DEL_CONFIRM_DATE")
    private Date delConfirmDate;
    @Column(name = "IS_CA")
    private Long isCa;
    @Column(name = "DOCUMENT_CA_ID")
    private Long documentCaId;
    @Column(name = "RECEIVER_CODE")
    private String receiverCode;
    @Column(name = "SMS_STATUS")
    private Long smsStatus;
    @Column(name = "LOGISTIC_ACTUAL_PER")
    private String logisticActualPer;

    public Long getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(Long deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    public Long getSourceWhId() {
        return sourceWhId;
    }

    public void setSourceWhId(Long sourceWhId) {
        this.sourceWhId = sourceWhId;
    }

    public Long getDestWhId() {
        return destWhId;
    }

    public void setDestWhId(Long destWhId) {
        this.destWhId = destWhId;
    }

    public Long getRecvGroupId() {
        return recvGroupId;
    }

    public void setRecvGroupId(Long recvGroupId) {
        this.recvGroupId = recvGroupId;
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

    public Long getExpCmdId() {
        return expCmdId;
    }

    public void setExpCmdId(Long expCmdId) {
        this.expCmdId = expCmdId;
    }

    public Long getDeliveryGroupId() {
        return deliveryGroupId;
    }

    public void setDeliveryGroupId(Long deliveryGroupId) {
        this.deliveryGroupId = deliveryGroupId;
    }

    public Long getRecvPersonId() {
        return recvPersonId;
    }

    public void setRecvPersonId(Long recvPersonId) {
        this.recvPersonId = recvPersonId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Long confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Long getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Long constructionId) {
        this.constructionId = constructionId;
    }

    public Date getActualExpDate() {
        return actualExpDate;
    }

    public void setActualExpDate(Date actualExpDate) {
        this.actualExpDate = actualExpDate;
    }

    public Long getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(Long workItemId) {
        this.workItemId = workItemId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Long getIsHandOverAll() {
        return isHandOverAll;
    }

    public void setIsHandOverAll(Long isHandOverAll) {
        this.isHandOverAll = isHandOverAll;
    }

    public Long getActualExpBy() {
        return actualExpBy;
    }

    public void setActualExpBy(Long actualExpBy) {
        this.actualExpBy = actualExpBy;
    }

    public Long getHandOverBy() {
        return handOverBy;
    }

    public void setHandOverBy(Long handOverBy) {
        this.handOverBy = handOverBy;
    }

    public Long getHandOverInfoId() {
        return handOverInfoId;
    }

    public void setHandOverInfoId(Long handOverInfoId) {
        this.handOverInfoId = handOverInfoId;
    }

    public Long getExpNoteType() {
        return expNoteType;
    }

    public void setExpNoteType(Long expNoteType) {
        this.expNoteType = expNoteType;
    }

    public String getRecvGroupName() {
        return recvGroupName;
    }

    public void setRecvGroupName(String recvGroupName) {
        this.recvGroupName = recvGroupName;
    }

    public Long getIsUpdateStationAll() {
        return isUpdateStationAll;
    }

    public void setIsUpdateStationAll(Long isUpdateStationAll) {
        this.isUpdateStationAll = isUpdateStationAll;
    }

    public Long getConstructExpType() {
        return constructExpType;
    }

    public void setConstructExpType(Long constructExpType) {
        this.constructExpType = constructExpType;
    }

    public Long getIsRetrieved() {
        return isRetrieved;
    }

    public void setIsRetrieved(Long isRetrieved) {
        this.isRetrieved = isRetrieved;
    }

    public Date getAppActualDate() {
        return appActualDate;
    }

    public void setAppActualDate(Date appActualDate) {
        this.appActualDate = appActualDate;
    }

    public Long getDeletable() {
        return deletable;
    }

    public void setDeletable(Long deletable) {
        this.deletable = deletable;
    }

    public Long getMerExpType() {
        return merExpType;
    }

    public void setMerExpType(Long merExpType) {
        this.merExpType = merExpType;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getDestType() {
        return destType;
    }

    public void setDestType(Long destType) {
        this.destType = destType;
    }

    public Long getRegularStatus() {
        return regularStatus;
    }

    public void setRegularStatus(Long regularStatus) {
        this.regularStatus = regularStatus;
    }

    public Long getReceiveConfirm() {
        return receiveConfirm;
    }

    public void setReceiveConfirm(Long receiveConfirm) {
        this.receiveConfirm = receiveConfirm;
    }

    public Date getReceiveConfirmDate() {
        return receiveConfirmDate;
    }

    public void setReceiveConfirmDate(Date receiveConfirmDate) {
        this.receiveConfirmDate = receiveConfirmDate;
    }

    public Date getRealReceiveDate() {
        return realReceiveDate;
    }

    public void setRealReceiveDate(Date realReceiveDate) {
        this.realReceiveDate = realReceiveDate;
    }

    public Long getIsSyncInventory() {
        return isSyncInventory;
    }

    public void setIsSyncInventory(Long isSyncInventory) {
        this.isSyncInventory = isSyncInventory;
    }

    public Long getUserIdDel() {
        return userIdDel;
    }

    public void setUserIdDel(Long userIdDel) {
        this.userIdDel = userIdDel;
    }

    public Date getDelConfirmDate() {
        return delConfirmDate;
    }

    public void setDelConfirmDate(Date delConfirmDate) {
        this.delConfirmDate = delConfirmDate;
    }

    public Long getIsCa() {
        return isCa;
    }

    public void setIsCa(Long isCa) {
        this.isCa = isCa;
    }

    public Long getDocumentCaId() {
        return documentCaId;
    }

    public void setDocumentCaId(Long documentCaId) {
        this.documentCaId = documentCaId;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public Long getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Long smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getLogisticActualPer() {
        return logisticActualPer;
    }

    public void setLogisticActualPer(String logisticActualPer) {
        this.logisticActualPer = logisticActualPer;
    }

}
