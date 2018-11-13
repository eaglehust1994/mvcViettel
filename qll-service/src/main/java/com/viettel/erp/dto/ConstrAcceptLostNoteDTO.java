/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.ConstrAcceptLostNoteBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_ACCEPT_LOST_NOTEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptLostNoteDTO extends BaseFWDTOImpl<ConstrAcceptLostNoteBO> {

private java.lang.Long acceptNoteId;
private java.lang.String acceptorName;
private java.lang.String acceptorPosition;
private java.lang.Long constrId;
private java.lang.String description;
private java.util.Date createDate;
private java.lang.Long creatorId;
private java.util.Date confirmDate;
private java.lang.Long valueLoss;
    @Override
    public ConstrAcceptLostNoteBO toModel() {
        ConstrAcceptLostNoteBO constrAcceptLostNoteBO = new ConstrAcceptLostNoteBO();
        constrAcceptLostNoteBO.setAcceptNoteId(this.acceptNoteId);
        constrAcceptLostNoteBO.setAcceptorName(this.acceptorName);
        constrAcceptLostNoteBO.setAcceptorPosition(this.acceptorPosition);
        constrAcceptLostNoteBO.setConstrId(this.constrId);
        constrAcceptLostNoteBO.setDescription(this.description);
        constrAcceptLostNoteBO.setCreateDate(this.createDate);
        constrAcceptLostNoteBO.setCreatorId(this.creatorId);
        constrAcceptLostNoteBO.setConfirmDate(this.confirmDate);
        return constrAcceptLostNoteBO;
    }

    @Override
     public Long getFWModelId() {
        return acceptNoteId;
    }
   
    @Override
    public String catchName() {
        return getAcceptNoteId().toString();
    }
    public java.lang.Long getAcceptNoteId(){
    return acceptNoteId;
    }
    public void setAcceptNoteId(java.lang.Long acceptNoteId)
    {
    this.acceptNoteId = acceptNoteId;
    }
    
    public java.lang.String getAcceptorName(){
    return acceptorName;
    }
    public void setAcceptorName(java.lang.String acceptorName)
    {
    this.acceptorName = acceptorName;
    }
    
    public java.lang.String getAcceptorPosition(){
    return acceptorPosition;
    }
    public void setAcceptorPosition(java.lang.String acceptorPosition)
    {
    this.acceptorPosition = acceptorPosition;
    }
    
    public java.lang.Long getConstrId(){
    return constrId;
    }
    public void setConstrId(java.lang.Long constrId)
    {
    this.constrId = constrId;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.util.Date getCreateDate(){
    return createDate;
    }
    public void setCreateDate(java.util.Date createDate)
    {
    this.createDate = createDate;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.util.Date getConfirmDate(){
    return confirmDate;
    }
    public void setConfirmDate(java.util.Date confirmDate)
    {
    this.confirmDate = confirmDate;
    }

	public java.lang.Long getValueLoss() {
		return valueLoss;
	}

	public void setValueLoss(java.lang.Long valueLoss) {
		this.valueLoss = valueLoss;
	}
    
}
