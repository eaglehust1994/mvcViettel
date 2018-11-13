package com.viettel.qll.dto;

import com.viettel.qll.bo.KpiUserBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "KPI_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KpiUserDTO extends BaseFWDTOImpl<KpiUserBO> {

	private java.lang.String fullName;
	private java.lang.String employeeCode;
	private java.lang.Long userRole;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public KpiUserBO toModel() {
        KpiUserBO kpiUserBO = new KpiUserBO();
        kpiUserBO.setFullName(this.fullName);
        kpiUserBO.setEmployeeCode(this.employeeCode);
        kpiUserBO.setUserRole(this.userRole);
        return kpiUserBO;
    }

	@JsonProperty("fullName")
    public java.lang.String getFullName(){
		return fullName;
    }
	
    public void setFullName(java.lang.String fullName){
		this.fullName = fullName;
    }	
	
	@JsonProperty("employeeCode")
    public java.lang.String getEmployeeCode(){
		return employeeCode;
    }
	
    public void setEmployeeCode(java.lang.String employeeCode){
		this.employeeCode = employeeCode;
    }	
	
	public java.lang.Long getUserRole() {
		return userRole;
	}

	public void setUserRole(java.lang.Long userRole) {
		this.userRole = userRole;
	}

	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	

	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}
}
