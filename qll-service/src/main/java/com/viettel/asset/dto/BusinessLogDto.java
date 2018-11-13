package com.viettel.asset.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viettel.asset.bo.BusinessLog;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UString;

public class BusinessLogDto {

	private Long businessLogId;
	private Long userId;
	private Date createdDate;
	private String dbTable;
	private String bulAction;
	private String oldValue;
	private String newValue;
	private String description;
	private Long mainId;
	private List<String> lstDbTable;	

	public BusinessLogDto() {

	}

	public BusinessLogDto(BusinessLog entity) throws Exception {
		this.businessLogId = entity.getBusinessLogId();
		this.userId = entity.getUserId();
		this.createdDate = entity.getCreatedDate();
		this.dbTable = entity.getDbTable();
		this.bulAction = entity.getBulAction();
		this.oldValue = entity.getOldValue();
		this.newValue = entity.getNewValue();
		this.description = entity.getDescription();
		this.mainId = entity.getMainId();
		if (entity.getDbTable() != null) {
			this.lstDbTable = UString.split(entity.getDbTable(), 
					BusinessLog.Constants.DB_TABLE_SEPARATOR);
		}
	}

	public BusinessLog toEntity() throws Exception {
		BusinessLog entity = new BusinessLog();
		entity.setBusinessLogId(this.businessLogId);
		entity.setUserId(this.userId);
		entity.setCreatedDate(this.createdDate);
		entity.setDbTable(UString.join(this.getLstDbTable(), 
				BusinessLog.Constants.DB_TABLE_SEPARATOR));
		entity.setBulAction(this.bulAction);
		entity.setOldValue(this.oldValue);
		entity.setNewValue(this.newValue);
		entity.setDescription(this.description);
		entity.setMainId(this.mainId);
		return entity;
	}

	public void validate() throws Exception {
		if (userId == null) {
			throw new BusinessException("error.businessLog.userId.null");
		}

		if (createdDate == null) {
			throw new BusinessException("error.businessLog.createdDate.null");
		}

		if (UString.join(this.getLstDbTable(), 
				BusinessLog.Constants.DB_TABLE_SEPARATOR).length() > 1000) {
			throw new BusinessException("error.businessLog.dbTable.maxlength");
		}
		
		if (UString.isNullOrWhitespace(bulAction)) {
			throw new BusinessException("error.businessLog.bulAction.null");
		}

		if (bulAction != null && bulAction.trim().length() > 250) {
			throw new BusinessException("error.businessLog.bulAction.maxlength");
		}

		if (description != null && description.trim().length() > 1000) {
			throw new BusinessException("error.businessLog.description.maxlength");
		}
	}
	
	

	public List<String> getLstDbTable() {
		if (lstDbTable == null) {
			lstDbTable = new ArrayList<>();
		}
		return lstDbTable;
	}

	public void setLstDbTable(List<String> lstDbTable) {
		this.lstDbTable = lstDbTable;
	}

	public Long getBusinessLogId() {
		return businessLogId;
	}

	public void setBusinessLogId(Long businessLogId) {
		this.businessLogId = businessLogId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDbTable() {
		return dbTable;
	}

	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}

	public String getBulAction() {
		return bulAction;
	}

	public void setBulAction(String bulAction) {
		this.bulAction = bulAction;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	
	

}
