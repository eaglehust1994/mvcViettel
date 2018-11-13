package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.MerHandOverInfo;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "merHandOverInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerHandOverInfoDto {
	
	
		private Long handOverId;
		private Long groupId;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date handOverDate;
		private String description;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date createdDate;
		private Long creatorId;
		private String receiverName;
		private Long type;
		private String code;
		private Long deliveryNoteId;
		private Long createBy;
		private String receiverPosition;
		private String deliveryPosition;
		private String deliveryName;
		private Long handOverUseId;
		private Long deliveryGroupId;
		private Long constructionId;
		private Long isTranferToExploit;
		private String handOverUseCode;
		private Long isAccepted;
		private Long acceptorId;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date acceptedDate;
		private String foundation;
		private Long contractId;
		private Long handOverReqId;
		private String financeName;
		private String financePosition;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date restoreDate;
		private Long isDummy;
		private Long warehouseId;
		private Long transferGroupId;
		private Long statusMapping;
		private Long typeDeliveryNote;
		private Long isActive;
		private Long assetType;
		private String reason;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date confirmDate;
		private Long statusCa;
		private String commentCa;
		private Long documentCaId;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date lastUpdate;
		private Long isSyncErp;
		private String reasonErp;
		private Long isToAsset;
	
		//MerHandOverEntity
		private String catWarehouseName;
		private String sysGroupName;
		private String namesysGroup;
		 //LongTermAsset
		private Long catAssetCodeId;
		private String lotaCode;
		private Long lotaIndex;
		private Long useGroupId;
		private Long originalPrice;
		
		//LongTermAssetEntity
		private Long longTermAssetEntityId;
		private Long merEntityId;
		private Long quantity;
		@JsonDeserialize(using = CustomJsonDateDeserializer.class)
		@JsonSerialize(using = CustomJsonDateSerializer.class)
		private Date createdDateAsset;
		private Long longTermAssetId;
		private String createdSource;
		private Long voucherType;
		private String attachName;
		private Long idsysGroup;
		private String checkUsing;
		private Long isSentErp;
		
		
	public Long getIsSentErp() {
			return isSentErp;
		}

		public void setIsSentErp(Long isSentErp) {
			this.isSentErp = isSentErp;
		}

	public MerHandOverInfoDto() {
		
	}
	
	public MerHandOverInfoDto(MerHandOverInfo entity) {
		this.handOverId = entity.getHandOverId();
		this.groupId = entity.getGroupId();
		this.handOverDate = entity.getHandOverDate();
		this.description = entity.getDescription();
		this.createdDate = entity.getCreatedDate();
		this.creatorId = entity.getCreatorId();
		this.receiverName = entity.getReceiverName();
		this.type = entity.getType();
		this.code = entity.getCode();
		this.deliveryNoteId = entity.getDeliveryNoteId();
		this.createBy = entity.getCreateBy();
		this.receiverPosition = entity.getReceiverPosition();
		this.deliveryPosition = entity.getDeliveryPosition();
		this.deliveryName = entity.getDeliveryName();
		this.handOverUseId = entity.getHandOverUseId();
		this.deliveryGroupId = entity.getDeliveryGroupId();
		this.constructionId = entity.getConstructionId();
		this.isTranferToExploit = entity.getIsTranferToExploit();
		this.handOverUseCode = entity.getHandOverUseCode();
		this.isAccepted = entity.getIsAccepted();
		this.acceptorId = entity.getAcceptorId();
		this.acceptedDate = entity.getAcceptedDate();
		this.foundation = entity.getFoundation();
		this.contractId = entity.getContractId();
		this.handOverReqId = entity.getHandOverReqId();
		this.financeName = entity.getFinanceName();
		this.financePosition = entity.getFinancePosition();
		this.restoreDate = entity.getRestoreDate();
		this.isDummy = entity.getIsDummy();
		this.warehouseId = entity.getWarehouseId();
		this.transferGroupId = entity.getTransferGroupId();
		this.statusMapping = entity.getStatusMapping();
		this.typeDeliveryNote = entity.getTypeDeliveryNote();
		this.isActive = entity.getIsActive();
		this.assetType = entity.getAssetType();
		this.reason = entity.getReason();
		this.confirmDate = entity.getConfirmDate();
		this.statusCa = entity.getStatusCa();
		this.commentCa = entity.getCommentCa();
		this.documentCaId = entity.getDocumentCaId();
		this.lastUpdate = entity.getLastUpdate();
		this.isSyncErp = entity.getIsSyncErp();
		this.reasonErp = entity.getReasonErp();
		this.isToAsset = entity.getIsToAsset();
	}
	
	public MerHandOverInfo toEntity() {
		MerHandOverInfo entity = new MerHandOverInfo();
		entity.setHandOverId(this.handOverId);
		entity.setGroupId(this.groupId);
		entity.setHandOverDate(this.handOverDate);
		entity.setDescription(this.description);
		entity.setCreatedDate(this.createdDate);
		entity.setCreatorId(this.creatorId);
		entity.setReceiverName(this.receiverName);
		entity.setType(this.type);
		entity.setCode(this.code);
		entity.setDeliveryNoteId(this.deliveryNoteId);
		entity.setCreateBy(this.createBy);
		entity.setReceiverPosition(this.receiverPosition);
		entity.setDeliveryPosition(this.deliveryPosition);
		entity.setDeliveryName(this.deliveryName);
		entity.setHandOverUseId(this.handOverUseId);
		entity.setDeliveryGroupId(this.deliveryGroupId);
		entity.setConstructionId(this.constructionId);
		entity.setIsTranferToExploit(this.isTranferToExploit);
		entity.setHandOverUseCode(this.handOverUseCode);
		entity.setIsAccepted(this.isAccepted);
		entity.setAcceptorId(this.acceptorId);
		entity.setAcceptedDate(this.acceptedDate);
		entity.setFoundation(this.foundation);
		entity.setContractId(this.contractId);
		entity.setHandOverReqId(this.handOverReqId);
		entity.setFinanceName(this.financeName);
		entity.setFinancePosition(this.financePosition);
		entity.setRestoreDate(this.restoreDate);
		entity.setIsDummy(this.isDummy);
		entity.setWarehouseId(this.warehouseId);
		entity.setTransferGroupId(this.transferGroupId);
		entity.setStatusMapping(this.statusMapping);
		entity.setTypeDeliveryNote(this.typeDeliveryNote);
		entity.setIsActive(this.isActive);
		entity.setAssetType(this.assetType);
		entity.setReason(this.reason);
		entity.setConfirmDate(this.confirmDate);
		entity.setStatusCa(this.statusCa);
		entity.setCommentCa(this.commentCa);
		entity.setDocumentCaId(this.documentCaId);
		entity.setLastUpdate(this.lastUpdate);
		entity.setIsSyncErp(this.isSyncErp);
		entity.setReasonErp(this.reasonErp);
		entity.setIsToAsset(this.isToAsset);
		return entity;
	}
	
	public void updateEntity(MerHandOverInfo entity) {
			entity.setGroupId(this.groupId);
			entity.setHandOverDate(this.handOverDate);
			entity.setDescription(this.description);
			entity.setCreatedDate(this.createdDate);
			entity.setCreatorId(this.creatorId);
			entity.setReceiverName(this.receiverName);
			entity.setType(this.type);
			entity.setCode(this.code);
			entity.setDeliveryNoteId(this.deliveryNoteId);
			entity.setCreateBy(this.createBy);
			entity.setReceiverPosition(this.receiverPosition);
			entity.setDeliveryPosition(this.deliveryPosition);
			entity.setDeliveryName(this.deliveryName);
			entity.setHandOverUseId(this.handOverUseId);
			entity.setDeliveryGroupId(this.deliveryGroupId);
			entity.setConstructionId(this.constructionId);
			entity.setIsTranferToExploit(this.isTranferToExploit);
			entity.setHandOverUseCode(this.handOverUseCode);
			entity.setIsAccepted(this.isAccepted);
			entity.setAcceptorId(this.acceptorId);
			entity.setAcceptedDate(this.acceptedDate);
			entity.setFoundation(this.foundation);
			entity.setContractId(this.contractId);
			entity.setHandOverReqId(this.handOverReqId);
			entity.setFinanceName(this.financeName);
			entity.setFinancePosition(this.financePosition);
			entity.setRestoreDate(this.restoreDate);
			entity.setIsDummy(this.isDummy);
			entity.setWarehouseId(this.warehouseId);
			entity.setTransferGroupId(this.transferGroupId);
			entity.setStatusMapping(this.statusMapping);
			entity.setTypeDeliveryNote(this.typeDeliveryNote);
			entity.setIsActive(this.isActive);
			entity.setAssetType(this.assetType);
			entity.setReason(this.reason);
			entity.setConfirmDate(this.confirmDate);
			entity.setStatusCa(this.statusCa);
			entity.setCommentCa(this.commentCa);
			entity.setDocumentCaId(this.documentCaId);
			entity.setLastUpdate(this.lastUpdate);
			entity.setIsSyncErp(this.isSyncErp);
			entity.setReasonErp(this.reasonErp);
			entity.setIsToAsset(this.isToAsset);
	}
	
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

		public String getCatWarehouseName() {
			return catWarehouseName;
		}

		public void setCatWarehouseName(String catWarehouseName) {
			this.catWarehouseName = catWarehouseName;
		}

		public String getSysGroupName() {
			return sysGroupName;
		}

		public void setSysGroupName(String sysGroupName) {
			this.sysGroupName = sysGroupName;
		}

		public Long getCatAssetCodeId() {
			return catAssetCodeId;
		}

		public void setCatAssetCodeId(Long catAssetCodeId) {
			this.catAssetCodeId = catAssetCodeId;
		}

		public String getLotaCode() {
			return lotaCode;
		}

		public void setLotaCode(String lotaCode) {
			this.lotaCode = lotaCode;
		}

		public Long getLotaIndex() {
			return lotaIndex;
		}

		public void setLotaIndex(Long lotaIndex) {
			this.lotaIndex = lotaIndex;
		}

		public Long getUseGroupId() {
			return useGroupId;
		}

		public void setUseGroupId(Long useGroupId) {
			this.useGroupId = useGroupId;
		}

		public Long getOriginalPrice() {
			return originalPrice;
		}

		public void setOriginalPrice(Long originalPrice) {
			this.originalPrice = originalPrice;
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

		public Long getQuantity() {
			return quantity;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
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

		public String getCreatedSource() {
			return createdSource;
		}

		public void setCreatedSource(String createdSource) {
			this.createdSource = createdSource;
		}

		public String getNamesysGroup() {
			return namesysGroup;
		}

		public void setNamesysGroup(String namesysGroup) {
			this.namesysGroup = namesysGroup;
		}

		public Long getVoucherType() {
			return voucherType;
		}

		public void setVoucherType(Long voucherType) {
			this.voucherType = voucherType;
		}

		public String getAttachName() {
			return attachName;
		}

		public void setAttachName(String attachName) {
			this.attachName = attachName;
		}

		public Long getIdsysGroup() {
			return idsysGroup;
		}

		public void setIdsysGroup(Long idsysGroup) {
			this.idsysGroup = idsysGroup;
		}

		public String getCheckUsing() {
			return checkUsing;
		}

		public void setCheckUsing(String checkUsing) {
			this.checkUsing = checkUsing;
		}		
	
}
