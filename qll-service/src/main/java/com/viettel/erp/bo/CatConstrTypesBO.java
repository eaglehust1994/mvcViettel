/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatConstrTypesDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "CAT_CONSTR_TYPES")
@Where( clause = "IS_ACTIVE = 1" )
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatConstrTypesBO extends BaseFWModelImpl {
     
private Long constrTypeId;
private String constrTypeName;
private String description;
private Long isActive;
private String code;

 public CatConstrTypesBO() {
        setColId("constrTypeId");
        setColName("constrTypeId");
        setUniqueColumn(new String[]{"constrTypeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_CONSTR_TYPES_SEQ")
            }
    )
@Column(name = "CONSTR_TYPE_ID", length = 22)
public Long getConstrTypeId(){
return constrTypeId;
}
public void setConstrTypeId(Long constrTypeId)
{
this.constrTypeId = constrTypeId;
}
@Column(name = "CONSTR_TYPE_NAME", length = 100)
public String getConstrTypeName(){
return constrTypeName;
}
public void setConstrTypeName(String constrTypeName)
{
this.constrTypeName = constrTypeName;
}
@Column(name = "DESCRIPTION", length = 200)
public String getDescription(){
return description;
}
public void setDescription(String description)
{
this.description = description;
}
@Column(name = "IS_ACTIVE", length = 22)
public Long getIsActive(){
return isActive;
}
public void setIsActive(Long isActive)
{
this.isActive = isActive;
}
@Column(name = "CODE", length = 50)
public String getCode(){
return code;
}
public void setCode(String code)
{
this.code = code;
}
   

    @Override
    public CatConstrTypesDTO toDTO() {
        CatConstrTypesDTO catConstrTypesDTO = new CatConstrTypesDTO();
        //set cac gia tri 
        catConstrTypesDTO.setConstrTypeId(this.constrTypeId);
        catConstrTypesDTO.setConstrTypeName(this.constrTypeName);
        catConstrTypesDTO.setDescription(this.description);
        catConstrTypesDTO.setIsActive(this.isActive);
        catConstrTypesDTO.setCode(this.code);
        return catConstrTypesDTO;
    }
}
