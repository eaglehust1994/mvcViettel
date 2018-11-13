/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.viettel.erp.bo.EmployeeBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "EMPLOYEEBO")
public class EmployeeDTO extends BaseFWDTOImpl<EmployeeBO> {

	private java.lang.Long employeeId;
	private java.util.Date dateBirth;
	private java.lang.String description;
	private java.lang.String employeeName;
	private java.lang.Long position;
	private java.lang.Long status;

	@Override
	public EmployeeBO toModel() {
		EmployeeBO employeeBO = new EmployeeBO();
		employeeBO.setEmployeeId(this.employeeId);
		employeeBO.setDateBirth(this.dateBirth);
		employeeBO.setDescription(this.description);
		employeeBO.setEmployeeName(this.employeeName);
		employeeBO.setPosition(this.position);
		employeeBO.setStatus(this.status);
		return employeeBO;
	}

	@Override
	public Long getFWModelId() {
		return employeeId;
	}

	@Override
	public String catchName() {
		return getEmployeeId().toString();
	}

	public java.lang.Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(java.lang.Long employeeId) {
		this.employeeId = employeeId;
	}

	public java.util.Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(java.util.Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(java.lang.String employeeName) {
		this.employeeName = employeeName;
	}

	public java.lang.Long getPosition() {
		return position;
	}

	public void setPosition(java.lang.Long position) {
		this.position = position;
	}

	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

}
