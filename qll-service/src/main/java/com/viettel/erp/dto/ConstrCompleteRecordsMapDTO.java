/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.viettel.erp.bo.AMaterialRecoveryMinutesModelBO;
import com.viettel.erp.bo.CategoryAcceptanceModelBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapCompletionDrawingBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapDUCMBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapReportBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "CONSTR_COMPLETE_RECORDS_MAPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrCompleteRecordsMapDTO extends BaseFWDTOImpl<ConstrCompleteRecordsMapBO> {

	private java.lang.Long constrCompReMapId;
	private java.lang.String dataTableName;
	private java.lang.String dataTableId;
	private java.lang.Long dataTableIdValue;
	private java.util.Date createdDate;
	private java.util.Date signed_date;
	private java.lang.String createdDateStr;
	private java.lang.Long createdUserId;
	private java.lang.Long catFileInvoiceId;
	private java.lang.Long status;
	private java.lang.Long levelOrder;
	private java.lang.Long constructionId;
	private java.lang.Long typeConstruction;
	private java.lang.String isExistProfile;
	private java.lang.String loginName;
	private java.lang.Long countsl;
	private java.lang.Long contractId;
	private List<ConstructionDto> listConstruct;
	private java.lang.String constructName;
	private java.lang.String constructorName;
	private java.lang.String constrtCode;
	private java.lang.String provinceName;
	private java.lang.String constrTypeName;
	private java.lang.String contractCode;
	private java.lang.String contractName;
	private java.lang.String constrtAddress;
	private java.lang.String fileInvoiceName;
	private java.lang.Long counts;
	

	public java.lang.Long getCounts() {
		return counts;
	}


	public void setCounts(java.lang.Long counts) {
		this.counts = counts;
	}


	public java.lang.String getFileInvoiceName() {
		return fileInvoiceName;
	}


	public void setFileInvoiceName(java.lang.String fileInvoiceName) {
		this.fileInvoiceName = fileInvoiceName;
	}


	private String createdUserName;
	private String invoiceName;
	private String invoiceCode;
	private String signEmployee;
	private String signComments;
	private String signApprovalDate;
	private String signApprovalStatus;
	private String employeeFullname;
	private String roleName;
	private String code;
	private List<ConstrCompleteRecordMapSubDTO> constrCompleteRecordMapSubDTO;
	private java.lang.Integer stt;

	@Override
	public ConstrCompleteRecordsMapBO toModel() {
		ConstrCompleteRecordsMapBO constrCompleteRecordsMapBO = new ConstrCompleteRecordsMapBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapBO.setConstructionId(this.constructionId);
		constrCompleteRecordsMapBO.setCode(this.code);
		// constrCompleteRecordsMapBO.setConstructionacceptance(this.dataTableIdValue
		// == null ? null :new ConstructionAcceptanceBO(this.dataTableIdValue));

		return constrCompleteRecordsMapBO;
	}


	// DistanceUnloadConstrMinutes
	public ConstrCompleteRecordsMapDUCMBO toModelDUCM() {
		ConstrCompleteRecordsMapDUCMBO constrCompleteRecordsMapBO = new ConstrCompleteRecordsMapDUCMBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		// constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		// constrCompleteRecordsMapBO.setConstructionacceptance(this.dataTableIdValue
		// == null ? null :new ConstructionAcceptanceBO(this.dataTableIdValue));

		return constrCompleteRecordsMapBO;
	}

	public AMaterialRecoveryMinutesModelBO toModelRecoverryMinutes() {
		AMaterialRecoveryMinutesModelBO constrCompleteRecordsMapBO = new AMaterialRecoveryMinutesModelBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		// constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapBO.setConstructionId(this.constructionId);

		return constrCompleteRecordsMapBO;
	}

	public ConstrCompleteRecordsMapReportBO toModelCableMeaReport() {
		ConstrCompleteRecordsMapReportBO constrCompleteRecordsMapBO = new ConstrCompleteRecordsMapReportBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		// constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapBO.setConstructionId(this.constructionId);

		return constrCompleteRecordsMapBO;
	}

	public CategoryAcceptanceModelBO toModelCategoryAcceptance() {
		CategoryAcceptanceModelBO constrCompleteRecordsMapBO = new CategoryAcceptanceModelBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		// constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapBO.setConstructionId(this.constructionId);

		return constrCompleteRecordsMapBO;
	}

	public ConstrCompleteRecordsMapCompletionDrawingBO toModelDrawing() {
		ConstrCompleteRecordsMapCompletionDrawingBO constrCompleteRecordsMapBO = new ConstrCompleteRecordsMapCompletionDrawingBO();
		constrCompleteRecordsMapBO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapBO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapBO.setDataTableId(this.dataTableId);
		// constrCompleteRecordsMapBO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapBO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapBO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapBO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapBO.setStatus(this.status);
		constrCompleteRecordsMapBO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapBO.setConstructionId(this.constructionId);

		return constrCompleteRecordsMapBO;
	}

	@Override
	public Long getFWModelId() {
		return constrCompReMapId;
	}

	@Override
	public String catchName() {
		return getConstrCompReMapId().toString();
	}

	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	public java.lang.String getDataTableName() {
		return dataTableName;
	}

	public void setDataTableName(java.lang.String dataTableName) {
		this.dataTableName = dataTableName;
	}

	public java.lang.String getDataTableId() {
		return dataTableId;
	}

	public void setDataTableId(java.lang.String dataTableId) {
		this.dataTableId = dataTableId;
	}

	public java.lang.Long getDataTableIdValue() {
		return dataTableIdValue;
	}

	public void setDataTableIdValue(java.lang.Long dataTableIdValue) {
		this.dataTableIdValue = dataTableIdValue;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(java.lang.Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public java.lang.Long getCatFileInvoiceId() {
		return catFileInvoiceId;
	}

	public void setCatFileInvoiceId(java.lang.Long catFileInvoiceId) {
		this.catFileInvoiceId = catFileInvoiceId;
	}

	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	public java.lang.Long getLevelOrder() {
		return levelOrder;
	}

	public void setLevelOrder(java.lang.Long levelOrder) {
		this.levelOrder = levelOrder;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getSignEmployee() {
		return signEmployee;
	}

	public void setSignEmployee(String signEmployee) {
		this.signEmployee = signEmployee;
	}

	public String getSignComments() {
		return signComments;
	}

	public void setSignComments(String signComments) {
		this.signComments = signComments;
	}

	public String getSignApprovalDate() {
		return signApprovalDate;
	}

	public void setSignApprovalDate(String signApprovalDate) {
		this.signApprovalDate = signApprovalDate;
	}

	public String getSignApprovalStatus() {
		return signApprovalStatus;
	}

	public void setSignApprovalStatus(String signApprovalStatus) {
		this.signApprovalStatus = signApprovalStatus;
	}

	public String getEmployeeFullname() {
		return employeeFullname;
	}

	public void setEmployeeFullname(String employeeFullname) {
		this.employeeFullname = employeeFullname;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public Long getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(Long constructionId) {
		this.constructionId = constructionId;
	}

	public List<ConstrCompleteRecordMapSubDTO> getConstrCompleteRecordMapSubDTO() {
		return constrCompleteRecordMapSubDTO;
	}

	public void setConstrCompleteRecordMapSubDTO(List<ConstrCompleteRecordMapSubDTO> constrCompleteRecordMapSubDTO) {
		this.constrCompleteRecordMapSubDTO = constrCompleteRecordMapSubDTO;
	}

	public java.lang.Long getTypeConstruction() {
		return typeConstruction;
	}

	public void setTypeConstruction(java.lang.Long typeConstruction) {
		this.typeConstruction = typeConstruction;
	}

	public java.lang.String getIsExistProfile() {
		return isExistProfile;
	}

	public void setIsExistProfile(java.lang.String isExistProfile) {
		this.isExistProfile = isExistProfile;
	}


	public java.lang.String getLoginName() {
		return loginName;
	}


	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}


	public java.lang.String getCreatedDateStr() {
		return createdDateStr;
	}


	public void setCreatedDateStr(java.lang.String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public java.lang.Integer getStt() {
		return stt;
	}


	public void setStt(java.lang.Integer stt) {
		this.stt = stt;
	}


	public java.lang.Long getCountsl() {
		return countsl;
	}


	public void setCountsl(java.lang.Long countsl) {
		this.countsl = countsl;
	}


	public List<ConstructionDto> getListConstruct() {
		return listConstruct;
	}


	public void setListConstruct(List<ConstructionDto> listConstruct) {
		this.listConstruct = listConstruct;
	}


	public java.lang.String getConstructName() {
		return constructName;
	}


	public void setConstructName(java.lang.String constructName) {
		this.constructName = constructName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public java.lang.String getConstructorName() {
		return constructorName;
	}


	public void setConstructorName(java.lang.String constructorName) {
		this.constructorName = constructorName;
	}


	public java.lang.String getConstrtCode() {
		return constrtCode;
	}


	public void setConstrtCode(java.lang.String constrtCode) {
		this.constrtCode = constrtCode;
	}


	public java.lang.String getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(java.lang.String provinceName) {
		this.provinceName = provinceName;
	}


	public java.lang.String getConstrTypeName() {
		return constrTypeName;
	}


	public void setConstrTypeName(java.lang.String constrTypeName) {
		this.constrTypeName = constrTypeName;
	}


	public java.lang.String getContractCode() {
		return contractCode;
	}


	public void setContractCode(java.lang.String contractCode) {
		this.contractCode = contractCode;
	}


	public java.lang.String getContractName() {
		return contractName;
	}


	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}


	public java.lang.String getConstrtAddress() {
		return constrtAddress;
	}


	public void setConstrtAddress(java.lang.String constrtAddress) {
		this.constrtAddress = constrtAddress;
	}


	public java.util.Date getSigned_date() {
		return signed_date;
	}


	public void setSigned_date(java.util.Date signed_date) {
		this.signed_date = signed_date;
	}


	public java.lang.Long getContractId() {
		return contractId;
	}


	public void setContractId(java.lang.Long contractId) {
		this.contractId = contractId;
	}
	
	
	

}
