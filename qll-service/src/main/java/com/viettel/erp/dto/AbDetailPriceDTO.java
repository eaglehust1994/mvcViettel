/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.AbDetailPriceBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_DETAIL_PRICEBO")
public class AbDetailPriceDTO extends BaseFWDTOImpl<AbDetailPriceBO> {

private java.lang.Long abDetailPriceId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long adirectorId;
private java.lang.Long bdirectorId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.Long constructId;
private java.lang.String contractCode;
private java.lang.Long constrCompReMapId;

private java.lang.String  contractDateSign;
private java.lang.String constrtName;
private java.lang.String constructAddress;
private java.lang.String stationcode;
private java.lang.String constructName;
private java.lang.String statusCA;	
private java.lang.String constrtCode;	

private java.lang.Integer rownum = 0;
private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;

    @Override
    public AbDetailPriceBO toModel() {
        AbDetailPriceBO abDetailPriceBO = new AbDetailPriceBO();
        abDetailPriceBO.setAbDetailPriceId(this.abDetailPriceId);
        abDetailPriceBO.setCode(this.code);
        abDetailPriceBO.setCreatedDate(this.createdDate);
        abDetailPriceBO.setCreatedUserId(this.createdUserId);
        abDetailPriceBO.setAdirectorId(this.adirectorId);
        abDetailPriceBO.setBdirectorId(this.bdirectorId);
        abDetailPriceBO.setStatusCa(this.statusCa);
        abDetailPriceBO.setDocumentCaId(this.documentCaId);
        abDetailPriceBO.setIsActive(this.isActive);
        abDetailPriceBO.setConstructId(this.constructId);
        return abDetailPriceBO;
    }

    public java.lang.Long getAdirectorId() {
		return adirectorId;
	}

	public void setAdirectorId(java.lang.Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public java.lang.Long getBdirectorId() {
		return bdirectorId;
	}

	public void setBdirectorId(java.lang.Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public java.lang.String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public java.lang.String getConstructAddress() {
		return Strings.nullToEmpty(constructAddress);
	}

	public void setConstructAddress(java.lang.String constructAddress) {
		this.constructAddress = constructAddress;
	}

	public java.lang.String getStationcode() {
		return Strings.nullToEmpty(stationcode);
	}

	public void setStationcode(java.lang.String stationcode) {
		this.stationcode = stationcode;
	}

	public java.lang.String getStatusCA() {
		return Strings.nullToEmpty(statusCA);
	}

	public void setStatusCA(java.lang.String statusCA) {
		this.statusCA = statusCA;
	}

	public java.lang.String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}

	@Override
     public Long getFWModelId() {
        return abDetailPriceId;
    }
   
    @Override
    public String catchName() {
        return getAbDetailPriceId().toString();
    }
    public java.lang.Long getAbDetailPriceId(){
    return abDetailPriceId;
    }
    public void setAbDetailPriceId(java.lang.Long abDetailPriceId)
    {
    this.abDetailPriceId = abDetailPriceId;
    }
    
    public java.lang.String getCode(){
    return Strings.nullToEmpty(code);
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }
    
    public java.lang.Long getCreatedUserId(){
    return createdUserId;
    }
    public void setCreatedUserId(java.lang.Long createdUserId)
    {
    this.createdUserId = createdUserId;
    }    
  
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public java.lang.String getContractDateSign() {
		return Strings.nullToEmpty(contractDateSign);
	}

	public void setContractDateSign(java.lang.String contractDateSign) {
		this.contractDateSign = contractDateSign;
	}

	public java.lang.String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}

	public java.lang.String getConstructName() {
		return Strings.nullToEmpty(constructName);
	}

	public void setConstructName(java.lang.String constructName) {
		this.constructName = constructName;
	}

	public java.lang.Integer getRownum() {
		return rownum;
	}

	public void setRownum(java.lang.Integer rownum) {
		this.rownum = rownum;
	}


    
   
}
