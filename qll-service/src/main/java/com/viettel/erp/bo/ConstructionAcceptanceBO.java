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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
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
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity(name = "constructionacceptance")
@Table(name = "CONSTRUCTION_ACCEPTANCE")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE CONSTRUCTION_ACCEPTANCE c SET c.IS_ACTIVE = 0 WHERE c.CONSTRUCTION_ACCEPTANCE_ID = ? ")
@Where(clause = "IS_ACTIVE = '1' OR IS_ACTIVE IS NULL")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstructionAcceptanceBO extends BaseFWModelImpl {

	private List<ConstrAcceptWorkListBO> constracceptworklist = Lists.newArrayList();
	private CatEmployeeBO aMonitor;
	private CatEmployeeBO aInChargeMonitor;
	private CatEmployeeBO bDirector;
	private CatEmployeeBO bInChargeConstruct;
	private CatEmployeeBO cDesignDirection;
	private CatEmployeeBO cDesignManager;
	private List<ConstrAcceptMerListBO> constracceptmerlist = Lists.newArrayList();

	private ConstrCompleteRecordsMapBO constrcompleterecordsmap;

	private VConstructionHcqtBO vconstructionHcqt;

	private java.lang.Long constructionAcceptanceId;
	private java.lang.String code;

	private java.util.Date acceptFromDate;
	private java.util.Date acceptToDate;
	private java.lang.String acceptPlace;
	private java.lang.String applyBenchmark;
	private java.lang.String constructionQuality;
	private java.lang.String otherDocuments;
	private java.lang.String otherComments;
	private java.lang.Long conclusion;
	private java.lang.String completeRequest;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.util.Date approvalDate;
	private java.lang.Long statusCa;
	private java.lang.Long documentCaId;
	private java.lang.Long type;
	private java.lang.String incompleteReason;
	private java.lang.Long isActive;

	private java.util.Date signdate;
	private java.lang.String signplace;
	
	public ConstructionAcceptanceBO() {
		setColId("constructionAcceptanceId");
		setColName("constructionAcceptanceId");
		setUniqueColumn(new String[] { "constructionAcceptanceId" });
	}

	public ConstructionAcceptanceBO(Long constructionAcceptanceId2) {
		this.constructionAcceptanceId = constructionAcceptanceId2;
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTRUCTION_ACCEPTANCE_SEQ") })
	@Column(name = "CONSTRUCTION_ACCEPTANCE_ID", length = 22)
	public java.lang.Long getConstructionAcceptanceId() {
		return constructionAcceptanceId;
	}

	public void setConstructionAcceptanceId(java.lang.Long constructionAcceptanceId) {
		this.constructionAcceptanceId = constructionAcceptanceId;
	}

	@Column(name = "CODE", length = 400)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "A_MONITOR_ID", referencedColumnName = "ID")
    public CatEmployeeBO getaMonitor() {
		return aMonitor;
	}

	public void setaMonitor(CatEmployeeBO aMonitor) {
		this.aMonitor = aMonitor;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "A_IN_CHARGE_MONITOR_ID", referencedColumnName = "ID")
	public CatEmployeeBO getaInChargeMonitor() {
		return aInChargeMonitor;
	}

	public void setaInChargeMonitor(CatEmployeeBO aInChargeMonitor) {
		this.aInChargeMonitor = aInChargeMonitor;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "B_DIRECTOR_ID", referencedColumnName = "ID")
	public CatEmployeeBO getbDirector() {
		return bDirector;
	}

	public void setbDirector(CatEmployeeBO bDirector) {
		this.bDirector = bDirector;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "B_IN_CHARGE_CONSTRUCT_ID", referencedColumnName = "ID")
	public CatEmployeeBO getbInChargeConstruct() {
		return bInChargeConstruct;
	}

	public void setbInChargeConstruct(CatEmployeeBO bInChargeConstruct) {
		this.bInChargeConstruct = bInChargeConstruct;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "C_DESIGN_DIRECTION_ID", referencedColumnName = "ID")
	public CatEmployeeBO getcDesignDirection() {
		return cDesignDirection;
	}

	public void setcDesignDirection(CatEmployeeBO cDesignDirection) {
		this.cDesignDirection = cDesignDirection;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "C_DESIGN_MANAGER_ID", referencedColumnName = "ID")
	public CatEmployeeBO getcDesignManager() {
		return cDesignManager;
	}

	public void setcDesignManager(CatEmployeeBO cDesignManager) {
		this.cDesignManager = cDesignManager;
	}

	@Column(name = "ACCEPT_FROM_DATE", length = 7)
	public java.util.Date getAcceptFromDate() {
		return acceptFromDate;
	}

	public void setAcceptFromDate(java.util.Date acceptFromDate) {
		this.acceptFromDate = acceptFromDate;
	}

	@Column(name = "ACCEPT_TO_DATE", length = 7)
	public java.util.Date getAcceptToDate() {
		return acceptToDate;
	}

	public void setAcceptToDate(java.util.Date acceptToDate) {
		this.acceptToDate = acceptToDate;
	}

	@Column(name = "ACCEPT_PLACE", length = 2000)
	public java.lang.String getAcceptPlace() {
		return acceptPlace;
	}

	public void setAcceptPlace(java.lang.String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	@Column(name = "APPLY_BENCHMARK", length = 1000)
	public java.lang.String getApplyBenchmark() {
		return applyBenchmark;
	}

	public void setApplyBenchmark(java.lang.String applyBenchmark) {
		this.applyBenchmark = applyBenchmark;
	}

	@Column(name = "CONSTRUCTION_QUALITY", length = 400)
	public java.lang.String getConstructionQuality() {
		return constructionQuality;
	}

	public void setConstructionQuality(java.lang.String constructionQuality) {
		this.constructionQuality = constructionQuality;
	}

	@Column(name = "OTHER_DOCUMENTS", length = 1000)
	public java.lang.String getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(java.lang.String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	@Column(name = "OTHER_COMMENTS", length = 2000)
	public java.lang.String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(java.lang.String otherComments) {
		this.otherComments = otherComments;
	}

	@Column(name = "CONCLUSION", length = 22)
	public java.lang.Long getConclusion() {
		return conclusion;
	}

	public void setConclusion(java.lang.Long conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "COMPLETE_REQUEST", length = 2000)
	public java.lang.String getCompleteRequest() {
		return completeRequest;
	}

	public void setCompleteRequest(java.lang.String completeRequest) {
		this.completeRequest = completeRequest;
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

	@Column(name = "DOCUMENT_CA_ID", length = 22)
	public java.lang.Long getDocumentCaId() {
		return documentCaId;
	}

	public void setDocumentCaId(java.lang.Long documentCaId) {
		this.documentCaId = documentCaId;
	}

	@Column(name = "TYPE", length = 22)
	public java.lang.Long getType() {
		return type;
	}

	public void setType(java.lang.Long type) {
		this.type = type;
	}

	@Column(name = "INCOMPLETE_REASON", length = 2000)
	public java.lang.String getIncompleteReason() {
		return incompleteReason;
	}

	public void setIncompleteReason(java.lang.String incompleteReason) {
		this.incompleteReason = incompleteReason;
	}

	@Column(name = "IS_ACTIVE", length = 22)
	public java.lang.Long getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.Long isActive) {
		this.isActive = isActive;
	}

	@OneToMany(mappedBy = "constructionacceptance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<ConstrAcceptWorkListBO> getConstracceptworklist() {
		return constracceptworklist;
	}

	public void setConstracceptworklist(List<ConstrAcceptWorkListBO> constracceptworklist) {
		this.constracceptworklist = constracceptworklist;
	}

	/**
	 * @return the constracceptmerlist
	 */
	
	  @OneToMany(mappedBy = "constructionacceptance",fetch = FetchType.EAGER, cascade = CascadeType.ALL ) 
	  public List<ConstrAcceptMerListBO> getConstracceptmerlist() 
	  { return constracceptmerlist; }
	   /**
		 * @param constracceptmerlist
		 *            the constracceptmerlist to set
		 */
	  public void setConstracceptmerlist(List<ConstrAcceptMerListBO> constracceptmerlist){
		  this.constracceptmerlist =  constracceptmerlist; 
	   }
		

	/*
	 * @Column(name = "CONSTRUCT_ID", length = 22) public java.lang.Long
	 * getConstructId(){ return constructId; } public void
	 * setConstructId(java.lang.Long constructId) { this.constructId =
	 * constructId; }
	 */
	/**
	 * @return the vconstructionHcqt
	 */
	@ManyToOne
	@JoinColumn(name = "CONSTRUCT_ID",referencedColumnName="CONSTRUCT_ID")
	public VConstructionHcqtBO getVconstructionHcqt() {
		return vconstructionHcqt;
	}

	/**
	 * @param vconstructionHcqt
	 *            the vconstructionHcqt to set
	 */
	public void setVconstructionHcqt(VConstructionHcqtBO vconstructionHcqt) {
		this.vconstructionHcqt = vconstructionHcqt;
	}

	// @PrimaryKeyJoinColumn(referencedColumnName = "DATA_TABLE_ID_VALUE")

/*	@OneToOne(mappedBy = "constructionacceptance", fetch = FetchType.LAZY)  
	public ConstrCompleteRecordsMapBO getConstrcompleterecordsmap() {
		return constrcompleterecordsmap;
	}

	public void setConstrcompleterecordsmap(ConstrCompleteRecordsMapBO constrcompleterecordsmap) {
		this.constrcompleterecordsmap = constrcompleterecordsmap;
	}*/

	
	@Column(name = "SIGN_PLACE")
	public java.lang.String getSignplace() {
		return signplace;
	}

	public void setSignplace(java.lang.String signplace) {
		this.signplace = signplace;
	}
	@Column(name = "SIGN_DATE")
	public java.util.Date getSigndate() {
		return signdate;
	}

	public void setSigndate(java.util.Date signdate) {
		this.signdate = signdate;
	}
	
	
	@Override
	public ConstructionAcceptanceDTO toDTO() {
		ConstructionAcceptanceDTO constructionAcceptanceDTO = new ConstructionAcceptanceDTO();
		// set cac gia tri

		constructionAcceptanceDTO.setConstructionAcceptanceId(this.constructionAcceptanceId);
		constructionAcceptanceDTO.setCode(this.code);
		
		constructionAcceptanceDTO.setAMonitorId(aMonitor == null ? null : Long.parseLong(aMonitor.getId()));
		constructionAcceptanceDTO.setamonitorName(aMonitor == null ? "" : aMonitor.getFullName());
		
		constructionAcceptanceDTO.setAInChargeMonitorId(aInChargeMonitor == null ? null : Long.parseLong(aInChargeMonitor.getId()));
		constructionAcceptanceDTO.setainChargeMonitorName(aInChargeMonitor == null ? "" : aInChargeMonitor.getFullName());
		
		constructionAcceptanceDTO.setBDirectorId(bDirector == null ? null : Long.parseLong(bDirector.getId()));
		constructionAcceptanceDTO.setbdirectorName(bDirector == null ? "" : bDirector.getFullName());
		
		constructionAcceptanceDTO.setBInChargeConstructId(bInChargeConstruct == null ? null : Long.parseLong(bInChargeConstruct.getId()));
		constructionAcceptanceDTO.setbinChargeConstructName(bInChargeConstruct == null ? "" : bInChargeConstruct.getFullName());
		
		constructionAcceptanceDTO.setCDesignDirectionId(cDesignDirection == null ? null : Long.parseLong(cDesignDirection.getId()));
		constructionAcceptanceDTO.setcdesignDirectionName(cDesignDirection == null ? "" : cDesignDirection.getFullName());
		
		constructionAcceptanceDTO.setCDesignManagerId(cDesignManager == null ? null : Long.parseLong(cDesignManager.getId()));
		constructionAcceptanceDTO.setcdesignManagerName(cDesignManager == null ? "" : cDesignManager.getFullName());
		
		constructionAcceptanceDTO.setAcceptFromDate(this.acceptFromDate);
		constructionAcceptanceDTO.setAcceptToDate(this.acceptToDate);
		constructionAcceptanceDTO.setAcceptPlace(this.acceptPlace== null ? "" : this.acceptPlace);
		constructionAcceptanceDTO.setApplyBenchmark(this.applyBenchmark== null ? "" : this.applyBenchmark);
		constructionAcceptanceDTO.setConstructionQuality(this.constructionQuality== null ? "" : this.constructionQuality);
		constructionAcceptanceDTO.setOtherDocuments(this.otherDocuments== null ? "" : this.otherDocuments);
		constructionAcceptanceDTO.setOtherComments(this.otherComments== null ? "" : this.otherComments);
		constructionAcceptanceDTO.setConclusion(this.conclusion);
		constructionAcceptanceDTO.setCompleteRequest(this.completeRequest== null ? "" : this.completeRequest);
		constructionAcceptanceDTO.setCreatedDate(this.createdDate);
		constructionAcceptanceDTO.setCreatedUserId(this.createdUserId);
		constructionAcceptanceDTO.setApprovalDate(this.approvalDate);
		constructionAcceptanceDTO.setStatusCa(this.statusCa);
		constructionAcceptanceDTO.setDocumentCaId(this.documentCaId);
		constructionAcceptanceDTO.setType(this.type);
		constructionAcceptanceDTO.setIncompleteReason(this.incompleteReason == null?"":this.incompleteReason );
		constructionAcceptanceDTO.setIsActive(this.isActive);
		constructionAcceptanceDTO.setSignPlace(this.signplace == "" ? "" : this.signplace);
		constructionAcceptanceDTO.setSignDate(this.signdate);
		constructionAcceptanceDTO.setConstrtName(vconstructionHcqt.getConstrtName() == null ? "" : vconstructionHcqt.getConstrtName());
		constructionAcceptanceDTO.setConstrtCode(vconstructionHcqt.getConstrtCode() == null ? "" : vconstructionHcqt.getConstrtCode());
		constructionAcceptanceDTO
				.setContractCode(vconstructionHcqt.getContractCode() == null ? "" : vconstructionHcqt.getContractCode());
		constructionAcceptanceDTO
		.setContractId(vconstructionHcqt.getContractId() == null ? 0 : vconstructionHcqt.getContractId());
		
		constructionAcceptanceDTO
				.setContractName(vconstructionHcqt.getContractName() == null ? "" : vconstructionHcqt.getContractName());
		constructionAcceptanceDTO
				.setConstrtAddress(vconstructionHcqt.getConstrtAddress() == null ? "" : vconstructionHcqt.getConstrtAddress());
		constructionAcceptanceDTO.setConstructId(vconstructionHcqt.getConstructId() == null ? 0 : vconstructionHcqt.getConstructId());
		
		constructionAcceptanceDTO.setStationCode(vconstructionHcqt.getStationCode() == null ? "" : vconstructionHcqt.getStationCode());
		
		constructionAcceptanceDTO.setConstracceptworklist(
				Lists.newArrayList(Iterables.transform(this.constracceptworklist, arg0 -> arg0.toDTO())));
		constructionAcceptanceDTO.setConstracceptmerlist(
				Lists.newArrayList(Iterables.transform(this.constracceptmerlist, arg0 -> arg0.toDTO())));
		constructionAcceptanceDTO.setConstrcompleterecordsmap(
				constrcompleterecordsmap == null ? null : constrcompleterecordsmap.toDTO());
		return constructionAcceptanceDTO;
	}




}
