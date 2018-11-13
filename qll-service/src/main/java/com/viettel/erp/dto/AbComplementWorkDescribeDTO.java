/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.AbComplementWorkDescribeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_COMPLEMENT_WORK_DESCRIBEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbComplementWorkDescribeDTO extends BaseFWDTOImpl<AbComplementWorkDescribeBO> {

private java.lang.Long abComplementWorkDescribeId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long aInChargeMonitorId;
private java.lang.Long bInChargeConstructId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;



private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;


public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
	return constrCompleteRecordMap;
}

public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
	this.constrCompleteRecordMap = constrCompleteRecordMap;
}


    @Override
    public AbComplementWorkDescribeBO toModel() {
        AbComplementWorkDescribeBO abComplementWorkDescribeBO = new AbComplementWorkDescribeBO();
        abComplementWorkDescribeBO.setAbComplementWorkDescribeId(this.abComplementWorkDescribeId);
        abComplementWorkDescribeBO.setCode(this.code);
        abComplementWorkDescribeBO.setCreatedDate(this.createdDate);
        abComplementWorkDescribeBO.setCreatedUserId(this.createdUserId);
        abComplementWorkDescribeBO.setADirectorId(this.aDirectorId);
        abComplementWorkDescribeBO.setBDirectorId(this.bDirectorId);
        abComplementWorkDescribeBO.setAInChargeMonitorId(this.aInChargeMonitorId);
        abComplementWorkDescribeBO.setBInChargeConstructId(this.bInChargeConstructId);
        abComplementWorkDescribeBO.setStatusCa(this.statusCa);
        abComplementWorkDescribeBO.setDocumentCaId(this.documentCaId);
        abComplementWorkDescribeBO.setIsActive(this.isActive);
        abComplementWorkDescribeBO.setConstructId(this.constructId);
        return abComplementWorkDescribeBO;
    }

    @Override
     public Long getFWModelId() {
        return abComplementWorkDescribeId;
    }
   
    @Override
    public String catchName() {
        return getAbComplementWorkDescribeId().toString();
    }
    public java.lang.Long getAbComplementWorkDescribeId(){
    return abComplementWorkDescribeId;
    }
    public void setAbComplementWorkDescribeId(java.lang.Long abComplementWorkDescribeId)
    {
    this.abComplementWorkDescribeId = abComplementWorkDescribeId;
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
    
    public java.lang.Long getAInChargeMonitorId(){
    return aInChargeMonitorId;
    }
    public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId)
    {
    this.aInChargeMonitorId = aInChargeMonitorId;
    }
    
    public java.lang.Long getBInChargeConstructId(){
    return bInChargeConstructId;
    }
    public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
    {
    this.bInChargeConstructId = bInChargeConstructId;
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
    
   
}
