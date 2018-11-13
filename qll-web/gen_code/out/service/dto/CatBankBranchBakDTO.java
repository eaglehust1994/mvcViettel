package com.viettel.ims.dto;

import com.viettel.ims.bo.CatBankBranchBakBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import java.util.Date;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CAT_BANK_BRANCH_BAKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatBankBranchBakDTO extends BaseFWDTOImpl<CatBankBranchBakBO> {

	private Long catBankBranchId;
	private String citadCode;
	private String bankCode;
	private String provinceCodeIm;
	private String branchName;
	private String provinceName;
	private Long isActive;
	private String levelGroup;
	private String address;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date creatorDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date creatorDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date creatorDateTo;
	private Long creatorId;
	private java.lang.String creatorName;
	private Long createdGroupId;
	private java.lang.String createdGroupName;
	private Long updatorId;
	private java.lang.String updatorName;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date updatorDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatorDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatorDateTo;
	private Long updatorGroupId;
	private java.lang.String updatorGroupName;
	private int size;
	private int page;
	private String orderBy;
	private String orderType;
	private int totalRecord;
	private String keySearch;//dùng cho autocomplete
	
	public String getKeySearch(){
		return keySearch;
	}
	public void setKeySearch(String key){
		this.keySearch=key;
	}
	

    @Override
    public CatBankBranchBakBO toModel() {
        CatBankBranchBakBO catBankBranchBakBO = new CatBankBranchBakBO();
        catBankBranchBakBO.setCatBankBranchId(this.catBankBranchId);		
        catBankBranchBakBO.setCitadCode(this.citadCode);		
        catBankBranchBakBO.setBankCode(this.bankCode);		
        catBankBranchBakBO.setProvinceCodeIm(this.provinceCodeIm);		
        catBankBranchBakBO.setBranchName(this.branchName);		
        catBankBranchBakBO.setProvinceName(this.provinceName);		
        catBankBranchBakBO.setIsActive(this.isActive);		
        catBankBranchBakBO.setLevelGroup(this.levelGroup);		
        catBankBranchBakBO.setAddress(this.address);		
        catBankBranchBakBO.setCreatorDate(this.creatorDate);		
        catBankBranchBakBO.setCreatorId(this.creatorId);		
        catBankBranchBakBO.setCreatedGroupId(this.createdGroupId);		
        catBankBranchBakBO.setUpdatorId(this.updatorId);		
        catBankBranchBakBO.setUpdatorDate(this.updatorDate);		
        catBankBranchBakBO.setUpdatorGroupId(this.updatorGroupId);		
        return catBankBranchBakBO;
    }

    @Override
     public Long getFWModelId() {
        return catBankBranchId;
    }
   
    @Override
    public String catchName() {
        return getCatBankBranchId().toString();
    }
	
	@JsonProperty("catBankBranchId")
    public Long getCatBankBranchId(){
		return catBankBranchId;
    }
	
    public void setCatBankBranchId(Long catBankBranchId){
		this.catBankBranchId = catBankBranchId;
    }
	
	
	@JsonProperty("citadCode")
    public String getCitadCode(){
		return citadCode;
    }
	
    public void setCitadCode(String citadCode){
		this.citadCode = citadCode;
    }
	
	
	@JsonProperty("bankCode")
    public String getBankCode(){
		return bankCode;
    }
	
    public void setBankCode(String bankCode){
		this.bankCode = bankCode;
    }
	
	
	@JsonProperty("provinceCodeIm")
    public String getProvinceCodeIm(){
		return provinceCodeIm;
    }
	
    public void setProvinceCodeIm(String provinceCodeIm){
		this.provinceCodeIm = provinceCodeIm;
    }
	
	
	@JsonProperty("branchName")
    public String getBranchName(){
		return branchName;
    }
	
    public void setBranchName(String branchName){
		this.branchName = branchName;
    }
	
	
	@JsonProperty("provinceName")
    public String getProvinceName(){
		return provinceName;
    }
	
    public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
    }
	
	
	@JsonProperty("isActive")
    public Long getIsActive(){
		return isActive;
    }
	
    public void setIsActive(Long isActive){
		this.isActive = isActive;
    }
	
	
	@JsonProperty("levelGroup")
    public String getLevelGroup(){
		return levelGroup;
    }
	
    public void setLevelGroup(String levelGroup){
		this.levelGroup = levelGroup;
    }
	
	
	@JsonProperty("address")
    public String getAddress(){
		return address;
    }
	
    public void setAddress(String address){
		this.address = address;
    }
	
	
	@JsonProperty("creatorDate")
    public Date getCreatorDate(){
		return creatorDate;
    }
	
    public void setCreatorDate(Date creatorDate){
		this.creatorDate = creatorDate;
    }
	
	public java.util.Date getCreatorDateFrom() {
    	return creatorDateFrom;
    }
	
    public void setCreatorDateFrom(java.util.Date creatorDateFrom) {
    	this.creatorDateFrom = creatorDateFrom;
    }
	
	public java.util.Date getCreatorDateTo() {
    	return creatorDateTo;
    }
	
    public void setCreatorDateTo(java.util.Date creatorDateTo) {
    	this.creatorDateTo = creatorDateTo;
    }
	
	@JsonProperty("creatorId")
    public Long getCreatorId(){
		return creatorId;
    }
	
    public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
    }
	
	@JsonProperty("creatorName")
    public java.lang.String getCreatorName(){
		return creatorName;
    }
	
    public void setCreatorName(java.lang.String creatorName){
		this.creatorName = creatorName;
    }
	
	
	@JsonProperty("createdGroupId")
    public Long getCreatedGroupId(){
		return createdGroupId;
    }
	
    public void setCreatedGroupId(Long createdGroupId){
		this.createdGroupId = createdGroupId;
    }
	
	@JsonProperty("createdGroupName")
    public java.lang.String getCreatedGroupName(){
		return createdGroupName;
    }
	
    public void setCreatedGroupName(java.lang.String createdGroupName){
		this.createdGroupName = createdGroupName;
    }
	
	
	@JsonProperty("updatorId")
    public Long getUpdatorId(){
		return updatorId;
    }
	
    public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
    }
	
	@JsonProperty("updatorName")
    public java.lang.String getUpdatorName(){
		return updatorName;
    }
	
    public void setUpdatorName(java.lang.String updatorName){
		this.updatorName = updatorName;
    }
	
	
	@JsonProperty("updatorDate")
    public Date getUpdatorDate(){
		return updatorDate;
    }
	
    public void setUpdatorDate(Date updatorDate){
		this.updatorDate = updatorDate;
    }
	
	public java.util.Date getUpdatorDateFrom() {
    	return updatorDateFrom;
    }
	
    public void setUpdatorDateFrom(java.util.Date updatorDateFrom) {
    	this.updatorDateFrom = updatorDateFrom;
    }
	
	public java.util.Date getUpdatorDateTo() {
    	return updatorDateTo;
    }
	
    public void setUpdatorDateTo(java.util.Date updatorDateTo) {
    	this.updatorDateTo = updatorDateTo;
    }
	
	@JsonProperty("updatorGroupId")
    public Long getUpdatorGroupId(){
		return updatorGroupId;
    }
	
    public void setUpdatorGroupId(Long updatorGroupId){
		this.updatorGroupId = updatorGroupId;
    }
	
	@JsonProperty("updatorGroupName")
    public java.lang.String getUpdatorGroupName(){
		return updatorGroupName;
    }
	
    public void setUpdatorGroupName(java.lang.String updatorGroupName){
		this.updatorGroupName = updatorGroupName;
    }
	
	
	@JsonProperty("page")
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JsonProperty("size")
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@JsonProperty("orderType")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		if("asc".equals(orderType)||"desc".equals(orderType)){
			this.orderType=orderType;
		}else{
			this.orderType="";
		}
				
	}

	@JsonProperty("orderBy")
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	;
	
	public int getTotalRecord(){
		return totalRecord;
	}
	
	public void setTotalRecord(int total){
			totalRecord=total;
	}
}
