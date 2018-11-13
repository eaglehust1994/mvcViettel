/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.WorkItemsAcceptListBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "WORK_ITEMS_ACCEPT_LISTBO")
public class WorkItemsAcceptListDTO extends BaseFWDTOImpl<WorkItemsAcceptListBO> {

private java.lang.Long workItemsAcceptListId;
private java.lang.Long workItemsAcceptanceId;
private java.lang.Long estimatesWorkItemId;

    @Override
    public WorkItemsAcceptListBO toModel() {
        WorkItemsAcceptListBO workItemsAcceptListBO = new WorkItemsAcceptListBO();
        workItemsAcceptListBO.setWorkItemsAcceptListId(this.workItemsAcceptListId);
        workItemsAcceptListBO.setWorkItemsAcceptanceId(this.workItemsAcceptanceId);
        workItemsAcceptListBO.setEstimatesWorkItemId(this.estimatesWorkItemId);
        return workItemsAcceptListBO;
    }

    @Override
     public Long getFWModelId() {
        return workItemsAcceptListId;
    }
   
    @Override
    public String catchName() {
        return getWorkItemsAcceptListId().toString();
    }
    public java.lang.Long getWorkItemsAcceptListId(){
    return workItemsAcceptListId;
    }
    public void setWorkItemsAcceptListId(java.lang.Long workItemsAcceptListId)
    {
    this.workItemsAcceptListId = workItemsAcceptListId;
    }
    
    public java.lang.Long getWorkItemsAcceptanceId(){
    return workItemsAcceptanceId;
    }
    public void setWorkItemsAcceptanceId(java.lang.Long workItemsAcceptanceId)
    {
    this.workItemsAcceptanceId = workItemsAcceptanceId;
    }
    
    public java.lang.Long getEstimatesWorkItemId(){
    return estimatesWorkItemId;
    }
    public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId)
    {
    this.estimatesWorkItemId = estimatesWorkItemId;
    }
    
   
}
