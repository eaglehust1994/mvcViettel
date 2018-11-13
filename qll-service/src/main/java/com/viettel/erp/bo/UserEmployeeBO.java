/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.UserEmployeeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USER_EMPLOYEE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class UserEmployeeBO extends BaseFWModelImpl {
     
private java.lang.Long userEmployeeId;
private java.lang.Long employeeId;
private java.lang.Long userId;

 public UserEmployeeBO() {
        setColId("userEmployeeId");
        setColName("userEmployeeId");
        setUniqueColumn(new String[]{"userEmployeeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "USER_EMPLOYEE_SEQ")
            }
    )
@Column(name = "USER_EMPLOYEE_ID", length = 22)
public java.lang.Long getUserEmployeeId(){
return userEmployeeId;
}
public void setUserEmployeeId(java.lang.Long userEmployeeId)
{
this.userEmployeeId = userEmployeeId;
}
@Column(name = "EMPLOYEE_ID", length = 22)
public java.lang.Long getEmployeeId(){
return employeeId;
}
public void setEmployeeId(java.lang.Long employeeId)
{
this.employeeId = employeeId;
}
@Column(name = "USER_ID", length = 22)
public java.lang.Long getUserId(){
return userId;
}
public void setUserId(java.lang.Long userId)
{
this.userId = userId;
}
   

    @Override
    public UserEmployeeDTO toDTO() {
        UserEmployeeDTO userEmployeeDTO = new UserEmployeeDTO();
        //set cac gia tri 
        userEmployeeDTO.setUserEmployeeId(this.userEmployeeId);
        userEmployeeDTO.setEmployeeId(this.employeeId);
        userEmployeeDTO.setUserId(this.userId);
        return userEmployeeDTO;
    }
}
