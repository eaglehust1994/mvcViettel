package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viettel.qll.bo.TblLuongTungTramBO;
import com.viettel.qll.business.TblLuongTungTramBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.qll.dto.TblLuongTungTramDTO;
import com.viettel.erp.constant.ApplicationConstants;
// import com.viettel.service.base.dto.ActionListDTO;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class TblLuongTungTramRsServiceImpl implements TblLuongTungTramRsService {

	@Value("${folder_upload2}")
	private String folderUpload;
	@Value("${folder_upload}")
	private String folderTemp;
	@Context HttpServletRequest request;
	protected final Logger log = Logger.getLogger(TblLuongTungTramRsService.class);
	@Autowired
	TblLuongTungTramBusinessImpl tblLuongTungTramBusinessImpl;
	private String result;
	@Override
	public Response doSearch(TblLuongTungTramDTO obj) {
		DataListDTO data = tblLuongTungTramBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}
	@Override
	public Response exportExcelGrid(TblLuongTungTramDTO obj) throws Exception {
		try {
			String result = tblLuongTungTramBusinessImpl.exportExcelGrid(obj);
			if (result.isEmpty()) {
				return Response.ok().build();
			} else {
				return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	@Override
	public Response tinhLuong(TblLuongTungTramDTO obj) throws Exception {
		result = tblLuongTungTramBusinessImpl.tinhluong(obj);
			return Response.ok().build();
		
	}

}
