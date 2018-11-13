package com.viettel.erp.dto;

import java.util.List;

import org.hibernate.type.StringType;

import java.util.Date;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class AMaterialRecoveryMinutesModelDTO {
	private Long amaterialRecoveryMinutesId;
	private Long constructId;
	private String constrtCode;
	private String contractCode;
	private Long statusCa;
	private String code;
	private String contractName;
	private Long adirectorId;
	private Long ahandoverPersonId;
	private Long bdirectorId;
	private Long breceivePersonId;
	private Long isActive;
	private String signPlace;
	private String adirectorName;
	private String ahandoverPersonName;
	private String bdirectorName;
	private String breceivePersonName;
	private String constrtName;
	private Long constrCompReMapId;
	private String constructorName;
	private String provinceName;
	private String stationCode;
	private Date createdDate;
	private String aDirectorPath;
	private String aHandoverPersonPath;
	private String bDirectorPath;
	private String bReceivePersonPath;
	private Long createdUserId;
	private Long contractId;
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private String adirectorNameSign;
	private String ahandoverPersonNameSign;
	private String bdirectorNameSign;
	private String breceivePersonNameSign;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getAdirectorNameSign() {
		return adirectorNameSign;
	}

	public void setAdirectorNameSign(String adirectorNameSign) {
		this.adirectorNameSign = adirectorNameSign;
	}

	public String getAhandoverPersonNameSign() {
		return ahandoverPersonNameSign;
	}

	public void setAhandoverPersonNameSign(String ahandoverPersonNameSign) {
		this.ahandoverPersonNameSign = ahandoverPersonNameSign;
	}

	public String getBdirectorNameSign() {
		return bdirectorNameSign;
	}

	public void setBdirectorNameSign(String bdirectorNameSign) {
		this.bdirectorNameSign = bdirectorNameSign;
	}

	public String getBreceivePersonNameSign() {
		return breceivePersonNameSign;
	}

	public void setBreceivePersonNameSign(String breceivePersonNameSign) {
		this.breceivePersonNameSign = breceivePersonNameSign;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getaDirectorPath() {
		return aDirectorPath;
	}

	public void setaDirectorPath(String aDirectorPath) {
		this.aDirectorPath = aDirectorPath;
	}

	public String getaHandoverPersonPath() {
		return aHandoverPersonPath;
	}

	public void setaHandoverPersonPath(String aHandoverPersonPath) {
		this.aHandoverPersonPath = aHandoverPersonPath;
	}

	public String getbDirectorPath() {
		return bDirectorPath;
	}

	public void setbDirectorPath(String bDirectorPath) {
		this.bDirectorPath = bDirectorPath;
	}

	public String getbReceivePersonPath() {
		return bReceivePersonPath;
	}

	public void setbReceivePersonPath(String bReceivePersonPath) {
		this.bReceivePersonPath = bReceivePersonPath;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getConstructorName() {
		return Strings.nullToEmpty(constructorName);
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	public String getProvinceName() {
		return Strings.nullToEmpty(provinceName);
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getConstrtName() {
		return Strings.nullToEmpty(constrtName);
	}

	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public String getAdirectorName() {
		return adirectorName;
	}

	public void setAdirectorName(String adirectorName) {
		this.adirectorName = adirectorName;
	}

	public String getAhandoverPersonName() {
		return ahandoverPersonName;
	}

	public void setAhandoverPersonName(String ahandoverPersonName) {
		this.ahandoverPersonName = ahandoverPersonName;
	}

	public String getBdirectorName() {
		return bdirectorName;
	}

	public void setBdirectorName(String bdirectorName) {
		this.bdirectorName = bdirectorName;
	}

	public String getBreceivePersonName() {
		return breceivePersonName;
	}

	public void setBreceivePersonName(String breceivePersonName) {
		this.breceivePersonName = breceivePersonName;
	}

	private List<AMaterialRecoveryListDTO> recoveryListDTO = Lists.newArrayList();

	public List<AMaterialRecoveryListDTO> getRecoveryListDTO() {
		return recoveryListDTO;
	}

	public void setRecoveryListDTO(List<AMaterialRecoveryListDTO> recoveryListDTO) {
		this.recoveryListDTO = recoveryListDTO;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Long getAmaterialRecoveryMinutesId() {
		return amaterialRecoveryMinutesId;
	}

	public String getSignPlace() {
		return Strings.nullToEmpty(signPlace);
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public void setAmaterialRecoveryMinutesId(Long amaterialRecoveryMinutesId) {
		this.amaterialRecoveryMinutesId = amaterialRecoveryMinutesId;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
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

	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	public String getCode() {
		return Strings.nullToEmpty(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContractName() {
		return Strings.nullToEmpty(contractName);
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Long getAdirectorId() {
		return adirectorId;
	}

	public void setAdirectorId(Long adirectorId) {
		this.adirectorId = adirectorId;
	}

	public Long getAhandoverPersonId() {
		return ahandoverPersonId;
	}

	public void setAhandoverPersonId(Long ahandoverPersonId) {
		this.ahandoverPersonId = ahandoverPersonId;
	}

	public Long getBdirectorId() {
		return bdirectorId;
	}

	public void setBdirectorId(Long bdirectorId) {
		this.bdirectorId = bdirectorId;
	}

	public Long getBreceivePersonId() {
		return breceivePersonId;
	}

	public void setBreceivePersonId(Long breceivePersonId) {
		this.breceivePersonId = breceivePersonId;
	}

}
