/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatMerchandiseBO;
//import com.viettel.erp.bo.CatUnitBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_MERCHANDISEBO")
public class CatMerchandiseDTO extends BaseFWDTOImpl<CatMerchandiseBO> {

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

    @Override
    public CatMerchandiseBO toModel() {
        CatMerchandiseBO catMerchandiseBO = new CatMerchandiseBO();
        catMerchandiseBO.setProposalNote(this.proposalNote);
        catMerchandiseBO.setProposalStatus(this.proposalStatus);
        catMerchandiseBO.setProposalPath(this.proposalPath);
        catMerchandiseBO.setRefMerId(this.refMerId);
        catMerchandiseBO.setMerType(this.merType);
        catMerchandiseBO.setMark(this.mark);
        catMerchandiseBO.setCdcMerchandiseId(this.cdcMerchandiseId);
        catMerchandiseBO.setFullName(this.fullName);
        catMerchandiseBO.setCatGroupType(this.catGroupType);
        catMerchandiseBO.setContractId(this.contractId);
        catMerchandiseBO.setIsSpending(this.isSpending);
        catMerchandiseBO.setIsAccounting(this.isAccounting);
        catMerchandiseBO.setCdcktMerchandiseId(this.cdcktMerchandiseId);
        catMerchandiseBO.setMerExpType(this.merExpType);
        catMerchandiseBO.setWarningContent(this.warningContent);
        catMerchandiseBO.setCycleKdhc(this.cycleKdhc);
        catMerchandiseBO.setTypeKdhc(this.typeKdhc);
        catMerchandiseBO.setCycleBd(this.cycleBd);
        catMerchandiseBO.setGroupType(this.groupType);
        catMerchandiseBO.setTheLevel(this.theLevel);
        catMerchandiseBO.setChangeSerialRequire(this.changeSerialRequire);
        catMerchandiseBO.setTestingPeriod(this.testingPeriod);
        catMerchandiseBO.setCalibrationPeriod(this.calibrationPeriod);
        catMerchandiseBO.setCharacter(this.character);
        catMerchandiseBO.setFunction(this.function);
        catMerchandiseBO.setExpiredDate(this.expiredDate);
        catMerchandiseBO.setLifeTime(this.lifeTime);
        catMerchandiseBO.setCatAssetFixedId(this.catAssetFixedId);
        catMerchandiseBO.setCatSourceCreatedId(this.catSourceCreatedId);
        catMerchandiseBO.setIsMerType(this.isMerType);
        catMerchandiseBO.setMerchandiseId(this.merchandiseId);
        catMerchandiseBO.setName(this.name);
        catMerchandiseBO.setCode(this.code);
        catMerchandiseBO.setParentId(this.parentId);
       // catMerchandiseBO.setUnitId(this.unitId);       
//        catMerchandiseBO.setCatunit(this.unitId == null? null : new CatUnitBO(this.unitId));       
        catMerchandiseBO.setDescription(this.description);
        catMerchandiseBO.setIsActive(this.isActive);
        catMerchandiseBO.setIsVisible(this.isVisible);
        catMerchandiseBO.setIsDevice(this.isDevice);
        catMerchandiseBO.setDefaultPrice(this.defaultPrice);
        catMerchandiseBO.setCurId(this.curId);
        catMerchandiseBO.setPath(this.path);
        catMerchandiseBO.setSerialRequired(this.serialRequired);
        catMerchandiseBO.setMinDeprePeriod(this.minDeprePeriod);
        catMerchandiseBO.setMaintainPeriod(this.maintainPeriod);
        catMerchandiseBO.setMaintainCost(this.maintainCost);
        catMerchandiseBO.setMaxDeprePeriod(this.maxDeprePeriod);
        catMerchandiseBO.setAmortMode(this.amortMode);
        catMerchandiseBO.setType(this.type);
        catMerchandiseBO.setIsGroup(this.isGroup);
        catMerchandiseBO.setIncreaseAssetAccId(this.increaseAssetAccId);
        catMerchandiseBO.setDeptAccId(this.deptAccId);
        catMerchandiseBO.setCreditAccId(this.creditAccId);
        catMerchandiseBO.setPartnumberRequired(this.partnumberRequired);
        catMerchandiseBO.setIsAccepted(this.isAccepted);
        catMerchandiseBO.setCreatorId(this.creatorId);
        catMerchandiseBO.setIsInFdmanager(this.isInFdmanager);
        catMerchandiseBO.setEnName(this.enName);
        catMerchandiseBO.setAbbreviate(this.abbreviate);
        catMerchandiseBO.setOldId(this.oldId);
        catMerchandiseBO.setOldType(this.oldType);
        catMerchandiseBO.setOldCode(this.oldCode);
        catMerchandiseBO.setPartNumber(this.partNumber);
        catMerchandiseBO.setBkCode(this.bkCode);
        catMerchandiseBO.setModifiedTime(this.modifiedTime);
        catMerchandiseBO.setUpdaterId(this.updaterId);
        return catMerchandiseBO;
    }

    public java.lang.String getProposalNote(){
    return proposalNote;
    }
    public void setProposalNote(java.lang.String proposalNote)
    {
    this.proposalNote = proposalNote;
    }
    
    public java.lang.Long getProposalStatus(){
    return proposalStatus;
    }
    public void setProposalStatus(java.lang.Long proposalStatus)
    {
    this.proposalStatus = proposalStatus;
    }
    
    public java.lang.String getProposalPath(){
    return proposalPath;
    }
    public void setProposalPath(java.lang.String proposalPath)
    {
    this.proposalPath = proposalPath;
    }
    
    public java.lang.Long getRefMerId(){
    return refMerId;
    }
    public void setRefMerId(java.lang.Long refMerId)
    {
    this.refMerId = refMerId;
    }
    
    public java.lang.Long getMerType(){
    return merType;
    }
    public void setMerType(java.lang.Long merType)
    {
    this.merType = merType;
    }
    
    public java.lang.Long getMark(){
    return mark;
    }
    public void setMark(java.lang.Long mark)
    {
    this.mark = mark;
    }
    
    public java.lang.Long getCdcMerchandiseId(){
    return cdcMerchandiseId;
    }
    public void setCdcMerchandiseId(java.lang.Long cdcMerchandiseId)
    {
    this.cdcMerchandiseId = cdcMerchandiseId;
    }
    
    public java.lang.String getFullName(){
    return fullName;
    }
    public void setFullName(java.lang.String fullName)
    {
    this.fullName = fullName;
    }
    
    public java.lang.String getCatGroupType(){
    return catGroupType;
    }
    public void setCatGroupType(java.lang.String catGroupType)
    {
    this.catGroupType = catGroupType;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.Long getIsSpending(){
    return isSpending;
    }
    public void setIsSpending(java.lang.Long isSpending)
    {
    this.isSpending = isSpending;
    }
    
    public java.lang.Long getIsAccounting(){
    return isAccounting;
    }
    public void setIsAccounting(java.lang.Long isAccounting)
    {
    this.isAccounting = isAccounting;
    }
    
    public java.lang.Long getCdcktMerchandiseId(){
    return cdcktMerchandiseId;
    }
    public void setCdcktMerchandiseId(java.lang.Long cdcktMerchandiseId)
    {
    this.cdcktMerchandiseId = cdcktMerchandiseId;
    }
    
    public java.lang.Long getMerExpType(){
    return merExpType;
    }
    public void setMerExpType(java.lang.Long merExpType)
    {
    this.merExpType = merExpType;
    }
    
    public java.lang.String getWarningContent(){
    return warningContent;
    }
    public void setWarningContent(java.lang.String warningContent)
    {
    this.warningContent = warningContent;
    }
    
    public java.lang.Long getCycleKdhc(){
    return cycleKdhc;
    }
    public void setCycleKdhc(java.lang.Long cycleKdhc)
    {
    this.cycleKdhc = cycleKdhc;
    }
    
    public java.lang.Long getTypeKdhc(){
    return typeKdhc;
    }
    public void setTypeKdhc(java.lang.Long typeKdhc)
    {
    this.typeKdhc = typeKdhc;
    }
    
    public java.lang.Long getCycleBd(){
    return cycleBd;
    }
    public void setCycleBd(java.lang.Long cycleBd)
    {
    this.cycleBd = cycleBd;
    }
    
    public java.lang.Long getGroupType(){
    return groupType;
    }
    public void setGroupType(java.lang.Long groupType)
    {
    this.groupType = groupType;
    }
    
    public java.lang.Long getTheLevel(){
    return theLevel;
    }
    public void setTheLevel(java.lang.Long theLevel)
    {
    this.theLevel = theLevel;
    }
    
    public java.lang.Long getChangeSerialRequire(){
    return changeSerialRequire;
    }
    public void setChangeSerialRequire(java.lang.Long changeSerialRequire)
    {
    this.changeSerialRequire = changeSerialRequire;
    }
    
    public java.lang.Long getTestingPeriod(){
    return testingPeriod;
    }
    public void setTestingPeriod(java.lang.Long testingPeriod)
    {
    this.testingPeriod = testingPeriod;
    }
    
    public java.lang.Long getCalibrationPeriod(){
    return calibrationPeriod;
    }
    public void setCalibrationPeriod(java.lang.Long calibrationPeriod)
    {
    this.calibrationPeriod = calibrationPeriod;
    }
    
    public java.lang.String getCharacter(){
    return character;
    }
    public void setCharacter(java.lang.String character)
    {
    this.character = character;
    }
    
    public java.lang.String getFunction(){
    return function;
    }
    public void setFunction(java.lang.String function)
    {
    this.function = function;
    }
    
    public java.lang.Double getExpiredDate(){
    return expiredDate;
    }
    public void setExpiredDate(java.lang.Double expiredDate)
    {
    this.expiredDate = expiredDate;
    }
    
    public java.lang.Double getLifeTime(){
    return lifeTime;
    }
    public void setLifeTime(java.lang.Double lifeTime)
    {
    this.lifeTime = lifeTime;
    }
    
    public java.lang.Long getCatAssetFixedId(){
    return catAssetFixedId;
    }
    public void setCatAssetFixedId(java.lang.Long catAssetFixedId)
    {
    this.catAssetFixedId = catAssetFixedId;
    }
    
    public java.lang.Long getCatSourceCreatedId(){
    return catSourceCreatedId;
    }
    public void setCatSourceCreatedId(java.lang.Long catSourceCreatedId)
    {
    this.catSourceCreatedId = catSourceCreatedId;
    }
    
    public java.lang.Long getIsMerType(){
    return isMerType;
    }
    public void setIsMerType(java.lang.Long isMerType)
    {
    this.isMerType = isMerType;
    }
    
    @Override
     public Long getFWModelId() {
        return merchandiseId;
    }
   
    @Override
    public String catchName() {
        return getMerchandiseId().toString();
    }
    public java.lang.Long getMerchandiseId(){
    return merchandiseId;
    }
    public void setMerchandiseId(java.lang.Long merchandiseId)
    {
    this.merchandiseId = merchandiseId;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.Long getUnitId(){
    return unitId;
    }
    public void setUnitId(java.lang.Long unitId)
    {
    this.unitId = unitId;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getIsVisible(){
    return isVisible;
    }
    public void setIsVisible(java.lang.Long isVisible)
    {
    this.isVisible = isVisible;
    }
    
    public java.lang.Long getIsDevice(){
    return isDevice;
    }
    public void setIsDevice(java.lang.Long isDevice)
    {
    this.isDevice = isDevice;
    }
    
    public java.lang.Double getDefaultPrice(){
    return defaultPrice;
    }
    public void setDefaultPrice(java.lang.Double defaultPrice)
    {
    this.defaultPrice = defaultPrice;
    }
    
    public java.lang.Long getCurId(){
    return curId;
    }
    public void setCurId(java.lang.Long curId)
    {
    this.curId = curId;
    }
    
    public java.lang.String getPath(){
    return path;
    }
    public void setPath(java.lang.String path)
    {
    this.path = path;
    }
    
    public java.lang.Long getSerialRequired(){
    return serialRequired;
    }
    public void setSerialRequired(java.lang.Long serialRequired)
    {
    this.serialRequired = serialRequired;
    }
    
    public java.lang.Long getMinDeprePeriod(){
    return minDeprePeriod;
    }
    public void setMinDeprePeriod(java.lang.Long minDeprePeriod)
    {
    this.minDeprePeriod = minDeprePeriod;
    }
    
    public java.lang.Long getMaintainPeriod(){
    return maintainPeriod;
    }
    public void setMaintainPeriod(java.lang.Long maintainPeriod)
    {
    this.maintainPeriod = maintainPeriod;
    }
    
    public java.lang.Long getMaintainCost(){
    return maintainCost;
    }
    public void setMaintainCost(java.lang.Long maintainCost)
    {
    this.maintainCost = maintainCost;
    }
    
    public java.lang.Long getMaxDeprePeriod(){
    return maxDeprePeriod;
    }
    public void setMaxDeprePeriod(java.lang.Long maxDeprePeriod)
    {
    this.maxDeprePeriod = maxDeprePeriod;
    }
    
    public java.lang.Long getAmortMode(){
    return amortMode;
    }
    public void setAmortMode(java.lang.Long amortMode)
    {
    this.amortMode = amortMode;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.Long getIsGroup(){
    return isGroup;
    }
    public void setIsGroup(java.lang.Long isGroup)
    {
    this.isGroup = isGroup;
    }
    
    public java.lang.Long getIncreaseAssetAccId(){
    return increaseAssetAccId;
    }
    public void setIncreaseAssetAccId(java.lang.Long increaseAssetAccId)
    {
    this.increaseAssetAccId = increaseAssetAccId;
    }
    
    public java.lang.Long getDeptAccId(){
    return deptAccId;
    }
    public void setDeptAccId(java.lang.Long deptAccId)
    {
    this.deptAccId = deptAccId;
    }
    
    public java.lang.Long getCreditAccId(){
    return creditAccId;
    }
    public void setCreditAccId(java.lang.Long creditAccId)
    {
    this.creditAccId = creditAccId;
    }
    
    public java.lang.Long getPartnumberRequired(){
    return partnumberRequired;
    }
    public void setPartnumberRequired(java.lang.Long partnumberRequired)
    {
    this.partnumberRequired = partnumberRequired;
    }
    
    public java.lang.Long getIsAccepted(){
    return isAccepted;
    }
    public void setIsAccepted(java.lang.Long isAccepted)
    {
    this.isAccepted = isAccepted;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.lang.Long getIsInFdmanager(){
    return isInFdmanager;
    }
    public void setIsInFdmanager(java.lang.Long isInFdmanager)
    {
    this.isInFdmanager = isInFdmanager;
    }
    
    public java.lang.String getEnName(){
    return enName;
    }
    public void setEnName(java.lang.String enName)
    {
    this.enName = enName;
    }
    
    public java.lang.String getAbbreviate(){
    return abbreviate;
    }
    public void setAbbreviate(java.lang.String abbreviate)
    {
    this.abbreviate = abbreviate;
    }
    
    public java.lang.Long getOldId(){
    return oldId;
    }
    public void setOldId(java.lang.Long oldId)
    {
    this.oldId = oldId;
    }
    
    public java.lang.Long getOldType(){
    return oldType;
    }
    public void setOldType(java.lang.Long oldType)
    {
    this.oldType = oldType;
    }
    
    public java.lang.String getOldCode(){
    return oldCode;
    }
    public void setOldCode(java.lang.String oldCode)
    {
    this.oldCode = oldCode;
    }
    
    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.String getBkCode(){
    return bkCode;
    }
    public void setBkCode(java.lang.String bkCode)
    {
    this.bkCode = bkCode;
    }
    
    public java.util.Date getModifiedTime(){
    return modifiedTime;
    }
    public void setModifiedTime(java.util.Date modifiedTime)
    {
    this.modifiedTime = modifiedTime;
    }
    
    public java.lang.Long getUpdaterId(){
    return updaterId;
    }
    public void setUpdaterId(java.lang.Long updaterId)
    {
    this.updaterId = updaterId;
    }
    
   
}
