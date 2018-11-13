/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.EmployeeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "EMPLOYEE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class EmployeeBO extends BaseFWModelImpl {

	private java.lang.Long employeeId;
	private java.util.Date dateBirth;
	private java.lang.String description;
	private java.lang.String employeeName;
	private java.lang.Long position;
	private java.lang.Long status;

	public EmployeeBO() {
		setColId("employeeId");
		setColName("employeeId");
		setUniqueColumn(new String[] { "employeeId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "EMPLOYEE_SEQ") })
	@Column(name = "EMPLOYEE_ID", length = 22)
	public java.lang.Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(java.lang.Long employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "DATE_BIRTH", length = 7)
	public java.util.Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(java.util.Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	@Column(name = "DESCRIPTION", length = 1000)
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	@Column(name = "EMPLOYEE_NAME", length = 200)
	public java.lang.String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(java.lang.String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "POSITION", length = 22)
	public java.lang.Long getPosition() {
		return position;
	}

	public void setPosition(java.lang.Long position) {
		this.position = position;
	}

	@Column(name = "STATUS", length = 22)
	public java.lang.Long getStatus() {
		return status;
	}

	public void setStatus(java.lang.Long status) {
		this.status = status;
	}

	@Override
	public EmployeeDTO toDTO() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		// set cac gia tri
		employeeDTO.setEmployeeId(this.employeeId);
		employeeDTO.setDateBirth(this.dateBirth);
		employeeDTO.setDescription(this.description);
		employeeDTO.setEmployeeName(this.employeeName);
		employeeDTO.setPosition(this.position);
		employeeDTO.setStatus(this.status);
		return employeeDTO;
	}
}
