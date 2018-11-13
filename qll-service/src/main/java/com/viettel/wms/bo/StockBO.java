/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.STOCK")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockBO extends BaseFWModelImpl {
     
private java.util.Date updatedDate;
private java.lang.Long stockId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String status;
private java.lang.Long parentId;
private java.lang.String path;
private java.lang.String description;
private java.lang.Long departmentId;
private java.lang.String departmentName;
private java.lang.Long updatedBy;
private java.lang.String type;
private java.lang.String level;
private java.lang.Long createdBy;
private java.util.Date createdDate;

 public StockBO() {
        setColId("stockId");
        setColName("stockId");
        setUniqueColumn(new String[]{"stockId"});
}

@Column(name = "UPDATED_DATE", length = 7)
public java.util.Date getUpdatedDate(){
return updatedDate;
}
public void setUpdatedDate(java.util.Date updatedDate)
{
this.updatedDate = updatedDate;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.STOCK_SEQ")
            }
    )
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "STATUS", length = 2)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "PATH", length = 2000)
public java.lang.String getPath(){
return path;
}
public void setPath(java.lang.String path)
{
this.path = path;
}
@Column(name = "DESCRIPTION", length = 4000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "DEPARTMENT_ID", length = 22)
public java.lang.Long getDepartmentId(){
return departmentId;
}
public void setDepartmentId(java.lang.Long departmentId)
{
this.departmentId = departmentId;
}
@Column(name = "DEPARTMENT_NAME", length = 400)
public java.lang.String getDepartmentName(){
return departmentName;
}
public void setDepartmentName(java.lang.String departmentName)
{
this.departmentName = departmentName;
}
@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
}
@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "LEVEL_STOCK", length = 2)
public java.lang.String getLevel(){
return level;
}
public void setLevel(java.lang.String level)
{
this.level = level;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
   

    @Override
    public StockDTO toDTO() {
        StockDTO stockDTO = new StockDTO();
        //set cac gia tri 
        stockDTO.setUpdatedDate(this.updatedDate);
        stockDTO.setStockId(this.stockId);
        stockDTO.setCode(this.code);
        stockDTO.setName(this.name);
        stockDTO.setStatus(this.status);
        stockDTO.setParentId(this.parentId);
        stockDTO.setPath(this.path);
        stockDTO.setDescription(this.description);
        stockDTO.setDepartmentId(this.departmentId);
        stockDTO.setDepartmentName(this.departmentName);
        stockDTO.setUpdatedBy(this.updatedBy);
        stockDTO.setType(this.type);
        stockDTO.setLevelST(this.level);
        stockDTO.setCreatedBy(this.createdBy);
        stockDTO.setCreatedDate(this.createdDate);
        return stockDTO;
    }
}
