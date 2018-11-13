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
import com.viettel.ims.bo.CatConstructionTypeBO;
import com.viettel.ims.business.CatConstructionTypeBusinessImpl;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.erp.constant.ApplicationConstants;
//import com.viettel.service.base.dto.ActionListDTO;
//import com.viettel.erp.utils.ExportExcel;
//import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class CatConstructionTypeRsServiceImpl implements CatConstructionTypeRsService {

	protected final Logger log = Logger.getLogger(CatConstructionTypeRsService.class);
	@Autowired
	CatConstructionTypeBusinessImpl catConstructionTypeBusinessImpl;
	
	
	@Override
	public Response doSearch(CatConstructionTypeDTO obj) {
		List<CatConstructionTypeDTO> ls = catConstructionTypeBusinessImpl.doSearch(obj);
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
		CatConstructionTypeDTO obj = (CatConstructionTypeDTO) catConstructionTypeBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getForAutoComplete(CatConstructionTypeDTO obj) {
		List<CatConstructionTypeDTO> results = catConstructionTypeBusinessImpl.getForAutoComplete(obj);
		return Response.ok(results).build();
	}

	@Override
	public Response getForComboBox(CatConstructionTypeDTO obj) {
		List<CatConstructionTypeDTO> results = catConstructionTypeBusinessImpl.getForComboBox(obj);
		return Response.ok(results).build();
	}

}
