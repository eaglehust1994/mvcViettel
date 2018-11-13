package com.viettel.erp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class approDTO {
	private Long statusCa;
	private Long employeeId;
	private Long createUserId;
	private String comments;
	private Long constrCompReMapId;
	/////////////// ////////////////////
	private Long isLast;
	private Long approvalOrder;
	private Long levelOrder;
	private Long constructId;
	////////////////// id bảng mỗi người /////////////////////////
	private Long currentStateHandoverId;
	private Long qualityCableMeaReportId;
	private Long workItemsAcceptanceId;
	private Long sceneGenerateWorkId;
	private Long disUnloadConsMinId;
	private Long categoryAcceptanceId;
	private Long bmaterialAcceptanceId;
	private Long titAziConstrAcceptId;
	private Long constrWorkLogsId;
	private Long constrWoLogsLabId;
	private Long detailSettlementProposalId;
	private Long detailSettlementEvaluateId;
	private Long constrGroundHandoverId;
	private Long estimatesWorkItemId;

	public Long getConstrWoLogsLabId() {
		return constrWoLogsLabId;
	}

	public Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	public void setConstrWoLogsLabId(Long constrWoLogsLabId) {
		this.constrWoLogsLabId = constrWoLogsLabId;
	}

	public Long getConstrGroundHandoverId() {
		return constrGroundHandoverId;
	}

	public void setConstrGroundHandoverId(Long constrGroundHandoverId) {
		this.constrGroundHandoverId = constrGroundHandoverId;
	}

	public Long getDetailSettlementEvaluateId() {
		return detailSettlementEvaluateId;
	}

	public void setDetailSettlementEvaluateId(Long detailSettlementEvaluateId) {
		this.detailSettlementEvaluateId = detailSettlementEvaluateId;
	}

	public Long getDetailSettlementProposalId() {
		return detailSettlementProposalId;
	}

	public void setDetailSettlementProposalId(Long detailSettlementProposalId) {
		this.detailSettlementProposalId = detailSettlementProposalId;
	}

	public Long getConstrWorkLogsId() {
		return constrWorkLogsId;
	}

	public void setConstrWorkLogsId(Long constrWorkLogsId) {
		this.constrWorkLogsId = constrWorkLogsId;
	}

	public Long getIsLast() {
		return isLast;
	}

	public void setIsLast(Long isLast) {
		this.isLast = isLast;
	}

	public Long getApprovalOrder() {
		return approvalOrder;
	}

	public void setApprovalOrder(Long approvalOrder) {
		this.approvalOrder = approvalOrder;
	}

	public Long getLevelOrder() {
		return levelOrder;
	}

	public void setLevelOrder(Long levelOrder) {
		this.levelOrder = levelOrder;
	}

	public Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public Long getBmaterialAcceptanceId() {
		return bmaterialAcceptanceId;
	}

	public void setBmaterialAcceptanceId(Long bmaterialAcceptanceId) {
		this.bmaterialAcceptanceId = bmaterialAcceptanceId;
	}

	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getComments() {
		return comments;
	}

	public Long getQualityCableMeaReportId() {
		return qualityCableMeaReportId;
	}

	public void setQualityCableMeaReportId(Long qualityCableMeaReportId) {
		this.qualityCableMeaReportId = qualityCableMeaReportId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getCurrentStateHandoverId() {
		return currentStateHandoverId;
	}

	public void setCurrentStateHandoverId(Long currentStateHandoverId) {
		this.currentStateHandoverId = currentStateHandoverId;
	}

	public Long getWorkItemsAcceptanceId() {
		return workItemsAcceptanceId;
	}

	public void setWorkItemsAcceptanceId(Long workItemsAcceptanceId) {
		this.workItemsAcceptanceId = workItemsAcceptanceId;
	}

	public Long getSceneGenerateWorkId() {
		return sceneGenerateWorkId;
	}

	public void setSceneGenerateWorkId(Long sceneGenerateWorkId) {
		this.sceneGenerateWorkId = sceneGenerateWorkId;
	}

	public Long getDisUnloadConsMinId() {
		return disUnloadConsMinId;
	}

	public void setDisUnloadConsMinId(Long disUnloadConsMinId) {
		this.disUnloadConsMinId = disUnloadConsMinId;
	}

	public Long getCategoryAcceptanceId() {
		return categoryAcceptanceId;
	}

	public void setCategoryAcceptanceId(Long categoryAcceptanceId) {
		this.categoryAcceptanceId = categoryAcceptanceId;
	}

	public Long getTitAziConstrAcceptId() {
		return titAziConstrAcceptId;
	}

	public void setTitAziConstrAcceptId(Long titAziConstrAcceptId) {
		this.titAziConstrAcceptId = titAziConstrAcceptId;
	}

	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

}
