package com.viettel.ims.bo;

import com.viettel.ims.dto.CatPartnerDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.CatPartnerBO")
@Table(name = "CAT_PARTNER")
/**
 *
 * @author: hailh10
 */
public class CatPartnerBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CAT_PARTNER_SEQ") })
	@Column(name = "CAT_PARTNER_ID", length = 22)
	private java.lang.Long catPartnerId;
	@Column(name = "CODE", length = 200)
	private java.lang.String code;
	@Column(name = "NAME", length = 1000)
	private java.lang.String name;
	@Column(name = "TAX_CODE", length = 100)
	private java.lang.String taxCode;
	@Column(name = "FAX", length = 60)
	private java.lang.String fax;
	@Column(name = "PHONE", length = 60)
	private java.lang.String phone;
	@Column(name = "ADDRESS", length = 600)
	private java.lang.String address;
	@Column(name = "REPRESENT", length = 200)
	private java.lang.String represent;
	@Column(name = "PARTNER_TYPE", length = 22)
	private java.lang.Long partnerType;
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

	
	public java.lang.Long getCatPartnerId(){
		return catPartnerId;
	}
	
	public void setCatPartnerId(java.lang.Long catPartnerId)
	{
		this.catPartnerId = catPartnerId;
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
	
	public java.lang.String getTaxCode(){
		return taxCode;
	}
	
	public void setTaxCode(java.lang.String taxCode)
	{
		this.taxCode = taxCode;
	}
	
	public java.lang.String getFax(){
		return fax;
	}
	
	public void setFax(java.lang.String fax)
	{
		this.fax = fax;
	}
	
	public java.lang.String getPhone(){
		return phone;
	}
	
	public void setPhone(java.lang.String phone)
	{
		this.phone = phone;
	}
	
	public java.lang.String getAddress(){
		return address;
	}
	
	public void setAddress(java.lang.String address)
	{
		this.address = address;
	}
	
	public java.lang.String getRepresent(){
		return represent;
	}
	
	public void setRepresent(java.lang.String represent)
	{
		this.represent = represent;
	}
	
	public java.lang.Long getPartnerType(){
		return partnerType;
	}
	
	public void setPartnerType(java.lang.Long partnerType)
	{
		this.partnerType = partnerType;
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
    public CatPartnerDTO toDTO() {
        CatPartnerDTO catPartnerDTO = new CatPartnerDTO(); 
        catPartnerDTO.setCatPartnerId(this.catPartnerId);		
        catPartnerDTO.setCode(this.code);		
        catPartnerDTO.setName(this.name);		
        catPartnerDTO.setTaxCode(this.taxCode);		
        catPartnerDTO.setFax(this.fax);		
        catPartnerDTO.setPhone(this.phone);		
        catPartnerDTO.setAddress(this.address);		
        catPartnerDTO.setRepresent(this.represent);		
        catPartnerDTO.setPartnerType(this.partnerType);		
        catPartnerDTO.setDescription(this.description);		
        catPartnerDTO.setStatus(this.status);		
        catPartnerDTO.setCreatedDate(this.createdDate);		
        catPartnerDTO.setCreatedUserId(this.createdUserId);		
        catPartnerDTO.setCreatedGroupId(this.createdGroupId);		
        catPartnerDTO.setUpdatedDate(this.updatedDate);		
        catPartnerDTO.setUpdatedUserId(this.updatedUserId);		
        catPartnerDTO.setUpdatedGroupId(this.updatedGroupId);		
        return catPartnerDTO;
    }
}
