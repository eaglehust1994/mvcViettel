/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.DetailSettlementProposalBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "DETAIL_SETTLEMENT_PROPOSALBO")
public class DetailSettlementProposalDTO extends BaseFWDTOImpl<DetailSettlementProposalBO> {

private java.lang.Long constructId;
private java.lang.Long detailSettlementProposalId;
private java.lang.String code;
private java.lang.Long sendPersonId;
private java.lang.Long bRepresentativeId;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long evaluatePersonId;
private java.lang.String evaluateComments;
private java.util.Date evaluateFinishDate;
private java.lang.Long evaluateStatus;
private java.lang.Long isActive;

private java.lang.String constrtCode;
private java.lang.String contractCode;
private java.lang.String contractName;
private java.lang.String constrtName;
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
@JsonSerialize(using = CustomJsonDateSerializer.class)
private java.util.Date signed_date;
private java.lang.String stationAddress;
private java.lang.String constrtAddress;
private java.lang.String fullNameEmployee;
private java.lang.String sendPersonName;
private String createdUserName;
private String provinceName;
private String comments;
public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public String getProvinceName() {
	return provinceName;
}

public void setProvinceName(String provinceName) {
	this.provinceName = provinceName;
}

public String getCreatedUserName() {
	return createdUserName;
}

public void setCreatedUserName(String createdUserName) {
	this.createdUserName = createdUserName;
}

public java.lang.String getSendPersonName() {
	return sendPersonName;
}

public void setSendPersonName(java.lang.String sendPersonName) {
	this.sendPersonName = sendPersonName;
}

public java.lang.String getBrepresentaiveName() {
	return brepresentaiveName;
}

public void setBrepresentaiveName(java.lang.String brepresentaiveName) {
	this.brepresentaiveName = brepresentaiveName;
}

private java.lang.String brepresentaiveName;


public java.lang.String getFullNameEmployee() {
	return fullNameEmployee;
}

public void setFullNameEmployee(java.lang.String fullNameEmployee) {
	this.fullNameEmployee = fullNameEmployee;
}

private Long constrCompReMapId;

	public java.lang.String getConstrtAddress() {
	return constrtAddress;
}

public void setConstrtAddress(java.lang.String constrtAddress) {
	this.constrtAddress = constrtAddress;
}

	public java.lang.String getStationAddress() {
	return stationAddress;
}

public void setStationAddress(java.lang.String stationAddress) {
	this.stationAddress = stationAddress;
}

	public java.util.Date getSigned_date() {
	return signed_date;
}

public void setSigned_date(java.util.Date signed_date) {
	this.signed_date = signed_date;
}

	@Override
    public DetailSettlementProposalBO toModel() {
        DetailSettlementProposalBO detailSettlementProposalBO = new DetailSettlementProposalBO();
        detailSettlementProposalBO.setConstructId(this.constructId);
        detailSettlementProposalBO.setDetailSettlementProposalId(this.detailSettlementProposalId);
        detailSettlementProposalBO.setCode(this.code);
        detailSettlementProposalBO.setSendPersonId(this.sendPersonId);
        detailSettlementProposalBO.setBRepresentativeId(this.bRepresentativeId);
        detailSettlementProposalBO.setCreatedDate(this.createdDate);
        detailSettlementProposalBO.setCreatedUserId(this.createdUserId);
        detailSettlementProposalBO.setApprovalDate(this.approvalDate);
        detailSettlementProposalBO.setStatusCa(this.statusCa);
        detailSettlementProposalBO.setEvaluatePersonId(this.evaluatePersonId);
        detailSettlementProposalBO.setEvaluateComments(this.evaluateComments);
        detailSettlementProposalBO.setEvaluateFinishDate(this.evaluateFinishDate);
        detailSettlementProposalBO.setEvaluateStatus(this.evaluateStatus);
        detailSettlementProposalBO.setIsActive(this.isActive);
        return detailSettlementProposalBO;
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
        return detailSettlementProposalId;
    }
   
    @Override
    public String catchName() {
        return getDetailSettlementProposalId().toString();
    }
    public java.lang.Long getDetailSettlementProposalId(){
    return detailSettlementProposalId;
    }
    public void setDetailSettlementProposalId(java.lang.Long detailSettlementProposalId)
    {
    this.detailSettlementProposalId = detailSettlementProposalId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getSendPersonId(){
    return sendPersonId;
    }
    public void setSendPersonId(java.lang.Long sendPersonId)
    {
    this.sendPersonId = sendPersonId;
    }
    
    public java.lang.Long getBRepresentativeId(){
    return bRepresentativeId;
    }
    public void setBRepresentativeId(java.lang.Long bRepresentativeId)
    {
    this.bRepresentativeId = bRepresentativeId;
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
    
    public java.lang.Long getEvaluatePersonId(){
    return evaluatePersonId;
    }
    public void setEvaluatePersonId(java.lang.Long evaluatePersonId)
    {
    this.evaluatePersonId = evaluatePersonId;
    }
    
    public java.lang.String getEvaluateComments(){
    return evaluateComments;
    }
    public void setEvaluateComments(java.lang.String evaluateComments)
    {
    this.evaluateComments = evaluateComments;
    }
    
    public java.util.Date getEvaluateFinishDate(){
    return evaluateFinishDate;
    }
    public void setEvaluateFinishDate(java.util.Date evaluateFinishDate)
    {
    this.evaluateFinishDate = evaluateFinishDate;
    }
    
    public java.lang.Long getEvaluateStatus(){
    return evaluateStatus;
    }
    public void setEvaluateStatus(java.lang.Long evaluateStatus)
    {
    this.evaluateStatus = evaluateStatus;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }

	public java.lang.String getConstrtCode() {
		return constrtCode;
	}
	
	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}
	
	public java.lang.String getContractCode() {
		return contractCode;
	}
	
	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}
	
	public java.lang.String getContractName() {
		return contractName;
	}
	
	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}
	
	public java.lang.String getConstrtName() {
			return constrtName;
		}

	public void setConstrtName(java.lang.String constrtName) {
			this.constrtName = constrtName;
	}
	
	private List<EstimatesWorkItemsDTO> listAcc = Lists.newArrayList();
    public List<EstimatesWorkItemsDTO> getListAcc() {
	return listAcc;
}

    public void setListAcc(List<EstimatesWorkItemsDTO> listAcc) {
	this.listAcc = listAcc;
}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}
   
    
    
    
}
