/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "constracceptworklist")
@Table(name = "CONSTR_ACCEPT_WORK_LIST")
@DynamicInsert
@DynamicUpdate
// @SQLDelete(
// sql = "UPDATE CONSTR_ACCEPT_WORK_LIST c SET c.IS_ACTIVE = 1 WHERE
// c.CONSTR_ACCEPT_WORK_LIST_ID = ? ")
// @Where( clause = "IS_ACTIVE = '1'" )

// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region="erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrAcceptWorkListBO extends BaseFWModelImpl {

	private EstimatesWorkItemsBO estimatesworkitems;
	private ConstructionAcceptanceBO constructionacceptance;
	private java.lang.Long constrAcceptWorkListId;
	private java.lang.Long estimatesWorkItemId;
	private java.lang.String explain;
	private java.lang.Double executeQuantity;
	private java.lang.Double evaluateQuantity;
	private java.lang.String evaluateComments;
	private java.lang.String instanceDrawCode;
	private java.lang.String comments;
	// private java.lang.Long constructionAcceptanceId;
	private java.lang.Double settleUnitPrice;
	private java.lang.Double evaluateUnitPrice;

	public ConstrAcceptWorkListBO() {
		setColId("constrAcceptWorkListId");
		setColName("constrAcceptWorkListId");
		setUniqueColumn(new String[] { "constrAcceptWorkListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTR_ACCEPT_WORK_LIST_SEQ") })

	@Column(name = "CONSTR_ACCEPT_WORK_LIST_ID", length = 22)
	public java.lang.Long getConstrAcceptWorkListId() {
		return constrAcceptWorkListId;
	}

	public void setConstrAcceptWorkListId(java.lang.Long constrAcceptWorkListId) {
		this.constrAcceptWorkListId = constrAcceptWorkListId;
	}

	@Column(name = "EXPLAIN", length = 2000)
	public java.lang.String getExplain() {
		return explain;
	}

	public void setExplain(java.lang.String explain) {
		this.explain = explain;
	}

	@Column(name = "EXECUTE_QUANTITY", length = 22)
	public java.lang.Double getExecuteQuantity() {
		return executeQuantity;
	}

	public void setExecuteQuantity(java.lang.Double executeQuantity) {
		this.executeQuantity = executeQuantity;
	}

	@Column(name = "EVALUATE_QUANTITY", length = 22)
	public java.lang.Double getEvaluateQuantity() {
		return evaluateQuantity;
	}

	public void setEvaluateQuantity(java.lang.Double evaluateQuantity) {
		this.evaluateQuantity = evaluateQuantity;
	}

	@Column(name = "EVALUATE_COMMENTS", length = 400)
	public java.lang.String getEvaluateComments() {
		return evaluateComments;
	}

	public void setEvaluateComments(java.lang.String evaluateComments) {
		this.evaluateComments = evaluateComments;
	}

	@Column(name = "INSTANCE_DRAW_CODE", length = 400)
	public java.lang.String getInstanceDrawCode() {
		return instanceDrawCode;
	}

	public void setInstanceDrawCode(java.lang.String instanceDrawCode) {
		this.instanceDrawCode = instanceDrawCode;
	}

	@Column(name = "COMMENTS", length = 2000)
	public java.lang.String getComments() {
		return comments;
	}

	public void setComments(java.lang.String comments) {
		this.comments = comments;
	}

	/*
	 * @Column(name = "CONSTRUCTION_ACCEPTANCE_ID", length = 22) public
	 * java.lang.Long getConstructionAcceptanceId() { return
	 * constructionAcceptanceId; }
	 * 
	 * public void setConstructionAcceptanceId(java.lang.Long
	 * constructionAcceptanceId) { this.constructionAcceptanceId =
	 * constructionAcceptanceId; }
	 */

/*	@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}*/


	@ManyToOne
	@JoinColumn(name = "CONSTRUCTION_ACCEPTANCE_ID")
	public ConstructionAcceptanceBO getConstructionacceptance() {
		return constructionacceptance;
	}

	public void setConstructionacceptance(ConstructionAcceptanceBO constructionacceptance) {
		this.constructionacceptance = constructionacceptance;
	}

	@ManyToOne( optional = true)
	@JoinColumn(name = "ESTIMATES_WORK_ITEM_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public EstimatesWorkItemsBO getEstimatesworkitems() {
		return estimatesworkitems;
	}

	public void setEstimatesworkitems(EstimatesWorkItemsBO estimatesworkitems) {
		this.estimatesworkitems = estimatesworkitems;
	}

	@Column(name = "SETTLE_UNIT_PRICE", length = 22)
	public java.lang.Double getSettleUnitPrice() {
		return settleUnitPrice;
	}

	public void setSettleUnitPrice(java.lang.Double settleUnitPrice) {
		this.settleUnitPrice = settleUnitPrice;
	}

	@Column(name = "EVALUATE_UNIT_PRICE", length = 22)
	public java.lang.Double getEvaluateUnitPrice() {
		return evaluateUnitPrice;
	}

	public void setEvaluateUnitPrice(java.lang.Double evaluateUnitPrice) {
		this.evaluateUnitPrice = evaluateUnitPrice;
	}

	@Override
	public ConstrAcceptWorkListDTO toDTO() {
		ConstrAcceptWorkListDTO constrAcceptWorkListDTO = new ConstrAcceptWorkListDTO();
		// set cac gia tri
		
		constrAcceptWorkListDTO.setWorkItemCode(estimatesworkitems.getWorkItemCode() == null ?"": estimatesworkitems.getWorkItemCode());
		constrAcceptWorkListDTO.setWorkAmount(estimatesworkitems.getWorkAmount() == null ?0D: estimatesworkitems.getWorkAmount());
		constrAcceptWorkListDTO.setWorkItemName(estimatesworkitems.getWorkItemName() == null ?"": estimatesworkitems.getWorkItemName());
		constrAcceptWorkListDTO.setEstimatesWorkItemId(estimatesworkitems.getEstimatesWorkItemId() == null ?0L: estimatesworkitems.getEstimatesWorkItemId());
		constrAcceptWorkListDTO.setUnit(estimatesworkitems.getUnit() == null ?"": estimatesworkitems.getUnit());
		constrAcceptWorkListDTO.setType(estimatesworkitems == null ?null: estimatesworkitems.getType() ==null ?1:estimatesworkitems.getType());
		
		constrAcceptWorkListDTO.setConstrAcceptWorkListId(this.constrAcceptWorkListId);
		constrAcceptWorkListDTO.setExplain(this.explain);
		constrAcceptWorkListDTO.setExecuteQuantity(this.executeQuantity == null ? 0D :this.executeQuantity);
		constrAcceptWorkListDTO.setEvaluateQuantity(this.evaluateQuantity == null ? 0D : this.evaluateQuantity);
		constrAcceptWorkListDTO.setEvaluateComments(this.evaluateComments == null ? "":this.evaluateComments);
		constrAcceptWorkListDTO.setInstanceDrawCode(this.instanceDrawCode ==null ?" ":this.instanceDrawCode);
		constrAcceptWorkListDTO.setComments(this.comments == null ? "" :this.comments );
		constrAcceptWorkListDTO.setConstructionAcceptanceId(this.constructionacceptance.getConstructionAcceptanceId());
		constrAcceptWorkListDTO.setSettleUnitPrice(this.settleUnitPrice == null ? 0D:this.settleUnitPrice);
		constrAcceptWorkListDTO.setEvaluateUnitPrice(this.evaluateUnitPrice == null ? 0D:this.evaluateUnitPrice);
		constrAcceptWorkListDTO.setAcceptWorkcomments(this.comments == null ? "" :this.comments );
		
		return constrAcceptWorkListDTO;
	}

}
