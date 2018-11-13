/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatConstrTypesBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_CONSTR_TYPESBO")
public class CatConstrTypesDTO extends BaseFWDTOImpl<CatConstrTypesBO> {

private Long constrTypeId;
private String constrTypeName;
private String description;
private Long isActive;
private String code;

    @Override
    public CatConstrTypesBO toModel() {
        CatConstrTypesBO catConstrTypesBO = new CatConstrTypesBO();
        catConstrTypesBO.setConstrTypeId(this.constrTypeId);
        catConstrTypesBO.setConstrTypeName(this.constrTypeName);
        catConstrTypesBO.setDescription(this.description);
        catConstrTypesBO.setIsActive(this.isActive);
        catConstrTypesBO.setCode(this.code);
        return catConstrTypesBO;
    }

    @Override
     public Long getFWModelId() {
        return constrTypeId;
    }
   
    @Override
    public String catchName() {
        return getConstrTypeId().toString();
    }
    public Long getConstrTypeId(){
    return constrTypeId;
    }
    public void setConstrTypeId(Long constrTypeId)
    {
    this.constrTypeId = constrTypeId;
    }
    
    public String getConstrTypeName(){
    return constrTypeName;
    }
    public void setConstrTypeName(String constrTypeName)
    {
    this.constrTypeName = constrTypeName;
    }
    
    public String getDescription(){
    return description;
    }
    public void setDescription(String description)
    {
    this.description = description;
    }
    
    public Long getIsActive(){
    return isActive;
    }
    public void setIsActive(Long isActive)
    {
    this.isActive = isActive;
    }
    
    public String getCode(){
    return code;
    }
    public void setCode(String code)
    {
    this.code = code;
    }
    
   
}
