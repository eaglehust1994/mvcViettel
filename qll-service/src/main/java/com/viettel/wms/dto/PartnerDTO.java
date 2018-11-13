/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.PartnerBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "PARTNERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerDTO extends wmsBaseDTO<PartnerBO> {

private java.lang.String type;
private java.lang.String status;
private java.lang.String name;
private java.lang.String code;
private java.lang.Long partnerId;

    @Override
    public PartnerBO toModel() {
        PartnerBO partnerBO = new PartnerBO();
        partnerBO.setType(this.type);
        partnerBO.setStatus(this.status);
        partnerBO.setName(this.name);
        partnerBO.setCode(this.code);
        partnerBO.setPartnerId(this.partnerId);
        return partnerBO;
    }

    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    @Override
     public Long getFWModelId() {
        return partnerId;
    }
   
    @Override
    public String catchName() {
        return getPartnerId().toString();
    }
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
   
}
