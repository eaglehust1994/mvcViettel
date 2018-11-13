/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.DetailSettlementEvaluateBO;
import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;
/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "DETAIL_SETTLEMENT_EVALUATEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailSettlementEvaluateDTO extends BaseFWDTOImpl<DetailSettlementEvaluateBO> {

private java.lang.Long constructId;
private java.lang.Long detailSettlementEvaluateId;
private java.lang.String code;
private java.lang.Long createdEvaluatePerId;
private java.lang.Long checkEvaluatePerId;
private java.lang.Long sendPersonId;
private java.lang.Long bRepresentativeId;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;
private java.lang.String createdEvaluatePerName;
private java.lang.String checkEvaluatePerName;
private java.lang.String sendPersonName;
private java.lang.String brepresentativeName;
private java.lang.String contractCode;
private java.lang.String contractName;
private java.lang.String constrtCode;
private java.lang.String constrtName;
private java.lang.String constrtAddress;
private java.lang.String stationAddress;
private java.lang.Long aDirectorId;
private String aDirectorName;
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

public String getaDirectorName() {
	return aDirectorName;
}

public void setaDirectorName(String aDirectorName) {
	this.aDirectorName = aDirectorName;
}

public java.lang.Long getaDirectorId() {
	return aDirectorId;
}

public void setaDirectorId(java.lang.Long aDirectorId) {
	this.aDirectorId = aDirectorId;
}


private String evaluateComments;
public String getEvaluateComments() {
	return evaluateComments;
}

public void setEvaluateComments(String evaluateComments) {
	this.evaluateComments = evaluateComments;
}


private Long constrCompReMapId;

private List<java.lang.Long> listEstimatesWorkItemId= Lists.newArrayList();
public List<java.lang.Long> getListEstimatesWorkItemId() {
	return listEstimatesWorkItemId;
}

public void setListEstimatesWorkItemId(List<java.lang.Long> listEstimatesWorkItemId) {
	this.listEstimatesWorkItemId = listEstimatesWorkItemId;
}

public Long getConstrCompReMapId() {
	return constrCompReMapId;
}

public void setConstrCompReMapId(Long constrCompReMapId) {
	this.constrCompReMapId = constrCompReMapId;
}


@JsonDeserialize(using = CustomJsonDateDeserializer.class)
@JsonSerialize(using = CustomJsonDateSerializer.class)
private java.util.Date signed_date;

public java.util.Date getSigned_date() {
	return signed_date;
}

public void setSigned_date(java.util.Date signed_date) {
	this.signed_date = signed_date;
}

public java.lang.String getStationAddress() {
	return stationAddress;
}

public void setStationAddress(java.lang.String stationAddress) {
	this.stationAddress = stationAddress;
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

public java.lang.String getConstrtCode() {
	return constrtCode;
}

public void setConstrtCode(java.lang.String constrtCode) {
	this.constrtCode = constrtCode;
}

public java.lang.String getConstrtName() {
	return constrtName;
}

public void setConstrtName(java.lang.String constrtName) {
	this.constrtName = constrtName;
}

public java.lang.String getConstrtAddress() {
	return constrtAddress;
}

public void setConstrtAddress(java.lang.String constrtAddress) {
	this.constrtAddress = constrtAddress;
}

private List<EstimatesDetailAnalystDTO> listEstDetail = Lists.newArrayList();
public List<EstimatesDetailAnalystDTO> getListEstDetail() {
	return listEstDetail;
}

public void setListEstDetail(List<EstimatesDetailAnalystDTO> listEstDetail) {
	this.listEstDetail = listEstDetail;
}


private List<EstimatesWorkItemsDTO> listAcc = Lists.newArrayList();
    public List<EstimatesWorkItemsDTO> getListAcc() {
	return listAcc;
}

public void setListAcc(List<EstimatesWorkItemsDTO> listAcc) {
	this.listAcc = listAcc;
}

	@Override
    public DetailSettlementEvaluateBO toModel() {
        DetailSettlementEvaluateBO detailSettlementEvaluateBO = new DetailSettlementEvaluateBO();
        detailSettlementEvaluateBO.setConstructId(this.constructId);
        detailSettlementEvaluateBO.setDetailSettlementEvaluateId(this.detailSettlementEvaluateId);
        detailSettlementEvaluateBO.setCode(this.code);
        detailSettlementEvaluateBO.setCreatedEvaluatePerId(this.createdEvaluatePerId);
        detailSettlementEvaluateBO.setCheckEvaluatePerId(this.checkEvaluatePerId);
        detailSettlementEvaluateBO.setSendPersonId(this.sendPersonId);
        detailSettlementEvaluateBO.setBRepresentativeId(this.bRepresentativeId);
        detailSettlementEvaluateBO.setCreatedDate(this.createdDate);
        detailSettlementEvaluateBO.setCreatedUserId(this.createdUserId);
        detailSettlementEvaluateBO.setApprovalDate(this.approvalDate);
        detailSettlementEvaluateBO.setStatusCa(this.statusCa);
        detailSettlementEvaluateBO.setIsActive(this.isActive);
        detailSettlementEvaluateBO.setaDirectorId(this.aDirectorId);
        return detailSettlementEvaluateBO;
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
        return detailSettlementEvaluateId;
    }
   
    @Override
    public String catchName() {
        return getDetailSettlementEvaluateId().toString();
    }
    public java.lang.Long getDetailSettlementEvaluateId(){
    return detailSettlementEvaluateId;
    }
    public void setDetailSettlementEvaluateId(java.lang.Long detailSettlementEvaluateId)
    {
    this.detailSettlementEvaluateId = detailSettlementEvaluateId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getCreatedEvaluatePerId(){
    return createdEvaluatePerId;
    }
    public void setCreatedEvaluatePerId(java.lang.Long createdEvaluatePerId)
    {
    this.createdEvaluatePerId = createdEvaluatePerId;
    }
    
    public java.lang.Long getCheckEvaluatePerId(){
    return checkEvaluatePerId;
    }
    public void setCheckEvaluatePerId(java.lang.Long checkEvaluatePerId)
    {
    this.checkEvaluatePerId = checkEvaluatePerId;
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
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.String getCreatedEvaluatePerName() {
    	return createdEvaluatePerName;
    }

    public void setCreatedEvaluatePerName(java.lang.String createdEvaluatePerName) {
    	this.createdEvaluatePerName = createdEvaluatePerName;
    }

    public java.lang.String getCheckEvaluatePerName() {
    	return checkEvaluatePerName;
    }

    public void setCheckEvaluatePerName(java.lang.String checkEvaluatePerName) {
    	this.checkEvaluatePerName = checkEvaluatePerName;
    }

    public java.lang.String getSendPersonName() {
    	return sendPersonName;
    }

    public void setSendPersonName(java.lang.String sendPersonName) {
    	this.sendPersonName = sendPersonName;
    }

    public java.lang.String getBrepresentativeName() {
    	return brepresentativeName;
    }

    public void setBrepresentativeName(java.lang.String brepresentativeName) {
    	this.brepresentativeName = brepresentativeName;
    }
   
}
