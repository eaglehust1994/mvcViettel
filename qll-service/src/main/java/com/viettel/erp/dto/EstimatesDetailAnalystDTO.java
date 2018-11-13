/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "ESTIMATES_DETAIL_ANALYSTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatesDetailAnalystDTO extends BaseFWDTOImpl<EstimatesDetailAnalystBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6493005954929145978L;
	private Long providedGroup;
	private Long estDetailAnalystId;
	private Long type;
	private String costIngredientCode;
	private String costIngredientName;
	private String unit;
	private Double normIndex;
	private Double unitPrice;
	private Double coefficient;
	private Double totalMoney;
	private Long estimatesWorkItemId;
	private String estimatesWorkItemName;
	private Long progressType;
	private String totalMoneyFormula;
	private Long constructId;
	
	private String workItemName;
	public String getWorkItemName() {
		return workItemName;
	}

	public void setWorkItemName(String workItemName) {
		this.workItemName = workItemName;
	}

	public String getWorkItemCode() {
		return workItemCode;
	}

	public void setWorkItemCode(String workItemCode) {
		this.workItemCode = workItemCode;
	}



	private String workItemCode;
	
	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}
	
	

	private EstimatesWorkItemsDTO estimatesWorkItemsDTO;

	@Override
	public EstimatesDetailAnalystBO toModel() {
		EstimatesDetailAnalystBO estimatesDetailAnalystBO = new EstimatesDetailAnalystBO();
		estimatesDetailAnalystBO.setProvidedGroup(this.providedGroup);
		estimatesDetailAnalystBO.setEstDetailAnalystId(this.estDetailAnalystId);
		estimatesDetailAnalystBO.setType(this.type);
		estimatesDetailAnalystBO.setCostIngredientCode(this.costIngredientCode);
		estimatesDetailAnalystBO.setCostIngredientName(this.costIngredientName);
		estimatesDetailAnalystBO.setUnit(this.unit);
		estimatesDetailAnalystBO.setNormIndex(this.normIndex);
		estimatesDetailAnalystBO.setUnitPrice(this.unitPrice);
		estimatesDetailAnalystBO.setCoefficient(this.coefficient);
		estimatesDetailAnalystBO.setTotalMoney(this.totalMoney);
		estimatesDetailAnalystBO.setEstimatesWorkItems(estimatesWorkItemId == null ? null : new EstimatesWorkItemsBO(estimatesWorkItemId));
		estimatesDetailAnalystBO.setProgressType(this.progressType);
		estimatesDetailAnalystBO.setTotalMoneyFormula(this.totalMoneyFormula);
		return estimatesDetailAnalystBO;
	}

	public Long getProvidedGroup() {
		return providedGroup;
	}

	public void setProvidedGroup(Long providedGroup) {
		this.providedGroup = providedGroup;
	}

	@Override
	public Long getFWModelId() {
		return estDetailAnalystId;
	}

	@Override
	public String catchName() {
		return getEstDetailAnalystId().toString();
	}

	public Long getEstDetailAnalystId() {
		return estDetailAnalystId;
	}

	public void setEstDetailAnalystId(Long estDetailAnalystId) {
		this.estDetailAnalystId = estDetailAnalystId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getCostIngredientCode() {
		return costIngredientCode;
	}

	public void setCostIngredientCode(String costIngredientCode) {
		this.costIngredientCode = costIngredientCode;
	}

	public String getCostIngredientName() {
		return costIngredientName;
	}

	public void setCostIngredientName(String costIngredientName) {
		this.costIngredientName = costIngredientName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getNormIndex() {
		return normIndex;
	}

	public void setNormIndex(Double normIndex) {
		this.normIndex = normIndex;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	public Long getProgressType() {
		return progressType;
	}

	public void setProgressType(Long progressType) {
		this.progressType = progressType;
	}

	/**
	 * @return the estimatesWorkItemsDTO
	 */
	public EstimatesWorkItemsDTO getEstimatesWorkItemsDTO() {
		return estimatesWorkItemsDTO;
	}

	/**
	 * @param estimatesWorkItemsDTO the estimatesWorkItemsDTO to set
	 */
	public void setEstimatesWorkItemsDTO(EstimatesWorkItemsDTO estimatesWorkItemsDTO) {
		this.estimatesWorkItemsDTO = estimatesWorkItemsDTO;
	}

	public String getTotalMoneyFormula() {
		return this.totalMoneyFormula;
	}
	public void setTotalMoneyFormula(String totalMoneyFormula) {
		this.totalMoneyFormula = totalMoneyFormula;
	}

	public String getEstimatesWorkItemName() {
		return estimatesWorkItemName;
	}

	public void setEstimatesWorkItemName(String estimatesWorkItemName) {
		this.estimatesWorkItemName = estimatesWorkItemName;
	}
}
