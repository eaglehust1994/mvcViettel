package com.viettel.asset.dto;

import java.util.List;

public class CatAssetCodeHistoryResponse {

	private List<CatAssetCodeHistory> lstCatAssetCodeHistory;
	private ResultInfo resultInfo;
	
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	public List<CatAssetCodeHistory> getLstCatAssetCodeHistory() {
		return lstCatAssetCodeHistory;
	}
	public void setLstCatAssetCodeHistory(List<CatAssetCodeHistory> lstCatAssetCodeHistory) {
		this.lstCatAssetCodeHistory = lstCatAssetCodeHistory;
	}
	
}
