/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CntConstrReferDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CNT_CONSTR_REFER")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CntConstrReferBO extends BaseFWModelImpl {
     
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

 public CntConstrReferBO() {
        setColId("cntConstrReferId");
        setColName("cntConstrReferId");
        setUniqueColumn(new String[]{"cntConstrReferId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CNT_CONSTR_REFER_SEQ")
            }
    )
@Column(name = "CNT_CONSTR_REFER_ID", length = 22)
public java.lang.Long getCntConstrReferId(){
return cntConstrReferId;
}
public void setCntConstrReferId(java.lang.Long cntConstrReferId)
{
this.cntConstrReferId = cntConstrReferId;
}
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
@Column(name = "CONTRACT_ID", length = 22)
public java.lang.Long getContractId(){
return contractId;
}
public void setContractId(java.lang.Long contractId)
{
this.contractId = contractId;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "STATUS", length = 22)
public java.lang.Long getStatus(){
return status;
}
public void setStatus(java.lang.Long status)
{
this.status = status;
}
@Column(name = "CONSTR_VALUE", length = 22)
public java.lang.Double getConstrValue(){
return constrValue;
}
public void setConstrValue(java.lang.Double constrValue)
{
this.constrValue = constrValue;
}
@Column(name = "IS_FEE_SIGN", length = 22)
public java.lang.Long getIsFeeSign(){
return isFeeSign;
}
public void setIsFeeSign(java.lang.Long isFeeSign)
{
this.isFeeSign = isFeeSign;
}
@Column(name = "IS_FEE_APPROVE", length = 22)
public java.lang.Long getIsFeeApprove(){
return isFeeApprove;
}
public void setIsFeeApprove(java.lang.Long isFeeApprove)
{
this.isFeeApprove = isFeeApprove;
}
@Column(name = "IS_FEE_ESTIMATE", length = 22)
public java.lang.Long getIsFeeEstimate(){
return isFeeEstimate;
}
public void setIsFeeEstimate(java.lang.Long isFeeEstimate)
{
this.isFeeEstimate = isFeeEstimate;
}
   

    @Override
    public CntConstrReferDTO toDTO() {
        CntConstrReferDTO cntConstrReferDTO = new CntConstrReferDTO();
        //set cac gia tri 
        cntConstrReferDTO.setCntConstrReferId(this.cntConstrReferId);
        cntConstrReferDTO.setConstructId(this.constructId);
        cntConstrReferDTO.setContractId(this.contractId);
        cntConstrReferDTO.setCreatorId(this.creatorId);
        cntConstrReferDTO.setCreatedDate(this.createdDate);
        cntConstrReferDTO.setStatus(this.status);
        cntConstrReferDTO.setConstrValue(this.constrValue);
        cntConstrReferDTO.setIsFeeSign(this.isFeeSign);
        cntConstrReferDTO.setIsFeeApprove(this.isFeeApprove);
        cntConstrReferDTO.setIsFeeEstimate(this.isFeeEstimate);
        return cntConstrReferDTO;
    }
}
