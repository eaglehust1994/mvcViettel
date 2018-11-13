package com.viettel.ims.rest;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viettel.ims.bo.SysUserBO;
import com.viettel.ims.business.SysUserBusinessImpl;
import com.viettel.ims.dto.SysUserDTO;
import com.viettel.service.base.dto.DataListDTO;
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
	public Response doSearch(SysUserDTO obj) {
		List<SysUserDTO> ls = sysUserBusinessImpl.doSearch(obj);
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
	
	@Override
	public Response getById(Long id) {
		SysUserDTO obj = (SysUserDTO) sysUserBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	
	@Override
	public Response getForAutoComplete(SysUserDTO obj) {
		List<SysUserDTO> results = sysUserBusinessImpl.getForAutoComplete(obj);
		if (obj.getIsSize()){
			SysUserDTO moreObject = new SysUserDTO();
			moreObject.setSysUserId(0l);;
			
			results.add(moreObject);
		}
		return Response.ok(results).build();
	}
	
}
