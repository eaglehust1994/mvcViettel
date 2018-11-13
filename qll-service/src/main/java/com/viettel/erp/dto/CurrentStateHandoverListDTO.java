/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CURRENT_STATE_HANDOVER_LISTBO")
@JsonIgnoreProperties(ignoreUnknown =true)
public class CurrentStateHandoverListDTO extends BaseFWDTOImpl<CurrentStateHandoverListBO> {

private java.lang.Long currentStateHandoverListId;
private java.lang.String handoverContent;
private java.lang.String unit;
private java.lang.Double quantity;
private java.lang.String currentState;
private java.lang.Long constructionRequest;
private java.lang.String notes;
private java.lang.Long currentStateHandoverId;
private java.lang.String requestYes;
private java.lang.String requestNo;
private java.lang.Integer stt;
    @Override
    public CurrentStateHandoverListBO toModel() {
        CurrentStateHandoverListBO currentStateHandoverListBO = new CurrentStateHandoverListBO();
        currentStateHandoverListBO.setCurrentStateHandoverListId(this.currentStateHandoverListId);
        currentStateHandoverListBO.setHandoverContent(this.handoverContent);
        currentStateHandoverListBO.setUnit(this.unit);
        currentStateHandoverListBO.setQuantity(this.quantity);
        currentStateHandoverListBO.setCurrentState(this.currentState);
        currentStateHandoverListBO.setConstructionRequest(this.constructionRequest);
        currentStateHandoverListBO.setNotes(this.notes);
        
        currentStateHandoverListBO.setCurrentStateHandoverBO(this.currentStateHandoverId == null ? null : new CurrentStateHandoverBO(this.currentStateHandoverId));
        return currentStateHandoverListBO;
    }

    @Override
     public Long getFWModelId() {
        return currentStateHandoverListId;
    }
   
    @Override
    public String catchName() {
        return getCurrentStateHandoverListId().toString();
    }
    public java.lang.Long getCurrentStateHandoverListId(){
    return currentStateHandoverListId;
    }
    public void setCurrentStateHandoverListId(java.lang.Long currentStateHandoverListId)
    {
    this.currentStateHandoverListId = currentStateHandoverListId;
    }
    
    public java.lang.String getHandoverContent(){
    return handoverContent;
    }
    public void setHandoverContent(java.lang.String handoverContent)
    {
    this.handoverContent = handoverContent;
    }
    
    public java.lang.String getUnit(){
    return unit;
    }
    public void setUnit(java.lang.String unit)
    {
    this.unit = unit;
    }
    
    public java.lang.Double getQuantity(){
    return quantity;
    }
    public void setQuantity(java.lang.Double quantity)
    {
    this.quantity = quantity;
    }
    
    public java.lang.String getCurrentState(){
    return currentState;
    }
    public void setCurrentState(java.lang.String currentState)
    {
    this.currentState = currentState;
    }
    
    public java.lang.Long getConstructionRequest(){
    return constructionRequest;
    }
    public void setConstructionRequest(java.lang.Long constructionRequest)
    {
    this.constructionRequest = constructionRequest;
    }
    
    public java.lang.String getNotes(){
    return notes;
    }
    public void setNotes(java.lang.String notes)
    {
    this.notes = notes;
    }
    
    public java.lang.Long getCurrentStateHandoverId(){
    return currentStateHandoverId;
    }
    public void setCurrentStateHandoverId(java.lang.Long currentStateHandoverId)
    {
    this.currentStateHandoverId = currentStateHandoverId;
    }

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.String getRequestYes() {
		return requestYes;
	}

	public void setRequestYes(java.lang.String requestYes) {
		this.requestYes = requestYes;
	}

	public java.lang.String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(java.lang.String requestNo) {
		this.requestNo = requestNo;
	}
	
    
   
}
