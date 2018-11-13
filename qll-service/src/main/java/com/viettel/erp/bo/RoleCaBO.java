/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "roleCa")
@Table(name = "ROLE_CA")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class RoleCaBO extends BaseFWModelImpl {
     
/**
 * 
 */
private static final long serialVersionUID = 5869848826589986223L;
private java.lang.Long roleid;
private java.lang.String rolename;
private java.lang.String tel;
private java.lang.String rolecode;
private java.util.Date updatedate;
private java.lang.Long type;
private java.lang.Long groupSide;

 public RoleCaBO() {
        setColId("roleid");
        setColName("roleid");
        setUniqueColumn(new String[]{"roleid"});
}
 public RoleCaBO(java.lang.Long roleid) {
     this.roleid = roleid;
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "ROLE_CA_SEQ")
            }
    )
@Column(name = "ROLEID", length = 22)
public java.lang.Long getRoleid(){
return roleid;
}
public void setRoleid(java.lang.Long roleid)
{
this.roleid = roleid;
}
@Column(name = "ROLENAME", length = 1024)
public java.lang.String getRolename(){
return rolename;
}
public void setRolename(java.lang.String rolename)
{
this.rolename = rolename;
}
@Column(name = "TEL", length = 200)
public java.lang.String getTel(){
return tel;
}
public void setTel(java.lang.String tel)
{
this.tel = tel;
}
@Column(name = "ROLECODE", length = 400)
public java.lang.String getRolecode(){
return rolecode;
}
public void setRolecode(java.lang.String rolecode)
{
this.rolecode = rolecode;
}
@Column(name = "UPDATEDATE", length = 7)
public java.util.Date getUpdatedate(){
return updatedate;
}
public void setUpdatedate(java.util.Date updatedate)
{
this.updatedate = updatedate;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "GROUP_SIDE", length = 22)
public java.lang.Long getGroupSide(){
return groupSide;
}
public void setGroupSide(java.lang.Long groupSide)
{
this.groupSide = groupSide;
}
   

    @Override
    public RoleCaDTO toDTO() {
        RoleCaDTO roleCaDTO = new RoleCaDTO();
        //set cac gia tri 
        roleCaDTO.setRoleid(this.roleid);
        roleCaDTO.setRolename(this.rolename);
        roleCaDTO.setTel(this.tel);
        roleCaDTO.setRolecode(this.rolecode);
        roleCaDTO.setUpdatedate(this.updatedate);
        roleCaDTO.setType(this.type);
        roleCaDTO.setGroupSide(this.groupSide);
        return roleCaDTO;
    }
}
