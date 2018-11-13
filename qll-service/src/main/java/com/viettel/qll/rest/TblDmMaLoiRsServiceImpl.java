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
import com.viettel.qll.bo.TblDmMaLoiBO;
import com.viettel.qll.business.TblDmMaLoiBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblDmMaLoiDTO;
import com.viettel.erp.constant.ApplicationConstants;
// import com.viettel.service.base.dto.ActionListDTO;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class TblDmMaLoiRsServiceImpl implements TblDmMaLoiRsService {

	protected final Logger log = Logger.getLogger(TblDmMaLoiRsService.class);
	@Autowired
	TblDmMaLoiBusinessImpl tblDmMaLoiBusinessImpl;

}
