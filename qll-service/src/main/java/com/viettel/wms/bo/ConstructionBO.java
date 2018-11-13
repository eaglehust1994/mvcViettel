/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ConstructionDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.CONSTRUCTION")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ConstructionBO extends BaseFWModelImpl {
     
private java.lang.String type;
private java.lang.Long constructionId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String status;

 public ConstructionBO() {
        setColId("constructionId");
        setColName("constructionId");
        setUniqueColumn(new String[]{"constructionId"});
}

@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.CONSTRUCTION_SEQ")
            }
    )
@Column(name = "CONSTRUCTION_ID", length = 22)
public java.lang.Long getConstructionId(){
return constructionId;
}
public void setConstructionId(java.lang.Long constructionId)
{
this.constructionId = constructionId;
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
@Column(name = "STATUS", length = 10)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
   

    @Override
    public ConstructionDTO toDTO() {
        ConstructionDTO constructionDTO = new ConstructionDTO();
        //set cac gia tri 
        constructionDTO.setType(this.type);
        constructionDTO.setConstructionId(this.constructionId);
        constructionDTO.setCode(this.code);
        constructionDTO.setName(this.name);
        constructionDTO.setStatus(this.status);
        return constructionDTO;
    }
}
