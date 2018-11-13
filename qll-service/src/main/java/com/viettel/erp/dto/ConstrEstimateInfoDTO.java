/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_ESTIMATE_INFOBO")
public class ConstrEstimateInfoDTO extends BaseFWDTOImpl<ConstrEstimateInfoBO> {

private java.lang.Long constrEstimateInfoId;
private java.lang.Long constructionId;
private java.util.Date finishDesignDate;
private java.lang.Long currencyId;
private java.lang.Long estimateBeforeTax;
private java.lang.Long tax;
private java.lang.Long estimateAfterTax;
private java.lang.Long evaluationGroupId;
private java.lang.Long evaluationEmployeeId;
private java.util.Date evaluationDate;
private java.lang.Long groundReleaseCostBt;
private java.lang.Long groundReleaseCostTax;
private java.lang.Long groundReleaseCostAt;
private java.lang.Long constructionCostBt;
private java.lang.Long constructionCostTax;
private java.lang.Long constructionCostAt;
private java.lang.Long deviceCostBt;
private java.lang.Long deviceCostTax;
private java.lang.Long deviceCostAt;
private java.lang.Long consultCostBt;
private java.lang.Long consultCostTax;
private java.lang.Long consultCostAt;
private java.lang.Long otherCostBt;
private java.lang.Long otherCostTax;
private java.lang.Long otherCostAt;
private java.lang.Long preventiveCostBt;
private java.lang.Long preventiveCostTax;
private java.lang.Long preventiveCostAt;
private java.lang.Long isDivideItems;
private java.util.Date createdDate;
private java.util.Date updatedDate;
private java.lang.String createdUserId;

    @Override
    public ConstrEstimateInfoBO toModel() {
        ConstrEstimateInfoBO constrEstimateInfoBO = new ConstrEstimateInfoBO();
        constrEstimateInfoBO.setConstrEstimateInfoId(this.constrEstimateInfoId);
        //constrEstimateInfoBO.setConstructionId(this.constructionId);
        constrEstimateInfoBO.setconstrconstructions(this.constructionId == null ? null : new ConstrConstructionsBO(this.constructionId));
        constrEstimateInfoBO.setFinishDesignDate(this.finishDesignDate);
        constrEstimateInfoBO.setCurrencyId(this.currencyId);
        constrEstimateInfoBO.setEstimateBeforeTax(this.estimateBeforeTax);
        constrEstimateInfoBO.setTax(this.tax);
        constrEstimateInfoBO.setEstimateAfterTax(this.estimateAfterTax);
        constrEstimateInfoBO.setEvaluationGroupId(this.evaluationGroupId);
        constrEstimateInfoBO.setEvaluationEmployeeId(this.evaluationEmployeeId);
        constrEstimateInfoBO.setEvaluationDate(this.evaluationDate);
        constrEstimateInfoBO.setGroundReleaseCostBt(this.groundReleaseCostBt);
        constrEstimateInfoBO.setGroundReleaseCostTax(this.groundReleaseCostTax);
        constrEstimateInfoBO.setGroundReleaseCostAt(this.groundReleaseCostAt);
        constrEstimateInfoBO.setConstructionCostBt(this.constructionCostBt);
        constrEstimateInfoBO.setConstructionCostTax(this.constructionCostTax);
        constrEstimateInfoBO.setConstructionCostAt(this.constructionCostAt);
        constrEstimateInfoBO.setDeviceCostBt(this.deviceCostBt);
        constrEstimateInfoBO.setDeviceCostTax(this.deviceCostTax);
        constrEstimateInfoBO.setDeviceCostAt(this.deviceCostAt);
        constrEstimateInfoBO.setConsultCostBt(this.consultCostBt);
        constrEstimateInfoBO.setConsultCostTax(this.consultCostTax);
        constrEstimateInfoBO.setConsultCostAt(this.consultCostAt);
        constrEstimateInfoBO.setOtherCostBt(this.otherCostBt);
        constrEstimateInfoBO.setOtherCostTax(this.otherCostTax);
        constrEstimateInfoBO.setOtherCostAt(this.otherCostAt);
        constrEstimateInfoBO.setPreventiveCostBt(this.preventiveCostBt);
        constrEstimateInfoBO.setPreventiveCostTax(this.preventiveCostTax);
        constrEstimateInfoBO.setPreventiveCostAt(this.preventiveCostAt);
        constrEstimateInfoBO.setIsDivideItems(this.isDivideItems);
        constrEstimateInfoBO.setCreatedDate(this.createdDate);
        constrEstimateInfoBO.setUpdatedDate(this.updatedDate);
        constrEstimateInfoBO.setCreatedUserId(this.createdUserId);
        return constrEstimateInfoBO;
    }

    @Override
     public Long getFWModelId() {
        return constrEstimateInfoId;
    }
   
    @Override
    public String catchName() {
        return getConstrEstimateInfoId().toString();
    }
    public java.lang.Long getConstrEstimateInfoId(){
    return constrEstimateInfoId;
    }
    public void setConstrEstimateInfoId(java.lang.Long constrEstimateInfoId)
    {
    this.constrEstimateInfoId = constrEstimateInfoId;
    }
    
   
    public java.lang.Long getConstructionId(){
    return constructionId;
    }
    public void setConstructionId(java.lang.Long constructionId)
    {
    this.constructionId = constructionId;
    }
    
    public java.util.Date getFinishDesignDate(){
    return finishDesignDate;
    }
    public void setFinishDesignDate(java.util.Date finishDesignDate)
    {
    this.finishDesignDate = finishDesignDate;
    }
    
    public java.lang.Long getCurrencyId(){
    return currencyId;
    }
    public void setCurrencyId(java.lang.Long currencyId)
    {
    this.currencyId = currencyId;
    }
    
    public java.lang.Long getEstimateBeforeTax(){
    return estimateBeforeTax;
    }
    public void setEstimateBeforeTax(java.lang.Long estimateBeforeTax)
    {
    this.estimateBeforeTax = estimateBeforeTax;
    }
    
    public java.lang.Long getTax(){
    return tax;
    }
    public void setTax(java.lang.Long tax)
    {
    this.tax = tax;
    }
    
    public java.lang.Long getEstimateAfterTax(){
    return estimateAfterTax;
    }
    public void setEstimateAfterTax(java.lang.Long estimateAfterTax)
    {
    this.estimateAfterTax = estimateAfterTax;
    }
    
    public java.lang.Long getEvaluationGroupId(){
    return evaluationGroupId;
    }
    public void setEvaluationGroupId(java.lang.Long evaluationGroupId)
    {
    this.evaluationGroupId = evaluationGroupId;
    }
    
    public java.lang.Long getEvaluationEmployeeId(){
    return evaluationEmployeeId;
    }
    public void setEvaluationEmployeeId(java.lang.Long evaluationEmployeeId)
    {
    this.evaluationEmployeeId = evaluationEmployeeId;
    }
    
    public java.util.Date getEvaluationDate(){
    return evaluationDate;
    }
    public void setEvaluationDate(java.util.Date evaluationDate)
    {
    this.evaluationDate = evaluationDate;
    }
    
    public java.lang.Long getGroundReleaseCostBt(){
    return groundReleaseCostBt;
    }
    public void setGroundReleaseCostBt(java.lang.Long groundReleaseCostBt)
    {
    this.groundReleaseCostBt = groundReleaseCostBt;
    }
    
    public java.lang.Long getGroundReleaseCostTax(){
    return groundReleaseCostTax;
    }
    public void setGroundReleaseCostTax(java.lang.Long groundReleaseCostTax)
    {
    this.groundReleaseCostTax = groundReleaseCostTax;
    }
    
    public java.lang.Long getGroundReleaseCostAt(){
    return groundReleaseCostAt;
    }
    public void setGroundReleaseCostAt(java.lang.Long groundReleaseCostAt)
    {
    this.groundReleaseCostAt = groundReleaseCostAt;
    }
    
    public java.lang.Long getConstructionCostBt(){
    return constructionCostBt;
    }
    public void setConstructionCostBt(java.lang.Long constructionCostBt)
    {
    this.constructionCostBt = constructionCostBt;
    }
    
    public java.lang.Long getConstructionCostTax(){
    return constructionCostTax;
    }
    public void setConstructionCostTax(java.lang.Long constructionCostTax)
    {
    this.constructionCostTax = constructionCostTax;
    }
    
    public java.lang.Long getConstructionCostAt(){
    return constructionCostAt;
    }
    public void setConstructionCostAt(java.lang.Long constructionCostAt)
    {
    this.constructionCostAt = constructionCostAt;
    }
    
    public java.lang.Long getDeviceCostBt(){
    return deviceCostBt;
    }
    public void setDeviceCostBt(java.lang.Long deviceCostBt)
    {
    this.deviceCostBt = deviceCostBt;
    }
    
    public java.lang.Long getDeviceCostTax(){
    return deviceCostTax;
    }
    public void setDeviceCostTax(java.lang.Long deviceCostTax)
    {
    this.deviceCostTax = deviceCostTax;
    }
    
    public java.lang.Long getDeviceCostAt(){
    return deviceCostAt;
    }
    public void setDeviceCostAt(java.lang.Long deviceCostAt)
    {
    this.deviceCostAt = deviceCostAt;
    }
    
    public java.lang.Long getConsultCostBt(){
    return consultCostBt;
    }
    public void setConsultCostBt(java.lang.Long consultCostBt)
    {
    this.consultCostBt = consultCostBt;
    }
    
    public java.lang.Long getConsultCostTax(){
    return consultCostTax;
    }
    public void setConsultCostTax(java.lang.Long consultCostTax)
    {
    this.consultCostTax = consultCostTax;
    }
    
    public java.lang.Long getConsultCostAt(){
    return consultCostAt;
    }
    public void setConsultCostAt(java.lang.Long consultCostAt)
    {
    this.consultCostAt = consultCostAt;
    }
    
    public java.lang.Long getOtherCostBt(){
    return otherCostBt;
    }
    public void setOtherCostBt(java.lang.Long otherCostBt)
    {
    this.otherCostBt = otherCostBt;
    }
    
    public java.lang.Long getOtherCostTax(){
    return otherCostTax;
    }
    public void setOtherCostTax(java.lang.Long otherCostTax)
    {
    this.otherCostTax = otherCostTax;
    }
    
    public java.lang.Long getOtherCostAt(){
    return otherCostAt;
    }
    public void setOtherCostAt(java.lang.Long otherCostAt)
    {
    this.otherCostAt = otherCostAt;
    }
    
    public java.lang.Long getPreventiveCostBt(){
    return preventiveCostBt;
    }
    public void setPreventiveCostBt(java.lang.Long preventiveCostBt)
    {
    this.preventiveCostBt = preventiveCostBt;
    }
    
    public java.lang.Long getPreventiveCostTax(){
    return preventiveCostTax;
    }
    public void setPreventiveCostTax(java.lang.Long preventiveCostTax)
    {
    this.preventiveCostTax = preventiveCostTax;
    }
    
    public java.lang.Long getPreventiveCostAt(){
    return preventiveCostAt;
    }
    public void setPreventiveCostAt(java.lang.Long preventiveCostAt)
    {
    this.preventiveCostAt = preventiveCostAt;
    }
    
    public java.lang.Long getIsDivideItems(){
    return isDivideItems;
    }
    public void setIsDivideItems(java.lang.Long isDivideItems)
    {
    this.isDivideItems = isDivideItems;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.String getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.String createdUserId)
    {
    this.createdUserId = createdUserId;
    }
    
   
}
