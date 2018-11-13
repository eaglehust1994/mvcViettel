/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.CatMerchandiseDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity (name = "catmerchandise")
@Table(name = "CAT_MERCHANDISE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatMerchandiseBO extends BaseFWModelImpl {
	
private List<MerEntityBO> merentity = Lists.newArrayList();
//private CatUnitBO catunit ;
private java.lang.String proposalNote;
private java.lang.Long proposalStatus;
private java.lang.String proposalPath;
private java.lang.Long refMerId;
private java.lang.Long merType;
private java.lang.Long mark;
private java.lang.Long cdcMerchandiseId;
private java.lang.String fullName;
private java.lang.String catGroupType;
private java.lang.Long contractId;
private java.lang.Long isSpending;
private java.lang.Long isAccounting;
private java.lang.Long cdcktMerchandiseId;
private java.lang.Long merExpType;
private java.lang.String warningContent;
private java.lang.Long cycleKdhc;
private java.lang.Long typeKdhc;
private java.lang.Long cycleBd;
private java.lang.Long groupType;
private java.lang.Long theLevel;
private java.lang.Long changeSerialRequire;
private java.lang.Long testingPeriod;
private java.lang.Long calibrationPeriod;
private java.lang.String character;
private java.lang.String function;
private java.lang.Double expiredDate;
private java.lang.Double lifeTime;
private java.lang.Long catAssetFixedId;
private java.lang.Long catSourceCreatedId;
private java.lang.Long isMerType;
private java.lang.Long merchandiseId;
private java.lang.String name;
private java.lang.String code;
private java.lang.Long parentId;
private java.lang.Long unitId;
private java.lang.String description;
private java.lang.Long isActive;
private java.lang.Long isVisible;
private java.lang.Long isDevice;
private java.lang.Double defaultPrice;
private java.lang.Long curId;
private java.lang.String path;
private java.lang.Long serialRequired;
private java.lang.Long minDeprePeriod;
private java.lang.Long maintainPeriod;
private java.lang.Long maintainCost;
private java.lang.Long maxDeprePeriod;
private java.lang.Long amortMode;
private java.lang.Long type;
private java.lang.Long isGroup;
private java.lang.Long increaseAssetAccId;
private java.lang.Long deptAccId;
private java.lang.Long creditAccId;
private java.lang.Long partnumberRequired;
private java.lang.Long isAccepted;
private java.lang.Long creatorId;
private java.lang.Long isInFdmanager;
private java.lang.String enName;
private java.lang.String abbreviate;
private java.lang.Long oldId;
private java.lang.Long oldType;
private java.lang.String oldCode;
private java.lang.String partNumber;
private java.lang.String bkCode;
private java.util.Date modifiedTime;
private java.lang.Long updaterId;

 public CatMerchandiseBO(java.lang.Long merchandiseId ) {
         this.merchandiseId = merchandiseId;
}
 public CatMerchandiseBO() {
     setColId("merchandiseId");
     setColName("merchandiseId");
     setUniqueColumn(new String[]{"merchandiseId"});
}
@Column(name = "PROPOSAL_NOTE", length = 1000)
public java.lang.String getProposalNote(){
return proposalNote;
}
public void setProposalNote(java.lang.String proposalNote)
{
this.proposalNote = proposalNote;
}
@Column(name = "PROPOSAL_STATUS", length = 22)
public java.lang.Long getProposalStatus(){
return proposalStatus;
}
public void setProposalStatus(java.lang.Long proposalStatus)
{
this.proposalStatus = proposalStatus;
}
@Column(name = "PROPOSAL_PATH", length = 200)
public java.lang.String getProposalPath(){
return proposalPath;
}
public void setProposalPath(java.lang.String proposalPath)
{
this.proposalPath = proposalPath;
}
@Column(name = "REF_MER_ID", length = 22)
public java.lang.Long getRefMerId(){
return refMerId;
}
public void setRefMerId(java.lang.Long refMerId)
{
this.refMerId = refMerId;
}
@Column(name = "MER_TYPE", length = 22)
public java.lang.Long getMerType(){
return merType;
}
public void setMerType(java.lang.Long merType)
{
this.merType = merType;
}
@Column(name = "MARK", length = 22)
public java.lang.Long getMark(){
return mark;
}
public void setMark(java.lang.Long mark)
{
this.mark = mark;
}
@Column(name = "CDC_MERCHANDISE_ID", length = 22)
public java.lang.Long getCdcMerchandiseId(){
return cdcMerchandiseId;
}
public void setCdcMerchandiseId(java.lang.Long cdcMerchandiseId)
{
this.cdcMerchandiseId = cdcMerchandiseId;
}
@Column(name = "FULL_NAME", length = 2000)
public java.lang.String getFullName(){
return fullName;
}
public void setFullName(java.lang.String fullName)
{
this.fullName = fullName;
}
@Column(name = "CAT_GROUP_TYPE", length = 200)
public java.lang.String getCatGroupType(){
return catGroupType;
}
public void setCatGroupType(java.lang.String catGroupType)
{
this.catGroupType = catGroupType;
}
@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Long getContractId(){
return contractId;
}
public void setContractId(java.lang.Long contractId)
{
this.contractId = contractId;
}
@Column(name = "IS_SPENDING", length = 22)
public java.lang.Long getIsSpending(){
return isSpending;
}
public void setIsSpending(java.lang.Long isSpending)
{
this.isSpending = isSpending;
}
@Column(name = "IS_ACCOUNTING", length = 22)
public java.lang.Long getIsAccounting(){
return isAccounting;
}
public void setIsAccounting(java.lang.Long isAccounting)
{
this.isAccounting = isAccounting;
}
@Column(name = "CDCKT_MERCHANDISE_ID", length = 22)
public java.lang.Long getCdcktMerchandiseId(){
return cdcktMerchandiseId;
}
public void setCdcktMerchandiseId(java.lang.Long cdcktMerchandiseId)
{
this.cdcktMerchandiseId = cdcktMerchandiseId;
}
@Column(name = "MER_EXP_TYPE", length = 22)
public java.lang.Long getMerExpType(){
return merExpType;
}
public void setMerExpType(java.lang.Long merExpType)
{
this.merExpType = merExpType;
}
@Column(name = "WARNING_CONTENT", length = 1200)
public java.lang.String getWarningContent(){
return warningContent;
}
public void setWarningContent(java.lang.String warningContent)
{
this.warningContent = warningContent;
}
@Column(name = "CYCLE_KDHC", length = 22)
public java.lang.Long getCycleKdhc(){
return cycleKdhc;
}
public void setCycleKdhc(java.lang.Long cycleKdhc)
{
this.cycleKdhc = cycleKdhc;
}
@Column(name = "TYPE_KDHC", length = 22)
public java.lang.Long getTypeKdhc(){
return typeKdhc;
}
public void setTypeKdhc(java.lang.Long typeKdhc)
{
this.typeKdhc = typeKdhc;
}
@Column(name = "CYCLE_BD", length = 22)
public java.lang.Long getCycleBd(){
return cycleBd;
}
public void setCycleBd(java.lang.Long cycleBd)
{
this.cycleBd = cycleBd;
}
@Column(name = "GROUP_TYPE", length = 22)
public java.lang.Long getGroupType(){
return groupType;
}
public void setGroupType(java.lang.Long groupType)
{
this.groupType = groupType;
}
@Column(name = "THE_LEVEL", length = 22)
public java.lang.Long getTheLevel(){
return theLevel;
}
public void setTheLevel(java.lang.Long theLevel)
{
this.theLevel = theLevel;
}
@Column(name = "CHANGE_SERIAL_REQUIRE", length = 22)
public java.lang.Long getChangeSerialRequire(){
return changeSerialRequire;
}
public void setChangeSerialRequire(java.lang.Long changeSerialRequire)
{
this.changeSerialRequire = changeSerialRequire;
}
@Column(name = "TESTING_PERIOD", length = 22)
public java.lang.Long getTestingPeriod(){
return testingPeriod;
}
public void setTestingPeriod(java.lang.Long testingPeriod)
{
this.testingPeriod = testingPeriod;
}
@Column(name = "CALIBRATION_PERIOD", length = 22)
public java.lang.Long getCalibrationPeriod(){
return calibrationPeriod;
}
public void setCalibrationPeriod(java.lang.Long calibrationPeriod)
{
this.calibrationPeriod = calibrationPeriod;
}
@Column(name = "CHARACTER", length = 2000)
public java.lang.String getCharacter(){
return character;
}
public void setCharacter(java.lang.String character)
{
this.character = character;
}
@Column(name = "FUNCTION", length = 4000)
public java.lang.String getFunction(){
return function;
}
public void setFunction(java.lang.String function)
{
this.function = function;
}
@Column(name = "EXPIRED_DATE", length = 22)
public java.lang.Double getExpiredDate(){
return expiredDate;
}
public void setExpiredDate(java.lang.Double expiredDate)
{
this.expiredDate = expiredDate;
}
@Column(name = "LIFE_TIME", length = 22)
public java.lang.Double getLifeTime(){
return lifeTime;
}
public void setLifeTime(java.lang.Double lifeTime)
{
this.lifeTime = lifeTime;
}
@Column(name = "CAT_ASSET_FIXED_ID", length = 22)
public java.lang.Long getCatAssetFixedId(){
return catAssetFixedId;
}
public void setCatAssetFixedId(java.lang.Long catAssetFixedId)
{
this.catAssetFixedId = catAssetFixedId;
}
@Column(name = "CAT_SOURCE_CREATED_ID", length = 22)
public java.lang.Long getCatSourceCreatedId(){
return catSourceCreatedId;
}
public void setCatSourceCreatedId(java.lang.Long catSourceCreatedId)
{
this.catSourceCreatedId = catSourceCreatedId;
}
@Column(name = "IS_MER_TYPE", length = 22)
public java.lang.Long getIsMerType(){
return isMerType;
}
public void setIsMerType(java.lang.Long isMerType)
{
this.isMerType = isMerType;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_MERCHANDISE_SEQ")
            }
    )
@Column(name = "MERCHANDISE_ID", length = 22)
public java.lang.Long getMerchandiseId(){
return merchandiseId;
}
public void setMerchandiseId(java.lang.Long merchandiseId)
{
this.merchandiseId = merchandiseId;
}
@Column(name = "NAME", length = 1000)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
/*@Column(name = "UNIT_ID", length = 22)
public java.lang.Long getUnitId(){
return unitId;
}
public void setUnitId(java.lang.Long unitId)
{
this.unitId = unitId;
}*/

//@ManyToOne
//@JoinColumn (name = "UNIT_ID")
//public CatUnitBO getCatunit(){
//	return catunit;
//}
//public void setCatunit(CatUnitBO catunit){
//	this.catunit = catunit;
//}



@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "IS_VISIBLE", length = 22)
public java.lang.Long getIsVisible(){
return isVisible;
}
public void setIsVisible(java.lang.Long isVisible)
{
this.isVisible = isVisible;
}
@Column(name = "IS_DEVICE", length = 22)
public java.lang.Long getIsDevice(){
return isDevice;
}
public void setIsDevice(java.lang.Long isDevice)
{
this.isDevice = isDevice;
}
@Column(name = "DEFAULT_PRICE", length = 22)
public java.lang.Double getDefaultPrice(){
return defaultPrice;
}
public void setDefaultPrice(java.lang.Double defaultPrice)
{
this.defaultPrice = defaultPrice;
}
@Column(name = "CUR_ID", length = 22)
public java.lang.Long getCurId(){
return curId;
}
public void setCurId(java.lang.Long curId)
{
this.curId = curId;
}
@Column(name = "PATH", length = 2000)
public java.lang.String getPath(){
return path;
}
public void setPath(java.lang.String path)
{
this.path = path;
}
@Column(name = "SERIAL_REQUIRED", length = 22)
public java.lang.Long getSerialRequired(){
return serialRequired;
}
public void setSerialRequired(java.lang.Long serialRequired)
{
this.serialRequired = serialRequired;
}
@Column(name = "MIN_DEPRE_PERIOD", length = 22)
public java.lang.Long getMinDeprePeriod(){
return minDeprePeriod;
}
public void setMinDeprePeriod(java.lang.Long minDeprePeriod)
{
this.minDeprePeriod = minDeprePeriod;
}
@Column(name = "MAINTAIN_PERIOD", length = 22)
public java.lang.Long getMaintainPeriod(){
return maintainPeriod;
}
public void setMaintainPeriod(java.lang.Long maintainPeriod)
{
this.maintainPeriod = maintainPeriod;
}
@Column(name = "MAINTAIN_COST", length = 22)
public java.lang.Long getMaintainCost(){
return maintainCost;
}
public void setMaintainCost(java.lang.Long maintainCost)
{
this.maintainCost = maintainCost;
}
@Column(name = "MAX_DEPRE_PERIOD", length = 22)
public java.lang.Long getMaxDeprePeriod(){
return maxDeprePeriod;
}
public void setMaxDeprePeriod(java.lang.Long maxDeprePeriod)
{
this.maxDeprePeriod = maxDeprePeriod;
}
@Column(name = "AMORT_MODE", length = 22)
public java.lang.Long getAmortMode(){
return amortMode;
}
public void setAmortMode(java.lang.Long amortMode)
{
this.amortMode = amortMode;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "IS_GROUP", length = 22)
public java.lang.Long getIsGroup(){
return isGroup;
}
public void setIsGroup(java.lang.Long isGroup)
{
this.isGroup = isGroup;
}
@Column(name = "INCREASE_ASSET_ACC_ID", length = 22)
public java.lang.Long getIncreaseAssetAccId(){
return increaseAssetAccId;
}
public void setIncreaseAssetAccId(java.lang.Long increaseAssetAccId)
{
this.increaseAssetAccId = increaseAssetAccId;
}
@Column(name = "DEPT_ACC_ID", length = 22)
public java.lang.Long getDeptAccId(){
return deptAccId;
}
public void setDeptAccId(java.lang.Long deptAccId)
{
this.deptAccId = deptAccId;
}
@Column(name = "CREDIT_ACC_ID", length = 22)
public java.lang.Long getCreditAccId(){
return creditAccId;
}
public void setCreditAccId(java.lang.Long creditAccId)
{
this.creditAccId = creditAccId;
}
@Column(name = "PARTNUMBER_REQUIRED", length = 22)
public java.lang.Long getPartnumberRequired(){
return partnumberRequired;
}
public void setPartnumberRequired(java.lang.Long partnumberRequired)
{
this.partnumberRequired = partnumberRequired;
}
@Column(name = "IS_ACCEPTED", length = 22)
public java.lang.Long getIsAccepted(){
return isAccepted;
}
public void setIsAccepted(java.lang.Long isAccepted)
{
this.isAccepted = isAccepted;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "IS_IN_FDMANAGER", length = 22)
public java.lang.Long getIsInFdmanager(){
return isInFdmanager;
}
public void setIsInFdmanager(java.lang.Long isInFdmanager)
{
this.isInFdmanager = isInFdmanager;
}
@Column(name = "EN_NAME", length = 600)
public java.lang.String getEnName(){
return enName;
}
public void setEnName(java.lang.String enName)
{
this.enName = enName;
}
@Column(name = "ABBREVIATE", length = 600)
public java.lang.String getAbbreviate(){
return abbreviate;
}
public void setAbbreviate(java.lang.String abbreviate)
{
this.abbreviate = abbreviate;
}
@Column(name = "OLD_ID", length = 22)
public java.lang.Long getOldId(){
return oldId;
}
public void setOldId(java.lang.Long oldId)
{
this.oldId = oldId;
}
@Column(name = "OLD_TYPE", length = 22)
public java.lang.Long getOldType(){
return oldType;
}
public void setOldType(java.lang.Long oldType)
{
this.oldType = oldType;
}
@Column(name = "OLD_CODE", length = 100)
public java.lang.String getOldCode(){
return oldCode;
}
public void setOldCode(java.lang.String oldCode)
{
this.oldCode = oldCode;
}
@Column(name = "PART_NUMBER", length = 100)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
}
@Column(name = "BK_CODE", length = 50)
public java.lang.String getBkCode(){
return bkCode;
}
public void setBkCode(java.lang.String bkCode)
{
this.bkCode = bkCode;
}
@Column(name = "MODIFIED_TIME", length = 7)
public java.util.Date getModifiedTime(){
return modifiedTime;
}
public void setModifiedTime(java.util.Date modifiedTime)
{
this.modifiedTime = modifiedTime;
}
@Column(name = "UPDATER_ID", length = 22)
public java.lang.Long getUpdaterId(){
return updaterId;
}
public void setUpdaterId(java.lang.Long updaterId)
{
this.updaterId = updaterId;
}

@OneToMany(mappedBy = "catmerchandise")
public List<MerEntityBO> getMerentity(){
	return merentity;
}
public void setMerentity(List<MerEntityBO> merentity){
	this.merentity = merentity;
}
   

    @Override
    public CatMerchandiseDTO toDTO() {
        CatMerchandiseDTO catMerchandiseDTO = new CatMerchandiseDTO();
        //set cac gia tri 
        catMerchandiseDTO.setProposalNote(this.proposalNote);
        catMerchandiseDTO.setProposalStatus(this.proposalStatus);
        catMerchandiseDTO.setProposalPath(this.proposalPath);
        catMerchandiseDTO.setRefMerId(this.refMerId);
        catMerchandiseDTO.setMerType(this.merType);
        catMerchandiseDTO.setMark(this.mark);
        catMerchandiseDTO.setCdcMerchandiseId(this.cdcMerchandiseId);
        catMerchandiseDTO.setFullName(this.fullName);
        catMerchandiseDTO.setCatGroupType(this.catGroupType);
        catMerchandiseDTO.setContractId(this.contractId);
        catMerchandiseDTO.setIsSpending(this.isSpending);
        catMerchandiseDTO.setIsAccounting(this.isAccounting);
        catMerchandiseDTO.setCdcktMerchandiseId(this.cdcktMerchandiseId);
        catMerchandiseDTO.setMerExpType(this.merExpType);
        catMerchandiseDTO.setWarningContent(this.warningContent);
        catMerchandiseDTO.setCycleKdhc(this.cycleKdhc);
        catMerchandiseDTO.setTypeKdhc(this.typeKdhc);
        catMerchandiseDTO.setCycleBd(this.cycleBd);
        catMerchandiseDTO.setGroupType(this.groupType);
        catMerchandiseDTO.setTheLevel(this.theLevel);
        catMerchandiseDTO.setChangeSerialRequire(this.changeSerialRequire);
        catMerchandiseDTO.setTestingPeriod(this.testingPeriod);
        catMerchandiseDTO.setCalibrationPeriod(this.calibrationPeriod);
        catMerchandiseDTO.setCharacter(this.character);
        catMerchandiseDTO.setFunction(this.function);
        catMerchandiseDTO.setExpiredDate(this.expiredDate);
        catMerchandiseDTO.setLifeTime(this.lifeTime);
        catMerchandiseDTO.setCatAssetFixedId(this.catAssetFixedId);
        catMerchandiseDTO.setCatSourceCreatedId(this.catSourceCreatedId);
        catMerchandiseDTO.setIsMerType(this.isMerType);
        catMerchandiseDTO.setMerchandiseId(this.merchandiseId);
        catMerchandiseDTO.setName(this.name);
        catMerchandiseDTO.setCode(this.code);
        catMerchandiseDTO.setParentId(this.parentId);
        catMerchandiseDTO.setUnitId(this.unitId);
        catMerchandiseDTO.setDescription(this.description);
        catMerchandiseDTO.setIsActive(this.isActive);
        catMerchandiseDTO.setIsVisible(this.isVisible);
        catMerchandiseDTO.setIsDevice(this.isDevice);
        catMerchandiseDTO.setDefaultPrice(this.defaultPrice);
        catMerchandiseDTO.setCurId(this.curId);
        catMerchandiseDTO.setPath(this.path);
        catMerchandiseDTO.setSerialRequired(this.serialRequired);
        catMerchandiseDTO.setMinDeprePeriod(this.minDeprePeriod);
        catMerchandiseDTO.setMaintainPeriod(this.maintainPeriod);
        catMerchandiseDTO.setMaintainCost(this.maintainCost);
        catMerchandiseDTO.setMaxDeprePeriod(this.maxDeprePeriod);
        catMerchandiseDTO.setAmortMode(this.amortMode);
        catMerchandiseDTO.setType(this.type);
        catMerchandiseDTO.setIsGroup(this.isGroup);
        catMerchandiseDTO.setIncreaseAssetAccId(this.increaseAssetAccId);
        catMerchandiseDTO.setDeptAccId(this.deptAccId);
        catMerchandiseDTO.setCreditAccId(this.creditAccId);
        catMerchandiseDTO.setPartnumberRequired(this.partnumberRequired);
        catMerchandiseDTO.setIsAccepted(this.isAccepted);
        catMerchandiseDTO.setCreatorId(this.creatorId);
        catMerchandiseDTO.setIsInFdmanager(this.isInFdmanager);
        catMerchandiseDTO.setEnName(this.enName);
        catMerchandiseDTO.setAbbreviate(this.abbreviate);
        catMerchandiseDTO.setOldId(this.oldId);
        catMerchandiseDTO.setOldType(this.oldType);
        catMerchandiseDTO.setOldCode(this.oldCode);
        catMerchandiseDTO.setPartNumber(this.partNumber);
        catMerchandiseDTO.setBkCode(this.bkCode);
        catMerchandiseDTO.setModifiedTime(this.modifiedTime);
        catMerchandiseDTO.setUpdaterId(this.updaterId);
        return catMerchandiseDTO;
    }
}
