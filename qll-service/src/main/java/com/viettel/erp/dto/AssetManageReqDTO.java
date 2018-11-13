/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.AssetManageReqBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "ASSET_MANAGE_REQBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetManageReqDTO extends BaseFWDTOImpl<AssetManageReqBO> {

private java.lang.Long manageReqId;
private java.lang.Long reqGroupId;
private java.lang.Long userId;
private java.lang.Long recvGroupId;
private java.lang.String cause;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private java.lang.Long status;
private java.lang.Long type;
private java.lang.Long usedGroupId;
private java.lang.String code;
private java.lang.Long acceptorId;
private java.lang.String acceptComment;
private java.lang.Long retrieveCauseId;
private java.lang.Long constructId;
private java.lang.String delegator;
private java.lang.Long isFull;
private java.lang.String titleDelegator;
private java.lang.String mobileNumber;
private java.lang.String document;
private java.lang.Long failCheckId;
private java.lang.Long deliveryNoteId;
private java.lang.Long isMaintain;
private java.lang.String causeReject;
private java.lang.Long retrieveType;
private java.lang.Long assetType;
private java.lang.Long retrieveCmdId;
private java.lang.Long planSwapId;
private java.lang.Long certificateRegistrationId;
private java.lang.Long partnerId;
private java.lang.Long contractId;
private java.lang.Long statusCa;
private java.lang.String commentCa;
private java.lang.Long documentCaId;
private java.lang.Long typeManage;

private java.lang.Long recoveryMaterialValue;
private java.lang.Long exportMaterialValue;
private java.lang.Long valueProposed;
private java.lang.Long valueOutProposed;
private java.lang.Long acceptMaterialValue;
private java.lang.Long unrecoveryMaterialValue;
private java.lang.Long lostMaterialValue;
private java.lang.Long contractTotalValue;
private java.lang.Long valueFinalizationContractors;
private java.lang.Long paidValue;
private java.lang.Long totalPrice;
private java.lang.String constructName;
private java.lang.String stationcode;
private java.lang.String constructAddress;
private java.lang.String contractCode;
private java.lang.String investProjectName;
private java.lang.Long date;
private java.lang.Long month;
private java.lang.String year;
private java.lang.Long valueResidual;
private java.lang.Long valueDeductionSupplies;
private java.lang.Long sumSettlementConstruction;
private java.lang.String aDirectorIdPath;
private java.lang.String bDirectorIdPath;

public java.lang.String getaDirectorIdPath() {
	return aDirectorIdPath;
}

public void setaDirectorIdPath(java.lang.String aDirectorIdPath) {
	this.aDirectorIdPath = aDirectorIdPath;
}

public java.lang.String getbDirectorIdPath() {
	return bDirectorIdPath;
}

public void setbDirectorIdPath(java.lang.String bDirectorIdPath) {
	this.bDirectorIdPath = bDirectorIdPath;
}

public java.lang.Long getSumSettlementConstruction() {
	return sumSettlementConstruction;
}

public void setSumSettlementConstruction(java.lang.Long sumSettlementConstruction) {
	this.sumSettlementConstruction = sumSettlementConstruction;
}

	@Override
    public AssetManageReqBO toModel() {
        AssetManageReqBO assetManageReqBO = new AssetManageReqBO();
        assetManageReqBO.setManageReqId(this.manageReqId);
        assetManageReqBO.setReqGroupId(this.reqGroupId);
        assetManageReqBO.setUserId(this.userId);
        assetManageReqBO.setRecvGroupId(this.recvGroupId);
        assetManageReqBO.setCause(this.cause);
        assetManageReqBO.setCreatorId(this.creatorId);
        assetManageReqBO.setCreatedDate(this.createdDate);
        assetManageReqBO.setStatus(this.status);
        assetManageReqBO.setType(this.type);
        assetManageReqBO.setUsedGroupId(this.usedGroupId);
        assetManageReqBO.setCode(this.code);
        assetManageReqBO.setAcceptorId(this.acceptorId);
        assetManageReqBO.setAcceptComment(this.acceptComment);
        assetManageReqBO.setRetrieveCauseId(this.retrieveCauseId);
        assetManageReqBO.setConstructId(this.constructId);
        assetManageReqBO.setDelegator(this.delegator);
        assetManageReqBO.setIsFull(this.isFull);
        assetManageReqBO.setTitleDelegator(this.titleDelegator);
        assetManageReqBO.setMobileNumber(this.mobileNumber);
        assetManageReqBO.setDocument(this.document);
        assetManageReqBO.setFailCheckId(this.failCheckId);
        assetManageReqBO.setDeliveryNoteId(this.deliveryNoteId);
        assetManageReqBO.setIsMaintain(this.isMaintain);
        assetManageReqBO.setCauseReject(this.causeReject);
        assetManageReqBO.setRetrieveType(this.retrieveType);
        assetManageReqBO.setAssetType(this.assetType);
        assetManageReqBO.setRetrieveCmdId(this.retrieveCmdId);
        assetManageReqBO.setPlanSwapId(this.planSwapId);
        assetManageReqBO.setCertificateRegistrationId(this.certificateRegistrationId);
        assetManageReqBO.setPartnerId(this.partnerId);
        assetManageReqBO.setContractId(this.contractId);
        assetManageReqBO.setStatusCa(this.statusCa);
        assetManageReqBO.setCommentCa(this.commentCa);
        assetManageReqBO.setDocumentCaId(this.documentCaId);
        assetManageReqBO.setTypeManage(this.typeManage);
        return assetManageReqBO;
    }

    public java.lang.Long getManageReqId(){
    return manageReqId;
    }
    public void setManageReqId(java.lang.Long manageReqId)
    {
    this.manageReqId = manageReqId;
    }
    
    public java.lang.Long getReqGroupId(){
    return reqGroupId;
    }
    public void setReqGroupId(java.lang.Long reqGroupId)
    {
    this.reqGroupId = reqGroupId;
    }
    
    public java.lang.Long getUserId(){
    return userId;
    }
    public void setUserId(java.lang.Long userId)
    {
    this.userId = userId;
    }
    
    public java.lang.Long getRecvGroupId(){
    return recvGroupId;
    }
    public void setRecvGroupId(java.lang.Long recvGroupId)
    {
    this.recvGroupId = recvGroupId;
    }
    
    public java.lang.String getCause(){
    return cause;
    }
    public void setCause(java.lang.String cause)
    {
    this.cause = cause;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.Long getUsedGroupId(){
    return usedGroupId;
    }
    public void setUsedGroupId(java.lang.Long usedGroupId)
    {
    this.usedGroupId = usedGroupId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getAcceptorId(){
    return acceptorId;
    }
    public void setAcceptorId(java.lang.Long acceptorId)
    {
    this.acceptorId = acceptorId;
    }
    
    public java.lang.String getAcceptComment(){
    return acceptComment;
    }
    public void setAcceptComment(java.lang.String acceptComment)
    {
    this.acceptComment = acceptComment;
    }
    
    public java.lang.Long getRetrieveCauseId(){
    return retrieveCauseId;
    }
    public void setRetrieveCauseId(java.lang.Long retrieveCauseId)
    {
    this.retrieveCauseId = retrieveCauseId;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.String getDelegator(){
    return delegator;
    }
    public void setDelegator(java.lang.String delegator)
    {
    this.delegator = delegator;
    }
    
    public java.lang.Long getIsFull(){
    return isFull;
    }
    public void setIsFull(java.lang.Long isFull)
    {
    this.isFull = isFull;
    }
    
    public java.lang.String getTitleDelegator(){
    return titleDelegator;
    }
    public void setTitleDelegator(java.lang.String titleDelegator)
    {
    this.titleDelegator = titleDelegator;
    }
    
    public java.lang.String getMobileNumber(){
    return mobileNumber;
    }
    public void setMobileNumber(java.lang.String mobileNumber)
    {
    this.mobileNumber = mobileNumber;
    }
    
    public java.lang.String getDocument(){
    return document;
    }
    public void setDocument(java.lang.String document)
    {
    this.document = document;
    }
    
    public java.lang.Long getFailCheckId(){
    return failCheckId;
    }
    public void setFailCheckId(java.lang.Long failCheckId)
    {
    this.failCheckId = failCheckId;
    }
    
    public java.lang.Long getDeliveryNoteId(){
    return deliveryNoteId;
    }
    public void setDeliveryNoteId(java.lang.Long deliveryNoteId)
    {
    this.deliveryNoteId = deliveryNoteId;
    }
    
    public java.lang.Long getIsMaintain(){
    return isMaintain;
    }
    public void setIsMaintain(java.lang.Long isMaintain)
    {
    this.isMaintain = isMaintain;
    }
    
    public java.lang.String getCauseReject(){
    return causeReject;
    }
    public void setCauseReject(java.lang.String causeReject)
    {
    this.causeReject = causeReject;
    }
    
    public java.lang.Long getRetrieveType(){
    return retrieveType;
    }
    public void setRetrieveType(java.lang.Long retrieveType)
    {
    this.retrieveType = retrieveType;
    }
    
    public java.lang.Long getAssetType(){
    return assetType;
    }
    public void setAssetType(java.lang.Long assetType)
    {
    this.assetType = assetType;
    }
    
    public java.lang.Long getRetrieveCmdId(){
    return retrieveCmdId;
    }
    public void setRetrieveCmdId(java.lang.Long retrieveCmdId)
    {
    this.retrieveCmdId = retrieveCmdId;
    }
    
    public java.lang.Long getPlanSwapId(){
    return planSwapId;
    }
    public void setPlanSwapId(java.lang.Long planSwapId)
    {
    this.planSwapId = planSwapId;
    }
    
    public java.lang.Long getCertificateRegistrationId(){
    return certificateRegistrationId;
    }
    public void setCertificateRegistrationId(java.lang.Long certificateRegistrationId)
    {
    this.certificateRegistrationId = certificateRegistrationId;
    }
    
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.String getCommentCa(){
    return commentCa;
    }
    public void setCommentCa(java.lang.String commentCa)
    {
    this.commentCa = commentCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.Long getTypeManage(){
    return typeManage;
    }
    public void setTypeManage(java.lang.Long typeManage)
    {
    this.typeManage = typeManage;
    }

	@Override
	public String catchName() {
		return getManageReqId().toString();
	}

	@Override
	public Long getFWModelId() {
		return manageReqId;
	}

	public java.lang.Long getValueProposed() {
		return valueProposed;
	}

	public void setValueProposed(java.lang.Long valueProposed) {
		this.valueProposed = valueProposed;
	}

	public java.lang.Long getValueOutProposed() {
		return valueOutProposed;
	}

	public void setValueOutProposed(java.lang.Long valueOutProposed) {
		this.valueOutProposed = valueOutProposed;
	}


	public java.lang.Long getContractTotalValue() {
		return contractTotalValue;
	}

	public void setContractTotalValue(java.lang.Long contractTotalValue) {
		this.contractTotalValue = contractTotalValue;
	}

	public java.lang.Long getValueFinalizationContractors() {
		return valueFinalizationContractors;
	}

	public void setValueFinalizationContractors(java.lang.Long valueFinalizationContractors) {
		this.valueFinalizationContractors = valueFinalizationContractors;
	}

	public java.lang.Long getPaidValue() {
		return paidValue;
	}

	public void setPaidValue(java.lang.Long paidValue) {
		this.paidValue = paidValue;
	}
	
	public java.lang.String getConstructName() {
		return constructName;
	}

	public void setConstructName(java.lang.String constructName) {
		this.constructName = constructName;
	}

	public java.lang.String getStationcode() {
		return stationcode;
	}

	public void setStationcode(java.lang.String stationcode) {
		this.stationcode = stationcode;
	}

	public java.lang.String getConstructAddress() {
		return constructAddress;
	}

	public void setConstructAddress(java.lang.String constructAddress) {
		this.constructAddress = constructAddress;
	}

	public java.lang.String getContractCode() {
		return contractCode;
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.Long getRecoveryMaterialValue() {
		return recoveryMaterialValue;
	}

	public void setRecoveryMaterialValue(java.lang.Long recoveryMaterialValue) {
		this.recoveryMaterialValue = recoveryMaterialValue;
	}

	public java.lang.Long getExportMaterialValue() {
		return exportMaterialValue;
	}

	public void setExportMaterialValue(java.lang.Long exportMaterialValue) {
		this.exportMaterialValue = exportMaterialValue;
	}

	public java.lang.Long getAcceptMaterialValue() {
		return acceptMaterialValue;
	}

	public void setAcceptMaterialValue(java.lang.Long acceptMaterialValue) {
		this.acceptMaterialValue = acceptMaterialValue;
	}

	public java.lang.Long getUnrecoveryMaterialValue() {
		return unrecoveryMaterialValue;
	}

	public void setUnrecoveryMaterialValue(java.lang.Long unrecoveryMaterialValue) {
		this.unrecoveryMaterialValue = unrecoveryMaterialValue;
	}

	public java.lang.Long getLostMaterialValue() {
		return lostMaterialValue;
	}

	public void setLostMaterialValue(java.lang.Long lostMaterialValue) {
		this.lostMaterialValue = lostMaterialValue;
	}

	public java.lang.Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(java.lang.Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public java.lang.String getInvestProjectName() {
		return investProjectName;
	}

	public void setInvestProjectName(java.lang.String investProjectName) {
		this.investProjectName = investProjectName;
	}

	public java.lang.Long getDate() {
		return date;
	}
	
	public void setDate(java.lang.Long date) {
		this.date = date;
	}
	
	public java.lang.Long getMonth() {
		return month;
	}
	
	public void setMonth(java.lang.Long month) {
		this.month = month;
	}
	
	public java.lang.String getYear() {
		return year;
	}
	
	public void setYear(java.lang.String year) {
		this.year = year;
	}
	

	public java.lang.Long getValueResidual() {
	return valueResidual;
}

	public void setValueResidual(java.lang.Long valueResidual) {
		this.valueResidual = valueResidual;
	}
	
	public java.lang.Long getValueDeductionSupplies() {
		return valueDeductionSupplies;
	}
	
	public void setValueDeductionSupplies(java.lang.Long valueDeductionSupplies) {
		this.valueDeductionSupplies = valueDeductionSupplies;
	}
	
}
