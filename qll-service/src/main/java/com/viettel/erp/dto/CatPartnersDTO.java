/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.CatPartnersBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_PARTNERSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatPartnersDTO extends BaseFWDTOImpl<CatPartnersBO> {

private java.lang.Long partnerId;
private java.lang.String partnerName;
private java.lang.String address;
private java.lang.String phone;
private java.lang.String description;
private java.lang.Long isActive;
private java.lang.String fax;
private java.lang.String taxCode;
private java.lang.String code;
private java.lang.Long refId;
private java.lang.Long type;
private java.lang.String abbName;
private java.lang.Long groupId;
private java.lang.String accountNumber;
private java.lang.String bussinessNumber;
private java.lang.String delegatePerson;
private java.lang.String title;
private java.lang.Long status;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private java.lang.String proposalNote;
private java.lang.Long acceptorId;
private java.util.Date acceptDate;
private java.lang.String acceptNote;
private java.lang.String rejectNote;
private java.lang.String numDispath;
private java.lang.String numSubmitsion;
private java.lang.Long catPartnerTypeId;
private java.util.Date creatPartnerDate;
private java.lang.String reasonChangePartner;
private java.lang.String delegatePosition;
private java.lang.String identityCard;
private java.lang.String bankName;
private java.lang.String stationCodeExpected;
private java.lang.Long stationIdExpected;
private java.util.Date modifieldDate;
private java.lang.Long catBankBranchId;
private java.lang.Long isLock;
private java.util.Date dateRange;
private java.lang.String issueBy;
private java.lang.String accountHolder;
private java.lang.Long checkPartner;
private java.util.Date dateUpdatePartnersContract;
private java.lang.Boolean setIsSize;
private java.lang.String name;


    @Override
    public CatPartnersBO toModel() {
        CatPartnersBO catPartnersBO = new CatPartnersBO();
        catPartnersBO.setPartnerId(this.partnerId);
        catPartnersBO.setPartnerName(this.partnerName);
        catPartnersBO.setAddress(this.address);
        catPartnersBO.setPhone(this.phone);
        catPartnersBO.setDescription(this.description);
        catPartnersBO.setIsActive(this.isActive);
        catPartnersBO.setFax(this.fax);
        catPartnersBO.setTaxCode(this.taxCode);
        catPartnersBO.setCode(this.code);
        catPartnersBO.setRefId(this.refId);
        catPartnersBO.setType(this.type);
        catPartnersBO.setAbbName(this.abbName);
        catPartnersBO.setGroupId(this.groupId);
        catPartnersBO.setAccountNumber(this.accountNumber);
        catPartnersBO.setBussinessNumber(this.bussinessNumber);
        catPartnersBO.setDelegatePerson(this.delegatePerson);
        catPartnersBO.setTitle(this.title);
        catPartnersBO.setStatus(this.status);
        catPartnersBO.setCreatorId(this.creatorId);
        catPartnersBO.setCreatedDate(this.createdDate);
        catPartnersBO.setProposalNote(this.proposalNote);
        catPartnersBO.setAcceptorId(this.acceptorId);
        catPartnersBO.setAcceptDate(this.acceptDate);
        catPartnersBO.setAcceptNote(this.acceptNote);
        catPartnersBO.setRejectNote(this.rejectNote);
        catPartnersBO.setNumDispath(this.numDispath);
        catPartnersBO.setNumSubmitsion(this.numSubmitsion);
        catPartnersBO.setCatPartnerTypeId(this.catPartnerTypeId);
        catPartnersBO.setCreatPartnerDate(this.creatPartnerDate);
        catPartnersBO.setReasonChangePartner(this.reasonChangePartner);
        catPartnersBO.setDelegatePosition(this.delegatePosition);
        catPartnersBO.setIdentityCard(this.identityCard);
        catPartnersBO.setBankName(this.bankName);
        catPartnersBO.setStationCodeExpected(this.stationCodeExpected);
        catPartnersBO.setStationIdExpected(this.stationIdExpected);
        catPartnersBO.setModifieldDate(this.modifieldDate);
        catPartnersBO.setCatBankBranchId(this.catBankBranchId);
        catPartnersBO.setIsLock(this.isLock);
        catPartnersBO.setDateRange(this.dateRange);
        catPartnersBO.setIssueBy(this.issueBy);
        catPartnersBO.setAccountHolder(this.accountHolder);
        catPartnersBO.setCheckPartner(this.checkPartner);
        catPartnersBO.setDateUpdatePartnersContract(this.dateUpdatePartnersContract);
        return catPartnersBO;
    }

    @Override
     public Long getFWModelId() {
        return partnerId;
    }
   
    @Override
    public String catchName() {
        return getPartnerId().toString();
    }
    public java.lang.Long getPartnerId(){
    return partnerId;
    }
    public void setPartnerId(java.lang.Long partnerId)
    {
    this.partnerId = partnerId;
    }
    
    public java.lang.String getPartnerName(){
    return partnerName;
    }
    public void setPartnerName(java.lang.String partnerName)
    {
    this.partnerName = partnerName;
    }
    
    public java.lang.String getAddress(){
    return address;
    }
    public void setAddress(java.lang.String address)
    {
    this.address = address;
    }
    
    public java.lang.String getPhone(){
    return phone;
    }
    public void setPhone(java.lang.String phone)
    {
    this.phone = phone;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.String getFax(){
    return fax;
    }
    public void setFax(java.lang.String fax)
    {
    this.fax = fax;
    }
    
    public java.lang.String getTaxCode(){
    return taxCode;
    }
    public void setTaxCode(java.lang.String taxCode)
    {
    this.taxCode = taxCode;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getRefId(){
    return refId;
    }
    public void setRefId(java.lang.Long refId)
    {
    this.refId = refId;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.String getAbbName(){
    return abbName;
    }
    public void setAbbName(java.lang.String abbName)
    {
    this.abbName = abbName;
    }
    
    public java.lang.Long getGroupId(){
    return groupId;
    }
    public void setGroupId(java.lang.Long groupId)
    {
    this.groupId = groupId;
    }
    
    public java.lang.String getAccountNumber(){
    return accountNumber;
    }
    public void setAccountNumber(java.lang.String accountNumber)
    {
    this.accountNumber = accountNumber;
    }
    
    public java.lang.String getBussinessNumber(){
    return bussinessNumber;
    }
    public void setBussinessNumber(java.lang.String bussinessNumber)
    {
    this.bussinessNumber = bussinessNumber;
    }
    
    public java.lang.String getDelegatePerson(){
    return delegatePerson;
    }
    public void setDelegatePerson(java.lang.String delegatePerson)
    {
    this.delegatePerson = delegatePerson;
    }
    
    public java.lang.String getTitle(){
    return title;
    }
    public void setTitle(java.lang.String title)
    {
    this.title = title;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
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
    
    public java.lang.String getProposalNote(){
    return proposalNote;
    }
    public void setProposalNote(java.lang.String proposalNote)
    {
    this.proposalNote = proposalNote;
    }
    
    public java.lang.Long getAcceptorId(){
    return acceptorId;
    }
    public void setAcceptorId(java.lang.Long acceptorId)
    {
    this.acceptorId = acceptorId;
    }
    
    public java.util.Date getAcceptDate(){
    return acceptDate;
    }
    public void setAcceptDate(java.util.Date acceptDate)
    {
    this.acceptDate = acceptDate;
    }
    
    public java.lang.String getAcceptNote(){
    return acceptNote;
    }
    public void setAcceptNote(java.lang.String acceptNote)
    {
    this.acceptNote = acceptNote;
    }
    
    public java.lang.String getRejectNote(){
    return rejectNote;
    }
    public void setRejectNote(java.lang.String rejectNote)
    {
    this.rejectNote = rejectNote;
    }
    
    public java.lang.String getNumDispath(){
    return numDispath;
    }
    public void setNumDispath(java.lang.String numDispath)
    {
    this.numDispath = numDispath;
    }
    
    public java.lang.String getNumSubmitsion(){
    return numSubmitsion;
    }
    public void setNumSubmitsion(java.lang.String numSubmitsion)
    {
    this.numSubmitsion = numSubmitsion;
    }
    
    public java.lang.Long getCatPartnerTypeId(){
    return catPartnerTypeId;
    }
    public void setCatPartnerTypeId(java.lang.Long catPartnerTypeId)
    {
    this.catPartnerTypeId = catPartnerTypeId;
    }
    
    public java.util.Date getCreatPartnerDate(){
    return creatPartnerDate;
    }
    public void setCreatPartnerDate(java.util.Date creatPartnerDate)
    {
    this.creatPartnerDate = creatPartnerDate;
    }
    
    public java.lang.String getReasonChangePartner(){
    return reasonChangePartner;
    }
    public void setReasonChangePartner(java.lang.String reasonChangePartner)
    {
    this.reasonChangePartner = reasonChangePartner;
    }
    
    public java.lang.String getDelegatePosition(){
    return delegatePosition;
    }
    public void setDelegatePosition(java.lang.String delegatePosition)
    {
    this.delegatePosition = delegatePosition;
    }
    
    public java.lang.String getIdentityCard(){
    return identityCard;
    }
    public void setIdentityCard(java.lang.String identityCard)
    {
    this.identityCard = identityCard;
    }
    
    public java.lang.String getBankName(){
    return bankName;
    }
    public void setBankName(java.lang.String bankName)
    {
    this.bankName = bankName;
    }
    
    public java.lang.String getStationCodeExpected(){
    return stationCodeExpected;
    }
    public void setStationCodeExpected(java.lang.String stationCodeExpected)
    {
    this.stationCodeExpected = stationCodeExpected;
    }
    
    public java.lang.Long getStationIdExpected(){
    return stationIdExpected;
    }
    public void setStationIdExpected(java.lang.Long stationIdExpected)
    {
    this.stationIdExpected = stationIdExpected;
    }
    
    public java.util.Date getModifieldDate(){
    return modifieldDate;
    }
    public void setModifieldDate(java.util.Date modifieldDate)
    {
    this.modifieldDate = modifieldDate;
    }
    
    public java.lang.Long getCatBankBranchId(){
    return catBankBranchId;
    }
    public void setCatBankBranchId(java.lang.Long catBankBranchId)
    {
    this.catBankBranchId = catBankBranchId;
    }
    
    public java.lang.Long getIsLock(){
    return isLock;
    }
    public void setIsLock(java.lang.Long isLock)
    {
    this.isLock = isLock;
    }
    
    public java.util.Date getDateRange(){
    return dateRange;
    }
    public void setDateRange(java.util.Date dateRange)
    {
    this.dateRange = dateRange;
    }
    
    public java.lang.String getIssueBy(){
    return issueBy;
    }
    public void setIssueBy(java.lang.String issueBy)
    {
    this.issueBy = issueBy;
    }
    
    public java.lang.String getAccountHolder(){
    return accountHolder;
    }
    public void setAccountHolder(java.lang.String accountHolder)
    {
    this.accountHolder = accountHolder;
    }
    
    public java.lang.Long getCheckPartner(){
    return checkPartner;
    }
    public void setCheckPartner(java.lang.Long checkPartner)
    {
    this.checkPartner = checkPartner;
    }
    
    public java.util.Date getDateUpdatePartnersContract(){
    return dateUpdatePartnersContract;
    }
    public void setDateUpdatePartnersContract(java.util.Date dateUpdatePartnersContract)
    {
    this.dateUpdatePartnersContract = dateUpdatePartnersContract;
    }

	public java.lang.Boolean getSetIsSize() {
		return setIsSize;
	}

	public void setSetIsSize(java.lang.Boolean setIsSize) {
		this.setIsSize = setIsSize;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

}
