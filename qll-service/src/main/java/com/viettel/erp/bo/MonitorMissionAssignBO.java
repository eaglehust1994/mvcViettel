/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "MONITOR_MISSION_ASSIGN")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class MonitorMissionAssignBO extends BaseFWModelImpl {
     
private java.lang.Long monitorMissionAssignId;
private java.lang.Long constructId;
private java.lang.String code;
private java.lang.Long aDirectorId;
private java.lang.Long aMonitorId;
private java.util.Date signDate;
private java.lang.String signPlace;
private java.util.Date missionDate;
private java.lang.String monitorDocument;
private java.lang.String assignNote;
private java.lang.Long statusCa;
private java.util.Date approvalDate;
private java.lang.Long isActive;
private java.util.Date createdDate;
private java.lang.Long createdUserId;

 public MonitorMissionAssignBO() {
        setColId("monitorMissionAssignId");
        setColName("monitorMissionAssignId");
        setUniqueColumn(new String[]{"monitorMissionAssignId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "MONITOR_MISSION_ASSIGN_SEQ")
            }
    )
@Column(name = "MONITOR_MISSION_ASSIGN_ID", length = 22)
public java.lang.Long getMonitorMissionAssignId(){
return monitorMissionAssignId;
}
public void setMonitorMissionAssignId(java.lang.Long monitorMissionAssignId)
{
this.monitorMissionAssignId = monitorMissionAssignId;
}
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "A_DIRECTOR_ID", length = 22)
public java.lang.Long getADirectorId(){
return aDirectorId;
}
public void setADirectorId(java.lang.Long aDirectorId)
{
this.aDirectorId = aDirectorId;
}
@Column(name = "A_MONITOR_ID", length = 22)
public java.lang.Long getAMonitorId(){
return aMonitorId;
}
public void setAMonitorId(java.lang.Long aMonitorId)
{
this.aMonitorId = aMonitorId;
}
@Column(name = "SIGN_DATE", length = 7)
public java.util.Date getSignDate(){
return signDate;
}
public void setSignDate(java.util.Date signDate)
{
this.signDate = signDate;
}
@Column(name = "SIGN_PLACE", length = 400)
public java.lang.String getSignPlace(){
return signPlace;
}
public void setSignPlace(java.lang.String signPlace)
{
this.signPlace = signPlace;
}
@Column(name = "MISSION_DATE", length = 7)
public java.util.Date getMissionDate(){
return missionDate;
}
public void setMissionDate(java.util.Date missionDate)
{
this.missionDate = missionDate;
}
@Column(name = "MONITOR_DOCUMENT", length = 1000)
public java.lang.String getMonitorDocument(){
return monitorDocument;
}
public void setMonitorDocument(java.lang.String monitorDocument)
{
this.monitorDocument = monitorDocument;
}
@Column(name = "ASSIGN_NOTE", length = 1000)
public java.lang.String getAssignNote(){
return assignNote;
}
public void setAssignNote(java.lang.String assignNote)
{
this.assignNote = assignNote;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "APPROVAL_DATE", length = 7)
public java.util.Date getApprovalDate(){
return approvalDate;
}
public void setApprovalDate(java.util.Date approvalDate)
{
this.approvalDate = approvalDate;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
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
   

    @Override
    public MonitorMissionAssignDTO toDTO() {
        MonitorMissionAssignDTO monitorMissionAssignDTO = new MonitorMissionAssignDTO();
        //set cac gia tri 
        monitorMissionAssignDTO.setMonitorMissionAssignId(this.monitorMissionAssignId);
        monitorMissionAssignDTO.setConstructId(this.constructId);
        monitorMissionAssignDTO.setCode(this.code);
        monitorMissionAssignDTO.setADirectorId(this.aDirectorId);
        monitorMissionAssignDTO.setAMonitorId(this.aMonitorId);
        monitorMissionAssignDTO.setSignDate(this.signDate);
        monitorMissionAssignDTO.setSignPlace(this.signPlace);
        monitorMissionAssignDTO.setMissionDate(this.missionDate);
        monitorMissionAssignDTO.setMonitorDocument(this.monitorDocument);
        monitorMissionAssignDTO.setAssignNote(this.assignNote);
        monitorMissionAssignDTO.setStatusCa(this.statusCa);
        monitorMissionAssignDTO.setApprovalDate(this.approvalDate);
        monitorMissionAssignDTO.setIsActive(this.isActive);
        monitorMissionAssignDTO.setCreatedDate(this.createdDate);
        monitorMissionAssignDTO.setCreatedUserId(this.createdUserId);
        return monitorMissionAssignDTO;
    }
}
