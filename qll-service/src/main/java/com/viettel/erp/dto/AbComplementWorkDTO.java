/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.AbComplementWorkBO;
import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "AB_COMPLEMENT_WORKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbComplementWorkDTO extends BaseFWDTOImpl<AbComplementWorkBO> {

private java.lang.Long abComplementWorkId;
private java.lang.String code;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long isActive;
private java.lang.String constructName;
private java.lang.String constructAddress;
private java.lang.String stationcode;
private java.lang.String statusCA;	
private java.lang.String constrtCode;		
private java.lang.Long adirectorId;
private java.lang.Long bdirectorId;
private java.lang.Long cdesignDirectionId;

private java.lang.Long constructId;
private java.lang.String contractCode;
private java.lang.Long constrCompReMapId;
private java.lang.Long type;
private ConstrCompleteRecordsMapDTO constrCompleteRecordsMap;
private java.lang.Double truocThueHD;
private java.lang.Double VATHD;
private java.lang.Double sauThueHD;

private java.lang.Double truocThueQT;
private java.lang.Double VATQT;
private java.lang.Double sauThueQT;
private java.lang.Integer rownum = 0;
private java.lang.Integer stt = 0;

private java.lang.String adirectorPath;
private java.lang.String bdirectorPath;
private java.lang.Long abSettlementWorkId;
private List<AbComplementWorkDTO> constracceptworklist = Lists.newArrayList();

@JsonDeserialize(using = CustomJsonDateDeserializer.class)
@JsonSerialize(using = CustomJsonDateSerializer.class)
private java.util.Date contractDateSign;
    @Override
    public AbComplementWorkBO toModel() {
        AbComplementWorkBO abComplementWorkBO = new AbComplementWorkBO();
        abComplementWorkBO.setAbComplementWorkId(this.abComplementWorkId);
        abComplementWorkBO.setCode(this.code);
        abComplementWorkBO.setCreatedDate(this.createdDate);
        abComplementWorkBO.setCreatedUserId(this.createdUserId);
        abComplementWorkBO.setADirectorId(this.adirectorId);
        abComplementWorkBO.setBDirectorId(this.bdirectorId);
        abComplementWorkBO.setCDesignDirectionId(this.cdesignDirectionId);
        abComplementWorkBO.setStatusCa(this.statusCa);
        abComplementWorkBO.setDocumentCaId(this.documentCaId);
        abComplementWorkBO.setIsActive(this.isActive);
        abComplementWorkBO.setConstructId(this.constructId);
                
        return abComplementWorkBO;
    }

    @Override
     public Long getFWModelId() {
        return abComplementWorkId;
    }
   
    @Override
    public String catchName() {
        return getAbComplementWorkId().toString();
    }
    public java.lang.Long getAbComplementWorkId(){
    return abComplementWorkId;
    }
    public void setAbComplementWorkId(java.lang.Long abComplementWorkId)
    {
    this.abComplementWorkId = abComplementWorkId;
    }
    
    public java.lang.String getCode(){
    return code;
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
   
	public java.util.Date getContractDateSign() {
		return contractDateSign;
	}

	public void setContractDateSign(java.util.Date contractDateSign) {
		this.contractDateSign = contractDateSign;
	}

	public java.lang.String getConstructName() {
		return Strings.nullToEmpty(constructName);
	}

	public void setConstructName(java.lang.String constructName) {
		this.constructName = constructName;
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

	public java.lang.String getConstrtCode() {
		return constrtCode;
	}

	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
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

	public java.lang.Long getCdesignDirectionId() {
		return cdesignDirectionId;
	}

	public void setCdesignDirectionId(java.lang.Long cdesignDirectionId) {
		this.cdesignDirectionId = cdesignDirectionId;
	}
	

	public ConstrCompleteRecordsMapDTO getConstrCompleteRecordsMap() {
		return constrCompleteRecordsMap;
	}

	public void setConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO constrCompleteRecordsMap) {
		this.constrCompleteRecordsMap = constrCompleteRecordsMap;
	}

	public java.lang.String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}

	public java.lang.Double getTruocThueHD() {
		if(truocThueHD==null){
			return 0d;
		}
		return truocThueHD;
	}

	public void setTruocThueHD(java.lang.Double truocThueHD) {
		this.truocThueHD = truocThueHD;
	}

	public java.lang.Double getTruocThueQT() {
		if(truocThueQT==null){
			return 0d;
		}
		return truocThueQT;
	}

	public void setTruocThueQT(java.lang.Double truocThueQT) {
		this.truocThueQT = truocThueQT;
	}

	public java.lang.Double getSauThueHD() {
		if(sauThueHD==null){
			return 0d;
		}
		return sauThueHD;
	}

	public void setSauThueHD(java.lang.Double sauThueHD) {
		this.sauThueHD = sauThueHD;
	}

	public java.lang.Double getSauThueQT() {
		if(sauThueQT==null){
			return 0d;
		}
		return sauThueQT;
	}

	public void setSauThueQT(java.lang.Double sauThueQT) {
		this.sauThueQT = sauThueQT;
	}

	public java.lang.Double getVATQT() {
		if(VATQT==null){
			return 0d;
		}
		return VATQT;
	}

	public void setVATQT(java.lang.Double vATQT) {
		VATQT = vATQT;
	}

	public java.lang.Double getVATHD() {
		if(VATHD==null){
			return 0d;
		}
		return VATHD;
	}

	public void setVATHD(java.lang.Double vATHD) {
		VATHD = vATHD;
	}

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.String getStatusCA() {
		return statusCA;
	}

	public void setStatusCA(java.lang.String statusCA) {
		this.statusCA = statusCA;
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public List<AbComplementWorkDTO> getConstracceptworklist() {
		return constracceptworklist;
	}

	public void setConstracceptworklist(List<AbComplementWorkDTO> constracceptworklist) {
		this.constracceptworklist = constracceptworklist;
	}
   
	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.Integer getRownum() {
		return rownum;
	}

	public void setRownum(java.lang.Integer rownum) {
		this.rownum = rownum;
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	public java.lang.String getAdirectorPath() {
		return adirectorPath;
	}

	public void setAdirectorPath(java.lang.String adirectorPath) {
		this.adirectorPath = adirectorPath;
	}

	public java.lang.String getBdirectorPath() {
		return bdirectorPath;
	}

	public void setBdirectorPath(java.lang.String bdirectorPath) {
		this.bdirectorPath = bdirectorPath;
	}

	public java.lang.Long getAbSettlementWorkId() {
		return abSettlementWorkId;
	}

	public void setAbSettlementWorkId(java.lang.Long abSettlementWorkId) {
		this.abSettlementWorkId = abSettlementWorkId;
	}
	
	
}
