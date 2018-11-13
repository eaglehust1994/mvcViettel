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
@Table(name = "CAT_MERCHANDISE")
public class CatMerchandise implements Serializable {
    
	private static final long serialVersionUID = 1L;

	public interface Columns {

		public static final String MERCHANDISE_ID = "merchandiseId";
		public static final String NAME = "name";
		public static final String CODE = "code";
		public static final String PARENT_ID = "parentId";
		public static final String UNIT_ID = "unitId";
		public static final String DESCRIPTION = "description";
		public static final String IS_ACTIVE = "isActive";
		public static final String IS_VISIBLE = "isVisible";
		public static final String IS_DEVICE = "isDevice";
		public static final String DEFAULT_PRICE = "defaultPrice";
		public static final String CUR_ID = "curId";
		public static final String PATH = "path";
		public static final String SERIAL_REQUIRED = "serialRequired";
		public static final String MIN_DEPRE_PERIOD = "minDeprePeriod";
		public static final String MAINTAIN_PERIOD = "maintainPeriod";
		public static final String MAINTAIN_COST = "maintainCost";
		public static final String MAX_DEPRE_PERIOD = "maxDeprePeriod";
		public static final String AMORT_MODE = "amortMode";
		public static final String TYPE = "type";
		public static final String IS_GROUP = "isGroup";
		public static final String INCREASE_ASSET_ACC_ID = "increaseAssetAccId";
		public static final String DEPT_ACC_ID = "deptAccId";
		public static final String CREDIT_ACC_ID = "creditAccId";
		public static final String PARTNUMBER_REQUIRED = "partnumberRequired";
		public static final String IS_ACCEPTED = "isAccepted";
		public static final String CREATOR_ID = "creatorId";
		public static final String IS_IN_FDMANAGER = "isInFdmanager";
		public static final String EN_NAME = "enName";
		public static final String ABBREVIATE = "abbreviate";
		public static final String OLD_ID = "oldId";
		public static final String OLD_TYPE = "oldType";
		public static final String OLD_CODE = "oldCode";
		public static final String PART_NUMBER = "partNumber";
		public static final String BK_CODE = "bkCode";
		public static final String MODIFIED_TIME = "modifiedTime";
		public static final String UPDATER_ID = "updaterId";
		public static final String PROPOSAL_NOTE = "proposalNote";
		public static final String PROPOSAL_STATUS = "proposalStatus";
		public static final String PROPOSAL_PATH = "proposalPath";
		public static final String REF_MER_ID = "refMerId";
		public static final String MER_TYPE = "merType";
		public static final String MARK = "mark";
		public static final String CDC_MERCHANDISE_ID = "cdcMerchandiseId";
		public static final String FULL_NAME = "fullName";
		public static final String CAT_GROUP_TYPE = "catGroupType";
		public static final String CONTRACT_ID = "contractId";
		public static final String IS_SPENDING = "isSpending";
		public static final String IS_ACCOUNTING = "isAccounting";
		public static final String CDCKT_MERCHANDISE_ID = "cdcktMerchandiseId";
		public static final String MER_EXP_TYPE = "merExpType";
		public static final String WARNING_CONTENT = "warningContent";
		public static final String CYCLE_KDHC = "cycleKdhc";
		public static final String TYPE_KDHC = "typeKdhc";
		public static final String CYCLE_BD = "cycleBd";
		public static final String GROUP_TYPE = "groupType";
		public static final String THE_LEVEL = "theLevel";
		public static final String CHANGE_SERIAL_REQUIRE = "changeSerialRequire";
		public static final String TESTING_PERIOD = "testingPeriod";
		public static final String CALIBRATION_PERIOD = "calibrationPeriod";
		public static final String CHARACTER = "character";
		public static final String FUNCTION = "function";
		public static final String EXPIRED_DATE = "expiredDate";
		public static final String LIFE_TIME = "lifeTime";
		public static final String CAT_ASSET_FIXED_ID = "catAssetFixedId";
		public static final String CAT_SOURCE_CREATED_ID = "catSourceCreatedId";
		public static final String IS_MER_TYPE = "isMerType";

	}

	public interface Constants {
		public static final String TABLE_NAME = "CAT_MERCHANDISE";
	}

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "CAT_MERCHANDISE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@Column(name = "MERCHANDISE_ID")
	private Long merchandiseId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@Column(name = "PARENT_ID")
	private Long parentId;
	@Column(name = "UNIT_ID")
	private Long unitId;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "IS_ACTIVE")
	private Long isActive;
	@Column(name = "IS_VISIBLE")
	private Long isVisible;
	@Column(name = "IS_DEVICE")
	private Long isDevice;
	@Column(name = "DEFAULT_PRICE")
	private Double defaultPrice;
	@Column(name = "CUR_ID")
	private Long curId;
	@Column(name = "PATH")
	private String path;
	@Column(name = "SERIAL_REQUIRED")
	private Long serialRequired;
	@Column(name = "MIN_DEPRE_PERIOD")
	private Long minDeprePeriod;
	@Column(name = "MAINTAIN_PERIOD")
	private Long maintainPeriod;
	@Column(name = "MAINTAIN_COST")
	private Long maintainCost;
	@Column(name = "MAX_DEPRE_PERIOD")
	private Long maxDeprePeriod;
	@Column(name = "AMORT_MODE")
	private Long amortMode;
	@Column(name = "TYPE")
	private Long type;
	@Column(name = "IS_GROUP")
	private Long isGroup;
	@Column(name = "INCREASE_ASSET_ACC_ID")
	private Long increaseAssetAccId;
	@Column(name = "DEPT_ACC_ID")
	private Long deptAccId;
	@Column(name = "CREDIT_ACC_ID")
	private Long creditAccId;
	@Column(name = "PARTNUMBER_REQUIRED")
	private Long partnumberRequired;
	@Column(name = "IS_ACCEPTED")
	private Long isAccepted;
	@Column(name = "CREATOR_ID")
	private Long creatorId;
	@Column(name = "IS_IN_FDMANAGER")
	private Long isInFdmanager;
	@Column(name = "EN_NAME")
	private String enName;
	@Column(name = "ABBREVIATE")
	private String abbreviate;
	@Column(name = "OLD_ID")
	private Long oldId;
	@Column(name = "OLD_TYPE")
	private Long oldType;
	@Column(name = "OLD_CODE")
	private String oldCode;
	@Column(name = "PART_NUMBER")
	private String partNumber;
	@Column(name = "BK_CODE")
	private String bkCode;
	@Column(name = "MODIFIED_TIME")
	private Date modifiedTime;
	@Column(name = "UPDATER_ID")
	private Long updaterId;
	@Column(name = "PROPOSAL_NOTE")
	private String proposalNote;
	@Column(name = "PROPOSAL_STATUS")
	private Long proposalStatus;
	@Column(name = "PROPOSAL_PATH")
	private String proposalPath;
	@Column(name = "REF_MER_ID")
	private Long refMerId;
	@Column(name = "MER_TYPE")
	private Long merType;
	@Column(name = "MARK")
	private Long mark;
	@Column(name = "CDC_MERCHANDISE_ID")
	private Long cdcMerchandiseId;
	@Column(name = "FULL_NAME")
	private String fullName;
	@Column(name = "CAT_GROUP_TYPE")
	private String catGroupType;
	@Column(name = "CONTRACT_ID")
	private Long contractId;
	@Column(name = "IS_SPENDING")
	private Long isSpending;
	@Column(name = "IS_ACCOUNTING")
	private Long isAccounting;
	@Column(name = "CDCKT_MERCHANDISE_ID")
	private Long cdcktMerchandiseId;
	@Column(name = "MER_EXP_TYPE")
	private Long merExpType;
	@Column(name = "WARNING_CONTENT")
	private String warningContent;
	@Column(name = "CYCLE_KDHC")
	private Long cycleKdhc;
	@Column(name = "TYPE_KDHC")
	private Long typeKdhc;
	@Column(name = "CYCLE_BD")
	private Long cycleBd;
	@Column(name = "GROUP_TYPE")
	private Long groupType;
	@Column(name = "THE_LEVEL")
	private Long theLevel;
	@Column(name = "CHANGE_SERIAL_REQUIRE")
	private Long changeSerialRequire;
	@Column(name = "TESTING_PERIOD")
	private Long testingPeriod;
	@Column(name = "CALIBRATION_PERIOD")
	private Long calibrationPeriod;
	@Column(name = "CHARACTER")
	private String character;
	@Column(name = "FUNCTION")
	private String function;
	@Column(name = "EXPIRED_DATE")
	private Double expiredDate;
	@Column(name = "LIFE_TIME")
	private Double lifeTime;
	@Column(name = "CAT_ASSET_FIXED_ID")
	private Long catAssetFixedId;
	@Column(name = "CAT_SOURCE_CREATED_ID")
	private Long catSourceCreatedId;
	@Column(name = "IS_MER_TYPE")
	private Long isMerType;

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
