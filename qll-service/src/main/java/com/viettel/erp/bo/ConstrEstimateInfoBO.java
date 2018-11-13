/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;

@Entity(name = "constrestimateinfo")
@Table(name = "CONSTR_ESTIMATE_INFO")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrEstimateInfoBO extends BaseFWModelImpl {

	//private List<EstimatesWorkItemsBO> estimatesworkitems = Lists.newArrayList();
	private ConstrConstructionsBO constrconstructions;
	private java.lang.Long constrEstimateInfoId;
	private java.lang.Long constructionId;
	private java.util.Date finishDesignDate;
	private java.lang.Long currencyId;
	private java.lang.Long estimateBeforeTax;
	private java.lang.Long tax;
	private java.lang.Long estimateAfterTax;
	private java.lang.Long evaluationGroupId;
	private java.lang.Long evaluationEmployeeId;
	private java.util.Date evaluationDate;
	private java.lang.Long groundReleaseCostBt;
	private java.lang.Long groundReleaseCostTax;
	private java.lang.Long groundReleaseCostAt;
	private java.lang.Long constructionCostBt;
	private java.lang.Long constructionCostTax;
	private java.lang.Long constructionCostAt;
	private java.lang.Long deviceCostBt;
	private java.lang.Long deviceCostTax;
	private java.lang.Long deviceCostAt;
	private java.lang.Long consultCostBt;
	private java.lang.Long consultCostTax;
	private java.lang.Long consultCostAt;
	private java.lang.Long otherCostBt;
	private java.lang.Long otherCostTax;
	private java.lang.Long otherCostAt;
	private java.lang.Long preventiveCostBt;
	private java.lang.Long preventiveCostTax;
	private java.lang.Long preventiveCostAt;
	private java.lang.Long isDivideItems;
	private java.util.Date createdDate;
	private java.util.Date updatedDate;
	private java.lang.String createdUserId;

	public ConstrEstimateInfoBO() {
		setColId("constructionId");
		setColName("constructionId");
		setUniqueColumn(new String[] { "constructionId" });
	}

	public ConstrEstimateInfoBO(Long constrEstimateInfoId) {
		this.constrEstimateInfoId = constrEstimateInfoId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTR_ESTIMATE_INFO_SEQ") })
	@Column(name = "CONSTR_ESTIMATE_INFO_ID", length = 22)
	public java.lang.Long getConstrEstimateInfoId() {
		return constrEstimateInfoId;
	}

	public void setConstrEstimateInfoId(java.lang.Long constrEstimateInfoId) {
		this.constrEstimateInfoId = constrEstimateInfoId;
	}

/*	@Column(name = "CONSTRUCTION_ID", length = 22)
	public java.lang.Long getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(java.lang.Long constructionId) {
		this.constructionId = constructionId;
	}*/
	
	@ManyToOne
	@JoinColumn(name = "CONSTRUCTION_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public ConstrConstructionsBO getconstrconstructions(){
		return constrconstructions;
	}
	public void setconstrconstructions(ConstrConstructionsBO constrconstructions){
		this.constrconstructions = constrconstructions;
	} 

/*	@OneToMany(mappedBy = "constrestimateinfo")
	public List<EstimatesWorkItemsBO> getEstimatesworkitems() {
		return estimatesworkitems;
	}

	public void setEstimatesworkitems(List<EstimatesWorkItemsBO> estimatesworkitems) {
		this.estimatesworkitems = estimatesworkitems;
	}*/

	@Column(name = "FINISH_DESIGN_DATE", length = 7)
	public java.util.Date getFinishDesignDate() {
		return finishDesignDate;
	}

	public void setFinishDesignDate(java.util.Date finishDesignDate) {
		this.finishDesignDate = finishDesignDate;
	}

	@Column(name = "CURRENCY_ID", length = 22)
	public java.lang.Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(java.lang.Long currencyId) {
		this.currencyId = currencyId;
	}

	@Column(name = "ESTIMATE_BEFORE_TAX", length = 22)
	public java.lang.Long getEstimateBeforeTax() {
		return estimateBeforeTax;
	}

	public void setEstimateBeforeTax(java.lang.Long estimateBeforeTax) {
		this.estimateBeforeTax = estimateBeforeTax;
	}

	@Column(name = "TAX", length = 22)
	public java.lang.Long getTax() {
		return tax;
	}

	public void setTax(java.lang.Long tax) {
		this.tax = tax;
	}

	@Column(name = "ESTIMATE_AFTER_TAX", length = 22)
	public java.lang.Long getEstimateAfterTax() {
		return estimateAfterTax;
	}

	public void setEstimateAfterTax(java.lang.Long estimateAfterTax) {
		this.estimateAfterTax = estimateAfterTax;
	}

	@Column(name = "EVALUATION_GROUP_ID", length = 22)
	public java.lang.Long getEvaluationGroupId() {
		return evaluationGroupId;
	}

	public void setEvaluationGroupId(java.lang.Long evaluationGroupId) {
		this.evaluationGroupId = evaluationGroupId;
	}

	@Column(name = "EVALUATION_EMPLOYEE_ID", length = 22)
	public java.lang.Long getEvaluationEmployeeId() {
		return evaluationEmployeeId;
	}

	public void setEvaluationEmployeeId(java.lang.Long evaluationEmployeeId) {
		this.evaluationEmployeeId = evaluationEmployeeId;
	}

	@Column(name = "EVALUATION_DATE", length = 7)
	public java.util.Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(java.util.Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	@Column(name = "GROUND_RELEASE_COST_BT", length = 22)
	public java.lang.Long getGroundReleaseCostBt() {
		return groundReleaseCostBt;
	}

	public void setGroundReleaseCostBt(java.lang.Long groundReleaseCostBt) {
		this.groundReleaseCostBt = groundReleaseCostBt;
	}

	@Column(name = "GROUND_RELEASE_COST_TAX", length = 22)
	public java.lang.Long getGroundReleaseCostTax() {
		return groundReleaseCostTax;
	}

	public void setGroundReleaseCostTax(java.lang.Long groundReleaseCostTax) {
		this.groundReleaseCostTax = groundReleaseCostTax;
	}

	@Column(name = "GROUND_RELEASE_COST_AT", length = 22)
	public java.lang.Long getGroundReleaseCostAt() {
		return groundReleaseCostAt;
	}

	public void setGroundReleaseCostAt(java.lang.Long groundReleaseCostAt) {
		this.groundReleaseCostAt = groundReleaseCostAt;
	}

	@Column(name = "CONSTRUCTION_COST_BT", length = 22)
	public java.lang.Long getConstructionCostBt() {
		return constructionCostBt;
	}

	public void setConstructionCostBt(java.lang.Long constructionCostBt) {
		this.constructionCostBt = constructionCostBt;
	}

	@Column(name = "CONSTRUCTION_COST_TAX", length = 22)
	public java.lang.Long getConstructionCostTax() {
		return constructionCostTax;
	}

	public void setConstructionCostTax(java.lang.Long constructionCostTax) {
		this.constructionCostTax = constructionCostTax;
	}

	@Column(name = "CONSTRUCTION_COST_AT", length = 22)
	public java.lang.Long getConstructionCostAt() {
		return constructionCostAt;
	}

	public void setConstructionCostAt(java.lang.Long constructionCostAt) {
		this.constructionCostAt = constructionCostAt;
	}

	@Column(name = "DEVICE_COST_BT", length = 22)
	public java.lang.Long getDeviceCostBt() {
		return deviceCostBt;
	}

	public void setDeviceCostBt(java.lang.Long deviceCostBt) {
		this.deviceCostBt = deviceCostBt;
	}

	@Column(name = "DEVICE_COST_TAX", length = 22)
	public java.lang.Long getDeviceCostTax() {
		return deviceCostTax;
	}

	public void setDeviceCostTax(java.lang.Long deviceCostTax) {
		this.deviceCostTax = deviceCostTax;
	}

	@Column(name = "DEVICE_COST_AT", length = 22)
	public java.lang.Long getDeviceCostAt() {
		return deviceCostAt;
	}

	public void setDeviceCostAt(java.lang.Long deviceCostAt) {
		this.deviceCostAt = deviceCostAt;
	}

	@Column(name = "CONSULT_COST_BT", length = 22)
	public java.lang.Long getConsultCostBt() {
		return consultCostBt;
	}

	public void setConsultCostBt(java.lang.Long consultCostBt) {
		this.consultCostBt = consultCostBt;
	}

	@Column(name = "CONSULT_COST_TAX", length = 22)
	public java.lang.Long getConsultCostTax() {
		return consultCostTax;
	}

	public void setConsultCostTax(java.lang.Long consultCostTax) {
		this.consultCostTax = consultCostTax;
	}

	@Column(name = "CONSULT_COST_AT", length = 22)
	public java.lang.Long getConsultCostAt() {
		return consultCostAt;
	}

	public void setConsultCostAt(java.lang.Long consultCostAt) {
		this.consultCostAt = consultCostAt;
	}

	@Column(name = "OTHER_COST_BT", length = 22)
	public java.lang.Long getOtherCostBt() {
		return otherCostBt;
	}

	public void setOtherCostBt(java.lang.Long otherCostBt) {
		this.otherCostBt = otherCostBt;
	}

	@Column(name = "OTHER_COST_TAX", length = 22)
	public java.lang.Long getOtherCostTax() {
		return otherCostTax;
	}

	public void setOtherCostTax(java.lang.Long otherCostTax) {
		this.otherCostTax = otherCostTax;
	}

	@Column(name = "OTHER_COST_AT", length = 22)
	public java.lang.Long getOtherCostAt() {
		return otherCostAt;
	}

	public void setOtherCostAt(java.lang.Long otherCostAt) {
		this.otherCostAt = otherCostAt;
	}

	@Column(name = "PREVENTIVE_COST_BT", length = 22)
	public java.lang.Long getPreventiveCostBt() {
		return preventiveCostBt;
	}

	public void setPreventiveCostBt(java.lang.Long preventiveCostBt) {
		this.preventiveCostBt = preventiveCostBt;
	}

	@Column(name = "PREVENTIVE_COST_TAX", length = 22)
	public java.lang.Long getPreventiveCostTax() {
		return preventiveCostTax;
	}

	public void setPreventiveCostTax(java.lang.Long preventiveCostTax) {
		this.preventiveCostTax = preventiveCostTax;
	}

	@Column(name = "PREVENTIVE_COST_AT", length = 22)
	public java.lang.Long getPreventiveCostAt() {
		return preventiveCostAt;
	}

	public void setPreventiveCostAt(java.lang.Long preventiveCostAt) {
		this.preventiveCostAt = preventiveCostAt;
	}

	@Column(name = "IS_DIVIDE_ITEMS", length = 22)
	public java.lang.Long getIsDivideItems() {
		return isDivideItems;
	}

	public void setIsDivideItems(java.lang.Long isDivideItems) {
		this.isDivideItems = isDivideItems;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "UPDATED_DATE", length = 7)
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "CREATED_USER_ID", length = 10)
	public java.lang.String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.String createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Override
	public ConstrEstimateInfoDTO toDTO() {
		ConstrEstimateInfoDTO constrEstimateInfoDTO = new ConstrEstimateInfoDTO();
		// set cac gia tri
		constrEstimateInfoDTO.setConstrEstimateInfoId(this.constrEstimateInfoId);
		constrEstimateInfoDTO.setConstructionId(this.constrconstructions == null ? null : this.constrconstructions.getConstructId());
		constrEstimateInfoDTO.setFinishDesignDate(this.finishDesignDate);
		constrEstimateInfoDTO.setCurrencyId(this.currencyId);
		constrEstimateInfoDTO.setEstimateBeforeTax(this.estimateBeforeTax);
		constrEstimateInfoDTO.setTax(this.tax);
		constrEstimateInfoDTO.setEstimateAfterTax(this.estimateAfterTax);
		constrEstimateInfoDTO.setEvaluationGroupId(this.evaluationGroupId);
		constrEstimateInfoDTO.setEvaluationEmployeeId(this.evaluationEmployeeId);
		constrEstimateInfoDTO.setEvaluationDate(this.evaluationDate);
		constrEstimateInfoDTO.setGroundReleaseCostBt(this.groundReleaseCostBt);
		constrEstimateInfoDTO.setGroundReleaseCostTax(this.groundReleaseCostTax);
		constrEstimateInfoDTO.setGroundReleaseCostAt(this.groundReleaseCostAt);
		constrEstimateInfoDTO.setConstructionCostBt(this.constructionCostBt);
		constrEstimateInfoDTO.setConstructionCostTax(this.constructionCostTax);
		constrEstimateInfoDTO.setConstructionCostAt(this.constructionCostAt);
		constrEstimateInfoDTO.setDeviceCostBt(this.deviceCostBt);
		constrEstimateInfoDTO.setDeviceCostTax(this.deviceCostTax);
		constrEstimateInfoDTO.setDeviceCostAt(this.deviceCostAt);
		constrEstimateInfoDTO.setConsultCostBt(this.consultCostBt);
		constrEstimateInfoDTO.setConsultCostTax(this.consultCostTax);
		constrEstimateInfoDTO.setConsultCostAt(this.consultCostAt);
		constrEstimateInfoDTO.setOtherCostBt(this.otherCostBt);
		constrEstimateInfoDTO.setOtherCostTax(this.otherCostTax);
		constrEstimateInfoDTO.setOtherCostAt(this.otherCostAt);
		constrEstimateInfoDTO.setPreventiveCostBt(this.preventiveCostBt);
		constrEstimateInfoDTO.setPreventiveCostTax(this.preventiveCostTax);
		constrEstimateInfoDTO.setPreventiveCostAt(this.preventiveCostAt);
		constrEstimateInfoDTO.setIsDivideItems(this.isDivideItems);
		constrEstimateInfoDTO.setCreatedDate(this.createdDate);
		constrEstimateInfoDTO.setUpdatedDate(this.updatedDate);
		constrEstimateInfoDTO.setCreatedUserId(this.createdUserId);
		return constrEstimateInfoDTO;
	}
}
