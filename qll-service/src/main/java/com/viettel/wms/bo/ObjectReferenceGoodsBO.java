/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "OBJECT_REFERENCE_GOODS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ObjectReferenceGoodsBO extends BaseFWModelImpl {
     
private java.lang.Long objectReferenceGoodsId;
private java.lang.Long orderId;
private java.lang.Long goodsType;
private java.lang.String goodsTypeName;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsIsSerial;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amount;
private java.lang.Double totalPrice;
private java.lang.Long objectReferenceId;

 public ObjectReferenceGoodsBO() {
        setColId("objectReferenceGoodsId");
        setColName("objectReferenceGoodsId");
        setUniqueColumn(new String[]{"objectReferenceGoodsId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "OBJECT_REFERENCE_GOODS_SEQ")
            }
    )
@Column(name = "OBJECT_REFERENCE_GOODS_ID", length = 22)
public java.lang.Long getObjectReferenceGoodsId(){
return objectReferenceGoodsId;
}
public void setObjectReferenceGoodsId(java.lang.Long objectReferenceGoodsId)
{
this.objectReferenceGoodsId = objectReferenceGoodsId;
}
@Column(name = "ORDER_ID", length = 22)
public java.lang.Long getOrderId(){
return orderId;
}
public void setOrderId(java.lang.Long orderId)
{
this.orderId = orderId;
}
@Column(name = "GOODS_TYPE", length = 22)
public java.lang.Long getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.Long goodsType)
{
this.goodsType = goodsType;
}
@Column(name = "GOODS_TYPE_NAME", length = 200)
public java.lang.String getGoodsTypeName(){
return goodsTypeName;
}
public void setGoodsTypeName(java.lang.String goodsTypeName)
{
this.goodsTypeName = goodsTypeName;
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
@Column(name = "GOODS_IS_SERIAL", length = 1)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
}
@Column(name = "GOODS_STATE", length = 1)
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
@Column(name = "GOODS_UNIT_NAME", length = 200)
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
@Column(name = "TOTAL_PRICE", length = 22)
public java.lang.Double getTotalPrice(){
return totalPrice;
}
public void setTotalPrice(java.lang.Double totalPrice)
{
this.totalPrice = totalPrice;
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
    public ObjectReferenceGoodsDTO toDTO() {
        ObjectReferenceGoodsDTO objectReferenceGoodsDTO = new ObjectReferenceGoodsDTO();
        //set cac gia tri 
        objectReferenceGoodsDTO.setObjectReferenceGoodsId(this.objectReferenceGoodsId);
        objectReferenceGoodsDTO.setOrderId(this.orderId);
        objectReferenceGoodsDTO.setGoodsType(this.goodsType);
        objectReferenceGoodsDTO.setGoodsTypeName(this.goodsTypeName);
        objectReferenceGoodsDTO.setGoodsId(this.goodsId);
        objectReferenceGoodsDTO.setGoodsCode(this.goodsCode);
        objectReferenceGoodsDTO.setGoodsName(this.goodsName);
        objectReferenceGoodsDTO.setGoodsIsSerial(this.goodsIsSerial);
        objectReferenceGoodsDTO.setGoodsState(this.goodsState);
        objectReferenceGoodsDTO.setGoodsStateName(this.goodsStateName);
        objectReferenceGoodsDTO.setGoodsUnitName(this.goodsUnitName);
        objectReferenceGoodsDTO.setGoodsUnitId(this.goodsUnitId);
        objectReferenceGoodsDTO.setAmount(this.amount);
        objectReferenceGoodsDTO.setTotalPrice(this.totalPrice);
        objectReferenceGoodsDTO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceGoodsDTO;
    }
}
