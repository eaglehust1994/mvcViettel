/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "bmaterialAcceptance")
@Table(name = "B_MATERIAL_ACCEPTANCE")
//dinh dang truong is_active trong DB de remove
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = " UPDATE B_MATERIAL_ACCEPTANCE bma set bma.IS_ACTIVE = 0 WHERE BMA.B_MATERIAL_ACCEPTANCE_ID = ? ")
@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class BMaterialAcceptanceBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long bMaterialAcceptanceId;
private java.lang.String code;
private java.lang.Long aMonitorId;
private java.lang.Long bInChargeConstructId;
private java.lang.String acceptanceBase;
private java.lang.Long conclusion;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.util.Date signDate;
private java.lang.String signPlace;

private List<BMaterialAcceptMerListBO> bMaterialAcceptMerList = Lists.newArrayList();


@OneToMany(mappedBy = "bMaterialAcceptance", cascade = CascadeType.ALL)
 public List<BMaterialAcceptMerListBO> getbMaterialAcceptMerList() {
	return bMaterialAcceptMerList;
}

public void setbMaterialAcceptMerList(List<BMaterialAcceptMerListBO> bMaterialAcceptMerList) {
	this.bMaterialAcceptMerList = bMaterialAcceptMerList;
}

public BMaterialAcceptanceBO() {
        setColId("bMaterialAcceptanceId");
        setColName("bMaterialAcceptanceId");
        setUniqueColumn(new String[]{"bMaterialAcceptanceId"});
}

public BMaterialAcceptanceBO(Long bMaterialAcceptanceId2) {
    this.bMaterialAcceptanceId= bMaterialAcceptanceId2;
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
                @Parameter(name = "sequence", value = "B_MATERIAL_ACCEPTANCE_SEQ")
            }
    )
@Column(name = "B_MATERIAL_ACCEPTANCE_ID", length = 22)
public java.lang.Long getBMaterialAcceptanceId(){
return bMaterialAcceptanceId;
}
public void setBMaterialAcceptanceId(java.lang.Long bMaterialAcceptanceId)
{
this.bMaterialAcceptanceId = bMaterialAcceptanceId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "A_MONITOR_ID", length = 22)
public java.lang.Long getAMonitorId(){
return aMonitorId;
}
public void setAMonitorId(java.lang.Long aMonitorId)
{
this.aMonitorId = aMonitorId;
}
@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
public java.lang.Long getBInChargeConstructId(){
return bInChargeConstructId;
}
public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
{
this.bInChargeConstructId = bInChargeConstructId;
}
@Column(name = "ACCEPTANCE_BASE", length = 1000)
public java.lang.String getAcceptanceBase(){
return acceptanceBase;
}
public void setAcceptanceBase(java.lang.String acceptanceBase)
{
this.acceptanceBase = acceptanceBase;
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
    public BMaterialAcceptanceDTO toDTO() {
        BMaterialAcceptanceDTO bMaterialAcceptanceDTO = new BMaterialAcceptanceDTO();
        //set cac gia tri 
        
        bMaterialAcceptanceDTO.setConstructId(this.constructId);    
        bMaterialAcceptanceDTO.setBmaterialAcceptanceId(this.bMaterialAcceptanceId);
        bMaterialAcceptanceDTO.setCode(this.code);
        bMaterialAcceptanceDTO.setAmonitorId(this.aMonitorId);
        bMaterialAcceptanceDTO.setBinChargeConstructId(this.bInChargeConstructId);
        bMaterialAcceptanceDTO.setAcceptanceBase(this.acceptanceBase);
        bMaterialAcceptanceDTO.setConclusion(this.conclusion);
        bMaterialAcceptanceDTO.setCreatedDate(this.createdDate);
        bMaterialAcceptanceDTO.setCreatedUserId(this.createdUserId);
        bMaterialAcceptanceDTO.setApprovalDate(this.approvalDate);
        bMaterialAcceptanceDTO.setStatusCa(this.statusCa);
        bMaterialAcceptanceDTO.setIsActive(this.isActive);
        bMaterialAcceptanceDTO.setSignDate(this.signDate);
        bMaterialAcceptanceDTO.setSignPlace(this.signPlace);
        return bMaterialAcceptanceDTO;
    }
}
