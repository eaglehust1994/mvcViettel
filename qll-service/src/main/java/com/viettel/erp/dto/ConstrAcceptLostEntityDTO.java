/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.ConstrAcceptLostEntityBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_ACCEPT_LOST_ENTITYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptLostEntityDTO extends BaseFWDTOImpl<ConstrAcceptLostEntityBO> {

private java.lang.Long id;
private java.lang.Long acceptNoteId;
private java.lang.Long merEntityId;
private java.lang.Long count;
private java.lang.Long unitPrice;
private java.lang.Long preStatusId;
private java.lang.Long deliveryNoteId;
private java.lang.Long upgradeParentId;

    @Override
    public ConstrAcceptLostEntityBO toModel() {
        ConstrAcceptLostEntityBO constrAcceptLostEntityBO = new ConstrAcceptLostEntityBO();
        constrAcceptLostEntityBO.setId(this.id);
        constrAcceptLostEntityBO.setAcceptNoteId(this.acceptNoteId);
        constrAcceptLostEntityBO.setMerEntityId(this.merEntityId);
        constrAcceptLostEntityBO.setCount(this.count);
        constrAcceptLostEntityBO.setUnitPrice(this.unitPrice);
        constrAcceptLostEntityBO.setPreStatusId(this.preStatusId);
        constrAcceptLostEntityBO.setDeliveryNoteId(this.deliveryNoteId);
        constrAcceptLostEntityBO.setUpgradeParentId(this.upgradeParentId);
        return constrAcceptLostEntityBO;
    }

    @Override
     public Long getFWModelId() {
        return id;
    }
   
    @Override
    public String catchName() {
        return getId().toString();
    }
    public java.lang.Long getId(){
    return id;
    }
    public void setId(java.lang.Long id)
    {
    this.id = id;
    }
    
    public java.lang.Long getAcceptNoteId(){
    return acceptNoteId;
    }
    public void setAcceptNoteId(java.lang.Long acceptNoteId)
    {
    this.acceptNoteId = acceptNoteId;
    }
    
    public java.lang.Long getMerEntityId(){
    return merEntityId;
    }
    public void setMerEntityId(java.lang.Long merEntityId)
    {
    this.merEntityId = merEntityId;
    }
    
    public java.lang.Long getCount(){
    return count;
    }
    public void setCount(java.lang.Long count)
    {
    this.count = count;
    }
    
    public java.lang.Long getUnitPrice(){
    return unitPrice;
    }
    public void setUnitPrice(java.lang.Long unitPrice)
    {
    this.unitPrice = unitPrice;
    }
    
    public java.lang.Long getPreStatusId(){
    return preStatusId;
    }
    public void setPreStatusId(java.lang.Long preStatusId)
    {
    this.preStatusId = preStatusId;
    }
    
    public java.lang.Long getDeliveryNoteId(){
    return deliveryNoteId;
    }
    public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
    {
    this.deliveryNoteId = deliveryNoteId;
    }
    
    public java.lang.Long getUpgradeParentId(){
    return upgradeParentId;
    }
    public void setUpgradeParentId(java.lang.Long upgradeParentId)
    {
    this.upgradeParentId = upgradeParentId;
    }
    
   
}
