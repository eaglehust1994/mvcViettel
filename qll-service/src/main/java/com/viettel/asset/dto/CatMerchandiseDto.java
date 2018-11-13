package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.CatMerchandise;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "catMerchandise")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatMerchandiseDto {
	
	
		private Long merchandiseId;
		private String name;
		private String code;
		private Long parentId;
		private Long unitId;
		private String description;
		private Long isActive;
		private Long isVisible;
		private Long isDevice;
		private Double defaultPrice;
		private Long curId;
		private String path;
		private Long serialRequired;
		private Long minDeprePeriod;
		private Long maintainPeriod;
		private Long maintainCost;
		private Long maxDeprePeriod;
		private Long amortMode;
		private Long type;
		private Long isGroup;
		private Long increaseAssetAccId;
		private Long deptAccId;
		private Long creditAccId;
		private Long partnumberRequired;
		private Long isAccepted;
		private Long creatorId;
		private Long isInFdmanager;
		private String enName;
		private String abbreviate;
		private Long oldId;
		private Long oldType;
		private String oldCode;
		private String partNumber;
		private String bkCode;
		private Date modifiedTime;
		private Long updaterId;
		private String proposalNote;
		private Long proposalStatus;
		private String proposalPath;
		private Long refMerId;
		private Long merType;
		private Long mark;
		private Long cdcMerchandiseId;
		private String fullName;
		private String catGroupType;
		private Long contractId;
		private Long isSpending;
		private Long isAccounting;
		private Long cdcktMerchandiseId;
		private Long merExpType;
		private String warningContent;
		private Long cycleKdhc;
		private Long typeKdhc;
		private Long cycleBd;
		private Long groupType;
		private Long theLevel;
		private Long changeSerialRequire;
		private Long testingPeriod;
		private Long calibrationPeriod;
		private String character;
		private String function;
		private Double expiredDate;
		private Double lifeTime;
		private Long catAssetFixedId;
		private Long catSourceCreatedId;
		private Long isMerType;
	
	public CatMerchandiseDto() {
		
	}
	
	public CatMerchandiseDto(CatMerchandise entity) {
		this.merchandiseId = entity.getMerchandiseId();
		this.name = entity.getName();
		this.code = entity.getCode();
		this.parentId = entity.getParentId();
		this.unitId = entity.getUnitId();
		this.description = entity.getDescription();
		this.isActive = entity.getIsActive();
		this.isVisible = entity.getIsVisible();
		this.isDevice = entity.getIsDevice();
		this.defaultPrice = entity.getDefaultPrice();
		this.curId = entity.getCurId();
		this.path = entity.getPath();
		this.serialRequired = entity.getSerialRequired();
		this.minDeprePeriod = entity.getMinDeprePeriod();
		this.maintainPeriod = entity.getMaintainPeriod();
		this.maintainCost = entity.getMaintainCost();
		this.maxDeprePeriod = entity.getMaxDeprePeriod();
		this.amortMode = entity.getAmortMode();
		this.type = entity.getType();
		this.isGroup = entity.getIsGroup();
		this.increaseAssetAccId = entity.getIncreaseAssetAccId();
		this.deptAccId = entity.getDeptAccId();
		this.creditAccId = entity.getCreditAccId();
		this.partnumberRequired = entity.getPartnumberRequired();
		this.isAccepted = entity.getIsAccepted();
		this.creatorId = entity.getCreatorId();
		this.isInFdmanager = entity.getIsInFdmanager();
		this.enName = entity.getEnName();
		this.abbreviate = entity.getAbbreviate();
		this.oldId = entity.getOldId();
		this.oldType = entity.getOldType();
		this.oldCode = entity.getOldCode();
		this.partNumber = entity.getPartNumber();
		this.bkCode = entity.getBkCode();
		this.modifiedTime = entity.getModifiedTime();
		this.updaterId = entity.getUpdaterId();
		this.proposalNote = entity.getProposalNote();
		this.proposalStatus = entity.getProposalStatus();
		this.proposalPath = entity.getProposalPath();
		this.refMerId = entity.getRefMerId();
		this.merType = entity.getMerType();
		this.mark = entity.getMark();
		this.cdcMerchandiseId = entity.getCdcMerchandiseId();
		this.fullName = entity.getFullName();
		this.catGroupType = entity.getCatGroupType();
		this.contractId = entity.getContractId();
		this.isSpending = entity.getIsSpending();
		this.isAccounting = entity.getIsAccounting();
		this.cdcktMerchandiseId = entity.getCdcktMerchandiseId();
		this.merExpType = entity.getMerExpType();
		this.warningContent = entity.getWarningContent();
		this.cycleKdhc = entity.getCycleKdhc();
		this.typeKdhc = entity.getTypeKdhc();
		this.cycleBd = entity.getCycleBd();
		this.groupType = entity.getGroupType();
		this.theLevel = entity.getTheLevel();
		this.changeSerialRequire = entity.getChangeSerialRequire();
		this.testingPeriod = entity.getTestingPeriod();
		this.calibrationPeriod = entity.getCalibrationPeriod();
		this.character = entity.getCharacter();
		this.function = entity.getFunction();
		this.expiredDate = entity.getExpiredDate();
		this.lifeTime = entity.getLifeTime();
		this.catAssetFixedId = entity.getCatAssetFixedId();
		this.catSourceCreatedId = entity.getCatSourceCreatedId();
		this.isMerType = entity.getIsMerType();
	}
	
	public CatMerchandise toEntity() {
		CatMerchandise entity = new CatMerchandise();
		entity.setMerchandiseId(this.merchandiseId);
		entity.setName(this.name);
		entity.setCode(this.code);
		entity.setParentId(this.parentId);
		entity.setUnitId(this.unitId);
		entity.setDescription(this.description);
		entity.setIsActive(this.isActive);
		entity.setIsVisible(this.isVisible);
		entity.setIsDevice(this.isDevice);
		entity.setDefaultPrice(this.defaultPrice);
		entity.setCurId(this.curId);
		entity.setPath(this.path);
		entity.setSerialRequired(this.serialRequired);
		entity.setMinDeprePeriod(this.minDeprePeriod);
		entity.setMaintainPeriod(this.maintainPeriod);
		entity.setMaintainCost(this.maintainCost);
		entity.setMaxDeprePeriod(this.maxDeprePeriod);
		entity.setAmortMode(this.amortMode);
		entity.setType(this.type);
		entity.setIsGroup(this.isGroup);
		entity.setIncreaseAssetAccId(this.increaseAssetAccId);
		entity.setDeptAccId(this.deptAccId);
		entity.setCreditAccId(this.creditAccId);
		entity.setPartnumberRequired(this.partnumberRequired);
		entity.setIsAccepted(this.isAccepted);
		entity.setCreatorId(this.creatorId);
		entity.setIsInFdmanager(this.isInFdmanager);
		entity.setEnName(this.enName);
		entity.setAbbreviate(this.abbreviate);
		entity.setOldId(this.oldId);
		entity.setOldType(this.oldType);
		entity.setOldCode(this.oldCode);
		entity.setPartNumber(this.partNumber);
		entity.setBkCode(this.bkCode);
		entity.setModifiedTime(this.modifiedTime);
		entity.setUpdaterId(this.updaterId);
		entity.setProposalNote(this.proposalNote);
		entity.setProposalStatus(this.proposalStatus);
		entity.setProposalPath(this.proposalPath);
		entity.setRefMerId(this.refMerId);
		entity.setMerType(this.merType);
		entity.setMark(this.mark);
		entity.setCdcMerchandiseId(this.cdcMerchandiseId);
		entity.setFullName(this.fullName);
		entity.setCatGroupType(this.catGroupType);
		entity.setContractId(this.contractId);
		entity.setIsSpending(this.isSpending);
		entity.setIsAccounting(this.isAccounting);
		entity.setCdcktMerchandiseId(this.cdcktMerchandiseId);
		entity.setMerExpType(this.merExpType);
		entity.setWarningContent(this.warningContent);
		entity.setCycleKdhc(this.cycleKdhc);
		entity.setTypeKdhc(this.typeKdhc);
		entity.setCycleBd(this.cycleBd);
		entity.setGroupType(this.groupType);
		entity.setTheLevel(this.theLevel);
		entity.setChangeSerialRequire(this.changeSerialRequire);
		entity.setTestingPeriod(this.testingPeriod);
		entity.setCalibrationPeriod(this.calibrationPeriod);
		entity.setCharacter(this.character);
		entity.setFunction(this.function);
		entity.setExpiredDate(this.expiredDate);
		entity.setLifeTime(this.lifeTime);
		entity.setCatAssetFixedId(this.catAssetFixedId);
		entity.setCatSourceCreatedId(this.catSourceCreatedId);
		entity.setIsMerType(this.isMerType);
		return entity;
	}
	
	public void updateEntity(CatMerchandise entity) {
			entity.setName(this.name);
			entity.setCode(this.code);
			entity.setParentId(this.parentId);
			entity.setUnitId(this.unitId);
			entity.setDescription(this.description);
			entity.setIsActive(this.isActive);
			entity.setIsVisible(this.isVisible);
			entity.setIsDevice(this.isDevice);
			entity.setDefaultPrice(this.defaultPrice);
			entity.setCurId(this.curId);
			entity.setPath(this.path);
			entity.setSerialRequired(this.serialRequired);
			entity.setMinDeprePeriod(this.minDeprePeriod);
			entity.setMaintainPeriod(this.maintainPeriod);
			entity.setMaintainCost(this.maintainCost);
			entity.setMaxDeprePeriod(this.maxDeprePeriod);
			entity.setAmortMode(this.amortMode);
			entity.setType(this.type);
			entity.setIsGroup(this.isGroup);
			entity.setIncreaseAssetAccId(this.increaseAssetAccId);
			entity.setDeptAccId(this.deptAccId);
			entity.setCreditAccId(this.creditAccId);
			entity.setPartnumberRequired(this.partnumberRequired);
			entity.setIsAccepted(this.isAccepted);
			entity.setCreatorId(this.creatorId);
			entity.setIsInFdmanager(this.isInFdmanager);
			entity.setEnName(this.enName);
			entity.setAbbreviate(this.abbreviate);
			entity.setOldId(this.oldId);
			entity.setOldType(this.oldType);
			entity.setOldCode(this.oldCode);
			entity.setPartNumber(this.partNumber);
			entity.setBkCode(this.bkCode);
			entity.setModifiedTime(this.modifiedTime);
			entity.setUpdaterId(this.updaterId);
			entity.setProposalNote(this.proposalNote);
			entity.setProposalStatus(this.proposalStatus);
			entity.setProposalPath(this.proposalPath);
			entity.setRefMerId(this.refMerId);
			entity.setMerType(this.merType);
			entity.setMark(this.mark);
			entity.setCdcMerchandiseId(this.cdcMerchandiseId);
			entity.setFullName(this.fullName);
			entity.setCatGroupType(this.catGroupType);
			entity.setContractId(this.contractId);
			entity.setIsSpending(this.isSpending);
			entity.setIsAccounting(this.isAccounting);
			entity.setCdcktMerchandiseId(this.cdcktMerchandiseId);
			entity.setMerExpType(this.merExpType);
			entity.setWarningContent(this.warningContent);
			entity.setCycleKdhc(this.cycleKdhc);
			entity.setTypeKdhc(this.typeKdhc);
			entity.setCycleBd(this.cycleBd);
			entity.setGroupType(this.groupType);
			entity.setTheLevel(this.theLevel);
			entity.setChangeSerialRequire(this.changeSerialRequire);
			entity.setTestingPeriod(this.testingPeriod);
			entity.setCalibrationPeriod(this.calibrationPeriod);
			entity.setCharacter(this.character);
			entity.setFunction(this.function);
			entity.setExpiredDate(this.expiredDate);
			entity.setLifeTime(this.lifeTime);
			entity.setCatAssetFixedId(this.catAssetFixedId);
			entity.setCatSourceCreatedId(this.catSourceCreatedId);
			entity.setIsMerType(this.isMerType);
	}
	
		public Long getMerchandiseId() {
			return merchandiseId;
		}
		public void setMerchandiseId(Long merchandiseId) {
			this.merchandiseId = merchandiseId;
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
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public Long getUnitId() {
			return unitId;
		}
		public void setUnitId(Long unitId) {
			this.unitId = unitId;
		}		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
		public Long getIsActive() {
			return isActive;
		}
		public void setIsActive(Long isActive) {
			this.isActive = isActive;
		}		
		public Long getIsVisible() {
			return isVisible;
		}
		public void setIsVisible(Long isVisible) {
			this.isVisible = isVisible;
		}		
		public Long getIsDevice() {
			return isDevice;
		}
		public void setIsDevice(Long isDevice) {
			this.isDevice = isDevice;
		}		
		public Double getDefaultPrice() {
			return defaultPrice;
		}
		public void setDefaultPrice(Double defaultPrice) {
			this.defaultPrice = defaultPrice;
		}		
		public Long getCurId() {
			return curId;
		}
		public void setCurId(Long curId) {
			this.curId = curId;
		}		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}		
		public Long getSerialRequired() {
			return serialRequired;
		}
		public void setSerialRequired(Long serialRequired) {
			this.serialRequired = serialRequired;
		}		
		public Long getMinDeprePeriod() {
			return minDeprePeriod;
		}
		public void setMinDeprePeriod(Long minDeprePeriod) {
			this.minDeprePeriod = minDeprePeriod;
		}		
		public Long getMaintainPeriod() {
			return maintainPeriod;
		}
		public void setMaintainPeriod(Long maintainPeriod) {
			this.maintainPeriod = maintainPeriod;
		}		
		public Long getMaintainCost() {
			return maintainCost;
		}
		public void setMaintainCost(Long maintainCost) {
			this.maintainCost = maintainCost;
		}		
		public Long getMaxDeprePeriod() {
			return maxDeprePeriod;
		}
		public void setMaxDeprePeriod(Long maxDeprePeriod) {
			this.maxDeprePeriod = maxDeprePeriod;
		}		
		public Long getAmortMode() {
			return amortMode;
		}
		public void setAmortMode(Long amortMode) {
			this.amortMode = amortMode;
		}		
		public Long getType() {
			return type;
		}
		public void setType(Long type) {
			this.type = type;
		}		
		public Long getIsGroup() {
			return isGroup;
		}
		public void setIsGroup(Long isGroup) {
			this.isGroup = isGroup;
		}		
		public Long getIncreaseAssetAccId() {
			return increaseAssetAccId;
		}
		public void setIncreaseAssetAccId(Long increaseAssetAccId) {
			this.increaseAssetAccId = increaseAssetAccId;
		}		
		public Long getDeptAccId() {
			return deptAccId;
		}
		public void setDeptAccId(Long deptAccId) {
			this.deptAccId = deptAccId;
		}		
		public Long getCreditAccId() {
			return creditAccId;
		}
		public void setCreditAccId(Long creditAccId) {
			this.creditAccId = creditAccId;
		}		
		public Long getPartnumberRequired() {
			return partnumberRequired;
		}
		public void setPartnumberRequired(Long partnumberRequired) {
			this.partnumberRequired = partnumberRequired;
		}		
		public Long getIsAccepted() {
			return isAccepted;
		}
		public void setIsAccepted(Long isAccepted) {
			this.isAccepted = isAccepted;
		}		
		public Long getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(Long creatorId) {
			this.creatorId = creatorId;
		}		
		public Long getIsInFdmanager() {
			return isInFdmanager;
		}
		public void setIsInFdmanager(Long isInFdmanager) {
			this.isInFdmanager = isInFdmanager;
		}		
		public String getEnName() {
			return enName;
		}
		public void setEnName(String enName) {
			this.enName = enName;
		}		
		public String getAbbreviate() {
			return abbreviate;
		}
		public void setAbbreviate(String abbreviate) {
			this.abbreviate = abbreviate;
		}		
		public Long getOldId() {
			return oldId;
		}
		public void setOldId(Long oldId) {
			this.oldId = oldId;
		}		
		public Long getOldType() {
			return oldType;
		}
		public void setOldType(Long oldType) {
			this.oldType = oldType;
		}		
		public String getOldCode() {
			return oldCode;
		}
		public void setOldCode(String oldCode) {
			this.oldCode = oldCode;
		}		
		public String getPartNumber() {
			return partNumber;
		}
		public void setPartNumber(String partNumber) {
			this.partNumber = partNumber;
		}		
		public String getBkCode() {
			return bkCode;
		}
		public void setBkCode(String bkCode) {
			this.bkCode = bkCode;
		}		
		public Date getModifiedTime() {
			return modifiedTime;
		}
		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}		
		public Long getUpdaterId() {
			return updaterId;
		}
		public void setUpdaterId(Long updaterId) {
			this.updaterId = updaterId;
		}		
		public String getProposalNote() {
			return proposalNote;
		}
		public void setProposalNote(String proposalNote) {
			this.proposalNote = proposalNote;
		}		
		public Long getProposalStatus() {
			return proposalStatus;
		}
		public void setProposalStatus(Long proposalStatus) {
			this.proposalStatus = proposalStatus;
		}		
		public String getProposalPath() {
			return proposalPath;
		}
		public void setProposalPath(String proposalPath) {
			this.proposalPath = proposalPath;
		}		
		public Long getRefMerId() {
			return refMerId;
		}
		public void setRefMerId(Long refMerId) {
			this.refMerId = refMerId;
		}		
		public Long getMerType() {
			return merType;
		}
		public void setMerType(Long merType) {
			this.merType = merType;
		}		
		public Long getMark() {
			return mark;
		}
		public void setMark(Long mark) {
			this.mark = mark;
		}		
		public Long getCdcMerchandiseId() {
			return cdcMerchandiseId;
		}
		public void setCdcMerchandiseId(Long cdcMerchandiseId) {
			this.cdcMerchandiseId = cdcMerchandiseId;
		}		
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}		
		public String getCatGroupType() {
			return catGroupType;
		}
		public void setCatGroupType(String catGroupType) {
			this.catGroupType = catGroupType;
		}		
		public Long getContractId() {
			return contractId;
		}
		public void setContractId(Long contractId) {
			this.contractId = contractId;
		}		
		public Long getIsSpending() {
			return isSpending;
		}
		public void setIsSpending(Long isSpending) {
			this.isSpending = isSpending;
		}		
		public Long getIsAccounting() {
			return isAccounting;
		}
		public void setIsAccounting(Long isAccounting) {
			this.isAccounting = isAccounting;
		}		
		public Long getCdcktMerchandiseId() {
			return cdcktMerchandiseId;
		}
		public void setCdcktMerchandiseId(Long cdcktMerchandiseId) {
			this.cdcktMerchandiseId = cdcktMerchandiseId;
		}		
		public Long getMerExpType() {
			return merExpType;
		}
		public void setMerExpType(Long merExpType) {
			this.merExpType = merExpType;
		}		
		public String getWarningContent() {
			return warningContent;
		}
		public void setWarningContent(String warningContent) {
			this.warningContent = warningContent;
		}		
		public Long getCycleKdhc() {
			return cycleKdhc;
		}
		public void setCycleKdhc(Long cycleKdhc) {
			this.cycleKdhc = cycleKdhc;
		}		
		public Long getTypeKdhc() {
			return typeKdhc;
		}
		public void setTypeKdhc(Long typeKdhc) {
			this.typeKdhc = typeKdhc;
		}		
		public Long getCycleBd() {
			return cycleBd;
		}
		public void setCycleBd(Long cycleBd) {
			this.cycleBd = cycleBd;
		}		
		public Long getGroupType() {
			return groupType;
		}
		public void setGroupType(Long groupType) {
			this.groupType = groupType;
		}		
		public Long getTheLevel() {
			return theLevel;
		}
		public void setTheLevel(Long theLevel) {
			this.theLevel = theLevel;
		}		
		public Long getChangeSerialRequire() {
			return changeSerialRequire;
		}
		public void setChangeSerialRequire(Long changeSerialRequire) {
			this.changeSerialRequire = changeSerialRequire;
		}		
		public Long getTestingPeriod() {
			return testingPeriod;
		}
		public void setTestingPeriod(Long testingPeriod) {
			this.testingPeriod = testingPeriod;
		}		
		public Long getCalibrationPeriod() {
			return calibrationPeriod;
		}
		public void setCalibrationPeriod(Long calibrationPeriod) {
			this.calibrationPeriod = calibrationPeriod;
		}		
		public String getCharacter() {
			return character;
		}
		public void setCharacter(String character) {
			this.character = character;
		}		
		public String getFunction() {
			return function;
		}
		public void setFunction(String function) {
			this.function = function;
		}		
		public Double getExpiredDate() {
			return expiredDate;
		}
		public void setExpiredDate(Double expiredDate) {
			this.expiredDate = expiredDate;
		}		
		public Double getLifeTime() {
			return lifeTime;
		}
		public void setLifeTime(Double lifeTime) {
			this.lifeTime = lifeTime;
		}		
		public Long getCatAssetFixedId() {
			return catAssetFixedId;
		}
		public void setCatAssetFixedId(Long catAssetFixedId) {
			this.catAssetFixedId = catAssetFixedId;
		}		
		public Long getCatSourceCreatedId() {
			return catSourceCreatedId;
		}
		public void setCatSourceCreatedId(Long catSourceCreatedId) {
			this.catSourceCreatedId = catSourceCreatedId;
		}		
		public Long getIsMerType() {
			return isMerType;
		}
		public void setIsMerType(Long isMerType) {
			this.isMerType = isMerType;
		}		
	
}
