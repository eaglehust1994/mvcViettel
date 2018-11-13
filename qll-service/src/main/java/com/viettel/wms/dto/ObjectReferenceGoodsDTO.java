/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ObjectReferenceGoodsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "OBJECT_REFERENCE_GOODSBO")
public class ObjectReferenceGoodsDTO extends BaseFWDTOImpl<ObjectReferenceGoodsBO> {

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
private java.lang.Long id;
private java.lang.String serial;

    @Override
    public ObjectReferenceGoodsBO toModel() {
        ObjectReferenceGoodsBO objectReferenceGoodsBO = new ObjectReferenceGoodsBO();
        objectReferenceGoodsBO.setObjectReferenceGoodsId(this.objectReferenceGoodsId);
        objectReferenceGoodsBO.setOrderId(this.orderId);
        objectReferenceGoodsBO.setGoodsType(this.goodsType);
        objectReferenceGoodsBO.setGoodsTypeName(this.goodsTypeName);
        objectReferenceGoodsBO.setGoodsId(this.goodsId);
        objectReferenceGoodsBO.setGoodsCode(this.goodsCode);
        objectReferenceGoodsBO.setGoodsName(this.goodsName);
        objectReferenceGoodsBO.setGoodsIsSerial(this.goodsIsSerial);
        objectReferenceGoodsBO.setGoodsState(this.goodsState);
        objectReferenceGoodsBO.setGoodsStateName(this.goodsStateName);
        objectReferenceGoodsBO.setGoodsUnitName(this.goodsUnitName);
        objectReferenceGoodsBO.setGoodsUnitId(this.goodsUnitId);
        objectReferenceGoodsBO.setAmount(this.amount);
        objectReferenceGoodsBO.setTotalPrice(this.totalPrice);
        objectReferenceGoodsBO.setObjectReferenceId(this.objectReferenceId);
        return objectReferenceGoodsBO;
    }

    @Override
     public Long getFWModelId() {
        return objectReferenceGoodsId;
    }
   
    @Override
    public String catchName() {
        return getObjectReferenceGoodsId().toString();
    }
    public java.lang.Long getObjectReferenceGoodsId(){
    return objectReferenceGoodsId;
    }
    public void setObjectReferenceGoodsId(java.lang.Long objectReferenceGoodsId)
    {
    this.objectReferenceGoodsId = objectReferenceGoodsId;
    }
    
    public java.lang.Long getOrderId(){
    return orderId;
    }
    public void setOrderId(java.lang.Long orderId)
    {
    this.orderId = orderId;
    }
    
    public java.lang.Long getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.Long goodsType)
    {
    this.goodsType = goodsType;
    }
    
    public java.lang.String getGoodsTypeName(){
    return goodsTypeName;
    }
    public void setGoodsTypeName(java.lang.String goodsTypeName)
    {
    this.goodsTypeName = goodsTypeName;
    }
    
    public java.lang.Long getGoodsId(){
    return goodsId;
    }
    public void setGoodsId(java.lang.Long goodsId)
    {
    this.goodsId = goodsId;
    }
    
    public java.lang.String getGoodsCode(){
    return goodsCode;
    }
    public void setGoodsCode(java.lang.String goodsCode)
    {
    this.goodsCode = goodsCode;
    }
    
    public java.lang.String getGoodsName(){
    return goodsName;
    }
    public void setGoodsName(java.lang.String goodsName)
    {
    this.goodsName = goodsName;
    }
    
    public java.lang.String getGoodsIsSerial(){
    return goodsIsSerial;
    }
    public void setGoodsIsSerial(java.lang.String goodsIsSerial)
    {
    this.goodsIsSerial = goodsIsSerial;
    }
    
    public java.lang.String getGoodsState(){
    return goodsState;
    }
    public void setGoodsState(java.lang.String goodsState)
    {
    this.goodsState = goodsState;
    }
    
    public java.lang.String getGoodsStateName(){
    return goodsStateName;
    }
    public void setGoodsStateName(java.lang.String goodsStateName)
    {
    this.goodsStateName = goodsStateName;
    }
    
    public java.lang.String getGoodsUnitName(){
    return goodsUnitName;
    }
    public void setGoodsUnitName(java.lang.String goodsUnitName)
    {
    this.goodsUnitName = goodsUnitName;
    }
    
    public java.lang.Long getGoodsUnitId(){
    return goodsUnitId;
    }
    public void setGoodsUnitId(java.lang.Long goodsUnitId)
    {
    this.goodsUnitId = goodsUnitId;
    }
    
    public java.lang.Double getAmount(){
    return amount;
    }
    public void setAmount(java.lang.Double amount)
    {
    this.amount = amount;
    }
    
    public java.lang.Double getTotalPrice(){
    return totalPrice;
    }
    public void setTotalPrice(java.lang.Double totalPrice)
    {
    this.totalPrice = totalPrice;
    }
    
    public java.lang.Long getObjectReferenceId(){
    return objectReferenceId;
    }
    public void setObjectReferenceId(java.lang.Long objectReferenceId)
    {
    this.objectReferenceId = objectReferenceId;
    }

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}
    
   
}
