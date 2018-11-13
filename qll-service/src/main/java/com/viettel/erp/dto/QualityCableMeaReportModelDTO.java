package com.viettel.erp.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.type.StringType;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityCableMeaReportModelDTO {
	private Long qualityCableMeaReportId;
	private Long constructId;
	private String constrtCode;
	private String contractCode;
	private Long statusCa;
	private String code;
	private String contractName;
	private Long aMonitorId;
	private Long bConstructId;
	private Long isActive;
	private String constructorName;
	private String afullName;
	private String bfullName;
	private String constrtName;
	private Date signDate;
	private Long constrCompReMapId;
	private String signPlace;
	private String stationCode;
	private String aMonitorPath;
	private String bConstructPath;
	private Long createdUserId;
	private Long contractId;

	
	private String comments;
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private String afullNameSign;
	private String bfullNamesign;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getAfullNameSign() {
		return afullNameSign;
	}

	public void setAfullNameSign(String afullNameSign) {
		this.afullNameSign = afullNameSign;
	}

	public String getBfullNamesign() {
		return bfullNamesign;
	}

	public void setBfullNamesign(String bfullNamesign) {
		this.bfullNamesign = bfullNamesign;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getaMonitorPath() {
		return aMonitorPath;
	}

	public void setaMonitorPath(String aMonitorPath) {
		this.aMonitorPath = aMonitorPath;
	}

	public String getbConstructPath() {
		return bConstructPath;
	}

	public void setbConstructPath(String bConstructPath) {
		this.bConstructPath = bConstructPath;
	}

	private Date createdDate;

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
		return Strings.nullToEmpty(signPlace);
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	private List<QualityCableMeaResultDTO> listResultDTO = Lists.newArrayList();

	public List<QualityCableMeaResultDTO> getListResultDTO() {
		return listResultDTO;
	}

	public void setListResultDTO(List<QualityCableMeaResultDTO> listResultDTO) {
		this.listResultDTO = listResultDTO;
	}

	public String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public String getAfullName() {
		return Strings.nullToEmpty(afullName);
	}

	public void setAfullName(String afullName) {
		this.afullName = afullName;
	}

	public String getBfullName() {
		return Strings.nullToEmpty(bfullName);
	}

	public void setBfullName(String bfullName) {
		this.bfullName = bfullName;
	}

	public String getConstructorName() {
		return Strings.nullToEmpty(constructorName);
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	/**
	 * @return the constructId
	 */

	public Long getConstructId() {
		return constructId;
	}

	public Long getaMonitorId() {
		return aMonitorId;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public void setaMonitorId(Long aMonitorId) {
		this.aMonitorId = aMonitorId;
	}

	public Long getbConstructId() {
		return bConstructId;
	}

	public void setbConstructId(Long bConstructId) {
		this.bConstructId = bConstructId;
	}

	public Long getQualityCableMeaReportId() {
		return qualityCableMeaReportId;
	}

	public void setQualityCableMeaReportId(Long qualityCableMeaReportId) {
		this.qualityCableMeaReportId = qualityCableMeaReportId;
	}

	/**
	 * @param constructId
	 *            the constructId to set
	 */
	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	/**
	 * @return the constrtCode
	 */
	public String getConstrtCode() {
		return Strings.nullToEmpty(constrtCode);
	}

	/**
	 * @param constrtCode
	 *            the constrtCode to set
	 */
	public void setConstrtCode(String constrtCode) {
		this.constrtCode = constrtCode;
	}

	/**
	 * @return the contractCode
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * @param contractCode
	 *            the contractCode to set
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * @return the statusCa
	 */
	public Long getStatusCa() {
		return statusCa;
	}

	/**
	 * @param statusCa
	 *            the statusCa to set
	 */
	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName
	 *            the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
}
