/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_ORGANIZATION_PLAN")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrOrganizationPlanBO extends BaseFWModelImpl {
     
private java.lang.Long constructId;
private java.lang.Long constrOrgPlanId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long representInvestorId;
private java.lang.Long representContractorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;

//private List<UtilAttachedDocumentsBO> utilAttachedDocuments = Lists.newArrayList();



 public ConstrOrganizationPlanBO() {
        setColId("constrOrgPlanId");
        setColName("constrOrgPlanId");
        setUniqueColumn(new String[]{"constrOrgPlanId"});
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
                @Parameter(name = "sequence", value = "CONSTR_ORGANIZATION_PLAN_SEQ")
            }
    )
@Column(name = "CONSTR_ORG_PLAN_ID", length = 22)
public java.lang.Long getConstrOrgPlanId(){
return constrOrgPlanId;
}
public void setConstrOrgPlanId(java.lang.Long constrOrgPlanId)
{
this.constrOrgPlanId = constrOrgPlanId;
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
@Column(name = "REPRESENT_INVESTOR_ID", length = 22)
public java.lang.Long getRepresentInvestorId(){
return representInvestorId;
}
public void setRepresentInvestorId(java.lang.Long representInvestorId)
{
this.representInvestorId = representInvestorId;
}
@Column(name = "REPRESENT_CONTRACTOR_ID", length = 22)
public java.lang.Long getRepresentContractorId(){
return representContractorId;
}
public void setRepresentContractorId(java.lang.Long representContractorId)
{
this.representContractorId = representContractorId;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
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
    public ConstrOrganizationPlanDTO toDTO() {
        ConstrOrganizationPlanDTO constrOrganizationPlanDTO = new ConstrOrganizationPlanDTO();
        //set cac gia tri 
        constrOrganizationPlanDTO.setConstructId(this.constructId);
        constrOrganizationPlanDTO.setConstrOrgPlanId(this.constrOrgPlanId);
        constrOrganizationPlanDTO.setCode(this.code);
        constrOrganizationPlanDTO.setCreatedDate(this.createdDate);
        constrOrganizationPlanDTO.setCreatedUserId(this.createdUserId);
        constrOrganizationPlanDTO.setRepresentInvestorId(this.representInvestorId);
        constrOrganizationPlanDTO.setRepresentContractorId(this.representContractorId);
        constrOrganizationPlanDTO.setStatusCa(this.statusCa);
        constrOrganizationPlanDTO.setDocumentCaId(this.documentCaId);
        constrOrganizationPlanDTO.setIsActive(this.isActive);
        return constrOrganizationPlanDTO;
    }
    
//    @OneToMany(mappedBy = "constrOrganizationPlanBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	public List<UtilAttachedDocumentsBO> getUtilAttachedDocuments() {
//		return utilAttachedDocuments;
//	}
//
//	public void setUtilAttachedDocuments(List<UtilAttachedDocumentsBO> utilAttachedDocuments) {
//		this.utilAttachedDocuments = utilAttachedDocuments;
//	}
    
    
}
