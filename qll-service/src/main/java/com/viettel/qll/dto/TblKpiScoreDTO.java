package com.viettel.qll.dto;

import com.viettel.qll.bo.TblKpiScoreBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.List;
import com.viettel.asset.dto.ResultInfo;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializerDOng;
import com.viettel.erp.utils.CustomJsonDateSerializerDOng;



/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_KPI_SCOREBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKpiScoreDTO extends QllBaseDTO<TblKpiScoreBO> {
	
	private java.lang.Long kpiid;
	private java.lang.Long departmentid;
	private java.lang.String departmentname;
	private java.lang.Float scorebonus;
	private java.lang.Float scorepenalty;
	private java.lang.String reason;
	private java.lang.Long useridcreated;
	private java.lang.String usernamecreated;
	private java.lang.String fullnamecreated;
	private java.lang.Long departmentidcreated;
	private java.lang.String departmentnamecreated;
	
	private java.lang.Float scorebonusConfirm;
	private java.lang.Float scorepenaltyConfirm;

	private java.lang.Long useridModified;
	private java.lang.String usernameModified;
	private java.lang.String fullnameModified;
	
	private java.lang.Long departmentidModified;
	private java.lang.String departmentnameModified;
	
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	
	private java.util.Date createddate;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)

	private java.util.Date createddateFrom;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	
	private java.util.Date createddateTo;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	
	private java.util.Date modifieddate;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	
	private java.util.Date modifieddateFrom;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	
	private java.util.Date modifieddateTo;
	private String text;
	private String created_date,modified_date;
	private Float sumScore,sumScoreConfirm;
	private Float viewScore,viewScoreConfirm;
	private String checkScore;
	private String reasonConfirm;
	public String getReasonConfirm() {
		return reasonConfirm;
	}

	public void setReasonConfirm(String reasonConfirm) {
		this.reasonConfirm = reasonConfirm;
	}

	public Float getViewScore() {
		return viewScore;
	}

	public void setViewScore(Float viewScore) {
		this.viewScore = viewScore;
	}

	public Float getViewScoreConfirm() {
		return viewScoreConfirm;
	}

	public void setViewScoreConfirm(Float viewScoreConfirm) {
		this.viewScoreConfirm = viewScoreConfirm;
	}




	


	public String getCheckScore() {
		return checkScore;
	}

	public void setCheckScore(String checkScore) {
		this.checkScore = checkScore;
	}

	public Float getSumScore() {
		return sumScore;
	}

	public void setSumScore(Float sumScore) {
		this.sumScore = sumScore;
	}

	public Float getSumScoreConfirm() {
		return sumScoreConfirm;
	}

	public void setSumScoreConfirm(Float sumScoreConfirm) {
		this.sumScoreConfirm = sumScoreConfirm;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}


	
	public java.lang.Long getDepartmentidModified() {
		return departmentidModified;
	}

	public void setDepartmentidModified(java.lang.Long departmentidModified) {
		this.departmentidModified = departmentidModified;
	}

	public java.lang.String getDepartmentnameModified() {
		return departmentnameModified;
	}

	public void setDepartmentnameModified(java.lang.String departmentnameModified) {
		this.departmentnameModified = departmentnameModified;
	}



	private int start;
	private int maxResult;
	



	


	public java.lang.Float getScorebonusConfirm() {
		return scorebonusConfirm;
	}

	public void setScorebonusConfirm(java.lang.Float scorebonusConfirm) {
		this.scorebonusConfirm = scorebonusConfirm;
	}

	public java.lang.Float getScorepenaltyConfirm() {
		return scorepenaltyConfirm;
	}

	public void setScorepenaltyConfirm(java.lang.Float scorepenaltyConfirm) {
		this.scorepenaltyConfirm = scorepenaltyConfirm;
	}

	public java.lang.Long getUseridModified() {
		return useridModified;
	}

	public void setUseridModified(java.lang.Long useridModified) {
		this.useridModified = useridModified;
	}

	public java.lang.String getUsernameModified() {
		return usernameModified;
	}

	public void setUsernameModified(java.lang.String usernameModified) {
		this.usernameModified = usernameModified;
	}

	public java.lang.String getFullnameModified() {
		return fullnameModified;
	}

	public void setFullnameModified(java.lang.String fullnameModified) {
		this.fullnameModified = fullnameModified;
	}

	public String getText() {
		return text;
	}

	@Override
    public TblKpiScoreBO toModel() {
        TblKpiScoreBO tblKpiScoreBO = new TblKpiScoreBO();
        tblKpiScoreBO.setKpiid(this.kpiid);
        tblKpiScoreBO.setDepartmentid(this.departmentid);
        tblKpiScoreBO.setDepartmentname(this.departmentname);
        tblKpiScoreBO.setScorebonus(this.scorebonus);
        tblKpiScoreBO.setScorepenalty(this.scorepenalty);
        tblKpiScoreBO.setReason(this.reason);
        tblKpiScoreBO.setUseridcreated(this.useridcreated);
        tblKpiScoreBO.setUsernamecreated(this.usernamecreated);
        tblKpiScoreBO.setFullnamecreated(this.fullnamecreated);
        tblKpiScoreBO.setDepartmentidcreated(this.departmentidcreated);
        tblKpiScoreBO.setDepartmentnamecreated(this.departmentnamecreated);
        tblKpiScoreBO.setCreateddate(this.createddate);
        tblKpiScoreBO.setModifieddate(this.modifieddate);
        tblKpiScoreBO.setScorebonusConfirm(this.scorebonusConfirm);
        tblKpiScoreBO.setScorepenaltyConfirm(this.scorepenaltyConfirm);
        tblKpiScoreBO.setUseridModified(this.useridModified);
        tblKpiScoreBO.setUsernameModified(this.usernameModified);
        tblKpiScoreBO.setFullnameModified(this.fullnameModified);
        tblKpiScoreBO.setDepartmentidModified(this.departmentidModified);
        tblKpiScoreBO.setDepartmentnameModified(this.departmentnameModified);
        tblKpiScoreBO.setReasonConfirm(this.reasonConfirm);
        return tblKpiScoreBO;
    }

    @Override
     public Long getFWModelId() {
        return kpiid;
    }
   
    @Override
    public String catchName() {
        return getKpiid().toString();
    }
	
	@JsonProperty("kpiid")
    public java.lang.Long getKpiid(){
		return kpiid;
    }
	
    public void setKpiid(java.lang.Long kpiid){
		this.kpiid = kpiid;
    }	
	
	@JsonProperty("departmentid")
    public java.lang.Long getDepartmentid(){
		return departmentid;
    }
	
    public void setDepartmentid(java.lang.Long departmentid){
		this.departmentid = departmentid;
    }	
	
	@JsonProperty("departmentname")
    public java.lang.String getDepartmentname(){
		return departmentname;
    }
	
    public void setDepartmentname(java.lang.String departmentname){
		this.departmentname = departmentname;
    }	
	
	@JsonProperty("scorebonus")
    public java.lang.Float getScorebonus(){
		return scorebonus;
    }
	
    public void setScorebonus(java.lang.Float scorebonus){
		this.scorebonus = scorebonus;
    }	
	
	@JsonProperty("scorepenalty")
    public java.lang.Float getScorepenalty(){
		return scorepenalty;
    }
	
    public void setScorepenalty(java.lang.Float scorepenalty){
		this.scorepenalty = scorepenalty;
    }	
	
	@JsonProperty("reason")
    public java.lang.String getReason(){
		return reason;
    }
	
    public void setReason(java.lang.String reason){
		this.reason = reason;
    }	
	
	@JsonProperty("useridcreated")
    public java.lang.Long getUseridcreated(){
		return useridcreated;
    }
	
    public void setUseridcreated(java.lang.Long useridcreated){
		this.useridcreated = useridcreated;
    }	
	
	@JsonProperty("usernamecreated")
    public java.lang.String getUsernamecreated(){
		return usernamecreated;
    }
	
    public void setUsernamecreated(java.lang.String usernamecreated){
		this.usernamecreated = usernamecreated;
    }	
	
	@JsonProperty("fullnamecreated")
    public java.lang.String getFullnamecreated(){
		return fullnamecreated;
    }
	
    public void setFullnamecreated(java.lang.String fullnamecreated){
		this.fullnamecreated = fullnamecreated;
    }	
	
	@JsonProperty("departmentidcreated")
    public java.lang.Long getDepartmentidcreated(){
		return departmentidcreated;
    }
	
    public void setDepartmentidcreated(java.lang.Long departmentidcreated){
		this.departmentidcreated = departmentidcreated;
    }	
	
	@JsonProperty("departmentnamecreated")
    public java.lang.String getDepartmentnamecreated(){
		return departmentnamecreated;
    }
	
    public void setDepartmentnamecreated(java.lang.String departmentnamecreated){
		this.departmentnamecreated = departmentnamecreated;
    }	
	
	@JsonProperty("createddate")
    public java.util.Date getCreateddate(){
		return createddate;
    }
	
    public void setCreateddate(java.util.Date createddate){
		this.createddate = createddate;
    }	
	
	public java.util.Date getCreateddateFrom() {
    	return createddateFrom;
    }
	
    public void setCreateddateFrom(java.util.Date createddateFrom) {
    	this.createddateFrom = createddateFrom;
    }
	
	public java.util.Date getCreateddateTo() {
    	return createddateTo;
    }
	
    public void setCreateddateTo(java.util.Date createddateTo) {
    	this.createddateTo = createddateTo;
    }
	
	@JsonProperty("modifieddate")
    public java.util.Date getModifieddate(){
		return modifieddate;
    }
	
    public void setModifieddate(java.util.Date modifieddate){
		this.modifieddate = modifieddate;
    }	
	
	public java.util.Date getModifieddateFrom() {
    	return modifieddateFrom;
    }
	
    public void setModifieddateFrom(java.util.Date modifieddateFrom) {
    	this.modifieddateFrom = modifieddateFrom;
    }
	
	public java.util.Date getModifieddateTo() {
    	return modifieddateTo;
    }
	
    public void setModifieddateTo(java.util.Date modifieddateTo) {
    	this.modifieddateTo = modifieddateTo;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkScore == null) ? 0 : checkScore.hashCode());
		result = prime * result + ((created_date == null) ? 0 : created_date.hashCode());
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((createddateFrom == null) ? 0 : createddateFrom.hashCode());
		result = prime * result + ((createddateTo == null) ? 0 : createddateTo.hashCode());
		result = prime * result + ((departmentid == null) ? 0 : departmentid.hashCode());
		result = prime * result + ((departmentidModified == null) ? 0 : departmentidModified.hashCode());
		result = prime * result + ((departmentidcreated == null) ? 0 : departmentidcreated.hashCode());
		result = prime * result + ((departmentname == null) ? 0 : departmentname.hashCode());
		result = prime * result + ((departmentnameModified == null) ? 0 : departmentnameModified.hashCode());
		result = prime * result + ((departmentnamecreated == null) ? 0 : departmentnamecreated.hashCode());
		result = prime * result + ((fullnameModified == null) ? 0 : fullnameModified.hashCode());
		result = prime * result + ((fullnamecreated == null) ? 0 : fullnamecreated.hashCode());
		result = prime * result + ((kpiid == null) ? 0 : kpiid.hashCode());
		result = prime * result + maxResult;
		result = prime * result + ((modified_date == null) ? 0 : modified_date.hashCode());
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + ((modifieddateFrom == null) ? 0 : modifieddateFrom.hashCode());
		result = prime * result + ((modifieddateTo == null) ? 0 : modifieddateTo.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((scorebonus == null) ? 0 : scorebonus.hashCode());
		result = prime * result + ((scorebonusConfirm == null) ? 0 : scorebonusConfirm.hashCode());
		result = prime * result + ((scorepenalty == null) ? 0 : scorepenalty.hashCode());
		result = prime * result + ((scorepenaltyConfirm == null) ? 0 : scorepenaltyConfirm.hashCode());
		result = prime * result + start;
		result = prime * result + ((sumScore == null) ? 0 : sumScore.hashCode());
		result = prime * result + ((sumScoreConfirm == null) ? 0 : sumScoreConfirm.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((useridModified == null) ? 0 : useridModified.hashCode());
		result = prime * result + ((useridcreated == null) ? 0 : useridcreated.hashCode());
		result = prime * result + ((usernameModified == null) ? 0 : usernameModified.hashCode());
		result = prime * result + ((usernamecreated == null) ? 0 : usernamecreated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblKpiScoreDTO other = (TblKpiScoreDTO) obj;
		if (checkScore == null) {
			if (other.checkScore != null)
				return false;
		} else if (!checkScore.equals(other.checkScore))
			return false;
		if (created_date == null) {
			if (other.created_date != null)
				return false;
		} else if (!created_date.equals(other.created_date))
			return false;
		if (createddate == null) {
			if (other.createddate != null)
				return false;
		} else if (!createddate.equals(other.createddate))
			return false;
		if (createddateFrom == null) {
			if (other.createddateFrom != null)
				return false;
		} else if (!createddateFrom.equals(other.createddateFrom))
			return false;
		if (createddateTo == null) {
			if (other.createddateTo != null)
				return false;
		} else if (!createddateTo.equals(other.createddateTo))
			return false;
		if (departmentid == null) {
			if (other.departmentid != null)
				return false;
		} else if (!departmentid.equals(other.departmentid))
			return false;
		if (departmentidModified == null) {
			if (other.departmentidModified != null)
				return false;
		} else if (!departmentidModified.equals(other.departmentidModified))
			return false;
		if (departmentidcreated == null) {
			if (other.departmentidcreated != null)
				return false;
		} else if (!departmentidcreated.equals(other.departmentidcreated))
			return false;
		if (departmentname == null) {
			if (other.departmentname != null)
				return false;
		} else if (!departmentname.equals(other.departmentname))
			return false;
		if (departmentnameModified == null) {
			if (other.departmentnameModified != null)
				return false;
		} else if (!departmentnameModified.equals(other.departmentnameModified))
			return false;
		if (departmentnamecreated == null) {
			if (other.departmentnamecreated != null)
				return false;
		} else if (!departmentnamecreated.equals(other.departmentnamecreated))
			return false;
		if (fullnameModified == null) {
			if (other.fullnameModified != null)
				return false;
		} else if (!fullnameModified.equals(other.fullnameModified))
			return false;
		if (fullnamecreated == null) {
			if (other.fullnamecreated != null)
				return false;
		} else if (!fullnamecreated.equals(other.fullnamecreated))
			return false;
		if (kpiid == null) {
			if (other.kpiid != null)
				return false;
		} else if (!kpiid.equals(other.kpiid))
			return false;
		if (maxResult != other.maxResult)
			return false;
		if (modified_date == null) {
			if (other.modified_date != null)
				return false;
		} else if (!modified_date.equals(other.modified_date))
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (modifieddateFrom == null) {
			if (other.modifieddateFrom != null)
				return false;
		} else if (!modifieddateFrom.equals(other.modifieddateFrom))
			return false;
		if (modifieddateTo == null) {
			if (other.modifieddateTo != null)
				return false;
		} else if (!modifieddateTo.equals(other.modifieddateTo))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (scorebonus == null) {
			if (other.scorebonus != null)
				return false;
		} else if (!scorebonus.equals(other.scorebonus))
			return false;
		if (scorebonusConfirm == null) {
			if (other.scorebonusConfirm != null)
				return false;
		} else if (!scorebonusConfirm.equals(other.scorebonusConfirm))
			return false;
		if (scorepenalty == null) {
			if (other.scorepenalty != null)
				return false;
		} else if (!scorepenalty.equals(other.scorepenalty))
			return false;
		if (scorepenaltyConfirm == null) {
			if (other.scorepenaltyConfirm != null)
				return false;
		} else if (!scorepenaltyConfirm.equals(other.scorepenaltyConfirm))
			return false;
		if (start != other.start)
			return false;
		if (sumScore == null) {
			if (other.sumScore != null)
				return false;
		} else if (!sumScore.equals(other.sumScore))
			return false;
		if (sumScoreConfirm == null) {
			if (other.sumScoreConfirm != null)
				return false;
		} else if (!sumScoreConfirm.equals(other.sumScoreConfirm))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (useridModified == null) {
			if (other.useridModified != null)
				return false;
		} else if (!useridModified.equals(other.useridModified))
			return false;
		if (useridcreated == null) {
			if (other.useridcreated != null)
				return false;
		} else if (!useridcreated.equals(other.useridcreated))
			return false;
		if (usernameModified == null) {
			if (other.usernameModified != null)
				return false;
		} else if (!usernameModified.equals(other.usernameModified))
			return false;
		if (usernamecreated == null) {
			if (other.usernamecreated != null)
				return false;
		} else if (!usernamecreated.equals(other.usernamecreated))
			return false;
		return true;
	}
}
