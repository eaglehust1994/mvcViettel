/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ObjectReferenceDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.OBJECT_REFERENCE_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ObjectReferenceDetailBO extends BaseFWModelImpl {
     
private java.lang.Long objectReferenceDetailId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.String serial;
private java.lang.Long unitTypeId;
private java.lang.String type;
private java.lang.String manufacturer;
private java.lang.String producerCountry;
private java.lang.String customsProcedure;
private java.lang.String partNumber;
private java.lang.Double price;
private java.lang.Long objectReferenceId;

 public ObjectReferenceDetailBO() {
        setColId("objectReferenceId");
        setColName("objectReferenceId");
        setUniqueColumn(new String[]{"objectReferenceId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.OBJECT_REFERENCE_DETAIL_SEQ")
            }
    )
@Column(name = "OBJECT_REFERENCE_DETAIL_ID", length = 22)
public java.lang.Long getObjectReferenceDetailId(){
return objectReferenceDetailId;
}
public void setObjectReferenceDetailId(java.lang.Long objectReferenceDetailId)
{
this.objectReferenceDetailId = objectReferenceDetailId;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "GOODS_CODE", length = 200)
public java.lang.String getGoodsCode(){
return goodsCode;
}
public void setGoodsCode(java.lang.String goodsCode)
{
this.goodsCode = goodsCode;
}
@Column(name = "GOODS_NAME", length = 200)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "AMOUNT", length = 22)
public java.lang.Double getAmount(){
return amount;
}
public void setAmount(java.lang.Double amount)
{
this.amount = amount;
}
@Column(name = "SERIAL", length = 200)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "UNIT_TYPE_ID", length = 22)
public java.lang.Long getUnitTypeId(){
return unitTypeId;
}
public void setUnitTypeId(java.lang.Long unitTypeId)
{
this.unitTypeId = unitTypeId;
}
@Column(name = "TYPE", length = 1)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "MANUFACTURER", length = 400)
public java.lang.String getManufacturer(){
return manufacturer;
}
public void setManufacturer(java.lang.String manufacturer)
{
this.manufacturer = manufacturer;
}
@Column(name = "PRODUCER_COUNTRY", length = 400)
public java.lang.String getProducerCountry(){
return producerCountry;
}
public void setProducerCountry(java.lang.String producerCountry)
{
this.producerCountry = producerCountry;
}
@Column(name = "CUSTOMS_PROCEDURE", length = 400)
public java.lang.String getCustomsProcedure(){
return customsProcedure;
}
public void setCustomsProcedure(java.lang.String customsProcedure)
{
this.customsProcedure = customsProcedure;
}
@Column(name = "PART_NUMBER", length = 400)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
}
@Column(name = "PRICE", length = 22)
public java.lang.Double getPrice(){
return price;
}
public void setPrice(java.lang.Double price)
{
this.price = price;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.OBJECT_REFERENCE_DETAIL_SEQ")
            }
    )
@Column(name = "OBJECT_REFERENCE_ID", length = 22)
public java.lang.Long getObjectReferenceId(){
return objectReferenceId;
}
public void setObjectReferenceId(java.lang.Long objectReferenceId)
{
this.objectReferenceId = objectReferenceId;
}
   

    @Override
    public ObjectReferenceDetailDTO toDTO() {
        ObjectReferenceDetailDTO objectReferenceDetailDTO = new ObjectReferenceDetailDTO();
        //set cac gia tri 
        objectReferenceDetailDTO.setObjectReferenceDetailId(this.objectReferenceDetailId);
        objectReferenceDetailDTO.setGoodsId(this.goodsId);
        objectReferenceDetailDTO.setGoodsCode(this.goodsCode);
        objectReferenceDetailDTO.setGoodsName(this.goodsName);
        objectReferenceDetailDTO.setAmount(this.amount);
        objectReferenceDetailDTO.setSerial(this.serial);
        objectReferenceDetailDTO.setUnitTypeId(this.unitTypeId);
        objectReferenceDetailDTO.setType(this.type);
        objectReferenceDetailDTO.setManufacturer(this.manufacturer);
        objectReferenceDetailDTO.setProducerCountry(this.producerCountry);
        objectReferenceDetailDTO.setCustomsProcedure(this.customsProcedure);
        objectReferenceDetailDTO.setPartNumber(this.partNumber);
        objectReferenceDetailDTO.setPrice(this.price);
        objectReferenceDetailDTO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceDetailDTO;
    }
}
