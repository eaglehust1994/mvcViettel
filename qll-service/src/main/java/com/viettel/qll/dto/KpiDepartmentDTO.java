package com.viettel.qll.dto;

import com.viettel.qll.bo.KpiDepartmentBO;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "KPI_DEPARTMENTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KpiDepartmentDTO extends QllBaseDTO<KpiDepartmentBO> {
	
	
	
	private java.lang.Long derpartmentId;
	private java.lang.String derpartmentName;

	private String text;
	private int start;
	private int maxResult;

    @Override
    public KpiDepartmentBO toModel() {
        KpiDepartmentBO kpiDepartmentBO = new KpiDepartmentBO();
        kpiDepartmentBO.setDerpartmentId(this.derpartmentId);
        kpiDepartmentBO.setDerpartmentName(this.derpartmentName);
        return kpiDepartmentBO;
    }

	@JsonProperty("derpartmentId")
    public java.lang.Long getDerpartmentId(){
		return derpartmentId;
    }
	
    public void setDerpartmentId(java.lang.Long derpartmentId){
		this.derpartmentId = derpartmentId;
    }	
	
	@JsonProperty("derpartmentName")
    public java.lang.String getDerpartmentName(){
		return derpartmentName;
    }
	
    public void setDerpartmentName(java.lang.String derpartmentName){
		this.derpartmentName = derpartmentName;
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
