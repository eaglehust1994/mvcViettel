/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.OrderPatternBO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author TuanNB
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "ORDER_PATTERNBO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderPatternDTO extends wmsBaseDTO<OrderPatternBO> {

private java.lang.Long orderPatternId;
private java.lang.String name;
private java.lang.String status;
private java.lang.String description;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.String createdUserName;
private java.lang.Long createdBy;
private java.util.Date updatedDate;
private java.lang.Long fwmodelId;

private List<OrderPatternGoodsDTO> patternGoodsListDTO;



	@Override
    public OrderPatternBO toModel() {
        OrderPatternBO orderPatternBO = new OrderPatternBO();
        orderPatternBO.setOrderPatternId(this.orderPatternId);
        orderPatternBO.setName(this.name);
        orderPatternBO.setStatus(this.status);
        orderPatternBO.setDescription(this.description);
        orderPatternBO.setCreatedDate(this.createdDate);
        orderPatternBO.setCreatedUserId(this.createdUserId);
        orderPatternBO.setCreatedUserName(this.createdUserName);
        return orderPatternBO;
    }

    @Override
     public Long getFWModelId() {
        return orderPatternId;
    }
   
    @Override
    public String catchName() {
        return getOrderPatternId().toString();
    }
    public java.lang.Long getOrderPatternId(){
    return orderPatternId;
    }
    public void setOrderPatternId(java.lang.Long orderPatternId)
    {
    this.orderPatternId = orderPatternId;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
	public java.lang.String getCreatedUserName() {
	return createdUserName;
	}

	public void setCreatedUserName(java.lang.String createdUserName) {
	this.createdUserName = createdUserName;
	}

	@JsonProperty("patternGoodsListDTO")
	public List<OrderPatternGoodsDTO> patternGoodsListDTO() {
		return patternGoodsListDTO;
	}

	public void setPatternGoodsListDTO(List<OrderPatternGoodsDTO> patternGoodsListDTO) {
		this.patternGoodsListDTO = patternGoodsListDTO;
	}

	public java.lang.Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

   
}
