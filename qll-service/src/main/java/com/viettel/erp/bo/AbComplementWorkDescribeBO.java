/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name="abComplementWorkDescribe")
@Table(name = "AB_COMPLEMENT_WORK_DESCRIBE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AbComplementWorkDescribeBO extends BaseFWModelImpl {
     
private java.lang.Long abComplementWorkDescribeId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long aDirectorId;
private java.lang.Long bDirectorId;
private java.lang.Long aInChargeMonitorId;
private java.lang.Long bInChargeConstructId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;

 public AbComplementWorkDescribeBO() {
        setColId("abComplementWorkDescribeId");
        setColName("abComplementWorkDescribeId");
        setUniqueColumn(new String[]{"abComplementWorkDescribeId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "AB_COMPLEMENT_WORK_DR_SEQ")
            }
    )
@Column(name = "AB_COMPLEMENT_WORK_DESCRIBE_ID", length = 22)
public java.lang.Long getAbComplementWorkDescribeId(){
return abComplementWorkDescribeId;
}
public void setAbComplementWorkDescribeId(java.lang.Long abComplementWorkDescribeId)
{
this.abComplementWorkDescribeId = abComplementWorkDescribeId;
}
@Column(name = "CODE", length = 400)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "CREATED_USER_ID", length = 22)
public java.lang.Long getCreatedUserId(){
return createdUserId;
}
public void setCreatedUserId(java.lang.Long createdUserId)
{
this.createdUserId = createdUserId;
}
@Column(name = "A_DIRECTOR_ID", length = 22)
public java.lang.Long getADirectorId(){
return aDirectorId;
}
public void setADirectorId(java.lang.Long aDirectorId)
{
this.aDirectorId = aDirectorId;
}
@Column(name = "B_DIRECTOR_ID", length = 22)
public java.lang.Long getBDirectorId(){
return bDirectorId;
}
public void setBDirectorId(java.lang.Long bDirectorId)
{
this.bDirectorId = bDirectorId;
}
@Column(name = "A_IN_CHARGE_MONITOR_ID", length = 22)
public java.lang.Long getAInChargeMonitorId(){
return aInChargeMonitorId;
}
public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId)
{
this.aInChargeMonitorId = aInChargeMonitorId;
}
@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
public java.lang.Long getBInChargeConstructId(){
return bInChargeConstructId;
}
public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
{
this.bInChargeConstructId = bInChargeConstructId;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "CONSTRUCT_ID", length = 22)
public java.lang.Long getConstructId(){
return constructId;
}
public void setConstructId(java.lang.Long constructId)
{
this.constructId = constructId;
}
   

    @Override
    public AbComplementWorkDescribeDTO toDTO() {
        AbComplementWorkDescribeDTO abComplementWorkDescribeDTO = new AbComplementWorkDescribeDTO();
        //set cac gia tri 
        abComplementWorkDescribeDTO.setAbComplementWorkDescribeId(this.abComplementWorkDescribeId);
        abComplementWorkDescribeDTO.setCode(this.code);
        abComplementWorkDescribeDTO.setCreatedDate(this.createdDate);
        abComplementWorkDescribeDTO.setCreatedUserId(this.createdUserId);
        abComplementWorkDescribeDTO.setADirectorId(this.aDirectorId);
        abComplementWorkDescribeDTO.setBDirectorId(this.bDirectorId);
        abComplementWorkDescribeDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
        abComplementWorkDescribeDTO.setBInChargeConstructId(this.bInChargeConstructId);
        abComplementWorkDescribeDTO.setStatusCa(this.statusCa);
        abComplementWorkDescribeDTO.setDocumentCaId(this.documentCaId);
        abComplementWorkDescribeDTO.setIsActive(this.isActive);
        abComplementWorkDescribeDTO.setConstructId(this.constructId);
        return abComplementWorkDescribeDTO;
    }
}
