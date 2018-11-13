package com.viettel.ktts2.rest;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.ktts2.business.CommonSysGroupBusiness;

public class CommonRsServiceImpl implements CommonRsService {

	@Autowired
	CommonSysGroupBusiness commonSysGroupBusiness;

	/**
	 * autocomplete group
	 */
	@Override
	public Response getSysGroupAjax(Long id) {
		return Response.ok(commonSysGroupBusiness.getSysGroupAjax(id)).build();
	}

}
