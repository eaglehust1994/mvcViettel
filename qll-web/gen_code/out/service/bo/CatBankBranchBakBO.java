package com.viettel.ims.bo;

import com.viettel.ims.dto.CatBankBranchBakDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "CAT_BANK_BRANCH_BAK")
/**
 *
 * @author: hailh10
 */
public class CatBankBranchBakBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "CAT_BANK_BRANCH_BAK_SEQ") })
	@Column(name = "CAT_BANK_BRANCH_ID", length = 0)
	private Long catBankBranchId;
	@Column(name = "CITAD_CODE", length = 200)
	private String citadCode;
	@Column(name = "BANK_CODE", length = 200)
	private String bankCode;
	@Column(name = "PROVINCE_CODE_IM", length = 200)
	private String provinceCodeIm;
	@Column(name = "BRANCH_NAME", length = 500)
	private String branchName;
	@Column(name = "PROVINCE_NAME", length = 500)
	private String provinceName;
	@Column(name = "IS_ACTIVE", length = 0)
	private Long isActive;
	@Column(name = "LEVEL_GROUP", length = 300)
	private String levelGroup;
	@Column(name = "ADDRESS", length = 500)
	private String address;
	@Column(name = "CREATOR_DATE", length = 11)
	private Date creatorDate;
	@Column(name = "CREATOR_ID", length = 11)
	private Long creatorId;
	@Column(name = "CREATED_GROUP_ID", length = 11)
	private Long createdGroupId;
	@Column(name = "UPDATOR_ID", length = 11)
	private Long updatorId;
	@Column(name = "UPDATOR_DATE", length = 11)
	private Date updatorDate;
	@Column(name = "UPDATOR_GROUP_ID", length = 11)
	private Long updatorGroupId;

	public Long getCatBankBranchId(){
		return catBankBranchId;
	}
	public void setCatBankBranchId(Long catBankBranchId)
	{
		this.catBankBranchId = catBankBranchId;
	}
	public String getCitadCode(){
		return citadCode;
	}
	public void setCitadCode(String citadCode)
	{
		this.citadCode = citadCode;
	}
	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode)
	{
		this.bankCode = bankCode;
	}
	public String getProvinceCodeIm(){
		return provinceCodeIm;
	}
	public void setProvinceCodeIm(String provinceCodeIm)
	{
		this.provinceCodeIm = provinceCodeIm;
	}
	public String getBranchName(){
		return branchName;
	}
	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}
	public String getProvinceName(){
		return provinceName;
	}
	public void setProvinceName(String provinceName)
	{
		this.provinceName = provinceName;
	}
	public Long getIsActive(){
		return isActive;
	}
	public void setIsActive(Long isActive)
	{
		this.isActive = isActive;
	}
	public String getLevelGroup(){
		return levelGroup;
	}
	public void setLevelGroup(String levelGroup)
	{
		this.levelGroup = levelGroup;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Date getCreatorDate(){
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate)
	{
		this.creatorDate = creatorDate;
	}
	public Long getCreatorId(){
		return creatorId;
	}
	public void setCreatorId(Long creatorId)
	{
		this.creatorId = creatorId;
	}
	public Long getCreatedGroupId(){
		return createdGroupId;
	}
	public void setCreatedGroupId(Long createdGroupId)
	{
		this.createdGroupId = createdGroupId;
	}
	public Long getUpdatorId(){
		return updatorId;
	}
	public void setUpdatorId(Long updatorId)
	{
		this.updatorId = updatorId;
	}
	public Date getUpdatorDate(){
		return updatorDate;
	}
	public void setUpdatorDate(Date updatorDate)
	{
		this.updatorDate = updatorDate;
	}
	public Long getUpdatorGroupId(){
		return updatorGroupId;
	}
	public void setUpdatorGroupId(Long updatorGroupId)
	{
		this.updatorGroupId = updatorGroupId;
	}
   
    @Override
    public CatBankBranchBakDTO toDTO() {
        CatBankBranchBakDTO catBankBranchBakDTO = new CatBankBranchBakDTO(); 
        catBankBranchBakDTO.setCatBankBranchId(this.catBankBranchId);		
        catBankBranchBakDTO.setCitadCode(this.citadCode);		
        catBankBranchBakDTO.setBankCode(this.bankCode);		
        catBankBranchBakDTO.setProvinceCodeIm(this.provinceCodeIm);		
        catBankBranchBakDTO.setBranchName(this.branchName);		
        catBankBranchBakDTO.setProvinceName(this.provinceName);		
        catBankBranchBakDTO.setIsActive(this.isActive);		
        catBankBranchBakDTO.setLevelGroup(this.levelGroup);		
        catBankBranchBakDTO.setAddress(this.address);		
        catBankBranchBakDTO.setCreatorDate(this.creatorDate);		
        catBankBranchBakDTO.setCreatorId(this.creatorId);		
        catBankBranchBakDTO.setCreatedGroupId(this.createdGroupId);		
        catBankBranchBakDTO.setUpdatorId(this.updatorId);		
        catBankBranchBakDTO.setUpdatorDate(this.updatorDate);		
        catBankBranchBakDTO.setUpdatorGroupId(this.updatorGroupId);		
        return catBankBranchBakDTO;
    }
}
