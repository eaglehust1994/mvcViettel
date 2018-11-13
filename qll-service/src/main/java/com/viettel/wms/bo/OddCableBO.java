/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OddCableDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ODD_CABLE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OddCableBO extends BaseFWModelImpl {
     
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

 public OddCableBO() {
        setColId("oddCableId");
        setColName("oddCableId");
        setUniqueColumn(new String[]{"oddCableId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ODD_CABLE_SEQ")
            }
    )
@Column(name = "ODD_CABLE_ID", length = 22)
public java.lang.Long getOddCableId(){
return oddCableId;
}
public void setOddCableId(java.lang.Long oddCableId)
{
this.oddCableId = oddCableId;
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
@Column(name = "AMOUNT_MINIMUM", length = 22)
public java.lang.Double getAmountMinimum(){
return amountMinimum;
}
public void setAmountMinimum(java.lang.Double amountMinimum)
{
this.amountMinimum = amountMinimum;
}
@Column(name = "STATUS", length = 1)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
   

    @Override
    public OddCableDTO toDTO() {
        OddCableDTO oddCableDTO = new OddCableDTO();
        //set cac gia tri 
        oddCableDTO.setOddCableId(this.oddCableId);
        oddCableDTO.setGoodsType(this.goodsType);
        oddCableDTO.setGoodsTypeName(this.goodsTypeName);
        oddCableDTO.setGoodsId(this.goodsId);
        oddCableDTO.setGoodsCode(this.goodsCode);
        oddCableDTO.setGoodsName(this.goodsName);
        oddCableDTO.setGoodsUnitName(this.goodsUnitName);
        oddCableDTO.setGoodsUnitId(this.goodsUnitId);
        oddCableDTO.setAmountMinimum(this.amountMinimum);
        oddCableDTO.setStatus(this.status);
        return oddCableDTO;
    }
}
