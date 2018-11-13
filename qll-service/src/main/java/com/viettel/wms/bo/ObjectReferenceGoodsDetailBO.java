/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "OBJECT_REFERENCE_GOODS_DETAIL")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ObjectReferenceGoodsDetailBO extends BaseFWModelImpl {
     
private java.lang.Long objectRefGoodsDetailId;
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

 public ObjectReferenceGoodsDetailBO() {
        setColId("objectRefGoodsDetailId");
        setColName("objectRefGoodsDetailId");
        setUniqueColumn(new String[]{"objectRefGoodsDetailId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "OBJECT_REFERENCE_GOODS_DETAIL_SEQ")
            }
    )
@Column(name = "OBJECT_REF_GOODS_DETAIL_ID", length = 22)
public java.lang.Long getObjectRefGoodsDetailId(){
return objectRefGoodsDetailId;
}
public void setObjectRefGoodsDetailId(java.lang.Long objectRefGoodsDetailId)
{
this.objectRefGoodsDetailId = objectRefGoodsDetailId;
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
@Column(name = "OBJECT_REFERENCE_ID", length = 22)
public java.lang.Long getObjectReferenceId(){
return objectReferenceId;
}
public void setObjectReferenceId(java.lang.Long objectReferenceId)
{
this.objectReferenceId = objectReferenceId;
}
   

    @Override
    public ObjectReferenceGoodsDetailDTO toDTO() {
        ObjectReferenceGoodsDetailDTO objectReferenceGoodsDetailDTO = new ObjectReferenceGoodsDetailDTO();
        //set cac gia tri 
        objectReferenceGoodsDetailDTO.setObjectRefGoodsDetailId(this.objectRefGoodsDetailId);
        objectReferenceGoodsDetailDTO.setGoodsId(this.goodsId);
        objectReferenceGoodsDetailDTO.setGoodsCode(this.goodsCode);
        objectReferenceGoodsDetailDTO.setGoodsName(this.goodsName);
        objectReferenceGoodsDetailDTO.setAmount(this.amount);
        objectReferenceGoodsDetailDTO.setSerial(this.serial);
        objectReferenceGoodsDetailDTO.setUnitTypeId(this.unitTypeId);
        objectReferenceGoodsDetailDTO.setType(this.type);
        objectReferenceGoodsDetailDTO.setManufacturer(this.manufacturer);
        objectReferenceGoodsDetailDTO.setProducerCountry(this.producerCountry);
        objectReferenceGoodsDetailDTO.setCustomsProcedure(this.customsProcedure);
        objectReferenceGoodsDetailDTO.setPartNumber(this.partNumber);
        objectReferenceGoodsDetailDTO.setPrice(this.price);
        objectReferenceGoodsDetailDTO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceGoodsDetailDTO;
    }
}
