/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "CONSTR_ACCEPTANCE_REQUEST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrAcceptanceRequestBO extends BaseFWModelImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261470026108482197L;
	private Long constructId;
	private Long constAcceptanceRequestId;
	private String code;
	private Long sendPersonId;
	private Long receivePersonId;
	private java.util.Date acceptanceDate;
	private String acceptancePlace;
	private java.util.Date createdDate;
	private Long createdUserId;
	private java.util.Date approvalDate;
	private Long statusCa;
	private Long isActive;
	private String dearGroup;
	
	private Date signDate;
	private String signPlace;
	private String toGroup;
	
	public ConstrAcceptanceRequestBO() {
		setColId("constAcceptanceRequestId");
		setColName("constAcceptanceRequestId");
		setUniqueColumn(new String[] { "constAcceptanceRequestId" });
	}

	@Column(name = "CONSTRUCT_ID", length = 22)
	public Long getConstructId() {
		return constructId;
	}

	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTR_ACCEPTANCE_REQUEST_SEQ") })
	@Column(name = "CONST_ACCEPTANCE_REQUEST_ID", length = 22)
	public Long getConstAcceptanceRequestId() {
		return constAcceptanceRequestId;
	}

	public void setConstAcceptanceRequestId(Long constAcceptanceRequestId) {
		this.constAcceptanceRequestId = constAcceptanceRequestId;
	}

	@Column(name = "CODE", length = 400)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "SEND_PERSON_ID", length = 22)
	public Long getSendPersonId() {
		return sendPersonId;
	}

	public void setSendPersonId(Long sendPersonId) {
		this.sendPersonId = sendPersonId;
	}

	@Column(name = "RECEIVE_PERSON_ID", length = 22)
	public Long getReceivePersonId() {
		return receivePersonId;
	}

	public void setReceivePersonId(Long receivePersonId) {
		this.receivePersonId = receivePersonId;
	}

	@Column(name = "ACCEPTANCE_DATE", length = 7)
	public java.util.Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(java.util.Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	@Column(name = "ACCEPTANCE_PLACE", length = 2000)
	public String getAcceptancePlace() {
		return acceptancePlace;
	}

	public void setAcceptancePlace(String acceptancePlace) {
		this.acceptancePlace = acceptancePlace;
	}

	@Column(name = "CREATED_DATE", length = 7)
	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_USER_ID", length = 22)
	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
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
	public Long getStatusCa() {
		return statusCa;
	}

	public void setStatusCa(Long statusCa) {
		this.statusCa = statusCa;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "DEAR_GROUP", length = 400)
	public String getDearGroup() {
		return dearGroup;
	}

	public void setDearGroup(String dearGroup) {
		this.dearGroup = dearGroup;
	}
	
	/**
	 * @return the signDate
	 */
	@Column(name = "SIGN_DATE")
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @return the signPlace
	 */
	@Column(name = "SIGN_PLACE")
	public String getSignPlace() {
		return signPlace;
	}

	/**
	 * @param signPlace the signPlace to set
	 */
	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}
	
	/**
	 * @return the toGroup
	 */
	@Column(name = "TO_GROUP")
	public String getToGroup() {
		return toGroup;
	}

	/**
	 * @param toGroup the toGroup to set
	 */
	public void setToGroup(String toGroup) {
		this.toGroup = toGroup;
	}

	@Override
	public ConstrAcceptanceRequestDTO toDTO() {
		ConstrAcceptanceRequestDTO constrAcceptanceRequestDTO = new ConstrAcceptanceRequestDTO();
		// set cac gia tri
		constrAcceptanceRequestDTO.setConstructId(this.constructId);
		constrAcceptanceRequestDTO.setConstAcceptanceRequestId(this.constAcceptanceRequestId);
		constrAcceptanceRequestDTO.setCode(this.code);
		constrAcceptanceRequestDTO.setSendPersonId(this.sendPersonId);
		constrAcceptanceRequestDTO.setReceivePersonId(this.receivePersonId);
		constrAcceptanceRequestDTO.setAcceptanceDate(this.acceptanceDate);
		constrAcceptanceRequestDTO.setAcceptancePlace(this.acceptancePlace);
		constrAcceptanceRequestDTO.setCreatedDate(this.createdDate);
		constrAcceptanceRequestDTO.setCreatedUserId(this.createdUserId);
		constrAcceptanceRequestDTO.setApprovalDate(this.approvalDate);
		constrAcceptanceRequestDTO.setStatusCa(this.statusCa);
		constrAcceptanceRequestDTO.setIsActive(this.isActive);
		constrAcceptanceRequestDTO.setDearGroup(this.dearGroup);
		
		constrAcceptanceRequestDTO.setSignDate(signDate);
		constrAcceptanceRequestDTO.setSignPlace(signPlace);
		constrAcceptanceRequestDTO.setToGroup(toGroup);
		
		return constrAcceptanceRequestDTO;
	}
}
