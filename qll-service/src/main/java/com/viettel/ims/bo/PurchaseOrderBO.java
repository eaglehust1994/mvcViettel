package com.viettel.ims.bo;

import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.PurchaseOrderBO")
@Table(name = "PURCHASE_ORDER")
/**
 *
 * @author: hailh10
 */
public class PurchaseOrderBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "PURCHASE_ORDER_SEQ") })
	@Column(name = "PURCHASE_ORDER_ID", length = 22)
	private java.lang.Long purchaseOrderId;
	@Column(name = "CODE", length = 200)
	private java.lang.String code;
	@Column(name = "NAME", length = 1000)
	private java.lang.String name;
	@Column(name = "CAT_PARTNER_ID", length = 22)
	private java.lang.Long catPartnerId;
	@Column(name = "SIGNER_PARTNER", length = 200)
	private java.lang.String signerPartner;
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long sysGroupId;
	@Column(name = "SIGNER_GROUP_NAME", length = 200)
	private java.lang.String signerGroupName;
	@Column(name = "SIGNER_GROUP_ID", length = 22)
	private java.lang.Long signerGroupId;
	@Column(name = "SIGN_DATE", length = 7)
	private java.util.Date signDate;
	@Column(name = "PRICE", length = 22)
	private java.lang.Double price;
	@Column(name = "EXPENSE", length = 2000)
	private java.lang.String expense;
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

	
	public java.lang.Long getPurchaseOrderId(){
		return purchaseOrderId;
	}
	
	public void setPurchaseOrderId(java.lang.Long purchaseOrderId)
	{
		this.purchaseOrderId = purchaseOrderId;
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
	
	public java.lang.Long getCatPartnerId(){
		return catPartnerId;
	}
	
	public void setCatPartnerId(java.lang.Long catPartnerId)
	{
		this.catPartnerId = catPartnerId;
	}
	
	public java.lang.String getSignerPartner(){
		return signerPartner;
	}
	
	public void setSignerPartner(java.lang.String signerPartner)
	{
		this.signerPartner = signerPartner;
	}
	
	public java.lang.Long getSysGroupId(){
		return sysGroupId;
	}
	
	public void setSysGroupId(java.lang.Long sysGroupId)
	{
		this.sysGroupId = sysGroupId;
	}
	
	public java.lang.String getSignerGroupName() {
		return signerGroupName;
	}

	public void setSignerGroupName(java.lang.String signerGroupName) {
		this.signerGroupName = signerGroupName;
	}

	public java.lang.Long getSignerGroupId() {
		return signerGroupId;
	}

	public void setSignerGroupId(java.lang.Long signerGroupId) {
		this.signerGroupId = signerGroupId;
	}

	public java.util.Date getSignDate(){
		return signDate;
	}
	
	public void setSignDate(java.util.Date signDate)
	{
		this.signDate = signDate;
	}
	
	public java.lang.Double getPrice(){
		return price;
	}
	
	public void setPrice(java.lang.Double price)
	{
		this.price = price;
	}
	
	public java.lang.String getExpense(){
		return expense;
	}
	
	public void setExpense(java.lang.String expense)
	{
		this.expense = expense;
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
    public PurchaseOrderDTO toDTO() {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(); 
        purchaseOrderDTO.setPurchaseOrderId(this.purchaseOrderId);		
        purchaseOrderDTO.setCode(this.code);		
        purchaseOrderDTO.setName(this.name);		
        purchaseOrderDTO.setCatPartnerId(this.catPartnerId);		
        purchaseOrderDTO.setSignerPartner(this.signerPartner);		
        purchaseOrderDTO.setSysGroupId(this.sysGroupId);		
        purchaseOrderDTO.setSignerGroupName(this.signerGroupName);
        purchaseOrderDTO.setSignerGroupId(this.signerGroupId);
        purchaseOrderDTO.setSignDate(this.signDate);		
        purchaseOrderDTO.setPrice(this.price);		
        purchaseOrderDTO.setExpense(this.expense);		
        purchaseOrderDTO.setDescription(this.description);		
        purchaseOrderDTO.setStatus(this.status);		
        purchaseOrderDTO.setCreatedDate(this.createdDate);		
        purchaseOrderDTO.setCreatedUserId(this.createdUserId);		
        purchaseOrderDTO.setCreatedGroupId(this.createdGroupId);		
        purchaseOrderDTO.setUpdatedDate(this.updatedDate);		
        purchaseOrderDTO.setUpdatedUserId(this.updatedUserId);		
        purchaseOrderDTO.setUpdatedGroupId(this.updatedGroupId);		
        return purchaseOrderDTO;
    }
}
