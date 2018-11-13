/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.StockDeliveryConfigBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "STOCK_DELIVERY_CONFIGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDeliveryConfigDTO extends BaseFWDTOImpl<StockDeliveryConfigBO> {

private java.lang.Long deliveryId;
private java.lang.String deliveryOneName;
private java.lang.String deliveryOneAddress;
private java.lang.String deliveryOneRepresent;
private java.lang.String deliveryTwoName;
private java.lang.String deliveryTwoAddress;
private java.lang.String deliveryTwoRepresent;
private java.lang.String tranferName;
private java.lang.String tranferAddress;
private java.lang.String tranferRepresent;
private java.lang.Long stockId;

    @Override
    public StockDeliveryConfigBO toModel() {
        StockDeliveryConfigBO stockDeliveryConfigBO = new StockDeliveryConfigBO();
        stockDeliveryConfigBO.setDeliveryId(this.deliveryId);
        stockDeliveryConfigBO.setDeliveryOneName(this.deliveryOneName);
        stockDeliveryConfigBO.setDeliveryOneAddress(this.deliveryOneAddress);
        stockDeliveryConfigBO.setDeliveryOneRepresent(this.deliveryOneRepresent);
        stockDeliveryConfigBO.setDeliveryTwoName(this.deliveryTwoName);
        stockDeliveryConfigBO.setDeliveryTwoAddress(this.deliveryTwoAddress);
        stockDeliveryConfigBO.setDeliveryTwoRepresent(this.deliveryTwoRepresent);
        stockDeliveryConfigBO.setTranferName(this.tranferName);
        stockDeliveryConfigBO.setTranferAddress(this.tranferAddress);
        stockDeliveryConfigBO.setTranferRepresent(this.tranferRepresent);
        stockDeliveryConfigBO.setStockId(this.stockId);
        return stockDeliveryConfigBO;
    }

    @Override
     public Long getFWModelId() {
        return deliveryId;
    }
   
    @Override
    public String catchName() {
        return getDeliveryId().toString();
    }
    public java.lang.Long getDeliveryId(){
    return deliveryId;
    }
    public void setDeliveryId(java.lang.Long deliveryId)
    {
    this.deliveryId = deliveryId;
    }
    public java.lang.Long getStockId(){
        return stockId;
        }
        public void setStockId(java.lang.Long stockId)
        {
        this.stockId = stockId;
        }
        
    public java.lang.String getDeliveryOneName(){
    return deliveryOneName;
    }
    public void setDeliveryOneName(java.lang.String deliveryOneName)
    {
    this.deliveryOneName = deliveryOneName;
    }
    
    public java.lang.String getDeliveryOneAddress(){
    return deliveryOneAddress;
    }
    public void setDeliveryOneAddress(java.lang.String deliveryOneAddress)
    {
    this.deliveryOneAddress = deliveryOneAddress;
    }
    
    public java.lang.String getDeliveryOneRepresent(){
    return deliveryOneRepresent;
    }
    public void setDeliveryOneRepresent(java.lang.String deliveryOneRepresent)
    {
    this.deliveryOneRepresent = deliveryOneRepresent;
    }
    
    public java.lang.String getDeliveryTwoName(){
    return deliveryTwoName;
    }
    public void setDeliveryTwoName(java.lang.String deliveryTwoName)
    {
    this.deliveryTwoName = deliveryTwoName;
    }
    
    public java.lang.String getDeliveryTwoAddress(){
    return deliveryTwoAddress;
    }
    public void setDeliveryTwoAddress(java.lang.String deliveryTwoAddress)
    {
    this.deliveryTwoAddress = deliveryTwoAddress;
    }
    
    public java.lang.String getDeliveryTwoRepresent(){
    return deliveryTwoRepresent;
    }
    public void setDeliveryTwoRepresent(java.lang.String deliveryTwoRepresent)
    {
    this.deliveryTwoRepresent = deliveryTwoRepresent;
    }
    
    public java.lang.String getTranferName(){
    return tranferName;
    }
    public void setTranferName(java.lang.String tranferName)
    {
    this.tranferName = tranferName;
    }
    
    public java.lang.String getTranferAddress(){
    return tranferAddress;
    }
    public void setTranferAddress(java.lang.String tranferAddress)
    {
    this.tranferAddress = tranferAddress;
    }
    
    public java.lang.String getTranferRepresent(){
    return tranferRepresent;
    }
    public void setTranferRepresent(java.lang.String tranferRepresent)
    {
    this.tranferRepresent = tranferRepresent;
    }
    
   
}
