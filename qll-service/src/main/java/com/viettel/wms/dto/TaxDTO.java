/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.TaxBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "TAXBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxDTO extends wmsBaseDTO<TaxBO> {

private java.lang.String name;
private java.lang.Double value;
private java.lang.String type;
private java.lang.String code;
private java.lang.String ignore;
private java.lang.String apply;
private java.lang.String status;
private java.lang.Long createdBy;
private java.util.Date createdDate;
private java.lang.Long taxId;
private java.util.Date updatedDate;
private java.lang.Long updatedBy;
private java.lang.Long totaloriginmoney;
private java.lang.Long shipmentId;
private java.lang.Long shipmentTaxId;

private java.lang.Long valueshipmenttax;

    @Override
    public TaxBO toModel() {
        TaxBO taxBO = new TaxBO();
        taxBO.setName(this.name);
        taxBO.setValue(this.value);
        taxBO.setType(this.type);
        taxBO.setCode(this.code);
        taxBO.setIgnore(this.ignore);
        taxBO.setApply(this.apply);
        taxBO.setStatus(this.status);
        taxBO.setCreatedBy(this.createdBy);
        taxBO.setCreatedDate(this.createdDate);
        taxBO.setTaxId(this.taxId);
        taxBO.setUpdatedDate(this.updatedDate);
        taxBO.setUpdatedBy(this.updatedBy);
        if (this.type == null) {
        	return null;
		} else {
			taxBO.setType(this.type);
		}
        /*taxBO.setType(this.type);*/
        if (this.ignore == null) {
        	return null;
		} else {
			taxBO.setIgnore(this.ignore);
		}
        /*taxBO.setIgnore(this.ignore);*/
        if (this.apply == null) {
        	return null;
		} else {
			taxBO.setApply(this.apply);
		}
        /*taxBO.setApply(this.apply);*/
        if (this.status == null || this.status.equalsIgnoreCase("1")) {
        	taxBO.setStatus("1");
		} else {
			taxBO.setStatus(this.status);
		}
        return taxBO;
    }

    public java.lang.Long getValueshipmenttax() {
		return valueshipmenttax;
	}

	public void setValueshipmenttax(java.lang.Long valueshipmenttax) {
		this.valueshipmenttax = valueshipmenttax;
	}

	public java.lang.Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(java.lang.Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public java.lang.Long getTotaloriginmoney() {
		return totaloriginmoney;
	}

	public void setTotaloriginmoney(java.lang.Long totaloriginmoney) {
		this.totaloriginmoney = totaloriginmoney;
	}

	public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = org.apache.commons.lang3.StringUtils.isNotEmpty(name) ? name.toUpperCase():null;
    }
    
    public java.lang.Double getValue(){
    return value;
    }
    public void setValue(java.lang.Double value)
    {
    this.value = value;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
    }
    
    public java.lang.String getIgnore(){
    return ignore;
    }
    public void setIgnore(java.lang.String ignore)
    {
    this.ignore = ignore;
    }
    
    public java.lang.String getApply(){
    return apply;
    }
    public void setApply(java.lang.String apply)
    {
    this.apply = apply;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    @Override
     public Long getFWModelId() {
        return taxId;
    }
   
    @Override
    public String catchName() {
        return getTaxId().toString();
    }
    public java.lang.Long getTaxId(){
    return taxId;
    }
    public void setTaxId(java.lang.Long taxId)
    {
    this.taxId = taxId;
    }
    
    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }

	public java.lang.Long getShipmentTaxId() {
		return shipmentTaxId;
	}

	public void setShipmentTaxId(java.lang.Long shipmentTaxId) {
		this.shipmentTaxId = shipmentTaxId;
	}
    
   
}
