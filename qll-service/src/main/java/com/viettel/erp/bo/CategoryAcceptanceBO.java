/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "CATEGORY_ACCEPTANCE")
@SQLDelete(
	    sql = "UPDATE CATEGORY_ACCEPTANCE c SET c.IS_ACTIVE = 0 WHERE c.CATEGORY_ACCEPTANCE_ID = ? ")
@Where( clause = "IS_ACTIVE = '1' OR IS_ACTIVE IS NULL" )
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CategoryAcceptanceBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long categoryAcceptanceId;
private java.lang.String code;
private java.lang.Long aMonitorId;
private java.lang.Long bInChargeConstructId;
private java.util.Date acceptFromDate;
private java.util.Date acceptToDate;
private java.lang.String acceptPlace;
private java.lang.String applyBenchmark;
private java.lang.String constructionQuality;
private java.lang.String otherDocuments;
private java.lang.String otherComments;
private java.lang.Long conclusion;
private java.lang.String completeRequest;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.lang.Long estimatesItemChildId;
private java.lang.String signPlace;
private java.util.Date signDate;

private CategoryAcceptanceModelBO categoryAcceptanceModelBO;
 public CategoryAcceptanceBO() {
        setColId("categoryAcceptanceId");
        setColName("categoryAcceptanceId");
        setUniqueColumn(new String[]{"categoryAcceptanceId"});
}
 
 @OneToOne(mappedBy = "categoryAcceptaceBO",fetch= FetchType.EAGER,cascade=CascadeType.ALL )
 public CategoryAcceptanceModelBO getCategoryAcceptanceModelBO() {
		return categoryAcceptanceModelBO;
	}

	public void setCategoryAcceptanceModelBO(CategoryAcceptanceModelBO categoryAcceptanceModelBO) {
		this.categoryAcceptanceModelBO = categoryAcceptanceModelBO;
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
                @Parameter(name = "sequence", value = "CATEGORY_ACCEPTANCE_SEQ")
            }
    )
@Column(name = "CATEGORY_ACCEPTANCE_ID", length = 22)
public java.lang.Long getCategoryAcceptanceId(){
return categoryAcceptanceId;
}
public void setCategoryAcceptanceId(java.lang.Long categoryAcceptanceId)
{
this.categoryAcceptanceId = categoryAcceptanceId;
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
@Column(name = "ACCEPT_FROM_DATE", length = 7)
public java.util.Date getAcceptFromDate(){
return acceptFromDate;
}
public void setAcceptFromDate(java.util.Date acceptFromDate)
{
this.acceptFromDate = acceptFromDate;
}
@Column(name = "ACCEPT_TO_DATE", length = 7)
public java.util.Date getAcceptToDate(){
return acceptToDate;
}
public void setAcceptToDate(java.util.Date acceptToDate)
{
this.acceptToDate = acceptToDate;
}
@Column(name = "ACCEPT_PLACE", length = 2000)
public java.lang.String getAcceptPlace(){
return acceptPlace;
}
public void setAcceptPlace(java.lang.String acceptPlace)
{
this.acceptPlace = acceptPlace;
}
@Column(name = "APPLY_BENCHMARK", length = 1000)
public java.lang.String getApplyBenchmark(){
return applyBenchmark;
}
public void setApplyBenchmark(java.lang.String applyBenchmark)
{
this.applyBenchmark = applyBenchmark;
}
@Column(name = "CONSTRUCTION_QUALITY", length = 400)
public java.lang.String getConstructionQuality(){
return constructionQuality;
}
public void setConstructionQuality(java.lang.String constructionQuality)
{
this.constructionQuality = constructionQuality;
}
@Column(name = "OTHER_DOCUMENTS", length = 1000)
public java.lang.String getOtherDocuments(){
return otherDocuments;
}
public void setOtherDocuments(java.lang.String otherDocuments)
{
this.otherDocuments = otherDocuments;
}
@Column(name = "OTHER_COMMENTS", length = 2000)
public java.lang.String getOtherComments(){
return otherComments;
}
public void setOtherComments(java.lang.String otherComments)
{
this.otherComments = otherComments;
}
@Column(name = "CONCLUSION", length = 22)
public java.lang.Long getConclusion(){
return conclusion;
}
public void setConclusion(java.lang.Long conclusion)
{
this.conclusion = conclusion;
}
@Column(name = "COMPLETE_REQUEST", length = 2000)
public java.lang.String getCompleteRequest(){
return completeRequest;
}
public void setCompleteRequest(java.lang.String completeRequest)
{
this.completeRequest = completeRequest;
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
@Column(name = "ESTIMATES_ITEM_CHILD_ID", length = 22)
public java.lang.Long getEstimatesItemChildId(){
return estimatesItemChildId;
}
public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId)
{
this.estimatesItemChildId = estimatesItemChildId;
}
@Column(name = "SIGN_PLACE", length = 200)
public java.lang.String getSignPlace() {
return signPlace;
}
public void setSignPlace(java.lang.String signPlace) {
this.signPlace = signPlace;
}
@Column(name = "SIGN_DATE", length = 7)
public java.util.Date getSignDate() {
return signDate;
}
public void setSignDate(java.util.Date signDate) {
this.signDate = signDate;
}


    @Override
    public CategoryAcceptanceDTO toDTO() {
        CategoryAcceptanceDTO categoryAcceptanceDTO = new CategoryAcceptanceDTO();
        //set cac gia tri 
        categoryAcceptanceDTO.setConstructId(this.constructId);
        categoryAcceptanceDTO.setCategoryAcceptanceId(this.categoryAcceptanceId);
        categoryAcceptanceDTO.setCode(this.code);
        categoryAcceptanceDTO.setAMonitorId(this.aMonitorId);
        categoryAcceptanceDTO.setBInChargeConstructId(this.bInChargeConstructId);
        categoryAcceptanceDTO.setAcceptFromDate(this.acceptFromDate);
        categoryAcceptanceDTO.setAcceptToDate(this.acceptToDate);
        categoryAcceptanceDTO.setAcceptPlace(this.acceptPlace);
        categoryAcceptanceDTO.setApplyBenchmark(this.applyBenchmark);
        categoryAcceptanceDTO.setConstructionQuality(this.constructionQuality);
        categoryAcceptanceDTO.setOtherDocuments(this.otherDocuments);
        categoryAcceptanceDTO.setOtherComments(this.otherComments);
        categoryAcceptanceDTO.setConclusion(this.conclusion);
        categoryAcceptanceDTO.setCompleteRequest(this.completeRequest);
        categoryAcceptanceDTO.setCreatedDate(this.createdDate);
        categoryAcceptanceDTO.setCreatedUserId(this.createdUserId);
        categoryAcceptanceDTO.setApprovalDate(this.approvalDate);
        categoryAcceptanceDTO.setStatusCa(this.statusCa);
        categoryAcceptanceDTO.setIsActive(this.isActive);
        categoryAcceptanceDTO.setSignDate(this.signDate);
        categoryAcceptanceDTO.setSignPlace(this.signPlace);
        categoryAcceptanceDTO.setEstimatesItemChildId(this.estimatesItemChildId);
        return categoryAcceptanceDTO;
    }



	
	
}
