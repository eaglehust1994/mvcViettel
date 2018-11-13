/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.VApConstructionDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity

/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class VApConstructionBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.String constrtCode;
private java.lang.Long status;
private java.lang.String constrtName;
private java.lang.String provinceCode;
private java.lang.Long provinceId;
private java.lang.String stationCode;
private java.lang.Long constrTypeId;
private java.lang.String statusName;
private java.lang.Long statusId;
private java.util.Date preStartingDate;
private java.lang.Long alert;
private java.util.Date sysCreatedDate;
private java.lang.Long isOff;

 public VApConstructionBO() {
        setColId("constructId");
        setColName("constructId");
        setUniqueColumn(new String[]{"constructId"});
}
@Id
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "CONSTRT_CODE", length = 200)
public java.lang.String getConstrtCode(){
return constrtCode;
}
public void setConstrtCode(java.lang.String constrtCode)
{
this.constrtCode = constrtCode;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "CONSTRT_NAME", length = 600)
public java.lang.String getConstrtName(){
return constrtName;
}
public void setConstrtName(java.lang.String constrtName)
{
this.constrtName = constrtName;
}
@Column(name = "PROVINCE_CODE", length = 40)
public java.lang.String getProvinceCode(){
return provinceCode;
}
public void setProvinceCode(java.lang.String provinceCode)
{
this.provinceCode = provinceCode;
}
@Column(name = "PROVINCE_ID", length = 22)
public java.lang.Long getProvinceId(){
return provinceId;
}
public void setProvinceId(java.lang.Long provinceId)
{
this.provinceId = provinceId;
}
@Column(name = "STATION_CODE", length = 1200)
public java.lang.String getStationCode(){
return stationCode;
}
public void setStationCode(java.lang.String stationCode)
{
this.stationCode = stationCode;
}
@Column(name = "CONSTR_TYPE_ID", length = 22)
public java.lang.Long getConstrTypeId(){
return constrTypeId;
}
public void setConstrTypeId(java.lang.Long constrTypeId)
{
this.constrTypeId = constrTypeId;
}
@Column(name = "STATUS_NAME", length = 400)
public java.lang.String getStatusName(){
return statusName;
}
public void setStatusName(java.lang.String statusName)
{
this.statusName = statusName;
}
@Column(name = "STATUS_ID", length = 22)
public java.lang.Long getStatusId(){
return statusId;
}
public void setStatusId(java.lang.Long statusId)
{
this.statusId = statusId;
}
@Column(name = "PRE_STARTING_DATE", length = 7)
public java.util.Date getPreStartingDate(){
return preStartingDate;
}
public void setPreStartingDate(java.util.Date preStartingDate)
{
this.preStartingDate = preStartingDate;
}
@Column(name = "ALERT", length = 22)
public java.lang.Long getAlert(){
return alert;
}
public void setAlert(java.lang.Long alert)
{
this.alert = alert;
}
@Column(name = "SYS_CREATED_DATE", length = 7)
public java.util.Date getSysCreatedDate(){
return sysCreatedDate;
}
public void setSysCreatedDate(java.util.Date sysCreatedDate)
{
this.sysCreatedDate = sysCreatedDate;
}
@Column(name = "IS_OFF", length = 22)
public java.lang.Long getIsOff(){
return isOff;
}
public void setIsOff(java.lang.Long isOff)
{
this.isOff = isOff;
}
   

    @Override
    public VApConstructionDTO toDTO() {
        VApConstructionDTO vApConstructionDTO = new VApConstructionDTO();
        //set cac gia tri 
        vApConstructionDTO.setConstructId(this.constructId);
        vApConstructionDTO.setConstrtCode(this.constrtCode);
        vApConstructionDTO.setStatus(this.status);
        vApConstructionDTO.setConstrtName(this.constrtName);
        vApConstructionDTO.setProvinceCode(this.provinceCode);
        vApConstructionDTO.setProvinceId(this.provinceId);
        vApConstructionDTO.setStationCode(this.stationCode);
        vApConstructionDTO.setConstrTypeId(this.constrTypeId);
        vApConstructionDTO.setStatusName(this.statusName);
        vApConstructionDTO.setStatusId(this.statusId);
        vApConstructionDTO.setPreStartingDate(this.preStartingDate);
        vApConstructionDTO.setAlert(this.alert);
        vApConstructionDTO.setSysCreatedDate(this.sysCreatedDate);
        vApConstructionDTO.setIsOff(this.isOff);
        return vApConstructionDTO;
    }
}
