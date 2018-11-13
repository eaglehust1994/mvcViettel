package com.viettel.ims.dto;


import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.ims.bo.CatPartnerBO;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;
//import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CAT_PARTNERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatPartnerDTO extends imsBaseDTO<CatPartnerBO> {

	private java.lang.Long catPartnerId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String taxCode;
	private java.lang.String fax;
	private java.lang.String phone;
	private java.lang.String address;
	private java.lang.String represent;
	private java.lang.Long partnerType;
	private java.lang.String description;
	private java.lang.Long status;
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


    @Override
    public CatPartnerBO toModel() {
        CatPartnerBO catPartnerBO = new CatPartnerBO();
        catPartnerBO.setCatPartnerId(this.catPartnerId);
        catPartnerBO.setCode(this.code);
        catPartnerBO.setName(this.name);
        catPartnerBO.setTaxCode(this.taxCode);
        catPartnerBO.setFax(this.fax);
        catPartnerBO.setPhone(this.phone);
        catPartnerBO.setAddress(this.address);
        catPartnerBO.setRepresent(this.represent);
        catPartnerBO.setPartnerType(this.partnerType);
        catPartnerBO.setDescription(this.description);
        catPartnerBO.setStatus(this.status);
        catPartnerBO.setCreatedDate(this.createdDate);
        catPartnerBO.setCreatedUserId(this.createdUserId);
        catPartnerBO.setCreatedGroupId(this.createdGroupId);
        catPartnerBO.setUpdatedDate(this.updatedDate);
        catPartnerBO.setUpdatedUserId(this.updatedUserId);
        catPartnerBO.setUpdatedGroupId(this.updatedGroupId);
        return catPartnerBO;
    }

    @Override
     public Long getFWModelId() {
        return catPartnerId;
    }
   
    @Override
    public String catchName() {
        return getCatPartnerId().toString();
    }
	
	@JsonProperty("catPartnerId")
    public java.lang.Long getCatPartnerId(){
		return catPartnerId;
    }
	
    public void setCatPartnerId(java.lang.Long catPartnerId){
		this.catPartnerId = catPartnerId;
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
	
	@JsonProperty("taxCode")
    public java.lang.String getTaxCode(){
		return taxCode;
    }
	
    public void setTaxCode(java.lang.String taxCode){
		this.taxCode = taxCode;
    }	
	
	@JsonProperty("fax")
    public java.lang.String getFax(){
		return fax;
    }
	
    public void setFax(java.lang.String fax){
		this.fax = fax;
    }	
	
	@JsonProperty("phone")
    public java.lang.String getPhone(){
		return phone;
    }
	
    public void setPhone(java.lang.String phone){
		this.phone = phone;
    }	
	
	@JsonProperty("address")
    public java.lang.String getAddress(){
		return address;
    }
	
    public void setAddress(java.lang.String address){
		this.address = address;
    }	
	
	@JsonProperty("represent")
    public java.lang.String getRepresent(){
		return represent;
    }
	
    public void setRepresent(java.lang.String represent){
		this.represent = represent;
    }	
	
	@JsonProperty("partnerType")
    public java.lang.Long getPartnerType(){
		return partnerType;
    }
	
    public void setPartnerType(java.lang.Long partnerType){
		this.partnerType = partnerType;
    }	
	
	@JsonProperty("description")
    public java.lang.String getDescription(){
		return description;
    }
	
    public void setDescription(java.lang.String description){
		this.description = description;
    }	
	
	@JsonProperty("status")
    public java.lang.Long getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.Long status){
		this.status = status;
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
	

}
