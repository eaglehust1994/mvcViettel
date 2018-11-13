/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "estimatesworkitems")
@Table(name = "ESTIMATES_WORK_ITEMS")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class EstimatesWorkItemsBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3144608668631092215L;
	private List<ConstrAcceptWorkListBO> constracceptworklist = Lists.newArrayList();
	private ConstrEstimateInfoBO constrestimateinfo;
	private Long status;
	private Long estimatesWorkItemId;
	private Long constrEstimateInfoId;
	private Long estimatesItemChildId;
	private String workItemCode;
	private String workItemName;
	private String unit;
	private Double workAmount;
	private Double unitPrice;
	private Double totalMoney;
	private Long progressType;
	private Long unitPriceBid;
	private Long totalMoneyBid;
	private Long unitPriceBidAward;
	private Long totalMoneyBidAward;
	private Long unitPriceContract;
	private Long totalMoneyContract;
	private Long type;
	


	private Long workType;
	private Long sceneGenWorkListId;
	private List<WorkItemsAcceptanceBO> workItemsAcceptances = Lists.newArrayList();
	private List<EstimatesDetailAnalystBO> estimatesDetailAnalysts = Lists.newArrayList();

	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("workItemCode", workItemCode).append("workItemName", workItemName)
				.append("unit", unit).append("estimatesDetailAnalysts", estimatesDetailAnalysts).toString();
	}

	/**
	 * @return the workItemsAcceptances
	 */
	@ManyToMany
	@JoinTable(name = "WORK_ITEMS_ACCEPT_LIST", 
		joinColumns = @JoinColumn(name = "ESTIMATES_WORK_ITEM_ID"), 
		inverseJoinColumns = @JoinColumn(name = "WORK_ITEMS_ACCEPTANCE_ID"))
	public List<WorkItemsAcceptanceBO> getWorkItemsAcceptances() {
		return workItemsAcceptances;
	}

	/**
	 * @param workItemsAcceptances
	 *            the workItemsAcceptances to set
	 */
	public void setWorkItemsAcceptances(List<WorkItemsAcceptanceBO> workItemsAcceptances) {
		this.workItemsAcceptances = workItemsAcceptances;
	}

	public EstimatesWorkItemsBO() {
		setColId("estimatesWorkItemId");
		setColName("estimatesWorkItemId");
		setUniqueColumn(new String[] { "estimatesWorkItemId" });
	}
	
	public EstimatesWorkItemsBO(Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	@Column(name = "STATUS", length = 22)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "ESTIMATES_WORK_ITEMS_SEQ") })
	@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
	public Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}

	/*@Column(name = "CONSTR_ESTIMATE_INFO_ID", length = 22)
	public Long getConstrEstimateInfoId() {
		return constrEstimateInfoId;
	}

	public void setConstrEstimateInfoId(Long constrEstimateInfoId) {
		this.constrEstimateInfoId = constrEstimateInfoId;
	}*/

	
	@OneToMany(mappedBy = "estimatesworkitems")
	public List<ConstrAcceptWorkListBO> getConstracceptworklist() {
		return constracceptworklist;
	}

	public void setConstracceptworklist(List<ConstrAcceptWorkListBO> constracceptworklist) {
		this.constracceptworklist = constracceptworklist;
	}
	

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "CONSTR_ESTIMATE_INFO_ID")
	public ConstrEstimateInfoBO getConstrestimateinfo() {
		return constrestimateinfo;
	}

	public void setConstrestimateinfo(ConstrEstimateInfoBO constrestimateinfo) {
		this.constrestimateinfo = constrestimateinfo;
	}
	
	
	
	
	@Column(name = "ESTIMATES_ITEM_CHILD_ID", length = 22)
	public Long getEstimatesItemChildId() {
		return estimatesItemChildId;
	}

	public void setEstimatesItemChildId(Long estimatesItemChildId) {
		this.estimatesItemChildId = estimatesItemChildId;
	}

	@Column(name = "WORK_ITEM_CODE", length = 200)
	public String getWorkItemCode() {
		return workItemCode;
	}

	public void setWorkItemCode(String workItemCode) {
		this.workItemCode = workItemCode;
	}

	@Column(name = "WORK_TYPE")
	public Long getWorkType() {
		return workType;
	}

	public void setWorkType(Long workType) {
		this.workType = workType;
	}
	@Column(name = "SCENE_GEN_WORK_LIST_ID")
	public Long getSceneGenWorkListId() {
		return sceneGenWorkListId;
	}

	public void setSceneGenWorkListId(Long sceneGenWorkListId) {
		this.sceneGenWorkListId = sceneGenWorkListId;
	}
	
	@Column(name = "WORK_ITEM_NAME", length = 2000)
	public String getWorkItemName() {
		return workItemName;
	}

	public void setWorkItemName(String workItemName) {
		this.workItemName = workItemName;
	}

	@Column(name = "UNIT", length = 20)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "WORK_AMOUNT", length = 22)
	public Double getWorkAmount() {
		return workAmount;
	}

	public void setWorkAmount(Double workAmount) {
		this.workAmount = workAmount;
	}

	@Column(name = "UNIT_PRICE", length = 22)
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "TOTAL_MONEY", length = 22)
	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "PROGRESS_TYPE", length = 22)
	public Long getProgressType() {
		return progressType;
	}

	public void setProgressType(Long progressType) {
		this.progressType = progressType;
	}

	@Column(name = "UNIT_PRICE_BID", length = 22)
	public Long getUnitPriceBid() {
		return unitPriceBid;
	}

	public void setUnitPriceBid(Long unitPriceBid) {
		this.unitPriceBid = unitPriceBid;
	}

	@Column(name = "TOTAL_MONEY_BID", length = 22)
	public Long getTotalMoneyBid() {
		return totalMoneyBid;
	}

	public void setTotalMoneyBid(Long totalMoneyBid) {
		this.totalMoneyBid = totalMoneyBid;
	}

	@Column(name = "UNIT_PRICE_BID_AWARD", length = 22)
	public Long getUnitPriceBidAward() {
		return unitPriceBidAward;
	}

	public void setUnitPriceBidAward(Long unitPriceBidAward) {
		this.unitPriceBidAward = unitPriceBidAward;
	}

	@Column(name = "TOTAL_MONEY_BID_AWARD", length = 22)
	public Long getTotalMoneyBidAward() {
		return totalMoneyBidAward;
	}

	public void setTotalMoneyBidAward(Long totalMoneyBidAward) {
		this.totalMoneyBidAward = totalMoneyBidAward;
	}

	@Column(name = "UNIT_PRICE_CONTRACT", length = 22)
	public Long getUnitPriceContract() {
		return unitPriceContract;
	}

	public void setUnitPriceContract(Long unitPriceContract) {
		this.unitPriceContract = unitPriceContract;
	}

	@Column(name = "TOTAL_MONEY_CONTRACT", length = 22)
	public Long getTotalMoneyContract() {
		return totalMoneyContract;
	}

	public void setTotalMoneyContract(Long totalMoneyContract) {
		this.totalMoneyContract = totalMoneyContract;
	}

	@Column(name = "TYPE", length = 22)
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	/**
	 * @return the estimatesDetailAnalysts
	 */
	@OneToMany(mappedBy = "estimatesWorkItems", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("EST_DETAIL_ANALYST_ID")
	public List<EstimatesDetailAnalystBO> getEstimatesDetailAnalysts() {
		return estimatesDetailAnalysts;
	}

	/**
	 * @param estimatesDetailAnalysts the estimatesDetailAnalysts to set
	 */
	public void setEstimatesDetailAnalysts(List<EstimatesDetailAnalystBO> estimatesDetailAnalysts) {
		this.estimatesDetailAnalysts = estimatesDetailAnalysts;
	}

	@Override
	public EstimatesWorkItemsDTO toDTO() {
		EstimatesWorkItemsDTO estimatesWorkItemsDTO = new EstimatesWorkItemsDTO();
		// set cac gia tri
		estimatesWorkItemsDTO.setStatus(this.status);
		estimatesWorkItemsDTO.setEstimatesWorkItemId(this.estimatesWorkItemId);
		estimatesWorkItemsDTO.setConstrEstimateInfoId(this.constrEstimateInfoId);
		estimatesWorkItemsDTO.setEstimatesItemChildId(this.estimatesItemChildId);
		estimatesWorkItemsDTO.setWorkItemCode(this.workItemCode);
		estimatesWorkItemsDTO.setWorkItemName(this.workItemName);
		estimatesWorkItemsDTO.setUnit(this.unit);
		estimatesWorkItemsDTO.setWorkAmount(this.workAmount);
		estimatesWorkItemsDTO.setWorkType(this.workType);
		estimatesWorkItemsDTO.setSceneGenWorkListId(this.sceneGenWorkListId);
		
		
		estimatesWorkItemsDTO.setEvaluateQuantity(this.workAmount == null ? null : this.workAmount.doubleValue());
		
		estimatesWorkItemsDTO.setUnitPrice(this.unitPrice);
		estimatesWorkItemsDTO.setTotalMoney(this.totalMoney);
		estimatesWorkItemsDTO.setProgressType(this.progressType);
		estimatesWorkItemsDTO.setUnitPriceBid(this.unitPriceBid);
		estimatesWorkItemsDTO.setTotalMoneyBid(this.totalMoneyBid);
		estimatesWorkItemsDTO.setUnitPriceBidAward(this.unitPriceBidAward);
		estimatesWorkItemsDTO.setTotalMoneyBidAward(this.totalMoneyBidAward);
		estimatesWorkItemsDTO.setUnitPriceContract(this.unitPriceContract);
		estimatesWorkItemsDTO.setTotalMoneyContract(this.totalMoneyContract);
		estimatesWorkItemsDTO.setType(this.type == null?1:this.type);
		//estimatesWorkItemsDTO.setEstimatesDetailAnalysts(Lists.newArrayList(Iterables.transform(this.estimatesDetailAnalysts, arg0 -> arg0.toDTO())));
		
		return estimatesWorkItemsDTO;
	}
	
}
