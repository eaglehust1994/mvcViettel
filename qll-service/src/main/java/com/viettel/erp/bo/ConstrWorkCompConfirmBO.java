/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name="cWorkComplete")
@Table(name = "CONSTR_WORK_COMP_CONFIRM")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrWorkCompConfirmBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long constrWorkCompConfirmId;
private java.lang.String code;
private java.lang.Long aDirectorId;
private java.lang.Long aInChargeMonitorId;
private java.lang.Long bDirectorId;
private java.lang.Long bInChargeConstructId;
private java.util.Date confirmFromDate;
private java.util.Date confirmToDate;
private java.lang.String confirmPlace;
private java.lang.Long conclusion;
private java.util.Date createdDate;
private java.lang.String ohterComments;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.util.Date signDate;
private java.lang.String signPlace;
private List<ConstrWorkCompConfListBO> listConstrWorkCompConfBo= Lists.newArrayList();
 public ConstrWorkCompConfirmBO() {
        setColId("constrWorkCompConfirmId");
        setColName("constrWorkCompConfirmId");
        setUniqueColumn(new String[]{"constrWorkCompConfirmId"});
}

@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}


public ConstrWorkCompConfirmBO(Long constrWorkCompConfirmId) {
	super();
	this.constrWorkCompConfirmId = constrWorkCompConfirmId;
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_WORK_COMP_CONFIRM_SEQ")
            }
    )
@Column(name = "CONSTR_WORK_COMP_CONFIRM_ID", length = 22)
public java.lang.Long getConstrWorkCompConfirmId(){
return constrWorkCompConfirmId;
}
public void setConstrWorkCompConfirmId(java.lang.Long constrWorkCompConfirmId)
{
this.constrWorkCompConfirmId = constrWorkCompConfirmId;
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
@Column(name = "CONFIRM_FROM_DATE", length = 7)
public java.util.Date getConfirmFromDate(){
return confirmFromDate;
}
public void setConfirmFromDate(java.util.Date confirmFromDate)
{
this.confirmFromDate = confirmFromDate;
}
@Column(name = "CONFIRM_TO_DATE", length = 7)
public java.util.Date getConfirmToDate(){
return confirmToDate;
}
public void setConfirmToDate(java.util.Date confirmToDate)
{
this.confirmToDate = confirmToDate;
}
@Column(name = "CONFIRM_PLACE", length = 2000)
public java.lang.String getConfirmPlace(){
return confirmPlace;
}
public void setConfirmPlace(java.lang.String confirmPlace)
{
this.confirmPlace = confirmPlace;
}
@Column(name = "CONCLUSION", length = 22)
public java.lang.Long getConclusion(){
return conclusion;
}
public void setConclusion(java.lang.Long conclusion)
{
this.conclusion = conclusion;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "OHTER_COMMENTS", length = 2000)
public java.lang.String getOhterComments(){
return ohterComments;
}
public void setOhterComments(java.lang.String ohterComments)
{
this.ohterComments = ohterComments;
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
   
@OneToMany(fetch =FetchType.LAZY,mappedBy="constrWorkCompConfirmBO",cascade= CascadeType.ALL)
    public List<ConstrWorkCompConfListBO> getListConstrWorkCompConfBo() {
	return listConstrWorkCompConfBo;
}

public void setListConstrWorkCompConfBo(List<ConstrWorkCompConfListBO> listConstrWorkCompConfBo) {
	this.listConstrWorkCompConfBo = listConstrWorkCompConfBo;
}


@Column(name="SIGN_DATE")
	public java.util.Date getSignDate() {
	return signDate;
}

public void setSignDate(java.util.Date signDate) {
	this.signDate = signDate;
}
@Column(name="SIGN_PLACE")
public java.lang.String getSignPlace() {
	return signPlace;
}

public void setSignPlace(java.lang.String signPlace) {
	this.signPlace = signPlace;
}


	@Override
    public ConstrWorkCompConfirmDTO toDTO() {
        ConstrWorkCompConfirmDTO constrWorkCompConfirmDTO = new ConstrWorkCompConfirmDTO();
        //set cac gia tri 
        constrWorkCompConfirmDTO.setConstructId(this.constructId);
        constrWorkCompConfirmDTO.setConstrWorkCompConfirmId(this.constrWorkCompConfirmId);
        constrWorkCompConfirmDTO.setCode(this.code);
        constrWorkCompConfirmDTO.setADirectorId(this.aDirectorId);
        constrWorkCompConfirmDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
        constrWorkCompConfirmDTO.setBDirectorId(this.bDirectorId);
        constrWorkCompConfirmDTO.setBInChargeConstructId(this.bInChargeConstructId);
        constrWorkCompConfirmDTO.setConfirmFromDate(this.confirmFromDate);
        constrWorkCompConfirmDTO.setConfirmToDate(this.confirmToDate);
        constrWorkCompConfirmDTO.setConfirmPlace(this.confirmPlace);
        constrWorkCompConfirmDTO.setConclusion(this.conclusion);
        constrWorkCompConfirmDTO.setCreatedDate(this.createdDate);
        constrWorkCompConfirmDTO.setOhterComments(this.ohterComments);
        constrWorkCompConfirmDTO.setCreatedUserId(this.createdUserId);
        constrWorkCompConfirmDTO.setApprovalDate(this.approvalDate);
        constrWorkCompConfirmDTO.setStatusCa(this.statusCa);
        constrWorkCompConfirmDTO.setIsActive(this.isActive);
        constrWorkCompConfirmDTO.setSignDate(this.signDate);
        constrWorkCompConfirmDTO.setSignPlace(this.signPlace);
        return constrWorkCompConfirmDTO;
    }
}
