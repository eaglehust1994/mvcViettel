package com.viettel.ims.bo;

import com.viettel.ims.dto.TaskQuotaDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TaskQuotaBO")
@Table(name = "TASK_QUOTA")
/**
 *
 * @author: hailh10
 */
public class TaskQuotaBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TASK_QUOTA_SEQ") })
	@Column(name = "TASK_QUOTA_ID", length = 22)
	private java.lang.Long taskQuotaId;
	@Column(name = "CODE", length = 200)
	private java.lang.String code;
	@Column(name = "NAME", length = 1000)
	private java.lang.String name;
	@Column(name = "WORK_ITEM_QUOTA_ID", length = 22)
	private java.lang.Long workItemQuotaId;
	@Column(name = "PRICE", length = 22)
	private java.lang.Double price;
	@Column(name = "WORK_DAY", length = 22)
	private java.lang.Double workDay;
	@Column(name = "QUOTA_TYPE", length = 22)
	private java.lang.Long quotaType;
	@Column(name = "DESCRIPTION", length = 4000)
	private java.lang.String description;
	@Column(name = "STATUS", length = 22)
	private java.lang.Long status;
	@Column(name = "CREATED_DATE", length = 7)
	private java.util.Date createdDate;
	@Column(name = "CREATED_USER_ID", length = 22)
	private java.lang.Long createdUserId;
	@Column(name = "CREATED_GROUP_ID", length = 22)
	private java.lang.Long createdGroupId;
	@Column(name = "UPDATED_DATE", length = 7)
	private java.util.Date updatedDate;
	@Column(name = "UPDATED_USER_ID", length = 22)
	private java.lang.Long updatedUserId;
	@Column(name = "UPDATED_GROUP_ID", length = 22)
	private java.lang.Long updatedGroupId;

	
	public java.lang.Long getTaskQuotaId(){
		return taskQuotaId;
	}
	
	public void setTaskQuotaId(java.lang.Long taskQuotaId)
	{
		this.taskQuotaId = taskQuotaId;
	}
	
	public java.lang.String getCode(){
		return code;
	}
	
	public void setCode(java.lang.String code)
	{
		this.code = code;
	}
	
	public java.lang.String getName(){
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.Long getWorkItemQuotaId(){
		return workItemQuotaId;
	}
	
	public void setWorkItemQuotaId(java.lang.Long workItemQuotaId)
	{
		this.workItemQuotaId = workItemQuotaId;
	}
	
	public java.lang.Double getPrice(){
		return price;
	}
	
	public void setPrice(java.lang.Double price)
	{
		this.price = price;
	}
	
	public java.lang.Double getWorkDay(){
		return workDay;
	}
	
	public void setWorkDay(java.lang.Double workDay)
	{
		this.workDay = workDay;
	}
	
	public java.lang.Long getQuotaType(){
		return quotaType;
	}
	
	public void setQuotaType(java.lang.Long quotaType)
	{
		this.quotaType = quotaType;
	}
	
	public java.lang.String getDescription(){
		return description;
	}
	
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
	public java.lang.Long getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.Long status)
	{
		this.status = status;
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
	
	public java.lang.Long getCreatedGroupId(){
		return createdGroupId;
	}
	
	public void setCreatedGroupId(java.lang.Long createdGroupId)
	{
		this.createdGroupId = createdGroupId;
	}
	
	public java.util.Date getUpdatedDate(){
		return updatedDate;
	}
	
	public void setUpdatedDate(java.util.Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	
	public java.lang.Long getUpdatedUserId(){
		return updatedUserId;
	}
	
	public void setUpdatedUserId(java.lang.Long updatedUserId)
	{
		this.updatedUserId = updatedUserId;
	}
	
	public java.lang.Long getUpdatedGroupId(){
		return updatedGroupId;
	}
	
	public void setUpdatedGroupId(java.lang.Long updatedGroupId)
	{
		this.updatedGroupId = updatedGroupId;
	}
   
    @Override
    public TaskQuotaDTO toDTO() {
        TaskQuotaDTO taskQuotaDTO = new TaskQuotaDTO(); 
        taskQuotaDTO.setTaskQuotaId(this.taskQuotaId);		
        taskQuotaDTO.setCode(this.code);		
        taskQuotaDTO.setName(this.name);		
        taskQuotaDTO.setWorkItemQuotaId(this.workItemQuotaId);		
        taskQuotaDTO.setPrice(this.price);		
        taskQuotaDTO.setWorkDay(this.workDay);		
        taskQuotaDTO.setQuotaType(this.quotaType);		
        taskQuotaDTO.setDescription(this.description);		
        taskQuotaDTO.setStatus(this.status);		
        taskQuotaDTO.setCreatedDate(this.createdDate);		
        taskQuotaDTO.setCreatedUserId(this.createdUserId);		
        taskQuotaDTO.setCreatedGroupId(this.createdGroupId);		
        taskQuotaDTO.setUpdatedDate(this.updatedDate);		
        taskQuotaDTO.setUpdatedUserId(this.updatedUserId);		
        taskQuotaDTO.setUpdatedGroupId(this.updatedGroupId);		
        return taskQuotaDTO;
    }
}
