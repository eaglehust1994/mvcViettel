/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.CntConstrReferBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CNT_CONSTR_REFERBO")
public class CntConstrReferDTO extends BaseFWDTOImpl<CntConstrReferBO> {

private java.lang.Long cntConstrReferId;
private java.lang.Long constructId;
private java.lang.Long contractId;
private java.lang.Long creatorId;
private java.util.Date createdDate;
private java.lang.Long status;
private java.lang.Double constrValue;
private java.lang.Long isFeeSign;
private java.lang.Long isFeeApprove;
private java.lang.Long isFeeEstimate;

    @Override
    public CntConstrReferBO toModel() {
        CntConstrReferBO cntConstrReferBO = new CntConstrReferBO();
        cntConstrReferBO.setCntConstrReferId(this.cntConstrReferId);
        cntConstrReferBO.setConstructId(this.constructId);
        cntConstrReferBO.setContractId(this.contractId);
        cntConstrReferBO.setCreatorId(this.creatorId);
        cntConstrReferBO.setCreatedDate(this.createdDate);
        cntConstrReferBO.setStatus(this.status);
        cntConstrReferBO.setConstrValue(this.constrValue);
        cntConstrReferBO.setIsFeeSign(this.isFeeSign);
        cntConstrReferBO.setIsFeeApprove(this.isFeeApprove);
        cntConstrReferBO.setIsFeeEstimate(this.isFeeEstimate);
        return cntConstrReferBO;
    }

    @Override
     public Long getFWModelId() {
        return cntConstrReferId;
    }
   
    @Override
    public String catchName() {
        return getCntConstrReferId().toString();
    }
    public java.lang.Long getCntConstrReferId(){
    return cntConstrReferId;
    }
    public void setCntConstrReferId(java.lang.Long cntConstrReferId)
    {
    this.cntConstrReferId = cntConstrReferId;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    public java.lang.Long getContractId(){
    return contractId;
    }
    public void setContractId(java.lang.Long contractId)
    {
    this.contractId = contractId;
    }
    
    public java.lang.Long getCreatorId(){
    return creatorId;
    }
    public void setCreatorId(java.lang.Long creatorId)
    {
    this.creatorId = creatorId;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getStatus(){
    return status;
    }
    public void setStatus(java.lang.Long status)
    {
    this.status = status;
    }
    
    public java.lang.Double getConstrValue(){
    return constrValue;
    }
    public void setConstrValue(java.lang.Double constrValue)
    {
    this.constrValue = constrValue;
    }
    
    public java.lang.Long getIsFeeSign(){
    return isFeeSign;
    }
    public void setIsFeeSign(java.lang.Long isFeeSign)
    {
    this.isFeeSign = isFeeSign;
    }
    
    public java.lang.Long getIsFeeApprove(){
    return isFeeApprove;
    }
    public void setIsFeeApprove(java.lang.Long isFeeApprove)
    {
    this.isFeeApprove = isFeeApprove;
    }
    
    public java.lang.Long getIsFeeEstimate(){
    return isFeeEstimate;
    }
    public void setIsFeeEstimate(java.lang.Long isFeeEstimate)
    {
    this.isFeeEstimate = isFeeEstimate;
    }
    
   
}
