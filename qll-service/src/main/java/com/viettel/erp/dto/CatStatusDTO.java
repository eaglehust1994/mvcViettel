/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatStatusBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_STATUSBO")
public class CatStatusDTO extends BaseFWDTOImpl<CatStatusBO> {

private java.lang.Long catStatusId;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long isActive;
private java.lang.Long type;
private java.lang.Long orderNum;
private java.lang.String code;

    @Override
    public CatStatusBO toModel() {
        CatStatusBO catStatusBO = new CatStatusBO();
        catStatusBO.setCatStatusId(this.catStatusId);
        catStatusBO.setName(this.name);
        catStatusBO.setDescription(this.description);
        catStatusBO.setIsActive(this.isActive);
        catStatusBO.setType(this.type);
        catStatusBO.setOrderNum(this.orderNum);
        catStatusBO.setCode(this.code);
        return catStatusBO;
    }

    @Override
     public Long getFWModelId() {
        return catStatusId;
    }
   
    @Override
    public String catchName() {
        return getCatStatusId().toString();
    }
    public java.lang.Long getCatStatusId(){
    return catStatusId;
    }
    public void setCatStatusId(java.lang.Long catStatusId)
    {
    this.catStatusId = catStatusId;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
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
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.Long getOrderNum(){
    return orderNum;
    }
    public void setOrderNum(java.lang.Long orderNum)
    {
    this.orderNum = orderNum;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
   
}
