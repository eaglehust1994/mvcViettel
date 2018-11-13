package com.viettel.ims.dto;

import com.viettel.ims.bo.TaskQuotaBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;
//import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TASK_QUOTABO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskQuotaDTO extends imsBaseDTO<TaskQuotaBO> {

	private java.lang.Long taskQuotaId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.Long workItemQuotaId;
	private java.lang.String workItemQuotaName;
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
    public TaskQuotaBO toModel() {
        TaskQuotaBO taskQuotaBO = new TaskQuotaBO();
        taskQuotaBO.setTaskQuotaId(this.taskQuotaId);
        taskQuotaBO.setCode(this.code);
        taskQuotaBO.setName(this.name);
        taskQuotaBO.setWorkItemQuotaId(this.workItemQuotaId);
        taskQuotaBO.setPrice(this.price);
        taskQuotaBO.setWorkDay(this.workDay);
        taskQuotaBO.setQuotaType(this.quotaType);
        taskQuotaBO.setDescription(this.description);
        taskQuotaBO.setStatus(this.status);
        taskQuotaBO.setCreatedDate(this.createdDate);
        taskQuotaBO.setCreatedUserId(this.createdUserId);
        taskQuotaBO.setCreatedGroupId(this.createdGroupId);
        taskQuotaBO.setUpdatedDate(this.updatedDate);
        taskQuotaBO.setUpdatedUserId(this.updatedUserId);
        taskQuotaBO.setUpdatedGroupId(this.updatedGroupId);
        return taskQuotaBO;
    }

    @Override
     public Long getFWModelId() {
        return taskQuotaId;
    }
   
    @Override
    public String catchName() {
        return getTaskQuotaId().toString();
    }
	
	@JsonProperty("taskQuotaId")
    public java.lang.Long getTaskQuotaId(){
		return taskQuotaId;
    }
	
    public void setTaskQuotaId(java.lang.Long taskQuotaId){
		this.taskQuotaId = taskQuotaId;
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
	
	@JsonProperty("workItemQuotaId")
    public java.lang.Long getWorkItemQuotaId(){
		return workItemQuotaId;
    }
	
    public void setWorkItemQuotaId(java.lang.Long workItemQuotaId){
		this.workItemQuotaId = workItemQuotaId;
    }	
	
	@JsonProperty("workItemQuotaName")
    public java.lang.String getWorkItemQuotaName(){
		return workItemQuotaName;
    }
	
    public void setWorkItemQuotaName(java.lang.String workItemQuotaName){
		this.workItemQuotaName = workItemQuotaName;
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
