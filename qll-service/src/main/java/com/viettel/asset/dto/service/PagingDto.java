package com.viettel.asset.dto.service;

import javax.ws.rs.QueryParam;

public class PagingDto {
	@QueryParam("page")
	private java.lang.Integer page;
	@QueryParam("size")
	private java.lang.Integer pageSize;
	public java.lang.Integer getPage() {
		return page;
	}
	public void setPage(java.lang.Integer page) {
		this.page = page;
	}
	public java.lang.Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(java.lang.Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getFirstResult(){
		return (page-1)*pageSize;
	}
}
