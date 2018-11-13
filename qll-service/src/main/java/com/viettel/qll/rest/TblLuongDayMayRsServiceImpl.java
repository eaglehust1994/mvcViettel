package com.viettel.qll.rest;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.TblLuongDayMayBusinessImpl;
import com.viettel.qll.dto.TblLuongDayMayDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class TblLuongDayMayRsServiceImpl implements TblLuongDayMayRsService {

	protected final Logger log = Logger.getLogger(TblLuongDayMayRsService.class);
	@Autowired
	TblLuongDayMayBusinessImpl tblLuongDayMayBusinessImpl;

	@Override
	public Response luongDayMay(TblLuongDayMayDTO obj,HttpServletRequest request) throws Exception {
		try {
			//DataListDTO data = tblQltsCongNoVatTuBusinessImpl.getAllCongNo(obj);
			String result = tblLuongDayMayBusinessImpl.tinhLuongNvDayMay(obj,request);
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
	public Response doSearch(TblLuongDayMayDTO obj) throws Exception {
		DataListDTO data = tblLuongDayMayBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
}
