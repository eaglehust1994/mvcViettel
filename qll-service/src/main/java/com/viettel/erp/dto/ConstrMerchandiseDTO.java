/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.ConstrMerchandiseBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_MERCHANDISEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrMerchandiseDTO extends BaseFWDTOImpl<ConstrMerchandiseBO> {

private java.lang.Long id;
private java.lang.Long quantity;
private java.lang.Long isActive;
private java.lang.Long merEntityId;
private java.lang.Long workItemId;
private java.lang.Long constructId;
private java.lang.Long rootMerEntityId;
private java.lang.Long remainCount;
private java.lang.Long merId;
private java.lang.Long upgradeParentId;
private java.lang.Long nodeId;
private java.lang.Long valueConstrMerchandise;

    @Override
    public ConstrMerchandiseBO toModel() {
        ConstrMerchandiseBO constrMerchandiseBO = new ConstrMerchandiseBO();
        constrMerchandiseBO.setId(this.id);
        constrMerchandiseBO.setQuantity(this.quantity);
        constrMerchandiseBO.setIsActive(this.isActive);
        constrMerchandiseBO.setMerEntityId(this.merEntityId);
        constrMerchandiseBO.setWorkItemId(this.workItemId);
        //constrMerchandiseBO.setConstructId(this.constructId);
        constrMerchandiseBO.setconstrconstructions(this.constructId == null ? null : new ConstrConstructionsBO(this.constructId));
        constrMerchandiseBO.setRootMerEntityId(this.rootMerEntityId);
        constrMerchandiseBO.setRemainCount(this.remainCount);
        constrMerchandiseBO.setMerId(this.merId);
        constrMerchandiseBO.setUpgradeParentId(this.upgradeParentId);
        constrMerchandiseBO.setNodeId(this.nodeId);
        return constrMerchandiseBO;
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
    
    public java.lang.Long getQuantity(){
    return quantity;
    }
    public void setQuantity(java.lang.Long quantity)
    {
    this.quantity = quantity;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getMerEntityId(){
    return merEntityId;
    }
    public void setMerEntityId(java.lang.Long merEntityId)
    {
    this.merEntityId = merEntityId;
    }
    
    public java.lang.Long getWorkItemId(){
    return workItemId;
    }
    public void setWorkItemId(java.lang.Long workItemId)
    {
    this.workItemId = workItemId;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.Long getRootMerEntityId(){
    return rootMerEntityId;
    }
    public void setRootMerEntityId(java.lang.Long rootMerEntityId)
    {
    this.rootMerEntityId = rootMerEntityId;
    }
    
    public java.lang.Long getRemainCount(){
    return remainCount;
    }
    public void setRemainCount(java.lang.Long remainCount)
    {
    this.remainCount = remainCount;
    }
    
    public java.lang.Long getMerId(){
    return merId;
    }
    public void setMerId(java.lang.Long merId)
    {
    this.merId = merId;
    }
    
    public java.lang.Long getUpgradeParentId(){
    return upgradeParentId;
    }
    public void setUpgradeParentId(java.lang.Long upgradeParentId)
    {
    this.upgradeParentId = upgradeParentId;
    }
    
    public java.lang.Long getNodeId(){
    return nodeId;
    }
    public void setNodeId(java.lang.Long nodeId)
    {
    this.nodeId = nodeId;
    }

	public java.lang.Long getValueConstrMerchandise() {
		return valueConstrMerchandise;
	}

	public void setValueConstrMerchandise(java.lang.Long valueConstrMerchandise) {
		this.valueConstrMerchandise = valueConstrMerchandise;
	}
    
}
