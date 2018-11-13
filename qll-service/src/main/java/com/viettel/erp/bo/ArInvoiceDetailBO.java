///* 
//* Copyright 2011 Viettel Telecom. All rights reserved. 
//* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
// */
//package com.viettel.erp.bo;
//
//import com.viettel.erp.dto.ArInvoiceDetailDTO;
//import com.viettel.service.base.model.BaseFWModelImpl;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//@Entity
//@Table(name = "AR_INVOICE_DETAIL")
///**
// *
// * @author: ThuanNHT
// * @version: 1.0
// * @since: 1.0
// */
//public class ArInvoiceDetailBO extends BaseFWModelImpl {
//     
//private java.lang.Long arInvoiceDetailId;
//private java.lang.Long arInvoiceId;
//private java.lang.Long adOrgId;
//private java.lang.Long adClientId;
//private java.util.Date created;
//private java.lang.Long createdby;
//private java.util.Date updated;
//private java.lang.Long updatedby;
//private java.lang.String description;
//private java.lang.String isactive;
//private java.lang.String isDeleted;
//private java.lang.Long mProductId;
//private java.lang.Long mWarehouseId;
//private java.lang.String note;
//private java.lang.Long cTaxId;
//private java.lang.Long taxAccountId;
//private java.lang.Long cBudgetId;
//private java.lang.Long cSalesRegionId;
//private java.lang.Long cCostTypeId;
//private java.lang.Long qty;
//private java.lang.Double unitPrice;
//private java.lang.Double sourceBtAmount;
//private java.lang.Double sourceTaxAmount;
//private java.lang.Double sourceTotalAmount;
//private java.lang.Double homeBtAmount;
//private java.lang.Double homeTaxAmount;
//private java.lang.Double homeTotalAmount;
//private java.lang.String bccsStaffCode;
//private java.lang.String bccsItemCode;
//private java.lang.String bccsServiceCode;
//private java.lang.String bccsItemName;
//private java.lang.String bccsRevenueTypeName;
//private java.lang.Long distributionAccountId;
//private java.lang.Long distributionType;
//private java.util.Date distributionFromDate;
//private java.util.Date distributionToDate;
//private java.lang.Double distributionDay;
//
// public ArInvoiceDetailBO() {
//        setColId("arInvoiceDetailId");
//        setColName("arInvoiceDetailId");
//        setUniqueColumn(new String[]{"arInvoiceDetailId"});
//}
//
//@Id
//@GeneratedValue(generator = "sequence")
//    @GenericGenerator(name = "sequence", strategy = "sequence",
//            parameters = {
//                @Parameter(name = "sequence", value = "AR_INVOICE_DETAIL_SEQ")
//            }
//    )
//@Column(name = "AR_INVOICE_DETAIL_ID", length = 22)
//public java.lang.Long getArInvoiceDetailId(){
//return arInvoiceDetailId;
//}
//public void setArInvoiceDetailId(java.lang.Long arInvoiceDetailId)
//{
//this.arInvoiceDetailId = arInvoiceDetailId;
//}
//@Column(name = "AR_INVOICE_ID", length = 22)
//public java.lang.Long getArInvoiceId(){
//return arInvoiceId;
//}
//public void setArInvoiceId(java.lang.Long arInvoiceId)
//{
//this.arInvoiceId = arInvoiceId;
//}
//@Column(name = "AD_ORG_ID", length = 22)
//public java.lang.Long getAdOrgId(){
//return adOrgId;
//}
//public void setAdOrgId(java.lang.Long adOrgId)
//{
//this.adOrgId = adOrgId;
//}
//@Column(name = "AD_CLIENT_ID", length = 22)
//public java.lang.Long getAdClientId(){
//return adClientId;
//}
//public void setAdClientId(java.lang.Long adClientId)
//{
//this.adClientId = adClientId;
//}
//@Column(name = "CREATED", length = 7)
//public java.util.Date getCreated(){
//return created;
//}
//public void setCreated(java.util.Date created)
//{
//this.created = created;
//}
//@Column(name = "CREATEDBY", length = 22)
//public java.lang.Long getCreatedby(){
//return createdby;
//}
//public void setCreatedby(java.lang.Long createdby)
//{
//this.createdby = createdby;
//}
//@Column(name = "UPDATED", length = 7)
//public java.util.Date getUpdated(){
//return updated;
//}
//public void setUpdated(java.util.Date updated)
//{
//this.updated = updated;
//}
//@Column(name = "UPDATEDBY", length = 22)
//public java.lang.Long getUpdatedby(){
//return updatedby;
//}
//public void setUpdatedby(java.lang.Long updatedby)
//{
//this.updatedby = updatedby;
//}
//@Column(name = "DESCRIPTION", length = 2000)
//public java.lang.String getDescription(){
//return description;
//}
//public void setDescription(java.lang.String description)
//{
//this.description = description;
//}
//@Column(name = "ISACTIVE", length = 1)
//public java.lang.String getIsactive(){
//return isactive;
//}
//public void setIsactive(java.lang.String isactive)
//{
//this.isactive = isactive;
//}
//@Column(name = "IS_DELETED", length = 1)
//public java.lang.String getIsDeleted(){
//return isDeleted;
//}
//public void setIsDeleted(java.lang.String isDeleted)
//{
//this.isDeleted = isDeleted;
//}
//@Column(name = "M_PRODUCT_ID", length = 22)
//public java.lang.Long getMProductId(){
//return mProductId;
//}
//public void setMProductId(java.lang.Long mProductId)
//{
//this.mProductId = mProductId;
//}
//@Column(name = "M_WAREHOUSE_ID", length = 22)
//public java.lang.Long getMWarehouseId(){
//return mWarehouseId;
//}
//public void setMWarehouseId(java.lang.Long mWarehouseId)
//{
//this.mWarehouseId = mWarehouseId;
//}
//@Column(name = "NOTE", length = 2000)
//public java.lang.String getNote(){
//return note;
//}
//public void setNote(java.lang.String note)
//{
//this.note = note;
//}
//@Column(name = "C_TAX_ID", length = 22)
//public java.lang.Long getCTaxId(){
//return cTaxId;
//}
//public void setCTaxId(java.lang.Long cTaxId)
//{
//this.cTaxId = cTaxId;
//}
//@Column(name = "TAX_ACCOUNT_ID", length = 22)
//public java.lang.Long getTaxAccountId(){
//return taxAccountId;
//}
//public void setTaxAccountId(java.lang.Long taxAccountId)
//{
//this.taxAccountId = taxAccountId;
//}
//@Column(name = "C_BUDGET_ID", length = 22)
//public java.lang.Long getCBudgetId(){
//return cBudgetId;
//}
//public void setCBudgetId(java.lang.Long cBudgetId)
//{
//this.cBudgetId = cBudgetId;
//}
//@Column(name = "C_SALES_REGION_ID", length = 22)
//public java.lang.Long getCSalesRegionId(){
//return cSalesRegionId;
//}
//public void setCSalesRegionId(java.lang.Long cSalesRegionId)
//{
//this.cSalesRegionId = cSalesRegionId;
//}
//@Column(name = "C_COST_TYPE_ID", length = 22)
//public java.lang.Long getCCostTypeId(){
//return cCostTypeId;
//}
//public void setCCostTypeId(java.lang.Long cCostTypeId)
//{
//this.cCostTypeId = cCostTypeId;
//}
//@Column(name = "QTY", length = 22)
//public java.lang.Long getQty(){
//return qty;
//}
//public void setQty(java.lang.Long qty)
//{
//this.qty = qty;
//}
//@Column(name = "UNIT_PRICE", length = 22)
//public java.lang.Double getUnitPrice(){
//return unitPrice;
//}
//public void setUnitPrice(java.lang.Double unitPrice)
//{
//this.unitPrice = unitPrice;
//}
//@Column(name = "SOURCE_BT_AMOUNT", length = 22)
//public java.lang.Double getSourceBtAmount(){
//return sourceBtAmount;
//}
//public void setSourceBtAmount(java.lang.Double sourceBtAmount)
//{
//this.sourceBtAmount = sourceBtAmount;
//}
//@Column(name = "SOURCE_TAX_AMOUNT", length = 22)
//public java.lang.Double getSourceTaxAmount(){
//return sourceTaxAmount;
//}
//public void setSourceTaxAmount(java.lang.Double sourceTaxAmount)
//{
//this.sourceTaxAmount = sourceTaxAmount;
//}
//@Column(name = "SOURCE_TOTAL_AMOUNT", length = 22)
//public java.lang.Double getSourceTotalAmount(){
//return sourceTotalAmount;
//}
//public void setSourceTotalAmount(java.lang.Double sourceTotalAmount)
//{
//this.sourceTotalAmount = sourceTotalAmount;
//}
//@Column(name = "HOME_BT_AMOUNT", length = 22)
//public java.lang.Double getHomeBtAmount(){
//return homeBtAmount;
//}
//public void setHomeBtAmount(java.lang.Double homeBtAmount)
//{
//this.homeBtAmount = homeBtAmount;
//}
//@Column(name = "HOME_TAX_AMOUNT", length = 22)
//public java.lang.Double getHomeTaxAmount(){
//return homeTaxAmount;
//}
//public void setHomeTaxAmount(java.lang.Double homeTaxAmount)
//{
//this.homeTaxAmount = homeTaxAmount;
//}
//@Column(name = "HOME_TOTAL_AMOUNT", length = 22)
//public java.lang.Double getHomeTotalAmount(){
//return homeTotalAmount;
//}
//public void setHomeTotalAmount(java.lang.Double homeTotalAmount)
//{
//this.homeTotalAmount = homeTotalAmount;
//}
//@Column(name = "BCCS_STAFF_CODE", length = 200)
//public java.lang.String getBccsStaffCode(){
//return bccsStaffCode;
//}
//public void setBccsStaffCode(java.lang.String bccsStaffCode)
//{
//this.bccsStaffCode = bccsStaffCode;
//}
//@Column(name = "BCCS_ITEM_CODE", length = 200)
//public java.lang.String getBccsItemCode(){
//return bccsItemCode;
//}
//public void setBccsItemCode(java.lang.String bccsItemCode)
//{
//this.bccsItemCode = bccsItemCode;
//}
//@Column(name = "BCCS_SERVICE_CODE", length = 200)
//public java.lang.String getBccsServiceCode(){
//return bccsServiceCode;
//}
//public void setBccsServiceCode(java.lang.String bccsServiceCode)
//{
//this.bccsServiceCode = bccsServiceCode;
//}
//@Column(name = "BCCS_ITEM_NAME", length = 200)
//public java.lang.String getBccsItemName(){
//return bccsItemName;
//}
//public void setBccsItemName(java.lang.String bccsItemName)
//{
//this.bccsItemName = bccsItemName;
//}
//@Column(name = "BCCS_REVENUE_TYPE_NAME", length = 200)
//public java.lang.String getBccsRevenueTypeName(){
//return bccsRevenueTypeName;
//}
//public void setBccsRevenueTypeName(java.lang.String bccsRevenueTypeName)
//{
//this.bccsRevenueTypeName = bccsRevenueTypeName;
//}
//@Column(name = "DISTRIBUTION_ACCOUNT_ID", length = 22)
//public java.lang.Long getDistributionAccountId(){
//return distributionAccountId;
//}
//public void setDistributionAccountId(java.lang.Long distributionAccountId)
//{
//this.distributionAccountId = distributionAccountId;
//}
//@Column(name = "DISTRIBUTION_TYPE", length = 22)
//public java.lang.Long getDistributionType(){
//return distributionType;
//}
//public void setDistributionType(java.lang.Long distributionType)
//{
//this.distributionType = distributionType;
//}
//@Column(name = "DISTRIBUTION_FROM_DATE", length = 7)
//public java.util.Date getDistributionFromDate(){
//return distributionFromDate;
//}
//public void setDistributionFromDate(java.util.Date distributionFromDate)
//{
//this.distributionFromDate = distributionFromDate;
//}
//@Column(name = "DISTRIBUTION_TO_DATE", length = 7)
//public java.util.Date getDistributionToDate(){
//return distributionToDate;
//}
//public void setDistributionToDate(java.util.Date distributionToDate)
//{
//this.distributionToDate = distributionToDate;
//}
//@Column(name = "DISTRIBUTION_DAY", length = 22)
//public java.lang.Double getDistributionDay(){
//return distributionDay;
//}
//public void setDistributionDay(java.lang.Double distributionDay)
//{
//this.distributionDay = distributionDay;
//}
//   
//
//    @Override
//    public ArInvoiceDetailDTO toDTO() {
//        ArInvoiceDetailDTO arInvoiceDetailDTO = new ArInvoiceDetailDTO();
//        //set cac gia tri 
//        arInvoiceDetailDTO.setArInvoiceDetailId(this.arInvoiceDetailId);
//        arInvoiceDetailDTO.setArInvoiceId(this.arInvoiceId);
//        arInvoiceDetailDTO.setAdOrgId(this.adOrgId);
//        arInvoiceDetailDTO.setAdClientId(this.adClientId);
//        arInvoiceDetailDTO.setCreated(this.created);
//        arInvoiceDetailDTO.setCreatedby(this.createdby);
//        arInvoiceDetailDTO.setUpdated(this.updated);
//        arInvoiceDetailDTO.setUpdatedby(this.updatedby);
//        arInvoiceDetailDTO.setDescription(this.description);
//        arInvoiceDetailDTO.setIsactive(this.isactive);
//        arInvoiceDetailDTO.setIsDeleted(this.isDeleted);
//        arInvoiceDetailDTO.setProductId(this.mProductId);
//        arInvoiceDetailDTO.setMWarehouseId(this.mWarehouseId);
//        arInvoiceDetailDTO.setNote(this.note);
//        arInvoiceDetailDTO.setCTaxId(this.cTaxId);
//        arInvoiceDetailDTO.setTaxAccountId(this.taxAccountId);
//        arInvoiceDetailDTO.setCBudgetId(this.cBudgetId);
//        arInvoiceDetailDTO.setCSalesRegionId(this.cSalesRegionId);
//        arInvoiceDetailDTO.setCCostTypeId(this.cCostTypeId);
//        arInvoiceDetailDTO.setQty(this.qty);
//        arInvoiceDetailDTO.setUnitPrice(this.unitPrice);
//        arInvoiceDetailDTO.setSourceBtAmount(this.sourceBtAmount);
//        arInvoiceDetailDTO.setSourceTaxAmount(this.sourceTaxAmount);
//        arInvoiceDetailDTO.setSourceTotalAmount(this.sourceTotalAmount);
//        arInvoiceDetailDTO.setHomeBtAmount(this.homeBtAmount);
//        arInvoiceDetailDTO.setHomeTaxAmount(this.homeTaxAmount);
//        arInvoiceDetailDTO.setHomeTotalAmount(this.homeTotalAmount);
//        arInvoiceDetailDTO.setBccsStaffCode(this.bccsStaffCode);
//        arInvoiceDetailDTO.setBccsItemCode(this.bccsItemCode);
//        arInvoiceDetailDTO.setBccsServiceCode(this.bccsServiceCode);
//        arInvoiceDetailDTO.setBccsItemName(this.bccsItemName);
//        arInvoiceDetailDTO.setBccsRevenueTypeName(this.bccsRevenueTypeName);
//        arInvoiceDetailDTO.setDistributionAccountId(this.distributionAccountId);
//        arInvoiceDetailDTO.setDistributionType(this.distributionType);
//        arInvoiceDetailDTO.setDistributionFromDate(this.distributionFromDate);
//        arInvoiceDetailDTO.setDistributionToDate(this.distributionToDate);
//        arInvoiceDetailDTO.setDistributionDay(this.distributionDay);
//        return arInvoiceDetailDTO;
//    }
//}
