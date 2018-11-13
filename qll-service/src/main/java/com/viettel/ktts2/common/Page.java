package com.viettel.ktts2.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<T> {

	private List<T> rows;
	private Integer totalRow;
	private Integer page;
	private Integer size;
	
	public Page() {
		
	}
	
	public Page(Page<?> p) {
		this.totalRow = p.getTotalRow();
		this.page = p.getPage();
		this.size = p.getSize();
	}
	
	public <E> Page(Page<E> p, Function<E, T> mapper) {
		this.totalRow = p.getTotalRow();
		this.page = p.getPage();
		this.size = p.getSize();
		this.rows = p.getRows().stream().map(mapper).collect(Collectors.toList());
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Integer totalRows) {
		this.totalRow = totalRows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
