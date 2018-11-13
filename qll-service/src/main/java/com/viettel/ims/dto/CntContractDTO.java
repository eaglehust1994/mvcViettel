package com.viettel.ims.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.ims.bo.CntContractBO;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CNT_CONTRACTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CntContractDTO extends imsBaseDTO<CntContractBO> {

	private java.lang.Long cntContractId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String contractCodeKtts;
	private java.lang.String content;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDateTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTime;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTimeFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTimeTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTime;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTimeFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTimeTo;
	private java.lang.Double price;
	private java.lang.Double appendixContract;
	private java.lang.Double numStation;
	private java.lang.Long biddingPackageId;
	private java.lang.String biddingPackageName;
	private java.lang.Long catPartnerId;
	private java.lang.String catPartnerName;
	private java.lang.String signerPartner;
	private java.lang.Long sysGroupId;
	private java.lang.String sysGroupName;
	private java.lang.Long signerGroup;
	private java.lang.String signerGroupName;
	private java.lang.String supervisor;
	private java.lang.Long status;
	private java.lang.Double formal;
	private java.lang.Long contractType;
	private java.lang.Double cntContractParentId;
	private java.lang.String cntContractParentName;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateTo;
	private java.lang.Long createdUserId;
	private java.lang.String createdUserName;
	private java.lang.Long createdGroupId;
	private java.lang.String createdGroupName;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateTo;
	private java.lang.Long updatedUserId;
	private java.lang.String updatedUserName;
	private java.lang.Long updatedGroupId;
	private java.lang.String updatedGroupName;

	private List<PurchaseOrderDTO> purchaseOrderLst;

	@Override
    public CntContractBO toModel() {
        CntContractBO cntContractBO = new CntContractBO();
        cntContractBO.setCntContractId(this.cntContractId);
        cntContractBO.setCode(this.code);
        cntContractBO.setName(this.name);
        cntContractBO.setContractCodeKtts(this.contractCodeKtts);
        cntContractBO.setContent(this.content);
        cntContractBO.setSignDate(this.signDate);
        cntContractBO.setStartTime(this.startTime);
        cntContractBO.setEndTime(this.endTime);
        cntContractBO.setPrice(this.price);
        cntContractBO.setAppendixContract(this.appendixContract);
        cntContractBO.setNumStation(this.numStation);
        cntContractBO.setBiddingPackageId(this.biddingPackageId);
        cntContractBO.setCatPartnerId(this.catPartnerId);
        cntContractBO.setSignerPartner(this.signerPartner);
        cntContractBO.setSysGroupId(this.sysGroupId);
        cntContractBO.setSignerGroup(this.signerGroup);
        cntContractBO.setSupervisor(this.supervisor);
        cntContractBO.setStatus(this.status);
        cntContractBO.setFormal(this.formal);
        cntContractBO.setContractType(this.contractType);
        cntContractBO.setCntContractParentId(this.cntContractParentId);
        cntContractBO.setCreatedDate(this.createdDate);
        cntContractBO.setCreatedUserId(this.createdUserId);
        cntContractBO.setCreatedGroupId(this.createdGroupId);
        cntContractBO.setUpdatedDate(this.updatedDate);
        cntContractBO.setUpdatedUserId(this.updatedUserId);
        cntContractBO.setUpdatedGroupId(this.updatedGroupId);
        return cntContractBO;
    }

    @Override
     public Long getFWModelId() {
        return cntContractId;
    }
   
    @Override
    public String catchName() {
        return getCntContractId().toString();
    }
	
	@JsonProperty("cntContractId")
    public java.lang.Long getCntContractId(){
		return cntContractId;
    }
	
    public void setCntContractId(java.lang.Long cntContractId){
		this.cntContractId = cntContractId;
    }	
	
	@JsonProperty("code")
    public java.lang.String getCode(){
		return code;
    }
	
    public void setCode(java.lang.String code){
		this.code = code;
    }	
	
	@JsonProperty("name")
    public java.lang.String getName(){
		return name;
    }
	
    public void setName(java.lang.String name){
		this.name = name;
    }	
	
	@JsonProperty("contractCodeKtts")
    public java.lang.String getContractCodeKtts(){
		return contractCodeKtts;
    }
	
    public void setContractCodeKtts(java.lang.String contractCodeKtts){
		this.contractCodeKtts = contractCodeKtts;
    }	
	
	@JsonProperty("content")
    public java.lang.String getContent(){
		return content;
    }
	
    public void setContent(java.lang.String content){
		this.content = content;
    }	
	
	@JsonProperty("signDate")
    public java.util.Date getSignDate(){
		return signDate;
    }
	
    public void setSignDate(java.util.Date signDate){
		this.signDate = signDate;
    }	
	
	public java.util.Date getSignDateFrom() {
    	return signDateFrom;
    }
	
    public void setSignDateFrom(java.util.Date signDateFrom) {
    	this.signDateFrom = signDateFrom;
    }
	
	public java.util.Date getSignDateTo() {
    	return signDateTo;
    }
	
    public void setSignDateTo(java.util.Date signDateTo) {
    	this.signDateTo = signDateTo;
    }
	
	@JsonProperty("startTime")
    public java.util.Date getStartTime(){
		return startTime;
    }
	
    public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
    }	
	
	public java.util.Date getStartTimeFrom() {
    	return startTimeFrom;
    }
	
    public void setStartTimeFrom(java.util.Date startTimeFrom) {
    	this.startTimeFrom = startTimeFrom;
    }
	
	public java.util.Date getStartTimeTo() {
    	return startTimeTo;
    }
	
    public void setStartTimeTo(java.util.Date startTimeTo) {
    	this.startTimeTo = startTimeTo;
    }
	
	@JsonProperty("endTime")
    public java.util.Date getEndTime(){
		return endTime;
    }
	
    public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
    }	
	
	public java.util.Date getEndTimeFrom() {
    	return endTimeFrom;
    }
	
    public void setEndTimeFrom(java.util.Date endTimeFrom) {
    	this.endTimeFrom = endTimeFrom;
    }
	
	public java.util.Date getEndTimeTo() {
    	return endTimeTo;
    }
	
    public void setEndTimeTo(java.util.Date endTimeTo) {
    	this.endTimeTo = endTimeTo;
    }
	
	@JsonProperty("price")
    public java.lang.Double getPrice(){
		return price;
    }
	
    public void setPrice(java.lang.Double price){
		this.price = price;
    }	
	
	@JsonProperty("appendixContract")
    public java.lang.Double getAppendixContract(){
		return appendixContract;
    }
	
    public void setAppendixContract(java.lang.Double appendixContract){
		this.appendixContract = appendixContract;
    }	
	
	@JsonProperty("numStation")
    public java.lang.Double getNumStation(){
		return numStation;
    }
	
    public void setNumStation(java.lang.Double numStation){
		this.numStation = numStation;
    }	
	
	@JsonProperty("biddingPackageId")
    public java.lang.Long getBiddingPackageId(){
		return biddingPackageId;
    }
	
    public void setBiddingPackageId(java.lang.Long biddingPackageId){
		this.biddingPackageId = biddingPackageId;
    }	
	
	@JsonProperty("biddingPackageName")
    public java.lang.String getBiddingPackageName(){
		return biddingPackageName;
    }
	
    public void setBiddingPackageName(java.lang.String biddingPackageName){
		this.biddingPackageName = biddingPackageName;
    }	
	
	@JsonProperty("catPartnerId")
    public java.lang.Long getCatPartnerId(){
		return catPartnerId;
    }
	
    public void setCatPartnerId(java.lang.Long catPartnerId){
		this.catPartnerId = catPartnerId;
    }	
	
	@JsonProperty("catPartnerName")
    public java.lang.String getCatPartnerName(){
		return catPartnerName;
    }
	
    public void setCatPartnerName(java.lang.String catPartnerName){
		this.catPartnerName = catPartnerName;
    }	
	
	@JsonProperty("signerPartner")
    public java.lang.String getSignerPartner(){
		return signerPartner;
    }
	
    public void setSignerPartner(java.lang.String signerPartner){
		this.signerPartner = signerPartner;
    }	
	
	@JsonProperty("sysGroupId")
    public java.lang.Long getSysGroupId(){
		return sysGroupId;
    }
	
    public void setSysGroupId(java.lang.Long sysGroupId){
		this.sysGroupId = sysGroupId;
    }	
	
	@JsonProperty("sysGroupName")
    public java.lang.String getSysGroupName(){
		return sysGroupName;
    }
	
    public void setSysGroupName(java.lang.String sysGroupName){
		this.sysGroupName = sysGroupName;
    }	
	
	@JsonProperty("signerGroup")
    public java.lang.Long getSignerGroup(){
		return signerGroup;
    }
	
    public void setSignerGroup(java.lang.Long signerGroup){
		this.signerGroup = signerGroup;
    }	
	
    @JsonProperty("signerGroupName")
    
	public java.lang.String getSignerGroupName() {
		return signerGroupName;
	}

	public void setSignerGroupName(java.lang.String signerGroupName) {
		this.signerGroupName = signerGroupName;
	}

	@JsonProperty("supervisor")
    public java.lang.String getSupervisor(){
		return supervisor;
    }
	
    public void setSupervisor(java.lang.String supervisor){
		this.supervisor = supervisor;
    }	
	
	@JsonProperty("status")
    public java.lang.Long getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.Long status){
		this.status = status;
    }	
	
	@JsonProperty("formal")
    public java.lang.Double getFormal(){
		return formal;
    }
	
    public void setFormal(java.lang.Double formal){
		this.formal = formal;
    }	
	
	@JsonProperty("contractType")
    public java.lang.Long getContractType(){
		return contractType;
    }
	
    public void setContractType(java.lang.Long contractType){
		this.contractType = contractType;
    }	
	
	@JsonProperty("cntContractParentId")
    public java.lang.Double getCntContractParentId(){
		return cntContractParentId;
    }
	
    public void setCntContractParentId(java.lang.Double cntContractParentId){
		this.cntContractParentId = cntContractParentId;
    }	
	
	@JsonProperty("cntContractParentName")
    public java.lang.String getCntContractParentName(){
		return cntContractParentName;
    }
	
    public void setCntContractParentName(java.lang.String cntContractParentName){
		this.cntContractParentName = cntContractParentName;
    }	
	
	@JsonProperty("createdDate")
    public java.util.Date getCreatedDate(){
		return createdDate;
    }
	
    public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
    }	
	
	public java.util.Date getCreatedDateFrom() {
    	return createdDateFrom;
    }
	
    public void setCreatedDateFrom(java.util.Date createdDateFrom) {
    	this.createdDateFrom = createdDateFrom;
    }
	
	public java.util.Date getCreatedDateTo() {
    	return createdDateTo;
    }
	
    public void setCreatedDateTo(java.util.Date createdDateTo) {
    	this.createdDateTo = createdDateTo;
    }
	
	@JsonProperty("createdUserId")
    public java.lang.Long getCreatedUserId(){
		return createdUserId;
    }
	
    public void setCreatedUserId(java.lang.Long createdUserId){
		this.createdUserId = createdUserId;
    }	
	
	@JsonProperty("createdUserName")
    public java.lang.String getCreatedUserName(){
		return createdUserName;
    }
	
    public void setCreatedUserName(java.lang.String createdUserName){
		this.createdUserName = createdUserName;
    }	
	
	@JsonProperty("createdGroupId")
    public java.lang.Long getCreatedGroupId(){
		return createdGroupId;
    }
	
    public void setCreatedGroupId(java.lang.Long createdGroupId){
		this.createdGroupId = createdGroupId;
    }	
	
	@JsonProperty("createdGroupName")
    public java.lang.String getCreatedGroupName(){
		return createdGroupName;
    }
	
    public void setCreatedGroupName(java.lang.String createdGroupName){
		this.createdGroupName = createdGroupName;
    }	
	
	@JsonProperty("updatedDate")
    public java.util.Date getUpdatedDate(){
		return updatedDate;
    }
	
    public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
    }	
	
	public java.util.Date getUpdatedDateFrom() {
    	return updatedDateFrom;
    }
	
    public void setUpdatedDateFrom(java.util.Date updatedDateFrom) {
    	this.updatedDateFrom = updatedDateFrom;
    }
	
	public java.util.Date getUpdatedDateTo() {
    	return updatedDateTo;
    }
	
    public void setUpdatedDateTo(java.util.Date updatedDateTo) {
    	this.updatedDateTo = updatedDateTo;
    }
	
	@JsonProperty("updatedUserId")
    public java.lang.Long getUpdatedUserId(){
		return updatedUserId;
    }
	
    public void setUpdatedUserId(java.lang.Long updatedUserId){
		this.updatedUserId = updatedUserId;
    }	
	
	@JsonProperty("updatedUserName")
    public java.lang.String getUpdatedUserName(){
		return updatedUserName;
    }
	
    public void setUpdatedUserName(java.lang.String updatedUserName){
		this.updatedUserName = updatedUserName;
    }	
	
	@JsonProperty("updatedGroupId")
    public java.lang.Long getUpdatedGroupId(){
		return updatedGroupId;
    }
	
    public void setUpdatedGroupId(java.lang.Long updatedGroupId){
		this.updatedGroupId = updatedGroupId;
    }	
	
	@JsonProperty("updatedGroupName")
    public java.lang.String getUpdatedGroupName(){
		return updatedGroupName;
    }
	
    public void setUpdatedGroupName(java.lang.String updatedGroupName){
		this.updatedGroupName = updatedGroupName;
    }	
	
	@JsonProperty("purchaseOrderLst")
	public List<PurchaseOrderDTO> getPurchaseOrderLst() {
		return purchaseOrderLst;
	}

	public void setPurchaseOrderLst(List<PurchaseOrderDTO> purchaseOrderLst) {
		this.purchaseOrderLst = purchaseOrderLst;
	}

}
