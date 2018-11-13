package com.viettel.ims.dto;

import com.viettel.ims.bo.SysGroupBO;
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
@XmlRootElement(name = "SYS_GROUPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysGroupDTO extends imsBaseDTO<SysGroupBO> {

	private java.lang.Long sysGroupId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.Long parentId;
	private java.lang.String parentName;
	private java.lang.String status;
	private java.lang.String path;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date effectDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date effectDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date effectDateTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endDate;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endDateFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endDateTo;


    @Override
    public SysGroupBO toModel() {
        SysGroupBO sysGroupBO = new SysGroupBO();
        sysGroupBO.setSysGroupId(this.sysGroupId);
        sysGroupBO.setCode(this.code);
        sysGroupBO.setName(this.name);
        sysGroupBO.setParentId(this.parentId);
        sysGroupBO.setStatus(this.status);
        sysGroupBO.setPath(this.path);
        sysGroupBO.setEffectDate(this.effectDate);
        sysGroupBO.setEndDate(this.endDate);
        return sysGroupBO;
    }

    @Override
     public Long getFWModelId() {
        return sysGroupId;
    }
   
    @Override
    public String catchName() {
        return getSysGroupId().toString();
    }
	
	@JsonProperty("sysGroupId")
    public java.lang.Long getSysGroupId(){
		return sysGroupId;
    }
	
    public void setSysGroupId(java.lang.Long sysGroupId){
		this.sysGroupId = sysGroupId;
    }	
	
	@JsonProperty("code")
    public java.lang.String getCode(){
		return code;
    }
	
    public void setCode(java.lang.String code){
		this.code = code;
    }	
	
	@JsonProperty("name")
    public java.lang.String getName(){
		return name;
    }
	
    public void setName(java.lang.String name){
		this.name = name;
    }	
	
	@JsonProperty("parentId")
    public java.lang.Long getParentId(){
		return parentId;
    }
	
    public void setParentId(java.lang.Long parentId){
		this.parentId = parentId;
    }	
	
	@JsonProperty("parentName")
    public java.lang.String getParentName(){
		return parentName;
    }
	
    public void setParentName(java.lang.String parentName){
		this.parentName = parentName;
    }	
	
	@JsonProperty("status")
    public java.lang.String getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.String status){
		this.status = status;
    }	
	
	@JsonProperty("path")
    public java.lang.String getPath(){
		return path;
    }
	
    public void setPath(java.lang.String path){
		this.path = path;
    }	
	
	@JsonProperty("effectDate")
    public java.util.Date getEffectDate(){
		return effectDate;
    }
	
    public void setEffectDate(java.util.Date effectDate){
		this.effectDate = effectDate;
    }	
	
	public java.util.Date getEffectDateFrom() {
    	return effectDateFrom;
    }
	
    public void setEffectDateFrom(java.util.Date effectDateFrom) {
    	this.effectDateFrom = effectDateFrom;
    }
	
	public java.util.Date getEffectDateTo() {
    	return effectDateTo;
    }
	
    public void setEffectDateTo(java.util.Date effectDateTo) {
    	this.effectDateTo = effectDateTo;
    }
	
	@JsonProperty("endDate")
    public java.util.Date getEndDate(){
		return endDate;
    }
	
    public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
    }	
	
	public java.util.Date getEndDateFrom() {
    	return endDateFrom;
    }
	
    public void setEndDateFrom(java.util.Date endDateFrom) {
    	this.endDateFrom = endDateFrom;
    }
	
	public java.util.Date getEndDateTo() {
    	return endDateTo;
    }
	
    public void setEndDateTo(java.util.Date endDateTo) {
    	this.endDateTo = endDateTo;
    }
	
}
