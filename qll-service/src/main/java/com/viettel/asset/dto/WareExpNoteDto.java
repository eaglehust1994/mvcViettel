package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.WareExpNote;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "wareExpNote")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WareExpNoteDto {
	
	
		private Long deliveryNoteId;
		private Long sourceWhId;
		private Long destWhId;
		private Long recvGroupId;
		private Date createdDate;
		private Long creatorId;
		private Long expCmdId;
		private Long deliveryGroupId;
		private Long recvPersonId;
		private String code;
		private Long status;
		private Long confirmStatus;
		private Long constructionId;
		private Date actualExpDate;
		private Long workItemId;
		private String receiverName;
		private Long isHandOverAll;
		private Long actualExpBy;
		private Long handOverBy;
		private Long handOverInfoId;
		private Long expNoteType;
		private String recvGroupName;
		private Long isUpdateStationAll;
		private Long constructExpType;
		private Long isRetrieved;
		private Date appActualDate;
		private Long deletable;
		private Long merExpType;
		private Long partnerId;
		private Long destType;
		private Long regularStatus;
		private Long receiveConfirm;
		private Date receiveConfirmDate;
		private Date realReceiveDate;
		private Long isSyncInventory;
		private Long userIdDel;
		private Date delConfirmDate;
		private Long isCa;
		private Long documentCaId;
		private String receiverCode;
		private Long smsStatus;
		private String logisticActualPer;
	
	public WareExpNoteDto() {
		
	}
	
	public WareExpNoteDto(WareExpNote entity) {
		this.deliveryNoteId = entity.getDeliveryNoteId();
		this.sourceWhId = entity.getSourceWhId();
		this.destWhId = entity.getDestWhId();
		this.recvGroupId = entity.getRecvGroupId();
		this.createdDate = entity.getCreatedDate();
		this.creatorId = entity.getCreatorId();
		this.expCmdId = entity.getExpCmdId();
		this.deliveryGroupId = entity.getDeliveryGroupId();
		this.recvPersonId = entity.getRecvPersonId();
		this.code = entity.getCode();
		this.status = entity.getStatus();
		this.confirmStatus = entity.getConfirmStatus();
		this.constructionId = entity.getConstructionId();
		this.actualExpDate = entity.getActualExpDate();
		this.workItemId = entity.getWorkItemId();
		this.receiverName = entity.getReceiverName();
		this.isHandOverAll = entity.getIsHandOverAll();
		this.actualExpBy = entity.getActualExpBy();
		this.handOverBy = entity.getHandOverBy();
		this.handOverInfoId = entity.getHandOverInfoId();
		this.expNoteType = entity.getExpNoteType();
		this.recvGroupName = entity.getRecvGroupName();
		this.isUpdateStationAll = entity.getIsUpdateStationAll();
		this.constructExpType = entity.getConstructExpType();
		this.isRetrieved = entity.getIsRetrieved();
		this.appActualDate = entity.getAppActualDate();
		this.deletable = entity.getDeletable();
		this.merExpType = entity.getMerExpType();
		this.partnerId = entity.getPartnerId();
		this.destType = entity.getDestType();
		this.regularStatus = entity.getRegularStatus();
		this.receiveConfirm = entity.getReceiveConfirm();
		this.receiveConfirmDate = entity.getReceiveConfirmDate();
		this.realReceiveDate = entity.getRealReceiveDate();
		this.isSyncInventory = entity.getIsSyncInventory();
		this.userIdDel = entity.getUserIdDel();
		this.delConfirmDate = entity.getDelConfirmDate();
		this.isCa = entity.getIsCa();
		this.documentCaId = entity.getDocumentCaId();
		this.receiverCode = entity.getReceiverCode();
		this.smsStatus = entity.getSmsStatus();
		this.logisticActualPer = entity.getLogisticActualPer();
	}
	
	public WareExpNote toEntity() {
		WareExpNote entity = new WareExpNote();
		entity.setDeliveryNoteId(this.deliveryNoteId);
		entity.setSourceWhId(this.sourceWhId);
		entity.setDestWhId(this.destWhId);
		entity.setRecvGroupId(this.recvGroupId);
		entity.setCreatedDate(this.createdDate);
		entity.setCreatorId(this.creatorId);
		entity.setExpCmdId(this.expCmdId);
		entity.setDeliveryGroupId(this.deliveryGroupId);
		entity.setRecvPersonId(this.recvPersonId);
		entity.setCode(this.code);
		entity.setStatus(this.status);
		entity.setConfirmStatus(this.confirmStatus);
		entity.setConstructionId(this.constructionId);
		entity.setActualExpDate(this.actualExpDate);
		entity.setWorkItemId(this.workItemId);
		entity.setReceiverName(this.receiverName);
		entity.setIsHandOverAll(this.isHandOverAll);
		entity.setActualExpBy(this.actualExpBy);
		entity.setHandOverBy(this.handOverBy);
		entity.setHandOverInfoId(this.handOverInfoId);
		entity.setExpNoteType(this.expNoteType);
		entity.setRecvGroupName(this.recvGroupName);
		entity.setIsUpdateStationAll(this.isUpdateStationAll);
		entity.setConstructExpType(this.constructExpType);
		entity.setIsRetrieved(this.isRetrieved);
		entity.setAppActualDate(this.appActualDate);
		entity.setDeletable(this.deletable);
		entity.setMerExpType(this.merExpType);
		entity.setPartnerId(this.partnerId);
		entity.setDestType(this.destType);
		entity.setRegularStatus(this.regularStatus);
		entity.setReceiveConfirm(this.receiveConfirm);
		entity.setReceiveConfirmDate(this.receiveConfirmDate);
		entity.setRealReceiveDate(this.realReceiveDate);
		entity.setIsSyncInventory(this.isSyncInventory);
		entity.setUserIdDel(this.userIdDel);
		entity.setDelConfirmDate(this.delConfirmDate);
		entity.setIsCa(this.isCa);
		entity.setDocumentCaId(this.documentCaId);
		entity.setReceiverCode(this.receiverCode);
		entity.setSmsStatus(this.smsStatus);
		entity.setLogisticActualPer(this.logisticActualPer);
		return entity;
	}
	
	public void updateEntity(WareExpNote entity) {
			entity.setSourceWhId(this.sourceWhId);
			entity.setDestWhId(this.destWhId);
			entity.setRecvGroupId(this.recvGroupId);
			entity.setCreatedDate(this.createdDate);
			entity.setCreatorId(this.creatorId);
			entity.setExpCmdId(this.expCmdId);
			entity.setDeliveryGroupId(this.deliveryGroupId);
			entity.setRecvPersonId(this.recvPersonId);
			entity.setCode(this.code);
			entity.setStatus(this.status);
			entity.setConfirmStatus(this.confirmStatus);
			entity.setConstructionId(this.constructionId);
			entity.setActualExpDate(this.actualExpDate);
			entity.setWorkItemId(this.workItemId);
			entity.setReceiverName(this.receiverName);
			entity.setIsHandOverAll(this.isHandOverAll);
			entity.setActualExpBy(this.actualExpBy);
			entity.setHandOverBy(this.handOverBy);
			entity.setHandOverInfoId(this.handOverInfoId);
			entity.setExpNoteType(this.expNoteType);
			entity.setRecvGroupName(this.recvGroupName);
			entity.setIsUpdateStationAll(this.isUpdateStationAll);
			entity.setConstructExpType(this.constructExpType);
			entity.setIsRetrieved(this.isRetrieved);
			entity.setAppActualDate(this.appActualDate);
			entity.setDeletable(this.deletable);
			entity.setMerExpType(this.merExpType);
			entity.setPartnerId(this.partnerId);
			entity.setDestType(this.destType);
			entity.setRegularStatus(this.regularStatus);
			entity.setReceiveConfirm(this.receiveConfirm);
			entity.setReceiveConfirmDate(this.receiveConfirmDate);
			entity.setRealReceiveDate(this.realReceiveDate);
			entity.setIsSyncInventory(this.isSyncInventory);
			entity.setUserIdDel(this.userIdDel);
			entity.setDelConfirmDate(this.delConfirmDate);
			entity.setIsCa(this.isCa);
			entity.setDocumentCaId(this.documentCaId);
			entity.setReceiverCode(this.receiverCode);
			entity.setSmsStatus(this.smsStatus);
			entity.setLogisticActualPer(this.logisticActualPer);
	}
	
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
