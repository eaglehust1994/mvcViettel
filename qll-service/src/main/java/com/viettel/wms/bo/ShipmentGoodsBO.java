/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ShipmentGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.SHIPMENT_GOODS")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ShipmentGoodsBO extends BaseFWModelImpl {
     
private java.lang.Long shipmentGoodsId;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.Double amount;
private java.lang.Long unitTypeId;
private java.lang.String unitTypeName;
private java.lang.Double originPrice;
private java.lang.Double totalOriginPrice;
private java.lang.Double estimatePrice;
private java.lang.Double estimatePriceSum;
private java.lang.Double estimatePricePercent;
private java.lang.Double applyPrice;
private java.lang.Double applyTotalMoney;
private java.lang.Long shipmentId;

 public ShipmentGoodsBO() {
        setColId("shipmentId");
        setColName("shipmentId");
        setUniqueColumn(new String[]{"shipmentId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SHIPMENT_GOODS_SEQ")
            }
    )
@Column(name = "SHIPMENT_GOODS_ID", length = 22)
public java.lang.Long getShipmentGoodsId(){
return shipmentGoodsId;
}
public void setShipmentGoodsId(java.lang.Long shipmentGoodsId)
{
this.shipmentGoodsId = shipmentGoodsId;
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
@Column(name = "ORIGIN_PRICE", length = 22)
public java.lang.Double getOriginPrice(){
return originPrice;
}
public void setOriginPrice(java.lang.Double originPrice)
{
this.originPrice = originPrice;
}
@Column(name = "TOTAL_ORIGIN_PRICE", length = 22)
public java.lang.Double getTotalOriginPrice(){
return totalOriginPrice;
}
public void setTotalOriginPrice(java.lang.Double totalOriginPrice)
{
this.totalOriginPrice = totalOriginPrice;
}
@Column(name = "ESTIMATE_PRICE", length = 22)
public java.lang.Double getEstimatePrice(){
return estimatePrice;
}
public void setEstimatePrice(java.lang.Double estimatePrice)
{
this.estimatePrice = estimatePrice;
}
@Column(name = "ESTIMATE_PRICE_SUM", length = 22)
public java.lang.Double getEstimatePriceSum(){
return estimatePriceSum;
}
public void setEstimatePriceSum(java.lang.Double estimatePriceSum)
{
this.estimatePriceSum = estimatePriceSum;
}
@Column(name = "ESTIMATE_PRICE_PERCENT", length = 22)
public java.lang.Double getEstimatePricePercent(){
return estimatePricePercent;
}
public void setEstimatePricePercent(java.lang.Double estimatePricePercent)
{
this.estimatePricePercent = estimatePricePercent;
}
@Column(name = "APPLY_PRICE", length = 22)
public java.lang.Double getApplyPrice(){
return applyPrice;
}
public void setApplyPrice(java.lang.Double applyPrice)
{
this.applyPrice = applyPrice;
}
@Column(name = "APPLY_TOTAL_MONEY", length = 22)
public java.lang.Double getApplyTotalMoney(){
return applyTotalMoney;
}
public void setApplyTotalMoney(java.lang.Double applyTotalMoney)
{
this.applyTotalMoney = applyTotalMoney;
}
/*@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SHIPMENT_GOODS_SEQ")
            }
    )*/
@Column(name = "SHIPMENT_ID", length = 22)
public java.lang.Long getShipmentId(){
return shipmentId;
}
public void setShipmentId(java.lang.Long shipmentId)
{
this.shipmentId = shipmentId;
}
   

    @Override
    public ShipmentGoodsDTO toDTO() {
        ShipmentGoodsDTO shipmentGoodsDTO = new ShipmentGoodsDTO();
        //set cac gia tri 
        shipmentGoodsDTO.setShipmentGoodsId(this.shipmentGoodsId);
        shipmentGoodsDTO.setGoodsId(this.goodsId);
        shipmentGoodsDTO.setGoodsCode(this.goodsCode);
        shipmentGoodsDTO.setGoodsName(this.goodsName);
        shipmentGoodsDTO.setAmount(this.amount);
        shipmentGoodsDTO.setUnitTypeId(this.unitTypeId);
        shipmentGoodsDTO.setUnitTypeName(this.unitTypeName);
        shipmentGoodsDTO.setOriginPrice(this.originPrice);
        shipmentGoodsDTO.setTotalOriginPrice(this.totalOriginPrice);
        shipmentGoodsDTO.setEstimatePrice(this.estimatePrice);
        shipmentGoodsDTO.setEstimatePriceSum(this.estimatePriceSum);
        shipmentGoodsDTO.setEstimatePricePercent(this.estimatePricePercent);
        shipmentGoodsDTO.setApplyPrice(this.applyPrice);
        shipmentGoodsDTO.setApplyTotalMoney(this.applyTotalMoney);
        shipmentGoodsDTO.setShipmentId(this.shipmentId);
        return shipmentGoodsDTO;
    }
}
