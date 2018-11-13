package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
import java.sql.Timestamp;
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
import com.viettel.qll.bo.TblLuongNvTramBO;
import com.viettel.qll.business.TblLuongNvTramBusinessImpl;
import com.viettel.qll.business.TblNgayCongBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.erp.constant.ApplicationConstants;
// import com.viettel.service.base.dto.ActionListDTO;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class TblLuongNvTramRsServiceImpl implements TblLuongNvTramRsService {


	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;
	@Context HttpServletRequest request;
	protected final Logger log = Logger.getLogger(TblLuongNvTramRsService.class);
	@Autowired
	TblLuongNvTramBusinessImpl tblLuongNvTramBusinessImpl;
	@Override
	public Response doSearch(TblLuongNvTramDTO obj) {
		DataListDTO data = tblLuongNvTramBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}
	@Override
	public Response tinhluong(TblLuongNvTramDTO obj) {
		String data = tblLuongNvTramBusinessImpl.tinhluong();
		return Response.ok(data).build();
	}

}
