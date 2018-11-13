package com.viettel.ims.bo;

import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.CatWorkItemTypeBO")
@Table(name = "CAT_WORK_ITEM_TYPE")
/**
 *
 * @author: hailh10
 */
public class CatWorkItemTypeBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CAT_WORK_ITEM_TYPE_SEQ") })
	@Column(name = "CAT_WORK_ITEM_TYPE_ID", length = 22)
	private java.lang.Long catWorkItemTypeId;
	@Column(name = "NAME", length = 400)
	private java.lang.String name;
	@Column(name = "CODE", length = 100)
	private java.lang.String code;
	@Column(name = "STATUS", length = 40)
	private java.lang.String status;
	@Column(name = "DESCRIPTION", length = 2000)
	private java.lang.String description;
	@Column(name = "CAT_CONSTRUCTION_TYPE_ID", length = 22)
	private java.lang.Long catConstructionTypeId;
	@Column(name = "CREATED_DATE", length = 7)
	private java.util.Date createdDate;
	@Column(name = "UPDATED_DATE", length = 7)
	private java.util.Date updatedDate;
	@Column(name = "CREATED_USER", length = 22)
	private java.lang.Long createdUser;
	@Column(name = "UPDATED_USER", length = 22)
	private java.lang.Long updatedUser;

	
	public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
	}
	
	public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId)
	{
		this.catWorkItemTypeId = catWorkItemTypeId;
	}
	
	public java.lang.String getName(){
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.String getCode(){
		return code;
	}
	
	public void setCode(java.lang.String code)
	{
		this.code = code;
	}
	
	public java.lang.String getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.String status)
	{
		this.status = status;
	}
	
	public java.lang.String getDescription(){
		return description;
	}
	
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
	public java.lang.Long getCatConstructionTypeId(){
		return catConstructionTypeId;
	}
	
	public void setCatConstructionTypeId(java.lang.Long catConstructionTypeId)
	{
		this.catConstructionTypeId = catConstructionTypeId;
	}
	
	public java.util.Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(java.util.Date createdDate)
	{
		this.createdDate = createdDate;
	}
	
	public java.util.Date getUpdatedDate(){
		return updatedDate;
	}
	
	public void setUpdatedDate(java.util.Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	
	public java.lang.Long getCreatedUser(){
		return createdUser;
	}
	
	public void setCreatedUser(java.lang.Long createdUser)
	{
		this.createdUser = createdUser;
	}
	
	public java.lang.Long getUpdatedUser(){
		return updatedUser;
	}
	
	public void setUpdatedUser(java.lang.Long updatedUser)
	{
		this.updatedUser = updatedUser;
	}
   
    @Override
    public CatWorkItemTypeDTO toDTO() {
        CatWorkItemTypeDTO catWorkItemTypeDTO = new CatWorkItemTypeDTO(); 
        catWorkItemTypeDTO.setCatWorkItemTypeId(this.catWorkItemTypeId);		
        catWorkItemTypeDTO.setName(this.name);		
        catWorkItemTypeDTO.setCode(this.code);		
        catWorkItemTypeDTO.setStatus(this.status);		
        catWorkItemTypeDTO.setDescription(this.description);		
        catWorkItemTypeDTO.setCatConstructionTypeId(this.catConstructionTypeId);		
        catWorkItemTypeDTO.setCreatedDate(this.createdDate);		
        catWorkItemTypeDTO.setUpdatedDate(this.updatedDate);		
        catWorkItemTypeDTO.setCreatedUser(this.createdUser);		
        catWorkItemTypeDTO.setUpdatedUser(this.updatedUser);		
        return catWorkItemTypeDTO;
    }
}
