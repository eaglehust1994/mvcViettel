/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "cat_partners")
@Table(name = "CAT_PARTNERS")
@DynamicInsert
@DynamicUpdate
@SQLDelete(
	    sql = "UPDATE CAT_PARTNERS c SET c.IS_ACTIVE = 1 WHERE c.PARTNER_ID = ? ")
//@Where( clause = "IS_ACTIVE = '1'" )
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="erp-cache")

/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatPartnersBO extends BaseFWModelImpl {
     
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

 public CatPartnersBO() {
        setColId("partnerId");
        setColName("partnerId");
        setUniqueColumn(new String[]{"partnerId"});
}
 public CatPartnersBO(Long partnerId) {
        this.partnerId  = partnerId;
} 

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_PARTNERS_SEQ")
            }
    )
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "PARTNER_NAME", length = 1400)
public java.lang.String getPartnerName(){
return partnerName;
}
public void setPartnerName(java.lang.String partnerName)
{
this.partnerName = partnerName;
}
@Column(name = "ADDRESS", length = 1000)
public java.lang.String getAddress(){
return address;
}
public void setAddress(java.lang.String address)
{
this.address = address;
}
@Column(name = "PHONE", length = 60)
public java.lang.String getPhone(){
return phone;
}
public void setPhone(java.lang.String phone)
{
this.phone = phone;
}
@Column(name = "DESCRIPTION", length = 1000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "FAX", length = 60)
public java.lang.String getFax(){
return fax;
}
public void setFax(java.lang.String fax)
{
this.fax = fax;
}
@Column(name = "TAX_CODE", length = 100)
public java.lang.String getTaxCode(){
return taxCode;
}
public void setTaxCode(java.lang.String taxCode)
{
this.taxCode = taxCode;
}
@Column(name = "CODE", length = 200)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "REF_ID", length = 22)
public java.lang.Long getRefId(){
return refId;
}
public void setRefId(java.lang.Long refId)
{
this.refId = refId;
}
@Column(name = "TYPE", length = 22)
public java.lang.Long getType(){
return type;
}
public void setType(java.lang.Long type)
{
this.type = type;
}
@Column(name = "ABB_NAME", length = 200)
public java.lang.String getAbbName(){
return abbName;
}
public void setAbbName(java.lang.String abbName)
{
this.abbName = abbName;
}
@Column(name = "GROUP_ID", length = 22)
public java.lang.Long getGroupId(){
return groupId;
}
public void setGroupId(java.lang.Long groupId)
{
this.groupId = groupId;
}
@Column(name = "ACCOUNT_NUMBER", length = 200)
public java.lang.String getAccountNumber(){
return accountNumber;
}
public void setAccountNumber(java.lang.String accountNumber)
{
this.accountNumber = accountNumber;
}
@Column(name = "BUSSINESS_NUMBER", length = 100)
public java.lang.String getBussinessNumber(){
return bussinessNumber;
}
public void setBussinessNumber(java.lang.String bussinessNumber)
{
this.bussinessNumber = bussinessNumber;
}
@Column(name = "DELEGATE_PERSON", length = 120)
public java.lang.String getDelegatePerson(){
return delegatePerson;
}
public void setDelegatePerson(java.lang.String delegatePerson)
{
this.delegatePerson = delegatePerson;
}
@Column(name = "TITLE", length = 200)
public java.lang.String getTitle(){
return title;
}
public void setTitle(java.lang.String title)
{
this.title = title;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "PROPOSAL_NOTE", length = 1000)
public java.lang.String getProposalNote(){
return proposalNote;
}
public void setProposalNote(java.lang.String proposalNote)
{
this.proposalNote = proposalNote;
}
@Column(name = "ACCEPTOR_ID", length = 22)
public java.lang.Long getAcceptorId(){
return acceptorId;
}
public void setAcceptorId(java.lang.Long acceptorId)
{
this.acceptorId = acceptorId;
}
@Column(name = "ACCEPT_DATE", length = 7)
public java.util.Date getAcceptDate(){
return acceptDate;
}
public void setAcceptDate(java.util.Date acceptDate)
{
this.acceptDate = acceptDate;
}
@Column(name = "ACCEPT_NOTE", length = 1000)
public java.lang.String getAcceptNote(){
return acceptNote;
}
public void setAcceptNote(java.lang.String acceptNote)
{
this.acceptNote = acceptNote;
}
@Column(name = "REJECT_NOTE", length = 1000)
public java.lang.String getRejectNote(){
return rejectNote;
}
public void setRejectNote(java.lang.String rejectNote)
{
this.rejectNote = rejectNote;
}
@Column(name = "NUM_DISPATH", length = 100)
public java.lang.String getNumDispath(){
return numDispath;
}
public void setNumDispath(java.lang.String numDispath)
{
this.numDispath = numDispath;
}
@Column(name = "NUM_SUBMITSION", length = 100)
public java.lang.String getNumSubmitsion(){
return numSubmitsion;
}
public void setNumSubmitsion(java.lang.String numSubmitsion)
{
this.numSubmitsion = numSubmitsion;
}
@Column(name = "CAT_PARTNER_TYPE_ID", length = 22)
public java.lang.Long getCatPartnerTypeId(){
return catPartnerTypeId;
}
public void setCatPartnerTypeId(java.lang.Long catPartnerTypeId)
{
this.catPartnerTypeId = catPartnerTypeId;
}
@Column(name = "CREAT_PARTNER_DATE", length = 7)
public java.util.Date getCreatPartnerDate(){
return creatPartnerDate;
}
public void setCreatPartnerDate(java.util.Date creatPartnerDate)
{
this.creatPartnerDate = creatPartnerDate;
}
@Column(name = "REASON_CHANGE_PARTNER", length = 1000)
public java.lang.String getReasonChangePartner(){
return reasonChangePartner;
}
public void setReasonChangePartner(java.lang.String reasonChangePartner)
{
this.reasonChangePartner = reasonChangePartner;
}
@Column(name = "DELEGATE_POSITION", length = 200)
public java.lang.String getDelegatePosition(){
return delegatePosition;
}
public void setDelegatePosition(java.lang.String delegatePosition)
{
this.delegatePosition = delegatePosition;
}
@Column(name = "IDENTITY_CARD", length = 100)
public java.lang.String getIdentityCard(){
return identityCard;
}
public void setIdentityCard(java.lang.String identityCard)
{
this.identityCard = identityCard;
}
@Column(name = "BANK_NAME", length = 200)
public java.lang.String getBankName(){
return bankName;
}
public void setBankName(java.lang.String bankName)
{
this.bankName = bankName;
}
@Column(name = "STATION_CODE_EXPECTED", length = 100)
public java.lang.String getStationCodeExpected(){
return stationCodeExpected;
}
public void setStationCodeExpected(java.lang.String stationCodeExpected)
{
this.stationCodeExpected = stationCodeExpected;
}
@Column(name = "STATION_ID_EXPECTED", length = 22)
public java.lang.Long getStationIdExpected(){
return stationIdExpected;
}
public void setStationIdExpected(java.lang.Long stationIdExpected)
{
this.stationIdExpected = stationIdExpected;
}
@Column(name = "MODIFIELD_DATE", length = 7)
public java.util.Date getModifieldDate(){
return modifieldDate;
}
public void setModifieldDate(java.util.Date modifieldDate)
{
this.modifieldDate = modifieldDate;
}
@Column(name = "CAT_BANK_BRANCH_ID", length = 22)
public java.lang.Long getCatBankBranchId(){
return catBankBranchId;
}
public void setCatBankBranchId(java.lang.Long catBankBranchId)
{
this.catBankBranchId = catBankBranchId;
}
@Column(name = "IS_LOCK", length = 22)
public java.lang.Long getIsLock(){
return isLock;
}
public void setIsLock(java.lang.Long isLock)
{
this.isLock = isLock;
}
@Column(name = "DATE_RANGE", length = 7)
public java.util.Date getDateRange(){
return dateRange;
}
public void setDateRange(java.util.Date dateRange)
{
this.dateRange = dateRange;
}
@Column(name = "ISSUE_BY", length = 600)
public java.lang.String getIssueBy(){
return issueBy;
}
public void setIssueBy(java.lang.String issueBy)
{
this.issueBy = issueBy;
}
@Column(name = "ACCOUNT_HOLDER", length = 200)
public java.lang.String getAccountHolder(){
return accountHolder;
}
public void setAccountHolder(java.lang.String accountHolder)
{
this.accountHolder = accountHolder;
}
@Column(name = "CHECK_PARTNER", length = 22)
public java.lang.Long getCheckPartner(){
return checkPartner;
}
public void setCheckPartner(java.lang.Long checkPartner)
{
this.checkPartner = checkPartner;
}
@Column(name = "DATE_UPDATE_PARTNERS_CONTRACT", length = 7)
public java.util.Date getDateUpdatePartnersContract(){
return dateUpdatePartnersContract;
}
public void setDateUpdatePartnersContract(java.util.Date dateUpdatePartnersContract)
{
this.dateUpdatePartnersContract = dateUpdatePartnersContract;
}
   

    @Override
    public CatPartnersDTO toDTO() {
        CatPartnersDTO catPartnersDTO = new CatPartnersDTO();
        //set cac gia tri 
        catPartnersDTO.setPartnerId(this.partnerId);
        catPartnersDTO.setPartnerName(this.partnerName);
        catPartnersDTO.setAddress(this.address);
        catPartnersDTO.setPhone(this.phone);
        catPartnersDTO.setDescription(this.description);
        catPartnersDTO.setIsActive(this.isActive);
        catPartnersDTO.setFax(this.fax);
        catPartnersDTO.setTaxCode(this.taxCode);
        catPartnersDTO.setCode(this.code);
        catPartnersDTO.setRefId(this.refId);
        catPartnersDTO.setType(this.type);
        catPartnersDTO.setAbbName(this.abbName);
        catPartnersDTO.setGroupId(this.groupId);
        catPartnersDTO.setAccountNumber(this.accountNumber);
        catPartnersDTO.setBussinessNumber(this.bussinessNumber);
        catPartnersDTO.setDelegatePerson(this.delegatePerson);
        catPartnersDTO.setTitle(this.title);
        catPartnersDTO.setStatus(this.status);
        catPartnersDTO.setCreatorId(this.creatorId);
        catPartnersDTO.setCreatedDate(this.createdDate);
        catPartnersDTO.setProposalNote(this.proposalNote);
        catPartnersDTO.setAcceptorId(this.acceptorId);
        catPartnersDTO.setAcceptDate(this.acceptDate);
        catPartnersDTO.setAcceptNote(this.acceptNote);
        catPartnersDTO.setRejectNote(this.rejectNote);
        catPartnersDTO.setNumDispath(this.numDispath);
        catPartnersDTO.setNumSubmitsion(this.numSubmitsion);
        catPartnersDTO.setCatPartnerTypeId(this.catPartnerTypeId);
        catPartnersDTO.setCreatPartnerDate(this.creatPartnerDate);
        catPartnersDTO.setReasonChangePartner(this.reasonChangePartner);
        catPartnersDTO.setDelegatePosition(this.delegatePosition);
        catPartnersDTO.setIdentityCard(this.identityCard);
        catPartnersDTO.setBankName(this.bankName);
        catPartnersDTO.setStationCodeExpected(this.stationCodeExpected);
        catPartnersDTO.setStationIdExpected(this.stationIdExpected);
        catPartnersDTO.setModifieldDate(this.modifieldDate);
        catPartnersDTO.setCatBankBranchId(this.catBankBranchId);
        catPartnersDTO.setIsLock(this.isLock);
        catPartnersDTO.setDateRange(this.dateRange);
        catPartnersDTO.setIssueBy(this.issueBy);
        catPartnersDTO.setAccountHolder(this.accountHolder);
        catPartnersDTO.setCheckPartner(this.checkPartner);
        catPartnersDTO.setDateUpdatePartnersContract(this.dateUpdatePartnersContract);
        return catPartnersDTO;
    }
    
}
