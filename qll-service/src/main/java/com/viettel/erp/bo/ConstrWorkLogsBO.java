/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_WORK_LOGS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrWorkLogsBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long aMonitorId;
private java.lang.Long bConstructId;
private java.lang.Long constrWorkLogsId;
private java.lang.String code;
private java.util.Date logDate;
private java.lang.String workContent;
private java.lang.String additionChangeArise;
private java.lang.String contractorComments;
private java.lang.String monitorComments;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long estimatesWorkItemId;
private java.lang.Long isActive;

 public ConstrWorkLogsBO() {
        setColId("constrWorkLogsId");
        setColName("constrWorkLogsId");
        setUniqueColumn(new String[]{"constrWorkLogsId"});
}

@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "A_MONITOR_ID", length = 22)
public java.lang.Long getAMonitorId(){
return aMonitorId;
}
public void setAMonitorId(java.lang.Long aMonitorId)
{
this.aMonitorId = aMonitorId;
}
@Column(name = "B_CONSTRUCT_ID", length = 22)
public java.lang.Long getBConstructId(){
return bConstructId;
}
public void setBConstructId(java.lang.Long bConstructId)
{
this.bConstructId = bConstructId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_WORK_LOGS_SEQ")
            }
    )
@Column(name = "CONSTR_WORK_LOGS_ID", length = 22)
public java.lang.Long getConstrWorkLogsId(){
return constrWorkLogsId;
}
public void setConstrWorkLogsId(java.lang.Long constrWorkLogsId)
{
this.constrWorkLogsId = constrWorkLogsId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "LOG_DATE", length = 7)
public java.util.Date getLogDate(){
return logDate;
}
public void setLogDate(java.util.Date logDate)
{
this.logDate = logDate;
}
@Column(name = "WORK_CONTENT", length = 1000)
public java.lang.String getWorkContent(){
return workContent;
}
public void setWorkContent(java.lang.String workContent)
{
this.workContent = workContent;
}
@Column(name = "ADDITION_CHANGE_ARISE", length = 1000)
public java.lang.String getAdditionChangeArise(){
return additionChangeArise;
}
public void setAdditionChangeArise(java.lang.String additionChangeArise)
{
this.additionChangeArise = additionChangeArise;
}
@Column(name = "CONTRACTOR_COMMENTS", length = 1000)
public java.lang.String getContractorComments(){
return contractorComments;
}
public void setContractorComments(java.lang.String contractorComments)
{
this.contractorComments = contractorComments;
}
@Column(name = "MONITOR_COMMENTS", length = 1000)
public java.lang.String getMonitorComments(){
return monitorComments;
}
public void setMonitorComments(java.lang.String monitorComments)
{
this.monitorComments = monitorComments;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_USER_ID", length = 22)
public java.lang.Long getCreatedUserId(){
return createdUserId;
}
public void setCreatedUserId(java.lang.Long createdUserId)
{
this.createdUserId = createdUserId;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
public java.lang.Long getEstimatesWorkItemId(){
return estimatesWorkItemId;
}
public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId)
{
this.estimatesWorkItemId = estimatesWorkItemId;
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
    public ConstrWorkLogsDTO toDTO() {
        ConstrWorkLogsDTO constrWorkLogsDTO = new ConstrWorkLogsDTO();
        //set cac gia tri 
        constrWorkLogsDTO.setConstructId(this.constructId);
        constrWorkLogsDTO.setAMonitorId(this.aMonitorId);
        constrWorkLogsDTO.setBConstructId(this.bConstructId);
        constrWorkLogsDTO.setConstrWorkLogsId(this.constrWorkLogsId);
        constrWorkLogsDTO.setCode(this.code);
        constrWorkLogsDTO.setLogDate(this.logDate);
        constrWorkLogsDTO.setWorkContent(this.workContent);
        constrWorkLogsDTO.setAdditionChangeArise(this.additionChangeArise);
        constrWorkLogsDTO.setContractorComments(this.contractorComments);
        constrWorkLogsDTO.setMonitorComments(this.monitorComments);
        constrWorkLogsDTO.setCreatedDate(this.createdDate);
        constrWorkLogsDTO.setCreatedUserId(this.createdUserId);
        constrWorkLogsDTO.setApprovalDate(this.approvalDate);
        constrWorkLogsDTO.setStatusCa(this.statusCa);
        constrWorkLogsDTO.setEstimatesWorkItemId(this.estimatesWorkItemId);
        constrWorkLogsDTO.setIsActive(this.isActive);
        return constrWorkLogsDTO;
    }
}
