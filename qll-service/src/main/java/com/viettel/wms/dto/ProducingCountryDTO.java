/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ProducingCountryBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "PRODUCING_COUNTRYBO")
public class ProducingCountryDTO extends BaseFWDTOImpl<ProducingCountryBO> {

private java.lang.Long producingCountryId;
private java.lang.String status;
private java.lang.String name;
private java.lang.String code;
private java.lang.String producingCountryName;
    @Override
    public ProducingCountryBO toModel() {
        ProducingCountryBO producingCountryBO = new ProducingCountryBO();
        producingCountryBO.setProducingCountryId(this.producingCountryId);
        producingCountryBO.setStatus(this.status);
        producingCountryBO.setName(this.name);
        producingCountryBO.setCode(this.code);
        return producingCountryBO;
    }

    @Override
     public Long getFWModelId() {
        return producingCountryId;
    }
   
    @Override
    public String catchName() {
        return getProducingCountryId().toString();
    }
    public java.lang.Long getProducingCountryId(){
    return producingCountryId;
    }
    public void setProducingCountryId(java.lang.Long producingCountryId)
    {
    this.producingCountryId = producingCountryId;
    }
    
    public java.lang.String getProducingCountryName() {
		return producingCountryName;
	}

	public void setProducingCountryName(java.lang.String producingCountryName) {
		this.producingCountryName = producingCountryName;
	}

	public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
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
    
   
}
