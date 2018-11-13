/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author phongpv
 */
@XmlRootElement(name = "CONSTR_ACCEPT_WORK_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptWorkListDTO extends BaseFWDTOImpl<ConstrAcceptWorkListBO> {

	/**
		 * 
		 */
	private static final long serialVersionUID = -5061648941256314135L;
	private java.lang.Long constrAcceptWorkListId;
	private java.lang.String explain;
	private java.lang.Double executeQuantity = 0D;
	private java.lang.Double evaluateQuantity = 0D;
	private java.lang.String evaluateComments;
	private java.lang.String instanceDrawCode;
	private java.lang.String comments = "";
	private java.lang.String acceptWorkcomments = "";
	private java.lang.Long constructionAcceptanceId;
	private java.lang.Long estimatesWorkItemId;
	private java.lang.Double settleUnitPrice;
	private java.lang.Double evaluateUnitPrice;

	private java.lang.String workItemCode = "";
	private java.lang.String workItemName = "";
	private java.lang.Double workAmount = 0D;
	private java.lang.String unit;
	private java.lang.Long type;
	private java.lang.Double deviation = 0D;
	private java.lang.Long constructId;
	private java.lang.Long valueProposed;// giatri quyet toan
	private java.lang.Long typeEstimates;

	private java.lang.String workListUp;
	private java.lang.String workListDown;
	private java.lang.Integer rownum = 0;
	DecimalFormat df = new DecimalFormat("###.###");

	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Override
	public ConstrAcceptWorkListBO toModel() {
		ConstrAcceptWorkListBO constrAcceptWorkListBO = new ConstrAcceptWorkListBO();
		constrAcceptWorkListBO.setConstrAcceptWorkListId(this.constrAcceptWorkListId);
		constrAcceptWorkListBO.setExplain(this.explain);
		constrAcceptWorkListBO.setExecuteQuantity(this.executeQuantity);
		constrAcceptWorkListBO.setEvaluateQuantity(this.evaluateQuantity);
		constrAcceptWorkListBO.setEvaluateComments(this.evaluateComments);
		constrAcceptWorkListBO.setInstanceDrawCode(this.instanceDrawCode);
		constrAcceptWorkListBO.setComments(this.comments);
		// constrAcceptWorkListBO.setConstructionAcceptanceId(this.constructionAcceptanceId);

		constrAcceptWorkListBO.setConstructionacceptance(
				this.constructionAcceptanceId == null ? null : new ConstructionAcceptanceBO(constructionAcceptanceId));

		if (estimatesWorkItemId != null) {
			constrAcceptWorkListBO.setEstimatesworkitems(new EstimatesWorkItemsBO(estimatesWorkItemId));
		}

		// constrAcceptWorkListBO.setEstimatesWorkItemId(this.estimatesWorkItemId);

		constrAcceptWorkListBO.setSettleUnitPrice(this.settleUnitPrice);
		constrAcceptWorkListBO.setEvaluateUnitPrice(this.evaluateUnitPrice);
		return constrAcceptWorkListBO;
	}

	@Override
	public Long getFWModelId() {
		return constrAcceptWorkListId;
	}

	@Override
	public String catchName() {
		return getConstrAcceptWorkListId().toString();
	}

	public java.lang.Long getConstrAcceptWorkListId() {
		return constrAcceptWorkListId;
	}

	public void setConstrAcceptWorkListId(java.lang.Long constrAcceptWorkListId) {
		this.constrAcceptWorkListId = constrAcceptWorkListId;
	}

	public java.lang.String getExplain() {
		return explain;
	}

	public void setExplain(java.lang.String explain) {
		this.explain = explain;
	}

	public java.lang.Double getExecuteQuantity() {
		return executeQuantity;
	}

	public void setExecuteQuantity(java.lang.Double executeQuantity) {
		this.executeQuantity = executeQuantity;
	}

	public java.lang.Double getEvaluateQuantity() {
		return evaluateQuantity;
	}

	public void setEvaluateQuantity(java.lang.Double evaluateQuantity) {
		this.evaluateQuantity = evaluateQuantity;
	}

	public java.lang.String getEvaluateComments() {
		return evaluateComments;
	}

	public void setEvaluateComments(java.lang.String evaluateComments) {
		this.evaluateComments = evaluateComments;
	}

	public java.lang.String getInstanceDrawCode() {
		return instanceDrawCode;
	}

	public void setInstanceDrawCode(java.lang.String instanceDrawCode) {
		this.instanceDrawCode = instanceDrawCode;
	}

	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	public java.lang.Long getConstructionAcceptanceId() {
		return constructionAcceptanceId;
	}

	public void setConstructionAcceptanceId(java.lang.Long constructionAcceptanceId) {
		this.constructionAcceptanceId = constructionAcceptanceId;
	}

	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	public java.lang.Double getSettleUnitPrice() {
		return settleUnitPrice;
	}

	public void setSettleUnitPrice(java.lang.Double settleUnitPrice) {
		this.settleUnitPrice = settleUnitPrice;
	}

	public java.lang.Double getEvaluateUnitPrice() {
		return evaluateUnitPrice;
	}

	public void setEvaluateUnitPrice(java.lang.Double evaluateUnitPrice) {
		this.evaluateUnitPrice = evaluateUnitPrice;
	}

	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.String getWorkItemName() {
		return workItemName;
	}

	public void setWorkItemName(java.lang.String workItemName) {
		this.workItemName = workItemName;
	}

	public java.lang.String getWorkItemCode() {
		return workItemCode;
	}

	public void setWorkItemCode(java.lang.String workItemCode) {
		this.workItemCode = workItemCode;
	}

	public java.lang.Double getWorkAmount() {
		return workAmount;
	}

	public void setWorkAmount(java.lang.Double workAmount) {
		this.workAmount = workAmount;
	}

	/**
	 * @return the deviation
	 */
	public java.lang.Double getDeviation() {

		if (workAmount == null) {
			workAmount = 0D;
		} else if (executeQuantity == null) {
			executeQuantity = 0D;
		}

		deviation = workAmount - executeQuantity;
		return deviation;
	}

	/**
	 * @param deviation
	 *            the deviation to set
	 */
	public void setDeviation(java.lang.Double deviation) {
		this.deviation = deviation;
	}

	public java.lang.Long getValueProposed() {
		if(valueProposed ==null){
			return 0L;
		}
		return valueProposed;
	}

	public void setValueProposed(java.lang.Long valueProposed) {
		this.valueProposed = valueProposed;
	}

	public java.lang.Long getTypeEstimates() {
		return typeEstimates;
	}

	public void setTypeEstimates(java.lang.Long typeEstimates) {
		this.typeEstimates = typeEstimates;
	}

	public java.lang.Integer getRownum() {
		return rownum;
	}

	public void setRownum(java.lang.Integer rownum) {
		this.rownum = rownum;
	}

	public java.lang.String getWorkListUp() {

		if (evaluateQuantity != null && executeQuantity != null) {
			if (evaluateQuantity > executeQuantity) {
				workListUp = String.valueOf(df.format(evaluateQuantity - executeQuantity));

			} else {
				workListUp = "";
			}

		}

		return workListUp;
	}

	public void setWorkListUp(java.lang.String workListUp) {
		this.workListUp = workListUp;
	}

	public java.lang.String getWorkListDown() {
		if (evaluateQuantity != null && executeQuantity != null) {
			if (executeQuantity > evaluateQuantity) {
				workListDown = String.valueOf(df.format(executeQuantity - evaluateQuantity));
			} else {
				workListDown = "";
			}
		}
		return workListDown;
	}

	public void setWorkListDown(java.lang.String workListDown) {
		this.workListDown = workListDown;
	}

	public java.lang.String getAcceptWorkcomments() {
		return acceptWorkcomments;
	}

	public void setAcceptWorkcomments(java.lang.String acceptWorkcomments) {
		this.acceptWorkcomments = acceptWorkcomments;
	}

}
