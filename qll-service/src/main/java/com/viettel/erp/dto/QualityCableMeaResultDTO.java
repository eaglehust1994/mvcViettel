/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;
import com.viettel.erp.bo.QualityCableMeaReportBO;
import com.viettel.erp.bo.QualityCableMeaResultBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "QUALITY_CABLE_MEA_RESULTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityCableMeaResultDTO extends BaseFWDTOImpl<QualityCableMeaResultBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -784341014756082527L;
	private java.lang.Long qualityCableMeaResultId;
	private java.lang.String objectChecking;
	private java.lang.Double length;
	private java.lang.Double attenuationLength;
	private java.lang.Double attenuationDegree;
	private java.lang.Double attenuationSum;
	private java.lang.Double attenuationAverage;
	private java.lang.String note;
	private java.lang.Long qualityCableMeaReportId;

	private java.lang.Integer stt;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public java.lang.Integer getStt() {
		return stt;
	}

	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}

	@Override
	public QualityCableMeaResultBO toModel() {
		QualityCableMeaResultBO qualityCableMeaResultBO = new QualityCableMeaResultBO();
		qualityCableMeaResultBO.setQualityCableMeaResultId(this.qualityCableMeaResultId);
		qualityCableMeaResultBO.setObjectChecking(this.objectChecking);
		qualityCableMeaResultBO.setLength(this.length);
		qualityCableMeaResultBO.setAttenuationLength(this.attenuationLength);
		qualityCableMeaResultBO.setAttenuationDegree(this.attenuationDegree);
		qualityCableMeaResultBO.setAttenuationSum(this.attenuationSum);
		qualityCableMeaResultBO.setAttenuationAverage(this.attenuationAverage);
		qualityCableMeaResultBO.setNote(this.note);
		qualityCableMeaResultBO.setQualityCableMeaReport(
				this.qualityCableMeaReportId == null ? null : new QualityCableMeaReportBO(qualityCableMeaReportId));
		return qualityCableMeaResultBO;
	}

	@Override
	public Long getFWModelId() {
		return qualityCableMeaResultId;
	}

	@Override
	public String catchName() {
		return getQualityCableMeaResultId().toString();
	}

	public java.lang.Long getQualityCableMeaResultId() {
		return qualityCableMeaResultId;
	}

	public void setQualityCableMeaResultId(java.lang.Long qualityCableMeaResultId) {
		this.qualityCableMeaResultId = qualityCableMeaResultId;
	}

	public java.lang.String getObjectChecking() {
		return Strings.nullToEmpty(objectChecking);
	}

	public void setObjectChecking(java.lang.String objectChecking) {
		this.objectChecking = objectChecking;
	}

	public java.lang.Double getLength() {
		if(length == null){
			setLength(1D);
		}
		return length;
	}

	public void setLength(java.lang.Double length) {
		this.length = length;
	}

	public java.lang.Double getAttenuationLength() {
		if(attenuationLength == null){
			setAttenuationLength(1D);
		}
		return attenuationLength;
	}

	public void setAttenuationLength(java.lang.Double attenuationLength) {
		this.attenuationLength = attenuationLength;
	}

	public java.lang.Double getAttenuationDegree() {
		if(attenuationDegree == null){
			setAttenuationDegree(1D);
		}
		return attenuationDegree;
	}

	public void setAttenuationDegree(java.lang.Double attenuationDegree) {
		this.attenuationDegree = attenuationDegree;
	}

	public java.lang.Double getAttenuationSum() {
		if(attenuationSum == null){
			setAttenuationSum(1D);
		}
		return attenuationSum;
	}

	public void setAttenuationSum(java.lang.Double attenuationSum) {
		this.attenuationSum = attenuationSum;
	}

	public java.lang.Double getAttenuationAverage() {
		if(attenuationAverage == null){
			setAttenuationAverage(1D);
		}
		return attenuationAverage;
	}

	public void setAttenuationAverage(java.lang.Double attenuationAverage) {
		this.attenuationAverage = attenuationAverage;
	}

	public java.lang.String getNote() {
		return Strings.nullToEmpty(note);
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public java.lang.Long getQualityCableMeaReportId() {
		return qualityCableMeaReportId;
	}

	public void setQualityCableMeaReportId(java.lang.Long qualityCableMeaReportId) {
		this.qualityCableMeaReportId = qualityCableMeaReportId;
	}

}
