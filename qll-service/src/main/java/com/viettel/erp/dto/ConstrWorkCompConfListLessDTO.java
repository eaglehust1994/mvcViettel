package com.viettel.erp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.base.Strings;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrWorkCompConfListLessDTO {
	private Long constrWorkCompListId;
	private String workItemName;
	private String unit;
	private Double workAmount;
	private Long estimatesWorkItemId;
	private Double executeQuantity;
	private String comments;
    private Long stt;
	public Double getExecuteQuantity() {
		return executeQuantity;
	}

	public void setExecuteQuantity(Double executeQuantity) {
		this.executeQuantity = executeQuantity;
	}

	public String getComments() {
		return  Strings.nullToEmpty(comments) ;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getWorkItemName() {
		return workItemName;
	}

	public void setWorkItemName(String workItemName) {
		this.workItemName = workItemName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getWorkAmount() {
		return workAmount;
	}

	public void setWorkAmount(Double workAmount) {
		this.workAmount = workAmount;
	}

	public Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	public Long getConstrWorkCompListId() {
		return constrWorkCompListId;
	}

	public void setConstrWorkCompListId(Long constrWorkCompListId) {
		this.constrWorkCompListId = constrWorkCompListId;
	}

	public Long getStt() {
		return stt;
	}

	public void setStt(Long stt) {
		this.stt = stt;
	}
	

}
