/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.TitAziConstrAcceptBO;
import com.viettel.erp.bo.TitAziConstrAcceptListBO;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateDeserializerDOng;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.utils.CustomJsonDateSerializerDOng;
import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializer;
import com.viettel.service.base.dto.BaseFWDTOImpl;
/*import com.viettel.service.base.utils.StringUtils;*/

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "TIT_AZI_CONSTR_ACCEPTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitAziConstrAcceptDTO extends BaseFWDTOImpl<TitAziConstrAcceptBO> {

	private java.lang.Long titAziConstrAcceptId;
	private java.lang.String code;
	private java.lang.Long adirectorId;
	private java.lang.Long ainchargemonitorid;
	private java.lang.Long bdirectorId;
	private java.lang.Long binchargeConstructId;
	
	private java.lang.String adirectorName;
	private java.lang.String ainchargemonitorName;
	private java.lang.String bdirectorName;
	private java.lang.String binchargeConstructName;
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private java.util.Date acceptFromDate;
	private String acceptFromDateHour = "";
	private String acceptFromDateMinute = "";
	private String acceptFromDateDay = "";
	private String acceptFromDateMonth = "";
	private String acceptFromDateYear = "";
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private java.util.Date acceptToDate;
	private String acceptToDateHour = "";
	private String acceptToDateMinute = "";
	private String acceptToDateDay = "";
	private String acceptToDateMonth = "";
	private String acceptToDateYear = "";
	
	private java.lang.String acceptPlace;
	private java.lang.String applyBenchmark;
	private java.lang.String constructionQuality;
	private java.lang.String otherDocuments;
	private java.lang.String otherComments;
	private java.lang.Long conclusion;
	private java.lang.String conclusionExport;
	private java.lang.String completeRequest;
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private java.util.Date createdDate;
	
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;
	
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private java.util.Date signDate;
	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";
	
	private java.lang.String signPlace;
	private java.lang.String comments;
	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	private String constrtCode;
	private String constrtName;
	private String constrtAddress;
	private String contractCode;
	private String contractName;
	private Long constructId;
	private Long contractId;
	private Long constrCompReMapId;
	private String stationCode;
	
	private java.lang.String adirectorPath;
	private java.lang.String ainchargemonitorPath;
	private java.lang.String bdirectorPath;
	private java.lang.String binchargeConstructPath;
	
	private java.lang.String adirectorSignName;
	private java.lang.String ainchargemonitorSignName;
	private java.lang.String bdirectorSignName;
	private java.lang.String binchargeConstructSignName;
	
	private List<TitAziConstrAcceptListDTO> titAziConstrAcceptList = Lists.newArrayList();

	public List<TitAziConstrAcceptListDTO> getTitAziConstrAcceptList() {
		return titAziConstrAcceptList;
	}

	public void setTitAziConstrAcceptList(List<TitAziConstrAcceptListDTO> titAziConstrAcceptList) {
		this.titAziConstrAcceptList = titAziConstrAcceptList;
	}

	@Override
	public TitAziConstrAcceptBO toModel() {
		TitAziConstrAcceptBO titAziConstrAcceptBO = new TitAziConstrAcceptBO();
		titAziConstrAcceptBO.setTitAziConstrAcceptId(this.titAziConstrAcceptId);
		titAziConstrAcceptBO.setCode(this.code);
		titAziConstrAcceptBO.setADirectorId(this.adirectorId);
		titAziConstrAcceptBO.setAInChargeMonitorId(this.ainchargemonitorid);
		titAziConstrAcceptBO.setBDirectorId(this.bdirectorId);
		titAziConstrAcceptBO.setBInChargeConstructId(this.binchargeConstructId);
		titAziConstrAcceptBO.setAcceptFromDate(this.acceptFromDate);
		titAziConstrAcceptBO.setAcceptToDate(this.acceptToDate);
		titAziConstrAcceptBO.setAcceptPlace(this.acceptPlace);
		titAziConstrAcceptBO.setApplyBenchmark(this.applyBenchmark);
		titAziConstrAcceptBO.setConstructionQuality(this.constructionQuality);
		titAziConstrAcceptBO.setOtherDocuments(this.otherDocuments);
		titAziConstrAcceptBO.setOtherComments(this.otherComments);
		titAziConstrAcceptBO.setConclusion(this.conclusion);
		titAziConstrAcceptBO.setCompleteRequest(this.completeRequest);
		titAziConstrAcceptBO.setCreatedDate(this.createdDate);
		titAziConstrAcceptBO.setCreatedUserId(this.createdUserId);
		titAziConstrAcceptBO.setApprovalDate(this.approvalDate);
		titAziConstrAcceptBO.setStatusCa(this.statusCa);
		titAziConstrAcceptBO.setIsActive(this.isActive);
		titAziConstrAcceptBO.setSignDate(this.signDate);
		titAziConstrAcceptBO.setSignPlace(this.signPlace);
		
		titAziConstrAcceptBO.setConstructId(constructId);
		for(TitAziConstrAcceptListDTO  titAziConstrAcceptListDTO : titAziConstrAcceptList){
			TitAziConstrAcceptListBO childBO= titAziConstrAcceptListDTO.toModel();
			childBO.setTitAziConstrAccept(titAziConstrAcceptBO);
			titAziConstrAcceptBO.getTitAziConstrAcceptList().add(childBO);
		}
		
		/*if(constructId != null){
			titAziConstrAcceptBO.setConstrConstructions(new ConstrConstructionsBO(constructId));
		}*/
		return titAziConstrAcceptBO;
	}
	
	

	public java.lang.String getConclusionExport() {
		return Strings.nullToEmpty(conclusionExport);
	}

	public void setConclusionExport(java.lang.String conclusionExport) {
		this.conclusionExport = conclusionExport;
	}

	@Override
	public Long getFWModelId() {
		return titAziConstrAcceptId;
	}

	@Override
	public String catchName() {
		return getTitAziConstrAcceptId().toString();
	}

	public java.lang.Long getTitAziConstrAcceptId() {
		return titAziConstrAcceptId;
	}

	public void setTitAziConstrAcceptId(java.lang.Long titAziConstrAcceptId) {
		this.titAziConstrAcceptId = titAziConstrAcceptId;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	

	public java.util.Date getAcceptFromDate() {
		return acceptFromDate;
	}

	public void setAcceptFromDate(java.util.Date acceptFromDate) {
		this.acceptFromDate = acceptFromDate;
	}

	public java.util.Date getAcceptToDate() {
		return acceptToDate;
	}

	public void setAcceptToDate(java.util.Date acceptToDate) {
		this.acceptToDate = acceptToDate;
	}

	public java.lang.String getAcceptPlace() {
		return acceptPlace;
	}

	public void setAcceptPlace(java.lang.String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	public java.lang.String getApplyBenchmark() {
		return applyBenchmark;
	}

	public void setApplyBenchmark(java.lang.String applyBenchmark) {
		this.applyBenchmark = applyBenchmark;
	}

	public java.lang.String getConstructionQuality() {
		return constructionQuality;
	}

	public void setConstructionQuality(java.lang.String constructionQuality) {
		this.constructionQuality = constructionQuality;
	}

	public java.lang.String getOtherDocuments() {
		return Strings.nullToEmpty(otherDocuments);
	}

	public void setOtherDocuments(java.lang.String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public java.lang.String getOtherComments() {
		return Strings.nullToEmpty(otherComments);
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	public java.lang.Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.Long conclusion) {
		this.conclusion = conclusion;
	}

	public java.lang.String getCompleteRequest() {
		return Strings.nullToEmpty(completeRequest);
	}

	public void setCompleteRequest(java.lang.String completeRequest) {
		this.completeRequest = completeRequest;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	public String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	public java.lang.Long getAdirectorId() {
		return adirectorId;
	}

	public void setAdirectorId(java.lang.Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public java.lang.Long getAinchargemonitorid() {
		return ainchargemonitorid;
	}

	public void setAinchargemonitorid(java.lang.Long ainchargemonitorid) {
		this.ainchargemonitorid = ainchargemonitorid;
	}

	public java.lang.Long getBdirectorId() {
		return bdirectorId;
	}

	public void setBdirectorId(java.lang.Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public java.lang.Long getBinchargeConstructId() {
		return binchargeConstructId;
	}

	public void setBinchargeConstructId(java.lang.Long binchargeConstructId) {
		this.binchargeConstructId = binchargeConstructId;
	}
	
	public String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public String getConstrtAddress() {
		return Strings.nullToEmpty(constrtAddress);
	}

	public void setConstrtAddress(String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}
	
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
	
	
// sign date
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

	public java.lang.String getAdirectorName() {
		return adirectorName;
	}

	public void setAdirectorName(java.lang.String adirectorName) {
		this.adirectorName = adirectorName;
	}

	public java.lang.String getAinchargemonitorName() {
		return ainchargemonitorName;
	}

	public void setAinchargemonitorName(java.lang.String ainchargemonitorName) {
		this.ainchargemonitorName = ainchargemonitorName;
	}

	public java.lang.String getBdirectorName() {
		return bdirectorName;
	}

	public void setBdirectorName(java.lang.String bdirectorName) {
		this.bdirectorName = bdirectorName;
	}

	public java.lang.String getBinchargeConstructName() {
		return binchargeConstructName;
	}

	public void setBinchargeConstructName(java.lang.String binchargeConstructName) {
		this.binchargeConstructName = binchargeConstructName;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	
	
	public java.lang.String getAdirectorPath() {
		return adirectorPath;
	}

	public void setAdirectorPath(java.lang.String adirectorPath) {
		this.adirectorPath = adirectorPath;
	}

	public java.lang.String getAinchargemonitorPath() {
		return ainchargemonitorPath;
	}

	public void setAinchargemonitorPath(java.lang.String ainchargemonitorPath) {
		this.ainchargemonitorPath = ainchargemonitorPath;
	}

	public java.lang.String getBdirectorPath() {
		return bdirectorPath;
	}

	public void setBdirectorPath(java.lang.String bdirectorPath) {
		this.bdirectorPath = bdirectorPath;
	}

	public java.lang.String getBinchargeConstructPath() {
		return binchargeConstructPath;
	}

	public void setBinchargeConstructPath(java.lang.String binchargeConstructPath) {
		this.binchargeConstructPath = binchargeConstructPath;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public java.lang.String getAdirectorSignName() {
		return Strings.nullToEmpty(adirectorSignName);
	}

	public void setAdirectorSignName(java.lang.String adirectorSignName) {
		this.adirectorSignName = adirectorSignName;
	}

	public java.lang.String getAinchargemonitorSignName() {
		return Strings.nullToEmpty(ainchargemonitorSignName);
	}

	public void setAinchargemonitorSignName(java.lang.String ainchargemonitorSignName) {
		this.ainchargemonitorSignName = ainchargemonitorSignName;
	}

	public java.lang.String getBdirectorSignName() {
		return Strings.nullToEmpty(bdirectorSignName);
	}

	public void setBdirectorSignName(java.lang.String bdirectorSignName) {
		this.bdirectorSignName = bdirectorSignName;
	}

	public java.lang.String getBinchargeConstructSignName() {
		return Strings.nullToEmpty(binchargeConstructSignName);
	}

	public void setBinchargeConstructSignName(java.lang.String binchargeConstructSignName) {
		this.binchargeConstructSignName = binchargeConstructSignName;
	}
	
	
	
}
