package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
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
import com.viettel.qll.bo.TblDanhMucBO;
import com.viettel.qll.business.TblDanhMucBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.ims.dto.CntContractDTO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.erp.constant.ApplicationConstants;
// import com.viettel.service.base.dto.ActionListDTO;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;
import com.viettel.wms.dto.DepartmentDTO;

/**
 * @author hailh10
 */

public class TblDanhMucRsServiceImpl implements TblDanhMucRsService {

	protected final Logger log = Logger.getLogger(TblDanhMucRsService.class);
	@Autowired
	TblDanhMucBusinessImpl tblDanhMucBusinessImpl;

	@Override
	public Response doSearch(CntContractDTO obj) {
		// List<CntContractDTO> ls = cntContractBusinessImpl.doSearch(obj);
		// if (ls == null) {
		// return Response.status(Response.Status.BAD_REQUEST).build();
		// } else {
		// DataListDTO data = new DataListDTO();
		// data.setData(ls);
		// data.setTotal(ls.size());
		// data.setSize(ls.size());
		// data.setStart(1);
		// return Response.ok(data).build();
		// }
		return null;
	}

	@Override
	public Response doSearchDepartment(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getall(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getDeptForAutocomplete(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getDeptForAutocomplete(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getDeptForAutocomplete1(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getDeptForAutocomplete1(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response getDeptForAutocomplete1Popup(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getDeptForAutocomplete1Popup(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getDeptForAutocomplete2(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getDeptForAutocomplete2(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getForAutoCompleteDeptFor62DV(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getForAutoCompleteDeptFor62DV(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getDeptForAutocomplete3(TblDanhMucDTO obj) {
		List<TblDanhMucDTO> ls = tblDanhMucBusinessImpl.getDeptForAutocomplete3(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	

}
