/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.UserEmployeeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "USER_EMPLOYEEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEmployeeDTO extends BaseFWDTOImpl<UserEmployeeBO> {

private java.lang.Long userEmployeeId;
private java.lang.Long employeeId;
private java.lang.Long userId;

    @Override
    public UserEmployeeBO toModel() {
        UserEmployeeBO userEmployeeBO = new UserEmployeeBO();
        userEmployeeBO.setUserEmployeeId(this.userEmployeeId);
        userEmployeeBO.setEmployeeId(this.employeeId);
        userEmployeeBO.setUserId(this.userId);
        return userEmployeeBO;
    }

    @Override
     public Long getFWModelId() {
        return userEmployeeId;
    }
   
    @Override
    public String catchName() {
        return getUserEmployeeId().toString();
    }
    public java.lang.Long getUserEmployeeId(){
    return userEmployeeId;
    }
    public void setUserEmployeeId(java.lang.Long userEmployeeId)
    {
    this.userEmployeeId = userEmployeeId;
    }
    
    public java.lang.Long getEmployeeId(){
    return employeeId;
    }
    public void setEmployeeId(java.lang.Long employeeId)
    {
    this.employeeId = employeeId;
    }
    
    public java.lang.Long getUserId(){
    return userId;
    }
    public void setUserId(java.lang.Long userId)
    {
    this.userId = userId;
    }
    
   
}
