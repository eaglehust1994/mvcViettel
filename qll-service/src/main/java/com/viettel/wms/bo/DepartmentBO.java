/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.DEPARTMENT")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class DepartmentBO extends BaseFWModelImpl {
     
private java.util.Date endDate;
private java.util.Date effectDate;
private java.lang.String path;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long parentId;
private java.lang.String status;
private java.lang.Long departmentId;

 public DepartmentBO() {
        setColId("departmentId");
        setColName("departmentId");
        setUniqueColumn(new String[]{"departmentId"});
}

@Column(name = "END_DATE", length = 7)
public java.util.Date getEndDate(){
return endDate;
}
public void setEndDate(java.util.Date endDate)
{
this.endDate = endDate;
}
@Column(name = "EFFECT_DATE", length = 7)
public java.util.Date getEffectDate(){
return effectDate;
}
public void setEffectDate(java.util.Date effectDate)
{
this.effectDate = effectDate;
}
@Column(name = "PATH", length = 1000)
public java.lang.String getPath(){
return path;
}
public void setPath(java.lang.String path)
{
this.path = path;
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
@Column(name = "PARENT_ID", length = 22)
public java.lang.Long getParentId(){
return parentId;
}
public void setParentId(java.lang.Long parentId)
{
this.parentId = parentId;
}
@Column(name = "STATUS", length = 20)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.DEPARTMENT_SEQ")
            }
    )
@Column(name = "DEPARTMENT_ID", length = 22)
public java.lang.Long getDepartmentId(){
return departmentId;
}
public void setDepartmentId(java.lang.Long departmentId)
{
this.departmentId = departmentId;
}
   

    @Override
    public DepartmentDTO toDTO() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        //set cac gia tri 
        departmentDTO.setEndDate(this.endDate);
        departmentDTO.setEffectDate(this.effectDate);
        departmentDTO.setPath(this.path);
        departmentDTO.setCode(this.code);
        departmentDTO.setName(this.name);
        departmentDTO.setParentId(this.parentId);
        departmentDTO.setStatus(this.status);
        departmentDTO.setDepartmentId(this.departmentId);
        return departmentDTO;
    }
}
