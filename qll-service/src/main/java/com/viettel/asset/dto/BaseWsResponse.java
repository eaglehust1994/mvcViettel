package com.viettel.asset.dto;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class BaseWsResponse {

	private ResultInfo resultInfo;

	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
