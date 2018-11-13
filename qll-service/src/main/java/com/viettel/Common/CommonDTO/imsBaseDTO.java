/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.Common.CommonDTO;

import java.util.Date;
import java.util.List;

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

public abstract class imsBaseDTO<Entity extends BaseFWModelImpl> extends
		BaseFWDTOImpl<Entity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Long id;
	private java.lang.String pathFile;

	private java.lang.String filePathError;
	private String text;
	private boolean isSize;

	public java.lang.String getFilePathError() {
		return filePathError;
	}

	public void setFilePathError(java.lang.String filePathError) {
		this.filePathError = filePathError;
	}

	public java.lang.String getPathFile() {
		return pathFile;
	}

	public void setPathFile(java.lang.String pathFile) {
		this.pathFile = pathFile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getIsSize() {
		return isSize;
	}

	public void setIsSize(boolean isSize) {
		this.isSize = isSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	private Integer pageSize;
	private String keySearch;
	private String keySearchAction;
	// thêm keysearch2
	private String keySearch2;
	// search by date 
	private Date searchDateFrom;
	private Date searchDateTo;

	public Date getSearchDateFrom() {
		return searchDateFrom;
	}

	public void setSearchDateFrom(Date searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}

	public Date getSearchDateTo() {
		return searchDateTo;
	}

	public void setSearchDateTo(Date searchDateTo) {
		this.searchDateTo = searchDateTo;
	}

	public void setSize(boolean isSize) {
		this.isSize = isSize;
	}

	public String getKeySearchAction() {
		return keySearchAction;
	}

	public void setKeySearchAction(String keySearchAction) {
		this.keySearchAction = keySearchAction;
	}

	public String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}

	public String getKeySearch2() {
		return keySearch2;
	}

	public void setKeySearch2(String keySearch2) {
		this.keySearch2 = keySearch2;
	}

	private java.lang.Integer totalRecord;

	public java.lang.Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(java.lang.Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

}
