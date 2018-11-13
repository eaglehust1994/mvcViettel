/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.OrderDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.\"ORDER\"")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class OrderBO extends BaseFWModelImpl {
     
private java.lang.String createdByName;
private java.lang.String cancelReasonName;
private java.lang.Long orderId;
private java.lang.String code;
private java.lang.String type;
private java.lang.String bussinessType;
private java.lang.Long stockId;
private java.lang.Long deptReceiveId;
private java.lang.String deptReceiveName;
private java.lang.String status;
private java.lang.String description;
private java.lang.String purchaseOrderNo;
private java.util.Date purchaseOrderDate;
private java.lang.String cerNo;
private java.util.Date cerDate;
private java.util.Date createdDate;
private java.lang.Long createdBy;
private java.lang.Long createdDeptedId;
private java.lang.String createdDeptedName;
private java.lang.Long updatedBy;
private java.util.Date updatedDate;
private java.lang.String shipperId;
private java.lang.String shipperName;
private java.util.Date shipDate;
private java.lang.Long shipmentId;
private java.lang.String shipmentCode;
private java.lang.String contractCode;
private java.lang.String maintainOrderCode;
private java.lang.String maintainReportCode;
private java.lang.String consRetrieveOrderCode;
private java.lang.String deptRetrieveOrderCode;
private java.lang.String loanRetrieveOrderCode;
private java.lang.String constrCode;
private java.lang.Long partnerId;
private java.lang.Long ieStockId;
private java.lang.String projectCode;
private java.lang.String signState;
private java.util.Date cancelDate;
private java.lang.Long cancelBy;
private java.lang.String cancelByName;
private java.lang.String cancelDescription;
private java.lang.String vofficeTransactionCode;
private java.lang.Long reasonId;
private java.lang.Long recieverId;
private java.util.Date expectedRecievedDate;
private java.lang.Long ieStockTransId;

 public OrderBO() {
        setColId("shipmentId");
        setColName("shipmentId");
        setUniqueColumn(new String[]{"shipmentId"});
}

@Column(name = "CREATED_BY_NAME", length = 400)
public java.lang.String getCreatedByName(){
return createdByName;
}
public void setCreatedByName(java.lang.String createdByName)
{
this.createdByName = createdByName;
}
@Column(name = "CANCEL_REASON_NAME", length = 400)
public java.lang.String getCancelReasonName(){
return cancelReasonName;
}
public void setCancelReasonName(java.lang.String cancelReasonName)
{
this.cancelReasonName = cancelReasonName;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.ORDER_SEQ")
            }
    )
@Column(name = "ORDER_ID", length = 22)
public java.lang.Long getOrderId(){
return orderId;
}
public void setOrderId(java.lang.Long orderId)
{
this.orderId = orderId;
}
@Column(name = "CODE", length = 200)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "TYPE", length = 4)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "BUSSINESS_TYPE", length = 4)
public java.lang.String getBussinessType(){
return bussinessType;
}
public void setBussinessType(java.lang.String bussinessType)
{
this.bussinessType = bussinessType;
}
@Column(name = "STOCK_ID", length = 22)
public java.lang.Long getStockId(){
return stockId;
}
public void setStockId(java.lang.Long stockId)
{
this.stockId = stockId;
}
@Column(name = "DEPT_RECEIVE_ID", length = 22)
public java.lang.Long getDeptReceiveId(){
return deptReceiveId;
}
public void setDeptReceiveId(java.lang.Long deptReceiveId)
{
this.deptReceiveId = deptReceiveId;
}
@Column(name = "DEPT_RECEIVE_NAME", length = 200)
public java.lang.String getDeptReceiveName(){
return deptReceiveName;
}
public void setDeptReceiveName(java.lang.String deptReceiveName)
{
this.deptReceiveName = deptReceiveName;
}
@Column(name = "STATUS", length = 4)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "DESCRIPTION", length = 2000)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "PURCHASE_ORDER_NO", length = 200)
public java.lang.String getPurchaseOrderNo(){
return purchaseOrderNo;
}
public void setPurchaseOrderNo(java.lang.String purchaseOrderNo)
{
this.purchaseOrderNo = purchaseOrderNo;
}
@Column(name = "PURCHASE_ORDER_DATE", length = 7)
public java.util.Date getPurchaseOrderDate(){
return purchaseOrderDate;
}
public void setPurchaseOrderDate(java.util.Date purchaseOrderDate)
{
this.purchaseOrderDate = purchaseOrderDate;
}
@Column(name = "CER_NO", length = 200)
public java.lang.String getCerNo(){
return cerNo;
}
public void setCerNo(java.lang.String cerNo)
{
this.cerNo = cerNo;
}
@Column(name = "CER_DATE", length = 7)
public java.util.Date getCerDate(){
return cerDate;
}
public void setCerDate(java.util.Date cerDate)
{
this.cerDate = cerDate;
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
@Column(name = "CREATED_DEPTED_ID", length = 22)
public java.lang.Long getCreatedDeptedId(){
return createdDeptedId;
}
public void setCreatedDeptedId(java.lang.Long createdDeptedId)
{
this.createdDeptedId = createdDeptedId;
}
@Column(name = "CREATED_DEPTED_NAME", length = 400)
public java.lang.String getCreatedDeptedName(){
return createdDeptedName;
}
public void setCreatedDeptedName(java.lang.String createdDeptedName)
{
this.createdDeptedName = createdDeptedName;
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
@Column(name = "SHIPPER_ID", length = 20)
public java.lang.String getShipperId(){
return shipperId;
}
public void setShipperId(java.lang.String shipperId)
{
this.shipperId = shipperId;
}
@Column(name = "SHIPPER_NAME", length = 20)
public java.lang.String getShipperName(){
return shipperName;
}
public void setShipperName(java.lang.String shipperName)
{
this.shipperName = shipperName;
}
@Column(name = "SHIP_DATE", length = 7)
public java.util.Date getShipDate(){
return shipDate;
}
public void setShipDate(java.util.Date shipDate)
{
this.shipDate = shipDate;
}

@Column(name = "SHIPMENT_ID", length = 22)
public java.lang.Long getShipmentId(){
return shipmentId;
}
public void setShipmentId(java.lang.Long shipmentId)
{
this.shipmentId = shipmentId;
}
@Column(name = "SHIPMENT_CODE", length = 20)
public java.lang.String getShipmentCode(){
return shipmentCode;
}
public void setShipmentCode(java.lang.String shipmentCode)
{
this.shipmentCode = shipmentCode;
}
@Column(name = "CONTRACT_CODE", length = 100)
public java.lang.String getContractCode(){
return contractCode;
}
public void setContractCode(java.lang.String contractCode)
{
this.contractCode = contractCode;
}
@Column(name = "MAINTAIN_ORDER_CODE", length = 200)
public java.lang.String getMaintainOrderCode(){
return maintainOrderCode;
}
public void setMaintainOrderCode(java.lang.String maintainOrderCode)
{
this.maintainOrderCode = maintainOrderCode;
}
@Column(name = "MAINTAIN_REPORT_CODE", length = 200)
public java.lang.String getMaintainReportCode(){
return maintainReportCode;
}
public void setMaintainReportCode(java.lang.String maintainReportCode)
{
this.maintainReportCode = maintainReportCode;
}
@Column(name = "CONS_RETRIEVE_ORDER_CODE", length = 200)
public java.lang.String getConsRetrieveOrderCode(){
return consRetrieveOrderCode;
}
public void setConsRetrieveOrderCode(java.lang.String consRetrieveOrderCode)
{
this.consRetrieveOrderCode = consRetrieveOrderCode;
}
@Column(name = "DEPT_RETRIEVE_ORDER_CODE", length = 200)
public java.lang.String getDeptRetrieveOrderCode(){
return deptRetrieveOrderCode;
}
public void setDeptRetrieveOrderCode(java.lang.String deptRetrieveOrderCode)
{
this.deptRetrieveOrderCode = deptRetrieveOrderCode;
}
@Column(name = "LOAN_RETRIEVE_ORDER_CODE", length = 200)
public java.lang.String getLoanRetrieveOrderCode(){
return loanRetrieveOrderCode;
}
public void setLoanRetrieveOrderCode(java.lang.String loanRetrieveOrderCode)
{
this.loanRetrieveOrderCode = loanRetrieveOrderCode;
}
@Column(name = "CONSTR_CODE", length = 200)
public java.lang.String getConstrCode(){
return constrCode;
}
public void setConstrCode(java.lang.String constrCode)
{
this.constrCode = constrCode;
}
@Column(name = "PARTNER_ID", length = 20)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
@Column(name = "IE_STOCK_ID", length = 22)
public java.lang.Long getIeStockId(){
return ieStockId;
}
public void setIeStockId(java.lang.Long ieStockId)
{
this.ieStockId = ieStockId;
}
@Column(name = "PROJECT_CODE", length = 200)
public java.lang.String getProjectCode(){
return projectCode;
}
public void setProjectCode(java.lang.String projectCode)
{
this.projectCode = projectCode;
}
@Column(name = "SIGN_STATE", length = 4)
public java.lang.String getSignState(){
return signState;
}
public void setSignState(java.lang.String signState)
{
this.signState = signState;
}
@Column(name = "CANCEL_DATE", length = 7)
public java.util.Date getCancelDate(){
return cancelDate;
}
public void setCancelDate(java.util.Date cancelDate)
{
this.cancelDate = cancelDate;
}
@Column(name = "CANCEL_BY", length = 22)
public java.lang.Long getCancelBy(){
return cancelBy;
}
public void setCancelBy(java.lang.Long cancelBy)
{
this.cancelBy = cancelBy;
}
@Column(name = "CANCEL_BY_NAME", length = 200)
public java.lang.String getCancelByName(){
return cancelByName;
}
public void setCancelByName(java.lang.String cancelByName)
{
this.cancelByName = cancelByName;
}
@Column(name = "CANCEL_DESCRIPTION", length = 2000)
public java.lang.String getCancelDescription(){
return cancelDescription;
}
public void setCancelDescription(java.lang.String cancelDescription)
{
this.cancelDescription = cancelDescription;
}
@Column(name = "VOFFICE_TRANSACTION_CODE", length = 20)
public java.lang.String getVofficeTransactionCode(){
return vofficeTransactionCode;
}
public void setVofficeTransactionCode(java.lang.String vofficeTransactionCode)
{
this.vofficeTransactionCode = vofficeTransactionCode;
}
@Column(name = "REASON_ID", length = 22)
public java.lang.Long getReasonId(){
return reasonId;
}
public void setReasonId(java.lang.Long reasonId)
{
this.reasonId = reasonId;
}
@Column(name = "RECEIVER_ID", length = 10)
public java.lang.Long getRecieverId() {
	return recieverId;
}

public void setRecieverId(java.lang.Long recieverId) {
	this.recieverId = recieverId;
}
@Column(name = "EXPECTED_RECIEVED_DATE", length = 10)
public java.util.Date getExpectedRecievedDate() {
	return expectedRecievedDate;
}

public void setExpectedRecievedDate(java.util.Date expectedRecievedDate) {
	this.expectedRecievedDate = expectedRecievedDate;
}
@Column(name = "IE_STOCK_TRANS_ID", length = 10)
public java.lang.Long getIeStockTransId() {
	return ieStockTransId;
}

public void setIeStockTransId(java.lang.Long ieStockTransId) {
	this.ieStockTransId = ieStockTransId;
}

	@Override
    public OrderDTO toDTO() {
        OrderDTO orderDTO = new OrderDTO();
        //set cac gia tri 
        orderDTO.setCreatedByName(this.createdByName);
        orderDTO.setCancelReasonName(this.cancelReasonName);
        orderDTO.setOrderId(this.orderId);
        orderDTO.setCode(this.code);
        orderDTO.setType(this.type);
        orderDTO.setBussinessType(this.bussinessType);
        orderDTO.setStockId(this.stockId);
        orderDTO.setDeptReceiveId(this.deptReceiveId);
        orderDTO.setDeptReceiveName(this.deptReceiveName);
        orderDTO.setStatus(this.status);
        orderDTO.setDescription(this.description);
        orderDTO.setPurchaseOrderNo(this.purchaseOrderNo);
        orderDTO.setPurchaseOrderDate(this.purchaseOrderDate);
        orderDTO.setCerNo(this.cerNo);
        orderDTO.setCerDate(this.cerDate);
        orderDTO.setCreatedDate(this.createdDate);
        orderDTO.setCreatedBy(this.createdBy);
        orderDTO.setCreatedDeptedId(this.createdDeptedId);
        orderDTO.setCreatedDeptedName(this.createdDeptedName);
        orderDTO.setUpdatedBy(this.updatedBy);
        orderDTO.setUpdatedDate(this.updatedDate);
        orderDTO.setShipperId(this.shipperId);
        orderDTO.setShipperName(this.shipperName);
        orderDTO.setShipDate(this.shipDate);
        orderDTO.setShipmentId(this.shipmentId);
        orderDTO.setShipmentCode(this.shipmentCode);
        orderDTO.setContractCode(this.contractCode);
        orderDTO.setMaintainOrderCode(this.maintainOrderCode);
        orderDTO.setMaintainReportCode(this.maintainReportCode);
        orderDTO.setConsRetrieveOrderCode(this.consRetrieveOrderCode);
        orderDTO.setDeptRetrieveOrderCode(this.deptRetrieveOrderCode);
        orderDTO.setLoanRetrieveOrderCode(this.loanRetrieveOrderCode);
        orderDTO.setConstrCode(this.constrCode);
        orderDTO.setPartnerId(this.partnerId);
        orderDTO.setIeStockId(this.ieStockId);
        orderDTO.setProjectCode(this.projectCode);
        orderDTO.setSignState(this.signState);
        orderDTO.setCancelDate(this.cancelDate);
        orderDTO.setCancelBy(this.cancelBy);
        orderDTO.setCancelByName(this.cancelByName);
        orderDTO.setCancelDescription(this.cancelDescription);
        orderDTO.setVofficeTransactionCode(this.vofficeTransactionCode);
        orderDTO.setReasonId(this.reasonId);
        orderDTO.setRecieverId(this.recieverId);
        orderDTO.setExpectedRecievedDate(this.expectedRecievedDate);
        orderDTO.setIeStockTransId(this.ieStockTransId);
        return orderDTO;
    }
}
