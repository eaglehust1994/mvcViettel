/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.DistanceUnloadListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "distanceUnloadList")
@Table(name = "DISTANCE_UNLOAD_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class DistanceUnloadListBO extends BaseFWModelImpl {
     
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
private DistanceUnloadConstrMinutesBO distanceUnloadConstrMinutesBO;


@ManyToOne
@JoinColumn(name = "DIS_UNLOAD_CONS_MIN_ID")
public DistanceUnloadConstrMinutesBO getDistanceUnloadConstrMinutesBO() {
	return distanceUnloadConstrMinutesBO;
}

public void setDistanceUnloadConstrMinutesBO(DistanceUnloadConstrMinutesBO distanceUnloadConstrMinutesBO) {
	this.distanceUnloadConstrMinutesBO = distanceUnloadConstrMinutesBO;
}

public DistanceUnloadListBO() {
        setColId("distanceUnloadListId");
        setColName("distanceUnloadListId");
        setUniqueColumn(new String[]{"distanceUnloadListId"});
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "DISTANCE_UNLOAD_LIST_SEQ")
            }
    )
@Column(name = "DISTANCE_UNLOAD_LIST_ID", length = 22)
public java.lang.Long getDistanceUnloadListId(){
return distanceUnloadListId;
}
public void setDistanceUnloadListId(java.lang.Long distanceUnloadListId)
{
this.distanceUnloadListId = distanceUnloadListId;
}
@Column(name = "MATERIAL_TYPE", length = 400)
public java.lang.String getMaterialType(){
return materialType;
}
public void setMaterialType(java.lang.String materialType)
{
this.materialType = materialType;
}
@Column(name = "UNIT", length = 400)
public java.lang.String getUnit(){
return unit;
}
public void setUnit(java.lang.String unit)
{
this.unit = unit;
}
@Column(name = "QUANTITY")
public java.lang.Double getQuantity(){
return quantity;
}
public void setQuantity(java.lang.Double quantity)
{
this.quantity = quantity;
}
@Column(name = "TRANSPORT_DISTANCE")
public java.lang.Double getTransportDistance(){
return transportDistance;
}
public void setTransportDistance(java.lang.Double transportDistance)
{
this.transportDistance = transportDistance;
}
@Column(name = "ROAD_TYPE", length = 22)
public java.lang.Long getRoadType(){
return roadType;
}
public void setRoadType(java.lang.Long roadType)
{
this.roadType = roadType;
}
@Column(name = "SLOPE_DEGREE", length = 400)
public java.lang.String getSlopeDegree(){
return slopeDegree;
}
public void setSlopeDegree(java.lang.String slopeDegree)
{
this.slopeDegree = slopeDegree;
}
@Column(name = "TRUCK_LOAD", length = 22)
public java.lang.Double getTruckLoad(){
return truckLoad;
}
public void setTruckLoad(java.lang.Double truckLoad)
{
this.truckLoad = truckLoad;
}
@Column(name = "NOTE", length = 2000)
public java.lang.String getNote(){
return note;
}
public void setNote(java.lang.String note)
{
this.note = note;
}
@Column(name = "TRANSPORT_TYPE", length = 22)
public java.lang.Long getTransportType(){
return transportType;
}
public void setTransportType(java.lang.Long transportType)
{
this.transportType = transportType;
}
/*@Column(name = "DIS_UNLOAD_CONS_MIN_ID", length = 22)
public java.lang.Long getDisUnloadConsMinId(){
return disUnloadConsMinId;
}
public void setDisUnloadConsMinId(java.lang.Long disUnloadConsMinId)
{
this.disUnloadConsMinId = disUnloadConsMinId;
}*/
@Column(name = "ROAD_TYPE_STR", length = 400)
public java.lang.String getRoadTypeStr(){
return roadTypeStr;
}
public void setRoadTypeStr(java.lang.String roadTypeStr)
{
this.roadTypeStr = roadTypeStr;
}

    @Override
    public DistanceUnloadListDTO toDTO() {
        DistanceUnloadListDTO distanceUnloadListDTO = new DistanceUnloadListDTO();
        //set cac gia tri
        distanceUnloadListDTO.setDistanceUnloadListId(this.distanceUnloadListId);
        distanceUnloadListDTO.setMaterialType(this.materialType);
        distanceUnloadListDTO.setUnit(this.unit);
        distanceUnloadListDTO.setQuantity(this.quantity);
        distanceUnloadListDTO.setTransportDistance(this.transportDistance);
        distanceUnloadListDTO.setRoadType(this.roadType);
        distanceUnloadListDTO.setSlopeDegree(this.slopeDegree);
        distanceUnloadListDTO.setTruckLoad(this.truckLoad);
        distanceUnloadListDTO.setNote(this.note);
        distanceUnloadListDTO.setTransportType(this.transportType);
        distanceUnloadListDTO.setDisUnloadConsMinId(this.disUnloadConsMinId);
        distanceUnloadListDTO.setRoadTypeStr(this.roadTypeStr);
        return distanceUnloadListDTO;
    }
}
