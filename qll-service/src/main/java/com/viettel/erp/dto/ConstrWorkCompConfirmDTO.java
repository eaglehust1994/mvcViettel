/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import java.util.List;

import com.viettel.erp.bo.ConstrWorkCompConfListBO;
import com.viettel.erp.bo.ConstrWorkCompConfirmBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_WORK_COMP_CONFIRMBO")
@JsonIgnoreProperties(ignoreUnknown= true)
public class ConstrWorkCompConfirmDTO extends BaseFWDTOImpl<ConstrWorkCompConfirmBO> {

private java.lang.Long constructId;
private java.lang.Long constrWorkCompConfirmId;
private java.lang.String code;
private java.lang.Long aDirectorId;
private java.lang.Long aInChargeMonitorId;
private java.lang.Long bDirectorId;
private java.lang.Long bInChargeConstructId;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date confirmFromDate;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date confirmToDate;
private java.lang.String confirmPlace;
private java.lang.Long conclusion;
private java.util.Date createdDate;
private java.lang.String ohterComments;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
@JsonSerialize(using = CustomJsonDateSerializer.class)
private java.util.Date signDate;
private java.lang.String signPlace;
private ConstrCompleteRecordsMapDTO constrCompleteRecordMap;
private List<ConstrWorkCompConfListDTO> listConstrWorkCompConfDTO = Lists.newArrayList();

    @Override
    public ConstrWorkCompConfirmBO toModel() {
        ConstrWorkCompConfirmBO constrWorkCompConfirmBO = new ConstrWorkCompConfirmBO();
        constrWorkCompConfirmBO.setConstructId(this.constructId);
        constrWorkCompConfirmBO.setConstrWorkCompConfirmId(this.constrWorkCompConfirmId);
        constrWorkCompConfirmBO.setCode(this.code);
        constrWorkCompConfirmBO.setADirectorId(this.aDirectorId);
        constrWorkCompConfirmBO.setAInChargeMonitorId(this.aInChargeMonitorId);
        constrWorkCompConfirmBO.setBDirectorId(this.bDirectorId);
        constrWorkCompConfirmBO.setBInChargeConstructId(this.bInChargeConstructId);
        constrWorkCompConfirmBO.setConfirmFromDate(this.confirmFromDate);
        constrWorkCompConfirmBO.setConfirmToDate(this.confirmToDate);
        constrWorkCompConfirmBO.setConfirmPlace(this.confirmPlace);
        constrWorkCompConfirmBO.setConclusion(this.conclusion);
        constrWorkCompConfirmBO.setCreatedDate(this.createdDate);
        constrWorkCompConfirmBO.setOhterComments(this.ohterComments);
        constrWorkCompConfirmBO.setCreatedUserId(this.createdUserId);
        constrWorkCompConfirmBO.setApprovalDate(this.approvalDate);
        constrWorkCompConfirmBO.setStatusCa(this.statusCa);
        constrWorkCompConfirmBO.setIsActive(this.isActive);
        constrWorkCompConfirmBO.setSignDate(this.signDate);
        constrWorkCompConfirmBO.setSignPlace(this.signPlace);
        for(ConstrWorkCompConfListDTO obj : listConstrWorkCompConfDTO){
        	ConstrWorkCompConfListBO con = obj.toModel();
        	con.setConstrWorkCompConfirmBO(constrWorkCompConfirmBO);
        constrWorkCompConfirmBO.getListConstrWorkCompConfBo().add(con);        	
        }
        return constrWorkCompConfirmBO;
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
        return constrWorkCompConfirmId;
    }
   
    @Override
    public String catchName() {
        return getConstrWorkCompConfirmId().toString();
    }
    public java.lang.Long getConstrWorkCompConfirmId(){
    return constrWorkCompConfirmId;
    }
    public void setConstrWorkCompConfirmId(java.lang.Long constrWorkCompConfirmId)
    {
    this.constrWorkCompConfirmId = constrWorkCompConfirmId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getADirectorId(){
    return aDirectorId;
    }
    public void setADirectorId(java.lang.Long aDirectorId)
    {
    this.aDirectorId = aDirectorId;
    }
    
    public java.lang.Long getAInChargeMonitorId(){
    return aInChargeMonitorId;
    }
    public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId)
    {
    this.aInChargeMonitorId = aInChargeMonitorId;
    }
    
    public java.lang.Long getBDirectorId(){
    return bDirectorId;
    }
    public void setBDirectorId(java.lang.Long bDirectorId)
    {
    this.bDirectorId = bDirectorId;
    }
    
    public java.lang.Long getBInChargeConstructId(){
    return bInChargeConstructId;
    }
    public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
    {
    this.bInChargeConstructId = bInChargeConstructId;
    }
    
    public java.util.Date getConfirmFromDate(){
    return confirmFromDate;
    }
    public void setConfirmFromDate(java.util.Date confirmFromDate)
    {
    this.confirmFromDate = confirmFromDate;
    }
    
    public java.util.Date getConfirmToDate(){
    return confirmToDate;
    }
    public void setConfirmToDate(java.util.Date confirmToDate)
    {
    this.confirmToDate = confirmToDate;
    }
    
    public java.lang.String getConfirmPlace(){
    return confirmPlace;
    }
    public void setConfirmPlace(java.lang.String confirmPlace)
    {
    this.confirmPlace = confirmPlace;
    }
    
    public java.lang.Long getConclusion(){
    return conclusion;
    }
    public void setConclusion(java.lang.Long conclusion)
    {
    this.conclusion = conclusion;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.String getOhterComments(){
    return Strings.nullToEmpty(ohterComments) ;
    }
    public void setOhterComments(java.lang.String ohterComments)
    {
    this.ohterComments = ohterComments;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }

	public List<ConstrWorkCompConfListDTO> getListConstrWorkCompConfDTO() {
		return listConstrWorkCompConfDTO;
	}

	public void setListConstrWorkCompConfDTO(List<ConstrWorkCompConfListDTO> listConstrWorkCompConfDTO) {
		this.listConstrWorkCompConfDTO = listConstrWorkCompConfDTO;
	}

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordMap() {
		return constrCompleteRecordMap;
	}

	public void setConstrCompleteRecordMap(ConstrCompleteRecordsMapDTO constrCompleteRecordMap) {
		this.constrCompleteRecordMap = constrCompleteRecordMap;
	}
	
	
    
   
}
