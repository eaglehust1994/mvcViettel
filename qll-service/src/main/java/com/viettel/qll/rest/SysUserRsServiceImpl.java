package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viettel.qll.bo.SysUserBO;
import com.viettel.qll.business.SysUserBusinessImpl;
import com.viettel.erp.dto.DataListDTO;
import com.viettel.qll.dto.SysUserDTO;
import com.viettel.erp.constant.ApplicationConstants;

import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class SysUserRsServiceImpl implements SysUserRsService {

	protected final Logger log = Logger.getLogger(SysUserRsService.class);
	@Autowired
	SysUserBusinessImpl sysUserBusinessImpl;
	

	
	@Override
	public Response getInfoUser(SysUserDTO obj) {
		List<SysUserDTO> ls = sysUserBusinessImpl.getInfoUser(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(ls.size());
			data.setSize(ls.size());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}
	

}
