/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AAssetDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "A_ASSET")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AAssetBO extends BaseFWModelImpl {
     
private java.lang.String isDeleted;
private java.lang.Long aAssetId;

private java.lang.Long aAssetTypeId;
private java.lang.Long aAssetGroupId;
private java.lang.Long adOrgId;
private java.lang.String value;
private java.lang.String name;
private java.util.Date dateused;
private java.util.Date repairdate;
private java.lang.String manufactturer;
private java.lang.String model;
private java.lang.String serial;
private java.lang.String power;
private java.lang.String gasType;
private java.lang.String fuel;
private java.lang.String isactive;
private java.lang.Long createdby;
private java.lang.Long updatedby;
private java.util.Date created;
private java.util.Date updated;


@Column(name = "IS_DELETED", length = 1)
public java.lang.String getIsDeleted(){
return isDeleted;
}
public void setIsDeleted(java.lang.String isDeleted)
{
this.isDeleted = isDeleted;
}
@Id
@GeneratedValue(generator = "sequence")
@GenericGenerator(name = "sequence", strategy = "sequence",
        parameters = {
            @Parameter(name = "sequence", value = "A_ASSET_SEQ")
        }
)
@Column(name = "A_ASSET_ID", length = 22)
public java.lang.Long getAAssetId(){
return aAssetId;
}
public void setAAssetId(java.lang.Long aAssetId)
{
this.aAssetId = aAssetId;
}
@Column(name = "A_ASSET_TYPE_ID", length = 22)
public java.lang.Long getAAssetTypeId(){
return aAssetTypeId;
}
public void setAAssetTypeId(java.lang.Long aAssetTypeId)
{
this.aAssetTypeId = aAssetTypeId;
}
@Column(name = "A_ASSET_GROUP_ID", length = 22)
public java.lang.Long getAAssetGroupId(){
return aAssetGroupId;
}
public void setAAssetGroupId(java.lang.Long aAssetGroupId)
{
this.aAssetGroupId = aAssetGroupId;
}
@Column(name = "AD_ORG_ID", length = 22)
public java.lang.Long getAdOrgId(){
return adOrgId;
}
public void setAdOrgId(java.lang.Long adOrgId)
{
this.adOrgId = adOrgId;
}
@Column(name = "VALUE", length = 40)
public java.lang.String getValue(){
return value;
}
public void setValue(java.lang.String value)
{
this.value = value;
}
@Column(name = "NAME", length = 510)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "DATEUSED", length = 7)
public java.util.Date getDateused(){
return dateused;
}
public void setDateused(java.util.Date dateused)
{
this.dateused = dateused;
}
@Column(name = "REPAIRDATE", length = 7)
public java.util.Date getRepairdate(){
return repairdate;
}
public void setRepairdate(java.util.Date repairdate)
{
this.repairdate = repairdate;
}
@Column(name = "MANUFACTTURER", length = 510)
public java.lang.String getManufactturer(){
return manufactturer;
}
public void setManufactturer(java.lang.String manufactturer)
{
this.manufactturer = manufactturer;
}
@Column(name = "MODEL", length = 100)
public java.lang.String getModel(){
return model;
}
public void setModel(java.lang.String model)
{
this.model = model;
}
@Column(name = "SERIAL", length = 40)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "POWER", length = 22)
public java.lang.String getPower(){
return power;
}
public void setPower(java.lang.String power)
{
this.power = power;
}
@Column(name = "GAS_TYPE", length = 510)
public java.lang.String getGasType(){
return gasType;
}
public void setGasType(java.lang.String gasType)
{
this.gasType = gasType;
}
@Column(name = "FUEL", length = 510)
public java.lang.String getFuel(){
return fuel;
}
public void setFuel(java.lang.String fuel)
{
this.fuel = fuel;
}
@Column(name = "ISACTIVE", length = 1)
public java.lang.String getIsactive(){
return isactive;
}
public void setIsactive(java.lang.String isactive)
{
this.isactive = isactive;
}
@Column(name = "CREATEDBY", length = 22)
public java.lang.Long getCreatedby(){
return createdby;
}
public void setCreatedby(java.lang.Long createdby)
{
this.createdby = createdby;
}
@Column(name = "UPDATEDBY", length = 22)
public java.lang.Long getUpdatedby(){
return updatedby;
}
public void setUpdatedby(java.lang.Long updatedby)
{
this.updatedby = updatedby;
}
@Column(name = "CREATED", length = 7)
public java.util.Date getCreated(){
return created;
}
public void setCreated(java.util.Date created)
{
this.created = created;
}
@Column(name = "UPDATED", length = 7)
public java.util.Date getUpdated(){
return updated;
}
public void setUpdated(java.util.Date updated)
{
this.updated = updated;
}
   

    @Override
    public AAssetDTO toDTO() {
        AAssetDTO aAssetDTO = new AAssetDTO();
        //set cac gia tri 
        aAssetDTO.setIsDeleted(this.isDeleted);
        aAssetDTO.setAAssetId(this.aAssetId);
        aAssetDTO.setAAssetTypeId(this.aAssetTypeId);
        aAssetDTO.setAAssetGroupId(this.aAssetGroupId);
        aAssetDTO.setAdOrgId(this.adOrgId);
        aAssetDTO.setValue(this.value);
        aAssetDTO.setName(this.name);
        aAssetDTO.setDateused(this.dateused);
        aAssetDTO.setRepairdate(this.repairdate);
        aAssetDTO.setManufactturer(this.manufactturer);
        aAssetDTO.setModel(this.model);
        aAssetDTO.setSerial(this.serial);
        aAssetDTO.setPower(this.power);
        aAssetDTO.setGasType(this.gasType);
        aAssetDTO.setFuel(this.fuel);
        aAssetDTO.setIsactive(this.isactive);
        aAssetDTO.setCreatedby(this.createdby);
        aAssetDTO.setUpdatedby(this.updatedby);
        aAssetDTO.setCreated(this.created);
        aAssetDTO.setUpdated(this.updated);
        return aAssetDTO;
    }
}
