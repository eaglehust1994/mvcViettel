package com.viettel.erp.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

import com.google.common.base.Strings;

@XmlRootElement(name = "A_MATERIAL_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhieuYeuCauNghiemThuDTO {
	
	private String constrName= "";
	private String code= "";
	private String address = "";
	private String contractCode= "";
	private String aperson1= "";
	private String hstart= "";
	private String dstart= "";
	private String mstart= "";
	private String ystart= "";
	private String receiveDateInMonth;
	private long signStatus;
	private String receiveMonth="";
	private String receiveYear="";
	private Date receiveDay;
	private String place= "";
	private String constAcceptanceRequestId;
	private String acceptanceDate= "";
	private String constractorName= "";
	private String signPlace= "";
	private Date signDate;
	private String fileType= "";
	private String toGroup= "";
	private String dearGroup= "";
	
	private Long statusca;
	private String sendPersonId;
	private String receivePersonId;
	private String sendPersonIdReady;
	private String sendPersonName;
	private String receivePersonName;

	private String sendPersonNameSign;
	private String receivePersonNameSign;
	
	private java.lang.Double contractId;
	
	
	
	public String getSendPersonNameSign() {
		return Strings.nullToEmpty(sendPersonNameSign);
	}

	public void setSendPersonNameSign(String sendPersonNameSign) {
		this.sendPersonNameSign = sendPersonNameSign;
	}

	public String getReceivePersonNameSign() {
		return Strings.nullToEmpty(receivePersonNameSign);
	}

	public void setReceivePersonNameSign(String receivePersonNameSign) {
		this.receivePersonNameSign = receivePersonNameSign;
	}

	public String getSendPersonId() {
		return sendPersonId;
	}

	public void setSendPersonId(String sendPersonId) {
		this.sendPersonId = sendPersonId;
	}

	public String getReceivePersonId() {
		return receivePersonId;
	}

	public void setReceivePersonId(String receivePersonId) {
		this.receivePersonId = receivePersonId;
	}

	public String getSendPersonName() {
		return sendPersonName;
	}

	public void setSendPersonName(String sendPersonName) {
		this.sendPersonName = sendPersonName;
	}

	public String getReceivePersonName() {
		return Strings.nullToEmpty(receivePersonName);
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	
	public String getSendPersonIdReady() {
		return sendPersonIdReady;
	}

	public void setSendPersonIdReady(String sendPersonIdReady) {
		this.sendPersonIdReady = sendPersonIdReady;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("constrName", constrName).append("code", code)
				.append("address", address).append("contractCode", contractCode).append("aperson1", aperson1)
				.append("hstart", hstart).append("dstart", dstart).append("mstart", mstart).append("ystart", ystart)
				.append("place", place).append("constAcceptanceRequestId", constAcceptanceRequestId)
				.append("acceptanceDate", acceptanceDate).append("constractorName", constractorName).toString();
	}

	/**
	 * @return the constractorName
	 */
	public String getConstractorName() {
		return Strings.nullToEmpty(constractorName);
	}

	/**
	 * @param constractorName the constractorName to set
	 */
	public void setConstractorName(String constractorName) {
		this.constractorName = constractorName;
	}


	public String getConstrName() {
		return Strings.nullToEmpty(constrName);
	}

	public void setConstrName(String constrName) {
		this.constrName = constrName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return  Strings.nullToEmpty(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContractCode() {
		return Strings.nullToEmpty(contractCode);
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getAperson1() {
		return aperson1;
	}

	public void setAperson1(String aperson1) {
		this.aperson1 = aperson1;
	}

	public String getHstart() {
		return hstart;
	}

	public void setHstart(String hstart) {
		this.hstart = hstart;
	}

	public String getDstart() {
		return dstart;
	}

	public void setDstart(String dstart) {
		this.dstart = dstart;
	}

	public String getMstart() {
		return mstart;
	}

	public void setMstart(String mstart) {
		this.mstart = mstart;
	}

	public String getYstart() {
		return ystart;
	}

	public void setYstart(String ystart) {
		this.ystart = ystart;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getConstAcceptanceRequestId() {
		return constAcceptanceRequestId;
	}

	public void setConstAcceptanceRequestId(String constAcceptanceRequestId) {
		this.constAcceptanceRequestId = constAcceptanceRequestId;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	/**
	 * @return the signPlace
	 */
	public String getSignPlace() {
		return Strings.nullToEmpty(signPlace);
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @return the signDay
	 */
	public String getSignDay() {
		if (signDate == null) return "";
		return new DateTime(signDate).dayOfMonth().getAsText();
	}

	/**
	 * @return the signMonth
	 */
	public String getSignMonth() {
		if (signDate == null) return "";
		return new DateTime(signDate).monthOfYear().get() + "";
	}

	/**
	 * @return the signYear
	 */
	public String getSignYear() {
		if (signDate == null) return "";
		return new DateTime(signDate).year().getAsText();
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return Strings.nullToEmpty(fileType);
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the toGroup
	 */
	public String getToGroup() {
		return Strings.nullToEmpty(toGroup);
	}

	/**
	 * @param toGroup the toGroup to set
	 */
	public void setToGroup(String toGroup) {
		this.toGroup = toGroup;
	}

	public Long getStatusca() {
		return statusca;
	}

	public void setStatusca(Long statusca) {
		this.statusca = statusca;
	}

	public String getDearGroup() {
		return Strings.nullToEmpty(dearGroup);
	}

	public void setDearGroup(String dearGroup) {
		this.dearGroup = dearGroup;
	}

	

	public String getReceiveDateInMonth() {
		return Strings.nullToEmpty(receiveDateInMonth);
	}

	public void setReceiveDateInMonth(String receiveDateInMonth) {
		this.receiveDateInMonth = receiveDateInMonth;
	}

	public String getReceiveMonth() {
		return Strings.nullToEmpty(receiveMonth);
	}

	public void setReceiveMonth(String receiveMonth) {
		this.receiveMonth = receiveMonth;
	}

	public String getReceiveYear() {
		return Strings.nullToEmpty(receiveYear);
	}

	public void setReceiveYear(String receiveYear) {
		this.receiveYear = receiveYear;
	}

	public Date getReceiveDay() {
		return receiveDay;
	}

	public void setReceiveDay(Date receiveDay) {
		this.receiveDay = receiveDay;
	}

	public long getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(long signStatus) {
		this.signStatus = signStatus;
	}

	public java.lang.Double getContractId() {
		return contractId;
	}

	public void setContractId(java.lang.Double contractId) {
		this.contractId = contractId;
	}
	
	
}
