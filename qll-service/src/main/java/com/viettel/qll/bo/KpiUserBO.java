package com.viettel.qll.bo;

import com.viettel.qll.dto.KpiUserDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.KpiUserBO")
@Table(name = "KPI_USER")
/**
 *
 * @author: hailh10
 */
public class KpiUserBO extends BaseFWModelImpl {
    @Id
	@Column(name = "FULL_NAME", length = 3000)
	private java.lang.String fullName;
	@Column(name = "EMPLOYEE_CODE", length = 1000)
	private java.lang.String employeeCode;
	@Column(name = "USER_ROLE", length = 10)
	private java.lang.Long userRole;
	
	public java.lang.String getFullName(){
		return fullName;
	}
	
	public void setFullName(java.lang.String fullName)
	{
		this.fullName = fullName;
	}
	
	public java.lang.String getEmployeeCode(){
		return employeeCode;
	}
	
	public void setEmployeeCode(java.lang.String employeeCode)
	{
		this.employeeCode = employeeCode;
	}
	
   
    public java.lang.Long getUserRole() {
		return userRole;
	}

	public void setUserRole(java.lang.Long userRole) {
		this.userRole = userRole;
	}

	@Override
    public KpiUserDTO toDTO() {
        KpiUserDTO kpiUserDTO = new KpiUserDTO(); 
        kpiUserDTO.setFullName(this.fullName);		
        kpiUserDTO.setEmployeeCode(this.employeeCode);	
        kpiUserDTO.setUserRole(this.userRole);
        return kpiUserDTO;
    }
}
