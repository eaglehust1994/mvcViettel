/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.Common.CommonBO;

import com.viettel.Common.CommonDTO.UserRoleDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "VPS_OWNER.USER_ROLE")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class UserRoleBO extends BaseFWModelImpl {
     
private java.lang.Long sysRoleId;
private java.lang.Long sysUserId;
private java.lang.Long userRoleId;
private java.lang.Long isActive;

 public UserRoleBO() {
        setColId("userRoleId");
        setColName("userRoleId");
        setUniqueColumn(new String[]{"userRoleId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VPS_OWNER.USER_ROLE_SEQ")
            }
    )
@Column(name = "USER_ROLE_ID", length = 22)
public java.lang.Long getUserRoleId(){
return userRoleId;
}
public void setUserRoleId(java.lang.Long userRoleId)
{
this.userRoleId = userRoleId;
}

@Column(name = "SYS_ROLE_ID", length = 22)
public java.lang.Long getSysRoleId(){
return sysRoleId;
}

public void setSysRoleId(java.lang.Long sysRoleId)
{
this.sysRoleId = sysRoleId;
}

@Column(name = "SYS_USER_ID", length = 22)
public java.lang.Long getSysUserId(){
return sysUserId;
}

public void setSysUserId(java.lang.Long sysUserId)
{
this.sysUserId = sysUserId;
}

@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}

public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
   

    @Override
    public UserRoleDTO toDTO() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        //set cac gia tri 
        userRoleDTO.setSysRoleId(this.sysRoleId);
        userRoleDTO.setSysUserId(this.sysUserId);
        userRoleDTO.setUserRoleId(this.userRoleId);
        userRoleDTO.setIsActive(this.isActive);
        return userRoleDTO;
    }
}
