/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ShipmentGoodsDetailBO extends BaseFWModelImpl {
     
private java.lang.Long shipmentGoodsDetailId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.String serial;
private java.lang.Long unitTypeId;
private java.lang.String unitTypeName;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double applyPrice;
private java.lang.String partNumber;
private java.lang.Double price;
private java.lang.Long shipmentId;
private java.lang.Long shipmentGoodsId;

 public ShipmentGoodsDetailBO() {
        setColId("shipmentGoodsId");
        setColName("shipmentGoodsId");
        setUniqueColumn(new String[]{"shipmentGoodsId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL_SEQ")
            }
    )
@Column(name = "SHIPMENT_GOODS_DETAIL_ID", length = 22)
public java.lang.Long getShipmentGoodsDetailId(){
return shipmentGoodsDetailId;
}
public void setShipmentGoodsDetailId(java.lang.Long shipmentGoodsDetailId)
{
this.shipmentGoodsDetailId = shipmentGoodsDetailId;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "GOODS_CODE", length = 100)
public java.lang.String getGoodsCode(){
return goodsCode;
}
public void setGoodsCode(java.lang.String goodsCode)
{
this.goodsCode = goodsCode;
}
@Column(name = "GOODS_NAME", length = 400)
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
@Column(name = "UNIT_TYPE_NAME", length = 400)
public java.lang.String getUnitTypeName(){
return unitTypeName;
}
public void setUnitTypeName(java.lang.String unitTypeName)
{
this.unitTypeName = unitTypeName;
}
@Column(name = "MANUFACTURER_ID", length = 22)
public java.lang.Long getManufacturerId(){
return manufacturerId;
}
public void setManufacturerId(java.lang.Long manufacturerId)
{
this.manufacturerId = manufacturerId;
}
@Column(name = "PRODUCING_COUNTRY_ID", length = 22)
public java.lang.Long getProducingCountryId(){
return producingCountryId;
}
public void setProducingCountryId(java.lang.Long producingCountryId)
{
this.producingCountryId = producingCountryId;
}
@Column(name = "MANUFACTURER_NAME", length = 2000)
public java.lang.String getManufacturerName(){
return manufacturerName;
}
public void setManufacturerName(java.lang.String manufacturerName)
{
this.manufacturerName = manufacturerName;
}
@Column(name = "PRODUCING_COUNTRY_NAME", length = 2000)
public java.lang.String getProducingCountryName(){
return producingCountryName;
}
public void setProducingCountryName(java.lang.String producingCountryName)
{
this.producingCountryName = producingCountryName;
}
@Column(name = "APPLY_PRICE", length = 22)
public java.lang.Double getApplyPrice(){
return applyPrice;
}
public void setApplyPrice(java.lang.Double applyPrice)
{
this.applyPrice = applyPrice;
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
@Column(name = "SHIPMENT_ID", length = 22)
public java.lang.Long getShipmentId(){
return shipmentId;
}
public void setShipmentId(java.lang.Long shipmentId)
{
this.shipmentId = shipmentId;
}
@Column(name = "SHIPMENT_GOODS_ID", length = 22)
public java.lang.Long getShipmentGoodsId(){
return shipmentGoodsId;
}
public void setShipmentGoodsId(java.lang.Long shipmentGoodsId)
{
this.shipmentGoodsId = shipmentGoodsId;
}
   

    @Override
    public ShipmentGoodsDetailDTO toDTO() {
        ShipmentGoodsDetailDTO shipmentGoodsDetailDTO = new ShipmentGoodsDetailDTO();
        //set cac gia tri 
        shipmentGoodsDetailDTO.setShipmentGoodsDetailId(this.shipmentGoodsDetailId);
        shipmentGoodsDetailDTO.setGoodsId(this.goodsId);
        shipmentGoodsDetailDTO.setGoodsCode(this.goodsCode);
        shipmentGoodsDetailDTO.setGoodsName(this.goodsName);
        shipmentGoodsDetailDTO.setAmount(this.amount);
        shipmentGoodsDetailDTO.setSerial(this.serial);
        shipmentGoodsDetailDTO.setUnitTypeId(this.unitTypeId);
        shipmentGoodsDetailDTO.setUnitTypeName(this.unitTypeName);
        shipmentGoodsDetailDTO.setManufacturerId(this.manufacturerId);
        shipmentGoodsDetailDTO.setProducingCountryId(this.producingCountryId);
        shipmentGoodsDetailDTO.setManufacturerName(this.manufacturerName);
        shipmentGoodsDetailDTO.setProducingCountryName(this.producingCountryName);
        shipmentGoodsDetailDTO.setApplyPrice(this.applyPrice);
        shipmentGoodsDetailDTO.setPartNumber(this.partNumber);
        shipmentGoodsDetailDTO.setPrice(this.price);
        shipmentGoodsDetailDTO.setShipmentId(this.shipmentId);
        shipmentGoodsDetailDTO.setShipmentGoodsId(this.shipmentGoodsId);
        return shipmentGoodsDetailDTO;
    }
}
