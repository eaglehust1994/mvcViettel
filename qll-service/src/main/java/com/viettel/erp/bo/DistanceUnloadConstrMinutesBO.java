/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "distanceUnloadConstrMinutes")
@Table(name = "DISTANCE_UNLOAD_CONSTR_MINUTES")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE DISTANCE_UNLOAD_CONSTR_MINUTES c SET c.IS_ACTIVE = 0 WHERE c.DIS_UNLOAD_CONS_MIN_ID = ? ")
@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class DistanceUnloadConstrMinutesBO extends BaseFWModelImpl {

	private VConstructionHcqtBO vconstructionHcqt;
	private SceneGenerateWorkListBO scenegenerateworklist;
	private ConstrCompleteRecordsMapBO constrcompleterecordsmap;
	private EstimatesWorkItemsBO estimatesworkitems;
	private java.lang.Long constructId;
	@SuppressWarnings("unused")
	private java.lang.Long sceneGenWorkListId;
	private java.lang.Long isActive;
	private java.util.Date signDate;
	private java.lang.String signPlace;
	private java.lang.Long disUnloadConsMinId;
	private java.lang.String code;
	private java.lang.Long estimatesWorkItemId;
	private java.lang.Long aDirectorId;
	private java.lang.Long aInChargeMonitorId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bInChargeConstructId;
	private java.lang.String carGatherPlace;
	private java.lang.String carDesPlace;
	private java.lang.String wheelbarrowGatherPlace;
	private java.lang.String wheelbarrowDesPlace;
	private java.lang.String handmadeGatherPlace;
	private java.lang.String handmadeDesPlace;
	private java.lang.String conclusion;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long type;
	
	private List<DistanceUnloadListBO> distanceUnloadList = Lists.newArrayList();

	public DistanceUnloadConstrMinutesBO(Long disUnloadConsMinId) {
		this.disUnloadConsMinId = disUnloadConsMinId;
	}

	public DistanceUnloadConstrMinutesBO() {
		setColId("disUnloadConsMinId");
		setColName("disUnloadConsMinId");
		setUniqueColumn(new String[] { "disUnloadConsMinId" });
	}

	/*
	 * @Column(name = "CONSTRUCT_ID", length = 22) public java.lang.Long
	 * getConstructId(){ return constructId; } public void
	 * setConstructId(java.lang.Long constructId) { this.constructId =
	 * constructId; }
	 */

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CONSTRUCT_ID",referencedColumnName="CONSTRUCT_ID")
	public VConstructionHcqtBO getVconstructionHcqt() {
		return vconstructionHcqt;
	}

	public void setVconstructionHcqt(VConstructionHcqtBO vconstructionHcqt) {
		this.vconstructionHcqt = vconstructionHcqt;
	}
	/*
	 * @Column(name = "SCENE_GEN_WORK_LIST_ID", length = 22) public
	 * java.lang.Long getSceneGenWorkListId(){ return sceneGenWorkListId; }
	 * public void setSceneGenWorkListId(java.lang.Long sceneGenWorkListId) {
	 * this.sceneGenWorkListId = sceneGenWorkListId; }
	 */

	@ManyToOne
	@JoinColumn(name = "SCENE_GEN_WORK_LIST_ID")
	public SceneGenerateWorkListBO getScenegenerateworklist() {
		return scenegenerateworklist;
	}

	public void setScenegenerateworklist(SceneGenerateWorkListBO scenegenerateworklist) {
		this.scenegenerateworklist = scenegenerateworklist;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "SIGN_DATE", length = 7)
	public java.util.Date getSignDate() {
		return signDate;
	}

	public void setSignDate(java.util.Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "SIGN_PLACE", length = 2000)
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	@Column(name = "TYPE", length = 22)
	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "DISTANCE_UNLOAD_CONSTR_MN_SEQ") })
	@Column(name = "DIS_UNLOAD_CONS_MIN_ID", length = 22)
	public java.lang.Long getDisUnloadConsMinId() {
		return disUnloadConsMinId;
	}

	public void setDisUnloadConsMinId(java.lang.Long disUnloadConsMinId) {
		this.disUnloadConsMinId = disUnloadConsMinId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

/*	@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
	public java.lang.Long getEstimatesWorkItemId() {
		return estimatesWorkItemId;
	}

	public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId) {
		this.estimatesWorkItemId = estimatesWorkItemId;
	}*/
	@ManyToOne( optional = true)
	@JoinColumn(name = "ESTIMATES_WORK_ITEM_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public EstimatesWorkItemsBO getEstimatesworkitems() {
		return estimatesworkitems;
	}

	public void setEstimatesworkitems(EstimatesWorkItemsBO estimatesworkitems) {
		this.estimatesworkitems = estimatesworkitems;
	}
	
	

	@Column(name = "A_DIRECTOR_ID", length = 22)
	public java.lang.Long getADirectorId() {
		return aDirectorId;
	}

	public void setADirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	@Column(name = "A_IN_CHARGE_MONITOR_ID", length = 22)
	public java.lang.Long getAInChargeMonitorId() {
		return aInChargeMonitorId;
	}

	public void setAInChargeMonitorId(java.lang.Long aInChargeMonitorId) {
		this.aInChargeMonitorId = aInChargeMonitorId;
	}

	@Column(name = "B_DIRECTOR_ID", length = 22)
	public java.lang.Long getBDirectorId() {
		return bDirectorId;
	}

	public void setBDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}

	@Column(name = "B_IN_CHARGE_CONSTRUCT_ID", length = 22)
	public java.lang.Long getBInChargeConstructId() {
		return bInChargeConstructId;
	}

	public void setBInChargeConstructId(java.lang.Long bInChargeConstructId) {
		this.bInChargeConstructId = bInChargeConstructId;
	}

	@Column(name = "CAR_GATHER_PLACE", length = 1000)
	public java.lang.String getCarGatherPlace() {
		return carGatherPlace;
	}

	public void setCarGatherPlace(java.lang.String carGatherPlace) {
		this.carGatherPlace = carGatherPlace;
	}

	@Column(name = "CAR_DES_PLACE", length = 2000)
	public java.lang.String getCarDesPlace() {
		return carDesPlace;
	}

	public void setCarDesPlace(java.lang.String carDesPlace) {
		this.carDesPlace = carDesPlace;
	}

	@Column(name = "WHEELBARROW_GATHER_PLACE", length = 1000)
	public java.lang.String getWheelbarrowGatherPlace() {
		return wheelbarrowGatherPlace;
	}

	public void setWheelbarrowGatherPlace(java.lang.String wheelbarrowGatherPlace) {
		this.wheelbarrowGatherPlace = wheelbarrowGatherPlace;
	}

	@Column(name = "WHEELBARROW_DES_PLACE", length = 2000)
	public java.lang.String getWheelbarrowDesPlace() {
		return wheelbarrowDesPlace;
	}

	public void setWheelbarrowDesPlace(java.lang.String wheelbarrowDesPlace) {
		this.wheelbarrowDesPlace = wheelbarrowDesPlace;
	}

	@Column(name = "HANDMADE_GATHER_PLACE", length = 2000)
	public java.lang.String getHandmadeGatherPlace() {
		return handmadeGatherPlace;
	}

	public void setHandmadeGatherPlace(java.lang.String handmadeGatherPlace) {
		this.handmadeGatherPlace = handmadeGatherPlace;
	}

	@Column(name = "HANDMADE_DES_PLACE", length = 2000)
	public java.lang.String getHandmadeDesPlace() {
		return handmadeDesPlace;
	}

	public void setHandmadeDesPlace(java.lang.String handmadeDesPlace) {
		this.handmadeDesPlace = handmadeDesPlace;
	}

	@Column(name = "CONCLUSION", length = 1000)
	public java.lang.String getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.String conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_USER_ID", length = 22)
	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Column(name = "APPROVAL_DATE", length = 7)
	public java.util.Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(java.util.Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "STATUS_CA", length = 22)
	public java.lang.Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(java.lang.Long statusCa) {
		this.statusCa = statusCa;
	}

	/*
	 * @OneToOne(mappedBy = "distanceunloadconstrminutes", fetch =
	 * FetchType.EAGER, cascade = CascadeType.ALL ) public
	 * ConstrCompleteRecordsMapBO getConstrcompleterecordsmap() { return
	 * constrcompleterecordsmap; }
	 * 
	 * public void setConstrcompleterecordsmap(ConstrCompleteRecordsMapBO
	 * constrcompleterecordsmap) { this.constrcompleterecordsmap =
	 * constrcompleterecordsmap; }
	 */
	@OneToMany(mappedBy = "distanceUnloadConstrMinutesBO", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<DistanceUnloadListBO> getDistanceUnloadList() {
		return distanceUnloadList;
	}

	public void setDistanceUnloadList(List<DistanceUnloadListBO> distanceUnloadList) {
		this.distanceUnloadList = distanceUnloadList;
	}

	@Override
	public DistanceUnloadConstrMinutesDTO toDTO() {
		DistanceUnloadConstrMinutesDTO distanceUnloadConstrMinutesDTO = new DistanceUnloadConstrMinutesDTO();
		// set cac gia tri
		distanceUnloadConstrMinutesDTO.setConstructId(this.constructId);
		// distanceUnloadConstrMinutesDTO.setSceneGenWorkListId(this.sceneGenWorkListId);

		distanceUnloadConstrMinutesDTO.setSceneGenWorkListId(
				this.scenegenerateworklist == null ? null : this.scenegenerateworklist.getSceneGenWorkListId());
		distanceUnloadConstrMinutesDTO.setIncurredContent(
				this.scenegenerateworklist == null ? null : this.scenegenerateworklist.getIncurredContent());
		distanceUnloadConstrMinutesDTO.setIsActive(this.isActive);
		distanceUnloadConstrMinutesDTO.setSignDate(this.signDate);
		distanceUnloadConstrMinutesDTO.setSignPlace(this.signPlace);
		distanceUnloadConstrMinutesDTO.setDisUnloadConsMinId(this.disUnloadConsMinId);
		distanceUnloadConstrMinutesDTO.setCode(this.code);
		distanceUnloadConstrMinutesDTO.setEstimatesWorkItemId(estimatesworkitems == null?null:estimatesworkitems.getEstimatesWorkItemId());
		distanceUnloadConstrMinutesDTO.setEstimatesWorkItemName(estimatesworkitems == null?null:estimatesworkitems.getWorkItemName());
		distanceUnloadConstrMinutesDTO.setADirectorId(this.aDirectorId);
		distanceUnloadConstrMinutesDTO.setAInChargeMonitorId(this.aInChargeMonitorId);
		distanceUnloadConstrMinutesDTO.setBDirectorId(this.bDirectorId);
		distanceUnloadConstrMinutesDTO.setBInChargeConstructId(this.bInChargeConstructId);
		distanceUnloadConstrMinutesDTO.setCarGatherPlace(this.carGatherPlace);
		distanceUnloadConstrMinutesDTO.setCarDesPlace(this.carDesPlace);
		distanceUnloadConstrMinutesDTO.setWheelbarrowGatherPlace(this.wheelbarrowGatherPlace);
		distanceUnloadConstrMinutesDTO.setWheelbarrowDesPlace(this.wheelbarrowDesPlace);
		distanceUnloadConstrMinutesDTO.setHandmadeGatherPlace(this.handmadeGatherPlace);
		distanceUnloadConstrMinutesDTO.setHandmadeDesPlace(this.handmadeDesPlace);
		distanceUnloadConstrMinutesDTO.setConclusion(this.conclusion);
		distanceUnloadConstrMinutesDTO.setCreatedDate(this.createdDate);
		distanceUnloadConstrMinutesDTO.setCreatedUserId(this.createdUserId);
		distanceUnloadConstrMinutesDTO.setApprovalDate(this.approvalDate);
		distanceUnloadConstrMinutesDTO.setStatusCa(this.statusCa);
		distanceUnloadConstrMinutesDTO.setType(this.type);
		// distanceUnloadConstrMinutesDTO.setConstrtName(vconstructionHcqt ==
		// null ? null : vconstructionHcqt.getConstrtName());
		distanceUnloadConstrMinutesDTO
				.setConstrtCode(vconstructionHcqt == null ? null : vconstructionHcqt.getConstrtCode());
		distanceUnloadConstrMinutesDTO
				.setContractCode(vconstructionHcqt == null ? null : vconstructionHcqt.getContractCode());
		distanceUnloadConstrMinutesDTO
				.setContractName(vconstructionHcqt == null ? null : vconstructionHcqt.getContractName());
		// distanceUnloadConstrMinutesDTO.setConstrtAddress(vconstructionHcqt ==
		// null ? null : vconstructionHcqt.getConstrtAddress());
		distanceUnloadConstrMinutesDTO
				.setConstructId(vconstructionHcqt == null ? null : vconstructionHcqt.getConstructId());

		distanceUnloadConstrMinutesDTO.setDistanceUnloadList(
				Lists.newArrayList(Iterables.transform(this.distanceUnloadList, arg0 -> arg0.toDTO())));

		distanceUnloadConstrMinutesDTO.setConstrcompleterecordsmap(
				constrcompleterecordsmap == null ? null : constrcompleterecordsmap.toDTO());

		return distanceUnloadConstrMinutesDTO;
	}



}