/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ManufacturerDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.MANUFACTURER")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ManufacturerBO extends BaseFWModelImpl {
     
private java.lang.String status;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long manufacturerId;

 public ManufacturerBO() {
        setColId("manufacturerId");
        setColName("manufacturerId");
        setUniqueColumn(new String[]{"manufacturerId"});
}

@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "NAME", length = 100)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.MANUFACTURER_SEQ")
            }
    )
@Column(name = "MANUFACTURER_ID", length = 22)
public java.lang.Long getManufacturerId(){
return manufacturerId;
}
public void setManufacturerId(java.lang.Long manufacturerId)
{
this.manufacturerId = manufacturerId;
}
   

    @Override
    public ManufacturerDTO toDTO() {
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        //set cac gia tri 
        manufacturerDTO.setStatus(this.status);
        manufacturerDTO.setCode(this.code);
        manufacturerDTO.setName(this.name);
        manufacturerDTO.setManufacturerId(this.manufacturerId);
        return manufacturerDTO;
    }
}
