/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ManufacturerBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "MANUFACTURERBO")
public class ManufacturerDTO extends BaseFWDTOImpl<ManufacturerBO> {

private java.lang.String status;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long manufacturerId;
private java.lang.String manufacturerName;

    @Override
    public ManufacturerBO toModel() {
        ManufacturerBO manufacturerBO = new ManufacturerBO();
        manufacturerBO.setStatus(this.status);
        manufacturerBO.setCode(this.code);
        manufacturerBO.setName(this.name);
        manufacturerBO.setManufacturerId(this.manufacturerId);
        return manufacturerBO;
    }

    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(java.lang.String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    @Override
     public Long getFWModelId() {
        return manufacturerId;
    }
   
    @Override
    public String catchName() {
        return getManufacturerId().toString();
    }
    public java.lang.Long getManufacturerId(){
    return manufacturerId;
    }
    public void setManufacturerId(java.lang.Long manufacturerId)
    {
    this.manufacturerId = manufacturerId;
    }
    
   
}
