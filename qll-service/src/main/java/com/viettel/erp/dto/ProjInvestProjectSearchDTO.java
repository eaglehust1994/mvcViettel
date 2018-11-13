/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ProjInvestProjectBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "PROJ_INVEST_PROJECTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjInvestProjectSearchDTO implements Serializable {

private java.lang.String name;
private java.lang.String code;
private java.lang.Double statusCode;
private java.lang.Long type;

private java.lang.Long page;
private java.lang.Long pageSize;
private java.lang.Boolean isSize;   
    
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
	public java.lang.Double getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(java.lang.Double statusCode) {
		this.statusCode = statusCode;
	}
	public java.lang.Long getType() {
		return type;
	}
	public void setType(java.lang.Long type) {
		this.type = type;
	}
	public java.lang.Long getPage() {
		return page;
	}
	public void setPage(java.lang.Long page) {
		this.page = page;
	}
	public java.lang.Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(java.lang.Long pageSize) {
		this.pageSize = pageSize;
	}
	public java.lang.Boolean getIsSize() {
		return isSize;
	}
	public void setIsSize(java.lang.Boolean isSize) {
		this.isSize = isSize;
	}
    
   
   
}
