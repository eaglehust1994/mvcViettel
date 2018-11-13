/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.VApConstructionBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "V_AP_CONSTRUCTIONBO")
public class VApConstructionDTO extends BaseFWDTOImpl<VApConstructionBO> {

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

    @Override
    public VApConstructionBO toModel() {
        VApConstructionBO vApConstructionBO = new VApConstructionBO();
        vApConstructionBO.setConstructId(this.constructId);
        vApConstructionBO.setConstrtCode(this.constrtCode);
        vApConstructionBO.setStatus(this.status);
        vApConstructionBO.setConstrtName(this.constrtName);
        vApConstructionBO.setProvinceCode(this.provinceCode);
        vApConstructionBO.setProvinceId(this.provinceId);
        vApConstructionBO.setStationCode(this.stationCode);
        vApConstructionBO.setConstrTypeId(this.constrTypeId);
        vApConstructionBO.setStatusName(this.statusName);
        vApConstructionBO.setStatusId(this.statusId);
        vApConstructionBO.setPreStartingDate(this.preStartingDate);
        vApConstructionBO.setAlert(this.alert);
        vApConstructionBO.setSysCreatedDate(this.sysCreatedDate);
        vApConstructionBO.setIsOff(this.isOff);
        return vApConstructionBO;
    }

    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.String getConstrtCode(){
    return constrtCode;
    }
    public void setConstrtCode(java.lang.String constrtCode)
    {
    this.constrtCode = constrtCode;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.String getConstrtName(){
    return constrtName;
    }
    public void setConstrtName(java.lang.String constrtName)
    {
    this.constrtName = constrtName;
    }
    
    public java.lang.String getProvinceCode(){
    return provinceCode;
    }
    public void setProvinceCode(java.lang.String provinceCode)
    {
    this.provinceCode = provinceCode;
    }
    
    public java.lang.Long getProvinceId(){
    return provinceId;
    }
    public void setProvinceId(java.lang.Long provinceId)
    {
    this.provinceId = provinceId;
    }
    
    public java.lang.String getStationCode(){
    return stationCode;
    }
    public void setStationCode(java.lang.String stationCode)
    {
    this.stationCode = stationCode;
    }
    
    public java.lang.Long getConstrTypeId(){
    return constrTypeId;
    }
    public void setConstrTypeId(java.lang.Long constrTypeId)
    {
    this.constrTypeId = constrTypeId;
    }
    
    public java.lang.String getStatusName(){
    return statusName;
    }
    public void setStatusName(java.lang.String statusName)
    {
    this.statusName = statusName;
    }
    
    public java.lang.Long getStatusId(){
    return statusId;
    }
    public void setStatusId(java.lang.Long statusId)
    {
    this.statusId = statusId;
    }
    
    public java.util.Date getPreStartingDate(){
    return preStartingDate;
    }
    public void setPreStartingDate(java.util.Date preStartingDate)
    {
    this.preStartingDate = preStartingDate;
    }
    
    public java.lang.Long getAlert(){
    return alert;
    }
    public void setAlert(java.lang.Long alert)
    {
    this.alert = alert;
    }
    
    public java.util.Date getSysCreatedDate(){
    return sysCreatedDate;
    }
    public void setSysCreatedDate(java.util.Date sysCreatedDate)
    {
    this.sysCreatedDate = sysCreatedDate;
    }
    
    public java.lang.Long getIsOff(){
    return isOff;
    }
    public void setIsOff(java.lang.Long isOff)
    {
    this.isOff = isOff;
    }

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}
    
   
}
