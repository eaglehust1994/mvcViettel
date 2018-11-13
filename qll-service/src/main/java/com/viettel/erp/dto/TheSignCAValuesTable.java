/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.viettel.erp.bo.AAssetTypeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

public class TheSignCAValuesTable  {
	
	private String dataTableName;
	private String dataTableID;
	private String dataTableIDValue;
	
	public TheSignCAValuesTable() {
	}
	public TheSignCAValuesTable(String dataTableName, String dataTableID, String dataTableIDValuefrom) {
		super();
		this.dataTableName = dataTableName;
		this.dataTableID = dataTableID;
		this.dataTableIDValue = dataTableIDValuefrom;
	}
	public String getDataTableName() {
		return dataTableName;
	}
	public void setDataTableName(String dataTableName) {
		this.dataTableName = dataTableName;
	}
	public String getDataTableID() {
		return dataTableID;
	}
	public void setDataTableID(String dataTableID) {
		this.dataTableID = dataTableID;
	}
	public String getDataTableIDValue() {
		return dataTableIDValue;
	}
	public void setDataTableIDValue(String dataTableIDValue) {
		this.dataTableIDValue = dataTableIDValue;
	}
	
	
	
	
	
	
}
