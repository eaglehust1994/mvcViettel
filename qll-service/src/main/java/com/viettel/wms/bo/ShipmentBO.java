/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ShipmentDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.SHIPMENT")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ShipmentBO extends BaseFWModelImpl {
     
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
private java.util.Date shiperDate;
private java.lang.String shipPlace;
private java.lang.String customsProcedure;
private java.lang.String description;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.Long createdDeptId;
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.String status;
private java.util.Date cancelDate;
private java.lang.Long cancelUserId;
private java.lang.String cancelReasonName;
private java.lang.String cancelDescription;
private java.lang.String feeDescription;

 public ShipmentBO() {
        setColId("shipmentId");
        setColName("shipmentId");
        setUniqueColumn(new String[]{"shipmentId"});
}

@Column(name = "TOTAL_ORIGIN_MONEY", length = 22)
public java.lang.Double getTotalOriginMoney(){
return totalOriginMoney;
}
public void setTotalOriginMoney(java.lang.Double totalOriginMoney)
{
this.totalOriginMoney = totalOriginMoney;
}
@Column(name = "TOTAL_FEE", length = 22)
public java.lang.Double getTotalFee(){
return totalFee;
}
public void setTotalFee(java.lang.Double totalFee)
{
this.totalFee = totalFee;
}
@Column(name = "TOTAL_TAX", length = 22)
public java.lang.Double getTotalTax(){
return totalTax;
}
public void setTotalTax(java.lang.Double totalTax)
{
this.totalTax = totalTax;
}
@Column(name = "TOTAL_MONEY", length = 22)
public java.lang.Double getTotalMoney(){
return totalMoney;
}
public void setTotalMoney(java.lang.Double totalMoney)
{
this.totalMoney = totalMoney;
}
@Column(name = "ORDER_CHECK_CODE", length = 100)
public java.lang.String getOrderCheckCode(){
return orderCheckCode;
}
public void setOrderCheckCode(java.lang.String orderCheckCode)
{
this.orderCheckCode = orderCheckCode;
}
@Column(name = "REPORT_CHECK_CODE", length = 100)
public java.lang.String getReportCheckCode(){
return reportCheckCode;
}
public void setReportCheckCode(java.lang.String reportCheckCode)
{
this.reportCheckCode = reportCheckCode;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SHIPMENT_SEQ")
            }
    )
@Column(name = "SHIPMENT_ID", length = 22)
public java.lang.Long getShipmentId(){
return shipmentId;
}
public void setShipmentId(java.lang.Long shipmentId)
{
this.shipmentId = shipmentId;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Long getContractId(){
return contractId;
}
public void setContractId(java.lang.Long contractId)
{
this.contractId = contractId;
}
@Column(name = "CONTRACT_CODE", length = 100)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "PROJ_INVEST_PROJECT_ID", length = 22)
public java.lang.Long getProjInvestProjectId(){
return projInvestProjectId;
}
public void setProjInvestProjectId(java.lang.Long projInvestProjectId)
{
this.projInvestProjectId = projInvestProjectId;
}
@Column(name = "PROJECT_CODE", length = 100)
public java.lang.String getProjectCode(){
return projectCode;
}
public void setProjectCode(java.lang.String projectCode)
{
this.projectCode = projectCode;
}
@Column(name = "SHIPER", length = 200)
public java.lang.String getShiper(){
return shiper;
}
public void setShiper(java.lang.String shiper)
{
this.shiper = shiper;
}
@Column(name = "SHIPER_DATE", length = 7)
public java.util.Date getShiperDate(){
return shiperDate;
}
public void setShiperDate(java.util.Date shiperDate)
{
this.shiperDate = shiperDate;
}
@Column(name = "SHIP_PLACE", length = 2000)
public java.lang.String getShipPlace(){
return shipPlace;
}
public void setShipPlace(java.lang.String shipPlace)
{
this.shipPlace = shipPlace;
}
@Column(name = "CUSTOMS_PROCEDURE", length = 200)
public java.lang.String getCustomsProcedure(){
return customsProcedure;
}
public void setCustomsProcedure(java.lang.String customsProcedure)
{
this.customsProcedure = customsProcedure;
}
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_BY", length = 22)
public java.lang.Long getCreatedBy(){
return createdBy;
}
public void setCreatedBy(java.lang.Long createdBy)
{
this.createdBy = createdBy;
}
@Column(name = "CREATED_DEPT_ID", length = 22)
public java.lang.Long getCreatedDeptId(){
return createdDeptId;
}
public void setCreatedDeptId(java.lang.Long createdDeptId)
{
this.createdDeptId = createdDeptId;
}
@Column(name = "UPDATED_BY", length = 22)
public java.lang.Long getUpdatedBy(){
return updatedBy;
}
public void setUpdatedBy(java.lang.Long updatedBy)
{
this.updatedBy = updatedBy;
}
@Column(name = "UPDATED_DATE", length = 7)
public java.util.Date getUpdatedDate(){
return updatedDate;
}
public void setUpdatedDate(java.util.Date updatedDate)
{
this.updatedDate = updatedDate;
}
@Column(name = "STATUS", length = 4)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "CANCEL_DATE", length = 7)
public java.util.Date getCancelDate(){
return cancelDate;
}
public void setCancelDate(java.util.Date cancelDate)
{
this.cancelDate = cancelDate;
}
@Column(name = "CANCEL_USER_ID", length = 22)
public java.lang.Long getCancelUserId(){
return cancelUserId;
}
public void setCancelUserId(java.lang.Long cancelUserId)
{
this.cancelUserId = cancelUserId;
}
@Column(name = "CANCEL_REASON_NAME", length = 2000)
public java.lang.String getCancelReasonName(){
return cancelReasonName;
}
public void setCancelReasonName(java.lang.String cancelReasonName)
{
this.cancelReasonName = cancelReasonName;
}
@Column(name = "CANCEL_DESCRIPTION", length = 2000)
public java.lang.String getCancelDescription(){
return cancelDescription;
}
public void setCancelDescription(java.lang.String cancelDescription)
{
this.cancelDescription = cancelDescription;
}
@Column(name = "FEE_DESCRIPTION", length = 2000)
public java.lang.String getFeeDescription(){
return feeDescription;
}
public void setFeeDescription(java.lang.String feeDescription)
{
this.feeDescription = feeDescription;
}
   

    @Override
    public ShipmentDTO toDTO() {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        //set cac gia tri 
        shipmentDTO.setTotalOriginMoney(this.totalOriginMoney);
        shipmentDTO.setTotalFee(this.totalFee);
        shipmentDTO.setTotalTax(this.totalTax);
        shipmentDTO.setTotalMoney(this.totalMoney);
        shipmentDTO.setOrderCheckCode(this.orderCheckCode);
        shipmentDTO.setReportCheckCode(this.reportCheckCode);
        shipmentDTO.setShipmentId(this.shipmentId);
        shipmentDTO.setCode(this.code);
        shipmentDTO.setContractId(this.contractId);
        shipmentDTO.setContractCode(this.contractCode);
        shipmentDTO.setType(this.type);
        shipmentDTO.setProjInvestProjectId(this.projInvestProjectId);
        shipmentDTO.setProjectCode(this.projectCode);
        shipmentDTO.setShiper(this.shiper);
        shipmentDTO.setShiperDate(this.shiperDate);
        shipmentDTO.setShipPlace(this.shipPlace);
        shipmentDTO.setCustomsProcedure(this.customsProcedure);
        shipmentDTO.setDescription(this.description);
        shipmentDTO.setCreatedDate(this.createdDate);
        shipmentDTO.setCreatedBy(this.createdBy);
        shipmentDTO.setCreatedDeptId(this.createdDeptId);
        shipmentDTO.setUpdatedBy(this.updatedBy);
        shipmentDTO.setUpdatedDate(this.updatedDate);
        shipmentDTO.setStatus(this.status);
        shipmentDTO.setCancelDate(this.cancelDate);
        shipmentDTO.setCancelUserId(this.cancelUserId);
        shipmentDTO.setCancelReasonName(this.cancelReasonName);
        shipmentDTO.setCancelDescription(this.cancelDescription);
        shipmentDTO.setFeeDescription(this.feeDescription);
        return shipmentDTO;
    }
}
