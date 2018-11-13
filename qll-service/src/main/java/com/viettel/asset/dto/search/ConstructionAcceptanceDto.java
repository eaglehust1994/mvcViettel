package com.viettel.asset.dto.search;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.asset.dto.MerEntityDto;
import com.viettel.erp.utils.CustomJsonDateSerializer;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructionAcceptanceDto{
	private Long constructId;
	private String constrName;
	private String constrCode;
	private String stationCode;
	private String stationName;
	private Long stationId;
	private String code;// ma bien ban ban giao
	private java.util.List<MerEntityDto> lstMerEntity;//tai san cua cong trinhf
	private java.util.List<MerEntityConstructionAcceptanceDto> lstDataConstructionAcceptance;// danh sach chi phi
	
	
	private String investProjectName;
	private String investProjectCode;
	private Long investProjectId;
	
	private String handoverCode;
		
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date handoverDate;
        
        public String getHandoverCode() {
		return handoverCode;
	}

	public void setHandoverCode(String handoverCode) {
		this.handoverCode = handoverCode;
	}

	public java.util.Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(java.util.Date handoverDate) {
		this.handoverDate = handoverDate;
	}

		public String getInvestProjectName() {
		return investProjectName;
	}

	public void setInvestProjectName(String investProjectName) {
		this.investProjectName = investProjectName;
	}

	public String getInvestProjectCode() {
		return investProjectCode;
	}

	public void setInvestProjectCode(String investProjectCode) {
		this.investProjectCode = investProjectCode;
	}

	public Long getInvestProjectId() {
		return investProjectId;
	}

	public void setInvestProjectId(Long investProjectId) {
		this.investProjectId = investProjectId;
	}
		private Long constructionAcceptanceId;

    public Long getConstructionAcceptanceId() {
        return constructionAcceptanceId;
    }

    public void setConstructionAcceptanceId(Long constructionAcceptanceId) {
        this.constructionAcceptanceId = constructionAcceptanceId;
    }

    public List<MerEntityConstructionAcceptanceDto> getLstDataConstructionAcceptance() {
        return lstDataConstructionAcceptance;
    }

    public void setLstDataConstructionAcceptance(List<MerEntityConstructionAcceptanceDto> lstDataConstructionAcceptance) {
        this.lstDataConstructionAcceptance = lstDataConstructionAcceptance;
    }
	public Long getConstructId() {
		return constructId;
	}
	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}
	public String getConstrName() {
		return constrName;
	}
	public void setConstrName(String constructName) {
		this.constrName = constructName;
	}
	public String getConstrCode() {
		return constrCode;
	}
	public void setConstrCode(String constructCode) {
		this.constrCode = constructCode;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public java.util.List<MerEntityDto> getLstMerEntity() {
		return lstMerEntity;
	}
	public void setLstMerEntity(java.util.List<MerEntityDto> lstMerEntity) {
		this.lstMerEntity = lstMerEntity;
	}
	
	
	
}
