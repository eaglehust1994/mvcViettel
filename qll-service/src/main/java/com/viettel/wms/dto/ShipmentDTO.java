/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.ShipmentBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "SHIPMENTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentDTO extends wmsBaseDTO<ShipmentBO> {

private java.lang.Double totalOriginMoney;
private java.lang.Double totalFee;
private java.lang.Double totalTax;
private java.lang.Double totalMoney;
private java.lang.String orderCheckCode;
private java.lang.String reportCheckCode;
private java.lang.Long shipmentId;
private java.lang.String code;
private java.lang.Long contractId;
private java.lang.String contractCode;
private java.lang.String type;
private java.lang.Long projInvestProjectId;
private java.lang.String projectCode;
private java.lang.String shiper;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date shiperDate;
private java.lang.String shipPlace;
private java.lang.String customsProcedure;
private java.lang.String description;
private java.lang.String fullName;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.Long createdDeptId;
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.String status;
@JsonSerialize(using = JsonDateSerializerDate.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date cancelDate;
private java.lang.Long cancelUserId;
private java.lang.String cancelReasonName;
private java.lang.String cancelDescription;
private java.lang.String feeDescription;
private List<String> listStatus;
private java.lang.String loginName;
private java.lang.String createdDeptName;
private java.lang.String manufacturerName;
private java.lang.String producingCountryName;
private java.lang.Double amount;
private java.lang.String goodsUnitName;
private java.lang.String partNumber;
private java.lang.String serial;
private List<ShipmentGoodsDTO> lstShipmentGoods;
private List<ShipmentTaxDTO> lstShipmentTax;
private List<ShipmentGoodsDetailDTO> lstShipmentDetail;
private java.lang.Long cntTypeId;
private java.util.List<Long> lstCreatedDeptId;



	public java.util.List<Long> getLstCreatedDeptId() {
	return lstCreatedDeptId;
}

public void setLstCreatedDeptId(java.util.List<Long> lstCreatedDeptId) {
	this.lstCreatedDeptId = lstCreatedDeptId;
}

	public java.lang.Long getCntTypeId() {
	return cntTypeId;
}

public void setCntTypeId(java.lang.Long cntTypeId) {
	this.cntTypeId = cntTypeId;
}

	public List<ShipmentGoodsDetailDTO> getLstShipmentDetail() {
	return lstShipmentDetail;
}

public void setLstShipmentDetail(List<ShipmentGoodsDetailDTO> lstShipmentDetail) {
	this.lstShipmentDetail = lstShipmentDetail;
}

	public List<ShipmentTaxDTO> getLstShipmentTax() {
	return lstShipmentTax;
}

public void setLstShipmentTax(List<ShipmentTaxDTO> lstShipmentTax) {
	this.lstShipmentTax = lstShipmentTax;
}

	public List<ShipmentGoodsDTO> getLstShipmentGoods() {
	return lstShipmentGoods;
}

public void setLstShipmentGoods(List<ShipmentGoodsDTO> lstShipmentGoods) {
	this.lstShipmentGoods = lstShipmentGoods;
}

	public java.lang.String getPartNumber() {
	return partNumber;
}

public void setPartNumber(java.lang.String partNumber) {
	this.partNumber = partNumber;
}

public java.lang.String getSerial() {
	return serial;
}

public void setSerial(java.lang.String serial) {
	this.serial = serial;
}

public java.lang.Double getAmount() {
	return amount;
}

	public java.lang.String getGoodsUnitName() {
	return goodsUnitName;
}

public void setGoodsUnitName(java.lang.String goodsUnitName) {
	this.goodsUnitName = goodsUnitName;
}

public void setAmount(java.lang.Double amount) {
	this.amount = amount;
}

	public java.lang.String getManufacturerName() {
	return manufacturerName;
}

public void setManufacturerName(java.lang.String manufacturerName) {
	this.manufacturerName = manufacturerName;
}

public java.lang.String getProducingCountryName() {
	return producingCountryName;
}

public void setProducingCountryName(java.lang.String producingCountryName) {
	this.producingCountryName = producingCountryName;
}



	@Override
    public ShipmentBO toModel() {
        ShipmentBO shipmentBO = new ShipmentBO();
        shipmentBO.setTotalOriginMoney(this.totalOriginMoney);
        shipmentBO.setTotalFee(this.totalFee);
        shipmentBO.setTotalTax(this.totalTax);
        shipmentBO.setTotalMoney(this.totalMoney);
        shipmentBO.setOrderCheckCode(this.orderCheckCode);
        shipmentBO.setReportCheckCode(this.reportCheckCode);
        shipmentBO.setShipmentId(this.shipmentId);
        shipmentBO.setCode(this.code);
        shipmentBO.setContractId(this.contractId);
        shipmentBO.setContractCode(this.contractCode);
        shipmentBO.setType(this.type);
        shipmentBO.setProjInvestProjectId(this.projInvestProjectId);
        shipmentBO.setProjectCode(this.projectCode);
        shipmentBO.setShiper(this.shiper);
        shipmentBO.setShiperDate(this.shiperDate);
        shipmentBO.setShipPlace(this.shipPlace);
        shipmentBO.setCustomsProcedure(this.customsProcedure);
        shipmentBO.setDescription(this.description);
        shipmentBO.setCreatedDate(this.createdDate);
        shipmentBO.setCreatedBy(this.createdBy);
        shipmentBO.setCreatedDeptId(this.createdDeptId);
        shipmentBO.setUpdatedBy(this.updatedBy);
        shipmentBO.setUpdatedDate(this.updatedDate);
        shipmentBO.setStatus(this.status);
        shipmentBO.setCancelDate(this.cancelDate);
        shipmentBO.setCancelUserId(this.cancelUserId);
        shipmentBO.setCancelReasonName(this.cancelReasonName);
        shipmentBO.setCancelDescription(this.cancelDescription);
        shipmentBO.setFeeDescription(this.feeDescription);
        return shipmentBO;
    }
    
    public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getCreatedDeptName() {
		return createdDeptName;
	}

	public void setCreatedDeptName(java.lang.String createdDeptName) {
		this.createdDeptName = createdDeptName;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.Double getTotalOriginMoney(){
    return totalOriginMoney;
    }
    public void setTotalOriginMoney(java.lang.Double totalOriginMoney)
    {
    this.totalOriginMoney = totalOriginMoney;
    }
    
    public java.lang.Double getTotalFee(){
    return totalFee;
    }
    public void setTotalFee(java.lang.Double totalFee)
    {
    this.totalFee = totalFee;
    }
    
    public java.lang.Double getTotalTax(){
    return totalTax;
    }
    public void setTotalTax(java.lang.Double totalTax)
    {
    this.totalTax = totalTax;
    }
    
    public java.lang.Double getTotalMoney(){
    return totalMoney;
    }
    public void setTotalMoney(java.lang.Double totalMoney)
    {
    this.totalMoney = totalMoney;
    }
    
    public java.lang.String getOrderCheckCode(){
    return orderCheckCode;
    }
    public void setOrderCheckCode(java.lang.String orderCheckCode)
    {
    this.orderCheckCode = orderCheckCode;
    }
    
    public java.lang.String getReportCheckCode(){
    return reportCheckCode;
    }
    public void setReportCheckCode(java.lang.String reportCheckCode)
    {
    this.reportCheckCode = reportCheckCode;
    }
    
    @Override
     public Long getFWModelId() {
        return shipmentId;
    }
   
    @Override
    public String catchName() {
        return getShipmentId().toString();
    }
    public java.lang.Long getShipmentId(){
    return shipmentId;
    }
    public void setShipmentId(java.lang.Long shipmentId)
    {
    this.shipmentId = shipmentId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.String getContractCode(){
    return contractCode;
    }
    public void setContractCode(java.lang.String contractCode)
    {
    this.contractCode = contractCode;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.Long getProjInvestProjectId(){
    return projInvestProjectId;
    }
    public void setProjInvestProjectId(java.lang.Long projInvestProjectId)
    {
    this.projInvestProjectId = projInvestProjectId;
    }
    
    public java.lang.String getProjectCode(){
    return projectCode;
    }
    public void setProjectCode(java.lang.String projectCode)
    {
    this.projectCode = projectCode;
    }
    
    public java.lang.String getShiper(){
    return shiper;
    }
    public void setShiper(java.lang.String shiper)
    {
    this.shiper = shiper;
    }
    
    public java.util.Date getShiperDate(){
    return shiperDate;
    }
    public void setShiperDate(java.util.Date shiperDate)
    {
    this.shiperDate = shiperDate;
    }
    
    public java.lang.String getShipPlace(){
    return shipPlace;
    }
    public void setShipPlace(java.lang.String shipPlace)
    {
    this.shipPlace = shipPlace;
    }
    
    public java.lang.String getCustomsProcedure(){
    return customsProcedure;
    }
    public void setCustomsProcedure(java.lang.String customsProcedure)
    {
    this.customsProcedure = customsProcedure;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.lang.Long getCreatedDeptId(){
    return createdDeptId;
    }
    public void setCreatedDeptId(java.lang.Long createdDeptId)
    {
    this.createdDeptId = createdDeptId;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }
    
    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.util.Date getCancelDate(){
    return cancelDate;
    }
    public void setCancelDate(java.util.Date cancelDate)
    {
    this.cancelDate = cancelDate;
    }
    
    public java.lang.Long getCancelUserId(){
    return cancelUserId;
    }
    public void setCancelUserId(java.lang.Long cancelUserId)
    {
    this.cancelUserId = cancelUserId;
    }
    
    public java.lang.String getCancelReasonName(){
    return cancelReasonName;
    }
    public void setCancelReasonName(java.lang.String cancelReasonName)
    {
    this.cancelReasonName = cancelReasonName;
    }
    
    public java.lang.String getCancelDescription(){
    return cancelDescription;
    }
    public void setCancelDescription(java.lang.String cancelDescription)
    {
    this.cancelDescription = cancelDescription;
    }
    
    public java.lang.String getFeeDescription(){
    return feeDescription;
    }
    public void setFeeDescription(java.lang.String feeDescription)
    {
    this.feeDescription = feeDescription;
    }

	public List<String> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}
    
}
