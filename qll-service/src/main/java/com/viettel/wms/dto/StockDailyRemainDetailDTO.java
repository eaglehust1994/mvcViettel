/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.StockDailyRemainDetailBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_DAILY_REMAIN_DETAILBO")
public class StockDailyRemainDetailDTO extends BaseFWDTOImpl<StockDailyRemainDetailBO> {

private java.lang.String partNumber;
private java.lang.String manufacturer;
private java.lang.String producerContry;
private java.lang.Double price;
private java.lang.Long stockDailyRemainId;
private java.lang.Long stockDailyRemainDetailId;
private java.util.Date remainDate;
private java.lang.String serial;
private java.lang.String contractCode;

    @Override
    public StockDailyRemainDetailBO toModel() {
        StockDailyRemainDetailBO stockDailyRemainDetailBO = new StockDailyRemainDetailBO();
        stockDailyRemainDetailBO.setPartNumber(this.partNumber);
        stockDailyRemainDetailBO.setManufacturer(this.manufacturer);
        stockDailyRemainDetailBO.setProducerContry(this.producerContry);
        stockDailyRemainDetailBO.setPrice(this.price);
        stockDailyRemainDetailBO.setStockDailyRemainId(this.stockDailyRemainId);
        stockDailyRemainDetailBO.setStockDailyRemainDetailId(this.stockDailyRemainDetailId);
        stockDailyRemainDetailBO.setRemainDate(this.remainDate);
        stockDailyRemainDetailBO.setSerial(this.serial);
        stockDailyRemainDetailBO.setContractCode(this.contractCode);
        return stockDailyRemainDetailBO;
    }

    public java.lang.String getPartNumber(){
    return partNumber;
    }
    public void setPartNumber(java.lang.String partNumber)
    {
    this.partNumber = partNumber;
    }
    
    public java.lang.String getManufacturer(){
    return manufacturer;
    }
    public void setManufacturer(java.lang.String manufacturer)
    {
    this.manufacturer = manufacturer;
    }
    
    public java.lang.String getProducerContry(){
    return producerContry;
    }
    public void setProducerContry(java.lang.String producerContry)
    {
    this.producerContry = producerContry;
    }
    
    public java.lang.Double getPrice(){
    return price;
    }
    public void setPrice(java.lang.Double price)
    {
    this.price = price;
    }
    
    public java.lang.Long getStockDailyRemainId(){
    return stockDailyRemainId;
    }
    public void setStockDailyRemainId(java.lang.Long stockDailyRemainId)
    {
    this.stockDailyRemainId = stockDailyRemainId;
    }
    
    @Override
     public Long getFWModelId() {
        return stockDailyRemainDetailId;
    }
   
    @Override
    public String catchName() {
        return getStockDailyRemainDetailId().toString();
    }
    public java.lang.Long getStockDailyRemainDetailId(){
    return stockDailyRemainDetailId;
    }
    public void setStockDailyRemainDetailId(java.lang.Long stockDailyRemainDetailId)
    {
    this.stockDailyRemainDetailId = stockDailyRemainDetailId;
    }
    
    public java.util.Date getRemainDate(){
    return remainDate;
    }
    public void setRemainDate(java.util.Date remainDate)
    {
    this.remainDate = remainDate;
    }
    
    public java.lang.String getSerial(){
    return serial;
    }
    public void setSerial(java.lang.String serial)
    {
    this.serial = serial;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
   
}
