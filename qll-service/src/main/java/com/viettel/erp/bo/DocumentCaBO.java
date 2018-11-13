/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.DocumentCaDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "DOCUMENT_CA")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class DocumentCaBO extends BaseFWModelImpl {
     
private java.lang.Long documentCaId;
private java.lang.String tableName;
private java.lang.String tableId;
private java.lang.Long tableIdValue;
private java.lang.Long statusCa;
private java.lang.String commentCa;
private java.lang.String email;
private java.lang.Long isActive;
private java.lang.String vofficeName;
private java.lang.Long createdId;
private java.util.Date createdDate;
private java.lang.Long groupId;
private java.util.Date modifieldDate;
private java.lang.Long signflowid;

 public DocumentCaBO() {
        setColId("documentCaId");
        setColName("documentCaId");
        setUniqueColumn(new String[]{"documentCaId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "DOCUMENT_CA_SEQ")
            }
    )
@Column(name = "DOCUMENT_CA_ID", length = 22)
public java.lang.Long getDocumentCaId(){
return documentCaId;
}
public void setDocumentCaId(java.lang.Long documentCaId)
{
this.documentCaId = documentCaId;
}
@Column(name = "TABLE_NAME", length = 400)
public java.lang.String getTableName(){
return tableName;
}
public void setTableName(java.lang.String tableName)
{
this.tableName = tableName;
}
@Column(name = "TABLE_ID", length = 200)
public java.lang.String getTableId(){
return tableId;
}
public void setTableId(java.lang.String tableId)
{
this.tableId = tableId;
}
@Column(name = "TABLE_ID_VALUE", length = 22)
public java.lang.Long getTableIdValue(){
return tableIdValue;
}
public void setTableIdValue(java.lang.Long tableIdValue)
{
this.tableIdValue = tableIdValue;
}
@Column(name = "STATUS_CA", length = 22)
public java.lang.Long getStatusCa(){
return statusCa;
}
public void setStatusCa(java.lang.Long statusCa)
{
this.statusCa = statusCa;
}
@Column(name = "COMMENT_CA", length = 1000)
public java.lang.String getCommentCa(){
return commentCa;
}
public void setCommentCa(java.lang.String commentCa)
{
this.commentCa = commentCa;
}
@Column(name = "EMAIL", length = 400)
public java.lang.String getEmail(){
return email;
}
public void setEmail(java.lang.String email)
{
this.email = email;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
@Column(name = "VOFFICE_NAME", length = 200)
public java.lang.String getVofficeName(){
return vofficeName;
}
public void setVofficeName(java.lang.String vofficeName)
{
this.vofficeName = vofficeName;
}
@Column(name = "CREATED_ID", length = 22)
public java.lang.Long getCreatedId(){
return createdId;
}
public void setCreatedId(java.lang.Long createdId)
{
this.createdId = createdId;
}
@Column(name = "CREATED_DATE", length = 7)
public java.util.Date getCreatedDate(){
return createdDate;
}
public void setCreatedDate(java.util.Date createdDate)
{
this.createdDate = createdDate;
}
@Column(name = "GROUP_ID", length = 22)
public java.lang.Long getGroupId(){
return groupId;
}
public void setGroupId(java.lang.Long groupId)
{
this.groupId = groupId;
}
@Column(name = "MODIFIELD_DATE", length = 7)
public java.util.Date getModifieldDate(){
return modifieldDate;
}
public void setModifieldDate(java.util.Date modifieldDate)
{
this.modifieldDate = modifieldDate;
}
@Column(name = "SIGNFLOWID", length = 22)
public java.lang.Long getSignflowid(){
return signflowid;
}
public void setSignflowid(java.lang.Long signflowid)
{
this.signflowid = signflowid;
}
   

    @Override
    public DocumentCaDTO toDTO() {
        DocumentCaDTO documentCaDTO = new DocumentCaDTO();
        //set cac gia tri 
        documentCaDTO.setDocumentCaId(this.documentCaId);
        documentCaDTO.setTableName(this.tableName);
        documentCaDTO.setTableId(this.tableId);
        documentCaDTO.setTableIdValue(this.tableIdValue);
        documentCaDTO.setStatusCa(this.statusCa);
        documentCaDTO.setCommentCa(this.commentCa);
        documentCaDTO.setEmail(this.email);
        documentCaDTO.setIsActive(this.isActive);
        documentCaDTO.setVofficeName(this.vofficeName);
        documentCaDTO.setCreatedId(this.createdId);
        documentCaDTO.setCreatedDate(this.createdDate);
        documentCaDTO.setGroupId(this.groupId);
        documentCaDTO.setModifieldDate(this.modifieldDate);
        documentCaDTO.setSignflowid(this.signflowid);
        return documentCaDTO;
    }
}
