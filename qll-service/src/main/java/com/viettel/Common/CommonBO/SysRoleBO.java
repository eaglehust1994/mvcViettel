/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.Common.CommonBO;

import com.viettel.Common.CommonDTO.SysRoleDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "VPS_OWNER.SYS_ROLE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class SysRoleBO extends BaseFWModelImpl {
     
private java.lang.Long sysRoleId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String description;
private java.lang.Long newId;

 public SysRoleBO() {
        setColId("sysRoleId");
        setColName("sysRoleId");
        setUniqueColumn(new String[]{"sysRoleId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VPS_OWNER.SYS_ROLE_SEQ")
            }
    )
@Column(name = "SYS_ROLE_ID", length = 22)
public java.lang.Long getSysRoleId(){
return sysRoleId;
}
public void setSysRoleId(java.lang.Long sysRoleId)
{
this.sysRoleId = sysRoleId;
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
@Column(name = "NEW_ID", length = 22)
public java.lang.Long getNewId(){
return newId;
}
public void setNewId(java.lang.Long newId)
{
this.newId = newId;
}
   

    @Override
    public SysRoleDTO toDTO() {
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        //set cac gia tri 
        sysRoleDTO.setSysRoleId(this.sysRoleId);
        sysRoleDTO.setCode(this.code);
        sysRoleDTO.setName(this.name);
        sysRoleDTO.setDescription(this.description);
        sysRoleDTO.setNewId(this.newId);
        return sysRoleDTO;
    }
}
