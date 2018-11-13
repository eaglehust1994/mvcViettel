/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "V_CONSTRUCTION_HCQTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VConstructionsHcqtSearchDTO implements Serializable{

	private static final long serialVersionUID = -7525957447922763873L;

	private java.lang.String contractNo;
	private java.lang.String  contractCode;
	private java.lang.Double projectId;
	private java.lang.String investProjectName;
	private java.lang.String constrtCode;
	private java.lang.String constrtName;
	private java.lang.String partnerName;
	private java.lang.Double partnerId;
	private java.lang.Double constrType;
	private java.lang.Double provinceId;
	private java.util.Date acceptStartDate;
	private java.lang.Long take;
	private java.lang.Long page;
	private java.lang.Long pageSize;
	private java.lang.Integer total;
	
	private java.lang.Double employeeId;
	
	//Tim kiem cong trinh: Quan ly de nghi va tham dinh: 1
	//Tim kiem cong trinh chung: 0
	
	private java.lang.Integer checktype = 0; 
	
	/**
	 * @return the acceptStartDate
	 */
	public java.util.Date getAcceptStartDate() {
		return acceptStartDate;
	}
	/**
	 * @param acceptStartDate the acceptStartDate to set
	 */
	public void setAcceptStartDate(java.util.Date acceptStartDate) {
		this.acceptStartDate = acceptStartDate;
	}
	public java.lang.String getContractNo() {
		return contractNo;
	}
	public void setContractNo(java.lang.String contractNo) {
		this.contractNo = contractNo;
	}
	public java.lang.Double getProjectId() {
		return projectId;
	}
	public void setProjectId(java.lang.Double projectId) {
		this.projectId = projectId;
	}
	public java.lang.String getConstrtCode() {
		return constrtCode;
	}
	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}
	public java.lang.String getConstrtName() {
		return constrtName;
	}
	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}
	public java.lang.Double getConstrType() {
		return constrType;
	}
	public void setConstrType(java.lang.Double constrType) {
		this.constrType = constrType;
	}
	public java.lang.Double getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(java.lang.Double provinceId) {
		this.provinceId = provinceId;
	}
	public java.lang.String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(java.lang.String partnerName) {
		this.partnerName = partnerName;
	}
	public java.lang.Double getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(java.lang.Double partnerId) {
		this.partnerId = partnerId;
	}
	public java.lang.String getInvestProjectName() {
		return investProjectName;
	}
	public void setInvestProjectName(java.lang.String investProjectName) {
		this.investProjectName = investProjectName;
	}
	public java.lang.String getContractCode() {
		return contractCode;
	}
	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * @return the take
	 */
	public java.lang.Long getTake() {
		return take;
	}
	/**
	 * @param take the take to set
	 */
	public void setTake(java.lang.Long take) {
		this.take = take;
	}
	/**
	 * @return the pageSize
	 */
	public java.lang.Long getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(java.lang.Long pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the page
	 */
	public java.lang.Long getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(java.lang.Long page) {
		this.page = page;
	}
	/**
	 * @return the total
	 */
	public java.lang.Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(java.lang.Integer total) {
		this.total = total;
	}
	public java.lang.Integer getChecktype() {
		return checktype;
	}
	public void setChecktype(java.lang.Integer checktype) {
		this.checktype = checktype;
	}
	public java.lang.Double getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(java.lang.Double employeeId) {
		this.employeeId = employeeId;
	}


   
}
