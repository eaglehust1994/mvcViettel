package com.viettel.ims.bo;

import com.viettel.ims.dto.WorkItemQuotaDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.WorkItemQuotaBO")
@Table(name = "WORK_ITEM_QUOTA")
/**
 *
 * @author: hailh10
 */
public class WorkItemQuotaBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "WORK_ITEM_QUOTA_SEQ") })
	@Column(name = "WORK_ITEM_QUOTA_ID", length = 22)
	private java.lang.Long workItemQuotaId;
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long sysGroupId;
	@Column(name = "CAT_CONSTRUCTION_TYPE_ID", length = 22)
	private java.lang.Long catConstructionTypeId;
	@Column(name = "CAT_WORK_ITEM_TYPE_ID", length = 22)
	private java.lang.Long catWorkItemTypeId;
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

	
	public java.lang.Long getWorkItemQuotaId(){
		return workItemQuotaId;
	}
	
	public void setWorkItemQuotaId(java.lang.Long workItemQuotaId)
	{
		this.workItemQuotaId = workItemQuotaId;
	}
	
	public java.lang.Long getSysGroupId(){
		return sysGroupId;
	}
	
	public void setSysGroupId(java.lang.Long sysGroupId)
	{
		this.sysGroupId = sysGroupId;
	}
	
	public java.lang.Long getCatConstructionTypeId(){
		return catConstructionTypeId;
	}
	
	public void setCatConstructionTypeId(java.lang.Long catConstructionTypeId)
	{
		this.catConstructionTypeId = catConstructionTypeId;
	}
	
	public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
	}
	
	public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId)
	{
		this.catWorkItemTypeId = catWorkItemTypeId;
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
    public WorkItemQuotaDTO toDTO() {
        WorkItemQuotaDTO workItemQuotaDTO = new WorkItemQuotaDTO(); 
        workItemQuotaDTO.setWorkItemQuotaId(this.workItemQuotaId);		
        workItemQuotaDTO.setSysGroupId(this.sysGroupId);		
        workItemQuotaDTO.setCatConstructionTypeId(this.catConstructionTypeId);		
        workItemQuotaDTO.setCatWorkItemTypeId(this.catWorkItemTypeId);		
        workItemQuotaDTO.setPrice(this.price);		
        workItemQuotaDTO.setWorkDay(this.workDay);		
        workItemQuotaDTO.setQuotaType(this.quotaType);		
        workItemQuotaDTO.setDescription(this.description);		
        workItemQuotaDTO.setStatus(this.status);		
        workItemQuotaDTO.setCreatedDate(this.createdDate);		
        workItemQuotaDTO.setCreatedUserId(this.createdUserId);		
        workItemQuotaDTO.setCreatedGroupId(this.createdGroupId);		
        workItemQuotaDTO.setUpdatedDate(this.updatedDate);		
        workItemQuotaDTO.setUpdatedUserId(this.updatedUserId);		
        workItemQuotaDTO.setUpdatedGroupId(this.updatedGroupId);		
        return workItemQuotaDTO;
    }
}
