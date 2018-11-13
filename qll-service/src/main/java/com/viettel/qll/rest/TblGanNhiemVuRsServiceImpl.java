package com.viettel.qll.rest;



import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.viettel.qll.business.TblGanNhiemVuBusinessImpl;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.dto.DataListDTO;


/**
 * @author hailh10
 */
 
public class TblGanNhiemVuRsServiceImpl implements TblGanNhiemVuRsService {

	protected final Logger log = Logger.getLogger(TblGanNhiemVuRsService.class);
	@Autowired
	TblGanNhiemVuBusinessImpl tblGanNhiemVuBusinessImpl;
	
	
	
	@Override
	public Response doSearch(TblGanNhiemVuDTO obj) throws Exception {
		DataListDTO data = tblGanNhiemVuBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response getEmpByWorkId(TblGanNhiemVuDTO obj) throws Exception {
		DataListDTO data = tblGanNhiemVuBusinessImpl.getEmpByWorkId(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}

	@Override
	public Response deleteObj(TblGanNhiemVuDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblGanNhiemVuBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response deleteListObj(TblGanNhiemVuDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblGanNhiemVuBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response saveGanNv(TblGanNhiemVuDTO obj) {
		// TODO Auto-generated method stub
		long ls = tblGanNhiemVuBusinessImpl.saveGanNv(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response saveAssignListTask(TblGanNhiemVuDTO obj) {
		// TODO Auto-generated method stub
		long ls = tblGanNhiemVuBusinessImpl.saveAssignListTask(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response DetailNV(TblGanNhiemVuDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblGanNhiemVuBusinessImpl.DetailNV(obj,request);
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
	public Response getDetailInfo(TblGanNhiemVuDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblGanNhiemVuBusinessImpl.getDetailInfo(obj,request);
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
	public Response getForAutoCompleteTenCv(TblGanNhiemVuDTO obj) {
		List<TblGanNhiemVuDTO> ls = tblGanNhiemVuBusinessImpl.getForAutoCompleteTenCv(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response getAutoMaViTriForShd(TblQlCvPtkDTO obj) {
		List<TblQlCvPtkDTO> ls = tblGanNhiemVuBusinessImpl.getAutoMaViTriForShd(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response updateGNV(TblGanNhiemVuDTO obj) throws Exception {
		long data = tblGanNhiemVuBusinessImpl.updateGNV(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	

}
