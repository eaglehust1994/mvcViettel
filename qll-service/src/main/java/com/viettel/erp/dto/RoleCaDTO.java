/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.RoleCaBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ROLE_CABO")
public class RoleCaDTO extends BaseFWDTOImpl<RoleCaBO> {

/**
 * 
 */
private static final long serialVersionUID = -1782991983428828702L;

private java.lang.Long roleid;
private java.lang.String rolename;
private java.lang.String tel;
private java.lang.String rolecode;
private java.util.Date updatedate;
private java.lang.Long type;
private java.lang.Long groupSide;

    @Override
    public RoleCaBO toModel() {
        RoleCaBO roleCaBO = new RoleCaBO();
        roleCaBO.setRoleid(this.roleid);
        roleCaBO.setRolename(this.rolename);
        roleCaBO.setTel(this.tel);
        roleCaBO.setRolecode(this.rolecode);
        roleCaBO.setUpdatedate(this.updatedate);
        roleCaBO.setType(this.type);
        roleCaBO.setGroupSide(this.groupSide);
        return roleCaBO;
    }

    @Override
     public Long getFWModelId() {
        return roleid;
    }
   
    @Override
    public String catchName() {
        return getRoleid().toString();
    }
    public java.lang.Long getRoleid(){
    return roleid;
    }
    public void setRoleid(java.lang.Long roleid)
    {
    this.roleid = roleid;
    }
    
    public java.lang.String getRolename(){
    return rolename;
    }
    public void setRolename(java.lang.String rolename)
    {
    this.rolename = rolename;
    }
    
    public java.lang.String getTel(){
    return tel;
    }
    public void setTel(java.lang.String tel)
    {
    this.tel = tel;
    }
    
    public java.lang.String getRolecode(){
    return rolecode;
    }
    public void setRolecode(java.lang.String rolecode)
    {
    this.rolecode = rolecode;
    }
    
    public java.util.Date getUpdatedate(){
    return updatedate;
    }
    public void setUpdatedate(java.util.Date updatedate)
    {
    this.updatedate = updatedate;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.Long getGroupSide(){
    return groupSide;
    }
    public void setGroupSide(java.lang.Long groupSide)
    {
    this.groupSide = groupSide;
    }
    
   
}
