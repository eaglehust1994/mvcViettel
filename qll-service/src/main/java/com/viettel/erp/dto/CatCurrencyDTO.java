/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatCurrencyBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_CURRENCYBO")
public class CatCurrencyDTO extends BaseFWDTOImpl<CatCurrencyBO> {

private java.lang.Long currencyId;
private java.lang.String name;
private java.lang.String code;
private java.lang.Long isActive;

    @Override
    public CatCurrencyBO toModel() {
        CatCurrencyBO catCurrencyBO = new CatCurrencyBO();
        catCurrencyBO.setCurrencyId(this.currencyId);
        catCurrencyBO.setName(this.name);
        catCurrencyBO.setCode(this.code);
        catCurrencyBO.setIsActive(this.isActive);
        return catCurrencyBO;
    }

    @Override
     public Long getFWModelId() {
        return currencyId;
    }
   
    @Override
    public String catchName() {
        return getCurrencyId().toString();
    }
    public java.lang.Long getCurrencyId(){
    return currencyId;
    }
    public void setCurrencyId(java.lang.Long currencyId)
    {
    this.currencyId = currencyId;
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
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
   
}
