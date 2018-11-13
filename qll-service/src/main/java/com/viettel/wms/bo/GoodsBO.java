/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.GoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.GOODS")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class GoodsBO extends BaseFWModelImpl {
     
private java.util.Date updatedDate;
private java.lang.Long goodsId;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long updatedBy;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.String producingCountryName;
private java.lang.String manufacturerName;
private java.lang.Long producingCountryId;
private java.lang.Long manufacturerId;
private java.lang.String description;
private java.lang.Double volumeReal;
private java.lang.Double volumeOrigin;
private java.lang.Double weight;
private java.lang.Double originSize;
private java.lang.Double originPrice;
private java.lang.String isSerial;
private java.lang.String goodsType;
private java.lang.Long unitType;
private java.lang.String unitTypeName;
private java.lang.String status;

 public GoodsBO() {
        setColId("manufacturerId");
        setColName("manufacturerId");
        setUniqueColumn(new String[]{"manufacturerId"});
}

@Column(name = "UPDATED_DATE", length = 7)
public java.util.Date getUpdatedDate(){
return updatedDate;
}
public void setUpdatedDate(java.util.Date updatedDate)
{
this.updatedDate = updatedDate;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.GOODS_SEQ")
            }
    )
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
@Column(name = "PRODUCING_COUNTRY_NAME", length = 2000)
public java.lang.String getProducingCountryName(){
return producingCountryName;
}
public void setProducingCountryName(java.lang.String producingCountryName)
{
this.producingCountryName = producingCountryName;
}
@Column(name = "MANUFACTURER_NAME", length = 2000)
public java.lang.String getManufacturerName(){
return manufacturerName;
}
public void setManufacturerName(java.lang.String manufacturerName)
{
this.manufacturerName = manufacturerName;
}
@Column(name = "PRODUCING_COUNTRY_ID", length = 22)
public java.lang.Long getProducingCountryId(){
return producingCountryId;
}
public void setProducingCountryId(java.lang.Long producingCountryId)
{
this.producingCountryId = producingCountryId;
}
@Column(name = "MANUFACTURER_ID", length = 22)
public java.lang.Long getManufacturerId(){
return manufacturerId;
}
public void setManufacturerId(java.lang.Long manufacturerId)
{
this.manufacturerId = manufacturerId;
}
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "VOLUME_REAL", length = 22)
public java.lang.Double getVolumeReal(){
return volumeReal;
}
public void setVolumeReal(java.lang.Double volumeReal)
{
this.volumeReal = volumeReal;
}
@Column(name = "VOLUME_ORIGIN", length = 22)
public java.lang.Double getVolumeOrigin(){
return volumeOrigin;
}
public void setVolumeOrigin(java.lang.Double volumeOrigin)
{
this.volumeOrigin = volumeOrigin;
}
@Column(name = "WEIGHT", length = 22)
public java.lang.Double getWeight(){
return weight;
}
public void setWeight(java.lang.Double weight)
{
this.weight = weight;
}
@Column(name = "ORIGIN_SIZE", length = 22)
public java.lang.Double getOriginSize(){
return originSize;
}
public void setOriginSize(java.lang.Double originSize)
{
this.originSize = originSize;
}
@Column(name = "ORIGIN_PRICE", length = 22)
public java.lang.Double getOriginPrice(){
return originPrice;
}
public void setOriginPrice(java.lang.Double originPrice)
{
this.originPrice = originPrice;
}
@Column(name = "IS_SERIAL", length = 1)
public java.lang.String getIsSerial(){
return isSerial;
}
public void setIsSerial(java.lang.String isSerial)
{
this.isSerial = isSerial;
}
@Column(name = "GOODS_TYPE", length = 2)
public java.lang.String getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.String goodsType)
{
this.goodsType = goodsType;
}
@Column(name = "UNIT_TYPE", length = 22)
public java.lang.Long getUnitType(){
return unitType;
}
public void setUnitType(java.lang.Long unitType)
{
this.unitType = unitType;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "UNIT_TYPE_NAME", length = 50)  
public java.lang.String getUnitTypeName() {
	return unitTypeName;
}
public void setUnitTypeName(java.lang.String unitTypeName) {
	this.unitTypeName = unitTypeName;
}

	@Override
    public GoodsDTO toDTO() {
        GoodsDTO goodsDTO = new GoodsDTO();
        //set cac gia tri 
        goodsDTO.setUpdatedDate(this.updatedDate);
        goodsDTO.setGoodsId(this.goodsId);
        goodsDTO.setCode(this.code);
        goodsDTO.setName(this.name);
        goodsDTO.setUpdatedBy(this.updatedBy);
        goodsDTO.setCreatedDate(this.createdDate);
        goodsDTO.setCreatedBy(this.createdBy);
        goodsDTO.setProducingCountryName(this.producingCountryName);
        goodsDTO.setManufacturerName(this.manufacturerName);
        goodsDTO.setProducingCountryId(this.producingCountryId);
        goodsDTO.setManufacturerId(this.manufacturerId);
        goodsDTO.setDescription(this.description);
        goodsDTO.setVolumeReal(this.volumeReal);
        goodsDTO.setVolumeOrigin(this.volumeOrigin);
        goodsDTO.setWeight(this.weight);
        goodsDTO.setOriginSize(this.originSize);
        goodsDTO.setOriginPrice(this.originPrice);
        goodsDTO.setIsSerial(this.isSerial);
        goodsDTO.setGoodsType(this.goodsType);
        goodsDTO.setUnitType(this.unitType);
        goodsDTO.setUnitTypeName(this.unitTypeName);
        goodsDTO.setStatus(this.status);
        return goodsDTO;
    }
}
