/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatProvincesDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "CAT_PROVINCES")
@Where( clause = "IS_ACTIVE = 1" )
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatProvincesBO extends BaseFWModelImpl {
     
private java.lang.Long provinceId;
private java.lang.String provinceName;
private java.lang.String provinceCode;
private java.lang.String postalCode;
private java.lang.String areaCode;
private java.lang.Long isActive;
private java.lang.Long groupId;
private java.lang.Long parentId;
private java.lang.Long areaCeilingPrice;

 public CatProvincesBO() {
        setColId("provinceId");
        setColName("provinceId");
        setUniqueColumn(new String[]{"provinceId"});
}
	public CatProvincesBO(Long id) {
		this.provinceId = id;
	}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_PROVINCES_SEQ")
            }
    )
@Column(name = "PROVINCE_ID", length = 22)
public java.lang.Long getProvinceId(){
return provinceId;
}
public void setProvinceId(java.lang.Long provinceId)
{
this.provinceId = provinceId;
}
@Column(name = "PROVINCE_NAME", length = 100)
public java.lang.String getProvinceName(){
return provinceName;
}
public void setProvinceName(java.lang.String provinceName)
{
this.provinceName = provinceName;
}
@Column(name = "PROVINCE_CODE", length = 40)
public java.lang.String getProvinceCode(){
return provinceCode;
}
public void setProvinceCode(java.lang.String provinceCode)
{
this.provinceCode = provinceCode;
}
@Column(name = "POSTAL_CODE", length = 20)
public java.lang.String getPostalCode(){
return postalCode;
}
public void setPostalCode(java.lang.String postalCode)
{
this.postalCode = postalCode;
}
@Column(name = "AREA_CODE", length = 20)
public java.lang.String getAreaCode(){
return areaCode;
}
public void setAreaCode(java.lang.String areaCode)
{
this.areaCode = areaCode;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "GROUP_ID", length = 22)
public java.lang.Long getGroupId(){
return groupId;
}
public void setGroupId(java.lang.Long groupId)
{
this.groupId = groupId;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "AREA_CEILING_PRICE", length = 22)
public java.lang.Long getAreaCeilingPrice(){
return areaCeilingPrice;
}
public void setAreaCeilingPrice(java.lang.Long areaCeilingPrice)
{
this.areaCeilingPrice = areaCeilingPrice;
}
   

    @Override
    public CatProvincesDTO toDTO() {
        CatProvincesDTO catProvincesDTO = new CatProvincesDTO();
        //set cac gia tri 
        catProvincesDTO.setProvinceId(this.provinceId);
        catProvincesDTO.setProvinceName(this.provinceName);
        catProvincesDTO.setProvinceCode(this.provinceCode);
        catProvincesDTO.setPostalCode(this.postalCode);
        catProvincesDTO.setAreaCode(this.areaCode);
        catProvincesDTO.setIsActive(this.isActive);
        catProvincesDTO.setGroupId(this.groupId);
        catProvincesDTO.setParentId(this.parentId);
        catProvincesDTO.setAreaCeilingPrice(this.areaCeilingPrice);
        return catProvincesDTO;
    }
}
