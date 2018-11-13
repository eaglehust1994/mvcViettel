package com.viettel.ims.dto;

import com.viettel.ims.bo.CatTaskBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.utils.CustomJsonDateDeserializer;
import com.viettel.utils.CustomJsonDateSerializer;
//import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "CAT_TASKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatTaskDTO extends imsBaseDTO<CatTaskBO> {

	private java.lang.Long createdUser;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedDateTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdDateTo;
	private java.lang.String status;
	private java.lang.String code;
	private java.lang.String description;
	private java.lang.String name;
	private java.lang.Long catWorkItemTypeId;
	private java.lang.String catWorkItemTypeName;
	private java.lang.Long catUnitId;
	private java.lang.String catUnitName;
	private java.lang.Long catTaskId;
	private java.lang.Long updatedUser;


    @Override
    public CatTaskBO toModel() {
        CatTaskBO catTaskBO = new CatTaskBO();
        catTaskBO.setCreatedUser(this.createdUser);
        catTaskBO.setUpdatedDate(this.updatedDate);
        catTaskBO.setCreatedDate(this.createdDate);
        catTaskBO.setStatus(this.status);
        catTaskBO.setCode(this.code);
        catTaskBO.setDescription(this.description);
        catTaskBO.setName(this.name);
        catTaskBO.setCatWorkItemTypeId(this.catWorkItemTypeId);
        catTaskBO.setCatUnitId(this.catUnitId);
        catTaskBO.setCatTaskId(this.catTaskId);
        catTaskBO.setUpdatedUser(this.updatedUser);
        return catTaskBO;
    }

	@JsonProperty("createdUser")
    public java.lang.Long getCreatedUser(){
		return createdUser;
    }
	
    public void setCreatedUser(java.lang.Long createdUser){
		this.createdUser = createdUser;
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
	
	@JsonProperty("status")
    public java.lang.String getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.String status){
		this.status = status;
    }	
	
	@JsonProperty("code")
    public java.lang.String getCode(){
		return code;
    }
	
    public void setCode(java.lang.String code){
		this.code = code;
    }	
	
	@JsonProperty("description")
    public java.lang.String getDescription(){
		return description;
    }
	
    public void setDescription(java.lang.String description){
		this.description = description;
    }	
	
	@JsonProperty("name")
    public java.lang.String getName(){
		return name;
    }
	
    public void setName(java.lang.String name){
		this.name = name;
    }	
	
	@JsonProperty("catWorkItemTypeId")
    public java.lang.Long getCatWorkItemTypeId(){
		return catWorkItemTypeId;
    }
	
    public void setCatWorkItemTypeId(java.lang.Long catWorkItemTypeId){
		this.catWorkItemTypeId = catWorkItemTypeId;
    }	
	
	@JsonProperty("catWorkItemTypeName")
    public java.lang.String getCatWorkItemTypeName(){
		return catWorkItemTypeName;
    }
	
    public void setCatWorkItemTypeName(java.lang.String catWorkItemTypeName){
		this.catWorkItemTypeName = catWorkItemTypeName;
    }	
	
	@JsonProperty("catUnitId")
    public java.lang.Long getCatUnitId(){
		return catUnitId;
    }
	
    public void setCatUnitId(java.lang.Long catUnitId){
		this.catUnitId = catUnitId;
    }	
	
	@JsonProperty("catUnitName")
    public java.lang.String getCatUnitName(){
		return catUnitName;
    }
	
    public void setCatUnitName(java.lang.String catUnitName){
		this.catUnitName = catUnitName;
    }	
	
    @Override
     public Long getFWModelId() {
        return catTaskId;
    }
   
    @Override
    public String catchName() {
        return getCatTaskId().toString();
    }
	
	@JsonProperty("catTaskId")
    public java.lang.Long getCatTaskId(){
		return catTaskId;
    }
	
    public void setCatTaskId(java.lang.Long catTaskId){
		this.catTaskId = catTaskId;
    }	
	
	@JsonProperty("updatedUser")
    public java.lang.Long getUpdatedUser(){
		return updatedUser;
    }
	
    public void setUpdatedUser(java.lang.Long updatedUser){
		this.updatedUser = updatedUser;
    }	
	
}
