package com.viettel.asset.dto;

import java.util.Date;
import java.util.List;
import com.viettel.asset.bo.LongTermAsset;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "longTermAsset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LongTermAssetDto {
	
	private Long longTermAssetId;
	private Long catAssetCodeId;
	private String lotaCode;
	private Long lotaIndex;
	private Long constructId;
	private Long stationId;
	private Long employeeId;
	private Long groupId;
	private Long useGroupId;
	private Long originalPrice;
	private Long residualPrice;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date depreciationStartDate;
	private Long depreciationTime;
	private Long depreciationRate;
	private Long depreciatedTime;
	private Long depreciatiedValue;
	private Long lotaType;
	private Long isActive;
	private Long creatorId;
	private Long createdGroupId;
	private Date creatorDate;
	private Long updatorId;
	private Long updatedGroupId;
	private Date updatorDate;
	private Long whExpReqId;
	private Long voucherType;
	private String createdSource;
	private Long lotaStatus;
	private String handoverCode;
	private Long isSentErp;
	
	private String assetNameName;
	private String assetNameCode;
	private String assetSourceName;
	private String assetSourceCode;
	private String assetTypeName;
	private String assetTypeCode;
	private String assetGroupName;
	private String assetGroupCode;
	private Long assetNameId;
	private Long assetSourceId;
	private Long assetTypeId;
	private Long assetGroupId;
	private List<LongTermAssetCostDto> listAssetCost;
	private List<MerEntityDto> listAssetEntities;

	private String stationCode;
	private String constructName;
	private String groupName;
	private String useGroupName;
	
	private Long longTermAssetEntityId;

	private Long merEntityId;
	private Long quantity;
	private Date createdDateAsset;

	private Long longTermAssetCostId;
	private Long loacType;
	private Long loacValue;
	private String voucherList;
	private Date createdDate;
	
	private Long longTermAssetVoucherId;
	private String voucherCode;
	private Date voucherDate;
	private Long voucherValue;
	private Long attachId;
	private Long objectId;
	private Long handOverId;
	
	
	
	private String attachName;
	private String hasUpload;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date dateUsingFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date dateUsingTo;
	private int rownum;
	private String lotaTypeText;
	private String isSentErpText;
	private String lotaStatusText;
	private String stringDate;
	private String checkUsing;
	
	private String nationalName;
	private Long madeYear;
	private String description;
	
	private Long isDevice;
	
	private String name;
	private String code;
	private String serialNumber;
	private Double count;
	private String caacName;
	private String attachIdEncrypted;
	private Long upgradeStatus;
	
	private String erpFailReason;
	private Long isOfferSlip;
	
	
	public Long getIsOfferSlip() {
		return isOfferSlip;
	}

	public void setIsOfferSlip(Long isOfferSlip) {
		this.isOfferSlip = isOfferSlip;
	}

	private List<LongTermAssetAttachDetailDto> lstAttachDetailDto;
	
	
	public List<LongTermAssetAttachDetailDto> getLstAttachDetailDto() {
		return lstAttachDetailDto;
	}

	public void setLstAttachDetailDto(List<LongTermAssetAttachDetailDto> lstAttachDetailDto) {
		this.lstAttachDetailDto = lstAttachDetailDto;
	}

	public String getErpFailReason() {
		return erpFailReason;
	}

	public void setErpFailReason(String erpFailReason) {
		this.erpFailReason = erpFailReason;
	}

	public Long getUpgradeStatus() {
		return upgradeStatus;
	}

	public void setUpgradeStatus(Long upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}

	public String getAttachIdEncrypted() {
		return attachIdEncrypted;
	}

	public void setAttachIdEncrypted(String attachIdEncrypted) {
		this.attachIdEncrypted = attachIdEncrypted;
	}

	public String getCaacName() {
		return caacName;
	}

	public void setCaacName(String caacName) {
		this.caacName = caacName;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getIsDevice() {
		return isDevice;
	}

	public void setIsDevice(Long isDevice) {
		this.isDevice = isDevice;
	}

	public String getNationalName() {
		return nationalName;
	}

	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}

	public Long getMadeYear() {
		return madeYear;
	}

	public void setMadeYear(Long madeYear) {
		this.madeYear = madeYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public String getLotaTypeText() {
		return lotaTypeText;
	}

	public void setLotaTypeText(String lotaTypeText) {
		this.lotaTypeText = lotaTypeText;
	}

	public String getIsSentErpText() {
		return isSentErpText;
	}

	public void setIsSentErpText(String isSentErpText) {
		this.isSentErpText = isSentErpText;
	}

	public String getLotaStatusText() {
		return lotaStatusText;
	}

	public void setLotaStatusText(String lotaStatusText) {
		this.lotaStatusText = lotaStatusText;
	}

	
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public LongTermAssetDto() {
		
	}
	
	public LongTermAssetDto(LongTermAsset entity) {
		this.longTermAssetId = entity.getLongTermAssetId();
		this.catAssetCodeId = entity.getCatAssetCodeId();
		this.lotaCode = entity.getLotaCode();
		this.lotaIndex = entity.getLotaIndex();
		this.constructId = entity.getConstructId();
		this.stationId = entity.getStationId();
		this.employeeId = entity.getEmployeeId();
		this.groupId = entity.getGroupId();
		this.useGroupId = entity.getUseGroupId();
		this.originalPrice = entity.getOriginalPrice();
		this.residualPrice = entity.getResidualPrice();
		this.depreciationStartDate = entity.getDepreciationStartDate();
		this.depreciationTime = entity.getDepreciationTime();
		this.depreciationRate = entity.getDepreciationRate();
		this.depreciatedTime = entity.getDepreciatedTime();
		this.depreciatiedValue = entity.getDepreciatiedValue();
		this.lotaType = entity.getLotaType();
		this.isActive = entity.getIsActive();
		this.creatorId = entity.getCreatorId();
		this.createdGroupId = entity.getCreatedGroupId();
		this.creatorDate = entity.getCreatorDate();
		this.updatorId = entity.getUpdatorId();
		this.updatedGroupId = entity.getUpdatedGroupId();
		this.updatorDate = entity.getUpdatorDate();
		this.whExpReqId = entity.getWhExpReqId();
		this.voucherType = entity.getVoucherType();
		this.createdSource = entity.getCreatedSource();
		this.lotaStatus = entity.getLotaStatus();
		this.handoverCode = entity.getHandoverCode();
		this.isSentErp = entity.getIsSentErp();
	}
	
	public LongTermAsset toEntity() {
		LongTermAsset entity = new LongTermAsset();
		entity.setLongTermAssetId(this.longTermAssetId);
		entity.setCatAssetCodeId(this.catAssetCodeId);
		entity.setLotaCode(this.lotaCode);
		entity.setLotaIndex(this.lotaIndex);
		entity.setConstructId(this.constructId);
		entity.setStationId(this.stationId);
		entity.setEmployeeId(this.employeeId);
		entity.setGroupId(this.groupId);
		entity.setUseGroupId(this.useGroupId);
		entity.setOriginalPrice(this.originalPrice);
		entity.setResidualPrice(this.residualPrice);
		entity.setDepreciationStartDate(this.depreciationStartDate);
		entity.setDepreciationTime(this.depreciationTime);
		entity.setDepreciationRate(this.depreciationRate);
		entity.setDepreciatedTime(this.depreciatedTime);
		entity.setDepreciatiedValue(this.depreciatiedValue);
		entity.setLotaType(this.lotaType);
		entity.setIsActive(this.isActive==null? 1L:this.isActive);
		entity.setCreatorId(this.creatorId);
		entity.setCreatedGroupId(this.createdGroupId);
		entity.setCreatorDate(this.creatorDate);
		entity.setUpdatorId(this.updatorId);
		entity.setUpdatedGroupId(this.updatedGroupId);
		entity.setUpdatorDate(this.updatorDate);
		entity.setWhExpReqId(this.whExpReqId);
		entity.setVoucherType(this.voucherType);
		entity.setCreatedSource(this.createdSource);
		entity.setLotaStatus(this.lotaStatus);
		entity.setHandoverCode(this.handoverCode);
		entity.setIsSentErp(this.isSentErp==null? 0L: this.isSentErp);
		
		return entity;
	}
	
	public void updateEntity(LongTermAsset entity) {
		entity.setCatAssetCodeId(this.catAssetCodeId);
		entity.setLotaCode(this.lotaCode);
		entity.setLotaIndex(this.lotaIndex);
		entity.setConstructId(this.constructId);
		entity.setStationId(this.stationId);
		entity.setEmployeeId(this.employeeId);
		entity.setGroupId(this.groupId);
		entity.setUseGroupId(this.useGroupId);
		entity.setOriginalPrice(this.originalPrice);
		entity.setResidualPrice(this.residualPrice);
		entity.setDepreciationStartDate(this.depreciationStartDate);
		entity.setDepreciationTime(this.depreciationTime);
		entity.setDepreciationRate(this.depreciationRate);
		entity.setDepreciatedTime(this.depreciatedTime);
		entity.setDepreciatiedValue(this.depreciatiedValue);
		entity.setLotaType(this.lotaType);
		entity.setIsActive(this.isActive);
		entity.setCreatorId(this.creatorId);
		entity.setCreatedGroupId(this.createdGroupId);
		entity.setCreatorDate(this.creatorDate);
		entity.setUpdatorId(this.updatorId);
		entity.setUpdatedGroupId(this.updatedGroupId);
		entity.setUpdatorDate(this.updatorDate);
		entity.setWhExpReqId(this.whExpReqId);
		entity.setVoucherType(this.voucherType);
		entity.setCreatedSource(this.createdSource);
		entity.setLotaStatus(this.lotaStatus);
		entity.setHandoverCode(this.handoverCode);
		entity.setIsSentErp(this.isSentErp);
	}
	
	public Long getLongTermAssetId() {
		return longTermAssetId;
	}
	public void setLongTermAssetId(Long longTermAssetId) {
		this.longTermAssetId = longTermAssetId;
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
	public Long getConstructId() {
		return constructId;
	}
	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}		
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}		
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}		
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	public Long getResidualPrice() {
		return residualPrice;
	}
	public void setResidualPrice(Long residualPrice) {
		this.residualPrice = residualPrice;
	}		
	public Date getDepreciationStartDate() {
		return depreciationStartDate;
	}
	public void setDepreciationStartDate(Date depreciationStartDate) {
		this.depreciationStartDate = depreciationStartDate;
	}		
	public Long getDepreciationTime() {
		return depreciationTime;
	}
	public void setDepreciationTime(Long depreciationTime) {
		this.depreciationTime = depreciationTime;
	}		
	public Long getDepreciationRate() {
		return depreciationRate;
	}
	public void setDepreciationRate(Long depreciationRate) {
		this.depreciationRate = depreciationRate;
	}		
	public Long getDepreciatedTime() {
		return depreciatedTime;
	}
	public void setDepreciatedTime(Long depreciatedTime) {
		this.depreciatedTime = depreciatedTime;
	}		
	public Long getDepreciatiedValue() {
		return depreciatiedValue;
	}
	public void setDepreciatiedValue(Long depreciatiedValue) {
		this.depreciatiedValue = depreciatiedValue;
	}		
	public Long getLotaType() {
		return lotaType;
	}
	public void setLotaType(Long lotaType) {
		this.lotaType = lotaType;
	}		
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}		
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}		
	public Long getCreatedGroupId() {
		return createdGroupId;
	}
	public void setCreatedGroupId(Long createdGroupId) {
		this.createdGroupId = createdGroupId;
	}		
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}		
	public Long getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}		
	public Long getUpdatedGroupId() {
		return updatedGroupId;
	}
	public void setUpdatedGroupId(Long updatedGroupId) {
		this.updatedGroupId = updatedGroupId;
	}		
	public Date getUpdatorDate() {
		return updatorDate;
	}
	public void setUpdatorDate(Date updatorDate) {
		this.updatorDate = updatorDate;
	}		
	public Long getWhExpReqId() {
		return whExpReqId;
	}
	public void setWhExpReqId(Long whExpReqId) {
		this.whExpReqId = whExpReqId;
	}		
	public Long getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(Long voucherType) {
		this.voucherType = voucherType;
	}		
	public String getCreatedSource() {
		return createdSource;
	}
	public void setCreatedSource(String createdSource) {
		this.createdSource = createdSource;
	}		
	public Long getLotaStatus() {
		return lotaStatus;
	}
	public void setLotaStatus(Long lotaStatus) {
		this.lotaStatus = lotaStatus;
	}		
	public String getHandoverCode() {
		return handoverCode;
	}
	public void setHandoverCode(String handoverCode) {
		this.handoverCode = handoverCode;
	}


	public String getConstructName() {
		return constructName;
	}

	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}

	public String getGroupName() {
		
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUseGroupName() {
		return useGroupName;
	}

	public void setUseGroupName(String useGroupName) {
		this.useGroupName = useGroupName;
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

	public Long getLongTermAssetCostId() {
		return longTermAssetCostId;
	}

	public void setLongTermAssetCostId(Long longTermAssetCostId) {
		this.longTermAssetCostId = longTermAssetCostId;
	}

	public Long getLoacType() {
		return loacType;
	}

	public void setLoacType(Long loacType) {
		this.loacType = loacType;
	}

	public Long getLoacValue() {
		return loacValue;
	}

	public void setLoacValue(Long loacValue) {
		this.loacValue = loacValue;
	}

	public String getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(String voucherList) {
		this.voucherList = voucherList;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLongTermAssetVoucherId() {
		return longTermAssetVoucherId;
	}

	public void setLongTermAssetVoucherId(Long longTermAssetVoucherId) {
		this.longTermAssetVoucherId = longTermAssetVoucherId;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public Long getVoucherValue() {
		return voucherValue;
	}

	public void setVoucherValue(Long voucherValue) {
		this.voucherValue = voucherValue;
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	public Long getIsSentErp() {
		return isSentErp;
	}

	public void setIsSentErp(Long isSentErp) {
		this.isSentErp = isSentErp;
	}

	public Long getHandOverId() {
		return handOverId;
	}

	public void setHandOverId(Long handOverId) {
		this.handOverId = handOverId;
	}
	
	public String getAssetNameName() {
		return assetNameName;
	}

	public void setAssetNameName(String assetNameName) {
		this.assetNameName = assetNameName;
	}

	public String getAssetNameCode() {
		return assetNameCode;
	}

	public void setAssetNameCode(String assetNameCode) {
		this.assetNameCode = assetNameCode;
	}

	public String getAssetSourceName() {
		return assetSourceName;
	}

	public void setAssetSourceName(String assetSourceName) {
		this.assetSourceName = assetSourceName;
	}

	public String getAssetSourceCode() {
		return assetSourceCode;
	}

	public void setAssetSourceCode(String assetSourceCode) {
		this.assetSourceCode = assetSourceCode;
	}

	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getAssetGroupName() {
		return assetGroupName;
	}

	public void setAssetGroupName(String assetGroupName) {
		this.assetGroupName = assetGroupName;
	}

	public String getAssetGroupCode() {
		return assetGroupCode;
	}

	public void setAssetGroupCode(String assetGroupCode) {
		this.assetGroupCode = assetGroupCode;
	}

	public Long getAssetNameId() {
		return assetNameId;
	}

	public void setAssetNameId(Long assetNameId) {
		this.assetNameId = assetNameId;
	}

	public Long getAssetSourceId() {
		return assetSourceId;
	}

	public void setAssetSourceId(Long assetSourceId) {
		this.assetSourceId = assetSourceId;
	}

	public Long getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public Long getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(Long assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public List<LongTermAssetCostDto> getListAssetCost() {
		return listAssetCost;
	}

	public void setListAssetCost(List<LongTermAssetCostDto> listAssetCost) {
		this.listAssetCost = listAssetCost;
	}

	public List<MerEntityDto> getListAssetEntities() {
		return listAssetEntities;
	}

	public void setListAssetEntities(List<MerEntityDto> listAssetEntities) {
		this.listAssetEntities = listAssetEntities;
	}
	
	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getHasUpload() {
		return hasUpload;
	}

	public void setHasUpload(String hasUpload) {
		this.hasUpload = hasUpload;
	}
	
	public Date getDateUsingFrom() {
		return dateUsingFrom;
	}

	public void setDateUsingFrom(Date dateUsingFrom) {
		this.dateUsingFrom = dateUsingFrom;
	}

	public Date getDateUsingTo() {
		return dateUsingTo;
	}

	public void setDateUsingTo(Date dateUsingTo) {
		this.dateUsingTo = dateUsingTo;
	}

	public String getCheckUsing() {
		return checkUsing;
	}

	public void setCheckUsing(String checkUsing) {
		this.checkUsing = checkUsing;
	}

}
