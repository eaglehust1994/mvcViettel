/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_WORK_LOGS_LABEL")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrWorkLogsLabelBO extends BaseFWModelImpl {
     
private java.lang.Long constrWoLogsLabId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long constructId;
private java.lang.Long isActive;
private java.lang.Long aMonitorId;
private java.lang.Long bConstructId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;

 public ConstrWorkLogsLabelBO() {
        setColId("constrWoLogsLabId");
        setColName("constrWoLogsLabId");
        setUniqueColumn(new String[]{"constrWoLogsLabId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_WORK_LOGS_LABEL_SEQ")
            }
    )
@Column(name = "CONSTR_WO_LOGS_LAB_ID", length = 22)
public java.lang.Long getConstrWoLogsLabId(){
return constrWoLogsLabId;
}
public void setConstrWoLogsLabId(java.lang.Long constrWoLogsLabId)
{
this.constrWoLogsLabId = constrWoLogsLabId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
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
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
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
@Column(name = "A_DIRECTOR_ID", length = 22)
public java.lang.Long getADirectorId(){
return aDirectorId;
}
public void setADirectorId(java.lang.Long aDirectorId)
{
this.aDirectorId = aDirectorId;
}
@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBDirectorId(){
return bDirectorId;
}
public void setBDirectorId(java.lang.Long bDirectorId)
{
this.bDirectorId = bDirectorId;
}
   

    @Override
    public ConstrWorkLogsLabelDTO toDTO() {
        ConstrWorkLogsLabelDTO constrWorkLogsLabelDTO = new ConstrWorkLogsLabelDTO();
        //set cac gia tri 
        constrWorkLogsLabelDTO.setConstrWoLogsLabId(this.constrWoLogsLabId);
        constrWorkLogsLabelDTO.setCode(this.code);
        constrWorkLogsLabelDTO.setCreatedDate(this.createdDate);
        constrWorkLogsLabelDTO.setCreatedUserId(this.createdUserId);
        constrWorkLogsLabelDTO.setApprovalDate(this.approvalDate);
        constrWorkLogsLabelDTO.setStatusCa(this.statusCa);
        constrWorkLogsLabelDTO.setConstructId(this.constructId);
        constrWorkLogsLabelDTO.setIsActive(this.isActive);
        constrWorkLogsLabelDTO.setAMonitorId(this.aMonitorId);
        constrWorkLogsLabelDTO.setBConstructId(this.bConstructId);
        constrWorkLogsLabelDTO.setADirectorId(this.aDirectorId);
        constrWorkLogsLabelDTO.setBDirectorId(this.bDirectorId);
        return constrWorkLogsLabelDTO;
    }
}
