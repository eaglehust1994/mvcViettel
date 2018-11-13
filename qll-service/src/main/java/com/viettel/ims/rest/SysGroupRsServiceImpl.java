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
import com.viettel.ims.bo.SysGroupBO;
import com.viettel.ims.business.SysGroupBusinessImpl;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class SysGroupRsServiceImpl implements SysGroupRsService {

	protected final Logger log = Logger.getLogger(SysGroupRsService.class);
	@Autowired
	SysGroupBusinessImpl sysGroupBusinessImpl;
	
	
	
	@Override
	public Response doSearch(SysGroupDTO obj) {
		List<SysGroupDTO> ls = sysGroupBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(obj.getTotalRecord());
			data.setSize(obj.getPageSize());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}
	
	@Override
	public Response getById(Long id) {
		SysGroupDTO obj = (SysGroupDTO) sysGroupBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	


	@Override
	public Response getForAutoComplete(SysGroupDTO obj) {
		List<SysGroupDTO> results = sysGroupBusinessImpl.getForAutoComplete(obj);
		return Response.ok(results).build();
	}

	@Override
	public Response getForComboBox(SysGroupDTO obj) {
		List<SysGroupDTO> results = sysGroupBusinessImpl.getForComboBox(obj);
		return Response.ok(results).build();
	}
	
}
