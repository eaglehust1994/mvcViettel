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

import com.viettel.erp.dto.AssetManageReqEntityDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "ASSET_MANAGE_REQ_ENTITY")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AssetManageReqEntityBO extends BaseFWModelImpl {
     
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

 public AssetManageReqEntityBO() {
        setColId("stationId");
        setColName("stationId");
        setUniqueColumn(new String[]{"stationId"});
}
@Id
@GeneratedValue(generator = "sequence")
@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ASSET_MANAGE_REQ_ENTITY_SEQ") })
@Column(name = "STATION_ID", length = 22)
public java.lang.Long getStationId(){
return stationId;
}
public void setStationId(java.lang.Long stationId)
{
this.stationId = stationId;
}
@Column(name = "FAIL_DATE", length = 7)
public java.util.Date getFailDate(){
return failDate;
}
public void setFailDate(java.util.Date failDate)
{
this.failDate = failDate;
}
@Column(name = "FAIL_REASON", length = 1000)
public java.lang.String getFailReason(){
return failReason;
}
public void setFailReason(java.lang.String failReason)
{
this.failReason = failReason;
}
@Column(name = "USED_DATE", length = 7)
public java.util.Date getUsedDate(){
return usedDate;
}
public void setUsedDate(java.util.Date usedDate)
{
this.usedDate = usedDate;
}
@Column(name = "PRE_STATUS_ID", length = 22)
public java.lang.Long getPreStatusId(){
return preStatusId;
}
public void setPreStatusId(java.lang.Long preStatusId)
{
this.preStatusId = preStatusId;
}
@Column(name = "GROUP_ID", length = 22)
public java.lang.Long getGroupId(){
return groupId;
}
public void setGroupId(java.lang.Long groupId)
{
this.groupId = groupId;
}
@Column(name = "QUANTITY", length = 22)
public java.lang.Long getQuantity(){
return quantity;
}
public void setQuantity(java.lang.Long quantity)
{
this.quantity = quantity;
}
@Column(name = "DESCRIPTION", length = 1200)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "UNIT_PRICE", length = 22)
public java.lang.Long getUnitPrice(){
return unitPrice;
}
public void setUnitPrice(java.lang.Long unitPrice)
{
this.unitPrice = unitPrice;
}
@Column(name = "TIME_RETRIEVE", length = 7)
public java.util.Date getTimeRetrieve(){
return timeRetrieve;
}
public void setTimeRetrieve(java.util.Date timeRetrieve)
{
this.timeRetrieve = timeRetrieve;
}
@Column(name = "CONSTRUCTION_ID", length = 22)
public java.lang.Long getConstructionId(){
return constructionId;
}
public void setConstructionId(java.lang.Long constructionId)
{
this.constructionId = constructionId;
}
@Column(name = "CAT_GROUP_MER_ID", length = 22)
public java.lang.Long getCatGroupMerId(){
return catGroupMerId;
}
public void setCatGroupMerId(java.lang.Long catGroupMerId)
{
this.catGroupMerId = catGroupMerId;
}
@Column(name = "CONDITIONER_ID", length = 22)
public java.lang.Long getConditionerId(){
return conditionerId;
}
public void setConditionerId(java.lang.Long conditionerId)
{
this.conditionerId = conditionerId;
}
@Column(name = "ID", length = 22)
public java.lang.Long getId(){
return id;
}
public void setId(java.lang.Long id)
{
this.id = id;
}
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "UPGRADE_PARENT_ID", length = 22)
public java.lang.Long getUpgradeParentId(){
return upgradeParentId;
}
public void setUpgradeParentId(java.lang.Long upgradeParentId)
{
this.upgradeParentId = upgradeParentId;
}
@Column(name = "DELIVERY_NOTE_ID", length = 22)
public java.lang.Long getDeliveryNoteId(){
return deliveryNoteId;
}
public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
{
this.deliveryNoteId = deliveryNoteId;
}
@Column(name = "REQ_ID", length = 22)
public java.lang.Long getReqId(){
return reqId;
}
public void setReqId(java.lang.Long reqId)
{
this.reqId = reqId;
}
@Column(name = "MER_ENTITY_ID", length = 22)
public java.lang.Long getMerEntityId(){
return merEntityId;
}
public void setMerEntityId(java.lang.Long merEntityId)
{
this.merEntityId = merEntityId;
}
   

    @Override
    public AssetManageReqEntityDTO toDTO() {
        AssetManageReqEntityDTO assetManageReqEntityDTO = new AssetManageReqEntityDTO();
        //set cac gia tri 
        assetManageReqEntityDTO.setStationId(this.stationId);
        assetManageReqEntityDTO.setFailDate(this.failDate);
        assetManageReqEntityDTO.setFailReason(this.failReason);
        assetManageReqEntityDTO.setUsedDate(this.usedDate);
        assetManageReqEntityDTO.setPreStatusId(this.preStatusId);
        assetManageReqEntityDTO.setGroupId(this.groupId);
        assetManageReqEntityDTO.setQuantity(this.quantity);
        assetManageReqEntityDTO.setDescription(this.description);
        assetManageReqEntityDTO.setUnitPrice(this.unitPrice);
        assetManageReqEntityDTO.setTimeRetrieve(this.timeRetrieve);
        assetManageReqEntityDTO.setConstructionId(this.constructionId);
        assetManageReqEntityDTO.setCatGroupMerId(this.catGroupMerId);
        assetManageReqEntityDTO.setConditionerId(this.conditionerId);
        assetManageReqEntityDTO.setId(this.id);
        assetManageReqEntityDTO.setPartnerId(this.partnerId);
        assetManageReqEntityDTO.setUpgradeParentId(this.upgradeParentId);
        assetManageReqEntityDTO.setDeliveryNoteId(this.deliveryNoteId);
        assetManageReqEntityDTO.setReqId(this.reqId);
        assetManageReqEntityDTO.setMerEntityId(this.merEntityId);
        return assetManageReqEntityDTO;
    }
}
