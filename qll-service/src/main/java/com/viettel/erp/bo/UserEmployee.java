/**
 * 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Huy
 *
 */
@Entity(name = "userEmpl")
@Table(name = "user_employee")
public class UserEmployee {
	
	@Id
	@Column(name = "user_employee_id")
	private Long userEmployeeId;
	
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "user_id")
	private Long userId;

	/**
	 * @return the userEmployeeId
	 */
	public Long getUserEmployeeId() {
		return userEmployeeId;
	}

	/**
	 * @param userEmployeeId the userEmployeeId to set
	 */
	public void setUserEmployeeId(Long userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
