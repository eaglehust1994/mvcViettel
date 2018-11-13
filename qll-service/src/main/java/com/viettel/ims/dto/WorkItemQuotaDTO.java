package com.viettel.ims.dto;

import com.viettel.ims.bo.WorkItemQuotaBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "WORK_ITEM_QUOTABO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkItemQuotaDTO extends imsBaseDTO<WorkItemQuotaBO> {

	private java.lang.Long workItemQuotaId;
	private java.lang.Long sysGroupId;
	private java.lang.String sysGroupName;
	private java.lang.Long catConstructionTypeId;
	private java.lang.String catConstructionTypeName;
	private java.lang.Long catWorkItemTypeId;
	private java.lang.String catWorkItemTypeName;
	private java.lang.Double price;
	private java.lang.Double workDay;
	private java.lang.Long quotaType;
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
    public WorkItemQuotaBO toModel() {
        WorkItemQuotaBO workItemQuotaBO = new WorkItemQuotaBO();
        workItemQuotaBO.setWorkItemQuotaId(this.workItemQuotaId);
        workItemQuotaBO.setSysGroupId(this.sysGroupId);
        workItemQuotaBO.setCatConstructionTypeId(this.catConstructionTypeId);
        workItemQuotaBO.setCatWorkItemTypeId(this.catWorkItemTypeId);
        workItemQuotaBO.setPrice(this.price);
        workItemQuotaBO.setWorkDay(this.workDay);
        workItemQuotaBO.setQuotaType(this.quotaType);
        workItemQuotaBO.setDescription(this.description);
        workItemQuotaBO.setStatus(this.status);
        workItemQuotaBO.setCreatedDate(this.createdDate);
        workItemQuotaBO.setCreatedUserId(this.createdUserId);
        workItemQuotaBO.setCreatedGroupId(this.createdGroupId);
        workItemQuotaBO.setUpdatedDate(this.updatedDate);
        workItemQuotaBO.setUpdatedUserId(this.updatedUserId);
        workItemQuotaBO.setUpdatedGroupId(this.updatedGroupId);
        return workItemQuotaBO;
    }

    @Override
     public Long getFWModelId() {
        return workItemQuotaId;
    }
   
    @Override
    public String catchName() {
        return getWorkItemQuotaId().toString();
    }
	
	@JsonProperty("workItemQuotaId")
    public java.lang.Long getWorkItemQuotaId(){
		return workItemQuotaId;
    }
	
    public void setWorkItemQuotaId(java.lang.Long workItemQuotaId){
		this.workItemQuotaId = workItemQuotaId;
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
	
	@JsonProperty("catConstructionTypeId")
    public java.lang.Long getCatConstructionTypeId(){
		return catConstructionTypeId;
    }
	
    public void setCatConstructionTypeId(java.lang.Long catConstructionTypeId){
		this.catConstructionTypeId = catConstructionTypeId;
    }	
	
	@JsonProperty("catConstructionTypeName")
    public java.lang.String getCatConstructionTypeName(){
		return catConstructionTypeName;
    }
	
    public void setCatConstructionTypeName(java.lang.String catConstructionTypeName){
		this.catConstructionTypeName = catConstructionTypeName;
    }	
	
	@JsonProperty("catWorkItemTypeId")
    public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
    }
	
    public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId){
		this.catWorkItemTypeId = catWorkItemTypeId;
    }	
	
	@JsonProperty("catWorkItemTypeName")
    public java.lang.String getCatWorkItemTypeName(){
		return catWorkItemTypeName;
    }
	
    public void setCatWorkItemTypeName(java.lang.String catWorkItemTypeName){
		this.catWorkItemTypeName = catWorkItemTypeName;
    }	
	
	@JsonProperty("price")
    public java.lang.Double getPrice(){
		return price;
    }
	
    public void setPrice(java.lang.Double price){
		this.price = price;
    }	
	
	@JsonProperty("workDay")
    public java.lang.Double getWorkDay(){
		return workDay;
    }
	
    public void setWorkDay(java.lang.Double workDay){
		this.workDay = workDay;
    }	
	
	@JsonProperty("quotaType")
    public java.lang.Long getQuotaType(){
		return quotaType;
    }
	
    public void setQuotaType(java.lang.Long quotaType){
		this.quotaType = quotaType;
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
