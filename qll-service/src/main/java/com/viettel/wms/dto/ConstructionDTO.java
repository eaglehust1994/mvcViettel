/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.ConstructionBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "CONSTRUCTIONBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructionDTO extends wmsBaseDTO<ConstructionBO> {

private java.lang.String type;
private java.lang.Long constructionId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String status;

    @Override
    public ConstructionBO toModel() {
        ConstructionBO constructionBO = new ConstructionBO();
        constructionBO.setType(this.type);
        constructionBO.setConstructionId(this.constructionId);
        constructionBO.setCode(this.code);
        constructionBO.setName(this.name);
        constructionBO.setStatus(this.status);
        return constructionBO;
    }

    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    @Override
     public Long getFWModelId() {
        return constructionId;
    }
   
    @Override
    public String catchName() {
        return getConstructionId().toString();
    }
    public java.lang.Long getConstructionId(){
    return constructionId;
    }
    public void setConstructionId(java.lang.Long constructionId)
    {
    this.constructionId = constructionId;
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
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
   
}
