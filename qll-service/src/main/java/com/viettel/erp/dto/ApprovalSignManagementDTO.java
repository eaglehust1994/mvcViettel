/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ApprovalSignManagementBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "APPROVAL_SIGN_MANAGEMENTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalSignManagementDTO extends BaseFWDTOImpl<ApprovalSignManagementBO> {

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
private java.lang.String tableName;
private java.lang.Long tableId;
private java.lang.String tableIdField;
    @Override
    public ApprovalSignManagementBO toModel() {
        ApprovalSignManagementBO approvalSignManagementBO = new ApprovalSignManagementBO();
        approvalSignManagementBO.setIsLast(this.isLast);
        approvalSignManagementBO.setApprovalSignManaId(this.approvalSignManaId);
        approvalSignManagementBO.setApprovalDate(this.approvalDate);
        approvalSignManagementBO.setComments(this.comments);
        approvalSignManagementBO.setApprovalStatus(this.approvalStatus);
        approvalSignManagementBO.setSignOrder(this.signOrder);
        approvalSignManagementBO.setConstrCompReMapId(this.constrCompReMapId);
        approvalSignManagementBO.setApprovalOrder(this.approvalOrder);
        approvalSignManagementBO.setEmployeeId(this.employeeId);
        approvalSignManagementBO.setRoleid(this.roleid);
        return approvalSignManagementBO;
    }

    public java.lang.Long getIsLast(){
    return isLast;
    }
    public void setIsLast(java.lang.Long isLast)
    {
    this.isLast = isLast;
    }
    
    @Override
     public Long getFWModelId() {
        return approvalSignManaId;
    }
   
    @Override
    public String catchName() {
        return getApprovalSignManaId().toString();
    }
    public java.lang.Long getApprovalSignManaId(){
    return approvalSignManaId;
    }
    public void setApprovalSignManaId(java.lang.Long approvalSignManaId)
    {
    this.approvalSignManaId = approvalSignManaId;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.String getComments(){
    return comments;
    }
    public void setComments(java.lang.String comments)
    {
    this.comments = comments;
    }
    
    public java.lang.Long getApprovalStatus(){
    return approvalStatus;
    }
    public void setApprovalStatus(java.lang.Long approvalStatus)
    {
    this.approvalStatus = approvalStatus;
    }
    
    public java.lang.Long getSignOrder(){
    return signOrder;
    }
    public void setSignOrder(java.lang.Long signOrder)
    {
    this.signOrder = signOrder;
    }
    
    public java.lang.Long getConstrCompReMapId(){
    return constrCompReMapId;
    }
    public void setConstrCompReMapId(java.lang.Long constrCompReMapId)
    {
    this.constrCompReMapId = constrCompReMapId;
    }
    
    public java.lang.Long getApprovalOrder(){
    return approvalOrder;
    }
    public void setApprovalOrder(java.lang.Long approvalOrder)
    {
    this.approvalOrder = approvalOrder;
    }
    
    public java.lang.Long getEmployeeId(){
    return employeeId;
    }
    public void setEmployeeId(java.lang.Long employeeId)
    {
    this.employeeId = employeeId;
    }
    
    public java.lang.Long getRoleid(){
    return roleid;
    }
    public void setRoleid(java.lang.Long roleid)
    {
    this.roleid = roleid;
    }

	public java.lang.String getTableName() {
		return tableName;
	}

	public void setTableName(java.lang.String tableName) {
		this.tableName = tableName;
	}

	public java.lang.Long getTableId() {
		return tableId;
	}

	public void setTableId(java.lang.Long tableId) {
		this.tableId = tableId;
	}

	public java.lang.String getTableIdField() {
		return tableIdField;
	}

	public void setTableIdField(java.lang.String tableIdField) {
		this.tableIdField = tableIdField;
	}
    
   
}
