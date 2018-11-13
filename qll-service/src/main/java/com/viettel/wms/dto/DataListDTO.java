/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author HungLQ9
 */
@XmlRootElement(name = "data")
@XmlSeeAlso({  })
public class DataListDTO {

	private Integer start;
	private Integer size;
	private Integer total;
	private List data;
	private String filterClau;

	public String getFilterClau() {
		return filterClau;
	}

	public void setFilterClau(String filterClau) {
		this.filterClau = filterClau;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
