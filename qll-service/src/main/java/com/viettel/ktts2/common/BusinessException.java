package com.viettel.ktts2.common;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -609099771903107222L;

	private List<Object> lstParam;
	
	public List<Object> getLstParam() {
		return lstParam;
	}

	public void setLstParam(List<Object> lstParam) {
		this.lstParam = lstParam;
	}

	public BusinessException(String arg0) {
		super(arg0);
	}
	
	public BusinessException(String arg0, Object... params) {
		super(arg0);
		lstParam = new ArrayList<>();
		for (Object object : params) {
			lstParam.add(object);
		}
	}
}
