/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "A_MATERIAL_RECOVERY_MINUTES")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE A_MATERIAL_RECOVERY_MINUTES q SET q.IS_ACTIVE = 0 WHERE q.A_MATERIAL_RECOVERY_MINUTES_ID = ? ")
@Where(clause = "IS_ACTIVE = '1'")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class AMaterialRecoveryMinutesBO extends BaseFWModelImpl {

	private java.lang.String signPlace;
	private java.lang.Long constructId;
	private java.lang.Long aMaterialRecoveryMinutesId;
	private java.lang.String code;
	private java.lang.Long aDirectorId;
	private java.lang.Long aHandoverPersonId;
	private java.lang.Long bDirectorId;
	private java.lang.Long bReceivePersonId;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long isActive;

	private List<AMaterialRecoveryListBO> amaterialRecoveryList = Lists.newArrayList();
	
	private AMaterialRecoveryMinutesModelBO recoveryMinutesModelBO;

	public AMaterialRecoveryMinutesBO() {
		setColId("aMaterialRecoveryMinutesId");
		setColName("aMaterialRecoveryMinutesId");
		setUniqueColumn(new String[] { "aMaterialRecoveryMinutesId" });
	}
	
	@OneToOne(mappedBy = "recoveryMinutesBO",fetch= FetchType.EAGER,cascade=CascadeType.ALL )
	public AMaterialRecoveryMinutesModelBO getRecoveryMinutesModelBO() {
		return recoveryMinutesModelBO;
	}

	public void setRecoveryMinutesModelBO(AMaterialRecoveryMinutesModelBO recoveryMinutesModelBO) {
		this.recoveryMinutesModelBO = recoveryMinutesModelBO;
	}
	
	public AMaterialRecoveryMinutesBO(Long aMaterialRecoveryMinutesId2) {
		this.aMaterialRecoveryMinutesId =  aMaterialRecoveryMinutesId2;
	}
	
	@OneToMany(mappedBy = "amaterialRecoveryMinutesBO", cascade = CascadeType.ALL)
	public List<AMaterialRecoveryListBO> getAmaterialRecoveryList() {
		return amaterialRecoveryList;
	}

	public void setAmaterialRecoveryList(List<AMaterialRecoveryListBO> amaterialRecoveryList) {
		this.amaterialRecoveryList = amaterialRecoveryList;
	}

	@Column(name = "SIGN_PLACE", length = 2000)
	public java.lang.String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(java.lang.String signPlace) {
		this.signPlace = signPlace;
	}

	@Column(name = "CONSTRUCT_ID", length = 22)
	public java.lang.Long getConstructId() {
		return constructId;
	}

	public void setConstructId(java.lang.Long constructId) {
		this.constructId = constructId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "A_MATERIAL_RECOVERY_MN_SEQ") })
	@Column(name = "A_MATERIAL_RECOVERY_MINUTES_ID", length = 22)
	public java.lang.Long getAMaterialRecoveryMinutesId() {
		return aMaterialRecoveryMinutesId;
	}

	public void setAMaterialRecoveryMinutesId(java.lang.Long aMaterialRecoveryMinutesId) {
		this.aMaterialRecoveryMinutesId = aMaterialRecoveryMinutesId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@Column(name = "A_DIRECTOR_ID", length = 22)
	public java.lang.Long getADirectorId() {
		return aDirectorId;
	}

	public void setADirectorId(java.lang.Long aDirectorId) {
		this.aDirectorId = aDirectorId;
	}

	@Column(name = "A_HANDOVER_PERSON_ID", length = 22)
	public java.lang.Long getAHandoverPersonId() {
		return aHandoverPersonId;
	}

	public void setAHandoverPersonId(java.lang.Long aHandoverPersonId) {
		this.aHandoverPersonId = aHandoverPersonId;
	}

	@Column(name = "B_DIRECTOR_ID", length = 22)
	public java.lang.Long getBDirectorId() {
		return bDirectorId;
	}

	public void setBDirectorId(java.lang.Long bDirectorId) {
		this.bDirectorId = bDirectorId;
	}

	@Column(name = "B_RECEIVE_PERSON_ID", length = 22)
	public java.lang.Long getBReceivePersonId() {
		return bReceivePersonId;
	}

	public void setBReceivePersonId(java.lang.Long bReceivePersonId) {
		this.bReceivePersonId = bReceivePersonId;
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


	@Column(name = "IS_ACTIVE", length = 22)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	@Override
	public AMaterialRecoveryMinutesDTO toDTO() {
		AMaterialRecoveryMinutesDTO aMaterialRecoveryMinutesDTO = new AMaterialRecoveryMinutesDTO();
		// set cac gia tri
		aMaterialRecoveryMinutesDTO.setSignPlace(this.signPlace);
		aMaterialRecoveryMinutesDTO.setConstructId(this.constructId);
		aMaterialRecoveryMinutesDTO.setamaterialRecoveryMinutesId(this.aMaterialRecoveryMinutesId);
		aMaterialRecoveryMinutesDTO.setCode(this.code);
		aMaterialRecoveryMinutesDTO.setadirectorId(this.aDirectorId);
		aMaterialRecoveryMinutesDTO.setahandoverPersonId(this.aHandoverPersonId);
		aMaterialRecoveryMinutesDTO.setbdirectorId(this.bDirectorId);
		aMaterialRecoveryMinutesDTO.setbreceivePersonId(this.bReceivePersonId);
		aMaterialRecoveryMinutesDTO.setCreatedDate(this.createdDate);
		aMaterialRecoveryMinutesDTO.setCreatedUserId(this.createdUserId);
		aMaterialRecoveryMinutesDTO.setApprovalDate(this.approvalDate);
		aMaterialRecoveryMinutesDTO.setStatusCa(this.statusCa);
		aMaterialRecoveryMinutesDTO.setIsActive(this.isActive);
		return aMaterialRecoveryMinutesDTO;
	}
}
