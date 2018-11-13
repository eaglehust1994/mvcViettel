package com.viettel.qll.dto;

import com.viettel.qll.bo.TblTimeWorkBO;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateDeserializerDOng;
import com.viettel.erp.utils.CustomJsonDateSerializerDOng;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_TIME_WORKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblTimeWorkDTO extends QllBaseDTO<TblTimeWorkBO> {

	private java.lang.Long tblTimeWorkId;
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	private java.util.Date genTime;
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	private java.util.Date genTimeFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	private java.util.Date genTimeTo;
	private java.lang.String derpartment;
	private java.lang.String name;
	
	private int start;
	private int maxResult;
	
	private String timeIn,timeOut,statusIn,statusOut;
	
    public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getStatusIn() {
		return statusIn;
	}

	public void setStatusIn(String statusIn) {
		this.statusIn = statusIn;
	}

	public String getStatusOut() {
		return statusOut;
	}

	public void setStatusOut(String statusOut) {
		this.statusOut = statusOut;
	}

	@Override
    public TblTimeWorkBO toModel() {
        TblTimeWorkBO tblTimeWorkBO = new TblTimeWorkBO();
        tblTimeWorkBO.setTblTimeWorkId(this.tblTimeWorkId);
        tblTimeWorkBO.setGenTime(this.genTime);
        tblTimeWorkBO.setDerpartment(this.derpartment);
        tblTimeWorkBO.setName(this.name);
        return tblTimeWorkBO;
    }

    @Override
     public Long getFWModelId() {
        return tblTimeWorkId;
    }
   
    @Override
    public String catchName() {
        return getTblTimeWorkId().toString();
    }
	
	@JsonProperty("tblTimeWorkId")
    public java.lang.Long getTblTimeWorkId(){
		return tblTimeWorkId;
    }
	
    public void setTblTimeWorkId(java.lang.Long tblTimeWorkId){
		this.tblTimeWorkId = tblTimeWorkId;
    }	
	
	@JsonProperty("genTime")
    public java.util.Date getGenTime(){
		return genTime;
    }
	
    public void setGenTime(java.util.Date genTime){
		this.genTime = genTime;
    }	
	
	public java.util.Date getGenTimeFrom() {
    	return genTimeFrom;
    }
	
    public void setGenTimeFrom(java.util.Date genTimeFrom) {
    	this.genTimeFrom = genTimeFrom;
    }
	
	public java.util.Date getGenTimeTo() {
    	return genTimeTo;
    }
	
    public void setGenTimeTo(java.util.Date genTimeTo) {
    	this.genTimeTo = genTimeTo;
    }
	
	@JsonProperty("derpartment")
    public java.lang.String getDerpartment(){
		return derpartment;
    }
	
    public void setDerpartment(java.lang.String derpartment){
		this.derpartment = derpartment;
    }	
	
	@JsonProperty("name")
    public java.lang.String getName(){
		return name;
    }
	
    public void setName(java.lang.String name){
		this.name = name;
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
	
	
}
