/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ProducingCountryDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.PRODUCING_COUNTRY")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ProducingCountryBO extends BaseFWModelImpl {
     
private java.lang.Long producingCountryId;
private java.lang.String status;
private java.lang.String name;
private java.lang.String code;

 public ProducingCountryBO() {
        setColId("producingCountryId");
        setColName("producingCountryId");
        setUniqueColumn(new String[]{"producingCountryId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.PRODUCING_COUNTRY_SEQ")
            }
    )
@Column(name = "PRODUCING_COUNTRY_ID", length = 22)
public java.lang.Long getProducingCountryId(){
return producingCountryId;
}
public void setProducingCountryId(java.lang.Long producingCountryId)
{
this.producingCountryId = producingCountryId;
}
@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "NAME", length = 100)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
   

    @Override
    public ProducingCountryDTO toDTO() {
        ProducingCountryDTO producingCountryDTO = new ProducingCountryDTO();
        //set cac gia tri 
        producingCountryDTO.setProducingCountryId(this.producingCountryId);
        producingCountryDTO.setStatus(this.status);
        producingCountryDTO.setName(this.name);
        producingCountryDTO.setCode(this.code);
        return producingCountryDTO;
    }
}
