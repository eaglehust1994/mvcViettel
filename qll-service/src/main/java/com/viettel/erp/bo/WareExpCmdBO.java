/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.WareExpCmdDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "WARE_EXP_CMD")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class WareExpCmdBO extends BaseFWModelImpl {
     
private java.lang.Long expCmdId;
private java.lang.String expCmdCode;
private java.lang.Long expReqId;
private java.lang.Long status;
private java.lang.Long recvGroupId;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private java.lang.Long receiptNoteId;
private java.lang.Long expCauseId;
private java.lang.Long expType;
private java.lang.Long srcWarehouseId;
private java.lang.Long destWarehouseId;
private java.lang.Long constructionId;
private java.lang.Long workItemId;
private java.lang.Long creGroupId;
private java.lang.Long creDeliveryNoteBy;
private java.lang.String tplName;
private java.lang.String expNoteCode;
private java.lang.String receiveName;
private java.lang.Long approveStatus;
private java.util.Date noteCreatedDate;
private java.lang.Long constructExpType;
private java.lang.String description;
private java.lang.Long partnerId;
private java.lang.Long destType;
private java.lang.String disapproveNoteCause;
private java.lang.Long merExpType;
private java.lang.String oldExpNoteCode;
private java.lang.Long creNoteGroupId;
private java.lang.Long delNoteUserId;
private java.lang.Long regularStatus;
private java.lang.String deleteDisapproveNoteCause;
private java.lang.Long planSwapId;
private java.lang.Long isReturn;
private java.lang.Long isCaNote;
private java.lang.Long documentCaNoteId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.String commemtCa;
private java.lang.String logisticNoteCode;
private java.lang.String reasonLogistic;
private java.util.Date logisticDate;
private java.lang.Long statusLogistic;
private java.lang.String logisticPerson;
private java.util.Date lastUpdate;
private java.lang.Long isSyncErp;
private java.lang.String reasonErp;

 public WareExpCmdBO() {
        setColId("expCmdId");
        setColName("expCmdId");
        setUniqueColumn(new String[]{"expCmdId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WARE_EXP_CMD_SEQ")
            }
    )
@Column(name = "EXP_CMD_ID", length = 22)
public java.lang.Long getExpCmdId(){
return expCmdId;
}
public void setExpCmdId(java.lang.Long expCmdId)
{
this.expCmdId = expCmdId;
}
@Column(name = "EXP_CMD_CODE", length = 100)
public java.lang.String getExpCmdCode(){
return expCmdCode;
}
public void setExpCmdCode(java.lang.String expCmdCode)
{
this.expCmdCode = expCmdCode;
}
@Column(name = "EXP_REQ_ID", length = 22)
public java.lang.Long getExpReqId(){
return expReqId;
}
public void setExpReqId(java.lang.Long expReqId)
{
this.expReqId = expReqId;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "RECV_GROUP_ID", length = 22)
public java.lang.Long getRecvGroupId(){
return recvGroupId;
}
public void setRecvGroupId(java.lang.Long recvGroupId)
{
this.recvGroupId = recvGroupId;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "RECEIPT_NOTE_ID", length = 22)
public java.lang.Long getReceiptNoteId(){
return receiptNoteId;
}
public void setReceiptNoteId(java.lang.Long receiptNoteId)
{
this.receiptNoteId = receiptNoteId;
}
@Column(name = "EXP_CAUSE_ID", length = 22)
public java.lang.Long getExpCauseId(){
return expCauseId;
}
public void setExpCauseId(java.lang.Long expCauseId)
{
this.expCauseId = expCauseId;
}
@Column(name = "EXP_TYPE", length = 22)
public java.lang.Long getExpType(){
return expType;
}
public void setExpType(java.lang.Long expType)
{
this.expType = expType;
}
@Column(name = "SRC_WAREHOUSE_ID", length = 22)
public java.lang.Long getSrcWarehouseId(){
return srcWarehouseId;
}
public void setSrcWarehouseId(java.lang.Long srcWarehouseId)
{
this.srcWarehouseId = srcWarehouseId;
}
@Column(name = "DEST_WAREHOUSE_ID", length = 22)
public java.lang.Long getDestWarehouseId(){
return destWarehouseId;
}
public void setDestWarehouseId(java.lang.Long destWarehouseId)
{
this.destWarehouseId = destWarehouseId;
}
@Column(name = "CONSTRUCTION_ID", length = 22)
public java.lang.Long getConstructionId(){
return constructionId;
}
public void setConstructionId(java.lang.Long constructionId)
{
this.constructionId = constructionId;
}
@Column(name = "WORK_ITEM_ID", length = 22)
public java.lang.Long getWorkItemId(){
return workItemId;
}
public void setWorkItemId(java.lang.Long workItemId)
{
this.workItemId = workItemId;
}
@Column(name = "CRE_GROUP_ID", length = 22)
public java.lang.Long getCreGroupId(){
return creGroupId;
}
public void setCreGroupId(java.lang.Long creGroupId)
{
this.creGroupId = creGroupId;
}
@Column(name = "CRE_DELIVERY_NOTE_BY", length = 22)
public java.lang.Long getCreDeliveryNoteBy(){
return creDeliveryNoteBy;
}
public void setCreDeliveryNoteBy(java.lang.Long creDeliveryNoteBy)
{
this.creDeliveryNoteBy = creDeliveryNoteBy;
}
@Column(name = "TPL_NAME", length = 200)
public java.lang.String getTplName(){
return tplName;
}
public void setTplName(java.lang.String tplName)
{
this.tplName = tplName;
}
@Column(name = "EXP_NOTE_CODE", length = 100)
public java.lang.String getExpNoteCode(){
return expNoteCode;
}
public void setExpNoteCode(java.lang.String expNoteCode)
{
this.expNoteCode = expNoteCode;
}
@Column(name = "RECEIVE_NAME", length = 200)
public java.lang.String getReceiveName(){
return receiveName;
}
public void setReceiveName(java.lang.String receiveName)
{
this.receiveName = receiveName;
}
@Column(name = "APPROVE_STATUS", length = 22)
public java.lang.Long getApproveStatus(){
return approveStatus;
}
public void setApproveStatus(java.lang.Long approveStatus)
{
this.approveStatus = approveStatus;
}
@Column(name = "NOTE_CREATED_DATE", length = 7)
public java.util.Date getNoteCreatedDate(){
return noteCreatedDate;
}
public void setNoteCreatedDate(java.util.Date noteCreatedDate)
{
this.noteCreatedDate = noteCreatedDate;
}
@Column(name = "CONSTRUCT_EXP_TYPE", length = 22)
public java.lang.Long getConstructExpType(){
return constructExpType;
}
public void setConstructExpType(java.lang.Long constructExpType)
{
this.constructExpType = constructExpType;
}
@Column(name = "DESCRIPTION", length = 1000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "DEST_TYPE", length = 22)
public java.lang.Long getDestType(){
return destType;
}
public void setDestType(java.lang.Long destType)
{
this.destType = destType;
}
@Column(name = "DISAPPROVE_NOTE_CAUSE", length = 4000)
public java.lang.String getDisapproveNoteCause(){
return disapproveNoteCause;
}
public void setDisapproveNoteCause(java.lang.String disapproveNoteCause)
{
this.disapproveNoteCause = disapproveNoteCause;
}
@Column(name = "MER_EXP_TYPE", length = 22)
public java.lang.Long getMerExpType(){
return merExpType;
}
public void setMerExpType(java.lang.Long merExpType)
{
this.merExpType = merExpType;
}
@Column(name = "OLD_EXP_NOTE_CODE", length = 100)
public java.lang.String getOldExpNoteCode(){
return oldExpNoteCode;
}
public void setOldExpNoteCode(java.lang.String oldExpNoteCode)
{
this.oldExpNoteCode = oldExpNoteCode;
}
@Column(name = "CRE_NOTE_GROUP_ID", length = 22)
public java.lang.Long getCreNoteGroupId(){
return creNoteGroupId;
}
public void setCreNoteGroupId(java.lang.Long creNoteGroupId)
{
this.creNoteGroupId = creNoteGroupId;
}
@Column(name = "DEL_NOTE_USER_ID", length = 22)
public java.lang.Long getDelNoteUserId(){
return delNoteUserId;
}
public void setDelNoteUserId(java.lang.Long delNoteUserId)
{
this.delNoteUserId = delNoteUserId;
}
@Column(name = "REGULAR_STATUS", length = 22)
public java.lang.Long getRegularStatus(){
return regularStatus;
}
public void setRegularStatus(java.lang.Long regularStatus)
{
this.regularStatus = regularStatus;
}
@Column(name = "DELETE_DISAPPROVE_NOTE_CAUSE", length = 2000)
public java.lang.String getDeleteDisapproveNoteCause(){
return deleteDisapproveNoteCause;
}
public void setDeleteDisapproveNoteCause(java.lang.String deleteDisapproveNoteCause)
{
this.deleteDisapproveNoteCause = deleteDisapproveNoteCause;
}
@Column(name = "PLAN_SWAP_ID", length = 22)
public java.lang.Long getPlanSwapId(){
return planSwapId;
}
public void setPlanSwapId(java.lang.Long planSwapId)
{
this.planSwapId = planSwapId;
}
@Column(name = "IS_RETURN", length = 22)
public java.lang.Long getIsReturn(){
return isReturn;
}
public void setIsReturn(java.lang.Long isReturn)
{
this.isReturn = isReturn;
}
@Column(name = "IS_CA_NOTE", length = 22)
public java.lang.Long getIsCaNote(){
return isCaNote;
}
public void setIsCaNote(java.lang.Long isCaNote)
{
this.isCaNote = isCaNote;
}
@Column(name = "DOCUMENT_CA_NOTE_ID", length = 22)
public java.lang.Long getDocumentCaNoteId(){
return documentCaNoteId;
}
public void setDocumentCaNoteId(java.lang.Long documentCaNoteId)
{
this.documentCaNoteId = documentCaNoteId;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
@Column(name = "COMMEMT_CA", length = 1000)
public java.lang.String getCommemtCa(){
return commemtCa;
}
public void setCommemtCa(java.lang.String commemtCa)
{
this.commemtCa = commemtCa;
}
@Column(name = "LOGISTIC_NOTE_CODE", length = 150)
public java.lang.String getLogisticNoteCode(){
return logisticNoteCode;
}
public void setLogisticNoteCode(java.lang.String logisticNoteCode)
{
this.logisticNoteCode = logisticNoteCode;
}
@Column(name = "REASON_LOGISTIC", length = 1000)
public java.lang.String getReasonLogistic(){
return reasonLogistic;
}
public void setReasonLogistic(java.lang.String reasonLogistic)
{
this.reasonLogistic = reasonLogistic;
}
@Column(name = "LOGISTIC_DATE", length = 7)
public java.util.Date getLogisticDate(){
return logisticDate;
}
public void setLogisticDate(java.util.Date logisticDate)
{
this.logisticDate = logisticDate;
}
@Column(name = "STATUS_LOGISTIC", length = 22)
public java.lang.Long getStatusLogistic(){
return statusLogistic;
}
public void setStatusLogistic(java.lang.Long statusLogistic)
{
this.statusLogistic = statusLogistic;
}
@Column(name = "LOGISTIC_PERSON", length = 400)
public java.lang.String getLogisticPerson(){
return logisticPerson;
}
public void setLogisticPerson(java.lang.String logisticPerson)
{
this.logisticPerson = logisticPerson;
}
@Column(name = "LAST_UPDATE", length = 7)
public java.util.Date getLastUpdate(){
return lastUpdate;
}
public void setLastUpdate(java.util.Date lastUpdate)
{
this.lastUpdate = lastUpdate;
}
@Column(name = "IS_SYNC_ERP", length = 22)
public java.lang.Long getIsSyncErp(){
return isSyncErp;
}
public void setIsSyncErp(java.lang.Long isSyncErp)
{
this.isSyncErp = isSyncErp;
}
@Column(name = "REASON_ERP", length = 2000)
public java.lang.String getReasonErp(){
return reasonErp;
}
public void setReasonErp(java.lang.String reasonErp)
{
this.reasonErp = reasonErp;
}
   

    @Override
    public WareExpCmdDTO toDTO() {
        WareExpCmdDTO wareExpCmdDTO = new WareExpCmdDTO();
        //set cac gia tri 
        wareExpCmdDTO.setExpCmdId(this.expCmdId);
        wareExpCmdDTO.setExpCmdCode(this.expCmdCode);
        wareExpCmdDTO.setExpReqId(this.expReqId);
        wareExpCmdDTO.setStatus(this.status);
        wareExpCmdDTO.setRecvGroupId(this.recvGroupId);
        wareExpCmdDTO.setCreatorId(this.creatorId);
        wareExpCmdDTO.setCreatedDate(this.createdDate);
        wareExpCmdDTO.setReceiptNoteId(this.receiptNoteId);
        wareExpCmdDTO.setExpCauseId(this.expCauseId);
        wareExpCmdDTO.setExpType(this.expType);
        wareExpCmdDTO.setSrcWarehouseId(this.srcWarehouseId);
        wareExpCmdDTO.setDestWarehouseId(this.destWarehouseId);
        wareExpCmdDTO.setConstructionId(this.constructionId);
        wareExpCmdDTO.setWorkItemId(this.workItemId);
        wareExpCmdDTO.setCreGroupId(this.creGroupId);
        wareExpCmdDTO.setCreDeliveryNoteBy(this.creDeliveryNoteBy);
        wareExpCmdDTO.setTplName(this.tplName);
        wareExpCmdDTO.setExpNoteCode(this.expNoteCode);
        wareExpCmdDTO.setReceiveName(this.receiveName);
        wareExpCmdDTO.setApproveStatus(this.approveStatus);
        wareExpCmdDTO.setNoteCreatedDate(this.noteCreatedDate);
        wareExpCmdDTO.setConstructExpType(this.constructExpType);
        wareExpCmdDTO.setDescription(this.description);
        wareExpCmdDTO.setPartnerId(this.partnerId);
        wareExpCmdDTO.setDestType(this.destType);
        wareExpCmdDTO.setDisapproveNoteCause(this.disapproveNoteCause);
        wareExpCmdDTO.setMerExpType(this.merExpType);
        wareExpCmdDTO.setOldExpNoteCode(this.oldExpNoteCode);
        wareExpCmdDTO.setCreNoteGroupId(this.creNoteGroupId);
        wareExpCmdDTO.setDelNoteUserId(this.delNoteUserId);
        wareExpCmdDTO.setRegularStatus(this.regularStatus);
        wareExpCmdDTO.setDeleteDisapproveNoteCause(this.deleteDisapproveNoteCause);
        wareExpCmdDTO.setPlanSwapId(this.planSwapId);
        wareExpCmdDTO.setIsReturn(this.isReturn);
        wareExpCmdDTO.setIsCaNote(this.isCaNote);
        wareExpCmdDTO.setDocumentCaNoteId(this.documentCaNoteId);
        wareExpCmdDTO.setStatusCa(this.statusCa);
        wareExpCmdDTO.setDocumentCaId(this.documentCaId);
        wareExpCmdDTO.setCommemtCa(this.commemtCa);
        wareExpCmdDTO.setLogisticNoteCode(this.logisticNoteCode);
        wareExpCmdDTO.setReasonLogistic(this.reasonLogistic);
        wareExpCmdDTO.setLogisticDate(this.logisticDate);
        wareExpCmdDTO.setStatusLogistic(this.statusLogistic);
        wareExpCmdDTO.setLogisticPerson(this.logisticPerson);
        wareExpCmdDTO.setLastUpdate(this.lastUpdate);
        wareExpCmdDTO.setIsSyncErp(this.isSyncErp);
        wareExpCmdDTO.setReasonErp(this.reasonErp);
        return wareExpCmdDTO;
    }
}
