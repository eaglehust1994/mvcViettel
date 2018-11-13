/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.DomainTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "VPS_OWNER.DOMAIN_TYPE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class DomainTypeBO extends BaseFWModelImpl {
     
private java.lang.Long domainTypeId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long type;

 public DomainTypeBO() {
        setColId("domainTypeId");
        setColName("domainTypeId");
        setUniqueColumn(new String[]{"domainTypeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VPS_OWNER.DOMAIN_TYPE_SEQ")
            }
    )
@Column(name = "DOMAIN_TYPE_ID", length = 22)
public java.lang.Long getDomainTypeId(){
return domainTypeId;
}
public void setDomainTypeId(java.lang.Long domainTypeId)
{
this.domainTypeId = domainTypeId;
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
@Column(name = "DESCRIPTION", length = 1000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
   

    @Override
    public DomainTypeDTO toDTO() {
        DomainTypeDTO domainTypeDTO = new DomainTypeDTO();
        //set cac gia tri 
        domainTypeDTO.setDomainTypeId(this.domainTypeId);
        domainTypeDTO.setCode(this.code);
        domainTypeDTO.setName(this.name);
        domainTypeDTO.setDescription(this.description);
        domainTypeDTO.setType(this.type);
        return domainTypeDTO;
    }
}
