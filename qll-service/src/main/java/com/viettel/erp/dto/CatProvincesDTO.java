/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatProvincesBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_PROVINCESBO")
public class CatProvincesDTO extends BaseFWDTOImpl<CatProvincesBO> {

private java.lang.Long provinceId;
private java.lang.String provinceName;
private java.lang.String provinceCode;
private java.lang.String postalCode;
private java.lang.String areaCode;
private java.lang.Long isActive;
private java.lang.Long groupId;
private java.lang.Long parentId;
private java.lang.Long areaCeilingPrice;

    @Override
    public CatProvincesBO toModel() {
        CatProvincesBO catProvincesBO = new CatProvincesBO();
        catProvincesBO.setProvinceId(this.provinceId);
        catProvincesBO.setProvinceName(this.provinceName);
        catProvincesBO.setProvinceCode(this.provinceCode);
        catProvincesBO.setPostalCode(this.postalCode);
        catProvincesBO.setAreaCode(this.areaCode);
        catProvincesBO.setIsActive(this.isActive);
        catProvincesBO.setGroupId(this.groupId);
        catProvincesBO.setParentId(this.parentId);
        catProvincesBO.setAreaCeilingPrice(this.areaCeilingPrice);
        return catProvincesBO;
    }

    @Override
     public Long getFWModelId() {
        return provinceId;
    }
   
    @Override
    public String catchName() {
        return getProvinceId().toString();
    }
    public java.lang.Long getProvinceId(){
    return provinceId;
    }
    public void setProvinceId(java.lang.Long provinceId)
    {
    this.provinceId = provinceId;
    }
    
    public java.lang.String getProvinceName(){
    return provinceName;
    }
    public void setProvinceName(java.lang.String provinceName)
    {
    this.provinceName = provinceName;
    }
    
    public java.lang.String getProvinceCode(){
    return provinceCode;
    }
    public void setProvinceCode(java.lang.String provinceCode)
    {
    this.provinceCode = provinceCode;
    }
    
    public java.lang.String getPostalCode(){
    return postalCode;
    }
    public void setPostalCode(java.lang.String postalCode)
    {
    this.postalCode = postalCode;
    }
    
    public java.lang.String getAreaCode(){
    return areaCode;
    }
    public void setAreaCode(java.lang.String areaCode)
    {
    this.areaCode = areaCode;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getGroupId(){
    return groupId;
    }
    public void setGroupId(java.lang.Long groupId)
    {
    this.groupId = groupId;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.Long getAreaCeilingPrice(){
    return areaCeilingPrice;
    }
    public void setAreaCeilingPrice(java.lang.Long areaCeilingPrice)
    {
    this.areaCeilingPrice = areaCeilingPrice;
    }
    
   
}
