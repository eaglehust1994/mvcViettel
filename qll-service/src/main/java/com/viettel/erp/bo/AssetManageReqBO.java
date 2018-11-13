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

import com.viettel.erp.dto.AssetManageReqDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "ASSET_MANAGE_REQ")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AssetManageReqBO extends BaseFWModelImpl {
     
private java.lang.Long manageReqId;
private java.lang.Long reqGroupId;
private java.lang.Long userId;
private java.lang.Long recvGroupId;
private java.lang.String cause;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private java.lang.Long status;
private java.lang.Long type;
private java.lang.Long usedGroupId;
private java.lang.String code;
private java.lang.Long acceptorId;
private java.lang.String acceptComment;
private java.lang.Long retrieveCauseId;
private java.lang.Long constructId;
private java.lang.String delegator;
private java.lang.Long isFull;
private java.lang.String titleDelegator;
private java.lang.String mobileNumber;
private java.lang.String document;
private java.lang.Long failCheckId;
private java.lang.Long deliveryNoteId;
private java.lang.Long isMaintain;
private java.lang.String causeReject;
private java.lang.Long retrieveType;
private java.lang.Long assetType;
private java.lang.Long retrieveCmdId;
private java.lang.Long planSwapId;
private java.lang.Long certificateRegistrationId;
private java.lang.Long partnerId;
private java.lang.Long contractId;
private java.lang.Long statusCa;
private java.lang.String commentCa;
private java.lang.Long documentCaId;
private java.lang.Long typeManage;

 public AssetManageReqBO() {
        setColId("manageReqId");
        setColName("manageReqId");
        setUniqueColumn(new String[]{"manageReqId"});
}
@Id
@GeneratedValue(generator = "sequence")
@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ASSET_MANAGE_REQ_SEQ") })
@Column(name = "MANAGE_REQ_ID", length = 22)
public java.lang.Long getManageReqId(){
return manageReqId;
}
public void setManageReqId(java.lang.Long manageReqId)
{
this.manageReqId = manageReqId;
}

@Column(name = "REQ_GROUP_ID", length = 22)
public java.lang.Long getReqGroupId(){
return reqGroupId;
}
public void setReqGroupId(java.lang.Long reqGroupId)
{
this.reqGroupId = reqGroupId;
}
@Column(name = "USER_ID", length = 22)
public java.lang.Long getUserId(){
return userId;
}
public void setUserId(java.lang.Long userId)
{
this.userId = userId;
}
@Column(name = "RECV_GROUP_ID", length = 22)
public java.lang.Long getRecvGroupId(){
return recvGroupId;
}
public void setRecvGroupId(java.lang.Long recvGroupId)
{
this.recvGroupId = recvGroupId;
}
@Column(name = "CAUSE", length = 1200)
public java.lang.String getCause(){
return cause;
}
public void setCause(java.lang.String cause)
{
this.cause = cause;
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
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "USED_GROUP_ID", length = 22)
public java.lang.Long getUsedGroupId(){
return usedGroupId;
}
public void setUsedGroupId(java.lang.Long usedGroupId)
{
this.usedGroupId = usedGroupId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "ACCEPTOR_ID", length = 22)
public java.lang.Long getAcceptorId(){
return acceptorId;
}
public void setAcceptorId(java.lang.Long acceptorId)
{
this.acceptorId = acceptorId;
}
@Column(name = "ACCEPT_COMMENT", length = 1200)
public java.lang.String getAcceptComment(){
return acceptComment;
}
public void setAcceptComment(java.lang.String acceptComment)
{
this.acceptComment = acceptComment;
}
@Column(name = "RETRIEVE_CAUSE_ID", length = 22)
public java.lang.Long getRetrieveCauseId(){
return retrieveCauseId;
}
public void setRetrieveCauseId(java.lang.Long retrieveCauseId)
{
this.retrieveCauseId = retrieveCauseId;
}
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "DELEGATOR", length = 300)
public java.lang.String getDelegator(){
return delegator;
}
public void setDelegator(java.lang.String delegator)
{
this.delegator = delegator;
}
@Column(name = "IS_FULL", length = 22)
public java.lang.Long getIsFull(){
return isFull;
}
public void setIsFull(java.lang.Long isFull)
{
this.isFull = isFull;
}
@Column(name = "TITLE_DELEGATOR", length = 100)
public java.lang.String getTitleDelegator(){
return titleDelegator;
}
public void setTitleDelegator(java.lang.String titleDelegator)
{
this.titleDelegator = titleDelegator;
}
@Column(name = "MOBILE_NUMBER", length = 100)
public java.lang.String getMobileNumber(){
return mobileNumber;
}
public void setMobileNumber(java.lang.String mobileNumber)
{
this.mobileNumber = mobileNumber;
}
@Column(name = "DOCUMENT", length = 100)
public java.lang.String getDocument(){
return document;
}
public void setDocument(java.lang.String document)
{
this.document = document;
}
@Column(name = "FAIL_CHECK_ID", length = 22)
public java.lang.Long getFailCheckId(){
return failCheckId;
}
public void setFailCheckId(java.lang.Long failCheckId)
{
this.failCheckId = failCheckId;
}
@Column(name = "DELIVERY_NOTE_ID", length = 22)
public java.lang.Long getDeliveryNoteId(){
return deliveryNoteId;
}
public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
{
this.deliveryNoteId = deliveryNoteId;
}
@Column(name = "IS_MAINTAIN", length = 22)
public java.lang.Long getIsMaintain(){
return isMaintain;
}
public void setIsMaintain(java.lang.Long isMaintain)
{
this.isMaintain = isMaintain;
}
@Column(name = "CAUSE_REJECT", length = 1000)
public java.lang.String getCauseReject(){
return causeReject;
}
public void setCauseReject(java.lang.String causeReject)
{
this.causeReject = causeReject;
}
@Column(name = "RETRIEVE_TYPE", length = 22)
public java.lang.Long getRetrieveType(){
return retrieveType;
}
public void setRetrieveType(java.lang.Long retrieveType)
{
this.retrieveType = retrieveType;
}
@Column(name = "ASSET_TYPE", length = 22)
public java.lang.Long getAssetType(){
return assetType;
}
public void setAssetType(java.lang.Long assetType)
{
this.assetType = assetType;
}
@Column(name = "RETRIEVE_CMD_ID", length = 22)
public java.lang.Long getRetrieveCmdId(){
return retrieveCmdId;
}
public void setRetrieveCmdId(java.lang.Long retrieveCmdId)
{
this.retrieveCmdId = retrieveCmdId;
}
@Column(name = "PLAN_SWAP_ID", length = 22)
public java.lang.Long getPlanSwapId(){
return planSwapId;
}
public void setPlanSwapId(java.lang.Long planSwapId)
{
this.planSwapId = planSwapId;
}
@Column(name = "CERTIFICATE_REGISTRATION_ID", length = 22)
public java.lang.Long getCertificateRegistrationId(){
return certificateRegistrationId;
}
public void setCertificateRegistrationId(java.lang.Long certificateRegistrationId)
{
this.certificateRegistrationId = certificateRegistrationId;
}
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Long getContractId(){
return contractId;
}
public void setContractId(java.lang.Long contractId)
{
this.contractId = contractId;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "COMMENT_CA", length = 1000)
public java.lang.String getCommentCa(){
return commentCa;
}
public void setCommentCa(java.lang.String commentCa)
{
this.commentCa = commentCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
@Column(name = "TYPE_MANAGE", length = 22)
public java.lang.Long getTypeManage(){
return typeManage;
}
public void setTypeManage(java.lang.Long typeManage)
{
this.typeManage = typeManage;
}
   

    @Override
    public AssetManageReqDTO toDTO() {
        AssetManageReqDTO assetManageReqDTO = new AssetManageReqDTO();
        //set cac gia tri 
        assetManageReqDTO.setManageReqId(this.manageReqId);
        assetManageReqDTO.setReqGroupId(this.reqGroupId);
        assetManageReqDTO.setUserId(this.userId);
        assetManageReqDTO.setRecvGroupId(this.recvGroupId);
        assetManageReqDTO.setCause(this.cause);
        assetManageReqDTO.setCreatorId(this.creatorId);
        assetManageReqDTO.setCreatedDate(this.createdDate);
        assetManageReqDTO.setStatus(this.status);
        assetManageReqDTO.setType(this.type);
        assetManageReqDTO.setUsedGroupId(this.usedGroupId);
        assetManageReqDTO.setCode(this.code);
        assetManageReqDTO.setAcceptorId(this.acceptorId);
        assetManageReqDTO.setAcceptComment(this.acceptComment);
        assetManageReqDTO.setRetrieveCauseId(this.retrieveCauseId);
        assetManageReqDTO.setConstructId(this.constructId);
        assetManageReqDTO.setDelegator(this.delegator);
        assetManageReqDTO.setIsFull(this.isFull);
        assetManageReqDTO.setTitleDelegator(this.titleDelegator);
        assetManageReqDTO.setMobileNumber(this.mobileNumber);
        assetManageReqDTO.setDocument(this.document);
        assetManageReqDTO.setFailCheckId(this.failCheckId);
        assetManageReqDTO.setDeliveryNoteId(this.deliveryNoteId);
        assetManageReqDTO.setIsMaintain(this.isMaintain);
        assetManageReqDTO.setCauseReject(this.causeReject);
        assetManageReqDTO.setRetrieveType(this.retrieveType);
        assetManageReqDTO.setAssetType(this.assetType);
        assetManageReqDTO.setRetrieveCmdId(this.retrieveCmdId);
        assetManageReqDTO.setPlanSwapId(this.planSwapId);
        assetManageReqDTO.setCertificateRegistrationId(this.certificateRegistrationId);
        assetManageReqDTO.setPartnerId(this.partnerId);
        assetManageReqDTO.setContractId(this.contractId);
        assetManageReqDTO.setStatusCa(this.statusCa);
        assetManageReqDTO.setCommentCa(this.commentCa);
        assetManageReqDTO.setDocumentCaId(this.documentCaId);
        assetManageReqDTO.setTypeManage(this.typeManage);
        return assetManageReqDTO;
    }

}
