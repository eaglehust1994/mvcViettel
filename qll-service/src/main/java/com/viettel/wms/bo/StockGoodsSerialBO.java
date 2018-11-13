/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_GOODS_SERIAL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockGoodsSerialBO extends BaseFWModelImpl {
     
private java.lang.Long stockGoodsId;
private java.lang.Long stockId;
private java.lang.Long goodsId;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Long goodsType;
private java.lang.String goodsIsSerial;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amount;
private java.util.Date changeDate;
private java.util.Date importDate;
private java.lang.Long orderId;
private java.lang.Long importStockTransId;
private java.lang.String status;
private java.lang.Double price;
private java.lang.String contractCode;
private java.lang.String shipmentCode;
private java.lang.String projectCode;
private java.lang.String serial;
private java.lang.String partNumber;
private java.lang.Long manufacturerId;
private java.lang.Long producingCountryId;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;

 public StockGoodsSerialBO() {
        setColId("orderId");
        setColName("orderId");
        setUniqueColumn(new String[]{"orderId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_GOODS_SERIAL_SEQ")
            }
    )
@Column(name = "STOCK_GOODS_ID", length = 22)
public java.lang.Long getStockGoodsId(){
return stockGoodsId;
}
public void setStockGoodsId(java.lang.Long stockGoodsId)
{
this.stockGoodsId = stockGoodsId;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "GOODS_ID", length = 22)
public java.lang.Long getGoodsId(){
return goodsId;
}
public void setGoodsId(java.lang.Long goodsId)
{
this.goodsId = goodsId;
}
@Column(name = "GOODS_STATE", length = 2)
public java.lang.String getGoodsState(){
return goodsState;
}
public void setGoodsState(java.lang.String goodsState)
{
this.goodsState = goodsState;
}
@Column(name = "GOODS_STATE_NAME", length = 200)
public java.lang.String getGoodsStateName(){
return goodsStateName;
}
public void setGoodsStateName(java.lang.String goodsStateName)
{
this.goodsStateName = goodsStateName;
}
@Column(name = "GOODS_CODE", length = 40)
public java.lang.String getGoodsCode(){
return goodsCode;
}
public void setGoodsCode(java.lang.String goodsCode)
{
this.goodsCode = goodsCode;
}
@Column(name = "GOODS_NAME", length = 1000)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "GOODS_TYPE", length = 22)
public java.lang.Long getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.Long goodsType)
{
this.goodsType = goodsType;
}
@Column(name = "GOODS_IS_SERIAL", length = 1)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
}
@Column(name = "GOODS_UNIT_NAME", length = 100)
public java.lang.String getGoodsUnitName(){
return goodsUnitName;
}
public void setGoodsUnitName(java.lang.String goodsUnitName)
{
this.goodsUnitName = goodsUnitName;
}
@Column(name = "GOODS_UNIT_ID", length = 22)
public java.lang.Long getGoodsUnitId(){
return goodsUnitId;
}
public void setGoodsUnitId(java.lang.Long goodsUnitId)
{
this.goodsUnitId = goodsUnitId;
}
@Column(name = "AMOUNT", length = 22)
public java.lang.Double getAmount(){
return amount;
}
public void setAmount(java.lang.Double amount)
{
this.amount = amount;
}
@Column(name = "CHANGE_DATE", length = 7)
public java.util.Date getChangeDate(){
return changeDate;
}
public void setChangeDate(java.util.Date changeDate)
{
this.changeDate = changeDate;
}
@Column(name = "IMPORT_DATE", length = 7)
public java.util.Date getImportDate(){
return importDate;
}
public void setImportDate(java.util.Date importDate)
{
this.importDate = importDate;
}
@Column(name = "ORDER_ID", length = 22)
public java.lang.Long getOrderId(){
return orderId;
}
public void setOrderId(java.lang.Long orderId)
{
this.orderId = orderId;
}
@Column(name = "IMPORT_STOCK_TRANS_ID", length = 22)
public java.lang.Long getImportStockTransId(){
return importStockTransId;
}
public void setImportStockTransId(java.lang.Long importStockTransId)
{
this.importStockTransId = importStockTransId;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "PRICE", length = 22)
public java.lang.Double getPrice(){
return price;
}
public void setPrice(java.lang.Double price)
{
this.price = price;
}
@Column(name = "CONTRACT_CODE", length = 100)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "SHIPMENT_CODE", length = 100)
public java.lang.String getShipmentCode(){
return shipmentCode;
}
public void setShipmentCode(java.lang.String shipmentCode)
{
this.shipmentCode = shipmentCode;
}
@Column(name = "PROJECT_CODE", length = 100)
public java.lang.String getProjectCode(){
return projectCode;
}
public void setProjectCode(java.lang.String projectCode)
{
this.projectCode = projectCode;
}
@Column(name = "SERIAL", length = 100)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
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
   

    @Override
    public StockGoodsSerialDTO toDTO() {
        StockGoodsSerialDTO stockGoodsSerialDTO = new StockGoodsSerialDTO();
        //set cac gia tri 
        stockGoodsSerialDTO.setStockGoodsId(this.stockGoodsId);
        stockGoodsSerialDTO.setStockId(this.stockId);
        stockGoodsSerialDTO.setGoodsId(this.goodsId);
        stockGoodsSerialDTO.setGoodsState(this.goodsState);
        stockGoodsSerialDTO.setGoodsStateName(this.goodsStateName);
        stockGoodsSerialDTO.setGoodsCode(this.goodsCode);
        stockGoodsSerialDTO.setGoodsName(this.goodsName);
        stockGoodsSerialDTO.setGoodsType(this.goodsType);
        stockGoodsSerialDTO.setGoodsIsSerial(this.goodsIsSerial);
        stockGoodsSerialDTO.setGoodsUnitName(this.goodsUnitName);
        stockGoodsSerialDTO.setGoodsUnitId(this.goodsUnitId);
        stockGoodsSerialDTO.setAmount(this.amount);
        stockGoodsSerialDTO.setChangeDate(this.changeDate);
        stockGoodsSerialDTO.setImportDate(this.importDate);
        stockGoodsSerialDTO.setOrderId(this.orderId);
        stockGoodsSerialDTO.setImportStockTransId(this.importStockTransId);
        stockGoodsSerialDTO.setStatus(this.status);
        stockGoodsSerialDTO.setPrice(this.price);
        stockGoodsSerialDTO.setContractCode(this.contractCode);
        stockGoodsSerialDTO.setShipmentCode(this.shipmentCode);
        stockGoodsSerialDTO.setProjectCode(this.projectCode);
        stockGoodsSerialDTO.setSerial(this.serial);
        stockGoodsSerialDTO.setPartNumber(this.partNumber);
        stockGoodsSerialDTO.setManufacturerId(this.manufacturerId);
        stockGoodsSerialDTO.setProducingCountryId(this.producingCountryId);
        stockGoodsSerialDTO.setManufacturerName(this.manufacturerName);
        stockGoodsSerialDTO.setProducingCountryName(this.producingCountryName);
        return stockGoodsSerialDTO;
    }
}
