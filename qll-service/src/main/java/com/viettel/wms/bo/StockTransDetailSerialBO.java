/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockTransDetailSerialBO extends BaseFWModelImpl {
     
private java.lang.Long stockTransDetailSerialId;
private java.lang.Long goodsId;
private java.lang.String serial;
private java.lang.String contractCode;
private java.lang.String partNumber;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double price;
private java.lang.String cellCode;
private java.lang.Long stockTransId;
private java.lang.Long stockTransDetailId;

 public StockTransDetailSerialBO() {
        setColId("stockTransDetailId");
        setColName("stockTransDetailId");
        setUniqueColumn(new String[]{"stockTransDetailId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL_SEQ")
            }
    )
@Column(name = "STOCK_TRANS_DETAIL_SERIAL_ID", length = 22)
public java.lang.Long getStockTransDetailSerialId(){
return stockTransDetailSerialId;
}
public void setStockTransDetailSerialId(java.lang.Long stockTransDetailSerialId)
{
this.stockTransDetailSerialId = stockTransDetailSerialId;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "SERIAL", length = 200)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "CONTRACT_CODE", length = 200)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "PART_NUMBER", length = 200)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
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
@Column(name = "PRICE", length = 22)
public java.lang.Double getPrice(){
return price;
}
public void setPrice(java.lang.Double price)
{
this.price = price;
}
@Column(name = "CELL_CODE", length = 100)
public java.lang.String getCellCode(){
return cellCode;
}
public void setCellCode(java.lang.String cellCode)
{
this.cellCode = cellCode;
}

@Column(name = "STOCK_TRANS_ID", length = 22)
public java.lang.Long getStockTransId(){
return stockTransId;
}
public void setStockTransId(java.lang.Long stockTransId)
{
this.stockTransId = stockTransId;
}

@Column(name = "STOCK_TRANS_DETAIL_ID", length = 22)
public java.lang.Long getStockTransDetailId(){
return stockTransDetailId;
}
public void setStockTransDetailId(java.lang.Long stockTransDetailId)
{
this.stockTransDetailId = stockTransDetailId;
}
   

    @Override
    public StockTransDetailSerialDTO toDTO() {
        StockTransDetailSerialDTO stockTransDetailSerialDTO = new StockTransDetailSerialDTO();
        //set cac gia tri 
        stockTransDetailSerialDTO.setStockTransDetailSerialId(this.stockTransDetailSerialId);
        stockTransDetailSerialDTO.setGoodsId(this.goodsId);
        stockTransDetailSerialDTO.setSerial(this.serial);
        stockTransDetailSerialDTO.setContractCode(this.contractCode);
        stockTransDetailSerialDTO.setPartNumber(this.partNumber);
        stockTransDetailSerialDTO.setManufacturerId(this.manufacturerId);
        stockTransDetailSerialDTO.setProducingCountryId(this.producingCountryId);
        stockTransDetailSerialDTO.setManufacturerName(this.manufacturerName);
        stockTransDetailSerialDTO.setProducingCountryName(this.producingCountryName);
        stockTransDetailSerialDTO.setPrice(this.price);
        stockTransDetailSerialDTO.setCellCode(this.cellCode);
        stockTransDetailSerialDTO.setStockTransId(this.stockTransId);
        stockTransDetailSerialDTO.setStockTransDetailId(this.stockTransDetailId);
        return stockTransDetailSerialDTO;
    }
}
