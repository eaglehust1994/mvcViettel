package com.viettel.ims.bo;

import com.viettel.ims.dto.CntContractDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.CntContractBO")
@Table(name = "CNT_CONTRACT")
/**
 *
 * @author: hailh10
 */
public class CntContractBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CNT_CONTRACT_SEQ") })
	@Column(name = "CNT_CONTRACT_ID", length = 22)
	private java.lang.Long cntContractId;
	@Column(name = "CODE", length = 200)
	private java.lang.String code;
	@Column(name = "NAME", length = 1000)
	private java.lang.String name;
	@Column(name = "CONTRACT_CODE_KTTS", length = 200)
	private java.lang.String contractCodeKtts;
	@Column(name = "CONTENT", length = 4000)
	private java.lang.String content;
	@Column(name = "SIGN_DATE", length = 7)
	private java.util.Date signDate;
	@Column(name = "START_TIME", length = 7)
	private java.util.Date startTime;
	@Column(name = "END_TIME", length = 7)
	private java.util.Date endTime;
	@Column(name = "PRICE", length = 22)
	private java.lang.Double price;
	@Column(name = "APPENDIX_CONTRACT", length = 22)
	private java.lang.Double appendixContract;
	@Column(name = "NUM_STATION", length = 22)
	private java.lang.Double numStation;
	@Column(name = "BIDDING_PACKAGE_ID", length = 22)
	private java.lang.Long biddingPackageId;
	@Column(name = "CAT_PARTNER_ID", length = 22)
	private java.lang.Long catPartnerId;
	@Column(name = "SIGNER_PARTNER", length = 200)
	private java.lang.String signerPartner;
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long sysGroupId;
	@Column(name = "SIGNER_GROUP", length = 22)
	private java.lang.Long signerGroup;
	@Column(name = "SUPERVISOR", length = 200)
	private java.lang.String supervisor;
	@Column(name = "STATUS", length = 22)
	private java.lang.Long status;
	@Column(name = "FORMAL", length = 22)
	private java.lang.Double formal;
	@Column(name = "CONTRACT_TYPE", length = 22)
	private java.lang.Long contractType;
	@Column(name = "CNT_CONTRACT_PARENT_ID", length = 22)
	private java.lang.Double cntContractParentId;
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

	
	public java.lang.Long getCntContractId(){
		return cntContractId;
	}
	
	public void setCntContractId(java.lang.Long cntContractId)
	{
		this.cntContractId = cntContractId;
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
	
	public java.lang.String getContractCodeKtts(){
		return contractCodeKtts;
	}
	
	public void setContractCodeKtts(java.lang.String contractCodeKtts)
	{
		this.contractCodeKtts = contractCodeKtts;
	}
	
	public java.lang.String getContent(){
		return content;
	}
	
	public void setContent(java.lang.String content)
	{
		this.content = content;
	}
	
	public java.util.Date getSignDate(){
		return signDate;
	}
	
	public void setSignDate(java.util.Date signDate)
	{
		this.signDate = signDate;
	}
	
	public java.util.Date getStartTime(){
		return startTime;
	}
	
	public void setStartTime(java.util.Date startTime)
	{
		this.startTime = startTime;
	}
	
	public java.util.Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(java.util.Date endTime)
	{
		this.endTime = endTime;
	}
	
	public java.lang.Double getPrice(){
		return price;
	}
	
	public void setPrice(java.lang.Double price)
	{
		this.price = price;
	}
	
	public java.lang.Double getAppendixContract(){
		return appendixContract;
	}
	
	public void setAppendixContract(java.lang.Double appendixContract)
	{
		this.appendixContract = appendixContract;
	}
	
	public java.lang.Double getNumStation(){
		return numStation;
	}
	
	public void setNumStation(java.lang.Double numStation)
	{
		this.numStation = numStation;
	}
	
	public java.lang.Long getBiddingPackageId(){
		return biddingPackageId;
	}
	
	public void setBiddingPackageId(java.lang.Long biddingPackageId)
	{
		this.biddingPackageId = biddingPackageId;
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
	
	public java.lang.Long getSignerGroup(){
		return signerGroup;
	}
	
	public void setSignerGroup(java.lang.Long signerGroup)
	{
		this.signerGroup = signerGroup;
	}
	
	public java.lang.String getSupervisor(){
		return supervisor;
	}
	
	public void setSupervisor(java.lang.String supervisor)
	{
		this.supervisor = supervisor;
	}
	
	public java.lang.Long getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.Long status)
	{
		this.status = status;
	}
	
	public java.lang.Double getFormal(){
		return formal;
	}
	
	public void setFormal(java.lang.Double formal)
	{
		this.formal = formal;
	}
	
	public java.lang.Long getContractType(){
		return contractType;
	}
	
	public void setContractType(java.lang.Long contractType)
	{
		this.contractType = contractType;
	}
	
	public java.lang.Double getCntContractParentId(){
		return cntContractParentId;
	}
	
	public void setCntContractParentId(java.lang.Double cntContractParentId)
	{
		this.cntContractParentId = cntContractParentId;
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
    public CntContractDTO toDTO() {
        CntContractDTO cntContractDTO = new CntContractDTO(); 
        cntContractDTO.setCntContractId(this.cntContractId);		
        cntContractDTO.setCode(this.code);		
        cntContractDTO.setName(this.name);		
        cntContractDTO.setContractCodeKtts(this.contractCodeKtts);		
        cntContractDTO.setContent(this.content);		
        cntContractDTO.setSignDate(this.signDate);		
        cntContractDTO.setStartTime(this.startTime);		
        cntContractDTO.setEndTime(this.endTime);		
        cntContractDTO.setPrice(this.price);		
        cntContractDTO.setAppendixContract(this.appendixContract);		
        cntContractDTO.setNumStation(this.numStation);		
        cntContractDTO.setBiddingPackageId(this.biddingPackageId);		
        cntContractDTO.setCatPartnerId(this.catPartnerId);		
        cntContractDTO.setSignerPartner(this.signerPartner);		
        cntContractDTO.setSysGroupId(this.sysGroupId);		
        cntContractDTO.setSignerGroup(this.signerGroup);		
        cntContractDTO.setSupervisor(this.supervisor);		
        cntContractDTO.setStatus(this.status);		
        cntContractDTO.setFormal(this.formal);		
        cntContractDTO.setContractType(this.contractType);		
        cntContractDTO.setCntContractParentId(this.cntContractParentId);		
        cntContractDTO.setCreatedDate(this.createdDate);		
        cntContractDTO.setCreatedUserId(this.createdUserId);		
        cntContractDTO.setCreatedGroupId(this.createdGroupId);		
        cntContractDTO.setUpdatedDate(this.updatedDate);		
        cntContractDTO.setUpdatedUserId(this.updatedUserId);		
        cntContractDTO.setUpdatedGroupId(this.updatedGroupId);		
        return cntContractDTO;
    }
}
