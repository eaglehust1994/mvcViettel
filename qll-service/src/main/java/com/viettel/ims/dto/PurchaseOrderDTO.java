package com.viettel.ims.dto;

import com.viettel.ims.bo.PurchaseOrderBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;
//import com.viettel.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "PURCHASE_ORDERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderDTO extends imsBaseDTO<PurchaseOrderBO> {

	private java.lang.Long purchaseOrderId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.Long catPartnerId;
	private java.lang.String catPartnerName;
	private java.lang.String signerPartner;
	private java.lang.Long sysGroupId;
	private java.lang.String sysGroupName;
	private java.lang.String signerGroupName;
	private java.lang.Long signerGroupId;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDateTo;
	private java.lang.Double price;
	private java.lang.String expense;
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
	private String text;
	private int start;
	private int maxResult;

    @Override
    public PurchaseOrderBO toModel() {
        PurchaseOrderBO purchaseOrderBO = new PurchaseOrderBO();
        purchaseOrderBO.setPurchaseOrderId(this.purchaseOrderId);
        purchaseOrderBO.setCode(this.code);
        purchaseOrderBO.setName(this.name);
        purchaseOrderBO.setCatPartnerId(this.catPartnerId);
        purchaseOrderBO.setSignerPartner(this.signerPartner);
        purchaseOrderBO.setSysGroupId(this.sysGroupId);
        purchaseOrderBO.setSignerGroupName(this.signerGroupName);
        purchaseOrderBO.setSignerGroupId(this.signerGroupId);
        purchaseOrderBO.setSignDate(this.signDate);
        purchaseOrderBO.setPrice(this.price);
        purchaseOrderBO.setExpense(this.expense);
        purchaseOrderBO.setDescription(this.description);
        purchaseOrderBO.setStatus(this.status);
        purchaseOrderBO.setCreatedDate(this.createdDate);
        purchaseOrderBO.setCreatedUserId(this.createdUserId);
        purchaseOrderBO.setCreatedGroupId(this.createdGroupId);
        purchaseOrderBO.setUpdatedDate(this.updatedDate);
        purchaseOrderBO.setUpdatedUserId(this.updatedUserId);
        purchaseOrderBO.setUpdatedGroupId(this.updatedGroupId);
        return purchaseOrderBO;
    }

    @Override
     public Long getFWModelId() {
        return purchaseOrderId;
    }
   
    @Override
    public String catchName() {
        return getPurchaseOrderId().toString();
    }
	
	@JsonProperty("purchaseOrderId")
    public java.lang.Long getPurchaseOrderId(){
		return purchaseOrderId;
    }
	
    public void setPurchaseOrderId(java.lang.Long purchaseOrderId){
		this.purchaseOrderId = purchaseOrderId;
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
	
	@JsonProperty("signerGroupName")
	public java.lang.String getSignerGroupName() {
		return signerGroupName;
	}
	
	public void setSignerGroupName(java.lang.String signerGroupName) {
		this.signerGroupName = signerGroupName;
	}

	@JsonProperty("signerGroupId")
	public java.lang.Long getSignerGroupId() {
		return signerGroupId;
	}

	public void setSignerGroupId(java.lang.Long signerGroupId) {
		this.signerGroupId = signerGroupId;
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
	
	@JsonProperty("price")
    public java.lang.Double getPrice(){
		return price;
    }
	
    public void setPrice(java.lang.Double price){
		this.price = price;
    }	
	
	@JsonProperty("expense")
    public java.lang.String getExpense(){
		return expense;
    }
	
    public void setExpense(java.lang.String expense){
		this.expense = expense;
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
	
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
//	@JsonProperty("text")
//	public String getText() {
//		if (this.value != null && this.name != null) {
//			if (!this.value.contains(ApplicationConstants.SEARCH_MORE) && !this.name.contains(ApplicationConstants.SEARCH_MORE)) {
//				return this.value != null ? this.value + " - " + this.name : this.name;
//			} else {
//				return ApplicationConstants.SEARCH_MORE;
//			}
//		} else {
//			return this.name;
//		}
//	}
//	
//	public void setText(String text) {
//		this.text = text;
//	}
}
