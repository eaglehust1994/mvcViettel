/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.ConstrWorkCompConfListBO;
import com.viettel.erp.bo.ConstrWorkCompConfirmBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_WORK_COMP_CONF_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrWorkCompConfListDTO extends BaseFWDTOImpl<ConstrWorkCompConfListBO> {

private java.lang.Long constrWorkCompListId;
private java.lang.Double executeQuantity;
private java.lang.String comments;
private java.lang.Long estimatesWorkItemId;
private java.lang.Long constrWorkCompConfirmId;

    @Override
    public ConstrWorkCompConfListBO toModel() {
        ConstrWorkCompConfListBO constrWorkCompConfListBO = new ConstrWorkCompConfListBO();
        constrWorkCompConfListBO.setConstrWorkCompListId(this.constrWorkCompListId);
        constrWorkCompConfListBO.setExecuteQuantity(this.executeQuantity);
        constrWorkCompConfListBO.setComments(this.comments);
        constrWorkCompConfListBO.setEstimatesWorkItemId(this.estimatesWorkItemId);
        constrWorkCompConfListBO.setConstrWorkCompConfirmBO(this.constrWorkCompConfirmId ==null ? null : new ConstrWorkCompConfirmBO(this.constrWorkCompConfirmId));;
        return constrWorkCompConfListBO;
    }

    @Override
     public Long getFWModelId() {
        return constrWorkCompListId;
    }
   
    @Override
    public String catchName() {
        return getConstrWorkCompListId().toString();
    }
    public java.lang.Long getConstrWorkCompListId(){
    return constrWorkCompListId;
    }
    public void setConstrWorkCompListId(java.lang.Long constrWorkCompListId)
    {
    this.constrWorkCompListId = constrWorkCompListId;
    }
    
    public java.lang.Double getExecuteQuantity(){
    return executeQuantity;
    }
    public void setExecuteQuantity(java.lang.Double executeQuantity)
    {
    this.executeQuantity = executeQuantity;
    }
    
    public java.lang.String getComments(){
    return Strings.nullToEmpty(comments);
    }
    public void setComments(java.lang.String comments)
    {
    this.comments = comments;
    }
    
    public java.lang.Long getEstimatesWorkItemId(){
    return estimatesWorkItemId;
    }
    public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId)
    {
    this.estimatesWorkItemId = estimatesWorkItemId;
    }
    
    public java.lang.Long getConstrWorkCompConfirmId(){
    return constrWorkCompConfirmId;
    }
    public void setConstrWorkCompConfirmId(java.lang.Long constrWorkCompConfirmId)
    {
    this.constrWorkCompConfirmId = constrWorkCompConfirmId;
    }

    
   
}
