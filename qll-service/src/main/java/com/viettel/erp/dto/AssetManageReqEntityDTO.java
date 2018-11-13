/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.AssetManageReqEntityBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ASSET_MANAGE_REQ_ENTITYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetManageReqEntityDTO extends BaseFWDTOImpl<AssetManageReqEntityBO> {

private java.lang.Long stationId;
private java.util.Date failDate;
private java.lang.String failReason;
private java.util.Date usedDate;
private java.lang.Long preStatusId;
private java.lang.Long groupId;
private java.lang.Long quantity;
private java.lang.String description;
private java.lang.Long unitPrice;
private java.util.Date timeRetrieve;
private java.lang.Long constructionId;
private java.lang.Long catGroupMerId;
private java.lang.Long conditionerId;
private java.lang.Long id;
private java.lang.Long partnerId;
private java.lang.Long upgradeParentId;
private java.lang.Long deliveryNoteId;
private java.lang.Long reqId;
private java.lang.Long merEntityId;

    @Override
    public AssetManageReqEntityBO toModel() {
        AssetManageReqEntityBO assetManageReqEntityBO = new AssetManageReqEntityBO();
        assetManageReqEntityBO.setStationId(this.stationId);
        assetManageReqEntityBO.setFailDate(this.failDate);
        assetManageReqEntityBO.setFailReason(this.failReason);
        assetManageReqEntityBO.setUsedDate(this.usedDate);
        assetManageReqEntityBO.setPreStatusId(this.preStatusId);
        assetManageReqEntityBO.setGroupId(this.groupId);
        assetManageReqEntityBO.setQuantity(this.quantity);
        assetManageReqEntityBO.setDescription(this.description);
        assetManageReqEntityBO.setUnitPrice(this.unitPrice);
        assetManageReqEntityBO.setTimeRetrieve(this.timeRetrieve);
        assetManageReqEntityBO.setConstructionId(this.constructionId);
        assetManageReqEntityBO.setCatGroupMerId(this.catGroupMerId);
        assetManageReqEntityBO.setConditionerId(this.conditionerId);
        assetManageReqEntityBO.setId(this.id);
        assetManageReqEntityBO.setPartnerId(this.partnerId);
        assetManageReqEntityBO.setUpgradeParentId(this.upgradeParentId);
        assetManageReqEntityBO.setDeliveryNoteId(this.deliveryNoteId);
        assetManageReqEntityBO.setReqId(this.reqId);
        assetManageReqEntityBO.setMerEntityId(this.merEntityId);
        return assetManageReqEntityBO;
    }

    public java.lang.Long getStationId(){
    return stationId;
    }
    public void setStationId(java.lang.Long stationId)
    {
    this.stationId = stationId;
    }
    
    public java.util.Date getFailDate(){
    return failDate;
    }
    public void setFailDate(java.util.Date failDate)
    {
    this.failDate = failDate;
    }
    
    public java.lang.String getFailReason(){
    return failReason;
    }
    public void setFailReason(java.lang.String failReason)
    {
    this.failReason = failReason;
    }
    
    public java.util.Date getUsedDate(){
    return usedDate;
    }
    public void setUsedDate(java.util.Date usedDate)
    {
    this.usedDate = usedDate;
    }
    
    public java.lang.Long getPreStatusId(){
    return preStatusId;
    }
    public void setPreStatusId(java.lang.Long preStatusId)
    {
    this.preStatusId = preStatusId;
    }
    
    public java.lang.Long getGroupId(){
    return groupId;
    }
    public void setGroupId(java.lang.Long groupId)
    {
    this.groupId = groupId;
    }
    
    public java.lang.Long getQuantity(){
    return quantity;
    }
    public void setQuantity(java.lang.Long quantity)
    {
    this.quantity = quantity;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getUnitPrice(){
    return unitPrice;
    }
    public void setUnitPrice(java.lang.Long unitPrice)
    {
    this.unitPrice = unitPrice;
    }
    
    public java.util.Date getTimeRetrieve(){
    return timeRetrieve;
    }
    public void setTimeRetrieve(java.util.Date timeRetrieve)
    {
    this.timeRetrieve = timeRetrieve;
    }
    
    public java.lang.Long getConstructionId(){
    return constructionId;
    }
    public void setConstructionId(java.lang.Long constructionId)
    {
    this.constructionId = constructionId;
    }
    
    public java.lang.Long getCatGroupMerId(){
    return catGroupMerId;
    }
    public void setCatGroupMerId(java.lang.Long catGroupMerId)
    {
    this.catGroupMerId = catGroupMerId;
    }
    
    public java.lang.Long getConditionerId(){
    return conditionerId;
    }
    public void setConditionerId(java.lang.Long conditionerId)
    {
    this.conditionerId = conditionerId;
    }
    
    public java.lang.Long getId(){
    return id;
    }
    public void setId(java.lang.Long id)
    {
    this.id = id;
    }
    
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
    public java.lang.Long getUpgradeParentId(){
    return upgradeParentId;
    }
    public void setUpgradeParentId(java.lang.Long upgradeParentId)
    {
    this.upgradeParentId = upgradeParentId;
    }
    
    public java.lang.Long getDeliveryNoteId(){
    return deliveryNoteId;
    }
    public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
    {
    this.deliveryNoteId = deliveryNoteId;
    }
    
    public java.lang.Long getReqId(){
    return reqId;
    }
    public void setReqId(java.lang.Long reqId)
    {
    this.reqId = reqId;
    }
    
    public java.lang.Long getMerEntityId(){
    return merEntityId;
    }
    public void setMerEntityId(java.lang.Long merEntityId)
    {
    this.merEntityId = merEntityId;
    }

	@Override
	public String catchName() {
		return getReqId().toString();
	}

	@Override
	public Long getFWModelId() {
		return reqId;
	}
    
   
}
