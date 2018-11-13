/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "APPROVAL_SIGN_MANAGEMENT")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ApprovalSignManagementBO extends BaseFWModelImpl {
     
private java.lang.Long isLast;
private java.lang.Long approvalSignManaId;
private java.util.Date approvalDate;
private java.lang.String comments;
private java.lang.Long approvalStatus;
private java.lang.Long signOrder;
private java.lang.Long constrCompReMapId;
private java.lang.Long approvalOrder;
private java.lang.Long employeeId;
private java.lang.Long roleid;

 public ApprovalSignManagementBO() {
        setColId("approvalSignManaId");
        setColName("approvalSignManaId");
        setUniqueColumn(new String[]{"approvalSignManaId"});
}

@Column(name = "IS_LAST", length = 22)
public java.lang.Long getIsLast(){
return isLast;
}
public void setIsLast(java.lang.Long isLast)
{
this.isLast = isLast;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "APPROVAL_SIGN_MANAGEMENT_SEQ")
            }
    )
@Column(name = "APPROVAL_SIGN_MANA_ID", length = 22)
public java.lang.Long getApprovalSignManaId(){
return approvalSignManaId;
}
public void setApprovalSignManaId(java.lang.Long approvalSignManaId)
{
this.approvalSignManaId = approvalSignManaId;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "COMMENTS", length = 1000)
public java.lang.String getComments(){
return comments;
}
public void setComments(java.lang.String comments)
{
this.comments = comments;
}
@Column(name = "APPROVAL_STATUS", length = 22)
public java.lang.Long getApprovalStatus(){
return approvalStatus;
}
public void setApprovalStatus(java.lang.Long approvalStatus)
{
this.approvalStatus = approvalStatus;
}
@Column(name = "SIGN_ORDER", length = 22)
public java.lang.Long getSignOrder(){
return signOrder;
}
public void setSignOrder(java.lang.Long signOrder)
{
this.signOrder = signOrder;
}
@Column(name = "CONSTR_COMP_RE_MAP_ID", length = 22)
public java.lang.Long getConstrCompReMapId(){
return constrCompReMapId;
}
public void setConstrCompReMapId(java.lang.Long constrCompReMapId)
{
this.constrCompReMapId = constrCompReMapId;
}
@Column(name = "APPROVAL_ORDER", length = 22)
public java.lang.Long getApprovalOrder(){
return approvalOrder;
}
public void setApprovalOrder(java.lang.Long approvalOrder)
{
this.approvalOrder = approvalOrder;
}
@Column(name = "EMPLOYEE_ID", length = 22)
public java.lang.Long getEmployeeId(){
return employeeId;
}
public void setEmployeeId(java.lang.Long employeeId)
{
this.employeeId = employeeId;
}
@Column(name = "ROLEID", length = 22)
public java.lang.Long getRoleid(){
return roleid;
}
public void setRoleid(java.lang.Long roleid)
{
this.roleid = roleid;
}
   

    @Override
    public ApprovalSignManagementDTO toDTO() {
        ApprovalSignManagementDTO approvalSignManagementDTO = new ApprovalSignManagementDTO();
        //set cac gia tri 
        approvalSignManagementDTO.setIsLast(this.isLast);
        approvalSignManagementDTO.setApprovalSignManaId(this.approvalSignManaId);
        approvalSignManagementDTO.setApprovalDate(this.approvalDate);
        approvalSignManagementDTO.setComments(this.comments);
        approvalSignManagementDTO.setApprovalStatus(this.approvalStatus);
        approvalSignManagementDTO.setSignOrder(this.signOrder);
        approvalSignManagementDTO.setConstrCompReMapId(this.constrCompReMapId);
        approvalSignManagementDTO.setApprovalOrder(this.approvalOrder);
        approvalSignManagementDTO.setEmployeeId(this.employeeId);
        approvalSignManagementDTO.setRoleid(this.roleid);
        return approvalSignManagementDTO;
    }
}
