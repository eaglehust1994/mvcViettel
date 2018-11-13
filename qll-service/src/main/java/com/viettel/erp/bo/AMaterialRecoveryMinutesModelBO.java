
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "CONSTR_COMPLETE_RECORDS_MAP")
public class AMaterialRecoveryMinutesModelBO extends BaseFWModelImpl {

	private java.lang.Long constrCompReMapId;
	private java.lang.String dataTableName;
	private java.lang.String dataTableId;
	private java.lang.Long dataTableIdValue;
	private java.util.Date createdDate;
	private java.lang.Long createdUserId;
	private java.lang.Long catFileInvoiceId;
	private java.lang.Long status;
	private java.lang.Long levelOrder;
	private Long constructionId;

	private AMaterialRecoveryMinutesBO recoveryMinutesBO;

	public AMaterialRecoveryMinutesModelBO() {
		setColId("constrCompReMapId");
		setColName("constrCompReMapId");
		setUniqueColumn(new String[] { "constrCompReMapId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "CONSTR_COMP_RE_MAP_SEQ") })
	@Column(name = "CONSTR_COMP_RE_MAP_ID", length = 22)
	public java.lang.Long getConstrCompReMapId() {
		return constrCompReMapId;
	}

	public void setConstrCompReMapId(java.lang.Long constrCompReMapId) {
		this.constrCompReMapId = constrCompReMapId;
	}

	@Column(name = "DATA_TABLE_NAME", length = 400)
	public java.lang.String getDataTableName() {
		return dataTableName;
	}

	public void setDataTableName(java.lang.String dataTableName) {
		this.dataTableName = dataTableName;
	}

	@Column(name = "DATA_TABLE_ID", length = 400)
	public java.lang.String getDataTableId() {
		return dataTableId;
	}

	public void setDataTableId(java.lang.String dataTableId) {
		this.dataTableId = dataTableId;
	}

	// @Column(name = "DATA_TABLE_ID_VALUE", length = 22)
	// public java.lang.Long getDataTableIdValue(){
	// return dataTableIdValue;
	// }
	// public void setDataTableIdValue(java.lang.Long dataTableIdValue)
	// {
	// this.dataTableIdValue = dataTableIdValue;
	// }

	@OneToOne
	@JoinColumn(name = "DATA_TABLE_ID_VALUE")
	public AMaterialRecoveryMinutesBO getRecoveryMinutesBO() {
		return recoveryMinutesBO;
	}

	public void setRecoveryMinutesBO(AMaterialRecoveryMinutesBO recoveryMinutesBO) {
		this.recoveryMinutesBO = recoveryMinutesBO;
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

	@Column(name = "CAT_FILE_INVOICE_ID", length = 22)
	public java.lang.Long getCatFileInvoiceId() {
		return catFileInvoiceId;
	}

	public void setCatFileInvoiceId(java.lang.Long catFileInvoiceId) {
		this.catFileInvoiceId = catFileInvoiceId;
	}

	@Column(name = "STATUS", length = 22)
	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	@Column(name = "LEVEL_ORDER", length = 22)
	public java.lang.Long getLevelOrder() {
		return levelOrder;
	}

	public void setLevelOrder(java.lang.Long levelOrder) {
		this.levelOrder = levelOrder;
	}

	@Column(name = "CONSTRUCTION_ID", length = 12)
	public Long getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(Long constructionId) {
		this.constructionId = constructionId;
	}

	@Override
	public ConstrCompleteRecordsMapDTO toDTO() {
		ConstrCompleteRecordsMapDTO constrCompleteRecordsMapDTO = new ConstrCompleteRecordsMapDTO();
		// set cac gia tri
		constrCompleteRecordsMapDTO.setConstrCompReMapId(this.constrCompReMapId);
		constrCompleteRecordsMapDTO.setDataTableName(this.dataTableName);
		constrCompleteRecordsMapDTO.setDataTableId(this.dataTableId);
		constrCompleteRecordsMapDTO.setDataTableIdValue(this.dataTableIdValue);
		constrCompleteRecordsMapDTO.setCreatedDate(this.createdDate);
		constrCompleteRecordsMapDTO.setCreatedUserId(this.createdUserId);
		constrCompleteRecordsMapDTO.setCatFileInvoiceId(this.catFileInvoiceId);
		constrCompleteRecordsMapDTO.setStatus(this.status);
		constrCompleteRecordsMapDTO.setLevelOrder(this.levelOrder);
		constrCompleteRecordsMapDTO.setConstructionId(this.constructionId);
		return constrCompleteRecordsMapDTO;
	}

}
