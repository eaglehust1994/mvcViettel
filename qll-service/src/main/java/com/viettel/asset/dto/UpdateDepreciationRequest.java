package com.viettel.asset.dto;

import java.util.List;

public class UpdateDepreciationRequest extends BaseWsRequest {

	private List<DepreciationInfo> lstDepreciationInfo;

	public List<DepreciationInfo> getLstDepreciationInfo() {
		return lstDepreciationInfo;
	}

	public void setLstDepreciationInfo(List<DepreciationInfo> lstDepreciationInfo) {
		this.lstDepreciationInfo = lstDepreciationInfo;
	}
	
	
}
