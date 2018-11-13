package com.viettel.qll.dto;

import java.util.List;

import com.viettel.asset.dto.ResultInfo;

public class SysGroupDTOResponse {
	private ResultInfo resultInfo;
	 private List<SysGroupDTO> listData;
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	public List<SysGroupDTO> getListData() {
		return listData;
	}
	public void setListData(List<SysGroupDTO> listData) {
		this.listData = listData;
	}
}
