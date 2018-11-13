/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.ORDER_PATTERN")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OrderPatternBO extends BaseFWModelImpl {
     
private java.lang.Long orderPatternId;
private java.lang.String name;
private java.lang.String status;
private java.lang.String description;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.String createdUserName;



public OrderPatternBO() {
        setColId("orderPatternId");
        setColName("orderPatternId");
        setUniqueColumn(new String[]{"orderPatternId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_PATTERN_SEQ")
            }
    )
@Column(name = "ORDER_PATTERN_ID", length = 22)
public java.lang.Long getOrderPatternId(){
return orderPatternId;
}
public void setOrderPatternId(java.lang.Long orderPatternId)
{
this.orderPatternId = orderPatternId;
}
@Column(name = "NAME", length = 2000)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "STATUS", length = 4)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_USER_ID", length = 22)
public java.lang.Long getCreatedUserId(){
return createdUserId;
}
public void setCreatedUserId(java.lang.Long createdUserId)
{
this.createdUserId = createdUserId;
}

@Column(name = "CREATED_USER_NAME", length = 1000)
public java.lang.String getCreatedUserName() {
	return createdUserName;
}

public void setCreatedUserName(java.lang.String createdUserName) {
	this.createdUserName = createdUserName;
}
   

    @Override
    public OrderPatternDTO toDTO() {
        OrderPatternDTO orderPatternDTO = new OrderPatternDTO();
        //set cac gia tri 
        orderPatternDTO.setOrderPatternId(this.orderPatternId);
        orderPatternDTO.setName(this.name);
        orderPatternDTO.setStatus(this.status);
        orderPatternDTO.setDescription(this.description);
        orderPatternDTO.setCreatedDate(this.createdDate);
        orderPatternDTO.setCreatedUserId(this.createdUserId);
        orderPatternDTO.setCreatedUserName(this.createdUserName);
        return orderPatternDTO;
    }
}
