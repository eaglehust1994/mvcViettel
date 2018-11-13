/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.viettel.erp.bo.DistanceUnloadConstrMinutesBO;
import com.viettel.erp.bo.DistanceUnloadListBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "DISTANCE_UNLOAD_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceUnloadListDTO extends BaseFWDTOImpl<DistanceUnloadListBO> {

private java.lang.Long distanceUnloadListId;
private java.lang.String materialType;
private java.lang.String unit;
private java.lang.Double quantity;
private java.lang.Double transportDistance;
private java.lang.Long roadType;
private java.lang.String slopeDegree;
private java.lang.Double truckLoad;
private java.lang.String note;
private java.lang.Long transportType;
private java.lang.Long disUnloadConsMinId;
private java.lang.String roadTypeStr;
private java.lang.Integer sttOto;
private java.lang.Integer sttCutKit;
private java.lang.Integer sttBocVac;
private String roadTypeName;

	@Override
    public DistanceUnloadListBO toModel() {
        DistanceUnloadListBO distanceUnloadListBO = new DistanceUnloadListBO();
        distanceUnloadListBO.setDistanceUnloadListId(this.distanceUnloadListId);
        distanceUnloadListBO.setMaterialType(this.materialType);
        distanceUnloadListBO.setUnit(this.unit);
        distanceUnloadListBO.setRoadTypeStr(this.roadTypeStr);
        distanceUnloadListBO.setQuantity(this.quantity);
        distanceUnloadListBO.setTransportDistance(this.transportDistance);
        distanceUnloadListBO.setRoadType(this.roadType);
        distanceUnloadListBO.setSlopeDegree(this.slopeDegree);
        distanceUnloadListBO.setTruckLoad(this.truckLoad);
        distanceUnloadListBO.setNote(this.note);
        distanceUnloadListBO.setTransportType(this.transportType);
        
        
       // distanceUnloadListBO.setDisUnloadConsMinId(this.disUnloadConsMinId);
        distanceUnloadListBO.setDistanceUnloadConstrMinutesBO(this.disUnloadConsMinId == null ? null : new DistanceUnloadConstrMinutesBO(disUnloadConsMinId));
        
        return distanceUnloadListBO;
    }

    @Override
     public Long getFWModelId() {
        return distanceUnloadListId;
    }
   
    @Override
    public String catchName() {
        return getDistanceUnloadListId().toString();
    }
    public java.lang.Long getDistanceUnloadListId(){
    return distanceUnloadListId;
    }
    public void setDistanceUnloadListId(java.lang.Long distanceUnloadListId)
    {
    this.distanceUnloadListId = distanceUnloadListId;
    }
    
    public java.lang.String getMaterialType(){
    return materialType;
    }
    public void setMaterialType(java.lang.String materialType)
    {
    this.materialType = materialType;
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
    public void setQuantity(Double quantity2)
    {
    this.quantity = quantity2;
    }
    
    public java.lang.Double getTransportDistance(){
    return transportDistance;
    }
    public void setTransportDistance(java.lang.Double transportDistance)
    {
    this.transportDistance = transportDistance;
    }
    
    public java.lang.Long getRoadType(){
    return roadType;
    }
    public void setRoadType(java.lang.Long roadType)
    {
    this.roadType = roadType;
    }
    
    public java.lang.String getSlopeDegree(){
    return slopeDegree;
    }
    public void setSlopeDegree(java.lang.String slopeDegree)
    {
    this.slopeDegree = slopeDegree;
    }
    
    public java.lang.Double getTruckLoad(){
    return truckLoad;
    }
    public void setTruckLoad(java.lang.Double truckLoad)
    {
    this.truckLoad = truckLoad;
    }
    
    public java.lang.String getNote(){
    	return Strings.nullToEmpty(note);
    }
    public void setNote(java.lang.String note)
    {
    this.note = note;
    }
    
    public java.lang.Long getTransportType(){
    return transportType;
    }
    public void setTransportType(java.lang.Long transportType)
    {
    this.transportType = transportType;
    }
    
    public java.lang.Long getDisUnloadConsMinId(){
    return disUnloadConsMinId;
    }
    public void setDisUnloadConsMinId(java.lang.Long disUnloadConsMinId)
    {
    this.disUnloadConsMinId = disUnloadConsMinId;
    }

	public java.lang.Integer getSttOto() {
		return sttOto;
	}

	public void setSttOto(java.lang.Integer sttOto) {
		this.sttOto = sttOto;
	}

	public java.lang.Integer getSttCutKit() {
		return sttCutKit;
	}

	public void setSttCutKit(java.lang.Integer sttCutKit) {
		this.sttCutKit = sttCutKit;
	}

	public java.lang.Integer getSttBocVac() {
		return sttBocVac;
	}

	public void setSttBocVac(java.lang.Integer sttBocVac) {
		this.sttBocVac = sttBocVac;
	}

	public String getRoadTypeName() {
		return roadTypeName;
	}

	public void setRoadTypeName(String roadTypeName) {
		this.roadTypeName = roadTypeName;
	}
	public java.lang.String getRoadTypeStr(){
	    return roadTypeStr;
	}
	
	public void setRoadTypeStr(java.lang.String roadTypeStr)
	{
	    this.roadTypeStr = roadTypeStr;
	}
    
}
