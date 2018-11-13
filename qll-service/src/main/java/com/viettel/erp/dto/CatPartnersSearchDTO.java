/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CatPartnersBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CAT_PARTNERSBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatPartnersSearchDTO implements Serializable {


private java.lang.String partnerName;
private java.lang.String stationCodeExpected;
private java.lang.String address;
private java.lang.String phone;
private java.lang.String fax;
private java.lang.String taxCode;
private java.lang.String name;

private java.lang.Long page;
private java.lang.Long pageSize;
private java.lang.Boolean isSize;

public java.lang.String getPartnerName() {
	return partnerName;
}
public void setPartnerName(java.lang.String partnerName) {
	this.partnerName = partnerName;
}
public java.lang.String getStationCodeExpected() {
	return stationCodeExpected;
}
public void setStationCodeExpected(java.lang.String stationCodeExpected) {
	this.stationCodeExpected = stationCodeExpected;
}
public java.lang.String getAddress() {
	return address;
}
public void setAddress(java.lang.String address) {
	this.address = address;
}
public java.lang.String getPhone() {
	return phone;
}
public void setPhone(java.lang.String phone) {
	this.phone = phone;
}
public java.lang.String getFax() {
	return fax;
}
public void setFax(java.lang.String fax) {
	this.fax = fax;
}
public java.lang.String getTaxCode() {
	return taxCode;
}
public void setTaxCode(java.lang.String taxCode) {
	this.taxCode = taxCode;
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
public java.lang.String getName() {
	return name;
}
public void setName(java.lang.String name) {
	this.name = name;
}



   
}
