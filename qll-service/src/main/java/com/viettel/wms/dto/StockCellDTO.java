/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.StockCellBO;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCK_CELLBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockCellDTO extends wmsBaseDTO<StockCellBO> {

private java.util.Date createdDate;
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.Long stockCellId;
private java.lang.String code;
private java.lang.String description;
private java.lang.Long stockId;
private java.lang.Long createdBy;

    @Override
    public StockCellBO toModel() {
        StockCellBO stockCellBO = new StockCellBO();
        stockCellBO.setCreatedDate(this.createdDate);
        stockCellBO.setUpdatedBy(this.updatedBy);
        stockCellBO.setUpdatedDate(this.updatedDate);
        stockCellBO.setStockCellId(this.stockCellId);
        stockCellBO.setCode(this.code);
        stockCellBO.setDescription(this.description);
        stockCellBO.setStockId(this.stockId);
        stockCellBO.setCreatedBy(this.createdBy);
        return stockCellBO;
    }

    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }
    
    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.Long getStockCellId(){
    return stockCellId;
    }
    public void setStockCellId(java.lang.Long stockCellId)
    {
    this.stockCellId = stockCellId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    @Override
     public Long getFWModelId() {
        return stockId;
    }
   
    @Override
    public String catchName() {
        return getStockId().toString();
    }
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
   
}
