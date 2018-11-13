/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.service.base.model.BaseFWModelImpl;
import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OrderChangeGoodsDetailBO extends BaseFWModelImpl {
     
private java.lang.Long orderChangeGoodsDetailId;
private java.lang.String goodsType;
private java.lang.String goodsTypeName;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsIsSerial;
private java.lang.String goodsState;
private java.lang.String goodsStateName;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amountChange;
private java.lang.String newGoodsCode;
private java.lang.String newGoodsName;
private java.lang.Long newGoodsId;
private java.lang.String newSerial;
private java.lang.Long orderChangeGoodsId;
private java.lang.String serial;

 public OrderChangeGoodsDetailBO() {
        setColId("orderChangeGoodsId");
        setColName("orderChangeGoodsId");
        setUniqueColumn(new String[]{"orderChangeGoodsId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL_SEQ")
            }
    )
@Column(name = "ORDER_CHANGE_GOODS_DETAIL_ID", length = 22)
public java.lang.Long getOrderChangeGoodsDetailId(){
return orderChangeGoodsDetailId;
}
public void setOrderChangeGoodsDetailId(java.lang.Long orderChangeGoodsDetailId)
{
this.orderChangeGoodsDetailId = orderChangeGoodsDetailId;
}
@Column(name = "GOODS_TYPE", length = 100)
public java.lang.String getGoodsType(){
return goodsType;
}
public void setGoodsType(java.lang.String goodsType)
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
	if(goodsCode!=null){
		this.goodsCode = goodsCode;
	}else{
		this.goodsCode = null;
	}

}
@Column(name = "GOODS_NAME", length = 400)
public java.lang.String getGoodsName(){
return goodsName;
}
public void setGoodsName(java.lang.String goodsName)
{
this.goodsName = goodsName;
}
@Column(name = "GOODS_IS_SERIAL", length = 2)
public java.lang.String getGoodsIsSerial(){
return goodsIsSerial;
}
public void setGoodsIsSerial(java.lang.String goodsIsSerial)
{
this.goodsIsSerial = goodsIsSerial;
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

@Column(name = "NEW_GOODS_CODE", length = 100)
public java.lang.String getNewGoodsCode(){
	
return newGoodsCode;
}
public void setNewGoodsCode(java.lang.String newGoodsCode)
{
	if(newGoodsCode!=null){
		this.newGoodsCode = newGoodsCode;
	}else{
		this.newGoodsCode = null;
	}

}
@Column(name = "NEW_GOODS_NAME", length = 400)
public java.lang.String getNewGoodsName(){
return newGoodsName;
}
public void setNewGoodsName(java.lang.String newGoodsName)
{
this.newGoodsName = newGoodsName;
}
@Column(name = "NEW_GOODS_ID", length = 22)
public java.lang.Long getNewGoodsId(){
return newGoodsId;
}
public void setNewGoodsId(java.lang.Long newGoodsId)
{
this.newGoodsId = newGoodsId;
}
@Column(name = "NEW_SERIAL", length = 100)
public java.lang.String getNewSerial(){
return newSerial;
}
public void setNewSerial(java.lang.String newSerial)
{
	if(newSerial!=null){
		this.newSerial = newSerial;
	}else{
		this.newSerial = null;
	}

}



@Column(name = "AMOUNT_CHANGE", length = 22)
public java.lang.Double getAmountChange() {
	return amountChange;
}

public void setAmountChange(java.lang.Double amountChange) {
	this.amountChange = amountChange;
}
@Column(name = "SERIAL", length = 100)
public java.lang.String getSerial() {
	return serial;
}

public void setSerial(java.lang.String serial) {
	if(serial!=null){
		this.serial = serial;
	}else{
		this.serial = null;
	}
	
}

@Column(name = "ORDER_CHANGE_GOODS_ID", length = 22)
public java.lang.Long getOrderChangeGoodsId(){
return orderChangeGoodsId;
}
public void setOrderChangeGoodsId(java.lang.Long orderChangeGoodsId)
{
this.orderChangeGoodsId = orderChangeGoodsId;
}
   

    @Override
    public OrderChangeGoodsDetailDTO toDTO() {
        OrderChangeGoodsDetailDTO orderChangeGoodsDetailDTO = new OrderChangeGoodsDetailDTO();
        //set cac gia tri 
        orderChangeGoodsDetailDTO.setOrderChangeGoodsDetailId(this.orderChangeGoodsDetailId);
        orderChangeGoodsDetailDTO.setGoodsType(this.goodsType);
        orderChangeGoodsDetailDTO.setGoodsTypeName(this.goodsTypeName);
        orderChangeGoodsDetailDTO.setGoodsId(this.goodsId);
        orderChangeGoodsDetailDTO.setGoodsCode(this.goodsCode);
        orderChangeGoodsDetailDTO.setGoodsName(this.goodsName);
        orderChangeGoodsDetailDTO.setGoodsIsSerial(this.goodsIsSerial);
        orderChangeGoodsDetailDTO.setGoodsState(this.goodsState);
        orderChangeGoodsDetailDTO.setGoodsStateName(this.goodsStateName);
        orderChangeGoodsDetailDTO.setGoodsUnitName(this.goodsUnitName);
        orderChangeGoodsDetailDTO.setGoodsUnitId(this.goodsUnitId);
        orderChangeGoodsDetailDTO.setAmountChange(this.amountChange);
        orderChangeGoodsDetailDTO.setNewGoodsCode(this.newGoodsCode);
        orderChangeGoodsDetailDTO.setNewGoodsName(this.newGoodsName);
        orderChangeGoodsDetailDTO.setNewGoodsId(this.newGoodsId);
        orderChangeGoodsDetailDTO.setNewSerial(this.newSerial);
        orderChangeGoodsDetailDTO.setOrderChangeGoodsId(this.orderChangeGoodsId);
        orderChangeGoodsDetailDTO.setSerial(serial);
        return orderChangeGoodsDetailDTO;
    }
}
