/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

//import com.viettel.erp.bo.CatUnitBO;
import com.viettel.erp.bo.ConstrAcceptMerListBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_ACCEPT_MER_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptMerListDTO extends BaseFWDTOImpl<ConstrAcceptMerListBO> {

private java.lang.Long constrAcceptMerListId;
private java.lang.Long merEntityId;
private java.lang.Long merChandiseId;
private java.lang.String merName;
private java.lang.String serialNumber;
private java.lang.Long unitId;
private java.lang.String unitName;
private java.lang.Double handoverQuantity;
private java.lang.Double executeQuantity;
private java.lang.String comments;
private java.lang.Long constructionAcceptanceId;
private java.lang.Integer stt;
private java.lang.String workListUp ;
private java.lang.String workListDown ;
DecimalFormat df = new DecimalFormat("###.###");

    @Override
    public ConstrAcceptMerListBO toModel() {
        ConstrAcceptMerListBO constrAcceptMerListBO = new ConstrAcceptMerListBO();
        constrAcceptMerListBO.setConstrAcceptMerListId(this.constrAcceptMerListId);
        constrAcceptMerListBO.setMerEntityId(this.merEntityId);
        constrAcceptMerListBO.setMerName(this.merName);
        constrAcceptMerListBO.setSerialNumber(this.serialNumber);
       // constrAcceptMerListBO.setUnitId(this.unitId);
//        constrAcceptMerListBO.setCatunit(this.unitId == null ?null : new CatUnitBO(this.unitId));
        constrAcceptMerListBO.setHandoverQuantity(this.handoverQuantity);
        constrAcceptMerListBO.setExecuteQuantity(this.executeQuantity);
        constrAcceptMerListBO.setComments(this.comments);
        constrAcceptMerListBO.setMerChandiseId(this.merChandiseId);
        //constrAcceptMerListBO.setConstructionAcceptanceId(this.constructionAcceptanceId);
        constrAcceptMerListBO.setConstructionacceptance(this.constructionAcceptanceId == null ? null :new ConstructionAcceptanceBO(this.constructionAcceptanceId));

        return constrAcceptMerListBO;
    }

    @Override
     public Long getFWModelId() {
        return constrAcceptMerListId;
    }
   
    @Override
    public String catchName() {
        return getConstrAcceptMerListId().toString();
    }
    public java.lang.Long getConstrAcceptMerListId(){
    return constrAcceptMerListId;
    }
    public void setConstrAcceptMerListId(java.lang.Long constrAcceptMerListId)
    {
    this.constrAcceptMerListId = constrAcceptMerListId;
    }
    
    public java.lang.Long getMerEntityId(){
    return merEntityId;
    }
    public void setMerEntityId(java.lang.Long merEntityId)
    {
    this.merEntityId = merEntityId;
    }
    
    public java.lang.String getMerName(){
    return merName;
    }
    public void setMerName(java.lang.String merName)
    {
    this.merName = merName;
    }
    
    public java.lang.String getSerialNumber(){
    return serialNumber;
    }
    public void setSerialNumber(java.lang.String serialNumber)
    {
    this.serialNumber = serialNumber;
    }
    
    public java.lang.Long getUnitId(){
    return unitId;
    }
    public void setUnitId(java.lang.Long unitId)
    {
    this.unitId = unitId;
    }
    
    public java.lang.Double getHandoverQuantity(){
    return handoverQuantity;
    }
    public void setHandoverQuantity(java.lang.Double handoverQuantity)
    {
    this.handoverQuantity = handoverQuantity;
    }
    
    public java.lang.Double getExecuteQuantity(){
    return executeQuantity;
    }
    public void setExecuteQuantity(java.lang.Double executeQuantity)
    {
    this.executeQuantity = executeQuantity;
    }
    
    public java.lang.String getComments(){
    return comments;
    }
    public void setComments(java.lang.String comments)
    {
    this.comments = comments;
    }
    
    public java.lang.Long getConstructionAcceptanceId(){
    return constructionAcceptanceId;
    }
    public void setConstructionAcceptanceId(java.lang.Long constructionAcceptanceId)
    {
    this.constructionAcceptanceId = constructionAcceptanceId;
    }

	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.Long getMerChandiseId() {
		return merChandiseId;
	}

	public void setMerChandiseId(java.lang.Long merChandiseId) {
		this.merChandiseId = merChandiseId;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}
	public java.lang.String getWorkListUp() {
		if(executeQuantity!=null && handoverQuantity !=null){
		if(executeQuantity >= handoverQuantity){
			workListUp = String.valueOf(df.format(executeQuantity - handoverQuantity));
		}else{
		    workListUp = "";
		    }
		}
		return workListUp;
	}

	public void setWorkListUp(java.lang.String workListUp) {
		this.workListUp = workListUp;
	}

	public java.lang.String getWorkListDown() {
		if(handoverQuantity!=null && executeQuantity !=null){
		if(handoverQuantity >= executeQuantity){
			workListDown = String.valueOf(df.format(handoverQuantity - executeQuantity));
		}else{
		   workListDown = "";
		}
		}

		return workListDown;
	}

   
}
