/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockDailyRemainDetailDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.STOCK_DAILY_REMAIN_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockDailyRemainDetailBO extends BaseFWModelImpl {
     
private java.lang.String partNumber;
private java.lang.String manufacturer;
private java.lang.String producerContry;
private java.lang.Double price;
private java.lang.Long stockDailyRemainId;
private java.lang.Long stockDailyRemainDetailId;
private java.util.Date remainDate;
private java.lang.String serial;
private java.lang.String contractCode;

 public StockDailyRemainDetailBO() {
        setColId("stockDailyRemainDetailId");
        setColName("stockDailyRemainDetailId");
        setUniqueColumn(new String[]{"stockDailyRemainDetailId"});
}

@Column(name = "PART_NUMBER", length = 100)
public java.lang.String getPartNumber(){
return partNumber;
}
public void setPartNumber(java.lang.String partNumber)
{
this.partNumber = partNumber;
}
@Column(name = "MANUFACTURER", length = 200)
public java.lang.String getManufacturer(){
return manufacturer;
}
public void setManufacturer(java.lang.String manufacturer)
{
this.manufacturer = manufacturer;
}
@Column(name = "PRODUCER_CONTRY", length = 200)
public java.lang.String getProducerContry(){
return producerContry;
}
public void setProducerContry(java.lang.String producerContry)
{
this.producerContry = producerContry;
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
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_DAILY_REMAIN_DETAIL_SEQ")
            }
    )
@Column(name = "STOCK_DAILY_REMAIN_ID", length = 22)
public java.lang.Long getStockDailyRemainId(){
return stockDailyRemainId;
}
public void setStockDailyRemainId(java.lang.Long stockDailyRemainId)
{
this.stockDailyRemainId = stockDailyRemainId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.STOCK_DAILY_REMAIN_DETAIL_SEQ")
            }
    )
@Column(name = "STOCK_DAILY_REMAIN_DETAIL_ID", length = 22)
public java.lang.Long getStockDailyRemainDetailId(){
return stockDailyRemainDetailId;
}
public void setStockDailyRemainDetailId(java.lang.Long stockDailyRemainDetailId)
{
this.stockDailyRemainDetailId = stockDailyRemainDetailId;
}
@Column(name = "REMAIN_DATE", length = 7)
public java.util.Date getRemainDate(){
return remainDate;
}
public void setRemainDate(java.util.Date remainDate)
{
this.remainDate = remainDate;
}
@Column(name = "SERIAL", length = 200)
public java.lang.String getSerial(){
return serial;
}
public void setSerial(java.lang.String serial)
{
this.serial = serial;
}
@Column(name = "CONTRACT_CODE", length = 100)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
   

    @Override
    public StockDailyRemainDetailDTO toDTO() {
        StockDailyRemainDetailDTO stockDailyRemainDetailDTO = new StockDailyRemainDetailDTO();
        //set cac gia tri 
        stockDailyRemainDetailDTO.setPartNumber(this.partNumber);
        stockDailyRemainDetailDTO.setManufacturer(this.manufacturer);
        stockDailyRemainDetailDTO.setProducerContry(this.producerContry);
        stockDailyRemainDetailDTO.setPrice(this.price);
        stockDailyRemainDetailDTO.setStockDailyRemainId(this.stockDailyRemainId);
        stockDailyRemainDetailDTO.setStockDailyRemainDetailId(this.stockDailyRemainDetailId);
        stockDailyRemainDetailDTO.setRemainDate(this.remainDate);
        stockDailyRemainDetailDTO.setSerial(this.serial);
        stockDailyRemainDetailDTO.setContractCode(this.contractCode);
        return stockDailyRemainDetailDTO;
    }
}
