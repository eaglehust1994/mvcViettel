/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatStatusDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_STATUS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatStatusBO extends BaseFWModelImpl {
     
private java.lang.Long catStatusId;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long isActive;
private java.lang.Long type;
private java.lang.Long orderNum;
private java.lang.String code;

 public CatStatusBO() {
        setColId("catStatusId");
        setColName("catStatusId");
        setUniqueColumn(new String[]{"catStatusId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_STATUS_SEQ")
            }
    )
@Column(name = "CAT_STATUS_ID", length = 22)
public java.lang.Long getCatStatusId(){
return catStatusId;
}
public void setCatStatusId(java.lang.Long catStatusId)
{
this.catStatusId = catStatusId;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "DESCRIPTION", length = 1000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "ORDER_NUM", length = 22)
public java.lang.Long getOrderNum(){
return orderNum;
}
public void setOrderNum(java.lang.Long orderNum)
{
this.orderNum = orderNum;
}
@Column(name = "CODE", length = 20)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
   

    @Override
    public CatStatusDTO toDTO() {
        CatStatusDTO catStatusDTO = new CatStatusDTO();
        //set cac gia tri 
        catStatusDTO.setCatStatusId(this.catStatusId);
        catStatusDTO.setName(this.name);
        catStatusDTO.setDescription(this.description);
        catStatusDTO.setIsActive(this.isActive);
        catStatusDTO.setType(this.type);
        catStatusDTO.setOrderNum(this.orderNum);
        catStatusDTO.setCode(this.code);
        return catStatusDTO;
    }
}
