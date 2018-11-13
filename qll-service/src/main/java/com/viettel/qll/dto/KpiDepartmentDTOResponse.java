package com.viettel.qll.dto;

import java.util.List;

import com.viettel.asset.dto.ResultInfo;

public class KpiDepartmentDTOResponse {
	private ResultInfo resultInfo;
	 private List<KpiDepartmentDTO> listData;
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	public List<KpiDepartmentDTO> getListData() {
		return listData;
	}
	public void setListData(List<KpiDepartmentDTO> listData) {
		this.listData = listData;
	}
}
