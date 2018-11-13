package com.viettel.erp.dto;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

public class AMaterialRecoveryListModelDTO {
	private Long merEntityId;
	private String merName;
	private String serialNumber;
	private Long unitId;
	private Double handoverQuantity;
	private Long quantity;
	private Long qualityStatus = 0L;
	private Double acceptQuantity;
	private Long constructId;
	private Long merID;
	private String name;
	private String comments;
	private Double enough = 0D;
	private Double recoveryQuantity;
	private String unitName;
	private Long amaterialRecoveryMinutesId;
	private Long adirectorId;
	private Long ahandoverPersonId;
	private Long bdirectorId;
	private Long breceivePersonId;
	private String constrtCode;
	private String contractCode;
	private String contractName;
	private String constrtName;
	private Long isActive; 
	
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Long getAmaterialRecoveryMinutesId() {
		return amaterialRecoveryMinutesId;
	}

	public void setAmaterialRecoveryMinutesId(Long amaterialRecoveryMinutesId) {
		this.amaterialRecoveryMinutesId = amaterialRecoveryMinutesId;
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

	public String getConstrtName() {
		return constrtName;
	}

	public void setConstrtName(String constrtName) {
		this.constrtName = constrtName;
	}

	public Double getRecoveryQuantity() {
		if(recoveryQuantity == null){
			return 0D;
		}
//		if (acceptQuantity == null) {
//			setAcceptQuantity(0L);
//		}
//		return handoverQuantity - acceptQuantity;
		return recoveryQuantity;
	}

	public Double getOriginRecoveryQuantity() {
		return getRecoveryQuantity();
	}
	
	public void setRecoveryQuantity(Double recoveryQuantity) {
		this.recoveryQuantity = recoveryQuantity;
	}

	public Double getEnough() {
		if (acceptQuantity == null) {
			setAcceptQuantity(0D);
		}
		return handoverQuantity - acceptQuantity;
	}

	public void setEnough(Double enough) {
		this.enough = enough;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(Long qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {

		return getName();
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getMerID() {
		return merID;
	}

	public void setMerID(Long merID) {
		this.merID = merID;
	}

	public Double getHandoverQuantity() {
		return handoverQuantity;
	}

	public void setHandoverQuantity(Double handoverQuantity) {
		this.handoverQuantity = handoverQuantity;
	}

	public Double getAcceptQuantity() {
		if(acceptQuantity == null){
			return 0D;
		}
		return acceptQuantity;
	}

	public void setAcceptQuantity(Double acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	public Long getMerEntityId() {
		return merEntityId;
	}

	public void setMerEntityId(Long merEntityId) {
		this.merEntityId = merEntityId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

}
