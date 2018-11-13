/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ConstrWorkLogsLabelBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "CONSTR_WORK_LOGS_LABELBO")
public class ConstrWorkLogsLabelDTO extends BaseFWDTOImpl<ConstrWorkLogsLabelBO> {

private java.lang.Long constrWoLogsLabId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long constructId;
private java.lang.Long isActive;
private java.lang.String contractCode;

private java.lang.Long aMonitorId;
private java.lang.Long bConstructId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;

private java.lang.String aMonitorPath;
private java.lang.String bConstructPath;
private java.lang.String aDirectorPath;
private java.lang.String bDirectorPath;

private String aDirectorName;
private String aMonitorName;
private String bConstructName;
private String bDirectorName;

private String aDirectorNameSign;
private String aMonitorNameSign;
private String bConstructNameSign;
private String bDirectorNameSign;
private String comments;


public String getComments() {
	return comments;
}



public void setComments(String comments) {
	this.comments = comments;
}



private Long constrCompReMapId;

    @Override
    public ConstrWorkLogsLabelBO toModel() {
        ConstrWorkLogsLabelBO constrWorkLogsLabelBO = new ConstrWorkLogsLabelBO();
        constrWorkLogsLabelBO.setConstrWoLogsLabId(this.constrWoLogsLabId);
        constrWorkLogsLabelBO.setCode(this.code);
        constrWorkLogsLabelBO.setCreatedDate(this.createdDate);
        constrWorkLogsLabelBO.setCreatedUserId(this.createdUserId);
        constrWorkLogsLabelBO.setApprovalDate(this.approvalDate);
        constrWorkLogsLabelBO.setStatusCa(this.statusCa);
        constrWorkLogsLabelBO.setConstructId(this.constructId);
        constrWorkLogsLabelBO.setIsActive(this.isActive);
        constrWorkLogsLabelBO.setAMonitorId(this.aMonitorId);
        constrWorkLogsLabelBO.setBConstructId(this.bConstructId);
        constrWorkLogsLabelBO.setADirectorId(this.aDirectorId);
        constrWorkLogsLabelBO.setBDirectorId(this.bDirectorId);
        return constrWorkLogsLabelBO;
    }

    
    
    public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	@Override
     public Long getFWModelId() {
        return constrWoLogsLabId;
    }
   
    @Override
    public String catchName() {
        return getConstrWoLogsLabId().toString();
    }
    public java.lang.Long getConstrWoLogsLabId(){
    return constrWoLogsLabId;
    }
    public void setConstrWoLogsLabId(java.lang.Long constrWoLogsLabId)
    {
    this.constrWoLogsLabId = constrWoLogsLabId;
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
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getAMonitorId(){
    return aMonitorId;
    }
    public void setAMonitorId(java.lang.Long aMonitorId)
    {
    this.aMonitorId = aMonitorId;
    }
    
    public java.lang.Long getBConstructId(){
    return bConstructId;
    }
    public void setBConstructId(java.lang.Long bConstructId)
    {
    this.bConstructId = bConstructId;
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

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}
	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public String getaDirectorName() {
		return aDirectorName;
	}

	public void setaDirectorName(String aDirectorName) {
		this.aDirectorName = aDirectorName;
	}

	public String getaMonitorName() {
		return aMonitorName;
	}

	public void setaMonitorName(String aMonitorName) {
		this.aMonitorName = aMonitorName;
	}

	public String getbConstructName() {
		return bConstructName;
	}

	public void setbConstructName(String bConstructName) {
		this.bConstructName = bConstructName;
	}

	public String getbDirectorName() {
		return bDirectorName;
	}

	public void setbDirectorName(String bDirectorName) {
		this.bDirectorName = bDirectorName;
	}

	public java.lang.String getaMonitorPath() {
		return aMonitorPath;
	}

	public void setaMonitorPath(java.lang.String aMonitorPath) {
		this.aMonitorPath = aMonitorPath;
	}

	public java.lang.String getbConstructPath() {
		return bConstructPath;
	}

	public void setbConstructPath(java.lang.String bConstructPath) {
		this.bConstructPath = bConstructPath;
	}

	public java.lang.String getaDirectorPath() {
		return aDirectorPath;
	}

	public void setaDirectorPath(java.lang.String aDirectorPath) {
		this.aDirectorPath = aDirectorPath;
	}

	public java.lang.String getbDirectorPath() {
		return bDirectorPath;
	}

	public void setbDirectorPath(java.lang.String bDirectorPath) {
		this.bDirectorPath = bDirectorPath;
	}



	public String getaDirectorNameSign() {
		return aDirectorNameSign;
	}



	public void setaDirectorNameSign(String aDirectorNameSign) {
		this.aDirectorNameSign = aDirectorNameSign;
	}



	public String getaMonitorNameSign() {
		return aMonitorNameSign;
	}



	public void setaMonitorNameSign(String aMonitorNameSign) {
		this.aMonitorNameSign = aMonitorNameSign;
	}



	public String getbConstructNameSign() {
		return bConstructNameSign;
	}



	public void setbConstructNameSign(String bConstructNameSign) {
		this.bConstructNameSign = bConstructNameSign;
	}



	public String getbDirectorNameSign() {
		return bDirectorNameSign;
	}



	public void setbDirectorNameSign(String bDirectorNameSign) {
		this.bDirectorNameSign = bDirectorNameSign;
	}
    
   
}
