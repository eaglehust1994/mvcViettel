package com.viettel.erp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;

@XmlRootElement(name = "A_MATERIAL_HANDOVERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BienBanBanGiaoAcapDTO {
	
	private String constrName;
	private String hangMuc;
	private String code;
	private String address;
	private String contractCode;
	private String aperson1;
	private String aperson1Orange;
	private String aperson2;
	private String aperson2Orange;
	private String bperson1;
	private String bperson1Orange;
	private String bperson2;
	private String bperson2Orange;
	private String hstart;
	private String dstart;
	private String mstart;
	private String ystart;
	private String otherComment;
	
	private String hend;
	private String dend;
	private String mend;
	private String yend;
	
	private String handoverFromDate;
	private String handoverToDate;
	
	private String place;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date signDate;
	private java.lang.String signPlace;
	
	private String signDateDay = "";
	private String signDateMonth = "";
	private String signDateYear = "";
	
	private String stationCode;
	
	private java.lang.String adirectorIdPath;
	private java.lang.String ahandoverPersonIdPath;
	private java.lang.String bdirectorIdPath;
	private java.lang.String breceivePersonIdPath;
	private Long statusCa;
	
	private String aperson1Sign;
	private String aperson2Sign;
	private String bperson1Sign;
	private String bperson2Sign;
	
	public String getAperson1Sign() {
		return aperson1Sign;
	}

	public void setAperson1Sign(String aperson1Sign) {
		this.aperson1Sign = aperson1Sign;
	}

	public String getAperson2Sign() {
		return aperson2Sign;
	}

	public void setAperson2Sign(String aperson2Sign) {
		this.aperson2Sign = aperson2Sign;
	}

	public String getBperson1Sign() {
		return bperson1Sign;
	}

	public void setBperson1Sign(String bperson1Sign) {
		this.bperson1Sign = bperson1Sign;
	}

	public String getBperson2Sign() {
		return bperson2Sign;
	}

	public void setBperson2Sign(String bperson2Sign) {
		this.bperson2Sign = bperson2Sign;
	}

	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	public java.lang.String getAdirectorIdPath() {
		return adirectorIdPath;
	}

	public void setAdirectorIdPath(java.lang.String adirectorIdPath) {
		this.adirectorIdPath = adirectorIdPath;
	}

	public java.lang.String getAhandoverPersonIdPath() {
		return ahandoverPersonIdPath;
	}

	public void setAhandoverPersonIdPath(java.lang.String ahandoverPersonIdPath) {
		this.ahandoverPersonIdPath = ahandoverPersonIdPath;
	}

	public java.lang.String getBdirectorIdPath() {
		return bdirectorIdPath;
	}

	public void setBdirectorIdPath(java.lang.String bdirectorIdPath) {
		this.bdirectorIdPath = bdirectorIdPath;
	}

	public java.lang.String getBreceivePersonIdPath() {
		return breceivePersonIdPath;
	}

	public void setBreceivePersonIdPath(java.lang.String breceivePersonIdPath) {
		this.breceivePersonIdPath = breceivePersonIdPath;
	}

	private List<AMaterialHandoverMerListDTO> vttbList = Lists.newArrayList();

	
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
	
	public String getConstrName() {
		return Strings.nullToEmpty(constrName);
	}

	public void setConstrName(String constrName) {
		this.constrName = constrName;
	}

	public String getCode() {
		return Strings.nullToEmpty(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return Strings.nullToEmpty(address);
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
		return Strings.nullToEmpty(aperson1);
	}

	public void setAperson1(String aperson1) {
		this.aperson1 = aperson1;
	}

	public String getAperson1Orange() {
		return Strings.nullToEmpty(aperson1Orange);
	}

	public void setAperson1Orange(String aperson1Orange) {
		this.aperson1Orange = aperson1Orange;
	}

	public String getAperson2() {
		return Strings.nullToEmpty(aperson2);
	}

	public void setAperson2(String aperson2) {
		this.aperson2 = aperson2;
	}

	public String getAperson2Orange() {
		return Strings.nullToEmpty(aperson2Orange);
	}

	public void setAperson2Orange(String aperson2Orange) {
		this.aperson2Orange = aperson2Orange;
	}

	public String getBperson1() {
		return Strings.nullToEmpty(bperson1);
	}

	public void setBperson1(String bperson1) {
		this.bperson1 = bperson1;
	}

	public String getBperson1Orange() {
		return Strings.nullToEmpty(bperson1Orange);
	}

	public void setBperson1Orange(String bperson1Orange) {
		this.bperson1Orange = bperson1Orange;
	}

	public String getBperson2() {
		return Strings.nullToEmpty(bperson2);
	}

	public void setBperson2(String bperson2) {
		this.bperson2 = bperson2;
	}

	public String getBperson2Orange() {
		return Strings.nullToEmpty(bperson2Orange);
	}

	public void setBperson2Orange(String bperson2Orange) {
		this.bperson2Orange = bperson2Orange;
	}

	public String getHstart() {
		return Strings.nullToEmpty(hstart);
	}

	public void setHstart(String hstart) {
		this.hstart = hstart;
	}

	public String getDstart() {
		return Strings.nullToEmpty(dstart);
	}

	public void setDstart(String dstart) {
		this.dstart = dstart;
	}

	public String getMstart() {
		return Strings.nullToEmpty(mstart);
	}

	public void setMstart(String mstart) {
		this.mstart = mstart;
	}

	public String getYstart() {
		return Strings.nullToEmpty(ystart);
	}

	public void setYstart(String ystart) {
		this.ystart = ystart;
	}

	public String getHend() {
		return Strings.nullToEmpty(hend);
	}

	public void setHend(String hend) {
		this.hend = hend;
	}

	public String getDend() {
		return Strings.nullToEmpty(dend);
	}

	public void setDend(String dend) {
		this.dend = dend;
	}

	public String getMend() {
		return Strings.nullToEmpty(mend);
	}

	public void setMend(String mend) {
		this.mend = mend;
	}

	public String getYend() {
		return Strings.nullToEmpty(yend);
	}

	public void setYend(String yend) {
		this.yend = yend;
	}

	public String getPlace() {
		return Strings.nullToEmpty(place);
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<AMaterialHandoverMerListDTO> getVttbList() {
		return vttbList;
	}

	public void setVttbList(List<AMaterialHandoverMerListDTO> vttbList) {
		this.vttbList = vttbList;
	}

	public String getHangMuc() {
		return Strings.nullToEmpty(hangMuc);
	}

	public void setHangMuc(String hangMuc) {
		this.hangMuc = hangMuc;
	}

	public String getHandoverFromDate() {
		return Strings.nullToEmpty(handoverFromDate);
	}

	public void setHandoverFromDate(String handoverFromDate) {
		this.handoverFromDate = handoverFromDate;
	}

	public String getHandoverToDate() {
		return Strings.nullToEmpty(handoverToDate);
	}

	public void setHandoverToDate(String handoverToDate) {
		this.handoverToDate = handoverToDate;
	}

	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	public java.lang.String getSignPlace() {
		return Strings.nullToEmpty(signPlace);
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	public String getStationCode() {
		return Strings.nullToEmpty(stationCode);
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getOtherComment() {
		return Strings.nullToEmpty(otherComment);
	}

	public void setOtherComment(String otherComment) {
		this.otherComment = otherComment;
	}
	
	
	
}
