/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrOrganizationPlanBO;
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "CONSTR_ORGANIZATION_PLANBO")
public class ConstrOrganizationPlanDTO extends BaseFWDTOImpl<ConstrOrganizationPlanBO> {

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

private String creatOrUpdate;
private String fullName;
private Long constrCompReMapId;

private String documentPath;
private String documentName;
private List<String> listDocumentName;
private List<String> listDocumentNameEx;

private java.lang.Double contractId;

private java.lang.String comments;

public java.lang.String getComments() {
	return comments;
}

public void setComments(java.lang.String comments) {
	this.comments = comments;
}

    @Override
    public ConstrOrganizationPlanBO toModel() {
        ConstrOrganizationPlanBO constrOrganizationPlanBO = new ConstrOrganizationPlanBO();
        constrOrganizationPlanBO.setConstructId(this.constructId);
        constrOrganizationPlanBO.setConstrOrgPlanId(this.constrOrgPlanId);
        constrOrganizationPlanBO.setCode(this.code);
        constrOrganizationPlanBO.setCreatedDate(this.createdDate);
        constrOrganizationPlanBO.setCreatedUserId(this.createdUserId);
        constrOrganizationPlanBO.setRepresentInvestorId(this.representInvestorId);
        constrOrganizationPlanBO.setRepresentContractorId(this.representContractorId);
        constrOrganizationPlanBO.setStatusCa(this.statusCa);
        constrOrganizationPlanBO.setDocumentCaId(this.documentCaId);
        constrOrganizationPlanBO.setIsActive(this.isActive);
        return constrOrganizationPlanBO;
    }

    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    @Override
     public Long getFWModelId() {
        return constrOrgPlanId;
    }
   
    @Override
    public String catchName() {
        return getConstrOrgPlanId().toString();
    }
    public java.lang.Long getConstrOrgPlanId(){
    return constrOrgPlanId;
    }
    public void setConstrOrgPlanId(java.lang.Long constrOrgPlanId)
    {
    this.constrOrgPlanId = constrOrgPlanId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
    public java.lang.Long getRepresentInvestorId(){
    return representInvestorId;
    }
    public void setRepresentInvestorId(java.lang.Long representInvestorId)
    {
    this.representInvestorId = representInvestorId;
    }
    
    public java.lang.Long getRepresentContractorId(){
    return representContractorId;
    }
    public void setRepresentContractorId(java.lang.Long representContractorId)
    {
    this.representContractorId = representContractorId;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCreatOrUpdate() {
		return creatOrUpdate;
	}

	public void setCreatOrUpdate(String creatOrUpdate) {
		this.creatOrUpdate = creatOrUpdate;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public List<String> getListDocumentName() {
		return listDocumentName;
	}

	public void setListDocumentName(List<String> listDocumentName) {
		this.listDocumentName = listDocumentName;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public List<String> getListDocumentNameEx() {
		return listDocumentNameEx;
	}

	public void setListDocumentNameEx(List<String> listDocumentNameEx) {
		this.listDocumentNameEx = listDocumentNameEx;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}

	
	
    
   
}
