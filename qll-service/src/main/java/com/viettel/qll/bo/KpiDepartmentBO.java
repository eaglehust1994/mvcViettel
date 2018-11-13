package com.viettel.qll.bo;

import com.viettel.qll.dto.KpiDepartmentDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.KpiDepartmentBO")
@Table(name = "KPI_DEPARTMENT")
/**
 *
 * @author: hailh10
 */
public class KpiDepartmentBO extends BaseFWModelImpl {
    
	@Id
	@Column(name = "DEPARTMENT_ID", length = 22)
	private java.lang.Long derpartmentId;
	@Column(name = "DEPARTMENT_NAME", length = 4000)
	private java.lang.String derpartmentName;

	
	public java.lang.Long getDerpartmentId(){
		return derpartmentId;
	}
	
	public void setDerpartmentId(java.lang.Long derpartmentId)
	{
		this.derpartmentId = derpartmentId;
	}
	
	public java.lang.String getDerpartmentName(){
		return derpartmentName;
	}
	
	public void setDerpartmentName(java.lang.String derpartmentName)
	{
		this.derpartmentName = derpartmentName;
	}
   
    @Override
    public KpiDepartmentDTO toDTO() {
        KpiDepartmentDTO kpiDepartmentDTO = new KpiDepartmentDTO(); 
        kpiDepartmentDTO.setDerpartmentId(this.derpartmentId);		
        kpiDepartmentDTO.setDerpartmentName(this.derpartmentName);		
        return kpiDepartmentDTO;
    }
}
