/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockDeliveryConfigDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.STOCK_DELIVERY_CONFIG")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class StockDeliveryConfigBO extends BaseFWModelImpl {
     
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

 public StockDeliveryConfigBO() {
        setColId("deliveryId");
        setColName("deliveryId");
        setUniqueColumn(new String[]{"deliveryId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.STOCK_DELIVERY_CONFIG_SEQ")
            }
    )
@Column(name = "DELIVERY_ID", length = 22)
public java.lang.Long getDeliveryId(){
return deliveryId;
}
public void setDeliveryId(java.lang.Long deliveryId)
{
this.deliveryId = deliveryId;
}
@Column(name = "DELIVERY_ONE_NAME", length = 800)
public java.lang.String getDeliveryOneName(){
return deliveryOneName;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
public void setDeliveryOneName(java.lang.String deliveryOneName)
{
this.deliveryOneName = deliveryOneName;
}
@Column(name = "DELIVERY_ONE_ADDRESS", length = 400)
public java.lang.String getDeliveryOneAddress(){
return deliveryOneAddress;
}
public void setDeliveryOneAddress(java.lang.String deliveryOneAddress)
{
this.deliveryOneAddress = deliveryOneAddress;
}
@Column(name = "DELIVERY_ONE_REPRESENT", length = 400)
public java.lang.String getDeliveryOneRepresent(){
return deliveryOneRepresent;
}
public void setDeliveryOneRepresent(java.lang.String deliveryOneRepresent)
{
this.deliveryOneRepresent = deliveryOneRepresent;
}
@Column(name = "DELIVERY_TWO_NAME", length = 400)
public java.lang.String getDeliveryTwoName(){
return deliveryTwoName;
}
public void setDeliveryTwoName(java.lang.String deliveryTwoName)
{
this.deliveryTwoName = deliveryTwoName;
}
@Column(name = "DELIVERY_TWO_ADDRESS", length = 400)
public java.lang.String getDeliveryTwoAddress(){
return deliveryTwoAddress;
}
public void setDeliveryTwoAddress(java.lang.String deliveryTwoAddress)
{
this.deliveryTwoAddress = deliveryTwoAddress;
}
@Column(name = "DELIVERY_TWO_REPRESENT", length = 400)
public java.lang.String getDeliveryTwoRepresent(){
return deliveryTwoRepresent;
}
public void setDeliveryTwoRepresent(java.lang.String deliveryTwoRepresent)
{
this.deliveryTwoRepresent = deliveryTwoRepresent;
}
@Column(name = "TRANFER_NAME", length = 400)
public java.lang.String getTranferName(){
return tranferName;
}
public void setTranferName(java.lang.String tranferName)
{
this.tranferName = tranferName;
}
@Column(name = "TRANFER_ADDRESS", length = 400)
public java.lang.String getTranferAddress(){
return tranferAddress;
}
public void setTranferAddress(java.lang.String tranferAddress)
{
this.tranferAddress = tranferAddress;
}
@Column(name = "TRANFER_REPRESENT", length = 400)
public java.lang.String getTranferRepresent(){
return tranferRepresent;
}
public void setTranferRepresent(java.lang.String tranferRepresent)
{
this.tranferRepresent = tranferRepresent;
}
   

    @Override
    public StockDeliveryConfigDTO toDTO() {
        StockDeliveryConfigDTO stockDeliveryConfigDTO = new StockDeliveryConfigDTO();
        //set cac gia tri 
        stockDeliveryConfigDTO.setDeliveryId(this.deliveryId);
        stockDeliveryConfigDTO.setDeliveryOneName(this.deliveryOneName);
        stockDeliveryConfigDTO.setDeliveryOneAddress(this.deliveryOneAddress);
        stockDeliveryConfigDTO.setDeliveryOneRepresent(this.deliveryOneRepresent);
        stockDeliveryConfigDTO.setDeliveryTwoName(this.deliveryTwoName);
        stockDeliveryConfigDTO.setDeliveryTwoAddress(this.deliveryTwoAddress);
        stockDeliveryConfigDTO.setDeliveryTwoRepresent(this.deliveryTwoRepresent);
        stockDeliveryConfigDTO.setTranferName(this.tranferName);
        stockDeliveryConfigDTO.setStockId(this.stockId);
        stockDeliveryConfigDTO.setTranferAddress(this.tranferAddress);
        stockDeliveryConfigDTO.setTranferRepresent(this.tranferRepresent);
        return stockDeliveryConfigDTO;
    }
}
