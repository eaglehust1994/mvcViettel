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

import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.ConstrAcceptMerListBO;
import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;

import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTRUCTION_ACCEPTANCEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructionAcceptanceDTO extends BaseFWDTOImpl<ConstructionAcceptanceBO> {

private java.lang.Long constructId;
private java.lang.Long constructionAcceptanceId;
private java.lang.String code;
private java.lang.Long aMonitorId;
private java.lang.String amonitorName;
private java.lang.Long aInChargeMonitorId;
private java.lang.String ainChargeMonitorName;
private java.lang.Long bDirectorId;
private java.lang.String bdirectorName;
private java.lang.Long bInChargeConstructId;
private java.lang.String binChargeConstructName;
private java.lang.Long cDesignDirectionId;
private java.lang.String cdesignDirectionName;
private java.lang.Long cDesignManagerId;
private java.lang.String cdesignManagerName;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date acceptFromDate;
@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date acceptToDate;
private java.lang.String acceptPlace;
private java.lang.String applyBenchmark;
private java.lang.String constructionQuality;
private java.lang.String otherDocuments;
private java.lang.String otherComments;
private java.lang.Long conclusion;
private java.lang.String completeRequest;
private java.util.Date createdDate;
private java.lang.Long createdUserId;
private java.util.Date approvalDate;
private java.lang.Long statusCa;
private java.lang.Long documentCaId;
private java.lang.Long type;
private java.lang.String incompleteReason;
private java.lang.Long isActive;
private java.lang.String comments;


public java.lang.String getComments() {
	return comments;
}

public void setComments(java.lang.String comments) {
	this.comments = comments;
}

private java.lang.Long constrCompReMapId;


public java.lang.Long getConstrCompReMapId() {
	return constrCompReMapId;
}

public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
	this.constrCompReMapId = constrCompReMapId;
}

private String acceptFromDateHour = "";
private String acceptFromDateMinute = "";
private String acceptFromDateDay = "";
private String acceptFromDateMonth = "";
private String acceptFromDateYear = "";

private String acceptToDateHour = "";
private String acceptToDateMinute = "";
private String acceptToDateDay = "";
private String acceptToDateMonth = "";
private String acceptToDateYear = "";


private String conclusionaccept = "";
private String conclusionnotaccept = "";



private String signDateDay = "";
private String signDateMonth = "";
private String signDateYear = "";

private String signPlace = "";

private String documentPath;
private String documentName;

private List<String> listDocumentPath;
private List<String> listDocumentName;

@JsonSerialize(using = JsonDateSerializer.class)
@JsonDeserialize(using = JsonDateDeserializer.class)
private java.util.Date signDate;

private List<ConstrAcceptWorkListDTO> constracceptworklist = Lists.newArrayList();	
private List<ConstrAcceptMerListDTO> constracceptmerlist = Lists.newArrayList();

private ConstrCompleteRecordsMapDTO constrcompleterecordsmap;


private VConstructionHcqtDTO vConstructionHcqtDTO;

// du lieu lay bang khac
private String constrtCode = "";
private String constrtName = "";
private String contractCode = "";
private String contractName = "";
private String constrtAddress = "";
private String stationCode = "";
private java.lang.Double contractId;


//userid
private java.lang.Long userid = 0L;

	@Override
    public ConstructionAcceptanceBO toModel() {
        ConstructionAcceptanceBO constructionAcceptanceBO = new ConstructionAcceptanceBO();
        constructionAcceptanceBO.setVconstructionHcqt(this.constructId == null ? null :new VConstructionHcqtBO(this.constructId));
       // constructionAcceptanceBO.setConstructId(this.constructId);
        constructionAcceptanceBO.setConstructionAcceptanceId(this.constructionAcceptanceId);
        constructionAcceptanceBO.setCode(this.code);
        constructionAcceptanceBO.setaMonitor(this.aMonitorId == null ? null :new CatEmployeeBO(this.aMonitorId.toString()));
        
        constructionAcceptanceBO.setaInChargeMonitor(this.aInChargeMonitorId == null ? null :new CatEmployeeBO(this.aInChargeMonitorId.toString()));
        
        constructionAcceptanceBO.setbDirector(this.bDirectorId == null ? null :new CatEmployeeBO(this.bDirectorId.toString()));
        
        constructionAcceptanceBO.setbInChargeConstruct(this.bInChargeConstructId == null ? null :new CatEmployeeBO(this.bInChargeConstructId.toString()));
        
        constructionAcceptanceBO.setcDesignDirection(this.cDesignDirectionId == null ? null :new CatEmployeeBO(this.cDesignDirectionId.toString()));
        
        constructionAcceptanceBO.setcDesignManager(this.cDesignManagerId == null ? null :new CatEmployeeBO(this.cDesignManagerId.toString()));
        
        constructionAcceptanceBO.setAcceptFromDate(this.acceptFromDate);
        constructionAcceptanceBO.setAcceptToDate(this.acceptToDate);
        constructionAcceptanceBO.setAcceptPlace(this.acceptPlace);
        constructionAcceptanceBO.setApplyBenchmark(this.applyBenchmark);
        constructionAcceptanceBO.setConstructionQuality(this.constructionQuality);
        constructionAcceptanceBO.setOtherDocuments(this.otherDocuments);
        constructionAcceptanceBO.setOtherComments(this.otherComments);
        constructionAcceptanceBO.setConclusion(this.conclusion);
        constructionAcceptanceBO.setCompleteRequest(this.completeRequest);
        constructionAcceptanceBO.setCreatedDate(this.createdDate);
        constructionAcceptanceBO.setCreatedUserId(this.createdUserId);
        constructionAcceptanceBO.setApprovalDate(this.approvalDate);
        constructionAcceptanceBO.setStatusCa(this.statusCa);
        constructionAcceptanceBO.setDocumentCaId(this.documentCaId);
        constructionAcceptanceBO.setType(this.type);
        constructionAcceptanceBO.setIncompleteReason(this.incompleteReason);
        constructionAcceptanceBO.setIsActive(this.isActive);
        constructionAcceptanceBO.setSigndate(this.signDate);
        constructionAcceptanceBO.setSignplace(this.signPlace);
        

        
		for (ConstrAcceptWorkListDTO constracceptworklistDTO : constracceptworklist) {
			ConstrAcceptWorkListBO childBO = constracceptworklistDTO.toModel();
			childBO.setConstructionacceptance(constructionAcceptanceBO);
			constructionAcceptanceBO.getConstracceptworklist().add(childBO);
		}
		
		for (ConstrAcceptMerListDTO constracceptmerlistDTO : constracceptmerlist) {
			ConstrAcceptMerListBO childBO = constracceptmerlistDTO.toModel();
			childBO.setConstructionacceptance(constructionAcceptanceBO);
			constructionAcceptanceBO.getConstracceptmerlist().add(childBO);
		}
/*		if(constrcompleterecordsmap != null){
		
        ConstrCompleteRecordsMapBO BO = constrcompleterecordsmap.toModel();
        BO.setConstructionacceptance(constructionAcceptanceBO);
        constructionAcceptanceBO.setConstrcompleterecordsmap(BO);
		}*/
        return constructionAcceptanceBO;
    }

    public java.lang.Long getConstructId(){
    return constructId;
    }
    public void setConstructId(java.lang.Long constructId)
    {
    this.constructId = constructId;
    }
    
    @Override
     public Long getFWModelId() {
        return constructionAcceptanceId;
    }
   
    @Override
    public String catchName() {
        return getConstructionAcceptanceId().toString();
    }
    public java.lang.Long getConstructionAcceptanceId(){
    return constructionAcceptanceId;
    }
    public void setConstructionAcceptanceId(java.lang.Long constructionAcceptanceId)
    {
    this.constructionAcceptanceId = constructionAcceptanceId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = code;
    }
    
    public java.lang.Long getAMonitorId(){
    return aMonitorId;
    }
    public void setAMonitorId(java.lang.Long aMonitorId)
    {
    this.aMonitorId = aMonitorId;
    }
    
    public java.lang.Long getAInChargeMonitorId(){
    return aInChargeMonitorId;
    }
    public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId)
    {
    this.aInChargeMonitorId = aInChargeMonitorId;
    }
    
    public java.lang.Long getBDirectorId(){
    return bDirectorId;
    }
    public void setBDirectorId(java.lang.Long bDirectorId)
    {
    this.bDirectorId = bDirectorId;
    }
    
    public java.lang.Long getBInChargeConstructId(){
    return bInChargeConstructId;
    }
    public void setBInChargeConstructId(java.lang.Long bInChargeConstructId)
    {
    this.bInChargeConstructId = bInChargeConstructId;
    }
    
    public java.lang.Long getCDesignDirectionId(){
    return cDesignDirectionId;
    }
    public void setCDesignDirectionId(java.lang.Long cDesignDirectionId)
    {
    this.cDesignDirectionId = cDesignDirectionId;
    }
    
    public java.lang.Long getCDesignManagerId(){
    return cDesignManagerId;
    }
    public void setCDesignManagerId(java.lang.Long cDesignManagerId)
    {
    this.cDesignManagerId = cDesignManagerId;
    }
    
    public java.util.Date getAcceptFromDate(){
    return acceptFromDate;
    }
    public void setAcceptFromDate(java.util.Date acceptFromDate)
    {
    this.acceptFromDate = acceptFromDate;
    }
    
    public java.util.Date getAcceptToDate(){
    return acceptToDate;
    }
    public void setAcceptToDate(java.util.Date acceptToDate)
    {
    this.acceptToDate = acceptToDate;
    }
    
    public java.lang.String getAcceptPlace(){
    return acceptPlace;
    }
    public void setAcceptPlace(java.lang.String acceptPlace)
    {
    this.acceptPlace = acceptPlace;
    }
    
    public java.lang.String getApplyBenchmark(){
    return applyBenchmark;
    }
    public void setApplyBenchmark(java.lang.String applyBenchmark)
    {
    this.applyBenchmark = applyBenchmark;
    }
    
    public java.lang.String getConstructionQuality(){
    return constructionQuality;
    }
    public void setConstructionQuality(java.lang.String constructionQuality)
    {
    this.constructionQuality = constructionQuality;
    }
    
    public java.lang.String getOtherDocuments(){
    return otherDocuments;
    }
    public void setOtherDocuments(java.lang.String otherDocuments)
    {
    this.otherDocuments = otherDocuments;
    }
    
    public java.lang.String getOtherComments(){
    return otherComments;
    }
    public void setOtherComments(java.lang.String otherComments)
    {
    this.otherComments = otherComments;
    }
    
    public java.lang.Long getConclusion(){
    return conclusion;
    }
    public void setConclusion(java.lang.Long conclusion)
    {
    this.conclusion = conclusion;
    }
    
    public java.lang.String getCompleteRequest(){
    return completeRequest;
    }
    public void setCompleteRequest(java.lang.String completeRequest)
    {
    this.completeRequest = completeRequest;
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
    
    public java.lang.Long getDocumentCaId(){
    return documentCaId;
    }
    public void setDocumentCaId(java.lang.Long documentCaId)
    {
    this.documentCaId = documentCaId;
    }
    
    public java.lang.Long getType(){
    return type;
    }
    public void setType(java.lang.Long type)
    {
    this.type = type;
    }
    
    public java.lang.String getIncompleteReason(){
    return incompleteReason;
    }
    public void setIncompleteReason(java.lang.String incompleteReason)
    {
    this.incompleteReason = incompleteReason;
    }
    
    public java.lang.Long getIsActive(){
    return isActive;
    }
    public void setIsActive(java.lang.Long isActive)
    {
    this.isActive = isActive;
    }


	public String getConstrtCode() {
		return constrtCode;
	}
	
	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}
	
	public String getContractCode() {
		return contractCode;
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	public String getContractName() {
		return contractName;
	}
	
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public List<ConstrAcceptWorkListDTO> getConstracceptworklist() {
		return constracceptworklist;
	}

	public void setConstracceptworklist(List<ConstrAcceptWorkListDTO> constracceptworklist) {
		this.constracceptworklist = constracceptworklist;
	}

	public java.lang.String getConstrtName() {
		return constrtName;
	}

	public void setConstrtName(java.lang.String constrtName) {
		this.constrtName = constrtName;
	}

	public java.lang.String getamonitorName() {
		return amonitorName;
	}

	public void setamonitorName(java.lang.String amonitorName) {
		this.amonitorName = amonitorName;
	}

	public java.lang.String getainChargeMonitorName() {
		return ainChargeMonitorName;
	}

	public void setainChargeMonitorName(java.lang.String ainChargeMonitorName) {
		this.ainChargeMonitorName = ainChargeMonitorName;
	}

	public java.lang.String getbdirectorName() {
		return bdirectorName;
	}

	public void setbdirectorName(java.lang.String bdirectorName) {
		this.bdirectorName = bdirectorName;
	}

	public java.lang.String getbinChargeConstructName() {
		return binChargeConstructName;
	}

	public void setbinChargeConstructName(java.lang.String binChargeConstructName) {
		this.binChargeConstructName = binChargeConstructName;
	}

	public java.lang.String getcdesignDirectionName() {
		return cdesignDirectionName;
	}

	public void setcdesignDirectionName(java.lang.String cdesignDirectionName) {
		this.cdesignDirectionName = cdesignDirectionName;
	}

	public java.lang.String getcdesignManagerName() {
		return cdesignManagerName;
	}

	public void setcdesignManagerName(java.lang.String cdesignManagerName) {
		this.cdesignManagerName = cdesignManagerName;
	}
	
	/**
	 * @return the acceptFromDateHour
	 */
	public String getAcceptFromDateHour() {
		if (acceptFromDate != null) {
			
			acceptFromDateHour = acceptFromDate.toString().substring(11, 13);
		}
		return acceptFromDateHour;
	}

	/**
	 * @return the acceptFromDateMinute
	 */
	public String getAcceptFromDateMinute() {
		if (acceptFromDate != null) {
			acceptFromDateMinute = DateFormatUtils.format(acceptFromDate, "mm");
		}
		return acceptFromDateMinute;
	}

	/**
	 * @return the acceptFromDateDay
	 */
	public String getAcceptFromDateDay() {
		if (acceptFromDate != null) {
			acceptFromDateDay = DateFormatUtils.format(acceptFromDate, "dd");
		}
		return acceptFromDateDay;
	}

	/**
	 * @return the acceptFromDateMonth
	 */
	public String getAcceptFromDateMonth() {
		if (acceptFromDate != null) {
			acceptFromDateMonth = DateFormatUtils.format(acceptFromDate, "MM");
		}
		return acceptFromDateMonth;
	}

	/**
	 * @return the acceptFromDateYear
	 */
	public String getAcceptFromDateYear() {
		if (acceptFromDate != null) {
			acceptFromDateYear = DateFormatUtils.format(acceptFromDate, "yyyy");
		}
		return acceptFromDateYear;
	}

	/**
	 * @return the acceptToDateHour
	 */
	public String getAcceptToDateHour() {
		if (acceptToDate != null) {
			acceptToDateHour = acceptToDate.toString().substring(11, 13);
		}
		return acceptToDateHour;
	}

	/**
	 * @return the acceptToDateMinute
	 */
	public String getAcceptToDateMinute() {
		if (acceptToDate != null) {
			acceptToDateMinute = DateFormatUtils.format(acceptToDate, "mm");
		}
		return acceptToDateMinute;
	}

	/**
	 * @return the acceptToDateDay
	 */
	public String getAcceptToDateDay() {
		if (acceptToDate != null) {
			acceptToDateDay = DateFormatUtils.format(acceptToDate, "dd");
		}
		return acceptToDateDay;
	}


	/**
	 * @return the acceptToDateMonth
	 */
	public String getAcceptToDateMonth() {
		if (acceptToDate != null) {
			acceptToDateMonth = DateFormatUtils.format(acceptToDate, "MM");
		}
		return acceptToDateMonth;
	}

	/**
	 * @return the acceptToDateYear
	 */
	public String getAcceptToDateYear() {
		if (acceptToDate != null) {
			acceptToDateYear = DateFormatUtils.format(acceptToDate, "yyyy");
		}
		return acceptToDateYear;
	}

	public String getConclusionaccept() {
		if(Float.floatToRawIntBits(conclusion) == 0){
			conclusionaccept = "V" ;
		}
		return conclusionaccept;
	}

	public void setConclusionaccept(String conclusionaccept) {
		this.conclusionaccept = conclusionaccept;
	}

	public String getConclusionnotaccept() {
		
		if(Float.floatToRawIntBits(conclusion) == 1){
			conclusionnotaccept = "V" ;
		}
		
		return conclusionnotaccept;
	}

	public void setConclusionnotaccept(String conclusionnotaccept) {
		this.conclusionnotaccept = conclusionnotaccept;
	}

	/**
	 * @return the constrtAddress
	 */
	public String getConstrtAddress() {
		return constrtAddress;
	}

	/**
	 * @param constrtAddress the constrtAddress to set
	 */
	public void setConstrtAddress(String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the constracceptmerlist
	 */
	public List<ConstrAcceptMerListDTO> getConstracceptmerlist() {
		return constracceptmerlist;
	}

	/**
	 * @param constracceptmerlist the constracceptmerlist to set
	 */
	public void setConstracceptmerlist(List<ConstrAcceptMerListDTO> constracceptmerlist) {
		this.constracceptmerlist = constracceptmerlist;
	}

	public ConstrCompleteRecordsMapDTO getConstrcompleterecordsmap() {
		return constrcompleterecordsmap;
	}

	public void setConstrcompleterecordsmap(ConstrCompleteRecordsMapDTO constrcompleterecordsmap) {
		this.constrcompleterecordsmap = constrcompleterecordsmap;
	}
	public String getSignDateDay() {
		if (signDate != null) {
			signDateDay = DateFormatUtils.format(signDate, "dd");
		}
		return signDateDay;
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

	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.Long getUserid() {
		return userid;
	}

	public void setUserid(java.lang.Long userid) {
		this.userid = userid;
	}

	public VConstructionHcqtDTO getvConstructionHcqtDTO() {
		return vConstructionHcqtDTO;
	}

	public void setvConstructionHcqtDTO(VConstructionHcqtDTO vConstructionHcqtDTO) {
		this.vConstructionHcqtDTO = vConstructionHcqtDTO;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public List<String> getListDocumentPath() {
		return listDocumentPath;
	}

	public void setListDocumentPath(List<String> listDocumentPath) {
		this.listDocumentPath = listDocumentPath;
	}

	public List<String> getListDocumentName() {
		return listDocumentName;
	}

	public void setListDocumentName(List<String> listDocumentName) {
		this.listDocumentName = listDocumentName;
	}

}
