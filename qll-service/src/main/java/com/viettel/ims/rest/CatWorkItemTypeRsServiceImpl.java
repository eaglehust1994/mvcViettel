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
import com.viettel.ims.bo.CatWorkItemTypeBO;
import com.viettel.ims.business.CatWorkItemTypeBusinessImpl;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class CatWorkItemTypeRsServiceImpl implements CatWorkItemTypeRsService {

	protected final Logger log = Logger.getLogger(CatWorkItemTypeRsService.class);
	@Autowired
	CatWorkItemTypeBusinessImpl catWorkItemTypeBusinessImpl;
	
	
	@Override
	public Response doSearch(CatWorkItemTypeDTO obj) {
		List<CatWorkItemTypeDTO> ls = catWorkItemTypeBusinessImpl.doSearch(obj);
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
		CatWorkItemTypeDTO obj = (CatWorkItemTypeDTO) catWorkItemTypeBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}


	

	
	@Override
	public Response getForAutoComplete(CatWorkItemTypeDTO obj) {
		List<CatWorkItemTypeDTO> results = catWorkItemTypeBusinessImpl.getForAutoComplete(obj);
		return Response.ok(results).build();
	}

	@Override
	public Response getForComboBox(CatWorkItemTypeDTO obj) {
		List<CatWorkItemTypeDTO> results = catWorkItemTypeBusinessImpl.getForComboBox(obj);
		return Response.ok(results).build();
	}
	
	
}
