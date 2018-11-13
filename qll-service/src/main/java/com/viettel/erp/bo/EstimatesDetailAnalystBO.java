/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
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
import com.google.common.base.MoreObjects;

@Entity(name = "estimatesDetailAnalysts")
@Table(name = "ESTIMATES_DETAIL_ANALYST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class EstimatesDetailAnalystBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75881841505334561L;
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
	private EstimatesWorkItemsBO estimatesWorkItems;
	private Long progressType;
	private String totalMoneyFormula;
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("costIngredientCode", costIngredientCode).add("costIngredientName", costIngredientName)
				.add("unit", unit).add("normIndex", normIndex).add("unitPrice", unitPrice)
				.add("coefficient", coefficient).add("totalMoney", totalMoney)
				.add("progressType", progressType)
				.add("totalMoneyFormula", totalMoneyFormula)
				.toString();
	}

	public EstimatesDetailAnalystBO() {
		setColId("estDetailAnalystId");
		setColName("estDetailAnalystId");
		setUniqueColumn(new String[] { "estDetailAnalystId" });
	}

	@Column(name = "PROVIDED_GROUP", length = 22)
	public Long getProvidedGroup() {
		return providedGroup;
	}

	public void setProvidedGroup(Long providedGroup) {
		this.providedGroup = providedGroup;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "ESTIMATES_DETAIL_ANALYST_SEQ") })
	@Column(name = "EST_DETAIL_ANALYST_ID", length = 22)
	public Long getEstDetailAnalystId() {
		return estDetailAnalystId;
	}

	public void setEstDetailAnalystId(Long estDetailAnalystId) {
		this.estDetailAnalystId = estDetailAnalystId;
	}

	@Column(name = "TYPE", length = 22)
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "COST_INGREDIENT_CODE", length = 400)
	public String getCostIngredientCode() {
		return costIngredientCode;
	}

	public void setCostIngredientCode(String costIngredientCode) {
		this.costIngredientCode = costIngredientCode;
	}

	@Column(name = "COST_INGREDIENT_NAME", length = 400)
	public String getCostIngredientName() {
		return costIngredientName;
	}

	public void setCostIngredientName(String costIngredientName) {
		this.costIngredientName = costIngredientName;
	}

	@Column(name = "UNIT", length = 200)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "NORM_INDEX", length = 22)
	public Double getNormIndex() {
		return normIndex;
	}

	public void setNormIndex(Double normIndex) {
		this.normIndex = normIndex;
	}

	@Column(name = "UNIT_PRICE", length = 22)
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "COEFFICIENT", length = 22)
	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "TOTAL_MONEY", length = 22)
	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the estimatesWorkItems
	 */
	@ManyToOne
	@JoinColumn(name = "ESTIMATES_WORK_ITEM_ID")
	public EstimatesWorkItemsBO getEstimatesWorkItems() {
		return estimatesWorkItems;
	}

	/**
	 * @param estimatesWorkItems the estimatesWorkItems to set
	 */
	public void setEstimatesWorkItems(EstimatesWorkItemsBO estimatesWorkItems) {
		this.estimatesWorkItems = estimatesWorkItems;
	}

	@Column(name = "PROGRESS_TYPE", length = 22)
	public Long getProgressType() {
		return progressType;
	}

	public void setProgressType(Long progressType) {
		this.progressType = progressType;
	}
	
	@Column(name = "TOTAL_MONEY_FORMULA", length = 200)
	public String getTotalMoneyFormula() {
		return totalMoneyFormula;
	}

	public void setTotalMoneyFormula(String totalMoneyFormula) {
		this.totalMoneyFormula = totalMoneyFormula;
	}

	@Override
	public EstimatesDetailAnalystDTO toDTO() {
		EstimatesDetailAnalystDTO estimatesDetailAnalystDTO = new EstimatesDetailAnalystDTO();
		// set cac gia tri
		estimatesDetailAnalystDTO.setProvidedGroup(this.providedGroup);
		estimatesDetailAnalystDTO.setEstDetailAnalystId(this.estDetailAnalystId);
		estimatesDetailAnalystDTO.setType(this.type);
		estimatesDetailAnalystDTO.setCostIngredientCode(this.costIngredientCode);
		estimatesDetailAnalystDTO.setCostIngredientName(this.costIngredientName);
		estimatesDetailAnalystDTO.setUnit(this.unit);
		estimatesDetailAnalystDTO.setNormIndex(this.normIndex);
		estimatesDetailAnalystDTO.setUnitPrice(this.unitPrice);
		estimatesDetailAnalystDTO.setCoefficient(this.coefficient);
		estimatesDetailAnalystDTO.setTotalMoney(this.totalMoney);
		
		//Dodt: Loi logic
		//estimatesDetailAnalystDTO.setEstimatesWorkItemsDTO(this.estimatesWorkItems == null ? null : estimatesWorkItems.toDTO());		
		estimatesDetailAnalystDTO.setEstimatesWorkItemId(this.estimatesWorkItems == null ? null : estimatesWorkItems.getEstimatesWorkItemId());
		estimatesDetailAnalystDTO.setEstimatesWorkItemName(this.estimatesWorkItems == null ? null : estimatesWorkItems.getWorkItemName());
		
		estimatesDetailAnalystDTO.setProgressType(this.progressType);
		estimatesDetailAnalystDTO.setTotalMoneyFormula(this.totalMoneyFormula);
		return estimatesDetailAnalystDTO;
	}
}
