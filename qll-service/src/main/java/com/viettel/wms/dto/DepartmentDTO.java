/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.wms.bo.DepartmentBO;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "DEPARTMENTBO")
public class DepartmentDTO extends wmsBaseDTO<DepartmentBO> {
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date endDate;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date effectDate;
private java.lang.String path;
private java.lang.String code;
private java.lang.String name;
private java.lang.Long parentId;
private java.lang.String status;
private java.lang.Long departmentId;
private java.lang.String parentName;

    @Override
    public DepartmentBO toModel() {
        DepartmentBO departmentBO = new DepartmentBO();
        departmentBO.setEndDate(this.endDate);
        departmentBO.setEffectDate(this.effectDate);
        departmentBO.setPath(this.path);
        departmentBO.setCode(this.code);
        departmentBO.setName(this.name);
        departmentBO.setParentId(this.parentId);
        departmentBO.setStatus(this.status);
        departmentBO.setDepartmentId(this.departmentId);
        return departmentBO;
    }

    public java.util.Date getEndDate(){
    return endDate;
    }
    public void setEndDate(java.util.Date endDate)
    {
    this.endDate = endDate;
    }
    
    public java.util.Date getEffectDate(){
    return effectDate;
    }
    public void setEffectDate(java.util.Date effectDate)
    {
    this.effectDate = effectDate;
    }
    
    public java.lang.String getPath(){
    return path;
    }
    public void setPath(java.lang.String path)
    {
    this.path = path;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code.toUpperCase();
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.String getParentName() {
  		return parentName;
  	}


  	public void setParentName(java.lang.String parentName) {
  		this.parentName = parentName;
  	}
    
    @Override
     public Long getFWModelId() {
        return departmentId;
    }
   
    @Override
    public String catchName() {
        return getDepartmentId().toString();
    }
    public java.lang.Long getDepartmentId(){
    return departmentId;
    }
    public void setDepartmentId(java.lang.Long departmentId)
    {
    this.departmentId = departmentId;
    }
    
   
}
