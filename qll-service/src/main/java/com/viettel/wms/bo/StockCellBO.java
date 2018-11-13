/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.StockCellDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.STOCK_CELL")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class StockCellBO extends BaseFWModelImpl {
     
private java.util.Date createdDate;
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.Long stockCellId;
private java.lang.String code;
private java.lang.String description;
private java.lang.Long stockId;
private java.lang.Long createdBy;

 public StockCellBO() {
        setColId("stockCellId");
        setColName("stockCellId");
        setUniqueColumn(new String[]{"stockCellId"});
}

@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
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
                @Parameter(name = "sequence", value = "CAT_OWNER.STOCK_CELL_SEQ")
            }
    )
@Column(name = "STOCK_CELL_ID", length = 22)
public java.lang.Long getStockCellId(){
return stockCellId;
}
public void setStockCellId(java.lang.Long stockCellId)
{
this.stockCellId = stockCellId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "DESCRIPTION", length = 4000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
   

    @Override
    public StockCellDTO toDTO() {
        StockCellDTO stockCellDTO = new StockCellDTO();
        //set cac gia tri 
        stockCellDTO.setCreatedDate(this.createdDate);
        stockCellDTO.setUpdatedBy(this.updatedBy);
        stockCellDTO.setUpdatedDate(this.updatedDate);
        stockCellDTO.setStockCellId(this.stockCellId);
        stockCellDTO.setCode(this.code);
        stockCellDTO.setDescription(this.description);
        stockCellDTO.setStockId(this.stockId);
        stockCellDTO.setCreatedBy(this.createdBy);
        return stockCellDTO;
    }
}
