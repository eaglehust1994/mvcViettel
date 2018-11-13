/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.BMaterialAcceptMerListBO;
import com.viettel.erp.bo.BMaterialAcceptanceBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "B_MATERIAL_ACCEPTANCEBO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BMaterialAcceptanceDTO extends BaseFWDTOImpl<BMaterialAcceptanceBO> {

/**
	 * 
	 */
private static final long serialVersionUID = 5810959926792971158L;
private java.lang.Long constructId;
private java.lang.Long bmaterialAcceptanceId;
private java.lang.String code;
private java.lang.Long amonitorId;
private java.lang.Long binChargeConstructId;
private java.lang.String acceptanceBase;
private java.lang.Long conclusion;

private java.lang.String comments;



public java.lang.String getComments() {
	return comments;
}



public void setComments(java.lang.String comments) {
	this.comments = comments;
}


private java.lang.Long contractId;





public java.lang.Long getContractId() {
	return contractId;
}



public void setContractId(java.lang.Long contractId) {
	this.contractId = contractId;
}


//@JsonSerialize(using = CustomJsonDateSerializer.class)
//@JsonDeserialize(using = CustomJsonDateDeserializer.class)
private java.util.Date createdDate;
private java.lang.Long createdUserId;

//@JsonSerialize(using = CustomJsonDateSerializer.class)
//@JsonDeserialize(using = CustomJsonDateDeserializer.class)
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long isActive;

@JsonSerialize(using = CustomJsonDateSerializer.class)
@JsonDeserialize(using = CustomJsonDateDeserializer.class)
private java.util.Date signDate;

private String signDateDay = "";
private String signDateMonth = "";
private String signDateYear = "";
private String conclusionString  ="";

//export co chan ky
private String amonitoridPath;
private String binchargeconstructidPath;

private String amonitoridSign;
private String binchargeconstructidSign;


//public String getConclusionString() {
//	if(conclusion == 0){
//		conclusionString = "Tu Choi";
//	}
//	else if(conclusion == 1){
//		conclusionString = "Dong y";}
//	return conclusionString;
//}
//
//public void setConclusionString(String conclusionString) {
//	this.conclusionString = conclusionString;
//}




public String getAmonitoridPath() {
	return amonitoridPath;
}



public void setAmonitoridPath(String amonitoridPath) {
	this.amonitoridPath = amonitoridPath;
}



public String getBinchargeconstructidPath() {
	return binchargeconstructidPath;
}



public void setBinchargeconstructidPath(String binchargeconstructidPath) {
	this.binchargeconstructidPath = binchargeconstructidPath;
}



public String getSignDateDay() {
	if (signDate != null) {
		signDateDay = DateFormatUtils.format(signDate, "dd");
	}
	return signDateDay;
}



public String getConclusionString() {
	return conclusionString;
}



public void setConclusionString(String conclusionString) {
	this.conclusionString = conclusionString;
}



public String getSignDateMonth() {
	if (signDate != null) {
		signDateMonth = DateFormatUtils.format(signDate, "MM");
	}
	return signDateMonth;
}


public String getSignDateYear() {
	if (signDate != null) {
		signDateYear = DateFormatUtils.format(signDate, "yyyy");
	}
	return signDateYear;
}


private java.lang.String signPlace;

private List<BMaterialAcceptMerListDTO> bmaterialAcceptMerList = Lists.newArrayList();

public List<BMaterialAcceptMerListDTO> getBmaterialAcceptMerList() {
	return bmaterialAcceptMerList;
}

public void setBmaterialAcceptMerList(List<BMaterialAcceptMerListDTO> bmaterialAcceptMerList) {
	this.bmaterialAcceptMerList = bmaterialAcceptMerList;
}

//du lieu lay bang khac
private String constrtAddress;
private String constrtCode;
private String contractCode;
private String contractName;
private String constructorName;
private String constrtName;
private String stationCode;


public String getStationCode() {
	return Strings.nullToEmpty(stationCode);
}



public void setStationCode(String stationCode) {
	this.stationCode = stationCode;
}



public String getConstrtAddress() {	
	return Strings.nullToEmpty(constrtAddress);
}



public void setConstrtAddress(String constrtAddress) {
	this.constrtAddress = constrtAddress;
}



public String getConstructorName() {
	return Strings.nullToEmpty(constructorName);
}

public void setConstructorName(String constructorName) {
	this.constructorName = constructorName;
}

public String getConstrtName() {
	return Strings.nullToEmpty(constrtName);
}

public void setConstrtName(String constrtName) {
	this.constrtName = constrtName;
}

public String getAfullName() {
	return afullName;
}

public void setAfullName(String afullName) {
	this.afullName = afullName;
}

public String getBfullName() {
	return bfullName;
}

public void setBfullName(String bfullName) {
	this.bfullName = bfullName;
}

private String afullName;
private String bfullName;

private Long constrCompReMapId;

public Long getConstrCompReMapId() {
	return constrCompReMapId;
}

public void setConstrCompReMapId(Long constrCompReMapId) {
	this.constrCompReMapId = constrCompReMapId;
}

	////
    @Override
    public BMaterialAcceptanceBO toModel() {
        BMaterialAcceptanceBO bMaterialAcceptanceBO = new BMaterialAcceptanceBO();
        bMaterialAcceptanceBO.setConstructId(this.constructId);
        bMaterialAcceptanceBO.setBMaterialAcceptanceId(this.bmaterialAcceptanceId);
        bMaterialAcceptanceBO.setCode(this.code);
        bMaterialAcceptanceBO.setAMonitorId(this.amonitorId);
        bMaterialAcceptanceBO.setBInChargeConstructId(this.binChargeConstructId);
        bMaterialAcceptanceBO.setAcceptanceBase(this.acceptanceBase);
        bMaterialAcceptanceBO.setConclusion(this.conclusion);
        bMaterialAcceptanceBO.setCreatedDate(this.createdDate);
        bMaterialAcceptanceBO.setCreatedUserId(this.createdUserId);
        bMaterialAcceptanceBO.setApprovalDate(this.approvalDate);
        bMaterialAcceptanceBO.setStatusCa(this.statusCa);
        bMaterialAcceptanceBO.setIsActive(this.isActive);
        bMaterialAcceptanceBO.setSignDate(this.signDate);
        bMaterialAcceptanceBO.setSignPlace(this.signPlace);
        
		for (BMaterialAcceptMerListDTO bmaterialAcceptMerListDTO : bmaterialAcceptMerList) {
			BMaterialAcceptMerListBO childBO = bmaterialAcceptMerListDTO.toModel();
			childBO.setbMaterialAcceptance(bMaterialAcceptanceBO);
			bMaterialAcceptanceBO.getbMaterialAcceptMerList().add(childBO);
		}
        
        return bMaterialAcceptanceBO;
    }

    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
  
    
    public java.lang.Long getBmaterialAcceptanceId() {
		return bmaterialAcceptanceId;
	}

	public void setBmaterialAcceptanceId(java.lang.Long bmaterialAcceptanceId) {
		this.bmaterialAcceptanceId = bmaterialAcceptanceId;
	}

	public java.lang.Long getAmonitorId() {
		return amonitorId;
	}

	public void setAmonitorId(java.lang.Long amonitorId) {
		this.amonitorId = amonitorId;
	}

	public java.lang.Long getBinChargeConstructId() {
		return binChargeConstructId;
	}

	public void setBinChargeConstructId(java.lang.Long binChargeConstructId) {
		this.binChargeConstructId = binChargeConstructId;
	}

	public java.lang.String getAcceptanceBase(){
    return acceptanceBase;
    }
    public void setAcceptanceBase(java.lang.String acceptanceBase)
    {
    this.acceptanceBase = acceptanceBase;
    }
    
    public java.lang.Long getConclusion(){
    return conclusion;
    }
    public void setConclusion(java.lang.Long conclusion)
    {
    this.conclusion = conclusion;
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
    
    public java.util.Date getApprovalDate(){
    return approvalDate;
    }
    public void setApprovalDate(java.util.Date approvalDate)
    {
    this.approvalDate = approvalDate;
    }
    
    public java.lang.Long getStatusCa(){
    return statusCa;
    }
    public void setStatusCa(java.lang.Long statusCa)
    {
    this.statusCa = statusCa;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }
    
    public java.util.Date getSignDate(){
    return signDate;
    }
    public void setSignDate(java.util.Date signDate)
    {
    this.signDate = signDate;
    }
    
    public java.lang.String getSignPlace(){
    return signPlace;
    }
    public void setSignPlace(java.lang.String signPlace)
    {
    this.signPlace = signPlace;
    }
    /**
     * @return the constrtCode
     */
    public String getConstrtCode() {
    	return Strings.nullToEmpty(constrtCode);
    }

    /**
     * @param constrtCode the constrtCode to set
     */
    public void setConstrtCode(String constrtCode) {
    	this.constrtCode = constrtCode;
    }

    /**
     * @return the contractCode
     */
    public String getContractCode() {
    	return Strings.nullToEmpty(contractCode);
    }

    /**
     * @param contractCode the contractCode to set
     */
    public void setContractCode(String contractCode) {
    	this.contractCode = contractCode;
    }

    /**
     * @return the contractName
     */
    public String getContractName() {
    	return Strings.nullToEmpty(contractName);
    }

    /**
     * @param contractName the contractName to set
     */
    public void setContractName(String contractName) {
    	this.contractName = contractName;
    }

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getAmonitoridSign() {
		return Strings.nullToEmpty(amonitoridSign);
	}



	public void setAmonitoridSign(String amonitoridSign) {
		this.amonitoridSign = amonitoridSign;
	}


	public String getBinchargeconstructidSign() {
		return Strings.nullToEmpty(binchargeconstructidSign);
	}



	public void setBinchargeconstructidSign(String binchargeconstructidSign) {
		this.binchargeconstructidSign = binchargeconstructidSign;
	}
   
	
}
