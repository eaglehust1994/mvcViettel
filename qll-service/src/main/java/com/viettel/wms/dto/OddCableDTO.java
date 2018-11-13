/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.OddCableBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "ODD_CABLEBO")
public class OddCableDTO extends wmsBaseDTO<OddCableBO> {

private java.lang.Long oddCableId;
private java.lang.String goodsType;
private java.lang.String goodsTypeName;
private java.lang.Long goodsId;
private java.lang.String goodsCode;
private java.lang.String goodsName;
private java.lang.String goodsUnitName;
private java.lang.Long goodsUnitId;
private java.lang.Double amountMinimum;
private java.lang.String status;
private java.lang.Long fwmodelId;
private java.lang.String name;
private java.lang.String code;
private java.lang.String unitTypeName;
    @Override
    public OddCableBO toModel() {
        OddCableBO oddCableBO = new OddCableBO();
        oddCableBO.setOddCableId(this.oddCableId);
        oddCableBO.setGoodsType(this.goodsType);
        oddCableBO.setGoodsTypeName(this.goodsTypeName);
        oddCableBO.setGoodsId(this.goodsId);
        oddCableBO.setGoodsCode(this.goodsCode);
        oddCableBO.setGoodsName(this.goodsName);
        oddCableBO.setGoodsUnitName(this.goodsUnitName);
        oddCableBO.setGoodsUnitId(this.goodsUnitId);
        oddCableBO.setAmountMinimum(this.amountMinimum);
        oddCableBO.setStatus(this.status);
        return oddCableBO;
    }

    
    @Override
     public Long getFWModelId() {
        return oddCableId;
    }
   
    @Override
    public String catchName() {
        return getOddCableId().toString();
    }
    public java.lang.Long getOddCableId(){
    return oddCableId;
    }
    public void setOddCableId(java.lang.Long oddCableId)
    {
    this.oddCableId = oddCableId;
    }
    
    public java.lang.String getGoodsType(){
    return goodsType;
    }
    public void setGoodsType(java.lang.String goodsType)
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
    this.goodsCode = org.apache.commons.lang3.StringUtils.isNotEmpty(goodsCode) ? goodsCode.toUpperCase():null;
    }
    
    public java.lang.String getGoodsName(){
    return goodsName;
    }
    public void setGoodsName(java.lang.String goodsName)
    {
    this.goodsName = goodsName;
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
    
    public java.lang.Double getAmountMinimum(){
    return amountMinimum;
    }
    public void setAmountMinimum(java.lang.Double amountMinimum)
    {
    this.amountMinimum = amountMinimum;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
	}
	public java.lang.String getUnitTypeName() {
		return unitTypeName;
	}
	public void setUnitTypeName(java.lang.String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

   
}
