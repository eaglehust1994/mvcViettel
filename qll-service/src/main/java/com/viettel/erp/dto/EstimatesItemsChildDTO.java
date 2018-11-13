/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ESTIMATES_ITEMS_CHILDBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatesItemsChildDTO extends BaseFWDTOImpl<EstimatesItemsChildBO> {

private java.lang.Long estimatesItemChildId;
private java.lang.Long constrEstimateInfoId;
private java.lang.String itemsCode;
private java.lang.String itemName;
private java.lang.Long itemId;
private java.lang.Long constructId;

private List<EstimatesWorkItemsDTO> estimatesWorkItemsDTO = new ArrayList<>();

    @Override
    public EstimatesItemsChildBO toModel() {
        EstimatesItemsChildBO estimatesItemsChildBO = new EstimatesItemsChildBO();
        estimatesItemsChildBO.setEstimatesItemChildId(this.estimatesItemChildId);
        
        if(this.constrEstimateInfoId!=null){
        estimatesItemsChildBO.setConstrestimateinfo(new ConstrEstimateInfoBO(constrEstimateInfoId));
        }
        estimatesItemsChildBO.setItemsCode(this.itemsCode);
        estimatesItemsChildBO.setItemName(this.itemName);
        return estimatesItemsChildBO;
    }

    @Override
     public Long getFWModelId() {
        return estimatesItemChildId;
    }
   
    @Override
    public String catchName() {
        return getEstimatesItemChildId().toString();
    }
    public java.lang.Long getEstimatesItemChildId(){
    return estimatesItemChildId;
    }
    public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId)
    {
    this.estimatesItemChildId = estimatesItemChildId;
    }
    
    public java.lang.Long getConstrEstimateInfoId(){
    return constrEstimateInfoId;
    }
    public void setConstrEstimateInfoId(java.lang.Long constrEstimateInfoId)
    {
    this.constrEstimateInfoId = constrEstimateInfoId;
    }
    
    public java.lang.String getItemsCode(){
    return itemsCode;
    }
    public void setItemsCode(java.lang.String itemsCode)
    {
    this.itemsCode = itemsCode;
    }
    
    public java.lang.String getItemName(){
    return itemName;
    }
    public void setItemName(java.lang.String itemName)
    {
    this.itemName = itemName;
    }

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.Long getItemId() {
		return itemId;
	}

	public void setItemId(java.lang.Long itemId) {
		this.itemId = itemId;
	}

	public List<EstimatesWorkItemsDTO> getEstimatesWorkItemsDTO() {
		return estimatesWorkItemsDTO;
	}

	public void setEstimatesWorkItemsDTO(List<EstimatesWorkItemsDTO> estimatesWorkItemsDTO) {
		this.estimatesWorkItemsDTO = estimatesWorkItemsDTO;
	}
    
   
}
