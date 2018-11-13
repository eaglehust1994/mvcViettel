package com.viettel.asset.dto.search;

/**
 * Để trả kết quả cho autocomplete công trình chức năng hình thành tài sản từ phiếu 
 * @author hanhls1
 *
 */
public class VConstructionOfferSlip {
	
	private Long constructId;
	private String constructName;
	private String constructCode;
	
	private String stationName;
	private String stationCode;
	
	private String contractCode;
	private String investProjectCode;
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	
	public Long getConstructId() {
		return constructId;
	}
	public void setConstructId(Long constructId) {
		this.constructId = constructId;
	}
	public String getConstructName() {
		return constructName;
	}
	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}
	
	public String getConstructCode() {
		return constructCode;
	}
	public void setConstructCode(String constructCode) {
		this.constructCode = constructCode;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getInvestProjectCode() {
		return investProjectCode;
	}
	public void setInvestProjectCode(String investProjectCode) {
		this.investProjectCode = investProjectCode;
	}
}
