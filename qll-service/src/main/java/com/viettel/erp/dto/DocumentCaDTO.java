/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.DocumentCaBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "DOCUMENT_CABO")
public class DocumentCaDTO extends BaseFWDTOImpl<DocumentCaBO> {

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

    @Override
    public DocumentCaBO toModel() {
        DocumentCaBO documentCaBO = new DocumentCaBO();
        documentCaBO.setDocumentCaId(this.documentCaId);
        documentCaBO.setTableName(this.tableName);
        documentCaBO.setTableId(this.tableId);
        documentCaBO.setTableIdValue(this.tableIdValue);
        documentCaBO.setStatusCa(this.statusCa);
        documentCaBO.setCommentCa(this.commentCa);
        documentCaBO.setEmail(this.email);
        documentCaBO.setIsActive(this.isActive);
        documentCaBO.setVofficeName(this.vofficeName);
        documentCaBO.setCreatedId(this.createdId);
        documentCaBO.setCreatedDate(this.createdDate);
        documentCaBO.setGroupId(this.groupId);
        documentCaBO.setModifieldDate(this.modifieldDate);
        documentCaBO.setSignflowid(this.signflowid);
        return documentCaBO;
    }

    @Override
     public Long getFWModelId() {
        return documentCaId;
    }
   
    @Override
    public String catchName() {
        return getDocumentCaId().toString();
    }
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.String getTableName(){
    return tableName;
    }
    public void setTableName(java.lang.String tableName)
    {
    this.tableName = tableName;
    }
    
    public java.lang.String getTableId(){
    return tableId;
    }
    public void setTableId(java.lang.String tableId)
    {
    this.tableId = tableId;
    }
    
    public java.lang.Long getTableIdValue(){
    return tableIdValue;
    }
    public void setTableIdValue(java.lang.Long tableIdValue)
    {
    this.tableIdValue = tableIdValue;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.String getCommentCa(){
    return commentCa;
    }
    public void setCommentCa(java.lang.String commentCa)
    {
    this.commentCa = commentCa;
    }
    
    public java.lang.String getEmail(){
    return email;
    }
    public void setEmail(java.lang.String email)
    {
    this.email = email;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.String getVofficeName(){
    return vofficeName;
    }
    public void setVofficeName(java.lang.String vofficeName)
    {
    this.vofficeName = vofficeName;
    }
    
    public java.lang.Long getCreatedId(){
    return createdId;
    }
    public void setCreatedId(java.lang.Long createdId)
    {
    this.createdId = createdId;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getGroupId(){
    return groupId;
    }
    public void setGroupId(java.lang.Long groupId)
    {
    this.groupId = groupId;
    }
    
    public java.util.Date getModifieldDate(){
    return modifieldDate;
    }
    public void setModifieldDate(java.util.Date modifieldDate)
    {
    this.modifieldDate = modifieldDate;
    }
    
    public java.lang.Long getSignflowid(){
    return signflowid;
    }
    public void setSignflowid(java.lang.Long signflowid)
    {
    this.signflowid = signflowid;
    }
    
   
}
