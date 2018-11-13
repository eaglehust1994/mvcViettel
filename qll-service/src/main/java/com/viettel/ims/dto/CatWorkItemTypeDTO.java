package com.viettel.ims.dto;

import com.viettel.ims.bo.CatWorkItemTypeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CAT_WORK_ITEM_TYPEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatWorkItemTypeDTO extends imsBaseDTO<CatWorkItemTypeBO> {

	private java.lang.Long catWorkItemTypeId;
	private java.lang.String name;
	private java.lang.String code;
	private java.lang.String status;
	private java.lang.String description;
	private java.lang.Long catConstructionTypeId;
	private java.lang.String catConstructionTypeName;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateTo;
	private java.lang.Long createdUser;
	private java.lang.Long updatedUser;
	
    @Override
    public CatWorkItemTypeBO toModel() {
        CatWorkItemTypeBO catWorkItemTypeBO = new CatWorkItemTypeBO();
        catWorkItemTypeBO.setCatWorkItemTypeId(this.catWorkItemTypeId);
        catWorkItemTypeBO.setName(this.name);
        catWorkItemTypeBO.setCode(this.code);
        catWorkItemTypeBO.setStatus(this.status);
        catWorkItemTypeBO.setDescription(this.description);
        catWorkItemTypeBO.setCatConstructionTypeId(this.catConstructionTypeId);
        catWorkItemTypeBO.setCreatedDate(this.createdDate);
        catWorkItemTypeBO.setUpdatedDate(this.updatedDate);
        catWorkItemTypeBO.setCreatedUser(this.createdUser);
        catWorkItemTypeBO.setUpdatedUser(this.updatedUser);
        return catWorkItemTypeBO;
    }

    @Override
     public Long getFWModelId() {
        return catWorkItemTypeId;
    }
   
    @Override
    public String catchName() {
        return getCatWorkItemTypeId().toString();
    }
	
	@JsonProperty("catWorkItemTypeId")
    public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
    }
	
    public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId){
		this.catWorkItemTypeId = catWorkItemTypeId;
    }	
	
	@JsonProperty("name")
    public java.lang.String getName(){
		return name;
    }
	
    public void setName(java.lang.String name){
		this.name = name;
    }	
	
	@JsonProperty("code")
    public java.lang.String getCode(){
		return code;
    }
	
    public void setCode(java.lang.String code){
		this.code = code;
    }	
	
	@JsonProperty("status")
    public java.lang.String getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.String status){
		this.status = status;
    }	
	
	@JsonProperty("description")
    public java.lang.String getDescription(){
		return description;
    }
	
    public void setDescription(java.lang.String description){
		this.description = description;
    }	
	
	@JsonProperty("catConstructionTypeId")
    public java.lang.Long getCatConstructionTypeId(){
		return catConstructionTypeId;
    }
	
    public void setCatConstructionTypeId(java.lang.Long catConstructionTypeId){
		this.catConstructionTypeId = catConstructionTypeId;
    }	
	
	@JsonProperty("catConstructionTypeName")
    public java.lang.String getCatConstructionTypeName(){
		return catConstructionTypeName;
    }
	
    public void setCatConstructionTypeName(java.lang.String catConstructionTypeName){
		this.catConstructionTypeName = catConstructionTypeName;
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
	
	@JsonProperty("createdUser")
    public java.lang.Long getCreatedUser(){
		return createdUser;
    }
	
    public void setCreatedUser(java.lang.Long createdUser){
		this.createdUser = createdUser;
    }	
	
	@JsonProperty("updatedUser")
    public java.lang.Long getUpdatedUser(){
		return updatedUser;
    }
	
    public void setUpdatedUser(java.lang.Long updatedUser){
		this.updatedUser = updatedUser;
    }	
	
}
