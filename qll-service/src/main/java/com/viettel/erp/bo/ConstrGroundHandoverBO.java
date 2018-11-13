/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_GROUND_HANDOVER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */

public class ConstrGroundHandoverBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long constrGroundHandoverId;
private java.lang.String code;
private java.util.Date handoverDate;
private java.lang.Long aDirectorId;
private java.lang.Long aMonitorId;
private java.lang.Long aInChargeMonitorId;
private java.lang.Long bDirectorId;
private java.lang.Long bInChargeConstructId;
private java.lang.String groundCurrentStatus;
private java.lang.String benchmark;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.util.Date signDate;
private java.lang.String signPlace;

 public ConstrGroundHandoverBO() {
        setColId("constrGroundHandoverId");
        setColName("constrGroundHandoverId");
        setUniqueColumn(new String[]{"constrGroundHandoverId"});
}

@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_GROUND_HANDOVER_SEQ")
            }
    )
@Column(name = "CONSTR_GROUND_HANDOVER_ID", length = 22)
public java.lang.Long getConstrGroundHandoverId(){
return constrGroundHandoverId;
}
public void setConstrGroundHandoverId(java.lang.Long constrGroundHandoverId)
{
this.constrGroundHandoverId = constrGroundHandoverId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "HANDOVER_DATE", length = 7)
public java.util.Date getHandoverDate(){
return handoverDate;
}
public void setHandoverDate(java.util.Date handoverDate)
{
this.handoverDate = handoverDate;
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
@Column(name = "A_IN_CHARGE_MONITOR_ID", length = 22)
public java.lang.Long getAInChargeMonitorId(){
return aInChargeMonitorId;
}
public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId)
{
this.aInChargeMonitorId = aInChargeMonitorId;
}
@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBDirectorId(){
return bDirectorId;
}
public void setBDirectorId(java.lang.Long bDirectorId)
{
this.bDirectorId = bDirectorId;
}
@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
public java.lang.Long getBInChargeConstructId(){
return bInChargeConstructId;
}
public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
{
this.bInChargeConstructId = bInChargeConstructId;
}
@Column(name = "GROUND_CURRENT_STATUS", length = 2000)
public java.lang.String getGroundCurrentStatus(){
return groundCurrentStatus;
}
public void setGroundCurrentStatus(java.lang.String groundCurrentStatus)
{
this.groundCurrentStatus = groundCurrentStatus;
}
@Column(name = "BENCHMARK", length = 2000)
public java.lang.String getBenchmark(){
return benchmark;
}
public void setBenchmark(java.lang.String benchmark)
{
this.benchmark = benchmark;
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
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "SIGN_DATE", length = 7)
public java.util.Date getSignDate(){
return signDate;
}
public void setSignDate(java.util.Date signDate)
{
this.signDate = signDate;
}
@Column(name = "SIGN_PLACE", length = 2000)
public java.lang.String getSignPlace(){
return signPlace;
}
public void setSignPlace(java.lang.String signPlace)
{
this.signPlace = signPlace;
}
   

    @Override
    public ConstrGroundHandoverDTO toDTO() {
        ConstrGroundHandoverDTO constrGroundHandoverDTO = new ConstrGroundHandoverDTO();
        //set cac gia tri 
        constrGroundHandoverDTO.setConstructId(this.constructId);
        constrGroundHandoverDTO.setConstrGroundHandoverId(this.constrGroundHandoverId);
        constrGroundHandoverDTO.setCode(this.code);
        constrGroundHandoverDTO.setHandoverDate(this.handoverDate);
        constrGroundHandoverDTO.setADirectorId(this.aDirectorId);
        constrGroundHandoverDTO.setAMonitorId(this.aMonitorId);
        constrGroundHandoverDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
        constrGroundHandoverDTO.setBDirectorId(this.bDirectorId);
        constrGroundHandoverDTO.setBInChargeConstructId(this.bInChargeConstructId);
        constrGroundHandoverDTO.setGroundCurrentStatus(this.groundCurrentStatus);
        constrGroundHandoverDTO.setBenchmark(this.benchmark);
        constrGroundHandoverDTO.setCreatedDate(this.createdDate);
        constrGroundHandoverDTO.setCreatedUserId(this.createdUserId);
        constrGroundHandoverDTO.setApprovalDate(this.approvalDate);
        constrGroundHandoverDTO.setStatusCa(this.statusCa);
        constrGroundHandoverDTO.setIsActive(this.isActive);
        constrGroundHandoverDTO.setSignDate(this.signDate);
        constrGroundHandoverDTO.setSignPlace(this.signPlace);
        return constrGroundHandoverDTO;
    }
}
