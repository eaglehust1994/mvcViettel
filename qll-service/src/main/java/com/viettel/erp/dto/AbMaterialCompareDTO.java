/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.AbMaterialCompareBO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_MATERIAL_COMPAREBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbMaterialCompareDTO extends BaseFWDTOImpl<AbMaterialCompareBO> {

private java.lang.Long abMaterialCompareId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long aHeadConstructId;
private java.lang.Long aHeadTechnicalId;
private java.lang.Long aHeadFinanceId;
private java.lang.Long bHeadConstructId;
private java.lang.Long bHeadAccountId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

private java.lang.Long parentId;
private UtilAttachedDocumentsDTO utilAttachedDocuments;
private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;
private String path;
private String documentName;
private String documentPath;


    @Override
    public AbMaterialCompareBO toModel() {
        AbMaterialCompareBO abMaterialCompareBO = new AbMaterialCompareBO();
        abMaterialCompareBO.setAbMaterialCompareId(this.abMaterialCompareId);
        abMaterialCompareBO.setCode(this.code);
        abMaterialCompareBO.setCreatedDate(this.createdDate);
        abMaterialCompareBO.setCreatedUserId(this.createdUserId);
        abMaterialCompareBO.setADirectorId(this.aDirectorId);
        abMaterialCompareBO.setBDirectorId(this.bDirectorId);
        abMaterialCompareBO.setAHeadConstructId(this.aHeadConstructId);
        abMaterialCompareBO.setAHeadTechnicalId(this.aHeadTechnicalId);
        abMaterialCompareBO.setAHeadFinanceId(this.aHeadFinanceId);
        abMaterialCompareBO.setBHeadConstructId(this.bHeadConstructId);
        abMaterialCompareBO.setBHeadAccountId(this.bHeadAccountId);
        abMaterialCompareBO.setStatusCa(this.statusCa);
        abMaterialCompareBO.setDocumentCaId(this.documentCaId);
        abMaterialCompareBO.setIsActive(this.isActive);
        abMaterialCompareBO.setConstructId(this.constructId);
        return abMaterialCompareBO;
    }

    @Override
     public Long getFWModelId() {
        return abMaterialCompareId;
    }
   
    
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
    public String catchName() {
        return getAbMaterialCompareId().toString();
    }
    public java.lang.Long getAbMaterialCompareId(){
    return abMaterialCompareId;
    }
    public void setAbMaterialCompareId(java.lang.Long abMaterialCompareId)
    {
    this.abMaterialCompareId = abMaterialCompareId;
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
    
    public java.lang.Long getADirectorId(){
    return aDirectorId;
    }
    public void setADirectorId(java.lang.Long aDirectorId)
    {
    this.aDirectorId = aDirectorId;
    }
    
    public java.lang.Long getBDirectorId(){
    return bDirectorId;
    }
    public void setBDirectorId(java.lang.Long bDirectorId)
    {
    this.bDirectorId = bDirectorId;
    }
    
    public java.lang.Long getAHeadConstructId(){
    return aHeadConstructId;
    }
    public void setAHeadConstructId(java.lang.Long aHeadConstructId)
    {
    this.aHeadConstructId = aHeadConstructId;
    }
    
    public java.lang.Long getAHeadTechnicalId(){
    return aHeadTechnicalId;
    }
    public void setAHeadTechnicalId(java.lang.Long aHeadTechnicalId)
    {
    this.aHeadTechnicalId = aHeadTechnicalId;
    }
    
    public java.lang.Long getAHeadFinanceId(){
    return aHeadFinanceId;
    }
    public void setAHeadFinanceId(java.lang.Long aHeadFinanceId)
    {
    this.aHeadFinanceId = aHeadFinanceId;
    }
    
    public java.lang.Long getBHeadConstructId(){
    return bHeadConstructId;
    }
    public void setBHeadConstructId(java.lang.Long bHeadConstructId)
    {
    this.bHeadConstructId = bHeadConstructId;
    }
    
    public java.lang.Long getBHeadAccountId(){
    return bHeadAccountId;
    }
    public void setBHeadAccountId(java.lang.Long bHeadAccountId)
    {
    this.bHeadAccountId = bHeadAccountId;
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
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }

	public UtilAttachedDocumentsDTO getUtilAttachedDocuments() {
		return utilAttachedDocuments;
	}

	public void setUtilAttachedDocuments(UtilAttachedDocumentsDTO utilAttachedDocuments) {
		this.utilAttachedDocuments = utilAttachedDocuments;
	}

	public java.lang.Long getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public String getDocumentPath() throws Exception {
		if(documentPath!=null){
			documentPath = UEncrypt.encryptFileUploadPath(documentPath);
		}
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
    
}
