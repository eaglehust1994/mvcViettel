package com.viettel.qll.dto;


import java.util.List;

import com.viettel.asset.dto.ResultInfo;

public class TblKpiScoreDTOResponse {

	private List<TblKpiScoreDTO> listData;
	private ResultInfo resultInfo;
	private Integer total,size,start;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public List<TblKpiScoreDTO> getListData() {
		return listData;
	}
	public void setListData(List<TblKpiScoreDTO> listData) {
		this.listData = listData;
	}
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
}
