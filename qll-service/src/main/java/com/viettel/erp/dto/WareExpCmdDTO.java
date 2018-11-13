/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.WareExpCmdBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "WARE_EXP_CMDBO")
public class WareExpCmdDTO extends BaseFWDTOImpl<WareExpCmdBO> {

private java.lang.Long expCmdId;
private java.lang.String expCmdCode;
private java.lang.Long expReqId;
private java.lang.Long status;
private java.lang.Long recvGroupId;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private String createdDateStr;
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
private java.util.Date actualExpDate;
private String actualExpDateStr;
private java.lang.Long deliveryNoteId;
private java.lang.String serialNumber;
private java.lang.String code;

    public java.lang.String getCode() {
	return code;
}

public void setCode(java.lang.String code) {
	this.code = code;
}

	public java.lang.String getSerialNumber() {
	return serialNumber;
}

public void setSerialNumber(java.lang.String serialNumber) {
	this.serialNumber = serialNumber;
}

	public java.lang.Long getDeliveryNoteId() {
	return deliveryNoteId;
}

public void setDeliveryNoteId(java.lang.Long deliveryNoteId) {
	this.deliveryNoteId = deliveryNoteId;
}

	public java.util.Date getActualExpDate() {
	return actualExpDate;
}

public void setActualExpDate(java.util.Date actualExpDate) {
	this.actualExpDate = actualExpDate;
}

	@Override
    public WareExpCmdBO toModel() {
        WareExpCmdBO wareExpCmdBO = new WareExpCmdBO();
        wareExpCmdBO.setExpCmdId(this.expCmdId);
        wareExpCmdBO.setExpCmdCode(this.expCmdCode);
        wareExpCmdBO.setExpReqId(this.expReqId);
        wareExpCmdBO.setStatus(this.status);
        wareExpCmdBO.setRecvGroupId(this.recvGroupId);
        wareExpCmdBO.setCreatorId(this.creatorId);
        wareExpCmdBO.setCreatedDate(this.createdDate);
        wareExpCmdBO.setReceiptNoteId(this.receiptNoteId);
        wareExpCmdBO.setExpCauseId(this.expCauseId);
        wareExpCmdBO.setExpType(this.expType);
        wareExpCmdBO.setSrcWarehouseId(this.srcWarehouseId);
        wareExpCmdBO.setDestWarehouseId(this.destWarehouseId);
        wareExpCmdBO.setConstructionId(this.constructionId);
        wareExpCmdBO.setWorkItemId(this.workItemId);
        wareExpCmdBO.setCreGroupId(this.creGroupId);
        wareExpCmdBO.setCreDeliveryNoteBy(this.creDeliveryNoteBy);
        wareExpCmdBO.setTplName(this.tplName);
        wareExpCmdBO.setExpNoteCode(this.expNoteCode);
        wareExpCmdBO.setReceiveName(this.receiveName);
        wareExpCmdBO.setApproveStatus(this.approveStatus);
        wareExpCmdBO.setNoteCreatedDate(this.noteCreatedDate);
        wareExpCmdBO.setConstructExpType(this.constructExpType);
        wareExpCmdBO.setDescription(this.description);
        wareExpCmdBO.setPartnerId(this.partnerId);
        wareExpCmdBO.setDestType(this.destType);
        wareExpCmdBO.setDisapproveNoteCause(this.disapproveNoteCause);
        wareExpCmdBO.setMerExpType(this.merExpType);
        wareExpCmdBO.setOldExpNoteCode(this.oldExpNoteCode);
        wareExpCmdBO.setCreNoteGroupId(this.creNoteGroupId);
        wareExpCmdBO.setDelNoteUserId(this.delNoteUserId);
        wareExpCmdBO.setRegularStatus(this.regularStatus);
        wareExpCmdBO.setDeleteDisapproveNoteCause(this.deleteDisapproveNoteCause);
        wareExpCmdBO.setPlanSwapId(this.planSwapId);
        wareExpCmdBO.setIsReturn(this.isReturn);
        wareExpCmdBO.setIsCaNote(this.isCaNote);
        wareExpCmdBO.setDocumentCaNoteId(this.documentCaNoteId);
        wareExpCmdBO.setStatusCa(this.statusCa);
        wareExpCmdBO.setDocumentCaId(this.documentCaId);
        wareExpCmdBO.setCommemtCa(this.commemtCa);
        wareExpCmdBO.setLogisticNoteCode(this.logisticNoteCode);
        wareExpCmdBO.setReasonLogistic(this.reasonLogistic);
        wareExpCmdBO.setLogisticDate(this.logisticDate);
        wareExpCmdBO.setStatusLogistic(this.statusLogistic);
        wareExpCmdBO.setLogisticPerson(this.logisticPerson);
        wareExpCmdBO.setLastUpdate(this.lastUpdate);
        wareExpCmdBO.setIsSyncErp(this.isSyncErp);
        wareExpCmdBO.setReasonErp(this.reasonErp);
        return wareExpCmdBO;
    }

    @Override
     public Long getFWModelId() {
        return expCmdId;
    }
   
    @Override
    public String catchName() {
        return getExpCmdId().toString();
    }
    public java.lang.Long getExpCmdId(){
    return expCmdId;
    }
    public void setExpCmdId(java.lang.Long expCmdId)
    {
    this.expCmdId = expCmdId;
    }
    
    public java.lang.String getExpCmdCode(){
    return expCmdCode;
    }
    public void setExpCmdCode(java.lang.String expCmdCode)
    {
    this.expCmdCode = expCmdCode;
    }
    
    public java.lang.Long getExpReqId(){
    return expReqId;
    }
    public void setExpReqId(java.lang.Long expReqId)
    {
    this.expReqId = expReqId;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Long getRecvGroupId(){
    return recvGroupId;
    }
    public void setRecvGroupId(java.lang.Long recvGroupId)
    {
    this.recvGroupId = recvGroupId;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getReceiptNoteId(){
    return receiptNoteId;
    }
    public void setReceiptNoteId(java.lang.Long receiptNoteId)
    {
    this.receiptNoteId = receiptNoteId;
    }
    
    public java.lang.Long getExpCauseId(){
    return expCauseId;
    }
    public void setExpCauseId(java.lang.Long expCauseId)
    {
    this.expCauseId = expCauseId;
    }
    
    public java.lang.Long getExpType(){
    return expType;
    }
    public void setExpType(java.lang.Long expType)
    {
    this.expType = expType;
    }
    
    public java.lang.Long getSrcWarehouseId(){
    return srcWarehouseId;
    }
    public void setSrcWarehouseId(java.lang.Long srcWarehouseId)
    {
    this.srcWarehouseId = srcWarehouseId;
    }
    
    public java.lang.Long getDestWarehouseId(){
    return destWarehouseId;
    }
    public void setDestWarehouseId(java.lang.Long destWarehouseId)
    {
    this.destWarehouseId = destWarehouseId;
    }
    
    public java.lang.Long getConstructionId(){
    return constructionId;
    }
    public void setConstructionId(java.lang.Long constructionId)
    {
    this.constructionId = constructionId;
    }
    
    public java.lang.Long getWorkItemId(){
    return workItemId;
    }
    public void setWorkItemId(java.lang.Long workItemId)
    {
    this.workItemId = workItemId;
    }
    
    public java.lang.Long getCreGroupId(){
    return creGroupId;
    }
    public void setCreGroupId(java.lang.Long creGroupId)
    {
    this.creGroupId = creGroupId;
    }
    
    public java.lang.Long getCreDeliveryNoteBy(){
    return creDeliveryNoteBy;
    }
    public void setCreDeliveryNoteBy(java.lang.Long creDeliveryNoteBy)
    {
    this.creDeliveryNoteBy = creDeliveryNoteBy;
    }
    
    public java.lang.String getTplName(){
    return tplName;
    }
    public void setTplName(java.lang.String tplName)
    {
    this.tplName = tplName;
    }
    
    public java.lang.String getExpNoteCode(){
    return expNoteCode;
    }
    public void setExpNoteCode(java.lang.String expNoteCode)
    {
    this.expNoteCode = expNoteCode;
    }
    
    public java.lang.String getReceiveName(){
    return receiveName;
    }
    public void setReceiveName(java.lang.String receiveName)
    {
    this.receiveName = receiveName;
    }
    
    public java.lang.Long getApproveStatus(){
    return approveStatus;
    }
    public void setApproveStatus(java.lang.Long approveStatus)
    {
    this.approveStatus = approveStatus;
    }
    
    public java.util.Date getNoteCreatedDate(){
    return noteCreatedDate;
    }
    public void setNoteCreatedDate(java.util.Date noteCreatedDate)
    {
    this.noteCreatedDate = noteCreatedDate;
    }
    
    public java.lang.Long getConstructExpType(){
    return constructExpType;
    }
    public void setConstructExpType(java.lang.Long constructExpType)
    {
    this.constructExpType = constructExpType;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
    public java.lang.Long getDestType(){
    return destType;
    }
    public void setDestType(java.lang.Long destType)
    {
    this.destType = destType;
    }
    
    public java.lang.String getDisapproveNoteCause(){
    return disapproveNoteCause;
    }
    public void setDisapproveNoteCause(java.lang.String disapproveNoteCause)
    {
    this.disapproveNoteCause = disapproveNoteCause;
    }
    
    public java.lang.Long getMerExpType(){
    return merExpType;
    }
    public void setMerExpType(java.lang.Long merExpType)
    {
    this.merExpType = merExpType;
    }
    
    public java.lang.String getOldExpNoteCode(){
    return oldExpNoteCode;
    }
    public void setOldExpNoteCode(java.lang.String oldExpNoteCode)
    {
    this.oldExpNoteCode = oldExpNoteCode;
    }
    
    public java.lang.Long getCreNoteGroupId(){
    return creNoteGroupId;
    }
    public void setCreNoteGroupId(java.lang.Long creNoteGroupId)
    {
    this.creNoteGroupId = creNoteGroupId;
    }
    
    public java.lang.Long getDelNoteUserId(){
    return delNoteUserId;
    }
    public void setDelNoteUserId(java.lang.Long delNoteUserId)
    {
    this.delNoteUserId = delNoteUserId;
    }
    
    public java.lang.Long getRegularStatus(){
    return regularStatus;
    }
    public void setRegularStatus(java.lang.Long regularStatus)
    {
    this.regularStatus = regularStatus;
    }
    
    public java.lang.String getDeleteDisapproveNoteCause(){
    return deleteDisapproveNoteCause;
    }
    public void setDeleteDisapproveNoteCause(java.lang.String deleteDisapproveNoteCause)
    {
    this.deleteDisapproveNoteCause = deleteDisapproveNoteCause;
    }
    
    public java.lang.Long getPlanSwapId(){
    return planSwapId;
    }
    public void setPlanSwapId(java.lang.Long planSwapId)
    {
    this.planSwapId = planSwapId;
    }
    
    public java.lang.Long getIsReturn(){
    return isReturn;
    }
    public void setIsReturn(java.lang.Long isReturn)
    {
    this.isReturn = isReturn;
    }
    
    public java.lang.Long getIsCaNote(){
    return isCaNote;
    }
    public void setIsCaNote(java.lang.Long isCaNote)
    {
    this.isCaNote = isCaNote;
    }
    
    public java.lang.Long getDocumentCaNoteId(){
    return documentCaNoteId;
    }
    public void setDocumentCaNoteId(java.lang.Long documentCaNoteId)
    {
    this.documentCaNoteId = documentCaNoteId;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.String getCommemtCa(){
    return commemtCa;
    }
    public void setCommemtCa(java.lang.String commemtCa)
    {
    this.commemtCa = commemtCa;
    }
    
    public java.lang.String getLogisticNoteCode(){
    return logisticNoteCode;
    }
    public void setLogisticNoteCode(java.lang.String logisticNoteCode)
    {
    this.logisticNoteCode = logisticNoteCode;
    }
    
    public java.lang.String getReasonLogistic(){
    return reasonLogistic;
    }
    public void setReasonLogistic(java.lang.String reasonLogistic)
    {
    this.reasonLogistic = reasonLogistic;
    }
    
    public java.util.Date getLogisticDate(){
    return logisticDate;
    }
    public void setLogisticDate(java.util.Date logisticDate)
    {
    this.logisticDate = logisticDate;
    }
    
    public java.lang.Long getStatusLogistic(){
    return statusLogistic;
    }
    public void setStatusLogistic(java.lang.Long statusLogistic)
    {
    this.statusLogistic = statusLogistic;
    }
    
    public java.lang.String getLogisticPerson(){
    return logisticPerson;
    }
    public void setLogisticPerson(java.lang.String logisticPerson)
    {
    this.logisticPerson = logisticPerson;
    }
    
    public java.util.Date getLastUpdate(){
    return lastUpdate;
    }
    public void setLastUpdate(java.util.Date lastUpdate)
    {
    this.lastUpdate = lastUpdate;
    }
    
    public java.lang.Long getIsSyncErp(){
    return isSyncErp;
    }
    public void setIsSyncErp(java.lang.Long isSyncErp)
    {
    this.isSyncErp = isSyncErp;
    }
    
    public java.lang.String getReasonErp(){
    return reasonErp;
    }
    public void setReasonErp(java.lang.String reasonErp)
    {
    this.reasonErp = reasonErp;
    }

	public String getCreatedDateStr() {
		return createdDateStr;
	}

	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}

	public String getActualExpDateStr() {
		return actualExpDateStr;
	}

	public void setActualExpDateStr(String actualExpDateStr) {
		this.actualExpDateStr = actualExpDateStr;
	}
    
    
    
   
}
