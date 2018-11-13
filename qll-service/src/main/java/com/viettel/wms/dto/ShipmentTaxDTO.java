/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.wms.bo.ShipmentTaxBO;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SHIPMENT_TAXBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentTaxDTO extends wmsBaseDTO<ShipmentTaxBO> {

private java.lang.Long shipmentTaxId;
private java.lang.Long taxId;
private java.lang.Long valueshipmenttax;
private java.lang.Long shipmentId;


private java.lang.String name;
private java.lang.Double valuetax;
private java.lang.String type;
private java.lang.String ignore;
private java.lang.String apply;
private java.lang.String status;
private List<ShipmentGoodsDTO> lstShipmentGoods;


    public java.lang.String getStatus() {
	return status;
}



public void setStatus(java.lang.String status) {
	this.status = status;
}



	@Override
    public ShipmentTaxBO toModel() {
        ShipmentTaxBO shipmentTaxBO = new ShipmentTaxBO();
        shipmentTaxBO.setShipmentTaxId(this.shipmentTaxId);
        shipmentTaxBO.setTaxId(this.taxId);
        shipmentTaxBO.setValueshipmenttax(this.valueshipmenttax);
        shipmentTaxBO.setShipmentId(this.shipmentId);
        return shipmentTaxBO;
    }



	public java.lang.Long getShipmentTaxId(){
    return shipmentTaxId;
    }
    public void setShipmentTaxId(java.lang.Long shipmentTaxId)
    {
    this.shipmentTaxId = shipmentTaxId;
    }
    
    public java.lang.Long getTaxId(){
    return taxId;
    }
    public void setTaxId(java.lang.Long taxId)
    {
    this.taxId = taxId;
    }
    
   
    public java.lang.Long getValueshipmenttax() {
		return valueshipmenttax;
	}



	public void setValueshipmenttax(java.lang.Long valueshipmenttax) {
		this.valueshipmenttax = valueshipmenttax;
	}



	@Override
     public Long getFWModelId() {
        return shipmentId;
    }
   
    @Override
    public String catchName() {
        return getShipmentId().toString();
    }
    public java.lang.Long getShipmentId(){
    return shipmentId;
    }
    public void setShipmentId(java.lang.Long shipmentId)
    {
    this.shipmentId = shipmentId;
    }

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Double getValuetax() {
		return valuetax;
	}

	public void setValuetax(java.lang.Double valuetax) {
		this.valuetax = valuetax;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getIgnore() {
		return ignore;
	}

	public void setIgnore(java.lang.String ignore) {
		this.ignore = ignore;
	}

	public java.lang.String getApply() {
		return apply;
	}

	public void setApply(java.lang.String apply) {
		this.apply = apply;
	}



	public List<ShipmentGoodsDTO> getLstShipmentGoods() {
		return lstShipmentGoods;
	}



	public void setLstShipmentGoods(List<ShipmentGoodsDTO> lstShipmentGoods) {
		this.lstShipmentGoods = lstShipmentGoods;
	}
    
}
