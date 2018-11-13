/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "QUALITY_CABLE_MEA_RESULT")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class QualityCableMeaResultBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8136453502685749195L;
	private Long qualityCableMeaResultId;
	private String objectChecking;
	private Double length;
	private Double attenuationLength;
	private Double attenuationDegree;
	private Double attenuationSum;
	private Double attenuationAverage;
	private String note;

	private QualityCableMeaReportBO qualityCableMeaReport;

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "QUALITY_CABLE_MEA_RESULT_SEQ") })
	@Column(name = "QUALITY_CABLE_MEA_RESULT_ID", length = 22)
	public Long getQualityCableMeaResultId() {
		return qualityCableMeaResultId;
	}

	public void setQualityCableMeaResultId(Long qualityCableMeaResultId) {
		this.qualityCableMeaResultId = qualityCableMeaResultId;
	}

	@Column(name = "OBJECT_CHECKING", length = 400)
	public String getObjectChecking() {
		return objectChecking;
	}

	public void setObjectChecking(String objectChecking) {
		this.objectChecking = objectChecking;
	}

	@Column(name = "LENGTH", length = 22)
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Column(name = "ATTENUATION_LENGTH", length = 22)
	public Double getAttenuationLength() {
		return attenuationLength;
	}

	public void setAttenuationLength(Double attenuationLength) {
		this.attenuationLength = attenuationLength;
	}

	@Column(name = "ATTENUATION_DEGREE", length = 22)
	public Double getAttenuationDegree() {
		return attenuationDegree;
	}

	public void setAttenuationDegree(Double attenuationDegree) {
		this.attenuationDegree = attenuationDegree;
	}

	@Column(name = "ATTENUATION_SUM", length = 22)
	public Double getAttenuationSum() {
		return attenuationSum;
	}

	public void setAttenuationSum(Double attenuationSum) {
		this.attenuationSum = attenuationSum;
	}

	@Column(name = "ATTENUATION_AVERAGE", length = 22)
	public Double getAttenuationAverage() {
		return attenuationAverage;
	}

	public void setAttenuationAverage(Double attenuationAverage) {
		this.attenuationAverage = attenuationAverage;
	}

	@Column(name = "NOTE", length = 1000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne
	@JoinColumn(name = "QUALITY_CABLE_MEA_REPORT_ID")
	public QualityCableMeaReportBO getQualityCableMeaReport() {
		return qualityCableMeaReport;
	}

	public void setQualityCableMeaReport(QualityCableMeaReportBO qualityCableMeaReport) {
		this.qualityCableMeaReport = qualityCableMeaReport;
	}

	public QualityCableMeaResultBO() {
		setColId("qualityCableMeaResultId");
		setColName("qualityCableMeaResultId");
		setUniqueColumn(new String[] { "qualityCableMeaResultId" });
	}

	@Override
	public QualityCableMeaResultDTO toDTO() {
		QualityCableMeaResultDTO qualityCableMeaResultDTO = new QualityCableMeaResultDTO();
		// set cac gia tri
		qualityCableMeaResultDTO.setQualityCableMeaResultId(this.qualityCableMeaResultId);
		qualityCableMeaResultDTO.setObjectChecking(this.objectChecking);
		qualityCableMeaResultDTO.setLength(this.length);
		qualityCableMeaResultDTO.setAttenuationLength(this.attenuationLength);
		qualityCableMeaResultDTO.setAttenuationDegree(this.attenuationDegree);
		qualityCableMeaResultDTO.setAttenuationSum(this.attenuationSum);
		qualityCableMeaResultDTO.setAttenuationAverage(this.attenuationAverage);
		qualityCableMeaResultDTO.setNote(this.note);
		qualityCableMeaResultDTO.setQualityCableMeaReportId(
				this.qualityCableMeaReport == null ? null : qualityCableMeaReport.getQualityCableMeaReportId());
		return qualityCableMeaResultDTO;
	}

}
