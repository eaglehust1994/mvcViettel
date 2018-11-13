package com.viettel.ims.bo;

import com.viettel.ims.dto.CatTaskDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.CatTaskBO")
@Table(name = "CAT_TASK")
/**
 *
 * @author: hailh10
 */
public class CatTaskBO extends BaseFWModelImpl {
     
	@Column(name = "CREATED_USER", length = 22)
	private java.lang.Long createdUser;
	@Column(name = "UPDATED_DATE", length = 7)
	private java.util.Date updatedDate;
	@Column(name = "CREATED_DATE", length = 7)
	private java.util.Date createdDate;
	@Column(name = "STATUS", length = 2)
	private java.lang.String status;
	@Column(name = "CODE", length = 100)
	private java.lang.String code;
	@Column(name = "DESCRIPTION", length = 2000)
	private java.lang.String description;
	@Column(name = "NAME", length = 400)
	private java.lang.String name;
	@Column(name = "CAT_WORK_ITEM_TYPE_ID", length = 22)
	private java.lang.Long catWorkItemTypeId;
	@Column(name = "CAT_UNIT_ID", length = 22)
	private java.lang.Long catUnitId;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CAT_TASK_SEQ") })
	@Column(name = "CAT_TASK_ID", length = 22)
	private java.lang.Long catTaskId;
	@Column(name = "UPDATED_USER", length = 22)
	private java.lang.Long updatedUser;

	
	public java.lang.Long getCreatedUser(){
		return createdUser;
	}
	
	public void setCreatedUser(java.lang.Long createdUser)
	{
		this.createdUser = createdUser;
	}
	
	public java.util.Date getUpdatedDate(){
		return updatedDate;
	}
	
	public void setUpdatedDate(java.util.Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	
	public java.util.Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(java.util.Date createdDate)
	{
		this.createdDate = createdDate;
	}
	
	public java.lang.String getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.String status)
	{
		this.status = status;
	}
	
	public java.lang.String getCode(){
		return code;
	}
	
	public void setCode(java.lang.String code)
	{
		this.code = code;
	}
	
	public java.lang.String getDescription(){
		return description;
	}
	
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
	public java.lang.String getName(){
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
	}
	
	public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId)
	{
		this.catWorkItemTypeId = catWorkItemTypeId;
	}
	
	public java.lang.Long getCatUnitId(){
		return catUnitId;
	}
	
	public void setCatUnitId(java.lang.Long catUnitId)
	{
		this.catUnitId = catUnitId;
	}
	
	public java.lang.Long getCatTaskId(){
		return catTaskId;
	}
	
	public void setCatTaskId(java.lang.Long catTaskId)
	{
		this.catTaskId = catTaskId;
	}
	
	public java.lang.Long getUpdatedUser(){
		return updatedUser;
	}
	
	public void setUpdatedUser(java.lang.Long updatedUser)
	{
		this.updatedUser = updatedUser;
	}
   
    @Override
    public CatTaskDTO toDTO() {
        CatTaskDTO catTaskDTO = new CatTaskDTO(); 
        catTaskDTO.setCreatedUser(this.createdUser);		
        catTaskDTO.setUpdatedDate(this.updatedDate);		
        catTaskDTO.setCreatedDate(this.createdDate);		
        catTaskDTO.setStatus(this.status);		
        catTaskDTO.setCode(this.code);		
        catTaskDTO.setDescription(this.description);		
        catTaskDTO.setName(this.name);		
        catTaskDTO.setCatWorkItemTypeId(this.catWorkItemTypeId);		
        catTaskDTO.setCatUnitId(this.catUnitId);		
        catTaskDTO.setCatTaskId(this.catTaskId);		
        catTaskDTO.setUpdatedUser(this.updatedUser);		
        return catTaskDTO;
    }
}
