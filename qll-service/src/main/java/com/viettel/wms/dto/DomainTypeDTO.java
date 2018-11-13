/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.DomainTypeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "DOMAIN_TYPEBO")
public class DomainTypeDTO extends BaseFWDTOImpl<DomainTypeBO> {

private java.lang.Long domainTypeId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long type;

    @Override
    public DomainTypeBO toModel() {
        DomainTypeBO domainTypeBO = new DomainTypeBO();
        domainTypeBO.setDomainTypeId(this.domainTypeId);
        domainTypeBO.setCode(this.code);
        domainTypeBO.setName(this.name);
        domainTypeBO.setDescription(this.description);
        domainTypeBO.setType(this.type);
        return domainTypeBO;
    }

    @Override
     public Long getFWModelId() {
        return domainTypeId;
    }
   
    @Override
    public String catchName() {
        return getDomainTypeId().toString();
    }
    public java.lang.Long getDomainTypeId(){
    return domainTypeId;
    }
    public void setDomainTypeId(java.lang.Long domainTypeId)
    {
    this.domainTypeId = domainTypeId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
   
}
